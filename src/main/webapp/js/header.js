$(document).ready(function() {
	// 네비바 마우스 오버
	$('.nav_over').mouseenter(function() {
		if ($('.nav_map').hasClass('active') === false && $('.nav_search').hasClass('active') === false) {
			$('.nav_bg').addClass('active');
		}
	});

	$('.nav_over').mouseleave(function() {
		if ($('.nav_map').hasClass('active') === false && $('.nav_search').hasClass('active') === false) {
			$('.nav_bg').removeClass('active');
		}
	});

	// 헤더 메뉴 클릭시 기존의 토글되어있는 메뉴 확인후 처리.
	$('.header_menu').click(function(event) {
		$('.nav_map').toggleClass('active')
		$('.header_menu').toggleClass('active')
		$('nav').toggleClass('active')

		if (typeof event.originalEvent != 'undefined') {
			if ($('.header_search').hasClass('active')) {
				$('.header_search').click();
			}
		}

	});

	// 헤더 메뉴 클릭시 기존의 토글되어있는 메뉴 확인후 처리.
	$('.header_search').click(function(event) {
		$('.nav_search').toggleClass('active')
		$('.header_search').toggleClass('active')
		$('nav').toggleClass('active')

		if (typeof event.originalEvent != 'undefined') {
			if ($('.header_menu').hasClass('active')) {
				$('.header_menu').click();
			}
		}
		// 헤더가 선택되면 실시간 순위및 포스터 호출.		
		if ($('.header_search').hasClass('active')) {
			var form = {
				type: 'default'
			}

			$.ajax({
				url: urlPath + '/moviesobject.do',
				type: 'POST',
				data: form,
				dataType: 'json',
				success: function(data) {
	
					$('.movie_List > div').remove();
	
					$('#headerPoster').attr('onclick', 'location.href =\'' + urlPath + '/moviedetail.do?index=' + data.movies[0].index + '\'');
					$('#headerPoster > img').attr('src', '/images/poster/' + data.movies[0].poster);
					$(data.movies).each(function(index, item) {
						$('.movie_List').append($(
							'<div onClick="location.href =\'' + urlPath + '/moviedetail.do?index=' + item.index + '\'">' +
							'<span>' + (index + 1) + '</span><span>' + item.title + '</span></div>'));
						if (index == 4) { return false; }
					});
				},
				error: function() {
					
				}
			});	
		}
	});
	
	// 헤더 검색시 비동기요청으로 영화 표시.
	$('#searchText').on('propertychange change keyup paste input', function(e){
		var searchText = e.currentTarget.value.trim();
		if(searchText != '') {
			var form = {
			search: searchText
			}

			$.ajax({
				url:  urlPath + '/moviestitle.do',
				type: 'POST',
				data: form,
				dataType: 'json',
				success: function(data) {
					if(data.movies.length > 0) {
						$('#search_List > div').remove();
						$(data.movies).each(function() {
							$('#search_List').append($('<div onClick="location.href =\'' + urlPath + '/moviedetail.do?index=' + this.index + '\'">' + this.title + '</div>'));
						});	
					}
				},
				error: function() {
					
				}
			});	
		} else {
			$('#search_List > div').remove();
		}

	});

	$('.mobile > li').on('click',function(e) {
		console.log(e.currentTarget);
		$(e.currentTarget).toggleClass('active');
	});

	$('.fa-bars').on('click',function(event) {
		$('.mobile').toggleClass('active');
		$('nav').toggleClass('active')

		if (typeof event.originalEvent != 'undefined') {
			if ($('.nav_search').hasClass('active')) {
				$('.nav_search').toggleClass('active')
				$('nav').toggleClass('active')
			}
		}
	})

	$('.fa-search').on('click',function(event) {
		$('.nav_search').toggleClass('active')
		$('nav').toggleClass('active')
		
		if (typeof event.originalEvent != 'undefined') {
			if ($('.mobile').hasClass('active')) {
				$('.mobile').toggleClass('active');
				$('nav').toggleClass('active')
			}
		}
		
		// 헤더가 선택되면 실시간 순위및 포스터 호출.		
		if ($('.nav_search').hasClass('active')) {
			var form = {
				type: 'default'
			}

			$.ajax({
				url: urlPath + '/moviesobject.do',
				type: 'POST',
				data: form,
				dataType: 'json',
				success: function(data) {
	
					$('.movie_List > div').remove();
	
					$('#headerPoster').attr('onclick', 'location.href =\'' + urlPath + '/moviedetail.do?index=' + data.movies[0].index + '\'');
					$('#headerPoster > img').attr('src', '/images/poster/' + data.movies[0].poster);
					$(data.movies).each(function(index, item) {
						$('.movie_List').append($(
							'<div onClick="location.href =\'' + urlPath + '/moviedetail.do?index=' + item.index + '\'">' +
							'<span>' + (index + 1) + '</span><span>' + item.title + '</span></div>'));
						if (index == 4) { return false; }
					});
				},
				error: function() {
					
				}
			});	
		}
	})
});