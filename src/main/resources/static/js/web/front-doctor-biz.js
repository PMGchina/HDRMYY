$(document).ready(function (e) {
	$.get("doctor", afterLoadDoctor);
});

//调入专家名录之后
var afterLoadDoctor = function (data) {
	$('.media-list').remove();
	var ul = '<ul class="media-list"></ul>';
	var len = data.all.length;

	if (len > 0) {
		var name, title, imageUrl, description, mod;
		for (var i = 0; i < len; i++) {
			name = data.all[i].name || "";
			title = data.all[i].title || "";
			imageUrl = data.all[i].imageUrl || "";
			description = data.all[i].description || "";
			mod = i % 2 === 0 ? true : false;
			ul = $(ul).append('<li class="media"><a class="media-' + (mod ? 'left' : 'right')
				+ '" href="javacript:void(0)">' + clearUpImageUrl(imageUrl)
				+ '</a><div class="media-body"><h3 class="media-heading' + (!mod ? ' text-right' : '') + '">'
				+ name + '<small>' + title + '</small></h3>'
				+ '<p>' + changeImageUrl2CrrentUrl(description) + '</p></div></li>');
		}
	}
	$('#btn-back').before(ul);
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
