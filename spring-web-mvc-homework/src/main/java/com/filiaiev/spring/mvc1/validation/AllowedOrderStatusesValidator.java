package com.filiaiev.spring.mvc1.validation;

import com.filiaiev.spring.mvc1.dto.order.OrderUpdatableDto;
import com.filiaiev.spring.mvc1.model.OrderStatus;
import com.filiaiev.spring.mvc1.model.Role;
import com.filiaiev.spring.mvc1.model.UserDetailsImpl;
import com.filiaiev.spring.mvc1.util.SecurityUtil;
import com.filiaiev.spring.mvc1.validation.constraint.AllowedOrderStatuses;
import com.filiaiev.spring.mvc1.validation.constraint.AllowedStatuses;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllowedOrderStatusesValidator
        implements ConstraintValidator<AllowedOrderStatuses, OrderStatus> {

    private Map<Role, List<OrderStatus>> supportedRoleStatuses;
    private String returnMessage;

    @Override
    public boolean isValid(OrderStatus value, ConstraintValidatorContext context) {
        // As objectMapper#updateValue ignores null, simple check field to null
        if(value == null)
            return true;

        UserDetailsImpl userDetails = SecurityUtil.getUserDetails(SecurityContextHolder.getContext().getAuthentication());

        boolean isValid = supportedRoleStatuses.get(userDetails.getRole()).contains(value);

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    String.format("%s '%s', expected: %s", returnMessage, value, supportedRoleStatuses.get(userDetails.getRole())))
                    .addConstraintViolation();
        }

        return isValid;
    }

    @Override
    public void initialize(AllowedOrderStatuses constraintAnnotation) {
        supportedRoleStatuses = new HashMap<>();
        returnMessage = constraintAnnotation.message();

        for (AllowedStatuses ss : constraintAnnotation.value()) {
            supportedRoleStatuses.put(ss.role(), Arrays.asList(ss.statuses()));
        }
    }
}
