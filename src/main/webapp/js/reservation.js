$(document).ready(function() {
	const urlParams = new URLSearchParams(location.search);
	let adultAmount;
	let teenagerAmount;
	
	// 서버에서 등급별 가격 호출.
	$.ajax({
		url: 'paytypeobject.do',
		type: 'POST',
		dataType: 'json',
		beforeSend: function() {
			$('#bg_mask').addClass('active');
	    },
	    complete: function() {
			$('#bg_mask').removeClass('active');
	    },
		success: function(data) {
			// 가격 호출.
			adultAmount = data.paytype[0].amount;
			teenagerAmount = data.paytype[1].amount;
		},
		error: function() {
			
		}
	});
	
	const form = {
		index:urlParams.get('index')
	}
	
	// 서버에서 예약된 좌석 호출.
	$.ajax({
		url: 'reservobject.do',
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
			$(data.reservs).each(function() {
				$('#x' + this.seatX + 'y' + this.seatY).addClass('reserv');
			});
		},
		error: function() {
			
		}
	});


	// 성인 버튼 선택시 버튼에 따른 이벤트.
	$('#adult > div').on('click', (e) => {
		const target = $(e.currentTarget).children('i');
		if (target) {
			const maxCount = parseInt($('#adult_count').text()) + parseInt($('#teenager_count').text());;
			const count = parseInt($('#adult_count').text());
				
			if ($(target).hasClass('fa-plus')) {	
				if (maxCount < 8) {
					$('#adult_count').text(count + 1);
				}
			} else {
				if (count > 0) {
					$('#adult_count').text(count - 1);
				}
			} amount();
		}
	});
	
	// 청소년 버튼 선택시 버튼에 따른 이벤트.
	$('#teenager > div').on('click', (e) => {
		const target = $(e.currentTarget).children('i');
		if (target) {
			const maxCount = parseInt($('#adult_count').text()) + parseInt($('#teenager_count').text());;
			const count = parseInt($('#teenager_count').text());
			
			if ($(target).hasClass('fa-plus')) {
				if (maxCount < 8) {
					$('#teenager_count').text(count + 1);
				}
			} else {
				
				if (count > 0) {
					$('#teenager_count').text(count - 1);
				}
			}
			amount();
		}
	});
	
	$('.reserv_reset').on('click',function() {
		init();
	});
	
	// 합계가격 표시.
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

	// 시트 선택시 오른쪽 시트 항목에 표시.
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
			
			$('.select_seat > div').each(function() {
				$(this).text('-');
				$(this).removeClass('active');
			});
			
			let seatCount = 0;
			$('.reserv_seat > div > div').each(function() {
				if ($(this).hasClass('active')) {
					seatCount++;
					$(`.select_seat > div:nth-child(${seatCount})`).text(`${String.fromCharCode(64 + parseInt($(this).data('seat-y')))}${$(this).data('seat-x')}`)
					$(`.select_seat > div:nth-child(${seatCount})`).addClass('active');
				}
			});
			
			if (checkCount() === maxCount) {
				$('#pay').addClass('active');
			}
			else {
				$('#pay').removeClass('active');
			}
		}

	});

	// 결제 API 호출 부분.
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
					alert('결제 금액은 자동으로 취소됩니다.');
					pay(data);
				},
				error: function() {
					
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
			name: $('.movie_title > div > div:nth-child(1)').text().trim(),
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
				
				// json형식으로 배열을 담아 직렬화 하여 서버로 전송.
				var jsonData = JSON.stringify(reservs);
				$.ajax({
					url: 'reservation.do',
					type: 'POST',
					data: { "jsonData": jsonData, "uid": rsp.imp_uid},
					dataType: 'json',
					beforeSend: function() {
						$('#bg_mask').addClass('active');
				    },
				    complete: function() {
						$('#bg_mask').removeClass('active');
				    },
					success: function(data) {
						// 정상적으로 결제가 되었는지 체크하는 부분.
						if (data === -1) {
							alert('로그인이 필요한 서비스 입니다.');
							return false;
						} else if (data.result === "fail") {
							alert(data.message);
							return false;
						}
						
						location.href=`pay.do?index=` + data.index;
					},
					error: function() {
						
					}
				});

							
			} else {
				alert(rsp.error_msg);
			}

		
		});
	}
	// 초기화.
	function init() {
		const maxCount = parseInt($('#adult_count').text()) + parseInt($('#teenager_count').text());;
		
		$('.reserv_seat > div > div').each(function() {
			$(this).removeClass('active');
			$('.reserv_pay > div:nth-child(2)').removeClass('active');
		});
		
		$('.select_seat > div').each(function(index, item) {
			$(item).text('-');
			$(item).removeClass('active');
			
			if(index < maxCount) {
				$(item).addClass('enabled');	
			} else
			{
				$(item).removeClass('enabled');
			}
		});
	}

	// 좌석수 카운트.
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
