<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${questionaire.QTE_TITLE }-问卷调查-重庆市交通委员会</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		var msg = "${msg}";
		if ("" != msg)
			alert(msg);
		
		appendToken();
	});

	function checkData() {
		var pass = true;
		var radioGroups = [], radioHash = {};
		var radios = $("input[type='radio']");
		$.each(radios, function() {
			var name = $(this).attr("name");
			if (!radioHash[name]) {
				radioGroups.push(name);
				radioHash[name] = true;
			}
		});
		$.each(radioGroups, function(i, n) {
			if ($("input[type=radio][name=" + n + "]:checked").length == 0) {
				alert("请填写完成后再提交！");
				pass = false;
				return false;
			}
		});
		if (!pass)
			return false;

		var checkboxGroups = [], checkboxHash = {};
		var checkboxs = $("input[type='checkbox']");
		$.each(checkboxs, function() {
			var name = $(this).attr("name");
			if (!checkboxHash[name]) {
				checkboxGroups.push(name);
				checkboxHash[name] = true;
			}
		});
		$.each(checkboxGroups, function(i, n) {
			if ($("input[type=checkbox][name=" + n + "]:checked").length == 0) {
				alert("请填写完成后再提交！");
				pass = false;
				return false;
			}
		});
		if (!pass)
			return false;

		var textAreas = $("textArea");
		$.each(textAreas, function() {
			if ("" == $.trim($(this).val())) {
				alert("请填写完成后再提交！");
				pass = false;
				return false;
			}
		});
		if (!pass)
			return false;

		return true;
	}

	function questionaireSts() {
		QTEID = '${QTEID}';
		window.open("${pageContext.request.contextPath}/questionaires/results/"
				+ QTEID + ".html");

	}
	
	function appendToken(){
	    updateForms();
	    updateTags();
	 }

	 function updateForms() {

	    // 得到页面中所有的 form 元素
	    var forms = document.getElementsByTagName('form');
	    for(i=0; i<forms.length; i++) {
	        var url = forms[i].action;
	        // 如果这个 form 的 action 值为空，则不附加 csrftoken
	        if(url == null || url == "" ) continue;
	        // 动态生成 input 元素，加入到 form 之后
	        var e = document.createElement("input");
	        e.name = "csrftoken";
	        e.value = token;
	        e.type="hidden";
	        forms[i].appendChild(e);
	    }

	 }

	 function updateTags() {

	    var all = document.getElementsByTagName('a');
	    var len = all.length;
	    // 遍历所有 a 元素

	    for(var i=0; i<len; i++) {
	        var e = all[i];
	        updateTag(e, 'href', token);
	    }

	 }

	 function updateTag(element, attr, token) {
	    var location = element.getAttribute(attr);
	    if(location != null && location != '') {
	        var fragmentIndex = location.indexOf('#');
	        var fragment = null;
	        if(fragmentIndex != -1){
	            //url 中含有只相当页的锚标记
	            fragment = location.substring(fragmentIndex);
	            location = location.substring(0,fragmentIndex);
	        }
	        var index = location.indexOf('?');
	        if(index != -1) {
	            //url 中已含有其他参数
	            location = location + '&csrftoken=' + token;
	        } else {
	            //url 中没有其他参数
	            location = location + '?csrftoken=' + token;
	        }

	        if(fragment != null){
	            location += fragment;
	        }
	        element.setAttribute(attr, location);
	    }

	 } 
</script>
<style type="text/css">
.infoList {
	line-height: 29px;
	table-layout: auto;
	border-collapse: collapse;
	width: 100%;
}

.infoList td,.infoList th {
	border: 1px solid #b5b4b4;
}

.infoList th {
	background-color: #558ed5;
	color: #fff;
}

.infoList td {
	text-align: center;
	font-size: 14px;
	color: #404040;
}

.infoList a:hover {
	color: #000;
}

.infoList td.infoTitle {
	text-align: left;
	text-indent: 10px;
}

.infoList td.infoTitle a {
	display: block;
	width: 100%;
	overflow: hidden;
	white-space: nowrap;
	work-break: keep-all;
	text-overflow: ellipsis;
}

.infoBar {
	list-style: none
}

.infoBar li {
	width: 33%;
	float: left;
}

.infoBarDes {
	list-style: none
}
</style>
</head>
<body>
	<div style="width: 800px; margin: 0 auto;">
		<div
			style="text-align: center; font-size: 16px; margin: 15px 0px 5px 0px; color: #404040; font-weight: bold;">${questionaire.QTE_TITLE }</div>
		<div
			style="text-align: center; font-size: 14px; margin: 5px 0px 15px 0px; color: #404040; height: 16px;">
			<ul class="infoBar">
				<li>创建日期：${questionaire.CREATE_DATE }</li>
				<li>过期日期：${questionaire.QTE_EXPIRES }</li>
				<li>参与人数：${number }</li>
			</ul>
		</div>
		<div
			style=" font-size: 14px; margin: 0px 0px 10px 0px; color: #404040; ">
				<c:if test="${questionaire.QTE_DES !='' && questionaire.QTE_DES !=null}">
				${questionaire.QTE_DES}
				</c:if>
		</div>
		<!-- <form method="post" action="${pageContext.request.contextPath}/questionaires/submits/${QTEID }.html"> -->
		<form method="post" action="${pageContext.request.contextPath}/Questionaire.do?action=saveAnswers">
			<input type="hidden" name="QTEID" value="${QTEID }"/>
			<table border="0" class="infoList">
				<c:forEach items="${questionList}" var="p" varStatus="status">
					<tr>
						<td style="text-align: left; text-indent: 10px;"><div
								style="float: left; font-weight: bold;">${status.count }.&nbsp;${p.QTN_DES }
							</div></td>
					</tr>
					<c:forEach items="${optionList }" var="option"
						varStatus="optionStatus">
						<c:if test="${p.QTN_ID eq option.QTN_ID }">
							<tr>
								<td style="text-align: left; text-indent: 10px;"><c:if
										test="${p.QTN_TYPE eq 1 }">
										<input type="radio" value="${option.OPN_ID }"
											name="${p.QTN_ID }" id="${option.OPN_ID }" />
										<label for="${option.OPN_ID }">&nbsp;${option.chr_order}：${ option.OPN_DES}</label>
									</c:if> <c:if test="${p.QTN_TYPE eq 2 }">
										<input type="checkbox" value="${option.OPN_ID }"
											id="${option.OPN_ID }" name="${p.QTN_ID }" />
										<label for="${option.OPN_ID }">&nbsp;${option.chr_order}：${ option.OPN_DES}</label>
									</c:if></td>
							</tr>
						</c:if>
					</c:forEach>
					<c:if test="${p.QTN_TYPE eq 3 }">
						<tr>
							<td style="text-align: left;"><textarea
									style="width: 760px; margin-left: 10px;" rows="3"
									name="${p.QTN_ID }" id="${p.QTN_ID }" class="textArea"></textarea></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<div
				style="text-align: center; font-size: 16px; color: black; margin: 20px 0px 50px 0px; color: #404040; font-weight: bold;">
				<input type="submit" value="提交" style="width: 80px;"
					onclick="return checkData();" />&nbsp;
					
<!-- 				暂时屏蔽
						<input type="button" -->
<!-- 					value="查看结果" style="width: 80px" onclick="questionaireSts();" /> -->
			</div>
		</form>
	</div>
</body>
</html>