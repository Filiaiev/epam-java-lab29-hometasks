package com.filiaiev.spring.mvc1.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    @Column(columnDefinition = "decimal(19,2) default 0.00",
            nullable = false, insertable = false)
    private BigDecimal cash = new BigDecimal(0);
}
