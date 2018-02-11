var isNeedLoad = {ghUnProcessed: true, ghProcessed: true, ghCanceled: true,
	fcUnProcessed: true, fcProcessed: true, fcCanceled: true,
	tjUnProcessed: true, tjProcessed: true, tjCanceled: true};
var isFirstLoad = true;

$(document).ready(function (e) {
	triggerLoad($('.dropdown-menu:eq(0) li:eq(0)'));
});

//触发选项
function triggerLoad(obj) {

	var ahref = $(obj).find('a').attr('href');
	if (isNeedLoad[ahref.substr(1)]) {
		$('#btn-back').css('display', 'none');
		if (ahref.substring(1, 3) === "tj") {
			modalSwitch = false;
			$('#identityModal').find('#ahref').val(ahref);
			$('#identityModal').modal('show');
		} else {
			$.get(getOptionUrl(ahref, true), function (data) {
				afterLoad(data, ahref);
				if (isFirstLoad) {
					isFirstLoad = false;
					adjustKeyHeight('#ghUnProcessed');
				}
			});
		}
	}
}

//输入身份证按确定之后
function aftertrInput() {
	modalSwitch = false;
	$('#btn-back').css('display', 'none');
	var getHref = $('#identityModal').find('#ahref').val();
	var identity = $('#identityModal').find('#identity').val();
	$.get(getOptionUrl(getHref, true) + "?identity=" + identity, function (data) {
		$('#identityModal').modal('hide');
		afterLoad(data, getHref);
	});
}

//调入我的预约之后
function afterLoad(data, href) {
	$(href).find('.row').remove();
	var subject = href.substring(1, 3);
	if (subject === 'gh') {
		afterLoadRegistration(data, href);
	} else if (subject === 'fc') {
		afterLoadReexamination(data, href);
	} else if (subject === 'tj') {
		afterLoadHealthExamination(data, href);
	}
}

//调入挂号预约之后
function afterLoadRegistration(data, href) {
	var len = data.all.length;
	if (len > 0) {
		var isProcessed = href.substr(3) === "Processed" ? true : false;
		var departmentCodes = {}, doctorCodes = {}, departmentNames, doctorNames;
		var curCode;
		for (var i = 0; i < len; i++) {
			curCode = data.all[i].departmentCode;
			if (departmentCodes['code' + curCode] === undefined)
				departmentCodes['code' + curCode] = curCode;
			curCode = data.all[i].doctorCode;
			if (doctorCodes['code' + curCode] === undefined)
				doctorCodes['code' + curCode] = curCode;
		}
		fnAJAX(getOptionUrl(href) + '/department', "POST", false, {codes: obj2Array(departmentCodes)}, function (data) {
			departmentNames = data.department;
		});
		fnAJAX(getOptionUrl(href) + '/doctor', "POST", false, {codes: obj2Array(doctorCodes)}, function (data) {
			doctorNames = data.doctor;
		});
		for (var i = 0; i < len; i++) {
			var row = '<div class="row"></div>';
			row = $(row).append('<div class="col-xs-' + (isProcessed ? '12' : '10') + ' myContent">'
					+ getNameByCode(data.all[i].departmentCode, departmentNames, "name") + ', '
					+ getNameByCode(data.all[i].doctorCode, doctorNames, "name,title") + ', '
					+ date2String(new Date(data.all[i].targetTime), true) + '</div>');
			if (!isProcessed) {
				row = $(row).append('<div class="col-xs-2 text-center myFnKey">'
						+ '<span class="my-panel-default" onclick="operatingRow('
						+ data.all[i].id + ',this,\'' + getOperatingText(href) + '\')">'
						+ getOperatingText(href, 'text') + '</span></div>');
			}
			$(href).append(row);
		}
		isNeedLoad[href.substr(1)] = false;
		$('#btn-back').css('display', 'block');
	} else {
		mdAlert("提示：", "此页挂号预约，无记录！");
	}
}

//调入复查预约之后
function afterLoadReexamination(data, href) {
	var len = data.all.length;
	if (len > 0) {
		var isProcessed = href.substr(3) === "Processed" ? true : false;
		var itemIds = {}, itemNames;
		for (var i = 0; i < len; i++) {
			var idArr = data.all[i].chosenReexaminationItems;
			var jl = idArr.length;
			var curId;
			for (var j = 0; j < jl; j++) {
				curId = idArr[j];
				if (itemIds['id' + curId] === undefined)
					itemIds['id' + curId] = curId;
			}
		}
		fnAJAX(getOptionUrl(href) + '/item', "POST", false, {ids: obj2Array(itemIds)}, function (data) {
			itemNames = data.item;
		});
		for (var i = 0; i < len; i++) {
			var row = '<div class="row"></div>';
			var content = '<div class="col-xs-' + (isProcessed ? '12' : '10') + ' myContent"></div>';
			var idArr = data.all[i].chosenReexaminationItems;
			var jl = idArr.length;
			for (var j = 0; j < jl; j++) {
				content = $(content).append('<div class="innerContent">'
						+ getNameById(idArr[j], itemNames, "name,code") + ', '
						+ date2String(new Date(data.all[i].targetTime)) + '</div>');
			}
			row = $(row).append(content);
			if (!isProcessed) {
				row = $(row).append('<div class="col-xs-2 text-center myFnKey"'
						+ '><span class="my-panel-default" onclick="operatingRow('
						+ data.all[i].id + ',this,\'' + getOperatingText(href) + '\')">'
						+ getOperatingText(href, 'text') + '</span></div>');
			}
			$(href).append(row);
		}
		isNeedLoad[href.substr(1)] = false;
		$('#btn-back').css('display', 'block');
	} else {
		mdAlert("提示：", "此页复查预约，无记录！");
	}
}

//调入体检预约之后
function afterLoadHealthExamination(data, href) {
	var len = data.all.length;
	if (len > 0) {
		var packageIds = {}, itemIds = {}, packageNames, itemNames;
		for (var i = 0; i < len; i++) {
			var idArr = data.all[i].chosenExaminationPackages;
			var jl = idArr.length;
			var curId;
			for (var j = 0; j < jl; j++) {
				curId = idArr[j];
				if (packageIds['id' + curId] === undefined)
					packageIds['id' + curId] = curId;
			}
			var idArr = data.all[i].chosenExaminationItems;
			var jl = idArr.length;
			for (var j = 0; j < jl; j++) {
				curId = idArr[j];
				if (itemIds['id' + curId] === undefined)
					itemIds['id' + curId] = curId;
			}
		}
		fnAJAX(getOptionUrl(href) + '/package', "POST", false, {ids: obj2Array(packageIds)}, function (data) {
			packageNames = data.package;
		});
		fnAJAX(getOptionUrl(href) + '/item', "POST", false, {ids: obj2Array(itemIds)}, function (data) {
			itemNames = data.item;
		});
		for (var i = 0; i < len; i++) {
			var row = '<div class="row"></div>';
			var content = '<div class="col-xs-12 myContent"></div>';
			var idArr = data.all[i].chosenExaminationPackages;
			var jl = idArr.length;
			for (var j = 0; j < jl; j++) {
				content = $(content).append('<div class="innerContent">'
						+ getNameById(idArr[j], packageNames, "name") + ', '
						+ date2String(new Date(data.all[i].targetTime)) + '</div>');
			}

			var idArr = data.all[i].chosenExaminationItems;
			var jjl = idArr.length;
			for (var j = 0; j < jjl; j++) {
				content = $(content).append('<div class="innerContent">'
						+ getNameById(idArr[j], itemNames, "name") + ', '
						+ date2String(new Date(data.all[i].targetTime)) + '</div>');
			}
			row = $(row).append(content);
			$(href).append(row);
		}
		isNeedLoad[href.substr(1)] = false;
		$('#btn-back').css('display', 'block');
	} else {
		mdAlert("提示：", "此页体检预约，无记录！");
	}
}

//标签页显示之后触发
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	var href = e.target.toString();
	adjustKeyHeight(href.substr(href.indexOf('#')));
});

//功能键调整高度
function adjustKeyHeight(href) {
	$(href).find('.row').each(function () {
		var keyObj = $(this).find('.myFnKey');
		var contentHeight = $(this).find('.myContent').css('height');
		var difference = px2int(contentHeight) - px2int(keyObj.css('height'));
		if (difference > 1) {
			difference = parseInt(difference / 2);
			difference += px2int(keyObj.css('margin-top'));
			difference += px2int(keyObj.css('padding-top'));
			keyObj.css('height', contentHeight);
			keyObj.css('padding-top', difference + 'px');
		}
	});
}
function px2int(str) {
	return parseInt(str.substring(0, str.indexOf('px')));
}

//获取URL
function getOptionUrl(href, isUrl) {
	var url = "";
	var subject = href.substring(1, 3);
	var process = href.substr(3);
	if (subject === 'gh') {
		url = "registration" + (isUrl ? "/" + process : "");
	} else if (subject === 'fc') {
		url = "reexamination" + (isUrl ? "/" + process : "");
	} else if (subject === 'tj') {
		url = "healthExamination" + (isUrl ? "/" + process : "");
	}
	return url;
}

//获取操作text
function getOperatingText(href, rtnType) {
	var value = "";
	var process = href.substr(3);
	if (process === 'UnProcessed') {
		value = (rtnType === "text") ? "取消" : "cancel";
	} else if (process === 'Canceled') {
		value = (rtnType === "text") ? "恢复" : "recover";
	}
	return value;
}

//对象转数组
function obj2Array(obj) {
	var arr = [];
	$.each(obj, function (i, v) {
		arr.push(v);
	});
	return arr;
}

//根据id查找器name
function getNameById(id, arrObj, key) {
	var name = "";
	var keyArr = key.split(",");
	var nId = typeof id === 'string' ? parseInt(id) : id;
	$.each(arrObj, function (i, v) {
		if (nId === v.id) {
			for (var j = 0, len = keyArr.length; j < len; j++) {
				name += (j > 0 ? " " : (name ? ", " : "")) + (keyArr[j] === "title" ? "<small>" + v[keyArr[j]] + "</small>" : v[keyArr[j]]);
			}
			return false;	//退出each循环
		}
	});
	return name;
}

//根据code查找器name
function getNameByCode(code, arrObj, key) {
	var name = "";
	var keyArr = key.split(",");
	$.each(arrObj, function (i, v) {
		if (code === v.code) {
			for (var j = 0, len = keyArr.length; j < len; j++) {
				name += (j > 0 ? " " : (name ? ", " : "")) + (keyArr[j] === "title" ? "<small>" + v[keyArr[j]] + "</small>" : v[keyArr[j]]);
			}
			return false;	//退出each循环
		}
	});
	return name;
}

//日期转字符串
function date2String(date, isFull) {
	var year = date.getFullYear();
	var month = (date.getMonth() + 1).toString();
	var day = (date.getDate()).toString();
	if (month.length === 1)
		month = "0" + month;
	if (day.length === 1)
		day = "0" + day;
	if (isFull) {
		var hour = (date.getHours()).toString();
		if (hour.length === 1)
			hour = "0" + hour;
		var minute = (date.getMinutes()).toString();
		if (minute.length === 1)
			minute = "0" + minute;
		return year + "-" + month + "-" + day + " " + hour + ":" + minute;
	} else {
		return year + "-" + month + "-" + day;
	}
}

//对每条预约做取消/恢复操作
function operatingRow(id, obj, proc) {
	mdConfirm('请确认:', '确实要' + (proc === "cancel" ? '取消' : '恢复') + '吗？', null, function () {
		var href = $(obj).parent('div').parent('.row').parent('.tab-pane').attr("id");
		href = '#' + href;
		fnAJAX(getOptionUrl(href) + '/cancelRecover', "POST", true, {id: id, available: (proc === "cancel" ? false : true)}, function (data) {
			if (data) {
				$(obj).parent('div').parent('.row').remove();
				var subject = href.substring(1, 3);
				if (proc === "cancel") {
					isNeedLoad[subject + "Canceled"] = true;
				} else if (proc === "recover") {
					isNeedLoad[subject + "UnProcessed"] = true;
				}
			}
		});
	});
}

//==============================
function fnAJAX(url, method, async, json, callback) {
	$.ajax({
		headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
		type: method,
		url: url,
		async: async,
		data: JSON.stringify(json),
		success: function (data) {
			if (callback)
				callback(data);
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.responseText);
		}
	});
}
