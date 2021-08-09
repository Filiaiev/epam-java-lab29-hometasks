package com.filiaiev.spring.mvc1.exception;

import com.filiaiev.spring.mvc1.error.ErrorType;
import com.filiaiev.spring.mvc1.util.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public abstract class ServiceException extends RuntimeException {

    private static final String DEFAULT_MESSAGE_KEY =
            "exception.service.default";

    public ServiceException() {
        this(DEFAULT_MESSAGE_KEY);
    }

    public ServiceException(String messageKey) {
        super(Messages.getMessage(
                messageKey, LocaleContextHolder.getLocale()));
    }

    public ServiceException(String messageKey, Locale locale) {
        super(Messages.getMessage(
                messageKey, locale));
    }

    public ErrorType getErrorType() {
        return ErrorType.FATAL_ERROR;
    }
}
