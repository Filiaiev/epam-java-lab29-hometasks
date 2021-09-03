package com.filiaiev.spring.mvc1.dto.order;

import com.filiaiev.spring.mvc1.model.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class OrderShortDto {

    private int id;
    private Timestamp orderDate;
    private BigDecimal cost;
    private OrderStatus status;
    private String statusLocalized;
}
