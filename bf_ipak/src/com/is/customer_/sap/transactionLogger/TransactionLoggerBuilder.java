package com.is.customer_.sap.transactionLogger;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;

/**
 * Created by root on 10.05.2017.
 * 13:27
 */
public class TransactionLoggerBuilder {
    private SessionAttributes sessionAttributes;
    private Customer customer;
    private int action;
    private int state;
    private String customer_type;
    private String message;

    public TransactionLoggerBuilder setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public TransactionLoggerBuilder setSessionAttributes(SessionAttributes sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
        return this;
    }

    public TransactionLoggerBuilder setAction(int action) {
        this.action = action;
        return this;
    }

    public TransactionLoggerBuilder setState(int state) {
        this.state = state;
        return this;
    }

    public TransactionLoggerBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public TransactionLoggerBuilder setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
        return this;
    }

    public TransactionLogger build() {
        return new TransactionLogger(sessionAttributes,customer,action, state, customer_type,message);
    }
}
