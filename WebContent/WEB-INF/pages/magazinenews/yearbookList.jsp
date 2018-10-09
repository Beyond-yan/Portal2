<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>交通年鉴-重庆市交通委员会</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/main.js"></script>
<style>
.yearbookList {
	
}

.yearbookList ul li {
	float: left;
	width: 25%;
	text-align: center;
}
</style>
</head>
<body onload="loadExternalResource()">
	<div class="bg_w1284">
		<div class="wrap">
			<!--header Start-->
			<c:import url="/index.do?action=getHeader"></c:import>
			<!--header End-->
			<!--content Start-->
			<div class="content">
				<div class="clearfix">
					<!--mainList Start-->
					<div class="mainList" id="lH">
						<div class="breadCutNav">
							<b class="icon_home"></b> <span>当前位置：</span> <a
								href="${pageContext.request.contextPath}/index.html">首页</a> <span>
								&gt;</span> <a
								href="${pageContext.request.contextPath}/subjectNewsList/_1.html">专题专栏</a>
							<span> &gt;</span> <a href="javascript:;" class="cur">交通年鉴</a>
						</div>
						<div class="blank_7px"></div>
						<div class="newsDetail clearfix">
							<div class="yearbookList">
								<ul>
									<c:forEach items="${list}" var="p" varStatus="status">
										<li>
<%-- 										<a href="${pageContext.request.contextPath}/magazines/download/${contentServer }${p.YEAR_BOOKS_FURL}"> --%>
											<a href="${contentServer }${p.YEAR_BOOKS_FURL}">
											<img src="${contentServer }${p.MAIN_PHOTOS_URL}"
												style="width: 147px; height: 182px;"> <span
												style="display: block;">${p.YEAR_BOOKS_NAME }</span></a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
					<!--mainList End-->
					<!--subList Start-->
					<c:import url="/textNews.do?action=getSubList"></c:import>
					<!--subList End-->
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