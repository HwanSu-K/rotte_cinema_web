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

    // 자동 슬라이드
    setInterval(function(){
        if(slidebool == true) {
            $('.banner > .banner_icon > div').eq(1).click();
        }
        else{
            slidebool = true;
        }
    }, 5000);

    // 포스터 슬라이드
    $(".reserv_list").lightSlider({
        loop:false,
        item: 5,
        pager:false,
        controls: false,
        keyPress: false,
        slideMargin:30

    });

    // 배너 동영상
    var banners = $(".banner_img > div");
    var videos = $(".video > div > video");
    $(".banner_play").on("click",function(){
        
        $.each(banners,function(index, item){
            if($(item).hasClass('active'))
            {
                videos.get(index).play(); 
                $(videos.get(index)).css('display','block');
                $(".video").css('visibility','inherit');
                return;
            }
        });
    });

	// 비디오 및 마스크 부분 클릭시 비디오 종료및 마스크 제거.
    $(".video").on("click",function(){
        $.each(banners,function(index, item){
            videos.get(index).load(); 
            $(videos.get(index)).css('display','none');
            $(".video").css('visibility','hidden');
        });
    });
    let sizeCheck = true;

    function size() {
        var windowWidth = $( window ).width();    
        if(windowWidth < 1280 && sizeCheck === true) {
            // 포스터 슬라이드
            $(".reserv_list").lightSlider({
                loop:false,
                item: 2,
                pager:false,
                controls: false,
                keyPress: false,
                slideMargin:30

            });
            sizeCheck = false;
        } else if(windowWidth >= 1280 && sizeCheck === false) {
            // 포스터 슬라이드
            $(".reserv_list").lightSlider({
                loop:false,
                item: 5,
                pager:false,
                controls: false,
                keyPress: false,
                slideMargin:30

            });
            sizeCheck = true;
        }
    }
    size();

    $( window ).resize(function() {
        size();
    });
});