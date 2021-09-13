package com.filiaiev.spring.mvc1.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.exception.order.OrderNotFoundException;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.model.OrderStatus;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.repository.OrderRepository;
import com.filiaiev.spring.mvc1.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(long id) {
        return orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Order createOrder(String description) {
        Order order = new Order();
        order.setDescription(description);
        return order;
    }

    // Fetch with db before transaction ends
    @Override
    public Order updateOrder(long id, OrderManagerDto order) {
        Order toUpdate = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        Order updated;
        try {
            updated = objectMapper.updateValue(toUpdate, order);
        } catch (JsonMappingException e) {
            log.error("Update order #{} fails due to: {}", id, e.getMessage(), e);
            return toUpdate;
        }
        updated = orderRepository.saveAndFlush(updated);
        entityManager.refresh(updated);
        return updated;
    }

    @Override
    public Order payForTheOrder(long id) {
        Order toPay = orderRepository.getOne(id);
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientRepository.getClientByUser_Login(principal.getUsername());

        if(client.getCash().compareTo(toPay.getCost()) < 0)
            return toPay;

        client.setCash(client.getCash().subtract(toPay.getCost()));
        toPay.setStatus(OrderStatus.PAID);
        return toPay;
    }
}
