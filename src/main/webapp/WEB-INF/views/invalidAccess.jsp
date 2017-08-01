<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>非法访问</title>
    <script type="text/javascript" src="/static/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<script type="text/javascript">
    $(function () {
        alert("非法访问，可能登录已过期。即将跳转到登录界面。");
        top.location.href = "/admin";
    });


</script>
</body>
</html>
