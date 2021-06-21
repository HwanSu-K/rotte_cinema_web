<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Rotte Cinema CMS</title>
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

<h1>영화 등록</h1>
	<form action='update.do' method='post' enctype='multipart/form-data'>
		<ul>
			<li><label for='index'>번호 </label> <input id='index' type='text'
				name='index' size='50' readonly value="${movie.index }"></li>
			<li><label for='title'>제목 </label> <input id='title' type='text'
				name='title' size='50' value="${movie.title }"></li>
			<li><label for='director'>감독 </label> <input id='director' type='text'
				name='director' size='50' value="${movie.director }"></li>
			<li><label for='actor'>배우 </label> <input id='actor' type='text'
				name='actor' size='50' value="${movie.actor }"></li>
			<li><label for='genre'>장르 </label> <input id='genre' type='text'
				name='genre' size='50' value="${movie.genre }"></li>
			<li><label for="limitAge">상태</label> <select id="limitAge"
			name="limitAge">
				<option value="0">전체 </option>
				<option value="12">12세 </option>
				<option value="15">15세 </option>
				<option value="19">19세 </option>
			</select></li>
			<li><label for='runningTime'>상영시간 </label> <input id='runningTime' type='number'
				name='runningTime' size='50' value="${movie.runningTime }"></li>
			<li><label for='openDate'>개봉일 </label> <input id='openDate' type='date'
				name='openDate' size='50' value="${movie.openDate }"></li>
			<li><label for='endDate'>종영일 </label> <input id='endDate' type='date'
				name='endDate' size='50' value="${movie.endDate }"></li>
			<li><label for='file'>포스터 </label> <input id='file' type='file'
				name='file' size='50' accept='image/*' value="${movie.poster }"></li>
			<li><label for='info'>정보 </label> <textarea id='info'
				name='info' rows='5' cols='40'>${movie.info }</textarea></li>
			<li><label for='tags'>태그 </label> <input id='tags' type='text'
				name='tags' size='50' value="${movie.tags }"></li>
		</ul>
		<input type='submit' value='변경'> <input type='reset'
			value='취소' onclick='history.back(-1)'>
	</form>
	
</body>
</html>