// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.b_file.entity;

public class B_file_trayler_record
{
    private Long id;
    private Long rec_num;
    private Long empc_file_id;
    private String Mtid;
    private String Rec_centr;
    private String Send_centr;
    private String File;
    private String number;
    private String Sign;
    private String Sum;
    private String Control;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Long getRec_num() {
        return this.rec_num;
    }
    
    public void setRec_num(final Long rec_num) {
        this.rec_num = rec_num;
    }
    
    public Long getEmpc_file_id() {
        return this.empc_file_id;
    }
    
    public void setEmpc_file_id(final Long empc_file_id) {
        this.empc_file_id = empc_file_id;
    }
    
    public String getMtid() {
        return this.Mtid;
    }
    
    public void setMtid(final String mtid) {
        this.Mtid = mtid;
    }
    
    public String getRec_centr() {
        return this.Rec_centr;
    }
    
    public void setRec_centr(final String rec_centr) {
        this.Rec_centr = rec_centr;
    }
    
    public String getSend_centr() {
        return this.Send_centr;
    }
    
    public void setSend_centr(final String send_centr) {
        this.Send_centr = send_centr;
    }
    
    public String getFile() {
        return this.File;
    }
    
    public void setFile(final String file) {
        this.File = file;
    }
    
    public String getNumber() {
        return this.number;
    }
    
    public void setNumber(final String number) {
        this.number = number;
    }
    
    public String getSign() {
        return this.Sign;
    }
    
    public void setSign(final String sign) {
        this.Sign = sign;
    }
    
    public String getSum() {
        return this.Sum;
    }
    
    public void setSum(final String sum) {
        this.Sum = sum;
    }
    
    public String getControl() {
        return this.Control;
    }
    
    public void setControl(final String control) {
        this.Control = control;
    }
    
    public B_file_trayler_record(final Long id, final Long rec_num, final Long empc_file_id, final String mtid, final String rec_centr, final String send_centr, final String file, final String number, final String sign, final String sum, final String control) {
        this.rec_num = rec_num;
        this.id = id;
        this.empc_file_id = empc_file_id;
        this.Mtid = mtid;
        this.Rec_centr = rec_centr;
        this.Send_centr = send_centr;
        this.File = file;
        this.number = number;
        this.Sign = sign;
        this.Sum = sum;
        this.Control = control;
    }
    
    public B_file_trayler_record() {
    }
    
    public B_file_trayler_record(final Long empc_file_id) {
        this.empc_file_id = empc_file_id;
    }
}
