package com.filiaiev.spring.mvc1.controller;

import com.filiaiev.spring.mvc1.controller.assembler.ClientOrderAssembler;
import com.filiaiev.spring.mvc1.controller.model.ClientOrderModel;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.model.User;
import com.filiaiev.spring.mvc1.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientOrderAssembler clientOrderAssembler;

    @PostMapping
    public Client registerClient(@RequestBody User user) {
        return clientService.registerClient(user);
    }

    @GetMapping("/orders")
    public List<ClientOrderModel> getOrders() {
        return clientService.getOrderList().stream()
                .map(clientOrderAssembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/order/{id}")
    public ClientOrderModel getOrder(@PathVariable int id) {
        return clientOrderAssembler.toModel(clientService.getOrder(id));
    }

    @PostMapping("/order")
    public ClientOrderModel createOrder(@RequestBody Order order) {
        return clientOrderAssembler.toModel(clientService.createOrder(order));
    }

    @PutMapping("/order/{id}")
    public ClientOrderModel updateOrderComment(@PathVariable int id,
                                               @RequestBody String comment) {
        return clientOrderAssembler.toModel(clientService.updateOrderComment(id, comment));
    }

}
