package com.filiaiev.spring.mvc1.controller.assembler;

import com.filiaiev.spring.mvc1.controller.ClientController;
import com.filiaiev.spring.mvc1.controller.model.ClientOrderModel;
import com.filiaiev.spring.mvc1.dto.order.OrderClientDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClientOrderAssembler extends RepresentationModelAssemblerSupport<OrderClientDto, ClientOrderModel> {

    private static final String GET_ORDER_REL = "get_order";
    private static final String CREATE_ORDER_REL = "create_order";
    private static final String UPDATE_ORDER_COMMENT_REL = "update_order_comment";

    public ClientOrderAssembler() {
        super(OrderClientDto.class, ClientOrderModel.class);
    }

    @Override
    public ClientOrderModel toModel(OrderClientDto entity) {
        ClientOrderModel clientOrderModel = new ClientOrderModel(entity);

        Link getOrder = linkTo(methodOn(ClientController.class)
                .getOrder(entity.getId()))
                .withRel(GET_ORDER_REL);

        Link createOrder = linkTo(methodOn(ClientController.class)
                .createOrder(null))
                .withRel(CREATE_ORDER_REL);

        Link updateOrderComment = linkTo(methodOn(ClientController.class)
                .updateOrderComment(entity.getId(), entity.getComment()))
                .withRel(UPDATE_ORDER_COMMENT_REL);

        clientOrderModel.add(getOrder, createOrder, updateOrderComment);

        return clientOrderModel;
    }
}
