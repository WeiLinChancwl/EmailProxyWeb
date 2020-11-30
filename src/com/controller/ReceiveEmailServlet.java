package com.controller;

import com.dao.EmailAccountDao;
import com.entity.EmailAccount;
import com.util.ReceiveEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;


@WebServlet("/ReceiveEmailServlet")
public class ReceiveEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<EmailAccount> emailAccounts = EmailAccountDao.Query();
        PrintWriter out = response.getWriter();
        for (EmailAccount emailAccount : emailAccounts) {
            ReceiveEmail receiveEmail = new ReceiveEmail(emailAccount);
            try {
                receiveEmail.receive();
                String message = URLEncoder.encode("更新成功", "UTF-8");
                out.print("<script>alert(decodeURIComponent('"+message+"'));" +
                        "window.location.href='receiveEmail.jsp'</script>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
