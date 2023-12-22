package com.is.tieto_globuz.agreements;

public class AgreementFilter {
	private Long id;
	private String merchant;
	private String agreement_type;
	private String action;
	
	public AgreementFilter() {
		super();
	}

	public AgreementFilter(Long id, String merchant, String agreement_type, String action) {
		super();
		this.id = id;
		this.merchant = merchant;
		this.agreement_type = agreement_type;
		this.action = action;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getAgreement_type() {
		return agreement_type;
	}

	public void setAgreement_type(String agreement_type) {
		this.agreement_type = agreement_type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
