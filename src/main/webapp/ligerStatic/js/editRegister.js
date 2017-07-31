function editRegisterHandle(){
	if(isValidate()==false||checkCode()==false||checkQQ()==false||checkPhone()==false)
	{
		alert("修改内容有错");
		}
	
	else{
	$.ajax({
		cache: false,
		type: "POST",
		url:"EditRegisterServlet?action=editHandle", //把表单数据发送到ajax.jsp
		data:$('#form1').serialize(), //要发送的是ajaxFrm表单中的数据
		async: false,
		error: function(request) {
			alert("发送请求失败!");
		},
		success: function(data) {
			alert(data);
			if(data.indexOf("成功")!=-1) {
				window.location.href="register.jsp";
			}
			else
				alert("失败");
			
		}});
	}
//	 form1.action="EditRegisterServlet?action=editHandle"; 
//	 form1.submit(); 
	}
function isValidate(){
	var psd=$.trim($("#userpass").val());
	if(psd==''){
		$("#errorWords").html("<font color='red'>密码不为空<font>");
		return false;
	}else{
		$("#errorWords").html("");
	}
}
//验证邮编
function checkCode(){
	var str=$("#code").val();
	var re= /^\d{6,6}$/;
	if(re.test(str)){
		$("#errorCode").html("");
	}
	else{
		
		$("#errorCode").html("<font color='red'>邮编不正确<font>");
		return false;
	}
	
}


//验证QQ
function checkQQ(){
	var str=$("#qq").val();
	if(str==''){
		$("#errorQQ").html("<font color='red'>QQ必填<font>");
		return false;
	}
	else{
		$("#errorQQ").html("");
		
	}
	
}

//手机验证

function checkPhone(){
	
	 var str=$("#phone").val();
	 var re = /^1\d{10}$/;
   if (re.test(str)) {
	   $("#errorPhone").html("");
     
   } else {
	   $("#errorPhone").html("<font color='red'>手机号码格式不正确<font>");  
	   return false;
   }
	
}

