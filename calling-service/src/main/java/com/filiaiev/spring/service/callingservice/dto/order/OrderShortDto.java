package com.filiaiev.spring.service.callingservice.dto.order;

import com.filiaiev.spring.service.callingservice.model.OrderStatus;
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
