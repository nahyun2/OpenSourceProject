# OpenSourceProject
오픈소스개발프로젝트 수업 프로젝트 계정을 위한 깃허브 레퍼지토리이며 , 건강관리 웹사이트 제작 목표

## 구성원
홍희혁
남연서 
최나현
박조현
김규현

## 사용 언어
![React](https://img.shields.io/badge/-React-20232A?logo=react&logoColor=61DAFB&style=flat) ![Java](https://img.shields.io/badge/-Java-orange?logo=java&logoColor=white&style=flat) ![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?logo=spring-boot&logoColor=white&style=flat) ![Spring Framework](https://img.shields.io/badge/-Spring-6DB33F?logo=spring&logoColor=white&style=flat)

## 깃허브 운영 방식(수정될 수 있음)
1. master branch
2. develop branch

위 두가지로 구성된 branch로 운영하며, master branch에는 오류나 충돌이 없는 완전한 코드만 올리도록 합니다.
그러니 그 외에 개발중이거나 버그가 있는 등등의 코드들은 develop branch에서 관리하도록 합니다.
(master 브랜치는 직접 개개인이 push할 수 없도록 설정해둘 것이기에 pull request를 통해 master branch를 업데이트가 가능하게 할 것.

## 실습파일과 개발파일 구분 안내
수업에서 다루는 실습코드와 개인적인 공부한 내용들은 실습파일에 깔끔하게 정리해서 각자 이름으로된 폴더로 올려주세요.
추후 개발이 시작되면, 관련 코드파일은 전부 개발 파일에 올려주시면 됩니다.
(개발파일은 개발이 시작되면 생성하여 올려두도록 하겠습니다.)

<br/>

***

## 연동하는법

```
JAVA version: jdk 21

환경변수 설정: C:\Program Files\Java\jdk-21\bin
-> Path에 설정
```

1. [JAVA-21 버전 설치](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)

2. [MySQL 설치](https://dev.mysql.com/downloads/installer/)

3. [node.js 설치](https://nodejs.org/en)

4. VSC 익스텐션 설치:
```
MySQL 검색 후 - Weijan Chen의 'MySQL' 설치

SpringBoot 설치

필요시 ERD Editor 설치
```

### VSC에서 MySQL 초기 세팅

![image](https://github.com/user-attachments/assets/839530de-9a3b-43fd-8a10-436a60314729)

### MySQL 설정

```
1. 돌고래 모양 아이콘 옆에 '+' 버튼 누르면 쿼리 생성 창이 나옴
2. CREATE DATABASE board 입력
3. RUN
4. Query - '+' 버튼 이름 'DDL', 'DCL', 'DML' 3개 코드 파일 생성
5. 프로젝트 파일에서 'DDL.sql', 'DCL.sql', 'DML.sql' 코드 복붙
6. DDL, DCL.sql 에서만 쿼리문 각각 실행 - 오류 발생시 대면할 때 해결
```

### 프로젝트 실행 방법

```
1. BoardBackApplication.java 화면 켜놓기
2. vsc 터미널에서 'cd board-front' 입력
3. npm run start - 오류 발생 시 대면 때 해결
4. BoardBackApplication.java Run
5. npm run start시 홈페이지 자동으로 켜지므로 회원가입, 로그인 테스트
```

#### 나머지 오류 발생 시 대면 때 해결

