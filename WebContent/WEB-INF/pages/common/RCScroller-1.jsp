<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>高速路况</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scroll.js"></script>
<style type="text/css">
body {
	font: 12px/1.5 arial, \5b8b\4f53;
}

li {
	display: block;
	line-height: 29px;
	height: 29px;
}

li a {
	display: block;
	text-overflow: ellipsis;
	overflow: hidden;
	word-break: keep-all;
	white-space: nowrap;
	line-height: 29px;
	font: 12px/1.5 arial, \5b8b\4f53;
	color: #424242;
	text-decoration: none;
}

li a:hover {
	color: #e55007;
}

#roadCondition {
	padding: 0;
	margin: 0;
}
</style>
<script type="text/javascript">
	$(function() {
		getRoadCondition();
	});

	function getRoadCondition() {
		var details = "http://cx.cqjt.gov.cn/zjcx/main.html?toZjMain=controlInfo,";
		$
				.ajax({
					url : "index.do?action=getRoadCondition",
					cache : false,
					type : "GET",
					dataType : "text",
					success : function(data) {
						var content = eval('(' + data + ')');
						$
								.each(
										content.data,
										function(key, value) {
											$("#roadCondition")
													.append(
															"<li><a target=\"_blank\" href=\""+details+value.longitude+","+value.latitude+","+value.id+"\" title=\""+value.content+"\"><b class=\"dot\"></b>"
																	+ value.content
																	+ "</a></li>");
										});
						$(".scrollList").Scroll({
							line : 7,
							speed : 400,
							timer : 4000
						});
					}
				});
	}

	//滚动插件 
	(function($) {
		$.fn.extend({
			Scroll : function(opt, callback) {
				//参数初始化 
				if (!opt)
					var opt = {};
				var _this = this.eq(0).find("ul:first");
				var lineH = _this.find("li:first").height(), //获取行高 
				line = opt.line ? parseInt(opt.line, 10) : parseInt(this
						.height()
						/ lineH, 10), //每次滚动的行数，默认为一屏，即父容器高度 
				speed = opt.speed ? parseInt(opt.speed, 10) : 500, //卷动速度，数值越大，速度越慢（毫秒） 
				timer = opt.timer ? parseInt(opt.timer, 10) : 6000; //滚动的时间间隔（毫秒） 

				if (line == 0)
					line = 1;
				var upHeight = 0 - line * lineH;
				//滚动函数 
				scrollUp = function() {
					_this.animate({
						marginTop : upHeight
					}, speed, function() {
						for (i = 1; i <= line; i++) {
							_this.find("li:first").appendTo(_this);
						}
						_this.css({
							marginTop : 0
						});
					});
				}
				//鼠标事件绑定 
				_this.hover(function() {
					clearInterval(timerID);
				}, function() {
					timerID = setInterval("scrollUp()", timer);

				}).mouseout();
			}
		})
	})(jQuery);
</script>
</head>
<body style="margin: 0; padding: 0;">
	<div class="scrollList"
		style="width: 730px; height: 203px; overflow: hidden; padding: 0">
		<ul id="roadCondition">
			
		</ul>
	</div>
</body>
</html>