package com.filiaiev.spring.mvc1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filiaiev.spring.mvc1.dto.group.OnOrderManagerUpdate;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "order_headers")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Null(message = "You can`t change order id", groups = OnOrderManagerUpdate.class)
    private Integer id;

    @OneToOne
    @Null(message = "You can`t change client id", groups = OnOrderManagerUpdate.class)
    private Client client;

    @OneToOne
    @JoinColumn(name = "worker_id")
    private Employee employee;

    @Null(message = "You can`t change order date", groups = OnOrderManagerUpdate.class)
    @CreationTimestamp
    private Timestamp orderDate;

    private Timestamp completeDate;

    private BigDecimal cost;

    @Null(message = "You can`t change order comment", groups = OnOrderManagerUpdate.class)
    private String comment;

    @Null(message = "You can`t change order description", groups = OnOrderManagerUpdate.class)
    private String description;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @Transient
//    @JsonIgnore
    private String statusName;
}