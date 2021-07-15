$(document).ready(function () {
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

	// 패스워드 변경.
	$('#btn_reg').on('click', function (e) {
		let input = true;
		$('#reg > div > input,#reg > div > select').each(function () {
			if ($(this).val() == '') {

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
			const urlParams = new URLSearchParams(location.search);
			
			var form = {
				password: $('#password').val(),
				key: urlParams.get('key')
			};

			$.ajax({
				url: 'passwordobject.do',
				type: 'POST',
				data: form,
				dataType: 'json',
				beforeSend: function() {
					$('#bg_mask').addClass('active');
			    },
			    complete: function() {
					$('#bg_mask').removeClass('active');
			    },
				success: function (data) {
					if (data === -1) {
						alert('오류가 발생했습니다.');
						return false;
					}
					
					location.href='login.do';

				},
				error: function () {
					
				},
			});
		}
	});

	$('#btn_cancel').on('click', function (e) {
		history.back();
	});
});
