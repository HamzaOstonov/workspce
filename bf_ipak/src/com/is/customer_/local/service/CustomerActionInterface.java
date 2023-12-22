package com.is.customer_.local.service;

import com.is.customer_.core.model.Customer;

import java.sql.Connection;

/**
 * Created by root on 17.07.2017.
 * 11:57
 */
public interface CustomerActionInterface {
    Customer getCustomer(String branch, String id);

    Customer getCustomer(Connection c, String branch, String id);

    Customer getCustomer(Connection c, String branch, String id, String code_subject);

    Customer getCustomer(String branch, String id, String code_subject);

    Customer executeAction(Customer actionCustomer_, int action) throws Exception;
    
    String executeActionOtherBranch(Customer actionCustomer_, int action) throws Exception;

    Customer executeActionC(Connection c,Customer actionCustomer_, int action) throws Exception;
    
    String executeActionOtherBranchC(Connection c,Customer actionCustomer_, int action) throws Exception;

    Customer synchronize(Customer actionCustomer) throws Exception;
}
