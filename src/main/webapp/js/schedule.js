$(document).ready(function() {

	$('.reserv_content_title > span').on('click', function(e) {
		var count = 0;
		if ($(e.target).data('tab-type') === 'default') {
			$('.reserv_content_list > div').each(function() {
				$(this).css('display', 'block');
				if(count === 0) {
					$(this).click();
					count ++;
				}
			});
		} else {
			
			$('.reserv_content_list > div').each(function() {
				if ($(this).data('movie-type') === 'default') {
					$(this).css('display', 'none');
				}
				else {
					$(this).css('display', 'block');
					if(count === 0) {
						$(this).click();
						count ++;
					}
				}
			});
		}
		
		$('.reserv_content_title > span').each(function() {
			$(this).removeClass('active');
		});

		$(e.target).addClass('active');
	});


	// 버튼 이벤트 할당.
	$('.reserv_content_list > div').on('click', function(e) {
		var form = {
			index: $(e.currentTarget).data('movie-index')
		}

		$.ajax({
			url: 'movieobject.do',
			type: 'POST',
			data: form,
			dataType: 'json',
			success: function(data) {
				$('#moviePoster').attr('src', './images/poster/' + data.movie.poster);
				$('#movieAge').attr('src', './images/icon/age_' + data.movie.limitAge + '.png');
				$('#movieTitle').text(data.movie.title);
				$('#movieSubTitle').text(data.movie.title);
				$('#movieRunningTime').text(data.movie.runningTime + '분');

				$('.reserv_content_list > div').each(function() {
					$(this).removeClass('active');
				});

				$(e.target).addClass('active');
			},
			error: function() {
				alert('통신이 원할하지 않습니다.');
			}
		});
	});

	// 전체영화 선택
	$('.reserv_content_title > span:nth-child(1)').click();

	// 첫번째 항목 클릭.
	$('.reserv_content_list > div:nth-child(1)').click();



	var button_movie = $('.reserv_type_movie');
	var button_theater = $('.reserv_type_theater');

	button_movie.click(function() {
		button_movie.addClass('active');
		button_theater.removeClass('active');
	})

	button_theater.click(function() {
		button_theater.addClass('active');
		button_movie.removeClass('active');
	})
});