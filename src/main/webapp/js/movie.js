$(document).ready(function() {

	// 좋아요 버튼 클릭시 서버에서 세션 확인후 리턴.
	// 좋아요 여부, 좋아요 갯수를 리턴받아 항목 변경.
	$('.movieLike').on('click', function(e) {
		var form = {
			indexMovie: $(e.currentTarget).data('movie-index'),
		}

		$.ajax({
			url: 'likeobject.do',
			type: 'POST',
			data: form,
			dataType: 'json',
			beforeSend: function() {
				$('#bg_mask').addClass('active');
		    },
		    complete: function() {
				$('#bg_mask').removeClass('active');
		    },
			success: function(data) {
				if(data === -1)
				{
					alert('로그인이 필요한 서비스 입니다.');
					return false;
				}
				
				if(data.return == 0){
					$(e.currentTarget).children('i').attr('class','far fa-heart');
				}
				else{
					$(e.currentTarget).children('i').attr('class','fas fa-heart active');
				}
				$(e.currentTarget).children('span').text(data.count)
			},
			error: function() {
				alert('통신이 원할하지 않습니다.');
			}
		});
	});
});