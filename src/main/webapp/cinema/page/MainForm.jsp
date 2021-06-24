<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>

    <link rel="stylesheet" href="./style/index.css" />
    <script src="./js/index.js"></script>
    <script src="./plugin/jquery.touchSwipe.min.js"></script>
    <link rel="stylesheet" href="./plugin/lightslider.css" />
    <script src="./plugin/lightslider.js"></script>
</head>
<body>
	<c:set var="path" value="<%=request.getContextPath() %>"/>
	
    <%@ include file="../include/IncludeHeader.jsp" %>
<div class="content">  
    <section class="banner">  <!-- 배너영역 -->
        <div class="banner_img">
        	<c:forEach var="banner" items="${banners}" varStatus="status">
        	<div class="${status.index == 0 ? 'active':'' }" style="background-image:url(${path }/images/banner/${banner.imagePath });"></div>
			</c:forEach>
        </div> 
        <div class="video">
            <div>
	            <c:forEach var="banner" items="${banners}">
	            <video loop><source src="${path }/images/banner/${banner.videoPath }"></video>
				</c:forEach>
            </div>
            <div class="mask"></div>
        </div>
        <div class="banner_icon">        <!-- 화살표 아이콘  < >  -->
            <div><img src="./images/icon/banner_arrow_icon01.png"></div>
            <span class="banner_play"><img src="./images/icon/play_icon.png"></span>                   <!--play 아이콘-->
            <div><img src="./images/icon/banner_arrow_icon02.png"></div>
        </div>
        <div class="dots">
			<c:forEach var="banner" items="${banners}" varStatus="status">
        	<div class="${status.index == 0 ? 'active':'' }"></div>
			</c:forEach>
        </div>
    </section>

    <section class="reserv">
        <div class="reserv_section1">        <!-- reserv클래스 안의 section 01 영역 -->
            <span OnClick="location.href ='movie.do'">
                <span><i class="fas fa-film"></i></span>            <!--1번 아이콘부분-->
                <span>
                    <div>Movie</div>
                    <div>전체영화</div>
                </span>
            </span>
            <span><img src="./images/icon/section2_line.png"></span>     <!--Line 아이콘부분-->

            <span OnClick="location.href ='schedule.do'">
                <span><i class="far fa-calendar-alt"></i></span>            <!--2번 아이콘부분-->
                <span>
                    <div>Timetable</div>
                    <div>상영시간표</div>
                </span>
            </span>

            <span><img src="./images/icon/section2_line.png"></span>     <!--Line 아이콘부분-->

            <span OnClick="location.href ='ticketing.do'">
                <span><i class="fas fa-ticket-alt"></i></span>            <!--3번 아이콘부분-->
                <span>
                    <div>Ticketing</div>
                    <div>빠른예매</div>
                </span>
            </span>
        </div>        
        <div class="reserv_section2">        <!-- reserv클래스 안의 section 02 포스트 슬라이드 영역 -->
            <div class="reserv_more">
	            <span OnClick="location.href ='movie.do'">
	                <span>더 많은 영화보기</span>
	                <img src="./images/icon/plus_icon.png">
	            </span>
            </div>
            <div class="reserv_arrow_left">
                <img src="./images/icon/section2_arrow_left.png">    <!-- 화살표 아이콘 -->
            </div>
            <div class="reserv_arrow_right">
                <img src="./images/icon/section2_arrow_right.png">   <!-- 화살표 아이콘 -->
            </div>
            <div class="reserv_list">
            <c:forEach var="movie" items="${movies}">
                <div>
                    <div>
                        <img src="${path }/images/poster/${movie.poster }">
                        <img src="./images/icon/age_${movie.limitAge }.png">
                    </div>
                    <div>
                        <span>${movie.title }</span>
                    </div>
                    <div>
                        <span>예매율 0.0%</span>
                        <span>|</span>
                        <img src="./images/icon/post_infobox_icon.png">
                        <span>${movie.rating }</span>
                    </div>
                    <div>
                        <div>예매</div>
                    </div>
                </div>
			</c:forEach>
            </div>
        </div>
    </section>


<!-- -------------------------------------------------------------------- -->


    <section class="qration">
        <div class="qration_content">
            <div class="qration_title">
                <span class="title">큐레이션</span>
                <span class="more">
                    <span>큐레이션 더보기</span>
                    <img src="./images/icon/plus_icon.png">
                </span>
            </div>

            <div class="qration_main">
                <div>
                    <img src="./images/qration_img/qration_img_01.jpg">
                </div>       <!-- 큰 포스트 -->
                <div>
                <input type="button" value="상세정보">
                <input type="button" value="예매">
                </div>
            </div>
            
            <div class="qration_sub_info">
                <div class="tag">#클래식소사이어티</div>      <!-- #adb4f8 -->
                <div class="title">2021 빈 필하모닉 여름음악회</div>
                <div>
                    스크린으로 만나는 세계 최대 클래식 여름 축제!<br>
                    2021 빈 필하모닉 여름음악회<br><br>
    
                    [상영정보]<br>
                    - 상영일시 : 2021년 6월 19일(토) 20:00 *딜레이중계<br>
                    - 예매오픈 : 2021년 5월 24일(월) 15:00<br>
                    - 공연시간 : 약 90분 (인터미션 없음)<br>
                </div>
            </div>
            <div class="qration_post">
                <div>
                    <img class="icon" src="./images/icon/qration_c_icon.png">
                    <img src="./images/qration_img/qration_img_01.jpg">
                    <div>2021 빈 필하모닉 여름음악회</div>
                </div>
                <div>
                    <img class="icon" src="./images/icon/qration_c_icon.png">
                    <img src="./images/qration_img/qration_img_02.jpg">
                    <div>낫아웃</div>
                </div>
                <div>
                    <img class="icon" src="./images/icon/qration_c_icon.png">
                    <img src="./images/qration_img/qration_img_03.jpg">
                    <div>학교가는길</div>
                </div>
                <div>
                    <img class="icon" src="./images/icon/qration_f_icon.png">
                    <img src="./images/qration_img/qration_img_04.jpg">
                    <div>인트로덕션</div>
                </div>
            </div>
        </div>
    </section>
    
</div>
    <%@ include file="../include/IncludeFooter.jsp" %>
</body>
</html>