<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>
	
	<link rel="stylesheet" href="./style/schedule.css" />    
    <script src="./js/schedule.js"></script>
</head>
<body>
	<c:import url="/include/header.do"/>
	<section class="location">      <!-- background-color: #f8f8fa; -->
		<div>
			<span><i class="fas fa-home"></i></span>
			<span>></span>
			<span>예매</span>
			<span>></span>
			<span>상영시간표</span>
		</div>
	</section>
	<div class="content">
		<c:forEach var="date" items="${dates}" varStatus="status">
						<div>${date}</div>
		</c:forEach>
	    <section class="reserv_choice">
	        <div class="reserv_type">
	            <div class="reserv_type_movie active">
	                <div> </div>
	                <div>영화별</div>
	            </div>
	            <div class="reserv_type_theater">
	                <div></div>
	                <div>극장별</div>
	            </div>
	        </div>
	        <div class="reserv_content">
	            <div class="reserv_content_title">
	                <span class="click">전체영화</span>
	                <span>큐레이션</span>
	            </div>
	            <div class="reserv_content_list">    
					<c:forEach var="movie" items="${movies}" varStatus="status">
						<div class="${status.index == 0 ? 'active':'' }">${movie.title }</div>
					</c:forEach>
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
	            <div><i class="fas fa-chevron-left"></i></div>      <!-- div 1 폰트 어썸 화살표 아이콘-->
	            <div>
	                <div class="active">
	                    <div><fmt:formatDate value="${now}" pattern="dd"/></div>
	                    <div>오늘</div>
	                </div>
	                <div>
	                    <div><fmt:formatDate value="${now}" pattern="dd"/></div>
	                    <div>내일</div>
	                </div>
	                <div>
	                    <div>11</div>
	                    <div>금</div>
	                </div>
	                <div class="b">
	                    <div>12</div>
	                    <div>토</div>
	                </div>
	                <div class="r">
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
	                <div class="g">
	                    <div>16</div>
	                    <div>수</div>
	                </div>
	                <div class="g">
	                    <div>17</div>
	                    <div>목</div>
	                </div>
	                <div class="g">
	                    <div>18</div>
	                    <div>금</div>
	                </div>
	                <div class="g">
	                    <div>19</div>
	                    <div>토</div>
	                </div>
	                <div class="g">
	                    <div>20</div>
	                    <div>일</div>
	                </div>
	                <div class="g">
	                    <div>21</div>
	                    <div>월</div>
	                </div>
	                <div class="g">
	                    <div>22</div>
	                    <div>화</div>
	                </div>
	            </div>
	            <div><i class="fas fa-chevron-right"></i></div>         <!--  3 폰트어썸 화살표 아이콘  -->
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
	                <img src="images/icon/age_18.png">
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
	                <span>/상영시간 134분</span>
	            </div>
	        </div>
	        <div class="reserv_info_local">
	            <div class="active">서울</div>
	            <div>대구</div>
	        </div>
	        <div class="reserv_info_list">
	            <div class="reserv_info_list_title">
	                강남
	            </div>
	            <div class="reserv_info_list_theater">
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
	                    <div class="">
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
	
	    </section>
	    <section class="reserv_warning">
	            <div>・ 지연입장에 의한 관람불편을 최소화하고자 본 영화는 약 10분 후 시작됩니다.</div>
	            <div>・ 쾌적한 관람 환경을 위해 상영시간 이전에 입장 부탁드립니다.</div>
	    </section>
	</div>
	<c:import url="/include/footer.do"/>
</body>
</html>