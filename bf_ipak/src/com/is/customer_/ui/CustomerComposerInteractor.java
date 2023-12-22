package com.is.customer_.ui;

import com.is.customer_.core.model.Customer;
import org.zkoss.zk.ui.Component;

import java.util.List;

/**
 * Created by root on 17.05.2017.
 * 15:43
 */
public interface CustomerComposerInteractor {
    void show(List<Customer> customers) throws Exception;

    void show(Customer customer) throws Exception;

    void create(Customer customer) throws Exception;

    Customer getCurrentCustomer() throws Exception;

}
