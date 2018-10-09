<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <h2 class="clearfix">
	<span><b class="icon i_triangleBlue"></b>最近来信</span><a
		href="${pageContext.request.contextPath}/mailbox.html" class="more">更多</a>
</h2>
<ul>
	<c:forEach items="${mailLists}" var="ml">
		<li><a href="${ml.href}">&bull;${ml.text}</a> <span>${ml.date}</span></li>
	</c:forEach>
</ul>
 --%>
<h3 class="title">
	<span>领导信箱</span><a
		href="${pageContext.request.contextPath}/mailbox.html" class="more"
		target="_blank"><b class="sIcon i_rArr_blue"></b>更多</a>
</h3>
<div class="subCon">
	<ul>
		<c:forEach items="${mailLists}" var="ml">
			<li title="${ml.text}"><a href="${ml.href}" target="_blank"><b class="sIcon i_mail"></b>${ml.text}
			</a> <span>${ml.date}</span></li>
		</c:forEach>
	</ul>
</div>