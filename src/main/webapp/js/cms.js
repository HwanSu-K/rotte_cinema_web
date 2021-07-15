$(document).ready(function() {
	// 주소 변경시 카카오 API에서 좌표값 다시 요청. 
	$('#addr').on('change',function(){
		const APIKEY = 'afaccf592a85bcd0d22ac83f291a8c25';
		const form = {
		  query: $('#addr').val()
		}
		
		$.ajax({
		    url:'https://dapi.kakao.com/v2/local/search/address.json',
		    data:form,
		    type:'POST',
		    headers: {'Authorization' : `KakaoAK ${APIKEY}`},
		    success:function(data){
				if(typeof data.documents[0] === 'undefined') {
					$('#lat').val('');
					$('#lng').val('');
					$('#addr').val('');
					$('#addr').focus();
					alert('정확한 주소를 입력해 주세요.');
					return false;
				}
				$('#lat').val(data.documents[0].y);
				$('#lng').val(data.documents[0].x);
				for(var i=0; i < $("#localIndex option").size(); i++) {
					$(`#localIndex > option:eq(${i})`).removeAttr('selected');					
					if($(`#localIndex > option:eq(${i})`).text().trim() === data.documents[0].address.region_1depth_name.trim()) {
						$(`#localIndex > option:eq(${i})`).prop("selected", true);
					}
				}
	    	},
		    error : function(){
		      
		    }
		  });
	});

	// 영화 등록시 오늘날짜 자동으로 입력되어 있도록.
	$('#date').val(new Date().toISOString().substring(0, 10));
	
	// 날짜 변경시 해당 일자에 상영하는 영화 목록이 호출되도록. 
	$('#date').on('change',function(){
		$('#movie > option').remove();
		
		var form = {
			date: $(this).val()
		}

		$.ajax({
			url:  '../../moviestitle.do',
			type: 'POST',
			data: form,
			dataType: 'json',
			success: function(data) {
				if(data.movies.length > 0) {
					$('#search_List > div').remove();

					$(data.movies).each(function() {
						var option = $(
							`<option value='${this.index }'>${this.title }</option>`
						);
						$('#movie').append(option);
					});	
				}
			},
			error: function() {
				
			}
		});
	});
	
	// 영화 등록시 비동기 요청으로 영화등록. 결과값 리턴.
	$('#submitShowing').on('click',function() {
		var form = $('#formShowing').serialize();
		form += `&indexTheater=${$('#index').val()}`
		
		$.ajax({
			url:  '../../showingobject.do',
			type: 'POST',
			data: form,
			dataType: 'json',
			success: function(data) {
				
				alert(data.message);
			},
			error: function() {
				
			}
		});
	});
});