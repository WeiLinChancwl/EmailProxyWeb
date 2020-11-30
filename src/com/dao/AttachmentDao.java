/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: AttachmentDao
 * Author:   陈伟林
 * Date:     2019/3/13 20:59
 * Description: 邮件附件操作
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dao;

import com.entity.Attachment;
import com.entity.Contact;
import com.util.Constant;

import java.sql.*;
import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈邮件附件操作〉
 *
 * @author 陈伟林
 * @create 2019/3/13
 * @since 1.0.0
 */
public class AttachmentDao {
    public static void insertAttachment(Attachment attachment) {
        /**
         * 增加附件
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        try {
            con = DBConnection.getConnection();
            String sql = "insert into attachment(attachment_url, filename, filesize, mail_id, account_id, attachment_type)" +
                    "values(?, ?, ?, ?, ?, ?)";
            pStatement = con.prepareStatement(sql);
            pStatement.setString(1, attachment.getAttachment_url());
            pStatement.setString(2, attachment.getFilename());
            pStatement.setString(3, attachment.getFilesize());
            pStatement.setInt(4, attachment.getMail_id());
            pStatement.setString(5, attachment.getAccount_id());
            pStatement.setInt(6, attachment.getAttachment_type());
            pStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
    }

    public static void deleteAttachment(Attachment attachment) {
        /**
         * 删除附件
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            con = DBConnection.getConnection();
            String sql = "delete from attachment where attachment_id=? and account_id=?";
            pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, attachment.getAttachment_id());
            pStatement.setString(2, attachment.getAccount_id());
            pStatement.executeUpdate();
            System.out.print("删除成功");
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
    }

    public static ArrayList<Attachment> searchAttachment(String keyword) {
        /**
         * 查找附件
         */
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        String patternString = null;	//使用正则表达式
        ArrayList<Attachment> attachments = new ArrayList<>();
        int n = 6;
        try {
            patternString = "^" + keyword + ".*";
            con = DBConnection.getConnection();
            stat = con.createStatement();
            String sql = "select * from attachment where account_id = " + "'" + Constant.userName + "'";
            rs = stat.executeQuery(sql);
            while(rs.next()) {
                boolean flag = false;
                for(int i = 1; i < 4; i++) {
                    String aString = rs.getString(i);
                    if(aString.matches(patternString)) {
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                   Attachment attachment = new Attachment();
                   attachment.setAttachment_id(rs.getInt(1));
                   attachment.setAttachment_url(rs.getString(2));
                   attachment.setFilename(rs.getString(3));
                   attachment.setFilesize(rs.getString(4));
                   attachment.setMail_id(rs.getInt(5));
                   attachment.setAccount_id(rs.getString(6));
                   attachment.setAttachment_type(rs.getInt(7));
                   attachments.add(attachment);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return attachments;
    }

    public static ArrayList<Attachment> Query(int type) {
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Attachment> arr = new ArrayList<>();
        try {
            con = DBConnection.getConnection();
            String sql = "select * from attachment where account_id = ? and attachment_type= ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, Constant.userName);
            stmt.setInt(2, type);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
               Attachment attachment = new Attachment();
               attachment.setAttachment_id(rs.getInt(1));
               attachment.setAttachment_url(rs.getString(2));
               attachment.setFilename(rs.getString(3));
               attachment.setFilesize(rs.getString(4));
               attachment.setMail_id(rs.getInt(5));
               attachment.setAccount_id(rs.getString(6));
               attachment.setAttachment_type(rs.getInt(7));
               arr.add(attachment);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return arr;
    }

    public static Attachment findAttachmentById(int attachment_id) {
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Attachment attachment = null;
        try {
            con = DBConnection.getConnection();
            String sql = "select * from attachment where account_id = ? and attachment_id = ?";
            pStatement = con.prepareStatement(sql);
            pStatement.setString(1, Constant.userName);
            pStatement.setInt(2, attachment_id);
            rs = pStatement.executeQuery();
            while(rs.next()) {
                attachment = new Attachment();
                attachment.setAttachment_id(rs.getInt(1));
                attachment.setAttachment_url(rs.getString(2));
                attachment.setFilename(rs.getString(3));
                attachment.setFilesize(rs.getString(4));
                attachment.setMail_id(rs.getInt(5));
                attachment.setAccount_id(rs.getString(6));
                attachment.setAttachment_type(rs.getInt(7));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return attachment;
    }
    public static Attachment findAttachmentByMail(int mail_id) {
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Attachment attachment = null;
        try {
            con = DBConnection.getConnection();
            String sql = "select * from attachment where account_id = ? and mail_id = ?";
            pStatement = con.prepareStatement(sql);
            pStatement.setString(1, Constant.userName);
            pStatement.setInt(2, mail_id);
            rs = pStatement.executeQuery();
            while(rs.next()) {
                attachment = new Attachment();
                attachment.setAttachment_id(rs.getInt(1));
                attachment.setAttachment_url(rs.getString(2));
                attachment.setFilename(rs.getString(3));
                attachment.setFilesize(rs.getString(4));
                attachment.setMail_id(rs.getInt(5));
                attachment.setAccount_id(rs.getString(6));
                attachment.setAttachment_type(rs.getInt(7));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return attachment;
    }
}