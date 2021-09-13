package com.filiaiev.spring.mvc1.util.mapper;

import com.filiaiev.spring.mvc1.dto.order.CreatedOrderDto;
import com.filiaiev.spring.mvc1.dto.order.OrderClientDto;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.dto.order.OrderShortDto;
import com.filiaiev.spring.mvc1.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toOrder(OrderShortDto dto);

    Order toOrder(OrderManagerDto dto);

    Order toOrder(OrderClientDto dto);

    OrderShortDto toOrderShort(Order order);

    OrderManagerDto toOrderManager(Order order);

    OrderClientDto toOrderClient(Order order);

    CreatedOrderDto toCreatedOrder(Order order);
}
