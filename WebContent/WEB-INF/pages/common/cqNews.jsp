<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重庆交通信息</title>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
}

dl {
	margin: 0;
}

.newsList {
	width: 490px;
	float: left;
	border: 1px solid #afc5dd;
}

.newsList .tabCon {
	padding: 10px 15px;
	height: 207px;
	overflow: hidden;
}

.newsList_index a {
	font-size: 14px;
	text-decoration: none;
}

.newsList_index a:hover {
	text-decoration: underline;
}

.newsList_index dt a {
	color: #05649B;
}

.newsList_index dd {
	width: 100%;
	overflow: hidden;
	white-space: nowrap;
	break-word: keep-all;
	text-overflow: ellipsis; /**/
}

.newsList_index dd a {
	color: #122E67;
}

.newsList .tabCon dt {
	font: bold 14px/30px \5b8b\4f53;
	color: #000;
	text-indent: 10px;
}

.newsList .tabCon dd {
	margin: 0 0 10px 10px;
	font: 14px/20px \5b8b\4f53;
}
</style>
</head>
<body>
	<div class="newsList">
		<div class="tabCon newsList_index">
			<dl>${textNewsList }
			</dl>
		</div>
	</div>
</body>
</html>