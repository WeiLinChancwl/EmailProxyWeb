package com.controller;

import com.dao.ContactDao;
import com.entity.Contact;
import com.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UpdateContactServlet")
public class UpdateContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indexString = request.getParameter("updateContactID");
        String message = null;
        PrintWriter out = response.getWriter();
        if(indexString != null && indexString.trim() != "") {
            Contact contact = new Contact();
            contact.setContact_id(Integer.parseInt(indexString));
            contact.setName(request.getParameter("updateContactName"));
            contact.setEmail(request.getParameter("updateContactEmail"));
            contact.setTel(request.getParameter("updateContactTel"));
            contact.setCate_id(request.getParameter("updateContactCate"));
            contact.setAccount_id(Constant.userName);
            ContactDao.updateContact(contact);
            message = "更新成功";
            out.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='contact.jsp'</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
