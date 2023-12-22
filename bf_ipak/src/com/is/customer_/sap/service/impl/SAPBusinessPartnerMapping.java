package com.is.customer_.sap.service.impl;

import client.NCI.com.ipakyulibank.client_p.*;
import com.is.customer_.core.model.Customer;
import com.is.customer_.sap.EmergencyMode;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;
import com.is.customer_.sap.service.BusinessPartnerMappingInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 09.05.2017.
 * 19:14
 */
public class SAPBusinessPartnerMapping extends SAPBusinessPartnerImpl
        implements BusinessPartnerMappingInterface{

    @Override
    public List<Customer> getMapping(String branch, String id, String idSAP) throws RemoteException {
        List<Customer> list = new ArrayList<Customer>();

        if (EmergencyMode.isTrue)
            return list;

        Customer_ReqestOutProxy proxy = new Customer_ReqestOutProxy(_customerEndpoint, _username, _password);

        Customer_ReqestOut customerRequest = proxy.getCustomer_ReqestOut();

        BusinessParnerComplex businessPartnerComplex = customerRequest
                .getDetails(new BusinessPartnerContentReqest(id, branch, idSAP));

        String sap_Id = businessPartnerComplex.getGeneral().getId_client_sap();
        BusinessPartnerNCI[] bpList = businessPartnerComplex.getBp_list();
        if (bpList != null){
            for (int i = 0; i < bpList.length; i++){
                Customer customer = mapContentToCustomer(businessPartnerComplex.getGeneral());
                customer.setBranch(bpList[i].getBranch());
                customer.setId(bpList[i].getClient_id());
                customer.setIdSap(sap_Id);
                list.add(customer);
            }
        }
        return list;
    }
}
