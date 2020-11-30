<%@ page import="com.entity.Contact" %>
<%@ page import="com.dao.ContactDao" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈伟林
  Date: 2019/3/7
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
<%
    Contact contact = null;
    String indexString = request.getParameter("updateContactID");
    //System.out.println(indexString);
    if(indexString != null && indexString.trim() != "") {
        int index = Integer.parseInt(indexString);
        contact = ContactDao.findContactById(index);
    }
%>
<div class="container">
    <form class="form-signin" action="UpdateContactServlet" method="post">
        <h2 class="form-signin-heading">编辑联系人</h2>
        <input type="text" name="updateContactID" value="<%=contact.getContact_id()%>" hidden>
        <label for="updateContactName" class="control-label">姓名:</label>
        <input type="text" name="updateContactName" id="updateContactName" class="form-control" placeholder="请输入姓名"  value="<%=contact.getName()%>" required autofocus>
        <br/><br/>
        <label for="updateContactEmail" class="control-label">邮箱:</label>
        <input type="text" name="updateContactEmail" id="updateContactEmail" class="form-control" placeholder="请输入邮箱" value="<%=contact.getEmail()%>" required>
        <br/><br/>
        <label for="updateContactTel" class="control-label">手机号码</label>
        <input type="text" name="updateContactTel" id="updateContactTel" class="form-control" placeholder="请输入手机号码" value="<%=contact.getTel()%>" required>
        <br/><br/>
        <label for="updateContactCate" class="control-label">分组类别</label>
        <input type="text" name="updateContactCate" id="updateContactCate" class="form-control" placeholder="请输入分组信息" value="<%=contact.getCate_id()%>" required>
        <br/><br/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">保存</button>
    </form>
</div> <!-- /container -->
</body>
</html>
