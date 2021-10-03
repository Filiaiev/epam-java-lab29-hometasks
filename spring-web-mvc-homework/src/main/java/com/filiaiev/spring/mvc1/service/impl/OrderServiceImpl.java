package com.filiaiev.spring.mvc1.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filiaiev.spring.mvc1.dto.order.OrderDescriptionDto;
import com.filiaiev.spring.mvc1.dto.order.OrderUpdatableDto;
import com.filiaiev.spring.mvc1.exception.repository.RecordNotFoundException;
import com.filiaiev.spring.mvc1.model.*;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.repository.OrderRepository;
import com.filiaiev.spring.mvc1.service.OrderService;
import com.filiaiev.spring.mvc1.util.OrderRolesId;
import com.filiaiev.spring.mvc1.util.SecurityUtil;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Scope("session")
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;
    private final UserDetailsImpl userDetails = SecurityUtil.getUserDetails(SecurityContextHolder.getContext().getAuthentication());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> getAllOrders(Integer pageNo, String sortField) {
        Pageable pr = PageRequest.of(pageNo-1, 2, Sort.by(sortField));
        return orderRepository.findAllImpl(pr).getContent();
    }

    @Override
    public Order getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(RecordNotFoundException::new);

        if(userDetails.getRole() == Role.ROLE_MANAGER)
            return order;

        OrderRolesId orderRolesId = OrderMapper.INSTANCE.toOrderRolesId(order);
        if(userDetails.getRoleEntityIdMap().get(userDetails.getRole()).equals(orderRolesId.getOrderRoleIdMap().get(userDetails.getRole()))) {
            return order;
        }

        throw new RecordNotFoundException();
    }

    @Override
    public Order createOrder(OrderDescriptionDto order) {
        Client client = new Client();
        client.setId(userDetails.getRoleEntityIdMap().get(Role.ROLE_CLIENT));

        Order newOrder = OrderMapper.INSTANCE.toOrder(order);
        newOrder.setClient(client);
        return orderRepository.save(newOrder);
    }

    @Override
    public Order updateOrder(Long id, OrderUpdatableDto order) {
        Order toUpdate = this.getOrder(id);
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
    public Order payForTheOrder(Long id) {
        Order toPay = this.getOrder(id);
        Client client = clientRepository.findById(
                userDetails.getRoleEntityIdMap().get(Role.ROLE_CLIENT)).get();

        if(client.getCash().compareTo(toPay.getCost()) < 0 || toPay.getStatus() != OrderStatus.WAITING_FOR_PAYMENT)
            return toPay;

        client.setCash(client.getCash().subtract(toPay.getCost()));
        toPay.setStatus(OrderStatus.PAID);
        return toPay;
    }

    @Override
    public Order addOrderComment(Long id, String comment) {
        Order order = this.getOrder(id);

        if(order.getStatus() == OrderStatus.COMPLETED)
            order.setComment(comment);

        return order;
    }
}
