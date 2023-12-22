package com.is.customer_.mobile.model.customerData;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Dev1 on 14.11.2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class MobileCustomerDataResponse implements Serializable{
    @JsonProperty("Data")
    private MobileCustomerData data;

    private int res;

    private String err;

    public MobileCustomerDataResponse() {
    }

    public MobileCustomerData getData() {
        return data;
    }

    public void setData(MobileCustomerData data) {
        this.data = data;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
