$(document).ready(function() {	
	
	// 영화 API를 받아오려 했으나, 제공되는 API는 박스오피스 만 제공하기때문에 랭킹에 따른 임의의 관객수 호출. 
	const urlParams = new URLSearchParams(location.search);
	
	let memberCountConTxt = 10_000_000 - (Math.random() * 1_000_000 * $('#rank').text());
	
	if(memberCountConTxt < 0) {
		memberCountConTxt = Math.random() * 100_000
	}
  
	$({ val: 0 }).animate({ val: memberCountConTxt }, {
		duration: 2000,
		step: function() {
			var num = numberWithCommas(Math.floor(this.val));
			$(".memberCountCon").text(num);
		},
		complete: function() {
			var num = numberWithCommas(Math.floor(this.val));
			$(".memberCountCon").text(num);
		}
	});

	// 3자리마다 콤마를 찍어주는 정규식.
	function numberWithCommas(x) {
			return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
	// 코멘트 정렬 변경시 비동기 재호출.
	$('.comment_title > div:nth-child(2) > div').on('click',function(e) {
		
		var form = {
			index: urlParams.get('index'),
			order: $(e.currentTarget).data('order-type')
		}

		$.ajax({
			url: 'reviewsobject.do',
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

				$('.comment_title > div:nth-child(2) > div > i').each(function(){
					$(this).removeClass('active');
				});
				
				$(e.currentTarget).children('i').addClass('active');
				
				reviewSet(data);
					
			},
			error: function() {
				
			}
		});
	});
	
	// 호출된 자료로 리뷰를 구성하는 부분.
	function reviewSet(data) {
		$('#reviewList > div').remove();
				
		$('.comment_title > div:nth-child(1)').text('총 ' + Object.keys(data.reviews).length + '건');
		$('.tab_title > div:nth-child(2) > span:nth-child(2)').text(' (' + Object.keys(data.reviews).length + ')');
		
		$(data.reviews).each(function() {
			$('#reviewList').append($(
				'<div>' +
					'<div>' +
                        '<div class="cmt_icon">' +
                            '<img src="./images/icon/sub4_comment_user.png">' +
                        '</div>' +
                        '<div class="cmt_info">' +
                            '<div>' +
                                '<div class="name_info">' + this.name + '</div>' +
                                '<span>|</span>' +
                                '<div><i class="fas fa-star"></i></div>' +
                                '<div>' + this.rating + '</div>' +
                            '</div>' +
                            '<div>' + this.text + '</div>' +
                            '<div>' + this.date + '</div>' +
                        '</div>' +
					'</div>' + 
					`<i class="fas fa-trash ${this.trash === true ? 'active':''}" data-index=${this.index}></i>` +
                '</div>'
			));
		});
	}
	
	$(document).on('click','.fa-trash',function() {
		var form = {
			index: $(this).data('index')
		}

		$.ajax({
			url: 'reviewdeleteobject.do',
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
				reviewLoad();
					
			},
			error: function() {
				
			}
		});
	});
	
	// 초기 로딩시 리뷰를 호출하는 부분.
	function reviewLoad() {
		var form = {
			index: urlParams.get('index')
		}

		$.ajax({
			url: 'reviewsobject.do',
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

				reviewSet(data);
				
			},
			error: function() {
				
			}
		});
	}


	// 리뷰 작성시 서버로 비동기 요청.
	$('#reviewButton').on('click', function() {
		if ($('#reviewButton').hasClass('disabled')) {
			return;
		}
		
		var form = {
			text: $('#txtComment').val(),
			rating: $('#score').text(),
			indexMovie: urlParams.get('index')
		}

		const formParams = new URLSearchParams(form);

		// key 목록
		const values = formParams.values();

		for (const value of values) {
			if (value == '') {
				return false;
			}
		}

		$.ajax({
			url: 'reviewobject.do',
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
				if (data === 'fail') {
					alert('오류가 발생했습니다.')
				}
				reviewLoad();
				$('#txtComment').val('');
				
				var count = 5;
				$(stars).each(function(index,elem){
					if(index < count){
						$(elem).addClass('active');
					} else{
						$(elem).removeClass('active');
					}
				});
				$('#score').text(count);
				
			},
			error: function() {
				
			}
		});
	});


	// 좋아요 비동기 요청.
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


	// 평점 마우스 오버 이벤트.
	var stars = $('.star > div');
	$(stars).on('mouseenter',function(e){
		if ($('#reviewButton').hasClass('disabled')) {
			return;
		}
		
		var count = $(e.currentTarget).data('value');
		$(stars).each(function(index,elem){
			if(index < count){
				$(elem).addClass('active');
			} else{
				$(elem).removeClass('active');
			}
		});
		$('#score').text(count);
	});

	// 댓글수 표시.
	$('#txtComment').on('propertychange change keyup paste input',function(e){
		var byte = $('#txtComment').val().length;
		$('.byte').text(byte);
	});
	
	reviewLoad();
});