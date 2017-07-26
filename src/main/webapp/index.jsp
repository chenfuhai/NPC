<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



    <script  type="text/javascript"  src="/static/js/jquery-1.12.4.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>

============================用户=========================
<br/>
<button id="btnDel">delete</button>
<button id="btnAdd">add</button>
<button id="btnUpdata">updata</button>


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
<button id="btnDelArt">delete</button>
<button id="btnAddArt">add</button>
<button id="btnUpdataArt">updata</button>


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
<button id="btnDelCol">delete</button>
<button id="btnAddCol">add</button>
<button id="btnUpdataCol">updata</button>


<form id="addFormCol" >
    名字<input type="text" name="coName">
    介绍<input type="text" name="coInfo">


</form>

<br/>
============================================================
<br/>


<script>
    (function () {
        $("#btnDel").click(function () {
            $.ajax({
                url: '/user/' + '1002-1003-1004-1005',
                type: 'delete',
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnAdd").click(function () {

            $.ajax({
                url: '/user',
                data:$('#addForm').serialize(),
                type: 'POST',
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnUpdata").click(function () {
            $.ajax({
                url: '/user/' + '2010',
                type: 'put',
                data:$('#addForm').serialize(),
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnDelArt").click(function () {
            $.ajax({
                url: '/art/' + '1002',
                type: 'delete',
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnAddArt").click(function () {
            $.ajax({
                url: '/art' ,
                type: 'post',
                data:$('#addFormArt').serialize(),
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnUpdataArt").click(function () {
            $.ajax({
                url: '/art/' + '1003',
                type: 'put',
                data:$('#addFormArt').serialize(),
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnDelCol").click(function () {
            $.ajax({
                url: '/col/' + '12',
                type: 'delete',

                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnUpdataCol").click(function () {
            $.ajax({
                url: '/col/' + '13',
                type: 'put',
                data:$('#addFormCol').serialize(),
                success: function (result) {
                    console.log(result);
                }
            })
        });

        $("#btnAddCol").click(function () {
            $.ajax({
                url: '/col',
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
