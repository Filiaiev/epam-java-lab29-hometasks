package com.filiaiev.spring.mvc1.exception;

import com.filiaiev.spring.mvc1.error.ErrorType;

import java.util.Locale;

public class UserNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE_KEY =
            "exception.service.user.notFound";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE_KEY);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Locale locale) {
        super(message, locale);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR;
    }
}
