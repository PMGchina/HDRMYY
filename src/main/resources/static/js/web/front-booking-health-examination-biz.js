var bookingData, noticeData;

//调入体检套餐
function loadPackages() {
	modalSwitch = false;
	bookingData = {name: null, gender: null, birthday: null, identity: null, phoneNumber: null
		, chosenExaminationPackages: [], chosenExaminationItems: []
		, targetTime: null, totalAmount: 0, available: true, process: false}; //预约提交数据格式
	noticeData = {packageName: [], packageAmount: [], itemName: [], itemPrice: []};
	$.get("package/load", afterLoadPackages);
}

var afterLoadPackages = function (data) {
	$('#packageModal').find('.modal-body ul').remove();
	var ul = '<ul class="list-group"></ul>';
	var len = data.all.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			ul = $(ul).append('<li class="list-group-item" onclick="javascript:modalSwitch=false;"><label>'
				+ '<input type="checkbox" name="selectPackages" value="' + data.all[i].id + '">'
				+ data.all[i].name + '</label><span>￥<strong>' + parseFloat(data.all[i].amount).toFixed(2) + '</strong>元/套</span></li>');
		}
	} else {
		ul = $(ul).append('<li class="list-group-item text-center">暂无体检套餐！请上一步另选...</li>');
	}
	$('#packageModal').find('.modal-body').append(ul);
	$('#packageModal').modal('show');
};

function backInPackages() {
	modalSwitch = false;
	$('#packageModal').find('.modal-body ul').remove();
	$('#packageModal').modal('hide');
}

function nextInPackages() {
	modalSwitch = false;
	$('#packageModal').find('.modal-body').find('ul').children('li').each(function () {
		if ($(this).find('input').is(':checked')) {
			bookingData.chosenExaminationPackages.push($(this).find('input').val());
			noticeData.packageName.push($(this).find('label').text());
			noticeData.packageAmount.push(parseFloat($(this).find('span strong').text()));
		}
	});
	if (bookingData.chosenExaminationPackages.length === 0) {
		$('#packageDialog').css('display', 'none');
		mdConfirm('请确认:', '您未选体检套餐！若不选只能选体检单项了。',
			function () {
				$('#packageDialog').css('display', 'block');
			}, function () {
			$('#packageModal').find('.modal-body ul').remove();
			$('#packageModal').modal('hide');
			$('#packageDialog').css('display', 'block');
			loadItems();
		});
	} else {
		$('#packageModal').find('.modal-body ul').remove();
		$('#packageModal').modal('hide');
		loadItems();
	}
}


//调入单项
function loadItems() {
	modalSwitch = false;
	bookingData.chosenExaminationItems = [];
	noticeData.itemName = [];
	noticeData.itemPrice = [];
	$.get("item/load", afterLoadItems);
}

var afterLoadItems = function (data) {
	$('#itemModal').find('.modal-body ul').remove();
	var ul = '<ul class="list-group"></ul>';
	var len = data.all.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			ul = $(ul).append('<li class="list-group-item" onclick="javascript:modalSwitch=false;"><label>'
				+ '<input type="checkbox" name="selectItems" value="' + data.all[i].id + '">'
				+ data.all[i].name + '</label><span>￥<strong>' + parseFloat(data.all[i].price).toFixed(2) + '</strong>元/项</span></li>');
		}
	} else {
		ul = $(ul).append('<li class="list-group-item text-center">暂无体检单项！</li>');
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
			bookingData.chosenExaminationItems.push($(this).find('input').val());
			noticeData.itemName.push($(this).find('label').text());
			noticeData.itemPrice.push(parseFloat($(this).find('span strong').text()));
		}
	});
	if (bookingData.chosenExaminationItems.length > 0) {
		$('#itemModal').find('.modal-body ul').remove();
		$('#itemModal').modal('hide');
		loadSelectTime();
	} else {
		if (bookingData.chosenExaminationPackages.length === 0) {
			$('#itemDialog').css('display', 'none');
			mdAlert('警告:', '您在体检套餐和单项中，一个都未选！', function () {
				$('#itemDialog').css('display', 'block');
			});
		} else {
			$('#itemModal').find('.modal-body ul').remove();
			$('#itemModal').modal('hide');
			loadSelectTime();
		}
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
		+ '<label class="glyphicon glyphicon-plus-sign" onclick="addSelectTimeOption()">点击追加往后日期选项...</label></li>');
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

function nextInSelectTime() {
	modalSwitch = false;
	var selectTime = null;
	$('#timeModal').find('.modal-body ul').children('li').each(function () {
		if ($(this).find('input').is(':checked')) {
			selectTime = $(this).find('label').text();
		}
	});
	if (selectTime) {
		bookingData.targetTime = new Date(selectTime);
		$('#timeModal').find('.modal-body ul').remove();
		$('#timeModal').modal('hide');
		loadInputInfo();
	} else {
		$('#timeDialog').css('display', 'none');
		mdAlert('提醒:', '预约体检日期未选！', function () {
			$('#timeDialog').css('display', 'block');
		});
	}
}

//输入个人信息
function loadInputInfo() {
	$.get("getCard", afterLoadInputInfo);	//若已登录时调入个人信息
}

var afterLoadInputInfo = function (data) {
	if (data.card) {
		var body = $('#infoModal').children('.modal-body');
		body.find('#name').val(data.card);
	}
	$('#infoModal').modal('show');
};

function backInInputInfo() {
	modalSwitch = false;
	$('#infoModal').modal('hide');
	bookingData.totalAmount = 0;
	loadSelectTime();
}

function beforeSubmitBooking() {
	modalSwitch = false;
	//检查是否都填写内容
	var body = $('#infoModal').find('.modal-body form').children('.form-group');
	var name = body.find('#name').val();
	var birthday = body.find('#birthday').val();
	var identity = body.find('#identity').val();
	var phoneNumber = body.find('#phoneNumber').val();
	var alarm = "";
	if (name === "")
		alarm += '姓  名：为空！<br>';
	if (birthday === "")
		alarm += '出生日：为空！<br>';
	if (identity === "")
		alarm += '身份证：为空！<br>';
	if (phoneNumber === "")
		alarm += '手  机：为空！<br>';

	if (alarm) {
		$('#infoDialog').css('display', 'none');
		mdAlert('提醒:', '未填写如下:<br>' + alarm, function () {
			$('#infoDialog').css('display', 'block');
		});
	} else {
		//个人信息赋值给提交数据上
		bookingData.name = name;
		bookingData.gender = body.find('input[name="gender"]:checked').val() === "1" ? true : false;
		bookingData.birthday = birthday;
		bookingData.identity = identity;
		bookingData.phoneNumber = phoneNumber;

		//计算总金额
		var ttAmount = 0;
		for (var i = 0, l = noticeData.packageAmount.length; i < l; i++) {
			ttAmount += noticeData.packageAmount[i];
		}
		for (var i = 0, l = noticeData.itemPrice.length; i < l; i++) {
			ttAmount += noticeData.itemPrice[i];
		}
		bookingData.totalAmount = ttAmount;
		submitBooking();
	}
}

function submitBooking() {
	modalSwitch = false;
	fnAJAX("add", "POST", bookingData, afterSubmitBooking, false);
}

var afterSubmitBooking = function () {
	modalSwitch = false;
	$('#infoModal').modal('hide');

	//整理提交信息
	var notify = '';
	for (var i = 0, l = noticeData.packageName.length; i < l; i++) {
		notify += '<div class="row">';
		notify += '<div class="col-xs-3 mycol">套餐：</div>'
			+ '<div class="col-xs-5 mycol">' + noticeData.packageName[i] + '</div>'
			+ '<div class="col-xs-4 text-right mycol">' + noticeData.packageAmount[i].toFixed(2) + '元</div><br>';
		notify += '</div>';
	}
	for (var i = 0, l = noticeData.itemName.length; i < l; i++) {
		notify += '<div class="row">';
		notify += '<div class="col-xs-3 mycol">单项：</div>'
			+ '<div class="col-xs-5 mycol">' + noticeData.itemName[i] + '</div>'
			+ '<div class="col-xs-4 text-right mycol">' + noticeData.itemPrice[i].toFixed(2) + '元</div><br>';
		notify += '</div>';
	}
	notify += '合计金额：' + bookingData.totalAmount.toFixed(2) + '元<br>';
	notify += '体检日期：' + date2String(bookingData.targetTime) + '<br>';
	notify += '<br>体检人的信息如下：<br>';
	notify += '&nbsp;姓  名：' + bookingData.name + '<br>';
	notify += '&nbsp;性  别：' + (bookingData.gender ? '男' : '女') + '<br>';
	notify += '&nbsp;出生日：' + bookingData.birthday + '<br>';
	notify += '&nbsp;身份证：' + bookingData.identity + '<br>';
	notify += '&nbsp;手  机：' + bookingData.phoneNumber;
	mdAlert('提示:', '您预约体检项目成功！如下：<br>' + notify);
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
