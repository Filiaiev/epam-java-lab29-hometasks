package com.filiaiev.spring.mvc1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.dto.order.OrderShortDto;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Order;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.repository.OrderRepository;
import com.filiaiev.spring.mvc1.service.impl.ManagerServiceImpl;
import com.filiaiev.spring.mvc1.test.util.TestUtil;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {

    private TestUtil util;

    @InjectMocks
    private ManagerServiceImpl managerService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private EntityManager entityManager;

    @BeforeEach
    public void prepareData() {
        util = new TestUtil();
        util.initOrdersTestData();
    }

    @Test
    public void getFirstPageOrdersTest() {
        when(orderRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(util.getOrders()));

        List<OrderShortDto> orders =
                managerService.getOrderList(1, null);

        assertThat("ids", orders, contains(
                hasProperty("id", is(1)),
                hasProperty("id", is(2))
        ));
        assertThat("size", orders, hasSize(2));
    }

    @Test
    public void updateClientCashTest() {
        BigDecimal newCash = new BigDecimal(200);
        BigDecimal oldCash = util.getClients().get(0).getCash();

        when(clientRepository.findById(any()))
                .thenReturn(Optional.of(util.getClients().get(0)));

        when(clientRepository.save(any(Client.class)))
                .thenReturn(util.getClients().get(0));

        managerService.updateClientCash(0, newCash);

        assertThat(util.getClients().get(0).getCash(),
                equalTo(oldCash.add(newCash)));
    }

    @Test
    public void getOrderByIdTest() {
        when(orderRepository.getOne(any()))
                .thenReturn(util.getOrders().get(0));

        OrderManagerDto dto = managerService.getOrderById(1);

        assertThat(dto,
                allOf(notNullValue(),
                      hasProperty("id", is(1)))
        );
    }

    @Test
    public void getClientByIdTest() {
        when(clientRepository.getOne(any()))
                .thenReturn(util.getClients().get(0));

        ClientDto dto = managerService.getClientById(1);

        assertThat(dto, hasProperty("id", is(1)));
    }

    @Test
    public void updateOrderTest() {
        BigDecimal oldCost = util.getOrders().get(0).getCost();
        managerService.setObjectMapper(new ObjectMapper());

        when(orderRepository.getOne(ArgumentMatchers.anyLong()))
                .thenReturn(util.getOrders().get(0));

        OrderManagerDto dto = new OrderManagerDto();
        dto.setCost(new BigDecimal(999));

        when(entityManager.merge(any(Order.class)))
                .thenReturn(util.getOrders().get(0));

        OrderManagerDto updatedDto = managerService.updateOrder(1, dto);

        assertThat(updatedDto.getCost(), greaterThan(oldCost));
        assertThat(updatedDto,
                samePropertyValuesAs(OrderMapper.INSTANCE.toOrderManager(util.getOrders().get(0))));
    }
}
