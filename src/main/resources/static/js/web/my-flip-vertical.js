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
	direction: 'vertical', //'vertical'/'horizontal'
	speed: 800,
	effect: 'cube', //'slide' or 'fade' or 'cube' or 'coverflow' or 'flip'
	grabCursor: true,
	pagination: '.swiper-pagination',
	prevButton: '.swiper-button-prev',
	nextButton: '.swiper-button-next'
});
