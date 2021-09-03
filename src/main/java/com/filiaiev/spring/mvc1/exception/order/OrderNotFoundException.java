package com.filiaiev.spring.mvc1.exception.order;

import com.filiaiev.spring.mvc1.error.ErrorType;
import com.filiaiev.spring.mvc1.exception.ServiceException;

import java.util.Locale;

public class OrderNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE_KEY =
            "exception.service.order.notFound";

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE_KEY);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Locale locale) {
        super(message, locale);
    }

    @Override
    public ErrorType getErrorType() {
        return super.getErrorType();
    }
}
