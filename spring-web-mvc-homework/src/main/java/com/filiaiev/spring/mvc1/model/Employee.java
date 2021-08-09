package com.filiaiev.spring.mvc1.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
