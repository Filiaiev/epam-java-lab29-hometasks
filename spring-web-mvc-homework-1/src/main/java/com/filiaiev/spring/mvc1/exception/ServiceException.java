package com.filiaiev.spring.mvc1.exception;

import com.filiaiev.spring.mvc1.error.ErrorType;

public abstract class ServiceException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Service exception";

    public ServiceException() {
        super(DEFAULT_MESSAGE);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ErrorType getErrorType() {
        return ErrorType.FATAL_ERROR;
    }
}
