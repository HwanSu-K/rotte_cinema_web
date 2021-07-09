<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../include/IncludeDefault.jsp"%>

	<link rel="stylesheet" href="./style/password.css" />
	<script src="./js/password.js"></script>
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
				<div class="active">비밀번호 변경</div>
				<div class=""></div>
			</div>
			<div class="reg_info">
				<div>기본 입력</div>
				<div>
					<span>*</span>
					<div>표시 필수 입력사항</div>
				</div>
			</div>
			<section id="formReg">
				<form id="reg">
					<div>
						<label for=password><span>*</span>비밀번호</label> <input
							type="password" id="password" name="name" maxlength="20">
						<div id="passAlert" class="disabled" data-tooltip-text="">
							<i class="fas fa-check"></i>
						</div>
					</div>
					<div>
						<label for="passwordcheck"><span>*</span>비밀번호확인</label> <input
							type="password" id="passwordcheck" name="name" maxlength="20">
					</div>
				</form>
			</section>
			<div class="reg_btn">
				<div id="btn_reg">비밀번호 변경</div>
			</div>
		</div>
	</div>
	<c:import url="/include/footer.do" />
</body>
</html>