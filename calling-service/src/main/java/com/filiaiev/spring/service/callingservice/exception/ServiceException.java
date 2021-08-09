package com.filiaiev.spring.service.callingservice.exception;

import com.filiaiev.spring.service.callingservice.error.ErrorType;

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
