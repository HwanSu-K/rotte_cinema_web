$(document).ready(function() {
	const urlParams = new URLSearchParams(location.search);
	let adultAmount;
	let teenagerAmount;

	$.ajax({
		url: 'paytypeobject.do',
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			// 가격 호출.
			adultAmount = data.paytype[0].amount;
			teenagerAmount = data.paytype[1].amount;
		},
		error: function() {
			console.log('error');
		}
	});
	
	const form = {
		index:urlParams.get('index')
	}
	
	$.ajax({
		url: 'reservobject.do',
		type: 'POST',
		data: form,
		dataType: 'json',
		success: function(data) {
			$(data.reservs).each(function() {
				$('#x' + this.seatX + 'y' + this.seatY).addClass('reserv');
			});
		},
		error: function() {
			console.log('error');
		}
	});

	$('#adult > div').on('click', (e) => {
		const target = $(e.currentTarget).children('i');
		if (target) {
			if ($(target).hasClass('fa-plus')) {
				const count = parseInt($('#adult_count').text());
				if (count < 4) {
					$('#adult_count').text(count + 1);
				}
			} else {
				const count = $('#adult_count').text();
				if (count > 0) {
					$('#adult_count').text(count - 1);
				}
			} amount();
		}
	});

	$('#teenager > div').on('click', (e) => {
		const target = $(e.currentTarget).children('i');
		if (target) {
			if ($(target).hasClass('fa-plus')) {
				const count = parseInt($('#teenager_count').text());
				if (count < 4) {
					$('#teenager_count').text(count + 1);
				}
			} else {
				const count = $('#teenager_count').text();
				if (count > 0) {
					$('#teenager_count').text(count - 1);
				}
			}
			amount();
		}
	});

	function amount() {
		const adult = parseInt($('#adult_count').text()) * adultAmount;
		const teenager = parseInt($('#teenager_count').text()) * teenagerAmount;
		const count = parseInt($('#adult_count').text()) + parseInt($('#teenager_count').text());;

		$('#amount').text((adult + teenager).toLocaleString('ko-KR'));
		if (count > 0) {
			$('.mask').fadeOut();
		} else {
			$('.mask').fadeIn();
		}

		init();
	}

	$('.reserv_seat > div > div').on('click', (e) => {
		const target = $(e.currentTarget);
		const maxCount = parseInt($('#adult_count').text()) + parseInt($('#teenager_count').text());;
		if (!target.hasClass('reserv') && !target.hasClass('disabled')) {
			if (!$(target).hasClass('active')) {

				if (checkCount() >= maxCount) {
					return false;
				}
			}
			target.toggleClass('active');

			if (checkCount() === maxCount) {
				$('#pay').addClass('active');
			}
			else {
				$('#pay').removeClass('active');
			}
		}

	});

	IMP.init('imp42092045');
	$('#pay').on('click', () => {
		const maxCount = parseInt($('#adult_count').text()) + parseInt($('#teenager_count').text());;

		if (checkCount() === maxCount && maxCount > 0) {

			$.ajax({
				url: 'customerobject.do',
				type: 'POST',
				dataType: 'json',
				success: function(data) {
					if (data === -1) {
						alert('로그인이 필요한 서비스 입니다.');
						return false;
					}
					
					pay(data);
				},
				error: function() {
					console.log('error');
				}
			});
		}
	});

	function pay(data) {
		const adult = parseInt($('#adult_count').text()) * adultAmount;
		const teenager = parseInt($('#teenager_count').text()) * teenagerAmount;
		const amount = adult + teenager;
		IMP.request_pay({
			pg: 'html5_inicis',
			pay_method: 'card',
			merchat_uid: 'merchant_' + new Date().getTime(),
			name: $('.reserv_info_title > div').text(),
			amount: amount,
			buyer_email: data.customer.email,
			buyer_name: data.customer.name,
			buyer_tel: data.customer.phonenum,
			buyer_addr: data.customer.address,
			buye_postcode: data.customer.zipcode
		}, function(rsp) {
			if (rsp.success) {
				const urlParams = new URLSearchParams(location.search);
				const maxCount = parseInt($('#teenager_count').text());
				let count = 0;
				let reservs = [];
				$('.reserv_seat > div > div').each(function() {
					if ($(this).hasClass('active')) {
						var reserv = {
							seatX: $(this).data('seat-x'),
							seatY: $(this).data('seat-y'),
							showingIndex: parseInt(urlParams.get('index')),
							payCategory: count < maxCount ? 2 : 1
						}
						count++;
						reservs.push(reserv);
					}
				});

				var jsonData = JSON.stringify(reservs);
				$.ajax({
					url: 'reservation.do',
					type: 'POST',
					data: { "jsonData": jsonData, "uid": rsp.imp_uid},
					dataType: 'json',
					success: function(data) {
						if (data === -1) {
							alert('로그인이 필요한 서비스 입니다.');
							return false;
						} else if (data.result === "fail") {
							alert(data.message);
							return false;
						}
						console.log(data);
					},
					error: function() {
						console.log('error');
					}
				});

							
			} else {
				alert(rsp.error_msg);
			}

		
		});
	}
	function init() {
		$('.reserv_seat > div > div').each(function() {
			$(this).removeClass('active');
			$('.reserv_pay > div:nth-child(2)').removeClass('active');
		});
	}

	function checkCount() {
		let count = 0;
		$('.reserv_seat > div > div').each(function() {
			if ($(this).hasClass('active')) {
				count++;
			}
		});
		return count;
	}
});