<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>2017年全市交通工作会议</title>
<link href="${pageContext.request.contextPath}/css/jwstyle.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</head>

<script type="text/javascript">
	var photos = null;

	$(function() {
		getIndexNewsList();
		getIndexPhotoList();
		querySmallPhotos();
	});

	function getIndexPhotoList() {
		$.ajax({
			url : "hyzl.do?action=getPhotoList",
			type : "Post",
			cache : false,
			dataType : "text",
			success : function(content) {
				var pageData = eval("(" + content + ")");
				photos = pageData.photos;
				setPhotoListHTML(photos);
			}
		});
	}

	function getIndexNewsList() {
		$.ajax({
			url : "hyzl.do?action=getIndexNewsList",
			type : "POST",
			cache : false,
			dataType : "text",
			success : function(content) {
				var pageData = eval("(" + content + ")");
				var textNews1 = pageData.TextNews1;
				var textNews3 = pageData.TextNews3;
				var textNews4 = pageData.TextNews4;
				var textNews5 = pageData.TextNews5;
				var photoList = pageData.photoList;
				setTextNews1HTML(textNews1);
				setTextNews3HTML(textNews3);
				setTextNews4HTML(textNews4);
				setTextNews5HTML(textNews5);
				setMainPhotoHTML(photoList);
			}
		});
	}

	function setTextNews1HTML(textNews1) {
		var newHTML = "";
		for (var i = 0; i < textNews1.length; i++) {
			newHTML += "<li style='float:left;width: 80%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;' > ·<a target='_blank' href='${pageContext.request.contextPath}/hyzl.do?action=newsDetail&programType=011101&newsID="
					+ textNews1[i].newsID
					+ "'>"
					+ textNews1[i].newsTitle
					+ "</a></li><span style='float:right;margin-top: 10px;'>"
					+ textNews1[i].entryDate
					+ "</span>";
		}
		$("#textNews1").empty().append(newHTML);
	}
	function setTextNews3HTML(textNews3) {
		var newHTML = "";
		for (var i = 0; i < textNews3.length; i++) {
			newHTML += "<li style='float:left;width: 80%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;' > ·<a target='_blank' href='${pageContext.request.contextPath}/hyzl.do?action=newsDetail&programType=011103&newsID="
					+ textNews3[i].newsID
					+ "'>"
					+ textNews3[i].newsTitle
					+ "</a></li><span style='float:right;margin-top: 10px;'>"
					+ textNews3[i].entryDate
					+ "</span>";
		}
		$("#textNews3").empty().append(newHTML);
	}
	function setTextNews4HTML(textNews4) {
		var newHTML = "";
		for (var i = 0; i < textNews4.length; i++) {
			newHTML += "<li style='float:left;width: 80%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;' > ·<a target='_blank' href='${pageContext.request.contextPath}/hyzl.do?action=newsDetail&programType=011104&newsID="
					+ textNews4[i].newsID
					+ "'>"
					+ textNews4[i].newsTitle
					+ "</a></li><span style='float:right;margin-top: 10px;'>"
					+ textNews4[i].entryDate
					+ "</span>";
		}
		$("#textNews4").empty().append(newHTML);
	}
	function setTextNews5HTML(textNews5) {
		var newHTML = "";
		for (var i = 0; i < textNews5.length; i++) {
			newHTML += "<b><a style='color:#000000;' target='_blank' href='${pageContext.request.contextPath}/hyzl.do?action=newsDetail&programType=011104&newsID="
					+ textNews5[i].newsID + "' >" + textNews5[i].newsTitle + "</a></b>";
			newHTML += "<div class='c_texts'><a target='_blank' href='${pageContext.request.contextPath}/hyzl.do?action=newsDetail&programType=011104&newsID="
					+ textNews5[i].newsID + "' class='t_span'>";
			newHTML += "<span>" + textNews5[i].newsSummary + "</span></a></div>";
		}
		$("#textNews5").empty().append(newHTML);
	}
	function setMainPhotoHTML(photoList) {
		var newHTML = "";
		var newHTML1 = "";
		for (var i = 0; i < photoList.length; i++) {
			if (i == 0) {
				newHTML += "<a class='left'><</a> <a class='right'>></a>";
			}
			if (i == photoList.length - 1) {
				// 				newHTML += "<div class='b_page'> <i>1</i> /<em class='img_numb'>25</em> </div>";
			}
			newHTML += "<span>" + photoList[i].photoTitle + "</span>";

			newHTML1 += "<li><a target='_blank' href='${pageContext.request.contextPath}/hyzl.do?action=newsDetail&programType=" + photoList[i].programType
					+ "&newsID=" + photoList[i].photosNewsID + "'>";
			newHTML1 += "<img src='http://www.cqjt.gov.cn:8080/"+photoList[i].photoURL+"' /></a></li>";
		}
		$("#photoList").empty().append(newHTML);
		$("#slidesImgs").empty().append(newHTML1);
	}

	function setPhotoListHTML(photos) {
		var newHTML = "";
		for (var j = 0, i = 0; j < parseInt(photos.length / 4); j++, i += 4) {
			var i1 = i + 1;
			var i2 = i + 2;
			var i3 = i + 3;
			var picUrl = "http://www.cqjt.gov.cn:8082/" + photos[i].pubWebappName + "/" + photos[i].savePath + "/MID_" + photos[i].fileName;
			var picUrl1 = "http://www.cqjt.gov.cn:8082/" + photos[i1].pubWebappName + "/" + photos[i1].savePath + "/MID_" + photos[i1].fileName;
			var picUrl2 = "http://www.cqjt.gov.cn:8082/" + photos[i2].pubWebappName + "/" + photos[i2].savePath + "/MID_" + photos[i2].fileName;
			var picUrl3 = "http://www.cqjt.gov.cn:8082/" + photos[i3].pubWebappName + "/" + photos[i3].savePath + "/MID_" + photos[i3].fileName;
			newHTML += "<ul class='imgages'>";
			newHTML += "<li><a href='javascript:void(0);'><img onclick='javascript:imageListClick(" + photos[i].id + ");' src='" + picUrl + "'/></a></li>";
			newHTML += "<li><a href='javascript:void(0);'><img onclick='javascript:imageListClick(" + photos[i1].id + ");' src='" + picUrl1 + "'/></a></li>";
			newHTML += "<li><a href='javascript:void(0);'><img onclick='javascript:imageListClick(" + photos[i2].id + ");' src='" + picUrl2 + "'/></a></li>";
			newHTML += "<li><a href='javascript:void(0);'><img onclick='javascript:imageListClick(" + photos[i3].id + ");' src='" + picUrl3 + "'/></a></li>";
			newHTML += "</ul>";
		}
		if (photos.length % 4 > 0) {
			newHTML += "<ul class='imgages'>";
			for (var j = 0, i = photos.length - 1; j < photos.length % 4; j++, i--) {
				var picUrl = "http://www.cqjt.gov.cn:8082/" + photos[i].pubWebappName + "/" + photos[i].savePath + "/MID_" + photos[i].fileName;
				newHTML += "<li><a href='javascript:void(0);'><img onclick='javascript:imageListClick(" + photos[i].id + ");' src='" + picUrl + "'/></a></li>";
			}
			newHTML += "</ul>";
		}
		$("#bannerPhotos").empty().append(newHTML);
	}

	function imageListClick(object) {

		for (var i = 0; i < photos.length; i++) {
			if (object == photos[i].id) {
				var picUrl = "http://www.cqjt.gov.cn:8082/" + photos[i].pubWebappName + "/" + photos[i].savePath + "/YUAN_" + photos[i].fileName;
				$('#bigimg-box').show();
				$('#imageTitle').empty().append(photos[i].title);
				$('#mainImage').empty().append("<li><img src='"+picUrl+"'></li>")
			}
		}
	}

	function querySmallPhotos() {
		$.ajax({
			url : "hyzl.do?action=getPhotoList",
			type : "Post",
			cache : false,
			dataType : "text",
			success : function(content) {
				var pageData = eval("(" + content + ")");
				var photos = pageData.photos;
				var newHTML = "";
				for (var i = 0; i < photos.length; i++) {
					var picUrl = "http://www.cqjt.gov.cn:8082/" + photos[i].pubWebappName + "/" + photos[i].savePath + "/MIN_" + photos[i].fileName;
					newHTML += "<li id='"+photos[i].id+"' ><img onclick='javascript:imageListClick(" + photos[i].id + ");' style='cursor: pointer;' src='"
							+ picUrl + "'></li>";
				}
				$("#imageDetailList").empty().append(newHTML);
				$("#bigimg-box .window-right-box ul li").click(function() {
					var index = $("#bigimg-box .window-right-box ul li").index(this)
					setPic(index)
				})
			}
		});
	}
</script>

<body>
	<div id="bigimg-box" style="display: none;">
		<div class="load-bg"></div>
		<div class="load-box">
			<div class="window-left-box">
				<div class="bigpic-box">
					<div style="margin-left: 20px;">
						<span id="imageTitle" class="title_p"></span>
					</div>
					<div class="big-pic-box">
						<ul id="mainImage">
						</ul>
					</div>
					<a onclick="lunbo('pre')" class="left-btn">
						<img src="${pageContext.request.contextPath}/images/left-arrow-icon.png">
					</a>
					<a onclick="lunbo('next')" class="right-btn">
						<img src="${pageContext.request.contextPath}/images/right-arrow-icon.png">
					</a>
					<ul id="imageDetailInfo"></ul>
				</div>
			</div>
			<div class="window-right-box">
				<div class="bigpic-box">
					<div>
						<span class="title_p">相关图片</span>
					</div>
					<ul id="imageDetailList">
					</ul>
					<div style="margin-top: -13px; margin-left: 50px" id="imagePageFromImageDetail"></div>
				</div>
				<a class="pic-window-close" onclick="bigimgBoxHide();">
					<img src="${pageContext.request.contextPath}/images/window-close-icon.png">
				</a>
			</div>
		</div>
	</div>

	<div class="head">
		<img src="images/head_01.jpg" />
	</div>
	<div class="title" style="text-align: center;">
		<div class="t_cont">
			<a href="${pageContext.request.contextPath}/hyzt2018.do?action=index">>> 返回会议首页</a>
			<ul style="width: 540px">
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
	<div class="conall">
		<div class="content">
			<div class="c_all">
				<div class="c_top">
					<div class="c_banner">
						<ul class="slideshow" id="slidesImgs">
						</ul>
						<div class="b_text" id="photoList" style="z-index: 2; background-color: rgba(3, 3, 3, 0.3); margin-top: -44px;"></div>
					</div>
					<div class="c_text" id="textNews5"></div>
				</div>
				<div class="c_bottom">
					<div class="b_cont">
						<div class="b_title">
							工作报告
							<a href="${pageContext.request.contextPath}/hyzl.do?action=newsList&programType=011101">+ 更多</a>
						</div>
						<ul id="textNews1">
						</ul>
					</div>
					<div class="b_cont" style="margin: 0 9px;">
						<div class="b_title">
							媒体报道
							<a href="${pageContext.request.contextPath}/hyzl.do?action=newsList&programType=011103">+ 更多</a>
						</div>
						<ul id="textNews3">
						</ul>
					</div>
					<div class="b_cont">
						<div class="b_title">
							贯彻落实
							<a href="${pageContext.request.contextPath}/hyzl.do?action=newsList&programType=011104">+ 更多</a>
						</div>
						<ul id="textNews4">
						</ul>
					</div>
				</div>
			</div>
			<div class="banner" id="">
				<div class="b_title">相关图片</div>
				<a class="carousel-control b_left" href="#myCarousel"></a>
				<a class="carousel-control b_right" href="#myCarousel"></a>
				<div id="bannerPhotos"></div>
			</div>
		</div>
	</div>
	<div class="footer" style="line-height: 20px;">
		<p>
			您是第
			<iframe width="85px" id="visiterCounter" scrolling="no" allowtransparency="true" style="background-color: transparent;" frameborder="0" height="16px"></iframe>
			位访者
		</p>
		<span>版权所有 重庆市交通委员会 ICP备案编号 渝ICP备16007941号</span>
	</div>
</body>
</html>
<script>
	document.getElementById("visiterCounter").src = "index.do?action=getVisiterCounter";
	$(document).ready(function() {
		index = 0;
		$(".img_numb").html($("#slidesImgs img").length);
		function autoplay() {
			index++;
			if (index > $("#slidesImgs img").length - 1) {
				index = 0;
			}
			play(index);
		}
		timer = setInterval(autoplay, 5000);

		$(".left").click(function() {
			index--;
			if (index < 1) {
				index = $("#slidesImgs img").length - 1;
			}
			play(index);
		})

		$(".right").click(function() {
			index++;
			if (index > $("#slidesImgs img").length - 1) {
				index = 0;
			}
			play(index)
		})

		function play(curindex) {
			$("#slidesImgs li").css("display", "none");
			$("#slidesImgs li").eq(index).css("display", "block");
			$(".b_text span").css("display", "none");
			$(".b_text span").eq(index).css("display", "block");
			$(".b_page i").html(index + 1);
			curindex = index;
		}
	});

	$(function() {
		var index = 0;
		//3秒轮播一次
		// 		var timer = setInterval(function() {
		// 			index = (index == 2) ? 0 : index + 1;
		// 			//某个div显示，其他的隐藏
		// 			$(".imgages").hide().eq(index).show();
		// 		}, 3000);

		$(".imgages:eq(0)").css("display", "block");
		var index = 0;
		$(".b_right").each(function() {
			$(this).click(function() {
				index++;
				if (index >= $(".imgages").length) {
					index = 0;
				}
				$(".imgages").css("display", "none");
				$(".imgages:eq(" + index + ")").fadeIn(1000);
			})
		});
		$(".b_left").each(function() {
			$(this).click(function() {
				index--;
				if (index < 0) {
					index = $(".imgages").length - 1;
				}
				$(".imgages").css("display", "none");
				$(".imgages:eq(" + index + ")").fadeIn(1000);
			})
		});
	});

	function bigimgBoxHide() {
		$('#bigimg-box').hide();
		$(".active").removeClass('active')
	}
</script>