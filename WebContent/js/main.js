/*
@: main.js
@: author:XieBei date:2013-05-11
*/
$(function(){
	//禁止右键
	//$(document).bind("contextmenu",function(){return false;});  
	//禁止复制 20160928
	//$(document).bind("selectstart",function(){return false;});  
	$(".tab dt").click(function(){
        if($(this).text()=="国务院新闻"){
            window.open("http://www.gov.cn/xinwen/yaowen.htm");return;
        }
		var index=$(".tab dt").index(this);
		$(this).removeClass("cur").parent(".tab").siblings(".tabCon").hide();
		$(this).siblings(".tab dt").removeClass("cur");
		if($(this).text()=="贯彻十九大"){
			$(this).css("color","yellow");
			$(this).css("background","red");
		}else{
			$(".19da").css("color","red");
			$(".19da").css("background","#fff");
		}
		$(this).addClass("cur").parent(".tab").siblings(".tabCon:eq("+index+")").show();
		// added by Cube @130824
		$(this).siblings(".tab .more").css("display","none");
		$(this).siblings(".tab .more").eq(index).css("display","block");
		
	});
	$(".tab_i dt").click(function(){
		var index=$(".tab_i dt").index(this);
		if($(this).text()=="市政府访谈"){
            window.open("http://www.cq.gov.cn/onlineinterview/list");return;
        }
		$(this).removeClass("cur_i").parent(".tab_i").siblings(".tabCon").hide();
		$(this).siblings(".tab_i dt").removeClass("cur_i");
		$(this).addClass("cur_i").parent(".tab_i").siblings(".tabCon:eq("+index+")").show();
		
		// added by Cube @130824
		$(this).siblings(".tab_i .more").css("display","none");
		$(this).siblings(".tab_i .more").eq(index).css("display","block");
	});
	
	//added by Cube @140731
	//出行服务中间区域标签页切换
	$(".tabs li").click(function(){
		var index=$(".tabs li").index(this);
		if(0==index||1==index){
		$(this).removeClass("active").parent(".tabs").siblings(".tabCon").find(".tabPane").removeClass("active").hide();
		$(this).siblings(".tabs li").removeClass("active");
		$(this).addClass("active").parent(".tabs").siblings(".tabCon").find(".tabPane:eq("+index+")").addClass("active").show();
		}else if(5==index||6==index){
			index=index-5;
            $(this).removeClass("active").parent(".tabs").siblings(".tabCon").find(".tabPane").removeClass("active").hide();
            $(this).siblings(".tabs li").removeClass("active");
            $(this).addClass("active").parent(".tabs").siblings(".tabCon").find(".tabPane:eq("+index+")").addClass("active").show();
        }
	});
	
	
	
	$(".part_jtzz .tab li").click(function(){
		var index=$(".tab li").index(this);
		$(this).removeClass("cur").parent(".tab").siblings(".tabCon").hide();
		$(this).siblings(".part_jtzz .tab li").removeClass("cur");
		$(this).addClass("cur").parent(".tab").siblings(".tabCon:eq("+index+")").show();
	});
	$(".part_cywz .tab li").click(function(){
		var index=$(".part_cywz .tab li").index(this);
		$(this).removeClass("active").closest("h2").siblings(".tabCon").hide();
		$(this).siblings(".part_cywz .tab li").removeClass("active");
		$(this).addClass("active").closest("h2").siblings(".tabCon:eq("+index+")").show();
	});
	
	// added by Cube @130826
	// 新闻排行标签切换
	$(function(){
		$(".subList_title .subList_tab li").click(function(){
			var index=$(".subList_title .subList_tab li").index(this);	
			$(this).siblings("li").removeClass("cur");
			$(this).siblings("li").addClass("normal");
			$(".subList_con ul").hide();	
			$(this).addClass("cur");
			$(this).removeClass("normal");
			$(".subList_con ul:eq("+index+")").show();
		});
	});

	$(".home").hover(function(){
		$(".navConHover").hide();// slideUp(1000);
		$(".nav li.tabHover").removeClass("cur");
		$(".nav .home").addClass("cur");
	});
	$("#imgList li").click(
		function(){
			$(this).addClass("cur").siblings().removeClass("cur");
		});
	/*
	 * @:列表隔行变色 @:xiebei 20130615
	 */
	$(".trafficNews li:even").css("background-color","#f2f2f2");

});

/* #myFocus 首页焦点图片 */
var myFocus={
	$:function(id){return document.getElementById(id);},
	$$:function(tag,obj){return (typeof obj=='object'?obj:this.$(obj)).getElementsByTagName(tag);},
	linear:function(t,b,c,d){return c*t/d + b;},
	easeIn:function(t,b,c,d){return c*(t/=d)*t*t*t + b;},
	easeOut:function(t,b,c,d){return -c*((t=t/d-1)*t*t*t - 1) + b;},
	opa:function(obj,v){
		if(v!=undefined) {v=v>100?100:(v<0?0:v); obj.style.filter = "alpha(opacity=" + v + ")"; obj.style.opacity = (v / 100);}
		else return (document.all)?((obj.filters.alpha)?obj.filters.alpha.opacity:100):((obj.style.opacity)?obj.style.opacity*100:100);
	},
	move:function(obj,dir,val,type,spd,fn){
		var t=0,b=parseInt(obj.style[dir])||0,c=val-b,d=spd||50,st=type||'linear',m=c>0?'ceil':'floor';
		if(obj[dir+'timer']) clearInterval(obj[dir+'timer']);
		obj[dir+'timer']=setInterval(function(){
			if(t<d){obj.style[dir]=Math[m](myFocus[st](t++,b,c,d))+'px';}
			else {clearInterval(obj[dir+'timer']);fn&&fn.call(myFocus);}
		},10);return this;
	},
	fade:function(obj,type,spd,fn){
		var o=this.opa(obj),m=spd||5;
		if(o==0) obj.style.display='';
		if(type=='out') m=-m;
		if(obj.fadeTimer) clearInterval(obj.fadeTimer);
		obj.fadeTimer=setInterval(function(){
			o+=m;myFocus.opa(obj,o);
			if(o<=0) obj.style.display='none';
			if(o>=100||o<=0){clearInterval(obj.fadeTimer);fn&&fn.call(myFocus);}
		},10);return this;
	},
	addList:function(obj,cla,arr){
		var s=[],n=this.$$('li',this.$$('ul',obj)[0]).length,num=cla.length;
		for(var j=0;j<num;j++){
			s.push('<ul class='+cla[j]+'>');
			for(var i=0;i<n;i++){s.push('<li>'+(cla[j]=='num'?(i+1):(cla[j]=='txt'?this.$$('li',obj)[i].innerHTML.replace(/\<img.*?\>/i,this.$$('img',obj)[i].alt):''))+'<span></span></li>');};
			s.push('</ul>');
		}; obj.innerHTML+=s.join('');
	},
	setting:function(par){// 设置DOM/文档加载就绪后执行的任务
		if(window.attachEvent){window.attachEvent('onload',function(){myFocus[par.style](par);});}
		else{window.addEventListener('load',function(){myFocus[par.style](par);},false);}
	},
	mF_expo2010:function(par){
		var box=this.$(par.id),t=par.time*1000;
		this.addList(box,['txt-bg','txt','num-bg','num']);
		var pic=this.$$('ul',box)[0],txt=this.$$('ul',box)[2],num=this.$$('ul',box)[4],img=this.$$('li',pic),tip=this.$$('li',txt);
		var H=tip[0].clientHeight+60;
		var n=img.length;
		var index=0;
		for(var i=0;i<img.length;i++){this.opa(img[i],0); img[i].style.display='none'; tip[i].style.top=-H+'px'}// bottom->top
		box.removeChild(this.$$('div',box)[0]);
		this.fade(img[index],'in');
		this.move(tip[index],'top',0,'easeOut',40)// bottom->top
		this.$$('li',num)[index].className='current';
		var run=function(idx){
			myFocus.fade(img[index],'out');
			myFocus.move(tip[index],'top',-H,'easeIn',15);// bottom->top
			myFocus.$$('li',num)[index].className='';
			if(index==n-1) index=-1;
			var N=idx!=undefined?idx:index+1;
			myFocus.fade(img[N],'in');
			myFocus.move(tip[N],'top',0,'easeOut',40);// bottom->top
			myFocus.$$('li',num)[N].className='current';
			index=N;
		}
		var auto=setInterval(function(){run()},t);
		for (var j=0;j<n;j++){
			this.$$('li',num)[j].j=j;
			this.$$('li',num)[j].onclick=function(){run(this.j);}
			this.$$('li',num)[j].onmouseover=function(){if(!this.className) this.className = 'hover';}
			this.$$('li',num)[j].onmouseout=function(){if(this.className=='hover') this.className ='';}
		}
		box.onmouseover=function(){clearInterval(auto);}
    	box.onmouseout=function(){auto=setInterval(function(){run()},t);}
	}
}
/*
 * ======================= @ 單擊表單值清空JS start =========================
 */

function inputFocus(obj){
	var inputValue=obj.value;
	var inputTip=obj.defaultValue;
	if(inputValue==inputTip){
		obj.style.color = "#333";// 设置用户输入时颜色
		obj.value="";
	}
}
function inputBlur(obj){
	var inputValue=obj.value;
	var inputTip=obj.defaultValue;
	if(inputValue==""){
		obj.style.color = "#333";// 重置提示文字颜色
		obj.value=inputTip;
	}
}
/*
 * ======================= @ 标签菜单 JS start =========================
 */
function tags(tagsId,tagsCntId,tagName,tabConName){
	// tagsId 标签菜单父元素的ID;
	// tagName 标签菜单的标签名称;
	// tagsCntId 菜单对应内容的父元素的ID;
	// tabConName 菜单对应内容的标签名称;
	var Tags=document.getElementById(tagsId).getElementsByTagName(tagName);
	var TagsCnt=document.getElementById(tagsCntId).getElementsByTagName(tabConName);
	var len=Tags.length;
	var flag=0;// 修改默认值
	for(i=0;i<len;i++){
		Tags[i].value = i;
		Tags[i].onmouseover=function(){changeNav(this.value)};
		TagsCnt[i].className='undis';
	}
	for(i=0;i<len;i++){
		TagsCnt[i].value = i;
		TagsCnt[i].onmouseover=function(){TagsCnt[this.value].className='dis';};
		TagsCnt[i].onmouseout=function(){TagsCnt[this.value].className='undis';};
	}
	Tags[flag].className='cur';
	TagsCnt[flag].className='dis';
	function changeNav(v){
		Tags[flag].className='normal';
		TagsCnt[flag].className='undis';
		flag=v;
		Tags[v].className='cur';
		TagsCnt[v].className='dis';
	}
	function undisCon(v){
		TagsCnt[v].className='undis';
	}
}

/*
 * ======================= @ 新闻相关页面左右等高 JS start =========================
 */
function twoColHeight(lId,rId){
	var lEle=document.getElementById(lId);
	var rEle=document.getElementById(rId);
	var lH=lEle.offsetHeight-2;
	var rH=rEle.offsetHeight-2;
	if(lH>rH){
		rEle.style.height = lH+"px";	
	}
	else if(lH<rH){
		lEle.style.height = rH+"px";	
	}
}
/*
 * ============= @ 首页产品预览-点击小图大图片切换JS @:xiebei 20130703 ================
 */
function photoSwitch(picSrc){
	var ele=document.getElementById("bigPhoto").getElementsByTagName("img");
	ele[0].src=picSrc;
}
/*
 * ============= @:下拉菜单 JS @:xiebei 20130706 ================
 */
var curZindex = 10;
function selectMenu(select,option,ev){
	curZindex++;
	var span = document.getElementById(select);
	var ul = document.getElementById(option);
	var li = document.getElementById(option).getElementsByTagName('li');
	var ev=ev||window.event;
	$("#"+select).parent().css("z-index",curZindex);
	if(ul.style.display == 'none'){
			$(".smLine ul").css("display","none");
			$(".selectMenu ul").css("display","none");
			ul.style.display = 'block';
	}
	else {
		ul.style.display = 'none';
	}
	ev.cancelBubble = true;// 取消冒泡事件,即父级以上的东西的事件不触发
	// 在頁面點擊時
	document.onclick=function(){
		$(".option").hide();
		$("#"+option).hide();
	}
	for(var i=0;i<li.length;i++){
		li[i].onclick=function(){
			span.innerHTML=this.innerHTML;
		}
	}
}
/*
 * ========================================= @ 视频播放JS 20130709
 * ===========================================
 */
function videoPlay(playerSrc,videoSrc,vW,vH,videoPic,videoWrapID,vSound){
	// playerSrc:播放器地址
	// videoSrc:视频地址
	// vW：宽
	// vH：高
	// videoPic：视频预载入图片
	// videoWrapID：视频父元素ID
	var s1 = new SWFObject(playerSrc,"single",vW,vH,"6");
	s1.addParam("allowfullscreen","false");
	s1.addParam("wmode","transparent");
	s1.addVariable("file",videoSrc);
	s1.addVariable("image",videoPic);
	s1.addVariable("width",vW);
	s1.addVariable("height",vH);
	s1.addVariable("autostart","true");
	s1.addVariable("repeat","always");
	s1.addVariable("volume",vSound);// *volume：音量值，默认90。参数可以填写0-100之间的整数值，并将存放在浏览器的cookie
	s1.write(videoWrapID);
}

function loadExternalResource() {
	/*
	$("#weatherIframe")
			.attr("src",
					"http://tianqi.2345.com/plugin/widget/index.htm?s=3&z=2&t=1&v=0&d=1&k=&f=1&q=1&e=0&a=0&c=57516&w=180&h=36");
	document.getElementById("visiterCounter").src = "/Portal/index.do?action=getVisiterCounter";
	*/
	$("#weatherIframe")
			.attr(
					"src",
					"http://tianqi.2345.com/plugin/widget/index.htm?s=3&z=2&t=1&v=0&d=1&k=&f=1&q=1&e=0&a=0&c=57516&w=180&h=36");
		document.getElementById("visiterCounter").src = "index.do?action=getVisiterCounter";
		var subjectSrc = "${subjectNews.IFRAME_URL }";
		if (subjectSrc != null && subjectSrc != "")
		$("#subjectFrame").attr("src", subjectSrc);
		$("#lxcx")
			.attr("src",
					"http://203.93.109.55:8088/widget/cxgh/loader.htm?width=222&theme=blue&height=150");
		$("#wsbs")
			.attr("src",
					"http://bs.cqjt.gov.cn:8000/cqnet/web/webdashboard!onlineServiceHall.do");
}