<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../cinema/include/IncludeCMS.jsp" %>
<style>

</style>
</head>
<body>

<h1>영화 등록</h1>
	<form action='add.do' method='post' enctype='multipart/form-data'>
		<div>
		  <label for="title" class="form-label">제목</label>
		  <input type="text" class="form-control" id="title" name="title" placeholder="">
		</div>
		<div>
		  <label for="director" class="form-label">감독</label>
		  <input type="text" class="form-control" id="director" name="director" placeholder="">
		</div>
		<div>
		  <label for="actor" class="form-label">배우</label>
		  <input type="text" class="form-control" id="actor" name="actor" placeholder="">
		</div>
		<div>
		  <label for="genre" class="form-label">장르</label>
		  <input type="text" class="form-control" id="genre" name="genre" placeholder="">
		</div>
		<div>
			<label for="type">유형 </label>
			<select class="form-select" id="limitAge" name="limitAge">
					<option value="0">전체 </option>
					<option value="12">12세 </option>
					<option value="15">15세 </option>
					<option value="18">청불 </option>
			</select>
		</div>
		<div>
		  <label for="runningTime" class="form-label">상영시간</label>
		  <input type="text" class="form-control" id="runningTime" name="runningTime" placeholder="">
		</div>
		<div>
		  <label for="openDate" class="form-label">개봉일</label>
		  <input type="date" class="form-control" id="openDate" name="openDate" placeholder="">
		</div>
		<div>
		  <label for="endDate" class="form-label">종영일</label>
		  <input type="date" class="form-control" id="endDate" name="endDate" placeholder="">
		</div>
		<div>
		  <label for="image" class="form-label">포스터</label>
		  <input class="form-control" type="file" id="image" name="image" accept="image/*">
		</div>
		<div>
		  <label for="info" class="form-label">정보</label>
		  <input type="text" class="form-control" id="info" name="info" placeholder="">
		</div>
		<div>
		  <label for="tags" class="form-label">태그</label>
		  <input type="text" class="form-control" id="tags" name="tags" placeholder="">
		</div>
		<div>
			<label for="type">유형 </label>
			<select class="form-select" id="type" name="type">
					<option value="0">일반 </option>
					<option value="1">큐레이션 </option>
			</select>
		</div>
		<button type="submit" class="btn btn-primary btn-lg">등록</button>
		<button type="reset" class="btn btn-secondary btn-lg" onclick="history.back(-1)">취소</button>
	</form>
	
</body>
</html>