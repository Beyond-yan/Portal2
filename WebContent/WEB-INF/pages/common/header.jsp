<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/js/layer2.4/layer.js"></script>
<!--header Start-->
<div class="header clearfix">
	<div class="tip">
		<ul class="l">
			<li id="dateView">今天是：${dateView}</li>
			<li class="weather clearfix"><iframe width="150px"
					id="weatherIframe" scrolling="no" allowtransparency="true"
					style="background-color: transparent; overflow: hidden;"
					frameborder="0" height="25px"></iframe></li>
		</ul>
		<a title="网站导航" style="margin-left: 1em;" 
			href="${pageContext.request.contextPath}/index.do?action=postSiteMap" class="r">网站导航</a>
		<a title="设为首页"
			onclick="this.style.behavior='url(#default#homepage)';this.setHomePage(window.location.href);"
			href="javascript:;" class="r">设为首页</a>
		<a href="${pageContext.request.contextPath}/search.jsp" style="margin-right: 10px;float: right;background: #0079C0;color: #fff;width: 66px;text-align: center;height: 22px;margin-top: 2px;border-radius: 3px;line-height: 22px;text-decoration: none;">高级搜索</a>
		<div class="search">
			<form action="${pageContext.request.contextPath}/searchnewslist/-1_1.html" target="_blank"
				name="searchForm" id="searchForm">
				<input name=tn type=hidden value=baidu> <input type="text"
					value="${word == null ? '请输入关键字' : word}" onfocus="inputFocus(this)" onblur="inputBlur(this)"
					name="word" id="word" /> 
					<input name=tn type=hidden value="bds"> <input
					name=cl type=hidden value="3"> <input name=ct type=hidden
					value="2097152"> <input name=si type=hidden
					value="www.cqjt.gov.cn"> <a href="javascript:;"
					class="btn_search" title="搜索"
					onclick="javascript:searchForm.submit();"></a>

			</form>
		</div>

	</div>
	<div class="clearfix" style="height:145px;">
		<a href="${pageContext.request.contextPath}/index.html" class="logo" target="_parent" style="clear:left"></a>
		<div style="float: right;width:450px; height:100px;">
			<embed src="${pageContext.request.contextPath}/images/jiaowei201802.swf"
				quality=high width=450 height=100 wmode=transparent
				type='application/x-shockwave-flash'></embed>
		</div>
		<a href="http://tw.cqjt.gov.cn:8888/Portal_topic/" ><img src="${pageContext.request.contextPath}/images/title5_5.png" style="height: 53px;margin-left: 125px;margin-top:18px;"/></a>

	</div>
	<!--topNav Start-->
	<ul class="topNav" >
<!-- 	<ul class="topNav" style="width: 1400px;margin-left: -140px;" > -->
		<li>
			<a href="http://cx.cqjt.gov.cn" class="i_menu first " target="_blank">
			<img src="${pageContext.request.contextPath}/images/top1.png" width="52" height="52" border="0" title="出行服务">
			</a>
			<div class="subMenu">
				<a href="http://cx.cqjt.gov.cn/zjcx/main.html?loader=condition" target="_blank">高速路况</a>
				<a href="http://cx.cqjt.gov.cn/ggcx/routePlan.html" target="_blank">城市交通</a>
				<a href="http://cx.cqjt.gov.cn/cjcx/intercitybus.html?type=default" target="_blank">客运班车</a> <br> 
				<a href="http://cx.cqjt.gov.cn/cjcx/train.html?type=default" target="_blank">火车时刻</a>
				<a href="http://cx.cqjt.gov.cn/cjcx/flight.html?type=leave" target="_blank">航空动态</a>
				<a href="http://cx.cqjt.gov.cn/bmfw/card.html" target="_blank">便民服务</a>
			</div>
		</li>
		<li>
			<a href="http://bs.cqjt.gov.cn:8000/cqnet/page/web/homePage.jsp" class="i_menu " target="_blank">
				<img src="${pageContext.request.contextPath}/images/top2.png" width="52" height="52" border="0" title="网上办事大厅">
			</a>
			<div class="subMenu">
				<a href="http://zwfw.cq.gov.cn/public/index" target="_blank">网上申报</a> 
				<a href="http://bs.cqjt.gov.cn:8000/cqnet/page/web/homePage.jsp?type=3" target="_blank">查询服务</a> <br> 
				<a href="${pageContext.request.contextPath}/openCatalog/init_/openCatalogList/01010901_1.html" target="_blank">公告公示</a>
				<a href="http://zwfw.cq.gov.cn/icity/govservice/portal?id=5000000080" target="_blank">办事指引</a>
			</div>
		</li>
		<li class="cur">
			<a href="javascript:;" class="i_menu " target="_parent">
				<img src="${pageContext.request.contextPath}/images/top3.png" width="52" height="52" border="0">
			</a>
			<div class="subMenu">
				<a href="${pageContext.request.contextPath}/articleList/010101_1.html" target="_blank">交通新闻</a> 
<!-- 				<a href="http://tw.cqjt.gov.cn:8082" target="_blank">交通图文</a> <br>  -->
<!-- 				<a href="maintain.jsp" target="_blank">交通图文</a> <br>  -->
				<a href="${pageContext.request.contextPath}/photoNewsList.html" target="_blank">交通图片</a> <br> 
				<a href="${pageContext.request.contextPath}/magazines/${latestMag}.html" target="_blank">交通杂志</a> 
				<a href="${pageContext.request.contextPath}/videoNewsList.html" target="_blank">交通视频</a>
			</div>
		</li>
<!--
		<li>
			<a href="http://hd.cqjt.gov.cn/" class="i_menu " target="_blank" title="交通活动">
				<img src="${pageContext.request.contextPath}/images/top5.png" width="52" height="52" border="0">
			</a>
			<div class="subMenu mr_0">
				<a href="javascript:void(0)"  onclick="show(this,'mydiv1');" onMouseOut="hide2(this,'mydiv1');">交通影赛</a> 
				<a href="javascript:void(0)"  onclick="show(this,'mydiv2');" onMouseOut="hide2(this,'mydiv2');">交通征文</a> <br> 
				<a href="http://www.cqjt.gov.cn/questionaires/list_1.html" >问卷调查</a>
				<a href="http://hd.cqjt.gov.cn/index.aspx?worksid=17" target="_blank">扶贫故事</a>
			</div>
		</li>-->
		<li>
			<a href="javascript:;" class="i_menu " target="_parent">
				<img src="${pageContext.request.contextPath}/images/top4.png" width="52" height="52" border="0">
			</a>
			<div class="subMenu mr_0">
				<a href="${pageContext.request.contextPath}/mailbox.html" target="_blank">领导信箱</a> <br>
				<a href="${pageContext.request.contextPath}/interviews/list_1.html" target="_blank">在线访谈</a>
			</div>
		</li>
		<li>
			<a href="javascript:;" class="i_menu " target="_blank" title="外部链接">
				<img src="${pageContext.request.contextPath}/images/top6.png" width="52" height="52" border="0">
			</a>
			<div class="subMenu mr_0">
			<!-- 	<a href="http://www.mot.gov.cn/" target="_blank" >交通运输部</a>  -->
			<a href="http://www.gov.cn/zhengce/index.htm" target="_blank" >国务院文件</a>
				<a href="http://zizhan.mot.gov.cn/st/chongqing/" target="_blank">交通部子站</a> <br>
			<!-- 	<a href="http://www.cq.gov.cn/" target="_blank" >重庆市政府</a>   -->
			<a href="http://www.cqjt.gov.cn/govDocument.do?action=jumpToGocDocumentList" target="_blank" >市政府文件</a>
				<a href="http://www.cq.gov.cn/publicity_sjw1" target="_blank" >交委信息公开</a>
			</div>
		</li>
	</ul>
	<!--topNav End-->
</div>
<!--header End-->

<!-- 第一个按钮div -->
<div id="mydiv1" style="position:absolute; z-index:999; display:none; background:#ffffff; width:72px; height:100px" onMouseOver="show2(this,'mydiv1');" onMouseOut="hide(this,'mydiv1');"> 
	<div style="margin-top:4px;">
		<a href="javascript:click_link_2()" >
			<img alt="" src="${pageContext.request.contextPath}/images/link_2.png">			
		</a>		
		<a href="javascript:click_link_0()" >
			<img  alt="" src="${pageContext.request.contextPath}/images/link_0.png">	
		</a>
		<a href="javascript:click_link_1()" >
			<img  alt="" src="${pageContext.request.contextPath}/images/link_1.png">	
		</a>
	</div>
</div> 

<!-- 第二个按钮div -->
<div id="mydiv2" border='3px'; style="position:absolute; z-index:999; display:none; background:#ffffff; width:72px;height:100px" onMouseOver="show2(this,'mydiv2');" onMouseOut="hide(this,'mydiv2');"> 	 
 	<div style="margin-top:4px;">
		<a href="javascript:click_link_5()" >
			<img alt="" src="${pageContext.request.contextPath}/images/link_2.png">
		</a>
		<a href="javascript:click_link_3()" >
			<img  alt="" src="${pageContext.request.contextPath}/images/link_3.png">	
		</a>
		<a href="javascript:click_link_4()" >
			<img alt="" src="${pageContext.request.contextPath}/images/link_4.png">
		</a>
	</div>
</div> 

<div style="display:none">
<img id="floatImg" style="position:fixed;z-index:78;top:373px;left:150px;cursor:hand;" onclick="closeImgDiv();" src="${pageContext.request.contextPath}/images/delete.png"></img>
<div id="floatDiv" style="cursor:pointer; position:fixed;z-index:77;top:385px;left:5px;height:120px;width:150px;border:1px solid #0473b3;padding:2px;">
	<a onclick="openImages();" target="_blank">
		<img src="${pageContext.request.contextPath}/images/newYear2018.jpg" style="height:120px;width:150px;"></img>
	</a>
</div>
</div>


<div style="display: none;">
	<img id="floatImg_1" style="position:fixed;z-index:78;top:303px;left:150px;cursor:hand;" onclick="closeImgDiv_1();" src="${pageContext.request.contextPath}/images/delete.png"></img>
	<div id="floatDiv_1" style="cursor:pointer; position:fixed;z-index:77;top:308px;left:5px;height:120px;width:150px;border:1px solid #0473b3;padding:2px;">
		<a href="http://www.cqjt.gov.cn:8082/topic/" target="_blank">
			<img src="${pageContext.request.contextPath}/images/newYear2017.jpg" style="height:120px;width:150px;"></img>
		</a>
	</div>

</div>
<div id="divbox">
	<img id="floatImg_2" style="position:fixed;z-index:78;top:303px;left:200px;cursor:hand;" onclick="closeImgDiv_2();" src="${pageContext.request.contextPath}/images/delete.png"></img>
	<div id="floatDiv_2" style="cursor:pointer; position:fixed;z-index:77;top:308px;left:5px;height:150px;width:200px;border:1px solid #0473b3;padding:2px;">
		<a href="http://www.cqjt.gov.cn/openCatalog/init_/openCatalogList/01010910001_1.html" target="_blank">
			<img src="http://www.cqjt.gov.cn/images/boomblack.jpg" style="height:150px;width:200px;"/>
		</a>
	</div>
</div>
<style>
.demo-class .layui-layer-title{background:#c00; color:#fff; border: none;}
</style>

<script>

setTimeout("codefans()",5000);//定时消失 5秒，可以改动
 function codefans(){
  	var box=document.getElementById("divbox");
    box.style.display="none"; 
  }

function openImages(){
	layer.open({
	  type: 2,
	  title:'新年贺词',
	  skin : 'demo-class',
	  area: ['780px', '600px'],
	  fixed : true,
	  content: '${pageContext.request.contextPath}/index.do?action=jumpNewYear'
	});
}

$(document).ready(function(){
	//refreshLocation();
});
$(window).scroll( function(){ 
	//refreshLocation();
});

//以下为鼠标悬停事件
var focus=false;
function show(obj,id) { 
	var objDiv = $("#"+id+""); 
	var event_link=getEvent();
	$(objDiv).css("display","block"); 
	$(objDiv).css("left", event_link.clientX); 
	$(objDiv).css("top", event_link.clientY + 10); 
	
	if(id == "mydiv2"){//隐藏上一个DIV
	 	$('#mydiv1').css("display", "none"); 
	}else{
	 	$('#mydiv2').css("display", "none"); 
	}
} 

function show2(obj,id) { 
	focus=true;
}	

function hide(obj,id) { 
// 	var objDiv = $("#"+id+""); 
// 	$(objDiv).css("display", "none"); 
	var objDiv = $("#"+id+""); 
	focus=false;
	setTimeout('hide3("'+id+'")',1000);
} 

function hide2(obj,id) { 
	var objDiv = $("#"+id+""); 
	focus=false;
	setTimeout('hide3("'+id+'")',3000);
} 

function hide3(id) { 
	var objDiv = $("#"+id+""); 
	if(!focus)$(objDiv).css('display', 'none');
    return;
} 
//以下为鼠标悬停事件
var focus=false;
function show(obj,id) { 
	var objDiv = $("#"+id+""); 
	var event_link=getEvent();
	$(objDiv).css("display","block"); 
	$(objDiv).css("left", event_link.clientX); 
	$(objDiv).css("top", event_link.clientY + 10); 
	
	if(id == "mydiv2"){//隐藏上一个DIV
	 	$('#mydiv1').css("display", "none"); 
	}else{
	 	$('#mydiv2').css("display", "none"); 
	}
} 

function show2(obj,id) { 
	focus=true;
}	

function hide(obj,id) { 
// 	var objDiv = $("#"+id+""); 
// 	$(objDiv).css("display", "none"); 
	var objDiv = $("#"+id+""); 
	focus=false;
	setTimeout('hide3("'+id+'")',1000);
} 

function hide2(obj,id) { 
	var objDiv = $("#"+id+""); 
	focus=false;
	setTimeout('hide3("'+id+'")',3000);
} 

function hide3(id) { 
	var objDiv = $("#"+id+""); 
	if(!focus)$(objDiv).css('display', 'none');
    return;
} 

function refreshLocation(){
	var top=200;//初始化离上方高度
	var right=10;//初始化离右边宽度
	var height=document.documentElement.scrollTop+top;
	$("#floatImg").css("top",height);
	$("#floatDiv").css("top",height+8);
	var width=((document.body.clientWidth-1024)/2-150)/2;
	if(width<10)width=right;
	$("#floatImg").css("right",width+8);
	$("#floatDiv").css("right",width);
}

function closeImgDiv(){
    $("#floatImg").remove();
    $("#floatDiv").remove();
}
function closeImgDiv_1(){
    $("#floatImg_1").remove();
    $("#floatDiv_1").remove();
}
function closeImgDiv_2(){
    $("#floatImg_2").remove();
    $("#floatDiv_2").remove();
}

function getEvent() //得到event，同时兼容ie和ff的写法
{  
    if(document.all)   return window.event;    
    func=getEvent.caller;        
    while(func!=null){  
        var arg0=func.arguments[0];
        if(arg0){
          if((arg0.constructor==Event || arg0.constructor ==MouseEvent) || (typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation))
          {  
          	return arg0;
          }
        }
			func=func.caller;
        }
	return null;
}
//链接跳转
function click_link_0(){
	 window.open("http://hd.cqjt.gov.cn/index.aspx?worksid=23");
}
function click_link_1(){
	 window.open("http://hd.cqjt.gov.cn/index.aspx?worksid=2");
}
function click_link_2(){
	 window.open("http://hd.cqjt.gov.cn/index.aspx?worksid=2216");
}
function click_link_3(){
	 window.open("http://hd.cqjt.gov.cn/index.aspx?worksid=16");
}
function click_link_4(){
	 window.open("http://hd.cqjt.gov.cn/index.aspx?worksid=10");
}
function click_link_5(){
	 window.open("http://hd.cqjt.gov.cn/index.aspx?worksid=2218");
}

</script>