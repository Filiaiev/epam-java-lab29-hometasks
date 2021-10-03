package com.filiaiev.spring.mvc1.api;

import com.filiaiev.spring.mvc1.dto.order.OrderDescriptionDto;
import com.filiaiev.spring.mvc1.dto.order.OrderDto;
import com.filiaiev.spring.mvc1.dto.order.OrderUpdatableDto;
import com.filiaiev.spring.mvc1.validation.constraint.ValuesAllowed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Api(tags = "Order API")
@RequestMapping("/api/v1/orders")
public interface OrderApi {

    @ApiOperation("Get all orders")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<OrderDto> getAllOrders(
            @RequestParam(required = false, defaultValue = "1", value="page")
            @Min(1) Integer pageNo,
            @RequestParam(required = false, defaultValue = "id", value = "sort")
            @ValuesAllowed(values = {"id", "status", "cost"})
                    String sortField
    );

    @ApiOperation("Get order by order id")
    @GetMapping("/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    OrderDto getOrder(@PathVariable("order_id") Long id);

    @ApiOperation("Create order")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    OrderDto createOrder(@RequestBody OrderDescriptionDto order);

    @ApiOperation("Update order info")
    @PatchMapping("/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_REPAIRER')")
    OrderDto updateOrder(@PathVariable("order_id") Long id,
                         @Valid @RequestBody OrderUpdatableDto order);

    @ApiOperation("Pay for the order")
    @PatchMapping("{order_id}/paid")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    OrderDto payForTheOrder(@PathVariable("order_id") Long id);


    @ApiOperation("Add comment after order completition")
    @PatchMapping("{order_id}/comment")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    OrderDto addOrderComment(@PathVariable("order_id") Long ig,
                             @RequestBody String comment);
//    @ApiOperation("Update order status")
//    @PatchMapping("{order_id}/status/{status_value}")
//    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasRole('ROLE_REPAIRER')")
//    default OrderDto updateOrderStatus(@PathVariable("order_id") Long id, @PathVariable("status_value") OrderStatus status) {
//        return null;
//    };

}
