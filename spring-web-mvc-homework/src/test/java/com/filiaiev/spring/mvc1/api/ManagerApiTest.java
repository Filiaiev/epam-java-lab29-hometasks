package com.filiaiev.spring.mvc1.api;

import com.filiaiev.spring.mvc1.controller.ManagerController;
import com.filiaiev.spring.mvc1.dto.order.OrderManagerDto;
import com.filiaiev.spring.mvc1.repository.UserRepository;
import com.filiaiev.spring.mvc1.service.impl.ManagerServiceImpl;
import com.filiaiev.spring.mvc1.test.config.TestConfig;
import com.filiaiev.spring.mvc1.test.util.TestUtil;
import com.filiaiev.spring.mvc1.util.mapper.OrderMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ManagerController.class)
@Import(TestConfig.class)
public class ManagerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagerServiceImpl managerService;

    @MockBean
    private UserRepository userRepository;

//    @BeforeAll
//    public static void setUp() {
//        manager = new User();
//        manager.setId(1);
//        manager.setLogin("testUser");
//        manager.setPassword("123");
//        manager.setEmail("furna@gmail.com");
//        manager.setPerson(null);
//        manager.setRole(Role.ROLE_MANAGER);
//    }

    @Test
    @WithMockUser(username = "testUser", password = "123", roles = "MANAGER")
    public void getOrderTest() throws Exception {
        TestUtil util = new TestUtil();
        util.initOrdersTestData();

        OrderManagerDto dto =
                OrderMapper.INSTANCE.toOrderManager(util.getOrders().get(0));

        when(managerService.getOrderById(anyInt()))
                .thenReturn(dto);

        mockMvc.perform(get("/manager/order/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

}
