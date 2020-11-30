<%@ page import="com.entity.Mail" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.dao.MailDao" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.util.Constant" %>
<%@ page import="com.entity.Attachment" %>
<%@ page import="com.dao.AttachmentDao" %>
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
                <div class="btn-group col-sm-4" role="group">
                    <button type="button" class="btn btn-default" onclick="window.location.href='receiveEmail.jsp'">返回</button>
                    <button type="button" class="btn btn-default" onclick="">回复</button>
                    <button type="button" class="btn btn-default" onclick="sendChangeEmail()">转发</button>
                    <button type="button" class="btn btn-default" onclick="window.location.href='writeEmail.jsp'">写信</button>
                    <button type="button" class="btn btn-default" onclick="deleteMail()">删除</button>
                </div>
                <div class="btn-group col-sm-4" role="group">
                    <button type="button" href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">移动到 <span class="caret"></span> </button>
                    <button type="button"href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">标记到 <span class="caret"></span></button>
                    <button type="button" href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">更多 <span class="caret"></span></button>
                </div>
            </div>
            <%
                Mail mail = new Mail();
                int mail_id = Integer.parseInt(request.getParameter("mail_id"));
                mail = MailDao.findMailById(mail_id);
                Attachment attachment = AttachmentDao.findAttachmentByMail(mail_id);
            %>
            <form class="col-sm-10" action="SendEmailServlet" method="post">
                <h2 class="form-signin-heading"><%=mail.getSubject()%></h2>
                <input type="text" name="mail_id" value="<%=mail.getMail_id()%>" hidden>
                <input type="text" name="content" value="<%=mail.getContent()%>" hidden>
                <input type="text" name="subject" value="<%=mail.getSubject()%>" hidden>
                <input type="text" name="type" value="1" hidden>
                发件人:<input type="text" class="form-control" id="from_mail" name="from_mail" placeholder="请输入发件人邮箱" value="<%=mail.getFrom_mail()%>" required>
                收件人:<input type="text" class="form-control" id="to_mail" name="to_mail" placeholder="请输入收件人邮箱" value="<%=mail.getTo_mail()%>" required>
                时间: <input type="text" class="form-control" id="date" name="date" placeholder="请输入发送时间" value="<%=mail.getDate_mail()%>" required>
                附件:
                <%
                    if (attachment != null) {
                        String downloadhref = "DownloadServlet?checkboxvalue=" + attachment.getAttachment_id();
                        out.println("<a href = '" + downloadhref + "'>" + attachment.getFilename() + "</a>");
                        //System.out.println("<a href = '" + downloadhref + "'>" + attachment.getFilename() + "</a>");
                    } else {
                        out.println("无");
                    }
                %>
                <hr/>
                <div style="height: 500px;">
                    <%=mail.getContent()%>
                </div>
                <button class="btn btn-lg btn-primary btn-block">转发</button>
            </form>
        </div>
    </div>
</div>
<script>
    function sendChangeEmail() {
        document.getElementById('send_mail_btn').submit();
    }
</script>
</body>
</html>
