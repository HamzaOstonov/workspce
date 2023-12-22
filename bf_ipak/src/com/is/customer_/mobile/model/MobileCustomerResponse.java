package com.is.customer_.mobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Dev1 on 16.11.2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileCustomerResponse {
    private int res;
    private String err;

    public int getRes() {
        return res;
    }

    public String getErr() {
        return err;
    }

    public boolean isSuccessful(){
        return res == 0;
    }
}
