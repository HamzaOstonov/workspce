package com.is.customer;

import java.util.List;

/**
 * Created by root on 31.05.2017.
 * 18:14
 */
public class SAPDuplicationException extends SAPException{
    private final List<Customer> list;

    public SAPDuplicationException(String message, List<Customer> list) {
        super(message);
        this.list = list;
    }

    public List<Customer> getList(){
        return this.list;
    }
}
