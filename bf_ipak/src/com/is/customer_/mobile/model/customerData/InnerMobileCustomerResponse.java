package com.is.customer_.mobile.model.customerData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


/**
 * Created by Dev1 on 14.11.2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class InnerMobileCustomerResponse {
    @JsonProperty("Id")
    private long id;
    @JsonProperty("UserName")
    private String username;
    @JsonProperty("Phone")
    private String phone;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("State")
    private String state;
    @JsonProperty("RegistrationDate")
    private Date registrationDate;

    public InnerMobileCustomerResponse() {
    }

    //@JsonProperty("Id")
    public long getId() {
        return id;
    }

    //@JsonProperty("Id")
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    //@JsonProperty("UserName")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    //@JsonProperty("Phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("Email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
