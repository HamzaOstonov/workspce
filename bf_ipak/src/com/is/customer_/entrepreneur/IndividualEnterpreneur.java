package com.is.customer_.entrepreneur;

import com.is.customer_.core.model.Customer;

/**
 * Created by root on 15.06.2017.
 * 16:43
 */
public class IndividualEnterpreneur {
    private Customer customer;
    private String mode;

    public IndividualEnterpreneur(Customer customer, String mode) {
        super();
        this.customer = customer;
        this.mode = mode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
