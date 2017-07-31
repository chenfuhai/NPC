//获取select标签【装载导航节点内容】
function getSelecTag() {
	$.post("/webs/nav_getMul.action", {
		cataId : -1
	}, function(data) {
		$('#selRoot').prepend(data);
	})
}

//删除sellev一级所有子分类显示
function clearcata(lev){
	//根级目录选中"--请选择分类--",删除所有子分类
	var i=lev+1;
	var cataspan="#cataspan_";
	while($(cataspan+i)[0]){
		$(cataspan+i).remove();
		i++;
	}
}

//取得下一级子分类
function getsubcata(lev){
	var selcata = "#cata_"+lev;
	var gid = -1;
	var selindex = -1;
	if( typeof $( selcata )[0].selectedIndex != 'undefined' ){
		selindex = $( selcata )[0].selectedIndex;
		gid = $( selcata ).val();
		if( selindex > 0 ){
			$("#dlgcataname").val( $(selcata+" option:selected").text() );
		} else {
			if( gid == -1 )//没有选任何分类
				$("#dlgcataname").val( "" );
			else{//子分类中选了第一个
				$("#dlgcataname").val( $("#cata_"+(lev-1)+" option:selected").text() );
			}
		}
		$("#dlgcataid").val(gid);
	}
	if( selindex != -1 ){
		clearcata(lev);
	}
	if(selindex==0)
		return;
	$.post("/webs/nav_getMul.action",
		{cataId:gid,groupId:lev+1},
		function(data){
			
			var newcata="#cataspan_"+lev;
			if( selindex == -1 ){
				$("#cata_0").empty();
			}
			if(data != "-1"){//有子分类的处理
				$(newcata).after(data);
			}
			
		}
	);	
}

//新增导航
function addNav(fla){
	if(fla==1){			//查找导航节点提交
		$cataname = $('#dlgcataname').val();
		$cataid = $('#dlgcataid').val();
		if($cataid==-1||$cataid==''){
			alert("请先选择分类再提交!");
			return;
		}
		$.post("/webs/nav_saveMyLink.action",
				{navName:$cataname,cataId:$cataid},
				function(data){
					callbac(data);
				}
		)
	}
	if(fla==2){         //自定义链接提交
		$navName = $('#navName').val();
		$navUrl = $('#navUrl').val();
		
		if($.trim($navName)==""){
			alert("导航栏名称不能为空!");
			return;
		}
		if($.trim($navUrl)==""){
			alert("导航栏链接不能为空!");
			return;
		}
		$.post("/webs/nav_saveMyLink.action",
				{navName:$navName,navUrl:$navUrl},
				function(data){
					callbac(data);
				}
		)
	}
}
function callbac(data){
	if(data != 0){
		alert("添加成功");
	}
/**
	if(data==1){
		alert("添加成功!");
	}
	if(data==2){
		alert("实在抱歉，添加失败!");
	}
**/
 location.reload();
}

