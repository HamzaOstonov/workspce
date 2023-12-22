package com.is.clients.controllers;

import com.is.customer_.core.model.Customer;
import com.is.customer_.ui.ComponentProducer;
import com.is.customer_.ui.CustomerComposerInteractor;
import com.is.customer_.ui.CustomerWindowFacade;

public class ContactSearchFacade extends CustomerWindowFacade{

    public ContactSearchFacade(ComponentProducer producer,
                               CustomerComposerInteractor interactor) {
        super(producer, interactor);
    }


    @Override
    public void create(Customer customer){
        return;
    }
}
