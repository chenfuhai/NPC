//检测输入的密码与确认密码是否一致
function isValidate(){
	var psd=$.trim($("#password").val());
	var psds=$.trim($("#passwords").val());
	if(psd==''|| psds==''|| psd!=psds){
		$("#errorWords").html("<font color='red'>两次密码不一样<font>");
		return false;
	}else{
		$("#errorWords").html("");
	}
}
//验证邮编
function checkCode(){
	var str=$("#code").val();
	//var re= /^\d{6,6}$/;
	var re= /^[1-9][0-9]{5}$/
	if(re.test(str)){
		$("#errorCode").html("");
	}
	else{
		$("#errorCode").html("<font color='red'>邮编不正确<font>");
		return false;
	}
	
}
//验证名字
function checkName(){
	var username=$("#username").val();
	if(username==''){
		$("#errorUser").html("<font color='red'>用户名不能为空<font>");
	}
	else{
		$.ajax({
	    	url:'RegisterServlet?action=checkName',/*提交到后台服务器中的action名称*/
	    	type:'post',/*提交方式*/
	    	data: {
	    		username:username/*提交的数据*/
	    	},
	    	success:function(data) { /*当后台服务器响应完成后会返回部分数据,则获取返回的数据*/
	    		if(data.indexOf("注册")!=-1) {
	    			$("#errorUser").html("<font color='red'>收货姓名已存在,请重新输入<font>");
	    			return false;
	    		}
	    		else
	    			$("#errorUser").html("");
	    	 }
	      }); 	
		
	}
}
//验证身份证
function checkIDnum(){
	var str=$("#IDnum").val();
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if(reg.test(str)==false || str==""){
		$("#errorIDnum").html("<font color='red'>身份证输入错误<font>");
		return false;	
	}
	else{
		$("#errorIDnum").html("");
		
	}
}
//验证QQ
function checkQQ(){
	var str=$("#QQ").val();
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
	 //var re = /^1\d{10}$/;
	//利用正则表达式控制用户输入的手机号码格式
	//在这里只能限制输入手机号码,不能控制固定电话或者小灵通号码
	var re=/^(1(([35][0-9])|(47)|[8][01236789]))\d{8}$/;
     if (re.test(str)) {
	     $("#errorPhone").html("");
     } else {
    	 $("#errorPhone").html("<font color='red'>手机号码格式不正确<font>");  
	    return false;
   }
	
}

//提交所有注册信息并进行判断
function subReg(){
	if(checkPhone()==false||checkQQ()==false||
			checkName()==false||checkCode()==false||
			isValidate()==false||checkIDnum()==false){
		alert("注册内容有误");}
	else{
		$.ajax({
			cache: false,
			type: "POST",
			url:"RegisterServlet?action=sub", //把表单数据发送到ajax.jsp
			data:$('#form1').serialize(), //要发送的是ajaxFrm表单中的数据
			async: false,
			error: function(request) {
				alert("发送请求失败!");
			},
			success: function(data) {
				alert(data);
				if(data.indexOf("成功")!=-1) {
					window.location.href="default.jsp";
				}
				//else
					//alert("失败");
				
			}});
//	form1.action="RegisterServlet?action=sub"; 
//	 form1.submit(); 
	}
}