package com.is.customer_.sap.service;

import com.is.customer_.core.model.Customer;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by root on 09.05.2017.
 * 19:10
 */
public interface BusinessPartnerMappingInterface {
    List<Customer> getMapping(String branch, String id, String idSAP) throws RemoteException;
}
