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
            <span>></span>
            <span>회원가입</span>
        </div>
    </section>
    <div class="content">
        <div class="member_reg">
            <div class="reg_tab">
                <div class="active">필수 정보</div>
                <div class="">가입 완료</div>
            </div>
            <div class="reg_info">
                <div>
                    기본 입력
                </div>
                <div>
                    <span>*</span>
                    <div>표시 필수 입력사항</div>
                </div>
            </div>
            <section id="formReg" class="active">
				<form id="reg">
                    <div>
                        <label for="email"><span>*</span>이메일</label>
                        <input type="text" id="email" name="email" maxlength="15">
                        <span>@</span>
                        <input type="text" id="emailAddr" name="email" maxlength="15">
                        <select id='emailSelect' name='state'>
                            <option value='default' >직접입력</option>
                            <option value='naver.com'>네이버</option>
                            <option value='hanmail.net'>다음</option>
                            <option value='gmail.com'>구글</option>
                            <option value='kakao.com'>카카오</option>
                        </select>
                        <div id="emailAlert" class="disabled" data-tooltip-text=""><i class="fas fa-check"></i></div>
                    </div>
                    <div>
                        <label for=password><span>*</span>비밀번호</label>
                        <input type="password" id="password" name="name" maxlength="20">    
                        <div id="passAlert" class="disabled" data-tooltip-text=""><i class="fas fa-check"></i></div>
                    </div>
                    <div>
                        <label for="passwordcheck"><span>*</span>비밀번호확인</label>
                        <input type="password" id="passwordcheck" name="name" maxlength="20">
                    </div>
                    <div><label for="name"><span>*</span>이름</label><input type="text" id="name" name="name" maxlength="5"></div>
                    <div>
                        <label for="birth"><span>*</span>생년월일</label>
                        <select id='year' name='year'>
                            <option value=''>년</option>
                        </select>
                        <select id='month' name='month'>
                            <option value=''>월</option>
                        </select>
                        <select id='day' name='day'>
                            <option value=''>일</option>
                        </select>
                    </div>
                    <div>
                        <label for="phonenum"><span>*</span>휴대전화</label>
                        <select id='p_top' name='p_top'>
                        	<option value=''>선택</option>
                            <option value='010'>010</option>
                            <option value='011'>011</option>
                            <option value='017'>017</option>
                        </select>
                        <input type="text" id="p_middle" name="p_middle" maxlength="4">
                        <input type="text" id="p_bottom" name="p_middle" maxlength="4">
                    </div>    
                    <div>
                        <label for="zipcode"></label>
                        <input type="text" id="zipcode" name="zipcode" maxlength="6" disabled>                        
                        <div class="btn" onclick="sample2_execDaumPostcode()">주소찾기</div>
                    </div>                
                    <div>
                    	<label for="address" onclick="sample2_execDaumPostcode()"><span>*</span>주소</label>
                        <input type="text" id="address" name="addr" maxlength="20" disabled>                        
                    </div>
                    <div>
                        <label for="detailAddress"></label>
                        <input type="text" id="detailAddress" name="addr" maxlength="20">                        
                    </div>
                </form>
                <div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
						<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
					</div>
            </section>
            
            <section id="formcomp">
                <div></div><div>님 가입을 환영합니다.</div>
            </section>

            <div class="reg_btn"><div id="btn_cancel" class="g">취소</div><div id="btn_reg">가입</div></div>
            <div class="reg_alert" OnClick="location.href ='login.do'"><div>확인</div></div>
        </div>
    </div>
    <c:import url="/include/footer.do"/>
</body>
<script>
// 우편번호 찾기 화면을 넣을 element
var element_layer = document.getElementById('layer');

function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_layer.style.display = 'none';
}

function sample2_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {        	
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr =  data.roadAddress; // 주소 변수
            var extraAddr = ''; // 참고항목 변수            

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
            
            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_layer.style.display = 'none';
        },
        width : '100%',
        height : '100%',
        maxSuggestItems : 5
    }).embed(element_layer);

    // iframe을 넣은 element를 보이게 한다.
    element_layer.style.display = 'block';

    // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
    initLayerPosition();
}

// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
// 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
function initLayerPosition(){
    var width = 600; //우편번호서비스가 들어갈 element의 width
    var height = 400; //우편번호서비스가 들어갈 element의 height
    var borderWidth = 5; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    element_layer.style.width = width + 'px';
    element_layer.style.height = height + 'px';
    element_layer.style.border = borderWidth + 'px solid';
    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
    element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
    element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
}
</script>
</html>