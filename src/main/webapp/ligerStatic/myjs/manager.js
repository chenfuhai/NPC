
//t通过栏目名查出栏目ID
var grid;
var menu;
var dlgedit = null;
var dlgchangfrom = null;
var dlgchangtime = null;
var dlgcopy = null;
var dlgmove = null;
var comboxCopy = null;
var comboxMove  = null;
var coId = 0;
$(function () {
    var s = decodeURIComponent(getPar("coName"));
    s = s.substring(1, s.length - 1);

    var coName = s;
    if (coName !== null && coName !== "") {
        //根据栏目名获取文章ID
        init(coName);
        initPlugins();
        initListener();
    } else {
        $.ligerDialog.warn("地址URL参数错误,coName缺失或者错误");
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
            [{text: '查看', click: itemclick}, {text: '刷新', click: itemclick}, {
                text: '编辑',
                click: itemclick
            }]

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
            {name: 'id', display: '文章ID', hide: true, width: 70,isSort:false},
            {name: 'coId', display: '栏目ID', hide: true, width: 70,isSort:false},
            {name: 'title', display: '文章标题', align: 'left', width: 300,isSort:false},
            {name: 'subTitle', display: '副标题', align: 'left', width: 200,isSort:false},
            {name: 'publicTime', display: '更新时间',width: 170},
            {
                name: 'status', display: '审核状态', type: 'int', width: 70,
                render: function (rowData) {
                    switch (rowData.status) {
                        case 0:
                            return "<span style='color:dodgerblue'>起草</span>";
                        case 1:
                            return "<span style='color:orangered'>审核中</span>";
                        case 2:
                            return "<span style='color:green'>已发布</span>";
                    }

                }
            },
            {name: 'from', display: '来源',isSort:false},
            {
                name: 'isHot', display: '是否HOT', width: 70,isSort:false,
                render: function (rowData) {
                    if (rowData.isHot === 'T') {
                        return "<span style='color:red'>HOT</span>";
                    } else {
                        return '';
                    }
                }

            },
            {
                name: 'isNew', display: '是否NEW', width: 70,isSort:false,
                render: function (rowData) {
                    if (rowData.isNew === 'T') {
                        return "<span style='color:orange'>NEW</span>";
                    } else {
                        return '';
                    }
                }
            },
            {name: 'clickCount', display: '点击数', width: 70}

        ],
        url: '/admin/arts/' + coId,
        method: 'get',
        usePager: true,
        pageSize: 10,//分页页面大小
        pageSizeOptions: [10, 20, 30],//可指定每页页面大小
        onDblClickRow: function (rowdata, rowindex) {
            //跳转到content页面
            window.open("/content.html?id=" + rowdata.id);
        },
        onContextmenu: function (parm, e) {
            actionCustomerID = parm.data.CustomerID;
            menu.show({top: e.pageY, left: e.pageX});
            return false;
        }
    });

}

function initPlugins() {
    //将一些比如日期选择器等的东西初始化
    $("#txtTime").ligerDateEditor({ showTime: true });

    $.ajax({
        url:"/admin/cols",
        type:'get',
        dataType:"json",
        success:function (result) {
            var cols = result.extend.cols;
            var jsonCols =[];
            $.each(cols,function (index,col) {
                jsonCols.push($.parseJSON('{"name":"'+col.coName+'","id":'+col.coId+',"info":"'+col.coInfo+'"}'));
            });
            console.log(jsonCols);
            comboxCopy=$("#txtCopy").ligerComboBox({
                data:jsonCols,
                textField:'name',
                valueField : 'id'

            });
            comboxMove = $("#txtMove").ligerComboBox({
                data:jsonCols,
                textField:'name',
                valueField : 'id'
            });
        }
    });
}

/**
 * 注册按钮监听器
 **/
function initListener() {
    //有tab被移除的时候 就是编口关闭的时候 刷新表格 虽然会刷新全部的tab 但没有办法了
    top.tab.bind('AfterRemoveTabItem', function (tabID) {
        grid.loadData();
    });
    top.tab.bind('AfterSelectTabItem', function (tabID) {
        grid.loadData();
    });

    $("#btnAdd").click(function () {
        var tabId = "Tab" + new Date().getTime();
        parent.f_addTab(tabId, "编辑文章", "/admin/kindEditor?coId=" + coId);
    });
    $("#btnUpdate").click(function () {
        updateArt();
    });
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
                    case 0:status="起草";break;
                    case 1:status="审核中";break;
                    case 2:status="已发布";break;
                }
                rowmsg += '文章标题： ' + row.title + "  " + "文章副标题   " + row.subTitle + "  "
                    + "更新时间  " + row.publicTime + "  " + "来源  " + row.from + "  " + "点击数 "
                    + row.clickCount + "  " + "状态 " +status + "<br>";
            });
            ids = ids.substring(0, ids.length - 1);

            //提示确认
            var msg = "您将删除以下文章：<br>" + rowmsg;

            if (dlgedit == null) {
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
            } else {
                dlgedit.show();
            }

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
        $.ligerDialog.confirm('确定要发布吗？',function (yes) {
            if (yes){
                if(row.status==2){
                    $.ligerDialog.warn("该文章已经发布，无需再次发布");
                    return;
                }
                simpleUpdateArt('arStatus=2',row.id);
            }
        })

    });
    $("#btnCheck").click(function () {
        if (!selectedRowCheck()) {
            return;
        }
        var row = grid.getSelectedRows()[0];
        $.ligerDialog.confirm('确定要送审吗？',function (yes) {
            if (yes){
                if(row.status>=2){
                    $.ligerDialog.warn("该文章已经发布，无需送审");
                    return;
                }
                simpleUpdateArt('arStatus=1',row.id);


            }
        })
    });
    $("#btnBack").click(function () {
        if (!selectedRowCheck()) {
            return;
        }
        var row = grid.getSelectedRows()[0];
        $.ligerDialog.confirm('确定要退回吗？',function (yes) {
            if (yes){
                if(row.status==0){
                    $.ligerDialog.warn("该文章已经是起草状态，无需退回");
                    return;
                }
                simpleUpdateArt('arStatus=0',row.id);
            }
        })
    });
    $("#btnSetHot").click(function () {
        if (!selectedRowCheck()) {
            return;
        }
        var row = grid.getSelectedRows()[0];
        $.ligerDialog.confirm('确定要设置/取消成HOT文章吗？',function (yes) {
            if (yes){
                if(row.isHot==='T'){
                    simpleUpdateArt('arIshot=F',row.id);
                }else{
                    simpleUpdateArt('arIshot=T',row.id);
                }
            }
        })
    });
    $("#btnSetNew").click(function () {
        if (!selectedRowCheck()) {
            return;
        }
        var row = grid.getSelectedRows()[0];
        $.ligerDialog.confirm('确定要设置/取消成NEW文章吗？',function (yes) {
            if (yes){
                if(row.isNew==='T'){
                    simpleUpdateArt("arIsnew=F",row.id);
                }else{
                    simpleUpdateArt("arIsnew=T",row.id);
                }
            }
        })
    });
    $("#btnFrom").click(function () {
        if (!selectedRowCheck()) {
            return;
        }
        var row = grid.getSelectedRows()[0];
        if(dlgchangfrom===null){
            dlgchangfrom = $.ligerDialog.open({
                buttons:[{text:'确定',onclick:function (i,d) {
                    //取得数据,提交保存
                    d.hide();
                    simpleUpdateArt('arFrom='+$("#txtFrom").val(),row.id);
                    $("#txtFrom").val("");
                }},{text:'取消',onclick:function (i,d) {
                    d.hide();
                    //清空输入框
                    $("#txtFrom").val("");
                }}],
                content:'修改文章来源<br>',
                width: 350,
                target:$("#divfrom")
            });
        }else{
            dlgchangfrom.show();
        }
    });
    $("#btnTime").click(function () {
        if (!selectedRowCheck()) {
            return;
        }
        var row = grid.getSelectedRows()[0];
        if(dlgchangtime===null){
            dlgchangtime = $.ligerDialog.open({
                buttons:[{text:'确定',onclick:function (i,d) {
                    //取得数据,提交保存
                    d.hide();
                    //拼接字符串注意 'abc'+"sdfd" 和 'abc'+'sdfd' 不是同一个东西
                    simpleUpdateArt('arPublictime='+$("#txtTime").val()+':00',row.id);
                    $("#txtTime").val("");
                }},{text:'取消',onclick:function (i,d) {
                    d.hide();
                    //清空输入框
                    $("#txtTime").val("");
                }}],
                content:'修改更新时间<br>',
                width: 350,
                target:$("#divtime")
            });
        }else{
            dlgchangtime.show();
        }
    });
    $("#btnCopy").click(function () {
        if (!selectedRowCheck()) {
            return;
        }
        var row = grid.getSelectedRows()[0];
        if(dlgcopy===null){
            dlgcopy=$.ligerDialog.open({
                buttons:[{text:'确定',onclick:function (i,d) {
                    if($("#txtCopy").val()==null||$("#txtCopy").val()==''){
                        $.ligerDialog.warn("您没有选择栏目");
                        return;
                    }
                    var fcoId = comboxCopy.findValueByText($("#txtCopy").val());
                    if (fcoId ==coId){
                        $.ligerDialog.warn("您选择的栏目与文章所在栏目相同，不必复制");
                        return;
                    }

                    //取得数据,提交保存
                    d.hide();
                    $("#txtCopy").val("");
                    simpleCopyArt(row.id,fcoId);

                    //simpleUpdateArt('arPublictime='+$("#txtTime").val(),row.id);
                }},{text:'取消',onclick:function (i,d) {
                    d.hide();
                    //清空输入框
                    $("#txtCopy").val("");
                }}],
                width: 350,
                target:$("#divcopy")

            });
        }else{
            dlgcopy.show();
        }

    });
    $("#btnMove").click(function () {
        if (!selectedRowCheck()) {
            return;
        }
        var row = grid.getSelectedRows()[0];
        if(dlgmove===null){
            dlgmove=$.ligerDialog.open({
                buttons:[{text:'确定',onclick:function (i,d) {
                    //取得数据,提交保存

                    if($("#txtMove").val()==null||$("#txtMove").val()==''){
                        $.ligerDialog.warn("您没有选择栏目");
                        return;
                    }
                    var fcoId = comboxMove.findValueByText($("#txtMove").val());
                    if (fcoId ==coId){
                        $.ligerDialog.warn("您选择的栏目与文章所在栏目相同，不必移动");
                        return;
                    }
                    d.hide();
                    $("#txtMove").val("");
                    simpleUpdateArt('arColumnid='+fcoId,row.id);
                }},{text:'取消',onclick:function (i,d) {
                    d.hide();
                    $("#txtMove").val("");
                }}],
                width: 350,
                target:$("#divmove")

            });
        }else{
            dlgmove.show();
        }
    });

}

//复制文章 新增文章 服务器处理
function simpleCopyArt(artId,coId) {
    var data ='artId='+artId+'&coId='+coId;
    $.ajax({
        url: "/admin/art/copy",
        type: "post",
        data: data,
        dataType: "json",
        success: function (result) {
            $.ligerDialog.success(result.msg);
            //刷新列表
            grid.loadData();
        }

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
        case '编辑':
            updateArt();
            return;
    }
}

function updateArt() {
    var artId;
    //获取选择的列
    var rows = grid.getSelectedRows();
    if (rows === null || rows.length === 0) {
        $.ligerDialog.warn("您没有选择数据");
    } else if (rows.length > 1) {
        $.ligerDialog.warn("只能选择一篇文章编辑");
    } else {
        //跳转
        artId = rows[0].id;
        var tabId = "Tab" + new Date().getTime();
        parent.f_addTab(tabId, "编辑文章", "/admin/kindEditor?artId=" + artId);
    }
}

function viewArt() {
    var artId;
    //获取选择的列
    var rows = grid.getSelectedRows();
    if (rows === null || rows.length === 0) {
        $.ligerDialog.warn("您没有选择数据");
    } else if (rows.length > 1) {
        $.ligerDialog.warn("只能选择一篇文章查看");
    } else {
        //跳转
        artId = rows[0].id;
        window.open("/content.html?id=" + artId);
    }
}


/**
 * 取出来地址栏get参数
 * @param par 传入的参数名 如果有就取出数职 没有就null;
 * @returns {*}
 **/
function getPar(par) {
    var localUrl = document.location.href;
    var get = localUrl.indexOf(par + "=");
    if (get === -1) {
        return null;
    }
    return localUrl.slice(par.length + get + 1);
}

/**
 * 获取对象的所有属性和方法
 * @param obj
 **/
function displayProp(obj) {
    var names = "";
    for (var name in obj) {
        names += name + ": " + obj[name] + ", ";
    }
    alert(names);
}

