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
	<link rel="stylesheet" href="../themes/default/default.css" />
	<link rel="stylesheet" href="../plugins/code/prettify.css" />
	<script charset="utf-8" src="../kindeditor-all.js"></script>
	<script charset="utf-8" src="../lang/zh-CN.js"></script>
	<script charset="utf-8" src="../plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
			    width:'700px',
				height:'400px',
				minHeight:'400px',
                resizeType:0,
				cssPath : '../plugins/code/prettify.css',
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
                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
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
		});
	</script>
</head>
<body>
	<%=htmlData%>
	<form name="example" method="post" action="demo.jsp">
		<textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;">
			<p>
	<strong><u>lkJFLSDJFLJSALDJFL;SJAD</u></strong>
</p>
<p style="text-align:center;">
	<br />
</p>
<p style="text-align:center;">
	FSADLKFJSAL;DF
</p>
<p style="text-align:center;">
	<br />
</p>
<p style="text-align:center;">
	<u><s>ASLDFJLASDJF</s></u>
</p>
<p style="text-align:center;">
	<u><s>'ASLLKDFH;LJ</s></u>
</p>
<p style="text-align:center;">
	<u><s>'</s></u>
</p>
<p style="text-align:center;">
	<u><s>A<span style="line-height:2;">SDLFHLSAJDF</span></s></u>
</p>
<p style="text-align:center;">
	<br />
</p>
<p style="text-align:center;">
	<span style="line-height:2;">ASLDFHL;SJDF</span>
</p>
<p style="text-align:center;">
	<span style="line-height:2;">'</span>
</p>
<p style="text-align:center;">
	<br />
</p>
<p style="text-align:center;">
	<span style="line-height:2;">LAKHSLDFJ</span>
</p>
<p>
	<br />
</p>
<p>
	<br />
</p>
<p>
	<br />
</p>
<p>
	<br />
</p>





			<%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
	</form>
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