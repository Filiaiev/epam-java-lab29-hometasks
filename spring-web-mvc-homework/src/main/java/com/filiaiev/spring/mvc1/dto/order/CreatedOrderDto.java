package com.filiaiev.spring.mvc1.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class CreatedOrderDto extends OrderDto {

    private long id;
    private String description;
    private Timestamp orderDate;
}
