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
<h1>배너 수정</h1>
	<form action='update.do' method='post' enctype='multipart/form-data'>
	<div>
		<label for='index'>번호</label>
		<input class="form-control" type="text" name="index" value="${banner.index }" readonly>
	</div>
	<div>
		<label for="formFileImage" class="form-label">이미지 선택</label>
		<input class="form-control" type="file" id="formFileImage" name="image" accept="image/*">
	</div>
	
	<div>
		<label for="formFileVideo" class="form-label">비디오 선택</label>
		<input class="form-control" type="file" id="formFileVideo" name="video" accept="video/*">
	</div>
	<div>
		<label for='state'>상태 </label>
		<select class="form-select" class="form-select" id='state' name='state' aria-label="Default select example">
			<option value='1' ${banner.state == 1 ? 'selected' : ''}>정상 </option>
			<option value='0' ${banner.state == 0 ? 'selected' : ''}>중지 </option>
		</select>
	</div>
	<button type="submit" class="btn btn-primary btn-lg">등록</button>
	<button type="reset" class="btn btn-secondary btn-lg" onclick="history.back(-1)">취소</button>
	</form>
	<img src="${path }/images/banner/${banner.imagePath }" class="img-thumbnail" alt="...">
	<video controls class="img-thumbnail"><source src="${path }/images/banner/${banner.videoPath }"></video>
</body>
</html>