// // 원페이지 스크롤 호출
// $(document).ready(function() {
//     $('#content').fullpage({});
// });


// 자동 슬라이드 부분
$(document).ready(function(){
    $('.banner').swipe({
        swipe:function(event, direction, distance, duration, fingerCount, fingerData) {
            if( direction == "left" ){
                $('.banner > .banner_icon > div').eq(1).click();
            }else if( direction == "right" ){
                $('.banner > .banner_icon > div').eq(0).click();
        }
    }});

    var slidebool = true;
    function init() {
    var slides = $('.banner_img > div');
    for (var i = 0; i < slides.length; i++) {
        var div = $("<div></div>");
        if(i == 0) {
            div.addClass('active');    
        }
        $('.dots').append(div);
    }
    
}

    init();

    // 기존 버튼형 슬라이더
    $('.banner > .dots > div').click(function(){
        slidebool = false;

        var $this = $(this);
        var index = $this.index();
        
        $this.addClass('active');
        $this.siblings('.active').removeClass('active');
        
        var $slider = $this.parent().parent();
        
        var $current = $slider.find(' > .banner_img > div.active');
        
        var $post = $slider.find(' > .banner_img > div').eq(index);
        
        $current.removeClass('active');
        $post.addClass('active');
    });

    // 좌/우 버튼 추가 슬라이더
    $('.banner > .banner_icon > div').click(function(){
        slidebool = false;

        var $this = $(this);
        var $slider = $this.closest('.banner');
        
        var index = $this.index();
        var isLeft = index == 0;
        
        var $current = $slider.find(' > .dots > div.active');
        var $post;
        
        if ( isLeft ){
            $post = $current.prev();
        }
        else {
            $post = $current.next();
        };
        
        if ( $post.length == 0 ){
            if ( isLeft ){
                $post = $slider.find(' > .dots > div:last-child');
            }
            else {
                $post = $slider.find(' > .dots > div:first-child');
            }
        };
        
        $post.click();
    });

    setInterval(function(){
        if(slidebool == true) {
            $('.banner > .banner_icon > div').eq(1).click();
        }
        else{
            slidebool = true;
        }
    }, 5000);

    $(".reserv_list").lightSlider({
        loop:false,
        item: 5,
        pager:false,
        controls: false,
        keyPress: false,
        slideMargin:30

    });
});