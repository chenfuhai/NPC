function editHandle(){
	//后来改进的
	//采用ajax方式进行异步刷新数据
	$.ajax({
		cache: false,
		type: "POST",
		url:"TelephoneServletManage?action=afterEdit", //把表单数据发送到ajax.jsp
		data:$('#ajaxFrm').serialize(), //要发送的是ajaxFrm表单中的数据
		async: false,
		error: function(request) {
			alert("发送请求失败!");
		},
		success: function(data) {
			alert(data);
			if(data.indexOf("成功")!=-1){//！=-1代表查询一遍查到了
				window.location.href="RecordServlet?action=showrecord";
			}
			//else
				//alert("修改失败！");		
		}
		});
	//最原始的方法
	 //form1.action="AddressServlet?action=editHandle"; 
	 //form1.submit(); 
	
}
