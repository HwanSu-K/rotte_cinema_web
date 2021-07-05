<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../cinema/include/IncludeCMS.jsp" %>
	<style>
		button {
			display:flex;
			justify-content: space-between;
		}
	</style>
</head>
<body>
<div class="button">
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='add.do'">등록</button>
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='../list.do'">콘텐츠 관리 시스템</button>
</div>
<h1>배너 목록</h1>
<table class="table">
	<tr>
		<th>인덱스 </th>
		<th>이미지 </th>
		<th>동영상 </th>
		<th>상태 </th>
		<th></th>
	</tr>
<c:forEach var="banner" items="${banners}">
	<tr>
		<td>${banner.index }</td>
		<td>${banner.imagePath }</td>
		<td>${banner.videoPath }</td>
		<td>${banner.state  }</td>
		<td><a href="update.do?no=${banner.index }">수정 </a></td>
	</tr>
</c:forEach>
</table>
</body>
</html>