package com.filiaiev.spring.mvc1.validation;

import com.filiaiev.spring.mvc1.dto.user.UserRegisterDto;
import com.filiaiev.spring.mvc1.exception.UserRegisterException;
import com.filiaiev.spring.mvc1.validation.annotation.UserPasswordConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserPasswordValidator
        implements ConstraintValidator<UserPasswordConstraint, UserRegisterDto> {

    @Override
    public boolean isValid(UserRegisterDto dto, ConstraintValidatorContext constraintValidatorContext) {
        if(!dto.getPassword().equals(dto.getRepeatedPassword()))
            throw new UserRegisterException("exception.service.user.register.fail.passwords.match");
        return true;
    }
}
