package com.filiaiev.spring.mvc1.controller;

import com.filiaiev.spring.mvc1.api.OrderApi;
import com.filiaiev.spring.mvc1.dto.order.OrderDescriptionDto;
import com.filiaiev.spring.mvc1.dto.order.OrderDto;
import com.filiaiev.spring.mvc1.dto.order.OrderUpdatableDto;
import com.filiaiev.spring.mvc1.dto.order.factory.OrderDtoFactory;
import com.filiaiev.spring.mvc1.model.OrderStatus;
import com.filiaiev.spring.mvc1.service.OrderService;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Scope("session")
@Validated
public class OrderController implements OrderApi {

    private final OrderService orderService;
    private final OrderDtoFactory orderDtoFactory;

    @Override
    public List<OrderDto> getAllOrders(Integer pageNo, String sortField) {
        return orderService.getAllOrders(pageNo, sortField).stream()
                .map(orderDtoFactory::createShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrder(Long id) {
        return orderDtoFactory.createDto(orderService.getOrder(id));
    }

    @Override
    public OrderDto createOrder(OrderDescriptionDto description) {
        return OrderMapper.INSTANCE.toCreatedOrder(orderService.createOrder(description));
    }

    @Override
    public OrderDto updateOrder(Long id, OrderUpdatableDto order) {
        return OrderMapper.INSTANCE.toOrderManager(orderService.updateOrder(id, order));
    }

    @Override
    public OrderDto payForTheOrder(Long id) {
        return OrderMapper.INSTANCE.toOrderClient(orderService.payForTheOrder(id));
    }

    @Override
    public OrderDto addOrderComment(Long id, String comment) {
        return OrderMapper.INSTANCE.toOrderClient(orderService.addOrderComment(id, comment));
    }
}
