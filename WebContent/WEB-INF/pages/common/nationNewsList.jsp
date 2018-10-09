<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <ul>
	<c:forEach items="${nationNewsList}" var="news">
		<li><a href="${news.href}" target="_blank">*&nbsp;&nbsp;${news.text}</a></li>
	</c:forEach>
</ul> --%>

<c:forEach items="${nationNewsList}" var="news">
	<dd title="${news.text}">
		<a href="${news.href}" target="_blank">${news.text}</a>
	</dd>
</c:forEach>
