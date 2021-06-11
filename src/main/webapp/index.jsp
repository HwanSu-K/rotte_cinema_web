<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>rotte_cinema</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="./plugin/jquery.fullPage.css" />
    <link rel="stylesheet" type="text/css" href="./style/default.css" />
    <link rel="stylesheet" type="text/css" href="./style/index.css" />
    <script src="./plugin/jquery.min.js"></script>
    <script src="./plugin/jquery-ui.min.js"></script>
    <script src="./js/default.js"></script>
</head>
<body>
<jsp:include page="./include/header.jsp"/>




<div id="content">
	<div class="section">           <!-- 배너영역 -->
        <section class="banner">
            <div class="banner_img"><img src="./images/banner_img/banner02.jpg"></div>
            <div class="banner_icon">        <!-- 화살표 아이콘  < >  -->
                <img src="./images/icon/banner_arrow_icon01.png">
                <img src="./images/icon/play_icon.png">                   <!--play 아이콘-->
                <img src="./images/icon/banner_arrow_icon02.png">
            </div>
        </section>
    </div>

	<div class="section">
    <section class="reserv">
        <div class="reserv_section1">        <!-- reserv클래스 안의 section 01 영역 -->
            <span>
                <span><i class="fas fa-film"></i></span>            <!--1번 아이콘부분-->
                <span>
                    <div>Movie</div>
                    <div>전체영화</div>
                </span>
            </span>
            <span><img src="./images/icon/section2_line.png"></span>     <!--Line 아이콘부분-->

            <span>
                <span><i class="far fa-calendar-alt"></i></span>            <!--2번 아이콘부분-->
                <span>
                    <div>Timetable</div>
                    <div>상영시간표</div>
                </span>
            </span>

            <span><img src="./images/icon/section2_line.png"></span>     <!--Line 아이콘부분-->

            <span>
                <span><i class="fas fa-ticket-alt"></i></span>            <!--3번 아이콘부분-->
                <span>
                    <div>Ticketing</div>
                    <div>빠른예매</div>
                </span>
            </span>
        </div>        

        <div class="reserv_section2">        <!-- reserv클래스 안의 section 02 포스트 슬라이드 영역 -->
            <div class="reserv_more">
                <a href="">
                    <span>
                        <span>더 많은 영화보기</span>
                        <img src="./images/icon/plus_icon.png">
                    </span>
                </a>
            </div>
            <div class="reserv_arrow_left">
                <img src="./images/icon/section2_arrow_left.png">    <!-- 화살표 아이콘 -->
            </div>
            <div class="reserv_arrow_right">
                <img src="./images/icon/section2_arrow_right.png">   <!-- 화살표 아이콘 -->
            </div>
            <div class="reserv_list">
            <span>
                <div><img src="./images/post_img/movie01_kruella.jpg"></div>
                <div class="post_info">                   <!-- 1포스트 영화 정보 박스 -->
                    <div>크루엘라</div>
                    <span>예매율 34.9%<span>|</span><img src="./images/icon/post_infobox_icon.png">9.2</span>
                </div>
                <div><input type="button" value="예매"></div>
            </span>

            <span>
                <div><img src="./images/post_img/movie02_ultimate.jpg"></div>
                <div class="post_info">                   <!-- 2포스트 영화 정보 박스 -->
                    <div>분노의 질주:더 얼티메이트</div>
                    <span>예매율 19.0%<span>|</span><img src="./images/icon/post_infobox_icon.png"> 8.5</span>
                </div>
                <div><input type="button" value="예매"></div>
            </span>

            <span>
                <div><img src="./images/post_img/movie03_pipeline.jpg"></div>
                <div class="post_info">                   <!-- 3포스트 영화 정보 박스 -->
                    <div>파이프라인</div>
                    <span>예매율 15.3%<span>|</span><img src="./images/icon/post_infobox_icon.png">7.6</span>
                </div>
                <div><input type="button" value="예매"></div>
            </span>

            <span>
                <div><img src="./images/post_img/movie04_luca.jpg"></div>
                <div class="post_info">                   <!-- 4포스트 영화 정보 박스 -->
                    <div>루카</div>
                    <span>예매율 10.0%<span>|</span><img src="./images/icon/post_infobox_icon.png">6.7</span>
                </div>
                <div><input type="button" value="예매"></div>
            </span>
            </div>
        </div>
    </section>
    </div>


<!-- -------------------------------------------------------------------- -->


	<div class="section">
        <section class="qration">
        <div class="qration_info">
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
        </div>     
        <jsp:include page="./include/footer.jsp"/>
        </section>
    </div>
</div>

<script type="text/javascript" src="./plugin/jquery.fullPage.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#content').fullpage({});
    });
</script>

</body>
</html>