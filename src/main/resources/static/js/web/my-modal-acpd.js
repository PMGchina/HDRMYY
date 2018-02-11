function mdAlert(title, html, okFn) {
	var obj = $('#alertDialog').children('.acpdModalContent');
	obj.find('.acpdTitleText').text(title);
	obj.find('.acpdContent').html(html);

	var fn = (typeof okFn === "function") ? okFn : function () {};
	obj.find('.acpdTitle button').one('click', fn);
	obj.find('.modal-footer').children('button').eq(0).one('click', fn);

	$('#alertModal').modal('show');
}

function mdConfirm(title, html, cancelFn, confirmFn) {
	var obj = $('#confirmDialog').children('.acpdModalContent');
	obj.find('.acpdTitleText').text(title);
	obj.find('.acpdContent').html(html);

	var ccfn = (typeof cancelFn === "function") ? cancelFn : function () {};
	var cffn = (typeof confirmFn === "function") ? confirmFn : function () {};

	obj.find('.acpdTitle button').one('click', ccfn);

	var footBtns = obj.find('.modal-footer').children('button');
	footBtns.eq(0).one('click', ccfn);
	footBtns.eq(1).one('click', cffn);

	$('#confirmModal').modal('show');
}

function mdPrompt(title, html, cancelFn, submitFn) {
	var obj = $('#promptDialog').children('.acpdModalContent');
	obj.find('.acpdTitleText').text(title);
	obj.find('.acpdContent').html(html);

	var ccfn = (typeof cancelFn === "function") ? cancelFn : function () {};
	var sufn = (typeof submitFn === "function") ? submitFn : function () {};

	obj.find('.acpdTitle button').one('click', ccfn);

	var footBtns = obj.find('.modal-footer').children('button');
	footBtns.eq(0).one('click', ccfn);
	footBtns.eq(1).one('click', sufn);

	$('#promptModal').modal('show');
}

function mdDialog(title, html, millisecond) {
	var obj = $('#dialogDialog').children('.acpdModalContent');
	obj.find('.acpdTitleText').text(title);
	obj.find('.acpdContent').html(html);
	if (millisecond) {
		setTimeout("$('#dialogModal').modal('hide')", millisecond);
	}
	$('#dialogModal').modal('show');
}
