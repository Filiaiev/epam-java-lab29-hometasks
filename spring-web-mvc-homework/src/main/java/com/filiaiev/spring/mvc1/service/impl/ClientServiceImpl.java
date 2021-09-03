package com.filiaiev.spring.mvc1.service.impl;

import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.order.OrderClientDto;
import com.filiaiev.spring.mvc1.dto.order.OrderShortDto;
import com.filiaiev.spring.mvc1.dto.user.UserRegisterDto;
import com.filiaiev.spring.mvc1.exception.UserRegisterException;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.model.User;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.repository.OrderRepository;
import com.filiaiev.spring.mvc1.repository.UserRepository;
import com.filiaiev.spring.mvc1.util.mapper.ClientMapper;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;
import com.filiaiev.spring.mvc1.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public OrderClientDto getOrder(int id) {
        Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);
        return OrderMapper.INSTANCE.toOrderClient(order);
    }

    public List<OrderShortDto> getOrderList() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper.INSTANCE::toOrderShort)
                .collect(Collectors.toList());
    }

    public OrderClientDto createOrder(OrderClientDto order) {
        Order saved = orderRepository.save(OrderMapper.INSTANCE.toOrder(order));
        return OrderMapper.INSTANCE.toOrderClient(saved);
    }

    public OrderClientDto updateOrderComment(int id, String comment) {
        Order toUpdate = orderRepository.getOne(id);
        toUpdate.setComment(comment);
        orderRepository.save(toUpdate);

        return OrderMapper.INSTANCE.toOrderClient(toUpdate);
    }

    public ClientDto registerClient(UserRegisterDto user) {
        User registerUser = UserMapper.INSTANCE.toUser(user);

        log.info("Creating client, login = {}, email = {}",
                user.getLogin(), user.getEmail());

        registerUser = userRepository.save(registerUser);

        Client client = new Client();
        client.setUser(registerUser);

        client = clientRepository.save(client);

        log.info("Client has been created, id = {}", client.getId());
        return ClientMapper.INSTANCE.toClientDto(client);
    }
}
