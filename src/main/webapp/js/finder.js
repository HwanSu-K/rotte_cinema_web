$(document).ready(function() {

	$('#btnId').on('click', function() {
		let input = true;
		$('#id > div > input').each(function() {
			if($(this).val() == ""){
				$(this).focus();
				input = false;
				return false;
			}
		});
		
		if(input) {
			const form = $('#id').serialize();
			$.ajax({
				url: 'finderobject.do',
				type: 'POST',
				data: form,
				dataType: 'json',
				success: function(data) {
					$('#id').each(function() {
						this.reset();
					});
	
	
					if (data.customer != null && data.customer.email != null) {
						$('#id').remove();
						$('#formID').append($(
							'<div>' + new URLSearchParams(form).get('name') + '님의 아이디는 아래와 같습니다.</div>' +
							'<div>' + data.customer.email + '</div>'));
					}
					else {
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
		$('#pass > div > input').each(function() {
			if($(this).val() == ""){
				$(this).focus();
				input = false;
				return false;
			}
		});
		
		if(input) {
			const form = $('#pass').serialize();
			$.ajax({
				url: 'finderobject.do',
				type: 'POST',
				data: form,
				dataType: 'json',
				success: function(data) {
					$('#pass').each(function() {
						this.reset();
					});
	
					if (data.customer != null && data.customer.password != null) {
						$('#pass').remove();
						$('#formPass').append($(
							'<div>' + new URLSearchParams(form).get('name') + '님의 비밀번호는 아래와 같습니다.</div>' +
							'<div>' + data.customer.password + '</div>'));
					}
					else {
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