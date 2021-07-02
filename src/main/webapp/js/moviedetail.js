$(document).ready(function() {
	const urlParams = new URLSearchParams(location.search);

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
				if (data === -1) {
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

	reviewLoad();

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
});