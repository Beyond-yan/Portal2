<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/userTag/PortalPager.tld" prefix="ut"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>问卷调查-重庆市交通委员会</title>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body onload="loadExternalResource()">
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
								href="${pageContext.request.contextPath}/index.html">首页</a>
							<span> &gt;</span> <a href="javascript:;" class="cur">问卷调查</a>
						</div>
						<div class="blank_7px"></div>
						<div class="trafficNews list">
							<h2>
								调查主题<span style="float: right; width: 160px;height:29px">创建日期&nbsp;&nbsp;&nbsp;&nbsp;过期日期</span>
							</h2>
							<ul>
								<c:forEach items="${list}" var="p"
									varStatus="optionId">
									<li><b class="icon_noteBook"></b>
									 <a style="width: 540px;"
										target="_blank"
										href="${pageContext.request.contextPath}/Questionaire.do?action=questionList&QTEID=${p.QTE_ID}&csrftoken=<%=request.getSession().getAttribute("csrftoken") %>">
											${p.QTE_TITLE}</a> 
											
										<span style="width: 160px ;height:29px">${p.CREATE_DATE}&nbsp;&nbsp; ${p.QTE_EXPIRES}</span></li>
								</c:forEach>
							</ul>
							<ut:PortalPager
								url="${pageContext.request.contextPath}/questionaires/list"
								pagesize="${pagesize}" totalLines="${count}"
								curpage="${curpage}" />
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
	<script type="text/javascript">
		twoColHeight("lH", "rH");
		var token = '';
		
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
			alert(1);
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
</body>
</html>