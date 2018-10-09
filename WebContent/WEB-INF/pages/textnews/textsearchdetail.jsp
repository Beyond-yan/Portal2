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
	function addCommend(sFlag) {
		$.ajax({
			url : "${pageContext.request.contextPath}/commentation/${newsID}_"
					+ sFlag + ".html",
			cache : false,
			type : "get",
			dataType : "text",
			success : function(content) {
				if (content == 1) {
					alert("此IP已评论过！");
				} else {
					window.location.href = "${pageContext.request.contextPath}/commented/${programType}/${newsID}.html";
				}
			},
			error : function() {
				alert("评论异常！");
			}
		});
	}
</script>
<title>${newsTitle}-${programTypeNamet}-重庆市交通委员会</title>
</head>


<body onload="loadExternalResource()"  topmargin="0" ondragstart="return true" >
<!-- 禁止复制 -->
<!-- <body onload="loadExternalResource()"  topmargin="0" oncontextmenu="return false"
		ondragstart="return false" onselectstart ="return false"
		oncopy="document.selection.empty()" onbeforecopy="return false"> -->
	
	<c:if test="${isBan eq 1} ">
		alert(111111111);
	</c:if>

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
							<c:if test="${programTypeName eq null}">
								<a
									href="${pageContext.request.contextPath}/searchnewslist/-1_1.html?word=${keyWord}">全文搜索结果</a>
								<span> &gt;</span>
								<a href="javascript:;" class="cur">文件详情</a>
							</c:if>
						</div>
						<div class="blank_7px"></div>
						<div class="newsDetail">
							<h2>${newsTitle}</h2>
							<ul class="tip">
								<li class="al_l">发布时间：${entryDate}&nbsp;&nbsp;&nbsp;发布：${newsSourceName}</li>
								<li class="al_c">共${readRecordCount}人阅读</li>
								<%@ include file="/WEB-INF/pages/common/share.jsp"%>
							</ul>
							<p class="dotLine_2px"></p>
							<div>${newsContent}</div>
							<dl class="evaluate clearfix">
<!-- 								<dt>&nbsp;</dt> -->
<!-- 								<dd onclick="addCommend('1')" class="sy"> -->
<%-- 									<b></b> <span>实用</span> <em>${effectiveRate}</em> --%>
<!-- 								</dd> -->
<!-- 								<dd onclick="addCommend('2')" class="gr"> -->
<%-- 									<b></b> <span>感人</span> <em>${emotionalRate}</em> --%>
<!-- 								</dd> -->
<!-- 								<dd onclick="addCommend('3')" class="kx"> -->
<%-- 									<b></b> <span>开心</span> <em>${happyRate}</em> --%>
<!-- 								</dd> -->
<!-- 								<dd onclick="addCommend('4')" class="wl"> -->
<%-- 									<b></b> <span>无聊</span> <em>${boringRate}</em> --%>
<!-- 								</dd> -->
<!-- 								<dd onclick="addCommend('5')" class="ng"> -->
<%-- 									<b></b> <span>难过</span> <em>${sadRate}</em> --%>
<!-- 								</dd> -->
<!-- 								<dd onclick="addCommend('6')" class="hd last"> -->
<%-- 									<b></b> <span>火大</span> <em>${angryRate}</em> --%>
<!-- 								</dd> -->
								<%@ include file="/WEB-INF/pages/common/commentAssembly.jsp"%>
							</dl>
						</div>
					</div>
					<!--mainList End-->
					<!--subList Start-->
					<c:import url="/textNews.do?action=getSubList"></c:import>
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