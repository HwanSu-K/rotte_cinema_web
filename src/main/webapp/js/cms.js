$(document).ready(function() {
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
				
				 //$("#localIndex").val(data.documents[0].address.region_1depth_name.trim()).attr("selected", "selected");
				//$("#localIndex").val(data.documents[0].address.region_1depth_name).prop("selected", true);
	    	},
		    error : function(){
		      
		    }
		  });
	});
});