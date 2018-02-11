var procDate = new Date(), doctorArr, departmentArr;

$(document).ready(function (e) {
	$('.container').find('.row').remove();
	$.get("doctor", function (data) {
		doctorArr = replaceImgUrl2CurUrl(data.all, 'image');
		$.get("department", function (data) {
			departmentArr = data.all;
			$.post("load", {date: procDate}, function (data) {
				afterLoad(data);
			});
		}, "json");
	}, "json");
});

//调入出诊表之后
function afterLoad(data) {
	var len = data.all.length;
	if (len > 0) {
		var row = '<div class="row"></div>';
		for (var i = 0; i < len; i++) {
			var row = '<div class="row"></div>';
			row = $(row).append('<div class="col-xs-8 myContent">' + getNameByDoctorCode(data.all[i].doctorCode, i) + '</div>');
			row = $(row).append('<div class="col-xs-4 text-right mySchedule">' + date2String(new Date(data.all[i].workday)) + '</div>');
			$('.btn-group').before(row);
		}
		adjustKeyHeight();
	} else {
		$('#btn-down').css('z-index', 0);
		mdDialog('提示:', date2String(procDate) + '，无排班！');
	}
}

//往下看出诊
function clickLookDown() {
	procDate.setDate(procDate.getDate() + 1);
	$.post("load", {date: procDate}, function (data) {
		afterLoad(data);
	});
}

function date2String(now) {
	var year = now.getFullYear();
	var month = (now.getMonth() + 1).toString();
	var day = (now.getDate()).toString();
	if (month.length === 1)
		month = "0" + month;
	if (day.length === 1)
		day = "0" + day;
	return year + "-" + month + "-" + day;
}

//根据doctorCode查找 医生的照片姓名,职称,科室
function getNameByDoctorCode(doctorCode, i) {
	var msg = "";
	$.each(doctorArr, function (indx, v) {
		if (doctorCode === v.code) {
			msg = clearUpImageUrl(v.image, i) + v.name + ' <small>' + v.title + '</small> ' + getNameByDepartmentCode(v.departmentCode);
			return false;
		}
	});
	return msg;
}

//根据departmentCode查找科室名
function getNameByDepartmentCode(departmentCode) {
	var name = "";
	$.each(departmentArr, function (indx, v) {
		if (departmentCode === v.code) {
			name = v.name;
			return false;
		}
	});
	return name;
}

//配置bootstrap式的<img>
function clearUpImageUrl(imageUrl, i) {
	if (imageUrl) {
		var div = '<div></div>';
		div = $(div).append(imageUrl);
		div.find('img').removeAttr('style');
		div.find('img').addClass('media-object');
		div.find('img').attr('id', 'img' + (i + 1));
		div.find('img').attr('onclick', 'showPhoto(' + (i + 1) + ')');
		return div.html();
	} else {
		return "";
	}
}

//把imageUrl的127.0.0.1成批替换成当前URL
function replaceImgUrl2CurUrl(arr, key) {
	var len = arr.length;
	if (len > 0) {
		var domain = location.hostname.replace('http://', '');
		var keyValue;
		for (var i = 0; i < len; i++) {
			keyValue = arr[i][key];
			if (keyValue)
				arr[i][key] = keyValue.replace(/127.0.0.1/g, domain);
		}
	}
	return arr;
}

//日期网格调整高度
function adjustKeyHeight() {
	$('.container').find('.row').each(function () {
		var keyObj = $(this).find('.mySchedule');
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
