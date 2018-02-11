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
	forceParse: 0
});
$('#form_date_1').datetimepicker({pickerPosition: 'bottom-right'});
$('#form_date_2').datetimepicker({pickerPosition: 'bottom-left'});

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
	$('#endDate').val(date2String(now));
	now.setDate(1);
	$('#startDate').val(date2String(now));
});

$(document).ready(function (e) {
	$('#myTab a[href="#ghDpt"]').tab('show');
});

//检查选择日期是否正确
function checkSelectDate() {
	var rtn = "";
	var startDate = $('#startDate').val() || "";
	var endDate = $('#endDate').val() || "";
	if (startDate !== "" && endDate !== "") {
		if (startDate <= endDate) {
			var now = new Date(endDate);
			now.setDate(now.getDate() + 1);
			rtn = startDate + "," + date2String(now);
		}
	}
	return rtn;
}

//标签页触发之后
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	var href = e.target.toString();
	var tabPane = href.substr(href.indexOf("#") + 1) || "";
	doRefresh(tabPane);
});

function doRefresh(tabPane) {
	var dateStr = checkSelectDate();
	if (dateStr) {
		var url = "";
		if (tabPane === "ghDpt") {
			url = "registration/department";
		} else if (tabPane === "ghDoc") {
			url = "registration/doctor";
		}
		if (url) {
			var index = dateStr.indexOf(",");
			fnAJAX(url, "POST", true, {startDate: dateStr.substring(0, index), endDate: dateStr.substr(index + 1)}, function (data) {
				afterLoad(data, tabPane);
			});
		}
	} else {
		mdAlert("提醒：", "选择日期有误！<br>未选或结束日期小于起始日期...");
	}
}
//调入之后
function afterLoad(data, tabPane) {
	$('#btn-fn').css('display', 'none');
	$('#' + tabPane).find('.panel-body .row').remove();
	$('#btn-fn').find('button').eq(1).attr("onclick", "");
	var len = data.count.length;
	if (len > 0) {
		var yyTotal = 0;
		for (var i = 0; i < len; i++) {
			yyTotal += data.count[i][1];
		}
		var percent = 0;
		for (var i = 0; i < len; i++) {
			var row = '<div class="row"></div>';
			row = $(row).append('<div class="col-xs-4 text-center my-col-option">' + data.count[i][0] + '</div>');
			var myColProgbar = '<div class="col-xs-8 my-col-progbar"></div>';

			var progress1 = '<div class="progress progress-striped active"></div>';
			percent = (data.count[i][1] / yyTotal * 100).toFixed(1);
			progress1 = $(progress1).append('<div class="progress-bar progress-bar-info" role="progressbar" '
					+ 'style="width:' + percent + '%">占比:' + data.count[i][1] + '次,' + percent + '%</div>');

			var progress2 = '<div class="progress"></div>';
			percent = (data.count[i][2] / data.count[i][1] * 100).toFixed(1);
			progress2 = $(progress2).append('<div class="progress-bar progress-bar-success" role="progressbar" '
					+ 'style="width:' + percent + '%">完成:' + data.count[i][1] + '次,' + percent + '%</div>');

			myColProgbar = $(myColProgbar).append(progress1);
			myColProgbar = $(myColProgbar).append(progress2);
			row = $(row).append(myColProgbar);
			$('#' + tabPane).find('.panel-body').append(row);
		}
	} else {
		mdAlert("提示：", "此期间内，无记录！");
	}
	$('#btn-fn').find('button').eq(1).attr("onclick", "doRefresh('" + tabPane + "')");
	$('#btn-fn').css('display', 'block');
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
