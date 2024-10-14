package com.is.clients.ebp.models.individualdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DEN on 12.04.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client_static {

    private String property_form;
    private String legal_form;
    private String activity_type;
    private String activity_region;
    private String activity_subregion;
    private String activity_address;

    public Client_static(String property_form, String legal_form, String activity_type, String activity_region, String activity_subregion, String activity_address) {
        this.property_form = property_form;
        this.legal_form = legal_form;
        this.activity_type = activity_type;
        this.activity_region = activity_region;
        this.activity_subregion = activity_subregion;
        this.activity_address = activity_address;
    }

    public Client_static() {
    }

    public String getProperty_form() {
        return property_form;
    }

    public void setProperty_form(String property_form) {
        this.property_form = property_form;
    }

    public String getLegal_form() {
        return legal_form;
    }

    public void setLegal_form(String legal_form) {
        this.legal_form = legal_form;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getActivity_region() {
        return activity_region;
    }

    public void setActivity_region(String activity_region) {
        this.activity_region = activity_region;
    }

    public String getActivity_subregion() {
        return activity_subregion;
    }

    public void setActivity_subregion(String activity_subregion) {
        this.activity_subregion = activity_subregion;
    }

    public String getActivity_address() {
        return activity_address;
    }

    public void setActivity_address(String activity_address) {
        this.activity_address = activity_address;
    }
}
