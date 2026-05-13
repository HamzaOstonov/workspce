// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "CARD_ACCT", "ACCOUNT_NO", "CL_ACCT_KEY", "CLIENT", "CARD", "EXP_DATE", "TRAN_TYPE", "TRAN_CCY", "TRAN_AMT", "CCY_EXP", "TRAN_DATE_TIME", "REC_DATE", "POST_DATE", "ACCNT_CCY", "AMOUNT_NET", "ACQREF_NR", "APR_CODE", "APR_SCR", "STAN", "TERM_ID", "MERCHANT", "POINT_CODE", "MCC_CODE", "ABVR_NAME", "CITY", "COUNTRY", "DEAL_DESC", "COUNTERPARTY", "INTERNAL_NO", "FLD_104", "BANK_C", "GROUPC", "CTIME", "TR_CODE", "TR_FEE", "TR_CODE2", "TR_FEE2", "LOCKING_FLAG", "ADD_INFO", "REF_NUMBER" })
public class RowsItem
{
    @JsonProperty("CARD_ACCT")
    private String cardAcct;
    @JsonProperty("ACCOUNT_NO")
    private String accountNo;
    @JsonProperty("CL_ACCT_KEY")
    private String clAcctKey;
    @JsonProperty("CLIENT")
    private String client;
    @JsonProperty("CARD")
    private Object card;
    @JsonProperty("EXP_DATE")
    private Object expDate;
    @JsonProperty("TRAN_TYPE")
    private String tranType;
    @JsonProperty("TRAN_CCY")
    private String tranCcy;
    @JsonProperty("TRAN_AMT")
    private String tranAmt;
    @JsonProperty("CCY_EXP")
    private String ccyExp;
    @JsonProperty("TRAN_DATE_TIME")
    private String tranDateTime;
    @JsonProperty("REC_DATE")
    private String recDate;
    @JsonProperty("POST_DATE")
    private String postDate;
    @JsonProperty("ACCNT_CCY")
    private String accntCcy;
    @JsonProperty("AMOUNT_NET")
    private String amountNet;
    @JsonProperty("ACQREF_NR")
    private Object acqrefNr;
    @JsonProperty("APR_CODE")
    private String aprCode;
    @JsonProperty("APR_SCR")
    private Object aprScr;
    @JsonProperty("STAN")
    private Object stan;
    @JsonProperty("TERM_ID")
    private Object termId;
    @JsonProperty("MERCHANT")
    private Object merchant;
    @JsonProperty("POINT_CODE")
    private Object pointCode;
    @JsonProperty("MCC_CODE")
    private Object mccCode;
    @JsonProperty("ABVR_NAME")
    private Object abvrName;
    @JsonProperty("CITY")
    private Object city;
    @JsonProperty("COUNTRY")
    private Object country;
    @JsonProperty("DEAL_DESC")
    private Object dealDesc;
    @JsonProperty("COUNTERPARTY")
    private Object counterparty;
    @JsonProperty("INTERNAL_NO")
    private String internalNo;
    @JsonProperty("FLD_104")
    private Object fld104;
    @JsonProperty("BANK_C")
    private String bankC;
    @JsonProperty("GROUPC")
    private String groupc;
    @JsonProperty("CTIME")
    private String ctime;
    @JsonProperty("TR_CODE")
    private Object trCode;
    @JsonProperty("TR_FEE")
    private Object trFee;
    @JsonProperty("TR_CODE2")
    private Object trCode2;
    @JsonProperty("TR_FEE2")
    private Object trFee2;
    @JsonProperty("LOCKING_FLAG")
    private Object lockingFlag;
    @JsonProperty("ADD_INFO")
    private String addInfo;
    @JsonProperty("REF_NUMBER")
    private Object refNumber;
    
    public RowsItem(final String cardAcct, final String accountNo, final String clAcctKey, final String client, final Object card, final Object expDate, final String tranType, final String tranCcy, final String tranAmt, final String ccyExp, final String tranDateTime, final String recDate, final String postDate, final String accntCcy, final String amountNet, final Object acqrefNr, final String aprCode, final Object aprScr, final Object stan, final Object termId, final Object merchant, final Object pointCode, final Object mccCode, final Object abvrName, final Object city, final Object country, final Object dealDesc, final Object counterparty, final String internalNo, final Object fld104, final String bankC, final String groupc, final String ctime, final Object trCode, final Object trFee, final Object trCode2, final Object trFee2, final Object lockingFlag, final String addInfo, final Object refNumber) {
        this.cardAcct = cardAcct;
        this.accountNo = accountNo;
        this.clAcctKey = clAcctKey;
        this.client = client;
        this.card = card;
        this.expDate = expDate;
        this.tranType = tranType;
        this.tranCcy = tranCcy;
        this.tranAmt = tranAmt;
        this.ccyExp = ccyExp;
        this.tranDateTime = tranDateTime;
        this.recDate = recDate;
        this.postDate = postDate;
        this.accntCcy = accntCcy;
        this.amountNet = amountNet;
        this.acqrefNr = acqrefNr;
        this.aprCode = aprCode;
        this.aprScr = aprScr;
        this.stan = stan;
        this.termId = termId;
        this.merchant = merchant;
        this.pointCode = pointCode;
        this.mccCode = mccCode;
        this.abvrName = abvrName;
        this.city = city;
        this.country = country;
        this.dealDesc = dealDesc;
        this.counterparty = counterparty;
        this.internalNo = internalNo;
        this.fld104 = fld104;
        this.bankC = bankC;
        this.groupc = groupc;
        this.ctime = ctime;
        this.trCode = trCode;
        this.trFee = trFee;
        this.trCode2 = trCode2;
        this.trFee2 = trFee2;
        this.lockingFlag = lockingFlag;
        this.addInfo = addInfo;
        this.refNumber = refNumber;
    }
    
    public RowsItem() {
    }
    
    @JsonProperty("CARD_ACCT")
    public String getCardAcct() {
        return this.cardAcct;
    }
    
    @JsonProperty("CARD_ACCT")
    public void setCardAcct(final String cardAcct) {
        this.cardAcct = cardAcct;
    }
    
    @JsonProperty("ACCOUNT_NO")
    public String getAccountNo() {
        return this.accountNo;
    }
    
    @JsonProperty("ACCOUNT_NO")
    public void setAccountNo(final String accountNo) {
        this.accountNo = accountNo;
    }
    
    @JsonProperty("CL_ACCT_KEY")
    public String getClAcctKey() {
        return this.clAcctKey;
    }
    
    @JsonProperty("CL_ACCT_KEY")
    public void setClAcctKey(final String clAcctKey) {
        this.clAcctKey = clAcctKey;
    }
    
    @JsonProperty("CLIENT")
    public String getClient() {
        return this.client;
    }
    
    @JsonProperty("CLIENT")
    public void setClient(final String client) {
        this.client = client;
    }
    
    @JsonProperty("CARD")
    public Object getCard() {
        return this.card;
    }
    
    @JsonProperty("CARD")
    public void setCard(final Object card) {
        this.card = card;
    }
    
    @JsonProperty("EXP_DATE")
    public Object getExpDate() {
        return this.expDate;
    }
    
    @JsonProperty("EXP_DATE")
    public void setExpDate(final Object expDate) {
        this.expDate = expDate;
    }
    
    @JsonProperty("TRAN_TYPE")
    public String getTranType() {
        return this.tranType;
    }
    
    @JsonProperty("TRAN_TYPE")
    public void setTranType(final String tranType) {
        this.tranType = tranType;
    }
    
    @JsonProperty("TRAN_CCY")
    public String getTranCcy() {
        return this.tranCcy;
    }
    
    @JsonProperty("TRAN_CCY")
    public void setTranCcy(final String tranCcy) {
        this.tranCcy = tranCcy;
    }
    
    @JsonProperty("TRAN_AMT")
    public String getTranAmt() {
        return this.tranAmt;
    }
    
    @JsonProperty("TRAN_AMT")
    public void setTranAmt(final String tranAmt) {
        this.tranAmt = tranAmt;
    }
    
    @JsonProperty("CCY_EXP")
    public String getCcyExp() {
        return this.ccyExp;
    }
    
    @JsonProperty("CCY_EXP")
    public void setCcyExp(final String ccyExp) {
        this.ccyExp = ccyExp;
    }
    
    @JsonProperty("TRAN_DATE_TIME")
    public String getTranDateTime() {
        return this.tranDateTime;
    }
    
    @JsonProperty("TRAN_DATE_TIME")
    public void setTranDateTime(final String tranDateTime) {
        this.tranDateTime = tranDateTime;
    }
    
    @JsonProperty("REC_DATE")
    public String getRecDate() {
        return this.recDate;
    }
    
    @JsonProperty("REC_DATE")
    public void setRecDate(final String recDate) {
        this.recDate = recDate;
    }
    
    @JsonProperty("POST_DATE")
    public String getPostDate() {
        return this.postDate;
    }
    
    @JsonProperty("POST_DATE")
    public void setPostDate(final String postDate) {
        this.postDate = postDate;
    }
    
    @JsonProperty("ACCNT_CCY")
    public String getAccntCcy() {
        return this.accntCcy;
    }
    
    @JsonProperty("ACCNT_CCY")
    public void setAccntCcy(final String accntCcy) {
        this.accntCcy = accntCcy;
    }
    
    @JsonProperty("AMOUNT_NET")
    public String getAmountNet() {
        return this.amountNet;
    }
    
    @JsonProperty("AMOUNT_NET")
    public void setAmountNet(final String amountNet) {
        this.amountNet = amountNet;
    }
    
    @JsonProperty("ACQREF_NR")
    public Object getAcqrefNr() {
        return this.acqrefNr;
    }
    
    @JsonProperty("ACQREF_NR")
    public void setAcqrefNr(final Object acqrefNr) {
        this.acqrefNr = acqrefNr;
    }
    
    @JsonProperty("APR_CODE")
    public String getAprCode() {
        return this.aprCode;
    }
    
    @JsonProperty("APR_CODE")
    public void setAprCode(final String aprCode) {
        this.aprCode = aprCode;
    }
    
    @JsonProperty("APR_SCR")
    public Object getAprScr() {
        return this.aprScr;
    }
    
    @JsonProperty("APR_SCR")
    public void setAprScr(final Object aprScr) {
        this.aprScr = aprScr;
    }
    
    @JsonProperty("STAN")
    public Object getStan() {
        return this.stan;
    }
    
    @JsonProperty("STAN")
    public void setStan(final Object stan) {
        this.stan = stan;
    }
    
    @JsonProperty("TERM_ID")
    public Object getTermId() {
        return this.termId;
    }
    
    @JsonProperty("TERM_ID")
    public void setTermId(final Object termId) {
        this.termId = termId;
    }
    
    @JsonProperty("MERCHANT")
    public Object getMerchant() {
        return this.merchant;
    }
    
    @JsonProperty("MERCHANT")
    public void setMerchant(final Object merchant) {
        this.merchant = merchant;
    }
    
    @JsonProperty("POINT_CODE")
    public Object getPointCode() {
        return this.pointCode;
    }
    
    @JsonProperty("POINT_CODE")
    public void setPointCode(final Object pointCode) {
        this.pointCode = pointCode;
    }
    
    @JsonProperty("MCC_CODE")
    public Object getMccCode() {
        return this.mccCode;
    }
    
    @JsonProperty("MCC_CODE")
    public void setMccCode(final Object mccCode) {
        this.mccCode = mccCode;
    }
    
    @JsonProperty("ABVR_NAME")
    public Object getAbvrName() {
        return this.abvrName;
    }
    
    @JsonProperty("ABVR_NAME")
    public void setAbvrName(final Object abvrName) {
        this.abvrName = abvrName;
    }
    
    @JsonProperty("CITY")
    public Object getCity() {
        return this.city;
    }
    
    @JsonProperty("CITY")
    public void setCity(final Object city) {
        this.city = city;
    }
    
    @JsonProperty("COUNTRY")
    public Object getCountry() {
        return this.country;
    }
    
    @JsonProperty("COUNTRY")
    public void setCountry(final Object country) {
        this.country = country;
    }
    
    @JsonProperty("DEAL_DESC")
    public Object getDealDesc() {
        return this.dealDesc;
    }
    
    @JsonProperty("DEAL_DESC")
    public void setDealDesc(final Object dealDesc) {
        this.dealDesc = dealDesc;
    }
    
    @JsonProperty("COUNTERPARTY")
    public Object getCounterparty() {
        return this.counterparty;
    }
    
    @JsonProperty("COUNTERPARTY")
    public void setCounterparty(final Object counterparty) {
        this.counterparty = counterparty;
    }
    
    @JsonProperty("INTERNAL_NO")
    public String getInternalNo() {
        return this.internalNo;
    }
    
    @JsonProperty("INTERNAL_NO")
    public void setInternalNo(final String internalNo) {
        this.internalNo = internalNo;
    }
    
    @JsonProperty("FLD_104")
    public Object getFld104() {
        return this.fld104;
    }
    
    @JsonProperty("FLD_104")
    public void setFld104(final Object fld104) {
        this.fld104 = fld104;
    }
    
    @JsonProperty("BANK_C")
    public String getBankC() {
        return this.bankC;
    }
    
    @JsonProperty("BANK_C")
    public void setBankC(final String bankC) {
        this.bankC = bankC;
    }
    
    @JsonProperty("GROUPC")
    public String getGroupc() {
        return this.groupc;
    }
    
    @JsonProperty("GROUPC")
    public void setGroupc(final String groupc) {
        this.groupc = groupc;
    }
    
    @JsonProperty("CTIME")
    public String getCtime() {
        return this.ctime;
    }
    
    @JsonProperty("CTIME")
    public void setCtime(final String ctime) {
        this.ctime = ctime;
    }
    
    @JsonProperty("TR_CODE")
    public Object getTrCode() {
        return this.trCode;
    }
    
    @JsonProperty("TR_CODE")
    public void setTrCode(final Object trCode) {
        this.trCode = trCode;
    }
    
    @JsonProperty("TR_FEE")
    public Object getTrFee() {
        return this.trFee;
    }
    
    @JsonProperty("TR_FEE")
    public void setTrFee(final Object trFee) {
        this.trFee = trFee;
    }
    
    @JsonProperty("TR_CODE2")
    public Object getTrCode2() {
        return this.trCode2;
    }
    
    @JsonProperty("TR_CODE2")
    public void setTrCode2(final Object trCode2) {
        this.trCode2 = trCode2;
    }
    
    @JsonProperty("TR_FEE2")
    public Object getTrFee2() {
        return this.trFee2;
    }
    
    @JsonProperty("TR_FEE2")
    public void setTrFee2(final Object trFee2) {
        this.trFee2 = trFee2;
    }
    
    @JsonProperty("LOCKING_FLAG")
    public Object getLockingFlag() {
        return this.lockingFlag;
    }
    
    @JsonProperty("LOCKING_FLAG")
    public void setLockingFlag(final Object lockingFlag) {
        this.lockingFlag = lockingFlag;
    }
    
    @JsonProperty("ADD_INFO")
    public String getAddInfo() {
        return this.addInfo;
    }
    
    @JsonProperty("ADD_INFO")
    public void setAddInfo(final String addInfo) {
        this.addInfo = addInfo;
    }
    
    @JsonProperty("REF_NUMBER")
    public Object getRefNumber() {
        return this.refNumber;
    }
    
    @JsonProperty("REF_NUMBER")
    public void setRefNumber(final Object refNumber) {
        this.refNumber = refNumber;
    }
}
