// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "institutionId", "accountId", "bankAccountId", "currency", "cardholderId", "effectiveDate", "updateDate", "accountType", "initialAmount", "lockedBackofficeAmount", "lockTime", "shadowAmount", "commissionGroup", "priority", "status", "availableAmount" })
public class Account
{
    @JsonProperty("institutionId")
    private String institutionId;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("bankAccountId")
    private String bankAccountId;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("cardholderId")
    private String cardholderId;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("updateDate")
    private String updateDate;
    @JsonProperty("accountType")
    private String accountType;
    @JsonProperty("initialAmount")
    private Integer initialAmount;
    @JsonProperty("lockedBackofficeAmount")
    private Integer lockedBackofficeAmount;
    @JsonProperty("lockTime")
    private String lockTime;
    @JsonProperty("shadowAmount")
    private Integer shadowAmount;
    @JsonProperty("commissionGroup")
    private String commissionGroup;
    @JsonProperty("priority")
    private Integer priority;
    @JsonProperty("status")
    private String status;
    @JsonProperty("availableAmount")
    private Integer availableAmount;
    
    public Account() {
    }
    
    public Account(final String institutionId, final String accountId, final String bankAccountId, final String currency, final String cardholderId, final String effectiveDate, final String updateDate, final String accountType, final Integer initialAmount, final Integer lockedBackofficeAmount, final String lockTime, final Integer shadowAmount, final String commissionGroup, final Integer priority, final String status, final Integer availableAmount) {
        this.institutionId = institutionId;
        this.accountId = accountId;
        this.bankAccountId = bankAccountId;
        this.currency = currency;
        this.cardholderId = cardholderId;
        this.effectiveDate = effectiveDate;
        this.updateDate = updateDate;
        this.accountType = accountType;
        this.initialAmount = initialAmount;
        this.lockedBackofficeAmount = lockedBackofficeAmount;
        this.lockTime = lockTime;
        this.shadowAmount = shadowAmount;
        this.commissionGroup = commissionGroup;
        this.priority = priority;
        this.status = status;
        this.availableAmount = availableAmount;
    }
    
    @JsonProperty("institutionId")
    public String getInstitutionId() {
        return this.institutionId;
    }
    
    @JsonProperty("institutionId")
    public void setInstitutionId(final String institutionId) {
        this.institutionId = institutionId;
    }
    
    @JsonProperty("accountId")
    public String getAccountId() {
        return this.accountId;
    }
    
    @JsonProperty("accountId")
    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }
    
    @JsonProperty("bankAccountId")
    public String getBankAccountId() {
        return this.bankAccountId;
    }
    
    @JsonProperty("bankAccountId")
    public void setBankAccountId(final String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
    
    @JsonProperty("currency")
    public String getCurrency() {
        return this.currency;
    }
    
    @JsonProperty("currency")
    public void setCurrency(final String currency) {
        this.currency = currency;
    }
    
    @JsonProperty("cardholderId")
    public String getCardholderId() {
        return this.cardholderId;
    }
    
    @JsonProperty("cardholderId")
    public void setCardholderId(final String cardholderId) {
        this.cardholderId = cardholderId;
    }
    
    @JsonProperty("effectiveDate")
    public String getEffectiveDate() {
        return this.effectiveDate;
    }
    
    @JsonProperty("effectiveDate")
    public void setEffectiveDate(final String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    @JsonProperty("updateDate")
    public String getUpdateDate() {
        return this.updateDate;
    }
    
    @JsonProperty("updateDate")
    public void setUpdateDate(final String updateDate) {
        this.updateDate = updateDate;
    }
    
    @JsonProperty("accountType")
    public String getAccountType() {
        return this.accountType;
    }
    
    @JsonProperty("accountType")
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }
    
    @JsonProperty("initialAmount")
    public Integer getInitialAmount() {
        return this.initialAmount;
    }
    
    @JsonProperty("initialAmount")
    public void setInitialAmount(final Integer initialAmount) {
        this.initialAmount = initialAmount;
    }
    
    @JsonProperty("lockedBackofficeAmount")
    public Integer getLockedBackofficeAmount() {
        return this.lockedBackofficeAmount;
    }
    
    @JsonProperty("lockedBackofficeAmount")
    public void setLockedBackofficeAmount(final Integer lockedBackofficeAmount) {
        this.lockedBackofficeAmount = lockedBackofficeAmount;
    }
    
    @JsonProperty("lockTime")
    public String getLockTime() {
        return this.lockTime;
    }
    
    @JsonProperty("lockTime")
    public void setLockTime(final String lockTime) {
        this.lockTime = lockTime;
    }
    
    @JsonProperty("shadowAmount")
    public Integer getShadowAmount() {
        return this.shadowAmount;
    }
    
    @JsonProperty("shadowAmount")
    public void setShadowAmount(final Integer shadowAmount) {
        this.shadowAmount = shadowAmount;
    }
    
    @JsonProperty("commissionGroup")
    public String getCommissionGroup() {
        return this.commissionGroup;
    }
    
    @JsonProperty("commissionGroup")
    public void setCommissionGroup(final String commissionGroup) {
        this.commissionGroup = commissionGroup;
    }
    
    @JsonProperty("priority")
    public Integer getPriority() {
        return this.priority;
    }
    
    @JsonProperty("priority")
    public void setPriority(final Integer priority) {
        this.priority = priority;
    }
    
    @JsonProperty("status")
    public String getStatus() {
        return this.status;
    }
    
    @JsonProperty("status")
    public void setStatus(final String status) {
        this.status = status;
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
