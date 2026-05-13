// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo;

public class EmpcSettings
{
    private String bincod;
    private String group_c;
    private String bank_c;
    private String range_id;
    private String chip_app_id;
    private String branch_id;
    
    public EmpcSettings() {
    }
    
    public EmpcSettings(final String bincod, final String group_c, final String bank_c, final String range_id, final String chip_app_id, final String branch_id) {
        this.bincod = bincod;
        this.group_c = group_c;
        this.bank_c = bank_c;
        this.range_id = range_id;
        this.chip_app_id = chip_app_id;
        this.branch_id = branch_id;
    }
    
    public String getBincod() {
        return this.bincod;
    }
    
    public void setBincod(final String bincod) {
        this.bincod = bincod;
    }
    
    public String getGroup_c() {
        return this.group_c;
    }
    
    public void setGroup_c(final String group_c) {
        this.group_c = group_c;
    }
    
    public String getBank_c() {
        return this.bank_c;
    }
    
    public void setBank_c(final String bank_c) {
        this.bank_c = bank_c;
    }
    
    public String getRange_id() {
        return this.range_id;
    }
    
    public void setRange_id(final String range_id) {
        this.range_id = range_id;
    }
    
    public String getChip_app_id() {
        return this.chip_app_id;
    }
    
    public void setChip_app_id(final String chip_app_id) {
        this.chip_app_id = chip_app_id;
    }
    
    public String getBranch_id() {
        return this.branch_id;
    }
    
    public void setBranch_id(final String branch_id) {
        this.branch_id = branch_id;
    }
}
