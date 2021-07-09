$(document).ready(function() {

	var year = new Date().getFullYear();
	for (var i = 0; i < 50; i++) {
		var option = $(
			"<option value='" + (year - i) + "'>" + (year - i) + '</option>'
		);
		$('#year').append(option);
	}

	for (var i = 0; i < 12; i++) {
		var option = $("<option value='" + (i + 1) + "'>" + (i + 1) + '</option>');
		$('#month').append(option);
	}

	for (var i = 0; i < 31; i++) {
		var option = $("<option value='" + (i + 1) + "'>" + (i + 1) + '</option>');
		$('#day').append(option);
	}

	function fn_DayOfMonth(year, month) {
		//month 는 0 부터 시작해서..
		return 32 - new Date(year, month - 1, 32).getDate();
	}

	$('#year,#month').on('change', function () {
		if (
			$('#year option:selected').val() === '' ||
			$('#month option:selected').val() === ''
		) {
			return false;
		}

		var day = fn_DayOfMonth(
			$('#year option:selected').val(),
			$('#month option:selected').val()
		);

		// 년 월 선택시 해당 일수를 재 설정함.
		$('#day > option').remove();
		var option = $("<option value=''>일</option>");
		$('#day').append(option);

		for (var i = 0; i < day; i++) {
			var option = $(
				"<option value='" + (i + 1) + "'>" + (i + 1) + '</option>'
			);
			$('#day').append(option);
		}
	});
	
	// 자리수 맞추
	function numberPad(n) {
		width = 2;
		n = n + '';
		return n.length >= width
			? n
			: new Array(width - n.length + 1).join('0') + n;
	}
	
	$('#btnId').on('click', function() {
		let input = true;
		$('#reg > div > input,#reg > div > select').each(function () {
			if ($(this).val() == '') {
				$(this).focus();
				input = false;
				return false;
			}
		});
		
		if(input) {
			var form = {
				name: $('#name').val(),
				birth: $('#year').val() + numberPad($('#month').val()) + numberPad($('#day').val()),
				phonenum: $('#p_top').val() + $('#p_middle').val() + $('#p_bottom').val()
			};
			
			$.ajax({
				url: 'finderobject.do',
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
					$('#reg').each(function() {
						this.reset();
					});
	
					if (data.customer != null) {
						$('#formFinder').addClass('active');
						$('#formFinder > div > p:nth-child(1)').text(form.name);
						$('#formFinder > div > p:nth-child(2)').text(data.customer.email);
					}
					else {
						$('#formFinder').removeClass('active');
						alert('일치하는 정보가 없습니다.');
					}
	
				},
				error: function() {
					alert('통신이 원할하지 않습니다.');
				}
			});
		}
	});

	$('#btnPass').on('click', function() {
		let input = true;
		$('#reg > div > input,#reg > div > select').each(function () {
			if ($(this).val() == '') {
				$(this).focus();
				input = false;
				return false;
			}
		});
		
		if(input) {
			var form = {
				email: $('#email').val() + '@' + $('#emailAddr').val(),
				name: $('#name').val(),
				birth: $('#year').val() + numberPad($('#month').val()) + numberPad($('#day').val()),
				phonenum: $('#p_top').val() + $('#p_middle').val() + $('#p_bottom').val()
			};
			$.ajax({
				url: 'finderobject.do',
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
					$('#reg').each(function() {
						this.reset();
					});
	
					if (data.customer != null) {
						$('#formFinder').addClass('active');
						$('#formFinder > div > p:nth-child(1)').text(form.email);
					}
					else {
						$('#formFinder').removeClass('active');
						alert('일치하는 정보가 없습니다.');
					}
				},
				error: function() {
					alert('통신이 원할하지 않습니다.');
				}
			});	
		}
	});
});