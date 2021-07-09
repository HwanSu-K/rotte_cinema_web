<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>   
	
	<link rel="stylesheet" href="./style/registration.css" /> 
	<script src="./js/registration.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
    <c:import url="/include/header.do"/>
    <section class="location">      <!-- background-color: #f8f8fa; -->
        <div>
            <span><i class="fas fa-home"></i></span>
            <span>></span>
            <span>로그인</span>
        </div>
    </section>
    <div class="content">
        <div class="member_reg">
            <section id="formcomp" class="active">
                <div>이메일 인증이 필요합니다. 이메일을 확인해 주세요.</div>
            </section>
            <div class="reg_alert active" onclick="location.href ='login.do'"><div>확인</div></div>
        </div>
    </div>
    <c:import url="/include/footer.do"/>
</body>
</html>