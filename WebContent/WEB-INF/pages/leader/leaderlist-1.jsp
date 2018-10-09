<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/userTag/PortalPager.tld" prefix="ut"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>主任网页-重庆市交通委员会</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<style>
.mainList{
   display: none;
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
								&gt;</span> <a href="${pageContext.request.contextPath}/openCatalog/index.html">政务公开</a> <span> &gt;</span> <a
								href="javaScript:;" class="cur">主任网页</a>
						</div>
						<!--personalIntro Start-->
						<div class="personalIntro clearfix">
							<div class="photo">
								<img alt="领导" src="${leaderPhotosUrl}" width="145px"
									height="208px" />
								<h3>${leaderName}</h3>
								<p>${leaderPost}</p>
							</div>
							<div class="intro">
								<h2 class="clearfix">
									<span><b class="icon i_triangleBlue"></b>简历</span>
								</h2>
								<div>${leaderResume}</div>
							</div>
						</div>
						<!--personalIntro End-->
						<div class="activityList list">
							<h2>
								<span>重要活动和讲话</span>
							</h2>
							<ul>
								<c:forEach items="${NewsList}" var="News" varStatus="newsID">
									<li><a target="_blank"
										href="${pageContext.request.contextPath}/articles/${leaderId}/${News.newsID}.html">
											&bull;&nbsp;${News.newsTitle}</a></li>
								</c:forEach>
							</ul>
							<ut:PortalPager
								url="${pageContext.request.contextPath}/leaders/${leaderId }"
								pagesize="${pagesize}" totalLines="${count}"
								curpage="${curpage}" />
						</div>
					</div>
					<!--mainList End-->
					<!--subList Start-->
					<div class="subList personalList" id="rH">
						<!--scroll start-->
						<div class="img-scroll_v clearfix" id="img-scroll_v">
							<a href="javascript:;" class="up scrollBtn" id="up"></a> <a
								href="javascript:;" class="down scrollBtn" id="down"></a>
							<div class="img-list_v" id="scrollUp">
								<ul class="clearfix" id="scrollUp01">
									<c:forEach items="${leaderList}" var="Leader"
										varStatus="leaderId">
										<li><a
											href="${pageContext.request.contextPath}/leaders/${Leader.leaderId}_.html">
												<img alt="领导" src="${prourl}${Leader.leaderPhotosUrl}" />
										</a>
											<div>
												<h4>
													<a
														href="${pageContext.request.contextPath}/leaders/${Leader.leaderId}_.html">
														${Leader.leaderName} </a>
												</h4>
												<p>${Leader.leaderPost}</p>
											</div></li>

									</c:forEach>
								</ul>
							</div>
						</div>
						<!--scroll end-->
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/main.js"></script>
	<script type="text/javascript">
		function DY_scroll(wraper, up, down, img, speed, or) {
			var wraper = $(wraper);
			var up = $(up);
			var down = $(down);
			var img = $(img).find('ul');
			var vH = 620;
			var h = img.find('li').outerHeight(true);
			var liNum = img.find('li').length;
			var imgH = liNum * h;
			var imgMT = img.css('margin-top').replace("px", "");
			var s = speed;
			down.click(function() {
				if (Math.abs(imgMT) < imgH - vH) {
					imgMT -= h;
					img.animate({
						'margin-top' : imgMT
					});
				}

			});
			up.click(function() {
				if (imgMT < 0) {
					imgMT += h;
					img.animate({
						'margin-top' : imgMT
					});
				}
			});
			if (or == true) {
				ad = setInterval(function() {
					down.click();
				}, s * 1000);
				wraper.hover(function() {
					clearInterval(ad);
				}, function() {
					ad = setInterval(function() {
						down.click();
					}, s * 1000);
				});

			}
		}
		DY_scroll('.img-scroll_v', '.up', '.down', '.img-list_v', 3, false);// true为自动播放，不加此参数或false就默认不自动
	</script>
</body>
</html>