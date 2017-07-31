//新增收货姓名时 调用的方法
//实时检测输入的收货姓名是否为空 并且判断输入的收货姓名是否已经存在
//如果存在则提示客户 重新输入收货姓名
function checkRname() {
	//$.trim()是去除首尾空格函数
	//$().val()获取指定ID输入的值
	//#id
	var rname=$.trim($("#rname").val());
	if(rname=='') {
		$("#errorRname").html("<font color='red'>收货姓名不可为空<font>");
		return false;
	}
	else {
		$.ajax({
	    	url:'CheckAddressServlet?action=checkrname',/*提交到后台服务器中的action名称*/
	    	type:'post',/*提交方式*/
	    	
	    	data: {
	    		rname:rname,/*提交的数据*/
	    		type:'add'

	    	},
	    	success:function(data) { /*当后台服务器响应完成后会返回部分数据,则获取返回的数据*/
	    	//	alert(data);
	    		if(data.indexOf("存在")!=-1) {
	    			$("#errorRname").html("<font color='red'>收货姓名已存在,请重新输入<font>");
	    		}
	    		else
	    			$("#errorRname").html("");
	    	 }
	      }); 	
	}
	
}

function checkExpress(){
	var express=$("posttype").val();
	alert(express);
	
}

function checkAddress(){
	var address=$.trim($("#address").val());
	if(address==''){
		$("#errorAddress").html("<font color='red'>地址不可为空<font>");
		return false;
	}else{
		$("#errorAddress").html("");
	}
	
}

//邮编判断是否合法
function checkCode(){
	var str=$("#code").val();
	//var re= /^1\d{5}$/;
	var re= /^[1-9][0-9]{5}$/
	if(re.test(str)){
		$("#errorcode").html("");
	}
	else{
		$("#errorcode").html("<font color='red'>邮编格式不正确<font>");
		return false;
	}
	
}


//电话号码判断
function checkMobile() {
	
  var str=$("#phone").val();
  //var re = /^1\d{10}$/;
  //利用正则表达式控制用户输入的手机号码格式
  //在这里只能限制输入手机号码,不能控制固定电话或者小灵通号码
  var re=/^(1(([35][0-9])|(47)|[8][01236789]))\d{8}$/;
  if (re.test(str)) {
	  $("#errorphone").html("");  
  } else {
	  $("#errorphone").html("<font color='red'>手机号码格式不正确<font>");  
	  return false;
   }
}



//修改收货姓名时 调用的方法
//实时检测输入的收货姓名是否为空 并且判断输入的收货姓名是否已经存在
//如果存在则提示客户 重新输入收货姓名
function checkUpdateRname() {
	//$.trim()是去除首尾空格函数
	//$().val()获取指定ID输入的值
	//#id
	var rname=$.trim($("#rname").val());
	var oldid=$("#oldid").val();
	if(rname=='') {
		$("#errorRname").html("<font color='red'>收货姓名不可为空<font>");
		return false;
	}
	else {
		$.ajax({
	    	url:'CheckAddressServlet?action=checkrname',/*提交到后台服务器中的action名称*/
	    	type:'post',/*提交方式*/
	    	data: {
	    		rname:rname,/*提交的数据*/
	    		type:'update',
	    		old_id:oldid

	    	},
	    	success:function(data) { /*当后台服务器响应完成后会返回部分数据,则获取返回的数据*/
	    		if(data.indexOf("存在")!=-1) {
	    			$("#errorRname").html("<font color='red'>收货姓名已存在,请重新输入<font>");
	    			return false;
	    		}
	    		else
	    			$("#errorRname").html("");
	    	 }
	      }); 	
	}
	
}
//修改提交表单并进行判断
function editHandle(){
	//后来改进的
	//采用ajax方式进行异步刷新数据
	if(checkUpdateRname()==false||checkMobile()==false||
			checkCode()==false||checkAddress()==false||
			postType()==false){
		alert("修改内容有错误");
	}	
	else{
	$.ajax({
		cache: false,
		type: "POST",
		url:"AddressServlet?action=editHandle", //把表单数据发送到ajax.jsp
		data:$('#ajaxFrm').serialize(), //要发送的是ajaxFrm表单中的数据
		async: false,
		error: function(request) {
			alert("发送请求失败!");
		},
		success: function(data) {
			alert(data);
			if(data.indexOf("成功")!=-1) {
				window.location.href="AddressServlet?action=scan";
			}
			/**else
				alert("失败");*/
			
		}});
	}
	//最原始的方法
	 //form1.action="AddressServlet?action=editHandle"; 
	 //form1.submit(); 
	
}

//对于快递选择状态进行判断
function postType(){
	var options=$("#posttype option:selected");
	if(options.text()=="请选择"){
		$("#errorPost").html("<font color='red'>快递方式没有选择<font>");
		return false;
	}
	else{
		$("#errorPost").html("");
		
	}
}

function sub(){
	//后来改进的
	//采用ajax方式进行异步刷新数据
//	var express=$("posttype").text();
//	alert("11"+express);
	
	
	
	if(checkRname()==false||checkMobile()==false||
		checkCode()==false||checkAddress()==false||
		postType()==false)
	{
	
		alert("提交内容有错误");
	}	
	else{
		
	$.ajax({
		cache: false,
		type: "POST",
		url:"AddressServlet?action=sub", //把表单数据发送到ajax.jsp
		data:$('#ajaxform1').serialize(), //要发送的是ajaxFrm表单中的数据
		async: false,
		error: function(request) {
			alert("发送请求失败!");
		},
		success: function(data) {
			alert(data);
			if(data.indexOf("成功")!=-1) {
				window.location.href="AddressServlet?action=scan";
			}
			//else
				//alert("失败");
			
		}});
     }
	//最原始的方法
	 //form1.action="AddressServlet?action=editHandle"; 
	 //form1.submit(); 
	
    }
