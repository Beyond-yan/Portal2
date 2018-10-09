<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>水运工程违规行为</title>
</head>
<style>
p {
	line-height: 6px;
	letter-spacing:3px;
}
table {
 	width:100%; 
	margin: 0 auto;
}
</style>

<script type="text/javascript">

	var ILLEGAL_BEHAVIOR_TYPE = '${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE}';
	var arrts = ILLEGAL_BEHAVIOR_TYPE.split(',');
	$(function(){
		$(".ILLEGAL_BEHAVIOR_TYPE").each(function(){
			for(var i=0; i<arrts.length; i++) {
				if (arrts[i] == $(this).val()) {
					$(this).attr("checked","true"); 
				}
			}
		});
	});
	
</script>

<body>

<div style="width:100%;" >
	<div style="width: 100%;text-align: center; font-size: 26px" >
		<p>重庆市公路水运工程建设质量安全违法违规行为</p>
		<p>信息公开报送表（人员）</p>
	</div>
	<div style="width:765px;margin: 0 auto;" >
		<span style="float: left;" >填报单位：${waterTransportIllegal.FILL_COMPANY}</span>
		<span style="float: right;margin: 0 100px;" >编号：${waterTransportIllegal.BIANHAO}</span>
	</div>
	<table border="1" cellspacing="0" cellpadding="5" >
		<tr>
			<td width="15%" >违法违规行为人员名称</td>
			<td width="35%" > ${waterTransportIllegal.ILLEGAL_PERSONNEL_NAME} </td>
			<td width="15%" >统一社会信用代码</td>
			<td width="35%" >${waterTransportIllegal.SOCIOLOGY_CREDIT_CODE}</td>
		</tr>
		<tr>
			<td width="15%" >身份证号码</td>
			<td width="35%" > ${waterTransportIllegal.ID_CARD_NO} </td>
			<td width="15%" >所在单位名称</td>
			<td width="35%" >${waterTransportIllegal.WHERE_COMPANY_NAME}</td>
		</tr>
		<tr>
			<td>项目及合同段</td>
			<td colspan="3" >${waterTransportIllegal.PROJECT_CONTRACT_SECTION}</td>
		</tr>
		<tr>
			<td>项目类别</td>
			<td colspan="3" >
				<c:if test="${waterTransportIllegal.PROJECT_CATEGORY eq 1}">公路</c:if>
				<c:if test="${waterTransportIllegal.PROJECT_CATEGORY eq 2}">水运</c:if>
			</td>
		</tr>
		<tr>
			<td>项目投资类型</td>
			<td colspan="3">
				<c:if test="${waterTransportIllegal.PROJECT_INVESTMENT_TYPE eq 1}">全部国家投资</c:if>
				<c:if test="${waterTransportIllegal.PROJECT_INVESTMENT_TYPE eq 2}">BOT</c:if>
				<c:if test="${waterTransportIllegal.PROJECT_INVESTMENT_TYPE eq 3}">BT</c:if>
				<c:if test="${waterTransportIllegal.PROJECT_INVESTMENT_TYPE eq 4}">PPP</c:if>
				<c:if test="${waterTransportIllegal.PROJECT_INVESTMENT_TYPE eq 5}">其它</c:if>
			</td>
		</tr>
		<tr>
			<td>项目建设状态</td>
			<td colspan="3" >
				<c:if test="${waterTransportIllegal.PROJECT_BUILD_STATE eq 1}">在建</c:if>
				<c:if test="${waterTransportIllegal.PROJECT_BUILD_STATE eq 2}">交工试运营</c:if>
				<c:if test="${waterTransportIllegal.PROJECT_BUILD_STATE eq 3}">竣工</c:if>
			</td>
		</tr>
		<tr>
			<td>工程等级</td>
			<td colspan="3" >${waterTransportIllegal.ENGINEERING_GRADE}</td>
		</tr>
		<tr>
			<td>违法违规行为类型</td>
			<td colspan="3"> ${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE} </td>
		</tr>
		<tr>
			<td>违法违规行为发生位置</td>
			<td colspan="3" >${waterTransportIllegal.ILLEGAL_BEHAVIOR_LOCATION}</td>
		</tr>
		<tr>
			<td>主要违法违规行为事实</td>
			<td colspan="3" >${waterTransportIllegal.ILLEGAL_BEHAVIOR_FACT}</td>
		</tr>
		<tr>
			<td>责任处理结果</td>
			<td colspan="3" >${waterTransportIllegal.RESPONSIBILITY_HANDLE_RESULT}</td>
		</tr>
		<tr>
			<td>认定文件</td>
			<td colspan="3" >${waterTransportIllegal.COGNIZANCE_FILE}</td>
		</tr>
		<tr>
			<td>认定时间</td>
			<td colspan="3" >${waterTransportIllegal.COGNIZANCE_TIME}</td>
		</tr>
		<tr>
			<td width="15%" >告知书发放时间</td>
			<td width="35%" >${waterTransportIllegal.NOTIFICATION_GRANT_TIME}</td>
			<td width="15%" >被告知单位是否提起申诉</td>
			<td width="35%" >${waterTransportIllegal.IS_MENTION_APPEAL}</td>
		</tr>
	</table>
	<div style="width:800px;margin: 0 auto;" >
		<span>填报人：${waterTransportIllegal.APPLICANT}</span>
		<span style="margin-left: 15%;" >联系电话：${waterTransportIllegal.CONTACT_PHONE}</span>
		<span style="margin-left: 15%;" >填报时间：${waterTransportIllegal.FILL_TIME}</span>
	</div>
</div>
</body>
</html>