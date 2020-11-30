package com.controller;

import com.dao.AttachmentDao;
import com.entity.Attachment;
import com.jspsmart.upload.SmartUpload;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String string = request.getParameter("checkboxvalue");
        try {
            if(string != null && string.trim() != "") {
                String[] str = string.split(",");
                for (String s : str) {
                    SmartUpload su = new SmartUpload();
                    su.initialize(this.getServletConfig(), request, response);
                    su.setContentDisposition(null);
                    int index = Integer.parseInt(s);
                    Attachment attachment = AttachmentDao.findAttachmentById(index);
                    String filePath = null;
                    if(attachment.getAttachment_type() == 0) {
                        filePath = attachment.getAttachment_url() + "//" + attachment.getFilename();
                    } else {
                        filePath = attachment.getAttachment_url() + "" + attachment.getFilename();
                    }
                    System.out.println(filePath);
                    su.downloadFile(filePath);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
