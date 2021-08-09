package com.filiaiev.spring.service.callingservice.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorInfo {

    private String message;
    private ErrorType errorType;
    private LocalDateTime errorTime;
}
