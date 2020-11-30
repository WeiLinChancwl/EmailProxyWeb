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
  Time: 8:10
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
                    <button type="button" class="btn btn-default" onclick="deleteAttachment()">删除文件</button>
                    <button type="button" class="btn btn-default" onclick="downloadAttachment()">下载</button>
                    <button type="button" class="btn btn-default" onclick="window.location.href='writeEmail.jsp'">写信</button>
                    <button type="button" class="btn btn-default" >刷新</button>
                </div>
            </div>
        </div>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th><input type="checkbox" name="checkbox_all" hidden/></th>
        <th>文件名</th>
        <th>大小</th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<Attachment> attachments;
        String keyword = request.getParameter("searchContact");
        int type = -1;
        String attachment_type = request.getParameter("type");
        if( keyword == null && attachment_type != null) {
             type = Integer.parseInt(attachment_type);
             attachments = AttachmentDao.Query(type);
        } else {
            keyword = new String(keyword.getBytes("ISO_8859_1"), "utf-8");
            attachments = AttachmentDao.searchAttachment(keyword);
        }
        for (Attachment attachment : attachments) {
    %>
    <tr>
        <td><input type="checkbox" name="checkbox_mail" value="<%=attachment.getAttachment_id()%>"/></td>
        <td><%=attachment.getFilename()%></td>
        <td><%=attachment.getFilesize()%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<script>
    function deleteAttachment() {
        var checkboxes = document.getElementsByName("checkbox_mail");
        var checkboxvalue = new Array();
        for(var k in checkboxes) {
            if(checkboxes[k].checked) {
                var res = checkboxes[k].value;
                checkboxvalue.push(res);
            }
        }
        window.location.href = "DeleteAttachmentServlet?checkboxvalue=" + checkboxvalue.toString();
    }

    function downloadAttachment() {
        var checkboxes = document.getElementsByName("checkbox_mail");
        var checkboxvalue = new Array();
        for(var k in checkboxes) {
            if(checkboxes[k].checked) {
                var res = checkboxes[k].value;
                checkboxvalue.push(res);
            }
        }
        window.location.href="DownloadServlet?checkboxvalue=" + checkboxvalue.toString();
    }
</script>
</body>
</html>
