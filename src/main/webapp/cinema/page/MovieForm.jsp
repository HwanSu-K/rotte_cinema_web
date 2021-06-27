<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>

    <link rel="stylesheet" href="./style/movie.css" />
</head>
<body>	
    <c:import url="/include/header.do"/>
    <section class="location">      <!-- background-color: #f8f8fa; -->
        <div>
            <span><i class="fas fa-home"></i></span>
            <span>></span>
            <span>영화</span>
            <span>></span>
            <span>전체영화</span>
        </div>
    </section>
    <div class="content">
        <section class="movie">
            <div class="movie_tab">
                <div class="${param.type == null ? 'active' : ''}" onClick="location.href ='movie.do'">현재상영작</div>
                <div class="${param.type == 'soon' ? 'active' : ''}" onClick="location.href ='movie.do?type=soon'">상영예정작</div>
                <div class="${param.type == 'qration' ? 'active' : ''}" onClick="location.href ='movie.do?type=qration'">큐레이션</div>
            </div>
            <div class="movie_list">
            <c:forEach var="movie" items="${movies}">
				<div>
                    <div onClick="location.href ='moviedetail.do?index=${movie.index}'">
                        <img src="./images/poster/${movie.poster }">
                        <img src="./images/icon/age_${movie.limitAge }.png">
                    </div>
                    <div>
                        <span>${movie.title }</span>
                    </div>
                    <div>
                        <span>예매율 ${movie.reservRating }%</span>
                        <span>|</span>
                        <img src="./images/icon/post_infobox_icon.png"> 
                        <span>${movie.rating }</span>
                    </div>
                    <div>
                        <div>
                            <i class="far fa-heart"></i>
                            <span>${movie.like }</span>
                        </div>
                        <div>예매</div>
                    </div>
                </div>
			</c:forEach>
            </div>
        </section>
    </div>
    <c:import url="/include/footer.do"/>
</body>
</html>