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
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
</head>

<body style="background: white;">
	<div class="mainList" id="lH" style="width: 100%">
		<div class="breadCutNav">
			<b class="icon_home"></b> <span>当前位置：</span> <a
				href="${pageContext.request.contextPath}/index.html" target="top">首页</a>
			<span> &gt;</span> <a
				href="${pageContext.request.contextPath}/openCatalog/init_/openCatalog/f1e8c2db-9e92-4a1e-a1cb-48569fa7f308.html"
				class="cur" target="_top">政务公开</a><span> &gt;</span> <a
				href="javascript:;" class="cur">公路水运违法违规信息公开</a>
		</div>
		<div class="trafficNews list">
			<h2>水运工程违规行为</h2>
			<ul id="datali" >
				<c:forEach items="${llegals}" var="llegals">
					<li>
						<b class="icon_noteBook"></b> 
						<a id="title" target="_self" style="white-space: nowrap; text-overflow: ellipsis;overflow: hidden;" 
							href="waterTransport.do?action=waterTransportIllegalDetail&id=${llegals.ID}&REPORT_FORM_TYPE=${llegals.REPORT_FORM_TYPE}" >
							${llegals.ILLEGAL_COMPANY_NAME} ${llegals.ILLEGAL_PERSONNEL_NAME} - ${llegals.SOCIOLOGY_CREDIT_CODE}
						</a> 
						<span id="createtime" >${llegals.CREATE_TIME}</span>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>