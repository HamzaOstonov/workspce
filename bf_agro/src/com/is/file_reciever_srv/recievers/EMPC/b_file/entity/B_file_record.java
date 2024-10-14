// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.b_file.entity;

import com.is.file_reciever_srv.recievers.EMPC.entity.EMPC_file_record;

public class B_file_record extends EMPC_file_record
{
    private String type;
    private String content;
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public B_file_record(final long eMPC_file_id, final String type, final String content) {
        super(eMPC_file_id);
        this.type = type;
        this.content = content;
    }
    
    public B_file_record() {
    }
    
    public B_file_record(final long eMPC_file_id) {
        super(eMPC_file_id);
    }
}
