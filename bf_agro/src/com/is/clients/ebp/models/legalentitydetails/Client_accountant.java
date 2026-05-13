package com.is.clients.ebp.models.legalentitydetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DEN on 12.04.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client_accountant {

    private String name_accountant;
    private String doc_type_accountant;
    private String doc_series_accountant;
    private String doc_number_accountant;
    private String doc_date_issue_accountant;
    private String doc_issuer_accountant;
    private String doc_date_expire_accountant;
    private String citizen_accountant;
    private String birth_date_accountant;
    private String birth_country_accountant;
    private String birth_place_accountant;
    private String address_accountant;

    public Client_accountant(String name_accountant, String doc_type_accountant, String doc_series_accountant, String doc_number_accountant, String doc_date_issue_accountant, String doc_issuer_accountant, String doc_date_expire_accountant, String citizen_accountant, String birth_date_accountant, String birth_country_accountant, String birth_place_accountant, String address_accountant) {
        this.name_accountant = name_accountant;
        this.doc_type_accountant = doc_type_accountant;
        this.doc_series_accountant = doc_series_accountant;
        this.doc_number_accountant = doc_number_accountant;
        this.doc_date_issue_accountant = doc_date_issue_accountant;
        this.doc_issuer_accountant = doc_issuer_accountant;
        this.doc_date_expire_accountant = doc_date_expire_accountant;
        this.citizen_accountant = citizen_accountant;
        this.birth_date_accountant = birth_date_accountant;
        this.birth_country_accountant = birth_country_accountant;
        this.birth_place_accountant = birth_place_accountant;
        this.address_accountant = address_accountant;
    }

    public Client_accountant() {
    }

    public String getName_accountant() {
        return name_accountant;
    }

    public void setName_accountant(String name_accountant) {
        this.name_accountant = name_accountant;
    }

    public String getDoc_type_accountant() {
        return doc_type_accountant;
    }

    public void setDoc_type_accountant(String doc_type_accountant) {
        this.doc_type_accountant = doc_type_accountant;
    }

    public String getDoc_series_accountant() {
        return doc_series_accountant;
    }

    public void setDoc_series_accountant(String doc_series_accountant) {
        this.doc_series_accountant = doc_series_accountant;
    }

    public String getDoc_number_accountant() {
        return doc_number_accountant;
    }

    public void setDoc_number_accountant(String doc_number_accountant) {
        this.doc_number_accountant = doc_number_accountant;
    }

    public String getDoc_date_issue_accountant() {
        return doc_date_issue_accountant;
    }

    public void setDoc_date_issue_accountant(String doc_date_issue_accountant) {
        this.doc_date_issue_accountant = doc_date_issue_accountant;
    }

    public String getDoc_issuer_accountant() {
        return doc_issuer_accountant;
    }

    public void setDoc_issuer_accountant(String doc_issuer_accountant) {
        this.doc_issuer_accountant = doc_issuer_accountant;
    }

    public String getDoc_date_expire_accountant() {
        return doc_date_expire_accountant;
    }

    public void setDoc_date_expire_accountant(String doc_date_expire_accountant) {
        this.doc_date_expire_accountant = doc_date_expire_accountant;
    }

    public String getCitizen_accountant() {
        return citizen_accountant;
    }

    public void setCitizen_accountant(String citizen_accountant) {
        this.citizen_accountant = citizen_accountant;
    }

    public String getBirth_date_accountant() {
        return birth_date_accountant;
    }

    public void setBirth_date_accountant(String birth_date_accountant) {
        this.birth_date_accountant = birth_date_accountant;
    }

    public String getBirth_country_accountant() {
        return birth_country_accountant;
    }

    public void setBirth_country_accountant(String birth_country_accountant) {
        this.birth_country_accountant = birth_country_accountant;
    }

    public String getBirth_place_accountant() {
        return birth_place_accountant;
    }

    public void setBirth_place_accountant(String birth_place_accountant) {
        this.birth_place_accountant = birth_place_accountant;
    }

    public String getAddress_accountant() {
        return address_accountant;
    }

    public void setAddress_accountant(String address_accountant) {
        this.address_accountant = address_accountant;
    }
}
