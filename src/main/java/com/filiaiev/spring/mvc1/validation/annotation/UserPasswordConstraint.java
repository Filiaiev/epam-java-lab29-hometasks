package com.filiaiev.spring.mvc1.validation.annotation;

import com.filiaiev.spring.mvc1.validation.UniqueUserValidator;
import com.filiaiev.spring.mvc1.validation.UserPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserPasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserPasswordConstraint {
    String message() default "Passwords don`t match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
