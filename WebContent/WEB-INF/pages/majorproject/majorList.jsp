<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>重大项目建设-重庆市交通委员会</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/news.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<style>
.hdjl_M {
    width: 478px;
    border: 1px solid #d7d7d7;
}
.subSection {
    float: left;
    margin: 10px;
}
.span_right {
	text-align:right
}

.more_news {
	padding-left: 420px; 
	height: 13px; 
	line-height: 13px; 
	padding-top: 12px;
}

</style>
</head>
<body style="background: white;">
	<div class="breadCutNav">
					<b class="icon_home"></b> <span>当前位置：</span> <a
						href="${pageContext.request.contextPath}/index.html">首页</a>
					<span> &gt;</span> <a href="javascript:;" class="cur">重大项目建设</a>
				</div>
				<!--banner start-->
				<div class="banner" id="wrapper">
					
				<!--part_hdjl Start-->
				<div class="part part_hdjl">
					<div class="con clearfix">
						<c:forEach items="${cataList}" var="tn" varStatus="status">
							<!--hdjl_M Start-->
							<div class="hdjl_M  subSection">
							<h3 class="title">
								<span>${tn.name }</span>
							</h3>
							<div class="subCon">
								<ul id="mailList">
									<c:forEach items="${tn.catalog}" var="ca" varStatus="newsID">
										<li title="${ca.newsTitle }">
											<a href="${pageContext.request.contextPath}/openCatalog/${ca.newsID}.html" target="majorFrame">
												<b class="icon_noteBook"></b>${ca.newsTitle }
											</a> 
											<span class="span_right">${ca.entryDate}</span>
										</li>
									</c:forEach>
								</ul>
								<div class="more_news">
									<a href="${pageContext.request.contextPath}${tn.url }" target="majorFrame">
										<img src="${pageContext.request.contextPath}/images/gengduo.gif" width="37" height="11" border="0" />
									</a>
								</div>
							</div>
						</div>
						<!--hdjl_M End-->
						</c:forEach>
						
						
						
						
						<!--hdjl_M Start
						<div class="hdjl_M  subSection">
							<h3 class="title">
								<span>重大项目建设</span>
							</h3>
							<div class="subCon">
								<ul id="mailList">
									<li title="关于高速收费的问题">
										<a href="http://www.cq.gov.cn/publicmail/citizen/ViewReleaseMail.aspx?intReleaseID=795740" target="_blank">
											<b class="icon_noteBook"></b>关于高速收费的问题
										</a> 
										<span class="span_right">2016-9-8</span>
									</li>
								</ul>
								<div class="more_news">
									<a href="${pageContext.request.contextPath}/mailbox.html" target="_blank">
										<img src="${pageContext.request.contextPath}/images/gengduo.gif" width="37" height="11" border="0" />
									</a>
								</div>
							</div>
						</div>
						<!--hdjl_M End-->
						
						
					</div>
				</div>
				<!--part_hdjl End-->
					
				</div>
</body>
</html>