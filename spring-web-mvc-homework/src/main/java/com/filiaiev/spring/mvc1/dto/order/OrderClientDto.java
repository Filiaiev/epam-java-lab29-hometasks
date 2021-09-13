package com.filiaiev.spring.mvc1.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.filiaiev.spring.mvc1.dto.employee.EmployeeShortDto;
import com.filiaiev.spring.mvc1.dto.group.OnClientOrderCreate;
import com.filiaiev.spring.mvc1.model.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderClientDto implements OrderDto {

    @Null(message = "{create.client.order.fail.access}",
            groups = {OnClientOrderCreate.class})
    private Integer id;

    @Null(message = "{create.client.order.fail.access}",
            groups = {OnClientOrderCreate.class})
    private EmployeeShortDto employee;

    @Null(message = "{create.client.order.fail.access}",
            groups = {OnClientOrderCreate.class})
    private Timestamp orderDate;

    @Null(message = "{create.client.order.fail.access}",
            groups = {OnClientOrderCreate.class})
    private Timestamp completeDate;

    @Null(message = "{create.client.order.fail.access}",
            groups = {OnClientOrderCreate.class})
    private BigDecimal cost;

    @Null(message = "{create.client.order.fail.access}",
            groups = {OnClientOrderCreate.class})
    private String comment;

    private String description;

    @Null(message = "{create.client.order.fail.access}",
            groups = {OnClientOrderCreate.class})
    private OrderStatus status;

    @Null(message = "{create.client.order.fail.access}",
            groups = {OnClientOrderCreate.class})
    private String statusLocalized;
}
