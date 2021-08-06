package com.filiaiev.spring.mvc1.dto.manager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.filiaiev.spring.mvc1.dto.client.ClientPersonalsDto;
import com.filiaiev.spring.mvc1.dto.EmployeePersonalsDto;
import com.filiaiev.spring.mvc1.dto.group.OnOrderManagerUpdate;
import com.filiaiev.spring.mvc1.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManagerOrderDto {

    @Null(message = "You don`t have permission to change order id",
            groups = {OnOrderManagerUpdate.class})
    private Integer id;

    @Null(message = "You don`t have permission to change order client",
            groups = {OnOrderManagerUpdate.class})
    private ClientPersonalsDto client;

    private EmployeePersonalsDto employee;

    @Null(message = "You don`t have permission to change order date",
            groups = {OnOrderManagerUpdate.class})
    private Timestamp orderDate;

    @Null(message = "You don`t have permission to change order completition date",
            groups = {OnOrderManagerUpdate.class})
    private Timestamp completeDate;

    private BigDecimal cost;

    @Null(message = "You don`t have permission to change order comment",
            groups = {OnOrderManagerUpdate.class})
    private String comment;

    @Null(message = "You don`t have permission change order description",
            groups = {OnOrderManagerUpdate.class})
    private String description;

    private OrderStatus status;

    @Null(message = "You don`t have permission to change order status localized value",
            groups = {OnOrderManagerUpdate.class})
    private String statusLocalized;
}
