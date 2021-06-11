<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
    <nav>
        <ul>
            <li><img src="images/icon/menu_icon.png"></li>
            <li><img src="images/icon/search_icon.png"></li>
            <li class="bar"><a href="">영화</a>
                <ul class="menu">
                    <li>전체영화</li>
                    <li>큐레이션</li>
                </ul>
            </li>
            <li class="bar"><a href="">예매</a>
                <ul class="menu">
                    <li>빠른예매</li>
                    <li>상영 시간표</li>
                </ul>
            </li>
            <li><img class="logo_w_blue" src="images/icon/rt_logo_w_blue.png"></li>
            <li class="bar"><a href="">이벤트</a>
                <ul class="menu">
                    <li>전체 이벤트</li>
                    <li>진행 이벤트</li>
                </ul>
            </li>
            <li class="bar"><a href="">시설안내</a>
                <ul class="menu">
                    <li>회사 소개</li>
                    <li>오시는 길</li>
                </ul>
            </li>
            <li><img src="images/icon/calendar_icon.png"></li>
            <li><img src="images/icon/user_icon.png"></li>
            
        </ul>
        <div id="back"></div>
    </nav>
</header>

<style>
.menu {
position: absolute; padding-top: 100px; display: none;
    color:#fff;

}

.menu li {padding: 0 10px 0 10px;}
#back.active {
    background-color: rgba(0,0,0, 0.6);
    height: 40px;
}

nav > ul > li:hover .menu {
    display: inline;
}
</style>

<script type="text/javascript">
    $(document).ready(function(){

    $('.bar').mouseenter(function() {
        console.log('마우스오버')
        $('#back').addClass('active');
});

    $('.bar').mouseleave(function() {
        console.log('리버')
        $('#back').removeClass('active');
});

});
</script>