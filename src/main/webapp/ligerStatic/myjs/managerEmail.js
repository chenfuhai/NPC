
//t通过栏目名查出栏目ID
var grid;
var menu;
var dlgedit = null;

var coId = 0;
var dlgViewMsg = null;
$(function () {

    var coName = "人大信箱";
    if (coName !== null && coName !== "") {
        //根据栏目名获取文章ID
        init(coName);
        initListener();
    } else {
        $.ligerDialog.warn("参数错误,coName缺失或者错误");
    }
});

//请求所有栏目名 请求成功和本栏目名对应 得到栏目ID  根据栏目ID获得文章列表
function init(mcoName) {

    $.ajax({
        url: "/admin/cols",
        type: 'get',
        dataType: "json",
        success: function (result) {
            var cols = result.extend.cols;
            $.each(cols, function (index, col) {

                if (col.coName == mcoName) {
                    coId = col.coId;
                }
            });
            if (coId !== 0) {
                //通过栏目id查询文章
                initGrid(coId);
            } else {
                $.ligerDialog.warn("地址URL参数错误,不存在栏目");
            }
        }

    });
}


/**
 * 初始化列表
 *
 **/
function initGrid(coId) {
    $("form-info").ligerForm();
    menu = $.ligerMenu({
        width: 120, items:
            [{text: '查看', click: itemclick}, {text: '刷新', click: itemclick}]

    });
    grid = $("#articleList").ligerGrid({
        width: '100%',
        height: '100%',
        rowHeight: 30,
        headerRowHeight: 30,
        checkbox: true,
        rownumbers: true,
        enabledSort: true,
        columns: [
            {name: 'id', display: '留言ID', hide: true, width: 70,isSort:false},
            {name: 'coId', display: '栏目ID', hide: true, width: 70,isSort:false},
            {name: 'title', display: '留言主题', align: 'left', width: 300,isSort:false},
            {name: 'publicTime', display: '更新时间',width: 170},
            {
                name: 'status', display: '阅读状态', type: 'int', width: 70,
                render: function (rowData) {
                    switch (rowData.status) {
                        case 0:
                            return "<span style='color:dodgerblue'>未读</span>";
                        case 1:
                            return "<span style='color:green'>已读</span>";
                    }

                }
            }

        ],
        url: '/admin/arts/' + coId,
        method: 'get',
        usePager: true,
        pageSize: 10,//分页页面大小
        pageSizeOptions: [10, 20, 30],//可指定每页页面大小
        onDblClickRow: function (rowdata, rowindex) {
            //跳转到content页面
            window.open("/content.html?id=" + rowdata.id);
            signRead(rowdata.id);
            // //初始化内数据
            // $.ajax({
            //     url:"/admin/art/"+rowdata.id,
            //     dataType:"json",
            //     type:"get",
            //     success:function (result) {
            //         var art = result.extend.art;
            //         var msg = "";
            //         msg="留言主题:  "+art.arTitle+"<br>"+"留言内容： "+art.arContent;
            //         $("#msgTitle").empty().append(art.arTitle);
            //         $("#msgContent").empty().append(art.arContent);
            //
            //         //打开提示框看内容
            //         if (dlgViewMsg===null){
            //             dlgViewMsg= $.ligerDialog.open({
            //                 buttons: [{
            //                     text: '确认', onclick: function (i, d) {
            //                         simpleUpdateArt('arStatus=1',grid.getSelectedRows()[0].id);
            //                         d.hide();
            //                     }
            //                 }],
            //                 width: 350,
            //                 content:msg,
            //                 target:$("#messageContent")
            //             });
            //         }else{
            //             dlgViewMsg.show();
            //         }
            //     },
            //     error:function (result) {
            //         alert("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
            //         console.log("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
            //     },
            //     beforeSend:function () {
            //         $.ligerDialog.waitting("载入数据中");
            //     },
            //     complete:function () {
            //         $.ligerDialog.closeWaitting();
            //     }
            // });


        },
        onContextmenu: function (parm, e) {
            actionCustomerID = parm.data.CustomerID;
            menu.show({top: e.pageY, left: e.pageX});
            return false;
        }
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

                var status='';
                switch (row.status){
                    case 0:status="未读";break;
                    case 1:status="已读";break;
                }
                rowmsg += '留言主题： ' + row.title + "  " + "更新时间  " + row.publicTime + "状态 " +status + "<br>";
            });
            ids = ids.substring(0, ids.length - 1);

            //提示确认
            var msg = "您将删除以下留言：<br>" + rowmsg;


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
                    url: "/admin/art/" + ids,
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

    $("#btnPublic").click(function () {
        if (!selectedRowCheck()) {
            return;
        }
        var row = grid.getSelectedRows()[0];
        $.ligerDialog.confirm('确定标记已读？',function (yes) {
            if (yes){
                if(row.status==1){
                    $.ligerDialog.warn("该文章已经标记为已读，无需再次标记");
                    return;
                }
                simpleUpdateArt('arStatus=1',row.id);

            }
        })

    });

}

//需要更新的属性
function simpleUpdateArt(data, atrId) {
    $.ajax({
        url: "/admin/art/su/" + atrId,
        type: "put",
        data: data,
        dataType: "json",
        success: function (result) {
            $.ligerDialog.success(result.msg);
            //刷新列表
            grid.loadData();
        }

    });

}

//标记已读
function signRead(atrId) {
    $.ajax({
        url: "/admin/art/su/" + atrId,
        type: "put",
        data: 'arStatus=1',
        dataType: "json",
        success: function (result) {

            //刷新列表
            grid.loadData();
        }

    });
}

function selectedRowCheck() {
    var rows = grid.getSelectedRows();
    if (rows === null || rows.length === 0) {
        $.ligerDialog.warn("您没有选择数据");
        return false;
    } else if (rows.length > 1) {
        $.ligerDialog.warn("只能选择一行数据");
        return false;
    } else {
        return true;
    }
}

/**
 * 表格鼠标右键菜单
 **/
function itemclick(data) {

    switch (data.text) {
        case '查看':
            viewArt();
            return;
        case '刷新':
            grid.loadData();
            return;

    }
}

function viewArt() {
    var artId;
    //获取选择的列
    var rows = grid.getSelectedRows();
    if (rows === null || rows.length === 0) {
        $.ligerDialog.warn("您没有选择数据");
    } else if (rows.length > 1) {
        $.ligerDialog.warn("只能选择一个留言查看");
    } else {
        //跳转
        artId = rows[0].id;
       //打开对话框查看
        window.open("/content.html?id=" + artId);
        signRead(artId);
    }
}

