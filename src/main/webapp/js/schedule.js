$(document).ready(function() {
	// GPS사용부분.
	let arounds = null;
	if (navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(
	        function(location){
			sessionStorage.setItem('lat', location.coords.latitude);
			sessionStorage.setItem('lng', location.coords.longitude);
			around();
			
	        },
	        function(error){
	       }
	    );
	}

	// GPS 사용중이라면 탭항목 추가.
	function around() {
		$('.reserv_theater .reserv_content_title').prepend($(
		`<span class="locals" data-tab-type="around">내주변</span>`
		));
		
		$('.reserv_info_local').prepend($(
			`<div data-tab-type="around">내주변</div>`
		));
		
		
		var form = {
			lat: sessionStorage.getItem('lat'),
			lng: sessionStorage.getItem('lng')
			
		}

		$.ajax({
			url: 'aroundobject.do',
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
				arounds = data.arounds;
				// 주변 극장 호출후 해당극장의 데이터에 항목 추가.
				$(arounds).each(function() {
					const cinema_index = this.indexCinema;
					$('.reserv_theater .reserv_content_list > div').each(function() {
						if($(this).data('cinema-index') === cinema_index) {
							$(this).data('cinema-around','true');
						}
					});
				});
			},
			error: function() {

			}
		});
	}
	

	const search = {
		movie: null,
		cinema: null,
		date: null
	}

	// 전체영화,큐레이션 지역 클릭.
	$(document).on('click','.reserv_content_title > span', function(e) {
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
				else if(typeof $(this).data('cinema-around') !== 'undefined' && $(e.currentTarget).data('tab-type') === 'around')
				{
					$(this).css('display', 'block');
					if (count === 0) {
						$(this).click();
						count++;
					}
				} else {
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

	// 예약 세부 항목 선택시 ajax를 이용하여 데이터 수신후 변경.
	$('.reserv_movie .reserv_content_list > div').on('click', function(e) {
		$('.reserv_content_list > div').each(function() {
			$(this).removeClass('active');
		});

		$(e.target).addClass('active');

		var form = {
			index: $(e.currentTarget).data('movie-index')
		}

		$.ajax({
			url: 'movieobject.do',
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
				$('#moviePoster').attr('src', '/images/poster/' + data.movie.poster);
				$('#movieAge').attr('src', './images/icon/age_' + data.movie.limitAge + '.png');
				$('#movieTitle').text(data.movie.title);
				$('#movieSubTitle').text(data.movie.title);
				$('#movieRunningTime').text(data.movie.runningTime + '분');

				search.cinema = null;
				search.movie = data.movie.index;
				theaters();

			},
			error: function() {

			}
		});
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
			beforeSend: function() {
				$('#bg_mask').addClass('active');
		    },
		    complete: function() {
				$('#bg_mask').removeClass('active');
		    },
			success: function(data) {

				$('#movieTitle').text(data.cinema.title);

				search.cinema = data.cinema.index;
				search.movie = null;
				movies();
			},
			error: function() {
				
			}
		});
	});

	//영화별로 표기시에는 지역선택히 해당 지역의 영화만 표시되도록.
	$(document).on('click', '.reserv_info_local > div', function(e) {

		$('.reserv_list > div').each(function() {
			if ($(this).data('local-class') === $(e.currentTarget).data('tab-type')) {
				$(this).css('display', 'block');
			}
			else if (typeof $(this).data('cinema-around') !== 'undefined' && $(e.currentTarget).data('tab-type') === 'around') {
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

	//선택된 영화의 영화관 목록을 호출.
	function theaters() {
		if(search.movie != null) {
			$.ajax({
				url: 'theatersobject.do',
				type: 'POST',
				data: search,
				dataType: 'json',
				beforeSend: function() {
					$('#bg_mask').addClass('active');
			    },
			    complete: function() {
					$('#bg_mask').removeClass('active');
			    },
				success: function(data) {
					var cinema = null;
					var theater = null;
	
					$('.reserv_list > div').remove();
	
					$(data.theaters).each(function() {
	
						if (cinema != this.indexCinema) {
							$('.reserv_list').append($(
								'<div class="reserv_info_list" data-cinema-index=' + this.indexCinema + ' data-local-class="' + this.localClass + '">' +
									'<div>' +
										'<div class="reserv_info_list_title">' + this.nameCinema + '</div>' +
										'</div>' +
										'<div id="cinema-' + this.indexCinema + '" class="reserv_info_list_theater">' +
									'</div>' +
								'</div>'
							));
							cinema = this.indexCinema;
						}
	
						if (theater != this.index) {
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
							`<div class="reservation ${dateConvert(search.date + this.startTime) < new Date() ? 'g' : ''}" data-index="${this.indexShowing}">` +
								'<div>' + this.startTime + '</div>' +
								'<span>' + (this.seatX * this.seatY - this.seatCount) + '석</span>' +
							'</div>'
						));
	
					})
	
					$('#dates > div').each(function() {
						$(this).addClass('g');
					});
						
					$(data.dates).each(function() {
						const date = this.date;
						$('#dates > div').each(function() {
							if($(this).data('date-value') === date) {
								$(this).removeClass('g');
								return false;
							}
						});	
					})	
					
					$('#dates > div').each(function() {
						if($(this).hasClass('active') && $(this).hasClass('g')) {
							$(this).removeClass('active');
							return false;
						}
					})			
					
					$(arounds).each(function() {
						const cinema_index = this.indexCinema;
						$('.reserv_list > div').each(function() {
							if($(this).data('cinema-index') === cinema_index) {
								$(this).data('cinema-around','true');
							}
						});
					});
					
					$('.reserv_info_local > div:nth-child(1)').click();
				},
				error: function() {
					
				}
			});	
		}
	}

	// 선택된 극장의 영화 표시.
	function movies() {
		if(search.cinema != null) {
			$.ajax({
				url: 'theatersobject.do',
				type: 'POST',
				data: search,
				dataType: 'json',
				beforeSend: function() {
					$('#bg_mask').addClass('active');
			    },
			    complete: function() {
					$('#bg_mask').removeClass('active');
			    },
				success: function(data) {
					var movie = null;
					var theater = null;
	
					$('.reserv_list > div').remove();
	
					$(data.theaters).each(function() {
	
						if (movie != this.indexMovie) {
							$('.reserv_list').append($(
								'<div class="reserv_info_list">' +
									'<div>' +
										'<div class="movie_info_list_title">' +
											'<div>' +
												'<img id="movieAge" src="./images/icon/age_' + this.limitAge + '.png">' +
												'<span id="movieSubTitle">' + this.nameMovie + '</span>' +
											'</div>' +
											'<div>' +
												'<div>상영중</div>' +
												'<div>/</div>' +
												'<div>' + this.runningTime + '분</div>' +
											'</div>' +
										'</div>' +
									'</div>' +
									'<div id="movie-' + this.indexMovie + '" class="reserv_info_list_theater">' +
									'</div>' +
								'</div>'
							));
							movie = this.indexMovie;
						}
	
						if (theater != this.index) {
							$('#movie-' + this.indexMovie + '').append($(
								'<div class="disabled">' +
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
							`<div class="reservation ${dateConvert(search.date + this.startTime) < new Date() ? 'g' : ''}" data-index="${this.indexShowing}">` +
								'<div>' + this.startTime + '</div>' +
								'<span>' + (this.seatX * this.seatY) + '석</span>' +
							'</div>'
						));
					})
					
					$('#dates > div').each(function() {
						$(this).addClass('g');
					});
						
					$(data.dates).each(function() {
						const date = this.date;
						$('#dates > div').each(function() {
							if($(this).data('date-value') === date) {
								$(this).removeClass('g');
								return false;
							}
						});	
					})	
					
					$('#dates > div').each(function() {
						if($(this).hasClass('active') && $(this).hasClass('g')) {
							$(this).removeClass('active');
							return false;
						}
					})		
						
				},
				error: function() {
					
				}
			});	
		}
	}

	// 영화별 선택.
	$('.reserv_type_movie').click(function() {
		$('.reserv_type_movie').addClass('active');
		$('.reserv_movie').addClass('active');
		$('.reserv_type_theater').removeClass('active');
		$('.reserv_theater').removeClass('active');

		$('.reserv_info_movie').addClass('active');
		$('.reserv_info_local').addClass('active');

		// 첫번째 탭 선택
		$('.reserv_movie .reserv_content_title > span:nth-child(1)').click();
	})

	// 극장별 선택.
	$('.reserv_type_theater').click(function() {
		$('.reserv_type_movie').removeClass('active');
		$('.reserv_movie').removeClass('active');
		$('.reserv_type_theater').addClass('active');
		$('.reserv_theater').addClass('active');

		$('.reserv_info_movie').removeClass('active');
		$('.reserv_info_local').removeClass('active');

		// 첫번째 탭 선택
		$('.reserv_theater .reserv_content_title > span:nth-child(1)').click();
	})
	
	// 상영 영화 선택시 해당 예약 페이지 이동.
	$(document).on('click','.reservation',function() {	
		if(!$(this).hasClass('g')) {
			location.href=`reservation.do?index=${$(this).data('index')}`
		}
	});
	
	// 날짜 선택시 영화 재호출.
	$('#dates > div').on('click', function(e) {
		
		if(!$(e.currentTarget).hasClass('g') || typeof e.originalEvent === 'undefined') {
			$('#dates > div').each(function() {
				$(this).removeClass('active');
			})
			search.date = $(e.currentTarget).data('date-value');
			if ($('.reserv_type_movie').hasClass('active')) {
				theaters();
			}
			else {
				movies();
			}
			$(e.currentTarget).addClass('active');	
		}
	})
	
	// 날짜 변경.
	function dateConvert(date) {
		var dateString = date;
		var reggie = /(\d{4})-(\d{2})-(\d{2})(\d{2}):(\d{2})/;
		var dateArray = reggie.exec(dateString);
		var dateObject = new Date(
		    (+dateArray[1]),
		    (+dateArray[2])-1, // Careful, month starts at 0!
		    (+dateArray[3]),
		    (+dateArray[4]),
		    (+dateArray[5])
		);
		return dateObject;
	}

	// 페이지 로딩시 전체영화 버튼을 클릭함.
	$('.reserv_movie .reserv_content_title > span:nth-child(1)').click();

	// 페이지 로딩시 첫번쨰 날짜 클릭
	$('#dates > div:nth-child(1)').click();
	
	// 날짜 컨트롤 이벤트
	$('#next').on('click', function() {
		//$('.dates').scrollLeft($('.dates').scrollLeft() - 70);
		$('#dates').stop().animate({ scrollLeft: '-=70' })
	})

	$('#prev').on('click', function() {
		//$('.dates').scrollLeft($('.dates').scrollLeft() + 70);
		$('#dates').stop().animate({ scrollLeft: '+=70' })
	})

	$('#today').on('click', function() {
		//$('.dates').scrollLeft(0);
		$('#dates').stop().animate({ scrollLeft: '0' })
	})
});