package com.mss.fashion.presentation.dto;

import com.mss.fashion.common.Errors;
import lombok.Builder;
import lombok.Getter;

public class CommonResponse {
    private Meta meta;

    private CommonResponse(final Errors error) {
        this.meta = Meta.builder()
                .message(error.getMessage())
                .build();
    }
    private CommonResponse(final String message) {
        this.meta = Meta.builder()
                .message(message)
                .build();
    }

    public static CommonResponse of(final Errors error) {
        return new CommonResponse(error);
    }
    public static CommonResponse of(String message) {
        return new CommonResponse(message);
    }

    @Builder
    @Getter
    public static class Meta {
        private final String message;
    }

}
