<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link
	href="${pageContext.request.contextPath}/js/zTree/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript">
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		}
	};

	var zNodes = ${menuNodes};

	$(document).ready(function() {
		$.fn.zTree.init($("#treeMenu"), setting, zNodes);
	});
</script>
</head>
<body>
	<!--     <div style="border-bottom:1px #ABABAB dotted">
		<a style="font-size:14px;font-weight:bold">政务公开</a>
		</div> -->
	<div>
		<ul id="treeMenu" class="ztree"></ul>
	</div>
</body>
</html>