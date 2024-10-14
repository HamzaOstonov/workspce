// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.EXPT;

import java.util.Date;

public class EXPT_header_record
{
    private Long id;
    private Long empc_file_id;
    private String record_type;
    private Long line_number;
    private Date create_date;
    private String user_id;
    private String bank_c;
    private String groupc;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Long getEmpc_file_id() {
        return this.empc_file_id;
    }
    
    public void setEmpc_file_id(final Long empc_file_id) {
        this.empc_file_id = empc_file_id;
    }
    
    public String getRecord_type() {
        return this.record_type;
    }
    
    public void setRecord_type(final String record_type) {
        this.record_type = record_type;
    }
    
    public Long getLine_number() {
        return this.line_number;
    }
    
    public void setLine_number(final Long line_number) {
        this.line_number = line_number;
    }
    
    public Date getCreate_date() {
        return this.create_date;
    }
    
    public void setCreate_date(final Date create_date) {
        this.create_date = create_date;
    }
    
    public String getUser_id() {
        return this.user_id;
    }
    
    public void setUser_id(final String user_id) {
        this.user_id = user_id;
    }
    
    public String getBank_c() {
        return this.bank_c;
    }
    
    public void setBank_c(final String bank_c) {
        this.bank_c = bank_c;
    }
    
    public String getGroupc() {
        return this.groupc;
    }
    
    public void setGroupc(final String groupc) {
        this.groupc = groupc;
    }
    
    public EXPT_header_record(final Long id, final Long empc_file_id, final String record_type, final Long line_number, final Date create_date, final String user_id, final String bank_c, final String groupc) {
        this.id = id;
        this.empc_file_id = empc_file_id;
        this.record_type = record_type;
        this.line_number = line_number;
        this.create_date = create_date;
        this.user_id = user_id;
        this.bank_c = bank_c;
        this.groupc = groupc;
    }
    
    public EXPT_header_record() {
    }
}
