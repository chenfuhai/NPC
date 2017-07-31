<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>KindEditor JSP</title>

    <link rel="stylesheet" href="/static/themes/default/default.css"/>
    <link rel="stylesheet" href="/static/plugins/code/prettify.css"/>
    <script charset="utf-8" src="/static/kindeditor-all.js"></script>
    <script charset="utf-8" src="/static/lang/zh-CN.js"></script>
    <script charset="utf-8" src="/static/plugins/code/prettify.js"></script>
    <script src="/ligerStatic/lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>

    <link href="/ligerStatic/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
    <link href="/ligerStatic/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css">
    <link href="/ligerStatic/css/style-item.css" rel="stylesheet" media="all"/>
    <script src="/ligerStatic/lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

    <script src="/ligerStatic/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>

    <script src="/ligerStatic/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
    <script src="/ligerStatic/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="/ligerStatic/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>

    <script>
        var editor;
        var coName;
        var coId;
        var artId;


        KindEditor.ready(function (K) {
            editor = K.create('textarea[name="arContent"]', {
                width: '700px',
                height: '400px',
                minHeight: '400px',
                resizeType: 0,
                cssPath: '/static/plugins/code/prettify.css',
                uploadJson: '/admin/uploadFile',
                fileManagerJson: '/admin/fileManager',
                fillDescAfterUploadImage: true,
                allowFileManager: true,
                filePostName: "file",

                items: [
                    'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
                    'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                    'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                    'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
                    'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                    'anchor', 'link', 'unlink'
                ],
                afterCreate: function () {
                    var self = this;
                    K.ctrl(document, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                    K.ctrl(self.edit.doc, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                }
            });
            prettyPrint();


            //取出来coid和id 判断 如果没有就不做事 如果有就做事
            coId = getPar("coId");
            artId = getPar("artId");

            //如果某一个不为空 那么就是另一个 如果两个都为空,都有值 提示错误 关闭编辑器
            if ((coId == null && artId == null ) || (coId != null && artId != null)) {
                $.ligerDialog.warn("地址URL参数错误,不存在coId或artId");
            } else if (coId != null) {
                $.ligerDialog.warn("地址URL参数错误,不存在coId或artId");
                //新增文章页面
                fillCoName(coId, "/admin/cols");

                $("#btnSave").click(function () {
                    //设置文章状态默认为0 草稿状态
                    $("#ar_status").empty().val(0);
                    saveArt();
                });
                $("#btnCheck").click(function () {
                    //设置文章状态默认为1 审核中状态
                    $("#ar_status").empty().val(1);
                    saveArt();
                });
                $("#btnPublic").click(function () {
                    //设置文章状态默认为2 已发布状态
                    $("#ar_status").empty().val(2);
                    saveArt();
                });
            } else {
                //更新文章页面
                fillAll("/admin/art/" + artId);

                $("#btnSave").click(function () {
                    //不对文章状态做出改变
                    updateArt();
                });
                $("#btnCheck").click(function () {
                    //设置文章状态默认为1 审核中状态
                    $("#ar_status").empty().val(1);
                    updateArt();
                });
                $("#btnPublic").click(function () {
                    //设置文章状态默认为2 已发布状态
                    $("#ar_status").empty().val(2);
                    updateArt();
                });
            }

            $("#btnCancle").click(function () {
                $.ligerDialog.confirm("您确定要取消？所做的更改将不会保存。", function (yes) {
                    if (yes) {
                        closeSelf();
                    }
                });
            });


        });


        //编辑状态 更新文章填入相关信息 提交保存的地址
        function fillAll(ajaxUrl) {

            $.ajax({
                url: ajaxUrl,
                type: 'get',
                dataType: "json",
                success: function (result) {
                    var art = result.extend.art;
                    //根据art的对应栏目ID 取出栏目名称
                    $.ajax({
                        url: "/admin/col/" + art.arColumnid,
                        type: 'get',
                        dataType: "json",
                        success: function (result) {
                            var col = result.extend.col;
                            $("#ar_coname").val(col.coName);
                        }

                    });
                    //标题等各种
                    //$("#ar_contnet").empty().text(art.arContent); 不能用这种方法
                    editor.html(art.arContent);
                    $("#ar_title").empty().val(art.arTitle);
                    $("#ar_subtitle").empty().val(art.arSubtitle);
                    $("#ar_status").empty().val(art.arStatus);
                    $("#ar_from").empty().val(art.arFrom);
                }
            });

        }

        //起草状态  保存文章 填入栏目的名字就好
        function fillCoName(coId, ajaxUrl) {
            $.ajax({
                url: ajaxUrl,
                type: 'get',
                dataType: "json",
                success: function (result) {
                    coName = "";
                    var cols = result.extend.cols;
                    $.each(cols, function (index, col) {

                        if (col.coId == coId) {
                            coName = col.coName;
                        }
                    });
                    if (coName != "") {
                        //填入栏目一栏
                        $("#ar_coname").val(coName);
                    } else {
                        $.ligerDialog.warn("地址URL参数错误,不存在栏目");
                    }
                }

            });
        }

        /**
         * 取出来地址栏get参数
         * @param par 传入的参数名 如果有就取出数职 没有就null;
         * @returns {*}
         */
        function getPar(par) {
            var localUrl = document.location.href;
            var get = localUrl.indexOf(par + "=");
            if (get === -1) {
                return null;
            }
            return localUrl.slice(par.length + get + 1);
        }

        function saveArt() {
            if (coId !== null || coId !== "") {
                $("#coId").val(coId);
                //同步编辑器
                editor.sync();
                //保存文章
                $.ajax({
                    url: "/admin/art/",
                    type: "post",
                    dataType: "json",
                    data: $("#saveForm").serialize(),
                    success: function (result) {
                        $.ligerDialog.success(result.msg);
                        //top.tab.removeTabItem('addArtTab');
                        closeSelf();
                    }
                });
            }
        }


        function updateArt() {
            if (artId !== null || artId !== "") {
                //同步编辑器
                editor.sync();
                //保存文章
                $.ajax({
                    url: "/admin/art/" + artId,
                    type: "put",
                    dataType: "json",
                    data: $("#saveForm").serialize(),
                    success: function (result) {
                        $.ligerDialog.success(result.msg);
                        //top.tab.removeTabItem('addArtTab');
                        closeSelf();
                    }
                });
            }
        }

        function closeSelf() {
            top.tab.removeTabItem(top.tab.getSelectedTabItemID());
        }


        function displayProp(obj) {
            var names = "";
            for (var name in obj) {
                names += name + ": " + obj[name] + ", ";
            }
            alert(names);
        }


    </script>

</head>
<body>
<%=htmlData%>
<form id="saveForm">
    <div class="div-default">
        标题：<input type="text" name="arTitle" id="ar_title"><br>
        副标题：<input type="text" name="arSubtitle" id="ar_subtitle"><br>
        所属栏目：<input type="text" id="ar_coname" ><br>
        来源：<input type="text" id="ar_from" name="arFrom"><br>
        <input type="hidden" value="" name="arColumnid" id="coId">
        <input type="hidden" value="" name="arStatus" id="ar_status">
    </div>

    <textarea id="ar_contnet" name="arContent" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;">

				<%=htmlspecialchars(htmlData)%></textarea>
</form>
<br/>

<div class="btn-default">
    <button id="btnSave">保存</button>&nbsp;&nbsp;&nbsp;<button id="btnCheck">送审</button>
    <button id="btnPublic">发布</button>&nbsp;&nbsp;&nbsp;<button id="btnCancle">取消</button>

</div>


<style>

    .div-default {
        margin: 0 auto;
        width: 700px;
        text-align: left;
    }

    .btn-default {
        margin: 0 auto;
        text-align: center;
    }

    .ke-container {
        margin: 0 auto;

    }
</style>

</body>
</html>
<%!
    private String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }
%>