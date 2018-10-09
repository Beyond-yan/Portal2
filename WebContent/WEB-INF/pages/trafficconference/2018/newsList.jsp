<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>2018年全市交通工作会议</title>
<link href="css/jwstyle.css" type="text/css" rel="stylesheet" />
<%-- <link href="${pageContext.request.contextPath}/css/index2018.css" type="text/css" rel="stylesheet" /> --%>
</head>
<body>
	<%@ include file="/WEB-INF/pages/trafficconference/2018/header.jsp"%>
	<div class="conall">
		<div class="c_title">
			<ul>
				<li>当前位置：</li>
				<a href="${pageContext.request.contextPath}/hyzt2018.do?action=index">
					<li>首页></li>
				</a>
				<a href="${pageContext.request.contextPath}/hyzt2018.do?action=index">
					<li>2018年全市交通工作会议></li>
				</a>
				<a href="${pageContext.request.contextPath}/hyzt2018.do?action=list&programType=${programType}">
					<li>${topName}</li>
				</a>
			</ul>
		</div>
		<div class="l_content">
			<ul>
				<c:forEach items="${catalogList}" var="catalog">
					<li>
						·
						<a href="${pageContext.request.contextPath}/hyzt2018.do?action=detail&programType=${programType}&newsID=${catalog.newsID}">${catalog.newsTitle}</a>
						<em>${catalog.entryDate}</em>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="footer" style="line-height: 20px;">
		<p>
			您是第
			<iframe width="85px" id="visiterCounter" scrolling="no" allowtransparency="true" style="background-color: transparent;" frameborder="0" height="16px"></iframe>
			位访者
		</p>
		<span>版权所有 重庆市交通委员会 ICP备案编号 渝ICP备16007941号</span>
	</div>
</body>
</html>
<script>
	document.getElementById("visiterCounter").src = "index.do?action=getVisiterCounter";
</script>