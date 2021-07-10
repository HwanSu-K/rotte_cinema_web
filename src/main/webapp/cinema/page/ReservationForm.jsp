<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/IncludeDefault.jsp"%>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
<link rel="stylesheet" href="./style/reservation.css" />
<script src="./js/reservation.js"></script>
</head>
<body>
	<c:import url="/include/header.do" />
	<section class="location">      <!-- background-color: #f8f8fa; -->
    <div>
        <span><i class="fas fa-home"></i></span>
        <span>></span>
        <span>좌석예매</span>
    </div>
  </section>
  <div class="content">
    <div class="reserv_title">좌석예매</div>
      <div>
        <div class="reserv_left">
          <div class="reserv_info">
            <div>
              관람인원선택
            </div>
            <div class="reserv_reset">
              <i class="fas fa-sync-alt"></i>
              초기화
            </div>
          </div>
          <div class="reserv_person">
            <div>성인</div>
            <div class="reserv_count" id="adult">
              <div><i class="fas fa-minus"></i></div>
              <div id="adult_count">0</div>
              <div><i class="fas fa-plus"></i></div>
            </div>
            <div>청소년</div>
            <div class="reserv_count" id="teenager">
              <div><i class="fas fa-minus"></i></div>
              <div id="teenager_count">0</div>
              <div><i class="fas fa-plus"></i></div>
            </div>
          </div>
          <div class="reserv_screen">
            <div class="mask">
              <div><img src="./images/icon/bg-seat-count-before.png"><span>관람인원을 선택하십시요</span></div>
            </div>
            <div class="reserv_screen_info">좌석 선택 후 결제하기 버튼을 클릭하세요.</div>
            <div class="reserv_screen_front">
              <p>S</p>
              <p>C</p>
              <p>R</p>
              <p>E</p>
              <p>E</p>
              <p>N</p>
            </div>
            <div class="reserv_seat">
              <c:set var="alhpa"
					value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" />
				<c:set var="array" value="${fn:split(alhpa,',')}" />
				<c:forEach begin="1" end="${reservation.theaterSeatY }"
					varStatus="statusY">
					<div>
						<span>${array[statusY.index - 1]}</span>
						<c:forEach begin="1" end="${reservation.theaterSeatX }"
							varStatus="statusX">
							<div id="x${statusX.index }y${statusY.index }"
								data-seat-x="${statusX.index }" data-seat-y="${statusY.index }">${statusX.index }</div>
						</c:forEach>
					</div>
				</c:forEach>
            </div>
          </div>
        </div>     
        <div class="reserv_right">
          <div class="movie_title">
            <img src="./images/icon/age_${reservation.movieLimitAge}.png">
            <div>
              <div>
                ${reservation.movieTitle }
              </div>
              <div>
                2D(자막)
              </div>
            </div>
          </div>
          <div class="movie_info">
            <div>
              <div>${reservation.cinemaTitle }</div>
              <div>${reservation.theaterTitle }</div>
              <div>${reservation.date } (${reservation.week })</div>
              <div></div>
              <div>${reservation.startTime }~${reservation.endTime }</div>
              <div></div>
            </div>
            <img src="./images/poster/${reservation.moviePoster }">
          </div>
          <div class="movie_seat">
            <div>
              <div class="reserv_seat_info">
                <div>
                  <span class=""></span>
                  <div>선택가능</div>
                </div>  
                <div>
                  <span class="active"></span>
                  <div>선택좌석</div>
                </div>
                <div>
                  <span class="reserv"></span>
                  <div>예매완료</div>
                </div>
                <div>
                  <span class="disabled"></span>
                  <div>선택불가</div>
                </div>
              </div>
            </div>
            <div>
              <div>선택좌석</div>
              <div class="select_seat">
                <div>-</div>
                <div>-</div>
                <div>-</div>
                <div>-</div>
                <div>-</div>
                <div>-</div>
                <div>-</div>
                <div>-</div>
              </div>
            </div>
          </div>
          <div class="movie_amount">
            <div>최종결제금액</div><div><p id="amount">0</p>원</div>
          </div>
          <div class="reserv_pay" id="pay">
            결제
          </div>
        </div>
      </div>
    </div>
  </div>
	<c:import url="/include/footer.do" />
</body>
</html>