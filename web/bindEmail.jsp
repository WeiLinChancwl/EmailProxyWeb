<%--
  Created by IntelliJ IDEA.
  User: 陈伟林
  Date: 2019/3/2
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="绑定邮箱">
    <meta name="author" content="williamchancwl">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <form class="form-signin"  action="BindEmailServlet" method="post">
        <h2 class="form-signin-heading">邮箱绑定</h2>
        <label for="email_account" class="control-label">邮箱地址</label>
        <input type="email" name="email_account" id="email_account" class="form-control" placeholder="Email address" required autofocus>
        <br/>
        <label for="email_pwd" class="control-label">密码</label>
        <input type="password" name="email_pwd" id="email_pwd" class="form-control" placeholder="Password" required>
        <br/>
        <label for="email_type" class="control-label">邮箱服务器类型</label>
        <br/>
        <input type="text" name="email_type" id="email_type" class="form-control" placeholder="例如：163.com" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">绑定账户</button>
    </form>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

