package com.controller;

import com.dao.DBConnection;
import com.dao.EmailAccountDao;
import com.entity.EmailAccount;
import com.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/BindEmailServlet")
public class BindEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = null;
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String email_account = request.getParameter("email_account");
        String email_pwd = request.getParameter("email_pwd");
        String email_type = request.getParameter("email_type");
        String account_id = Constant.userName;
        EmailAccount emailAccount = new EmailAccount();
        emailAccount.setAccount_id(account_id);
        emailAccount.setEmail_account(email_account);
        emailAccount.setEmail_pwd(email_pwd);
        emailAccount.setEmail_type(email_type);
        if(email_account==null || email_account.trim().length()==0 || email_pwd==null ||
                email_pwd.trim().length()==0) {
            message = URLEncoder.encode("账户密码不能为空！", "UTF-8");
            out.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='register.jsp'</script>");
        } else {
            Connection con = null;
            PreparedStatement pStatement = null;
            ResultSet rSet = null;
            try {
                con = DBConnection.getConnection();
                String sql = "select * from emailaccount where account_id=? and email_account=?";
                pStatement = con.prepareStatement(sql);
                pStatement.setString(1, Constant.userName);
                pStatement.setString(2, email_account);
                rSet = pStatement.executeQuery();
                if (rSet.next()) {
                    message = URLEncoder.encode("已绑定该邮箱账户！", "UTF-8");
                    out.print("<script>alert(decodeURIComponent('" + message + "'));</script>");
                } else {
                    EmailAccountDao.insertEmailAccount(emailAccount);
                    message = URLEncoder.encode("绑定成功", "UTF-8");
                    out.print("<script>alert(decodeURIComponent('"+message+"'));</script>");
                }
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
                DBConnection.free(con, null, null);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
