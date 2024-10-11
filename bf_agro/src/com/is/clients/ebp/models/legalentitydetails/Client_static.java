package com.is.clients.ebp.models.legalentitydetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DEN on 12.04.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client_static {

    private String property_form;
    private String legal_form;
    private String oked;
    private String juridical_form;
    private String business_sign;
    private String header_tin;
    private String activity_region;
    private String activity_subregion;
    private String activity_address;

    public Client_static(String property_form, String legal_form, String oked, String juridical_form, String business_sign, String header_tin, String activity_region, String activity_subregion, String activity_address) {
        this.property_form = property_form;
        this.legal_form = legal_form;
        this.oked = oked;
        this.juridical_form = juridical_form;
        this.business_sign = business_sign;
        this.header_tin = header_tin;
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

    public String getOked() {
        return oked;
    }

    public void setOked(String oked) {
        this.oked = oked;
    }

    public String getJuridical_form() {
        return juridical_form;
    }

    public void setJuridical_form(String juridical_form) {
        this.juridical_form = juridical_form;
    }

    public String getBusiness_sign() {
        return business_sign;
    }

    public void setBusiness_sign(String business_sign) {
        this.business_sign = business_sign;
    }

    public String getHeader_tin() {
        return header_tin;
    }

    public void setHeader_tin(String header_tin) {
        this.header_tin = header_tin;
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
