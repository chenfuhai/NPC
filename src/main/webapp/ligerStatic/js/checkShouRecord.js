	function checkRecord(type) {
		//alert('1111');
			var msg="";
			if(type=='pass') {
				msg="你确定要通过该售后信息申请吗?";
			}
			else
			if(type=='nopass') {
				msg="你确定要不通过该售后信息申请吗?";
			}
			//alert(msg);
			var confirm=window.confirm(msg);
			if(confirm) { //点击确定返回true执行下面语句，否则点击取消返回false不执行下列语句
				$.ajax({
					cache: false,
					type: "POST",
					url:"TelephoneServletManage?action=checkrecord&type="+type, //把表单数据发送到ajax.jsp
					data:$('#form').serialize(), //要发送的是ajaxFrm表单中的数据
					async: false,
					error: function(request) {
						alert("发送请求失败!");
					},
					success: function(data) {//data相当于action=checkrecord中out.print(message)中的message;
						alert(data);
						if(data.indexOf("成功")!=-1){//！=-1代表查询一遍查到了
							window.location.href="TelephoneServletManage?action=querydeal";
						}	
					}
					});
			
				//form.action="TelephoneServletManage?action=checkrecord&type="+type;
				//form.submit();
				//return true;
			}
		}