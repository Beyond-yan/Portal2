<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/userTag/PortalPager.tld" prefix="ut"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>意见征集-重庆市交通委员会</title>
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
								href="${pageContext.request.contextPath}/index.html">首页</a>
							<span> &gt;</span> <a href="javascript:;" class="cur">征集主题</a>
						</div>
						<div class="blank_7px"></div>
						<div class="trafficNews list">
							<h2>
								征集主题<span style="float: right; /*margin-right: 10px;*/ width: 172px;">开始日期&nbsp;&nbsp;&nbsp;&nbsp;结束日期</span>
							</h2>
							<ul>
								<c:forEach items="${list}" var="p" varStatus="optionId">
									<li>
										<b class="icon_noteBook"></b> 
										<a style="width: 540px;" target="_blank"
											href="${pageContext.request.contextPath}/topics/${p.OPTION_ID}.html">${p.OPTION_TITLE}</a> 
										<span style="width: 170px">${p.VALID_DATE_S}&nbsp;&nbsp;&nbsp;&nbsp;${p.VALID_DATE_E}</span>
									</li>
								</c:forEach>
							</ul>
							<ut:PortalPager url="${pageContext.request.contextPath}/topics/list" 
								pagesize="${pagesize}" totalLines="${count}" curpage="${curpage}"/>
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
	<script type="text/javascript">
		twoColHeight("lH", "rH");
	</script>
</body>
</html>