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

@WebServlet("/UpdateEmailAccountServlet")
public class UpdateEmailAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmailAccount emailAccount = new EmailAccount();
        emailAccount.setEmail_account(request.getParameter("email_account"));
        emailAccount.setEmail_pwd(request.getParameter("email_pwd"));
        emailAccount.setEmail_type(request.getParameter("email_type"));
        String email_account_id = request.getParameter("email_account_id");
        emailAccount.setEmail_account_id(Integer.parseInt(request.getParameter("email_account_id")));
        emailAccount.setAccount_id(Constant.userName);
        EmailAccountDao.updateEmailAccount(emailAccount);
        System.out.println("更新成功");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
