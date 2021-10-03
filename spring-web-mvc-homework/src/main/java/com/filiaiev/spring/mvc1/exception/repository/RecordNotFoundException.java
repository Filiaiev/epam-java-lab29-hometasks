package com.filiaiev.spring.mvc1.exception.repository;

import com.filiaiev.spring.mvc1.error.ErrorType;
import com.filiaiev.spring.mvc1.exception.ServiceException;

import java.util.Locale;

public class RecordNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE =
            "repository.read.notfound.default";

    public RecordNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public RecordNotFoundException(String messageKey) {
        super(messageKey);
    }

    public RecordNotFoundException(String messageKey, Locale locale) {
        super(messageKey, locale);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATA_READ_ERROR;
    }
}
