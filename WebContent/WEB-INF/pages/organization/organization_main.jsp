<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="keywords" content="${newsKeyWord}">
<meta name="description" content="${newsTitle}">
<title>${newsTitle}-重庆市交通委员会</title>
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
<style type="text/css">
.subList {
	border: none;
	background-image: none;
	background-color: #fff;
}

.subList .column {
	margin-bottom: 10px;
	border: 1px solid #AFC5DD;
	background: url("../images/global/bg_newsListRight.png") no-repeat scroll
		right top #E5E5E5;
}

.subList .last {
	margin-bottom: 0;
}

.subList .column h2 {
	font-weight: bold;
	font-size: 14px;
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
					<div class="mainList organization" id="lH"
						style="border: 1px solid #afc5dd">
						<div class="breadCutNav" style="border-width: 0 0 3px 0">
							<b class="icon_home"></b> <span>当前位置：</span> <a
								href="${pageContext.request.contextPath}/index.html">首页</a> <span>
								&gt;</span> <a href="${pageContext.request.contextPath}/openCatalog/init_/openCatalog/f1e8c2db-9e92-4a1e-a1cb-48569fa7f308.html"  target="_top">政务公开</a> <span> &gt;</span> <a
								href="javaScript:;" class="cur">组织机构</a>
						</div>
						<div class="blank_h7"></div>
						<div class="redTap">
							<h3>${newsTitle}</h3>
							<p class="tip">更新时间：${modifyDate}</p>
							<div class="redTap_con">${newsContent}</div>
						</div>
					</div>
					<!--mainList End-->
					<!--subList Start-->
					<div class="subList" id="rH">
						<div class="column">
							<div class="subList_title">
								<h2>机关处室</h2>
							</div>
							<div id="subList_con" class="subList_con">
								<ul>
									<c:forEach items="${list1}" var="p" varStatus="index">
										<li class="hot"><b>${index.count}</b> <a
											href="${pageContext.request.contextPath}/organizations/${p.newsID}.html">
												${p.newsTitle} </a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="column">
							<div class="subList_title">
								<h2>委属单位</h2>
							</div>
							<div id="subList_con" class="subList_con">
								<ul>
									<c:forEach items="${list2}" var="p" varStatus="index">
										<li class="hot"><b>${index.count}</b> <a
											href="${pageContext.request.contextPath}/organizations/${p.newsID}.html">
												${p.newsTitle} </a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="column last">
							<div class="subList_title">
								<h2>企业单位</h2>
							</div>
							<div id="subList_con" class="subList_con">
								<ul>
									<c:forEach items="${list3}" var="p" varStatus="index">
										<li class="hot"><b>${index.count}</b> <a
											href="${pageContext.request.contextPath}/organizations/${p.newsID}.html">
												${p.newsTitle} </a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
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
	<script type="text/javascript">
		twoColHeight("lH", "rH");
	</script>
</body>
</html>