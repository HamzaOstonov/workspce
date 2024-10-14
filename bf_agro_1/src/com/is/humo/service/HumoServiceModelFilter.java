// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import java.io.Serializable;

public class HumoServiceModelFilter implements Serializable
{
    static final long serialVersionUID = 103844514947365244L;
    private Double id;
    private Double prots_name;
    private Double descripsion;
    private Double state;
    
    public HumoServiceModelFilter() {
    }
    
    public HumoServiceModelFilter(final Double id, final Double prots_name, final Double descripsion, final Double state) {
        this.id = id;
        this.prots_name = prots_name;
        this.descripsion = descripsion;
        this.state = state;
    }
    
    public Double getId() {
        return this.id;
    }
    
    public void setId(final Double id) {
        this.id = id;
    }
    
    public Double getProts_name() {
        return this.prots_name;
    }
    
    public void setProts_name(final Double prots_name) {
        this.prots_name = prots_name;
    }
    
    public Double getDescripsion() {
        return this.descripsion;
    }
    
    public void setDescripsion(final Double descripsion) {
        this.descripsion = descripsion;
    }
    
    public Double getState() {
        return this.state;
    }
    
    public void setState(final Double state) {
        this.state = state;
    }
}
