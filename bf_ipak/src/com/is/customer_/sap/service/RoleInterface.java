package com.is.customer_.sap.service;

import com.is.customer_.core.model.Customer;

/**
 * Created by root on 22.05.2017.
 * 16:06
 */
public interface RoleInterface {
    void createRole(Customer customer,String role) throws Exception;
    void createRoleForContactPerson(Customer customer,String role) throws Exception;
}
