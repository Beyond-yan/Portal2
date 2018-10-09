<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>政务公开-重庆市交通委员会</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript" language="javascript">
	function iFrameHeight() {
		var ifm = document.getElementById("rightFrame");
		var subWeb = document.frames ? document.frames["rightFrame"].document
				: ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight;
		}
	}
</script>
<style type="text/css">
.subList {
	border: none;
	background-image: none;
	background-color: #fff;
}

.subList .column {
	margin-bottom: 10px;
	border: 1px solid #AFC5DD;
	background: url("${pageContext.request.contextPath}/images/global/bg_newsListRight.png") no-repeat scroll right top #E5E5E5;
}

.subList .last {
	margin-bottom: 0;
}

.subList .column h2 {
	font-weight: bold;
	font-size: 14px;
}
</style>
</head>
	
<body onload="loadExternalResource()">
	<div class="bg_w1284">
		<div class="wrap">
			<!-- 网站导航跳转参数 -->
			<input id="jumpLabel" type="hidden" value="${jumpLabel}">
			<!--header Start-->
			<c:import url="/index.do?action=getHeader"></c:import>
			<!--header End-->
			
			
			
			<!--content Start-->
			<div class="content">
				<div class="clearfix">
					<!--mainList Start-->
						<div class="mainList" id="lH" style="width:100%">
							<div class="breadCutNav">
								<b class="icon_home"></b> <span>当前位置：</span> <a
									href="${pageContext.request.contextPath}/index.html" target="top">首页</a>
								<span> &gt;</span><a href="${pageContext.request.contextPath}/openCatalog/init_/openCatalogList/01010901_1.html"  target="_top">政务公开</a><span> &gt;</span><a href="${pageContext.request.contextPath}/openCatalog/init_/openCatalogList/01010901_1.html"  target="_top">政务信息</a>
							</div>
							<div class="blank_7px"></div>
							<div class="newsDetail">
								<h2>${newsTitle}</h2>
								<p class="dotLine_2px"></p>
								<ul class="tip clearfix">
									<c:if test="${modifyDate ne null}">
										<li class="al_l">更新时间：${modifyDate}</li>
										<%@ include file="/WEB-INF/pages/common/share.jsp"%>
									</c:if>
								</ul>
								<div>${newsContent}</div>
							</div>
						</div>
					<!--mainList End-->
					
					<script>
						//网站导航
						var timer;
						var labelTitle = $("#jumpLabel").val();
						$(function(){
							if(labelTitle!=null && labelTitle!="" 
									&& typeof(labelTitle)!="undefined"){
								timer = setInterval(getTitle, 500);	
							}
						});
						function getTitle(){
							var labelA =  labelTitle.replace("span","a");
							var classA = $(window.frames["leftFrame"].document).find("#"+labelA+"").prop("class");
							if(classA.indexOf("curSelectedNode") == -1){
								$(window.frames["leftFrame"].document).find("#"+labelTitle+"").click();
// 								var openTielt = $(window.frames["leftFrame"].document).find("#"+labelTitle+"").text();
								clearInterval(timer);
							}
						}
					</script>
				</div>
				<!--partC Start-->
				<%@ include file="/WEB-INF/pages/common/friendLink.jsp"%>
				<!--partC End-->
			</div>
			<!--content End-->
		</div>
		<!--footer Start-->
		<%@ include file="/WEB-INF/pages/common/footer.jsp"%>
		<!--footer End-->
	</div>
</body>
</html>