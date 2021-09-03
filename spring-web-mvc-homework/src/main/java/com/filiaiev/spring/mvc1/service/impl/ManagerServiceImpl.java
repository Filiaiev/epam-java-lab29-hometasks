package com.filiaiev.spring.mvc1.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.dto.order.OrderShortDto;
import com.filiaiev.spring.mvc1.exception.UserNotFoundException;
import com.filiaiev.spring.mvc1.exception.order.OrderUpdateException;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.repository.OrderRepository;
import com.filiaiev.spring.mvc1.repository.impl.OrderRepositoryImpl;
import com.filiaiev.spring.mvc1.util.mapper.ClientMapper;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableTransactionManagement
@Transactional
public class ManagerServiceImpl {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    private ObjectMapper objectMapper;

    private final EntityManager entityManager;

    public ManagerServiceImpl(OrderRepository orderRepository,
                              ClientRepository clientRepository,
                              @Qualifier("myEntityManager")
                                      EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.entityManager = entityManager;
    }

    //    @Autowired
//    public void setEntityManager(@Qualifier("myEntityManager") EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public OrderManagerDto getOrderById(int id) {
        return OrderMapper.INSTANCE.toOrderManager(orderRepository.getOne(id));
    }

    // TODO: If pageNo greater than all pages, return first page
    public List<OrderShortDto> getOrderList(int pageNo, String sort) {
        if(!OrderRepositoryImpl.isSortingAllowed(sort))
            sort = "orderDate";

        Pageable page = PageRequest.of(pageNo-1, 2, Sort.by(sort));
        Page<Order> orders = orderRepository.findAll(page);
        return orders.stream()
                .map(OrderMapper.INSTANCE::toOrderShort)
                .collect(Collectors.toList());
    }

    // TODO: fix update issue ( not using Factory )
    public OrderManagerDto updateOrder(int id, OrderManagerDto order) {
        Order repoOrder = orderRepository.getOne(id);

        try {
            Order merged = entityManager.merge(objectMapper.updateValue(repoOrder, order));
            return OrderMapper.INSTANCE.toOrderManager(merged);

        } catch (JsonMappingException e) {
            log.error("Json mapping exception", e);
            throw new OrderUpdateException();
        }
    }

    public ClientDto updateClientCash(int id, BigDecimal cash) {
        Client client = clientRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        client.setCash(client.getCash().add(cash));
        clientRepository.save(client);

        log.info("Client with id: {} cash increased by {}", client.getId(), cash);

        return ClientMapper.INSTANCE.toClientDto(client);
    }

    public ClientDto getClientById(int id) {
        return ClientMapper.INSTANCE.toClientDto(clientRepository.getOne(id));
    }
}
