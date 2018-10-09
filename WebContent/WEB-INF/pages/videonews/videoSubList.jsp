<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="clearfix" id="video">
	<!--mainList Start-->
	<div class="mainList" id="lH">
		<h2 class="title_photo">
			<span>推荐视频</span>
		</h2>
		<ul class="photoList">

			<c:forEach items="${recommendList }" var="p" varStatus="status">
				<li><a
					href="${pageContext.request.contextPath}/videoNews/${p.videoNewsId}.html"
					class="pic"><img src="${contentServer }${p.mainPhotosUrl}" /><b
						class="i_videoPlay51_51"></b></a>
					<p>
						<span class="r">${p.entryDate }</span><b class="i_videoPlay r"></b>
					</p>
					<h3>
						<a
							href="${pageContext.request.contextPath}/videoNews/${p.videoNewsId}.html">${p.videoNewsName}</a>
					</h3></li>
			</c:forEach>
		</ul>
	</div>
	<!--mainList End-->
	<!--subList Start-->
	<div class="subList" id="rH">
		<h2 class="title_photo">
			<span>视频集锦</span>
		</h2>
		<ul class="photoList_sub">
			<c:forEach items="${collectionList }" var="p" varStatus="status">
				<li title="${p.videoNewsName}"><b class="i_video"></b><a
					href="${pageContext.request.contextPath}/videoNews/${p.videoNewsId}.html">${p.videoNewsName}</a></li>
			</c:forEach>
		</ul>
		<a href="${pageContext.request.contextPath}/videoNews.do?action=getVideoAllList&curpage=1&programType=03"><span style="color:blue;float:right;">>>更多>></span></a>
	</div>
	<!--subList End-->
</div>