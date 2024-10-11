package com.is.clients.ebp.models.legalentitydetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DEN on 12.04.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {

    private String tin;
    private String subject_type;
    private String citizenship_code;
    private String residency_code;

    public Client(String tin, String subject_type, String citizenship_code) {
        this.tin = tin;
        this.subject_type = subject_type;
        this.citizenship_code = citizenship_code;
    }

    public Client() {
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getSubject_type() {
        return subject_type;
    }

    public void setSubject_type(String subject_type) {
        this.subject_type = subject_type;
    }

    public String getCitizenship_code() {
        return citizenship_code;
    }

    public void setCitizenship_code(String citizenship_code) {
        this.citizenship_code = citizenship_code;
    }

    public String getResidency_code() {
        return residency_code;
    }

    public void setResidency_code(String residency_code) {
        this.residency_code = residency_code;
    }
}
