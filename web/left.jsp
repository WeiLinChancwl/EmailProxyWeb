<%@ page import="com.entity.EmailAccount" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dao.EmailAccountDao" %><%--
  Created by IntelliJ IDEA.
  User: 陈伟林
  Date: 2019/2/26
  Time: 21:25
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
<ul class="nav nav-pills nav-stacked">
    <li role="presentation"><a href="receiveEmail.jsp?type=2" target="right">收信 <span class="badge">新</span></a> </li>
    <li role="presentation"><a href="writeEmail.jsp" target="right">写信</a></li>
    <li role="presentation"><a href="contact.jsp" target="right">通讯录</a></li>
    <li role="presentation"><a href="right.jsp?type=0" target="right">草稿箱</a></li>
    <li role="presentation"><a href="right.jsp?type=1" target="right">已发送</a></li>
    <li role="presentation"><a href="right.jsp?type=3" target="right">订阅邮件</a></li>
    <li role="dropdown">
        <a href="right.jsp?type=4" class="dropdown-toggle" data-toggle="dropdown" target="right">
            邮箱中心<b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <%
                ArrayList<EmailAccount> emailAccounts;
                emailAccounts = EmailAccountDao.Query();
                for(EmailAccount emailAccount : emailAccounts) {
            %>
            <li><a href="updateEmailAccount.jsp?email_account=<%=emailAccount.getEmail_account()%>" target="right"><%=emailAccount.getEmail_account()%></a></li>
            <% } %>
        </ul>
    </li>
    <li role="presentation"><a href="attachment.jsp?type=0" target="right">文件中心</a></li>
    <li role="presentation"><a href="attachment.jsp?type=1" target="right">邮箱附件</a></li>
</ul>
</body>
</html>
