package com.is.customer_.sap.service;

import com.is.customer_.sap.service.impl.*;

/**
 * Created by root on 09.05.2017.
 * 19:04
 */
public class SAPServiceFactory {
    private static SAPServiceFactory factory;

    private SAPServiceFactory(){}

    public static SAPServiceFactory getInstance(){
        if (factory == null)
            factory = new SAPServiceFactory();
        return factory;
    }

    public BusinessPartnerInterface getBusinessPartnerService(){
        return new SAPBusinessPartnerImpl();
    }

    public SearchBusinessPartnerInterface getSearchService(){
        return new SAPSearchBusinessPartnerImpl();
    }

    public BusinessPartnerMappingInterface getMappingService(){
        return new SAPBusinessPartnerMapping();
    }

    public AttachmentInterface getAttachmentService(){
        return new SAPAttachmentInterfaceImpl();
    }

    public RoleInterface getRoleService(){
        return new RoleInterfaceImpl();
    }

    public RelationshipGateway getRelationshipGateway(){
        return new RelationshipGatewayImpl();
    }

    public CustomerGatewayDecorator getCustomerGatewayDecorator(){
        return new CustomerGatewayDecoratorImpl();
    }
}
