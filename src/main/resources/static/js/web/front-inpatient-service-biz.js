var swiper;

$(document).ready(function () {
	$.get("load", afterLoadInpatientService);
});

//调入门诊导医之后
var afterLoadInpatientService = function (data) {
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
		direction: 'horizontal', //'vertical'/'horizontal'
		mousewheelControl: false,
		watchSlidesProgress: true,
		onInit: function (swiper) {
			swiper.myactive = 0;
		},
		onProgress: function (swiper) {
			for (var i = 0; i < swiper.slides.length; i++) {
				var slide = swiper.slides[i];
				var progress = slide.progress;
				var translate, boxShadow;
				translate = progress * swiper.width * 0.6;
				scale = 1 - Math.min(Math.abs(progress * 0.4), 1);
				boxShadowOpacity = 0;
				slide.style.boxShadow = '0px 0px 10px rgba(0,0,0,' + boxShadowOpacity + ')';
				if (i === swiper.myactive) {
					es = slide.style;
					es.webkitTransform = es.MsTransform = es.msTransform = es.MozTransform = es.OTransform = es.transform = 'translate3d(' + (translate) + 'px,0,0) scale(' + scale + ')';
					es.zIndex = 0;
				} else {
					es = slide.style;
					es.webkitTransform = es.MsTransform = es.msTransform = es.MozTransform = es.OTransform = es.transform = '';
					es.zIndex = 1;
				}
			}
		},
		onTransitionEnd: function (swiper, speed) {
			for (var i = 0; i < swiper.slides.length; i++) {
				es = swiper.slides[i].style;
				es.webkitTransform = es.MsTransform = es.msTransform = es.MozTransform = es.OTransform = es.transform = '';
				swiper.slides[i].style.zIndex = Math.abs(swiper.slides[i].progress);
			}
			swiper.myactive = swiper.activeIndex;
		},
		onSetTransition: function (swiper, speed) {
			for (var i = 0; i < swiper.slides.length; i++) {
				es = swiper.slides[i].style;
				es.webkitTransitionDuration = es.MsTransitionDuration = es.msTransitionDuration = es.MozTransitionDuration = es.OTransitionDuration = es.transitionDuration = speed + 'ms';
			}
		}
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
