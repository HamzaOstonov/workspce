// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.fileProcessing;

public class HumoFileRecords
{
    Long id;
    String state;
    String merchant;
    String card;
    String termId;
    String mcc;
    String tran_type;
    String errorText;
    String summa;
    String tran_date_time;
    String abvr_name;
    String tranz_acct;
    String tran_amt2;
    
    public String getTran_amt2() {
        return this.tran_amt2;
    }
    
    public void setTran_amt2(final String tran_amt2) {
        this.tran_amt2 = tran_amt2;
    }
    
    public String getTranz_acct() {
        return this.tranz_acct;
    }
    
    public void setTranz_acct(final String tranz_acct) {
        this.tranz_acct = tranz_acct;
    }
    
    public String getAbvr_name() {
        return this.abvr_name;
    }
    
    public void setAbvr_name(final String abvr_name) {
        this.abvr_name = abvr_name;
    }
    
    public String getTran_date_time() {
        return this.tran_date_time;
    }
    
    public void setTran_date_time(final String tran_date_time) {
        this.tran_date_time = tran_date_time;
    }
    
    public String getCard() {
        return this.card;
    }
    
    public void setCard(final String card) {
        this.card = card;
    }
    
    public String getSumma() {
        return this.summa;
    }
    
    public void setSumma(final String summa) {
        this.summa = summa;
    }
    
    public String getTran_type() {
        return this.tran_type;
    }
    
    public void setTran_type(final String tran_type) {
        this.tran_type = tran_type;
    }
    
    public String getErrorText() {
        return this.errorText;
    }
    
    public void setErrorText(final String errorText) {
        this.errorText = errorText;
    }
    
    public HumoFileRecords(final Long id, final String state, final String merchant, final String abvr_name, final String card, final String tranz_acct, final String termId, final String mcc, final String tran_type, final String summa, final String tran_date_time, final String errorText, final String tran_amt2) {
        this.id = id;
        this.state = state;
        this.merchant = merchant;
        this.termId = termId;
        this.mcc = mcc;
        this.tran_type = tran_type;
        this.summa = summa;
        this.errorText = errorText;
        this.card = card;
        this.tran_date_time = tran_date_time;
        this.abvr_name = abvr_name;
        this.tranz_acct = tranz_acct;
        this.tran_amt2 = tran_amt2;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public String getState() {
        return this.state;
    }
    
    public void setState(final String state) {
        this.state = state;
    }
    
    public String getMerchant() {
        return this.merchant;
    }
    
    public void setMerchant(final String merchant) {
        this.merchant = merchant;
    }
    
    public String getTermId() {
        return this.termId;
    }
    
    public void setTermId(final String termId) {
        this.termId = termId;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
}
