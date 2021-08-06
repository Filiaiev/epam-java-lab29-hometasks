package com.filiaiev.spring.mvc1.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;
    private String login;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20)
    private Role role;
}
