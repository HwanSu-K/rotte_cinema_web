<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../cinema/include/IncludeCMS.jsp" %>
	<script src="../../js/cms.js"></script>
</head>
<body>

<h1>상영관 등록</h1>
	<form action='add.do' method='post' enctype='multipart/form-data'>
		<div>
		  <label for="name" class="form-label">상영관명</label>
		  <input type="text" class="form-control" id="name" name="name"">
		</div>
		<div>
		  <label for="seatX" class="form-label">좌석X</label>
		  <input type="text" class="form-control" id="seatX" name="seatX">
		</div>
		<div>
		  <label for="seatY" class="form-label">좌석Y</label>
		  <input type="text" class="form-control" id="seatY" name="seatY">
		</div>
		<div>
			<label for="cinemaIndex">지역</label>
			<select class="form-select" id="indexCinema" name="indexCinema">
				<c:forEach var="cinema" items="${cinemas }">
					<option value="${cinema.indexCinema }">${cinema.nameCinema } </option>
				</c:forEach>
			</select>
		</div>
		<button type="submit" class="btn btn-primary btn-lg">등록</button>
		<button type="reset" class="btn btn-secondary btn-lg" onclick="history.back(-1)">취소</button>
	</form>
</body>
</html>