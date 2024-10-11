// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "result" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionHistoryResponse
{
    @JsonProperty("id")
    private String id;
    @JsonProperty("result")
    private Result result;
    @JsonProperty("error")
    private Error error;
    
    public TransactionHistoryResponse() {
    }
    
    public TransactionHistoryResponse(final String id, final Result result, final Error error) {
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
    public Result getResult() {
        return this.result;
    }
    
    @JsonProperty("result")
    public void setResult(final Result result) {
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
