$(document).ready(function() {
    var button_movie = $('.reserv_type_movie');
    var button_theater = $('.reserv_type_theater');
    var button_dates = $('.reserv_info_date > div:nth-child(2) > div');

    button_movie.click(function(){
        button_movie.addClass('active'); 
        button_theater.removeClass('active');
    })

    button_theater.click(function(){
        button_theater.addClass('active');
        button_movie.removeClass('active');
    })

    button_dates.hover(function(){


    }, function() {

    })
});