//查出所有的用户 以分页形式查出 类似文章 列出来 新增就弹出form表单 用户名 密码 确认密码 权限级别 用户说明
//删除 显示详情 然后就可以
var grid;
var menu;
var dlgedit = null;

var coId = 0;
var dlgViewMsg = null;
$(function () {

    initData();
    initListener();
});


/**
 * 初始化列表
 *
 **/
function initData() {
    $.ajax({
        url:"/admin/user/logined",
        dataType:"json",
        type:"get",
        success:function (result) {
            if (result.code===200){
                $.ligerDialog.warn(result.extend.msg);
            }else if (result.code===100){
                var user = result.extend.loginedUser;
                $("#info_name").empty().html(user.adName);
                $("#info_info").empty().html(user.adInfo);
                var pc="";
                switch (user.adPowercode) {
                    case 0:
                        pc= "<span style='color:dodgerblue'>普通用户</span>";
                        break;
                    case 1:
                        pc= "<span style='color:green'>超级管理员</span>";
                        break;
                }
                $("#info_powercode").empty().html(pc);
            }
        }



    });


}

/**
 * 注册按钮监听器
 **/
function initListener() {

    $("#btnUpdateInfo").click(function () {
        $.ligerDialog.open({
            buttons: [{
                text: '确认', onclick: function (i, d) {
                    d.hide();
                    $.ajax({
                        url:"/admin/user/logined",
                        dataType:"json",
                        type:"get",
                        success:function (result) {
                            if (result.code===200){
                                $.ligerDialog.warn(result.extend.msg);
                            }else if (result.code===100) {
                                var user = result.extend.loginedUser;
                                var info = $("#cag_info").val();
                                if(info===""){
                                    $.ligerDialog.warn("没有填写简介信息");
                                    return;
                                }

                                $.ajax({
                                    url:"/admin/user/"+user.adId,
                                    dataType:"json",
                                    type:"put",
                                    data:"adInfo="+info,
                                    success:function (result) {
                                        if (result.code===200){
                                            $.ligerDialog.warn(result.msg);

                                        }else if (result.code===100) {
                                            $.ligerDialog.success(result.msg);
                                            initData();
                                        }
                                    }

                                })


                            }
                        }



                    });


                }
            },
                {
                    text: '取消', onclick: function (i, d) {
                    d.hide();
                }
                }],
            target: $("#changeInfo"), width: 350
        });
    });
    $("#btnChangePwd").click(function () {

             $.ligerDialog.open({
                buttons: [{
                    text: '确认', onclick: function (i, d) {
                        if(!check()){
                            return ;
                        }
                        d.hide();
                        $.ajax({
                            url:"/admin/user/changePwd",
                            dataType:"json",
                            type:"put",
                            data:"oldpwd="+$("#cag_oldpwd").val()+"&newpwd",
                            success:function (result) {
                                if (result.code===200){
                                    $.ligerDialog.warn(result.extend.msg);
                                }else if (result.code===100){
                                    $.ligerDialog.success(result.extrend.msg);
                                }
                            }
                        });
                    }
                },
                    {
                        text: '取消', onclick: function (i, d) {
                        d.hide();
                    }
                    }],
                target: $("#changePwd"), width: 350
            });
    });



    function check() {
        var userPwd = $("#add_pwd").val();
        var userConfirmPwd = $("#add_compwd").val();
        if (userPwd !== userConfirmPwd) {
            $.ligerDialog.warn("两次输入密码不一致，请重试！");
            return false;
        } else {
            return true;
        }


    }


}
