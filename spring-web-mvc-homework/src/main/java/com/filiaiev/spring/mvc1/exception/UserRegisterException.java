package com.filiaiev.spring.mvc1.exception;

import com.filiaiev.spring.mvc1.error.ErrorType;

import java.util.Locale;

public class UserRegisterException extends ServiceException {

    private static final String DEFAULT_MESSAGE_KEY =
            "exception.service.user.register.default";

    public UserRegisterException() {
        super(DEFAULT_MESSAGE_KEY);
    }

    public UserRegisterException(String message) {
        super(message);
    }

    public UserRegisterException(String message, Locale locale) {
        super(message, locale);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.REGISTER_ERROR;
    }
}
