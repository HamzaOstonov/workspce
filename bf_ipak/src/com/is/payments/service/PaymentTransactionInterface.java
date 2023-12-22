package com.is.payments.service;

import com.is.payments.entity.Payment;
import general.General;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by root on 27.05.2017.
 * 14:47
 */
public interface PaymentTransactionInterface {
    List<General> commitTransaction(Payment payment) throws SQLException, Exception;
}
