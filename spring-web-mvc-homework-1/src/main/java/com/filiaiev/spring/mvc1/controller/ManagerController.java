package com.filiaiev.spring.mvc1.controller;

import com.filiaiev.spring.mvc1.api.ManagerApi;
import com.filiaiev.spring.mvc1.controller.model.ManagerOrderModel;
import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.group.OnOrderManagerUpdate;
import com.filiaiev.spring.mvc1.dto.manager.ManagerOrderDto;
import com.filiaiev.spring.mvc1.service.ManagerService;
import com.filiaiev.spring.mvc1.util.CashWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ManagerController implements ManagerApi {

    private final ManagerService managerService;

    @Override
    public List<ManagerOrderModel> getOrders(@MatrixVariable(pathVar = "params", required = false) Map<String, String> params) {
        // TODO: Filtering, sorting
        return managerService.getOrderList();
    }

    @Override
    public ManagerOrderModel getOrder(@PathVariable int id) {
        return managerService.getOrderById(id);
    }

    @Override
    public ClientDto updateClientCash(@PathVariable int id,
                                      @RequestBody CashWrapper cash) {
        return managerService.updateClientCash(id, cash.getCash());
    }

    @Override
    public ManagerOrderModel updateOrder(@PathVariable int id,
                                         @RequestBody
                                         @Validated(OnOrderManagerUpdate.class) ManagerOrderDto order) {
        return managerService.updateOrder(id, order);
    }


}
