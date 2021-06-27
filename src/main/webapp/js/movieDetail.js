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
						'<div>' + this.rating + '</div><div>' + this.text + '</div>' +
						'<div>' + this.date + '</div><div>' + this.name + '</div>'));
				});
			},
			error: function() {
				alert('통신이 원할하지 않습니다.');
			}
		});
	}

	$('#reviewButton').on('click', function() {
		if ($('#reviewButton').hasClass('disabled')) {
			return;
		}
		var form = $('#reviewInput').serialize();
		form += '&indexMovie=' + urlParams.get('index');

		const formParams = new URLSearchParams(form);

		// key 목록
		const values = formParams.values();

		for (const value of values) {
			if (value == '') {
				return;
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
				$('#reviewInput > input').val('');
				
			},
			error: function() {
				alert('통신이 원할하지 않습니다.');
			}
		});
	});

	reviewLoad();
});