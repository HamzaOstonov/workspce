package com.is.customer_.sap;

/**
 * Created by root on 01.05.2017.
 * 17:36
 */
public enum PARTNER_TYPES {
    CUSTOMER("01"),
    CONTACT_PERSON("04"),
    POTENTIAL_CUSTOMER("03");

    private final String value;

    PARTNER_TYPES(String value) {
    this.value = value;
    }

    public String value(){
        return this.value;
    }
}
