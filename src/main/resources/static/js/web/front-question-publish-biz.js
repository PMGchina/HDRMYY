$(document).ready(function () {
	loadQuestionnaire();
});

//调入问卷期刊Questionnaire之后
function loadQuestionnaire() {
	$.get("questionnaireEdit", function (data) {	//调入问卷Questionnaire(有效未发布)
		afterLoadQuestionnaire(data);
	});
}

function afterLoadQuestionnaire(data) {
	$('.list-group').remove();
	var ul = '<ul class="list-group"></ul>';
	var len = data.all.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			ul = $(ul).append('<li class="list-group-item" onclick="publishQuestionnaire(' + data.all[i].id + ',this)"><strong>'
				+ data.all[i].name + '</strong><span>&rsaquo;</span></li>');
		}
	} else {
		ul = $(ul).append('<li class="list-group-item">无有效未发布的问卷!</li>');
	}
	$('#btn-back').before(ul);
	$('#btn-back').css('display', 'block');
	
}

function publishQuestionnaire(id, obj) {
	mdConfirm('请确认:', '确实要发布问卷吗？<br>&nbsp;问卷为: ' + $(obj).find('strong').text(),
		function () {
			loadQuestionnaire();
		}, function () {
		fnAJAX("questionnairePublish", "POST", {id: id}, function (data) {
			loadQuestionnaire();
			mdDialog('提示:', '发布成功!');
		}, false);
	});
}

//==============================
function fnAJAX(url, method, json, callback, isUseData) {
	$.ajax({
		headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
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
