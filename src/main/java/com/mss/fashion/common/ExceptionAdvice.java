package com.mss.fashion.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handleClientException(ClientException ex, HttpServletRequest request) {
        // 예외 메시지와 상태 코드를 반환
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(ex.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(ClientException ex, HttpServletRequest request) {
        // 예외 메시지와 상태 코드를 반환
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(ex.getStatus()));
    }
}
