/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ContactDao
 * Author:   陈伟林
 * Date:     2019/2/25 0:04
 * Description: 联系人操作
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dao;

import com.entity.Contact;
import com.util.Constant;

import java.sql.*;
import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈联系人操作〉
 *
 * @author 陈伟林
 * @create 2019/2/25
 * @since 1.0.0
 */
public class ContactDao {
    public static void insertContact(Contact contact) {
        /**
         * 增加一位联系人
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        try {
            con = DBConnection.getConnection();
            String sql = "insert into contact(cname, email, tel, cate_id, account_id)" +
                    "values(?, ?, ?, ?, ?)";
            pStatement = con.prepareStatement(sql);
            pStatement.setString(1, contact.getName());
            pStatement.setString(2, contact.getEmail());
            pStatement.setString(3, contact.getTel());
            pStatement.setString(4, contact.getCate_id());
            pStatement.setString(5, contact.getAccount_id());
            pStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
    }

    public static void deleteContact(Contact contact) {
        /**
         * 删除一位联系人
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            con = DBConnection.getConnection();
            String sql = "delete from contact where contact_id=? and account_id=?";
            pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, contact.getContact_id());
            pStatement.setString(2, contact.getAccount_id());
            pStatement.executeUpdate();
            System.out.print("删除成功");
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
    }

    public static ArrayList<Contact> searchContact(String keyword) {
        /**
         * 查找联系人
         */
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        String patternString = null;	//使用正则表达式
        ArrayList<Contact> contacts = new ArrayList<>();
        int n = 6;
        try {
            patternString = "^" + keyword + ".*";
            con = DBConnection.getConnection();
            stat = con.createStatement();
            String sql = "select * from contact where account_id = " + "'" + Constant.userName + "'";
            rs = stat.executeQuery(sql);
            while(rs.next()) {
                boolean flag = false;
                for(int i = 1; i < 5; i++) {
                    String aString = rs.getString(i);
                    if(aString.matches(patternString)) {
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                   Contact contact = new Contact();
                   contact.setContact_id(rs.getInt(1));
                   contact.setName(rs.getString(2));
                   contact.setEmail(rs.getString(3));
                   contact.setTel(rs.getString(4));
                   contact.setCate_id(rs.getString(5));
                   contact.setAccount_id(rs.getString(6));
                   contacts.add(contact);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return contacts;
    }

    public static void updateContact(Contact contact) {
        /**
         * 更新联系人
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            String sql = "update contact set cname = ?, email = ?, tel = ?, cate_id = ?" +
                    " where contact_id = ? and account_id = ?";
            pStatement = con.prepareStatement(sql);
            pStatement.setString(1, contact.getName());
            pStatement.setString(2, contact.getEmail());
            pStatement.setString(3, contact.getTel());
            pStatement.setString(4, contact.getCate_id());
            pStatement.setInt(5, contact.getContact_id());
            pStatement.setString(6, contact.getAccount_id());
            pStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
    }

    public static Contact findContactById(int index) {
        /**
         * 根据ID寻找联系人
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Contact contact = null;
        try {
            con = DBConnection.getConnection();
            String sql = "select * from contact where contact_id = ? and account_id = ?";
            pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, index);
            pStatement.setString(2, Constant.userName);
            rs = pStatement.executeQuery();
            while(rs.next()) {
                contact = new Contact();
                contact.setContact_id(rs.getInt("contact_id"));
                contact.setName(rs.getString("cname"));
                contact.setEmail(rs.getString("email"));
                contact.setTel(rs.getString("tel"));
                contact.setCate_id(rs.getString("cate_id"));
                contact.setAccount_id(Constant.userName);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return contact;
    }

    public static ArrayList<Contact> Query() {
        Connection con = null;
        Statement stmt = null;
        ArrayList<Contact> arr = new ArrayList<>();
        try {
            con = DBConnection.getConnection();
            String sql = "select contact_id, cname, email, tel, cate_id from contact where account_id = " + "'"+ Constant.userName+"'";
            //System.out.println(sql);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Contact contact = new Contact();
                contact.setContact_id(rs.getInt("contact_id"));
                contact.setName(rs.getString("cname"));
                contact.setEmail(rs.getString("email"));
                contact.setTel(rs.getString("tel"));
                contact.setCate_id(rs.getString("cate_id"));
                contact.setAccount_id(Constant.userName);
                arr.add(contact);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return arr;
    }
}