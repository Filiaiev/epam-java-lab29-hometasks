package com.filiaiev.spring.mvc1.util.mapper;

import com.filiaiev.spring.mvc1.dto.order.*;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Employee;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.model.Role;
import com.filiaiev.spring.mvc1.util.OrderRolesId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toOrder(OrderShortDto dto);

    Order toOrder(OrderManagerDto dto);

    Order toOrder(OrderClientDto dto);

    Order toOrder(OrderRepairerDto dto);

    Order toOrder(OrderDescriptionDto dto);

    OrderShortDto toOrderShort(Order order);

    OrderManagerDto toOrderManager(Order order);

    OrderClientDto toOrderClient(Order order);

    CreatedOrderDto toCreatedOrder(Order order);

    OrderRepairerDto toOrderRepairer(Order order);

    OrderDescriptionDto toOrderDescription(Order order);

    default OrderRolesId toOrderRolesId(Order order) {
        OrderRolesId orid = new OrderRolesId();

        Optional<Client> client = Optional.ofNullable(order.getClient());
        Optional<Employee> employee = Optional.ofNullable(order.getEmployee());

        orid.getOrderRoleIdMap().put(Role.ROLE_CLIENT, client.orElse(new Client()).getId());
        orid.getOrderRoleIdMap().put(Role.ROLE_REPAIRER, employee.orElse(new Employee()).getId());
        return orid;
    }
}
