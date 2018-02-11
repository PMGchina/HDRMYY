var bookingData, noticeData;

//调入复查项目
function loadItems() {
	modalSwitch = false;
	bookingData = {medicalCard: null, totalAmount: 0, chosenReexaminationItems: [], targetTime: null
		, available: true, process: false}; //预约提交数据格式
	noticeData = {itemName: [], itemPrice: []};
	$.get("item/load", afterLoadItems);
}

var afterLoadItems = function (data) {
	$('#itemModal').find('.modal-body ul').remove();
	var ul = '<ul class="list-group"></ul>';
	var len = data.all.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			ul = $(ul).append('<li class="list-group-item" onclick="javascript:modalSwitch=false;">'
				+ '<label>' + '<input type="checkbox" name="selectItems" value="' + data.all[i].id + '">'
				+ data.all[i].name + '(' + data.all[i].code + ')</label>'
				+ '<span>￥<strong>' + parseFloat(data.all[i].price).toFixed(2) + '</strong>元/项</span></li>');
		}
	} else {
		ul = $(ul).append('<li class="list-group-item text-center">暂无复查项目！</li>');
	}
	$('#itemModal').find('.modal-body').append(ul);
	$('#itemModal').modal('show');
};

function backInItem() {
	modalSwitch = false;
	$('#itemModal').find('.modal-body ul').remove();
	$('#itemModal').modal('hide');
	loadPackages();
}

function nextInItem() {
	modalSwitch = false;
	$('#itemModal').find('.modal-body ul').children('li').each(function () {
		if ($(this).find('input').is(':checked')) {
			bookingData.chosenReexaminationItems.push($(this).find('input').val());
			noticeData.itemName.push($(this).find('label').text());
			noticeData.itemPrice.push(parseFloat($(this).find('span strong').text()));
		}
	});
	if (bookingData.chosenReexaminationItems.length > 0) {
		$('#itemModal').find('.modal-body ul').remove();
		$('#itemModal').modal('hide');
		loadSelectTime();
	} else {
		$('#itemDialog').css('display', 'none');
		mdAlert('警告:', '您还未选复查项目(可多选)！', function () {
			$('#itemDialog').css('display', 'block');
		});
	}
}

//预约日期
function loadSelectTime() {
	bookingData.targetTime = null;
	var data = {schedule: generateDate()};
	afterLoadSelectTime(data);
}

function generateDate(dateStr) {
	var now = dateStr ? new Date(dateStr) : new Date();
	var dateArr = [];
	for (var i = 0; i < 15; i++) {
		now.setDate(now.getDate() + 1);
		dateArr.push(date2String(now));
	}
	return dateArr;
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

function afterLoadSelectTime(data) {
	$('#timeModal').find('.modal-body ul').remove();
	var ul = '<ul class="list-group"></ul>';
	for (var i = 0, l = data.schedule.length; i < l; i++) {
		ul = $(ul).append('<li class="list-group-item"><label onclick="javascript:modalSwitch=false;">'
			+ '<input type="radio" name="selectDate">' + data.schedule[i] + '</label></li>');
	}
	ul = $(ul).append('<li class="list-group-item text-center my-panel-default" id="addTimeOption">'
		+ '<label class="glyphicon glyphicon-plus-sign" onclick="addSelectTimeOption()">点击追加日期选项...</label></li>');
	$('#timeModal').find('.modal-body').append(ul);
	$('#timeModal').modal('show');
}

function addSelectTimeOption() {
	modalSwitch = false;
	var obj = $('#timeModal').find('.modal-body ul').find('#addTimeOption');
	var curEndDate = obj.prev().find('label').text();
	var li = "";
	var schedule = generateDate(curEndDate);
	for (var i = 0, l = schedule.length; i < l; i++) {
		li += '<li class="list-group-item"><label onclick="javascript:modalSwitch=false;">'
			+ '<input type="radio" name="selectDate">' + schedule[i] + '</label></li>';
	}
	if (li) {
		obj.before(li);
		centerModals('time');
	}
}

function backInSelectTime() {
	modalSwitch = false;
	$('#timeModal').find('.modal-body ul').remove();
	$('#timeModal').modal('hide');
	loadItems();
}

function submitBooking() {
	modalSwitch = false;
	var selectTime = null;
	$('#timeModal').find('.modal-body ul').children('li').each(function () {
		if ($(this).find('input').is(':checked')) {
			selectTime = $(this).find('label').text();
		}
	});
	if (selectTime) {
		bookingData.targetTime = new Date(selectTime);
		//计算总金额
		var ttAmount = 0;
		for (var i = 0, l = noticeData.itemPrice.length; i < l; i++) {
			ttAmount += noticeData.itemPrice[i];
		}
		bookingData.totalAmount = ttAmount;
		$('#timeModal').find('.modal-body ul').remove();
		$('#timeModal').modal('hide');
		fnAJAX("add", "POST", bookingData, afterSubmitBooking, false);
	} else {
		$('#timeDialog').css('display', 'none');
		mdAlert('提醒:', '预约复查日期未选！', function () {
			$('#timeDialog').css('display', 'block');
		});
	}
}

var afterSubmitBooking = function () {
	modalSwitch = false;
	$('#infoModal').modal('hide');

	//整理提交信息
	var notify = '';
	for (var i = 0, l = noticeData.itemName.length; i < l; i++) {
		notify += '<div class="row">';
		notify += '<div class="col-xs-7 mycol">' + noticeData.itemName[i] + '</div>'
			+ '<div class="col-xs-4 text-right mycol">' + noticeData.itemPrice[i].toFixed(2) + '元</div><br>';
		notify += '</div>';
	}
	notify += '合计金额：' + bookingData.totalAmount.toFixed(2) + '元<br>';
	notify += '复查日期：' + date2String(bookingData.targetTime) + '<br>';
	mdAlert('提示:', '您预约复查成功！如下：<br>' + notify);
};

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
