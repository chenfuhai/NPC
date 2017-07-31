$(document).ready(function(){
	$.post("/webs/user_checkLoginopac.action",
				function(data){
					if(data!="0"){				
						$("#a1").hide();
					    $("#a2").show();
					    $("#loginUser").val(data);
					}
					else
					{
						$("#a1").show();
						$("#a2").hide();
					}
				}
			);
})

