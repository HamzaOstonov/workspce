// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "state", "phone", "message" })
public class Mb
{
    @JsonProperty("state")
    private String state;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("message")
    private String message;
    
    public Mb() {
    }
    
    public Mb(final String state, final String phone, final String message) {
        this.state = state;
        this.phone = phone;
        this.message = message;
    }
    
    @JsonProperty("state")
    public String getState() {
        return this.state;
    }
    
    @JsonProperty("state")
    public void setState(final String state) {
        this.state = state;
    }
    
    @JsonProperty("phone")
    public String getPhone() {
        return this.phone;
    }
    
    @JsonProperty("phone")
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    
    @JsonProperty("message")
    public String getMessage() {
        return this.message;
    }
    
    @JsonProperty("message")
    public void setMessage(final String message) {
        this.message = message;
    }
}
