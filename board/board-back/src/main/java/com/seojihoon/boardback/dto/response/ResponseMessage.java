package com.seojihoon.boardback.dto.response;

public interface ResponseMessage {
    
    String SUCCESS = "Success.";

    String VALIDATION_FAILED = "Validation failed.";
    String DUPLICATED_EMAIL = "Duplicate email.";
    String DUPLICATED_NICKNAME = "Duplicate nickname.";
    String DUPLICATED_TEL_NUMBER = "Duplicate telephone number.";
    String NOT_EXIST_USER = "This user does not exist.";
    String NOT_EXIST_BOARD = "This board does not exist.";
    String NOT_EXIST_COMMENT = "This comment does not exist.";

    String SIGN_IN_FAILED = "Login information mismatch.";

    String NO_PERMISSION = "Do not have permission.";

    String DATABASE_ERROR = "Database error.";
    String NOT_ALLOW_NESTED_REPLY = "Nested replies are not allowed.";

    String NOT_EXIST_PARENT_COMMENT = "Parent comment does not exist.";

    String INVALID_TEAM_URL = "Team URL is required for team board.";
}
