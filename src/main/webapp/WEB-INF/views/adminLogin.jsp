<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script  type="text/javascript"  src="../static/js/jquery-1.12.4.min.js"></script>
</head>
<body>
<h2>管理员登录界面</h2>

<form id="login">
    用户名<input type="text" name="userName" >
    密码<input type="text" name="userPwd">


</form>

<button id ='btnLogin'>登录</button>


<script>
    (function () {

        $('#btnLogin').click(function () {
            $.ajax({
                url: '/admin/login',
                type: 'post',
                data:$("#login").serialize(),
                success: function (result) {
                    console.log(result);
                    if(result.code==100){
                        window.location.href="/admin/home";
                    }
                }
            })
        });
    })();


</script>

</body>
</html>
