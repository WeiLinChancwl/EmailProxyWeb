<%--
  Created by IntelliJ IDEA.
  User: 陈伟林
  Date: 2019/2/26
  Time: 19:26
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
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            color: #fff;
            background-color: rgb(240, 249, 253);
            background-image: linear-gradient(-180deg, #fff, #f4f4f4);
            box-shadow: 2px 2px 5px rgba(0, 0, 0, .1), -2px -2px 5px rgba(0, 0, 0, .1);
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <!-- The mobile navbar-toggle button can be safely removed since you do not need it in a non-responsive implementation -->
                    <img src="images/logo.png" alt="邮箱代收发助手" href="index.jsp">
                </div>
                <!-- Note that the .navbar-collapse and .collapse classes have been removed from the #navbar -->
                <div id="navbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="body.jsp" target="right"> <span class="glyphicon glyphicon-home"></span> 首页</a></li>
                        <li><a href="bindEmail.jsp" target="right">邮箱绑定</a></li>
                        <li><a href="body.jsp" target="right">帮助</a></li>
                        <li><a href="body.jsp" target="right"> <span class="glyphicon glyphicon-user"></span> ${userName} </a></li>
                    </ul>
                    <form class="navbar-form navbar-right" action="SearchEmailServlet" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" name="keyword" placeholder="邮箱全文搜索">
                        </div>
                        <button type="submit" class="btn btn-default">查找</button>
                    </form>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
    </div>
</div>
</body>
</html>
