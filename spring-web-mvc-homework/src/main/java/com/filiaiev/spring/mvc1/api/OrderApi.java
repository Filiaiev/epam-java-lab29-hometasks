package com.filiaiev.spring.mvc1.api;

import com.filiaiev.spring.mvc1.dto.order.OrderDto;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.dto.order.OrderShortDto;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.util.wrapper.StringWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Api(tags = "Order management API")
@RequestMapping("/api/v1/orders")
public interface OrderApi {

    @ApiOperation("Get all orders")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<OrderDto> getAllOrders();

    @ApiOperation("Get order by order id")
    @GetMapping("/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    OrderDto getOrder(@PathVariable("order_id") long id);

    @ApiOperation("Create order")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    OrderDto createOrder(@RequestBody String description);

    @ApiOperation("Update order info")
    @PatchMapping("/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    Order updateOrder(@PathVariable("order_id") long id,
                      @RequestBody OrderManagerDto order);

    @ApiOperation("Pay for the order")
    @PostMapping("{order_id}/pay")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    OrderDto payForTheOrder(@PathVariable("order_id") long id);
}
