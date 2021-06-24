<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../cinema/include/IncludeCMS.jsp" %>
<style>
ul {
	padding: 0;
}

li {
	list-style: none;
}

label {
	float: left;
	text-align: right;
	width: 80px;
}
</style>
</head>
<body>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<h1>영화 등록</h1>
	<form action='update.do' method='post' enctype='multipart/form-data'>
		<ul>
			<li><label for='index'>번호 </label> <input id='index' type='text'
				name='index' size='50' readonly value='${banner.index }'></li>
			<li><label for='image'>이미지 </label> <input id='image' type='file'
				name='image' size='50' accept='image/*'></li>
			<li><label for='video'>비디오 </label> <input id='video' type='file'
				name='video' size='50' accept='video/*'></li>
			<li><label for='state'>상태 </label> <select id='state'
				name='state'>
				<option value='1' ${banner.state == 1 ? 'selected' : ''}>정상 </option>
				<option value='0' ${banner.state == 0 ? 'selected' : ''}>중지 </option>
			</select></li>
		</ul>
		<input type='submit' value='변경'> <input type='reset'
			value='취소' onclick='history.back(-1)'>
	</form>
	<img src="${path }/images/banner/${banner.imagePath }">
	<video controls><source src="${path }/images/banner/${banner.videoPath }"></video>
</body>
</html>