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
	<c:set var="path" value="<%=request.getContextPath() %>"/>
	
    <%@ include file="../include/IncludeHeader.jsp" %>
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
                <div class="${param.view == null ? 'active' : ''}" OnClick="location.href ='movie.do'">현재 상영작</div>
                <div class="${param.view == 'comingsoon' ? 'active' : ''}" OnClick="location.href ='movie.do?view=comingsoon'">상영 예정작</div>
            </div>
            <div class="movie_list">
            <c:forEach var="movie" items="${movies}">
				<div>
                    <div>
                        <img src="${path }/images/poster/${movie.poster }">
                        <img src="./images/icon/age_${movie.limitAge }.png">
                    </div>
                    <div>
                        <span>${movie.title }</span>
                    </div>
                    <div>
                        <span>예매율 0.0%</span>
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
    <%@ include file="../include/IncludeFooter.jsp" %>
</body>
</html>