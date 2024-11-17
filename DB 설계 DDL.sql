-- 사용자 정보를 저장하는 테이블
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15),
    address VARCHAR(255),
    detail_address VARCHAR(255),
    activity_range DECIMAL(6, 3) DEFAULT 0.000, -- 활동 반경, 최대 999.999
    profile_picture_url VARCHAR(500),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 게시글 정보를 저장하는 테이블
CREATE TABLE posts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL, -- 작성자 ID
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    view_count INT DEFAULT 0, -- 단순 조회수 관리
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- 게시글 조회 내역을 저장하는 테이블
CREATE TABLE post_views (
    view_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    viewed_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, post_id), -- 동일 사용자의 중복 조회 방지
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts(post_id) ON DELETE CASCADE
);

-- 조회 내역이 추가될 때 게시글의 조회수를 증가시키는 트리거
DELIMITER $$

CREATE TRIGGER update_post_view_count
AFTER INSERT ON post_views
FOR EACH ROW
BEGIN
    UPDATE posts
    SET view_count = view_count + 1
    WHERE post_id = NEW.post_id;
END$$

DELIMITER ;

-- 외래 키 추가 명령어
ALTER TABLE posts
  ADD CONSTRAINT FK_user_TO_posts
    FOREIGN KEY (user_id)
    REFERENCES users (user_id);

ALTER TABLE post_views
  ADD CONSTRAINT FK_user_TO_post_views
    FOREIGN KEY (user_id)
    REFERENCES users (user_id),
  ADD CONSTRAINT FK_post_TO_post_views
    FOREIGN KEY (post_id)
    REFERENCES posts (post_id);

-- 추가 인덱스 설정
CREATE INDEX idx_user_email ON users (email);
CREATE INDEX idx_post_user ON posts (user_id);
CREATE INDEX idx_post_views_user_post ON post_views (user_id, post_id);

-- CHECK 제약 조건 추가
ALTER TABLE users
  ADD CONSTRAINT chk_activity_range CHECK (activity_range >= 0 AND activity_range <= 999.999);
