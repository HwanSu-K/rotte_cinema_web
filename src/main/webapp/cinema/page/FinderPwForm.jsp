<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../include/IncludeDefault.jsp"%>

<link rel="stylesheet" href="./style/finder.css" />
<script src="./js/finder.js"></script>
</head>
<body>
	<c:import url="/include/header.do" />
	<section class="location">
		<!-- background-color: #f8f8fa; -->
		<div>
			<span><i class="fas fa-home"></i></span> <span>></span> <span>로그인</span>
			<span>></span> <span>아이디/비밀번호 찾기</span>
		</div>
	</section>
	<div class="content">
		<div class="member_reg">
			<div class="reg_tab">
				<div class="">아이디 찾기</div>
				<div class="active">비밀번호 찾기</div>
			</div>
			<div class="reg_info">
				<div>기본 입력</div>
				<div>
					<span>*</span>
					<div>표시 필수 입력사항</div>
				</div>
			</div>
			<section id="formReg">
				<form id="reg" autocomplete=”off”>
					<div>
						<label for="email"><span>*</span>이메일</label> <input type="text"
							id="email" name="email" maxlength="15"> <span>@</span> <input
							type="text" id="emailAddr" name="email" maxlength="15">
					</div>
					<div>
						<label for="name"><span>*</span>이름</label><input type="text"
							id="name" name="name" maxlength="5">
					</div>
					<div>
						<label for="birth"><span>*</span>생년월일</label> <select id="year"
							name="year">
							<option value="">년</option>
						</select> <select id="month" name="month">
							<option value="">월</option>
						</select> <select id="day" name="day">
							<option value="">일</option>
						</select>
					</div>
					<div>
						<label for="phonenum"><span>*</span>휴대전화</label> <select
							id="p_top" name="p_top">
							<option value="">선택</option>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="017">017</option>
						</select> <input type="text" id="p_middle" name="p_middle" maxlength="4">
						<input type="text" id="p_bottom" name="p_middle" maxlength="4">
					</div>
				</form>
			</section>
			<div class="reg_btn">
				<div id="btnPass">비밀번호 찾기</div>
			</div>

			<section id="formFinder">
				<div>
					<p>email@kumas.dev</p>
					로 이메일이 발송되었습니다.
				</div>
			</section>
		</div>
	</div>
	<c:import url="/include/footer.do" />
</body>
</html>