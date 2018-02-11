var modalSwitch = true;

//Modal水平垂直居中
function centerModals(divId) {
	$('#' + divId + 'Modal').each(function () {
		var $clone = $(this).clone().css('display', 'block').appendTo('body');
		var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
		top = top > 0 ? top : 0;
		$clone.remove();
		$(this).find('.modal-dialog').css("margin-top", top);
	});
}

//当点击时,Bootstrap模态窗口的动画效果
function modalAnimation(anim, target) {
	if (modalSwitch) {
		modalAnimationBase(anim, target);
	} else {
		modalSwitch = true;
	}
}

//Bootstrap模态窗口 基本动作
function modalAnimationBase(anim, target) {
	if (anim) {
		var dialogObj = $("#" + target + "Dialog");
		dialogObj.addClass(anim + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
			$(this).removeClass(anim + ' animated');
		});
	}
}
