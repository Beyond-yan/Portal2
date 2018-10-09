<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="subList" id="rH">
	<div class="subList_title">
		<ul class="subList_tab" id="subList_tab" style="float: left;" >
			<li class="cur">
				<b class="nav_LBg"></b> 
					<a href="javascript:;">最新访谈</a>
				<b class="nav_RBg"></b>
			</li>
		</ul>
	</div>
	<div id="subList_con" class="subList_con">
		<ul>
			<c:forEach items="${interviewList}" var="list" varStatus="status">
				<c:if test="${status.count<4 }">
					<li class="hot"><b>${status.count}</b><a title="${list.interviewTitle}"
						href="${pageContext.request.contextPath}/interviews/${list.interviewId}.html">${list.interviewTitle}</a></li>
				</c:if>
				<c:if test="${status.count>3 }">
					<li><b>${status.count}</b><a title="${list.interviewTitle}"
						href="${pageContext.request.contextPath}/interviews/${list.interviewId}.html">${list.interviewTitle}</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>
<script type="text/javascript">
	twoColHeight("lH", "rH");
</script>