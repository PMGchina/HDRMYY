var swiper;

$(document).ready(function () {
	$.get("load", afterLoadHealthEducation);
});

//调入健康宣教之后
var afterLoadHealthEducation = function (data) {
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
		speed: 800,
		effect: 'flip', //'slide' or 'fade' or 'cube' or 'coverflow' or 'flip'
		grabCursor: true,
		pagination: '.swiper-pagination',
		nextButton: '.swiper-button-next',
		prevButton: '.swiper-button-prev'
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
