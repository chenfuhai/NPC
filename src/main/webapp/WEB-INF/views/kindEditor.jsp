<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>

<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>

	<link rel="stylesheet" href="../static/themes/default/default.css" />
	<link rel="stylesheet" href="../static/plugins/code/prettify.css" />
	<script charset="utf-8"  src="../static/kindeditor-all.js"></script>
	<script charset="utf-8"  src="../static/lang/zh-CN.js"></script>
	<script charset="utf-8"  src="../static/plugins/code/prettify.js"></script>
   <script src="/static/js/jquery-3.2.1.min.js"></script>
	<script>
		var editor;
		KindEditor.ready(function(K) {
			 editor= K.create('textarea[name="arContent"]', {
			    width:'700px',
				height:'400px',
				minHeight:'400px',
                resizeType:0,
				cssPath : '../static/plugins/code/prettify.css',
				uploadJson : '/admin/uploadFile',
                fileManagerJson:'/admin/fileManager',
                fillDescAfterUploadImage:true,
                allowFileManager:true,
                filePostName:"file",

                items:[
                    'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
                    'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                    'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                    'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
                    'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                    'anchor', 'link', 'unlink'
                ],
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();

            var coId;
            function getPar(par) {
                var localUrl = document.location.href;
                var get = localUrl.indexOf(par+"=");
                if(get === -1){
                    return false;
                }

                var get_par  = localUrl.slice(par.length+get+1);
                return get_par;
            }


            alert('coId='+getPar("coId"));
            coId = getPar("coId");

            $("#btnSave").click(function () {
                if (coId!==null ||coId!==""){
                    $("#coId").val(coId);
                    //同步编辑器
                    editor.sync();
                    alert("ddd");
                    //保存文章
                    $.ajax({
                        url:"/admin/art/",
                        type:"post",
                        dataType:"json",
                        data:$("#saveForm").serialize(),
                        success:function (result) {
                            alert(result.msg);
                            window.open("","_self");
                            window.close();
                        }


                    });
                }
            });

        });



	</script>

</head>
<body>
	<%=htmlData%>
	<form id="saveForm" >
		<div class="div-default" >
			标题：<input type="text" name="arTitle"><br>
			副标题：<input type="text" name="arSubTitle"><br>
			所属栏目：<input type="text" ><br>
			<input type="hidden" value="" name="arColumnid" id="coId">
		</div>

			<textarea name="arContent" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;">

				<%=htmlspecialchars(htmlData)%></textarea>
	</form>
	<br />

	<div class="btn-default">
		<button id="btnSave">保存</button>&nbsp;&nbsp;&nbsp;<button id="btnPublic">发布</button>

	</div>


	<style>

		.div-default{
			margin: 0 auto;
			width:700px;
			text-align: left;
		}
		.btn-default{
			margin: 0 auto;
			text-align: center;
		}
		.ke-container{
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