<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=222a1aea3eb30dafa3b98b821fed8f7d"></script>
	<link rel="stylesheet" href="./style/location.css" /> 
</head>
<body>
	<c:import url="/include/header.do"/>
	<section class="location">      <!-- background-color: #f8f8fa; -->
        <div>
            <span><i class="fas fa-home"></i></span>
            <span>></span>
            <span>오시는길</span>
            <span></span>
            <span></span>
        </div>
    </section>
	<div class="content">
		<div class="map_wrap">
		    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div> 
		    <!-- 지도타입 컨트롤 div 입니다 -->
		    <div class="custom_typecontrol radius_border">
		        <span id="btnRoadmap" class="selected_btn" onclick="setMapType('roadmap')">지도</span>
		        <span id="btnSkyview" class="btn" onclick="setMapType('skyview')">스카이뷰</span>
		    </div>
		    <!-- 지도 확대, 축소 컨트롤 div 입니다 -->
		    <div class="custom_zoomcontrol radius_border"> 
		        <span onclick="zoomIn()"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png" alt="확대"></span>  
		        <span onclick="zoomOut()"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png" alt="축소"></span>
		    </div>
		</div>
		<div class="map_info">
			<div>교통정보</div>
			<div>
				<ul>
					<li><i class="far fa-map"></i>(701-835) 대구광역시 동구 화랑로 525(용계동)</li>
					<li><i class="fas fa-bus"></i>618, 719, 805, 808, 818, 836, 980, 동구1, 동구1-1, 동구2</li>
					<li><i class="fas fa-subway"></i>대구1호선 용계역 (3번 출구)</li>
				</ul>
			</div>
		</div>
    </div>
    <c:import url="/include/footer.do"/>
</body>
<script>
var mapContainer = document.getElementById('map'), // 지도의 중심좌표
    mapOption = { 
        center: new kakao.maps.LatLng(35.87555107556265, 128.68168847477938), // 지도의 중심좌표
        level: 2 // 지도의 확대 레벨
    }; 

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도에 마커를 표시합니다 
var marker = new kakao.maps.Marker({
    map: map, 
    position: new kakao.maps.LatLng(35.87555107556265, 128.68168847477938)
});

// 커스텀 오버레이에 표시할 컨텐츠 입니다
// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
// 별도의 이벤트 메소드를 제공하지 않습니다 
var content = '<div class="wrap">' + 
            '    <div class="info">' + 
            '        <div class="title">' + 
            '            ROTTE CINEMA' +  
            '        </div>' + 
            '        <div class="body">' + 
            '            <div class="img">' +
            '                <img src="./images/favicon/android-icon-192x192.png" width="73" height="70">' +
            '           </div>' + 
            '            <div class="desc">' + 
            '                <div class="ellipsis">대구광역시 동구 화랑로 525</div>' + 
            '                <div class="jibun ellipsis">(우) 701-835 (지번)용계동 1018-1</div>' + 
            '                <div><a href="./main.do" target="_blank" class="link">홈페이지</a></div>' + 
            '            </div>' + 
            '        </div>' + 
            '    </div>' +    
            '</div>';

// 마커 위에 커스텀오버레이를 표시합니다
// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
var overlay = new kakao.maps.CustomOverlay({
    content: content,
    map: map,
    position: marker.getPosition()       
});

//지도타입 컨트롤의 지도 또는 스카이뷰 버튼을 클릭하면 호출되어 지도타입을 바꾸는 함수입니다
function setMapType(maptype) { 
    var roadmapControl = document.getElementById('btnRoadmap');
    var skyviewControl = document.getElementById('btnSkyview'); 
    if (maptype === 'roadmap') {
        map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);    
        roadmapControl.className = 'selected_btn';
        skyviewControl.className = 'btn';
    } else {
        map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);    
        skyviewControl.className = 'selected_btn';
        roadmapControl.className = 'btn';
    }
}

// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
function zoomIn() {
    map.setLevel(map.getLevel() - 1);
}

// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
function zoomOut() {
    map.setLevel(map.getLevel() + 1);
}
</script>
</html>