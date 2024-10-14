// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

public class Params
{
    private String pan;
    private String tranType;
    private String dateFrom;
    private String dateTo;
    
    public Params() {
    }
    
    public Params(final String pan, final String tranType, final String dateFrom, final String dateTo) {
        this.pan = pan;
        this.tranType = tranType;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
    
    public String getPan() {
        return this.pan;
    }
    
    public void setPan(final String pan) {
        this.pan = pan;
    }
    
    public String getTranType() {
        return this.tranType;
    }
    
    public void setTranType(final String tranType) {
        this.tranType = tranType;
    }
    
    public String getDateFrom() {
        return this.dateFrom;
    }
    
    public void setDateFrom(final String dateFrom) {
        this.dateFrom = dateFrom;
    }
    
    public String getDateTo() {
        return this.dateTo;
    }
    
    public void setDateTo(final String dateTo) {
        this.dateTo = dateTo;
    }
}
