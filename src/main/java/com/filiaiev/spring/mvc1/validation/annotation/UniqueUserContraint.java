package com.filiaiev.spring.mvc1.validation.annotation;

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
public @interface UniqueUserContraint {
    String message() default "User with such login or email exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
