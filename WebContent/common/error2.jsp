<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${bth}">
    <title>重庆交通</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- <link rel="stylesheet" type="text/css" href="css/error2.css"/> -->
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
    <div id="error">
			<div id="icon"></div>
			<div id="message">
				<div id="title">
					<span id="span1">Oooops!!!</span>
					<span id="span2">Error 404 = Not Found</span>
				</div>
				<div id="content">
					The page you are looking for can not be found!<br/>
					You may have mis-typed the URL, so please check your spelling.<br/>
					It could also mean it does not exist, or maybe it was moved!<br/>
					Please try again! 
				</div>
				<div id="recommend">
					<span>Here are a couple of places you might want to go:</span>
					<ul>
						<li><a>Go back to the Home page</a></li>
						<li><a>Check out some work</a></li>
						<li><a>Contact your system administrator</a></li>
					<%-- 	<li><a href="${sch}://${snm}">GDS</a></li> --%>
					</ul>
				</div>
			</div>
		</div>
  </body>
</html>
