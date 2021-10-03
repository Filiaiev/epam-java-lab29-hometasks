package com.filiaiev.spring.mvc1.validation.constraint;

import com.filiaiev.spring.mvc1.model.Role;
import com.filiaiev.spring.mvc1.validation.PermittedRolesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PermittedRolesValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PermittedRoles {

    String message() default "No privileges to set field";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Role[] roles();
    String fieldName();
}
