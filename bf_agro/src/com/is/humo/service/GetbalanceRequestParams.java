// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "primaryAccountNumber", "mb_flag" })
public class GetbalanceRequestParams
{
    @JsonProperty("primaryAccountNumber")
    private String primaryAccountNumber;
    @JsonProperty("mb_flag")
    private Integer mbFlag;
    
    public GetbalanceRequestParams() {
    }
    
    public GetbalanceRequestParams(final String primaryAccountNumber, final Integer mbFlag) {
        this.primaryAccountNumber = primaryAccountNumber;
        this.mbFlag = mbFlag;
    }
    
    @JsonProperty("primaryAccountNumber")
    public String getPrimaryAccountNumber() {
        return this.primaryAccountNumber;
    }
    
    @JsonProperty("primaryAccountNumber")
    public void setPrimaryAccountNumber(final String primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
    }
    
    @JsonProperty("mb_flag")
    public Integer getMbFlag() {
        return this.mbFlag;
    }
    
    @JsonProperty("mb_flag")
    public void setMbFlag(final Integer mbFlag) {
        this.mbFlag = mbFlag;
    }
}
