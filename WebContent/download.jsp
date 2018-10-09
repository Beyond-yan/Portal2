<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="${newsKeyWord}">
<meta name="description" content="${newsTitle}">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript">
</script>
<style>
#download{
margin: 0 auto;
height: 100px;
 text-decoration:none;  
    background:#2f435e;  
    color:#f2f2f2;  
    padding: 15px 60px 15px 60px;  
    font-size:16px;  
    font-family: 微软雅黑,宋体,Arial,Helvetica,Verdana,sans-serif;  
    font-weight:bold;  
    border-radius:3px;  
   border: 1px solid #afc5dd
    -webkit-transition:all linear 0.30s;  
    -moz-transition:all linear 0.30s;  
    transition:all linear 0.30s;  
}
.download:hover { background:#385f9e; }
.download_div{
width: 200px;
margin: 0 auto;

margin-top:100px; height:40px;
}
.contenttext
{font-family: 宋体,SimSun;
text-align:left;
    font-size: 16px;}
</style>
<title>重庆市交通委员会</title>
</head>

<body onload="loadExternalResource()"  topmargin="0"ondragstart="return true">
<!-- 禁止复制 -->
<!-- <body onload="loadExternalResource()"  topmargin="0" oncontextmenu="return false" 
	ondragstart="return false" onselectstart ="return false" 
	oncopy="document.selection.empty()" onbeforecopy="return false"> -->
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
								&gt;</span>
							
								<a href="javascript:;" class="cur">附件下载</a>
						</div>
						<div class="blank_7px"></div>
						<div class="newsDetail">
							<h2>重庆市交通委员会行政许可事项服务指南（完整版）</h2>
							<p class="dotLine_2px"></p>
							<div class="contenttext">重庆市交通委员会行政许可事项服务指南（完整版）</div>
							<div class="download_div">
							 <a id="download" href="doc/DownloadFile.rar">下载附件</a>  
							</div>
						</div>
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
</html>