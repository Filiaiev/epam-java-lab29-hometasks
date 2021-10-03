package com.filiaiev.spring.mvc1.dto.order.factory;

import com.filiaiev.spring.mvc1.dto.order.OrderDto;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;

public class RepairerOrderDtoFactory implements OrderDtoFactory {

    @Override
    public OrderDto createDto(Order order) {
        return OrderMapper.INSTANCE.toOrderRepairer(order);
    }
}
