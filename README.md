![- 메인 화면](https://user-images.githubusercontent.com/80761447/141538846-65971a25-5aa3-4f94-b18e-ec37c2642e03.png)

[YouTube](https://youtu.be/I4QrISJO7Tg)

## 소개

- 영진직업전문학교 송암전 대상 작품입니다.
- 영화관 플랫폼을 운영한다면 필요한 부분은 무엇일까 생각하며 제작하였습니다.

## 도구 및 기술

- `jsp` `spring` `mybatis` `jstl` `ajax` `javascript` `jquery`  `mysql`

## 담당 역할

- 프론트엔드, 백엔드, DB설계

## 작업 내용

- vultr에서 리눅스 호스팅을 받아 작업하였습니다.
- apache2+tomcat9을 이용하여 배포하였으며 https를 적용하였습니다.
- 배너, 영화, 지역, 극장 등을 모두 콘텐츠 관리자 페이지에서 등록, 수정이 가능하도록 작성하였습니다.
- 영화 목록은 crontab 이용하여 매일 0시에 더미 데이터를 생성하도록 하였습니다.
- 댓글, 좋아요 등 비동기적 호출이 필요한 경우 jquery ajax를 사용하여 호출하였습니다.
- spring, jstl, mybatis, mysql 을 이용하여 작성하였습니다.
- GPS를 이용하여 내주변 극장을 표시합니다.
- spring 스케쥴러를 이용한 안드로이드 푸시알림을 구현했습니다.

## 구현

### 스토리 보드

- Adobe XD, PPT

*작업 이전 스토리보드 작성으로 계획적으로 레이아웃을 구성할 수 있도록 설계*

*대략적인 레이아웃 파악으로 실제 작업시간 단축*
<div style="diplay:flex">
<img src="https://user-images.githubusercontent.com/80761447/141539867-803acf8f-6a45-4bd8-b415-3ceb991471a2.png" width="30%" />
<img src="https://user-images.githubusercontent.com/80761447/141539873-e1759c55-0e00-4e7e-ba01-cb47d7c11c48.png" width="30%" />
<img src="https://user-images.githubusercontent.com/80761447/141539895-5c9f4022-f08a-42cb-b664-6ee7ea52eeb1.png" width="30%" />
</div>
  

  
### ERD

- MySQL

*데이터 베이스의 중복을 최소화하고, 유지 보수를 위해 외래 키를 이용하여 관계 설정*

*자주 사용하는 컬럼에 대해 색인 설정*
<img src="https://user-images.githubusercontent.com/80761447/141538997-babe1476-4978-4f9d-bf64-3e2519e56f9b.png" width="90%" />


## 배포

- Apache2 + Tomcat9

*리눅스 서버 호스팅 WEB, WAS Database, HTTPS*

<img src="https://user-images.githubusercontent.com/80761447/141540297-0a917f92-d8ae-4a00-b51b-446b5485f778.png" width="90%" />


 

## 시행착오 및 해결

- **영화 목록을 받아올 때 목록을 누르는 경우 화면이 새로 고침 되며 스크롤이 제일 상단으로 올라가는 문제 발생.**
*자바스크립트를 이용하여 현재 스크롤 위치를 저장한 뒤 새로 고침 하려 했으나 근본적인 해결책은 되지 않아 여러 사이트들을 참고한 결과 ajax라는 기술을 알게 되어 처리할 수 있었음.*

- **javascrip에서 예약좌석 객체를 배열에 담아 spring으로 전달하였으나 수신 받을 수 없는 문제.**
*spring에서 파라미터를 자동으로 객체로 묶어서 받아주는 부분 때문에 객체를 받을 수 있다고 착각한 문제. 애초에 파라미터는 string 타입으로 밖에 보낼 수 없다는 걸 알게 되어서, 객체를 json 타입으로 변환한 뒤 직렬화하여 spring으로 전송. net.sf.json 라이브러리를 이용하여 직렬화된 string 변수를 JSONArray 타입으로 변환하여 처리.*

- **이미지 업로드 시 파일명만 업로드되는 현상.**
*파라미터 타입에 MultipartHttpServletRequest 할당 시에 오류 발생. 원인 확인 결과 bean 파일에 CommonsMultipartResolver 변수 생성 필요.*

- **이미지를 내부 경로에 업로드 시 용량이 계속 증가하여 배포에 문제가 발생.**
*외부 경로에 업로드할 수 있도록 설정하였으나, 로컬 환경과 서버 환경에서 서로 다른 경로를 지정해야 하기 때문에 톰캣에 매개변수를 추가. 환경에 따라 서로 다른 properties 파일을 읽게 하여 경로를 설정. 이미지는 톰캣의 Context 경로를 설정 서버 환경과 로컬 환경에서 완벽하게 작동할 수 있도록 설정*
