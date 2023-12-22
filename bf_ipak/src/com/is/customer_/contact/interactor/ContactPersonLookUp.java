package com.is.customer_.contact.interactor;

import com.is.customer_.core.model.Customer;

/**
 * Created by root on 04.06.2017.
 * 15:48
 */
public interface ContactPersonLookUp {
    Customer getPerson(String branch, String id, String idSAP);
}
