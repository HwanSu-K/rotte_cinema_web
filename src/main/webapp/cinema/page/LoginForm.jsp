<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/IncludeDefault.jsp" %>   
	
	<link rel="stylesheet" href="./style/login.css" />
	<script src="./js/login.js"></script> 
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
    <c:if test="${empty sessionScope.customer or empty sessionScope.customer.email}">
        <div class="member_login">
            <div class="login_tab">
                <div class="active">회원 로그인</div>
                <div class="">비회원 로그인 (예매확인)</div>
            </div>

            <div class="welcom">
                롯데시네마 회원이 되시면 회원만을 위한 다양한 서비스와 혜택을 받으실 수 있습니다.
            </div>

            <div class="login_form">
                <div class="login_box">
                    <div class="chkbox">
                        <label><input type="radio" name="chkbox" value="member" checked>회원</label>
                        <label><input type="radio" name="chkbox" value="n_member" disabled>비회원(예매확인)</label>
                    </div>

					<form action="login.do" method="post" id="login">
                    <div class="login_area">
                        <div>
                            <input type="text" name="email" maxlength="50" placeholder="이메일을 입력해 주세요." value="${email}">
                            <input type="password" name="password" maxlength="15" placeholder="비밀번호를 입력해 주세요."
                             onKeypress="javascript:if(event.keyCode==13) {document.getElementById('login').submit();}">
                        </div>
                        <div id="loginBtn">로그인</div>
                    </div>

                    <div class="save">
                        <input type="checkbox" name="saveEmail" ${saveEmailState}>
                        <label for="checkSavedID">이메일 저장</label>
                    </div>
                    </form>

                    <div class="click">
                        <div onclick="location.href='registration.do'">회원가입</div>
                        <div onclick="location.href='finder.do?type=email'">아이디 찾기</div>
                        <div onclick="location.href='finder.do?type=password'">비밀번호 찾기</div>
                    </div>
                </div>
            </div>
        </div>
	</c:if> 
		
    <c:if test="${!empty sessionScope.customer and !empty sessionScope.customer.email}">
			${sessionScope.customer.name}<br>
			<a style="color: #000" href="./logout.do">로그아웃</a>
			<c:if test="${sessionScope.customer.access == 0}">
			<br>
			<a style="color: #000" href="./cms/list.do">컨텐츠 관리 시스템</a><br>
			</c:if>
	</c:if>
    </div>
    <c:import url="/include/footer.do"/>
</body>
</html>