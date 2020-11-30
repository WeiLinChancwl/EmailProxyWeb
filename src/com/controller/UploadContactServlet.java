package com.controller;

import com.dao.ContactDao;
import com.dao.DBConnection;
import com.entity.Contact;
import com.jspsmart.upload.SmartUpload;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UploadContactServlet")
public class UploadContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UploadContactServlet");
        SmartUpload su = new SmartUpload();
        try {
            su.initialize(this.getServletConfig(), request, response);
            su.upload();
            String path = "C:\\Users\\陈伟林\\IdeaProjects\\webDemo\\emailproxyweb\\upload\\";
            su.save(path);

            String filename = su.getFiles().getFile(0).getFileName();
            System.out.println(filename);
            filename = new String(filename.getBytes("iso8859-1"),"utf-8");
            Connection con = null;
            try {
                con = DBConnection.getConnection();
                Workbook book = Workbook.getWorkbook(new File(path + filename));
                Sheet sheet = book.getSheet(0);
                int rowCount = sheet.getRows();
                System.out.println(rowCount);
                int inputCount = 0;
                for(int i = 1; i < rowCount; i++) {
                    Contact contact = new Contact();
                    contact.setContact_id(Integer.parseInt(sheet.getCell(0, i).getContents().toString()));
                    contact.setName(sheet.getCell(1, i).getContents().toString());
                    contact.setEmail(sheet.getCell(2, i).getContents().toString());
                    contact.setTel(sheet.getCell(3, i).getContents().toString());
                    contact.setCate_id(sheet.getCell(4, i).getContents().toString());
                    contact.setAccount_id(sheet.getCell(5, i).getContents().toString());
                    if(sheet.getCell(0, i).getContents().toString().length() > 0) {
                        ContactDao.insertContact(contact);
                        inputCount++;
                    }
                }
                book.close();
                HttpSession session = request.getSession();
                session.setAttribute("message", "<script> alert(\"上传文件" + filename
                        + "成功，导入" + inputCount + "条数据\"); <script>");
            } finally {
                DBConnection.free(con, null, null);
            }
            response.sendRedirect("body.jsp");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
