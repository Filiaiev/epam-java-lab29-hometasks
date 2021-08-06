package com.filiaiev.spring.mvc1.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.filiaiev.spring.mvc1.controller.assembler.ManagerOrderAssembler;
import com.filiaiev.spring.mvc1.controller.model.ManagerOrderModel;
import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.client.ClientPersonalsDto;
import com.filiaiev.spring.mvc1.dto.manager.ManagerOrderDto;
import com.filiaiev.spring.mvc1.exception.UserNotFoundException;
import com.filiaiev.spring.mvc1.exception.order.OrderNotFoundException;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.repository.OrderRepository;
import com.filiaiev.spring.mvc1.util.BeanUtilImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
                .toModel(objectMapper.convertValue(orderRepository.findById(id).orElseThrow(OrderNotFoundException::new),
                ManagerOrderDto.class));
    }

    public List<ManagerOrderModel> getOrderList() {
        return orderRepository.findAll().stream()
                .map(v -> objectMapper.convertValue(v, ManagerOrderDto.class))
                .map(managerOrderAssembler::toModel)
                .collect(Collectors.toList());
    }

    // TODO: need total rework (nested objects copying with null)
    public ManagerOrderModel updateOrder(int id, ManagerOrderDto order) {
        Order repoOrder = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        Order updatingOrder = objectMapper.convertValue(order, Order.class);

        BeanUtils.copyProperties(updatingOrder, repoOrder, BeanUtilImpl.getNullFields(updatingOrder));
        System.out.println(Arrays.toString(BeanUtilImpl.getNullFields(updatingOrder)));

        Order updatedOrder = orderRepository.save(repoOrder);
        return managerOrderAssembler
                .toModel(objectMapper.convertValue(updatedOrder, ManagerOrderDto.class));
    }

    public ClientDto updateClientCash(int id, BigDecimal cash) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        Client client = clientOptional.orElseThrow(UserNotFoundException::new);

        Optional<BigDecimal> currentCash = Optional.ofNullable(client.getCash());
        client.setCash(currentCash.orElse(new BigDecimal(0)).add(cash));

        clientRepository.save(client);

        log.info("Client with id: {} cash increased by {}", client.getId(), cash);

        return objectMapper.convertValue(client, ClientDto.class);
    }
}
