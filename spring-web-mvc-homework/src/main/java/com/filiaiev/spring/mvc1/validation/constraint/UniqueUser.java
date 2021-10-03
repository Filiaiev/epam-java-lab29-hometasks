package com.filiaiev.spring.mvc1.validation.constraint;

import com.filiaiev.spring.mvc1.validation.UniqueUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUserValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUser {
    String message() default "User with such login or email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
