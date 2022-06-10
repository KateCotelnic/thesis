package com.ehealth.ms.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class ErrorHandler {

    @ExceptionHandler({
            RSServiceClientException.class,
            ASServiceClientException.class})
    public ResponseEntity<ErrorData> exceptionHandle(Exception exception) {
        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ErrorData> AuthenticationExceptionHandler() {
        SecurityContextHolder.clearContext();
        return getErrorResponseEntity(HttpStatus.UNAUTHORIZED, "Jwt is invalid");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ErrorData> BadCredentialsException(Exception exception) {
        return getErrorResponseEntity(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler({
            JsonProcessingException.class,
            IllegalArgumentException.class,
            RSServiceServerException.class,
            ASServiceServerException.class,
            Exception.class
    })
    public ResponseEntity<ErrorData> exceptionServerHandle(Exception exception) {
        return getErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
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
