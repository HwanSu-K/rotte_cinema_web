$(document).ready(function () {
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

	// 비밀번호 동일한지 체크.
	$('#password,#passwordcheck').on('blur', function () {
		var pw = $('#password,#passwordcheck').val();
		var num = pw.search(/[0-9]/g);
		var eng = pw.search(/[a-z]/gi);
		var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

		if (pw.length < 8) {
			$('#passAlert').removeClass('disabled');
			$('#passAlert').removeClass('active');
			$('#passAlert').attr(
				'data-content',
				'비밀번호는 8자리 이상으로 입력해주세요.'
			);
			return false;
		} else if (pw.search(/\s/) != -1) {
			$('#passAlert').removeClass('disabled');
			$('#passAlert').removeClass('active');
			$('#passAlert').attr(
				'data-content',
				'비밀번호는 공백 없이 입력해주세요.'
			);
			return false;
		} else if (num < 0 || eng < 0 || spe < 0) {
			$('#passAlert').removeClass('disabled');
			$('#passAlert').removeClass('active');
			$('#passAlert').attr(
				'data-content',
				'영문,숫자,특수문자를 혼합하여 입력해주세요.'
			);
			return false;
		}

		if ($('#password').val() != $('#passwordcheck').val()) {
			$('#passAlert').removeClass('disabled');
			$('#passAlert').removeClass('active');
			$('#passAlert').attr('data-content', '비밀번호가 일치하지 않습니다.');
		} else {
			if ($('#password').val() === '' || $('#passwordcheck').val() === '') {
				return false;
			}

			$('#passAlert').removeClass('disabled');
			$('#passAlert').addClass('active');
			$('#passAlert').attr('data-content', '사용 가능한 비밀번호 입니다.');
		}
	});

	// 이메일 형식 검사.
	function validateEmail(sEmail) {
		var filter =
			/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		if (filter.test(sEmail)) {
			return true;
		} else {
			return false;
		}
	}

	// 자리수 맞추
	function numberPad(n) {
		width = 2;
		n = n + '';
		return n.length >= width
			? n
			: new Array(width - n.length + 1).join('0') + n;
	}
	$('#email,#emailAddr,#emailSelect').on('blur', function () {
		if ($('#email').val() === '' || $('#emailAddr').val() === '') {
			// 내용을 입력해야 처리되도록.
			return false;
		}

		var form = {
			email: $('#email').val() + '@' + $('#emailAddr').val(),
		};

		if (!validateEmail(form.email)) {
			$('#emailAlert').removeClass('disabled');
			$('#emailAlert').removeClass('active');
			$('#emailAlert').attr('data-content', '이메일 형식이 잘못되었습니다.');
			return false;
		}

		$.ajax({
			url: 'emailobject.do',
			type: 'POST',
			data: form,
			dataType: 'json',
			success: function (data) {
				if (data != true) {
					$('#emailAlert').removeClass('disabled');
					$('#emailAlert').removeClass('active');
					$('#emailAlert').attr(
						'data-content',
						'사용할 수 없는 이메일 입니다.'
					);
				} else {
					$('#emailAlert').removeClass('disabled');
					$('#emailAlert').addClass('active');
					$('#emailAlert').attr('data-content', '사용 가능한 이메일 입니다.');
				}
			},
			error: function () {
				console.log('error');
			},
		});
	});

	$('#emailSelect').on('change', function (e) {
		var idx = $('#emailSelect option').index($('#emailSelect option:selected'));

		if (idx === 0) {
			$('#emailAddr').attr('disabled', false);
			$('#emailAddr').val('');
		} else {
			$('#emailAddr').attr('disabled', true);
			$('#emailAddr').val(this.value);
		}
	});
	$('#btn_reg').on('click', function (e) {
		let input = true;
		$('#reg > div > input,#reg > div > select').each(function () {
			if ($(this).val() == '') {
				if ($(this) === $('#zipcode')) {
					sample2_execDaumPostcode();
				}

				$(this).focus();
				input = false;
				return false;
			}
		});

		if (input) {
			// 비밀번호 불일치시 진행 안함.
			if (!$('#passAlert').hasClass('active')) {
				return false;
			}

			// 사용가능한 이메일인지 체크.
			if (!$('#emailAlert').hasClass('active')) {
				return false;
			}

			var form = {
				email: $('#email').val() + '@' + $('#emailAddr').val(),
				password: $('#password').val(),
				name: $('#name').val(),
				birth:
					$('#year').val() +
					numberPad($('#month').val()) +
					numberPad($('#day').val()),
				phonenum:
					$('#p_top').val() + $('#p_middle').val() + $('#p_bottom').val(),
				zipcode: $('#zipcode').val(),
				address: $('#address').val(),
				detailaddress: $('#detailAddress').val(),
			};

			$.ajax({
				url: 'registrationobject.do',
				type: 'POST',
				data: form,
				dataType: 'json',
				success: function (data) {
					if (data === -1) {
						alert('오류가 발생했습니다.');
						return false;
					}
					$('#reg').each(function () {
						this.reset();
					});

					$('.reg_info').remove();
					$('.reg_btn').remove();
					$('#formReg').remove();
					$('.reg_tab > div:nth-child(1)').removeClass('active');

					$('.reg_alert').addClass('active');
					$('#formcomp').addClass('active');
					$('#formcomp > div:nth-child(1)').text(form.name);
					$('.reg_tab > div:nth-child(2)').addClass('active');
				},
				error: function () {
					console.log('error');
				},
			});
		}
	});

	$('#btn_cancel').on('click', function (e) {
		history.back();
	});
});
