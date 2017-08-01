<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>KindEditor JSP</title>

    <link rel="stylesheet" href="/static/themes/default/default.css"/>
    <link rel="stylesheet" href="/static/plugins/code/prettify.css"/>
    <script charset="utf-8" src="/static/kindeditor-all.js"></script>
    <script charset="utf-8" src="/static/lang/zh-CN.js"></script>
    <script charset="utf-8" src="/static/plugins/code/prettify.js"></script>
    <script src="/ligerStatic/lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>

    <link href="/ligerStatic/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
    <link href="/ligerStatic/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css">
    <link href="/ligerStatic/css/style-item.css" rel="stylesheet" media="all"/>
    <script src="/ligerStatic/lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

    <script src="/ligerStatic/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>

    <script src="/ligerStatic/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
    <script src="/ligerStatic/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="/ligerStatic/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="/static/myjs/kindeditor.js" charset="utf-8"></script>

</head>
<body>
<%=htmlData%>
<form id="saveForm">
    <div class="div-default">
        标题：<input type="text" name="arTitle" id="ar_title"><br>
        副标题：<input type="text" name="arSubtitle" id="ar_subtitle"><br>
        所属栏目：<input type="text" id="ar_coname" ><br>
        来源：<input type="text" id="ar_from" name="arFrom"><br>
        <input type="hidden" value="" name="arColumnid" id="coId">
        <input type="hidden" value="" name="arStatus" id="ar_status">
    </div>

    <textarea id="ar_contnet" name="arContent" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;">

				<%=htmlspecialchars(htmlData)%></textarea>
</form>
<br/>

<div class="btn-default">
    <button id="btnSave">保存</button>&nbsp;&nbsp;&nbsp;<button id="btnCheck">送审</button>
    <button id="btnPublic">发布</button>&nbsp;&nbsp;&nbsp;<button id="btnCancle">取消</button>

</div>


<style>

    .div-default {
        margin: 0 auto;
        width: 700px;
        text-align: left;
    }

    .btn-default {
        margin: 0 auto;
        text-align: center;
    }

    /*居中*/
    .ke-container {
        margin: 0 auto;

    }
    /*修复排版bug*/
    .ke-dialog-row label{
        display:inline;

    }
</style>

</body>
</html>
<%!
    private String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }
%>