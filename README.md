# 개발환경
1. IDE: IntelliJ IDEA Community
2. Spring Boot 3.0.6
3. JDK 17
4. MySQL8Dialect
5. Spring Data JPA
6. Thymeleaf
7. lombok
8. Spring Web

# 회원 관리 목록 주요기능
1. 멤버 목록 쓰기(/member/save)


2. 멤버 로그인(/member/login)


3. 멤버 목록 조회(/member/{id})


4. 멤버 수정(/member/update)
    - 상세화면에서 수정 버튼 클릭
    - 서버에서 해당 회원 관리 정보를 가지고 수정 화면 출력
    - 제목, 내용 수정 입력 받아서 서버로 요청
    - 수정 처리


5. 멤버 삭제(/member/delete/{id})


6. 멤버 로그아웃(/member/logout)
