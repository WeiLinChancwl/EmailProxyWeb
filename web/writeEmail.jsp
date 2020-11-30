<%--
  Created by IntelliJ IDEA.
  User: 陈伟林
  Date: 2019/3/1
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.dao.EmailAccountDao, com.entity.EmailAccount, java.util.ArrayList" %>
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
<div class="container-fluid">
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="toolbar">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">预览</button>
                <button type="button" class="btn btn-default">取消</button>
            </div>
            <div class="btn-group " role="group">
                <input type="button" class="btn btn-default" title="同时将这封邮件发给其他联系人"
                       id="wcc_btn" onclick="inputWcc()" value="抄送">
                <input type="button" class="btn btn-default" title="同时将这封邮件发给其他联系人，但收件人和抄送人不会看到密送人"
                       id="bcc_btn" onclick="inputBcc()" value="密送">
                <input type="button" class="btn btn-default" title="定时发送给好友邮箱"
                       id="send_date_btn" onclick="inputSendDate()" value="定时发送">
            </div>
        </div>
        <form action="UploadAttachmentServlet" method="post" id="uploadfile" enctype="multipart/form-data">
            <label for="email_pref" class="control-label">添加附件</label>
            <input type="file" id="email_pref" name="email_pref" onchange="uploadfile()">
        </form>
        <form action="SendEmailServlet" method="post" id="writeEmailForm">
            <div class="form-group">
                <label for="to_mail" class="control-label">收件人:</label>
                <input type="text" class="form-control" id="to_mail" name="to_mail" placeholder="请输入邮箱">
            </div>
            <div class="form-group">
                <label for="subject" class="control-label">主题:</label>
                <input type="text" class="form-control" id="subject" name="subject" placeholder="主题">
            </div>
            <div class="form-group" id="wcc_form" hidden>
                <label for="wcc" class="control-label">抄送人:</label>
                <input type="text" class="form-control" id="wcc" name="wcc" placeholder="抄送人">
            </div>
            <div class="form-group" id="bcc_form" hidden>
                <label for="bcc" class="control-label">密送人:</label>
                <input type="text" class="form-control" id="bcc" name="bcc" placeholder="密送人">
            </div>
            <div class="form-group" id="send_date_form" hidden>
                <label class="control-label">发送日期:</label>
                <select id="scheduleYear">
                    <option value="2019">2019</option>
                    <option value="2020">2020</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                </select>
                年
                <select id="scheduleMonth">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
                月
                <select id="scheduleDay">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="24">24</option>
                    <option value="25">25</option>
                    <option value="26">26</option>
                    <option value="27">27</option>
                    <option value="28">28</option>
                    <option value="29">29</option>
                    <option value="30">30</option>
                    <option value="31">31</option>
                </select>
                日
                <select id="scheduleHour"><option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option></select>
                时
                <select id="scheduleMinute"><option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option><option value="32">32</option><option value="33">33</option><option value="34">34</option><option value="35">35</option><option value="36">36</option><option value="37">37</option><option value="38">38</option><option value="39">39</option><option value="40">40</option><option value="41">41</option><option value="42">42</option><option value="43">43</option><option value="44">44</option><option value="45">45</option><option value="46">46</option><option value="47">47</option><option value="48">48</option><option value="49">49</option><option value="50">50</option><option value="51">51</option><option value="52">52</option><option value="53">53</option><option value="54">54</option><option value="55">55</option><option value="56">56</option><option value="57">57</option><option value="58">58</option><option value="59">59</option></select>
                分
                <p class="bB0">本邮件将在 <strong id="scheduleInfo">明天凌晨0 : 00</strong> 发送到对方邮箱</p>
            </div>

            <div id="editor"></div>
            <input id="content" name="content" hidden>
            <input id="type" name="type" hidden>
            <input id="date" name="date" placeholder="发送日期" hidden>
            <div class="form-group">
                <label class="control-label">发件人:</label>
                <input id="from_mail" name="from_mail" hidden>
                <select class="form-control" id="select_from_mail">
                    <%
                        ArrayList<EmailAccount> emailAccounts;
                        emailAccounts = EmailAccountDao.Query();
                        for (EmailAccount emailAccount : emailAccounts) {
                    %>
                    <option tabindex="<%=emailAccount.getEmail_account_id()%>">
                        <%=emailAccount.getEmail_account()%>
                    </option>
                    <% } %>
                </select>
            </div>
            <div>
                <button class="btn btn-default" id="send_mail_btn" onclick="sendEmail()">发送</button>
                <button class="btn btn-default" id="save_btn" onclick="saveEmailToDraft()">存草稿</button>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="wangEditor-3.1.1/release/wangEditor.js"></script>
<script type="text/javascript">
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.customConfig.uploadImgShowBase64 = true;   // 使用 base64 保存图片
    editor.create();

    function sendEmail() {
        var from_mail = document.getElementById("select_from_mail").value;
        var flag_send_date = document.getElementById("send_date_form").hidden;
        document.getElementById('content').value = editor.txt.html();

        if(!flag_send_date) {
            document.getElementById('date').value = document.getElementById("scheduleYear").value + "/" + document.getElementById("scheduleMonth").value + "/"
                + document.getElementById("scheduleDay").value + " " + document.getElementById("scheduleHour").value + ":" +
                document.getElementById("scheduleMinute").value;
            document.getElementById("scheduleInfo").value = document.getElementById('date').value
        } else {
            var date = new Date();
            document.getElementById('date').value = date.getFullYear() + "/" + date.getMonth() + "/" + date.getDate()
             + " " + date.getHours() + ":" + date.getMinutes();
        }
        document.getElementById("from_mail").value = from_mail;
        document.getElementById("type").value = 1;  //type=1表示已发送
        document.getElementById('send_mail_btn').submit();
    }

    function saveEmailToDraft() {
        var from_mail = document.getElementById("select_from_mail").value;
        var flag_send_date = document.getElementById("send_date_form").hidden;
        document.getElementById('content').value = editor.txt.html();
        if(!flag_send_date) {
            document.getElementById('date').value = document.getElementById("scheduleYear").value + "/" + document.getElementById("scheduleMonth").value + "/"
                + document.getElementById("scheduleDay").value + " " + document.getElementById("scheduleHour").value + ":" +
                document.getElementById("scheduleMinute").value;
            document.getElementById("scheduleInfo").value = document.getElementById('date').value
        } else {
            var date = new Date();
            document.getElementById('date').value = date.getFullYear() + "/" + date.getMonth() + "/" + date.getDate()
                + " " + date.getHours() + ":" + date.getMinutes();
        }
        document.getElementById("from_mail").value = from_mail;
        document.getElementById("type").value = 0; //type=0表示草稿
        document.getElementById('send_mail_btn').submit();
    }

    function inputWcc() {
        var flag = document.getElementById("wcc_form").hidden;
        if (flag) {
            document.getElementById("wcc_form").hidden = false;
            document.getElementById("wcc_btn").value = "删除抄送";
        } else {
            document.getElementById("wcc_form").hidden = true;
            document.getElementById("wcc_btn").value = "抄送";
        }
    }

    function inputBcc() {
        var flag = document.getElementById("bcc_form").hidden;
        if (flag) {
            document.getElementById("bcc_form").hidden = false;
            document.getElementById("bcc_btn").value = "删除密送";
        } else {
            document.getElementById("bcc_form").hidden = true;
            document.getElementById("bcc_btn").value = "密送";
        }
    }
    function inputSendDate() {
        var flag = document.getElementById("send_date_form").hidden;
        if (flag) {
            document.getElementById("send_date_form").hidden = false;
            document.getElementById("send_date_btn").value = "取消定时发送";
        } else {
            document.getElementById("send_date_form").hidden = true;
            document.getElementById("send_date_btn").value = "定时发送";
        }
    }

    function uploadfile() {
        document.getElementById("uploadfile").submit();
    }
</script>
</body>
</html>
