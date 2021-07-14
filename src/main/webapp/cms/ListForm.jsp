<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../cinema/include/IncludeCMS.jsp" %>
	<style>
		body {
			display:flex;
			justify-content: center;
			flex-direction: column;
		}
		button {
			width:300px;
			margin:10px auto;
		}
	</style>
</head>

<body>
	<button type="button" id="main" class="btn btn-secondary btn-lg" onclick="location.href='../'">메인페이지</button>
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='banner/list.do'">배너관리</button>
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='movie/list.do'">영화관리</button>
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='customer/list.do'">회원관리</button>
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='local/list.do'">지역관리</button>
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='cinema/list.do'">극장관리</button>
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='theater/list.do'">상영관관리</button>
</body>
</html>