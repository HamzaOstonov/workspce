package com.is.openwayj.model;

import java.math.BigDecimal;

public class CardInfo {
	private String ACCOUNT_NO;
	private String CARD_ACCT;
	private String CARD;
	private String STATUS;
	private String STATUS2;
	private String STOP_CAUSE;
	//private String EXPIRY;
	//private String EXPIRY2;
	private String CL_ROLE;
	private String CARD_String;
	private BigDecimal ACCOUNT_AVAIL_AMOUNT;
	private BigDecimal ACCOUNT_LOCKED_AMOUNT;
	private BigDecimal ACCOUNT_END_BAL;
	private String Bank_account;
	private String Bank_account_status;
	private String Bank_account_Ccy;
	private boolean isActive;
	private String branch;
	private String rbsNumberWay;
	private String rbsNumberIbs;
	private String contractNumber;
	private String contractName;
	private String cbsNumber;
	private String currency;
	private String commentText;
	private String masterProductCode1;
	private String productCode1;
	private String productWay;
	private String productIbs;
	private String cardExpiry;
	private String sequenceNumber;
	private String firstName;
	private String lastName;
	private String dateOpen;
	private String client_id;
	private String socialNumber;
	private String phone;
	private String order_dprt;
	private boolean way_exist;
	
	public CardInfo(String aCCOUNT_NO, String cARD_ACCT, String cARD,
			String bASE_SUPP, String sTATUS, String sTATUS2, String sTOP_CAUSE,
			String eXPIRY, String eXPIRY2, String cOND_SET, String rISK_LEVEL,
			String cL_ROLE, String aGREEMENT_KEY,
			String cARD_String, String bANK_C, String gROUPC) {
		this.ACCOUNT_NO = aCCOUNT_NO;
		this.CARD_ACCT = cARD_ACCT;
		this.CARD = cARD;
		//this.BASE_SUPP = bASE_SUPP;
		this.STATUS = sTATUS;
		this.STATUS2 = sTATUS2;
		this.STOP_CAUSE = sTOP_CAUSE;
		//this.EXPIRY = eXPIRY;
		//this.EXPIRY2 = eXPIRY2;
		//this.COND_SET = cOND_SET;
		//this.RISK_LEVEL = rISK_LEVEL;
		this.CL_ROLE = cL_ROLE;
		//this.AGREEMENT_KEY = aGREEMENT_KEY;
		this.CARD_String = cARD_String;
		
	}

	public CardInfo() {
		super();
	}

	public String getACCOUNT_NO() {
		return this.ACCOUNT_NO;
	}

	public void setACCOUNT_NO(String aCCOUNT_NO) {
		this.ACCOUNT_NO = aCCOUNT_NO;
	}

	public String getCARD_ACCT() {
		return this.CARD_ACCT;
	}

	public void setCARD_ACCT(String cARD_ACCT) {
		this.CARD_ACCT = cARD_ACCT;
	}

	public String getCARD() {
		return this.CARD;
	}

	public void setCARD(String cARD) {
		this.CARD = cARD;
	}

	
	public String getSTATUS() {
		return this.STATUS;
	}

	public void setSTATUS(String sTATUS) {
		this.STATUS = sTATUS;
	}

	public String getSTATUS2() {
		return this.STATUS2;
	}

	public void setSTATUS2(String sTATUS2) {
		this.STATUS2 = sTATUS2;
	}

	public String getSTOP_CAUSE() {
		return this.STOP_CAUSE;
	}

	public void setSTOP_CAUSE(String sTOP_CAUSE) {
		this.STOP_CAUSE = sTOP_CAUSE;
	}

	

	

	public String getClient_id() {
		return this.client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getCL_ROLE() {
		return this.CL_ROLE;
	}

	public void setCL_ROLE(String cL_ROLE) {
		this.CL_ROLE = cL_ROLE;
	}



	public String getCARD_String() {
		return this.CARD_String;
	}

	public void setCARD_String(String cARD_String) {
		this.CARD_String = cARD_String;
	}

	

	public BigDecimal getACCOUNT_AVAIL_AMOUNT() {
		return this.ACCOUNT_AVAIL_AMOUNT;
	}

	public void setACCOUNT_AVAIL_AMOUNT(BigDecimal aCCOUNT_AVAIL_AMOUNT) {
		this.ACCOUNT_AVAIL_AMOUNT = aCCOUNT_AVAIL_AMOUNT;
	}

	public BigDecimal getACCOUNT_LOCKED_AMOUNT() {
		return this.ACCOUNT_LOCKED_AMOUNT;
	}

	public void setACCOUNT_LOCKED_AMOUNT(BigDecimal aCCOUNT_LOCKED_AMOUNT) {
		this.ACCOUNT_LOCKED_AMOUNT = aCCOUNT_LOCKED_AMOUNT;
	}

	public BigDecimal getACCOUNT_END_BAL() {
		return this.ACCOUNT_END_BAL;
	}

	public void setACCOUNT_END_BAL(BigDecimal aCCOUNT_END_BAL) {
		this.ACCOUNT_END_BAL = aCCOUNT_END_BAL;
	}

	public String getBank_account() {
		return this.Bank_account;
	}

	public void setBank_account(String bank_account) {
		this.Bank_account = bank_account;
	}

	public String getBank_account_status() {
		return this.Bank_account_status;
	}

	public void setBank_account_status(String bank_account_status) {
		this.Bank_account_status = bank_account_status;
	}

	public String getBank_account_Ccy() {
		return this.Bank_account_Ccy;
	}

	public void setBank_account_Ccy(String bank_account_Ccy) {
		this.Bank_account_Ccy = bank_account_Ccy;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CardInfo [ACCOUNT_NO=" + ACCOUNT_NO + ", CARD_ACCT="
				+ CARD_ACCT + ", CARD=" + CARD + ", BASE_SUPP=" + ""
				+ ", STATUS=" + STATUS + ", STATUS2=" + STATUS2
				+ ", STOP_CAUSE=" + STOP_CAUSE + ", EXPIRY=" + ""
				+ ", EXPIRY2=" + "" + ", COND_SET=" + ""
				+ ", RISK_LEVEL=" + "" + ", CLIENT_ID=" + client_id
				+ ", CL_ROLE=" + CL_ROLE + ", AGREEMENT_KEY=" + ""
				+ ", CARD_String=" + CARD_String + ", BANK_C=" + ""
				+ ", GROUPC=" + "" + ", ACCOUNT_AVAIL_AMOUNT="
				+ ACCOUNT_AVAIL_AMOUNT + ", ACCOUNT_LOCKED_AMOUNT="
				+ ACCOUNT_LOCKED_AMOUNT + ", ACCOUNT_END_BAL="
				+ ACCOUNT_END_BAL + ", Bank_account=" + Bank_account
				+ ", Bank_account_status=" + Bank_account_status
				+ ", Bank_account_Ccy=" + Bank_account_Ccy + ", isActive="
				+ isActive + "]";
	}

	public String getBranch() {
		return branch;
	}


	public String getContractNumber() {
		return contractNumber;
	}

	public String getContractName() {
		return contractName;
	}

	public String getCbsNumber() {
		return cbsNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public String getCommentText() {
		return commentText;
	}

	public String getProductCode1() {
		return productCode1;
	}

	public String getCardExpiry() {
		return cardExpiry;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDateOpen() {
		return dateOpen;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public void setCbsNumber(String cbsNumber) {
		this.cbsNumber = cbsNumber;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public void setProductCode1(String productCode1) {
		this.productCode1 = productCode1;
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDateOpen(String dateOpen) {
		this.dateOpen = dateOpen;
	}

	public void setMasterProductCode1(String masterProductCode1) {
		this.masterProductCode1 = masterProductCode1;
	}

	public String getMasterProductCode1() {
		return masterProductCode1;
	}

	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
	}

	public String getSocialNumber() {
		return socialNumber;
	}

	public void setRbsNumberIbs(String rbsNumberIbs) {
		this.rbsNumberIbs = rbsNumberIbs;
	}

	public String getRbsNumberIbs() {
		return rbsNumberIbs;
	}

	public void setRbsNumberWay(String rbsNumberWay) {
		this.rbsNumberWay = rbsNumberWay;
	}

	public String getRbsNumberWay() {
		return rbsNumberWay;
	}

	public void setWay_exist(boolean way_exist) {
		this.way_exist = way_exist;
	}

	public boolean isWay_exist() {
		return way_exist;
	}

	public void setProductWay(String productWay) {
		this.productWay = productWay;
	}

	public String getProductWay() {
		return productWay;
	}

	public void setProductIbs(String productIbs) {
		this.productIbs = productIbs;
	}

	public String getProductIbs() {
		return productIbs;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setOrder_dprt(String order_dprt) {
		this.order_dprt = order_dprt;
	}

	public String getOrder_dprt() {
		return order_dprt;
	}

}
