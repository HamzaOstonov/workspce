package com.is.payments.dao;

import com.is.customer_.core.model.Customer;
import com.is.payments.entity.Payment;
import general.General;

import java.util.List;

/**
 * Created by root on 27.05.2017.
 * 14:41
 */
public interface TransactionDao_ {
    List<General> commitTransaction(Payment payment) throws Exception;

    void attachPaymentToCustomer(Customer customer) throws Exception;
}
