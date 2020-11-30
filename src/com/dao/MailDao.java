/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MailDao
 * Author:   陈伟林
 * Date:     2019/2/24 23:58
 * Description: 邮件操作
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dao;

import com.entity.Mail;
import com.util.Constant;

import java.sql.*;
import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br>
 * 〈邮件操作〉
 *
 * @author 陈伟林
 * @create 2019/2/24
 * @since 1.0.0
 */
public class MailDao {

    public static void insertMail(Mail mail) {
        /**
         * 增加一封邮件
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            con = DBConnection.getConnection();
            String sql = "insert into mail(to_mail, from_mail, subject, wcc, bcc, content, " +
                    "date_mail, flag_read, type_mail, flag_status, account_id) " +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pStatement = con.prepareStatement(sql);
            pStatement.setString(1, mail.getTo_mail());
            pStatement.setString(2, mail.getFrom_mail());
            pStatement.setString(3, mail.getSubject());
            pStatement.setString(4, mail.getWcc());
            pStatement.setString(5, mail.getBcc());
            pStatement.setString(6, mail.getContent());
            pStatement.setString(7, mail.getDate_mail());
            pStatement.setInt(8, mail.getFlag_read());
            pStatement.setInt(9, mail.getType_mail());
            pStatement.setInt(10, mail.getFlag_status());
            pStatement.setString(11, mail.getAccount_id());
            System.out.println(getNextMail_id());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
    }

    public static int getNextMail_id() {
        //获取mail_id的下一个值
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        int nextMail_id = -1;
        try {
            con = DBConnection.getConnection();
            statement = con.createStatement();
            String sql = "select auto_increment from INFORMATION_SCHEMA.TABLES where " +
                    "table_name = 'mail'";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                nextMail_id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return nextMail_id;
    }

    public static void deleteMail(Mail mail) {
        /**
         * 删除一封邮件
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            con = DBConnection.getConnection();
            String sql = "delete from mail where mail_id = ? and account_id = ?";
            pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, mail.getMail_id());
            pStatement.setString(2, mail.getAccount_id());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
    }

    public static ArrayList<Mail> searchMail(String keyword) {
        /**
         * 根据输入的关键字查找邮件
         */
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        String patternString = null;    //使用正则表达式
        ArrayList<Mail> mails = new ArrayList<>();
        int n = 12;
        try {
            patternString = "^" + keyword + ".*";
            con = DBConnection.getConnection();
            stat = con.createStatement();
            String sql = "select * from mail where account_id = " + Constant.userName;
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                boolean flag = false;
                for (int i = 1; i <= n; i++) {
                    String aString = rs.getString(i);
                    if (aString.matches(patternString)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    Mail mail = new Mail();
                    mail.setMail_id(rs.getInt(1));
                    mail.setTo_mail(rs.getString(2));
                    mail.setFrom_mail(rs.getString(3));
                    mail.setSubject(rs.getString(4));
                    mail.setWcc(rs.getString(5));
                    mail.setBcc(rs.getString(6));
                    mail.setContent(rs.getString(7));
                    mail.setDate_mail(rs.getString(8));
                    mail.setFlag_read(rs.getInt(9));
                    mail.setType_mail(rs.getInt(10));
                    mail.setFlag_read(rs.getInt(11));
                    mail.setAccount_id(Constant.userName);
                    mails.add(mail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return mails;
    }

    public static void updateMail(Mail mail) {
        /**
         * 更新邮件
         */
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "update email set  to_mail=?, from_mail=?," +
                    "subject=?, wcc=?, bcc=?, content=?, date_mail=?, flag_read=?, type_mail=?, flag_status=? " +
                    "where account_id=? and mail_id=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, mail.getTo_mail());
            stat.setString(2, mail.getFrom_mail());
            stat.setString(3, mail.getSubject());
            stat.setString(4, mail.getWcc());
            stat.setString(5, mail.getBcc());
            stat.setString(6, mail.getContent());
            stat.setString(7, mail.getDate_mail());
            stat.setInt(8, mail.getFlag_read());
            stat.setInt(9, mail.getType_mail());
            stat.setInt(10, mail.getFlag_status());
            stat.setString(11, mail.getAccount_id());
            stat.setInt(12, mail.getMail_id());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, null, null);
        }
    }

    public static ArrayList<Mail> Query(int type) {
        /**
         * 根据输入类型查询
         */
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        ArrayList<Mail> mails = new ArrayList<>();
        try {
            con = DBConnection.getConnection();
            String sql = "select * from mail where account_id = " + "'" + Constant.userName + "'" + "and type_mail = " + "'" + type + "'";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                Mail mail = new Mail();
                mail.setMail_id(rs.getInt(1));
                mail.setTo_mail(rs.getString(2));
                mail.setFrom_mail(rs.getString(3));
                mail.setSubject(rs.getString(4));
                mail.setWcc(rs.getString(5));
                mail.setBcc(rs.getString(6));
                mail.setContent(rs.getString(7));
                mail.setDate_mail(rs.getString(8));
                mail.setFlag_read(rs.getInt(9));
                mail.setType_mail(rs.getInt(10));
                mail.setFlag_read(rs.getInt(11));
                mail.setAccount_id(Constant.userName);
                mails.add(mail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return mails;
    }

    public static Mail findMailById(int mail_id) {
        /**
         * 根据id查找邮件
         */
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        Mail mail = null;
        try {
            con = DBConnection.getConnection();
            String sql = "select * from mail where account_id = ? and mail_id = ?";
            pstat = con.prepareStatement(sql);
            pstat.setString(1, Constant.userName);
            pstat.setInt(2, mail_id);
            rs = pstat.executeQuery();
            while (rs.next()) {
                mail = new Mail();
                mail.setMail_id(rs.getInt(1));
                mail.setTo_mail(rs.getString(2));
                mail.setFrom_mail(rs.getString(3));
                mail.setSubject(rs.getString(4));
                mail.setWcc(rs.getString(5));
                mail.setBcc(rs.getString(6));
                mail.setContent(rs.getString(7));
                mail.setDate_mail(rs.getString(8));
                mail.setFlag_read(rs.getInt(9));
                mail.setType_mail(rs.getInt(10));
                mail.setFlag_read(rs.getInt(11));
                mail.setAccount_id(Constant.userName);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return mail;
    }
}