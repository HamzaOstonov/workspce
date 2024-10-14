// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.b_file.entity;

public class B_file_header_record
{
    private long id;
    private Long rec_num;
    private Long EMPC_file_id;
    private String mtid;
    private String rec_centr;
    private String send_centr;
    private String file;
    private String card_id;
    private String version;
    
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public Long getRec_num() {
        return this.rec_num;
    }
    
    public void setRec_num(final Long rec_num) {
        this.rec_num = rec_num;
    }
    
    public Long getEMPC_file_id() {
        return this.EMPC_file_id;
    }
    
    public void setEMPC_file_id(final Long eMPC_file_id) {
        this.EMPC_file_id = eMPC_file_id;
    }
    
    public String getMtid() {
        return this.mtid;
    }
    
    public void setMtid(final String mtid) {
        this.mtid = mtid;
    }
    
    public String getRec_centr() {
        return this.rec_centr;
    }
    
    public void setRec_centr(final String rec_centr) {
        this.rec_centr = rec_centr;
    }
    
    public String getSend_centr() {
        return this.send_centr;
    }
    
    public void setSend_centr(final String send_centr) {
        this.send_centr = send_centr;
    }
    
    public String getFile() {
        return this.file;
    }
    
    public void setFile(final String file) {
        this.file = file;
    }
    
    public String getCard_id() {
        return this.card_id;
    }
    
    public void setCard_id(final String card_id) {
        this.card_id = card_id;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(final String version) {
        this.version = version;
    }
    
    public B_file_header_record(final long eMPC_file_id, final Long rec_num, final long id, final String mtid, final String rec_centr, final String send_centr, final String file, final String card_id, final String version) {
        this.EMPC_file_id = eMPC_file_id;
        this.rec_num = rec_num;
        this.id = id;
        this.mtid = mtid;
        this.rec_centr = rec_centr;
        this.send_centr = send_centr;
        this.file = file;
        this.card_id = card_id;
        this.version = version;
    }
    
    public B_file_header_record() {
    }
    
    public B_file_header_record(final long eMPC_file_id) {
        this.EMPC_file_id = eMPC_file_id;
    }
}
