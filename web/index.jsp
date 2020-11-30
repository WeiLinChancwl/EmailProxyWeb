<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>邮件代收发助手</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<frameset rows="15%, 85%" border="2">
    <frame src="head.jsp" noresize scrolling="no"></frame>
    <frameset cols="15%, 85%">
        <frame src="left.jsp" noresize scrolling="yes" name="left"></frame>
        <frame src="body.jsp" noresize scrolling="yes" name="right"></frame>
    </frameset>
</frameset>
</html>