<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/userTag/PortalPager.tld" prefix="ut"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>在线访谈-重庆市交通委员会</title>
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
<!-- added by Cube @130831 -->
<style type="text/css">
table {
	table-layout: fixed;
	width: 100%;
}

td {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
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
				<div class="breadCutNav">
					<b class="icon_home"></b> <span>当前位置：</span> <a
						href="${pageContext.request.contextPath}/index.html">首页</a>
					<!-- <span> &gt;</span> <a href="javascript:;">互动交流</a>  -->
					<span> &gt;</span> <a href="javascript:;" class="cur">在线访谈</a>
				</div>
				<div class="onlineInterviewList clearfix">
					<ul>
						<c:forEach items="${interviewList}" var="interview"
							varStatus="interviewId">
							<li>
								<div
									style="width: 150px; height: 125px; float: left; ">
									<a href="javascript:;" class="interviewPhoto"> <img
										src="${prourl}${interview.guestPhotosUrl}"
										style="width: auto; height: auto; max-width: 150px; max-height: 125px; margin: auto" />

									</a>
								</div>

								<table width="100%" border="0" style="float: left;">
									<tr>
										<th width="10%">主题:</th>
										<td width="90%" title="${interview.interviewTitle}">${interview.interviewTitle}</td>
									</tr>
									<tr>
										<th>时间:</th>
										<td>${interview.interviewDate}</td>
									</tr>
									<tr>
										<th>嘉宾:</th>
										<td>${interview.guestName}</td>
									</tr>
									<tr>
										<th>摘要:</th>
										<td title="${interview.interviewSummary}">${interview.interviewSummary}</td>
									</tr>
								</table> <a
								href="${pageContext.request.contextPath}/interviews/${interview.interviewId}.html"
								class="btnBlue82_25">进入访谈</a>
							</li>
						</c:forEach>
					</ul>
					<ut:PortalPager
						url="${pageContext.request.contextPath}/interviews/list"
						pagesize="${pagesize}" totalLines="${count}" curpage="${curpage}" />
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
</body>
</html>