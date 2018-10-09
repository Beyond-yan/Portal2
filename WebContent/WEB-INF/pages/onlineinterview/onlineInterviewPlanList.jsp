<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${interviewTitle}访谈计划-重庆市交通委员会</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/tablesaw/tablesaw.css" />
<script src="${pageContext.request.contextPath}/js/tablesaw/tablesaw.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
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
					<div class="mainList " id="lH">
						<div class="breadCutNav">
							<b class="icon_home"></b> <span>当前位置：</span> <a
								href="${pageContext.request.contextPath}/index.html">首页</a> 
<%-- 								<span>&gt;</span><a href="${pageContext.request.contextPath}/interviews/list_1.html">在线访谈</a> --%>
							<span> &gt;</span> <a href="javascript:;" class="cur">访谈计划</a>
						</div>
						
						<table class="tablesaw" data-tablesaw-mode="stack">
							<thead>
								<tr style="font-size: 1.2em">
									<th scope="col" data-tablesaw-sortable-col data-tablesaw-priority="persist">访谈主题</th>
									<th scope="col" data-tablesaw-sortable-col data-tablesaw-sortable-default-col data-tablesaw-priority="3">预约嘉宾</th>
									<th scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">预约时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${interviewPlanList }" var="plan">
									<tr>
										<td class="title">${plan.INTERVIEW_THEME}</a></td>
										<td>${plan.RESERVATION_GUEST}</td>
										<td>${plan.APPOINTMENT_TIME}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!--mainList End-->
					
					<!--subList Start-->
					<c:import url="/onlineinterview.do?action=getSubList"></c:import>
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
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script type="text/javascript">
		twoColHeight("lH", "rH");
	</script>
</body>
</html>