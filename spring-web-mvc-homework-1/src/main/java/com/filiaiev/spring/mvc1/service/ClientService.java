package com.filiaiev.spring.mvc1.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.filiaiev.spring.mvc1.dto.OrderDto;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final OrderRepository orderRepository;

    public OrderDto getOrderById(int id) {
        OrderDto orderDto = new OrderDto();
        Order order = orderRepository.getEntity(id);

        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }

    public List<OrderDto> getOrderList() {
        List<Order> orders = orderRepository.getAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        for(Order o : orders) {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(o, orderDto);
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    public OrderDto createOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        orderRepository.createEntity(order);

        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }

    public OrderDto updateComment(int id, String comment) {
        Order toUpdate = orderRepository.getEntity(id);
        toUpdate.setComment(comment);
        orderRepository.updateEntity(id, toUpdate);

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(toUpdate, orderDto);
        return orderDto;
    }
}
