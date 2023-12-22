package com.is.customer_.sap.service.impl;

import client.NCI.com.ipakyulibank.client_p.*;
import com.is.customer_.core.model.Customer;
import com.is.customer_.sap.service.SearchBusinessPartnerInterface;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 09.05.2017.
 * 19:07
 */
public class SAPSearchBusinessPartnerImpl extends AbstractEndpointService implements SearchBusinessPartnerInterface{
    @Override
    public List<Customer> search(Customer customer) throws RemoteException {
        List<Customer> list = new ArrayList<Customer>();
        Customer_ReqestOutProxy proxy = new Customer_ReqestOutProxy(_customerEndpoint, _username, _password);
        Customer_ReqestOut customerOut = proxy.getCustomer_ReqestOut();
        BusinessPartnerSearchReqestReqest searchRequest = new BusinessPartnerSearchReqestReqest(
                customer.getP_type_document(),
                customer.getP_passport_number(),
                customer.getP_first_name(),
                customer.getP_patronymic(),
                customer.getP_family(),
                customer.getP_birthday());

        BusinessPartnerSearchReqestReqest[] search = { searchRequest };
        BusinessPartnerSearchResponce response = customerOut.search(search);
        BusinessPartnerResponceHeader[] results = response.getPartners();
        BigInteger count = response.getPartners_count();
        if (results != null) {
            for (int i = 0, length = results.length; i < length; i++) {
                BusinessPartnerResponceHeader result = results[i];
                Customer response_ = new Customer();
                response_.setP_birthday(result.getBirthday());
                response_.setP_family(result.getSurname_global());
                response_.setP_first_name(result.getName_global());
                response_.setP_patronymic(result.getMid_name_global());
                response_.setP_family_local(result.getSurname_local());
                response_.setP_first_name_local(result.getName_local());
                response_.setP_patronymic_local(result.getMidname_local());
                response_.setIdSap(result.getId_client_sap());
                list.add(response_);
            }
        }
        return list;
    }
}
