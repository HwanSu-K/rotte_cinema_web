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
				<img src="./images/icon/RT_user.png">
				<div>
					<div class="user_name">${sessionScope.customer.name} 님</div>
					<div class="user_email">${sessionScope.customer.email}</div>
				</div>
			</div>
			<div class="info">
				<div class="title">나의 무비 스토리</div>
				<div class="list">
					<div>
						<div>본 영화</div>
						<div>${fn:length(reservs)} </div>
					</div>
					<div>
						<div>관심영화</div>
						<div>${fn:length(likes)} </div>
					</div>
					<div>
						<div>관람평</div>
						<div>${fn:length(reviews)} </div>
					</div>
				</div>
			</div>
		</div>

		<div class="ticket">
			<div class="title">
				<div>온라인 티켓</div>
				<div>
					<div>더보기</div>
					<img src="./images/icon/plus_icon.png">
				</div>
			</div>
			<div class="info">
				<c:forEach var="reserv" items="${reservs }" end="0">
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
				</c:forEach>
			</div>
		</div>


		<div class="reserv">
			<div class="title">
				<div>나의 예매내역</div>
				<div>
					<div>더보기</div>
					<img src="./images/icon/plus_icon.png">
				</div>
			</div>
			<div class="reserv_list">
			<c:forEach var="reserv" items="${reservs }" end="2">
				<div>
					<div>
						${reserv.movie }<p>${reserv.cinema } ${reserv.theater }</p>
					</div>
					<div>${reserv.date } ${reserv.time }</div>
				</div>
			</c:forEach>
			</div>
		</div>
		<a style="color: #000" href="./logout.do">로그아웃</a>
		<c:if test="${sessionScope.customer.access == 0}">
			<br>
			<a style="color: #000" href="./cms/list.do">컨텐츠 관리 시스템</a>
			<br>
		</c:if>
	</div>
	<c:import url="/include/footer.do"/>
</body>
</html>