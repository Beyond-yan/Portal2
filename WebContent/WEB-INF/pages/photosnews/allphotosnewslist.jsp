<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/userTag/PortalPager.tld" prefix="ut"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>交通信息-重庆市交通委员会</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"  type="image/x-icon" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/news.css" />
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/main.js"></script>	
<style type="text/css">
.allList_sub li {
    overflow-x: hidden;
    overflow-y: hidden;
    text-overflow: ellipsis;
    white-space: 10px;
}
.even{ background: #eee;}
.odd{ background: #ddd;} 
.hover{background: #aabbff;}
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
					<div class="mainList" id="lH" style="width:100%">
						<div class="breadCutNav">
							<b class="photoNews_icon"></b> <span>当前位置：</span> <a
								href="${pageContext.request.contextPath}/index.html">首页</a>
							<span> &gt;</span> <a href="javascript:;" class="cur">交通图片</a>
							<span> &gt;</span> <a href="javascript:;" class="cur">图片列表</a>
						
						</div>
						<div class="blank_7px"></div>
						<!--subList Start-->
						<div id="news" >
							<ul class="photoList_sub">
								<c:forEach items="${photosNewsList }" var="p" varStatus="status">
									<li title="${p.photosNewsTitle}" >
										<b class="i_pic"></b>
										<span style="float:right">${p.ENTRY_DATE}</span>
										<a target="_blank"  style="width:864px;overflow:hidden;white-space:nowrap;"
										 			href="${pageContext.request.contextPath}/photoNews/${p.photosNewsID}.html">
										 	${p.photosNewsTitle}
										 </a>
									</li>
								</c:forEach>
							</ul>
							<ut:PortalPager
								url="${pageContext.request.contextPath}/getAllPhotosNewsList/20"
								pagesize="${limitCount }" totalLines="${photosNewsCount }"
								curpage="${pageIndex }" />
						</div>
						<!--subList End-->
					</div>
					<!--mainList End-->
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
<script type="text/javascript">
$(function(){
	$("#news ul li:nth-child(even)").addClass("even");    
	/* $("#news ul li:nth-child(odd)").addClass("odd"); */
	
	$("#news ul li").hover(function(){
		$(this).addClass("hover");},
		function(){$(this).removeClass("hover");}             
	); 
});

</script>
</html>