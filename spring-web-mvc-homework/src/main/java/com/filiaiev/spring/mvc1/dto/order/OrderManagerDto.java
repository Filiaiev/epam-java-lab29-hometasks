package com.filiaiev.spring.mvc1.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.filiaiev.spring.mvc1.dto.client.ClientShortDto;
import com.filiaiev.spring.mvc1.dto.employee.EmployeeShortDto;
import com.filiaiev.spring.mvc1.dto.group.OnOrderManagerUpdate;
import com.filiaiev.spring.mvc1.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderManagerDto implements OrderDto {

    @Null(message = "{update.order.fail.access.id}",
            groups = {OnOrderManagerUpdate.class})
    private Integer id;

    @Null(message = "{update.order.fail.access.client}",
            groups = {OnOrderManagerUpdate.class})
    private ClientShortDto client;

    @Valid
    private EmployeeShortDto employee;

    @Null(message = "{update.order.fail.access.orderDate}",
            groups = {OnOrderManagerUpdate.class})
    private Timestamp orderDate;

    @Null(message = "{update.order.fail.access.completeDate}",
            groups = {OnOrderManagerUpdate.class})
    private Timestamp completeDate;

    private BigDecimal cost;

    @Null(message = "{update.order.fail.access.comment}",
            groups = {OnOrderManagerUpdate.class})
    private String comment;

    @Null(message = "{update.order.fail.access.description}",
            groups = {OnOrderManagerUpdate.class})
    private String description;

    private OrderStatus status;

    private String statusLocalized;
}
