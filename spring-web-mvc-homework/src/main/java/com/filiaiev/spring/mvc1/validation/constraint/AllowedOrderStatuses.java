package com.filiaiev.spring.mvc1.validation.constraint;

import com.filiaiev.spring.mvc1.validation.AllowedOrderStatusesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AllowedOrderStatusesValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedOrderStatuses {

    String message() default "No privileges to set status";
    Class<?>[] groups(  ) default {};
    Class<? extends Payload>[] payload() default {};

    AllowedStatuses[] value() default {};
}
