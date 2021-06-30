$(document).ready(function() {

	$('.reserv_content_title > span').on('click', function(e) {
		var count = 0;
		if ($(e.currentTarget).data('tab-type') === 'default') {
			$('.reserv_content_list > div').each(function() {
				$(this).css('display', 'block');
				if(count === 0) {
					$(this).click();
					count ++;
				}
			});
		} else {
			$('.reserv_content_list > div').each(function() {
				if ($(this).data('movie-type') === $(e.currentTarget).data('tab-type')) {
					$(this).css('display', 'block');
					if(count === 0) {
						$(this).click();
						count ++;
					}
				}
				else {
					$(this).css('display', 'none');
				}
			});
		}
		
		$('.reserv_content_title > span').each(function() {
			$(this).removeClass('active');
		});

		$(e.currentTarget).addClass('active');
	});

	// 버튼 이벤트 할당.
	$('.reserv_movie .reserv_content_list > div').on('click', function(e) {
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
				
				theaters(data.movie.index);

			},
			error: function() {
				//alert('통신이 원할하지 않습니다.');
			}
		});
	});
	
	// 버튼 이벤트 할당.
	$('.reserv_theater .reserv_content_list > div').on('click', function(e) {
		var form = {
			index: $(e.currentTarget).data('cinema-index')
		}

		$.ajax({
			url: 'cinemaobject.do',
			type: 'POST',
			data: form,
			dataType: 'json',
			success: function(data) {
				$('#movieTitle').text(data.cinema.title);

				$('.reserv_content_list > div').each(function() {
					$(this).removeClass('active');
				});

				$(e.currentTarget).addClass('active');
			},
			error: function() {
				//alert('통신이 원할하지 않습니다.');
			}
		});
	});


	$('.reserv_info_local > div').on('click', function(e) {
		
		$('.reserv_list > div').each(function() {
			if ($(this).data('local-class') === $(e.currentTarget).data('tab-type')) {
				$(this).css('display', 'block');
			}
			else {
				$(this).css('display', 'none');
			}
		});
			
		$('.reserv_info_local > div').each(function() {
				$(this).removeClass('active');
		});
		
		$(e.currentTarget).addClass('active');
	});
	
	function theaters(index) {
		
		$('.reserv_list > div').remove();
		var form = {
				movie: index
			}
			
		$.ajax({
				url: 'theatersobject.do',
				type: 'POST',
				data: form,
				dataType: 'json',
				success: function(data) {
					var cinema = null;
					var theater = null;
					
					$(data.theaters).each(function(){
						if(cinema != this.indexCinema) {
							$('.reserv_list').append($(
							'<div class="reserv_info_list" data-local-class="' + this.localClass + '">' +
							 	'<div>' +
							 		'<div class="reserv_info_list_title">' + this.nameCinema + '</div>' +
								'</div>' +
								'<div id="cinema-' + this.indexCinema + '" class="reserv_info_list_theater">' +
								'</div>' +
							'</div>'
							));
							cinema = this.indexCinema;
						}
						
						if(theater != this.index) {
							$('#cinema-' + this.indexCinema + '').append($(
							'<div>' + 
								'<div>' +
									'<div>' +
										'<div>' + this.name + '</div>' +
										'<div>총 ' + (this.seatX * this.seatY) + '석</div>' +
									'</div>' +
									'<div>' +
										'<div>2D(자막)</div>' +
									'</div>' +
								'</div>' +
								'<div id="theater-' + this.index + '">' +
								'</div>' +
							'</div>'
							));
							theater = this.index;
						}
						
						$('#theater-' + this.index + '').append($(
							'<div>' +
								'<div>' + this.startTime + '</div>' +
								'<span>' + (this.seatX * this.seatY) + '석</span>' +
							'</div>'
						));	
							
					})
					
					$('.reserv_info_local > div:nth-child(1)').click();
				},
				error: function() {
					alert('통신이 원할하지 않습니다.');
				}
			});
	}
	// 전체영화 선택
	$('.reserv_movie .reserv_content_title > span:nth-child(1)').click();

	$('.reserv_type_movie').click(function() {
		$('.reserv_type_movie').addClass('active');
		$('.reserv_movie').addClass('active');
		$('.reserv_type_theater').removeClass('active');
		$('.reserv_theater').removeClass('active');
		
		$('.reserv_info_movie').addClass('active');
		$('.reserv_info_local').addClass('active');
		$('.reserv_list').addClass('active');
		// 첫번째 탭 선택
		$('.reserv_movie .reserv_content_title > span:nth-child(1)').click();
	})

	$('.reserv_type_theater').click(function() {
		$('.reserv_type_movie').removeClass('active');
		$('.reserv_movie').removeClass('active');
		$('.reserv_type_theater').addClass('active');
		$('.reserv_theater').addClass('active');
		
		$('.reserv_info_movie').removeClass('active');
		$('.reserv_info_local').removeClass('active');
		$('.reserv_list').removeClass('active');

		// 첫번째 탭 선택
		$('.reserv_theater .reserv_content_title > span:nth-child(1)').click();
	})
});