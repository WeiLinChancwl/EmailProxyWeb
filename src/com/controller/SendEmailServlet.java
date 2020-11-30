package com.controller;

import com.dao.AttachmentDao;
import com.dao.MailDao;
import com.entity.Attachment;
import com.entity.Mail;
import com.jspsmart.upload.SmartUpload;
import com.util.Constant;
import com.util.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Mail mail = new Mail();
        String account_id = Constant.userName;
        String to_mail = request.getParameter("to_mail");
        String from_mail = request.getParameter("from_mail");
        String subject = request.getParameter("subject");
        String wcc = request.getParameter("wcc");
        String bcc = request.getParameter("bcc");
        String content = request.getParameter("content");
        String date = request.getParameter("date");
        String flag_read = request.getParameter("flag_read");
        String type_mail = request.getParameter("type");
        int type = Integer.parseInt(type_mail);
        String flag_status = request.getParameter("flag_status");
        mail.setAccount_id(account_id);
        mail.setTo_mail(to_mail);
        mail.setFrom_mail(from_mail);
        mail.setSubject(subject);
        mail.setWcc(wcc);
        mail.setBcc(bcc);
        mail.setContent(content);
        mail.setDate_mail(date);
        mail.setMail_id(MailDao.getNextMail_id());
        if(flag_read != null && flag_read.trim() != "")
            mail.setFlag_read(Integer.parseInt(flag_read));
        if(flag_status != null && flag_status.trim() != "")
            mail.setFlag_status(Integer.parseInt(flag_status));
        if(type_mail != null && type_mail.trim() != "")
            mail.setType_mail(Integer.parseInt(type_mail));
        MailDao.insertMail(mail);
        if(type == 1) { //type为1时，发送邮件
            try {
                SendEmail sendEmail = new SendEmail(mail);
                sendEmail.send();
                String message = URLEncoder.encode("发送邮件成功", "UTF-8");
                out.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='writeEmail.jsp'</script>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
