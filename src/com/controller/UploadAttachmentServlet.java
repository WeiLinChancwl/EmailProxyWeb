package com.controller;

import com.dao.AttachmentDao;
import com.dao.MailDao;
import com.entity.Attachment;
import com.jspsmart.upload.SmartUpload;
import com.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet("/UploadAttachmentServlet")
public class UploadAttachmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        SmartUpload su = new SmartUpload();
        try {
            su.initialize(this.getServletConfig(), request, response);
            su.upload();
            String path = "C:\\Users\\陈伟林\\IdeaProjects\\webDemo\\emailproxyweb\\upload\\";
            String filename = su.getFiles().getFile(0).getFileName();
            String filesize = su.getFiles().getFile(0).getSize()/1024 +"KB";
            filename = new String(filename.getBytes("GBK"),"utf-8");
            System.out.println(filename);
            su.getFiles().getFile(0).saveAs(path + "\\" + filename);
            Attachment attachment = new Attachment();
            attachment.setAttachment_url(path);
            attachment.setFilename(filename);
            attachment.setFilesize(filesize);
            attachment.setMail_id(MailDao.getNextMail_id());
            attachment.setAccount_id(Constant.userName);
            attachment.setAttachment_type(0);
            AttachmentDao.insertAttachment(attachment);
            String message = URLEncoder.encode("上传成功", "UTF-8");
            out.print("<script>alert(decodeURIComponent('"+message+"'));" +
                    "window.location.href='writeEmail.jsp'</script>");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
