// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "result", "error" })
public class GetBalanceResponse
{
    @JsonProperty("id")
    private String id;
    @JsonProperty("result")
    private BalanceResult result;
    @JsonProperty("error")
    private Error error;
    
    public GetBalanceResponse() {
    }
    
    public GetBalanceResponse(final String id, final BalanceResult result, final Error error) {
        this.id = id;
        this.result = result;
        this.error = error;
    }
    
    @JsonProperty("id")
    public String getId() {
        return this.id;
    }
    
    @JsonProperty("id")
    public void setId(final String id) {
        this.id = id;
    }
    
    @JsonProperty("result")
    public BalanceResult getResult() {
        return this.result;
    }
    
    @JsonProperty("result")
    public void setResult(final BalanceResult result) {
        this.result = result;
    }
    
    @JsonProperty("error")
    public Error getError() {
        return this.error;
    }
    
    @JsonProperty("error")
    public void setError(final Error error) {
        this.error = error;
    }
}
