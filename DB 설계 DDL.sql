-- 게시물 테이블 (최대 참가 인원 추가)
CREATE TABLE boards (
  board_number   INT         NOT NULL AUTO_INCREMENT COMMENT '게시물 번호',
  title          TEXT        NOT NULL COMMENT '게시물 제목',
  content        TEXT        NOT NULL COMMENT '게시물 내용',
  write_datetime DATETIME    NOT NULL COMMENT '게시물 작성 날짜 및 시간',
  comment_count  INT         NOT NULL DEFAULT 0 COMMENT '게시물 댓글 수',
  view_count     INT         NOT NULL DEFAULT 0 COMMENT '게시물 조회 수',
  max_participants INT       NOT NULL DEFAULT 10 COMMENT '최대 참가 인원',
  current_participants INT   NOT NULL DEFAULT 0 COMMENT '현재 참가 인원',
  writer_email   VARCHAR(50) NOT NULL COMMENT '작성자 이메일',
  PRIMARY KEY (board_number),
  FOREIGN KEY (writer_email) REFERENCES users(email) ON DELETE CASCADE
) COMMENT '게시물 테이블';

-- 댓글 테이블
CREATE TABLE comments (
  comment_number INT         NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
  content        TEXT        NOT NULL COMMENT '댓글 내용',
  write_datetime DATETIME    NOT NULL COMMENT '댓글 작성 날짜 및 시간',
  user_email     VARCHAR(50) NOT NULL COMMENT '작성자 이메일',
  board_number   INT         NOT NULL COMMENT '게시물 번호',
  PRIMARY KEY (comment_number)
) COMMENT '댓글 테이블';

-- 게시물 이미지 테이블
CREATE TABLE images (
  sequence       INT         NOT NULL AUTO_INCREMENT COMMENT '이미지 번호',
  board_number   INT         NOT NULL COMMENT '게시물 번호',
  image          TEXT        NOT NULL COMMENT '게시물 이미지 URL',
  PRIMARY KEY (sequence)
) COMMENT '게시물 이미지 테이블';

-- 검색 기록 테이블
CREATE TABLE search_logs (
  sequence        INT         NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  search_word     TEXT        NOT NULL COMMENT '검색어',
  relation_word   TEXT        NULL     COMMENT '관련 검색어',
  relation        BOOLEAN     NOT NULL COMMENT '관련 검색어 여부',
  PRIMARY KEY (sequence)
) COMMENT '검색 기록 테이블';

-- 사용자 테이블
CREATE TABLE users (
  email           VARCHAR(50)  NOT NULL COMMENT '사용자 이메일',
  password        VARCHAR(100) NOT NULL COMMENT '사용자 비밀번호',
  nickname        VARCHAR(20)  NOT NULL UNIQUE COMMENT '사용자 닉네임',
  tell_number     VARCHAR(15)  NOT NULL UNIQUE COMMENT '사용자 휴대전화번호',
  address         TEXT         NOT NULL COMMENT '사용자 주소',
  address_detail  TEXT         NULL     COMMENT '사용자 상세 주소',
  profile_image   TEXT         NULL     COMMENT '사용자 프로필 사진 URL',
  PRIMARY KEY (email)
) COMMENT '사용자 테이블';

-- 게시글 조회 내역 테이블
CREATE TABLE board_views (
  view_id      INT AUTO_INCREMENT PRIMARY KEY COMMENT '조회 ID',
  user_email   VARCHAR(50) NOT NULL COMMENT '조회한 사용자 이메일',
  board_number INT         NOT NULL COMMENT '조회된 게시물 번호',
  viewed_at    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '조회 시간',
  UNIQUE(user_email, board_number), -- 중복 조회 방지
  FOREIGN KEY (user_email) REFERENCES user(email) ON DELETE CASCADE,
  FOREIGN KEY (board_number) REFERENCES board(board_number) ON DELETE CASCADE
) COMMENT '게시글 조회 내역 테이블';

-- 게시물 리스트 뷰 생성
CREATE VIEW board_views AS 
SELECT 
    B.board_number AS board_number,
    B.title AS title,
    B.content AS content,
    I.image AS title_image,
    B.comment_count AS comment_count,
    B.view_count AS view_count,
    B.write_datetime AS write_datetime,
    U.email AS writer_email,
    U.nickname AS writer_nickname,
    U.profile_image AS writer_profile_image
FROM board AS B
INNER JOIN user AS U
ON B.writer_email = U.email
LEFT JOIN (
    SELECT board_number, ANY_VALUE(image) AS image
    FROM image
    GROUP BY board_number
) AS I 
ON B.board_number = I.board_number;

-- 인기 검색어 계산을 위한 뷰 생성
CREATE VIEW popular_search_keywords AS
SELECT 
    search_word AS search_keyword,
    COUNT(*) AS search_count
FROM search_log
GROUP BY search_word
ORDER BY search_count DESC, search_word ASC;

-- 참가자 관리 테이블
CREATE TABLE group_participants (
  participant_id   INT AUTO_INCREMENT PRIMARY KEY COMMENT '참가 요청 ID',
  board_number     INT         NOT NULL COMMENT '게시물 번호',
  user_email       VARCHAR(50) NOT NULL COMMENT '참가자 이메일',
  request_status   ENUM('PENDING', 'ACCEPTED', 'REJECTED') DEFAULT 'PENDING' COMMENT '참가 요청 상태',
  requested_at     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '참가 요청 시간',
  FOREIGN KEY (board_number) REFERENCES boards(board_number) ON DELETE CASCADE,
  FOREIGN KEY (user_email) REFERENCES users(email) ON DELETE CASCADE,
  UNIQUE(board_number, user_email) -- 동일 게시물에 중복 참가 요청 방지
) COMMENT '운동 그룹 참가자 관리 테이블';

-- 관계 설정
ALTER TABLE images
  ADD CONSTRAINT FK_board_TO_image
    FOREIGN KEY (board_number)
    REFERENCES board (board_number);

ALTER TABLE boards
  ADD CONSTRAINT FK_user_TO_board
    FOREIGN KEY (writer_email)
    REFERENCES user (email);

ALTER TABLE comments
  ADD CONSTRAINT FK_user_TO_comment
    FOREIGN KEY (user_email)
    REFERENCES user (email);

ALTER TABLE comments
  ADD CONSTRAINT FK_board_TO_comment
    FOREIGN KEY (board_number)
    REFERENCES board (board_number);


