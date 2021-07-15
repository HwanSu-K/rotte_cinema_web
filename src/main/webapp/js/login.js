$(document).ready(function() {
	
	// 로그인 실패, 성공 여부 확인을 위하여 비동기 요청으로 변경.
	$('#loginBtn').on('click', function(e) {
		let input = true;
		$('#login > div > div > input').each(function() {
			if ($(this).val() == "") {
				$(this).focus();
				input = false;
				return false;
			}
		});

		if (input == true) {
			var form = $('#login').serialize();
			
			$.ajax({
				url: 'loginobject.do',
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
					if ($('input:checkbox[name=saveEmail]').is(':checked') === true) {
						$.cookie('email', $('input:text[name=email]').val(), { expires: 365 });
					}

					if (data.result == 'fail') {
						$('input:password[name=password]').val('');
						alert('이메일이나 패스워드를 확인해 주세요.');
					} else if (data.result == 'auth') {
						location.href = 'loginwarning.do';
					} else if (data.result == 'connect') {
						location.href = 'main.do';
					}
					
					
				},
				error: function() {

				}
			});
		}
	});
});