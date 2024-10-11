package com.is.tietovisa.model;

//import visa.issuing_v_01_02_xsd.RowType_AccountInfo;
//import visa.issuing_v_01_02_xsd.RowType_AccountInfo_Base;
//import visa.issuing_v_01_02_xsd.RowType_Agreement;
//import visa.issuing_v_01_02_xsd.RowType_CardInfo;

public class NewAgreementRequest {
	private RowType_Agreement rt_agreement;
	private RowType_AccountInfo rt_accountInfo; 
	private RowType_CardInfo rt_cardInfo;
	
	public RowType_Agreement getRt_agreement() {
		return rt_agreement;
	}
	public void setRt_agreement(RowType_Agreement rt_agreement) {
		this.rt_agreement = rt_agreement;
	}
	public RowType_AccountInfo getRt_accountInfo() {
		return rt_accountInfo;
	}
	public void setRt_accountInfo(RowType_AccountInfo rt_accountInfo) {
		this.rt_accountInfo = rt_accountInfo;
	}
	public RowType_CardInfo getRt_cardInfo() {
		return rt_cardInfo;
	}
	public void setRt_cardInfo(RowType_CardInfo rt_cardInfo) {
		this.rt_cardInfo = rt_cardInfo;
	}
	
}
