<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/IncludeDefault.jsp"%>

<link rel="stylesheet" href="./style/pay.css" />
</head>
<body>
	<c:import url="/include/header.do" />
	<section class="location">
		<!-- background-color: #f8f8fa; -->
		<div>
			<span><i class="fas fa-home"></i></span> 
			<span>></span> 
			<span>예매정보</span>
		</div>
	</section>
	<div class="content">
		<div class="reserv_title">예매완료</div>
		<div class="reserv_info">
			<div class="reserv_left">
				<div>
					<span>티켓 예매번호</span>
					<div class="number">
					${fn:substring(pay.uid,4,8) }-${fn:substring(pay.uid,8,11) }-${fn:substring(pay.uid,11,16) }
					</div>
				</div>
				<img
					src="./images/poster/${reservation.moviePoster }">
			</div>
			<div class="reserv_right">
				<div>
					예매가 완료되었습니다.<p>!</p>
				</div>
				<div>
					<div>
						<div>예매영화</div>
						<div>${reservation.movieTitle } / 2D(자막)</div>
					</div>
					<div>
						<div>관람극장/상영관</div>
						<div>${reservation.cinemaTitle } / ${reservation.theaterTitle }</div>
					</div>
					<div>
						<div>관람일시</div>
						<div>${reservation.date } (${reservation.week }) ${reservation.startTime }</div>
					</div>
					<div>
						<div>관람인원</div>
						<div>${reservPerson }</div>
					</div>
					<div>
						<div>좌석번호</div>
						<div>${reservSeat }</div>
					</div>
					<div>
						<div>전화번호</div>
						<div>
						<c:choose>
							<c:when test="${fn:length(customer.phonenum) == 11}">
							${fn:substring(customer.phonenum,0,3)}-${fn:substring(customer.phonenum,3,7)}-${fn:substring(customer.phonenum,7,11)}
							</c:when>
							<c:otherwise>
							${fn:substring(customer.phonenum,0,3)}-${fn:substring(customer.phonenum,3,6)}-${fn:substring(customer.phonenum,6,10)}
							</c:otherwise>
						</c:choose>
						</div>
					</div>
					<div>
						<div>결제정보</div>
						<div>
						<fmt:formatNumber value="${pay.amount }" pattern="#,###" /><p>원</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="reserv_btn">
			<div>출력</div>
			<div onclick="location.href='login.do'">예매내역</div>
		</div>
		<div class="reserv_caution">
			<div>
				<div>상영안내</div>
				<span>쾌적한 관람 환경을 위해 상영시간 이전에 입장 부탁드립니다.</span> 
				<span>지연입장에 의한 관람불편을 최소화하고자 본 영화는 10분 후 시작됩니다.</span>
				<span>상영시간 20분전까지 취소 가능하며, 캡쳐화면으로는 입장하실 수 없습니다.</span>
			</div>
			<div>
				<div>주차안내</div>
				<span>로데오타운 건물 지하 1층 ~ 지하 2층 주차장 이용가능하며, 무인정산시스템으로 운영되오니 홈페이지 및 어플 예매 고객님은 매점에서 셀프 주차등록 바랍니다</span>
			</div>
		</div>
	</div>
	<c:import url="/include/footer.do" />
</body>
</html>