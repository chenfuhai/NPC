
function noticeUp(){
	
	if(checkTitle()==false||checkContent()==false){
		alert("公告发布失败");
	}
	else{	
		$.ajax({	
		cache: false,
		type: "POST",
		url:"NoticeServlet?action=releaseHandle", //把表单数据发送到NoticeServlet并调用相应的方法
		data:$('#form1').serialize(), //要发送的是form1表单中的数据
		async: false,
		error: function(request) {
			alert("发送请求失败!");
		},
		success: function(data) {
			alert(data);
			if(data.indexOf("成功")!=-1) {
				window.location.href="NoticeServlet?action=notice";
			}
			//else
				//alert("失败");
			
		}});
	}
}

function checkTitle(){
	
	var title=$("#note_title").val();
	if(title==null||title==''){
		$("#error_title").html("<font color='red'>标题不能为空<font>");
	return false;
	}
	else{
		$("#error_title").html("");	
	}
	
}

function checkContent(){
	
	var title=$("#note_content").val();
	if(title==null||title==''){
		$("#error_content").html("<font color='red'>发布内容不能为空<font>");
	return false;
	}
	else{
		$("#error_content").html("");	
	}
	
}