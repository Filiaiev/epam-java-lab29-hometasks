package com.filiaiev.spring.mvc1.dto.order.factory;

import com.filiaiev.spring.mvc1.dto.order.OrderDto;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;

public interface OrderDtoFactory {

    OrderDto createDto(Order order);

    default OrderDto createShortDto(Order order) {
        return OrderMapper.INSTANCE.toOrderShort(order);
    }
}
