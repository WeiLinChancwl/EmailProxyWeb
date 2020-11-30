<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮箱代收发助手</title>
<style>
body,form,h1,h2,h3,img,li,p,ul {
	margin: 0;
	padding: 0;
	border: 0;
}
body,button,input,select {
	font-family: PingFangSC-Medium, Microsoft YaHei, verdana, Simsun,
		STXihei;
	zoom: 1;
}

body {
	color: #000;
	background: #fff;
	font-size: 12px;
	line-height: 166.6%;
	text-align: center;
}

.header {
	width: 1000px;
	height: 64px;
	position: relative;
	margin: 0 auto;
	z-index: 2;
	overflow: hidden;
}

div {
	display: block;
}

body,button,input,select {
	font-family: PingFangSC-Medium, Microsoft YaHei, verdana, Simsun,
		STXihei;
	zoom: 1;
}
.headerlogo {
	height: 52px;
	line-height: 28px;
	width: 165px;
	display: block;
	position: absolute;
	top: 5px;
	left: 0px;
	color: #777;
}
.headerTitle {
	font-size: 16px;
	height: 28px;
	line-height: 28px;
	width: 156px;
	display: block;
	position: absolute;
	top: 17px;
	left: 175px;
	border-left: 1px solid #ccc;
	color: #777;
}

.headerNav {
	display: block;
	position: relative;
	top: 21px;
	right: 16px;
	text-align: right;
	color: #cfd0d0;
}

.headerNav a {
	padding-left: 13px;
	display: inline-block;
	font-size: 16px;
}

a {
	text-decoration: none;
	color: #848585;
}

.main {
	height: 800px;
	background: #fff;
	position: relative;
	min-width: 1000px;
	background-color: rgb(240, 249, 253);
}

.login {
	width: 450px;
	top: 100px;
	right: 300px;
	text-align: left;
	position: absolute;
	z-index: 2;
	background: #fff;
	border-radius: 8px;
	background-image: linear-gradient(-180deg, #fff, #f4f4f4);
	box-shadow: 2px 2px 5px rgba(0, 0, 0, .1), -2px -2px 5px
		rgba(0, 0, 0, .1);
}

.loginFunc {
	width: 100%;
	height: 48px;
	clear: both;
	position: relative;
}

.loginFuncNormal {
	letter-spacing: 1px;
	width: 224px;
	overflow: hidden;
	position: relative;
	line-height: 48px;
	float: left;
	font-size: 16px;
	text-align: center;
	color: #626262;
	cursor: pointer;
	font-weight: 100;
}

.loginForm {
	min-height: 450px;
	padding: 0;
	border-radius: 8px;
	position: relative;
	padding-top: 40px;
	background: #fff;
	text-align: center;
}

tr,td {
	height: 40px;
	vertical-align: center;
	line-height: 48px;
	font-size: 16px;
	text-align: left;
	color: #626262;
}

.normalInput {
	width: 200px;
}

.shortInput {
	width: 100px;
}

.button {
	background-color: #3182D9;
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 5px 5px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="header">
		<img src="images/logo.png" alt="邮箱代收发助手" class="headerlogo">
		<p class="headerTitle" href="javascript:void(0);">邮箱代收发助手</p>
		<div class="headerNav">
			<a href="#" target="_blank">帮助</a> <a href="#" target="_blank">常见问题</a>
			<a href="#" target="_blank">登录反馈</a>
		</div>
	</div>
	<div class="main" id="mainBg">
		<div class="login" id="loginblock">
			<div class="loginFunc">
				<div class="loginFuncNormal">账号登录</div>
			</div>
			<div class="loginFrame">
			<form action="LoginServlet" method="post">
				<table align="center">
					<tr>
						<td>用户名：</td>
						<td colspan="2"><input type="text" name="userName"
							class="normalInput" />
						</td>
					</tr>
					<tr>
						<td>密码：</td>
						<td colspan="2"><input type="password" name="passWord"
							class="normalInput" />
						</td>
					</tr>
					<tr>
						<td>验证码：</td>
						<td><input type="text" name="inputCode" class="shortInput">
						</td>
						<td><img src="GetAuthCodeServlet" id="authImg" />
						</td>
					</tr>
					<tr>
						<td>记住状态：</td>
						<td><input type="checkbox" id="remember_me"
							style=" position: relative; top: 3px; margin: 0; "
							name="remember_me" />
						</td>
						<td><a href="#" style="color:#3182D9; ">忘记密码？</a>
						</td>
					</tr>
					<tr>
						<td><input type="submit" value="登录" class="button" /></td>
						<td><input type="button"
							onclick="window.location.href='register.jsp'" value="注册"
							class="button">
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
	</div>
</body>
</html>