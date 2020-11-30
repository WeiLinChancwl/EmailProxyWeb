<%@ page import="com.dao.ContactDao, com.entity.Contact" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.util.Constant" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈伟林
  Date: 2019/2/28
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="main">
            <div class="btn-toolbar" role="toolbar" aria-label="toolbar">
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-default" onclick="window.location.href='addcontact.jsp'">新建联系人</button>
                    <button type="button" class="btn btn-default" onclick="window.location.href='writeEmail.jsp'">写信</button>
                    <button type="button" class="btn btn-default" onclick="updateContact()">编辑</button>
                    <button type="button" class="btn btn-default" onclick="deleteContact()">删除</button>
                </div>
                <div class="btn-group col-sm-2" role="group">
                    <button type="button" class="btn btn-default" onclick="window.location.href='uploadcontact.jsp'">导入</button>
                    <a class="btn btn-default" href="DownloadContact">导出</a>
                </div>
                <div class="col-lg-4">
                    <form id="searchContactForm">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="搜索所有联系人" name="searchContact">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="submit">Go!</button>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<table class="table table-hover">
    <thead>
    <tr>
        <th><input type="checkbox" name="checkbox_all_contact" hidden/></th>
        <th>姓名</th>
        <th>邮件地址</th>
        <th>手机号码</th>
        <th>所在分组</th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<Contact> contacts;
        String keyword = request.getParameter("searchContact");
        if( keyword == null) {
            contacts = ContactDao.Query();
        } else {
            keyword = new String(keyword.getBytes("ISO_8859_1"), "utf-8");
            contacts = ContactDao.searchContact(keyword);
        }
        for (Contact contact : contacts) {
    %>
    <tr onclick="location.href='updatecontact.jsp?updateContactID=<%=contact.getContact_id()%>'">
        <td><input type="checkbox" name="checkbox_contact" value="<%=contact.getContact_id()%>"/></td>
        <td><%=contact.getName()%></td>
        <td><%=contact.getEmail()%></td>
        <td><%=contact.getTel()%></td>
        <td><%=contact.getCate_id()%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<script>
    function deleteContact() {
        var checkboxes = document.getElementsByName("checkbox_contact");
        var checkboxvalue = new Array();
        for(var k in checkboxes) {
            if(checkboxes[k].checked) {
                var res = checkboxes[k].value;
                checkboxvalue.push(res);
            }
        }
        window.location.href = "contact.jsp?checkboxvalue=" + checkboxvalue.toString();
    }
    function updateContact() {
        var checkboxes = document.getElementsByName("checkbox_contact");
        for(var k in checkboxes) {
            if(checkboxes[k].checked) {
                var res = checkboxes[k].value;
                break;
            }
        }
        window.location.href = "updatecontact.jsp?updateContactID=" + res.toString();
    }

</script>
<%
    String string = request.getParameter("checkboxvalue");
    if(string != null && string.trim() != "") {
        String[] str = string.split(",");
        for (String s : str) {
            int index = Integer.parseInt(s);
            Contact contact = new Contact();
            contact.setContact_id(index);
            contact.setAccount_id(Constant.userName);
            ContactDao.deleteContact(contact);
            String message = "删除成功";
            PrintWriter printWriter = response.getWriter();
            printWriter.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='contact.jsp'</script>");
        }
    }
%>
</body>
</html>
