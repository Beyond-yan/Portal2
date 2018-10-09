<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>${photosNews.photosNewsTitle}-交通图片-重庆市交通委员会</title>
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
	href="${pageContext.request.contextPath}/css/jquery.ad-gallery.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.ad-gallery.js"></script>
<script type="text/javascript">
	$(function() {
		var galleries = $('.ad-gallery').adGallery();
		$('#switch-effect').change(function() {
			galleries[0].settings.effect = $(this).val();
			return false;
		});
		$('#toggle-slideshow').click(function() {
			galleries[0].slideshow.toggle();
			return false;
		});
		$('#toggle-description').click(function() {
			if (!galleries[0].settings.description_wrapper) {
				galleries[0].settings.description_wrapper = $('#descriptions');
			} else {
				galleries[0].settings.description_wrapper = false;
			}
			return false;
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
				<div class="clearfix">
					<div class="breadCutNav">
						<b class="icon_home"></b> <span>当前位置：</span> <a
							href="${pageContext.request.contextPath}/index.html">首页</a>
						<span> &gt;</span> <a
							href="${pageContext.request.contextPath}/photoNewsList.html">交通图片</a>
						<span> &gt;</span> <a href="javascript:;" class="cur">图片详情</a>
					</div>
					<div class="newsDetail">
						<h2>${photosNews.photosNewsTitle}</h2>
						<p class="dotLine_2px"></p>
						<ul class="tip">
							<li class="al_l">${photosNews.ENTRY_DATE }&nbsp;&nbsp;&nbsp;
								图片来源：${photosNews.photosNewsSource }</li>
							<li class="al_c">&nbsp;</li>
							<%@ include file="/WEB-INF/pages/common/share.jsp"%>
						</ul>
						<div>
							<!--BigPhoto Start 20130830-->
							<DIV class="wrap picshow">
								<div id="gallery" class="ad-gallery">
									<div class="ad-image-wrapper"></div>
									<div class="ad-controls clearfix"></div>
									<div class="ad-nav clearfix">
										<div class="ad-thumbs">
											<ul class="ad-thumb-list">
												<c:forEach items="${photosInfoList }" var="p"
													varStatus="status">
													<li><a href="${contentServer}${p.photosUrl }"> <img
															src="${contentServer}${p.photosUrl }"
															class="image${status.index}" title="${p.photosDesc }">
													</a></li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>
							</DIV>
						</div>
					</div>
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
	<script src="${pageContext.request.contextPath}/js/slide.js"></script>
</body>
</html>