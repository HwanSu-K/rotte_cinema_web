<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/IncludeDefault.jsp"%>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=222a1aea3eb30dafa3b98b821fed8f7d"></script>
<link rel="stylesheet" href="./style/location.css" />

<script src="./js/location.js"></script>
</head>
<body>
	<c:import url="/include/header.do" />
	<section class="location">
		<!-- background-color: #f8f8fa; -->
		<div>
			<span><i class="fas fa-home"></i></span> <span>></span> <span>극장안내</span>
			<span></span> <span></span>
		</div>
	</section>
	<div class="content">
		<section class="reserv_choice">
			<div class="reserv_type">
				<div class="reserv_type_movie active" onclick="location.href ='schedule.html'">
					<div></div>
					<div>극장별</div>
				</div>
			</div>
			<section class="reserv_theater">
				<div class="reserv_content">
					<div class="reserv_content_title">
						<c:forEach var="local" items="${locals}" varStatus="status">
							<span class="locals" data-tab-type="${local.localClass }">${local.localName }</span>
						</c:forEach>
					</div>
					<div class="reserv_content_list">
						<c:forEach var="cinema" items="${cinemas}">
							<div data-cinema-index="${cinema.index }"
								data-movie-type="${cinema.localClass }">${cinema.title }</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</section>
		<div class="cinema_info">
			<div class="info_title"></div>
			<div class="info_map">
				<div class="info_addr">
					<p>도로명주소 : </p>
					<p><p>
				</div>
				<div class="map_wrap">
					<div id="map"
						style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
					<div class="custom_zoomcontrol radius_border">
						<span id="zooIn"><img
							src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png"
							alt="확대"></span> <span id="zooOut"><img
							src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png"
							alt="축소"></span>
					</div>
				</div>
			</div>
			<div class="info_transport">
				<div>대중교통</div>
				<span>
					<div>
						<div>
							<img src="./images/icon/ico-bus.png">
						</div>
						<div>
							<span>버스</span>
							<ul>
								<li>서초03번, 3412번, 4412번</li>
								<li>140번, 144번, 145번, 360번, 400번, 402번, 407번, 420번, 421번,
									440번, 441번, 471번, 542번, 643번, N13번, N37번</li>
								<li>1005-1번, 1100번, 1550번, 1570번, 3030번, 3100번, 3600번,
									6000번, 6009번, 6500번, 6009-1번, 8001번, 8541번, 9600번, 9700번,
									9404번, 9408번</li>
							</ul>
						</div>
					</div>
					<div>
						<div>
							<img src="./images/icon/ico-metro.png">
						</div>
						<div>
							<span>지하철</span>
							<ul>
								<li>지하철 2호선 '강남역'하차 -> 지하철 11번 출구 이용 도보 3분 (250m)</li>
								<li>지하철 9호선 '신논현역'하차 -> 지하철 5번 출구 이용 도보 8분 (500m)</li>
							</ul>
						</div>
					</div>
				</span>
			</div>
			<div class="info_parking">
				<div>주차</div>
				<span>
					<div>
						<div>
							<img src="./images/icon/ico-parking.png">
						</div>
						<div>
							<span>주차안내</span>
							<ul>
								<li>씨티빌딩 후면 주차타워 이용</li>
								<li>주차공간이 협소하오니 대중교통 이용바랍니다.</li>
							</ul>
						</div>
					</div>
					<div>
						<div>
							<img src="./images/icon/ico-confirm.png">
						</div>
						<div>
							<span>주차확인</span>
							<ul>
								<li>영화 관람전 매표소에서 주차 인증</li>
							</ul>
						</div>
					</div>
					<div>
						<div>
							<img src="./images/icon/ico-cash.png">
						</div>
						<div>
							<span>주차요금</span>
							<ul>
								<li>주차 요금은 입차시간을 기준으로 합니다.</li>
								<li>평일 관람 시 3시간 무료</li>
								<li>주말 및 공휴일 3시간 2,000원</li>
								<li>매표소에서 영화 티켓과 주차권 함께 제시 (티켓당 1대 적용)</li>
								<li>초과 요금 발생시 출차시 정산 (30분당 2,500원)</li>
								<li>출차시 무인정산기 이용</li>
							</ul>
						</div>
					</div>
				</span>
			</div>
		</div>

	</div>
	<c:import url="/include/footer.do" />
</body>
</html>