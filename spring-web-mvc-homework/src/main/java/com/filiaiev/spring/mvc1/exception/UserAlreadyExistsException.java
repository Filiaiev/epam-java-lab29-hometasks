package com.filiaiev.spring.mvc1.exception;

import com.filiaiev.spring.mvc1.error.ErrorType;
import org.apache.tomcat.jni.Local;

import java.util.Locale;

public class UserAlreadyExistsException extends ServiceException {

    private static final String DEFAULT_MESSAGE_KEY =
            "exception.service.user.exists";

    public UserAlreadyExistsException() {
        super(DEFAULT_MESSAGE_KEY);
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Locale locale) {
        super(message, locale);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR;
    }
}
