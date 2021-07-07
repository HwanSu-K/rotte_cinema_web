$(document).ready(function() {
	
	// 전체영화,큐레이션 지역 클릭.
	$('.reserv_content_title > span').on('click', function(e) {
		var count = 0;
		// 전체영화일때만 모든 항목 표시.
		if ($(e.currentTarget).data('tab-type') === 'default') {
			$('.reserv_content_list > div').each(function() {
				$(this).css('display', 'block');
				if (count === 0) {
					$(this).click();
					count++;
				}
			});
		} else {
			$('.reserv_content_list > div').each(function() {
				// 이외의 경우는 선택된 항목과 동일한 내용의 항목만 표시.
				if ($(this).data('movie-type') === $(e.currentTarget).data('tab-type')) {
					$(this).css('display', 'block');
					if (count === 0) {
						$(this).click();
						count++;
					}
				}
				else {
					$(this).css('display', 'none');
				}
			});
		}

		// 항목 선택을위해 모든 항목 선택 초기화.
		$('.reserv_content_title > span').each(function() {
			$(this).removeClass('active');
		});

		//선택된 항목 선택표시 
		$(e.currentTarget).addClass('active');
	});
	
	// 지역 세부목록 클릭시 ajax를 이용하여 데이터 수신후 변경.
	$('.reserv_theater .reserv_content_list > div').on('click', function(e) {
		$('.reserv_content_list > div').each(function() {
			$(this).removeClass('active');
		});

		$(e.currentTarget).addClass('active');

		var form = {
			index: $(e.currentTarget).data('cinema-index')
		}

		$.ajax({
			url: 'cinemaobject.do',
			type: 'POST',
			data: form,
			dataType: 'json',
			success: function(data) {
				$('.info_title').text(data.cinema.title);
				const APIKEY = 'afaccf592a85bcd0d22ac83f291a8c25';
				const form = {
				  query: data.cinema.addr
				}
				
				$.ajax({
				    url:'https://dapi.kakao.com/v2/local/search/address.json',
				    data:form,
				    type:'POST',
				    headers: {'Authorization' : `KakaoAK ${APIKEY}`},
				    success:function(data){
						$('.info_addr > p:nth-child(2)').text(data.documents[0].road_address.address_name);
						mapCreate(data);
					
			    	},
				    error : function(e){
				        console.error(e);
				    }
				  });
				
			},
			error: function() {
				console.log('error');
			}
		});
	});
	
	$('.reserv_content_title > span:nth-child(1)').click();
	
	var map;
	
	function mapCreate(data) {
		
		$('#map > div').remove();
					
		var mapContainer = document.getElementById('map'), // 지도의 중심좌표
		    mapOption = { 
		        center: new kakao.maps.LatLng(data.documents[0].y, data.documents[0].x), // 지도의 중심좌표
		        level: 2 // 지도의 확대 레벨
		    }; 
		
		map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		// 지도에 마커를 표시합니다 
		var marker = new kakao.maps.Marker({
		    map: map, 
		    position: new kakao.maps.LatLng(data.documents[0].y, data.documents[0].x)
		});
		
		// 커스텀 오버레이에 표시할 컨텐츠 입니다
		// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		// 별도의 이벤트 메소드를 제공하지 않습니다 
		var content = 
					`<div class="wrap">` + 
		            `    <div class="info">` + 
		            `        <div class="title">` + 
		            `            ROTTE CINEMA <P>(${$('.info_title').text()})</P>` +  
		            `        </div>` + 
		            `        <div class="body">` + 
		            `            <div class="img">` +
		            `                <img src="./images/favicon/android-icon-192x192.png" width="73" height="70">` +
		            `           </div>` + 
		            `            <div class="desc">` + 
		            `                <div class="ellipsis">${data.documents[0].road_address.address_name }</div>` + 
		            `                <div class="jibun ellipsis">(우) ${data.documents[0].road_address.zone_no} (지번)${data.documents[0].address.address_name}</div>` + 
		            `                <div><a href="./main.do" target="_blank" class="link">홈페이지</a></div>` + 
		            `            </div>` + 
		            `        </div>` + 
		            `    </div>` +    
		            `</div>`;
		
		// 마커 위에 커스텀오버레이를 표시합니다
		// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		var overlay = new kakao.maps.CustomOverlay({
		    content: content,
		    map: map,
		    position: marker.getPosition()       
		});
	}
	
	$('#zooIn').on('click',function() {
		map.setLevel(map.getLevel() - 1);
	});
	
	$('#zooOut').on('click',function() {
		map.setLevel(map.getLevel() + 1);
	});
});