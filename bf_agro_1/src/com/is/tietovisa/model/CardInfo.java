package com.is.tietovisa.model;

import java.math.BigDecimal;

public class CardInfo {
	private String ACCOUNT_NO;
	private String CARD_ACCT;
	private String CARD;
	private String BASE_SUPP;
	private String STATUS;
	private String STATUS1;	
	private String STATUS2;
	private String STOP_CAUSE;
	private String EXPIRY;
	private String EXPIRY1;	
	private String EXPIRY2;
	private String COND_SET;
	private String RISK_LEVEL;
	private String CL_ROLE;
	private String AGREEMENT_KEY;
	private String CARD_String;
	private String CARD_NAME;
	private String BANK_C;
	private String GROUPC;
	private String ICO_EXPORT;
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
	private String cardExpiry;
	private String sequenceNumber;
	private String firstName;
	private String lastName;
	private String dateOpen;
	private String client_id;
	private String client;
	private String socialNumber;
	private String design_id;
	private boolean way_exist;
	private boolean sign_error_record;
	private boolean sign_reissued;
	
	public CardInfo(String aCCOUNT_NO, String cARD_ACCT, String cARD,
			String bASE_SUPP, String sTATUS, String sTATUS2, String sTOP_CAUSE,
			String eXPIRY, String eXPIRY2, String cOND_SET, String rISK_LEVEL,
			String cL_ROLE, String aGREEMENT_KEY,
			String cARD_String, String bANK_C, String gROUPC) {
		this.ACCOUNT_NO = aCCOUNT_NO;
		this.CARD_ACCT = cARD_ACCT;
		this.CARD = cARD;
		this.BASE_SUPP = bASE_SUPP;
		this.STATUS = sTATUS;
		this.STATUS2 = sTATUS2;
		this.STOP_CAUSE = sTOP_CAUSE;
		this.EXPIRY = eXPIRY;
		this.EXPIRY2 = eXPIRY2;
		this.COND_SET = cOND_SET;
		this.RISK_LEVEL = rISK_LEVEL;
		this.CL_ROLE = cL_ROLE;
		this.AGREEMENT_KEY = aGREEMENT_KEY;
		this.CARD_String = cARD_String;
		this.BANK_C = bANK_C;
		this.GROUPC = gROUPC;
	}

	public CardInfo() {
		super();
	}

	public CardInfo clone (CardInfo old) {
		CardInfo newCard=new CardInfo();
		
		newCard.setACCOUNT_NO(old.getACCOUNT_NO());
		newCard.setCARD_ACCT(old.getCARD_ACCT());
		newCard.setCARD(old.getCARD());
		newCard.setBASE_SUPP(old.getBASE_SUPP());
		newCard.setSTATUS(old.getSTATUS());
		newCard.setSTATUS2(old.getSTATUS2());
		newCard.setSTOP_CAUSE(old.getSTOP_CAUSE());
		newCard.setEXPIRY(old.getEXPIRY());
		newCard.setEXPIRY2(old.getEXPIRY2());
		newCard.setCOND_SET(old.getCOND_SET());
		newCard.setRISK_LEVEL(old.getRISK_LEVEL());
		newCard.setCL_ROLE(old.getCL_ROLE());
		newCard.setAGREEMENT_KEY(old.getAGREEMENT_KEY());
		newCard.setCARD_String(old.getCARD_String());
		newCard.setCARD_NAME(old.getCARD_NAME());
		newCard.setBANK_C(old.getBANK_C());
		newCard.setGROUPC(old.getGROUPC());
		newCard.setICO_EXPORT(old.getICO_EXPORT());
		newCard.setACCOUNT_AVAIL_AMOUNT(old.getACCOUNT_AVAIL_AMOUNT());
		newCard.setACCOUNT_LOCKED_AMOUNT(old.getACCOUNT_LOCKED_AMOUNT());
		newCard.setACCOUNT_END_BAL(old.getACCOUNT_END_BAL());
		newCard.setBank_account(old.getBank_account());
		newCard.setBank_account_status(old.getBank_account_status());
		newCard.setBank_account_Ccy(old.getBank_account_Ccy());
		//newCard.setIsActive(old.getIsActive());
		newCard.setBranch(old.getBranch());
		newCard.setRbsNumberWay(old.getRbsNumberWay());
		newCard.setRbsNumberIbs(old.getRbsNumberIbs());
		newCard.setContractNumber(old.getContractNumber());
		newCard.setContractName(old.getContractName());
		newCard.setCbsNumber(old.getCbsNumber());
		newCard.setCurrency(old.getCurrency());
		newCard.setCommentText(old.getCommentText());
		newCard.setMasterProductCode1(old.getMasterProductCode1());
		newCard.setProductCode1(old.getProductCode1());
		newCard.setCardExpiry(old.getCardExpiry());
		newCard.setSequenceNumber(old.getSequenceNumber());
		newCard.setFirstName(old.getFirstName());
		newCard.setLastName(old.getLastName());
		newCard.setDateOpen(old.getDateOpen());
		newCard.setClient_id(old.getClient_id());
		newCard.setSocialNumber(old.getSocialNumber());
		newCard.setDesign_id(old.getDesign_id());
		//newCard.setWay_exist(old.getWay_exist());
		//newCard.setSign_error_record(old.getSign_error_record());

		return newCard;
		
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

	public String getBASE_SUPP() {
		return this.BASE_SUPP;
	}

	public void setBASE_SUPP(String bASE_SUPP) {
		this.BASE_SUPP = bASE_SUPP;
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

	public String getEXPIRY() {
		return this.EXPIRY;
	}

	public void setEXPIRY(String eXPIRY) {
		this.EXPIRY = eXPIRY;
	}

	public String getEXPIRY2() {
		return this.EXPIRY2;
	}

	public void setEXPIRY2(String eXPIRY2) {
		this.EXPIRY2 = eXPIRY2;
	}

	public String getCOND_SET() {
		return this.COND_SET;
	}

	public void setCOND_SET(String cOND_SET) {
		this.COND_SET = cOND_SET;
	}

	public String getRISK_LEVEL() {
		return this.RISK_LEVEL;
	}

	public void setRISK_LEVEL(String rISK_LEVEL) {
		this.RISK_LEVEL = rISK_LEVEL;
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

	public String getAGREEMENT_KEY() {
		return this.AGREEMENT_KEY;
	}

	public void setAGREEMENT_KEY(String aGREEMENT_KEY) {
		this.AGREEMENT_KEY = aGREEMENT_KEY;
	}

	public String getCARD_String() {
		return this.CARD_String;
	}

	public void setCARD_String(String cARD_String) {
		this.CARD_String = cARD_String;
	}

	public String getBANK_C() {
		return this.BANK_C;
	}

	public void setBANK_C(String bANK_C) {
		this.BANK_C = bANK_C;
	}

	public String getGROUPC() {
		return this.GROUPC;
	}

	public void setGROUPC(String gROUPC) {
		this.GROUPC = gROUPC;
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
				+ CARD_ACCT + ", CARD=" + CARD + ", BASE_SUPP=" + BASE_SUPP
				+ ", STATUS=" + STATUS + ", STATUS2=" + STATUS2
				+ ", STOP_CAUSE=" + STOP_CAUSE + ", EXPIRY=" + EXPIRY
				+ ", EXPIRY2=" + EXPIRY2 + ", COND_SET=" + COND_SET
				+ ", RISK_LEVEL=" + RISK_LEVEL + ", CLIENT_ID=" + client_id
				+ ", CL_ROLE=" + CL_ROLE + ", AGREEMENT_KEY=" + AGREEMENT_KEY
				+ ", CARD_String=" + CARD_String + ", BANK_C=" + BANK_C
				+ ", GROUPC=" + GROUPC + ", ACCOUNT_AVAIL_AMOUNT="
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

	public void setSign_error_record(boolean sign_error_record) {
		this.sign_error_record = sign_error_record;
	}

	public boolean isSign_error_record() {
		return sign_error_record;
	}

	public void setCARD_NAME(String cARD_NAME) {
		CARD_NAME = cARD_NAME;
	}

	public String getCARD_NAME() {
		return CARD_NAME;
	}

	public void setICO_EXPORT(String iCO_EXPORT) {
		ICO_EXPORT = iCO_EXPORT;
	}

	public String getICO_EXPORT() {
		return ICO_EXPORT;
	}

	public void setDesign_id(String design_id) {
		this.design_id = design_id;
	}

	public String getDesign_id() {
		return design_id;
	}

	public void setEXPIRY1(String eXPIRY1) {
		EXPIRY1 = eXPIRY1;
	}

	public String getEXPIRY1() {
		return EXPIRY1;
	}

	public void setSTATUS1(String sTATUS1) {
		STATUS1 = sTATUS1;
	}

	public String getSTATUS1() {
		return STATUS1;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClient() {
		return client;
	}

	public void setSign_reissued(boolean sign_reissued) {
		this.sign_reissued = sign_reissued;
	}

	public boolean isSign_reissued() {
		return sign_reissued;
	}

}
