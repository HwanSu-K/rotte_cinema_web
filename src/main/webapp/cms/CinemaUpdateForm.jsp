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
<h1>극장 수정</h1>
	<form action='update.do' method='post' enctype='multipart/form-data'>
		<div>
			<label for='index'>번호</label>
			<input class="form-control" type="text" name="index" value="${cinema.index }" readonly>
		</div>
		<div>
		  <label for="title" class="form-label">극장명</label>
		  <input type="text" class="form-control" id="title" name="title" value="${cinema.title }">
		</div>
		<div>
		  <label for="addr" class="form-label">주소</label>
		  <input type="text" class="form-control" id="addr" name="addr" value="${cinema.addr }">
		</div>
		<div>
		  <label for="info" class="form-label">정보</label>
		  <input type="text" class="form-control" id="info" name="info" value="${cinema.info }">
		</div>
		<div>
			<label for="localIndex">지역</label>
			<select class="form-select" id="localIndex" name="localIndex">
				<c:forEach var="local" items="${locals }">
					<option value="${local.localIndex }" ${cinema.localIndex == local.localIndex ? 'selected' : ''}>${local.localName } </option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for='lat'>위도</label>
			<input class="form-control" type="text" id="lat" name="lat" value="${cinema.lat }" readonly>
		</div>
		<div>
			<label for='lng'>경도</label>
			<input class="form-control" type="text" id="lng" name="lng" value="${cinema.lng }" readonly>
		</div>
		<div>
		<label for='state'>상태 </label>
			<select class="form-select" class="form-select" id='state' name='state' aria-label="Default select example">
				<option value='1' ${cinema.state == 1 ? 'selected' : ''}>정상 </option>
				<option value='0' ${cinema.state == 0 ? 'selected' : ''}>중지 </option>
			</select>
		</div>
		<button type="submit" class="btn btn-primary btn-lg">등록</button>
		<button type="reset" class="btn btn-secondary btn-lg" onclick="history.back(-1)">취소</button>
	</form>
</body>
</html>