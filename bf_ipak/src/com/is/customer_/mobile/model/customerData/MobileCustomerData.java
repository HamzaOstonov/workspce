package com.is.customer_.mobile.model.customerData;

import com.is.customer_.mobile.model.MobileBankClient;

import java.io.Serializable;

/**
 * Created by Dev1 on 15.11.2018.
 */

public class MobileCustomerData implements Serializable{
    private InnerMobileCustomerResponse customer;
    private MobileBankClient[] clients;

    public MobileCustomerData() {
    }

    public InnerMobileCustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(InnerMobileCustomerResponse customer) {
        this.customer = customer;
    }

    public MobileBankClient[] getClients() {
        return clients;
    }

    public void setClients(MobileBankClient[] clients) {
        this.clients = clients;
    }
}
