package com.filiaiev.spring.mvc1.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
