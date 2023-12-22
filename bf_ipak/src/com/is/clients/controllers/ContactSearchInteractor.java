package com.is.clients.controllers;

import com.is.customer_.core.model.Customer;
import com.is.customer_.ui.CustomerComposerInteractor;

import java.util.List;

public class ContactSearchInteractor implements CustomerComposerInteractor{
    @Override
    public void show(List<Customer> customers) throws Exception {

    }

    @Override
    public void show(Customer customer) throws Exception {

    }

    @Override
    public void create(Customer customer) throws Exception {
        return;
    }

    @Override
    public Customer getCurrentCustomer() throws Exception {
        return null;
    }
}
