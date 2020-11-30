/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: EmailAccount
 * Author:   陈伟林
 * Date:     2019/3/3 23:51
 * Description: 邮箱绑定
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.entity;

/**
 * 〈一句话功能简述〉<br> 
 * 〈邮箱绑定〉
 *
 * @author 陈伟林
 * @create 2019/3/3
 * @since 1.0.0
 */
public class EmailAccount {
    private int email_account_id;
    private String email_account;
    private String email_pwd;
    private String email_type;
    private String account_id;

    public int getEmail_account_id() {
        return email_account_id;
    }

    public void setEmail_account_id(int email_account_id) {
        this.email_account_id = email_account_id;
    }

    public String getEmail_account() {
        return email_account;
    }

    public void setEmail_account(String email_account) {
        this.email_account = email_account;
    }

    public String getEmail_pwd() {
        return email_pwd;
    }

    public void setEmail_pwd(String email_pwd) {
        this.email_pwd = email_pwd;
    }

    public String getEmail_type() {
        return email_type;
    }

    public void setEmail_type(String email_type) {
        this.email_type = email_type;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
}