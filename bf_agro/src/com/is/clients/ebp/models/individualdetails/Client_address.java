package com.is.clients.ebp.models.individualdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DEN on 12.04.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client_address {

    private String address_region;
    private String address_subregion;
    private String address_soato;
    private String zip_code;
    private String address;
    private String temporary_address;
    private String Phone;
    private String fax;
    private String email;
    private String mobile_phone;

    public Client_address() {
    }

    public Client_address(String address_region, String address_subregion, String address_soato, String zip_code, String address, String temporary_address, String phone, String fax, String email, String mobile_phone) {
        this.address_region = address_region;
        this.address_subregion = address_subregion;
        this.address_soato = address_soato;
        this.zip_code = zip_code;
        this.address = address;
        this.temporary_address = temporary_address;
        Phone = phone;
        this.fax = fax;
        this.email = email;
        this.mobile_phone = mobile_phone;
    }

    public String getAddress_region() {
        return address_region;
    }

    public void setAddress_region(String address_region) {
        this.address_region = address_region;
    }

    public String getAddress_subregion() {
        return address_subregion;
    }

    public void setAddress_subregion(String address_subregion) {
        this.address_subregion = address_subregion;
    }

    public String getAddress_soato() {
        return address_soato;
    }

    public void setAddress_soato(String address_soato) {
        this.address_soato = address_soato;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTemporary_address() {
        return temporary_address;
    }

    public void setTemporary_address(String temporary_address) {
        this.temporary_address = temporary_address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }
}
