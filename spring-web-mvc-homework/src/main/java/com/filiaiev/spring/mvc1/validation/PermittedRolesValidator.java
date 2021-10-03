package com.filiaiev.spring.mvc1.validation;

import com.filiaiev.spring.mvc1.model.Role;
import com.filiaiev.spring.mvc1.util.SecurityUtil;
import com.filiaiev.spring.mvc1.validation.constraint.PermittedRoles;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PermittedRolesValidator implements ConstraintValidator<PermittedRoles, Object> {

    private List<Role> permittedRoles;
    private String returnMessage;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Role role = SecurityUtil.getUserDetails(SecurityContextHolder.getContext().getAuthentication()).getRole();

        if(!permittedRoles.contains(role) && value != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(returnMessage).addConstraintViolation();
            return false;
        }

        return true;
    }

    @Override
    public void initialize(PermittedRoles constraintAnnotation) {
        returnMessage = constraintAnnotation.message().concat(": ").concat(constraintAnnotation.fieldName());
        permittedRoles = Arrays.asList(constraintAnnotation.roles());
    }
}
