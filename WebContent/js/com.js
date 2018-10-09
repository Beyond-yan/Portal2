/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * version 1.0: (1)add comm_update
 * version 1.1: (1)add comm_callForm
 *                    (2)modify comm_lov
 *                    (3)modify comm_quickviewer
 *                    (4)modify comm_upload
 *version 1.2 update comm_callForm
 *version 1.3 add comm_showJobName
 *version 1.4 (1) comm_lov:加入判斷原本欄位的功能
 *                  (2) comm_validateLengthZH():判斷欄位長度，含中文字功能(double byte)
 *                  (3) comm_checkAYMgtBYM(check 後面的時間要gt前面的時間,為YYYYMMDD)
 *                  (4) comm_trimAllInputs(將所有畫面上的欄位清空)
 *                  (5) comm_checkYYYYMM:check日期格式
 *                  (6)comm_checkpoints
 *version1.5 (1)addLoadListener(fn)/__showVersion()/showVersion()
 *                  (2)assignValue()//for lov function update
 *version 1.6(1)comm_showtotalRecord(record) //shot出目前total record
 *                  (2)comm_table_record(obj)//目前table的列數
 *                  (3)comm_countrecord (pagenum)//判斷之前有沒有出現過
 *                  (4)comm_checkAYMDgtBYMD(beginDate,endDate)//日期格式是YYYYMMDD
 *                  (5)initial :提供re-szie功能與自動計算筆數功能
 *                  (6)comm_callExpired 提供導回網頁功能，但是在ie7+jsf的環境下還會有到下一頁的問題
 *                  (7)comm_checkPersent 檢查Like查詢欄位是否包含 "%" ,"_"
 *                  (8)comm_callForm bug更新
 *version 1.7 (1) comm_getDate 可以修改欄位
 *                  (2)修改comm_lov,有點選資料則回傳true,否則為false
 *                  (3))add comm_tri_iframe:影藏的iframe,可以動態呼叫執行某一網頁
 *version 1.8 GDS新增功能(comm_saveFiledsData~comm_CheckyyyyMMddWithPer)
 *                  (1)日期模糊查詢
 *                  (2) 解決TextArea的輸入問題      
 *                  (3) judge weather the page data has been modified
 *                  (4)comm_getCurrRowObj(this.seat) //取出
 *
 *verson 1.9 add when calculating record finish,return status
 *
 *version 2.0 add comm_upload_local:for upload
 *                js_alltotalRecord:for calculate total record if any error happened
 *
 *version 2.1 add below:
 *      (1)  comm_callDetail(cells_Index,children_Index,tablename):call detail when calling mater block,ex
 *      (2)  comm_isDate_DateTime(str,label):check date formate ,can be 'YYYYMMDD' or 'YYYYMMDD HH24:MI:SS'
 *      (3)  comm_rowValue(obj,cell,child):thisobject,cell number,child number
 *
 *version 20091105 add below: 
 *      (1)  comm_isDate_DateTime(str,label):check date formate ,can be 'YYYYMMDD' or 'YYYYMMDD HH24:MI:SS'
 *      (2)  comm_rowValue(obj,cell,child):thisobject,cell number,child number
 *      (3)  comm_quickviewer
 *version 20091124 add below:
 *      (1)add comm_getTopParent
 *      (2)update comm_callForm
 */
//===============================================
// date         name        function
// 2007xxxx     Jay&Vincent Writing js common function
// 20071126     Vincent     Update common.js & common_function.js=>comm.js
// 20080129   vincent add comm_upper
// 20090310 vincent add function : validateLengthZH
//
//
// comm_showJobName(job_name,alertSttring)
// comm_isnum(obj):是否為數字
// comm_upper(): 小寫轉大寫
// comm_replaceAll:字串取代
// comm_LRTrim(str)/comm_trim: 去掉前後空白
// comm_RTrim(str): 去掉後面空白 *
// comm_getDate(obj): 取得現在日期
// comm_isDate(str): 是否為日期格式 yyyymmdd
// comm_isDatetime(str): 是否為日期時間格式 yyyymmdd hh24miss
// comm_isEmail(str): 是否為 e-mail address 格式
// comm_isEnglish(obj) 是否為英數字格式
// comm_isInteger(str): 是否為整數
// comm_isPositiveInteger(str): 是否為正整數
// comm_isPositiveNumber(str): 是否為正數
// comm_lov(obj, strURL): 指定 List Of Value 功能
// comm_getElementIndex(obj): 取得所在的index
// comm_goItem(obj): 將focus()移到下一個item
// comm_setCheckboxValue(obj, strTrue, strFalse): 改變 Checkbox 的值
// comm_onSubmit(document.form)
// comm_showRunning:提供立即檢查的Div Tag,防止使用者馬上又打其他欄位
// comm_hideRunning:取消立即檢查的Div Tag,刪除Div
//===============================================
// comm_doResize(): 將iframe的高度撐開至最大(防止iframe出現scrollbar)
// comm_checkAllInput(bolAlert):檢查form內所有必填(requried="required")欄位
// comm_checkType_input(bolAlert):檢查form內所有必填(requried="required")的input欄位
// comm_checkType_textarea(bolAlert):檢查form內所有必填(requried="required")的textarea欄位
// comm_checkType_select(bolAlert):檢查form內所有必填(requried="required")的select欄位
// comm_showCalendarDialog(myObj, strRoot):萬年曆對話框
// comm_showDialog(strURL,strHeight,strWidth):對話框
// comm_doUnlock():將頁面上所有被disabled的項目enable
// comm_AssembleParamByButton(obj):由被觸發的button後的項目組合querystring
// comm_AssembleParamByRadio(obj):由被選擇到的radio後的項目組合querystring
// comm_AssembleParamByCheckBox(obj):由被選擇到的checkbox後的項目組合querystring
// comm_addRow(obj,bolKeepData,bolChangeID):新增一列tr,並決定是否要保留資料及改變ID
// comm_delRow(obj,bolKillFirstRow):刪除特定的tr,若為最後一列時,決定是否可刪除或僅清除資料
// comm_divExtend(obj,bolShowAll):控制頁面上div的顯示
//20100118 vincent add jQuery1.4
document.onkeypress = comm_upperCase;
try {
	// document.domain = "&&.com";
} catch (e) {
}

function comm_doResize(iframe_name) {

	if (document.readyState == "complete" || document.addEventListener) {
		var oCurrObj = "";
		var oFoundObj = window.parent.document.getElementsByTagName("iframe");
		for ( var i = 0; i < oFoundObj.length; i++) {
			var oTempObj = oFoundObj.item(i);
			if (oTempObj.id == iframe_name) {
				oCurrObj = oTempObj;

				break;
			}
		}
		oCurrObj.height = document.body.scrollHeight;
		// oCurrObj.width = document.body.scrollWidth;
	}
}

function comm_upperCase(event) {
	// alert(event.keyCode);
	event = event || window.event;
	var code = (event.charCode || event.keyCode);
	if (code >= 97 && code <= 122) {
		if (event.charCode) {
			/*
			 * var Event = document.createEvent("KeyEvents");
			 * Event.initKeyEvent("keypress",0,0,null,0,0,0,0,0,(code-32));
			 * event.target.dispatchEvent(Event); event.preventDefault();
			 */
		} else {
			event.keyCode -= 32;
		}

	}
}

function comm_backgroundReload(obj) {
	i = document.getElementById("masterForm:columnList:tb").childNodes.length;
	for (j = 0; j < i; j++) {
		// if ( j % 2 == 1) {
		// color = 'Gainsboro'
		// } else {
		color = '#EEEEEE'
		// }
		document.getElementById("masterForm:columnList:tb").childNodes[j].style.backgroundColor = color;
	}
	obj.style.backgroundColor = '#FAFA78';
}

function comm_checkAllInput(bolAlert) {
	if (bolAlert == null)
		bolAlert = true;

	var bReturn = false;
	if (comm_checkType_input(bolAlert) == true)
		if (comm_checkType_textarea(bolAlert) == true)
			if (comm_checkType_select(bolAlert) == true)
				bReturn = true;
	return (bReturn);
}

function comm_checkType_input(bolAlert) {
	if (bolAlert == null)
		bolAlert = true;

	var bReturn = true;
	var oFoundObj = document.getElementsByTagName("input");
	for ( var i = 0; i < oFoundObj.length; i++) {
		var oTempObj = oFoundObj.item(i);
		if (typeof (oTempObj.getAttribute('required')) == "string") {
			if (comm_LRTrim(oTempObj.value) == '') {
				if (bolAlert)
					alert('Please input the required column!');
				bReturn = false;
			}
		}

		switch (oTempObj.getAttribute('alt')) {
		case "string-Y":// 必填字符串
			if (oTempObj.value == "") {
				if (bolAlert)
					alert('Please input the required column !');
				bReturn = false;
			}
		case "string-N":// 非必填字符串
			break;

		case "number-Y":// 必填数字
			if (isNaN(oTempObj.value) == true) {
				if (bolAlert)
					alert('Please input the required NUMBER !');
				bReturn = false;
			}
			break;

		case "number-N":// 非填数字
			if (oTempObj.value == "") {

			} else {
				if (isNaN(oTempObj.value) == true) {
					if (bolAlert)
						alert('Please input the required NUMBER !');
					bReturn = false;
				}
			}
			break;

		case "mail-Y":
			if (comm_isEmail(oTempObj.value) == false) {
				if (bolAlert)
					alert('Please input the required EMail column !');
				bReturn = false;
			}

			break;
		case "mail-N":
			if (oTempObj.value == "") {

			} else {
				if (comm_isEmail(oTempObj.value) == false) {

					if (bolAlert)
						alert('Please input the required EMail column !');
					bReturn = false;
				}
			}
			break;

		case "dateformat":
			break;
		}

		if (bReturn == false) {
			oTempObj.focus();
			break;
		}
	}
	return (bReturn);
}

function comm_checkType_textarea(bolAlert) {
	if (bolAlert == null)
		bolAlert = true;

	var bReturn = true;
	var oFoundObj = document.getElementsByTagName("textarea");
	for ( var i = 0; i < oFoundObj.length; i++) {
		var oTempObj = oFoundObj.item(i);
		if (typeof (oTempObj.getAttribute('required')) == "string") {
			if (comm_LRTrim(oTempObj.value) == '') {
				if (bolAlert)
					alert('Please input the required column');
				bReturn = false;
			}
		}
		if (bReturn == false) {
			oTempObj.focus();
			break;
		}
	}
	return (bReturn);
}

function comm_checkType_select(bolAlert) {
	if (bolAlert == null)
		bolAlert = true;

	var bReturn = true;
	var oFoundObj = document.getElementsByTagName("select");
	for ( var i = 0; i < oFoundObj.length; i++) {
		var oTempObj = oFoundObj.item(i);
		if (typeof (oTempObj.getAttribute('required')) == "string") {
			if (oTempObj.selectedIndex == 0) {
				if (bolAlert)
					alert('Please input the required column');
				bReturn = false;
			}
		}

		if (bReturn == false) {
			oTempObj.focus();
			break;
		}
	}
	return (bReturn);
}

function comm_showCalendarDialog(myObj, strRoot) {
	myObj.value = window
			.showModalDialog(
					strRoot + "/js/calendar_c.htm",
					"",
					"dialogTop: 150px; dialogLeft: 300px; center: Yes; help: No; resizable: No; status: No;");
}

function comm_showDialog(strURL, strHeight, strWidth) {
	var strParam = "dialogHeight:" + strHeight + "px; dialogWidth:" + strWidth
			+ "px;";
	// vincent add for avoid cache.
	strURL = strURL + "&" + (new Date()).getUTCMilliseconds();
	var strTemp = window.showModalDialog(strURL, window, strParam
			+ "center: Yes; help: No; resizable: No; status: No;");
	return (strTemp);
}

function comm_doUnlock() {
	var oFoundObj = document.getElementsByTagName('*');
	for ( var i = 0; i < oFoundObj.length; i++) {
		var oTempObj = oFoundObj.item(i);
		if (oTempObj.disabled == true)
			oTempObj.disabled = false;
	}
}

function comm_AssembleParamByButton(obj) {
	var strParam = "";
	for ( var i = 1; i < obj.parentNode.childNodes.length; i++) {
		if (obj.parentNode.childNodes[i].nodeName == "INPUT"
				&& obj.parentNode.childNodes[i].type == "hidden") {
			if (comm_LRTrim(obj.parentNode.childNodes[i].value) != "") {
				if (strParam == "")
					strParam += "?";
				else
					strParam += "&";
				strParam += obj.parentNode.childNodes[i].name
						+ "="
						+ encodeURIComponent(obj.parentNode.childNodes[i].value);
			}
		}
	}
	return (strParam);
}

function comm_AssembleParamByRadio(obj) {
	var strParam = "";
	var oFoundObj = null;
	if (obj == null)
		oFoundObj = document.getElementsByTagName("input");
	else
		oFoundObj = obj.getElementsByTagName("input");
	for ( var i = 0; i < oFoundObj.length; i++) {
		var oTempObj = oFoundObj.item(i);
		if (oTempObj.type == "radio" && oTempObj.checked == true) {
			for ( var j = 1; j < oTempObj.parentNode.childNodes.length; j++) {
				if (oTempObj.parentNode.childNodes[j].nodeName == "INPUT"
						&& oTempObj.parentNode.childNodes[j].type == "hidden") {
					if (strParam == "")
						strParam += "?";
					else
						strParam += "&";
					strParam += oTempObj.parentNode.childNodes[j].name
							+ "="
							+ encodeURIComponent(oTempObj.parentNode.childNodes[j].value);
				}
			}
			break;
		}
	}
	return (strParam);
}

function comm_AssembleParamByCheckBox(obj) {
	var strParam = "";
	var oFoundObj = null;
	if (obj == null)
		oFoundObj = document.getElementsByTagName("input");
	else
		oFoundObj = obj.getElementsByTagName("input");
	for ( var i = 0; i < oFoundObj.length; i++) {
		var oTempObj = oFoundObj.item(i);
		if (oTempObj.type == "checkbox" && oTempObj.checked == true) {
			for ( var j = 1; j < oTempObj.parentNode.childNodes.length; j++) {
				if (oTempObj.parentNode.childNodes[j].nodeName == "INPUT"
						&& oTempObj.parentNode.childNodes[j].type == "hidden") {
					if (comm_LRTrim(oTempObj.parentNode.childNodes[j].value) != "") {
						if (strParam == "")
							strParam += "?";
						else
							strParam += "&";
						strParam += oTempObj.parentNode.childNodes[j].name
								+ "="
								+ encodeURIComponent(oTempObj.parentNode.childNodes[j].value);
					}
				}
			}
		}
	}
	return (strParam);
}

function comm_divExtend(obj, bolShowAll) {
	var strMode = (bolShowAll) ? "" : "none";
	for ( var i = 0; i < document.getElementsByTagName("div").length; i++)
		document.getElementsByTagName("div")[i].style.display = strMode;
	if (obj != null)
		obj.style.display = "";
}

function comm_addRow(obj, bolKeepData, bolChangeID, clone_index) {
	if (bolKeepData == null)
		bolKeepData = true;
	if (bolChangeID == null)
		bolChangeID = false;
	if (clone_index == null)
		clone_index = 0;

	var oTable = obj.parentNode;
	while (oTable.nodeName != 'TABLE')
		oTable = oTable.parentNode;
	var oCloneNode = oTable.rows[clone_index];// 取第一列
	var oNewNode = oTable.insertRow(-1);// firefox要有參數,可以為-1
	if (!document.all) {
		Node.prototype.replaceNode = function(Node) {// 替換指定節點
			this.parentNode.replaceChild(Node, this);
		}
	}
	for ( var i = 0; i < oCloneNode.cells.length; i++) {
		var oNewCell = oNewNode.insertCell(i);
		oNewCell.replaceNode(oCloneNode.cells[i].cloneNode(true));
	}

	if (bolKeepData == false) {
		var nRowIndex = oTable.rows.length;
		for ( var i = 0; i < oCloneNode.cells.length; i++)
			if (oTable.rows[nRowIndex - 1].cells[i].childNodes[0].nodeName == "INPUT"
					&& oTable.rows[nRowIndex - 1].cells[i].childNodes[0].type != 'button')
				oTable.rows[nRowIndex - 1].cells[i].childNodes[0].value = '';
	}

	if (bolChangeID) {
		for ( var j = 1; j < oTable.rows.length; j++)
			for ( var k = 0; k < oCloneNode.cells.length; k++) {
				if (oTable.rows[j].cells[k].firstChild) {
					var oNode = oTable.rows[j].cells[k].firstChild;
					oNode.id = oNode.name + j;
				}
			}
	}
}

function comm_delRow(obj, bolKillFirstRow) {
	if (bolKillFirstRow == null)
		bolKillFirstRow = false;

	var oNewNode = obj.parentNode;
	while (oNewNode.nodeName != 'TR')
		oNewNode = oNewNode.parentNode;
	var nRemoveIndex = oNewNode.rowIndex;
	while (oNewNode.nodeName != 'TABLE')
		oNewNode = oNewNode.parentNode;
	if (!document.all) {
		Node.prototype.removeNode = function(removeChildren) {// 刪除指定節點
			if (removeChildren)
				return this.parentNode.removeChild(this);
			else {
				var range = document.createRange();
				range.selectNodeContents(this);
				return this.parentNode.replaceChild(range.extractContents(),
						this);
			}
		}
	}
	if (!bolKillFirstRow && oNewNode.rows.length == 2) {
		var nColumns = oNewNode.cells.length / 2;
		for ( var i = 0; i < nColumns; i++)
			if (oNewNode.rows[1].cells[i].childNodes[0].nodeName == "INPUT"
					&& oNewNode.rows[1].cells[i].childNodes[0].type != 'button')
				oNewNode.rows[1].childNodes[i].childNodes[0].value = '';
	} else
		oNewNode.rows[nRemoveIndex].removeNode(true);
}

function comm_isnum(obj) {
	bReturn = true;
	if (isNaN(obj.value) == true || LRTrim(obj.value) == '') {
		alert('Please check ! Must be Numeric');
		obj.focus();
		bReturn = false;
	}
	return bReturn;
}

function comm_LRTrim(str) {
	var re;
	var new_str;
	re = /^\s{1,}/g;
	new_str = str.replace(re, "");
	re = /\s{1,}$/g;
	return new_str.replace(re, "");
}

function comm_trim(str) {
	return comm_LRTrim(str);
}

function comm_RTrim(str) {
	var re;
	var new_str;
	re = /\s{1,}$/g;
	new_str = str.replace(re, "");
	return new_str;
}

function comm_replaceAll(strOrg, strFind, strReplace) {

	var index = 0;
	while (strOrg.indexOf(strFind, index) != -1) {
		strOrg = strOrg.replace(strFind, strReplace);
		index = strOrg.indexOf(strFind, index);
	}
	return strOrg
}

function comm_isDate(str) {
	var rs = true;
	var len = str.length;
	// ��迂-/蝑����
	var reg = /^(\d{0,4})(-|\/|)(\d{1,2})(-|\/|)(\d{1,2})/;
	// var reg=/^(\d{0,4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})/;
	var r = reg.exec(str);

	if (r == null || len != 8) {
		alert("The format is not correct, it must be:YYYYMMDD");
		rs = false;
	} else {
		var d = new Date(r[1], r[3] - 1, r[5]);
		if (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
				&& d.getDate() == r[5]) {
			rs = true;
		} else {
			alert("The time is not correct, it must be:YYYYMMDD");
			rs = false;
		}
	}
	return rs;
}

function comm_isdate(str) {
	return isDate(str);
}

function comm_isDatetime(str) {
	var arr = str.split(" ", 2);
	var strDate = arr[0];
	var strTime = arr[1];
	if (isDate(strDate)) {
		var hhmiss = strTime.split(":");
		var hh = "";
		var mi = "";
		var ss = "";
		if (hhmiss.length == 1 && hhmiss[0].length == 6) {
			hh = Number(hhmiss[0].substr(0, 2));
			mi = Number(hhmiss[0].substr(2, 2));
			ss = Number(hhmiss[0].substr(4, 2));
		} else if (hhmiss.length == 3 && hhmiss[0].length >= 1
				&& hhmiss[0].length <= 2 && hhmiss[1].length >= 1
				&& hhmiss[1].length <= 2 && hhmiss[2].length >= 1
				&& hhmiss[2].length <= 2) {
			hh = Number(hhmiss[0]);
			mi = Number(hhmiss[1]);
			ss = Number(hhmiss[2]);
		} else {
			alert("The time format error!");
			return false;
		}

		if (isNaN(hh) || isNaN(mi) || isNaN(ss) || hh < 0 || hh > 23 || mi < 0
				|| mi > 59 || ss < 0 || ss > 59) {
			alert("The time is not correct, it must be:HH24MISS");
			return false;
		} else {
			return true;
		}
	} else {
		return false;
	}
}

function comm_getDate(obj) {
	var d = new Date;
	var YYYY = d.getYear();
	if (YYYY < 1000)
		YYYY += 1900;
	var MM = d.getMonth() + 1;
	if (MM < 10)
		MM = "0" + MM;
	var DD = d.getDate();
	if (DD < 10)
		DD = "0" + DD;
	obj.value = YYYY + "" + MM + "" + DD;
	// obj.disabled=true;
}

function comm_isEnglish(obj) {
	var ret = true;
	for ( var i = 0; i < obj.value.length; i++) {
		ret = ret && (obj.value.charCodeAt(i) <= 10000);
		if (!ret) {
			alert('You can not type Chinese Word!');
			break;
		}
	}
	return ret;
}

function comm_goItem(obj) {

	if (obj == null) {
		return false;
	} else {
		if (document.getElementsByName(obj.name).length > 1) {
			index = comm_getElementIndex(obj);
			document.getElementsByName(obj.name)[index].focus();
			return true;
		} else {
			obj.focus();
			return true;
		}
	}
}

// ��������ndex
function comm_getElementIndex(obj) {
	var index = 0;
	var oFoundObj = document.getElementsByName(obj.name);
	for ( var v = 0; v < oFoundObj.length; v++) {
		if (oFoundObj[v] == obj)
			index = v;
	}
	return index;
}

function comm_onsubmit(obj) {
	var checkAll = check_all_input();
	if (checkAll == true) {
		obj.submit();
	} else {
		return checkAll;
	}
}

function comm_isInteger(str) {
	var rs = true;
	if (isNaN(str)) {
		rs = false;
		alert("Wrong number!");
	} else {
		if (str.indexOf(".") >= 0) {
			rs = false;
			alert("Not an integer!");
		}
	}
	return rs;
}

function comm_isPositiveInteger(str) {
	var rs = true;
	if (isNaN(str)) {
		rs = false;
		alert("Wrong number!");
	} else {
		if (str < 0) {
			rs = false;
			alert("The number must >= zero!");
		}
		if (str.indexOf(".") >= 0) {
			rs = false;
			alert("Not an integer!");
		}
	}
	return rs;
}

function comm_isPositiveNumber(str) {
	var rs = true;
	if (isNaN(str)) {
		rs = false;
		alert("Wrong number!");
	} else {
		if (str < 0) {
			rs = false;
			alert("The number must >= zero!");
		}
	}
	return rs;
}

function comm_setCheckboxValue(obj, strTrue, strFalse) {
	if (obj.checked) {
		obj.value = strTrue;
	} else {
		obj.value = strFalse;
	}

}

function comm_lov(jndi_name, lov_name, default_value, return_fields,
		extra_condition, overwrite, autoselect, cust_msg) {
	var strParam = "";
	if (jndi_name == null || comm_LRTrim(jndi_name) == '') {
		alert("jndi_name didn't assign");
		return;
	}
	strParam += "&jndi_name=" + jndi_name;

	if (lov_name == null || comm_LRTrim(lov_name) == '') {
		alert("lov_name didn't assign");
		return;
	}
	strParam += "&lov_name=" + encodeURIComponent(lov_name);

	if (return_fields == null || comm_LRTrim(return_fields) == '') {
		alert("return_fields didn't assign");
		return;
	}
	strParam += "&return_fields=" + encodeURIComponent(return_fields);

	if (default_value != null) {
		try {
			default_value = encodeURIComponent(document
					.getElementById(default_value).value);
		} catch (e) {
			default_value = encodeURIComponent(default_value.replace("[", "")
					.replace("]", ""));
		}
	} else
		default_value = "";
	strParam += "&default_value=" + encodeURIComponent(default_value);

	strParam += "&extra_condition="
			+ (extra_condition == null ? ""
					: encodeURIComponent(extra_condition));
	strParam += "&overwrite="
			+ (overwrite == null ? "false" : encodeURIComponent(overwrite));
	strParam += "&autoselect="
			+ (autoselect == null ? "false" : encodeURIComponent(autoselect));
	strParam += "&msg="
			+ (cust_msg == null ? "" : encodeURIComponent(cust_msg));
	// alert(strParam.substring(1));
	// vincent add for avoid proxy cache
	var strURL = "";
	if (_getCurrentSystem() == "pnl")
		strURL = '/wanhai/pnl/whl-prompt/lov.jsp?' + strParam.substring(1)
				+ "&" + (new Date()).getUTCMilliseconds();
	else
		strURL = '/wanhai/whl-prompt/lov.jsp?' + strParam.substring(1) + "&"
				+ (new Date()).getUTCMilliseconds();
	var strRtnXML = window
			.showModalDialog(strURL, window,
					"dialogHeight:500px; dialogWidth:550px; center:Yes; help:No; status:No;");
	if (strRtnXML != null)
		assignValue(return_fields, strRtnXML);
	return false;
}

function assignValue(return_fields, strRtnXML) {
	// var xmlDoc = new ActiveXObject("Msxml2.DOMDocument");
	// xmlDoc.loadXML(strRtnXML);
	var xmlDoc = loadXMLString(strRtnXML);
	if (xmlDoc.documentElement != null) {
		var oNodeList = xmlDoc.selectNodes("/root/data");
		if (oNodeList != null) {
			var oAryReturn = return_fields.split(',');
			for ( var i = 0; i < oAryReturn.length; i++)
				if (oAryReturn[i] != "x") {
					try {
						this.document.getElementById(oAryReturn[i]).value = oNodeList[i].text;
					} catch (e) {
					}
				}
		}
		try {
			alert(xmlDoc.selectSingleNode("/root/err_msg").text);
		} catch (e) {
		}
	}
}

function loadXMLString(txt) {
	try // Internet Explorer
	{
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.async = "false";
		xmlDoc.loadXML(txt);
		return (xmlDoc);
	} catch (e) {
		try // Firefox, Mozilla, Opera, etc.
		{
			parser = new DOMParser();
			xmlDoc = parser.parseFromString(txt, "text/xml");
			return (xmlDoc);
		} catch (e) {
			// alert(e.message)
		}
	}
	return (null);
}

function comm_quickviewer(jndi_name, lov_name, query_value, extra_condition,
		overwrite, isforward, column_amount) {
	var strParam = "";
	if (jndi_name == null || comm_LRTrim(jndi_name) == '') {
		alert("jndi_name didn't assign");
		return;
	}
	strParam += "&jndi_name=" + jndi_name;

	if (lov_name == null || comm_LRTrim(lov_name) == '') {
		alert("lov_name didn't assign");
		return;
	}
	strParam += "&lov_name=" + encodeURIComponent(lov_name);

	var oAryColumn = query_value.split(',');
	query_value = "";
	for ( var i = 0; i < oAryColumn.length; i++) {
		try {
			query_value += ','
					+ encodeURIComponent(document.getElementById(oAryColumn[i]).value);
		} catch (e) {
			query_value += ','
					+ encodeURIComponent(oAryColumn[i].replace("[", "")
							.replace("]", ""));
		}
	}
	if (query_value != "")
		query_value = query_value.substring(1);
	strParam += "&query_value="
			+ (query_value == null ? "" : encodeURIComponent(query_value));
	strParam += "&extra_condition="
			+ (extra_condition == null ? ""
					: encodeURIComponent(extra_condition));
	strParam += "&overwrite="
			+ (overwrite == null ? "false" : encodeURIComponent(overwrite));
	strParam += "&column_amount="
			+ (column_amount == null ? "2" : column_amount);
	strParam += "&open_type=" + ((isforward != null && isforward) ? "1" : "0");
	// alert(strParam.substring(1));
	var strURL = '';
	if (_getCurrentSystem() == "pnl")
		strURL = '/wanhai/pnl/whl-prompt/quick_viewer.jsp?'
				+ strParam.substring(1) + "&"
				+ (new Date()).getUTCMilliseconds();
	else
		strURL = '/wanhai/whl-prompt/quick_viewer.jsp?' + strParam.substring(1)
				+ "&" + (new Date()).getUTCMilliseconds();
	if (isforward != null && isforward)
		window.location.href = strURL;
	else
		window.showModalDialog(strURL);
}

function comm_getTopParent(oWin) {
	if (oWin.dialogWidth != null)
		oWin = comm_getTopParent(oWin.dialogArguments);

	if (oWin.parent.opener != null)
		oWin = comm_getTopParent(oWin.parent.opener);

	if (oWin.location.pathname != oWin.parent.location.pathname)
		oWin = comm_getTopParent(oWin.parent);

	return (oWin);
}

function comm_callForm(strProgCode, strParams, asgWidth, asgHeight) {
	var width = 1024;
	var height = 768;
	if (asgWidth)
		width = asgWidth;
	if (asgHeight)
		height = asgHeight;

	var strPathName = location.pathname;
	if (comm_LRTrim(strParams) == "")
		strParams = "src=" + strPathName;
	else
		strParams += "&src=" + strPathName;

	var strSysCode = strProgCode.substr(2, 3);
	var strCurrSystem = "", strCurrProg = "";
	var strAry = location.pathname.split('/');
	for ( var i = 0; i < strAry.length; i++)
		if (strAry[i] == "wanhai") {
			strCurrSystem = strAry[i + 1];
			strCurrProg = strAry[i + 2];
			break;
		}

	if (strSysCode.toLowerCase() != strCurrSystem.toLowerCase()) {// 想辦法回portal去呼叫
		var obj = this.window;
		try {
			obj = comm_getTopParent(this.window);
			obj.checkProgList(strProgCode.toUpperCase(), strParams);
		} catch (e) {
			alert("You are calling different system.\r\n\r\nPlease swap your portal to Menu-tab first.\r\n If it still don't work, please Re-Login!");
		}
	} else {// 用ajax取得開啟參數
		// 先取得internet or intranet flag
		var strTemp = "/wanhai/whl-htm/checkIsIntranet.jsp?action=isintranet&"
				+ (new Date).getTime();
		strTemp = doXmlHttpRequest(strTemp); // return true or false

		// 再判斷是否有權限使用被呼叫的程式
		strTemp = doXmlHttpRequest("/wanhai/" + strSysCode.toLowerCase()
				+ "/comm_getUserProg.jsp?is_intranet=" + strTemp + "&old_prog="
				+ strCurrProg + "&new_prog=" + strProgCode);
		if (comm_LRTrim(strTemp) != ";") {
			var strOpenNum = comm_LRTrim(strTemp.split(';')[0]);
			var strOffice = comm_LRTrim(strTemp.split(';')[1]);
			// var strSize = "height=768,width=1024,";
			var strSize = "height=" + height + ",width=" + width + ",";
			strTemp = ("/wanhai/" + strSysCode + "/" + strProgCode + "/index.jsp?")
					.toLowerCase();
			if (strOpenNum == "0")
				strProgCode += (new Date).getTime();
			strParams += "&office_code=" + strOffice;

			var subWin = window
					.open(
							strTemp + strParams,
							strProgCode,
							strSize
									+ 'status=yes,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes');
			subWin.focus();
		} else
			alert('You have not been authorized to execute this program');
	}
}

function comm_isEmail(str) {
	rs = true;
	if (str.length > 0) {
		i = str.indexOf("@");
		j = str.indexOf(".", i);
		k = str.indexOf(",");
		kk = str.indexOf(" ");
		jj = str.lastIndexOf(".") + 1;
		len = str.length;

		if ((i <= 0) || (j <= (i + 1)) || (k != -1) || (kk != -1)
				|| (len - jj < 2) || (len - jj > 3)) {
			// alert("�刻撓�亦� �餃��萎辣 銝�迤蝣�!");
			// alert("The format of email address error!");
			rs = false;
		}
	} else {
		// alert("The email address blank!");
		rs = false;
	}
	return rs;
}

function comm_showRunning() {
	if (document.getElementById && document.createTextNode) {
		var formObj = document.getElementsByTagName("Form")[0];
		if (formObj == null) {
			alert('Pls Write Tag <form>');
		} else {
			var runningObj = document.getElementById('running');
			if (runningObj == null) {
				var divRunning = "<div id='running'>" + "<div id='notice'>"
						+ "<img src='../img/loading.gif'><br>" + "</div>"
						+ "</div>";
				formObj.insertAdjacentHTML("afterEnd", divRunning);
				var runningObj = document.getElementById('running');
				if (runningObj.style.display == ''
						|| runningObj.style.display == 'none') {
					runningObj.style.display = 'inline';
				}
			}
		}
	}
}

function comm_hideRunning() {
	if (document.getElementById && document.createTextNode) {
		if (document.getElementById('running') != null) {
			document.all.running.removeNode(true);
		}
		return true;
	}
}

function comm_startCal() {
	pageOpen = new Date();
}

function comm_stayCal() {
	pageClose = new Date();
	minutes = (pageClose.getMinutes() - pageOpen.getMinutes());
	seconds = (pageClose.getSeconds() * 1000 - pageOpen.getSeconds() * 1000);
	millseconds = (pageClose.getMilliseconds() - pageOpen.getMilliseconds());

	millsecondsClose = pageClose.getMinutes() * 60 * 1000
			+ pageClose.getSeconds() * 1000 + pageClose.getMilliseconds()
	millsecondsStart = pageOpen.getMinutes() * 60 * 1000
			+ pageOpen.getSeconds() * 1000 + pageOpen.getMilliseconds()

	alert((millsecondsClose - millsecondsStart) / 1000);
}
function comm_check_input(obj) {
	var bReturn = true;
	if (LRTrim(obj.value) == '') {
		alert(obj.name + ' can not be blank!');
		bReturn = false;
		obj.focus();
	}
	return (bReturn);
}
// =======隞乩��箏�function======
function do_resize() {
	comm_doResize();
}

function LRTrim(str) {
	return (comm_LRTrim(str));
}

function check_all_input(bolAlert) {
	return (comm_checkAllInput(bolAlert));
}

function check_type_input(bolAlert) {
	return (comm_checkType_input(bolAlert));
}

function check_type_textarea(bolAlert) {
	return (comm_checkType_textarea(bolAlert));
}

function check_type_select(bolAlert) {
	return (comm_checkType_select(bolAlert));
}

function showCalendarDialog(myObj, strRoot) {
	comm_showCalendarDialog(myObj, strRoot);
}

function showDialog(strURL, strHeight, strWidth) {
	return (comm_showDialog(strURL, strHeight, strWidth));
}

function do_unlock() {
	comm_doUnlock();
}

function AssembleParamByButton(obj) {
	return (comm_AssembleParamByButton(obj));
}

function AssembleParamByRadio(obj) {
	return (comm_AssembleParamByRadio(obj));
}

function AssembleParamByCheckBox(obj) {
	return (comm_AssembleParamByCheckBox(obj));
}

function div_extend(obj, bolShowAll) {
	comm_divExtend(obj, bolShowAll);
}

function to_upper() {
	comm_upper();
}

function isnum(obj) {
	return (comm_isnum(obj));
}

function ReplaceAll(strOrg, strFind, strReplace) {
	return (comm_replaceAll(strOrg, strFind, strReplace));
}

function isDate(str) {
	return (comm_isDate(str));
}

function get_date(obj) {
	comm_getDate(obj);
}

function add_row(obj) {
	comm_addRow(obj, null, null);
}

function del_row(obj) {
	comm_delRow(obj, null);
}
function check_input(obj) {
	return comm_check_input(obj);
}

function validateDate(obj, objName) {
	if (obj.value == 'undefined' || obj.value.length == 0) {
		return "";
	} else {
		if (!CheckyyyyMMdd(obj.value)) {
			return 'The format of ' + objName
					+ ' is wrong,input again please,for example 20080529 \n\n';
		} else {
			return "";
		}
	}

}

function validateDateTime(objOfDate, objOfDateName, objOfTime, objOfTimeName) {
	var vMessage = "";
	if (objOfDate.value.length != 0) {
		vMessage += validateDate(objOfDate, objOfDateName);
	}
	if (objOfTime.value.length != 0) {
		vMessage += validateTime(objOfTime.value, objOfTimeName);
	}

	/*
	 * else{ if(objOfDate.value.length !=0){ if(objOfTime.value.length !=0){
	 * if(validateTime(objOfTime.value,objOfTimeName)!=0){ vMessage
	 * +=validateTime(objOfTime.value,objOfTimeName); } } else{ vMessage +="The
	 * value of "+objOfTimeName+" is empty,input again please \n\n"; } } else{
	 * if(objOfTime.value.length!=0){ vMessage +="The value of "+objOfDateName+"
	 * is empty,input again please \n\n";
	 * if(validateTime(objOfTime.value,objOfTimeName)!=0){ vMessage
	 * +=validateTime(objOfTime.value,objOfTimeName); } } } }
	 */
	return vMessage;
}
function CheckDate(INDate) {

	// 年月日检验函数yyyy-MM-dd
	if (INDate == "" || INDate == "undefined") {
		return true;
	}
	subYY = INDate.substr(0, 4)
	if (isNaN(subYY) || subYY <= 0) {
		return true;
	}

	if (INDate.indexOf('-', 0) != -1) {
		separate = "-"
	} else {
		if (INDate.indexOf('/', 0) != -1) {
			separate = "/"
		} else {
			return true;
		}
	}
	area = INDate.indexOf(separate, 0)
	subMM = INDate.substr(area + 1, INDate.indexOf(separate, area + 1)
			- (area + 1))
	if (isNaN(subMM) || subMM <= 0) {
		return true;
	}
	if (subMM.length < 2) {
		subMM = "0" + subMM
	}
	area = INDate.lastIndexOf(separate)
	subDD = INDate.substr(area + 1, INDate.length - area - 1)
	if (isNaN(subDD) || subDD <= 0) {
		return true;
	}
	if (eval(subDD) < 10) {
		subDD = "0" + eval(subDD)
	}
	NewDate = subYY + "-" + subMM + "-" + subDD
	if (NewDate.length != 10) {
		return true;
	}
	if (NewDate.substr(4, 1) != "-") {
		return true;
	}
	if (NewDate.substr(7, 1) != "-") {
		return true;
	}

	var MM = NewDate.substr(5, 2);
	var DD = NewDate.substr(8, 2);
	if ((subYY % 4 == 0 && subYY % 100 != 0) || subYY % 400 == 0) {
		if (parseInt(MM) == 2) {
			if (DD > 29) {
				return true;
			}
		}
	} else {
		if (parseInt(MM) == 2) {
			if (DD > 28) {
				return true;
			}
		}
	}
	if (parseInt(MM) == 4 || parseInt(MM) == 6 || parseInt(MM) == 9
			|| parseInt(MM) == 11) {
		if (parseInt(DD) > 30) {
			return true;
		}
	} else {
		if (parseInt(DD) > 31) {
			return true;
		}
	}
	if (parseInt(MM) > 12) {
		return true;
	}

	return false;
}

function CheckyyyyMMdd(dayString) {
	// 年月日检验函数yyyyMMdd
	var digit = "0123456789";
	datelist = new Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if (dayString.length != 8)
		return (false);
	for (i = 0; i < 8; i++) {
		if (digit.indexOf(dayString.charAt(i), 0) == -1)
			return (false);
	}
	year = dayString.substr(0, 4); // 截取年部分
	month = dayString.substr(4, 2); // 截取月部分
	date = dayString.substr(6, 2); // 截取日部分
	if (year < 0001 || month > 12 || month < 1 || date > 31 || date < 1)
		return (false);
	if (date > datelist[month - 1])
		return (false);

	yyyy = eval(year);
	if (month == "02") {
		if ((yyyy % 400) == 0) {
			if (date > 29)
				return (false);
		} else if ((yyyy % 4) == 0 && (yyyy % 100) != 0) {
			if (date > 29)
				return (false);
		} else {
			if (date > 28)
				return (false);
		}
	}
	return (true);
}
/** ========validate Time format===========* */
function checkTime(time, nameOfTime) {
	// 时间校验函数
	var digit = "0123456789";
	rexEx = '^(([0]?[0-9])|([1]?[0-9])|([2]?[0-3])):([0-5]?[0-9]):([0-5]?[0-9])$';
	if (!time.match(rexEx)) {
		alert('The format of ' + nameOfTime
				+ ' is wrong,input again please,for example 00:00:00');
		return false;
	}
	return true;
}
/** ========validate the length of field===========* */
function validateLength(obj, objName, minValue, maxValue) {
	if (obj.value.length >= minValue && obj.value.length <= maxValue) {
		return "";
	} else {
		return objName + " value is greater than allowable maximum of "
				+ maxValue + " \n\n";
	}
}
function validateTime(time, timeName) {
	// 时间校验函数
	var digit = "0123456789";
	rexEx = '^(([0]?[0-9])|([1]?[0-9])|([2]?[0-3])):([0-5]?[0-9]):([0-5]?[0-9])$';
	if (!time.match(rexEx)) {
		return 'The format of ' + timeName
				+ ' is wrong,input again please,for example 00:00:00 \n\n';
	} else {
		return "";
	}
}
function ShowOrHideAsterisk(errorIDs, allIDs) {
	for ( var i = 0; i < allIDs.length; i++) {
		var obj = document.getElementById(allIDs[i]);
		if (hasElement(allIDs[i], errorIDs)) {
			obj.style.display = "";
		} else {
			obj.style.display = "none";
		}
	}

	return;
}
function hasElement(elem, errorIDs) {
	for ( var i = 0; i < errorIDs.length; i++) {
		if (errorIDs[i] == elem) {
			return true;
		}
	}
	return false;
}
/** ========validate Required and length===========* */
function validateField(obj, objName) {

	if (comm_LRTrim(obj.value) == "" || obj.value.length == 0) {
		return objName + " is required \n\n";
	} else {

		return "";
	}
}

function validateNum(obj, objName) {
	/*
	 * if(isNaN(obj.value) == true) { return objName + " must be numeric!"; }
	 * return "";
	 */
	if (0 == obj.value.trim().length)
		return "";
	var regExp = /^-?\d+(\.\d+)?$/;
	if (!regExp.test(obj.value)) {
		return objName + " must be numeric! \n\n";
	}
	return "";

}

function comm_hiddenAllInput() {
	var inputObj = document.getElementsByTagName("input");
	for ( var i = 0; i < inputObj.length; i++) {
		if (document.getElementById(inputObj.item(i).name) != null) {
			document.getElementById(inputObj.item(i).name).style.visibility = "hidden";
		}
	}
}

function comm_upload(path_token, sub_path, file_prefix, file_type, file_amount,
		file_size, bolflash) {
	var strParam = "";
	if (path_token == null || path_token == '')
		alert('You have to assign upload path');
	else {
		strParam += "&pathtoken=" + encodeURIComponent(path_token);
		strParam += "&subpath=" + encodeURIComponent(sub_path);
		strParam += "&fileprefix="
				+ (file_prefix == null ? "" : encodeURIComponent(file_prefix));
		strParam += "&filetype="
				+ (file_type == null ? "" : encodeURIComponent(file_type));
		strParam += "&filesize="
				+ (file_size == null ? "" : encodeURIComponent(file_size));
		strParam += "&fileamount="
				+ (file_amount == null ? "" : encodeURIComponent(file_amount));
		strParam += "&flash=" + (bolflash == null ? "false" : bolflash);

		var strURL = "/wanhai/whl-htm/checkIsIntranet.jsp?action=buildinguser&"
				+ (new Date).getTime();
		strURL = doXmlHttpRequest(strURL);

		if (comm_LRTrim(strURL) == 'true')
			strURL = "/wanhai/whl-prompt2/file_upload.jsp?"
					+ strParam.substring(1);
		else
			strURL = "/wanhai/whl-prompt/file_upload.jsp?"
					+ strParam.substring(1)

			// alert(strURL);
		return comm_showDialog(strURL, '250', '450');
	}
}

function comm_showJobName(id, alertStr, target_name) {
	var strFeatures = "dialogWidth=200px;dialogHeight=80px;scrollbars=auto;status=yes;";
	// var strURL="/wanhai/whl-htm/mdbParam.jsp"+"?"+(new
	// Date()).getUTCMilliseconds();
	if (target_name == undefined) {
		job_name = '';
	}
	var strURL = "/wanhai/whl-htm/mdbParam.jsp?job_name=" + job_name + "&"
			+ (new Date()).getUTCMilliseconds();
	var jobName = window.showModalDialog(strURL, window, strFeatures);
	if (jobName == undefined) {
		// jobName = " ";
		// 20110316 vincent :set default value to avoid the myjob error
		document.getElementById(id).value = (new Date()).getUTCMilliseconds();
	} else {
		document.getElementById(id).value = jobName;
	}
	alert(alertStr);
}

/*
 * 取得字符串的字節數,中文算3個byte
 */
String.prototype.lengthZH = function() {
	return (this.replace(/[^\u0000-\u00ff]/g, "aaa").length);
}
/* 計算對象的值的長度(中文按3個byte) */
function comm_validateLengthZH(obj, objName, minValue, maxValue, errMsg) {

	if (obj.value.lengthZH() >= minValue && obj.value.lengthZH() <= maxValue) {
		return "";
	} else {
		if (errMsg != undefined) {
			return errMsg;
		} else {
			return "Please check your " + objName + " value length \n\n";
		}
	}
}

// Trim all space in page
String.prototype.trim = function() {
	var re = /^[ \s]+|[\s ]+$/g;
	return function() {
		return this.replace(re, "");
	};
}();

function comm_trimAllInputs() {
	var inputs = document.getElementsByTagName('input');
	// for(var i in inputs)//edit by leonny @20090804
	for ( var i = 0; i < inputs.length; i++) {
		var obj = inputs[i];
		if (!obj.type || obj.type.toLowerCase() != 'text')
			continue;
		obj.value = obj.value.trim();
	}
	var textareas = document.getElementsByTagName('textarea');
	// for(var i in textareas)//edit by leonny @20090804
	for ( var i = 0; i < textareas.length; i++) {
		var obj = textareas[i];
		if (typeof (obj) != 'object')
			continue;
		obj.value = obj.value.trim();
	}
}

// check date format is YYYYMM
function comm_checkYYYYMM(yyyymm, name) {
	try {
		if (!/\d{6}/.test(yyyymm))
			throw '';
		if (yyyymm.substring(4) * 1 > 12)
			throw '';
	} catch (e) {
		return 'the value of ' + name + ' is invalid \n\n';
	}
	return '';
}

// check date range
function comm_checkAYMgtBYM(a, aName, b, bName) {
	var ca = comm_checkYYYYMM(a, aName);
	if (0 != ca.length)
		return ca;
	var cb = comm_checkYYYYMM(b, bName);
	if (0 != cb.length)
		return cb;
	if (a * 1 > b * 1) {
		return aName + ' can\'t be greater than ' + bName + "\n\n";
	}
	return '';
}

function comm_checkPoints(value, p, name) {
	if ('number' != typeof (p))
		return 'the second parameter of function checkPoints is invalid';
	var pattern = eval('/^\\d+\\\.\\d{' + p + '}$/');
	if (!pattern.test(value.toString()))
		return 'the value of ' + name + ' is invalid \n\n';
	return '';
}

// show version
function __showVersion() {
	document.title += " - " + __version;
}

// Add Load Listener, Called by showVersion()
function addLoadListener(fn) {
	if (typeof window.addEventListener != 'undefined') {
		window.addEventListener('load', fn, false);
	} else if (typeof document.addEventListener != 'undefined') {
		document.addEventListener('load', fn, false);
	} else if (typeof window.attachEvent != 'undefined') {
		window.attachEvent('onload', fn);
	} else {
		var oldfn = window.onload;
		if (typeof window.onload != 'function') {
			window.onload = fn;
		} else {
			window.onload = function() {

				oldfn();
				fn();

			};
		}
	}
}
function showVersion(version) {
	__version = version; // Version number
	__debug = true; // True:Show the version number;false don't show the version
	// number

	if (__debug)
		addLoadListener(__showVersion);
}

function comm_checkAYMDgtBYMD(a, aName, b, bName) {
	var ca = comm_isDate(a, aName);
	if (!ca)
		return '';
	var cb = comm_isDate(b, bName);
	if (!cb)
		return '';
	if (a * 1 > b * 1) {
		return aName + ' can\'t be greater than ' + bName + " \n\n ";
	}
	return '';
}
// vincent add for show page num
var _pageNum = new Array();

function comm_totalrecord() {
	// alert(document.getElementById("masterForm:totalRecord").innerHTML);
	document.getElementById("masterForm:totalRecord").innerHTML = parseInt(document
			.getElementById("masterForm:totalRecord").innerHTML)
			+ comm_table_record("masterForm:columnList");
}

function comm_countRecord(pageNum) {
	// vincent 20090826 add
	return true;
	var isExist = "N";
	for ( var i = 0; i < _pageNum.length; ++i) {
		if (_pageNum[i] == pageNum) {
			isExist = "Y"
		}
	}
	if (isExist == "N") {
		_pageNum.push(pageNum);
		comm_showtotalRecord(comm_table_record("masterForm:columnList"));
		// comm_totalrecord();
	}
}

function comm_showtotalRecord(record) {
	if (_pageNum.length == 0) {
		_pageNum.push("1");
	}
	if (document.getElementById("masterForm:totalRecord") != null) {
		var totalRecord = parseInt(document
				.getElementById("masterForm:totalRecord").innerHTML)
				+ record;
		document.getElementById("masterForm:totalRecord").innerHTML = totalRecord;
	}
}
function comm_table_record(obj) {
	if (document.getElementById(obj) != null) {
		return document.getElementById(obj).rows.length - 1;// minus head column
	} else {
		return 0;
	}
}
function initial() {
	comm_initial();
}
function comm_initial() {
	_pageNum.clear();
	comm_doResize('_child');
	// vincent mark for bug
	// if
	// (parseInt(document.getElementById("masterForm:totalRecord").innerHTML)==0){
	// for old version初始值:
	// comm_showtotalRecord(comm_table_record("masterForm:columnList"));
	// }
}
function comm_callExpired(url) {
	// for ie7 issue, to clear all history record
	var len = history.length;
	history.go(-len);
	location.replace(url);
}
/*
 * 
 * A4J.AJAX.onError = function(req,status,message){ window.alert("Custom
 * onError:\n"+message); }
 * 
 * A4J.AJAX.onExpired = function(loc,expiredMsg){ var nowPage=location.href;
 * str=nowPage.split("/"); var index_page=""; //URL
 * Format:http://localhost:8080/wanhai/ars/wdars138/xxx/xx/xx.jsp
 * //故只要取到第6各區段如http://localhost:8080/wanhai/ars/wdars138/ + index.jsp即可
 * for(i=0;i<6;i++)index_page+=str[i]+"/" index_page+="index.jsp";
 * 
 * if(window.confirm("Session Expired:\nYou must return to main
 * page:\n"+index_page)){
 * 
 * return index_page; } else { return false; } }
 */
String.prototype.hasPercent = function() {
	return (this.indexOf('%') != -1);
}
String.prototype.hasUnderline = function() {
	return (this.indexOf('_') != -1);
}

/*
 * 檢查Like查詢欄位是否包含 "%" ,"_" @id: UI 的 Control id @columnName: 欄名
 */

function comm_checkPersent(id, columnName) {
	/*
	 * var o = document.getElementById(id); return (o.value.hasPercent()? ""
	 * :"\"LIKE\" Condition of "+columnName+" must contain \"%\"");
	 */

	var o = document.getElementById(id);
	var r = false;
	r = o.value.hasPercent();
	var u = o.value.hasUnderline();
	if (r || u) {
		return "";
	} else {
		return ("\"LIKE\" Condition of " + columnName + " must contain \"%\" or \"_\" ");
	}

}
function comm_tri_iframe(url) {

	var dform = document.getElementsByTagName("form")[0];
	if (document.getElementsByName("trigger_iframe") != null
			&& document.getElementsByName("trigger_iframe").length > 0) {
		document.all.trigger_iframe.removeNode(true);
	}
	dform
			.insertAdjacentHTML(
					"beforeEnd",
					"<iframe frameborder='0' height='0' width='0' scrolling='no' name=trigger_iframe style='visibility:hidden'></iframe>");
	document.trigger_iframe.location = url;
}
// =========Under code for check update is modified
// Page Data
var _inputsData;
var _textareasData;
var _selectsData;

/*
 * save initial data when page load
 * 
 */

function comm_saveFiledsData() {
	var inputs = document.getElementsByTagName("input");
	var textareas = document.getElementsByTagName("textarea");
	var selects = document.getElementsByTagName("select");
	_inputsData = new Array();
	var index = 0;
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].id.indexOf("subViewUpdate:modifyForm:") == 0) {
			if (inputs[i].type == "radio" || inputs[i].type == "checkbox") {
				_inputsData[index] = inputs[i].checked;
			} else {
				_inputsData[index] = inputs[i].value;
			}
			index++;
		}
	}
	_textareasData = new Array();
	index = 0;
	for ( var j = 0; j < textareas.length; j++) {
		if (textareas[j].id.indexOf("subViewUpdate:modifyForm:") == 0) {
			_textareasData[index] = textareas[j].value;
			index++;
		}
	}
	_selectsData = new Array();
	index = 0;
	for ( var k = 0; k < selects.length; k++) {
		if (selects[k].id.indexOf("subViewUpdate:modifyForm:") == 0) {
			_selectsData[index] = selects[k].value;
			index++;
		}
	}
}
/*
 * judge weather the page data has been modified
 */
function comm_checkModification() {
	var inputs = document.getElementsByTagName("input");
	var textareas = document.getElementsByTagName("textarea");
	var selects = document.getElementsByTagName("select");
	var index = 0;
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].id.indexOf("subViewUpdate:modifyForm:") == 0) {
			if (inputs[i].type == "radio" || inputs[i].type == "checkbox") {
				if (inputs[i].checked != _inputsData[index]) {
					return true;
				}
			}
			if (inputs[i].type == "text"
					&& _inputsData[index] != inputs[i].value) {
				return true;
			}
			index++;
		}
	}
	index = 0;
	for ( var j = 0; j < textareas.length; j++) {
		if (textareas[j].id.indexOf("subViewUpdate:modifyForm:") == 0) {
			if (_textareasData[index] != textareas[j].value) {
				return true;
			}
			index++;
		}

	}
	index = 0;
	for ( var k = 0; k < selects.length; k++) {
		if (selects[k].id.indexOf("subViewUpdate:modifyForm:") == 0) {
			if (_selectsData[index] != selects[k].value) {
				return true;
			}
			index++;
		}

	}
	return false;
}

// =========end Check Update MOdify

/*
 * GDS提出以下來解決TextArea的輸入問題 1、此方法支持中文、英文等的長度有效限制。
 * 2、同樣支持大寫的轉換。（在屏蔽com.js中document.onkeypress時） 3、支持在達到最大長度後，有選擇文字時可以再輸入。
 * 4、允許在TextArea中換行回車，並正確計算實際文字長度。 5、不采用截取，而是直接不可輸入，不產生閃爍現象。 6、在IE，Firefox下測試通過
 */
// Keep nothing to do in document.onkeypress when focus TextArea
function comm_undoUpperCase() {
}
// Call at onBlur Event , to redo UpperCase
function comm_redoUpperCase() {
	document.onkeypress = comm_upperCase;
}
function comm_replaceNRStr(str) {
	return str.replace(/\r\n/ig, "").replace(/\n\r/ig, "").replace(/\r/ig, "")
			.replace(/\n/ig, "");
}
function comm_getSelectedText(obj) {
	var selectedText;
	if (window.getSelection)
		selectedText = comm_getTextFieldSelection(obj);
	else
		selectedText = document.selection.createRange().text;
	return selectedText;
}
function comm_getTextFieldSelection(obj) {
	// var oEvent=arguments.callee.caller.arguments[0];
	if (obj.selectionStart != undefined && obj.selectionEnd != undefined)
		return obj.value.substring(obj.selectionStart, obj.selectionEnd);
	else
		return "";
}
function comm_selectAreaFunc(obj) {
	// var text1 =
	// document.all?obj.ownerDocument.selection.createRange().text:obj.ownerDocument.getSelection();
	var selectText = comm_getSelectedText(obj);
	var result = (selectText == null || selectText.length == 0 || selectText == "");
	return result;
}
function comm_limitAreaMaxLength(e, obj, maxLength, doUpper) {
	// Before process, must be reset document.onkeypress, else will be a
	// conflict with com.js
	document.onkeypress = comm_undoUpperCase;
	// If current length > maxLength-1 and not select any char, can't input any
	var value = obj.value;
	// Before compute lengthZH(), replace \r , \n
	value = comm_replaceNRStr(value);
	var needUpper = true;
	if (value.lengthZH() > maxLength - 3) {
		var iKeyCode = window.event ? e.keyCode : e.which;
		if (comm_selectAreaFunc(obj)) {
			if (value.lengthZH() > maxLength - 1) {
				// Before 32 is Control Key, Between 32 and 127 is E-Char, After
				// 127 is W-Char
				if (iKeyCode >= 32) {
					needUpper = false;
					window.event ? e.returnValue = false : e.preventDefault();
				}
			} else {
				// if Last input is not enough for a W-Char
				if (iKeyCode >= 127) {
					needUpper = false;
					window.event ? e.returnValue = false : e.preventDefault();
				}
			}
		}
	}
	if (!doUpper)
		needUpper = doUpper;
	if (needUpper) {
		// Beyond, toUpperCase if it's E-Char
		e = e || window.event;
		var code = (e.charCode || e.keyCode);
		if (code >= 97 && code <= 122) {
			if (e.charCode) {
				var Event = document.createEvent("KeyEvents");
				Event.initKeyEvent("keypress", 0, 0, null, 0, 0, 0, 0, 0,
						(code - 32));
				e.target.dispatchEvent(Event);
				e.preventDefault();
			} else {
				e.keyCode -= 32;
			}
		}
	}
}
/*
 * for 日期模糊查詢
 */
function comm_validateDateWithPer(obj, objName) {
	return comm_validateDateWithPer(obj, objName, 8);
}

// 新增此一 function
function comm_validateDateWithPer(obj, objName, limitLength) {
	if (obj.value == 'undefined' || obj.value.length == 0) {
		return "";
	} else {
		if (!comm_CheckyyyyMMddWithPer(obj.value)) {
			var returnMessage = 'The format of ' + objName
					+ ' is not correct, it must be:';
			if (limitLength == 8) {
				returnMessage += 'YYYYMMDD or YYYY% or YYYYMM% or %';
			} else if (limitLength == 6) {
				returnMessage += 'YYYYMM or YYYY% or %';
			} else if (limitLength == 4) {
				returnMessage += 'YYYY or %';
			}
			returnMessage += '\n\n';
			return returnMessage;
		} else {
			return "";
		}
	}
}

function comm_CheckyyyyMMddWithPer(dayString) {
	var p = /^[1-9][0-9]{3}((\%)|(((11)|(12)|(0[1-9]))\%)|(((11)|(12)|(0[1-9]))((0[1-9])|([1-2]\d)|(3[0-1]))))$/;
	var result = p.test(dayString);
	if (result && 8 == dayString.length) {
		var y = dayString.substr(0, 4);
		var m = dayString.substr(4, 2) - 1;
		var d = dayString.substr(6, 2);
		var date = new Date(y, m, d);
		result = (date.getDate() == d);
	} else if (dayString == '%') {
		result = true;
	}
	return result;
}
function comm_getCurrRowObj(obj, col) {
	if (col > obj.parentElement.parentElement.cells.length - 1) {
		alert('Please input valid column seat!');
		return false;
	} else {
		return obj.parentElement.parentElement.cells[col];
	}
}
function js_alltotalRecord(data) {
	if (data.length != 0) {
		msg.render(data);
	}
}
function doXmlHttpRequest(url) {
	var http_request = false;

	if (window.XMLHttpRequest) {
		if (window.ActiveXObject) // IE7
			http_request = new XMLHttpRequest();
		else
			// Opera, Safari, Firefox...
			http_request = new XMLHttpRequest();
	} else {
		// IE6,IE5...
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}

	if (!http_request) {
		alert("can't initial XMLHTTP");
		return false;
	}

	http_request.open('GET', url, false); // 同步
	http_request.send(null);
	return (http_request.responseText);
}

function comm_upload_local(jndi_name, path_token, sub_path, file_prefix,
		file_type, file_amount, office_code) {
	var strURL = "", strParam = "";
	if (jndi_name == null || comm_LRTrim(jndi_name) == '') {
		alert("jndi_name didn't assign");
		return;
	}

	if (path_token == null || path_token == '')
		alert('You have to assign upload path');
	else {
		// 1.檢查是否要upload到local
		if (office_code != null && office_code != '') {
			var strSysCode = "";
			var strTempAry = document.location.pathname.split('/');
			for ( var i = 0; i < strTempAry.length; i++)
				if (strTempAry[i] == "wanhai") {
					strSysCode = strTempAry[i + 1].toUpperCase();
					break;
				}

			if (strSysCode == '') {
				alert('無法取得system code,暫時無法提供上傳');
				return;
			}

			// 2.取得local address或顯示無法上傳的訊息
			strURL = "/wanhai/whl-prompt/getURL.jsp?ds_name=" + jndi_name
					+ "&sys_code=" + strSysCode + "&office_code=" + office_code
					+ "&" + (new Date).getTime();
			strURL = doXmlHttpRequest(strURL);
			if (comm_LRTrim(strURL) == '') {
				alert('local report server異常,暫時無法提供上傳');
				return;
			} else
				strURL = "http://" + comm_LRTrim(strURL);
		}
		strParam += "&pathtoken=" + encodeURIComponent(path_token);
		strParam += "&subpath=" + encodeURIComponent(sub_path);
		strParam += "&fileprefix="
				+ (file_prefix == null ? "" : encodeURIComponent(file_prefix));
		strParam += "&filetype="
				+ (file_type == null ? "" : encodeURIComponent(file_type));
		strParam += "&filesize=0";
		strParam += "&fileamount="
				+ (file_amount == null ? "" : encodeURIComponent(file_amount));

		return comm_showDialog(strURL + "/wanhai/whl-prompt/file_upload.jsp?"
				+ strParam.substring(1), '250', '450');
	}
}

function comm_openfile(data) {
	/*
	 * msg.resolveMsgs(data);
	 * 
	 * msgcounter=msg.messages.length; for(var i=0;i<msgcounter;i++){
	 * amsg=msg.messages[i]; alert(i+':'+amsg.msgDesc); }
	 * if(data[0]=='encode_ok'){ var encodeString =msg.messages[0].msgDesc; var
	 * servlet="FileInvoke_Servlet"; var
	 * strurl="/wanhai/whl-prompt/file/"+servlet+"?";
	 * comm_tri_iframe(strurl+encodeString);
	 * //window.open(strurl+encodeString,"","status=no,toolbar=no,resizable=no,scrollbars=no,menubar=no,width=1,height=1,left=0,top=0");
	 * }else{ msg.render(data); }
	 */
}
function comm_openfileMsg(data) {
	msg.render(data);
}

function comm_show_div(mapping_object, div_name) {
	var posY = comm_posY(mapping_object.id);
	var posX = comm_posX(mapping_object.id);
	document.getElementById(div_name).style.top = posY + 25
	document.getElementById(div_name).style.left = posX;
	document.getElementById(div_name).style.background = "#116B07";
	document.getElementById(div_name).style.display = '';
}
function comm_close_div(div_name) {
	document.getElementById(div_name).style.display = 'none';
}
function comm_posX(objID) {
	var elmt;
	if (typeof objID == "object") {
		elmt = objID;
	} else {
		elmt = document.getElementById(objID);
	}
	var x = 0;
	for ( var e = elmt; e; e = e.offsetParent) {
		x += e.offsetLeft;
	}
	for (e = elmt.parentNode; e && e != document.body; e = e.parentNode) {
		if (e.scrollLeft)
			x -= e.scrollLeft;
	}
	return x;
}

function comm_posY(objID) {
	var elmt;
	if (typeof objID == "object") {
		elmt = objID;
	} else {
		elmt = document.getElementById(objID);
	}
	var y = 0;
	for ( var e = elmt; e; e = e.offsetParent) {
		y += e.offsetTop;
	}
	for (e = elmt.parentNode; e && e != document.body; e = e.parentNode) {
		if (e.scrollTop)
			y -= e.scrollTop;
	}
	return y;
}

function comm_callDetail(cells_Index, children_Index, tablename) {
	var tblobj = document.getElementById(tablename);
	if (tblobj == null)
		tblobj = document.getElementById("masterForm:columnList");
	if (document.readyState == "complete" || document.addEventListener) {
		if (tblobj.rows[1] != null) {
			tblobj.rows[1].cells[cells_Index].children[children_Index].click();
		}
	}
}

function comm_isDate_DateTime(str, label) {
	var arr = str.split(" ", 2);
	var strDate = arr[0];
	var strTime = arr[1];
	var len = strDate.length;
	// Date
	var reg = /^(\d{0,4})(-|\/|)(\d{1,2})(-|\/|)(\d{1,2})/;
	var r = reg.exec(strDate);
	if (r == null || len != 8) {
		return "[" + label + "]:The Date is not correct, it must be:YYYYMMDD";
	} else {
		var d = new Date(r[1], r[3] - 1, r[5]);
		if (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
				&& d.getDate() == r[5]) {

		} else {
			return "[" + label
					+ "]:The Date is not correct, it must be:YYYYMMDD";
		}
	}
	// Time
	if (strTime != null) {
		var hhmiss = strTime.split(":");
		if (hhmiss.length != 3) {
			return "[" + label + "]:time format must be hour:minute:second"
		}
		var hh = hhmiss[0];
		var mi = hhmiss[1];
		var ss = hhmiss[2];
		if (isNaN(hh) || isNaN(mi) || isNaN(ss) || hh < 0 || hh > 23 || mi < 0
				|| mi > 59 || ss < 0 || ss > 59) {
			return "[" + label
					+ "]:The time is not correct, it must be:HH24MISS";
		}
	}
	return "";
}
// get same other value in same row
function comm_rowValue(obj, cell, child) {
	var columnValue;
	var _tagName
	if (obj.parentElement.tagName == "TD") {
		_tagName = obj.parentElement.parentElement.cells[cell].childNodes[child].tagName;
		if (_tagName == "INPUT" || _tagName == "TEXTAREA") {
			columnValue = obj.parentElement.parentElement.cells[cell].childNodes[child].value;
		} else if (_tagName == "SELECT") {
			var _selectObj = obj.parentElement.parentElement.cells[cell].childNodes[child];
			columnValue = _selectObj.options[_selectObj.selectedIndex].text
		} else {
			columnValue = obj.parentElement.parentElement.cells[cell].innerHTML;
		}
	} else {
		alert('Your tag must between in <TD> tag!');
	}
	return columnValue;
}
// for report use
function getRadioValue(eleName) {
	var radioAry = document.queryForm.elements[eleName];
	var radioValue;
	for (i = 0; i < radioAry.length; i++) {
		if (radioAry[i].checked)
			radioValue = radioAry[i].value;
	}
	return radioValue;
}

function comm_loading() {
	seq = new Date().getTime().toString();
	wait_page_bg = document.createElement("div");
	wait_page_bg.setAttribute("id", "wait_page_bg" + seq);
	wait_page_bg.style.background = "#000";
	wait_page_bg.style.width = "110%";
	wait_page_bg.style.height = "200%";
	wait_page_bg.style.position = "absolute";
	wait_page_bg.style.top = "0";
	wait_page_bg.style.left = "0";
	wait_page_bg.style.zIndex = "500";
	wait_page_bg.style.opacity = "0.3";
	wait_page_bg.style.filter = "Alpha(opacity=30)";
	document.body.appendChild(wait_page_bg);

	main_div = document.createElement("div");
	main_div.setAttribute("id", "alert" + seq);
	main_div.style.display = "block";
	main_div.style.border = "1px solid #369";
	main_div.style.position = "absolute";
	main_div.style.width = "400px";
	main_div.style.height = "30px";
	main_div.style.background = "#e2ecf5";
	main_div.style.zIndex = "1000";
	main_div.style.top = "50%";
	main_div.style.left = "50%";
	main_div.style.marginTop = "-75px";
	main_div.style.marginLeft = "-150px";

	var wait_message = document
			.createTextNode(" Your request is being processed, please wait.. or  ");

	main_div.appendChild(wait_message);
	main_href = document.createElement("A");
	main_href.setAttribute("href", "#");
	main_href.appendChild(document.createTextNode("close"));
	main_href.onclick = function() {
		wait_page_bg.style.display = "none";
		main_div.style.display = "none";
	};
	main_div.appendChild(main_href);
	document.body.appendChild(main_div);
}
// wennie add below function,for multitable
function closeExtraAction() {
	try {
		do_closeExtraAction();
	} catch (e) {
	}
}

function _getCurrentSystem() {
	var strCurrSystem = "";
	var strAry = location.pathname.split('/');
	for ( var i = 0; i < strAry.length; i++)
		if (strAry[i] == "wanhai") {
			strCurrSystem = strAry[i + 1];
			break;
		}
	return strCurrSystem;
}
function comm_autoEnter(id) {
	document.onkeydown = function(event) {
		var code;
		var e;
		if (document.all) {
			if (!event) {
				e = window.event;
				code = e.keyCode;
			}
		} else if (event.which) {
			e = event;
			code = event.which;
		}
		if (msgHasRender == true) {
			if (code == 13) {
				document.getElementById("msg_closeButton").click();
			} else {
				return false;
			}
		} else if (code == 13) {
			if (id) {
				var targetBtn = document.getElementById(id)
				targetBtn.click();
			}
		}
	};
}

function comm_upload_move_client(path_token, sub_path, file_prefix, file_type,
		file_amount, client_dest, bolflash, client_src) {
	if (bolflash == null)
		bolflash = false;
	var aryEntries = comm_upload(path_token, sub_path, file_prefix, file_type,
			file_amount, '128000', bolflash);
	if (aryEntries != null) {
		var strSrcPath = "";
		if (bolflash) {// get client path
			if (client_src == null)
				client_src = '';
			while (comm_LRTrim(strSrcPath) == "") {
				strSrcPath = prompt(
						'Please enter/confirm the full path of those files you selected',
						client_src);
				if (strSrcPath == null)
					strSrcPath = "";
				else
					strSrcPath = comm_LRTrim(strSrcPath);
			}
			if (strSrcPath.lastIndexOf("\\") != strSrcPath.length - 1)
				strSrcPath += "\\";
		}

		var strSrcFiles = "";
		for ( var i = 0; i < aryEntries.length; i++) {
			if (aryEntries[i].state == 'done')
				strSrcFiles += '<cmd>cmd /c move /Y "' + strSrcPath
						+ aryEntries[i].fileName + '" "' + client_dest + '"';
		}
		if (strSrcFiles.length > 0)
			comm_clientCMD(strSrcFiles);
	}
	return aryEntries;
}

function comm_clientCMD(strCmds) {
	try {
		document.all._bottom.removeNode(true);
	} catch (e) {
	}

	document.body.insertAdjacentHTML("beforeEnd",
			"<div id='_bottom' style='display:none'></div>");
	var d = document.getElementById("_bottom");
	d.insertAdjacentHTML("beforeEnd",
			"<form action='/wanhai/whl-htm/exec_command.jsp' method='post' id='_jnlp'>"
					+ "<input type='text' name='cmd' value='"
					+ encodeURIComponent(strCmds) + "' />" + "</form>");
	document.getElementById("_jnlp").submit();
}
