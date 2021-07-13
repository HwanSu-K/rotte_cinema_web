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
<div class="button">
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='add.do'">등록</button>
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='../list.do'">콘텐츠 관리 시스템</button>
</div>
<h1>상영관 목록 </h1>
<table class="table">
	<tr>
		<th>인덱스 </th>
		<th>상영관 </th>
		<th>좌석 </th>
		<th>극장명 </th>
		<th></th>
	</tr>
<c:forEach var="theater" items="${theaters}">
	<tr>
		<td>${theater.index }</td>
		<td>${theater.name }</td>
		<td>${theater.seatX }/${theater.seatY }</td>
		<td>${theater.nameCinema }</td>
		<td><a href="update.do?no=${theater.index }">수정 </a></td>
	</tr>
</c:forEach>
</table>
</body>
</html>