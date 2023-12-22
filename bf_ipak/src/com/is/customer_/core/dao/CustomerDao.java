package com.is.customer_.core.dao;

import com.is.customer_.core.model.Customer;

import java.sql.SQLException;

/**
 * Created by root on 08.05.2017.
 * 17:57
 */
public interface CustomerDao {
    Customer getCustomer(String branch, String id) throws CustomerDaoException, SQLException;

    Customer createCustomer(Customer customer) throws CustomerDaoException, SQLException;

    void updateCustomer(Customer customer) throws CustomerDaoException, SQLException;

    void deleteCustomer(Customer customer) throws CustomerDaoException, SQLException;
}
