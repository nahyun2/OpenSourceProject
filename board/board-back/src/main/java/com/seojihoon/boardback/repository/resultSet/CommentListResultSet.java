package com.seojihoon.boardback.repository.resultSet;

public interface CommentListResultSet {
    Integer getCommentNumber();
    String getNickname();
    String getProfileImage();
    String getContent();
    String getWriteDatetime();
    Integer getParentCommentNumber();
}
