// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "rows" })
public class Result
{
    @JsonProperty("rows")
    private List<RowsItem> rows;
    
    public Result() {
        this.rows = new ArrayList<RowsItem>();
    }
    
    public Result(final List<RowsItem> rows) {
        this.rows = new ArrayList<RowsItem>();
        this.rows = rows;
    }
    
    @JsonProperty("rows")
    public List<RowsItem> getRows() {
        return this.rows;
    }
    
    @JsonProperty("rows")
    public void setRows(final List<RowsItem> rows) {
        this.rows = rows;
    }
}
