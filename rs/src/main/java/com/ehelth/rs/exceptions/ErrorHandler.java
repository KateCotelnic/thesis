package com.ehelth.rs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorData> exceptionHandle(Exception exception) {
        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, "RS service exception. " + exception.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class, Exception.class})
    public ResponseEntity<ErrorData> allExceptionHandle(Exception exception) {
        return getErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "RS service exception. " + exception.getMessage());
    }

    private ResponseEntity<ErrorData> getErrorResponseEntity(HttpStatus code, String message) {
        return new ResponseEntity<>(ErrorData.builder()
                .message(message)
                .status(code)
                .timeStamp(LocalDateTime.now())
                .build(),
                code);
    }
}