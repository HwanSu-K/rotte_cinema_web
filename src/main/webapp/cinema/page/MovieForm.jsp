<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>

    <link rel="stylesheet" href="./style/movie.css" />
    <script src="./js/movie.js"></script>
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
            
            <div class="reserv_info_age">
                <div>
                    <img src="./images/icon/age_0.png">
                    <span>전체 관람</span>
                </div>
                <div>
                    <img src="./images/icon/age_12.png">
                    <span>만 12세 이상</span>
                </div>
                <div>
                    <img src="./images/icon/age_15.png">
                    <span>만 15세 이상</span>
                </div>
                <div>
                    <img src="./images/icon/age_18.png">
                    <span>청소년 관람불가(만 18세 이상)</span>
                </div>
            </div>
            
            <div class="movie_list">
            <c:forEach var="movie" items="${movies}" varStatus="status">
				<div>
                    <div onClick="location.href ='moviedetail.do?index=${movie.index}'">
                    	<div><p>${status.count }</p></div>
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
                    <div class="movieLike" data-movie-index ="${movie.index }">
                        <c:set var="loop_flag" value="false" />
                        <c:forEach var="like" items="${likes }">
					    	<c:if test="${like.indexMovie == movie.index }">
					    		<i class="fas fa-heart active"></i>
					    		<c:set var="loop_flag" value="true" />
					    	</c:if>
					    </c:forEach>
					    <c:if test="${not loop_flag }">
					    	<i class="far fa-heart"></i>
				    	</c:if>
					    
                            
                            <span>${movie.like }</span>
                        </div>
                        <c:if test="${movie.openSoon >= 0 }">
                        	<div onClick="location.href ='ticketing.do?index=${movie.index}'">예매</div>
                        </c:if>
                        
                        <c:if test="${movie.openSoon < 0 }">
                        	<div class="disabled">D${movie.openSoon }</div>
                        </c:if>
                        
                    </div>
                </div>
			</c:forEach>
            </div>
        </section>
    </div>
    <c:import url="/include/footer.do"/>
</body>
</html>