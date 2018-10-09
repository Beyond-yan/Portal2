<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="${newsKeyWord}">
<meta name="description" content="${newsTitle}">
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
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
    <link href="//cdn.bootcss.com/jquery-confirm/3.1.0/jquery-confirm.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery-confirm/3.1.0/jquery-confirm.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
    <script
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript">
    $(function () {
        getPublicData();
        $(".href_filter").click(function () {
            var oldhref= this.href;
            var lowUrl = this.href.toLowerCase();
            var flag = true;
            if (lowUrl.indexOf(".cq") >= 0) {
                flag = false;
            }
            if(lowUrl.indexOf("http")<0){
                flag = false;
			}
            if (flag) {
                $.confirm({
                    title: '提示',
                    content: '您访问的链接即将离开重庆交通委员会政府网站，是否继续？',
                    icon: 'glyphicon glyphicon-heart',
                    buttons: {
                        ok: {
                            text: '继续访问',
                            btnClass: 'btn-primary',
                            action: function(){
                               window.location.href=oldhref;
                            }
                        },
                        cancel: {
                            text: '放弃',
                            btnClass: 'btn-primary',
                            action: function(){
                                // button action.
                            }
                        },
                    }
                });
                //OtherLinkOpen();
                return false;
            }
            window.location.href=oldhref;
        });

    });

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
                            html+=" <div class=\"download_row\">";
                        }
                        html+=" <div class=\"download_cell\"> <p style=\"margin:5px 0 0 25px; height:24px;font-size: 13px\">"+content[i].newsTitle+"</p> <div> <img src=\"./images/query.png\" style=\"margin-left: 25px;margin-top: -4px;\"/> <a target=\"_blank\" style=\"color:#3B687E;text-decoration:underline;\"href=\""+content[i].subNewsTitle+"\">在线查询</a>";
					console.info(content[i].newsContent.length);
                        if(content[i].newsContent!=null&&content[i].newsContent.length>20){
                        	html+="<img src=\"./images/down.png\" style=\"margin-left: 33px;margin-right: 8px;margin-top: -4px;\"/><a target=\"_blank\" style=\"color:#3B687E;text-decoration:underline;\"href=\"http://www.cqjt.gov.cn/articles/01010102/"+content[i].newsID+".html\">数据下载</a>";
						}else{
                            html+="<img src=\"./images/down.png\" style=\"margin-left: 33px;margin-right: 8px;margin-top: -4px;\"/><a target=\"_blank\" style=\"text-decoration:underline; color: #888888;background-color: transparent;\">数据下载</a>";
                        }
						html+=	"</div></div>";
                    }
                    if(html!=""){
                        html+="</div>";
                    }
                    $(".download_table").append(html);
                }
            });
    }
</script>
<style>

.contenttext {
	font-family: 宋体,SimSun;
	text-align:left;
    font-size: 16px;
}
	.blank_7px{
		height: 29px;
		padding-left: 18px;
		font: bold 14px/29px \5b8b\4f53;
		color: #fff;
		line-height: 29px;
		background-color: #027fd8;
	}
.kfy h3 {
    height: 50px;
    display: inline-block;
    text-align: center;
    width: 100%;
    margin: 40px 0 10px 0;
}
.kfy p {
    line-height: 20px;
    text-indent: 2em;
}
    .btn-primary{
        height: 30px;
        width: 90px;
    }
    .kfy{
        border: 1px solid #a8c1da;
        border-bottom: 0px solid #a8c1da;
        text-align: justify;
        padding: 20px;
    }
	.download_cell{
		float: left;
		border: 1px solid #a8c1da;
		border-radius: 5px;
		width: 228px;
		text-align: left;
		background:rgb(242, 242, 242);
		overflow: hidden;
		margin-left: 25px;
		margin-bottom: 15px;
	}
	.download_row{
		overflow: hidden;
	}
</style>
<title>重庆市交通委员会</title>
</head>

<body onload="loadExternalResource()"  topmargin="0"ondragstart="return true">
<!-- 禁止复制 -->
<!-- <body onload="loadExternalResource()"  topmargin="0" oncontextmenu="return false"
	ondragstart="return false" onselectstart ="return false"
	oncopy="document.selection.empty()" onbeforecopy="return false"> -->
	<div class="bg_w1284">
		<div class="wrap">
			<!--header Start-->
			<c:import url="/index.do?action=getHeader"></c:import>
			<!--header End-->
			<!--content Start-->
			<div class="content" style="width: 1040px;margin-top: 7px;">
				<div class="clearfix">
					<!--mainList Start-->
					<div class="mainList" style="width: 1040px" id="lH">
						<div class="breadCutNav">
							<b class="icon_home"></b> <span>当前位置：</span> <a
								href="${pageContext.request.contextPath}/index.html">首页</a> <span>
								&gt;</span>

								<a href="javascript:;" class="cur">数据开放</a>
						</div>
						<div class="blank_7px" ><p>数据开放</p></div>
						<div class="kfy">
							 <h3><img src="${pageContext.request.contextPath}/images/ysj.jpg"></h3>
							   <p>出行云是由交通运输部采用政企合作模式建设的，基于公共云服务的综合交通出行服务数据开放、管理与应用平台。出行云平台旨在汇聚开放综合交通出行优质信息资源，支撑技术企业、科研机构创新开发应用，营造综合交通出行信息服务"众创"发展环境，促进交通运输行业科学决策与管理创新，为社会公众提供高品质、差异化、多层次的综合交通出行信息服务。我厅作为该项目的第一批试点单位，基于云平台向社会开放、共享了部分业务数据，提升了我省交通信息服务水平。
						</div>
						<div class="newsDetail" style="border-top:0px;padding-left: 0px;">
                            <div style="border-bottom: 1px solid #a8c1da;margin:0 20px 0 20px"> </div>
							<p style="text-align: left;margin-left: 25px;">其他数据开放</p>
							<div class="download_table">
								<%--<div class="download_row">
									<div class="download_cell">
										<p style="margin:5px 0 0 10px; height:24px;">高速路况</p>
										<div>
											<img src="./images/query.png" style="margin-left: 10px;"/>
											<a target="_blank" style="color:#3B687E;"
											   href="http://cx.cqjt.gov.cn/zjcx/main.html?loader=condition">在线查询</a>
											<img src="./images/down.png" style="margin-left: 10px;"/>
											<a target="_blank" style="color:#3B687E;"
											   href="http://cx.cqjt.gov.cn/zjcx/main.html?loader=condition">数据下载</a>
										</div>
									</div>

								</div>--%>

							</div>
						</div>

					<!--mainList End-->
				</div>
					<div style="clear: both;" />
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
</html>