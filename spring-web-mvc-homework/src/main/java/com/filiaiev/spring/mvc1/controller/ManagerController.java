package com.filiaiev.spring.mvc1.controller;

import com.filiaiev.spring.mvc1.api.ManagerApi;
import com.filiaiev.spring.mvc1.controller.assembler.ManagerOrderAssembler;
import com.filiaiev.spring.mvc1.controller.model.ManagerOrderModel;
import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.dto.order.OrderShortDto;
import com.filiaiev.spring.mvc1.service.impl.ManagerServiceImpl;
import com.filiaiev.spring.mvc1.util.wrapper.CashWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController implements ManagerApi {

    private final ManagerServiceImpl managerService;

    private final ManagerOrderAssembler assembler;

    @Override
    public List<OrderShortDto> getOrders(int page, String sort) {
        // TODO: Filtering, sorting
        return managerService.getOrderList(page, sort);
    }

    @Override
    public ManagerOrderModel getOrder(int id) {
        return assembler.toModel(managerService.getOrderById(id));
    }

    @Override
    public ClientDto getClient(int id) {
        return managerService.getClientById(id);
    }

    @Override
    public ClientDto updateClientCash(int id, CashWrapper cash) {
        return managerService.updateClientCash(id, cash.getCash());
    }

    @Override
    public ManagerOrderModel updateOrder(int id, OrderManagerDto order) {
        return assembler.toModel(managerService.updateOrder(id, order));
    }
}
