package com.filiaiev.spring.mvc1.service;

import com.filiaiev.spring.mvc1.dto.order.OrderDescriptionDto;
import com.filiaiev.spring.mvc1.dto.order.OrderUpdatableDto;
import com.filiaiev.spring.mvc1.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders(Integer pageNo, String sortField);

    Order getOrder(Long id);

    Order createOrder(OrderDescriptionDto order);

    Order updateOrder(Long id, OrderUpdatableDto order);

    Order payForTheOrder(Long id);

    Order addOrderComment(Long id, String comment);
}
