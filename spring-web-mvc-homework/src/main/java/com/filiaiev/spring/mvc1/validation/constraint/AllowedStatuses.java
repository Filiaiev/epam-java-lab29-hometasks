package com.filiaiev.spring.mvc1.validation.constraint;

import com.filiaiev.spring.mvc1.model.OrderStatus;
import com.filiaiev.spring.mvc1.model.Role;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AllowedOrderStatuses.class)
public @interface AllowedStatuses {

    Role role();
    OrderStatus[] statuses();
}
