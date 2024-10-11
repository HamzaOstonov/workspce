package com.is.clients.ebp.models.individualdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DEN on 12.04.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {
    private String tin;
    private String client;
    private String client_key;
    private String pinfl;
    private String resident_code;
    private String country_code;
    private String gender;
    private String birth_date;
    private String birth_country;
    private String birth_place;
    private String subject_type;

    public Client() {
    }

    public Client(String tin, String client, String client_key, String pinfl, String resident_code, String country_code, String gender, String birth_date, String birth_country, String birth_place, String subject_type) {
        this.tin = tin;
        this.client = client;
        this.client_key = client_key;
        this.pinfl = pinfl;
        this.resident_code = resident_code;
        this.country_code = country_code;
        this.gender = gender;
        this.birth_date = birth_date;
        this.birth_country = birth_country;
        this.birth_place = birth_place;
        this.subject_type = subject_type;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClient_key() {
        return client_key;
    }

    public void setClient_key(String client_key) {
        this.client_key = client_key;
    }

    public String getPinfl() {
        return pinfl;
    }

    public void setPinfl(String pinfl) {
        this.pinfl = pinfl;
    }

    public String getResident_code() {
        return resident_code;
    }

    public void setResident_code(String resident_code) {
        this.resident_code = resident_code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getBirth_country() {
        return birth_country;
    }

    public void setBirth_country(String birth_country) {
        this.birth_country = birth_country;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public void setBirth_place(String birth_place) {
        this.birth_place = birth_place;
    }

    public String getSubject_type() {
        return subject_type;
    }

    public void setSubject_type(String subject_type) {
        this.subject_type = subject_type;
    }
}
