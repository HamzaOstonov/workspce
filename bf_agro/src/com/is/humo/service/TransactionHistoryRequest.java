// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

public class TransactionHistoryRequest
{
    private String id;
    private Params params;
    
    public TransactionHistoryRequest() {
    }
    
    public TransactionHistoryRequest(final String id, final Params params) {
        this.id = id;
        this.params = params;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public Params getParams() {
        return this.params;
    }
    
    public void setParams(final Params params) {
        this.params = params;
    }
}
