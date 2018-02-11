var submitData;

$(document).ready(function (e) {
	$.get("load", function (data) {
		afterLoad(data);
	});
});

//调入之前的便民服务内容之后
function afterLoad(data) {
	$('.container').find('.row').remove();
	var len = data.all.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			var row = '<div class="row"></div>';
			row = $(row).append('<div class="col-xs-12 myTitle">' + data.all[i].title + '</div>');
			row = $(row).append('<div class="col-xs-12 myContent">' + changeImageUrl2CrrentUrl(data.all[i].content) + '</div>');
			$('.btn-group').before(row);
		}
	} else {
	}
}

//涮新
function refreshContent() {
	modalSwitch = false;
	$('.container').find('.row').remove();
	$.get("load", function (data) {
		afterLoad(data);
	});
}

//点击创建
function creatContent() {
	modalSwitch = false;
	$('#inputModal').modal('show');
}

//提交内容
function submitContent() {
	modalSwitch = false;
	submitData = {title: null, content: null, available: true, medicalCard: ""};
	//检查填写是否为空?
	var obj = $('#inputModal').find('.modal-body form').children('.form-group');
	submitData.title = obj.find('#title').val();
	if (!submitData.title)
		submitData.title = "?";
	submitData.content = obj.find('#content').val();
	submitData.content = submitData.content.replace(/\n/g, '<br>');
	if (submitData.content === "") {
		$('#inputDialog').css('display', 'none');
		mdAlert('提醒:', '<br>您还未填写内容！', function () {
			$('#inputDialog').css('display', 'block');
		});
	} else {
		$('#inputDialog').css('display', 'none');
		mdConfirm('请确认:', '您确实提交此留言吗？',
				function () {
					$('#inputDialog').css('display', 'block');
				}, function () {
			$('#inputModal').modal('hide');
			$('#inputDialog').css('display', 'block');
			fnAJAX("add", "POST", submitData, function (data) {
				afterSubmitContent(data);
			});
		});
	}
}

//提交内容之后
function afterSubmitContent(data) {
	modalSwitch = false;
	var row = '<div class="row"></div>';
	row = $(row).append('<div class="col-xs-12 myTitle">' + submitData.title + '</div>');
	row = $(row).append('<div class="col-xs-12 myContent">' + changeImageUrl2CrrentUrl(submitData.content) + '</div>');
	$('.btn-group').before(row);
	mdDialog('提示:', '提交成功！<br>非常感谢，您对我院工作的支持和参与！', 3000);
}

//在img的URL更换成当前URL
function changeImageUrl2CrrentUrl(content) {
	return (content ? content.replace(/127.0.0.1/g, location.hostname) : '');
}

//==============================
function fnAJAX(url, method, json, callback) {
	$.ajax({
		headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
		type: method,
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
