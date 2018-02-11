var swiper;

$(document).ready(function (e) {
	$.get("load", afterLoadSeekingTreatStrategy);
});

//调入就医攻略之后
var afterLoadSeekingTreatStrategy = function (data) {
	var len = data.all.length;
	if (len > 0) {
		$('.swiper-wrapper').children().remove();
		for (var i = 0; i < len; i++) {
			var tempDiv = '<div></div>';
			var imgUrls = data.all[i].content || "";
			tempDiv = $(tempDiv).append(imgUrls);
			$(tempDiv).find('img').each(function () {
				var slide = '<div class="swiper-slide"></div>';
				$(this).removeAttr('style');
				$(this).attr('src', $(this).attr('src').replace(/127.0.0.1/g, location.hostname));
				slide = $(slide).append(this);
				$('.swiper-wrapper').append(slide);
			});
		}
		defaultImages();
	} else {
		defaultImages();
	}

	fixPagesHeight();
	swiper = new Swiper('.swiper-container', {
		direction: 'vertical', //'vertical'/'horizontal'
		speed: 800,
		effect: 'cube', //'slide' or 'fade' or 'cube' or 'coverflow' or 'flip'
		grabCursor: true,
		pagination: '.swiper-pagination',
		prevButton: '.swiper-button-prev',
		nextButton: '.swiper-button-next'
	});
};

function defaultImages() {
	for (var i = 0; i < 6; i++) {
		var slide = '<div class="swiper-slide"></div>';
		slide = $(slide).append('<img src="/hdrmyy/static/image/web/seeking_treat/' + (i + 1) + '.jpg" alt=""/>');
		$('.swiper-wrapper').append(slide);
	}
}

function fixPagesHeight() {
	$('.swiper-slide,.swiper-container').css({
		height: $(window).height()
	});
}

$(window).on('resize', function () {
	fixPagesHeight();
});
