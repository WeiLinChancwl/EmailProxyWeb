<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
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
	<form class="form-signin" action="RegisterServlet" method="post">
		<h2 class="form-signin-heading">注册账户</h2>
		<label for="newUserName" class="control-label">用户名:</label>
		<input type="email" name="newUserName" id="newUserName" class="form-control" placeholder="请输入账号" required autofocus>
		<label class="control-label">长度1~12个字符</label>
		<br/><br/>
		<label for="newPassword" class="control-label">密码:</label>
		<input type="password" name="newPassWord" id="newPassWord" class="form-control" placeholder="请输入密码" required>
		<label class="control-label"> 密码必须由字母和数字组成</label>
		<br/><br/>
		<label for="confirmPassWord" class="control-label">确认密码</label>
		<input type="password" name="confirmPassWord" id="confirmPassWord" class="form-control" placeholder="请确认密码" required>
		<label class="control-label"> 两次密码需要相同</label>
		<br/><br/>
		<button class="btn btn-lg btn-primary btn-block" type="submit">注册账户</button>
	</form>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>