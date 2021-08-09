package com.filiaiev.spring.service.callingservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filiaiev.spring.service.callingservice.controller.model.ManagerOrderModel;
import com.filiaiev.spring.service.callingservice.dto.group.OnOrderManagerUpdate;
import com.filiaiev.spring.service.callingservice.dto.order.OrderClientDto;
import com.filiaiev.spring.service.callingservice.dto.order.OrderManagerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.applet.Main;

import javax.validation.Valid;

@RestController
@RequestMapping("/service")
public class MainController {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public MainController() {
        restTemplate = new RestTemplate();

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);

        restTemplate.setRequestFactory(requestFactory);
    }

    @GetMapping("/agency/order/{id}")
    public ManagerOrderModel getAgencyOrder(@PathVariable int id) {
        return restTemplate.getForObject(
                "http://localhost:8080/manager/order/" + id,
                ManagerOrderModel.class);
    }

    @PostMapping("/agency/order")
    public OrderClientDto createAgencyOrder(@RequestBody OrderClientDto order) throws JsonProcessingException {
        HttpEntity<String> request =
                new HttpEntity<>(new ObjectMapper().writeValueAsString(order), headers);

        return restTemplate.postForEntity(
                "http://localhost:8080/client/order",
                request, OrderClientDto.class).getBody();
    }

    @PatchMapping("/agency/order/{id}")
    public ManagerOrderModel updateAgencyOrder(@PathVariable int id,
                                               @Validated(OnOrderManagerUpdate.class)
                                               @RequestBody OrderManagerDto dto) throws JsonProcessingException {
        HttpEntity<String> request =
                new HttpEntity<>(new ObjectMapper().writeValueAsString(dto), headers);

        return restTemplate.exchange(
                "http://localhost:8080/manager/order/" + id,
                HttpMethod.PATCH,
                request, ManagerOrderModel.class).getBody();
    }
}
