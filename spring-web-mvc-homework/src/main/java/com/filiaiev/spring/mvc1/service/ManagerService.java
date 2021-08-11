package com.filiaiev.spring.mvc1.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filiaiev.spring.mvc1.controller.assembler.ManagerOrderAssembler;
import com.filiaiev.spring.mvc1.controller.model.ManagerOrderModel;
import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.dto.order.OrderShortDto;
import com.filiaiev.spring.mvc1.exception.UserNotFoundException;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.repository.OrderRepository;
import com.filiaiev.spring.mvc1.util.mapper.ClientMapper;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    private ObjectMapper objectMapper;

    private final ManagerOrderAssembler managerOrderAssembler;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ManagerOrderModel getOrderById(int id) {
        return managerOrderAssembler
                .toModel(OrderMapper.INSTANCE.toOrderManager(orderRepository.getOne(id)));
    }

    public List<OrderShortDto> getOrderList() {
        return orderRepository.findAll().stream()
                .map(OrderMapper.INSTANCE::toOrderShort)
                .collect(Collectors.toList());
    }

    // TODO: fix update issue
    // Закоментований варіант - робочий
    @Transactional
    public ManagerOrderModel updateOrder(int id, OrderManagerDto order) {
        Order repoOrder = orderRepository.getOne(id);
//        EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        try {
//            Order merged = entityManager1.merge(objectMapper.updateValue(repoOrder, order));
            Order merged = orderRepository.save(objectMapper.updateValue(repoOrder, order));
            return managerOrderAssembler
                    .toModel(OrderMapper.INSTANCE.toOrderManager(merged));

        } catch (JsonMappingException e) {
            log.error("Json mapping exception", e);
            throw new RuntimeException("Json mapping exception");
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
