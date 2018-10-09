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
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var flag=false;
	function DrawImage(ImgD){
		var image=new Image();
		var iwidth = 117;//定义允许图片宽度
		var iheight = 151;//定义允许图片高度
		image.src=ImgD.src;
		if(image.width>0 && image.height>0){
			flag=true;
			if(image.width/image.height>= iwidth/iheight){
				ImgD.width = iwidth;
				ImgD.height = (image.height*iwidth)/image.width;
			}else{
				ImgD.height = iheight;
				ImgD.width = (image.width*iheight)/image.height;
			}
		}
	}
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
					<!--mainList Start-->
					<div class="mainList " id="lH">
						<div class="breadCutNav">
							<b class="icon_home"></b> <span>当前位置：</span> <a
								href="${pageContext.request.contextPath}/index.html">首页</a> <span>
								&gt;</span> <a
								href="${pageContext.request.contextPath}/interviews/list_1.html">在线访谈</a>
							<span> &gt;</span> <a href="javascript:;" class="cur">访谈嘉宾</a>
						</div>
						<div class="onlineDetail">
							<div class="clearfix">
								<div class="trafficNews column01">
									<h2>访谈嘉宾介绍</h2>
									<div class="clearfix">
										<a href="javascript:;" class="photo"><img
											src="${prourl}${guestPhotosUrl}" onload="DrawImage(this)"/></a>
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
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script type="text/javascript">
		twoColHeight("lH", "rH");
	</script>
</body>
</html>