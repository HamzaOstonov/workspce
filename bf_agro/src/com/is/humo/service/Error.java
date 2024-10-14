// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error
{
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;
    
    public Error() {
    }
    
    public Error(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }
    
    @JsonProperty("code")
    public Integer getCode() {
        return this.code;
    }
    
    @JsonProperty("code")
    public void setCode(final Integer code) {
        this.code = code;
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
