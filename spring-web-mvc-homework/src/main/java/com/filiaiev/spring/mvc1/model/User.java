package com.filiaiev.spring.mvc1.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(length = 64, nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Person person;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(20) default 'ROLE_CLIENT'",
            length = 20, nullable = false)
    private Role role = Role.ROLE_CLIENT;
}
