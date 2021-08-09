package com.filiaiev.spring.mvc1.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "order_headers")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_id")
    private Employee employee;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()",
            nullable = false)
    private Timestamp orderDate;

    private Timestamp completeDate;

    private BigDecimal cost;

    private String comment;

    @Column(nullable = false)
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(20) default 'CREATED'",
            nullable = false, insertable = false)
    private OrderStatus status;

    @Transient
    private String statusName;
}
