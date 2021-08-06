package com.filiaiev.spring.mvc1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filiaiev.spring.mvc1.dto.order.ClientOrderDto;
import com.filiaiev.spring.mvc1.exception.UserAlreadyExistsException;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.model.User;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.repository.OrderRepository;
import com.filiaiev.spring.mvc1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientOrderDto getOrder(int id) {
//        ManagerOrderDto orderDto = new ManagerOrderDto();
        Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);

        //        BeanUtils.copyProperties(order, orderDto);
        return new ObjectMapper().convertValue(order, ClientOrderDto.class);
    }

    public List<ClientOrderDto> getOrderList() {
        // TODO: convert to ClientOrderDto
        List<Order> orders = orderRepository.findAll();
        List<ClientOrderDto> clientOrderDtos = new ArrayList<>();

        for(Order o : orders) {
            ClientOrderDto clientOrderDto = new ClientOrderDto();
            BeanUtils.copyProperties(o, clientOrderDto);
            clientOrderDtos.add(clientOrderDto);
        }

        return clientOrderDtos;
    }

    public ClientOrderDto createOrder(Order order) {
        ClientOrderDto clientOrderDto = new ClientOrderDto();
        orderRepository.save(order);

        BeanUtils.copyProperties(order, clientOrderDto);
        return clientOrderDto;
    }

    public ClientOrderDto updateOrderComment(int id, String comment) {
        Order toUpdate = orderRepository.getOne(id);
        toUpdate.setComment(comment);
        orderRepository.save(toUpdate);

        ClientOrderDto clientOrderDto = new ClientOrderDto();
        BeanUtils.copyProperties(toUpdate, clientOrderDto);
        return clientOrderDto;
    }

    public Client registerClient(User user) {
        // TODO: Matcher factory?
        ExampleMatcher.GenericPropertyMatcher propertyMatcher = new ExampleMatcher.GenericPropertyMatcher();
        Example<User> example = Example.of(user, ExampleMatcher.matchingAny()
                .withIgnorePaths("id", "password", "person", "role")
                .withMatcher("email", propertyMatcher.ignoreCase()));

        if(userRepository.exists(example)) {
            throw new UserAlreadyExistsException();
        }

        log.info("Creating client, user info = {}", user);
        userRepository.save(user);

        Client client = new Client();
        client.setUser(user);

        clientRepository.save(client);
        log.info("Created client, {}", client);
        return client;
    }
}
