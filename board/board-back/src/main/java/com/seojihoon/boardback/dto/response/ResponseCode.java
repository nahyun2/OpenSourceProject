package com.seojihoon.boardback.dto.response;

public interface ResponseCode {

    String SUCCESS = "SU";

    String VALIDATION_FAILED = "VF";
    String DUPLICATED_EMAIL = "DE";
    String DUPLICATED_NICKNAME = "DN";
    String DUPLICATED_TEL_NUMBER = "DT";
    String NOT_EXIST_USER = "NU";
    String NOT_EXIST_BOARD = "NB";

    String SIGN_IN_FAILED = "SF";

    String NO_PERMISSION = "NP";

    String DATABASE_ERROR = "DBE";
    String NOT_ALLOW_NESTED_REPLY = "NNR";
    String NOT_EXIST_COMMENT = "NC";
    String NOT_EXIST_PARENT_COMMENT = "NPC";

    String INVALID_TEAM_URL = "ITU";
}
