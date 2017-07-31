function getClass(arr,className){
	var tempArr = new Array();
	for(i=0;i<arr.length;i++){
		if(arr[i].className == className){
			tempArr.push(arr[i]);
		}
	}
	return tempArr;
}
function getStyle(obj,attr){
	if(obj.currentStyle){
		return obj.currentStyle[attr];
	}
	else{
		return getComputedStyle(obj,false)[attr];
	}
}
function move(obj,json,fn){
	clearInterval(obj.timer);
	obj.timer = setInterval(function(){
		var state = true;
		for(attr in json){
			var cur = parseInt(getStyle(obj,attr));
			if(attr == "opacity"){
				cur = parseFloat(getStyle(obj,attr));
				cur *= 100;
				cur = parseInt(cur);
			}
			var speed = (json[attr] - cur) / 4;
			speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
			if(cur != speed){
				state = false;
			}
			cur += speed;
			if(attr == "opacity"){
				obj.style.opacity = cur / 100;
				obj.style.filter = "alpha(opacity=" + cur + ")";
			}
			else{
				obj.style[attr] = cur + "px";
			}
		}
		if(state){
			clearInterval(obj.timer);
			if(fn){
				fn();
			}
		}
	},20)
}
function popMove(obj,attr,target){
	clearInterval(obj.timer);
	var speed = 0;
	var cur = parseInt(getStyle(obj,attr));
	obj.timer = setInterval(function(){
		speed += (target - cur) / 5;
		speed *= 0.7;
		if(Math.abs(speed) < 1 && Math.abs(target - cur) < 1){
			clearInterval(obj.timer);
			cur = target;
		}
		else{
			cur += speed;
		}
		obj.style[attr] = cur + "px";
	},30)
}