
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
                },
                error:function (result) {
                    alert("发生错误，可能是网络连接失败或登录过期，请重新登录或检查网络 "+"状态码"+result.status+"错误信息"+result.statusText);
                    console.log("发生错误，可能是网络连接失败或登录过期，请重新登录或检查网络 "+"状态码"+result.status+"错误信息"+result.statusText);
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
        },
        error:function (result) {
            alert("发生错误，可能是网络连接失败或登录过期，请重新登录或检查网络 "+"状态码"+result.status+"错误信息"+result.statusText);
            console.log("发生错误，可能是网络连接失败或登录过期，请重新登录或检查网络 "+"状态码"+result.status+"错误信息"+result.statusText);
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
                if (result.code==200){
                    $.ligerDialog.warn(result.msg);
                    return;
                }else if(result.code==100){
                    $.ligerDialog.success(result.msg,function () {
                        closeSelf();
                    });

                    //top.tab.removeTabItem('addArtTab');

                }
            },
            error:function (result) {
                alert("发生错误，可能是网络连接失败或登录过期，请重新登录或检查网络 "+"状态码"+result.status+"错误信息"+result.statusText);
                console.log("发生错误，可能是网络连接失败或登录过期，请重新登录或检查网络 "+"状态码"+result.status+"错误信息"+result.statusText);
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
                if (result.code==200){
                    $.ligerDialog.warn(result.msg);
                    return;
                }else if(result.code==100){
                    $.ligerDialog.success(result.msg,function () {
                        closeSelf();
                    });

                    //top.tab.removeTabItem('addArtTab');

                }
            },
            error:function (result) {
                alert("发生错误，可能是网络连接失败或登录过期，请重新登录或检查网络 "+"状态码"+result.status+"错误信息"+result.statusText);
                console.log("发生错误，可能是网络连接失败或登录过期，请重新登录或检查网络 "+"状态码"+result.status+"错误信息"+result.statusText);
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


