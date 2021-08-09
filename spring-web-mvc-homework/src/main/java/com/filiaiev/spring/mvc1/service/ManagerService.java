package com.filiaiev.spring.mvc1.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filiaiev.spring.mvc1.controller.assembler.ManagerOrderAssembler;
import com.filiaiev.spring.mvc1.controller.model.ManagerOrderModel;
import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.dto.order.OrderShortDto;
import com.filiaiev.spring.mvc1.exception.UserNotFoundException;
import com.filiaiev.spring.mvc1.exception.order.OrderNotFoundException;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.repository.OrderRepository;
import com.filiaiev.spring.mvc1.util.BeanUtilImpl;
import com.filiaiev.spring.mvc1.util.mapper.ClientMapper;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

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

     /*
        This method communicate with DB 3 times
        TODO: Try to optimize this process in future
        1 - Read order to be changed
        2 - Rewrite this order
        3 - Read updated order
            (to get changes wired by DB like employee user info)
     */
    public ManagerOrderModel updateOrder(int id, OrderManagerDto order) {
        Order repoOrder = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        try {
            orderRepository.save(objectMapper.updateValue(repoOrder, order));
            return managerOrderAssembler.toModel(
                    OrderMapper.INSTANCE.toOrderManager(orderRepository.getOne(id)));
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
