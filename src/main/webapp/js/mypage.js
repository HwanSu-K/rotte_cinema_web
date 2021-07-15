$(document).ready(function() {
	$('#like_plus').on('click',function() {
		$('.like > .reserv_movie_list > div').each(function() {
			$(this).removeClass('h');
		})
	});
	
	$('#reserv_plus').on('click',function() {
		$('.reserv table tr').each(function() {
			$(this).removeClass('h');
		})
	});	
	
	$('#ticket_plus').on('click',function() {
		$('.ticket > .info > div').each(function() {
			$(this).removeClass('h');
		})
	});	
});