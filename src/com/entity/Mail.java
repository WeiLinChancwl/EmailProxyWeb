/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Mail
 * Author:   陈伟林
 * Date:     2019/2/24 23:43
 * Description: 邮箱bean
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.entity;

/**
 * 〈一句话功能简述〉<br> 
 * 〈邮箱bean〉
 *
 * @author 陈伟林
 * @create 2019/2/24
 * @since 1.0.0
 */
public class Mail {
    private int mail_id;
    private String to_mail;
    private String from_mail;
    private String subject;
    private String wcc;
    private String bcc;
    private String content;
    private String date_mail;
    private int type_mail;
    private int flag_read;
    private String account_id;
    private int flag_status;

    public int getMail_id() {
        return mail_id;
    }

    public void setMail_id(int mail_id) {
        this.mail_id = mail_id;
    }

    public String getTo_mail() {
        return to_mail;
    }

    public void setTo_mail(String to_mail) {
        this.to_mail = to_mail;
    }

    public String getFrom_mail() {
        return from_mail;
    }

    public void setFrom_mail(String from_mail) {
        this.from_mail = from_mail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getWcc() {
        return wcc;
    }

    public void setWcc(String wcc) {
        this.wcc = wcc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_mail() {
        return date_mail;
    }

    public void setDate_mail(String date_mail) {
        this.date_mail = date_mail;
    }

    public int getType_mail() {
        return type_mail;
    }

    public void setType_mail(int type_mail) {
        this.type_mail = type_mail;
    }

    public int getFlag_read() {
        return flag_read;
    }

    public void setFlag_read(int flag_read) {
        this.flag_read = flag_read;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public int getFlag_status() {
        return flag_status;
    }

    public void setFlag_status(int flag_status) {
        this.flag_status = flag_status;
    }
}