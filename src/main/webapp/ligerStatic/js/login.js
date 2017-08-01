//在这个js函数中 执行登录操作的判断
$(function () {


    $(".login-text").focus(function () {
        $(this).addClass("login-text-focus");
    }).blur(function () {
        $(this).removeClass("login-text-focus");
    });

    $(document).keydown(function (e) {
        if (e.keyCode == 13) {
            dologin();
        }
    });
    $("#btnLogin").click(function () {
        dologin();
    });
    //登录判断语句
    //判断输入的账号是否为空 或者输入的密码是否为空 或者输入的验证码是否为空及错误
    function dologin() {
        var username = $("#txtUsername").val();
        var password = $("#txtPassword").val();

        if (username == "") {
            alert('账号不能为空!');
            $("#txtUsername").focus();
            return;
        }
        if (password == "") {
            alert('密码不能为空!');
            $("#txtPassword").focus();
            return;
        }

        $.ajax({
            type: 'post', cache: false, dataType: 'json',
            url: '/admin/login',
            data: $("#loginForm").serialize(),
            success: function (result) {
                if(result.code==100){
                    window.location.href="/admin/main";
                }else{
                    $.ligerDialog.warn(result.msg);
                }

            },
            error: function () {
                alert("发送系统错误,请与系统管理员联系!");
            },
            beforeSend: function () {
                $.ligerDialog.waitting("正在登陆中,请稍后...");
                $("#btnLogin").attr("disabled", true);
            },
            complete: function () {
                $.ligerDialog.closeWaitting();
                $("#btnLogin").attr("disabled", false);
            }
        });
    }
});