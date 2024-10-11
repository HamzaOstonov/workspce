package com.is.clients.ebp.models.legalentitydetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DEN on 12.04.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client_founders {

    private String type_founder;
    private String capital_founder;
    private String date_registration_founder;
    private String name_founder;
    private String tin_founder;
    private String citizen_founder;
    private String juridical_form_founder;
    private String address_founder;
    private String doc_series_founder;
    private String doc_number_founder;
    private String doc_date_issue_founder;
    private String doc_issuer_founder;
    private String doc_type_accountant;
    private String doc_date_expire_accountant;

    public Client_founders(String type_founder, String capital_founder, String date_registration_founder, String name_founder, String tin_founder, String citizen_founder, String juridical_form_founder, String address_founder, String doc_series_founder, String doc_number_founder, String doc_date_issue_founder, String doc_issuer_founder) {
        this.type_founder = type_founder;
        this.capital_founder = capital_founder;
        this.date_registration_founder = date_registration_founder;
        this.name_founder = name_founder;
        this.tin_founder = tin_founder;
        this.citizen_founder = citizen_founder;
        this.juridical_form_founder = juridical_form_founder;
        this.address_founder = address_founder;
        this.doc_series_founder = doc_series_founder;
        this.doc_number_founder = doc_number_founder;
        this.doc_date_issue_founder = doc_date_issue_founder;
        this.doc_issuer_founder = doc_issuer_founder;
    }

    public Client_founders() {
    }


    public String getType_founder() {
        return type_founder;
    }

    public void setType_founder(String type_founder) {
        this.type_founder = type_founder;
    }

    public String getCapital_founder() {
        return capital_founder;
    }

    public void setCapital_founder(String capital_founder) {
        this.capital_founder = capital_founder;
    }

    public String getDate_registration_founder() {
        return date_registration_founder;
    }

    public void setDate_registration_founder(String date_registration_founder) {
        this.date_registration_founder = date_registration_founder;
    }

    public String getName_founder() {
        return name_founder;
    }

    public void setName_founder(String name_founder) {
        this.name_founder = name_founder;
    }

    public String getTin_founder() {
        return tin_founder;
    }

    public void setTin_founder(String tin_founder) {
        this.tin_founder = tin_founder;
    }

    public String getCitizen_founder() {
        return citizen_founder;
    }

    public void setCitizen_founder(String citizen_founder) {
        this.citizen_founder = citizen_founder;
    }

    public String getJuridical_form_founder() {
        return juridical_form_founder;
    }

    public void setJuridical_form_founder(String juridical_form_founder) {
        this.juridical_form_founder = juridical_form_founder;
    }

    public String getAddress_founder() {
        return address_founder;
    }

    public void setAddress_founder(String address_founder) {
        this.address_founder = address_founder;
    }

    public String getDoc_series_founder() {
        return doc_series_founder;
    }

    public void setDoc_series_founder(String doc_series_founder) {
        this.doc_series_founder = doc_series_founder;
    }

    public String getDoc_number_founder() {
        return doc_number_founder;
    }

    public void setDoc_number_founder(String doc_number_founder) {
        this.doc_number_founder = doc_number_founder;
    }

    public String getDoc_date_issue_founder() {
        return doc_date_issue_founder;
    }

    public void setDoc_date_issue_founder(String doc_date_issue_founder) {
        this.doc_date_issue_founder = doc_date_issue_founder;
    }

    public String getDoc_issuer_founder() {
        return doc_issuer_founder;
    }

    public void setDoc_issuer_founder(String doc_issuer_founder) {
        this.doc_issuer_founder = doc_issuer_founder;
    }

    public String getDoc_type_accountant() {
        return doc_type_accountant;
    }

    public void setDoc_type_accountant(String doc_type_accountant) {
        this.doc_type_accountant = doc_type_accountant;
    }

    public String getDoc_date_expire_accountant() {
        return doc_date_expire_accountant;
    }

    public void setDoc_date_expire_accountant(String doc_date_expire_accountant) {
        this.doc_date_expire_accountant = doc_date_expire_accountant;
    }
}
