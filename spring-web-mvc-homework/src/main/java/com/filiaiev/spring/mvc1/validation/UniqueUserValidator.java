package com.filiaiev.spring.mvc1.validation;

import com.filiaiev.spring.mvc1.dto.user.UserRegisterDto;
import com.filiaiev.spring.mvc1.exception.UserAlreadyExistsException;
import com.filiaiev.spring.mvc1.repository.UserRepository;
import com.filiaiev.spring.mvc1.validation.annotation.UniqueUserContraint;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUserValidator
        implements ConstraintValidator<UniqueUserContraint, UserRegisterDto> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(UserRegisterDto o, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository.existsByEmailOrLogin(o.getEmail(), o.getLogin()))
            throw new UserAlreadyExistsException();
        return true;
    }
}
