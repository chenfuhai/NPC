<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class='no-js' lang='en'>
  <head>
    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>东乡人大后台管理员登录</title>
    <meta content='lab2023' name='author'>
    <meta content='' name='description'>
    <meta content='' name='keywords'>
    <link href="../static/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css" />
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="../static/assets/images/favicon.ico" rel="icon" type="image/ico" />

    <script type="javascript" src="../static/js/jquery-3.2.1.min.js"></script>


    <link rel="stylesheet" href="http://apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
    <script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
  </script><script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.6.2/modernizr.min.js" type="text/javascript">

  </script><script src="../static/assets/javascripts/application-985b892b.js" type="text/javascript"></script>
    
  </head>
  <body class='login'>
    <div class='wrapper'>
     
      <div class='row'>
        <div class='col-lg-12'>
          <form id="loginForm">
            <fieldset class='text-center'>
              <legend>后台管理员登录</legend>
              <div class='form-group'>
                <input name = "userName" class='form-control' placeholder='用户名' type='text'>
              </div>
              <div class='form-group'>
                <input name="userPwd" class='form-control' placeholder='密码' type='password'>
              </div>
            </fieldset>
          </form>
          <div class='text-center'>

            <button id="btnLogin" class="btn btn-default" >登录</button>
            <br>

          </div>
        </div>
      </div>
    </div>

    <script>
        (function () {

            $('#btnLogin').click(function () {
                $.ajax({
                    url: '/admin/login',
                    type: 'post',
                    data:$("#loginForm").serialize(),
                    success: function (result) {
                        console.log(result);
                        if(result.code==100){
                            window.location.href="/admin/main";
                        }
                    }
                })
            });
        })();


    </script>




  </body>
</html>
