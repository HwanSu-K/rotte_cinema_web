$(document).ready(function() {

	$('.theater_list_title > div').on('click', function(e) {
		$('.theater_list_title > div').each(function() {
			$(this).removeClass('active');
		});
		
		$('.theater_list_tab > div').each(function() {
			if ($(e.currentTarget).data('local-class') === $(this).data('local-class')) {
				$(this).css('display','flex');
			} else {
				$(this).css('display','none');
			}
		});

		$(e.currentTarget).addClass('active');
	});

	// 첫번째 항목 클릭.
	$('.theater_list_title > div:nth-child(1)').click();

	$('.reserv_tab > div').on('click',function(e){
		//console.log(e.target);
	});
	
	$('.theater_list_tab > div').on('click',function(e){
		if(!$(e.currentTarget).hasClass('disabled')) {
			$('.reserv_tab > div:nth-child(2)').removeClass('active');
			$('.reserv_tab > div:nth-child(3)').addClass('active');
			$('.theater_choice').removeClass('active');
			$('.datetime_choice').addClass('active');
			
			$('.datetime_list_title').text($(e.currentTarget).text());
			
			var form = {
				movie: new URLSearchParams(location.search).get('index'),
				cinema: $(e.target).data('cinema-index')
			}
					
			$.ajax({
				url: 'theatersobject.do',
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
					$('.datetime_list_tab > div').remove();
					
					var theater = null;
					$(data.theaters).each(function(){
						if(theater != this.index) {
							$('.datetime_list_tab').append($(
							`<div>` + 
								`<div>` +
									`<div>` +
										`<div>${this.name}</div>` +
										`<div>총 ${this.seatX * this.seatY}석</div>` +
									`</div>` +
									`<div>` +
										`<div>2D(자막)</div>` +
									`</div>` +
								`</div>` +
								`<div id="theater-${this.index}">` +
								`</div>` +
							`</div>`));
							theater = this.index;
						}
						
						
						$('#theater-' + this.index + '').append($(
							`<div onclick="location.href='reservation.do?index=${this.indexShowing}'">` +
								`<div>${this.startTime}</div>` +
								`<span>${this.seatX * this.seatY - this.seatCount}석</span>` +
							`</div>`
						));								
					})
				},
				error: function() {
					console.log('error');
				}
			});
		}
	});
});