<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>   
	
	<link rel="stylesheet" href="./style/mypage.css" />
	<script src="./js/mypage.js"></script> 
</head>
<body>
    <c:import url="/include/header.do"/>
    <section class="location">      <!-- background-color: #f8f8fa; -->
            <div>
                <span><i class="fas fa-home"></i></span>
                <span>></span>
                <span>마이페이지</span>
            </div>
        </section>
	<div class="content">
		<div class="user">
		    <div class="profile">
		        <div>
		            <img src="./images/icon/RT_user.png">
		            <div>
		                <div class="user_name">
		                ${sessionScope.customer.name} 님
		                <c:if test="${sessionScope.customer.access == 0}">
							<i class="fas fa-unlock" onclick="location.href='./cms/list.do'"></i>
						</c:if>
		                </div>
		                <div class="btn">
		                    <div onclick="location.href='./password.do?key=${sessionScope.customer.key}'">비밀번호변경<i class="fas fa-chevron-right"></i></div>
		                    <div onclick="location.href='./logout.do'">로그아웃<i class="fas fa-chevron-right"></i></div>
		                </div>
		            </div>
		        </div>
		        <div class="user_point">
		            <div>
		                <div>RT POINT</div>
		                <span>0P</span>
		            </div>
		            <div>|</div>
		            <div>
		                <div>쿠폰함</div>
		                <span>0장</span>
		            </div>
		        </div>
		    </div>
		    <div class="info">
		        <div class="title">
		            나의 무비 스토리
		        </div>
		        <div class="list">
		            <div>
		                <div>
		                    <i class="fas fa-video"></i>
		                    <div>본 영화</div>
		                </div>
		                <div>${fn:length(reservs)}</div>
		            </div>
		            <div>
		                <div>
		                    <i class="fas fa-heart"></i>
		                    <div>관심영화</div>
		                </div>
		                <div>${fn:length(likes)}</div>
		            </div>
		            <div>
		                <div>
		                    <i class="fas fa-comment-dots"></i>
		                    <div>관람평</div>
		                </div>
		                <div>${fn:length(reviews)}</div>
		            </div>
		        </div>
		        <div></div>
		    </div>
		</div>

		<div class="ticket">
			<div class="title">
				<div>온라인 티켓</div>
				<div>
					<div id="ticket_plus">더보기<i class="fas fa-plus"></i></div>
				</div>
			</div>
			<div class="info">
				<c:forEach var="reserv" items="${onReservs }" varStatus="status">
				<div <c:if test="${status.index > 0}">class="h"</c:if>>
					<img src="/images/poster/${reserv.poster }">
					<div>
						<div>
							<div>
								<div>${reserv.movie }</div>
								<div>
									2D(자막)
									<p>${reserv.age }세 관람가</p>
								</div>
							</div>
							<div>
								<div>상영일시</div>
								<div>
									<span>${fn:substring(reserv.date,5,10)}(${reserv.week })</span>${reserv.time }
								</div>
							</div>
						</div>
						<div>
							<div>
								<div>상영관</div>
								<div>
									<span>${reserv.cinema }</span>${reserv.theater }
								</div>
							</div>
							<div>
								<div>좌석정보</div>
								<div>
									<span>${reserv.customer }</span>${reserv.seat }
								</div>
							</div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>

		<div class="reserv">
            <div class="title">
                <div>나의 구매 내역</div>
                <div id="reserv_plus">더보기<i class="fas fa-plus"></i></div>
            </div>
            <table>
                <tr>
                    <th></th>
                    <th>관람영화</th>
                    <th>결제일시</th>
                    <th></th>
                </tr>
                <c:forEach var="reserv" items="${reservs }" varStatus="status">
                <tr <c:if test="${status.index > 3}">class="h"</c:if>>
					<td>${status.index + 1 }</td>
                    <td>${reserv.movie }<p>${reserv.cinema } ${reserv.theater }</p></td>
                    <td>${reserv.datePay }</td>
                    <td>
                    <c:if test="${reserv.cancel == true }">
                    	<div>예매취소</div>
                   	</c:if>
                   	
                   	<c:if test="${reserv.cancel == false }">
                    	<div class="h">예매취소</div>
                   	</c:if>
                   	
                    </td>
                </tr>
				</c:forEach>
            </table>
        </div>
        
         <div class="reserv like">
            <div class="title like">
                <div>나의 관심 영화</div>
                <div id="like_plus">더보기<i class="fas fa-plus"></i></div>
            </div>
            <div class="reserv_movie_list">
                <c:forEach var="movie" items="${movies}" varStatus="status">
				<div <c:if test="${status.index > 4}">class="h"</c:if>>
					<div onClick="location.href ='moviedetail.do?index=${movie.index}'">
						<img src="/images/poster/${movie.poster }">
                        <img src="./images/icon/age_${movie.limitAge }.png">
					</div>
					<div>
						<span>${movie.title }</span>
					</div>
					<div>
                        <span>예매율 ${movie.reservRating }%</span>
                        <span>|</span>
                        <img src="./images/icon/post_infobox_icon.png"> 
                        <span>${movie.rating }</span>
                    </div>
				</div>
			</c:forEach>
            </div>
        </div>
	</div>
	<c:import url="/include/footer.do"/>
</body>
</html>