<%--
  Created by IntelliJ IDEA.
  User: 陈伟林
  Date: 2019/3/8
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form action="UploadContactServlet" method="post" enctype="multipart/form-data">
    <p>
        上传文件:<input type="file" name="uploadfile" />
    </p>
    <p>
        <input type="submit" value="上传" />
    </p>
</form>
${message }
</body>
</html>
