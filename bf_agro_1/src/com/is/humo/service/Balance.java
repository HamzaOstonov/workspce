// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "currency", "initialAmount", "bonusAmount", "creditLimit", "lockedBackofficeAmount", "lockedBackofficeAmountOffline", "lockedAmount", "availableAmount" })
public class Balance
{
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("initialAmount")
    private Integer initialAmount;
    @JsonProperty("bonusAmount")
    private Integer bonusAmount;
    @JsonProperty("creditLimit")
    private Integer creditLimit;
    @JsonProperty("lockedBackofficeAmount")
    private Integer lockedBackofficeAmount;
    @JsonProperty("lockedBackofficeAmountOffline")
    private Integer lockedBackofficeAmountOffline;
    @JsonProperty("lockedAmount")
    private Integer lockedAmount;
    @JsonProperty("availableAmount")
    private Integer availableAmount;
    
    public Balance() {
    }
    
    public Balance(final String currency, final Integer initialAmount, final Integer bonusAmount, final Integer creditLimit, final Integer lockedBackofficeAmount, final Integer lockedBackofficeAmountOffline, final Integer lockedAmount, final Integer availableAmount) {
        this.currency = currency;
        this.initialAmount = initialAmount;
        this.bonusAmount = bonusAmount;
        this.creditLimit = creditLimit;
        this.lockedBackofficeAmount = lockedBackofficeAmount;
        this.lockedBackofficeAmountOffline = lockedBackofficeAmountOffline;
        this.lockedAmount = lockedAmount;
        this.availableAmount = availableAmount;
    }
    
    @JsonProperty("currency")
    public String getCurrency() {
        return this.currency;
    }
    
    @JsonProperty("currency")
    public void setCurrency(final String currency) {
        this.currency = currency;
    }
    
    @JsonProperty("initialAmount")
    public Integer getInitialAmount() {
        return this.initialAmount;
    }
    
    @JsonProperty("initialAmount")
    public void setInitialAmount(final Integer initialAmount) {
        this.initialAmount = initialAmount;
    }
    
    @JsonProperty("bonusAmount")
    public Integer getBonusAmount() {
        return this.bonusAmount;
    }
    
    @JsonProperty("bonusAmount")
    public void setBonusAmount(final Integer bonusAmount) {
        this.bonusAmount = bonusAmount;
    }
    
    @JsonProperty("creditLimit")
    public Integer getCreditLimit() {
        return this.creditLimit;
    }
    
    @JsonProperty("creditLimit")
    public void setCreditLimit(final Integer creditLimit) {
        this.creditLimit = creditLimit;
    }
    
    @JsonProperty("lockedBackofficeAmount")
    public Integer getLockedBackofficeAmount() {
        return this.lockedBackofficeAmount;
    }
    
    @JsonProperty("lockedBackofficeAmount")
    public void setLockedBackofficeAmount(final Integer lockedBackofficeAmount) {
        this.lockedBackofficeAmount = lockedBackofficeAmount;
    }
    
    @JsonProperty("lockedBackofficeAmountOffline")
    public Integer getLockedBackofficeAmountOffline() {
        return this.lockedBackofficeAmountOffline;
    }
    
    @JsonProperty("lockedBackofficeAmountOffline")
    public void setLockedBackofficeAmountOffline(final Integer lockedBackofficeAmountOffline) {
        this.lockedBackofficeAmountOffline = lockedBackofficeAmountOffline;
    }
    
    @JsonProperty("lockedAmount")
    public Integer getLockedAmount() {
        return this.lockedAmount;
    }
    
    @JsonProperty("lockedAmount")
    public void setLockedAmount(final Integer lockedAmount) {
        this.lockedAmount = lockedAmount;
    }
    
    @JsonProperty("availableAmount")
    public Integer getAvailableAmount() {
        return this.availableAmount;
    }
    
    @JsonProperty("availableAmount")
    public void setAvailableAmount(final Integer availableAmount) {
        this.availableAmount = availableAmount;
    }
}
