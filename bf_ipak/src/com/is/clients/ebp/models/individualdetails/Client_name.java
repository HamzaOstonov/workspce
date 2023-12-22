package com.is.clients.ebp.models.individualdetails;
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

/**
 * Created by DEN on 12.04.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Client_name {

    private String lastname;
    private String firstname;
    private String middlename;

    public Client_name() {
    }

    public Client_name(String lastname, String firstname, String middlename) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
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
}
