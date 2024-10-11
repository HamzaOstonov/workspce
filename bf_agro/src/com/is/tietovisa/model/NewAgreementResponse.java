package com.is.tietovisa.model;

//import visa.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
//import visa.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
//import visa.issuing_v_01_02_xsd.holders.RowType_AgreementHolder;

public class NewAgreementResponse {
	private String err_code;
	private String err_text;
	private RowType_AgreementHolder rt_agreementHolder;
	private ListType_AccountInfoHolder lt_accountInfoHolder;
	private ListType_CardInfoHolder lt_cardInfoHolder;
	
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_text() {
		return err_text;
	}
	public void setErr_text(String err_text) {
		this.err_text = err_text;
	}
	public RowType_AgreementHolder getRt_agreementHolder() {
		return rt_agreementHolder;
	}
	public void setRt_agreementHolder(RowType_AgreementHolder rt_agreementHolder) {
		this.rt_agreementHolder = rt_agreementHolder;
	}
	public ListType_AccountInfoHolder getLt_accountInfoHolder() {
		return lt_accountInfoHolder;
	}
	public void setLt_accountInfoHolder(ListType_AccountInfoHolder lt_accountInfoHolder) {
		this.lt_accountInfoHolder = lt_accountInfoHolder;
	}
	public ListType_CardInfoHolder getLt_cardInfoHolder() {
		return lt_cardInfoHolder;
	}
	public void setLt_cardInfoHolder(ListType_CardInfoHolder lt_cardInfoHolder) {
		this.lt_cardInfoHolder = lt_cardInfoHolder;
	}
	
}
