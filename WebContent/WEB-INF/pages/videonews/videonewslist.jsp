<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>交通视频-重庆市交通委员会</title>
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
	src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/banner.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".btn").show();
		$(".btn  a:first").addClass("active");
		$().gallery({
			current : [ ".show_images_1", ".show_images_1_img" ],
			left : [ ".show_images_2", ".show_images_2_img" ],
			right : [ ".show_images_3", ".show_images_3_img" ],
			none : [ ".show_images_4", ".show_images_4_img" ],
			duration : 500,
			start : function() {
				$(".header_text").fadeOut(150);
			},
			end : function() {
				$(".header_text").fadeIn(150);
			},
			autoChange : true,
			changeTimeout : 5000,
			stopTarget : ".header_stage"
		});
	});
</script>
</head>

<body onload="loadExternalResource()">	
	<div class="bg_w1284">
		<div class="wrap">
			<!--header Start-->
			<c:import url="/index.do?action=getHeader"></c:import>
			<!--header End-->
			
			
			
			<!--content Start-->
			<div class="content">
				<div class="breadCutNav">
					<b class="icon_home"></b> <span>当前位置：</span> <a
						href="${pageContext.request.contextPath}/index.html">首页</a>
					<span> &gt;</span> <a href="javascript:;" class="cur">交通视频</a>
				</div>
				<!--banner start-->
				<div class="banner" id="wrapper">
					<ul class="bannerList">
						<c:forEach items="${latestList }" var="p" varStatus="status">
							<li class="show_images_${status.count }"><a
								href="${pageContext.request.contextPath}/videoNews/${p.videoNewsId}.html"
								target="_blank"> <img
									class="show_images_${status.count }_img"
									src="${contentServer }${p.mainPhotosUrl}"
									alt="${p.videoNewsName }" /><b class="i_videoPlay51_51">
									</b><b class="videoDesc">${p.videoNewsName }</b>
							</a></li>
						</c:forEach>
					</ul>
					<div class="btn">
						<c:forEach items="${latestList }" var="p" varStatus="status">
							<a class="btn${status.count }" rel="${status.count}"></a>
						</c:forEach>
					</div>
				</div>

				<!--banner end-->
				<c:import url="/videoNews.do?action=getVideoSubList"></c:import>

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