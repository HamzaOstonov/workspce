package com.is.clients.ebp.models.legalentitydetails;
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

/**
 * Created by DEN on 12.04.2017.
 */
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client_registration {

    private String tax_organization_code;
    private String issuer_region;
    private String issuer_subregion;
    private String registration_date;
    private String registration_expire_date;
    private String registration_issuer;
    private String registration_document;

    public Client_registration(String tax_organization_code, String issuer_region, String issuer_subregion, String registration_date, String registration_expire_date, String registration_issuer, String registration_document) {
        this.tax_organization_code = tax_organization_code;
        this.issuer_region = issuer_region;
        this.issuer_subregion = issuer_subregion;
        this.registration_date = registration_date;
        this.registration_expire_date = registration_expire_date;
        this.registration_issuer = registration_issuer;
        this.registration_document = registration_document;
    }

    public Client_registration() {
    }

    public String getTax_organization_code() {
        return tax_organization_code;
    }

    public void setTax_organization_code(String tax_organization_code) {
        this.tax_organization_code = tax_organization_code;
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

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getRegistration_expire_date() {
        return registration_expire_date;
    }

    public void setRegistration_expire_date(String registration_expire_date) {
        this.registration_expire_date = registration_expire_date;
    }

    public String getRegistration_issuer() {
        return registration_issuer;
    }

    public void setRegistration_issuer(String registration_issuer) {
        this.registration_issuer = registration_issuer;
    }

    public String getRegistration_document() {
        return registration_document;
    }

    public void setRegistration_document(String registration_document) {
        this.registration_document = registration_document;
    }
}
