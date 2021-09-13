package com.filiaiev.spring.mvc1.controller;

import com.filiaiev.spring.mvc1.api.OrderApi;
import com.filiaiev.spring.mvc1.dto.order.OrderDto;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.dto.order.factory.OrderDtoFactory;
import com.filiaiev.spring.mvc1.model.Employee;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.service.OrderService;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Scope("session")
public class OrderController implements OrderApi {

    private final OrderService orderService;
    private final OrderDtoFactory orderDtoFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(orderDtoFactory::createShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrder(long id) {
        return orderDtoFactory.createDto(orderService.getOrder(id));
    }

    @Override
    public OrderDto createOrder(String description) {
        return OrderMapper.INSTANCE.toCreatedOrder(orderService.createOrder(description));
    }

    @Override
    public Order updateOrder(long id, OrderManagerDto order) {
        return orderService.updateOrder(id, order);
    }

    @Override
    public OrderDto payForTheOrder(long id) {
        return OrderMapper.INSTANCE.toOrderClient(orderService.payForTheOrder(id));
    }

    @Transactional
    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") long id) {
        Employee employee = new Employee();
        employee.setId(id);

//        em.refresh(employee);
        return em.getReference(Employee.class, employee.getId());
    }
}
