package com.filiaiev.spring.mvc1.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Order {
    private Integer id;
    private Integer clientId;
    private Integer workerId;
    private Timestamp orderDate;
    private Timestamp completeDate;
    private BigDecimal cost;
    private String comment;
    private String description;
    private Integer statusId;
    private String statusName;
}
