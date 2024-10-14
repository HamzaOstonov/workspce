package com.is.accounts;

import java.util.Date;




public class SAccount {
	 private String nci_id;
     private String destin;
     private String code_b;
     private String name_s;
     private String type;
     private String sect_code;
     private String kod_acc;
     private String rever_code;
     private String kod_k;
     private String kod_r;
     private Date date_open;
     private Date date_close;
     private String act;
     private String pr_nibbd;
	public SAccount(String nci_id, String destin, String code_b, String name_s, String type, String sect_code,
			String kod_acc, String rever_code, String kod_k, String kod_r, Date date_open, Date date_close, String act,
			String pr_nibbd) {
		super();
		this.nci_id = nci_id;
		this.destin = destin;
		this.code_b = code_b;
		this.name_s = name_s;
		this.type = type;
		this.sect_code = sect_code;
		this.kod_acc = kod_acc;
		this.rever_code = rever_code;
		this.kod_k = kod_k;
		this.kod_r = kod_r;
		this.date_open = date_open;
		this.date_close = date_close;
		this.act = act;
		this.pr_nibbd = pr_nibbd;
	}
	public String getNci_id() {
		return nci_id;
	}
	public void setNci_id(String nci_id) {
		this.nci_id = nci_id;
	}
	public String getDestin() {
		return destin;
	}
	public void setDestin(String destin) {
		this.destin = destin;
	}
	public String getCode_b() {
		return code_b;
	}
	public void setCode_b(String code_b) {
		this.code_b = code_b;
	}
	public String getName_s() {
		return name_s;
	}
	public void setName_s(String name_s) {
		this.name_s = name_s;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSect_code() {
		return sect_code;
	}
	public void setSect_code(String sect_code) {
		this.sect_code = sect_code;
	}
	public String getKod_acc() {
		return kod_acc;
	}
	public void setKod_acc(String kod_acc) {
		this.kod_acc = kod_acc;
	}
	public String getRever_code() {
		return rever_code;
	}
	public void setRever_code(String rever_code) {
		this.rever_code = rever_code;
	}
	public String getKod_k() {
		return kod_k;
	}
	public void setKod_k(String kod_k) {
		this.kod_k = kod_k;
	}
	public String getKod_r() {
		return kod_r;
	}
	public void setKod_r(String kod_r) {
		this.kod_r = kod_r;
	}
	public Date getDate_open() {
		return date_open;
	}
	public void setDate_open(Date date_open) {
		this.date_open = date_open;
	}
	public Date getDate_close() {
		return date_close;
	}
	public void setDate_close(Date date_close) {
		this.date_close = date_close;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getPr_nibbd() {
		return pr_nibbd;
	}
	public void setPr_nibbd(String pr_nibbd) {
		this.pr_nibbd = pr_nibbd;
	}
     
     
    
    
}
