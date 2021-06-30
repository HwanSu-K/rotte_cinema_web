$(document).ready(function() {
	$('#loginBtn').on('click', function(e) {

		let input = true;
		$('#login > div > div > input').each(function() {
			if($(this).val() == ""){
				$(this).focus();
				input = false;
				return false;
			}
		});
		
		if(input) {
			$('#login').submit();
		}
	});
});