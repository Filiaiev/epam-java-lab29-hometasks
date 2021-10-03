package com.filiaiev.spring.mvc1.controller;

import com.filiaiev.spring.mvc1.api.ClientApi;
import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientApi {

    private final ClientService clientService;

    @Override
    public ClientDto getClient(Long id) {
        return clientService.getClient(id);
    }

    @Override
    public ClientDto addClientCash(Long id, BigDecimal cash) {
        return clientService.addClientCash(id, cash);
    }
//
//    @PutMapping("/order/{id}")
//    public ClientOrderModel updateOrderComment(@PathVariable int id,
//                                               @RequestBody String comment) {
//        return clientOrderAssembler.toModel(clientService.updateOrderComment(id, comment));
//    }

}
