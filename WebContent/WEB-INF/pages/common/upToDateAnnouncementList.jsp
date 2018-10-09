<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="clearfix">
	<span><b class="icon i_triangleBlue"></b>政务公告</span> <a
		href="${pageContext.request.contextPath}/articleList/01010901_1.html"
		class="more">更多</a>
</h2>
<ul>
	<c:forEach items="${announcementList}" var="al">
		<li><a target="_blank"
			href="${pageContext.request.contextPath}/articles/${programType}/${al.newsID}.html">
				&bull;${al.newsTitle}</a> <span>${al.entryDate}</span></li>
	</c:forEach>
</ul>