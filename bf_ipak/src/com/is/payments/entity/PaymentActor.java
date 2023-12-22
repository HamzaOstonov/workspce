package com.is.payments.entity;

import com.is.customer_.core.model.Customer;

/**
 * Created by root on 24.05.2017.
 * 16:39
 */
public class PaymentActor {
    private String branch;
    private String account;
    private String name;
    private Customer customer;

    public PaymentActor(String branch, String account, String name) {
        this.branch = branch;
        this.account = account;
        this.name = name;
    }

    public PaymentActor(String branch, String account, Customer customer,String name) {
        this.branch = branch;
        this.account = account;
        this.customer = customer;
        this.name = name;
    }



    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
