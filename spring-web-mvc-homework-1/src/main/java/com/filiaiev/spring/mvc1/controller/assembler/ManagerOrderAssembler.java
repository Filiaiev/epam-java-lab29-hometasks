package com.filiaiev.spring.mvc1.controller.assembler;

import com.filiaiev.spring.mvc1.controller.ManagerController;
import com.filiaiev.spring.mvc1.controller.model.ManagerOrderModel;
import com.filiaiev.spring.mvc1.dto.manager.ManagerOrderDto;
import com.filiaiev.spring.mvc1.util.CashWrapper;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ManagerOrderAssembler extends RepresentationModelAssemblerSupport<ManagerOrderDto, ManagerOrderModel> {

    private static final String GET_ORDER_REL = "get_order";
    private static final String GET_ORDERS_REL = "get_orders";
    private static final String UPDATE_ORDER = "update_order";

    public ManagerOrderAssembler() {
        super(ManagerOrderDto.class, ManagerOrderModel.class);
    }

    @Override
    public ManagerOrderModel toModel(ManagerOrderDto entity) {
        ManagerOrderModel managerOrderModel = new ManagerOrderModel(entity);

        Link getOrder = linkTo(methodOn(ManagerController.class)
                .getOrder(entity.getId()))
                .withRel(GET_ORDER_REL);

        Link getOrders = linkTo(methodOn(ManagerController.class)
                .getOrders(null))
                .withRel(GET_ORDERS_REL);

        Link updateOrder = linkTo(methodOn(ManagerController.class)
                .updateOrder(entity.getId(), entity))
                .withRel(UPDATE_ORDER);

        managerOrderModel.add(getOrder, getOrders, updateOrder);

        return managerOrderModel;
    }
}
