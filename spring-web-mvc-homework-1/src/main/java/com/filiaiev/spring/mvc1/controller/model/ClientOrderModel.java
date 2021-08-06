package com.filiaiev.spring.mvc1.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.filiaiev.spring.mvc1.dto.order.ClientOrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ClientOrderModel extends RepresentationModel<ClientOrderModel> {

    @JsonUnwrapped
    private ClientOrderDto clientOrderDto;
}
