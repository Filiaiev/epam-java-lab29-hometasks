package com.filiaiev.spring.mvc1.exception.order;

import com.filiaiev.spring.mvc1.error.ErrorType;
import com.filiaiev.spring.mvc1.exception.ServiceException;

import java.util.Locale;

public class OrderUpdateException extends ServiceException {

    private static final String DEFAULT_MESSAGE_KEY =
            "update.order.fail.default";

    public OrderUpdateException() {
        super(DEFAULT_MESSAGE_KEY);
    }

    public OrderUpdateException(String message) {
        super(message);
    }

    public OrderUpdateException(String message, Locale locale) {
        super(message, locale);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR;
    }
}
