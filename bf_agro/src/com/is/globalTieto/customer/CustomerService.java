package com.is.globalTieto.customer;

import com.is.globalTieto.tietoModels.ListCustomersFilter;
import com.is.globalTieto.tietoModels.ListCustomersItem;
import com.is.globalTieto.tietoModels.ResponseInfoHolder;
import com.is.globalTieto.webServices.KapitalWebService;

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
