package com.ehealth.as.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(RSServiceClientException.class)
    public ResponseEntity<ErrorData> exceptionHandle(Exception exception) {
        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, "AS service exception. " + exception.getMessage());
    }

    @ExceptionHandler({
            JsonProcessingException.class,
            IllegalArgumentException.class,
            RSServiceServerException.class,
            Exception.class
    })
    public ResponseEntity<ErrorData> exceptionServerHandle(Exception exception) {
        return getErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "AS service exception. " + exception.getMessage());
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
