<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/userTag/PortalPager.tld" prefix="ut"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${topic.OPTION_TITLE}-意见征集-重庆市交通委员会</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
								href="${pageContext.request.contextPath}/index.html">首页</a> <span>
								&gt;</span> <a
								href="${pageContext.request.contextPath}/topics/list_1.html">征集主题</a>
							<span> &gt;</span> <a href="javascript:;" class="cur">意见征集</a>
						</div>

						<div class="newsDetail">
							<h2>${topic.OPTION_TITLE}</h2>
							<p class="dotLine_2px"></p>
							<ul class="tip clearfix">
								<li class="al_l">征集时间：${topic.VALID_DATE_S}~${topic.VALID_DATE_E}</li>
								<li class="al_c">共${participantsCnt}人参加</li>
<%-- 								<%@ include file="/WEB-INF/pages/common/share.jsp"%> --%>
							</ul>
							<div>${topic.OPTION_CONTENT}</div>
							<div style="margin-top: 10px;display: none;">
								<h3 class="commentTitle">
									<b class="i_replay"></b>全部回复：
								</h3>
								<c:forEach items="${list}" var="p" varStatus="xh">
									<table width=100% class="commentList">
										<tr class="title">
											<td width="5%"><b class="i_listTitle"></b></td>
											<th width="15%"><b>姓名:</b> <c:out value="${p.USER_NAME}" /></th>
											<th width="40%"><b>单位:</b> <c:out value="${p.USER_DEPT}" /></th>
											<th width="40%"><%-- <b>邮箱:</b> <c:out value="${p.USER_MAIL}" /> --%></th>
										</tr>
										<tr class="content">
											<td colspan="4" class="userCommend">${p.USE_COMMEND}</td>
										</tr>
									</table>
								</c:forEach>
								<ut:PortalPager
 									url="${pageContext.request.contextPath}/topics/${topic.OPTION_ID }"
									pagesize="${pagesize}" totalLines="${count}"
									curpage="${curpage}" />
							</div>
							<%--提交评论 --%>
							<h3 class="commentTitle" style="display: none;">
								<b class="i_comment"></b>我要发表意见：
							</h3>
							<table width="700" align="center" class="commentForm" style="display: none;">
								<tr>
									<td width="70">用&nbsp;户&nbsp;名：</td>
									<td><input type="text" id="userName" name="userName"></td>
									<td>电话：</td>
									<td><input type="text" id="userTel" name="userTel"
										alt="number-N"></td>
								</tr>
								<tr>
									<td>单位名称：</td>
									<td><input type="text" id="userDept" name="userDept"></td>
									<td>信箱：</td>
									<td><input type="text" id="userMail" name="userMail"
										alt="mail-N"></td>
								</tr>
								<tr>
									<td>意见内容：</td>
									<td colspan="3" style="width: 600px; height: 210px;">
<!-- 									<div id="myEditor" name="userCommend" style="height: 100px;"></div> -->
										<textarea id="myEditor" style="width: 100%; height: 100%;"></textarea>
										<input type="hidden" id="topicID" value='${topic.OPTION_ID}'>
									</td>

								</tr>
								<tr>
<!-- 									<td align="center" colspan="4"><input type="button" -->
<!-- 										value="提交" class="btn_submit" onclick="insertTextNews();"> -->
<!-- 										<input type="button" value="重置" class="btn_reset" -->
<!-- 										name="simple" onclick="reset();"></td> -->
									<td align="center" colspan="4"><img title="看不清，点击换一张"
										id="validateCode" name="validatecode" width="67" height="26"
										onclick="reloadcode()"
										style="cursor: pointer; float: right; margin-top: 11px; margin-right: 10px;"
										src="${pageContext.request.contextPath}/common/validate.jsp">
										<input id="validateBox" name="validateBox" type="text"
										style="float: right; width: 67px; height: 23px; margin-top: 10px; margin-right: 10px;">
										<input type="button" value="提交" class="btn_submit"
										onclick="insertTextNews();"
										style="float: right; margin-right: 10px;"></td>
								</tr>
							</table>
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
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/main.js"></script> --%>
	<script>
		UE.getEditor('myEditor');
		function insertTextNews() {
			var topicID = $("#topicID").val();
			var userName = encodeURIComponent($.trim($("#userName").val()));
			var userTel = encodeURIComponent($.trim($("#userTel").val()));
			var userDept = encodeURIComponent($.trim($("#userDept").val()));
			var userMail = encodeURIComponent($.trim($("#userMail").val()));
			var userCommend = encodeURIComponent($.trim($('#myEditor').val()));
			var validateBox = $("#validateBox").val();

			if (userName == "") {
				alert("请填写用户名");
				return false;
			}
			if (userTel == "") {

				alert("请填写电话");
				return false;
			}
			if (userDept == "") {
				alert("请填写单位名称");
				return false;
			}
			if (userMail == "") {
				alert("请填写邮箱");
				return false;
			}
			if (userCommend == "") {
				alert("请填写内容");
				return false;
			}
			if (validateBox == "") {
				alert("请输入验证码!");
				return false;
			}

			if (confirm("感谢您的参与，是否确定提交该条意见？") == true) {
				$.ajax({
					url : "${pageContext.request.contextPath}/topics/saveOpinion.html",
					cache : false,
					type : "post",
					dataType : "text",
					data : {
						topicID : topicID,
						userName : userName,
						userTel : userTel,
						userDept : userDept,
						userMail : userMail,
						validateCode : validateBox,
						userCommend : userCommend
					},
					success : function(content) {
						var pageDate = eval("("+content+")");
						var status = pageDate.status;
						if(status == 0){
							alert("提交成功，感谢您的参与！");
							// window.location.href = window.location.href;
							reset();
						}else{
							alert("提交失败，验证码不正确！");
							reloadcode();
						}
					},
					error : function() {
						alert("提交失败！");
					}
				});
			}
		}
		
		function reloadcode() {
			document.getElementById("validateCode").src = "${pageContext.request.contextPath}/common/validate.jsp?r="
					+ Math.random();
		}

		function reset() {
			$("#userName").val('');
			$("#userTel").val('');
			$("#userDept").val('');
			$("#userMail").val('');
			$("#myEditor").val('');
			$("#validateBox").val('');
		}
	</script>
</body>
</html>