package com.filiaiev.spring.mvc1.exception;

import com.filiaiev.spring.mvc1.error.ErrorType;

public class UserAlreadyExistsException extends ServiceException {

    private static final String DEFAULT_MESSAGE =
            "User with such email or login already exists";

    public UserAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR;
    }
}
