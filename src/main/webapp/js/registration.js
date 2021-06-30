$(document).ready(function() {

	$('#passcheck,#pass').on('blur', function() {
		if ($('#passcheck').val() === "" || $('#pass').val() === "") {
			return false;
		}

		if ($('#passcheck').val() != $('#pass').val()) {
			$('#passAlert').removeClass('disabled');
			$('#passAlert').text('비밀번호가 일치하지 않습니다.');
		}
		else {
			$('#passAlert').addClass('disabled');
			$('#passAlert').text('');
		}
	});

	function validateEmail(sEmail) {
		var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		if (filter.test(sEmail)) {
			return true;
		}
		else {
			return false;
		}
	}

	$('#email,#emailAddr,#emailSelect').on('blur', function() {
		if ($('#email').val() === "" || $('#emailAddr').val() === "") {
			// 내용을 입력해야 처리되도록.
			return false;
		}

		var form = {
			email: $('#email').val() + '@' + $('#emailAddr').val()
		}

		if (!validateEmail(form.email)) {
			$('#emailAlert').removeClass('disabled');
			$('#emailAlert').text('이메일 형식이 잘못되었습니다.');
			return false;
		}

		$.ajax({
			url: 'emailobject.do',
			type: 'POST',
			data: form,
			dataType: 'json',
			success: function(data) {
				if (data != true) {
					$('#emailAlert').removeClass('disabled');
					$('#emailAlert').text('이미 존재하는 이메일 입니다.');
				}
				else {
					$('#emailAlert').removeClass('disabled');
					$('#emailAlert').addClass('active');
					$('#emailAlert').text('사용 가능한 이메일 입니다.');
				}
			},
			error: function() {
				alert('통신이 원할하지 않습니다.');
			}
		});
	});

	$('#btn_reg').on('click', function(e) {

		let input = true;
		$('#reg > div > input').each(function() {
			if ($(this).val() == "") {
				$(this).focus();
				input = false;
				return false;
			}
		});

		if (input) {
			//$('#login').submit();
		}
	});

	$('#emailSelect').on('change', function(e) {

		var idx = $("#emailSelect option").index($("#emailSelect option:selected"));

		if (idx === 0) {
			$('#emailAddr').attr('disabled', false);
			$('#emailAddr').val('');
		} else {
			$('#emailAddr').attr('disabled', true);
			$('#emailAddr').val(this.value);
		}

	});
});