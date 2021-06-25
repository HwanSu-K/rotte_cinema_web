<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="<%=request.getContextPath() %>" scope="application"/>
<header>
<nav>
    <ul>
        <li>
            <div class="header_menu"></div>
            <div class="header_search"></div>
        </li>
        <li class="nav_over" onclick="location.href='./movie.do';">영화
            <ul>
                <li><a href="./movie.do">전체영화</a></li>
                <li><a href="./movie.do?tab=2">큐레이션</a></li>
            </ul>
        </li>
        <li class="nav_over" onclick="location.href='./ticketing.do';">예매
            <ul>
                <li><a href="./ticketing.do">빠른예매</a></li>
                <li><a href="./schedule.do">상영시간표</a></li>
            </ul>
        </li>
        <li>
 	        <div class="header_logo" onclick="location.href='./main.do';"></div>
        </li>
        <li class="nav_over" onclick="location.href='./event.do';">이벤트
            <ul>
                <li><a href="./event.do">전체이벤트</a></li>
                <li><a href="./event.do">진행이벤트</a></li>
            </ul>
        </li>
        <li class="nav_over" onclick="location.href='./about.do';">시설안내
            <ul>
                <li><a href="./about.do">극장안내</a></li>
                <li><a href="./location.do">오시는길</a></li>
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
                        <li><a href="./movie.do?tab=2">큐레이션</a></li>
                    </ul>
                </li>
                <li><div>예매</div>
                    <ul>
                        <li><a href="./ticketing.do">빠른예매</a></li>
                        <li><a href="./schedule.do">상영시간표</a></li>
                    </ul>
                </li>
                <li><div>이벤트</div>
                    <ul>
                        <li><a href="./event.do">전체이벤트</a></li>
                        <li><a href="./evnet.do">진행이벤트</a></li>
                    </ul>
                </li>
                <li><div>시설안내</div>
                    <ul>
                        <li><a href="./about.do">극장안내</a></li>
                        <li><a href="./location.do">오시는길</a></li>
                    </ul>
                </li>
            </ul>
        </div> 
    </div>
    <div class="nav_search">
        <div>
            <div>
            	<c:forEach var="movie" items="${sMovies}" end="0">
                <div onClick="location.href ='moviedetail.do?no=${movie.index}'"><img src="${path }/images/poster/${movie.poster }"></div>
                </c:forEach>
                <div>
	                <c:forEach var="movie" items="${sMovies}" varStatus="status" end="4">
	                <div onClick="location.href ='moviedetail.do?no=${movie.index}'">
                        <span>${status.count}</span>
                        <span>${movie.title}</span>
                    </div>
	                </c:forEach>
                </div>
            </div>
            <div>
            	<form action="movie.do" method="get" id="search">
				<input type="text" name="search" placeholder="영화를 검색하세요">
				<i class="fas fa-search" onclick="document.getElementById('search').submit();"></i>
				</form>
            </div>
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