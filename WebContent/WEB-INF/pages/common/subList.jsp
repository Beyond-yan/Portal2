<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="subList" id="rH">
	<div class="subList_title">
		<h2>新闻排行</h2>
		<ul class="subList_tab" id="subList_tab">
			<li class="cur"><b class="nav_LBg"></b> <a href="javascript:;">一周</a>
				<b class="nav_RBg"></b></li>
			<li class="normal"><b class="nav_LBg"></b> <a
				href="javascript:;">当月</a> <b class="nav_RBg"></b></li>
		</ul>
	</div>
	<div id="subList_con" class="subList_con">
		<ul>
			<c:forEach items="${weekList}" var="p" varStatus="status">
				<c:if test="${status.count<6 }">
					<li class="hot"><b>${status.count}</b><a title="${p.newsTitle}"
						href="${pageContext.request.contextPath}/articles/010101/${p.newsID}.html">${p.newsTitle}</a></li>
				</c:if>
				<c:if test="${status.count>5 }">
					<li><b>${status.count}</b><a title="${p.newsTitle}"
						href="${pageContext.request.contextPath}/articles/010101/${p.newsID}.html">${p.newsTitle}</a></li>
				</c:if>
			</c:forEach>
		</ul>
		<ul class="undis">
			<c:forEach items="${monthList}" var="p" varStatus="status">
				<c:if test="${status.count<6 }">
					<li class="hot"><b>${status.count}</b><a title="${p.newsTitle}"
						href="${pageContext.request.contextPath}/articles/010101/${p.newsID}.html">${p.newsTitle}</a></li>
				</c:if>
				<c:if test="${status.count>5 }">
					<li><b>${status.count}</b><a title="${p.newsTitle}"
						href="${pageContext.request.contextPath}/articles/010101/${p.newsID}.html">${p.newsTitle}</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>
<script type="text/javascript">
	twoColHeight("lH", "rH");
</script>