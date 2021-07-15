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
    <div class="content" style="display: flex; justify-content: center; align-items: center; flex-direction: column; height: 80vh">
        <img src="${path }/images/icon/warning.jpeg">
        <br>
        <span>문제가 발생하였습니다. 관리자에게 문의하세요.</span>
        <span>${error }</span>
    </div>
	<c:import url="/include/footer.do"/>
</body>
</html>