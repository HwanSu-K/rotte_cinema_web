<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>    
</head>
<body>
    <c:import url="/include/header.do"/>
    <div class="content">
    <c:if test="${empty sessionScope.customer or empty sessionScope.customer.email}">
		<h2>사용자 로그인</h2>
		<form action="login.do" method="post">
			이메일: <input type="text" name="email"><br>
			암호: <input type="password" name="password"><br>
			<input type="submit" value="로그인">
		</form>
		<br><br><br><br><br><br><br>
	</c:if> 
		
    <c:if test="${!empty sessionScope.customer and !empty sessionScope.customer.email}">
			${sessionScope.customer.name}<br>
			<a style="color: #000" href="<%=request.getContextPath()%>/logout.do">로그아웃</a>
			<c:if test="${sessionScope.customer.access == 0}">
			<br>
			<a style="color: #000" href="<%=request.getContextPath()%>/cms/list.do">컨텐츠 관리 시스템</a><br>
			</c:if>
	</c:if>
    </div>
    <c:import url="/include/footer.do"/>
</body>
</html>