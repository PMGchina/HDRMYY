function fixPagesHeight() {
	$('.swiper-slide,.swiper-container').css({
		height: $(window).height()
	});
}

$(window).on('resize', function () {
	fixPagesHeight();
});

fixPagesHeight();

var swiper = new Swiper('.swiper-container', {
	speed: 800,
	effect: 'flip', //'slide' or 'fade' or 'cube' or 'coverflow' or 'flip'
	grabCursor: true,
	pagination: '.swiper-pagination',
	nextButton: '.swiper-button-next',
	prevButton: '.swiper-button-prev'
});
