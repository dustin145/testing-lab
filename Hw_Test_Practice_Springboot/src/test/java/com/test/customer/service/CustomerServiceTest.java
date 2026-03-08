package com.test.customer.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
    }

    @Test
    void 고객조회_성공() {

        String result = customerService.findCustomerNo("홍길동", "19930216");

        assertEquals("CUST1001", result);
    }

    @Test
    void 고객조회_실패() {

        String result = customerService.findCustomerNo("박민수", "20000101");

        assertEquals("", result);
    }

}