package com.test.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.customer.dto.CustomerRequest;
import com.test.customer.dto.CustomerResponse;
import com.test.customer.service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/api/customer")
    public CustomerResponse getCustomer(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "birthDate", required = false) String birthDate) {

        return createResponse(name, birthDate);
    }

    @PostMapping("/api/customer")
    public CustomerResponse postCustomer(@RequestBody CustomerRequest request) {
        return createResponse(request.getName(), request.getBirthDate());
    }

    private CustomerResponse createResponse(String name, String birthDate) {
        if (name == null || name.trim().isEmpty() ||
            birthDate == null || birthDate.trim().isEmpty()) {
            return new CustomerResponse("", "INVALID_REQUEST");
        }

        String customerNo = customerService.findCustomerNo(name, birthDate);

        if (!customerNo.isEmpty()) {
            return new CustomerResponse(customerNo, "SUCCESS");
        }

        return new CustomerResponse("", "NOT_FOUND");
    }
}