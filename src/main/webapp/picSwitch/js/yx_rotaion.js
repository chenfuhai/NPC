(function($){
	//匿名函数中传入入JQuery并直接调用 jQuery直接用$代替了  开发Jquery插件的格式
	//$.fn.extend 给所有的jQuery对象添加方法，也就是说这个时候 每一个Jquery对象都可以使用添加的这个方法了
	//$()使用JQuery选择器选择的都是jQuery对象 其实$()整个才是一个JQuery对象
	//jQuery和js对象相互转化是 this===$(this)[0]
	//判断一个对象是否为jquery对象可以用 obj instanceof jQuery 
	//javascript的对象是什么类型，可以使用typeof, 
    $.fn.extend({     
         yx_rotaion: function(options) {   
		    //extend扩展方法：extend(dest,src1,src2,src3...);
			//src1,src2,src3...合并到dest中,返回值为合并后的dest
			//var result=$.extend({},{name:"Tom",age:21},{name:"Jerry",sex:"Boy"})
			//result={name:"Jerry",age:21,sex:"Boy"}
			//后面的参数如果和前面的参数存在相同的名称，那么后面的会覆盖前面的参数值。

		//extend方法原型中的dest参数是可以省略的，如果省略了，则该方法就只能有一个src参数，而且是将该src合并到调用extend方法的对象中去
		//$.extend(src)
 　　//该方法就是将src合并到jquery的全局对象中去，如：
 //$.extend({
//hello:function(){alert('hello');}
//});  可以在其他地方 直接使用功能$.hello();

/**
$.fn.extend(src)
 　　该方法将src合并到jquery的实例对象中去，
$.fn.extend({
hello:function(){alert('hello');}
});

可以在其他地方使用 $().hello();

**/

			//默认参数
            var defaults = {
			     /**轮换间隔时间，单位毫秒*/
                 during:3000,
				 /**是否显示左右按钮*/
                 btn:true,
				 /**是否显示焦点按钮*/
                 focus:true,
				 /**是否显示标题*/
                 title:true,
				 /**是否自动播放*/
                 auto:true,
				/** 默认数据  data.li.href data.li.src data.li.title传入*/
				 data:null,
				/**默认宽*/
				 width:350,
				/**默认高*/
				 height:250
            }
				 
            var options = $.extend(defaults, options);   
		
			if(options.data === null){
				//清空 添加style 添加span标签
				this.empty();
				var e_span=$("<span></span>");
				e_span.css("line-height",options.height+"px");
				e_span.css("vertical-align","middle");
				e_span.append("您没有放置data数据 ");
				this.css("width",options.width+"px");
				this.css("height",options.height+"px");
				this.css("margin","0 auto");
				this.css("text-align","center");
				this.css("border","1px solid red");
				this.append(e_span);
				return this;
			}
	
			//判断div是否有宽度 有的话使用其宽度 没有的话使用设置的值
			if(this.width()==0 || this.height()==0){
				this.width(options.width);
				this.height(options.height);
			}else{
				options.height = this.height();
				options.width = this.width();
			}
			
			
			
			var ul = $("<ul></ul>").appendTo(this);
			

			$.each(options.data,function(index,d){
				var mhref = d.href;
				var msrc = d.src;
				var mtitle = d.title;
				//构建li 并添加到ul中 ul添加到div中
				var li = $("<li></li>");
				var a  = $("<a></a>");
				var img = $("<img></img>");
				img.attr({src:msrc,alt:mtitle});
				img.width(options.width);
				img.height(options.height);
				a.attr({href:mhref,target:"view_window"});
				a.append(img);
				li.append(a);
				ul.append(li);
				//添加到div中
			});
			this.append(ul);

			//=====this指的是，【调用当前函数的那个对象,调用对象是Jquery对象 this就是Jquery对象，调用的事JS对象，this就是JS对象】,是javascript提供的当前对象 直接用this.style修改样式 this.style.display="none"
			//$(this)表示的是用jquery封装的Jquery对象  jquery提供的方法访问样式$(this).css("display","none")
			//它代表函数运行时，自动生成的一个内部对象，只能在函数内部使用。
			//这里的this是Jquery对象 可以使用Jquery的each方法
			//区别于$.each(datas,functionA(index,data){}),这个用来便利数组
			//$().each(functionB(index){}) 在dom处理上面用的较多，这里用于对Dom的每个子元素进行便利
			//jQueryObject.each( callback ) 和全局jQuery对象的each()函数 不同
			//调用each回调函数的是当前已经便利出来的对象，一般都是JS对象 理论上 functionA中的参数data也可以不要 直接在方法里面使用this 也是一样的 如果要用Jquery方法的话 就用$(this)
            //each()方法的返回值为jQuery类型，返回当前jQuery对象本身。
			//if(this instanceof jQuery){
			//	alert("这是一个jQuery对象"); 
			//}else if (typeof this)
			//{
			//	alert("这是一个其它对象") 
			//}
			
			this.each(function(){
			     var o = options;   
				var curr_index = 0;
                var $this = $(this);				
                var $li = $this.find("li");
                var li_count = $li.length;
				$this.css({position:'relative',overflow:'hidden',width:o.width,height:o.height});
				$this.find("li").css({position:'absolute',left:0,top:0}).hide();
			    $li.first().show();
			    $this.append('<div class="yx-rotaion-btn"><span class="left_btn"><\/span><span class="right_btn"></span><\/div>');
				if(!o.btn) $(".yx-rotaion-btn").css({visibility:'hidden'});
                if(o.title) $this.append(' <div class="yx-rotation-title"><\/div><a href="" class="yx-rotation-t"><\/a>');
                if(o.focus) $this.append('<div class="yx-rotation-focus"><\/div>');

				

				$(".yx-rotation-title").css("height",0.1*o.height+"px");
				$(".yx-rotation-title").css("line-height",0.1*o.height+"px");
				$(".yx-rotation-focus").css("height",0.1*o.height+"px");
				$(".yx-rotation-focus").css("line-height",0.1*o.height+"px");


				
				$(".yx-rotation-t").css("line-height",0.1*o.height+"px");

				var $btn = $(".yx-rotaion-btn span"),$title = $(".yx-rotation-t"),$title_bg = $(".yx-rotation-title"),$focus = $(".yx-rotation-focus");
				//如果自动播放，设置定时器
				if(o.auto) var t = setInterval(function(){$btn.last().click()},o.during);
                $title.text($li.first().find("img").attr("alt"));	
				$title.attr("href",$li.first().find("a").attr("href"));			
				
               // 输出焦点按钮
               for(i=1;i<=li_count;i++){
                 $focus.append('<span>'+i+'</span>');
               }
			 
				$(".yx-rotation-focus span").css("margin-top",(0.1*o.height/2-6)+"px");
				$(".yx-rotation-focus span").css("vertical-align","middle");
               // 兼容IE6透明图片   
               if($.browser.msie && $.browser.version == "6.0" ){
                  $btn.add($focus.children("span")).css({backgroundImage:'url("images/ico.gif")'});
               }		
               var $f = $focus.children("span");
               $f.first().addClass("hover");
               // 鼠标覆盖左右按钮设置透明度
               $btn.hover(function(){
	              $(this).addClass("hover");
               },function(){
	              $(this).removeClass("hover");
               });
			   //鼠标覆盖元素，清除计时器
               $btn.add($li).add($f).hover(function(){
                if(t) clearInterval(t);
               },function(){
                if(o.auto) t = setInterval(function(){$btn.last().click()},o.during);
               });
			   //鼠标覆盖焦点按钮效果
               $f.bind("mouseover",function(){
	             var i = $(this).index();
	             $(this).addClass("hover");
	             $focus.children("span").not($(this)).removeClass("hover");
	             $li.eq(i).fadeIn(300);
                 $li.not($li.eq(i)).fadeOut(300);	
	             $title.text($li.eq(i).find("img").attr("alt"));
	             curr_index = i;
               });
			   //鼠标点击左右按钮效果
               $btn.bind("click",function(){
                 $(this).index() == 1?curr_index++:curr_index--;
	             if(curr_index >= li_count) curr_index = 0;
	             if(curr_index < 0) curr_index = li_count-1;
                 $li.eq(curr_index).fadeIn(300);
	             $li.not($li.eq(curr_index)).fadeOut(300);	
	             $f.eq(curr_index).addClass("hover");
	             $f.not($f.eq(curr_index)).removeClass("hover");
	             $title.text($li.eq(curr_index).find("img").attr("alt"));
				 $title.attr("href",$li.eq(curr_index).find("a").attr("href"));	
               });

			//======this.each(function(){=====
            }); 
			return this;
		//====== yx_rotaion: function(options) { =====
        } 
	//====	$.fn.extend({ ====
    });   
       
})(jQuery);

