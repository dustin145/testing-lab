package com.test.model;

public class Customer {
    
    private String name;
    private String birthDate;
    private String customerNo;
    
    public Customer(String name, String birthDate, String customerNo) {
        this.name = name;
        this.birthDate = birthDate;
        this.customerNo = customerNo;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getCustomerNo() {
        return customerNo;
    }
}