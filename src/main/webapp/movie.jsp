<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>rotte_cinema</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="./style/default.css" />
    <link rel="stylesheet" type="text/css" href="./style/movie.css" />
    <script src="./js/default.js"></script>
    <script src="./plugin/jquery.min.js"></script>
    <script src="./plugin/jquery-ui.min.js"></script>
</head>
<body>
<jsp:include page="./include/header.jsp"/>
<div id="content">
    <section class="location">
        <span><i class="fas fa-home"></i></span>
        <span>></span>
        <span>예매</span>
        <span>></span>
        <span>상영시간표</span>
    </section>
    <section class="reserv_choice">
        <div class="reserv_type">
            <div class="reserv_type_movie">
                <div><i class="fas fa-film"></i></div>
                <div>영화별</div>
            </div>
            <div class="reserv_type_theater">
                <div><i class="fas fa-camera"></i></div>
                <div>극장별</div>
            </div>
        </div>
        <div class="reserv_content">
            <div class="reserv_content_title">
                <span>전체영화</span>
                <span>큐레이션</span>
            </div>
            <div class="reserv_content_list">
                <div>캐시트럭</div>
                <div>크루엘라</div>
                <div>컨저링3:악마가 시켰다.</div>
                <div>미스피츠</div>
                <div>뱅드림! 로젤리아 에피소드</div>
                <div>2021 빈 필하모닉 여름음악회</div>
                <div>분노의질주:더 얼티메이트</div>
                <div>극장판 귀멸의칼날:무한열차</div>
                <div>낫아웃</div>
                <div>프로페서 앤 매드맨</div>
                <div>콰이어트 플레이스2</div>
                <div>파이프라인</div>
            </div>
            
        </div>
        <div class="reserv_post">
            <img src="./images/post_img/movie01_kruella.jpg">
        </div>
    </section>
    <section class="reserv_info">
        <div class="reserv_info_title">
            <span>크루엘라</span>
            <span>상영시간표</span>
        </div>
        <div class="reserv_info_date">
            <div><i class="fas fa-chevron-left"></i></div>
            <div>
                <div>
                    <div>9</div>
                    <div>오늘</div>
                </div>
                <div>
                    <div>10</div>
                    <div>내일</div>
                </div>
                <div>
                    <div>11</div>
                    <div>금</div>
                </div>
                <div>
                    <div>12</div>
                    <div>토</div>
                </div>
                <div>
                    <div>13</div>
                    <div>일</div>
                </div>
                <div>
                    <div>14</div>
                    <div>월</div>
                </div>
                <div>
                    <div>15</div>
                    <div>화</div>
                </div>
                <div>
                    <div>16</div>
                    <div>수</div>
                </div>
                <div>
                    <div>17</div>
                    <div>목</div>
                </div>
                <div>
                    <div>18</div>
                    <div>금</div>
                </div>
                <div>
                    <div>19</div>
                    <div>토</div>
                </div>
                <div>
                    <div>20</div>
                    <div>일</div>
                </div>
                <div>
                    <div>21</div>
                    <div>월</div>
                </div>
                <div>
                    <div>22</div>
                    <div>화</div>
                </div>
            </div>
            <div><i class="fas fa-chevron-right"></i></div>
            <div><i class="far fa-calendar-alt"></i></div>
        </div>
        <div class="reserv_info_age">
            <div>
                <img src="images/icon/age_0.png">
                <span>전체 관람</span>
            </div>
            <div>
                <img src="images/icon/age_12.png">
                <span>만 12세 이상</span>
            </div>
            <div>
                <img src="images/icon/age_15.png">
                <span>만 15세 이상</span>
            </div>
            <div>
                <img src="images/icon/age_19.png">
                <span>청소년 관람불가(만 18세 이상)</span>
            </div>
        </div>
        <div class="reserv_info_movie">
            <div>
                <img src="images/icon/age_12.png">
                <span>크루엘라</span>
            </div>
            <div>
                <span>상영중</span>
                <span>/상영시간134분</span>
            </div>
        </div>
        <div class="reserv_info_local">
            <div>서울</div>
            <div>대구</div>
        </div>
        <div class=reserv_info_list">
            <div class="reserv_info_list_title">
                강남
            </div>
            <div>
                <div>
                    <div>1관</div>
                    <div>총 232석</div>
                </div>
                <div>
                    <div>2D(자막)</div>
                </div>
                <div>
                    <div>17:50</div>
                    <div>140석</div>
                </div>
            </div>
        </div>

    </section>

</div>
<jsp:include page="./include/footer.jsp"/>
</body>
</html>