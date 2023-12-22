package com.is.customer_.sap.service;

import java.rmi.RemoteException;

import com.is.customer_.core.model.Customer;
import com.is.customer_.sap.service.exception.SAPException;

/**
 * Created by root on 09.05.2017.
 * 16:55
 */
public interface BusinessPartnerInterface {
    Customer create(Customer customer) throws SAPException,RemoteException;

    void update(Customer customer) throws SAPException,RemoteException;

    Customer get(String branch, String id, String idSAP) throws SAPException,RemoteException;

    // 05-03-2018
    Customer create (Customer customer, String operation, String origin) throws SAPException, RemoteException;

    // 05-03-2018
    void update (Customer customer, String operation, String origin) throws RemoteException, SAPException;
}
