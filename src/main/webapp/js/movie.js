$(document).ready(function() {
    var button1 = $('.movie_tab > div:nth-child(1)');
    var button2 = $('.movie_tab > div:nth-child(2)');

    button1.click(function(){
        button1.addClass('active'); 
        button2.removeClass('active');
        location.href='movie.do';
    })

    button2.click(function(){
        button2.addClass('active');
        button1.removeClass('active');
        location.href='movie.do?view=comingsoon';
    })
});