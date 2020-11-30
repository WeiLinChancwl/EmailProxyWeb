package com.controller;

import com.dao.AttachmentDao;
import com.entity.Attachment;
import com.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteAttachmentServlet")
public class DeleteAttachmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String string = request.getParameter("checkboxvalue");
        if(string != null && string.trim() != "") {
            String[] str = string.split(",");
            for (String s : str) {
                int index = Integer.parseInt(s);
                Attachment attachment = new Attachment();
                attachment.setAttachment_id(index);
                attachment.setAccount_id(Constant.userName);
                AttachmentDao.deleteAttachment(attachment);
            }
            String message = "删除成功";
            PrintWriter printWriter = response.getWriter();
            printWriter.print("<script>alert(decodeURIComponent('"+message+"'));</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
