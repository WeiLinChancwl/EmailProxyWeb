<%@ page import="com.entity.Mail" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.dao.MailDao" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.util.Constant" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈伟林
  Date: 2019/3/2
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="main">
            <div class="btn-toolbar" role="toolbar" aria-label="toolbar">
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-default" onclick="deleteMail()">删除</button>
                    <button type="button" class="btn btn-default" onclick="">查看</button>
                    <button type="button" class="btn btn-default" onclick="window.location.href='writeEmail.jsp'">写信</button>
                    <button type="button" class="btn btn-default" onclick="">刷新</button>
                </div>
                <div class="btn-group col-sm-2" role="group">
                    <button type="button" class="btn btn-default" onclick="window.location.href=''">导入</button>
                    <button type="button" class="btn btn-default" onclick="window.location.href=''">导出</button>
                </div>
            </div>
        </div>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th><input type="checkbox" name="checkbox_all" hidden/></th>
        <th>收件人</th>
        <th>发件人</th>
        <th>主题</th>
        <th>时间</th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<Mail> mails;
        String keyword = request.getParameter("searchContact");
        int type = 5;
        String type_mail = request.getParameter("type");
        if(type_mail != null)
            type = Integer.parseInt(type_mail);
        if( keyword == null) {
            mails = MailDao.Query(type);
        } else {
            keyword = new String(keyword.getBytes("ISO_8859_1"), "utf-8");
            mails = MailDao.searchMail(keyword);
        }
        for (Mail mail : mails) {
    %>
    <tr onclick="location.href='displayEmail.jsp?mail_id=<%=mail.getMail_id()%>'">
        <td><input type="checkbox" name="checkbox_mail" value="<%=mail.getMail_id()%>"/></td>
        <td><%=mail.getTo_mail()%></td>
        <td><%=mail.getFrom_mail()%></td>
        <td><%=mail.getSubject()%></td>
        <td><%=mail.getDate_mail()%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<script>
    function deleteMail() {
        var checkboxes = document.getElementsByName("checkbox_mail");
        var checkboxvalue = new Array();
        for(var k in checkboxes) {
            if(checkboxes[k].checked) {
                var res = checkboxes[k].value;
                checkboxvalue.push(res);
            }
        }
        window.location.href = "receiveEmail.jsp?checkboxvalue=" + checkboxvalue.toString();
    }
</script>
<%
    String string = request.getParameter("checkboxvalue");
    if(string != null && string.trim() != "") {
        String[] str = string.split(",");
        for (String s : str) {
            int index = Integer.parseInt(s);
            Mail mail = new Mail();
            mail.setMail_id(index);
            mail.setAccount_id(Constant.userName);
            MailDao.deleteMail(mail);
            String message = "删除成功";
            PrintWriter printWriter = response.getWriter();
            printWriter.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='contact.jsp'</script>");
        }
    }
%>
</body>
</html>
