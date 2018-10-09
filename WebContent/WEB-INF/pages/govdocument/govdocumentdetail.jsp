<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="${newsKeyWord}">
<meta name="description" content="${newsTitle}">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript">

	function loadExternal() {
		var documentId = $("#documentId").val() == null ? "" : $("#documentId").val();
		var readNumber = $("#readNumber").val() == null ? "" : $("#readNumber").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/govDocument.do?action=loadReadNumner",
			cache : false,
			type : "POST",
			dataType : "text",
			data : {
				"documentId" : documentId,
				"readNumber" : readNumber
			},
			success : function(content) {},
			error : function() {}
		});
	}

	function addCommend(sFlag) {
		if($("#em"+sFlag).html() == "0%"){
			$("#em"+sFlag).html("30%")
		}else if($("#em"+sFlag).html() == "30%"){
			$("#em"+sFlag).html("60%")
		}else if($("#em"+sFlag).html() == "60%"){
			$("#em"+sFlag).html("90%")
		}else if($("#em"+sFlag).html() == "90%"){
			$("#em"+sFlag).html("100%")
		}else if($("#em"+sFlag).html() == "100%"){
			alert("此IP已评论过！");
			return;
		}
		alert("评论成功！");
	}

</script>

<title>市政府文件-重庆市交通委员会</title>
</head>

<body onload="loadExternal()"  topmargin="0" ondragstart="return false" >
	<input id="documentId" type="hidden" value="${govDocument.DOCUMENT_ID}">
	<input id="readNumber" type="hidden" value="${govDocument.READ_NUMBER}">
	<div class="bg_w1284">
		<div class="wrap">
			<!--header Start-->
			<c:import url="/index.do?action=getHeader"></c:import>
			<!--header End-->
			<!--content Start--> 
			<div class="content">
				<div class="clearfix">
					<!--mainList Start-->
					<div class="mainList" id="lH">
						<div class="breadCutNav">
							<b class="icon_home"></b> <span>当前位置：</span> <a
								href="${pageContext.request.contextPath}/index.html">首页</a> <span>
								&gt;</span>
								<a href="${pageContext.request.contextPath}/govDocument.do?action=jumpToGocDocumentList" class="cur">市政府文件</a>
						</div>
						<div class="blank_7px"></div>
						<div class="newsDetail">
							<h2>${govDocument.DOCUMENT_TITLE}</h2>
							<ul class="tip">
								<li class="al_l">发布时间：${govDocument.PUBLISH_TIME}&nbsp;&nbsp;&nbsp;发布：${govDocument.DOCUMENT_SOURCE}</li>
								<li class="al_c">共${govDocument.READ_NUMBER}人阅读</li>
								<%@ include file="/WEB-INF/pages/common/share.jsp"%>
							</ul>
							<p class="dotLine_2px"></p>
							<div>${govDocument.DOCUMENT_CONTENT}</div>
							<dl class="evaluate clearfix">
								<dt>&nbsp;</dt>
								<dd onclick="addCommend('1')" class="sy">
									<b></b> <span>实用</span> <em id="em1">0%</em>
								</dd>
								<dd onclick="addCommend('2')" class="gr">
									<b></b> <span>感人</span> <em id="em2">0%</em>
								</dd>
								<dd onclick="addCommend('3')" class="kx">
									<b></b> <span>开心</span> <em id="em3">0%</em>
								</dd>
								<dd onclick="addCommend('4')" class="wl">
									<b></b> <span>无聊</span> <em id="em4">0%</em>
								</dd>
								<dd onclick="addCommend('5')" class="ng">
									<b></b> <span>难过</span> <em id="em5">0%</em>
								</dd>
								<dd onclick="addCommend('6')" class="hd last">
									<b></b> <span>火大</span> <em id="em6">0%</em>
								</dd>
							</dl>
						</div>
					</div>
					<!--mainList End-->
					<!--subList Start-->
					<c:import url="/govDocument.do?action=govDocumentSubList"></c:import>
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
</body>
</html>