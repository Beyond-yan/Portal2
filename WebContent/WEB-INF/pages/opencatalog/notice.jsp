<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>政务公开-重庆市交通委员会</title>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript" language="javascript">
	function iFrameHeight() {
		var ifm = document.getElementById("rightFrame");
		var subWeb = document.frames ? document.frames["rightFrame"].document
				: ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight;
		}
	}
</script>
<style type="text/css">
.subList {
	border: none;
	background-image: none;
	background-color: #fff;
}

.subList .column {
	margin-bottom: 10px;
	border: 1px solid #AFC5DD;
	background: url("images/global/bg_newsListRight.png") no-repeat scroll
		right top #E5E5E5;
}

.subList .last {
	margin-bottom: 0;
}

.subList .column h2 {
	font-weight: bold;
	font-size: 14px;
}

body {
	background: none;
}
</style>
</head>

<body onload="loadExternalResource()">
	<div class="bg_w1284">
		<div class="wrap" style="width:100%">
			<!--header Start-->
			<iframe width="100%" style="height: 246px; margin-bottom: 10px;"
				src="http://bs.cqjt.gov.cn:8000/cqnet/page/web/header.jsp"
				scrolling="no" frameborder="0"></iframe>
			<!--header End-->
			<!--content Start-->
			<div class="content">
				<div class="clearfix">
					<!--mainList Start-->
					<div style="float: left;">
						<iframe src="${pageContext.request.contextPath}${initURL }"
							id="rightFrame" name="rightFrame" width="1024px" scrolling="yes"
							height="750px" frameborder="0" name="rightFrame" id="rightFrame"></iframe>
					</div>
					<!--mainList End-->
					<!--subList Start-->
<!-- 					<div class="subList" id="rH" style="float: right; width: 242px;">
						<iframe
							src="http://bs.cqjt.gov.cn:8000/cqnet/page/navibar.jsp?type=1"
							name="leftFrame" id="leftFrame" title="leftFrame" scrolling="no"
							frameborder="0" width="100%" height="750px"></iframe>
							
					</div> -->
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