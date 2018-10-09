<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${interviewTitle}-在线访谈-重庆市交通委员会</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<!-- 滚动插件  liMarquee -->
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="${pageContext.request.contextPath}/js/liMarquee/liMarquee.css" /> --%>
<!-- <script -->
<%-- 	src="${pageContext.request.contextPath}/js/liMarquee/jquery.liMarquee.js"></script> --%>
<!-- 滚动插件 Marquee -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/marquee/Marquee.css" />
<script
	src="${pageContext.request.contextPath}/js/marquee/Marquee.js"></script>
<script type="text/javascript">
	$(function() {

		var heightA = $("#coursePicture").height();
		$("#play").css("margin-top", -heightA * 0.5 - 25);
		$("#play").show();
		
// 		$('.str1').liMarquee({
// 			direction : 'up',
// 			scrollamount : 20
// 		});
		$("#marquee2").height($("#marquee2 ul li a img").height()*3+12);
		$('#marquee2').kxbdSuperMarquee({
			distance:$("#marquee2 ul li a img").height()+4,
			time:3,
			btnGo:{up:'#goU',down:'#goD'},
			direction:'up'
		});

		$("#play").click(function() {
			window.open("${videoPhotosUrl}");
		});

	});
	
	var flag = false;
	function DrawImage(ImgD) {
		var image = new Image();
		var iwidth = 117;//定义允许图片宽度
		var iheight = 151;//定义允许图片高度
		image.src = ImgD.src;
		if (image.width > 0 && image.height > 0) {
			flag = true;
			if (image.width / image.height >= iwidth / iheight) {
				ImgD.width = iwidth;
				ImgD.height = (image.height * iwidth) / image.width;
			} else {
				ImgD.height = iheight;
				ImgD.width = (image.width * iheight) / image.height;
			}
		}
	}
</script>

<style>
#play {
	z-index: 100;
	width: 60px;
	position: absolute;
	/*	    margin-top: -105px;*/
	margin-left: 80px;
	cursor: pointer;
}

.class_outer {
	display: block;
	width: 100%;
	margin: 2px auto;
	position: relative;
	overflow: hidden;
}

.class_outer img {
	width: 100%;
	height: auto;
/* 	margin-top: 3px; */
}

.class_cover {
	width: 100%;
	height: 28px;
	line-height: 30px;
	padding-left: 2px;
	background-color: rgba(0, 0, 0, .50);
	color: #FFFFFF;
	font-size: 12px;
	position: absolute;
	left: 0px;
	bottom: 0px;
}
.onlineDetail {
	padding: 0 0 50px 0;
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
					<div class="mainList " id="lH">
						<div class="breadCutNav">
							<b class="icon_home"></b> <span>当前位置：</span> <a
								href="${pageContext.request.contextPath}/index.html">首页</a> <span>
								&gt;</span> <a
								href="${pageContext.request.contextPath}/interviews/list_1.html">在线访谈
							</a> <span> &gt;</span> <a href="javascript:;" class="cur">访谈嘉宾</a>
						</div>
						<div class="onlineDetail">
							<div class="clearfix">
								<div class="trafficNews column01">
									<h2>访谈嘉宾介绍</h2>
									<div class="clearfix">
										<a href="javascript:;" class="photo"> <img
											src="${prourl}${guestPhotosUrl}" onload="DrawImage(this)" /></a>
										<p class="intro">${guestDesc}</p>
									</div>
								</div>
								<div class="trafficNews column02">
									<h3>【访谈简介】</h3>
									<p class="con">${interviewSummary}</p>
								</div>
							</div>
							<div class="trafficNews column03"
								style="font-family: SimSun; font-size: 12px;">
								<h2>文字直播</h2>
								<dl>
									<dd>${interviewContent}</dd>
								</dl>
							</div>
						</div>
						<!-- 在线访谈评论 -->
						<%@ include file="/WEB-INF/pages/common/commentAssembly.jsp"%>
						<div style="width:100%;height: 50px;" ></div>
					</div>
					<!--mainList End-->
<%-- 				<c:import url="/textNews.do?action=getSubList"></c:import> --%>

					<!--subList Start-->
					<div class="subList" id="rH">
					
						<c:if test="${ !empty interviewPhotosUrl }">
							<div class="subList_title">
								<ul class="subList_tab" id="subList_tab" style="float: left;">
									<li class="cur"><b class="nav_LBg"></b> <a
										href="javascript:;">现场视频</a> <b class="nav_RBg"></b></li>
								</ul>
							</div>
						
							<a id="coursePicture" target="_blank" href="${videoPhotosUrl}"
								class="class_outer"> <img
								src="http://www.cqjt.gov.cn:8080/${interviewPhotosUrl}" /> <span
								class="class_cover">${interviewPhotosName}</span>
							</a>
							<img id="play" style="display: none;"
								src="${pageContext.request.contextPath}/images/play_128px.png" />
						</c:if>
						
						<c:if test="${ !empty scenePhotosList }">
							<div class="subList_title" style="border-bottom: none;">
								<ul class="subList_tab" id="subList_tab"
									style="float: left; margin-top: 2px;">
									<li class="cur"><b class="nav_LBg"></b> <a
										href="javascript:;">现场图片</a> <b class="nav_RBg"></b></li>
									<div class="subList_title" style="width: 235px;"></div>
								</ul>
							</div>
							<div id="marquee2" class="str1 str_wrap" >
<!-- 							<div class="str1 str_wrap"> -->
								<ul>
									<c:forEach items="${scenePhotosList}" var="list" varStatus="status">
										<li>
											<a target="_blank" href="${list.scenePhotosUrl}"
												class="class_outer"> <img id="img${status.index}"
												src="http://www.cqjt.gov.cn:8080/${list.scenePhotosPath}">
												<span id="span${status.index}" class="class_cover">${list.scenePhotosName}</span>
											</a>
										</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						
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
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script type="text/javascript">
		twoColHeight("lH", "rH");
	</script>
</body>
</html>