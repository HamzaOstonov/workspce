package com.is.qr_online.send;

public class Body {
	
	private String payee;
	private String merchant;
	private String transaction;
	private String payee_template;
	private String localized_info;
	private String dsign;
	
	public Body(String payee, String merchant, String transaction, String payee_template,String localized_info, String dsign) {
		super();
		this.payee = payee;
		this.merchant = merchant;
		this.transaction = transaction;
		this.payee_template = payee_template;
		this.localized_info=localized_info;
		this.dsign = dsign;
	}
	
	public Body(){
		
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getPayee_template() {
		return payee_template;
	}

	public void setPayee_template(String payee_template) {
		this.payee_template = payee_template;
	}

		
	public String getDsign() {
		return dsign;
	}

	public void setDsign(String dsign) {
		this.dsign = dsign;
	}

	public String getLocalized_info() {
		return localized_info;
	}

	public void setLocalized_info(String localized_info) {
		this.localized_info = localized_info;
	}

	@Override
	  public String toString() {
	    return "{"
	        + "\"payee\":  " + payee + ", "
	        + "\"merchant\":  " + merchant + ", "
	        + "\"transaction\":  " + transaction + " , "
	        + "\"payee_template\":  " + payee_template + ", "
	        + "\"localized_info\":  " + localized_info + ", "
	        + "\"dsign\":  \"" + dsign + "\" "
	        + "}";
	  }
}
