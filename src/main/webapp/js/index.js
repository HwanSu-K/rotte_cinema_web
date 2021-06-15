// 원페이지 스크롤 호출
$(document).ready(function() {
    $('#content').fullpage({});
});

// 자동 슬라이드 부분
$(document).ready(function(){
    $('.banner').swipe({
        swipe:function(event, direction, distance, duration, fingerCount, fingerData) {
            if( direction == "left" ){
                nextSlide(1);
            }else if( direction == "right" ){
                nextSlide(-1);
        }
    }});
    
    $('.banner_icon > div:first-child').click(function(){
        userSlide(-1);
    })

    $('.banner_icon > div:last-child').click(function(){
        userSlide(1);
    })

    autoSlides();
    init();

});

var slideIndex = 0;
var slidebool = true;
function init() {
    var slides = $('.banner_img > img');
    for (var i = 0; i < slides.length; i++) {
        var div = $("<div></div>").addClass('dot');
        if(i == 0) {
            div.addClass('active');    
        }
        $('.dots').append(div);
    }
    
}
function autoSlides() {
    if(slidebool == true){
    slideIndex++;
    slide();
    }
    else{
        slidebool = true;
    }
    setTimeout(autoSlides, 5000); // Change image every 2 seconds
}

function userSlide(vector) {
    slidebool = false;
    slideIndex += vector;
    slide();
}

function slide(){
    var slides = $('.banner_img > img');
    if (slideIndex > slides.length) {slideIndex = 1}    
    if (slideIndex <= 0) {slideIndex = slides.length}  
    $('.dot').removeClass('active');
    $('.banner_img').animate({ marginLeft : -(slideIndex-1) * $('.banner_img').width() });
    $('.dot:nth-child(' + slideIndex +')').addClass('active');
}
