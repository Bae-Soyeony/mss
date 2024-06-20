package com.mss.fashion.common;

import lombok.Getter;

public class ClientException extends RuntimeException{
    @Getter
    private final Errors error;

    public ClientException(Errors error) {
        super(error.getMessage());
        this.error = error;
    }
    public int getStatus() {
        return error.getStatus();
    }
}
