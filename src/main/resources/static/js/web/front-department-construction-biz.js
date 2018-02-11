$(document).ready(function (e) {
	$.get("departmentConstruction", afterLoadDepartment);
});

//调入后
var afterLoadDepartment = function (data) {
	$('.container-fluid').find('.panel').remove();
	var div = '<div></div>';
	var len = data.all.length;
	if (len > 0) {
		var name, title, imageUrl, description;
		for (var i = 0; i < len; i++) {
			name = data.all[i].name || "";
			title = data.all[i].title || "";
			imageUrl = data.all[i].imageUrl || "";
			description = data.all[i].description || "";

			var panel = '<div class="panel panel-success"></div>';
			var panelHeading = '<div class="panel-heading"></div>';
			var panelCollapse = '<div id="collapse' + (i + 1) + '" class="panel-collapse collapse' + (i === 0 ? ' in' : '') + '"></div>';

			panelHeading = $(panelHeading).append(clearUpImageUrl(imageUrl, i)
					+ '<a data-toggle="collapse" data-parent="#accordion" href="#collapse' + (i + 1)
					+ '"><h4 class="panel-title">' + name + '</h4></a>');
			panel = $(panel).append(panelHeading);

			panelCollapse = $(panelCollapse).append('<div class="panel-body">' + changeImageUrl2CrrentUrl(description) + '</div>');
			panel = $(panel).append(panelCollapse);

			div = $(div).append(panel);
		}
	}
	$('#btn-back').before(div.html());
	$('#btn-back').css('display', 'block');
};

function clearUpImageUrl(imageUrl, i) {
	if (imageUrl) {
		var div = '<div></div>';
		div = $(div).append(imageUrl);
		div.find('img').removeAttr('style');
		div.find('img').addClass('media-object');
		div.find('img').attr('id', 'img' + (i + 1));
		div.find('img').attr('onclick', 'showPhoto(' + (i + 1) + ')');
		return changeImageUrl2CrrentUrl(div.html());
	} else {
		return "";
	}
}

//在img的URL更换成当前URL
function changeImageUrl2CrrentUrl(content) {
	return (content ? content.replace(/127.0.0.1/g, location.hostname) : '');
}
