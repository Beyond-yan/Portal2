<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/vali/css/vali.css" />
<title></title>

</head>

<body style="background: white;">
	<!-- <body style="background: white;" onload="loadExternalResource()"> -->
	<!-- 禁止复制 -->
	<!--  <body style="background: white;" onload="loadExternalResource()"  topmargin="0" oncontextmenu="return false" 
	ondragstart="return false" onselectstart ="return false" 
	oncopy="document.selection.empty()" onbeforecopy="return false">  -->

	<div class="mainList" id="lH" style="width: 100%">
		<div class="breadCutNav">
			<b class="icon_home"></b> <span>当前位置：</span> <a
				href="${pageContext.request.contextPath}/index.html" target="top">首页</a>
			<span> &gt;</span><a
				href="${pageContext.request.contextPath}/openCatalog/init_/openCatalog/f1e8c2db-9e92-4a1e-a1cb-48569fa7f308.html"
				target="_top">政务公开</a><span> &gt;</span>${topName}
		</div>
		<div class="blank_7px"></div>
		<div class="newsDetail">
			<h2>${newsTitle}</h2>
			<p class="dotLine_2px"></p>
			<ul class="tip clearfix">
				<li class="al_l">更新时间：${modifyDate}</li>
			</ul>
			<div>${newsContent}</div>
			<h3 class="commentTitle">
				<b class="i_comment"></b>我要发表意见：
			</h3>
			<form id="commentForm">
				<table width="700" align="center" class="commentForm">
					<tr>
						<td width="70">用&nbsp;户&nbsp;名：</td>
						<td><input type="text" id="userName" name="userName"></td>
						<td>电话：</td>
						<td><input type="text" id="userTel" name="userTel"
							alt="number-N" phone="t" vali></td>
					</tr>
					<tr>
						<td>身份证号码：</td>
						<td><input type="text" id="userDept" name="userDept"
							idcard="t" vali></td>
						<td>邮箱：</td>
						<td><input type="text" id="userMail" name="userMail"
							alt="mail-N" email="t" vali></td>
					</tr>
					<tr>
						<td>意见内容：</td>
						<td colspan="3" style="width: 600px; height: 210px;"><textarea
								id="myEditor" style="width: 100%; height: 100%;"></textarea> <input
							type="hidden" id="topicID" value='${newsID}'></td>
					</tr>
					<tr>
						<td align="center" colspan="4"><img title="看不清，点击换一张"
							id="validateCode" name="validatecode" width="67" height="26"
							onclick="reloadcode()"
							style="cursor: pointer; float: right; margin-top: 11px; margin-right: 10px;"
							src="${pageContext.request.contextPath}/common/validate.jsp">
							<input id="validateBox" name="validateBox" type="text"
							style="float: right; width: 67px; height: 23px; margin-top: 10px; margin-right: 10px;">
							<input type="button" value="提交" class="btn_submit"
							onclick="insertTextNews();"
							style="float: right; margin-right: 10px;"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
	<script>
		function insertTextNews() {
			var topicID = $("#topicID").val();
			var userName = encodeURIComponent($.trim($("#userName").val()));
			var userTel = encodeURIComponent($.trim($("#userTel").val()));
			var userDept = encodeURIComponent($.trim($("#userDept").val()));
			var userMail = encodeURIComponent($.trim($("#userMail").val()));
			var userCommend = encodeURIComponent($.trim($('#myEditor').val()));
			var isSubmit=$.vali.form("#commentForm");
			var validateBox = $("#validateBox").val();
			if(!isSubmit){
				alert("请完善个人信息！");
				return false;
			} if (userName == "") {
				alert("请填写用户名");
				return false;
			} if (userTel == "") {
				alert("请填写电话");
				return false;
			} if (userDept == "") {
				alert("请填写单位名称");
				return false;
			} if (userMail == "") {
				alert("请填写邮箱");
				return false;
			} if (userCommend == "") {
				alert("请填写内容");
				return false;
			} if (validateBox == "") {
				alert("请输入验证码!");
				return false;
			} if (confirm("感谢您的参与，是否确定提交该条意见？") == true) {
				$.ajax({
					url : "${pageContext.request.contextPath}/topics/saveOpinion.html",
					cache : false,
					type : "post",
					dataType : "text",
					data : {
						topicID : topicID,
						userName : userName,
						userTel : userTel,
						userDept : userDept,
						userMail : userMail,
						userCommend : userCommend,
						validateCode : validateBox,
						modifyUser : 'FLFG'
					},
					success : function(content) {
						var pageDate = eval("("+content+")");
						var status = pageDate.status;
						if(status == 0){
							alert("提交成功，感谢您的参与！");
							// window.location.href = window.location.href;
							reset();
						}else{
							alert("提交失败，验证码不正确！");
							reloadcode();
						}
					},
					error : function() {
						alert("提交失败！");
					}
				});
			}
		}
	
		function reset() {
			$("#userName").val('');
			$("#userTel").val('');
			$("#userDept").val('');
			$("#userMail").val('');
			$("#myEditor").val('');
			$("#validateBox").val('');
		}
		
		function reloadcode() {
			document.getElementById("validateCode").src = "${pageContext.request.contextPath}/common/validate.jsp?r="
					+ Math.random();
		}
		
		//表单验证
		$(function() {
			(function($){var vali_current=0,vali_size=-1,_err=0,_findvali=new Array();$.extend($.fn,{vali:function(A){if(!this.is("form")){console.error("Not is Form");return false}else{vali_size++}var _,_id,_o,_v,_x,_y,_Rep,_r,_e,_s,_top,_left,_prompt,_validate,_custom,_c,_vali,class_error,class_success;_custom=new Array();_r=new Array();_r[0] = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;_r[1]=/^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;_prompt=$.vali.prompt;_c=function(){this._ele=null;this._fun=null;this._err=null};_validate=function(){this._email=false;this._phone=false;this._max=false;this._min=false;this._minmax=false;this._bank=false;this._only=false;this._must=false;this._first_must=false;this._first_cannot=false;this._number=false;this._idcard=false;this._equally=false;this._cannot=false;this._custom=false;this._date=false;this._http=false;this._number_max=false;this._number_min=false;this._number_minmax=false;this._time=false;this._datetime=false;var _this=this;this.setAttr=function(obj){_this._email=obj.attr("email");_this._phone=obj.attr("phone");_this._max=obj.attr("max");_this._min=obj.attr("min");_this._bank=obj.attr("bank");_this._only=obj.attr("only");_this._must=obj.attr("must");_this._first_must=obj.attr("first_must");_this._first_cannot=obj.attr("first_cannot");_this._number=obj.attr("number");_this._idcard=obj.attr("idcard");_this._equally=obj.attr("equally");_this._cannot=obj.attr("cannot");_this._custom=obj.attr("custom");_this._date=obj.attr("date");_this._http=obj.attr("http");_this._number_max=obj.attr("number_max");_this._number_min=obj.attr("number_min");_this._number_minmax=obj.attr("number_minmax");_this._minmax=obj.attr("minmax");_this._time=obj.attr("time");_this._datetime=obj.attr("datetime")}};_=$(this),_id=0,_err=0,_e="",_s="";_id=vali_current;_.attr("vali_size",vali_size);var _A={"vali":"vali","class_error":"vali_error","class_success":"vali_success","disparityH":4,"disparityW":0,"prompt_width":true,"prompt_log":false,"success_show":false,"icon_show":true,"icon_img":false,"icon_img_eu":"","icon_img_su":"","decimal_places":2,"custom":null,"window_show":true,"pe":false,"ps":false};$.extend(_A,A);class_error=_A["class_error"];class_success=_A["class_success"];if(_A["icon_show"]){_e=' <i class="fa fa-remove"></i>';_s=' <i class="fa fa-check"></i>';if(_A["icon_img"]){_e='<img src="'+_A["icon_img_eu"]+'" >';_s='<img src="'+_A["icon_img_su"]+'" >'}}var init,code,input_focus,input_blur,prompt_log,validate_error,validate_success,se_switch,err_validate,custom;se_switch=function(p,i){if(i){p.removeClass("error");p.addClass("success")}else{p.removeClass("success");p.addClass("error")}};prompt_log=function(f){if(_A["prompt_log"]){var date=new Date();console.log(date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"-->"+f)}};init=function(obj){_id++;vali_current++;_o=obj;_top=obj.offset().top;_left=obj.offset().left;_x=obj.width();_y=obj.height();_top=parseInt(_top)+parseInt(vali_fun.getPaddingTB(obj));
		    _x=parseInt(_x)+parseInt(vali_fun.getPaddingLR(obj))};code=function(){_o.attr("valiid",+_id);$("body").append('<label class="validate_label_prompt" id="valiid'+_id+'"></label>');$("#valiid"+_id).css({"top":(_top-1+_y+_A["disparityH"]+1)+"px","left":(_left-1+_A["disparityW"]+1)+"px"});if(_A["prompt_width"]){$("#valiid"+_id).css("width",_x)}};input_focus=function(id){var prompt=$("#valiid"+id);_o.focus(function(){_o=$(this);_o.removeClass(class_error).removeClass(class_success);prompt.hide();if(_A["pe"]){var pe=_o.attr("pe");if(vali_fun.isChackUn(pe)){prompt_log("ps is null")}else{$(pe).hide()}}if(_A["ps"]){var ps=_o.attr("ps");if(vali_fun.isChackUn(ps)){prompt_log("ps is null")}else{$(ps).hide()}}_top=_o.offset().top;_left=_o.offset().left;_y=_o.height();_top=parseInt(_top)+parseInt(vali_fun.getPaddingTB(_o));prompt.css({"top":(_top-1+_y+_A["disparityH"]+1)+"px","left":(_left-1+_A["disparityW"]+1)+"px"})})};input_blur=function(id){var validate=new _validate();validate.setAttr(_o);var prompt=$("#valiid"+id);_o.blur(function(){_o=$(this);_err=0;_v=$(this).val();if(err_validate(validate,prompt)==false){_o.addClass(class_error);prompt_log("input_blur is Error")}if(_err==0){_o.addClass(class_success);validate_success(prompt);return true}else{return false}})};err_validate=function(validate,prompt){validate._email?input_email(prompt):prompt_log("Not");if(_err!=0){return false}validate._phone?input_phone(prompt):prompt_log("Not");if(_err!=0){return false}validate._max?input_max(prompt,validate._max):prompt_log("Not");if(_err!=0){return false}validate._min?input_min(prompt,validate._min):prompt_log("Not");if(_err!=0){return false}validate._bank?input_bank(prompt):prompt_log("Not");if(_err!=0){return false}validate._only?input_only(prompt,validate._only):prompt_log("Not");if(_err!=0){return false}validate._must?input_must(prompt,validate._must):prompt_log("Not");if(_err!=0){return false}validate._first_must?input_first_must(prompt,validate._first_must):prompt_log("Not");if(_err!=0){return false}validate._first_cannot?input_first_cannot(prompt,validate._first_cannot):prompt_log("Not");if(_err!=0){return false}validate._number?input_number(prompt):prompt_log("Not");if(_err!=0){return false}validate._idcard?input_idcard(prompt):prompt_log("Not");if(_err!=0){return false}validate._equally?input_equally(prompt,validate._equally):prompt_log("Not");if(_err!=0){return false}validate._cannot?input_cannot(prompt,validate._cannot):prompt_log("Not");if(_err!=0){return false}validate._custom?input_custom(prompt):prompt_log("Not");if(_err!=0){return false}validate._date?input_date(prompt):prompt_log("Not");if(_err!=0){return false}validate._http?input_http(prompt):prompt_log("Not");if(_err!=0){return false}validate._minmax?input_minmax(prompt,validate._minmax):prompt_log("Not");if(_err!=0){return false}validate._number_max?input_number_max(prompt,validate._number_max):prompt_log("Not");if(_err!=0){return false}validate._number_min?input_number_min(prompt,validate._number_min):prompt_log("Not");
		    if(_err!=0){return false}validate._number_minmax?input_number_minmax(prompt,validate._number_minmax):prompt_log("Not");if(_err!=0){return false}validate._time?input_time(prompt):prompt_log("Not");if(_err!=0){return false}validate._datetime?input_datetime(prompt):prompt_log("Not");if(_err!=0){return false}return true};validate_error=function(p){se_switch(p,false);_A["window_show"]?p.fadeIn(500):p.hide();if(_A["pe"]){var pe=_o.attr("pe");if(vali_fun.isChackUn(pe)){prompt_log("pe is null");return false}$(pe).size()>0?$(pe).fadeIn(300):prompt_log("label pe is null")}};validate_success=function(p){prompt_log("validate Success!");se_switch(p,true);p.html(_prompt["Success"]);p.append(_s);_A["window_show"]?_A["success_show"]?p.fadeIn(500):p.hide():p.hide();if(_A["ps"]){var ps=_o.attr("ps");if(vali_fun.isChackUn(ps)){prompt_log("ps is null");return false}$(ps).size()>0?$(ps).fadeIn(300):prompt_log("label ps is null")}};_vali=function(){this.isURL=function(str){return !!str.match(/(((^https?:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)$/g)};this.vali_date=function(str){var r=str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);if(r==null){return false}var d=new Date(r[1],r[3]-1,r[4]);return(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4])};this.vali_time=function(str){var a=str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);if(a==null){return false}if(a[1]>24||a[3]>60||a[4]>60){return false}return true};this.vali_datetime=function(str){var reg=/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;var r=str.match(reg);if(r==null){return false}var d=new Date(r[1],r[3]-1,r[4],r[5],r[6],r[7]);return(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7])};this.bank_luhmCheck=function(bankno){var lastNum=bankno.substr(bankno.length-1,1);var first15Num=bankno.substr(0,bankno.length-1);var newArr=new Array();for(var i=first15Num.length-1;i>-1;i--){newArr.push(first15Num.substr(i,1))}var arrJiShu=new Array();var arrJiShu2=new Array();var arrOuShu=new Array();for(var j=0;j<newArr.length;j++){if((j+1)%2==1){if(parseInt(newArr[j])*2<9){arrJiShu.push(parseInt(newArr[j])*2)}else{arrJiShu2.push(parseInt(newArr[j])*2)}}else{arrOuShu.push(newArr[j])}}var jishu_child1=new Array();var jishu_child2=new Array();for(var h=0;h<arrJiShu2.length;h++){jishu_child1.push(parseInt(arrJiShu2[h])%10);jishu_child2.push(parseInt(arrJiShu2[h])/10)}var sumJiShu=0;var sumOuShu=0;var sumJiShuChild1=0;var sumJiShuChild2=0;var sumTotal=0;for(var m=0;m<arrJiShu.length;m++){sumJiShu=sumJiShu+parseInt(arrJiShu[m])}for(var n=0;n<arrOuShu.length;n++){sumOuShu=sumOuShu+parseInt(arrOuShu[n])}for(var p=0;p<jishu_child1.length;p++){sumJiShuChild1=sumJiShuChild1+parseInt(jishu_child1[p]);sumJiShuChild2=sumJiShuChild2+parseInt(jishu_child2[p])}sumTotal=parseInt(sumJiShu)+parseInt(sumOuShu)+parseInt(sumJiShuChild1)+parseInt(sumJiShuChild2);
		    var k=parseInt(sumTotal)%10==0?10:parseInt(sumTotal)%10;var luhm=10-k;if(lastNum==luhm){return true}else{return false}};this.bank_Check=function(bankno){if(bankno==""){return false}if(bankno.length<16||bankno.length>19){return false}var num=/^\d*$/;if(!num.exec(bankno)){return false}var strBin="10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";if(strBin.indexOf(bankno.substring(0,2))==-1){return false}if(!this.bank_luhmCheck(bankno)){return false}return true};this.isChackUn=function(v){return v==""||v==null||v==undefined};this.Wi=[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1];this.Vc=[1,0,10,9,8,7,6,5,4,3,2];this.IdCard=function(idCard){if(idCard.length==15){return this.IdCard15(idCard)}else{if(idCard.length==18){var a_idCard=idCard.split("");if(this.Id2Card18(idCard)&&this.Id1Card18(a_idCard)){return true}else{return false}}else{return false}}};this.Id1Card18=function(a_idCard){var sum=0;if(a_idCard[17].toLowerCase()=="x"){a_idCard[17]=10}for(var i=0;i<17;i++){sum+=this.Wi[i]*a_idCard[i]}var i=sum%11;if(a_idCard[17]==this.Vc[i]){return true}else{return false}};this.Id2Card18=function(idCard18){var year=idCard18.substring(6,10);var month=idCard18.substring(10,12);var day=idCard18.substring(12,14);var temp_date=new Date(year,parseFloat(month)-1,parseFloat(day));if(temp_date.getFullYear()!=parseFloat(year)||temp_date.getMonth()!=parseFloat(month)-1||temp_date.getDate()!=parseFloat(day)){return false}else{return true}};this.IdCard15=function(idCard15){var year=idCard15.substring(6,8);var month=idCard15.substring(8,10);var day=idCard15.substring(10,12);var temp_date=new Date(year,parseFloat(month)-1,parseFloat(day));if(temp_date.getYear()!=parseFloat(year)||temp_date.getMonth()!=parseFloat(month)-1||temp_date.getDate()!=parseFloat(day)){return false}else{return true}};this.trim=function(str){return str.replace(/(^\s*)|(\s*$)/g,"")};this.getPaddingTB=function(o){var t=o.css("padding-top");var b=o.css("padding-bottom");var bl=o.css("border-top-width");var br=o.css("border-bottom-width");return parseInt(t)+parseInt(b)+parseInt(bl)+parseInt(br)};this.getPaddingLR=function(o){var l=o.css("padding-left");var r=o.css("padding-right");var bl=o.css("border-left-width");var br=o.css("border-right-width");return parseInt(l)+parseInt(r)+parseInt(bl)+parseInt(br)}};var vali_fun=new _vali();var input_email,input_phone,input_max,input_min,input_bank,input_only,input_must,input_first_must,input_first_cannot,input_number,input_idcard,input_equally,input_cannot,input_custom,input_date,input_http,input_minmax,input_number_max,input_number_min,input_number_minmax,input_time,input_datetime;input_email=function(p){_Rep=_r[0];p.html(_prompt["email"]);p.append(_e);_Rep.test(_v)?prompt_log("input_email-->Success"):validate_error(p);_Rep.test(_v)?_err=0:_err=1};input_number_minmax=function(p,num){var _num=num.split(",");if(_num.length==1){_num=num.split("-")}if(_num.length==1){prompt_log("input_number_minmax-->Error")}else{if(input_number_min(p,_num[0])){input_number_max(p,_num[1])
			}}};input_minmax=function(p,num){var _num=num.split(",");if(_num.length==1){_num=num.split("-")}if(_num.length==1){prompt_log("input_minmax-->Error")}else{if(input_min(p,_num[0])){input_max(p,_num[1])}}};input_http=function(p){var _ifs=vali_fun.isURL(_v);p.html(_prompt["http"]);p.append(_e);_ifs?prompt_log("input_http-->Success"):validate_error(p);_ifs?_err=0:_err=1};input_date=function(p){var _ifs=vali_fun.vali_date(_v);p.html(_prompt["date"]);p.append(_e);_ifs?prompt_log("input_date-->Success"):validate_error(p);_ifs?_err=0:_err=1};input_time=function(p){var _ifs=vali_fun.vali_time(_v);p.html(_prompt["time"]);p.append(_e);_ifs?prompt_log("input_time-->Success"):validate_error(p);_ifs?_err=0:_err=1};input_datetime=function(p){var _ifs=vali_fun.vali_datetime(_v);p.html(_prompt["datetime"]);p.append(_e);_ifs?prompt_log("input_datetime-->Success"):validate_error(p);_ifs?_err=0:_err=1};input_cannot=function(p,a){p.html(_prompt["cannot"].replace("｛0｝",a));p.append(_e);if(_v==a){validate_error(p);_err=1}};input_idcard=function(p){var _ifs=vali_fun.IdCard(_v);p.html(_prompt["idcard"]);p.append(_e);_ifs?prompt_log("input_idcard-->Success"):validate_error(p);_ifs?_err=0:_err=1};input_equally=function(p,a){if($(a).size()==0){p.html("没有找到："+a);p.append(_e);validate_error(p);_err=1;return false}if(_v!=$(a).eq(0).val()){p.html(_prompt["equally"]);p.append(_e);validate_error(p);_err=1}};input_number=function(p){p.html(_prompt["number"]);p.append(_e);var num=parseFloat(_v);if(isNaN(num)){validate_error(p);_err=1;return false}else{var dp=parseInt(_o.attr("dp"));var _dp=_A["decimal_places"];if(!isNaN(dp)&&dp>=0){_dp=dp}_o.val(num.toFixed(_dp));return true}};input_phone=function(p){_Rep=_r[1];p.html(_prompt["phone"]);p.append(_e);_Rep.test(_v)?prompt_log("input_phone-->Success"):validate_error(p);if(!_Rep.test(_v)){_err=1;return false}var bl=_o.attr("bl");if(!vali_fun.isChackUn(bl)){var bls=bl.split(",");$.each(bls,function(a,b){if(_v.indexOf(b)==0){_err=1;validate_error(p);p.html(_prompt["phone_bl"]+bl);p.append(_e);return false}})}};input_number_max=function(p,num){if(!input_number(p)){return false}if(isNaN(num)){return prompt_log("Max is NaN")}p.html(_prompt["number_max"].replace("｛0｝",num));p.append(_e);var this_v=parseFloat(_v);this_v<=num?prompt_log("input_max-->Success"):validate_error(p);this_v<=num?_err=0:_err=1;return this_v<=num};input_max=function(p,num){if(isNaN(parseInt(num))||num<0){return prompt_log("Max is NaN and <0")}p.html(_prompt["max"].replace("｛0｝",num));p.append(_e);_v.length<=num?prompt_log("input_max-->Success"):validate_error(p);_v.length<=num?_err=0:_err=1;return _v.length<=num};input_number_min=function(p,num){if(!input_number(p)){return false}if(isNaN(parseInt(num))){return prompt_log("Min is NaN")}p.html(_prompt["number_min"].replace("｛0｝",num));p.append(_e);var this_v=parseFloat(_v);this_v>=num?prompt_log("input_min-->Success"):validate_error(p);this_v>=num?_err=0:_err=1;return this_v>=num};input_min=function(p,num){if(isNaN(parseInt(num))){return prompt_log("Min is NaN")
			}p.html(_prompt["min"].replace("｛0｝",num));p.append(_e);_v.length>=num?prompt_log("input_min-->Success"):validate_error(p);_v.length>=num?_err=0:_err=1;return _v.length>=num};input_bank=function(p){var ifs=vali_fun.bank_Check(_v);p.html(_prompt["bank"]);p.append(_e);ifs?prompt_log("input_bank-->Success"):validate_error(p);return ifs?_err=0:_err=1};input_must=function(p,num){var pr=_prompt["must"];var n=num.split(",");for(var i=0;i<n.length;i++){if(n[i]=="0"){_Rep=new RegExp("[\\u4E00-\\u9FFF]+","g");if(!_Rep.test(_v)){_err=1}pr=pr+"，"+_prompt["value0"]}else{if(n[i]=="1"){_Rep=new RegExp("[a-zA-Z]+","g");if(!_Rep.test(_v)){_err=1}pr=pr+"，"+_prompt["value1"]}else{if(n[i]=="2"){_Rep=new RegExp("[0-9]+","g");if(!_Rep.test(_v)){_err=1}pr=pr+"，"+_prompt["value2"]}else{if(n[i]=="3"){_Rep=new RegExp("[\\s]+","g");if(!_Rep.test(_v)){_err=1}pr=pr+"，"+_prompt["value3"]}else{_Rep=new RegExp("["+n[i]+"]+","g");if(!_Rep.test(_v)){_err=1}pr=pr+"，"+n[i]}}}}}p.html(pr);p.append(_e);if(_err!=0){validate_error(p);return false}0==0?prompt_log("input_only-->Success"):validate_error(p);0==0?_err=0:_err=1};input_only=function(p,num){var _v_rep="";var pr=_prompt["only"];var n=num.split(",");for(var i=0;i<n.length;i++){if(n[i]=="0"){_v_rep=_v_rep+"\u4e00-\u9fa5";pr=pr+"，"+_prompt["value0"]}else{if(n[i]=="1"){_v_rep=_v_rep+"a-zA-Z";pr=pr+"，"+_prompt["value1"]}else{if(n[i]=="2"){_v_rep=_v_rep+"0-9";pr=pr+"，"+_prompt["value2"]}else{if(n[i]=="3"){_v_rep=_v_rep+"\\s";pr=pr+"，"+_prompt["value3"]}else{_v_rep=_v_rep+n[i];pr=pr+"，"+n[i]}}}}}p.html(pr);p.append(_e);_Rep=new RegExp("^["+_v_rep+"]+$");_Rep.test(_v)?prompt_log("input_only-->Success"):validate_error(p);_Rep.test(_v)?_err=0:_err=1};input_first_must=function(p,num){var pr=_prompt["first_must"];var fm="";vali_fun.isChackUn(_v)?_err=1:fm=_v.substring(0,1);if(num=="0"){_Rep=new RegExp("[\\u4E00-\\u9FFF]+","g");if(!_Rep.test(fm)){_err=1}pr=pr+"，"+_prompt["value0"]}else{if(num=="1"){_Rep=new RegExp("[a-zA-Z]+","g");if(!_Rep.test(fm)){_err=1}pr=pr+"，"+_prompt["value1"]}else{if(num=="2"){_Rep=new RegExp("[0-9]+","g");if(!_Rep.test(fm)){_err=1}pr=pr+"，"+_prompt["value2"]}else{if(num=="3"){_Rep=new RegExp("[\\s]+","g");if(!_Rep.test(fm)){_err=1}pr=pr+"，"+_prompt["value3"]}else{_Rep=new RegExp("["+num+"]+","g");if(!_Rep.test(fm)){_err=1}pr=pr+"，"+num}}}}p.html(pr);p.append(_e);_err==0?prompt_log("input_first_must-->Success"):validate_error(p)};input_first_cannot=function(p,num){var pr=_prompt["first_cannot"];var fm="";vali_fun.isChackUn(_v)?_err=1:fm=_v.substring(0,1);if(num=="0"){_Rep=new RegExp("[\\u4E00-\\u9FFF]+","g");if(_Rep.test(fm)){_err=1}pr=pr+"，"+_prompt["value0"]}else{if(num=="1"){_Rep=new RegExp("[a-zA-Z]+","g");if(_Rep.test(fm)){_err=1}pr=pr+"，"+_prompt["value1"]}else{if(num=="2"){_Rep=new RegExp("[0-9]+","g");if(_Rep.test(fm)){_err=1}pr=pr+"，"+_prompt["value2"]}else{if(num=="3"){_Rep=new RegExp("[\\s]+","g");if(_Rep.test(fm)){_err=1}pr=pr+"，"+_prompt["value3"]}else{_Rep=new RegExp("["+num+"]+","g");if(_Rep.test(fm)){_err=1
			}pr=pr+"，"+num}}}}p.html(pr);p.append(_e);_err==0?prompt_log("input_first_must-->Success"):validate_error(p)};input_custom=function(p){var ele=_o.attr("ele");var _cus=null;$.each(_custom,function(a){if(_custom[a]._ele==ele){_cus=_custom[a]}});if(_cus==null){p.html("Custom Error");p.append(_e);validate_error(p);_err=1;return false}p.html(_cus._err);p.append(_e);var ifs=_cus._fun(_o);ifs?_err=0:validate_error(p);ifs?_err=0:_err=1};custom=function(c){$.each(c,function(a,validate){_custom[a]=new _c();$.each(validate,function(n,v){if(n=="ele"){_custom[a]._ele=v}if(n=="fun"){_custom[a]._fun=v}if(n=="err"){_custom[a]._err=v}})})};_A["custom"]==null?_A["custom"]=null:custom(_A["custom"]);_.each(function(){_findvali[vali_size]=$(this).find("*["+_A["vali"]+"]");_findvali[vali_size].each(function(){init($(this));code();if(_o.is("input[type='text']")||_o.is("input[type='password']")||_o.is("textarea")||_o.is("select")||_o.is("input")){input_focus(_o.attr("valiid"));input_blur(_o.attr("valiid"))}});$(this).on("submit",function(){_findvali[vali_size].each(function(){$(this).blur();if(_err!=0){return false}});if(_err!=0){return false}})})},}),$.vali=function(){return !0},$.extend($.vali,{prompt:{"Success":"Success","email":"Please enter the correct email address","phone":"Please enter the correct cell phone number","phone_bl":"Not supported：","max":"Can not exceed ｛0｝ characters","min":"Not less than ｛0｝ characters","bank":"Please enter the correct bank card number","only":"Contain only","must":"Must contain","first_must":"The first must be：","first_cannot":"The first cannot be：","number":"Please enter a number","idcard":"Please enter the correct ID number","equally":"Must be the same","cannot":"cannot is ｛0｝","date":"Please enter the correct date","http":"Please enter the correct URL","number_max":"Can not be greater than ｛0｝","number_min":"Can not be less than ｛0｝","time":"Please enter the correct time","datetime":"Please enter the correct date time","value0":"Chinese","value1":"Letter","value2":"Number","value3":"Space"},setPrompt:function(a){$.extend(this.prompt,a)},form:function(a,b){var _t=this;b==undefined?b=true:b=false;if($(a).size()==0){return false}var vs=$(a).attr("vali_size");if(vs==""||vs==null||vs==undefined){console.log("First Run vali");return false}_findvali[vs].each(function(){$(this).blur();if(_err!=0&&b){_t.promptHide();return false}});if(_err!=0){_t.promptHide();return false}_t.promptHide();return true},ele:function(a){var _t=this;if($(a).size()==0){return false}$(a).blur();if(_err!=0){_t.promptHide();return false}_t.promptHide();return true},promptHide:function(a){var id="";a?id=$(a).attr("valiid"):id="";if(id==""||id==null||id==undefined){$(".validate_label_prompt").hide()}else{$("#valiid"+id).hide()}}})})(jQuery);		
			$.vali.setPrompt({
			    "Success": "输入正确",
			    "email": "请输入正确的邮箱地址",
			    "phone": "请输入正确的手机号码",
			    "phone_bl":"不支持：" ,
			    "max": "长度不能超过｛0｝位",
			    "min": "长度不能小于｛0｝位",
			    "bank": "请输入正确的银行卡号",
			    "only": "只能包含",
			    "must": "必须包含",
			    "first_must": "首位必须是",
			    "first_cannot": "首位不能是",
			    "number": "请输入正确合法的数字",
			    "idcard": "请输入正确的身份证号",
			    "equally": "两次输入必须一致",
			    "cannot": "不能是｛0｝",
			    "date": "请输入正确的日期格式",
			    "http": "请输入正确的网址",
			    "number_max": "不能大于 ｛0｝",
			    "number_min": "不能小于 ｛0｝",
			    "time": "请输入正确的时间格式",
			    "datetime": "请输入正确的日期时间格式",
			    "value0": "汉字",
			    "value1": "字母",
			    "value2": "数字",
			    "value3": "空格字符"
			});
			
			$("#commentForm").vali({
			    "window_show":true,
			    "custom":[{
			        "ele":"a",
			        "fun":function(e){
			            return e.val()=="LUKANG";
			        }
			    }]
			});
		});
	</script>
</body>
</html>