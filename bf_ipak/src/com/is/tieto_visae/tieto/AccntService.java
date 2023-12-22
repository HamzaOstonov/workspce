package com.is.tieto_visae.tieto;

import java.util.ArrayList;


public class AccntService {
    private KapitalWebService service;

    public AccntService() {
        service = new KapitalWebService();
    }

    public ArrayList<ListAccountsItem> listAccounts(ListAccountsFilter filter, ResponseInfoHolder response) {
        ArrayList<ListAccountsItem> result;

        result = service.tietoListAccounts(filter, response);

        return result;
    }

    public ArrayList<ListAccountsItem> listAccounts(ListCustomersItem filter, ResponseInfoHolder response) {
        ArrayList<ListAccountsItem> result;

        ListAccountsFilter rightFilter = KapitalModelTransformer.getAccFilterByCustomer(filter);
        result = listAccounts(rightFilter, response);

        return result;
    }
}
