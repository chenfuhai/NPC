var xmlHttp;  
/*function sub(){ //提交之后对所填情况进行检查
              var brand=document.form1.brand.value;
              var shouhouname=document.form1.shouhouname.value;
              var banben=document.form1.banben.value;
              var huowutype=document.form1.huowutype.value;
              var BuyDate=document.form1.BuyDate.value;
              var imei=document.form1.imei.value;
              var fromwinboth=document.form1.fromwinboth.value;
              var reason=document.form1.reason.value;
              var requestservice=document.form1.requestservice.value;
              if(brand==null||brand==""){
            	  document.getElementById("brand1").innerHTML="请选择品牌";
                    
              }
              else  if(shouhouname==null||shouhouname==""){
            document.getElementById("shouhouname1").innerHTML="型号颜色没有填";
           
          }
        else if(
        		huowutype==null||huowutype=="")
          {
                   document.getElementById("huowutype1").innerHTML="货物类别没有填";
         
          }
         else{ 
        form1.action="servlet/ShouhouInfoServlet" ;           
        form1.submit();
        }
}
  
function checkMail(str){ //检查邮箱格式是否正确
	   var strReg=""; 
	  var r; 
	   var strText=document.all(str).value; 
	//strReg=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/i;
	  strReg=/^\w+((-\w+)|(\.\w+))*\@{1}\w+\.{1}\w{2,4}(\.{0,1}\w{2}){0,1}/ig;
	  r=strText.search(strReg); 
	  if(r==-1) { 
	  document.getElementById("ema").innerHTML="邮箱格式不正确";
	  document.getElementById("email1").value="";
	  //document.getElementById("upmit").disabled=true; 
	 } else
		 {
		 document.getElementById("ema").innerHTML="格式正确";
		// document.getElementById("upmit").disabled=false; 
		 }
	   }
  
  function isValidate(){ //调用的函数，确认密码情况
    var pw1=document.form1.password.value;
    var pw2=document.form1.password1.value;
    if(pw1!=null&&pw1!=""){
      if(pw1==pw2){
document.getElementById("tishi").innerHTML="密码相同";
 // document.getElementById("upmit").disabled=false;
            }
   else{
document.getElementById("tishi").innerHTML="密码不相同";
//document.getElementById("upmit").disabled=true;
document.getElementById("pwd1").value="";
       }
       }
       else{
      document.getElementById("tishi").innerHTML="密码不能为空";
    //  document.getElementById("upmit").disabled=true;
       }
                         }
function creatXMLHttp(){  //ajax 
  if(window.XMLHttpRequest)
  {
         xmlHttp=new  XMLHttpRequest();

  }
else
{
      xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
}
}
function checkUse(user){  //ajax 传递进行验证
   creatXMLHttp();
   xmlHttp.open("POST","CheckServlet?user="+user);
   xmlHttp.onreadystatechange=checkUseCallBack;
   xmlHttp.send(null);
}
function checkUseCallBack(){  //ajax  回调
if(xmlHttp.readyState==4){
          if(xmlHttp.status==200){
            var text=xmlHttp.responseText;
            if(document.getElementById("user").value!=null &&
            document.getElementById("user").value!=""){
                if(text=="true"){ //用户名已经存在
            document.getElementById("msg").innerHTML="此用户名不可以使用";
           document.getElementById("upmit").disabled=true;               
                              }
                  else{
               
         document.getElementById("msg").innerHTML="此用户名可以使用";
         document.getElementById("upmit").disabled=false;    
                       }
                                  }
                                  else{
         document.getElementById("msg").innerHTML="用户名必须输入";  
         document.getElementById("upmit").disabled=true;   
                                  }
                                  }
                          }
                     }
*/

//实时判断输入的品牌是否输入为空
function checkBrand() {
	var brand=$("#brand").val();
	if(brand=='') {
		$("#brand1").html("<font color='red'>品牌不能为空,请先选择</font>");
		return false;
	}
	else {
		$("#brand1").html("");
		return true;
	}
}
//实时判断输入的型号颜色是否输入为空
function checkShouhouname() {
	var shouhouname=$("#shouhouname").val();
	if(shouhouname==''||shouhouname==null) {
		$("#shouhouname1").html("<font color='red'>型号颜色不能为空,请先选择</font>");
		return false;
	}
	else  {
		$("#shouhouname1").html("");
		return true;
	}
	
		
}
//实时判断输入的品牌货物版本是否输入为空
function checkBanben() {
	var banben=$("#banben").val();
	if(banben=='') {
		$("#banben1").html("<font color='red'>货物版本不能为空,请先选择</font>");
		return false;
	}
	else {
		$("#banben1").html("");
		return true;
	}
}
//实时判断输入的货物类别是否输入为空
function checkHuowutype() {
	var huowutype=$("#huowutype").val();
	if(huowutype=='') {
		$("#huowutype1").html("<font color='red'>货物类别不能为空,请先选择</font>");
		return false;
	}
	else {
		$("#huowutype1").html("");
		return true;
	}
}
//实时判断输入的购买日期是否输入为空
function checkBuyDate() {
	var BuyDate=$("#BuyDate").val();
	if(BuyDate=='') {
		$("#BuyDate1").html("<font color='red'>购买日期不能为空,请先选择</font>");
		return false;
	}
	else {
		$("#BuyDate1").html("");
		return true;
	}
}
//实时判断输入的手机串号是否输入为空
function checkImei() {
	var imei=$("#imei").val();
	if(imei==''||imei==null) {
		$("#imei1").html("<font color='red'>手机串号不能为空,请先选择</font>");
		return false;
	}
	else {
		$("#imei1").html("");
		return true;
	}
}
//实时判断输入的何处购买是否输入为空
function checkFromwinboth() {
	var fromwinboth=$("#fromwinboth").val();
	if(fromwinboth=='') {
		$("#fromwinboth1").html("<font color='red'>何处购买不能为空,请先选择</font>");
		return false;
	}
	else {
		$("#fromwinboth1").html("");
		return true;
	}
}
//实时判断输入的存在问题是否输入为空
function checkreRason() {
	var reason=$("#reason").val();
	if(reason==''||reason==null) {
		$("#reason1").html("<font color='red'>存在问题不能为空,请先选择</font>");
		return false;
	}
	else {
		$("#reason1").html("");
		return true;
	}
}
//实时判断输入的申请服务是否输入为空
function checkRequestservice() {
	var requestservice=$("#requestservice").val();
	if(requestservice=='') {
		$("#requestservice1").html("<font color='red'>申请服务不能为空,请先选择</font>");
		return false;
	}
	else {
		$("#requestservice1").html("");
		return true;
	}
}
function sub(){
/*	var shouhouname=$("#shouhouname").val();
	var banben=$("#banben").val();
	var huowutype=$("#huowutype").val();
	var BuyDate=$("#BuyDate").val();
	var imei=$("#imei").val();
	var fromwinboth=$("#fromwinboth").val();
	var reason=$("#reason").val();
	var requestservice=$("#requestservice").val();*/
	
	 if(checkBrand()==true &&  checkShouhouname()==true && checkBanben()==true &&
			 checkHuowutype()==true && checkFromwinboth()==true &&
			 checkImei()==true && checkreRason()==true && checkRequestservice()==true) {
		 form1.action="ShouhouInfoServlet";           
	     form1.submit();	
	 }
				
}

function download() {
	window.open('DownloadServlet?curfile='+curfile+'&path='+path,'newwindow','height=30,width=40,top='+yy+',left='+xx+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}


