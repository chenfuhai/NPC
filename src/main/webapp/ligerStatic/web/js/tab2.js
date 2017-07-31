


function doClick(o){
	 o.className="navR_current";
	 var j;
	 var id;
	 var e;
	 for(var i=1;i<=5;i++){ //这里3 需要你修改 你多少条分类 就是多少
	   id ="navR_nav"+i;	   
	   j = document.getElementById(id);
	   e = document.getElementById("navR_sub"+i);
	   if(id != o.id){
	   	 j.className="navR_link";
	   	 e.style.display = "none";
	   }else{
			e.style.display = "block";
			 document.getElementById("tspan").innerText=document.getElementsByName("hTitle")[i-1].value;
	   }
	 }
	 }
