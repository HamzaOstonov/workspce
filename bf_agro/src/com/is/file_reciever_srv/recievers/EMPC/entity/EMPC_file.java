// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.entity;

public class EMPC_file
{
    private long id;
    private long fr_file_id;
    
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public long getFr_file_id() {
        return this.fr_file_id;
    }
    
    public void setFr_file_id(final long fr_file_id) {
        this.fr_file_id = fr_file_id;
    }
    
    public EMPC_file(final long id, final long fr_file_id) {
        this.id = id;
        this.fr_file_id = fr_file_id;
    }
    
    public EMPC_file() {
    }
}
