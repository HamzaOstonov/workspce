package com.is.globalTieto.account;

import com.is.globalTieto.modelTransform.KapitalModelTransformer;
import com.is.globalTieto.tietoModels.ListAccountsFilter;
import com.is.globalTieto.tietoModels.ListAccountsItem;
import com.is.globalTieto.tietoModels.ListCustomersItem;
import com.is.globalTieto.tietoModels.ResponseInfoHolder;
import com.is.globalTieto.webServices.KapitalWebService;

import java.util.ArrayList;

public class AccountService {
    private KapitalWebService service;

    public AccountService() {
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
