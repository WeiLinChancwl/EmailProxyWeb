<%--
  Created by IntelliJ IDEA.
  User: 陈伟林
  Date: 2019/3/7
  Time: 8:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>邮件代收发助手</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form class="form-signin" action="AddContactServlet" method="post">
        <h2 class="form-signin-heading">添加联系人</h2>
        <label for="newContactName" class="control-label">姓名:</label>
        <input type="text" name="newContactName" id="newContactName" class="form-control" placeholder="请输入姓名" required autofocus>
        <br/><br/>
        <label for="newContactEmail" class="control-label">邮箱:</label>
        <input type="text" name="newContactEmail" id="newContactEmail" class="form-control" placeholder="请输入邮箱" required>
        <br/><br/>
        <label for="newContactTel" class="control-label">手机号码</label>
        <input type="text" name="newContactTel" id="newContactTel" class="form-control" placeholder="请输入手机号码" required>
        <br/><br/>
        <label for="newContactCate" class="control-label">分组</label>
        <input type="text" name="newContactCate" id="newContactCate" class="form-control" placeholder="请输入分组" required>
        <br/><br/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">添加</button>
    </form>
</div> <!-- /container -->
</body>
</html>
