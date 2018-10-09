<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>2018年全市交通工作会议</title>
<link href="${pageContext.request.contextPath}/css/index2018.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<script type="text/javascript">
    var t = n = 0,count;

    function bannerRoll(){
        count = $("#banner_list a").length;
        $("#banner_list a:not(:first-child)").hide();
        $("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt'));
        $("#banner_info").click(function() {
            window.open($("#banner_list a:first-child").attr('href'), "_blank")
        });
        $("#banner li").click(function() {
            var i = $(this).text() - 1; //获取Li元素内的值，即1，2，3，4
            n = i;
            if (i >= count) return;
            $("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt'));
            $("#banner_info").unbind().click(function() {
                window.open($("#banner_list a").eq(i).attr('href'), "_blank")
            })
            $("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
            document.getElementById("banner").style.background = "";
            $(this).toggleClass("on");
            $(this).siblings().removeAttr("class");
        });
        t = setInterval("showAuto()", 4000);
        $("#banner").hover(function() {
                clearInterval(t)
            },
        function() {
                t = setInterval("showAuto()", 4000);
            });

    }
    function showAuto() {
        n = n >= (count - 1) ? 0 : ++n;
        $("#banner li").eq(n).trigger('click');
    }
    
    
	var photos = null;

	$(function() {
		// 新闻
		getIndexMainNews();
		setTimeout(getIndexNewsList(), 100);
		// 图片
		setTimeout(getIndexPhotoList(), 200);
		setTimeout(querySmallPhotos(), 1000);
	});

	function getIndexMainNews() {
		$.ajax({
			url : "hyzt2018.do?action=mnews",
			type : "POST",
			cache : false,
			dataType : "json",
			success : function(content) {
				var TextNews5 = content.TextNews5;
				setTextNews5HTML(TextNews5);
				var photoList = content.photoList;
				setMainPhotoHTML(photoList);
			}
		});
	}

	function getIndexNewsList() {
		$.ajax({
			url : "hyzt2018.do?action=inews",
			type : "POST",
			cache : false,
			dataType : "json",
			success : function(content) {
				var TextNews1 = content.TextNews1;
				setTextNews1HTML(TextNews1);
				var TextNews3 = content.TextNews3;
				setTextNews3HTML(TextNews3);
				var TextNews4 = content.TextNews4;
				setTextNews4HTML(TextNews4);
			}
		});
	}

	function getIndexPhotoList() {
		$.ajax({
			url : "hyzt2018.do?action=plist",
			type : "Post",
			cache : false,
			dataType : "json",
			success : function(content) {
				photos = content;
				setPhotoListHTML(photos);
			}
		});
	}

	function querySmallPhotos() {
		$.ajax({
			url : "hyzt2018.do?action=plist",
			type : "Post",
			cache : false,
			dataType : "json",
			success : function(photos) {
				var newHTML = "";
				for (var i = 0; i < photos.length; i++) {
					var picUrl = "http://www.cqjt.gov.cn:8080/" + photos[i].photosUrl;
					newHTML += "<li id='"+photos[i].photosID+"' ><img onclick=javascript:imageListClick('" + photos[i].photosID
							+ "'); style='cursor: pointer;' src='" + picUrl + "'></li>";
				}
				$("#imageDetailList").empty().append(newHTML);
				$("#bigimg-box .window-right-box ul li").click(function() {
					var index = $("#bigimg-box .window-right-box ul li").index(this)
					setPic(index)
				})
			}
		});
	}

	function setTextNews1HTML(textNews1) {
		var newHTML = "";
		for (var i = 0; i < textNews1.length; i++) {
			newHTML += "<li style='float:left;width: 100%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;' ><a target='_blank' href='${pageContext.request.contextPath}/hyzt2018.do?action=detail&programType=011106&newsID="
					+ textNews1[i].newsID
					+ "'>"
					+ textNews1[i].newsTitle
					+ "</a></li>";
// 					+ "<span style='float:right;font-family:Microsoft YaHei;font-size: 14px'>"
// 					+ textNews1[i].entryDate
// 					+ "</span>";
		}
		$("#textNews1").empty().append(newHTML);
	}
	function setTextNews3HTML(textNews3) {
		var newHTML = "";
		////<li><a href="#">牢记使命 对标对表 扎实 面发 展建aaa</a></li>
		for (var i = 0; i < textNews3.length; i++) {
			newHTML += "<li style='float:left;width: 100%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;' ><a target='_blank' href='${pageContext.request.contextPath}/hyzt2018.do?action=detail&programType=011108&newsID="
					+ textNews3[i].newsID
					+ "'>"
					+ textNews3[i].newsTitle
					+ "</a></li>";
// 					+"<span style='float:right;font-family:Microsoft YaHei;font-size: 14px'>"
// 					+ textNews3[i].entryDate
// 					+ "</span>";
		}
		$("#textNews3").empty().append(newHTML);
	}
	function setTextNews4HTML(textNews4) {
		var newHTML = "";
		for (var i = 0; i < textNews4.length; i++) {
			newHTML += "<li style='float:left;width: 100%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;' ><a target='_blank' href='${pageContext.request.contextPath}/hyzt2018.do?action=detail&programType=011109&newsID="
					+ textNews4[i].newsID
					+ "'>"
					+ textNews4[i].newsTitle
					+ "</a></li>"
// 					"+<span style='float:right;font-family:Microsoft YaHei;font-size: 14px'>"
// 					+ textNews4[i].entryDate
// 					+ "</span>";
		}
		$("#textNews4").empty().append(newHTML);
	}
	function setTextNews5HTML(textNews5) {
		var newHTML = "";
		    if(textNews5.length>0){
		    	
        	newHTML += "<div class='news-detail-title'><a style='text-decoration:none;color: rgb(51,51,51);' target='_blank' href='${pageContext.request.contextPath}/hyzt2018.do?action=detail&programType=011109&newsID="
					+ textNews5[0].newsID + "' >" + textNews5[0].newsTitle + "</a></div>";
			newHTML += "<div class='news-detail-content'><a target='_blank' style='height:137px;color: rgb(51,51,51);text-decoration:none;display:block;overflow:hidden;line-height:19px;'"
			        + "href='${pageContext.request.contextPath}/hyzt2018.do?action=detail&programType=011109&newsID="
					+ textNews5[0].newsID + "' >";
			newHTML +=textNews5[0].newsSummary + "</a><span><a href=''>更多&gt;</a></span></div>";
		    }

		$(".news-detail").empty().append(newHTML);
	}
	function setMainPhotoHTML(photoList) {
		var newHTML = "";
		var newHTML1 = "";
		for (var i = 0; i < photoList.length; i++) {
			
			newHTML1 += "<a target='_blank' href='${pageContext.request.contextPath}/hyzt2018.do?action=detail&programType=" + photoList[i].programType
					+ "&newsID=" + photoList[i].photosNewsID + "'>";
			newHTML1 += "<img src='http://www.cqjt.gov.cn:8080/"+photoList[i].photoURL+"' /></a>";
		}
		$("#banner_list").empty().append(newHTML1);
		bannerRoll();
	}

	function setPhotoListHTML(photos) {
		
		var newHTML = "";
// 		for (var j = 0, i = 0; j < parseInt(photos.length / 4); j++, i += 4) {
// 			var i1 = i + 1;
// 			var i2 = i + 2;
// 			var i3 = i + 3;
// 			var picUrl = "http://www.cqjt.gov.cn:8080/" + photos[i].photosUrl;
// 			var picUrl1 = "http://www.cqjt.gov.cn:8080/" + photos[i1].photosUrl;
// 			var picUrl2 = "http://www.cqjt.gov.cn:8080/" + photos[i2].photosUrl;
// 			var picUrl3 = "http://www.cqjt.gov.cn:8080/" + photos[i3].photosUrl;
// 			newHTML += "<ul class='imgages'>";
// 			newHTML += "<li><a href='javascript:void(0);'><img onclick=javascript:imageListClick('" + photos[i].photosID + "'); src='" + picUrl + "'></a></li>";
// 			newHTML += "<li><a href='javascript:void(0);'><img onclick=javascript:imageListClick('" + photos[i1].photosID + "'); src='" + picUrl1
// 					+ "'></a></li>";
// 			newHTML += "<li><a href='javascript:void(0);'><img onclick=javascript:imageListClick('" + photos[i2].photosID + "'); src='" + picUrl2
// 					+ "'></a></li>";
// 			newHTML += "<li><a href='javascript:void(0);'><img onclick=javascript:imageListClick('" + photos[i3].photosID + "'); src='" + picUrl3
// 					+ "'></a></li>";
// 			newHTML += "</ul>";
// 		}
// 		if (photos.length % 4 > 0) {
// 			newHTML += "<ul class='imgages clearfix'>";
// 			for (var j = 0, i = photos.length - 1; j < photos.length % 4; j++, i--) {
// 				var picUrl = "http://www.cqjt.gov.cn:8080/" + photos[i].photosUrl;
// 				newHTML += "<li><a href='#'><img onclick=javascript:imageListClick('" + photos[i].photosID + "'); src='" + picUrl
// 						+ "'></a></li>";
// 			}
// 			newHTML += "</ul>";
// 		}

			newHTML += "<ul class='imgages clearfix'>";
			for (var j = 0; j < photos.length; j++) {
				var picUrl = "http://www.cqjt.gov.cn:8080/" + photos[j].photosUrl;
				newHTML += "<li><a href='#'><img onclick=javascript:imageListClick('" + photos[j].photosID + "'); src='" + picUrl
						+ "'></a></li>";
			}
		$("#bannerPhotos").empty().append(newHTML);
	}

	function imageListClick(object) {
		for (var i = 0; i < photos.length; i++) {
			if (object == photos[i].photosID) {
				var picUrl = "http://www.cqjt.gov.cn:8080/" + photos[i].photosUrl;
				$('#bigimg-box').show();
				$('#imageTitle').empty().append(photos[i].photosName);
				$('#mainImage').empty().append("<li><img src='"+picUrl+"'></li>")
			}
		}
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
		<a href="${pageContext.request.contextPath}/hyzt2018.do?action=index"><img src="${pageContext.request.contextPath}/images/trafficconference/head_01.png"/></a>
    <div class="title">
            <div class="t_cont">
                <ul>
                    <li><a href="http://www.cqjt.gov.cn" style="font-size: 14px;">>> 交通政务网</a></li>
                    <li><a href="${pageContext.request.contextPath}/hyzt2018.do?action=index">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/hyzt2018.do?action=list&programType=011106">工作报告</a></li>
                  <%--  <li><a href="${pageContext.request.contextPath}/hyzt2018.do?action=list&programType=011107">行业看点</a></li>--%>
                    <li><a href="${pageContext.request.contextPath}/hyzt2018.do?action=list&programType=011108">媒体报道</a></li>
<%--                     <li><a href="${pageContext.request.contextPath}/hyzl.do?action=index">往年会议</a></li> --%>
                    <li><a href="${pageContext.request.contextPath}/hyzt2018.do?action=list&programType=011109">贯彻落实</a></li>
                </ul>
            </div>
        </div></div>
    <div class="conall">
    	<div class="all-content">
            <div class="news-content clearfix">
                <div class="panel1">
                    <div class="picandnews">
                      <div class="pics" id="banner">
                          <div id="banner_bg">
                          </div>
                          <!--标题背景-->
                          <div id="banner_info">
                          </div>
                          <!--标题-->
                          <ul>
                              <li class="on">1</li>
                              <li>2</li>
                              <li>3</li>
                              <li>4</li>
                          </ul>
                          <div id="banner_list">
                          </div>
                      </div>
                        <div class="news-detail">
                        </div>
                    </div>
                </div>
                <div class="panel2">
                    <div class="panel4">
                        <div class="guozhuo_title">工作报告
                            <span><a href="${pageContext.request.contextPath}/hyzt2018.do?action=list&programType=011106">更多></a></span></div>
                        <div class="guozhuo_content">
                            <ul id="textNews1">

                            </ul>
                        </div>
                    </div>
                    <div class="panel5">
                        <div class="meiti_title">媒体报告
                            <span><a href="${pageContext.request.contextPath}/hyzt2018.do?action=list&programType=011108">更多></a></span></div>
                        <div class="meiti_content">
                            <ul id="textNews3">

                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel3">
                    <div class="guanqie_title">贯彻落实
                    <span><a href="${pageContext.request.contextPath}/hyzt2018.do?action=list&programType=011109">更多></a></span></div>
                    <div class="guanqie_content">
                        <ul id="textNews4">


                        </ul>
                       </div>
                </div>
            </div>
            <div class="pics-content">
                <div class="xiangguan_title clearfix">交通美图
                  </div>
                <div class="xiangguan_content">
                    <a class="carousel-control b_left" href="#myCarousel" ></a>
                    <a class="carousel-control b_right" href="#myCarousel"></a>
                    <div class="imgscontainer" id="bannerPhotos">
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
             <div class="footer_info1">您是网站第
    		<iframe width="85px" id="visiterCounter" scrolling="no" allowtransparency="true" style="background-color: transparent;" frameborder="0" height="16px"></iframe>         
                                   访客</div>
            <div class="footer_info2">版权所有 重庆市交通委员会 ICP备案编号 渝ICP备16007941号</div>
        </div>
    </div>

</body>
</html>
<script>
	document.getElementById("visiterCounter").src = "index.do?action=getVisiterCounter";

	$(function() {
		// 切换图片
	    $(".carousel-control").click(function () {
	        var total = $('.imgages li').length*180
	        console.log( total)
	        if($(this).hasClass('b_right')){
	            var left=$(".imgages").position().left;
	            console.log(left)
	            if(Math.abs(left-900)<total){
	                $(".imgages").css('left', left-900+'px')
	                console.log($(".imgages").position().left)
	            }
	        }
	        else {
	            var left=$(".imgages").position().left;
	            console.log(left)
	            if(left<0){
	                $(".imgages").css('left', left+900+'px')
	                console.log($(".imgages").position().left)
	            }

	        }
	    })
	});
	
    

	function bigimgBoxHide() {
		$('#bigimg-box').hide();
		$(".active").removeClass('active')
	}
</script>
