<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rotte Cinema CMS</title>
</head>
<body>
<h1>movie list</h1>
<p>
		<a href='add.do'>movie add</a>
</p>
<table border="1">
	<tr>
		<th>no </th>
		<th>title </th>
		<th>limitAge </th>
		<th></th>
	</tr>
<c:forEach var="movie" items="${movies}">
	<tr>
		<td>${movie.index }</td>
		<td>${movie.title }</td>
		<td>${movie.limitAge }</td>
		<td><a href="update.do?no=${movie.index }">수정</a></td>
	</tr>
</c:forEach>
</table>
</body>
</html>