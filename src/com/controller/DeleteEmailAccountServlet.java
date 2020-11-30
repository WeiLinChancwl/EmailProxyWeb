package com.controller;

import com.dao.EmailAccountDao;
import com.entity.EmailAccount;
import com.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet("/DeleteEmailAccountServlet")
public class DeleteEmailAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String email_account = request.getParameter("email_account");
        //System.out.println(email_account);
        EmailAccount emailAccount = new EmailAccount();
        emailAccount.setEmail_account(email_account);
        emailAccount.setAccount_id(Constant.userName);
        EmailAccountDao.deleteEmailAccount(emailAccount);
        String message = URLEncoder.encode("删除账户邮箱成功", "UTF-8");
        out.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='body.jsp'</script>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
