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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<title></title>

</head>
<style type="text/css">
.detail h2{color: #000;
    font: 24px/50px Microsoft YaHei;
    text-align: center;}
.table-info {
    border-collapse: collapse;
    color: #03386c;
    font-size: 12px;
    margin: 5px;
    table-layout: fixed;
}
.table-info th {
    color: #0168b7;
    font-weight: normal;
    line-height: 28px;
    vertical-align: top;
    text-align:left;
}
.table-info td {
    color: #555;
}
.table-info a {
    color: #555;
}
.table-info th, .table-info td {
    background-color: white;
    border: 1px solid #333;
    height: 28px;
    padding: 2px 2px 2px 4px;
}
</style>
<script type="text/javascript">

	$(function() {
		getPublicArchiveDetail();
	})

	function getPublicArchiveDetail() {
		var detailList = "", docName = "", doc = "", flag = "", ArchivesNo = "";
		var result = '${result}';
		var json = $.parseJSON(result);
		if (json.Status == "0") {
			$.each( json.Data, function(i, n) {
				Enclosure = n.Enclosure;
				CcTo = n.CcTo;
				ArchivesNo = n.ArchivesNo;
				$("#title").html(n.Subject);
				if (Enclosure == "null" || Enclosure == null) {
					Enclosure = "";
				}
				if (CcTo == "null" || CcTo == null) {
					CcTo = "";
				}
				if (ArchivesNo == "0") {
					ArchivesNo = "未生成编号";
				}
				detailList += "<table class='table-info'  cellpadding=\"0\" cellspacing=\"1\" width=\"98%\" align=\"center\"><tr><th>发文字号</th><td colspan=\"5\">"
						+ ArchivesNo
						+ "</td></tr><tr><th>主送</th><td colspan=\"5\">"
						+ n.SendTo
						+ "</td></tr><tr><th>抄送</th><td colspan=\"5\">"
						+ CcTo
						+ "</td></tr><tr><th>标题</th><td colspan=\"5\">"
						+ n.Subject
						+ "</td></tr><tr><th>附件（无文档）</th><td colspan=\"5\">"
						+ Enclosure
						+ "</td></tr><tr><th>正文</th><td colspan=\"5\" style=\"height:60px;padding-top:5px;\" valign=\"top\">";
				for (var m = 0; m < n.ArchivesDocs.length; m++) {
					docName = n.ArchivesDocs[m].docName
							.substring();
					doc += docName.substring(
							docName.length - 3, docName.length);
					if (doc.indexOf("pdf") >= 0) {
						flag = 1;
					}
				}
				if (flag == 1) {
					for (var m = 0; m < n.ArchivesDocs.length; m++) {
						docName = n.ArchivesDocs[m].docName
								.substring();
						if (docName.substring(
								docName.length - 3,
								docName.length) == "pdf") {
							detailList += "<a href='javascript:;' onclick=\"downLoad('"
									+ n.ArchivesDocs[m].docPath
									+ "','"
									+ n.ArchivesDocs[m].docName
									+ "')\"><span style='color:blue'>"
									+ n.ArchivesDocs[m].docName
									+ "</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
						}
					}
				} else {
					for (var m = 0; m < n.ArchivesDocs.length; m++) {
						detailList += "<a href='javascript:;' onclick=\"downLoad('"
								+ n.ArchivesDocs[m].docPath
								+ "','"
								+ n.ArchivesDocs[m].docName
								+ "')\"><span style='color:blue'>"
								+ n.ArchivesDocs[m].docName
								+ "</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
					}
				}
				detailList += "</td></tr><tr><th>附件</th><td colspan=\"5\" style=\"height:60px;padding-top:5px;\" valign=\"top\">";
				// 处理部分数据不展示附件的附件 后续更改 20170901				
				if (n.Subject == "重庆市交通委员会关于重庆城口（陕渝界）至开州高速公路二期工程施工招标B3合同段中铁十二局集团有限公司投诉事项的处理决定书" 
						|| n.Subject == "重庆市交通委员会关于重庆城口（陕渝界）至开州高速公路二期工程施工招标C2合同段四川川交路桥有限责任公司投诉事项的处理决定书" 
						|| n.Subject == "重庆市交通委员会关于重庆城口（陕渝界）至开州高速公路二期工程施工招标C2合同段山东省公路建设（集团）有限公司投诉事项的处理决定书") {
					detailList += "" ;
				} else {
					for (var m = 0; m < n.ArchivesFiles.length; m++) {
						detailList += "<a href='javascript:;' onclick=\"downLoad('"
								+ n.ArchivesFiles[m].filePath
								+ "','"
								+ n.ArchivesFiles[m].fileName
								+ "')\"><span style='color:blue'>"
								+ n.ArchivesFiles[m].fileName
								+ "</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
					}
				}
				

				"</td></tr></table>";
			});
			$(".detail").html("");
			$(".detail").html(detailList);
		} else {
			$("#ul_public_archives li").text(json.Error_Msg);
		}
	}

	function downLoad(filePath, name) {
		var fileName = encodeURI(encodeURI(name));
		location.href="${pageContext.request.contextPath}/publicArchive.do?face=downLoad&fileName="+fileName+"&filePath="+filePath;
	}
	
</script>

<body style="background: white;">
	<div class="mainList" id="lH" style="width: 100%">
		<div class="breadCutNav">
			<b class="icon_home"></b> <span>当前位置：</span> <a
				href="${pageContext.request.contextPath}/index.html" target="top">首页</a>
			<span> &gt;</span><a
				href="${pageContext.request.contextPath}/openCatalog/init_/openCatalog/f1e8c2db-9e92-4a1e-a1cb-48569fa7f308.html"
				target="_top">政务公开</a><span> &gt;</span>公开公文
		</div>
		<div class="blank_7px"></div>
		<div class="newsDetail">
			<h2 id="title" ></h2>
			<p class="dotLine_2px"></p>
			<div class="detail"></div>
		</div>
	</div>
</body>
</html>