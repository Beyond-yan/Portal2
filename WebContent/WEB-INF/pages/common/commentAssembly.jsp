<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/coral.css" />
<style type="text/css">
#top_reply h1 span {
	float: left;
	font-size: 13px;
	margin-left: 10px;
	color: #c2c0c0
}

#top_reply h1 span a:hover {
	color: #df7070
}

#top_reply h1 span a {
	color: #c2c0c0;
	font-size: 13px
}
</style>

<script type="text/javascript">
	function publishSpecialColumn() {
		if (commentBox.COMMENT_CONTENT.value == "") {
			alert("请完善评论内容!");
			return false;
		}
		if (commentBox.COMMENT_CONTENT.value.length < 10) {
			alert("评论内容不能少于10个字符!");
			return false;
		}
		if (commentBox.validateBox.value == "") {
			alert("请输入验证码!");
			return false;
		} else {
			$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/textNews.do?action=newsCommentSave",
					data : $('#commentBox').serialize(),
					success : function(data) {
						alert(data);
						//重置数据
						$('#top_textarea').val('')
						$('#validateBox').val('')
						reloadcode()
				}
			})
		}
	}

	function reloadcode() {
		document.getElementById("validateCode").src = "${pageContext.request.contextPath}/common/validate.jsp?r="
				+ Math.random();
	}
</script>

<%--
<form id="commentBox" name="commentBox" method="POST"
	enctype="multipart/form-data">
	<input id="NEWS_ID" name="NEWS_ID" type="hidden" value="${newsID}">
	<input id="COMMENT_CATEGORY" name="COMMENT_CATEGORY" type="hidden" value="${commentCategory}">
	<!-- 评论框 -->
	<div id="mainBody" class="np-frame"
		style="padding-bottom: 0px; width: 100%;">
		<div id="top_reply">
			<h1 class="np-title">
				<strong>网友评论</strong> <span>文明上网理性发言，请遵守新闻评论服务协议</span>
			</h1>
			<div class="np-reply-box blueLight np-reply-box-active"
				id="np-reply-box" style="">
				<div class="np-reply-box-content textarea">
					<textarea tabindex="1" autocomplete="off" name="COMMENT_CONTENT"
						accesskey="u" id="top_textarea"
						style="height: 80px; padding: 10px;"></textarea>
				</div>
			</div>
		</div>
	</div>

	<!-- 发表 -->
	<div class="submitBtn" style="float: right; margin-top: 10px"" >
		<input id="validateBox" name="validateBox" type="text"
			style="width: 67px; height: 25px; margin-right: 10px"> <a
			href="javascript:void(0);" onclick="publishSpecialColumn();"
			class="np-btn np-btn-submit" id="top_post_btn" hidefocus="true">发表</a>
	</div>
	<img title="看不清，点击换一张" id="validateCode" name="validatecode" width="67"
		height="28" onclick="reloadcode()"
		style="cursor:pointer; float: right; margin-right: 10px; margin-top: 11px"
		src="${pageContext.request.contextPath}/common/validate.jsp">
</form>

<!-- 评论列表 -->
<div id="content">
	<ul class="np-nav-tab">
		<li id="myAllComment" class="np-active">全部评论</li>
	</ul>
	<c:forEach items="${newsCommentList}" var="comment">
		<ul class="post-list np-comment-list">
			<li class="np-post   topAll  "><img class="np-avatar popClick"
				src="${pageContext.request.contextPath}/favicon.ico">
				<div class="np-post-header">
					&nbsp;<span style="color: #0074b6;font-size: 12px">匿名网友</span> <span class="np-time"
						style="color: #c2c0c0;">${comment.COMMENT_TIME}</span>
				</div>
				<div data-height="5" class="np-post-content">
					&nbsp;<span>${comment.COMMENT_CONTENT}</span>
				</div>
				<c:forEach items="${comment.children}" var="child">
					<li class="np-post   topAll  " style="margin-left: 40px;">
							<img class="np-avatar popClick"
								 src="${pageContext.request.contextPath}/favicon.ico" >
							<div class="np-post-header">
								&nbsp;<span style="color: #0074b6;font-size: 12px">管理员</span> <span class="np-time"
																									 style="color: #c2c0c0;">${child.COMMENT_TIME}</span>
							</div>
							<div data-height="5" class="np-post-content">
								&nbsp;<span>${child.COMMENT_CONTENT}</span>
							</div>
					</li>
					<c:forEach items="${child.children}" var="child2">
						<li class="np-post   topAll  " style="margin-left: 80px;">
							<img class="np-avatar popClick"
								 src="${pageContext.request.contextPath}/favicon.ico" >
							<div class="np-post-header">
								&nbsp;<span style="color: #0074b6;font-size: 12px">管理员</span> <span class="np-time"
																									style="color: #c2c0c0;">${child2.COMMENT_TIME}</span>
							</div>
							<div data-height="5" class="np-post-content">
								&nbsp;<span>${child2.COMMENT_CONTENT}</span>
							</div>
						</li>
					</c:forEach>
				</c:forEach>
			</li>
		</ul>
	</c:forEach>
</div>--%>
