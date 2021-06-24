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

<h1>배너 등록</h1>
	<form action='add.do' method='post' enctype='multipart/form-data'>
		<ul>
			<li><label for='image'>이미지 </label> <input id='image' type='file'
				name='image' size='50' accept='image/*'></li>
			<li><label for='video'>비디오 </label> <input id='video' type='file'
				name='video' size='50' accept='video/*'></li>
		</ul>
		<input type='submit' value='등록'> <input type='reset'
			value='취소' onclick='history.back(-1)'>
	</form>
	
</body>
</html>