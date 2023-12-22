package com.is.tf.confirm;

public class Confirm {
	private String bankinn;
    private String contractidn;
    private String doctype;
    private String docnum;
    private String chdocnum;
    private String confirm;
    private String reason;
    private String responsiblename;
	
    public Confirm() {
    	super();
    }
    public Confirm(String bankinn, String contractidn, String doctype,
			String docnum, String chdocnum, String confirm, String reason,
			String responsiblename) {
		super();
		this.bankinn = bankinn;
		this.contractidn = contractidn;
		this.doctype = doctype;
		this.docnum = docnum;
		this.chdocnum = chdocnum;
		this.confirm = confirm;
		this.reason = reason;
		this.responsiblename = responsiblename;
	}


	public String getBankinn() {
		return bankinn;
	}


	public void setBankinn(String bankinn) {
		this.bankinn = bankinn;
	}


	public String getContractidn() {
		return contractidn;
	}


	public void setContractidn(String contractidn) {
		this.contractidn = contractidn;
	}


	public String getDoctype() {
		return doctype;
	}


	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}


	public String getDocnum() {
		return docnum;
	}


	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}


	public String getChdocnum() {
		return chdocnum;
	}


	public void setChdocnum(String chdocnum) {
		this.chdocnum = chdocnum;
	}


	public String getConfirm() {
		return confirm;
	}


	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getResponsiblename() {
		return responsiblename;
	}


	public void setResponsiblename(String responsiblename) {
		this.responsiblename = responsiblename;
	}
    
    
    
}

