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
<body onload="loadExternalResource()">
	<div class="bg_w1284">
		<div class="wrap">
			<!--header Start-->
			<c:import url="/index.do?action=getHeader"></c:import>
			<!--header End-->
			
			<!--content Start-->
			<div class="content">
				<iframe src="${pageContext.request.contextPath}/majorproject.do?action=queryList"
						width="100%" scrolling="yes" height="750px" frameborder="0"
						 name="majorFrame" id="majorFrame"></iframe>

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
//注意：下面的代码是放在和iframe同一个页面调用
$("#majorFrame").load(function(){
	var mainheight = $(this).contents().find("body").height() + 30;
	if(mainheight== 30){
		mainheight = 750;
	}
	$(this).height(mainheight);
});
</script>
</body>
</html>