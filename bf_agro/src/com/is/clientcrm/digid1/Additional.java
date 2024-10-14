package com.is.clientcrm.digid1;

public class Additional {
	
	private String Inn;
	private String InnDate;
	private String TaxCode;
	private String TaxName;
	private String Inps;
	private String InpsDate;
	private String InpsDocument;
	private String InpsIssuedBy;
	private String Additional;
	
	public Additional(String inn, String innDate, String taxCode, String taxName, String inps, String inpsDate,
			String inpsDocument, String inpsIssuedBy, String additional) {
		super();
		Inn = inn;
		InnDate = innDate;
		TaxCode = taxCode;
		TaxName = taxName;
		Inps = inps;
		InpsDate = inpsDate;
		InpsDocument = inpsDocument;
		InpsIssuedBy = inpsIssuedBy;
		Additional = additional;
	}
	public String getInn() {
		return Inn;
	}
	public void setInn(String inn) {
		Inn = inn;
	}
	public String getInnDate() {
		return InnDate;
	}
	public void setInnDate(String innDate) {
		InnDate = innDate;
	}
	public String getTaxCode() {
		return TaxCode;
	}
	public void setTaxCode(String taxCode) {
		TaxCode = taxCode;
	}
	public String getTaxName() {
		return TaxName;
	}
	public void setTaxName(String taxName) {
		TaxName = taxName;
	}
	public String getInps() {
		return Inps;
	}
	public void setInps(String inps) {
		Inps = inps;
	}
	public String getInpsDate() {
		return InpsDate;
	}
	public void setInpsDate(String inpsDate) {
		InpsDate = inpsDate;
	}
	public String getInpsDocument() {
		return InpsDocument;
	}
	public void setInpsDocument(String inpsDocument) {
		InpsDocument = inpsDocument;
	}
	public String getInpsIssuedBy() {
		return InpsIssuedBy;
	}
	public void setInpsIssuedBy(String inpsIssuedBy) {
		InpsIssuedBy = inpsIssuedBy;
	}
	public String getAdditional() {
		return Additional;
	}
	public void setAdditional(String additional) {
		Additional = additional;
	}
	
	
	

}
