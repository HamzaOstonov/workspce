package com.is.tieto_visa.tieto;

import java.util.ArrayList;

public class CustomerService {
    private KapitalWebService service;

    public CustomerService() {
        service = new KapitalWebService();
    }

    public ArrayList<ListCustomersItem> listCustomers(ListCustomersFilter filter, ResponseInfoHolder response) {
        ArrayList<ListCustomersItem> result;

        result = service.tietoListCustomers(filter, response);

        return result;
    }
}
