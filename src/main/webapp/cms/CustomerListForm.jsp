<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../cinema/include/IncludeCMS.jsp" %>
</head>
<body>
<div class="button">
	<button type="button" class="btn btn-primary btn-lg">등록</button>
	<button type="button" class="btn btn-primary btn-lg" onclick="location.href='../list.do'">콘텐츠 관리 시스템</button>
</div>
<h1>회원 목록 </h1>
<table class="table">
	<tr>
		<th>인덱스 </th>
		<th>아이디 </th>
		<th>비밀번호 </th>
		<th>이름 </th>
		<th>생년월일 </th>
		<th>전화번호 </th>
		<th>권한 </th>
		<th></th>
	</tr>
<c:forEach var="customer" items="${customers}">
	<tr>
		<td>${customer.index }</td>
		<td>${customer.email }</td>
		<td>${customer.password }</td>
		<td>${customer.name }</td>
		<td>${customer.birth  }</td>
		<td>${customer.phonenum  }</td>
		<td>${customer.access  }</td>
		<td>수정</a></td>
	</tr>
</c:forEach>
</table>
</body>
</html>