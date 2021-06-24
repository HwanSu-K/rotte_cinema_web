<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
<nav>
    <ul>
        <li>
            <div class="header_menu"></div>
            <div class="header_search"></div>
        </li>
        <li class="nav_over">영화
            <ul>
                <li><a href="./movie.do">전체영화</a></li>
                <li><a href="./qration.do">큐레이션</a></li>
            </ul>
        </li>
        <li class="nav_over">예매
            <ul>
                <li><a href="./ticketing.do">빠른예매</a></li>
                <li><a href="./schedule.do">상영 시간표</a></li>
            </ul>
        </li>
        <li>
 	        <div class="header_logo" onclick="location.href='./main.do';"></div>
        </li>
        <li class="nav_over">이벤트
            <ul>
                <li><a href="./event.do">전체 이벤트</a></li>
                <li><a href="./event.do">진행 이벤트</a></li>
            </ul>
        </li>
        <li class="nav_over">시설안내
            <ul>
                <li><a href="./about.do">회사 소개</a></li>
                <li><a href="./location.do">오시는 길</a></li>
            </ul>
        </li>
        <li>
            <div onclick="location.href='./ticketing.do';" class="header_calendar"></div>
            <div onclick="location.href='./login.do';" class="header_user"></div>
        </li>
        
    </ul>
    <div class="nav_bg"></div>
    <div class="nav_map">
        <div>
            <div><span>SITEMAP</span></div>
            <ul>
                <li><div>영화</div>
                    <ul>
                        <li><a href="./movie.do">전체영화</a></li>
                        <li><a href="./qration.do">큐레이션</a></li>
                    </ul>
                </li>
                <li><div>예매</div>
                    <ul>
                        <li><a href="./ticketing.do">빠른예매</a></li>
                        <li><a href="./schedule.do">상영 시간표</a></li>
                    </ul>
                </li>
                <li><div>이벤트</div>
                    <ul>
                        <li><a href="./event.do">전체 이벤트</a></li>
                        <li><a href="./evnet.do">진행 이벤트</a></li>
                    </ul>
                </li>
                <li><div>시설안내</div>
                    <ul>
                        <li><a href="./about.do">회사 소개</a></li>
                        <li><a href="./location.do">오시는 길</a></li>
                    </ul>
                </li>
            </ul>
        </div> 
    </div>
    <div class="nav_search">
        <div>
            <div>
                <div><img src="./images/post_img/movie01_kruella.jpg"></div>
                <div>
                    <div>
                        <span>1</span>
                        <span>루카</span>
                    </div>
                    <div>
                        <span>2</span>
                        <span>킬러의 보디가드2</span>
                    </div>
                    <div>
                        <span>3</span>
                        <span>발신제한</span>
                    </div>
                    <div>
                        <span>4</span>
                        <span>콰이어트 플레이스2</span>
                    </div>
                    <div>
                        <span>5</span>
                        <span>여고괴담 여섯번째 이야기:모교</span>
                    </div>
                </div>
            </div>
            <div><input type="text" placeholder="영화를 검색하세요">
                <i class="fas fa-search"></i></div>
        </div>
    </div>
</nav>

<script>
$(document).ready(function(){
    // 네비바 마우스 오버
    $('.nav_over').mouseenter(function() {
        if($(".nav_map").hasClass('active') === false && $(".nav_search").hasClass('active') === false) {
            $('.nav_bg').addClass('active');
        }
    });

    $('.nav_over').mouseleave(function() {
        if($(".nav_map").hasClass('active') === false && $(".nav_search").hasClass('active') === false) {
            $('.nav_bg').removeClass('active');
        }
    });

    var menus = $('nav > ul > li > div');

    $('.header_menu').click(function(event) {
        $('.nav_map').toggleClass('active')
        $('.header_menu').toggleClass('active')
        $('nav').toggleClass('active')

        if(typeof event.originalEvent != "undefined"){
            if($('.header_search').hasClass('active')) {
                $('.header_search').click();
            }
        }

    });

    $('.header_search').click(function(event) {
        $('.nav_search').toggleClass('active')
        $('.header_search').toggleClass('active')
        $('nav').toggleClass('active')

        if(typeof event.originalEvent != "undefined"){
            if($('.header_menu').hasClass('active')) {
                $('.header_menu').click();
            }
        }
    });
});
</script>
</header> 