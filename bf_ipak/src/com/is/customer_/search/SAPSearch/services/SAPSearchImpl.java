package com.is.customer_.search.SAPSearch.services;

import client.NCI.com.ipakyulibank.client_p.*;
import com.is.customer_.search.SAPSearch.model.Input;
import com.is.customer_.search.SAPSearch.model.Response;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19.05.2017.
 * 11:40
 */
@SuppressWarnings("Duplicates")
public class SAPSearchImpl extends AbstractEndpointService implements SAPSearch {

    @Override
    public List<Response> search(Input input) throws RemoteException {
        List<Response> list = new ArrayList<Response>();
        Customer_ReqestOutProxy proxy = new Customer_ReqestOutProxy(_customerEndpoint, _username, _password);
        Customer_ReqestOut customerOut = proxy.getCustomer_ReqestOut();
        BusinessPartnerSearchReqestReqest searchRequest = new BusinessPartnerSearchReqestReqest(
                input.getDocumentType(), input.getDocumentNumber(),
                input.getFirstName(),
                input.getMiddleName(),
                input.getLastName(), input.getBirthday());
        BusinessPartnerSearchReqestReqest[] search = { searchRequest };
        BusinessPartnerSearchResponce response = customerOut.search(search);
        BusinessPartnerResponceHeader[] results = response.getPartners();
        BigInteger count = response.getPartners_count();
        if (results != null) {
            for (int i = 0, length = results.length; i < length; i++) {
                BusinessPartnerResponceHeader result = results[i];
                Response response_ = new Response();
                response_.setCount(count.intValue());
                response_.setBranch(result.getBranch());
                response_.setBirthDay(result.getBirthday());
                response_.setLastNameGlobal(result.getSurname_global());
                response_.setFirstNameGlobal(result.getName_global());
                response_.setMiddleNameGlobal(result.getMid_name_global());
                response_.setLastNameLocal(result.getSurname_local());
                response_.setFirstNameLocal(result.getName_local());
                response_.setMiddleNameLocal(result.getMidname_local());
                response_.setNciId(result.getId_client_nci());
                response_.setSapId(result.getId_client_sap());
                list.add(response_);
            }
        }
        return list;
    }
}
