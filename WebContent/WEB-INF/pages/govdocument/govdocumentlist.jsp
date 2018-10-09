<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/userTag/PortalPager.tld" prefix="ut"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>市政府文件-重庆市交通委员会</title>
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
							<span> &gt;</span> <a href="${pageContext.request.contextPath}/govDocument.do?action=jumpToGocDocumentList" class="cur">市政府文件</a>
						</div>
						<div class="blank_7px"></div>
						<div class="trafficNews list">
							<h2>市政府文件</h2>
							<ul>
								<c:forEach items="${govDocumentList}" var="govDocument"
									varStatus="newsID">
									<li><b class="icon_noteBook"></b> <a style="width: 600px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" target="_blank"
										href="${pageContext.request.contextPath}/govDocument.do?action=queryGovDocumentDetail&documentId=${govDocument.DOCUMENT_ID}">
											${govDocument.DOCUMENT_TITLE}</a> 
										<span>[${govDocument.PUBLISH_TIME}]</span>
											
									</li>
								</c:forEach>
							</ul>
							<ut:PortalPager
								url="${pageContext.request.contextPath}/govdocument/1988"
								pagesize="${pagesize}" totalLines="${count}"
								curpage="${curpage}" />
						</div>
					</div>
					<!--mainList End-->
					<!--subList Start-->
					<c:import url="/govDocument.do?action=govDocumentSubList"></c:import>
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