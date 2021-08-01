package com.filiaiev.spring.mvc1.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private Integer workerId;
    private Timestamp orderDate;
    private Timestamp completeDate;
    private BigDecimal cost;
    private String comment;
    private String description;
    private int statusId;
    private String statusName;

}
