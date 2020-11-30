/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Attachment
 * Author:   陈伟林
 * Date:     2019/3/13 20:54
 * Description: 邮件附件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.entity;

/**
 * 〈一句话功能简述〉<br> 
 * 〈邮件附件〉
 *
 * @author 陈伟林
 * @create 2019/3/13
 * @since 1.0.0
 */
public class Attachment {
    private int attachment_id;
    private String attachment_url;
    private String filename;
    private String filesize;
    private int mail_id;
    private String account_id;
    private int attachment_type;

    public int getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(int attachment_id) {
        this.attachment_id = attachment_id;
    }

    public String getAttachment_url() {
        return attachment_url;
    }

    public void setAttachment_url(String attachment_url) {
        this.attachment_url = attachment_url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public int getMail_id() {
        return mail_id;
    }

    public void setMail_id(int mail_id) {
        this.mail_id = mail_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public int getAttachment_type() {
        return attachment_type;
    }

    public void setAttachment_type(int attachment_type) {
        this.attachment_type = attachment_type;
    }
}