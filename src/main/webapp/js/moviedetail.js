$(document).ready(function() {
	const urlParams = new URLSearchParams(location.search);

	var memberCountConTxt= $(".memberCountCon").text();
  
  $({ val : 0 }).animate({ val : memberCountConTxt }, {
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

	function numberWithCommas(x) {
			return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
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
			success: function(data) {

				$('.comment_title > div:nth-child(2) > div > i').each(function(){
					$(this).removeClass('active');
				});
				
				$(e.currentTarget).children('i').addClass('active');
					
				$('#reviewList > div').remove();
				
				$('.comment_title > div:nth-child(1)').text('총 ' + data.count + '건');
				$('.tab_title > div:nth-child(2) > span:nth-child(2)').text(' (' + data.count + ')');
				
				$(data.reviews).each(function() {
					$('#reviewList').append($(
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
                        '</div>'
					));
				});
			},
			error: function() {
				console.log('error');
			}
		});
	});
	
	function reviewLoad() {
		var form = {
			index: urlParams.get('index')
		}

		$.ajax({
			url: 'reviewsobject.do',
			type: 'POST',
			data: form,
			dataType: 'json',
			success: function(data) {

				$('#reviewList > div').remove();
				
				$('.comment_title > div:nth-child(1)').text('총 ' + data.count + '건');
				$('.tab_title > div:nth-child(2) > span:nth-child(2)').text(' (' + data.count + ')');
				
				$(data.reviews).each(function() {
					$('#reviewList').append($(
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
                        '</div>'
					));
				});
			},
			error: function() {
				console.log('error');
			}
		});
	}

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
				console.log('error');
			}
		});
	});

	$('.movieLike').on('click', function(e) {
		
		var form = {
			indexMovie: $(e.currentTarget).data('movie-index'),
		}
		console.log(form);
		$.ajax({
			url: 'likeobject.do',
			type: 'POST',
			data: form,
			dataType: 'json',
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

	var stars = $('.star > div');
	$(stars).on('hover',function(e){
		if ($('#reviewButton').hasClass('disabled')) {
			return false;
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

	$('#txtComment').on('propertychange change keyup paste input',function(e){
		var byte = $('#txtComment').val().length;
		$('.byte').text(byte);
	});
	
	reviewLoad();
});