package com.test.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.customer.model.Customer;

@Service
public class CustomerService {

    private static final List<Customer> customerList = new ArrayList<Customer>();

    static {
        customerList.add(new Customer("홍길동", "19930216", "CUST1001"));
        customerList.add(new Customer("김영희", "19900101", "CUST1002"));
        customerList.add(new Customer("이철수", "19880512", "CUST1003"));
    }

    public String findCustomerNo(String name, String birthDate) {
        for (Customer customer : customerList) {
            if (customer.getName().equals(name) && customer.getBirthDate().equals(birthDate)) {
                return customer.getCustomerNo();
            }
        }
        return "";
    }
}