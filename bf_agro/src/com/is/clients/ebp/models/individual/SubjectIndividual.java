package com.is.clients.ebp.models.individual;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DEN on 27.03.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectIndividual {

    private String branch;
    private String tin;
    private String residency_code;
    private String citizenship_code;
    private String lastname;
    private String firstname;
    private String middlename;
    private String address_region;
    private String address_subregion;
    private String address_soato;
    private String property_form;
    private String legal_form;
    private String registration_issuer;
    private String registration_doc_number;
    private String registration_date;
    private String issuer_region;
    private String issuer_subregion;
    private String registration_expire_date;
    private String subject_type;

    public SubjectIndividual() {
    }

    public SubjectIndividual(String branch, String tin, String residency_code, String citizenship_code,
                             String lastname, String firstname, String middlename,
                             String address_region, String address_subregion,
                             String address_soato, String property_form, String legal_form,
                             String registration_issuer, String registration_doc_number,
                             String registration_date, String issuer_region,
                             String issuer_subregion, String registration_expire_date,
                             String subject_type) {
        super();
        this.branch = branch;
        this.tin = tin;
        this.residency_code = residency_code;
        this.citizenship_code = citizenship_code;
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.address_region = address_region;
        this.address_subregion = address_subregion;
        this.address_soato = address_soato;
        this.property_form = property_form;
        this.legal_form = legal_form;
        this.registration_issuer = registration_issuer;
        this.registration_doc_number = registration_doc_number;
        this.registration_date = registration_date;
        this.issuer_region = issuer_region;
        this.issuer_subregion = issuer_subregion;
        this.registration_expire_date = registration_expire_date;
        this.subject_type = subject_type;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getResidency_code() {
        return residency_code;
    }

    public void setResidency_code(String residency_code) {
        this.residency_code = residency_code;
    }

    public String getCitizenship_code() {
        return citizenship_code;
    }

    public void setCitizenship_code(String citizenship_code) {
        this.citizenship_code = citizenship_code;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
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

    public String getRegistration_issuer() {
        return registration_issuer;
    }

    public void setRegistration_issuer(String registration_issuer) {
        this.registration_issuer = registration_issuer;
    }

    public String getRegistration_doc_number() {
        return registration_doc_number;
    }

    public void setRegistration_doc_number(String registration_doc_number) {
        this.registration_doc_number = registration_doc_number;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getIssuer_region() {
        return issuer_region;
    }

    public void setIssuer_region(String issuer_region) {
        this.issuer_region = issuer_region;
    }

    public String getIssuer_subregion() {
        return issuer_subregion;
    }

    public void setIssuer_subregion(String issuer_subregion) {
        this.issuer_subregion = issuer_subregion;
    }

    public String getRegistration_expire_date() {
        return registration_expire_date;
    }

    public void setRegistration_expire_date(String registration_expire_date) {
        this.registration_expire_date = registration_expire_date;
    }

    public String getSubject_type() {
        return subject_type;
    }

    public void setSubject_type(String subject_type) {
        this.subject_type = subject_type;
    }
}
