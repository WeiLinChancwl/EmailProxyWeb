/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: contact
 * Author:   陈伟林
 * Date:     2019/2/24 23:50
 * Description: 联系人bean
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.entity;

/**
 * 〈一句话功能简述〉<br> 
 * 〈联系人bean〉
 *
 * @author 陈伟林
 * @create 2019/2/24
 * @since 1.0.0
 */
public class Contact {
    private int contact_id; //邮箱编号
    private String name;    //姓名
    private String email;  //电子邮箱
    private String tel;    //电话
    private String cate_id;     //类别
    private String account_id; //账号

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
}