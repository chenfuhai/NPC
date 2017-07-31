<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script  type="text/javascript"  src="../static/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>

============================用户=========================
${sessionScope.loginedUser.adName}


<button id="btnLogout"> 登出</button>
<br/>
删除ID<input type="text" id="delUser">
<button id="btnDel">delete</button>
<button id="btnAdd">add</button>
修改ID<input type="text" id="updateUser">
<button id="btnUpdate">update</button>


<form id="addForm" >
    名称<input type="text" name="adName">
    密码<input type="text" name="adPwd">
    权限<input type="text" name="adPowercode">


</form>


<br/>
============================================================
<br/>
============================文章=========================
<br/>
删除ID<input type="text" id="delArt">
<button id="btnDelArt">delete</button>
<button id="btnAddArt">add</button>
修改ID<input type="text" id="updateArt">
<button id="btnUpdateArt">update</button>


<form id="addFormArt" >
    标题<input type="text" name="arTitle">
    列ID<input type="text" name="arColumnid">
    内容<input type="text" name="arContent">


</form>

<br/>
============================================================
<br/>
============================栏目========================
<br/>
删除ID<input type="text" id="delCol">
<button id="btnDelCol">delete</button>
<button id="btnAddCol">add</button>
修改ID<input type="text" id="updateCol">
<button id="btnUpdateCol">update</button>


<form id="addFormCol" >
    名字<input type="text" name="coName">
    介绍<input type="text" name="coInfo">


</form>

<br/>
============================================================
<br/>


<script>
    (function () {

        $("#btnLogout").click(function () {
            $.ajax({
                url: '/admin/logout',
                type: 'post',
                success: function (result) {
                    console.log(result);
                    if(result.code==100){
                        window.location.href="/admin";
                    }
                }
            })
        });
        $("#btnDel").click(function () {
            $.ajax({
                url: '/admin/user/' + $("#delUser").val(),
                type: 'delete',
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnAdd").click(function () {

            $.ajax({
                url: '/admin/user',
                data:$('#addForm').serialize(),
                type: 'POST',
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnUpdate").click(function () {
            $.ajax({
                url: '/admin/user/' +$("#updateUser").val(),
                type: 'put',
                data:$('#addForm').serialize(),
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnDelArt").click(function () {
            $.ajax({
                url: '/admin/art/' +$("#delArt").val(),
                type: 'delete',
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnAddArt").click(function () {
            $.ajax({
                url: '/admin/art' ,
                type: 'post',
                data:$('#addFormArt').serialize(),
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnUpdateArt").click(function () {
            $.ajax({
                url: '/admin/art/' +$("#updateArt").val(),
                type: 'put',
                data:$('#addFormArt').serialize(),
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnDelCol").click(function () {
            $.ajax({
                url: '/admin/col/' + $('#delCol').val(),
                type: 'delete',

                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnUpdateCol").click(function () {
            $.ajax({
                url: '/admin/col/' +$("#updateCol").val(),
                type: 'put',
                data:$('#addFormCol').serialize(),
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnAddCol").click(function () {
            $.ajax({
                url: '/admin/col',
                type: 'post',
                data:$('#addFormCol').serialize(),
                success: function (result) {
                    console.log(result);
                }
            })
        });
    })();


</script>

</body>
</html>
