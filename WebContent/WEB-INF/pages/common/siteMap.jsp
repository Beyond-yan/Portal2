<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>网站地图-重庆市交通委员会</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.poptrox.min.js"></script>
</head>

<style>
	#wrapCon { position:relative;overflow:hidden;zoom:1;}
	.cons{ position:absolute;}
	.con{margin-bottom:12px;border:#d6dbe1 1px solid;border-top:#458fce 2px solid;}
	.con a{padding:1px 2px 2px 2px;_padding:2px;*padding:2px;}
	.con a:hover{color:#fff;background:#3b639f; text-decoration:none;}
	.con .hd{height:39px; background:#fcfcfc;border-bottom:#fff 1px solid;}
 	.con .hd h1{width:122px;height:39px; background:url(${pageContext.request.contextPath}/images/contit_bg.png) right center no-repeat;font-size:16px;line-height:39px;font-family: "微软雅黑","MicrosoftYahei","宋体","Arial Narrow",sans-serif; text-align:center;_font-weight:bold;_font-size:14px;} 
	.con .bd{padding:10px 0;border-top:#d6dbe1 1px solid;overflow:hidden;zoom:1;}
	.con:hover .bd{background:#f5f8fa;}
	.con:hover .hd h1{color:#3b639f;}
	.con .bd dl{padding:0 0 10px 10px;overflow:hidden;zoom:1;}
	.con .bd dt{padding-left:7px;color:#b87500;font-weight:bold;line-height:28px;}
	.con .bd dd{float:left;overflow:hidden;zoom:1;}
	.con .bd ul{margin-right:0;padding-right:0;overflow:hidden;zoom:1;}
	.con .bd li{float:left;height:28px;padding:0 5px;line-height:28px;}
	.con .bd strong{color:#3b639f;}
	.con .bd strong a{color:#3b639f;}
	.con .bd strong a:hover{color:#fff;}
</style>

<script>
	$(function(){
		//微信弹窗
		$('#gallery').poptrox({
			usePopupCaption: true
		});
	}) 
</script>

<body>
<c:import url="/index.do?action=getHeader"></c:import>
<div class="wrap" >
	<div class="main">   
		<div class="tagsMod">
			<div class="con" id="0">
			<div class="hd">
			   	<h1>网站<em>·</em>导航</h1>
			</div>
			<div class="bd" >
				<c:forEach items="${siteMapList }" var="site">
					<dl style="margin-left: 5em;">
					<dt >${site.name }</dt>
						<dd>
							<ul style="width: 650px">
								<c:forEach items="${site.data }" var="s">
									<c:choose>
										<c:when test="${s.name eq '交通微信' }">
											<li>
												<div id="gallery">
													<a href="${pageContext.request.contextPath}/images/weixin_traffic.jpg"
														title="重庆交通  微信" class="i_tl1">
													<img src="${pageContext.request.contextPath}/images/weixin_traffic.jpg" alt="" 
														title="请扫描二维码关注重庆交通微信公众号" style="display: none"/>交通微信
													</a>
												</div>
											</li>
										</c:when>
											<c:otherwise>
												<li><a href="${s.url}" target="_blank">${s.name}</a></li>
											</c:otherwise>
										</c:choose>
								</c:forEach>
							</ul>
						</dd>
					</dl>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
</div>
<div class="wrap">
	<%@ include file="/WEB-INF/pages/common/friendLink.jsp"%>
	<%@ include file="/WEB-INF/pages/common/footer.jsp"%>
</div>
</body>
</html>