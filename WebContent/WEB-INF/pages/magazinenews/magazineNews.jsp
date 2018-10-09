<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<title>交通杂志-重庆市交通委员会</title>
<style type="text/css">
.column03 dt {
	background-image: url();
}
</style>
<script type="text/javascript">

	$(function(){
		if ('${result}' == 1) {
			alert ('${message}');
			$('.cur .subMenu a').each(function(){
				if($(this).attr('href').indexOf('magazines') != -1 ) {
					window.location.href = $(this).attr('href');
				}
			})
		}
	})

	function getContent(contentID) {
		$.ajax({
			url : "${pageContext.request.contextPath}/magazines/contents/"
					+ contentID + ".html",
			cache : false,
			type : "GET",
			dataType : "json",
			success : function(data) {
				$("#contentTitle").html(data.contentTitle);
				if (null != data.writer)
					$("#author").html("作者：" + data.writer);
				else
					$("#author").html("");
				$("#magazineContent").html(data.magazineContent);
			},
			error:function(){
				alert("错误！");	
			}
		});
	}

	function magazineNumChange(obj) {
		if (obj.value != "")
			window.location.href = "${pageContext.request.contextPath}/magazines/"
					+ obj.value + ".html";

	}
	
	
	
</script>
</head>
<body onload="loadExternalResource() ">
	<div class="bg_w1284">
		<div class="wrap">
			<!--header Start-->
			<c:import url="/index.do?action=getHeader"></c:import>
			<!--header End-->
			<!--content Start-->
			<div class="content">
				<div class="clearfix">
					<div class="breadCutNav">
						<b class="icon_home"></b> <span>当前位置：</span> <a
							href="${pageContext.request.contextPath}/index.html">首页</a> <span>
							&gt;</span><span class="cur">交通杂志</span>
					</div>
					<!--mgzSelect Start 下拉菜单-->
					<div align="right">
						<h3>杂志期数：<input type="text" class="monthText" id="txtMonth" name="txtMonth"
							onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.M',minDate:'${minMon}',maxDate:'${maxMon }',onpicked:function(){this.blur();}});"
							size="12" maxlength="30" readonly value="${iniMon}"
							onchange="magazineNumChange(this);" /></h3>
					</div>
					<div class="mgzSelect"></div>

					<!--mgzSelect End 下拉菜单-->
					<!--magazine Start-->
					<div id="magazine">
						<!--mgzPhotoIntro Start-->
						<div class="mgzPhotoIntro clearfix">
							<div class="column01">
								<img src="${photoURL}" />
							</div>
							<div class="column02">
								<h3>
									<span title="重庆交通"></span><b><font size="3px">${magazineNum}</font>
									</b>
								</h3>
								<dl>
									<dt>主题策划：${magazineTitle}</dt>
									<dd>
										<a href="${fileURL}"> <img alt=""
											src="${pageContext.request.contextPath}/images/global/bt_readonline.png"></a>
									</dd>
									<dd>
										<a
											href="${pageContext.request.contextPath}/magazines/download/${fileURL}">
											<img alt=""
											src="${pageContext.request.contextPath}/images/global/bt_download.png">
										</a>
									</dd>
								</dl>
							</div>
							<div class="column03">
								<h3 title="目录"></h3>
								<div class="mgzMenu">
									<dl>
										<c:set var="catalogID" value="0"></c:set>
										<c:forEach items="${magazineNewsList}" var="p" varStatus="xh">
											<c:if test="${p.catalogID ne catalogID }">
												<dt title="${p.catalogName}"
													style="font: bold 16px/30px \9ED1\4F53;">${p.catalogName}</dt>
												<c:set var="catalogID" value="${p.catalogID}"></c:set>
											</c:if>
											<c:if test="${p.catalogID eq catalogID }">
												<dd>
													<a href="javascript:;"
														onclick='getContent("${p.contentID}");'>${p.contentTitle}</a>
												</dd>
											</c:if>

										</c:forEach>

									</dl>
								</div>
							</div>
						</div>
						<!--mgzPhotoIntro End-->
						<div class="dividerLine">
							<div class="Line130_10"></div>
							<b class="triangleGray32_16"></b>
						</div>
						<div class="mgzTextIntro">
							<h2 id="contentTitle">${firstContent.contentTitle}</h2>
							<div id="author" style="text-align: center;">
								<c:if test="${firstContent.writer ne null }">
								作者：${firstContent.writer}</c:if>
							</div>
							<div id="magazineContent">${firstContent.magazineContent}</div>
						</div>
					</div>
					<!--magazine End-->
				</div>
				<%@ include file="../common/friendLink.jsp"%>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>