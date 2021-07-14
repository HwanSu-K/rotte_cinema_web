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
<h1>지역 수정</h1>
	<form action='update.do' method='post' enctype='multipart/form-data'>
		<div>
			<label for='index'>번호</label>
			<input class="form-control" type="text" id="index" name="index" value="${local.index }" readonly>
		</div>
		<div>
		  <label for="name" class="form-label">지역명</label>
		  <input type="text" class="form-control" id="name" name="name" value="${local.name }">
		</div>
		<div>
		  <label for="className" class="form-label">별칭</label>
		  <input type="text" class="form-control" id="className" name="className" value="${local.className }">
		</div>
		<div>
		  <label for="sort" class="form-label">정렬</label>
		  <input type="number" class="form-control" id="sort" name="sort" value="${local.sort }">
		</div>
		<button type="submit" class="btn btn-primary btn-lg">등록</button>
		<button type="reset" class="btn btn-secondary btn-lg" onclick="history.back(-1)">취소</button>
	</form>
</body>
</html>