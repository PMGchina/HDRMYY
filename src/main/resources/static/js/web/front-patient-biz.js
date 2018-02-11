$(document).ready(function (e) {
	$.get("patient", function (data) {
		afterLoadPatient(data);
	});
});

//调入患者信息之后
function afterLoadPatient(data) {
	$('#barCode').find('img').remove();
	$('#qrCode').find('img').remove();
	if (data) {
		$('#barCode').append(clearUpImageUrl(data.patient.barcode));
		$('#qrCode').append(clearUpImageUrl(data.patient.qrcode));
	}
	$('#btn-back').css('display', 'block');
}
;

//配置bootstrap式的<img>
function clearUpImageUrl(imageUrl) {
	if (imageUrl) {
		var div = '<div></div>';
		div = $(div).append(imageUrl);
		div.find('img').removeAttr('style');
		div.find('img').addClass('media-object');
		return changeImageUrl2CrrentUrl(div.html());
	} else {
		return "";
	}
}

//在img的URL更换成当前URL
function changeImageUrl2CrrentUrl(content) {
	return (content ? content.replace(/127.0.0.1/g, location.hostname) : '');
}
