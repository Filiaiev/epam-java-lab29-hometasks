package com.filiaiev.spring.mvc1.validation.constraint;

import com.filiaiev.spring.mvc1.validation.AllowedValuesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AllowedValuesValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValuesAllowed {

    String message() default "Value does not allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] values();
}
