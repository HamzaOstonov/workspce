// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "type", "actionCode", "actionDescription", "effectiveDate", "note" })
public class Item
{
    @JsonProperty("type")
    private String type;
    @JsonProperty("actionCode")
    private String actionCode;
    @JsonProperty("actionDescription")
    private String actionDescription;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("note")
    private String note;
    
    public Item() {
    }
    
    public Item(final String type, final String actionCode, final String actionDescription, final String effectiveDate, final String note) {
        this.type = type;
        this.actionCode = actionCode;
        this.actionDescription = actionDescription;
        this.effectiveDate = effectiveDate;
        this.note = note;
    }
    
    @JsonProperty("type")
    public String getType() {
        return this.type;
    }
    
    @JsonProperty("type")
    public void setType(final String type) {
        this.type = type;
    }
    
    @JsonProperty("actionCode")
    public String getActionCode() {
        return this.actionCode;
    }
    
    @JsonProperty("actionCode")
    public void setActionCode(final String actionCode) {
        this.actionCode = actionCode;
    }
    
    @JsonProperty("actionDescription")
    public String getActionDescription() {
        return this.actionDescription;
    }
    
    @JsonProperty("actionDescription")
    public void setActionDescription(final String actionDescription) {
        this.actionDescription = actionDescription;
    }
    
    @JsonProperty("effectiveDate")
    public String getEffectiveDate() {
        return this.effectiveDate;
    }
    
    @JsonProperty("effectiveDate")
    public void setEffectiveDate(final String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    @JsonProperty("note")
    public String getNote() {
        return this.note;
    }
    
    @JsonProperty("note")
    public void setNote(final String note) {
        this.note = note;
    }
}
