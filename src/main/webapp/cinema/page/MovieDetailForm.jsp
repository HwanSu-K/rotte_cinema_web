<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>

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

		<img src="${path }/images/poster/${movie.poster }">
		<img src="./images/icon/age_${movie.limitAge }.png">
		<br>
		<div>제목 ${movie.title }</div>
		<br>
		<div>감독 ${movie.director }</div>
		<br>
		<div>배우 ${movie.actor }</div>
		<br>
		<div>장르 ${movie.genre }</div>
		<br>
		<div>상영시간 ${movie.runningTime }</div>
		<br>
		<div>개봉일 ${movie.openDate }</div>
		<br>
		<div>정보 ${movie.info }</div>
		<br>
		<div>태그 ${movie.tags }</div>
		<br>
		<div>예매율 ${movie.reservRating }%</div>
		<br> 
		<div>평점 ${movie.rating }</div>
		<br> 
		<div>좋아요 ${movie.like }</div>
		
                        
	</div>
    <c:import url="/include/footer.do"/>
</body>
</html>