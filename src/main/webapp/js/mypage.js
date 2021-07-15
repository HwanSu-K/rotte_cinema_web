$(document).ready(function() {
	$('#like_plus').on('click',function() {
		$('.like > .reserv_movie_list > div').each(function() {
			$(this).removeClass('h');
		})
	});
	
	$('#reserv_plus').on('click',function() {
		$('.reserv table tr').each(function() {
			$(this).removeClass('h');
		})
	});	
	
	$('#ticket_plus').on('click',function() {
		$('.ticket > .info > div').each(function() {
			$(this).removeClass('h');
		})
	});	
	
	$('td > div').on('click',function(e) {
		if($(e.currentTarget).hasClass('g') || $(e.currentTarget).hasClass('h')) {
			return false;
		}
		const form = {
			index: $(this).data('pay-index')
		}
		
		$.ajax({
			url: 'reservdeleteobject.do',
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
				if(data.result === 'fail')
				{
					alert('오류가 발생했습니다.');
					return false;
				}
				
				$(e.currentTarget).addClass('g');
				$(e.currentTarget).text('취소완료');
					
			},
			error: function(e) {
			}
		});
	})
});