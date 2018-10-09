<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/userTag/PortalPager.tld" prefix="ut"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<title>全文搜索-重庆市交通委员会</title>
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
				<div class="con clearfix" id="lH">
					<div class="breadCutNav">
						<b class="icon_home"></b> <span>当前位置：</span> <a
							href="${pageContext.request.contextPath}/index.html">首页</a>
						<span> &gt;</span> <a href="javascript:;" class="cur">全文搜索结果</a>
					</div>
					<div class="blank_7px"></div>
					<%String word1 = request.getAttribute("word").toString(); %>
					<div class="trafficNews list">
						<h2>${programTypeName}</h2>
						<ul id="trafficNewsUL" >
							<c:forEach items="${trafficNewsList}" var="trafficNews"
									   varStatus="id">
								<li><b class="icon_noteBook"></b>
									<a target="_blank" style="width:864px;overflow:hidden;white-space:nowrap;"
									   href="${pageContext.request.contextPath}/${trafficNews.url}.html">
											${trafficNews.title}</a> <span>${trafficNews.entry_date}</span>
									<div style="clear:both;"></div>
									<div style="margin-left: 48px;margin-right: 20px;">${trafficNews.content}</div>
								</li>
							</c:forEach>
						</ul>
						<div class="page" ><%-- 套用分页样式，对数据进行分页 --%>
							<% int pagesize=Integer.parseInt(request.getAttribute("pagesize").toString());
								int count=Integer.parseInt(request.getAttribute("count").toString());
								int pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
								String programType=request.getAttribute("programType").toString();
								String orderby=request.getAttribute("orderby").toString();
								String startTime=request.getAttribute("startTime").toString();
								String endTime=request.getAttribute("endTime").toString();
								String wordPosition=request.getAttribute("wordPosition").toString();
								String word = request.getAttribute("word").toString();
								word = java.net.URLEncoder.encode(word, "UTF-8");//测试服务器编码不同  暂时屏蔽
								int pageCount = count%pagesize == 0? count/pagesize : (count/pagesize + 1);
								int displayPages = 10;
								if(pageIndex > 1){
									String pageType = programType + "_" + (pageIndex-1);
							%>
							<a href="<%=request.getContextPath() %>/searchnewslist/<%=pageType %>.html?word=<%=word%>&orderby=<%=orderby%>&startTime=<%=startTime%>&endTime=<%=endTime%>&wordPosition=<%=wordPosition%>">上一页</a>
							<%} %>
							<%if(displayPages >= pageCount){
								for(int i = 1; i <= pageCount; i++){
									if (pageIndex == i){
							%>
							<a href="javascript:;" class="cur"><%=i %></a>
							<%} else{
								String pageType = programType + "_" + i;
							%>
							<a href="<%=request.getContextPath() %>/searchnewslist/<%=pageType %>.html?word=<%=word%>&orderby=<%=orderby%>&startTime=<%=startTime%>&endTime=<%=endTime%>&wordPosition=<%=wordPosition%>"><%=i %></a>
							<%} %>
							<%}
							}else{
								for(int i = (pageIndex-displayPages/2) > 1 ? (pageIndex - displayPages / 2) : 1;
									i <= ((pageIndex + displayPages / 2 - 1) < pageCount ?
											(pageIndex + displayPages / 2 - 1) : pageCount);i++){
									if(pageIndex == i){
							%>
							<a href="javascript:;" class="cur"><%=i %></a>
							<%}else{
								String pageType = programType + "_" + i;
							%>
							<a href="<%=request.getContextPath() %>/searchnewslist/<%=pageType %>.html?word=<%=word%>&orderby=<%=orderby%>&startTime=<%=startTime%>&endTime=<%=endTime%>&wordPosition=<%=wordPosition%>"><%=i %></a>
							<%} %>
							<%}
							}%>
							<%if (pageIndex < pageCount){
								String pageType = programType + "_" + (pageIndex+1);
							%>
							<a href="<%=request.getContextPath() %>/searchnewslist/<%=pageType %>.html?word=<%=word%>&orderby=<%=orderby%>&startTime=<%=startTime%>&endTime=<%=endTime%>&wordPosition=<%=wordPosition%>">下一页</a>
							<%} %>
							<%-- 							<ut:PortalPager --%>
							<%-- 								url="${pageContext.request.contextPath}/searchnewslist/${programType}" --%>
							<%-- 								pagesize="${pagesize}" totalLines="${count}" --%>
							<%-- 								curpage="${pageIndex}"/> --%>
							<!-- 							</div> -->
							<%-- 分页结束 --%>
						</div>
					</div>
					<!--mainList End-->
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
</body>
<script type="text/javascript">

    $(function(){



// 	    $("#trafficNewsUL a").each(function(index, element) {
// 	    	var word = '${word}';
// 	        var title = $(this).attr('title');
// 	        title = title.replace(word, '<font>'+word+'</font>');
// 	        $(this).text(title);
// 	    });



    })

    function searchNews(){
        var url = "${pageContext.request.contextPath}/textNews.do?action=getSearchNewsList";
        $.ajax({
            url : url,
            cache : false,
            type : "POST",
            dataType : "text",
            success : function(data) {
                alert("执行成功了~~");
            }
        });
    }
</script>
</html>