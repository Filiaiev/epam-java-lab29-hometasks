package com.filiaiev.spring.mvc1.controller.assembler;

import com.filiaiev.spring.mvc1.controller.ManagerController;
import com.filiaiev.spring.mvc1.controller.model.ManagerOrderModel;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ManagerOrderAssembler extends RepresentationModelAssemblerSupport<OrderManagerDto, ManagerOrderModel> {

    private static final String GET_ORDER_REL = "get_order";
    private static final String UPDATE_ORDER = "update_order";

    public ManagerOrderAssembler() {
        super(OrderManagerDto.class, ManagerOrderModel.class);
    }

    @Override
    public ManagerOrderModel toModel(OrderManagerDto entity) {
        ManagerOrderModel managerOrderModel = new ManagerOrderModel(entity);

        Link getOrder = linkTo(methodOn(ManagerController.class)
                .getOrder(entity.getId()))
                .withRel(GET_ORDER_REL);

        Link updateOrder = linkTo(methodOn(ManagerController.class)
                .updateOrder(entity.getId(), entity))
                .withRel(UPDATE_ORDER);

        managerOrderModel.add(getOrder, updateOrder);

        return managerOrderModel;
    }
}
