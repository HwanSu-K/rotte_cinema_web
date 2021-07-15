<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="${path }/js/header.js"></script>

<header>
<nav>
    <i id="toggle" class="fas fa-bars"></i>
    <ul>
        <li>
            <div class="header_menu"></div>
            <div class="header_search"></div>
        </li>
        <li class="nav_over" onclick="location.href='${path }/movie.do';">영화
            <ul>
                <li><a href="${path }/movie.do">전체영화</a></li>
                <li><a href="${path }/movie.do?type=qration">큐레이션</a></li>
            </ul>
        </li>
        <li class="nav_over" onclick="location.href='${path }/ticketing.do';">예매
            <ul>
                <li><a href="${path }/ticketing.do">빠른예매</a></li>
                <li><a href="${path }/schedule.do">상영시간표</a></li>
            </ul>
        </li>
        <li>
 	        <div class="header_logo" onclick="location.href='${path }/main.do';"></div>
        </li>
        <li class="nav_over" onclick="location.href='${path }/event.do';">이벤트
            <ul>
                <li><a href="${path }/event.do">전체이벤트</a></li>
                <li><a href="${path }/event.do">진행이벤트</a></li>
            </ul>
        </li>
        <li class="nav_over" onclick="location.href='${path }/about.do';">시설안내
            <ul>
                <li><a href="${path }/about.do">시설안내</a></li>
                <li><a href="${path }/location.do">극장안내</a></li>
            </ul>
        </li>
        <li>
            <div onclick="location.href='${path }/ticketing.do';" class="header_calendar"></div>
            <div onclick="location.href='${path }/login.do';" class="header_user"></div>
        </li>
        
    </ul>
    <div class="nav_bg"></div>
    <div class="nav_map">
        <div>
            <div><span>SITEMAP</span></div>
            <ul>
                <li><div>영화</div>
                    <ul>
                        <li><a href="${path }/movie.do">전체영화</a></li>
                        <li><a href="${path }/movie.do?tab=2">큐레이션</a></li>
                    </ul>
                </li>
                <li><div>예매</div>
                    <ul>
                        <li><a href="${path }/ticketing.do">빠른예매</a></li>
                        <li><a href="${path }/schedule.do">상영시간표</a></li>
                    </ul>
                </li>
                <li><div>이벤트</div>
                    <ul>
                        <li><a href="${path }/event.do">전체이벤트</a></li>
                        <li><a href="${path }/evnet.do">진행이벤트</a></li>
                    </ul>
                </li>
                <li><div>시설안내</div>
                    <ul>
                        <li><a href="${path }/about.do">시설안내</a></li>
                        <li><a href="${path }/location.do">극장안내</a></li>
                    </ul>
                </li>
            </ul>
        </div> 
    </div>
    <div class="nav_search">
        <div>
            <div>
            	<div id="headerPoster"><img></div>
                <div class="movie_List">
                </div>
            </div>
            <div>
            	<div></div>
            	<form id="search">
				<input id="searchText" type="text" name="search" placeholder="영화를 검색하세요">
				<i class="fas fa-search" onclick="document.getElementById('search').submit();"></i>
				</form>
				<div id="search_List"></div>
            </div>
        </div>
    </div>
</nav>
<div id="bg_mask">
    <img src="./images/icon/loading.gif">
</div>
</header> 