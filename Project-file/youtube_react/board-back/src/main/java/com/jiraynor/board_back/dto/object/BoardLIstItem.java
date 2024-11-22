package com.jiraynor.board_back.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor 
@AllArgsConstructor
public class BoardLIstItem {
    private int boardNumber;
    private String title;
    private String content;
    private String boardTItleImage;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String writeDatetiem;
    private String writerNickname;
    private String writerProfileImage;
}
