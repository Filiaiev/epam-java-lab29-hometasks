package com.filiaiev.spring.mvc1.api;

import com.filiaiev.spring.mvc1.controller.model.ManagerOrderModel;
import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.group.OnOrderManagerUpdate;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.dto.order.OrderShortDto;
import com.filiaiev.spring.mvc1.util.CashWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "Manager API")
//@RequestMapping("/api/v1/manager")
@RequestMapping("/manager")
public interface ManagerApi {

    @ApiImplicitParam(name = "params", required = false, paramType = "path", value = "Filter params")
    @ApiOperation("Get orders")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping({"/orders/{params}", "/orders"})
    List<OrderShortDto> getOrders(@MatrixVariable(pathVar = "params", required = false) Map<String, String> params);

    @ApiImplicitParam(name = "id", required = true, paramType = "path", value = "Order id to get")
    @ApiOperation("Get order")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/{id}")
    ManagerOrderModel getOrder(@PathVariable int id);

    @ApiImplicitParam(name = "id", required = true, paramType = "path", value = "Client id to get")
    @ApiOperation("Get client info")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/client/{id}")
    ClientDto getClient(@PathVariable int id);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, paramType = "path", value = "Client id to update"),
            @ApiImplicitParam(name = "cash", required = true, paramType = "body", value = "Simple BigDecimal wrapper holding value")
    })
    @ApiOperation("Update client cash")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/client/{id}")
    ClientDto updateClientCash(@PathVariable int id,
                               @RequestBody CashWrapper cash);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, paramType = "path", value = "Order id to update"),
            @ApiImplicitParam(name = "order", required = true, paramType = "body", value = "Order to insert")
    })
    @ApiOperation("Update order info")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/{id}")
    ManagerOrderModel updateOrder(@PathVariable int id,
                                  @RequestBody
                                  @Validated(OnOrderManagerUpdate.class) OrderManagerDto order);
}
