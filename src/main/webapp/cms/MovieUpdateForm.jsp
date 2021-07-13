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
<c:set var="path" value="<%=request.getContextPath() %>"/>
<h1>영화 등록</h1>
	<form action='update.do' method='post' enctype='multipart/form-data'>
		<div>
			<label for='index'>번호</label>
			<input class="form-control" type="text" name="index" value="${movie.index }" readonly>
		</div>
		<div>
		  <label for="title" class="form-label">제목</label>
		  <input type="text" class="form-control" id="title" name="title" value="${movie.title }" placeholder="">
		</div>
		<div>
		  <label for="director" class="form-label">감독</label>
		  <input type="text" class="form-control" id="director" name="director" value="${movie.director }" placeholder="">
		</div>
		<div>
		  <label for="actor" class="form-label">배우</label>
		  <input type="text" class="form-control" id="actor" name="actor" value="${movie.actor }" placeholder="">
		</div>
		<div>
		  <label for="genre" class="form-label">장르</label>
		  <input type="text" class="form-control" id="genre" name="genre" value="${movie.genre }" placeholder="">
		</div>
		<div>
			<label for="type">유형 </label>
			<select class="form-select" id="limitAge" name="limitAge">
					<option value="0" ${movie.limitAge == 0 ? 'selected' : ''}>전체 </option>
					<option value="12" ${movie.limitAge == 12 ? 'selected' : ''}>12세 </option>
					<option value="15" ${movie.limitAge == 15 ? 'selected' : ''}>15세 </option>
					<option value="18" ${movie.limitAge == 18 ? 'selected' : ''}>청불 </option>
			</select>
		</div>
		<div>
		  <label for="runningTime" class="form-label">상영시간</label>
		  <input type="text" class="form-control" id="runningTime" name="runningTime" value="${movie.runningTime }" placeholder="">
		</div>
		<div>
		  <label for="openDate" class="form-label">개봉일</label>
		  <input type="date" class="form-control" id="openDate" name="openDate" value="${movie.openDate }" placeholder="">
		</div>
		<div>
		  <label for="endDate" class="form-label">종영일</label>
		  <input type="date" class="form-control" id="endDate" name="endDate" value="${movie.endDate }" placeholder="">
		</div>
		<div>
		  <label for="image" class="form-label">포스터</label>
		  <input class="form-control" type="file" id="image" name="image" value="${movie.poster }" accept="image/*">
		</div>
		<div>
		  <label for="info" class="form-label">정보</label>
		  <input type="text" class="form-control" id="info" name="info" value="${movie.info }" placeholder="">
		</div>
		<div>
		  <label for="tags" class="form-label">태그</label>
		  <input type="text" class="form-control" id="tags" name="tags" value="${movie.tags }" placeholder="">
		</div>
		<div>
			<label for="type">유형 </label>
			<select class="form-select" id="type" name="type">
					<option value="0" ${movie.type == 0 ? 'selected' : ''}>일반 </option>
					<option value="1" ${movie.type == 1 ? 'selected' : ''}>큐레이션 </option>
			</select>
		</div>
		<div>
		<label for='state'>상태 </label>
			<select class="form-select" class="form-select" id='state' name='state' aria-label="Default select example">
				<option value='1' ${movie.state == 1 ? 'selected' : ''}>정상 </option>
				<option value='0' ${movie.state == 0 ? 'selected' : ''}>중지 </option>
			</select>
		</div>
		<button type="submit" class="btn btn-primary btn-lg">등록</button>
		<button type="reset" class="btn btn-secondary btn-lg" onclick="history.back(-1)">취소</button>
	</form>
	<img src="/images/poster/${movie.poster }">
</body>
</html>