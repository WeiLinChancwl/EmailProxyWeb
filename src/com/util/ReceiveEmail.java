/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ReceiveEmail
 * Author:   陈伟林
 * Date:     2019/3/15 14:35
 * Description: 接收邮件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.util;

/**
 * 〈一句话功能简述〉<br> 
 * 〈接收邮件〉
 *
 * @author 陈伟林
 * @create 2019/3/15
 * @since 1.0.0
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TreeSet;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;

import com.dao.AttachmentDao;
import com.dao.MailDao;
import com.entity.Attachment;
import com.entity.EmailAccount;
import com.entity.Mail;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import org.junit.Test;

public class ReceiveEmail {
    private static String recipientHost;     //邮箱服务器Host
    private static String recipientAccount;     //邮箱账户
    private static String recipientPassword;    //账户密码
    private static String DIR="D:\\mailTemp\\";      //保存附件位置
    private  static  Properties props = null;

    public ReceiveEmail(EmailAccount emailAccount){
        String email_type = emailAccount.getEmail_type();
        recipientAccount = emailAccount.getEmail_account();
        recipientPassword = emailAccount.getEmail_pwd();
        recipientHost = "imap." + email_type; //例如 imap.163.com

        props = new Properties();
        props.setProperty("mail.store.protocol", "imap");       // 协议
        props.setProperty("mail.imap.port", "143");             // 端口
        if(email_type.equals("qq.com")) {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String imapPort = "993";
            recipientHost = "imap." + email_type; //例如 imap.qq.com
            props.setProperty("mail.imap.port", imapPort);
            props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.imap.socketFactory.fallback", "false");
            props.setProperty("mail.imap.socketFactory.port", imapPort);
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            props.setProperty("mail.imap.auth.login.disable", "true");
        }
        props.setProperty("mail.imap.host", recipientHost);    // imap服务器
        File dir = new File(DIR);
        if(!dir.exists())
            dir.mkdirs();
    }

    @Test
    public void receive() throws Exception {

        // 创建Session实例对象
        Session session = Session.getInstance(props);
        IMAPStore store = (IMAPStore)session.getStore("imap");
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
        store.connect(recipientHost, recipientAccount, recipientPassword);

        // 获得收件箱
        IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX");
        /* Folder.READ_ONLY：只读权限
         * Folder.READ_WRITE：可读可写（可以修改邮件的状态）
         */
        folder.open(Folder.READ_WRITE); //打开收件箱
        System.out.println("未读邮件数：" + folder.getUnreadMessageCount());
        if(folder.getUnreadMessageCount() != 0) {
            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false); //false代表未读，true代表已读
            Message messages[] = folder.search(ft); //根据设置好的条件获取message
            parseMessage(messages);
            folder.setFlags(messages, new Flags(Flags.Flag.SEEN), true);
        }

        //释放资源
        folder.close(true);
        store.close();
    }

    /**
     * 解析邮件
     * @param messages 要解析的邮件列表
     */
    public  void parseMessage(Message ...messages) throws MessagingException, IOException {
        if (messages == null || messages.length < 1)
            throw new MessagingException("未找到要解析的邮件!");

        // 解析所有邮件
        for (int i = 0, count = messages.length; i < count; i++) {
            MimeMessage msg = (MimeMessage) messages[i];
            Mail mail = new Mail();
            mail.setMail_id(MailDao.getNextMail_id());
            mail.setSubject(getSubject(msg));
//            System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");
//            System.out.println("主题: " + getSubject(msg));
            mail.setFrom_mail(getFrom(msg));
//            System.out.println("发件人: " + getFrom(msg));
            mail.setTo_mail(getReceiveAddress(msg, null));
//            System.out.println("收件人：" + getReceiveAddress(msg, null));
            mail.setDate_mail(getSentDate(msg, null));
//            System.out.println("发送时间：" + getSentDate(msg, null));
            int flag_read;
            if(isSeen(msg))
                flag_read = 1;
            else
                flag_read = 0;
            mail.setFlag_read(flag_read);
//            System.out.println("是否已读：" + isSeen(msg));
//            System.out.println("邮件优先级：" + getPriority(msg));
//            System.out.println("是否需要回执：" + isReplySign(msg));
//            System.out.println("邮件大小：" + msg.getSize() * 1024 + "kb");
            boolean isContainerAttachment = isContainAttachment(msg);
//            System.out.println("是否包含附件：" + isContainerAttachment);
            if (isContainerAttachment) {
                saveAttachment(msg, DIR + msg.getSubject() + "_"); //保存附件
            }
            StringBuffer content = new StringBuffer(30);
            getMailTextContent(msg, content);
//            System.out.println("邮件正文：" + (content.length() > 100 ? content.substring(0,100) + "..." : content));
            mail.setContent(content.toString());
            mail.setType_mail(2);
            mail.setAccount_id(Constant.userName);
            MailDao.insertMail(mail);
//            System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");
//            System.out.println();
        }
    }

    /**
     * 获得邮件主题
     * @param msg 邮件内容
     * @return 解码后的邮件主题
     */
    public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {
        return MimeUtility.decodeText(msg.getSubject());
    }

    /**
     * 获得邮件发件人
     * @param msg 邮件内容
     * @return 姓名 <Email地址>
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
        String from = "";
        Address[] froms = msg.getFrom();
        if (froms.length < 1)
            throw new MessagingException("没有发件人!");

        InternetAddress address = (InternetAddress) froms[0];
        String person = address.getPersonal();
        if (person != null) {
            person = MimeUtility.decodeText(person) + " ";
        } else {
            person = "";
        }
        from = person + "<" + address.getAddress() + ">";

        return from;
    }

    /**
     * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人
     * <p>Message.RecipientType.TO  收件人</p>
     * <p>Message.RecipientType.CC  抄送</p>
     * <p>Message.RecipientType.BCC 密送</p>
     * @param msg 邮件内容
     * @param type 收件人类型
     * @return 收件人1 <邮件地址1>, 收件人2 <邮件地址2>, ...
     * @throws MessagingException
     */
    public static String getReceiveAddress(MimeMessage msg, Message.RecipientType type) throws MessagingException {
        StringBuffer receiveAddress = new StringBuffer();
        Address[] addresss = null;
        if (type == null) {
            addresss = msg.getAllRecipients();
        } else {
            addresss = msg.getRecipients(type);
        }

        if (addresss == null || addresss.length < 1)
            return "无";
        for (Address address : addresss) {
            InternetAddress internetAddress = (InternetAddress)address;
            receiveAddress.append(internetAddress.toUnicodeString()).append(",");
        }

        receiveAddress.deleteCharAt(receiveAddress.length()-1); //删除最后一个逗号

        return receiveAddress.toString();
    }

    /**
     * 获得邮件发送时间
     * @param msg 邮件内容
     * @return yyyy年mm月dd日 星期X HH:mm
     * @throws MessagingException
     */
    public static String getSentDate(MimeMessage msg, String pattern) throws MessagingException {
        Date receivedDate = msg.getSentDate();
        if (receivedDate == null)
            return "";

        if (pattern == null || "".equals(pattern))
            pattern = "yyyy年MM月dd日 E HH:mm ";

        return new SimpleDateFormat(pattern).format(receivedDate);
    }

    /**
     * 判断邮件中是否包含附件
     * @return 邮件中存在附件返回true，不存在返回false
     * @throws MessagingException
     * @throws IOException
     */
    public static boolean isContainAttachment(Part part) throws MessagingException, IOException {
        boolean flag = false;
        if (part.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    flag = true;
                } else if (bodyPart.isMimeType("multipart/*")) {
                    flag = isContainAttachment(bodyPart);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("application") != -1) {
                        flag = true;
                    }

                    if (contentType.indexOf("name") != -1) {
                        flag = true;
                    }
                }

                if (flag) break;
            }
        } else if (part.isMimeType("message/rfc822")) {
            flag = isContainAttachment((Part)part.getContent());
        }
        return flag;
    }

    /**
     * 判断邮件是否已读
     * @param msg 邮件内容
     * @return 如果邮件已读返回true,否则返回false
     * @throws MessagingException
     */
    public static boolean isSeen(MimeMessage msg) throws MessagingException {
        return msg.getFlags().contains(Flags.Flag.SEEN);
    }

    /**
     * 判断邮件是否需要阅读回执
     * @param msg 邮件内容
     * @return 需要回执返回true,否则返回false
     * @throws MessagingException
     */
    public static boolean isReplySign(MimeMessage msg) throws MessagingException {
        boolean replySign = false;
        String[] headers = msg.getHeader("Disposition-Notification-To");
        if (headers != null)
            replySign = true;
        return replySign;
    }

    /**
     * 获得邮件的优先级
     * @param msg 邮件内容
     * @return 1(High):紧急  3:普通(Normal)  5:低(Low)
     * @throws MessagingException
     */
    public  String getPriority(MimeMessage msg) throws MessagingException {
        String priority = "普通";
        String[] headers = msg.getHeader("X-Priority");
        if (headers != null) {
            String headerPriority = headers[0];
            if (headerPriority.indexOf("1") != -1 || headerPriority.indexOf("High") != -1)
                priority = "紧急";
            else if (headerPriority.indexOf("5") != -1 || headerPriority.indexOf("Low") != -1)
                priority = "低";
            else
                priority = "普通";
        }
        return priority;
    }

    /**
     * 获得邮件文本内容
     * @param part 邮件体
     * @param content 存储邮件文本内容的字符串
     * @throws MessagingException
     * @throws IOException
     */
    public  void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part)part.getContent(),content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart,content);
            }
        }
    }

    /**
     * 保存附件
     * @param part 邮件中多个组合体中的其中一个组合体
     * @param destDir  附件保存目录
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public  void saveAttachment(Part part, String destDir) throws UnsupportedEncodingException, MessagingException,
            FileNotFoundException, IOException {
        if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();    //复杂体邮件
            //复杂体邮件包含多个邮件体
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                //获得复杂体邮件中其中一个邮件体
                BodyPart bodyPart = multipart.getBodyPart(i);
                //某一个邮件体也有可能是由多个邮件体组成的复杂体
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    InputStream is = bodyPart.getInputStream();
                    saveFile(is, destDir, decodeText(bodyPart.getFileName()));
                    Attachment attachment = new Attachment();
                    attachment.setMail_id(MailDao.getNextMail_id());
                    attachment.setAttachment_url(destDir);
                    attachment.setFilename(decodeText(bodyPart.getFileName()));
                    attachment.setAccount_id(Constant.userName);
                    attachment.setFilesize(bodyPart.getSize()/1024 + "KB");
                    attachment.setAttachment_type(1);
                    AttachmentDao.insertAttachment(attachment);
                } else if (bodyPart.isMimeType("multipart/*")) {
                    saveAttachment(bodyPart,destDir);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
                        saveFile(bodyPart.getInputStream(), destDir, decodeText(bodyPart.getFileName()));
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            saveAttachment((Part) part.getContent(),destDir);
        }
    }

    /**
     * 读取输入流中的数据保存至指定目录
     * @param is 输入流
     * @param fileName 文件名
     * @param destDir 文件存储目录
     * @throws FileNotFoundException
     * @throws IOException
     */
    private  void saveFile(InputStream is, String destDir, String fileName)
            throws FileNotFoundException, IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(new File(destDir + fileName)));
        int len = -1;
        while ((len = bis.read()) != -1) {
            bos.write(len);
            bos.flush();
        }
        bos.close();
        bis.close();
    }

    /**
     * 文本解码
     * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本
     * @return 解码后的文本
     * @throws UnsupportedEncodingException
     */
    public  String decodeText(String encodeText) throws UnsupportedEncodingException {
        if (encodeText == null || "".equals(encodeText)) {
            return "";
        } else {
            return MimeUtility.decodeText(encodeText);
        }
    }

    //从一堆邮件中获取最近的邮件的时间
    public String getTime(Message[] messages) throws MessagingException {
        //利用TreeSet自动排序,获取最新时间
        TreeSet<String> treeSet = new TreeSet();
        MimeMessage msg;
        String sentDate;
        for (int i = 0, count = messages.length; i < count; i++) {
            msg = (MimeMessage) messages[i];
            sentDate = getSentDate(msg, null);
            treeSet.add(sentDate);
        }
        return treeSet.last();
    }

    /**
     * 获取最新时间的邮件
     * @param messages
     * @return
     */
    public MimeMessage getNewMsg(String last,Message[] messages) throws MessagingException {

        MimeMessage msg = null;
        for (Message message : messages) {
            MimeMessage mimeMessage = (MimeMessage) message;
            if(getSentDate(mimeMessage,null).equals(last)){
                msg = mimeMessage;
            }
        }
        return msg;
    }
}