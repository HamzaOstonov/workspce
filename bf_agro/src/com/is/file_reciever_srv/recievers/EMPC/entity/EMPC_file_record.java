// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.entity;

public class EMPC_file_record
{
    private long EMPC_file_id;
    
    public long getEMPC_file_id() {
        return this.EMPC_file_id;
    }
    
    public void setEMPC_file_id(final long eMPC_file_id) {
        this.EMPC_file_id = eMPC_file_id;
    }
    
    public EMPC_file_record(final long eMPC_file_id) {
        this.EMPC_file_id = eMPC_file_id;
    }
    
    public EMPC_file_record() {
    }
}
