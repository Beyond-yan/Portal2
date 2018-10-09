<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>重庆市交通委员会</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css?version=20180718"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css?version=20180718"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zzsc.css?version=20180828"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scroll.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.SuperSlide.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.poptrox.min.js"></script>
    <style>
        .column-con li {
            margin-bottom: 12px;
            height: 23px;
        }

        .btn-login {
            line-height: 26px;
            width: 155px;
            height: 29px;
            text-align: center;
            font: bold 16px/27px 微软雅黑;
            color: #fff;
            background-position: -204px -153px;
            background-image: url(${pageContext.request.contextPath}/images/bg_sprite.png);
            background-repeat: no-repeat;
            text-decoration: none;
            display: inline-block;
        }

        a:hover {
            text-decoration: none;
        }
        
    </style>
    <script  type="text/javascript">
  

    
        //焦点图JS
        $(function () {
            /* 		myFocus.setting({
             style : 'mF_expo2010',
             id : 'myFocus',
             time : 3
             });//style为风格样式，id为焦点图ID，time为每帧间隔时间(秒) */
            $('#gallery').poptrox({
                usePopupCaption: true
            });

            $(".focusBox").slide({
                titCell: ".num li",
                mainCell: ".pic",
                effect: "fold",
                autoPlay: true,
                trigger: "click",
                delayTime: 1000,
                interTime: 5000,
                startFun: function (i) {
                    jQuery(".focusBox .txt li").eq(i).animate({
                        "bottom": 0
                    }).siblings().animate({
                        "bottom": -36
                    });
                }
            });
            $(".focusBox1").slide({
                titCell: ".num li",
                mainCell: ".pic",
                effect: "fold",
                autoPlay: true,
                trigger: "click",
                delayTime: 1000,
                interTime: 5000,
                startFun: function (i) {
                    jQuery(".focusBox1 .txt li").eq(i).animate({
                        "bottom": 0
                    }).siblings().animate({
                        "bottom": -36
                    });
                }
            });
            var subjectSrc = "${subjectNews.IFRAME_URL }";
            if (subjectSrc != null && subjectSrc != "") {
                //	$("#subjectFrame").attr("src", subjectSrc);
            }
            $("#subjectFrame").attr("src", "${pageContext.request.contextPath}/subjects/20131213/subject.html");
            getRoadCondition();
            getNationwideNews();
            getMailList();
            //getStateCouncilInfo();

            //added by Cube @140731
            getRoadConditionGSGD();
            getGovDocument();
            getPublicData();
        });

        function loadIndexExternalResource() {
            $("#weatherIframe")
                .attr(
                    "src",
                    "http://tianqi.2345.com/plugin/widget/index.htm?s=3&z=2&t=1&v=0&d=1&k=&f=1&q=1&e=0&a=0&c=57516&w=180&h=36");
            document.getElementById("visiterCounter").src = "index.do?action=getVisiterCounter";
            var subjectSrc = "${subjectNews.IFRAME_URL }";
            if (subjectSrc != null && subjectSrc != "")
            //$("#subjectFrame").attr("src", subjectSrc);
            //$("#lxcx").attr("src", "http://cx.cqjt.gov.cn/widget/cxgh/loader.htm?width=222&theme=blue&height=150");
                $("#wsbs")
                    .attr("src",
                        "http://bs.cqjt.gov.cn:8000/cqnet/web/webdashboard!onlineServiceHall.do");

        }

        function getRoadCondition() {
            var details = "http://cx.cqjt.gov.cn/zjcx/main.html?toZjMain=controlInfo,";
            $
                .ajax({
                    url: "index.do?action=getRoadCondition",
                    cache: false,
                    type: "GET",
                    dataType: "text",
                    success: function (data) {
                        var content = eval('(' + data + ')');
                        $
                            .each(
                                content.data,
                                function (key, value) {
                                    $("#roadCondition")
                                        .append(
                                            "<li><a target=\"_blank\" href=\"" + details + value.longitude + "," + value.latitude + "," + value.id + "\" title=\"" + value.content + "\"><b class=\"dot\"></b>"
                                            + value.content
                                            + "</a></li>");
                                });
                        if (content.data.length > 10) {
                            $(".scrollList").Scroll({
                                line: 1,
                                speed: 500,
                                timer: 15000
                            });
                        } else if (content.data.length == 0) {
                            $("#roadCondition").append("<li><a href=\"javascript:;\" ><b class=\"dot\"></b>路况畅通</a></li>");
                        }
                    }
                });
        }
        function getPublicData() {
            $
                .ajax({
                    url: "textNews.do?action=getNewsJsonList",
                    cache: false,
                    type: "GET",
                    dataType: "text",
                    data: {
                        "curpage": 0,
                        "pagesize": 20,
                        "programType":"01010102"
                    },
                    success: function (data) {
                        var content = eval('(' + data + ')');
                        var html="";
                        content=content.root;
                        for (var i = 0; i < content.length; i++) {
                            if(i%4==0){
                                if(i>0){
                                    html+="</div>";
                                }
                                html+="<div class=\"table_row bold\">";
                            }
                            html+="<div class=\"table_cell\"><a target=\"_blank\" style=\"color:#3B687E;\" href=\""+content[i].subNewsTitle+"\">"+content[i].newsTitle+"</a> </div>\n";
                        }
                        if(html!=""){
                            html+="</div>";
                        }
                        $("#public_table").append(html);
                    }
                });
        }
        //added by Cube @140732
        function getRoadConditionGSGD() {
            $
                .ajax({
                    url: "index.do?action=getRoadConditionGSGD",
                    cache: false,
                    type: "GET",
                    dataType: "text",
                    success: function (data) {
                        var content = eval('(' + data + ')');

                        $.each(content.data, function (key, value) {
                            $("#roadConditionGSGD").append(
                                "<li><a href=\"javascript:;\" title=\"" + value.content + "\"><b class=\"dot\"></b>" + value.content + "</a></li>");
                        });
                        $(".scrollListGSGD").Scroll({
                            line: 1,
                            speed: 500,
                            timer: 15000
                        });
                    }
                });
        }


        function getGovDocument() {
            $.ajax({
                url: "govDocument.do?action=getGovDocumentList",
                cache: false,
                type: "GET",
                dataType: "text",
                data: {
                    "pageIndex": 1,
                    "pageSize": 6
                },
                success: function (data) {
                    var content = eval('(' + data + ')');
                    $("#govDocumentToId").append(FillGovDocument(content));
                }
            });
        }

        function FillGovDocument(content) {
            var govDocumentHTML = "";
            for (var i = 0; i < content.length; i++) {
                govDocumentHTML += "<dd title='" + content[i].DOCUMENT_TITLE + "'>";
                govDocumentHTML += "<a href='${pageContext.request.contextPath}/govDocument.do?action=queryGovDocumentDetail&documentId=" + content[i].DOCUMENT_ID + "' target='_blank'>";
                govDocumentHTML += content[i].DOCUMENT_TITLE + "</a></dd>";
            }
            return govDocumentHTML;
        }

        function getNationwideNews() {
            $.ajax({
                url: "index.do?action=nationNewsList",
                cache: false,
                type: "GET",
                dataType: "json",
                success: function (data) {
                    $.each(data, function (key, value) {
                        $("#nationwideNews").append(
                            "<dd title=\"" + value.text + "\"><a href=\"" + value.href + "\" target=\"_blank\">"
                            + value.text + "</a></dd>");
                    });
                }
            });
        }

        /* 国务院文件 20160901 */
        function getStateCouncilInfo() {
            $.ajax({
                url: 'http://www.cq.gov.cn/gwywj/pushinfo.js',
                dataType: "jsonp",
                jsonp: "pushInfoJsonpCallBack",
                jsonpCallback: "pushInfoJsonpCallBack",
                success: function (data) {
                    if (data && data.length > 0) {
                        for (var i = 0; i < 6; i++) {
                            var json = data[i];
                            $("#stateCouncilInfo").append("<dd title=\"" + json.title + "\"><a href=\""
                                + json.link + "\" target=\"_blank\">"
                                + json.title + "</a></dd>");
                        }
                    }
                },
            });
        }

        function getMailList() {
            $
                .ajax({
                    url: "index.do?action=recentlyMail",
                    cache: false,
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        $.each(data, function (key, value) {
                            $("#mailList").append(
                                "<li title=\"" + value.text + "\"><a href=\"" + value.href + "\" target=\"_blank\"><b class=\"sIcon i_mail\"></b>"
                                + value.text
                                + "</a> <span>"
                                + value.date
                                + "</span></li>");
                        });
                    }
                });
        }

        function setTitle() {
            var title = '${topic.OPTION_TITLE}';
            if (title != null && title.length >= 30) {
                var str = title.substring(0, 30) + " . . .";
                $("#wjTitle").html("<b>主题：</b>111111111111");

            }

        }


        function queryLk() {
            var beginSite = $("#beginSite").val();
            var endSite = $("#endSite").val();
            location.href = "http://cx.cqjt.gov.cn/zjcx/car.html?cfd=&mdd=&startN=" + beginSite + "&endN=" + endSite
        }

        //滚动插件
        (function ($) {
            $.fn.extend({
                Scroll: function (opt, callback) {
                    //参数初始化
                    if (!opt)
                        var opt = {};
                    var _this = this.eq(0).find("ul:first");
                    var lineH = _this.find("li:first").height(), //获取行高
                        line = opt.line ? parseInt(opt.line, 10) : parseInt(this.height() / lineH, 10), //每次滚动的行数，默认为一屏，即父容器高度
                        speed = opt.speed ? parseInt(opt.speed, 10) : 500, //卷动速度，数值越大，速度越慢（毫秒）
                        timer = opt.timer ? parseInt(opt.timer, 10) : 6000; //滚动的时间间隔（毫秒）

                    if (line == 0)
                        line = 1;
                    var upHeight = 0 - line * lineH;
                    //滚动函数
                    scrollUp = function () {
                        _this.animate({
                            marginTop: upHeight
                        }, speed, function () {
                            for (i = 1; i <= line; i++) {
                                _this.find("li:first").appendTo(_this);
                            }
                            _this.css({
                                marginTop: 0
                            });
                        });
                    }
                    //鼠标事件绑定
                    _this.hover(function () {
                        clearInterval(timerID);
                    }, function () {
                        timerID = setInterval("scrollUp()", timer);

                    }).mouseout();
                }
            })
        })(jQuery);

        function locationHref() {
            //location.href ="http://cx.cqjt.gov.cn/index/UnderConstructionAnd.html";
            window.open("http://cx.cqjt.gov.cn/index/UnderConstructionW.html", "_blank");
        }
    </script>
</head>
<body onload="loadIndexExternalResource()">
<!--header Start-->
<c:import url="/index.do?action=getHeader"></c:import>
<!--header End-->
<div class="content">
    <!--partA Start-->
    <div class="partA clearfix">
        <div class="btnList">
            <a href="${pageContext.request.contextPath}/leaders/0A6DAEA3B6ED4A19A204F4FE58F0492A_1.html"
            <%-- 				<a href="${pageContext.request.contextPath}/leaders/index.html" --%>
               target="_blank" class="left_t_w1"
               onMouseOver="this.className='left_t_w_1';"
               onMouseOut="this.className='left_t_w1';"></a> <a class="left_t_w2"
                                                                href="${pageContext.request.contextPath}/openCatalog/init_/openCatalog/f1e8c2db-9e92-4a1e-a1cb-48569fa7f308.html"
                                                                target="_blank"
                                                                onMouseOver="this.className='left_t_w_2';"
                                                                onMouseOut="this.className='left_t_w2';"></a> <a
                href="${pageContext.request.contextPath}/organizations/f2b5e16f-e81c-41ef-8c1d-c1a0d230c552.html"
                target="_blank" class="left_t_w3"
                onMouseOver="this.className='left_t_w_3';"
                onMouseOut="this.className='left_t_w3';"></a> <a
                href="${pageContext.request.contextPath}/organizations/362ee30c-e337-4dea-afe8-2bba8a23435b.html"
                target="_blank" class="left_t_w4"
                onMouseOver="this.className='left_t_w_4';"
                onMouseOut="this.className='left_t_w4';"></a>
        </div>
        <div id="tab" class="newsList">
            <dl class="tab clearfix">
                <dt style="margin-left: 15px;color:red;display:none;" class="19da"> 贯彻十九大</dt>
                <dt class="cur" >重庆交通新闻</dt>
                <dt >重庆交通政务信息</dt>
                <dt >全国交通信息</dt>
                <dt >国务院新闻</dt>

                <!-- 					<dd class="more"> -->
                <!-- 						<a target="_blank" -->
                <%-- 							href="${pageContext.request.contextPath}/articleList/010101_1.html"><img --%>
                <%-- 							src="${pageContext.request.contextPath}/images/gengduo.gif" --%>
                <!-- 							width="37" height="11" border="0" style="margin-top: 10px;" /></a> -->
                <!-- 					</dd> -->
                <!-- 					<dd style="display: none;" class="more"> -->
                <!-- 						<a target="_blank" href="http://www.mot.gov.cn/xinxilb/index.html"> -->
                <!-- 						<a target="_blank" href="http://www.mot.gov.cn/difangxinwen/xxlb_fabu/index.html"> -->
                <%-- 						<img src="${pageContext.request.contextPath}/images/gengduo.gif" width="37" height="11" border="0" style="margin-top: 10px;" /> --%>
                <!-- 						</a> -->
                <!-- 					</dd> -->
                <!-- 					<dd style="display: none;" class="more"> -->
                <!-- 						<a target="_blank" href="http://www.cq.gov.cn/gwywj/default_1.shtml"> -->
                <%-- 						<img src="${pageContext.request.contextPath}/images/gengduo.gif" width="37" height="11" border="0" style="margin-top: 10px;" /> --%>
                <!-- 						</a> -->
                <!-- 					</dd> -->
                <!-- 					<dd style="display: none;" class="more"> -->
                <%-- 						<a target="_blank" href="${pageContext.request.contextPath}/govDocument.do?action=jumpToGocDocumentList"> --%>
                <%-- 						<img src="${pageContext.request.contextPath}/images/gengduo.gif" width="37" height="11" border="0" style="margin-top: 10px;" /> --%>
                <!-- 						</a> -->
                <!-- 					</dd> -->
            </dl>
            <!--tabCon Start-->
        	   <div class="tabCon newsList_index " style="display:none">
	                <dl>${textNewsList4}</dl>
	                <a style="float: right;bottom: 0px" target="_blank"
	                   href="${pageContext.request.contextPath}/articleList/010101_1.html">
	                   
	                    <img src="${pageContext.request.contextPath}/images/gengduo.gif"
	                         width="37" height="11" border="0"/>
	                </a>
	            </div>
	            
	             <div class="tabCon newsList_index "  style="display:block" >
	                <dl>${textNewsList}</dl>
	                <a style="float: right;bottom: 0px" target="_blank"
	                   href="${pageContext.request.contextPath}/articleList/010101_1.html">
	                   
	                    <img src="${pageContext.request.contextPath}/images/gengduo.gif"
	                         width="37" height="11" border="0"/>
	                </a>
	            </div>
	            
	            
	             <div class="tabCon newsList_index"  style="display:none" >
	                <dl>${textNewsList2}</dl>
	                <a style="float: right;bottom: 0px" target="_blank"
	                   href="/openCatalog/init_/openCatalogList/01010901_1.html">
	                   
	                    <img src="${pageContext.request.contextPath}/images/gengduo.gif"
	                         width="37" height="11" border="0"/>
	                </a>
	            </div>
	           
	            <div class="tabCon newsList_index "   style="display:none">
	                ${news}
	                <a style="float: right;bottom: 0px" target="_blank"
	                   href="http://www.mot.gov.cn/difangxinwen/xxlb_fabu/index.html">
	                   
	                    <img src="${pageContext.request.contextPath}/images/gengduo.gif"
	                         width="37" height="11" border="0"/>
	                </a>
	            </div>
            
         
			 <!--
            <div class="tabCon newsList_index" style="display: none;">
                <dl style="margin-top: 10px;" id="nationwideNews"></dl>
                <a style="float: right;" target="_blank" href="http://www.mot.gov.cn/difangxinwen/xxlb_fabu/index.html">
                    <img src="${pageContext.request.contextPath}/images/gengduo.gif" width="37" height="11" border="0"
                         style="margin-top: 10px;"/>
                </a>
            </div>



            <div class="tabCon newsList_index" style="display: none;">
                <dl style="margin-top: 10px;" id="stateCouncilInfo"></dl>
                <a style="float: right;" target="_blank" href="http://www.gov.cn/">
                    <img src="${pageContext.request.contextPath}/images/gengduo.gif" width="37" height="11" border="0"
                         style="margin-top: 10px;"/>
                </a>
            </div>

            <div class="tabCon newsList_index" style="display: none;">
                <dl style="margin-top: 10px;" id="govDocumentToId"></dl>
                <a style="float: right;" target="_blank"
                   href="${pageContext.request.contextPath}/govDocument.do?action=jumpToGocDocumentList">
                    <img src="${pageContext.request.contextPath}/images/gengduo.gif" width="37" height="11" border="0"
                         style="margin-top: 10px;"/>
                </a>
            </div>
           tabCon End-->
        </div>
        <!--newsList End-->
        <%-- <div id="myFocus" class="mF_expo2010">
            <div class="loading">
                <span>请稍候...</span>
            </div>
            <!--载入画面-->
            <ul class="pic">
                <!--内容列表-->
                <c:forEach items="${photosList}" var="p">
                    <c:if test="${p.isTextNews eq 0 }">
                        <li><a target="_blank"
                            href="${pageContext.request.contextPath}/photoNews/${p.photosNewsID}.html">
                                <img src="${contentServer }${p.photoURL}" alt="${p.photoTitle}" />
                        </a></li>
                    </c:if>
                    <c:if test="${p.isTextNews eq 1 }">
                        <li><a target="_blank"
                            href="${pageContext.request.contextPath}/articles/${p.programType}/${p.photosNewsID}.html">
                                <img src="${contentServer }${p.photoURL}" alt="${p.photoTitle}" />
                        </a></li>
                    </c:if>
                </c:forEach>
            </ul>
        </div> --%>

        <div class="focusBox" style="margin: 0">
            <ul class="pic"
                style="position: relative; width: 340px; height: 262px;">
                <c:forEach items="${photosList}" var="p">
                    <c:if test="${p.isTextNews eq 0 }">
                        <li><a target="_blank"
                               href="${pageContext.request.contextPath}/photoNews/${p.photosNewsID}.html">
                            <img src="${contentServer }${p.photoURL}" alt="${p.photoTitle}"/>
                        </a></li>
                    </c:if>
                    <c:if test="${p.isTextNews eq 1 }">
                        <li><a target="_blank"
                               href="${pageContext.request.contextPath}/articles/${p.programType}/${p.photosNewsID}.html">
                            <img src="${contentServer }${p.photoURL}" alt="${p.photoTitle}"/>
                        </a></li>
                    </c:if>
                </c:forEach>
            </ul>
            <div class="txt-bg"></div>
            <div class="txt">
                <ul>
                    <c:forEach items="${photosList}" var="p">
                        <li><a target="_blank"
                               href="${pageContext.request.contextPath}/articles/${p.programType}/${p.photosNewsID}.html">
                                ${p.photoTitle} </a></li>
                    </c:forEach>

                </ul>
            </div>
            <ul class="num">
                <c:forEach items="${photosList}" varStatus="status">
                    <li class=""><a>${status.count }</a><span></span></li>
                </c:forEach>
            </ul>
        </div>


        <!--myFocus End-->
    </div>
    <!--partA End-->

    <c:if test="${subjectNews.IFRAME_URL ne null }">
        <div class="part banner"
             style="width: 1024px; height: 60px; overflow: hidden;margin-top: 10px;">
            <iframe scrolling="no" width="1024px" height="60px" frameborder="0"
                    id="subjectFrame" name="subjectFrame" src="" class="bannerImg"></iframe>
        </div>
    </c:if>

    <!--part_gzcx Start-->
    <div class="part part_gzcx">
        <h2 class="title">
            <a href="http://cx.cqjt.gov.cn/main.html" target="_blank"> <img
                    src="${pageContext.request.contextPath}/images/hongquan.png"
                    style="float: left;"/><span style="float: left; color: #0074b6;">出行服务</span></a>
        </h2>
        <div class="con clearfix" id="part_gzcxCon">
            <!--gzcx_L Start-->
            <div class="gzcx_L">
                <!--column01 出行规划 Start-->
                <div class="column">
                    <h2>
                        <img src="${pageContext.request.contextPath}/images/chuxingjihua.jpg" width="238" height="35"
                             border="0"/>
                    </h2>
                    <div class="column-con" style="text-align: center;">
                        <!--
                        <iframe id="lxcx"
                            style="width: 230px; height: 152px; background-color: transparent; overflow: hidden;"
                            frameborder="0" scrolling="no" src=""></iframe>
                             -->
                        <ul>
                            <li>
                                <label>出发地：</label>
                                <input type="input" name="beginSite" id="beginSite" style="width: 150px;height: 21px;"/>
                            </li>
                            <li>
                                <label>目的地：</label>
                                <input type="input" name="endSite" id="endSite" style="width: 150px;height: 21px;"/>
                            </li>

                            <li>
                                <label></label>
                                <a href="javascript:queryLk();" id="serchBtn" class="btn-login" width="155px"
                                   height="29px">查&nbsp;&nbsp;询</a>
                            </li>
                            <li style="text-align: left;">
                                <p>* 输入起点和终点进行沿途线路信息查询</p>
                                <p>* 支持重庆范围内的规划</p>
                            </li>
                        </ul>
                    </div>
                </div>
                <!--column01 End-->
                <!--column02 Start-->
                <div class="column mobileDownloads mb_0">
                    <h2>
                        <img
                                src="${pageContext.request.contextPath}/images/zhangshangjiao.png"
                                width="238" height="35" border="0"/>
                    </h2>
                    <div class="column-con">
                        <ul>
                            <li onclick="locationHref()" style="cursor: pointer;">
                                <img src="${pageContext.request.contextPath}/images/Android.png"/>
                                <span>重庆交通APP</span>
                            </li>
                            <li><img
                                    src="${pageContext.request.contextPath}/images/ticket.png"/>
                                <span>联网售票</span></li>
                        </ul>
                    </div>
                </div>
                <!--column02 End-->
            </div>
            <!--gzcx_L End-->

            <!--gzcx_M Start-->
            <div class="gzcx_M">
                <ul class="tabs">
                    <li class="active"><a href="javascript:;">路况信息</a></li>
                    <li><a href="javascript:;">国省干道</a></li>
                    <li><a target="_blank"
                           href="http://cx.cqjt.gov.cn/cjcx/intercitybus.html?type=default">客运班车</a></li>
                    <li><a target="_blank"
                           href="http://cx.cqjt.gov.cn/cjcx/train.html?type=default">火车时刻</a></li>
                    <li class="last"><a target="_blank"
                                        href="http://cx.cqjt.gov.cn/cjcx/flight.html?type=leave">航空动态</a></li>
                </ul>
                <!--tabCon Start-->
                <div class="tabCon">
                    <!--tabPane01 Start-->
                    <div class="tabPane active">
                        <div class="scrollList" style="height: 300px; overflow: hidden;">
                            <ul id="roadCondition">
                            </ul>
                        </div>
                        <div
                                style="padding-left: 410px; height: 20px; line-height: 20px; margin-top: 5px;">
                            <a href="http://cx.cqjt.gov.cn/zjcx/main.html?loader=control"
                               target="_blank"><img
                                    src="${pageContext.request.contextPath}/images/gengduo.gif"
                                    width="37" height="11" border="0"/></a>
                        </div>
                    </div>
                    <!--tabPane01 End-->
                    <!--tabPane02 Start-->
                    <div class="tabPane">
                        <div class="scrollListGSGD" style="height: 300px; overflow: hidden;">
                            <ul id="roadConditionGSGD">
                            </ul>
                        </div>
                    </div>
                    <!--tabPane02  End-->
                </div>
                <!--tabCon End-->
            </div>
            <!--gzcx_M End-->
            <!--gzcx_R End-->
            <!-- 要放大显示的div -->
            <div class=" gzcx_R sideLink" id="gallery">

                <a href="${pageContext.request.contextPath}/images/weixin_traffic.jpg"
                   title="重庆交通  微信" class="i_tl1"
                   onMouseOver="this.className='i_tl1_';"
                   onMouseOut="this.className='i_tl1';">
                    <img src="${pageContext.request.contextPath}/images/weixin_traffic.jpg" alt=""
                         title="请扫描二维码关注重庆交通微信公众号" style="display: none"/>
                </a>

                <a href="http://e.weibo.com/u/2464831845?ref=http%3A%2F%2Fwww.cqjt.gov.cn%2FTLimages%2Fgbtop.html"
                   target="_blank" title="@重庆交通  新浪" class="i_tl2"
                   onMouseOver="this.className='i_tl2_';"
                   onMouseOut="this.className='i_tl2';" data-poptrox="ignore"></a>

                <a href="http://e.t.qq.com/cqjiaowei" target="_blank" style="display: none"
                   title="@重庆交通  腾讯" class="i_tl3"
                   onMouseOver="this.className='i_tl3_';"
                   onMouseOut="this.className='i_tl3';" data-poptrox="ignore"></a>

                <a href="http://cx.cqjt.gov.cn/bmfw/online.html"
                   title="重庆交通服务热线" class="i_tl4"
                   onMouseOver="this.className='i_tl4_';"
                   onMouseOut="this.className='i_tl4';" data-poptrox="ignore"></a>
                <a
                        href="http://www.fm955.com.cn/" target="_blank" title="重庆交通广播"
                        class="i_tl5" onMouseOver="this.className='i_tl5_';"
                        onMouseOut="this.className='i_tl5';" data-poptrox="ignore"></a>

            </div>
            <a  href="http://toupiao.www.gov.cn/100dudian/index.htm" target="_blank" title="群众办事百项堵点疏解行动" style="display: block;float: left;padding-left: 1px;"
            >
                <img src="${pageContext.request.contextPath}/images/index_100du.png" alt=""
                     style="width: 260px;"
                />

            </a>
            <!--gzcx_R End-->
        </div>
    </div>
    <!--part_gzcx End
            <!--办事服务 Start-->
    <div class="part part_gzcx">
        <h2 class="title">
            <a> <img
                    src="${pageContext.request.contextPath}/images/hongquan.png"
                    style="float: left;"/><span style="float: left; color: #0074b6;">办事服务</span></a>
        </h2>
        <div class="con clearfix">
            <!--自助服务 start-->
            <div class="gzcx_L" id="self_panel">
                <div class="column">
                    <h2>
                        <img src="${pageContext.request.contextPath}/images/dothing_back.png" width="238" height="35"
                             border="0"/>
                        <span style="position: relative;    top: -35px;left: 25px; color: #0074b6;">办事服务</span></a>
                    </h2>
                    <div class="column-con" style="text-align: center;">
                        <!--
                        <iframe id="lxcx"
                            style="width: 230px; height: 152px; background-color: transparent; overflow: hidden;"
                            frameborder="0" scrolling="no" src=""></iframe>
                             -->
                        <div class=" sideLink" id="self">

                            <a href="http://cx.cqjt.gov.cn/bmfw/onlineFillForm.html?sjzt=%E9%97%AE%E4%BA%8B"
                               title="问事" class="self_a index_ask"
                            >
                                <img src="${pageContext.request.contextPath}/images/index_ask.png" class="ask_img"/>
                            </a>

                            <a href="http://cx.cqjt.gov.cn/bmfw/onlineFillForm.html?sjzt=%E6%8A%95%E8%AF%89"
                               title="投诉" class="self_a index_ask"
                            >
                                <img src="${pageContext.request.contextPath}/images/index_cpt.png" class="ask_img"/>
                            </a>
                            <a href="http://cx.cqjt.gov.cn/bmfw/onlineFillForm.html?sjzt=%E6%8A%A5%E8%AD%A6"
                               title="报警" class="self_a index_ask"
                            >
                                <img src="${pageContext.request.contextPath}/images/index_police.png" class="ask_img"/>
                            </a>


                        </div>
                    </div>
                </div>
                <!--column01 End-->

            </div>
            <!--自助服务 End-->

            <!--gzcx_M Start-->
            <div class="gzcx_M" style="width: 396px;margin: 0px;">
                <ul class="tabs">
                    <li class="active"><a href="javascript:;">查询服务</a></li>
                    <li><a href="javascript:;">公告公示</a></li>

                </ul>
                <!--tabCon Start-->
                <div class="tabCon" style="height: auto">
                    <!--tabPane01 Start-->
                    <div class="tabPane active">
                        <div class="scrollList" style="height: 170px; overflow: hidden;">
                            <ul id="queryList">
                                <li><a target="_blank"
                                       href="http://zwfw.cq.gov.cn/icity/govservice/queryprogress?i=3"><b
                                        class="dot"></b>行政许可办理查询</a></li>
                                <li><a target="_blank"
                                       href="http://bs.cqjt.gov.cn:8000/cqnet//integrate/integrate!listTrroademploye.do"><b
                                        class="dot"></b>公路水运工程从业人员查询</a></li>
                                <li><a target="_blank"
                                       href="http://bs.cqjt.gov.cn:8000/cqnet//integrate/integrate!listQyxx.do"><b
                                        class="dot"></b>公路建设市场从业单位信息查询</a></li>
                                <li><a target="_blank"
                                       href="http://bs.cqjt.gov.cn:8000/cqnet//integrate/integrate!listXmxx.do"><b
                                        class="dot"></b>公路建设市场项目信息查询</a></li>
                                <li><a target="_blank"
                                       href="http://bs.cqjt.gov.cn:8000/cqnet//integrate/integrate!listUnitview.do"><b
                                        class="dot"></b>水运建设市场从业单位信息查询</a></li>
                                <li><a target="_blank"
                                       href="http://bs.cqjt.gov.cn:8000/cqnet//integrate/integrate!listProjectview.do"><b
                                        class="dot"></b>水运建设市场项目信息查询</a></li>
                            </ul>
                        </div>
                        <div
                                style="padding-left: 350px; height: 20px; line-height: 20px; margin-top: 5px;">
                            <a href="http://bs.cqjt.gov.cn:8000/cqnet/page/web/homePage.jsp?type=3"
                               target="_blank"><img
                                    src="${pageContext.request.contextPath}/images/gengduo.gif"
                                    width="37" height="11" border="0"/></a>
                        </div>
                    </div>
                    <div class="tabPane">
                        <div class="scrollList" style="height: 170px; overflow: hidden;">

                            <ul id="publicinfo">

                                <li><a target="_blank" href="http://zwfw.cq.gov.cn/icity/icity/bjgg?i=4"><b
                                        class="dot"></b>行政许可结果公示</a></li>
                                <li><a target="_blank"
                                       href="http://bs.cqjt.gov.cn:8000/cqnet/web/webdashboard!powerDashboardnew.do?orgId=500000ZF&itemType=CF"><b
                                        class="dot"></b>行政处罚裁量基准公示</a></li>
                                <li><a target="_blank"
                                       href="http://www.cqjt.gov.cn/openCatalog/init_/openCatalogList/01010902_1.html"><b
                                        class="dot"></b>交通行业招标公告</a></li>
                                <li><a target="_blank"
                                       href="http://www.cqjt.gov.cn/openCatalog/init_/openCatalogList/01010903_1.html"><b
                                        class="dot"></b>交通行业中标公告</a></li>
                                <li><a target="_blank"
                                       href="http://bs.cqjt.gov.cn:8000/cqnet/integrate/integrate!listQyxyxx.do"><b
                                        class="dot"></b>公路建设市场信用信息管理系统市级评价</a></li>
                                <li><a target="_blank"
                                       href="http://bs.cqjt.gov.cn:8000/cqnet/integrate/integrate!listJcjlxx.do"><b
                                        class="dot"></b>公路建设市场信用信息管理系统奖惩记录</a></li>
                            </ul>
                        </div>
                        <div
                                style="padding-left: 350px; height: 20px; line-height: 20px; margin-top: 5px;">
                            <a href="http://bs.cqjt.gov.cn:8000/cqnet/page/web/homePage.jsp"
                               target="_blank"><img
                                    src="${pageContext.request.contextPath}/images/gengduo.gif"
                                    width="37" height="11" border="0"/></a>
                        </div>
                    </div>
                    <!--tabPane01 End-->
                    <!--tabPane02 Start-->

                    <!--tabPane02  End-->
                </div>
                <!--tabCon End-->
            </div>
            <!--gzcx_M End-->
            <!--gzcx_R End-->
            <div class="focusBox1" style="margin: 0;height: 234px;margin-right: 10px;">
                <ul class="tabs" style="height: 33px;background: url(../images/titl_32.png) 0 0 repeat-x;border: none;">
                    <li class="peopleService inherit" style="float: left">便民服务</li>
                    <li class="peopleService inherit" style="float: right"><a
                            href="http://cx.cqjt.gov.cn/bmfw/card.html" target="_blank" style="color:#046598;">更多></a>
                    </li>
                </ul>
                <ul class="pic"
                    style="position: relative; width: 340px; height: 262px;">
                    <li><a target="_blank"
                           href="http://cx.cqjt.gov.cn/bmfw/queryAllLostInfo.html">
                        <img src="${pageContext.request.contextPath}/images/index_lost.png" style="height: 200px; "/>
                    </a></li>
                    <li><a target="_blank"
                           href="http://cx.cqjt.gov.cn/bmfw/card.html">
                        <img src="${pageContext.request.contextPath}/images/index_card.png" style="height: 200px; "/>
                    </a></li>
                    <li><a target="_blank"
                           href="http://cx.cqjt.gov.cn/bmfw/etc.html">
                        <img src="${pageContext.request.contextPath}/images/index_etc.png" style="height: 200px; "/>
                    </a></li>
                </ul>

                <ul class="num">
                    <li class=""><a>1</a><span></span></li>
                    <li class=""><a>2</a><span></span></li>
                    <li class=""><a>3</a><span></span></li>

                </ul>
            </div>


            <!--gzcx_R End-->

            <div class="bs_bottom">
                <div class="dothings ">
                    <ul class="tabs"
                        style="height: 33px;background: url(../images/titl_32.png) 0 0 repeat-x;border: none;">
                        <li class="peopleService inherit" style="float: left">办事指南</li>
                        <li class="peopleService inherit" style="float: right"><a target="_blank" href="http://zwfw.cq.gov.cn/icity/govservice/powerlist"
                                                                                  style="color:#046598;">更多></a></li>
                        <div class="things_table">
                            <div class="things_link">
                                <div class="things_row">
                                    <div class="things_cell">
                                        <a target="_blank" style="color:#3e92c5;"
                                           href="http://zwfw.cq.gov.cn/icity/govservice/banlixiang?code=0102924000&name=%E6%B8%AF%E5%8F%A3%E7%BB%8F%E8%90%A5%E8%AE%B8%E5%8F%AF&orgcode=5000000080&orgname=%E5%B8%82%E4%BA%A4%E5%A7%94&object=0,1&agreetime=0&type=XK">港口经营许可</a>
                                    </div>
                                    <div class="things_cell">
                                        <a target="_blank" style="color:#3e92c5;"
                                           href="http://zwfw.cq.gov.cn/icity/govservice/banlixiang?code=0102682003&name=%E5%B8%82%E5%86%85%E6%B0%B4%E8%B7%AF%E5%AE%A2%E8%BF%90%E7%BB%8F%E8%90%A5%E8%AE%B8%E5%8F%AF&orgcode=5000000080&orgname=%E5%B8%82%E4%BA%A4%E5%A7%94&object=0,1&agreetime=0&type=XK">市内水路客运经营许可</a>
                                    </div>
                                    <div class="things_cell">
                                        <a target="_blank" style="color:#3e92c5;"
                                           href="http://zwfw.cq.gov.cn/icity/govservice/banlixiang?code=0102100000&name=%E8%88%B9%E8%88%B6%E6%B3%95%E5%AE%9A%E6%A3%80%E9%AA%8C&orgcode=5000000080&orgname=%E5%B8%82%E4%BA%A4%E5%A7%94&object=0,1&agreetime=0&type=XK">船舶法定检验</a>
                                    </div>
                                </div>
                                <div class="things_row">
                                    <div class="things_cell">
                                        <a target="_blank" style="color:#3e92c5;"
                                           href="http://zwfw.cq.gov.cn/icity/govservice/banlixiang?code=0102109000&name=%E7%BB%8F%E8%90%A5%E6%B8%AF%E5%8F%A3%E7%90%86%E8%B4%A7%E4%B8%9A%E5%8A%A1%E8%AE%B8%E5%8F%AF&orgcode=5000000080&orgname=%E5%B8%82%E4%BA%A4%E5%A7%94&object=0,1&agreetime=0&type=XK">经营港口理货业务许可</a>
                                    </div>
                                    <div class="things_cell">
                                        <a target="_blank" style="color:#3e92c5;"
                                           href="http://zwfw.cq.gov.cn/icity/govservice/banlixiang?code=0102098001&name=%E8%88%B9%E5%91%98%E6%B3%A8%E5%86%8C%E5%8F%8A%E6%9C%8D%E5%8A%A1%E7%B0%BF%E7%AD%BE%E5%8F%91&orgcode=5000000080&orgname=%E5%B8%82%E4%BA%A4%E5%A7%94&object=0,1&agreetime=0&type=XK">船员注册及服务簿签发</a>
                                    </div>
                                    <div class="things_cell">
                                        <a target="_blank" style="color:#3e92c5;"
                                           href="http://zwfw.cq.gov.cn/icity/govservice/banlixiang?code=0102091001&name=%E9%80%9A%E8%88%AA%E6%B0%B4%E5%9F%9F%E5%B2%B8%E7%BA%BF%E5%AE%89%E5%85%A8%E8%AE%B8%E5%8F%AF&orgcode=5000000080&orgname=%E5%B8%82%E4%BA%A4%E5%A7%94&object=0,1&agreetime=0&type=XK">通航水域岸线安全许可</a>
                                    </div>
                                </div>
                                <div class="things_row">
                                    <div class="things_cell">
                                        <a target="_blank" style="color:#3e92c5;"
                                           href="http://zwfw.cq.gov.cn/icity/govservice/banlixiang?code=0102076000&name=%E5%9B%BD%E9%99%85%E9%81%93%E8%B7%AF%E8%BF%90%E8%BE%93%E7%BB%8F%E8%90%A5%E8%AE%B8%E5%8F%AF&orgcode=5000000080&orgname=%E5%B8%82%E4%BA%A4%E5%A7%94&object=1&agreetime=0&type=XK">国际道路运输经营许可</a>
                                    </div>
                                    <div class="things_cell">
                                        <a target="_blank" style="color:#3e92c5;"
                                           href="http://zwfw.cq.gov.cn/icity/govservice/banlixiang?code=0102068002&name=%E9%AB%98%E9%80%9F%E5%85%AC%E8%B7%AF%E8%B7%AF%E6%94%BF%E8%AE%B8%E5%8F%AF&orgcode=5000000080&orgname=%E5%B8%82%E4%BA%A4%E5%A7%94&object=1&agreetime=0&type=XK">高速公路路政许可</a>
                                    </div>
                                    <div class="things_cell">
                                        <a target="_blank" style="color:#3e92c5;"
                                           href="http://zwfw.cq.gov.cn/icity/govservice/banlixiang?code=0102049000&name=%E5%85%AC%E8%B7%AF%E5%85%BB%E6%8A%A4%E8%B5%84%E8%B4%A8%E8%AE%B8%E5%8F%AF&orgcode=5000000080&orgname=%E5%B8%82%E4%BA%A4%E5%A7%94&object=1&agreetime=0&type=XK">公路养护资质许可</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </ul>
                </div>
                <div class="opendata">
                    <ul class="tabs"
                        style="height: 33px;background: url(../images/titl_32.png) 0 0 repeat-x;border: none;">
                        <li class="peopleService inherit" style="float: left">数据开放</li>
                        <li class="peopleService inherit" style="float: right"><a target="_blank"
                                                                                  href="${pageContext.request.contextPath}/publicData.jsp"
                                                                                  style="color:#046598;">更多></a></li>
                    </ul>
                    <div style="overflow: hidden;" id="public_table">
                        <%-- <div class="table_row bold">
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;"
                                    href="http://cx.cqjt.gov.cn/zjcx/main.html?loader=condition">高速路况</a>
                             </div>
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;"
                                    href="http://cx.cqjt.gov.cn/zjcx/main.html?loader=service">服务区</a>
                             </div>
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;"
                                    href="http://cx.cqjt.gov.cn/zjcx/main.html?loader=tollgate">收费站</a>

                             </div>
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;"
                                    href="http://cx.cqjt.gov.cn/cjcx/intercitybus.html?type=default">客运班车</a>

                             </div>
                         </div>
                         <div class="table_row bold">
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;"
                                    href="http://cx.cqjt.gov.cn/cjcx/train.html?type=default">火车时刻</a>
                             </div>
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;"
                                    href="http://cx.cqjt.gov.cn/cjcx/flight.html?type=leave">航班动态</a>
                             </div>
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;"
                                    href="http://cx.cqjt.gov.cn/cjcx/ship.html?type=default">船期查询</a>

                             </div>
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;"
                                    href="http://cx.cqjt.gov.cn/bmfw/queryAllLostInfo.html">失物招领</a>

                             </div>
                         </div>
                         <div class="table_row bold">
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;"
                                    href="http://cx.cqjt.gov.cn/bmfw/airplane.html">机票售票点</a>
                             </div>
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;" href="http://cx.cqjt.gov.cn/bmfw/bus.html">客运售票点</a>
                             </div>
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;"
                                    href="http://cx.cqjt.gov.cn/bmfw/driving.html">驾校信息</a>

                             </div>
                             <div class="table_cell">
                                 <a target="_blank" style="color:#3B687E;" href="http://cx.cqjt.gov.cn/bmfw/etc.html">ETC办理点查询</a>

                             </div>
                         </div>--%>
                    </div>
                </div>
                <div style="clear: both"></div>
            </div>
        </div>

    </div>
    <!--办事服务 End

		<c:if test="${subjectNews.IFRAME_URL ne null }">
			<div class="part banner"
				style="width: 1024px; height: 60px; overflow: hidden;">
				<iframe scrolling="no" width="1024px" height="60px" frameborder="0"
					id="subjectFrame" name="subjectFrame" src="" class="bannerImg"></iframe>
			</div>
		</c:if>
		part_wsbs Start-->
    <div class="part part_wsbs" style="display: none;">
        <div style="padding: 0; margin: 0; height: 43px;">
            <a href="http://bs.cqjt.gov.cn:8000/cqnet/page/web/homePage.jsp"
               target="_blank"> <img
                    src="${pageContext.request.contextPath}/images/wangshangbanshi.jpg"
                    width="1024" height="43" border="0"/></a>
        </div>
        <iframe id="wsbs" src="" style="width: 1024px; height: 278px;"
                scrolling="no" frameborder="0"></iframe>
    </div>
    <!--part_wsbs End-->
    <!--part_hdjl Start-->
    <div class="part part_hdjl">
        <div style="padding: 0; margin: 0; height: 43px;">
            <img
                    src="${pageContext.request.contextPath}/images/hudongjiaoliu.jpg"
                    width="1024" height="43" border="0"/>
        </div>
        <div class="con clearfix">
            <div class="hdjl_R clearfix">
                <div class="subSection" id="sendIdea">
                    <div
                            style="float: left; width: 150px; height: 40px; line-height: 20px; padding: 40px 0 0 90px;">
                        <div style="height: 100%;overflow: hidden;"><a target="_blank"
                                                                       href="${pageContext.request.contextPath}/topics/${topic.OPTION_ID}.html">${topic.OPTION_TITLE }</a>
                        </div>
                    </div>
                    <a target="_blank"
                       href="${pageContext.request.contextPath}/topics/list_1.html"
                       style="float: right; padding-right: 20px; padding-top: 3px; color: #306297;">更多</a>
                </div>
                <div class="subSection">
                    <div
                            style="float: left; width: 150px; height: 40px; line-height: 20px; padding: 36px 0 0 90px;">
                        <!-- <a target="_blank"
								href="${pageContext.request.contextPath}/questionaires/${questionaire.QTE_ID}.html">${questionaire.QTE_TITLE }</a>
								 -->
                        <a target="_blank"
                           href="${pageContext.request.contextPath}/Questionaire.do?action=questionList&QTEID=${questionaire.QTE_ID}">${questionaire.QTE_TITLE }</a>
                    </div>
                    <a target="_blank"
                       href="${pageContext.request.contextPath}/questionaires/list_1.html"
                       style="float: right; padding-right: 20px; padding-top: 3px; color: #306297;">更多</a>
                </div>
            </div>
            <!--hdjl_L Start
				<div class="hdjl_L subSection">
					<h3 class="title">
						<span>最近访谈</span>
					</h3>
					<c:import url="/index.do?action=getUpToDateInterview"></c:import>
				</div>-->

            <div class="interview">
                <dl class="tab_i clearfix ">
                    <dt class="cur_i" style="margin-left: 15px;">重庆交通访谈</dt>
                    <dt>市政府访谈</dt>


                </dl>
                <!--tabCon_01 Start-->
                <div class="tabCon ">
                    <dl>
                        <c:import url="/index.do?action=getUpToDateInterview"></c:import>
                    </dl>
                </div>
                <!--tabCon_01 End-->
                <!--tabCon_02 Start-->
                <div class="tabCon" style="display: none;">
                    <dl style="margin-top: 10px;">
                        <div class="subCon">
                            <img src="http://www.cq.gov.cn/zxft/images/zxft.jpg" class="photo_onlineInterview">
                            <h4 style="overflow: hidden;" title="主城区背街小巷市容环境整治及城市公共厕所建设">
                                <b>主题：</b>主城区背街小巷市容环境整治及城市公共厕所建设
                            </h4>
                            <ul>
                                <li><b>时间：</b>2015-6-15 15:00:00</li>

                                <li><b>嘉宾：</b> 郑如彬 , 秦玉忠 , 罗 利</li>
                                <li class="summary"><b class="titleName">摘要：</b>
                                    <div class="overflowHidden" title="">
                                        市委、市政府决定，2015年将集中力量办好事关群众切身利益的25件重点民生实事，“317个主城区背街小巷市容环境整治及362座城市公共厕所建设”是其中之一。
                                    </div>
                                    <div style="clear: both; height: 0;"></div>
                                </li>
                            </ul>
                            <div>
                                <a
                                        href="http://cq.gov.cn/assistant-interview-webapp/upc/interviewList.action"
                                        target="_blank" style="float: right;"><img
                                        src="${pageContext.request.contextPath}/images/gengduo.gif"
                                        width="37" height="11" border="0"></a>
                            </div>
                        </div>
                    </dl>
                </div>
                <!--tabCon_02 End-->
            </div>


            <!--hdjl_L End-->
            <!--hdjl_M Start-->
            <div class="hdjl_M  subSection">
                <h3 class="title">
                    <span>领导信箱</span>
                </h3>
                <div class="subCon">
                    <ul id="mailList">
                    </ul>
                    <div
                            style="padding-left: 300px; height: 13px; line-height: 13px; padding-top: 12px;">
                        <a href="${pageContext.request.contextPath}/mailbox.html"
                           target="_blank"><img
                                src="${pageContext.request.contextPath}/images/gengduo.gif"
                                width="37" height="11" border="0"/></a>
                    </div>
                </div>
            </div>
            <!--hdjl_M End-->
        </div>
    </div>
    <!--part_hdjl End-->
    <!--part_cywz Start-->
    <%@ include file="/WEB-INF/pages/common/friendLink.jsp" %>
    <!--part_cywz End-->
    <!--footer Start-->
    <%@ include file="/WEB-INF/pages/common/footer.jsp" %>
    <!--footer End-->
</div>

</body>

</html>
