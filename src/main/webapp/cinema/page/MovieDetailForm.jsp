<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>

	<link rel="stylesheet" href="./style/moviedetail.css" />    
	<script src="./js/moviedetail.js"></script>
</head>
<body>
    <c:import url="/include/header.do"/>
	<section class="location">      <!-- background-color: #f8f8fa; -->
        <div>
            <span><i class="fas fa-home"></i></span>
            <span>></span>
            <span>영화</span>
            <span>></span>
            <span>상세정보</span>
        </div>
    </section>

    <div class="content">
        <div class="movie_info">
            <div class="post">
                <img src="./images/poster/${movie.poster }">
            </div>
            
            <div class="detail_top">
                <div class="title">
                    <span><img src="./images/icon/age_${movie.limitAge }.png"></span>
                    <span>${movie.title }</span>
                </div>
                <div class="info_area1">
                    <div class="area1_section">                   <!-- info_area1 > div:nth-child(1) -->
                        <p>예매율</p>
                        <div>
                            <span><img src="./images/icon/sub4_info_ticket.png"></span>
                            <span>${rank }</span>
                            <span>위</span>
                            <span>${movie.reservRating }%</span>
                        </div>
                    </div>
                    <div class="area1_section">                   <!-- info_area1 > div:nth-child(2) -->
                        <p>누적관객수</p>
                        <div>
                            <span><img src="./images/icon/sub4_info_user.png"></span>
                            <span class="memberCountCon">12345678</span>
                        </div>
                    </div>
                </div>
                <div class="info_area2">
                    <div class="area2_section1">
                        <div class="sub_info">
                            <div>장르</div>
                            <span>${movie.genre }</span>  <span>|</span>
                            <span>${movie.openDate } 개봉</span>    <span>|</span>
                            <span>${movie.runningTime }분</span>
                        </div>
                        <div class="sub_info">
                            <div>감독</div>   <span>${movie.director }</span>
                        </div>
                        <div class="sub_info">
                            <div>출연</div>   <span>${movie.actor }</span>
                        </div>
                    </div>

                    <div class="area2_section2">
                        <div class="movieLike" data-movie-index ="${movie.index }">
                        	<c:if test="${like.indexMovie == movie.index }">
					    		<i class="fas fa-heart active"></i>
					    	</c:if>
					    	<c:if test="${like.indexMovie != movie.index }">
					    		<i class="far fa-heart"></i>
				    		</c:if>
                            <!-- 마우스 오버시 : <i class="fas fa-heart"></i> -->
                            <span>${movie.like }</span>
                        </div>
                        <div onClick="location.href ='ticketing.do?index=${movie.index}'">예매하기</div>
                    </div>
                </div>
            </div>
        </div>


        <div class="detail_tab">
            <div class="tab_title">
                <div>영화정보</div>                      <!-- detail_tab > div:first-child -->
                <div class="active">                                    <!-- detail_tab > div:last-child -->
                    <span>평점 및 관람평</span>
                    <span></span>
                </div>
            </div>

            <div class="tab_info1">

            </div>


            <div class="review_box">
                <div class="grade">
                    <div>총 평점</div>
                    <div><i class="fas fa-star"></i></div>
                    <div>${movie.rating }</div>
                    <div>/</div>
                    <div>10</div>
                </div>
                <form id="reviewInput">
                    <div class="star_score_box" ${empty sessionScope.customer ? 'disabled':'' }>                       <!-- form > .star_score_box -->
                        <span>
                            <span id="score">5</span>
                            <p>점</p>
                        </span>
                        
                        <div class="star">
                            <div class="active" data-value="1"><i class="fas fa-star"></i></div>
                            <div class="active" data-value="2"><i class="fas fa-star"></i></div>
                            <div class="active" data-value="3"><i class="fas fa-star"></i></div>
                            <div class="active" data-value="4"><i class="fas fa-star"></i></div>
                            <div class="active" data-value="5"><i class="fas fa-star"></i></div>
                            <div data-value="6"><i class="fas fa-star"></i></div>
                            <div data-value="7"><i class="fas fa-star"></i></div>
                            <div data-value="8"><i class="fas fa-star"></i></div>
                            <div data-value="9"><i class="fas fa-star"></i></div>
                            <div data-value="10"><i class="fas fa-star"></i></div>
                        </div>
                    </div>
                    <div class="review_write_box">                      <!-- form > .review_write_box > -->
                        <textarea id="txtComment" placeholder=
                        ${empty sessionScope.customer ? '"로그인 후 사용할 수 있습니다"':'"평점 및 영화 관람평을 작성해주세요.&#13;&#10;주제와 무관한 리뷰 또는 스포일러는 표시제한 또는 삭제될 수 있습니다."' }
                        
                        title="관람평 작성" ${empty sessionScope.customer ? 'disabled':'' }></textarea>
                        <span class="byte_info">
                            <strong class="byte">0</strong>/<em>220</em>
                        </span>
                    </div>

                <div class="review_btn${empty sessionScope.customer ? ' disabled':'' }" id="reviewButton">관람평 작성</div>               <!-- form > .review_btn -->
            </form>
                <div class="comment">
                    <div class="comment_title">
                        <div></div>
                        <div>
                            <div data-order-type=""><i class="fas fa-circle active"></i>최신순</div>
                            <div data-order-type="score"><i class="fas fa-circle"></i>평점순</div>
                        </div>
                    </div>
                    <div class="review_comment" id="reviewList">

                    </div>
                </div>
            </div>
        </div>
    </div>   <!--컨텐츠 div-->
    <c:import url="/include/footer.do"/>
</body>
</html>