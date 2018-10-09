<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="head">
	<img src="images/head_01.jpg" />
</div>
<div class="title">
	<div class="t_cont">
		<a href="${pageContext.request.contextPath}/hyzt2018.do?action=index">>> 返回会议首页</a>
		<ul>
			<a href="${pageContext.request.contextPath}/hyzl.do?action=index">
				<li>返回</li>
			</a>
			<i></i>
			<a href="${pageContext.request.contextPath}/hyzl.do?action=newsList&programType=011101">
				<li>工作报告</li>
			</a>
			<i></i>
			<a href="${pageContext.request.contextPath}/hyzl.do?action=newsList&programType=011102">
				<li>行业看点</li>
			</a>
			<i></i>
			<a href="${pageContext.request.contextPath}/hyzl.do?action=newsList&programType=011103">
				<li>媒体报道</li>
			</a>
			<i></i>
			<a href="${pageContext.request.contextPath}/hyzl.do?action=newsList&programType=011104">
				<li>贯彻落实</li>
			</a>
		</ul>
	</div>
</div>