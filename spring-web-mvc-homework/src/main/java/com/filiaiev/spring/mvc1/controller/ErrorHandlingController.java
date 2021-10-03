package com.filiaiev.spring.mvc1.controller;

import com.filiaiev.spring.mvc1.error.ErrorInfo;
import com.filiaiev.spring.mvc1.error.ErrorType;
import com.filiaiev.spring.mvc1.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorInfo handleException(Exception ex) {
        log.error(ex.getClass().getSimpleName() + " invoked. {}", ex.getMessage(), ex);
        return new ErrorInfo(ex.getMessage(), ErrorType.FATAL_ERROR, LocalDateTime.now());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ErrorInfo handleServiceException(ServiceException ex) {
        log.error(ex.getClass().getSimpleName() + " invoked. {}", ex.getMessage(), ex);
        return new ErrorInfo(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorInfo> handleMethodArgumentExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .map(v -> new ErrorInfo(v.getDefaultMessage(),
                                        ErrorType.VALIDATION_ERROR,
                                        LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorInfo handleConstraintException(ConstraintViolationException ex) {
        log.error("{} invoked {} ", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        return new ErrorInfo(ex.getMessage(), ErrorType.VALIDATION_ERROR, LocalDateTime.now());
    }
}
