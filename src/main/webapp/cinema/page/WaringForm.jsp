<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>
</head>
<body>
	<c:import url="/include/header.do"/>
	<div class="content" style="display: flex; justify-content: center; align-items: center; flex-direction: column; height: 80vh;">
        <img src="./images/icon/warning.jpeg">
        <br>
        <span>이 페이지는 공사중 입니다.</span>
    </div>
    <c:import url="/include/footer.do"/>
</body>
</html>