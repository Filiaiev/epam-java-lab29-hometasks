package com.filiaiev.spring.mvc1.controller;

import com.filiaiev.spring.mvc1.controller.assembler.ClientOrderAssembler;
import com.filiaiev.spring.mvc1.controller.model.ClientOrderModel;
import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.group.OnClientRegister;
import com.filiaiev.spring.mvc1.dto.order.OrderClientDto;
import com.filiaiev.spring.mvc1.dto.order.OrderShortDto;
import com.filiaiev.spring.mvc1.dto.user.UserRegisterDto;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientOrderAssembler clientOrderAssembler;

    @PostMapping
    public ClientDto registerClient(@Validated @RequestBody UserRegisterDto user) {
        return clientService.registerClient(user);
    }

    @GetMapping("/orders")
    public List<OrderShortDto> getOrders() {
        return clientService.getOrderList();
    }

    @GetMapping("/order/{id}")
    public ClientOrderModel getOrder(@PathVariable int id) {
        return clientOrderAssembler.toModel(clientService.getOrder(id));
    }

    @PostMapping("/order")
    public ClientOrderModel createOrder(@RequestBody OrderClientDto order) {
        return clientOrderAssembler.toModel(clientService.createOrder(order));
    }

    @PutMapping("/order/{id}")
    public ClientOrderModel updateOrderComment(@PathVariable int id,
                                               @RequestBody String comment) {
        return clientOrderAssembler.toModel(clientService.updateOrderComment(id, comment));
    }

}
