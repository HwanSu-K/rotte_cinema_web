<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/IncludeDefault.jsp"%>

<link rel="stylesheet" href="./style/ticketing.css" />
<script src="./js/ticketing.js"></script>
</head>
<body>
	<c:import url="/include/header.do"/>
	<section class="location">
		<!-- background-color: #f8f8fa; -->
		<div>
			<span><i class="fas fa-home"></i></span> <span>></span> <span>예매</span>
			<span>></span> <span>빠른예매</span>
		</div>
	</section>
	<div class="content">
		<section class="reserv">
			<div class="reserv_tab">
				<div class="${param.index == null ? 'active' : ''}">
					<span>STEP 1</span> <span>영화선택</span>
				</div>
				<div class="${param.index != null ? 'active' : ''}">
					<span>STEP 2</span> <span>영화관 선택</span>
				</div>
				<div>
					<span>STEP 3</span> <span>시간 선택</span>
				</div>
			</div>

			<div class="reserv_info_age">
				<div>
					<img src="./images/icon/age_0.png"> <span>전체 관람</span>
				</div>
				<div>
					<img src="./images/icon/age_12.png"> <span>만 12세 이상</span>
				</div>
				<div>
					<img src="./images/icon/age_15.png"> <span>만 15세 이상</span>
				</div>
				<div>
					<img src="./images/icon/age_18.png"> <span>청소년 관람불가(만 18세 이상)</span>
				</div>
			</div>

			<article class="movie_choice ${param.index == null ? 'active' : ''}">
				<div class="reserv_movie_tab">
					<div class="${param.type == null ? 'active' : ''}" OnClick="location.href ='ticketing.do'">예매순</div>
					<!-- <div class="${param.type == 'soon' ? 'active' : ''}" OnClick="location.href ='ticketing.do?type=soon'">예정작</div> -->
					<div class="${param.type == 'rating' ? 'active' : ''}" OnClick="location.href ='ticketing.do?type=rating'">평점순</div>
				</div>

			<div class="reserv_movie_list">
			<c:forEach var="movie" items="${movies}">
				<div>
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
					<div>
						<div onClick="location.href ='ticketing.do?index=${movie.index}'">예매</div>
					</div>
				</div>
			</c:forEach>
			</div>
			</article>

			<article class="theater_choice ${param.index != null ? 'active' : ''}">
				<div>
					<div class="theater_movie">
					<c:if test="${movie != null}">
						<img src="/images/poster/${movie.poster }">
                        <img src="./images/icon/age_${movie.limitAge }.png">
					</c:if>
					</div>
					<div class="theater_list">
						<div class="theater_list_title">
						<c:forEach var="local" items="${locals}" varStatus="status">
							<div class="locals" data-local-class="${local.localClass }">${local.localName }</div>
							<c:if test="${!status.last }">
									<span>|</span>
							</c:if>
						</c:forEach>
						</div>

						<div class="theater_list_tab">
							<c:forEach var="cinema" items="${cinemas}">
								<div data-cinema-index="${cinema.index }" data-local-class="${cinema.localClass }" class="${cinema.count == 0 ? 'disabled':'' }">${cinema.title }</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</article>

			<article class="datetime_choice">
                <div>
                    <div class="datetime_movie">
                        <c:if test="${movie != null}">
						<img src="/images/poster/${movie.poster }">
                        <img src="./images/icon/age_${movie.limitAge }.png">
						</c:if>
                    </div>
                    <div class="datetime_list">
                        <div>
                            <div class="datetime_list_title">강남</div>
                            <div><div>상영중</div><div>/</div><div id="minute">${movie.runningTime }분</div></div>
                        </div>
                        <div class="datetime_list_tab">
                        </div>
                    </div>
                </div>
            </article>

		</section>
	</div>
	<c:import url="/include/footer.do"/>
</body>
</html>