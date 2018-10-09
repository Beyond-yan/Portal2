<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<title></title>
<style>
.newsDetail  h2 {
	line-height: 40px;
	padding: 0 0 22px;
}
</style>
</head>

<body style="background: white;" onload="loadExternalResource()">
<!-- 禁止复制 -->
<!--  <body style="background: white;" onload="loadExternalResource()"  topmargin="0" oncontextmenu="return false" 
	ondragstart="return false" onselectstart ="return false" 
	oncopy="document.selection.empty()" onbeforecopy="return false">  -->
	
	<div class="mainList" id="lH" style="width:100%">
		<div class="breadCutNav">
			<b class="icon_home"></b> <span>当前位置：</span> <a
				href="${pageContext.request.contextPath}/index.html" target="top">首页</a>
			<span> &gt;</span><a href="${pageContext.request.contextPath}/openCatalog/init_/openCatalog/f1e8c2db-9e92-4a1e-a1cb-48569fa7f308.html"  target="_top">政务公开</a><span> &gt;</span>${topName}
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
</body>
</html>