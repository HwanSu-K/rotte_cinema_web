<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>   
	
	<link rel="stylesheet" href="./style/finder.css" />
	<script src="./js/finder.js"></script>
</head>
<body>
    <c:import url="/include/header.do"/>
    <section class="location">      <!-- background-color: #f8f8fa; -->
        <div>
            <span><i class="fas fa-home"></i></span>
            <span>></span>
            <span>로그인</span>
            <span>></span>
            <span>아이디/비밀번호 찾기</span>
        </div>
    </section>
    <div class="content">
        <div class="member_finder">
            <div class="finder_tab">
                <div class="${param.type == 'email' ? 'active' : ''}" onclick="location.href='finder.do?type=email'">아이디 찾기</div>
                <div class="${param.type == 'password' ? 'active' : ''}" onclick="location.href='finder.do?type=password'">비밀번호 찾기</div>
            </div>
            <section id="formID" class="${param.type == 'email' ? 'active' : ''}">
				<form id="id">
                    <div><label for="name">이름</label><input type="text" id="name" name="name" maxlength="5" placeholder="이름을 입력해 주세요."></div>
                    <div><label for="birth">생년월일</label><input type="text" id="birth" name="birth" maxlength="10" placeholder="생년월일을 입력해 주세요."></div>
                    <div><label for="phonenum">휴대폰번호</label><input type="text" id="phonenum" name="phonenum" maxlength="13" placeholder="'-'없이 입력해 주세요."></div>
                    
                    <div id="btnId">아이디 찾기</div>
                </form>
            </section>
            <section id="formPass" class="${param.type == 'password' ? 'active' : ''}">
				<form id="pass">
                    <div><label for="email2">이메일</label><input type="text" id="email2" name="email" maxlength="20" placeholder="이메일을 입력해 주세요."></div>
                    <div><label for="name2">이름</label><input type="text" id="name2" name="name" maxlength="5" placeholder="이름을 입력해 주세요."></div>
                    <div><label for="birth2">생년월일</label><input type="text" id="birth2" name="birth" maxlength="10" placeholder="생년월일을 입력해 주세요."></div>
                    <div><label for="phonenum2">휴대폰번호</label><input type="text" id="phonenum2" name="phonenum" maxlength="13" placeholder="'-'없이 입력해 주세요."></div>
                    
                    <div id="btnPass">비밀번호 찾기</div>
                </form>
            </section>
        </div>
    </div>
    <c:import url="/include/footer.do"/>
</body>
</html>