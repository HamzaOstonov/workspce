package com.is.tieto_visae.tieto;

import java.util.ArrayList;

public class CustomirService {
    private KapitalWebService service;

    public CustomirService() {
        service = new KapitalWebService();
    }

    public ArrayList<ListCustomersItem> listCustomers(ListCustomersFilter filter, ResponseInfoHolder response) {
        ArrayList<ListCustomersItem> result;

        result = service.tietoListCustomers(filter, response);

        return result;
    }
}
