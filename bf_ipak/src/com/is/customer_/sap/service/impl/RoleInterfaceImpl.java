package com.is.customer_.sap.service.impl;

import com.is.customer_.ActionsEnum;
import com.is.customer_.core.model.Customer;
import com.is.customer_.sap.service.RoleInterface;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;

import relationships.NCI.com.ipakyulibank.BPContent;
import roles.NCI.com.ipakyulibank.RoleAccountsItem;
import roles.NCI.com.ipakyulibank.RolesByEventReqest;
import roles.NCI.com.ipakyulibank.RolesByEventResponceRelationshipsResponce;
import roles.NCI.com.ipakyulibank.RolesByEvent_OutProxy;

/**
 * Created by root on 22.05.2017.
 * 16:06
 */
public class RoleInterfaceImpl extends AbstractEndpointService implements RoleInterface{
    @Override
    public void createRole(Customer customer,String role) throws Exception {
        RolesByEvent_OutProxy roleProxy = new RolesByEvent_OutProxy(_rolesEndpoint, _username, _password);
        
        String[] events = {role};
        relationships.NCI.com.ipakyulibank.IdentNums[] identifications = {
                new relationships.NCI.com.ipakyulibank.IdentNums(customer.getP_type_document(),
                        null, customer.getP_passport_serial(),
                        customer.getP_passport_number()) };
        
        relationships.NCI.com.ipakyulibank.BPContent content = new BPContent(
                ActionsEnum.TYPE_BUSINESS_PARTNER.value(), customer.getId(),
                customer.getBranch(), getProfileAuthor(),
                identifications);

        RoleAccountsItem[] accounts = {
                new RoleAccountsItem("1", null, null, null) };

        RolesByEventResponceRelationshipsResponce[] response = roleProxy.rolesByEvent_Out(new RolesByEventReqest(
                content, events,null));

        if (response != null)
            for (int i = 0; i < response.length; i++) {
                if (!response[i].getStatus().equals("0")) {
                    throw new Exception(response[i].getStatus_details());
                }
            }
    }
    
    @Override
    public void createRoleForContactPerson(Customer customer,String role) throws Exception {
        RolesByEvent_OutProxy roleProxy = new RolesByEvent_OutProxy(_rolesEndpoint, _username, _password);
        
        String[] events = {role};
        relationships.NCI.com.ipakyulibank.IdentNums[] identifications = {
                new relationships.NCI.com.ipakyulibank.IdentNums(customer.getP_type_document(),
                        null, customer.getP_passport_serial(),
                        customer.getP_passport_number()) };
        
        relationships.NCI.com.ipakyulibank.BPContent content = new BPContent(
                ActionsEnum.TYPE_BUSINESS_PARTNER.value(), customer.getUnion_id(),
                customer.getBranch(), getProfileAuthor(),
                identifications);

        RoleAccountsItem[] accounts = {
                new RoleAccountsItem("1", null, null, null) };

        RolesByEventResponceRelationshipsResponce[] response = roleProxy.rolesByEvent_Out(new RolesByEventReqest(
                content, events,null));

        if (response != null)
            for (int i = 0; i < response.length; i++) {
                if (!response[i].getStatus().equals("0")) {
                    throw new Exception(response[i].getStatus_details());
                }
            }
    }
    
}
