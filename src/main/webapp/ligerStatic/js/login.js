
//在这个js函数中 执行登录操作的判断  
$(function (){
      if (getCookie("user_id") && getCookie("user_id") != null)
    	 $("#txtUsername").val(getCookie("user_id"));
         var FromUrl = getQueryStringByName("FromUrl");
         if (!FromUrl){
            FromUrl = encodeURIComponent("main.jsp");
         }
         $(".login-text").focus(function (){
               $(this).addClass("login-text-focus");
          }).blur(function (){
                $(this).removeClass("login-text-focus");
          });
         
         $(document).keydown(function (e){
             if (e.keyCode == 13){
                   dologin();
              }});
         $("#btnLogin").click(function (){
                dologin();
         });
         //登录判断语句 
         //判断输入的账号是否为空 或者输入的密码是否为空 或者输入的验证码是否为空及错误
         function dologin(){   
             var username = $("#txtUsername").val();
             var password = $("#txtPassword").val();
             var validate = $("#txtValidate").val();
             if (username == ""){
                  alert('账号不能为空!');
                  $("#txtUsername").focus();
                  return;
             }
             if (password == ""){
                  alert('密码不能为空!');
                  $("#txtPassword").focus();
                  return;
             }
             if (validate == "") {
                  alert("验证码不能为空！");
                  $("#txtValidate").focus();
                  return;
             }
             $.ajax({
                    type: 'post', cache: false, dataType: 'json',
                    url: 'loginValidate.action',
                    data: {
                        Action:'login',
                    	username:username,
                    	password:password,//$.md5(password),//password,
                    	rnd:validate
                    },
                    success: function (result){
                        switch (result.message) {
                                case '0':
                                    alert("验证码错误!");
                                    $("#imgValidate").click();
                                    $("#txtValidate").val("");
                                    $("#txtValidate").focus();
                                    break;
                                case '1':
                                    alert("读取数据库失败!");
                                    break;
                                case '2':                                   
                                    alert("用户名错误!");                                   
                                    $("#txtUsername").focus();
                                    break;
                                case '3':
                                    alert("密码错误!");
                                     $("#txtPassword").focus();
                                    break; 
                                case '4':
                                    SetCookie("user_id", username, 30);
                                    //SetCookie("xhd_crm_show_wellcome", "1");
                                    location.href = decodeURIComponent(FromUrl);
                                    break;
                                default: 
                                   alert("登录失败!");                      
                            }          
                        
                    },
                    error: function ()
                    {
                        alert("发送系统错误,请与系统管理员联系!");
                    },
                    beforeSend: function ()
                    {
                        $.ligerDialog.waitting("正在登陆中,请稍后...");
                        $("#btnLogin").attr("disabled", true);
                    },
                    complete: function ()
                    {
                        $.ligerDialog.closeWaitting();
                        $("#btnLogin").attr("disabled", false);
                    }
                });
            }
        });