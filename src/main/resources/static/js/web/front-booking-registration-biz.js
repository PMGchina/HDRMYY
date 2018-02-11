var bookingData, noticeData;

//调入科室
function loadDepartment() {
	modalSwitch = false; //Modal开关
	bookingData = {medicalCard: null, departmentCode: null, doctorCode: null, targetTime: null
		, available: true, process: false}; //预约提交数据格式
	noticeData = {departmentName: null, doctorName: null}; //操作进行中提示(暂存)
	$.get("department/load", afterLoadDepartment);
}

var afterLoadDepartment = function (data) {
	$('#departmentModal').find('.modal-body').find('ul').remove();
	var ul = '<ul class="list-group"></ul>';
	var len = data.department.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			ul = $(ul).append('<li class="list-group-item" onclick="clickDepartment('
				+ data.department[i].code + ',this)">' + clearUpImageUrl(data.department[i].icon, null) + '<strong>'
				+ data.department[i].name + '</strong><span>&rsaquo;</span></li>');
		}
	} else {
		ul = $(ul).append('<li class="list-group-item text-center"><strong>暂无科室！请上一步另选...</strong></li>');
	}
	$('#departmentModal').find('.modal-body').append(ul);
	$('#departmentModal').modal('show');
};
function clickDepartment(code, obj) {
	bookingData.departmentCode = code;
	noticeData.departmentName = $(obj).find('strong').text();
	$('#departmentModal').modal('hide');
	loadDoctor(code);
}

function backInDepartment() {
	bookingData.departmentCode = null;
	noticeData.departmentName = null;
	$('#departmentModal').modal('hide');
}

//调入医生
function loadDoctor(deptCode) {
	modalSwitch = false;
	$.get("doctor/load?code=" + deptCode, afterLoadDoctor);
}

var afterLoadDoctor = function (data) {
	$('#doctorModal').find('.modal-body').find('ul').remove();
	var ul = '<ul class="list-group"></ul>';
	var len = data.doctor.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			ul = $(ul).append('<li class="list-group-item" onclick="clickDoctor(' + data.doctor[i].code + ',this)">'
				+ clearUpImageUrl(data.doctor[i].image, data.doctor[i].gender)
				+ '<strong>' + data.doctor[i].name + ' <small>' + data.doctor[i].title + '</small></strong>'
				+ '<span>&rsaquo;</span></li>');
		}
	} else {
		ul = $(ul).append('<li class="list-group-item text-center"><strong>'
			+ (noticeData.departmentName !== null ? noticeData.departmentName : '')
			+ '，暂无医生！<br>请上一步另选科室.</strong></li>');
	}
	$('#doctorModal').find('.modal-body').append(ul);
	$('#doctorModal').modal('show');
};
function clickDoctor(code, obj) {
	bookingData.doctorCode = code;
	noticeData.doctorName = $(obj).find('strong').text();
	$('#doctorModal').modal('hide');
	loadWorkDay(code);
}

function backInDoctor() {
	bookingData.doctorCode = null;
	noticeData.doctorName = null;
	$('#doctorModal').modal('hide');
	loadDepartment();
}

function skipInDoctor() {
	bookingData.doctorCode = null;
	noticeData.doctorName = null;
	modalSwitch = false;
	$('#doctorModal').modal('hide');
	mdConfirm('请确认:', '您未选医生！若不选只能预约医诊科室。是否跳过？',
		function () {
			loadDoctor(bookingData.departmentCode);
		}, function () {
		loadWorkDay();
	});
}

//时段排班
function loadWorkDay(doctorCode) {
	modalSwitch = false;
	if (doctorCode) {
		$.get("schedule/load?code=" + doctorCode, afterLoadWorkDay);
	} else {
		//默认产生自明日起7日的时段
		var now = new Date();
		var data = {schedule: []};
		for (var i = 0; i < 7; i++) {
			now.setDate(now.getDate() + 1);
			data.schedule.push({doctorCode: null, workday: date2String(now), available: false});
		}
		afterLoadWorkDay(data);
	}
}

function date2String(now) {
	var year = now.getFullYear();
	var month = (now.getMonth() + 1).toString();
	var day = (now.getDate()).toString();
	if (month.length === 1) {
		month = "0" + month;
	}
	if (day.length === 1) {
		day = "0" + day;
	}
	return year + "-" + month + "-" + day;
}

var afterLoadWorkDay = function (data) {
	$('#datetimeModal').find('.modal-body').find('ul').remove();
	var timeArr = [];
	var now = new Date();
	now.setDate(now.getDate() + 1);
	now.setHours(0);
	now.setMinutes(0);
	now.setSeconds(0);
	var limitDay = 0;
	var timeArray = generateTimeInterval();
	for (var i = 0, il = data.schedule.length; i < il; i++) {
		var workDay = new Date(data.schedule[i].workday);
		if (workDay > now) {	//根据医生排班表的日期,大于当前日期
			if (limitDay < 7) {	//限于7天
				for (var j = 0, jl = timeArray.length; j < jl; j++) {
					timeArr.push(workDay.toJSON().substring(0, 10) + " , " + timeArray[j]);
				}
				limitDay++;
			} else {
				break;
			}
		}
	}
	var ul = '<ul class="list-group"></ul>';
	len = timeArr.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			ul = $(ul).append('<li class="list-group-item"><label onclick="javascript:modalSwitch=false;">'
				+ '<input type="radio" name="bkTime">' + timeArr[i] + '</label></li>');
		}
	} else {
		ul = $(ul).append('<li class="list-group-item text-center"><strong>'
			+ noticeData.departmentName + '的 '
			+ (noticeData.doctorName !== null ? noticeData.doctorName : '')
			+ '，暂无排班！<br>请到上一步另选医生.</strong></li>');
	}
	$('#datetimeModal').find('.modal-body').append(ul);
	$('#datetimeModal').modal('show');
};
function generateTimeInterval() {
	var amTimeArr = ['08:00', '09:00', '10:00', '11:00', '12:00'];
	var pmTimeArr = ['13:00', '14:00', '15:00', '16:00', '17:00'];
	var timeArray = [];
	for (var i = 0, len = amTimeArr.length - 1; i < len; i++) {
		timeArray.push(amTimeArr[i] + " ~ " + amTimeArr[i + 1]);
	}
	for (var i = 0, len = pmTimeArr.length - 1; i < len; i++) {
		timeArray.push(pmTimeArr[i] + " ~ " + pmTimeArr[i + 1]);
	}
	return timeArray;
}

function backInTime() {
	bookingData.targetTime = null;
	$('#datetimeModal').modal('hide');
	loadDoctor(bookingData.departmentCode);
}

function submitBooking() {
	modalSwitch = false;
	bookingData.targetTime = null;
	var selectedDateTime = null;
	$('#datetimeModal').find('.modal-body ul').children('li').each(function () {
		if ($(this).find('input').is(':checked')) {
			selectedDateTime = $(this).find('label').text();
		}
	});
	if (selectedDateTime) {
		var now = new Date(selectedDateTime.substring(0, selectedDateTime.indexOf(" ~")).replace(" , ", "T") + ":00");
		bookingData.targetTime = now.getTime();
		fnAJAX("bookingRegistration/add", "POST", bookingData, function (data) {
			$('#datetimeModal').modal('hide');
			mdDialog('提示:', '预约成功! 如下:<br>科 室: '
				+ noticeData.departmentName + '<br>医 生: '
				+ (noticeData.doctorName ? noticeData.doctorName : '')
				+ '<br>时 间: ' + selectedDateTime
				+ '<br>记 录: ' + data.creat);
		}, true);
	} else {
		$('#datetimeDialog').css('display', 'none');
		mdAlert('提醒:', '时段未选！', function () {
			$('#datetimeDialog').css('display', 'block');
		});
	}
}

//配置bootstrap式的<img>
function clearUpImageUrl(imageUrl, gender) {
	if (imageUrl) {
		var div = '<div></div>';
		div = $(div).append(imageUrl);
		div.find('img').removeAttr('style');
		div.find('img').addClass('media-object');
		return changeImageUrl2CrrentUrl(div.html());
	} else {
		if (gender !== null) {
			return '<img src="http://' + location.host + '/hdrmyy/static/image/web/doctor-default-'
				+ (gender ? '' : 'wo') + 'man.png" calss="media-object" alt=""/>';
		} else {
			return '';
		}
	}
}

//在img的URL更换成当前URL
function changeImageUrl2CrrentUrl(content) {
	return (content ? content.replace(/127.0.0.1/g, location.hostname) : '');
}

//==============================
function fnAJAX(url, method, json, callback, isUseData) {
	$.ajax({
		headers: {'Accept': 'application/json', 'Content-Type': 'application/json;charset=UTF-8'},
		type: method,
		url: url,
		data: JSON.stringify(json),
		success: function (data) {
			if (callback) {
				if (isUseData) {
					callback(data);
				} else {
					callback();
				}
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.responseText);
		}
	});
}
