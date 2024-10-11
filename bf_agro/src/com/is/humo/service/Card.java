// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "institutionId", "primaryAccountNumber", "effectiveDate", "updateDate", "prefixNumber", "expiry", "cardSequenceNumber", "cardholderId", "nameOnCard", "cardholderPassword", "accountRestrictionsFlag", "commissionGroup", "cardUserId", "additionalInfo", "riskGroup", "riskGroup2", "bankC", "pinTryCount", "statuses" })
public class Card
{
    @JsonProperty("institutionId")
    private String institutionId;
    @JsonProperty("primaryAccountNumber")
    private String primaryAccountNumber;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("updateDate")
    private String updateDate;
    @JsonProperty("prefixNumber")
    private Integer prefixNumber;
    @JsonProperty("expiry")
    private String expiry;
    @JsonProperty("cardSequenceNumber")
    private Integer cardSequenceNumber;
    @JsonProperty("cardholderId")
    private String cardholderId;
    @JsonProperty("nameOnCard")
    private String nameOnCard;
    @JsonProperty("cardholderPassword")
    private String cardholderPassword;
    @JsonProperty("accountRestrictionsFlag")
    private String accountRestrictionsFlag;
    @JsonProperty("commissionGroup")
    private String commissionGroup;
    @JsonProperty("cardUserId")
    private String cardUserId;
    @JsonProperty("additionalInfo")
    private String additionalInfo;
    @JsonProperty("riskGroup")
    private String riskGroup;
    @JsonProperty("riskGroup2")
    private String riskGroup2;
    @JsonProperty("bankC")
    private String bankC;
    @JsonProperty("pinTryCount")
    private Integer pinTryCount;
    @JsonProperty("statuses")
    private Statuses statuses;
    
    public Card() {
    }
    
    public Card(final String institutionId, final String primaryAccountNumber, final String effectiveDate, final String updateDate, final Integer prefixNumber, final String expiry, final Integer cardSequenceNumber, final String cardholderId, final String nameOnCard, final String cardholderPassword, final String accountRestrictionsFlag, final String commissionGroup, final String cardUserId, final String additionalInfo, final String riskGroup, final String riskGroup2, final String bankC, final Integer pinTryCount, final Statuses statuses) {
        this.institutionId = institutionId;
        this.primaryAccountNumber = primaryAccountNumber;
        this.effectiveDate = effectiveDate;
        this.updateDate = updateDate;
        this.prefixNumber = prefixNumber;
        this.expiry = expiry;
        this.cardSequenceNumber = cardSequenceNumber;
        this.cardholderId = cardholderId;
        this.nameOnCard = nameOnCard;
        this.cardholderPassword = cardholderPassword;
        this.accountRestrictionsFlag = accountRestrictionsFlag;
        this.commissionGroup = commissionGroup;
        this.cardUserId = cardUserId;
        this.additionalInfo = additionalInfo;
        this.riskGroup = riskGroup;
        this.riskGroup2 = riskGroup2;
        this.bankC = bankC;
        this.pinTryCount = pinTryCount;
        this.statuses = statuses;
    }
    
    @JsonProperty("institutionId")
    public String getInstitutionId() {
        return this.institutionId;
    }
    
    @JsonProperty("institutionId")
    public void setInstitutionId(final String institutionId) {
        this.institutionId = institutionId;
    }
    
    @JsonProperty("primaryAccountNumber")
    public String getPrimaryAccountNumber() {
        return this.primaryAccountNumber;
    }
    
    @JsonProperty("primaryAccountNumber")
    public void setPrimaryAccountNumber(final String primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
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
    
    @JsonProperty("prefixNumber")
    public Integer getPrefixNumber() {
        return this.prefixNumber;
    }
    
    @JsonProperty("prefixNumber")
    public void setPrefixNumber(final Integer prefixNumber) {
        this.prefixNumber = prefixNumber;
    }
    
    @JsonProperty("expiry")
    public String getExpiry() {
        return this.expiry;
    }
    
    @JsonProperty("expiry")
    public void setExpiry(final String expiry) {
        this.expiry = expiry;
    }
    
    @JsonProperty("cardSequenceNumber")
    public Integer getCardSequenceNumber() {
        return this.cardSequenceNumber;
    }
    
    @JsonProperty("cardSequenceNumber")
    public void setCardSequenceNumber(final Integer cardSequenceNumber) {
        this.cardSequenceNumber = cardSequenceNumber;
    }
    
    @JsonProperty("cardholderId")
    public String getCardholderId() {
        return this.cardholderId;
    }
    
    @JsonProperty("cardholderId")
    public void setCardholderId(final String cardholderId) {
        this.cardholderId = cardholderId;
    }
    
    @JsonProperty("nameOnCard")
    public String getNameOnCard() {
        return this.nameOnCard;
    }
    
    @JsonProperty("nameOnCard")
    public void setNameOnCard(final String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
    
    @JsonProperty("cardholderPassword")
    public String getCardholderPassword() {
        return this.cardholderPassword;
    }
    
    @JsonProperty("cardholderPassword")
    public void setCardholderPassword(final String cardholderPassword) {
        this.cardholderPassword = cardholderPassword;
    }
    
    @JsonProperty("accountRestrictionsFlag")
    public String getAccountRestrictionsFlag() {
        return this.accountRestrictionsFlag;
    }
    
    @JsonProperty("accountRestrictionsFlag")
    public void setAccountRestrictionsFlag(final String accountRestrictionsFlag) {
        this.accountRestrictionsFlag = accountRestrictionsFlag;
    }
    
    @JsonProperty("commissionGroup")
    public String getCommissionGroup() {
        return this.commissionGroup;
    }
    
    @JsonProperty("commissionGroup")
    public void setCommissionGroup(final String commissionGroup) {
        this.commissionGroup = commissionGroup;
    }
    
    @JsonProperty("cardUserId")
    public String getCardUserId() {
        return this.cardUserId;
    }
    
    @JsonProperty("cardUserId")
    public void setCardUserId(final String cardUserId) {
        this.cardUserId = cardUserId;
    }
    
    @JsonProperty("additionalInfo")
    public String getAdditionalInfo() {
        return this.additionalInfo;
    }
    
    @JsonProperty("additionalInfo")
    public void setAdditionalInfo(final String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    
    @JsonProperty("riskGroup")
    public String getRiskGroup() {
        return this.riskGroup;
    }
    
    @JsonProperty("riskGroup")
    public void setRiskGroup(final String riskGroup) {
        this.riskGroup = riskGroup;
    }
    
    @JsonProperty("riskGroup2")
    public String getRiskGroup2() {
        return this.riskGroup2;
    }
    
    @JsonProperty("riskGroup2")
    public void setRiskGroup2(final String riskGroup2) {
        this.riskGroup2 = riskGroup2;
    }
    
    @JsonProperty("bankC")
    public String getBankC() {
        return this.bankC;
    }
    
    @JsonProperty("bankC")
    public void setBankC(final String bankC) {
        this.bankC = bankC;
    }
    
    @JsonProperty("pinTryCount")
    public Integer getPinTryCount() {
        return this.pinTryCount;
    }
    
    @JsonProperty("pinTryCount")
    public void setPinTryCount(final Integer pinTryCount) {
        this.pinTryCount = pinTryCount;
    }
    
    @JsonProperty("statuses")
    public Statuses getStatuses() {
        return this.statuses;
    }
    
    @JsonProperty("statuses")
    public void setStatuses(final Statuses statuses) {
        this.statuses = statuses;
    }
}
