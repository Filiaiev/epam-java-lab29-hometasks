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

    private BigDecimal cash;
}
