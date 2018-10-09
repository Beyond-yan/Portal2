<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="subCon">
	<img src="${interviewPhotosUrl }" class="photo_onlineInterview">
	<h4 style="height: 35px; overflow: hidden;" title="${interviewTitle}" id="interviewTitle">
		<b>主题：</b>${interviewTitle}
	</h4>
	<ul>
		<li><b>时间：</b>${interviewDate}</li>
		<li><b>嘉宾：</b>${guestName}</li>
		<li class="summary"><b class="titleName">摘要：</b>
			<div class="overflowHidden" title="${interviewSummary}" id = "interviewSummary">${interviewSummary}</div>
			<div style="clear: both; height: 0;"></div></li>
	</ul>
	<div style="margin-top: 0px;">
		<a target="_blank"  href="${pageContext.request.contextPath}/interviews/${interviewID }.html">
			<img src="${pageContext.request.contextPath}/images/jinru.png" width="76" height="19" border="0">
		</a>
		<a target="_blank" href="${pageContext.request.contextPath}/interviewsplan/${interviewID }.html" style="margin-left: 10px">
			<img src="${pageContext.request.contextPath}/images/jinru3.png" width="76" height="19" border="0">
		</a> 
		<a href="${pageContext.request.contextPath}/interviews/list_1.html" target="_blank" style="float: right;margin-top: 4px;">
			<img src="${pageContext.request.contextPath}/images/gengduo.gif" width="37" height="11" border="0">
		</a>


	</div>
</div>

<script type="text/javascript">
$(function(){
	var title = '${interviewTitle}';
	var summary = '${interviewSummary}';
	if(title != null && title.length>=13){
// 		var str= title.substring(0,12)+" . . .";
// 		$("#interviewTitle").html("<b>主题：</b>"+str);
	}
	if(summary != null && summary.length>=70){
		var str=summary.substring(0,70)+" . . .";
		$("#interviewSummary").html(str);
	}
	/**
	//alert("浏览器:"+navigator.userAgent);
	if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
			//IE
			//alert('你是使用IE')
		}
	else
	    if (navigator.userAgent.indexOf('Firefox') >= 0){
			//火狐浏览器
			$("#goToMore").css("margin-top","-3px");
			//alert("火狐!");
	    }else
	        if (navigator.userAgent.indexOf('Opera') >= 0){
				//opera浏览器
	        	$("#goToMore").css("margin-top","5px");
	        }else{
	    	//其他浏览器	    
	        	$("#goToMore").css("margin-top","5px");
		}*/
});
</script>
