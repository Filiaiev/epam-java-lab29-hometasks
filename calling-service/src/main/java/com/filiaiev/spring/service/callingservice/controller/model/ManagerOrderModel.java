package com.filiaiev.spring.service.callingservice.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.filiaiev.spring.service.callingservice.dto.order.OrderManagerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ManagerOrderModel extends RepresentationModel<ManagerOrderModel> {

    @JsonUnwrapped
    private OrderManagerDto orderManagerDto;
}
