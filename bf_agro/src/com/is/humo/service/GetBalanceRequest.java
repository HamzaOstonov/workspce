// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "params" })
public class GetBalanceRequest
{
    @JsonProperty("id")
    private String id;
    @JsonProperty("params")
    private GetbalanceRequestParams params;
    
    public GetBalanceRequest() {
    }
    
    public GetBalanceRequest(final String id, final GetbalanceRequestParams params) {
        this.id = id;
        this.params = params;
    }
    
    @JsonProperty("id")
    public String getId() {
        return this.id;
    }
    
    @JsonProperty("id")
    public void setId(final String id) {
        this.id = id;
    }
    
    @JsonProperty("params")
    public GetbalanceRequestParams getParams() {
        return this.params;
    }
    
    @JsonProperty("params")
    public void setParams(final GetbalanceRequestParams params) {
        this.params = params;
    }
}
