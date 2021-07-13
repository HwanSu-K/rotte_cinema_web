$(document).ready(function() {

	let aroundCheck = true;
	if (navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(
	        function(location){
			sessionStorage.setItem('lat', location.coords.latitude);
			sessionStorage.setItem('lng', location.coords.longitude);
			around();			
			
	        },
	        function(error){
	       }
	    );
	}

	
	function around() {
		if(aroundCheck === true) {
			$('.theater_list_title').prepend($(
			`<span>|</span>`
			));
			$('.theater_list_title').prepend($(
			`<div class="locals" data-tab-type="around">내주변</div>`
			));
			
			
			var form = {
				lat: sessionStorage.getItem('lat'),
				lng: sessionStorage.getItem('lng')
				
			}
	
			$.ajax({
				url: 'aroundobject.do',
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
					const arounds = data.arounds;
					
					$(arounds).each(function() {
						const cinema_index = this.indexCinema;
						$('.theater_list_tab > div').each(function() {
							if($(this).data('cinema-index') === cinema_index) {
								$(this).data('cinema-around','true');
							}
						});
					});
					
					$('.theater_list_title > div:nth-child(1)').click();
				},
				error: function() {
	
				}
			});
			
			aroundCheck = false;
		}
		
	}
	$(document).on('click','.theater_list_title > div', function(e) {
		$('.theater_list_title > div').each(function() {
			$(this).removeClass('active');
		});
		
		$('.theater_list_tab > div').each(function() {
			if ($(e.currentTarget).data('local-class') === $(this).data('local-class')) {
				$(this).css('display','flex');
			} else if(typeof $(this).data('cinema-around') !== 'undefined' && $(e.currentTarget).data('tab-type') === 'around')
				{
					$(this).css('display', 'flex');
			} else {
				$(this).css('display','none');
			}
		});

		$(e.currentTarget).addClass('active');
	});
	
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
				
				}
			});
		}
	});
	
	if(sessionStorage.getItem('lat')) {
		around();
	}
	
	$('.theater_list_title > div:nth-child(1)').click();
});