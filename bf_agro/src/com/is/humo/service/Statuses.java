// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "item" })
public class Statuses
{
    @JsonProperty("item")
    private List<Item> item;
    
    public Statuses() {
        this.item = null;
    }
    
    public Statuses(final List<Item> item) {
        this.item = null;
        this.item = item;
    }
    
    @JsonProperty("item")
    public List<Item> getItem() {
        return this.item;
    }
    
    @JsonProperty("item")
    public void setItem(final List<Item> item) {
        this.item = item;
    }
}
