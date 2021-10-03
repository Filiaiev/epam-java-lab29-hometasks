package com.filiaiev.spring.mvc1.validation;

import com.filiaiev.spring.mvc1.dto.user.UserRegisterDto;
import com.filiaiev.spring.mvc1.validation.constraint.PasswordMatcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegisterPasswordsShouldMatchValidator
        implements ConstraintValidator<PasswordMatcher, UserRegisterDto> {

    @Override
    public boolean isValid(UserRegisterDto dto, ConstraintValidatorContext constraintValidatorContext) {
        return dto.getPassword().equals(dto.getRepeatedPassword());
    }
}
