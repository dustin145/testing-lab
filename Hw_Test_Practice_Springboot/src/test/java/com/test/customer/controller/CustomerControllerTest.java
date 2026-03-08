package com.test.customer.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.test.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void 고객조회_GET_API_TEST() throws Exception {
        given(customerService.findCustomerNo("홍길동", "19930216"))
                .willReturn("CUST1001");

        mockMvc.perform(get("/api/customer")
                .param("name", "홍길동")
                .param("birthDate", "19930216"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerNo").value("CUST1001"))
                .andExpect(jsonPath("$.result").value("SUCCESS"));
    }

    @Test
    void 고객조회_POST_API_TEST() throws Exception {
        given(customerService.findCustomerNo("홍길동", "19930216"))
                .willReturn("CUST1001");

        String json = "{ \"name\": \"홍길동\", \"birthDate\": \"19930216\" }";

        mockMvc.perform(post("/api/customer")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerNo").value("CUST1001"))
                .andExpect(jsonPath("$.result").value("SUCCESS"));
    }
}