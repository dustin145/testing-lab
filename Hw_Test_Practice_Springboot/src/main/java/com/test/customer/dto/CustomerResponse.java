package com.test.customer.dto;

public class CustomerResponse {

    private String customerNo;
    private String result;

    public CustomerResponse() {
    }

    public CustomerResponse(String customerNo, String result) {
        this.customerNo = customerNo;
        this.result = result;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public String getResult() {
        return result;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public void setResult(String result) {
        this.result = result;
    }
}