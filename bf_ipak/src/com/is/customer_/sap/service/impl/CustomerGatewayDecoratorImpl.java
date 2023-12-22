package com.is.customer_.sap.service.impl;

import com.is.customer_.core.model.Customer;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.CustomerGatewayDecorator;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.sap.service.SearchBusinessPartnerInterface;

/**
 * Created by root on 07.06.2017.
 * 17:58
 */
public class CustomerGatewayDecoratorImpl implements CustomerGatewayDecorator {
    private BusinessPartnerInterface businessPartnerGateway = SAPServiceFactory.getInstance().getBusinessPartnerService();
    private SearchBusinessPartnerInterface searchGateway = SAPServiceFactory.getInstance().getSearchService();

    @Override
    public String process(Customer customer) throws Exception {
        String idSAP = customer.getIdSap();
        //customer.setIdSap(null); //2017-12-21 //2018-02-08 HAMZA
        try {
            /*if (idSAP == null){
                Customer customer_ = businessPartnerGateway.get(customer.getBranch(),customer.getId(),null);
                if (customer_ != null && customer_.getIdSap() != null)
                    customer.setIdSap(customer_.getIdSap());
            }*/

            idSAP = businessPartnerGateway.create(customer).getIdSap();
            /*if (idSAP != null)
                businessPartnerGateway.update(customer);
            else {
                *//*Customer customer_ = businessPartnerGateway.get(customer.getBranch(),customer.getId(),null);
                idSAP = customer_ != null ? customer_.getIdSap() : null;
                if (idSAP == null)
                    idSAP = businessPartnerGateway.create(customer).getIdSap();
                else {
                    customer.setIdSap(idSAP);
                    businessPartnerGateway.update(customer);
                }*//*
                Customer customer_ = businessPartnerGateway.get(customer.getBranch(),customer.getId(),null);
                idSAP = customer_ != null ? customer_.getIdSap() : null;
                if (idSAP == null)
                    idSAP = businessPartnerGateway.create(customer).getIdSap();
                else {
                    businessPartnerGateway.update(customer);
                }
                ISLogger.getLogger().error("Gateway IDSAP " + idSAP);
            }*/
        } catch (Exception e) {
            throw e;
        }
        return idSAP;
    }

    // 05-03-2018
    @Override
    public Customer create(Customer customer) throws Exception {
        Customer customer_ = businessPartnerGateway.create(customer);
        if (customer_ != null)
            customer.setIdSap(customer_.getIdSap());
        return customer;
    }

    @Override
    public Customer create(Customer customer, String operation, String origin) throws Exception {
        Customer customer_ = businessPartnerGateway.create(customer, operation, origin);
        if (customer_ != null)
            customer.setIdSap(customer_.getIdSap());
        return customer;
    }
}
