package com.filiaiev.spring.mvc1.exception.order;

import com.filiaiev.spring.mvc1.error.ErrorType;
import com.filiaiev.spring.mvc1.exception.ServiceException;

public class OrderNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE =
            "Order that was requested not found";

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return super.getErrorType();
    }
}
