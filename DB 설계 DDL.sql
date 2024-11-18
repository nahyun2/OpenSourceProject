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
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_activity_range CHECK (activity_range >= 0 AND activity_range <= 999.999) -- CHECK 제약 조건
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

-- 검색 기록을 저장하는 테이블
CREATE TABLE search_log (
    search_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL, -- 검색한 사용자 ID
    search_keyword VARCHAR(255) NOT NULL, -- 검색어
    searched_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 검색 시간
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- 인기 검색어를 계산하는 쿼리를 위한 뷰 생성
CREATE VIEW popular_search_keywords AS
SELECT 
    search_keyword,
    COUNT(*) AS search_count
FROM search_log
GROUP BY search_keyword
ORDER BY search_count DESC, search_keyword ASC;

