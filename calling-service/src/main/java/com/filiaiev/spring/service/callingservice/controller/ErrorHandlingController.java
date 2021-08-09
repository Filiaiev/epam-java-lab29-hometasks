package com.filiaiev.spring.service.callingservice.controller;

import com.filiaiev.spring.service.callingservice.error.ErrorInfo;
import com.filiaiev.spring.service.callingservice.error.ErrorType;
import com.filiaiev.spring.service.callingservice.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ErrorInfo handleServiceException(ServiceException ex) {
        log.error(ex.getClass().getSimpleName() + " invoked. {}", ex.getMessage(), ex);
        return new ErrorInfo(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorInfo> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .map(v -> new ErrorInfo(v.getDefaultMessage(),
                                        ErrorType.VALIDATION_ERROR,
                                        LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
