<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title></title>
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
				&gt;</span> <a href="${pageContext.request.contextPath}/openCatalog/init_/openCatalog/f1e8c2db-9e92-4a1e-a1cb-48569fa7f308.html" class="cur"  target="_top">政务公开</a><span> &gt;</span> 
				<a href="javascript:;" class="cur">${topName}</a>
		</div>
		<div class="trafficNews list">
			<h2>${topName}</h2>
			<ul>
				<c:choose>
					<c:when test="${topName eq '交通信用'}">
						<li>
							<b class="icon_noteBook"></b> 
							<a target="_blank" style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden;"
								href="http://203.93.109.52:8088/gl"
								title="公路建设市场信用信息">公路建设市场信用信息</a> 
						</li>
						<li>
							<b class="icon_noteBook"></b> 
							<a target="_blank" style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden;"
								href="http://203.93.109.52:8088/sy"
								title="水运建设市场信用信息">水运建设市场信用信息</a> 
						</li>
						<li>
							<b class="icon_noteBook"></b> 
							<a target="_blank" style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden;"
								href="http://113.207.114.18:8096/pub/pageindex"
								title="公路水运工程从业人员信用管理">公路水运工程从业人员信用管理</a> 
						</li>
					</c:when>
					<c:when test="${topName eq '行政处罚'}">
						<li>
							<b class="icon_noteBook"></b> 
							<a target="_blank" style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden;"
								href="http://bs.cqjt.gov.cn:8000/cqnet/page/web/homePage.jsp?type=6"
								title="行政处罚裁量基准公示">行政处罚裁量基准公示</a> 
						</li>
						<li>
							<b class="icon_noteBook"></b> 
							<a target="_blank" style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden;"
								href="http://bs.cqjt.gov.cn:8000/cqnet/page/web/homePage.jsp"
								title="交通行政处罚处理流程">交通行政处罚处理流程</a> 
						</li>
					</c:when>
				</c:choose>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
</script>
</body>
</html>
