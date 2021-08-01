package com.filiaiev.spring.mvc1.controller;

import com.filiaiev.spring.mvc1.dto.OrderDto;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        return clientService.getOrderList();
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrder(@PathVariable int id) {
        return clientService.getOrderById(id);
    }

    @PostMapping("/orders")
    public OrderDto createOrder(@RequestBody Order order) {
        return clientService.createOrder(order);
    }

    @PutMapping("/orders/{id}")
    public OrderDto updateComment(@PathVariable int id,
                                  @RequestBody String comment) {
        return clientService.updateComment(id, comment);
    }

}
