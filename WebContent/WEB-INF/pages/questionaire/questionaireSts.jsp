<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${surveyQuestionaire.QTE_TITLE}-调查结果-重庆市交通委员会</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<style type="text/css">
.infoList {
	line-height: 29px;
	table-layout: auto;
	border-collapse: collapse;
	width: 100%;
}

/*.infoList td,.infoList th {
	border: 1px solid #b5b4b4;
}*/
.infoList th {
	background-color: #558ed5;
	color: #fff;
}

.infoList td {
	text-align: left;
	font-size: 14px;
	color: #404040;
}

.infoList a:hover {
	color: #000;
}

.infoList td.infoTitle {
	text-align: left;
	text-indent: 10px;
}

.infoList td.infoTitle a {
	display: block;
	width: 100%;
	overflow: hidden;
	white-space: nowrap;
	work-break: keep-all;
	text-overflow: ellipsis;
}

.infoBar {
	list-style: none
}

.infoBar li {
	width: 33%;
	float: left;
}
.infoBarDes {
	list-style: none
}
</style>
</head>
<body style="background: white;">
	<div style="width: 800px; margin: 0 auto;">
		<div
			style="text-align: center; font-size: 16px; margin: 15px 0px 5px 0px; color: #404040; font-weight: bold;">《${surveyQuestionaire.QTE_TITLE}》调查结果</div>
		<div
			style="text-align: center; font-size: 14px; margin: 5px 0px 15px 0px; color: #404040; height: 16px;">
			<ul class="infoBar">
				<li>创建日期：${surveyQuestionaire.CREATE_DATE }</li>
				<li>过期日期：${surveyQuestionaire.QTE_EXPIRES }</li>
				<li>参与人数：${iCount }</li>
			</ul>
		</div>
		<div
			style=" font-size: 14px; margin: 0px 0px 10px 0px; color: #404040;padding-left:40px; ">
			<!-- <div style="padding-left:10px;"> -->
				<c:if test="${surveyQuestionaire.QTE_DES !='' && surveyQuestionaire.QTE_DES !=null}">
				${surveyQuestionaire.QTE_DES}
				</c:if>
			<!-- </div> -->
		</div>
		<div style="margin-bottom: 50px;">
			<ul style="list-style: none;">${strResultList}
			</ul>
		</div>
	</div>
</body>
</html>