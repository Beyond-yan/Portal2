<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib uri="/WEB-INF/userTag/PortalPager.tld" prefix="ut"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>市交委行政事业性收费项目清理表</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
</head>
<body style="background: white;">
	<div class="mainList" id="lH" style="width:100%">
		<div class="breadCutNav">
			<b class="icon_home"></b> <span>当前位置：</span> <a
				href="${pageContext.request.contextPath}/index.html"  target="top">首页</a> <span>
				&gt;</span> <a href="${pageContext.request.contextPath}/openCatalog/init_/openCatalog/f1e8c2db-9e92-4a1e-a1cb-48569fa7f308.html" class="cur"  target="_top">政务公开</a><span> &gt;</span> <a
				href="javascript:;" class="cur">交通规费</a>
		</div>
		<div class="trafficNews list">
			<h2>${trafficCosts.ENTRY_NAME}</h2> 
			<a class="media" href="http://www.cqjt.gov.cn:8080/${trafficCosts.EXECUTE_FILE}"></a> 
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>  
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.media.js"></script>
	<script>
		$(function(){
			$('.media').media({ width: '100%', height: '600', autoplay: true });  
		})
	</script>
</body>
</html>