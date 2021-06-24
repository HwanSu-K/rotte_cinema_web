<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/IncludeDefault.jsp"%>

<link rel="stylesheet" href="./style/ticketing.css" />
</head>
<body>
<c:set var="path" value="<%=request.getContextPath() %>"/>
	<%@ include file="../include/IncludeHeader.jsp"%>
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
				<div class="active">
					<span>STEP 1</span> <span>영화선택</span>
				</div>
				<div>
					<span>STEP 2</span> <span>영화관 선택</span>
				</div>
				<div>
					<span>STEP 3</span> <span>시간 선택</span>
				</div>
			</div>

			<div class="reserv_info_age">
				<div>
					<img src="images/icon/age_0.png"> <span>전체 관람</span>
				</div>
				<div>
					<img src="images/icon/age_12.png"> <span>만 12세 이상</span>
				</div>
				<div>
					<img src="images/icon/age_15.png"> <span>만 15세 이상</span>
				</div>
				<div>
					<img src="images/icon/age_18.png"> <span>청소년 관람불가(만
						18세 이상)</span>
				</div>
			</div>

			<article class="movie_choice active">
				<div class="reserv_movie_tab">
					<div class="active">예매순</div>
					<div>예정작</div>
					<div>평점순</div>
				</div>

			<div class="reserv_movie_list">
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
			</article>

			<article class="theater_choice">
				<div></div>
				<div>
					<div class="theater_movie">
						<img src="./images/post_img/movie01_kruella.jpg">
					</div>
					<div class="theater_list">
						<div class="theater_list_title">
							<div class="active">서울</div>
							<div>|</div>
							<div>대구</div>
						</div>

						<div class="theater_list_tab">
							<div class="active">강남</div>
							<div>강남대로(시티)</div>
							<div>강동</div>
							<div>군자</div>
							<div>동대문</div>
							<div>마곡</div>
							<div>목동</div>
						</div>
					</div>
				</div>
			</article>

			<article class="datetime_choice">
				<div></div>
				<div>
					<div class="datetime_movie">
						<img src="./images/post_img/movie01_kruella.jpg">
					</div>
					<div class="datetime_list">
						<div class="datetime_list_title">강남</div>

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
								<span>140석</span>
							</div>
						</div>
						<div>
							<div>
								<div>4관</div>
								<div>총 103석</div>
							</div>
							<div>
								<div>2D(자막)</div>
							</div>
							<div>
								<div>13:40</div>
								<span>65석</span>
							</div>
							<div>
								<div>21:10</div>
								<span>66석</span>
							</div>
						</div>
						<div>
							<div>
								<div>6관</div>
								<div>총 103석</div>
							</div>
							<div>
								<div>2D(자막)</div>
							</div>
							<div>
								<div>12:30</div>
								<span>63석</span>
							</div>
							<div>
								<div>14:50</div>
								<span>65석</span>
							</div>
							<div>
								<div>17:10</div>
								<span>62석</span>
							</div>
							<div>
								<div>19:30</div>
								<span>43석</span>
							</div>
							<div>
								<div>21:50</div>
								<span>63석</span>
							</div>
						</div>
					</div>
				</div>
			</article>

		</section>
	</div>
	<%@ include file="../include/IncludeFooter.jsp"%>
</body>
</html>