package com.filiaiev.spring.mvc1.validation.constraint;

import com.filiaiev.spring.mvc1.validation.RegisterPasswordsShouldMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RegisterPasswordsShouldMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatcher {
    String message() default "Passwords should match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
