package com.filiaiev.spring.mvc1.service;

import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrder(long id);

    Order createOrder(String description);

    Order updateOrder(long id, OrderManagerDto order);

    Order payForTheOrder(long id);
}
