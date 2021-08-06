package com.filiaiev.spring.mvc1.exception;

import com.filiaiev.spring.mvc1.error.ErrorType;

public class UserNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE =
            "User not found";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR;
    }
}
