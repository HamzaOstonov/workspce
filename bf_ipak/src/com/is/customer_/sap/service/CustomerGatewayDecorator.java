package com.is.customer_.sap.service;

import com.is.customer_.core.model.Customer;

/**
 * Created by root on 07.06.2017.
 * 17:58
 */
public interface CustomerGatewayDecorator {
    String process(Customer customer) throws Exception;

    // 05-03-2018
    Customer create(Customer customer) throws Exception;

    Customer create(Customer customer, String operation, String origin) throws Exception;
}
