<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../cinema/include/IncludeCMS.jsp" %>	
</head>
<style>

</style>
<body>
<a href='../list.do'>콘텐츠 관리 시스템</a>
<h1>영화 목록 </h1>
<p>
	<a href='add.do'>영화 등록 </a>
</p>
<table border="1">
	<tr>
		<th>인덱스 </th>
		<th>제목 </th>
		<th>감독 </th>
		<!-- <th>출연자 </th> -->
		<th>장르 </th>
		<th>연령 </th>
		<th>상영시간 </th>
		<th>개봉일 </th>
		<th>종영일 </th>
		<th>포스터 </th>
		<th>태그 </th>
		<th>상태 </th>
		<th></th>
	</tr>
<c:forEach var="movie" items="${movies}">
	<tr>
		<td>${movie.index }</td>
		<td>${movie.title }</td>
		<td>${movie.director }</td>
		<%-- <td>${movie.actor }</td> --%>
		<td>${movie.genre }</td>
		<td>${movie.limitAge }</td>
		<td>${movie.runningTime }</td>
		<td>${movie.openDate }</td>
		<td>${movie.endDate }</td>
		<td>${movie.poster }</td>
		<td>${movie.tags  }</td>
		<td>${movie.state  }</td>
		<td><a href="update.do?no=${movie.index }">수정 </a></td>
	</tr>
</c:forEach>
</table>
</body>
</html>