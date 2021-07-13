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

<h1>극장 등록</h1>
	<form action='add.do' method='post' enctype='multipart/form-data'>
		<div>
		  <label for="title" class="form-label">극장명</label>
		  <input type="text" class="form-control" id="title" name="title" placeholder="">
		</div>
		<div>
		  <label for="addr" class="form-label">주소</label>
		  <input type="text" class="form-control" id="addr" name="addr" placeholder="">
		</div>
		<div>
		  <label for="info" class="form-label">정보</label>
		  <input type="text" class="form-control" id="info" name="info" placeholder="">
		</div>
		<div>
			<label for="localIndex">지역</label>
			<select class="form-select" id="localIndex" name="localIndex">
				<c:forEach var="local" items="${locals }">
					<option value="${local.localIndex }">${local.localName } </option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for='lat'>위도</label>
			<input class="form-control" type="text" id="lat" name="lat" readonly>
		</div>
		<div>
			<label for='lng'>경도</label>
			<input class="form-control" type="text" id="lng" name="lng" readonly>
		</div>
		<button type="submit" class="btn btn-primary btn-lg">등록</button>
		<button type="reset" class="btn btn-secondary btn-lg" onclick="history.back(-1)">취소</button>
	</form>
</body>
</html>