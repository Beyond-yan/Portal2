<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>交通图片-重庆市交通委员会</title>
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
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/banner.js"></script>
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
					<span> &gt;</span> <a href="javascript:;" class="cur">交通图片</a>
				</div>
				<!--banner start-->
				<div class="banner" id="wrapper">
					<ul class="bannerList">
						<c:forEach items="${latestList }" var="p" varStatus="status">
							<li class="show_images_${status.count }"><a
								href="${pageContext.request.contextPath}/photoNews/${p.photosNewsID}.html"
								target="_blank"> <img
									class="show_images_${status.count }_img"
									src="${contentServer }${p.mainPhotosUrl}" alt="${p.photosNewsTitle }" />
							<b class="picDesc">${p.photosNewsTitle }</b></a></li>
						</c:forEach>
					</ul>
					<div class="header_text" id="belt"></div>
					<div class="btn">
						<c:forEach items="${latestList }" var="p" varStatus="status">
							<a class="btn${status.count }" rel="${status.count}"></a>
						</c:forEach>
					</div>
				</div>

				<!--banner end-->
				<div class="clearfix" id="photo">
					<!--mainList Start-->
					<div class="mainList" id="lH">
						<h2 class="title_photo">
							<span>推荐图片</span>
						</h2>
						<ul class="photoList">
							<c:forEach items="${recommendList }" var="p" varStatus="status">
								<li><a
									href="${pageContext.request.contextPath}/photoNews/${p.photosNewsID}.html"
									class="pic"><img src="${contentServer }${p.mainPhotosUrl}" /></a>
									<p>
										<span class="r">${p.ENTRY_DATE }</span><b class="i_pic r"></b>
									</p>
									<h3>
										<a href="javascript:;">${p.photosNewsTitle }</a>
									</h3></li>
							</c:forEach>
						</ul>
					</div>
					<!--mainList End-->
					<!--subList Start-->
					<div class="subList" id="rH">
						<h2 class="title_photo">
							<span>图片集锦</span>
						</h2>
						<ul class="photoList_sub">
							<c:forEach items="${collectionList }" var="p" varStatus="status">
								<li title="${p.photosNewsTitle}"><b class="i_pic"></b><a
									href="${pageContext.request.contextPath}/photoNews/${p.photosNewsID}.html">${p.photosNewsTitle}</a></li>
							</c:forEach>
							
							<a target="_blank"  style="float:right;" href="${pageContext.request.contextPath}/PhotosNews.do?action=getAllPhotosNewsList&pageIndex=1&limitCount=20">
    							<img width="37" border="0" height="11" src="/Portal/images/gengduo.gif"></img>
							</a>
						</ul>
					</div>
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