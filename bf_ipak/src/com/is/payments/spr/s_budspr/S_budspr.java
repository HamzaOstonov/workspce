package com.is.payments.spr.s_budspr;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class S_budspr implements Serializable {
    static final long serialVersionUID = 431254351854312254L;

	private String nci_id;
	private String treasure_id;
	private String account;
	private String inn;
	private String name;
	private Date date_open;
	private Date date_close;
	private String act;
	private String bank_id;
	private String bankacc;
	private String bankinn;
	private String bankaccname;



    public S_budspr() {
		super();
    }

    public S_budspr(String nci_id, String treasure_id, String account, String inn, String name, Date date_open, Date date_close, String act, String bank_id, String bankacc, String bankinn, String bankaccname) {
		super();
		this.nci_id = nci_id;
		this.treasure_id = treasure_id;
		this.account = account;
		this.inn = inn;
		this.name = name;
		this.date_open = date_open;
		this.date_close = date_close;
		this.act = act;
		this.bank_id = bank_id;
		this.bankacc = bankacc;
		this.bankinn = bankinn;
		this.bankaccname = bankaccname;


    }
    

	public String getNci_id() { 
		return nci_id;
	} 

	public void setNci_id(String nci_id) { 
		this.nci_id = nci_id;
	} 

	public String getTreasure_id() { 
		return treasure_id;
	} 

	public void setTreasure_id(String treasure_id) { 
		this.treasure_id = treasure_id;
	} 

	public String getAccount() { 
		return account;
	} 

	public void setAccount(String account) { 
		this.account = account;
	} 

	public String getInn() { 
		return inn;
	} 

	public void setInn(String inn) { 
		this.inn = inn;
	} 

	public String getName() { 
		return name;
	} 

	public void setName(String name) { 
		this.name = name;
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

	public String getBank_id() { 
		return bank_id;
	} 

	public void setBank_id(String bank_id) { 
		this.bank_id = bank_id;
	} 

	public String getBankacc() { 
		return bankacc;
	} 

	public void setBankacc(String bankacc) { 
		this.bankacc = bankacc;
	} 

	public String getBankinn() { 
		return bankinn;
	} 

	public void setBankinn(String bankinn) { 
		this.bankinn = bankinn;
	} 

	public String getBankaccname() { 
		return bankaccname;
	} 

	public void setBankaccname(String bankaccname) { 
		this.bankaccname = bankaccname;
	}

    public String getAccAndTaxNumber(){
        return String.format("%s%s", StringUtils.rightPad(bankaccname, 50, " "), inn);
    }
}