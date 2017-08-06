//查出所有的用户 以分页形式查出 类似文章 列出来 新增就弹出form表单 用户名 密码 确认密码 权限级别 用户说明
//删除 显示详情 然后就可以
var grid;
var menu;
var dlgedit = null;

var coId = 0;
var dlgViewMsg = null;
$(function () {

    initGrid();
    initListener();
});


/**
 * 初始化列表
 *
 **/
function initGrid() {
    $("form-info").ligerForm();

    grid = $("#usersList").ligerGrid({
        width: '100%',
        height: '100%',
        rowHeight: 30,
        headerRowHeight: 30,
        checkbox: true,
        rownumbers: true,
        columns: [
            {name: 'id', display: '用户ID', hide: true, width: 70, isSort: false},
            {name: 'name', display: '用户名', width: 70, isSort: false},
            {name: 'info', display: '用户信息', width: 170},
            {
                name: 'powercode', display: '权限级别', type: 'int', width: 70,
                render: function (rowData) {
                    switch (rowData.powercode) {
                        case 0:
                            return "<span style='color:dodgerblue'>普通用户</span>";
                        case 1:
                            return "<span style='color:green'>超级管理员</span>";
                    }

                }
            }

        ],
        url: '/admin/users',
        method: 'get',
        usePager: true,
        pageSize: 10,//分页页面大小
        pageSizeOptions: [10, 20, 30]//可指定每页页面大小

    });

}

/**
 * 注册按钮监听器
 **/
function initListener() {

    $("#btnRefresh").click(function () {
        grid.loadData();
    });
    $("#btnDel").click(function () {
        var rows = grid.getSelectedRows();
        if (rows === null || rows.length === 0) {
            $.ligerDialog.warn("您没有选择数据");
        } else {
            var ids = '';
            var rowmsg = '';//保存信息
            $.each(rows, function (index, row) {
                var id = row.id;
                ids = ids + id + "-";

                var status = '';
                switch (row.powercode) {
                    case 0:
                        status = "普通用户";
                        break;
                    case 1:
                        status = "超级管理员";
                        break;
                }
                rowmsg += '用户名 ' + row.name + "  " + "用户信息  " + row.info + "权限级别 " + status + "<br>";
            });
            ids = ids.substring(0, ids.length - 1);

            //提示确认
            var msg = "您将删除以下用户：<br>" + rowmsg;

            dlgedit = $.ligerDialog.open({
                buttons: [{
                    text: '确认', onclick: function (i, d) {
                        d.hide();
                        delBatch();
                    }
                },
                    {
                        text: '取消', onclick: function (i, d) {
                        d.hide();
                    }
                    }],
                content: msg, width: 350
            });


            //批量删除
            function delBatch() {
                $.ajax({
                    url: "/admin/user/" + ids,
                    type: "delete",
                    dataType: "json",
                    success: function (result) {
                        $.ligerDialog.success(result.msg);
                        //刷新列表
                        grid.loadData();
                    }
                });

            }

        }
    });

    $("#btnAdd").click(function () {

        $.ligerDialog.open({
            target: $("#addUser"),
            buttons: [{
                text: '确认', onclick: function (i, d) {

                    if (!check()) {
                        return;
                    }
                    d.hide();
                    addUser();
                }
            },
                {
                    text: '取消', onclick: function (i, d) {
                    d.hide();
                }
                }],
            width: 350
        });


        // var row = grid.getSelectedRows()[0];
        // $.ligerDialog.confirm('确定标记已读？',function (yes) {
        //     if (yes){
        //         if(row.status==1){
        //             $.ligerDialog.warn("该文章已经标记为已读，无需再次标记");
        //             return;
        //         }
        //         simpleUpdateArt('arStatus=1',row.id);
        //
        //     }
        // })

    });

    function check() {
        var userName = $("#add_name").val();
        var userPwd = $("#add_pwd").val();
        var userConfirmPwd = $("#add_compwd").val();
        var regName = /^[a-zA-Z0-9_-]{3,16}$/;
        if (userPwd !== userConfirmPwd) {
            $.ligerDialog.warn("两次输入密码不一致，请重试！");
            return false;
        } else if (userName === "" || userPwd === "" || userConfirmPwd === "") {
            $.ligerDialog.warn("用户名或密码为空请重试！");
            return false;
        } else if (!regName.test(userName)) {
            $.ligerDialog.warn("用户名只能是3-16位的英文字母组合");
            return false;
        } else {
            return true;
        }


    }

    function addUser() {
        var userName = $("#add_name").val();
        var userPwd = $("#add_pwd").val();
        var userInfo = $("#add_info").val();


        $.ajax({
            url: "/admin/user",
            type: "post",
            data: "adName=" + userName + "&adPwd=" + userPwd + "&adInfo=" + userInfo,
            dataType: "json",
            success: function (result) {
                if (result.code === 100) {

                    $.ligerDialog.success(result.msg);
                    //刷新列表
                    grid.loadData();
                }
                else {
                    $.ligerDialog.warn(result.msg);
                    //刷新列表
                    grid.loadData();
                }
            }


        });
    }

}
