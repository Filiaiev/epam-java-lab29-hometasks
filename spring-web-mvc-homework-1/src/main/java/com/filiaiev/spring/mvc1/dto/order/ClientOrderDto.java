package com.filiaiev.spring.mvc1.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.filiaiev.spring.mvc1.dto.EmployeePersonalsDto;
import com.filiaiev.spring.mvc1.dto.group.OnOrderManagerUpdate;
import com.filiaiev.spring.mvc1.model.OrderStatus;
import lombok.*;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientOrderDto {

    @Null(message = "You can`t change order id", groups = OnOrderManagerUpdate.class)
    private Integer id;

    private EmployeePersonalsDto employee;

    @Null(message = "You can`t change order date", groups = OnOrderManagerUpdate.class)
    private Timestamp orderDate;

    @Null(message = "You can`t change complete date", groups = OnOrderManagerUpdate.class)
    private Timestamp completeDate;

    private BigDecimal cost;

    @Null(message = "You can`t change order comment", groups = OnOrderManagerUpdate.class)
    private String comment;

    @Null(message = "You can`t change order description", groups = OnOrderManagerUpdate.class)
    private String description;

    private OrderStatus status;

    @Null(message = "You can`t change order status name", groups = OnOrderManagerUpdate.class)
    private String statusLocalized;
}
