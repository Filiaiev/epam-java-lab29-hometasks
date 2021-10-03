package com.filiaiev.spring.mvc1.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.filiaiev.spring.mvc1.dto.employee.EmployeeUpdatableDto;
import com.filiaiev.spring.mvc1.model.OrderStatus;
import com.filiaiev.spring.mvc1.model.Role;
import com.filiaiev.spring.mvc1.validation.constraint.AllowedStatuses;
import com.filiaiev.spring.mvc1.validation.constraint.PermittedRoles;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderUpdatableDto extends OrderDto {

    @PermittedRoles(roles = {Role.ROLE_MANAGER}, fieldName = "cost")
    private BigDecimal cost;

    @PermittedRoles(roles = {Role.ROLE_MANAGER}, fieldName = "employee")
    private EmployeeUpdatableDto employee;

    @AllowedStatuses(role = Role.ROLE_MANAGER, statuses = {OrderStatus.WAITING_FOR_PAYMENT, OrderStatus.PAID, OrderStatus.CANCELED})
    @AllowedStatuses(role = Role.ROLE_REPAIRER, statuses = {OrderStatus.IN_WORK, OrderStatus.COMPLETED})
    private OrderStatus status;

}
