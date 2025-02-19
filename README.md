프로젝트 개요

이 프로젝트는 MVC 아키텍처를 기반으로 웹 애플리케이션을 구성하며, 다양한 기능을 담당하는 모듈들이 포함되어 있습니다.
 
 
 
 프로젝트 루트
├── 📂 src/main/java       # Java 소스 코드
│   ├── 📂 controller      # 각종 MVC 컨트롤러 모음
│   ├── 📂 filter          # 로그인 필터 관련 코드
│   ├── 📂 moa             # 스크래핑 페이지 제작 과정 포함
│   ├── 📂 phonebook       # 전화번호부 관련 코드 (페이지 설정, VO, DAO, 서비스)
│   ├── 📂 replyboard      # 게시판 관련 코드 (페이지 설정, VO, DAO, 서비스)
│   ├── 📜 Test.java       # 테스트용 Java 파일
│   ├── 📜 imsi.txt        # 임시 파일
├── 📂 webapp              # 웹 애플리케이션 관련 파일
│   ├── 📂 META-INF        # 메타데이터 정보
│   ├── 📂 WEB-INF         # AJAX 및 JavaScript를 이용한 사용자 인터페이스 파일
│   │   ├── 📂 board       # 게시판 관련 파일
│   │   ├── 📂 commit      # 커밋 관련 파일
│   │   ├── 📂 drool-html  # HTML 관련 파일
│   │   ├── 📂 home        # 홈 페이지 관련 파일
│   │   ├── 📂 lib         # 라이브러리 모음
│   │   ├── 📂 login       # 로그인 기능
│   │   ├── 📂 mail        # 이메일 관련 기능
│   │   ├── 📂 member      # 회원 관련 기능
│   │   ├── 📂 moa         # 스크래핑 관련 파일
│   │   ├── 📂 phonebook   # 전화번호부 관련 파일
│   │   ├── 📂 replyboard  # 게시판 관련 파일
│   │   ├── 📜 chat.jsp    # 채팅 관련 JSP 파일
│   │   ├── 📜 dispatcher-servlet.xml  # 서블릿 설정 파일
│   │   ├── 📜 gallery.jsp # 갤러리 관련 JSP 파일
│   │   ├── 📜 index.jsp   # 메인 페이지
│   │   ├── 📜 star.jsp    # 별점 관련 JSP 파일
│   │   ├── 📜 web.xml     # 웹 애플리케이션 설정 파일


MVC 패턴 기반 컨트롤러

로그인 필터 구현

웹 스크래핑 페이지 제작

전화번호부 관리 시스템

게시판 기능 지원

AJAX 및 JavaScript 활용한 동적 웹 페이지

🛠 기술 스택

Backend: Java, Spring (또는 해당하는 프레임워크 기재)

Frontend: HTML, CSS, JavaScript, AJAX

Database: MySQL (또는 사용 중인 DB 기재)
