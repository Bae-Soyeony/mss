package com.mss.fashion.common;

import lombok.Getter;

@Getter
public enum Errors {
    BRAND_ALREADY_EXISTS(400, "이미 존재하는 브랜드입니다."),
    BRAND_NOT_FOUND(400, "브랜드를 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(400, "카테고리를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류"),

    ;
    private final int status;
    private final String message;

    Errors(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
