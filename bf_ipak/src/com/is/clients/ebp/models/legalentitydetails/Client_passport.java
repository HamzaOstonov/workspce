package com.is.clients.ebp.models.legalentitydetails;
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

/**
 * Created by DEN on 12.04.2017.
 */
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client_passport {
    private String name_director;
    private String doc_type_director;
    private String doc_series_director;
    private String doc_number_director;
    private String doc_date_issue_director;
    private String doc_issuer_director;
    private String doc_date_expire_director;
    private String citizen_director;
    private String birth_date_director;
    private String birth_country_director;
    private String birth_place_director;
    private String address_director;

    public Client_passport(String name_director, String doc_type_director, String doc_series_director, String doc_number_director, String doc_date_issue_director, String doc_issuer_director, String doc_date_expire_director, String citizen_director, String birth_date_director, String birth_country_director, String birth_place_director, String address_director) {
        this.name_director = name_director;
        this.doc_type_director = doc_type_director;
        this.doc_series_director = doc_series_director;
        this.doc_number_director = doc_number_director;
        this.doc_date_issue_director = doc_date_issue_director;
        this.doc_issuer_director = doc_issuer_director;
        this.doc_date_expire_director = doc_date_expire_director;
        this.citizen_director = citizen_director;
        this.birth_date_director = birth_date_director;
        this.birth_country_director = birth_country_director;
        this.birth_place_director = birth_place_director;
        this.address_director = address_director;
    }

    public Client_passport() {
    }

    public String getName_director() {
        return name_director;
    }

    public void setName_director(String name_director) {
        this.name_director = name_director;
    }

    public String getDoc_type_director() {
        return doc_type_director;
    }

    public void setDoc_type_director(String doc_type_director) {
        this.doc_type_director = doc_type_director;
    }

    public String getDoc_series_director() {
        return doc_series_director;
    }

    public void setDoc_series_director(String doc_series_director) {
        this.doc_series_director = doc_series_director;
    }

    public String getDoc_number_director() {
        return doc_number_director;
    }

    public void setDoc_number_director(String doc_number_director) {
        this.doc_number_director = doc_number_director;
    }

    public String getDoc_date_issue_director() {
        return doc_date_issue_director;
    }

    public void setDoc_date_issue_director(String doc_date_issue_director) {
        this.doc_date_issue_director = doc_date_issue_director;
    }

    public String getDoc_issuer_director() {
        return doc_issuer_director;
    }

    public void setDoc_issuer_director(String doc_issuer_director) {
        this.doc_issuer_director = doc_issuer_director;
    }

    public String getDoc_date_expire_director() {
        return doc_date_expire_director;
    }

    public void setDoc_date_expire_director(String doc_date_expire_director) {
        this.doc_date_expire_director = doc_date_expire_director;
    }

    public String getCitizen_director() {
        return citizen_director;
    }

    public void setCitizen_director(String citizen_director) {
        this.citizen_director = citizen_director;
    }

    public String getBirth_date_director() {
        return birth_date_director;
    }

    public void setBirth_date_director(String birth_date_director) {
        this.birth_date_director = birth_date_director;
    }

    public String getBirth_country_director() {
        return birth_country_director;
    }

    public void setBirth_country_director(String birth_country_director) {
        this.birth_country_director = birth_country_director;
    }

    public String getBirth_place_director() {
        return birth_place_director;
    }

    public void setBirth_place_director(String birth_place_director) {
        this.birth_place_director = birth_place_director;
    }

    public String getAddress_director() {
        return address_director;
    }

    public void setAddress_director(String address_director) {
        this.address_director = address_director;
    }
}
