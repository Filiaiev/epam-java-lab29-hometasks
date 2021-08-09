package com.filiaiev.spring.service.callingservice.exception;


import com.filiaiev.spring.service.callingservice.error.ErrorType;

public class UserRegisterException extends ServiceException {

    private static final String DEFAULT_MESSAGE =
            "Couldn`t register user";

    public UserRegisterException() {
        super(DEFAULT_MESSAGE);
    }

    public UserRegisterException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.REGISTER_ERROR;
    }
}
