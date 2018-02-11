//DateTimePicker initialize
$('.form_date').datetimepicker({
	language: 'zh-CN',
	weekStart: 1,
	endDate: new Date(),
	autoclose: 1,
	startView: 2,
	minView: 2,
	todayBtn: 1,
	todayHighlight: 1,
	forceParse: 0,
	pickerPosition: 'bottom-left'
});
//日期转字符串yyyy-MM-dd
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

//给日期控件赋初值
$(function () {
	var now = new Date();
	$('#startDate').val(date2String(now));
});
$(document).ready(function (e) {
	clickSearchBtn();
});
//点击搜索键
function clickSearchBtn() {
	var startDate = $('#startDate').val() || "";
	if (startDate) {
		var now = new Date(startDate + "T00:00:00");
		fnAJAX("search", {oneday: now.getTime()}, function (data) {
			afterLoadCheckReport(data);
		});
	} else {
		mdDialog('提醒：', '搜索日期为空！');
	}
}

//调入后
function afterLoadCheckReport(data) {
	$('.container').find('.panel').remove();
	var len = data.search.length;
	if (len > 0) {
		var panel = '<div class="panel panel-warning"></div>';
		var inspectionDate;
		for (var i = 0; i < len; i++) {
			inspectionDate = date2String(new Date(data.search[i].inspectionDate));
			var panelHeading = '<div class="panel-heading"></div>';
			panelHeading = $(panelHeading).append(
					'<a data-toggle="collapse" data-parent="#accordion" href="#collapse'
					+ (i + 1) + '"><h4 class="panel-title">'
					+ data.search[i].departmentName + ', '
					+ data.search[i].specimenType + ', '
					+ inspectionDate + '</h4></a>');
			panel = $(panel).append(panelHeading);
			var collapse = '<div id="collapse' + (i + 1) + '" class="panel-collapse collapse'
					+ (i + 1 === len ? ' in' : '') + '">';
			var row = '<div class="row"></div>';
			row = $(row).append('<div class="col-xs-2 my-label">姓名:</div>');
			row = $(row).append('<div class="col-xs-4">' + data.search[i].name + '</div>');
			row = $(row).append('<div class="col-xs-2 my-label">性别:</div>');
			row = $(row).append('<div class="col-xs-4">' + data.search[i].sex + '</div>');
			collapse = $(collapse).append(row);

			var row = '<div class="row"></div>';
			row = $(row).append('<div class="col-xs-2 my-label">年龄:</div>');
			row = $(row).append('<div class="col-xs-4">' + data.search[i].age + '</div>');
			row = $(row).append('<div class="col-xs-2 my-label">送检医生:</div>');
			row = $(row).append('<div class="col-xs-4">' + data.search[i].doctorName + '</div>');
			collapse = $(collapse).append(row);

			var row = '<div class="row"></div>';
			row = $(row).append('<div class="col-xs-2 my-label">科别:</div>');
			row = $(row).append('<div class="col-xs-4">' + data.search[i].departmentName + '</div>');
			row = $(row).append('<div class="col-xs-2 my-label">病历号:</div>');
			row = $(row).append('<div class="col-xs-4">' + data.search[i].medicalRecordsNo + '</div>');
			collapse = $(collapse).append(row);

			var row = '<div class="row"></div>';
			row = $(row).append('<div class="col-xs-2 my-label">条码号:</div>');
			row = $(row).append('<div class="col-xs-4">' + data.search[i].barcodeNo + '</div>');
			row = $(row).append('<div class="col-xs-2 my-label">标本号:</div>');
			row = $(row).append('<div class="col-xs-4">' + data.search[i].specimenNo + '</div>');
			collapse = $(collapse).append(row);

			var row = '<div class="row"></div>';
			row = $(row).append('<div class="col-xs-2 my-label">标本类型:</div>');
			row = $(row).append('<div class="col-xs-4">' + data.search[i].specimenType + '</div>');
			row = $(row).append('<div class="col-xs-2 my-label">送检时间:</div>');
			row = $(row).append('<div class="col-xs-4">' + inspectionDate + '</div>');
			collapse = $(collapse).append(row);

			var row = '<div class="row"></div>';
			row = $(row).append('<div class="col-xs-2 my-label">备  注:</div>');
			row = $(row).append('<div class="col-xs-10">' + data.search[i].remark + '</div>');
			collapse = $(collapse).append(row);

			//明细
			collapse = $(collapse).append('<hr>');
			var row = '<div class="row my-panel-warning"></div>';
			row = $(row).append('<div class="col-xs-2">英文名</div>');
			row = $(row).append('<div class="col-xs-2">中文名</div>');
			row = $(row).append('<div class="col-xs-2">结  果</div>');
			row = $(row).append('<div class="col-xs-1">态</div>');
			row = $(row).append('<div class="col-xs-2">单  位</div>');
			row = $(row).append('<div class="col-xs-3">参考区</div>');
			collapse = $(collapse).append(row);
			collapse = $(collapse).append('<hr>');

			var details = data.search[i].checkReportDetail;
			for (var j = 0, jl = details.length; j < jl; j++) {
				var row = '<div class="row"></div>';
				row = $(row).append('<div class="col-xs-2">' + details[j].englishName + '</div>');
				row = $(row).append('<div class="col-xs-2">' + details[j].chineseName + '</div>');
				row = $(row).append('<div class="col-xs-2">' + details[j].checkResult + '</div>');
				row = $(row).append('<div class="col-xs-1">' + (details[j].checkState === null ? '' : details[j].checkState) + '</div>');
				row = $(row).append('<div class="col-xs-2">' + details[j].unit + '</div>');
				row = $(row).append('<div class="col-xs-3">' + details[j].referenceRange + '</div>');
				collapse = $(collapse).append(row);
			}
			panel = $(panel).append(collapse);
		}
		$('#btn-fn').before(panel);
	}
}

function requestSend() {
	fnAJAX("requestsend", {}, function (data) {
		var msgTtle = '提示：';
		var msgText = '请求发送';
		if (data.id !== null && data.id > 0) {
			mdDialog(msgTtle, msgText + '成功！' + data.id);
		} else {
			mdDialog(msgTtle, msgText + '失败！');
		}
	});
}

//==============================
function fnAJAX(url, json, callback) {
	$.ajax({
		headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
		type: "POST",
		url: url,
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
