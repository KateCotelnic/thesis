package com.ehealth.ms.exceptions;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorData {
    private LocalDateTime timeStamp;
    private String message;
    private HttpStatus status;
}