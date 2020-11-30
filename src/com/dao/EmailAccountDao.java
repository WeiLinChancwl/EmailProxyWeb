/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: EmailAccountDao
 * Author:   陈伟林
 * Date:     2019/3/3 23:54
 * Description: 邮箱绑定
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dao;

import com.entity.Contact;
import com.entity.EmailAccount;
import com.util.Constant;
import java.sql.*;
import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈邮箱绑定〉
 *
 * @author 陈伟林
 * @create 2019/3/3
 * @since 1.0.0
 */
public class EmailAccountDao {
    public static void insertEmailAccount(EmailAccount emailAccount) {
        /**
         * 增加一个邮箱账号
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            con = DBConnection.getConnection();
            String sql = "insert into emailaccount(account_id, email_account, email_pwd, email_type)" +
                    "values(?, ?, ?, ?)";
            pStatement = con.prepareStatement(sql);
            pStatement.setString(1, emailAccount.getAccount_id());
            pStatement.setString(2, emailAccount.getEmail_account());
            pStatement.setString(3, emailAccount.getEmail_pwd());
            pStatement.setString(4, emailAccount.getEmail_type());
            pStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
    }

    public static void deleteEmailAccount(EmailAccount emailAccount) {
        /**
         * 删除邮箱账号
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            con = DBConnection.getConnection();
            String sql = "delete from emailaccount where email_account=? and account_id=?";
            pStatement = con.prepareStatement(sql);
            pStatement.setString(1, emailAccount.getEmail_account());
            pStatement.setString(2, emailAccount.getAccount_id());
            pStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
    }

    public static void updateEmailAccount(EmailAccount emailAccount) {
        /**
         * 更新邮箱账号
         */
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "update emailaccount set email_account = ?, email_pwd = ?, email_type=? " +
                    " where email_account_id = ?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, emailAccount.getEmail_account());
            stat.setString(2, emailAccount.getEmail_pwd());
            stat.setString(3, emailAccount.getEmail_type());
            stat.setInt(4, emailAccount.getEmail_account_id());
            stat.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, null, null);
        }
    }

    public static ArrayList<EmailAccount> Query() {
        Connection con = null;
        Statement stmt = null;
        ArrayList<EmailAccount> arr = new ArrayList<>();
        try {
            con = DBConnection.getConnection();
            String sql = "select * from emailaccount where account_id = " + "'"+ Constant.userName+"'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                EmailAccount emailAccount = new EmailAccount();
                emailAccount.setEmail_account(rs.getString("email_account"));
                emailAccount.setEmail_type(rs.getString("email_type"));
                emailAccount.setEmail_pwd(rs.getString("email_pwd"));
                emailAccount.setAccount_id(Constant.userName);
                emailAccount.setEmail_account_id(rs.getInt("email_account_id"));
                arr.add(emailAccount);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return arr;
    }

    public static EmailAccount FindByEmailAccount(String email_account) {
        /**
         * 根据账号查找账户
         */
        Connection con = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        EmailAccount emailAccount = null;
        try {
            con = DBConnection.getConnection();
            String sql = "select * from emailaccount where email_account = ? and account_id = ?";
            pStatement = con.prepareStatement(sql);
            pStatement.setString(1, email_account);
            pStatement.setString(2, Constant.userName);
            rs = pStatement.executeQuery();
            while(rs.next()) {
                emailAccount = new EmailAccount();
                emailAccount.setEmail_pwd(rs.getString("email_pwd"));
                emailAccount.setEmail_type(rs.getString("email_type"));
                emailAccount.setEmail_account(rs.getString("email_account"));
                emailAccount.setAccount_id(rs.getString("account_id"));
                emailAccount.setEmail_account_id(rs.getInt("email_account_id"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
        return emailAccount;
    }
}