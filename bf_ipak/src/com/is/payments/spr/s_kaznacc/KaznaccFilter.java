package com.is.payments.spr.s_kaznacc;

import java.io.Serializable;
import java.util.Date;

public class KaznaccFilter implements Serializable {

    static final long serialVersionUID = 2L;

	private String nci_id;
	private String budget;
	private String kod_doh;
	private String kod_soato;
	private String kod_acc;
	private String kod_uns;
	private String namebudget;
	private Date date_open;
	private Date date_close;
	private String act;

    public KaznaccFilter() {
		super();
    }

    public KaznaccFilter(String nci_id, String budget, String kod_doh, String kod_soato, String kod_acc, String kod_uns, String namebudget, Date date_open, Date date_close, String act) {
		super();
		this.nci_id = nci_id;
		this.budget = budget;
		this.kod_doh = kod_doh;
		this.kod_soato = kod_soato;
		this.kod_acc = kod_acc;
		this.kod_uns = kod_uns;
		this.namebudget = namebudget;
		this.date_open = date_open;
		this.date_close = date_close;
		this.act = act;
    }

	public String getNci_id() { 
		return nci_id;
	} 

	public void setNci_id(String nci_id) { 
		this.nci_id = nci_id;
	} 

	public String getBudget() { 
		return budget;
	} 

	public void setBudget(String budget) { 
		this.budget = budget;
	} 

	public String getKod_doh() { 
		return kod_doh;
	} 

	public void setKod_doh(String kod_doh) { 
		this.kod_doh = kod_doh;
	} 

	public String getKod_soato() { 
		return kod_soato;
	} 

	public void setKod_soato(String kod_soato) { 
		this.kod_soato = kod_soato;
	} 

	public String getKod_acc() { 
		return kod_acc;
	} 

	public void setKod_acc(String kod_acc) { 
		this.kod_acc = kod_acc;
	} 

	public String getKod_uns() { 
		return kod_uns;
	} 

	public void setKod_uns(String kod_uns) { 
		this.kod_uns = kod_uns;
	} 

	public String getNamebudget() { 
		return namebudget;
	} 

	public void setNamebudget(String namebudget) { 
		this.namebudget = namebudget;
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
}