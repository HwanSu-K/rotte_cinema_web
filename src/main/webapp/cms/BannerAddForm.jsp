<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../cinema/include/IncludeCMS.jsp" %>
</head>
<body>

<h1>배너 등록</h1>
	<form action='add.do' method='post' enctype='multipart/form-data'>
	<div>
	  <label for="formFileImage" class="form-label">이미지 선택</label>
	  <input class="form-control" type="file" id="formFileImage" name="image" accept="image/*">
	</div>
	
	<div>
	  <label for="formFileVideo" class="form-label">비디오 선택</label>
	  <input class="form-control" type="file" id="formFileVideo" name="video" accept="video/*">
	</div>
	
	<button type="submit" class="btn btn-primary btn-lg">등록</button>
	<button type="reset" class="btn btn-secondary btn-lg" onclick="history.back(-1)">취소</button>
	</form>
	
</body>
</html>