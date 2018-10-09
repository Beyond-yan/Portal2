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
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $(".submit_btn").click(function () {

                var scope=$('input:radio[name="scope"]:checked').val();
                if(scope=="1"){
                    scope="010101";
                }else if(scope=="2"){
                    scope="010116_01010902_01010903_01010904_010112";
                }else if(scope=="3"){
                    scope="010102_" +
                        "010106_" +
                        "01010901_" +
                        "0101081_" +
                        "010103_" +
                        "010108_" +
                        "010111_" +
                        "010104_" +
                        "010117_" +
                        "010120_" +
                        "010118_" +
                        "0101082_" +
                        "010115_" +
                        "010105_" +
                        "010107_" +
                        "010107_" +
                        "01010811_" +
                        "01010812_" +
                        "01010813_" +
                        "010113";
                }else if(scope=="4") {
                    scope="01011004_" +
                        "01011003_"+
                        "01011002_"+//*
                        "01011005_"+
                        "01011001"
                }
                var url="http://www.cqjt.gov.cn/searchnewslist/"+scope+"_1.html?word="+encodeURIComponent(encodeURIComponent($(".keyword").val()))+"&wordPosition="+$('input:radio[name="position"]:checked').val()+"&orderby="+$('input:radio[name="sort"]:checked').val();
                var  startTime=$("#startTime").val();
                var  endTime=$("#noticeEndTime").val();
                if(startTime!=""&&endTime!=""){
                    url+="&startTime="+startTime+"&endTime="+endTime;
                }
                //console.info(url);
                window.location.href=url;

            });
        });
    </script>
    <style>

        .newsDetail{
            text-align: center;
        }
        .condition_name{
            width: 100px;
        }
        .condition_value{
            float: left;
        }
        .condition_name{
            float: left;
        }
        .condition_li{
            overflow: hidden;
            width: 435px;
            margin: 0 auto;
            margin-top: 10px;
        }
        label{
            margin-left: 5px;
        }
        .submit_btn{
            height: 32px;
            width: 100px;
            border:1px;
            border-radius: 5px;
            color: #fff;
            background: #0d5da6;
            margin: 0 auto;
            margin-top: 20px;
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
        <div class="content">
            <div class="clearfix">
                <!--mainList Start-->
                <div class="mainList" id="lH">
                    <div class="breadCutNav">
                        <b class="icon_home"></b> <span>当前位置：</span> <a
                            href="${pageContext.request.contextPath}/index.html">首页</a> <span>
								&gt;</span>

                        <a href="javascript:;" class="cur">高级搜索</a>
                    </div>
                    <div class="blank_7px"></div>
                    <div class="newsDetail">
                        <h2 style="font-size: 18px;">高级搜索</h2>
                        <div class="search_condition">
                            <li class="condition_li">
                                <p class="condition_name">
                                    关键字
                                </p>
                                <div class="condition_value">
                                    <input type="text" style="height:23px;width: 250px;"  class="keyword"/>
                                </div>
                            </li>
                            <li class="condition_li">
                                <p class="condition_name">
                                    排序字段
                                </p>
                                <div class="condition_value">
                                    <input type="radio" name="sort" value="0" style="vertical-align:middle; margin-top:-2px;" checked /><label>日期</label>
                                    <input type="radio" name="sort" value="1" style="vertical-align:middle; margin-top:-2px; "/><label >相关度</label>

                                </div>
                            </li>

                            <li class="condition_li">
                                <p class="condition_name">
                                    时间范围
                                </p>
                                <div class="condition_value">
                                    <input class='notice-time' type='text' style="height:23px;" id='startTime'  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'noticeEndTime\')}'})">
                                    到
                                    <input type="text" class="notice-time" style="height:23px;" id='noticeEndTime'  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})"/>

                                </div>
                            </li>

                            <li class="condition_li">
                                <p class="condition_name">
                                    关键字位置
                                </p>
                                <div class="condition_value">
                                    <input type="radio" name="position" value="1" style="vertical-align:middle; margin-top:-2px; " /><label>标题</label>
                                    <input type="radio" name="position" value="2" style="vertical-align:middle; margin-top:-2px; "/><label >正文</label>
                                    <input type="radio" name="position" value="0" style="vertical-align:middle; margin-top:-2px; " checked/><label >全部</label>

                                </div>
                            </li>
                            <li class="condition_li">
                                <p class="condition_name">
                                    搜索范围
                                </p>
                                <div class="condition_value">
                                    <input type="radio" name="scope" value="1" style="vertical-align:middle; margin-top:-2px; " /><label>交通新闻
                                </label>
                                    <input type="radio" name="scope" value="2" style="vertical-align:middle; margin-top:-2px; "/><label >公告公示</label>
                                    <input type="radio" name="scope" value="3" style="vertical-align:middle; margin-top:-2px; "/><label >政务公开</label>
                                    <input type="radio" name="scope" value="4" style="vertical-align:middle; margin-top:-2px; "/><label >组织机构</label>
                                    <input type="radio" name="scope" value="-1" style="vertical-align:middle; margin-top:-2px; " checked/><label >全部</label>

                                </div>
                            </li>
                            <div class="submit_btn">
                                搜索
                            </div>

                        </div>
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
</html>