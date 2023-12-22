package com.is.korona_pay;

import java.math.BigDecimal;
import java.util.Date;

public class PrintOrders {

	private String type_order;
	private Date v_date;
	//private String v_date;
    //private String client;
    private String client_name1;
    private String client_name2;
    private String client_name3;
    
    private String doc_series;
    private String doc_number;
    private String doc_date_issue;
    private String doc_issue;
    
    private String accdoper1;
    private BigDecimal summaoper1;
    private BigDecimal comission;
    private String cur;
    private String branch;
    private String opendoper;
    private String purpose1;
	private String psummaoper1;
	private String summa5;
	private String psumma5;
	private String kursvalfxsumma;
	private String pkursvalfxsumma;
	private String tveoper;
	
	private String message;
    
	public String getTveoper() {
		return tveoper;
	}


	public void setTveoper(String tveoper) {
		this.tveoper = tveoper;
	}


	public PrintOrders() {
	
		// TODO Auto-generated constructor stub
	}
	

	public String getType_order() {
		return type_order;
	}


	public void setType_order(String type_order) {
		this.type_order = type_order;
	}



	public Date getV_date() {
		return v_date;
	}


	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}
	
	
	public String getClient_name1() {
		return client_name1;
	}

	
	

	public void setClient_name1(String client_name1) {
		this.client_name1 = client_name1;
	}

	
	public String getClient_name2() {
		return client_name2;
	}

	
	public void setClient_name2(String client_name2) {
		this.client_name2 = client_name2;
	}

	public String getClient_name3() {
		return client_name3;
	}

	
	public void setClient_name3(String client_name3) {
		this.client_name3 = client_name3;
	}
	
	public String getDoc_series() {
		return doc_series;
	}


	public void setDoc_series(String doc_series) {
		this.doc_series = doc_series;
	}


	public String getDoc_number() {
		return doc_number;
	}


	public void setDoc_number(String doc_number) {
		this.doc_number = doc_number;
	}


	public String getDoc_date_issue() {
		return doc_date_issue;
	}


	public void setDoc_date_issue(String doc_date_issue) {
		this.doc_date_issue = doc_date_issue;
	}


	public String getDoc_issue() {
		return doc_issue;
	}


	public void setDoc_issue(String doc_issue) {
		this.doc_issue = doc_issue;
	}


	public String getAccdoper1() {
		return accdoper1;
	}

	
	public void setAccdoper1(String accdoper1) {
		this.accdoper1 = accdoper1;
	}

	
	public BigDecimal getSummaoper1() {
		return summaoper1;
	}

	
	public void setSummaoper1(BigDecimal bigDecimal) {
		this.summaoper1 = bigDecimal;
	}

	public BigDecimal getComission() {
		return comission;
	}

	public void setComission(BigDecimal comission) {
		this.comission = comission;
	}

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur;
	}

	public String getBranch() {
		return branch;
	}


	public void setBranch(String string) {
		this.branch = string;
	}


	public String getOpendoper() {
		return opendoper;
	}


	public void setOpendoper(String opendoper) {
		this.opendoper = opendoper;
	}



	public String getPurpose1() {
		return purpose1;
	}

	
	public void setPurpose1(String purpose1) {
		this.purpose1 = purpose1;
	}


	public String getPsummaoper1() {
		return psummaoper1;
	}


	public void setPsummaoper1(String psummaoper1) {
		this.psummaoper1 = psummaoper1;
	}


	public String getSumma5() {
		return summa5;
	}


	public void setSumma5(String summa5) {
		this.summa5 = summa5;
	}


	public String getPsumma5() {
		return psumma5;
	}


	public void setPsumma5(String psumma5) {
		this.psumma5 = psumma5;
	}


	public String getKursvalfxsumma() {
		return kursvalfxsumma;
	}


	public void setKursvalfxsumma(String kursvalfxsumma) {
		this.kursvalfxsumma = kursvalfxsumma;
	}


	public String getPkursvalfxsumma() {
		return pkursvalfxsumma;
	}


	public void setPkursvalfxsumma(String pkursvalfxsumma) {
		this.pkursvalfxsumma = pkursvalfxsumma;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

	
	
}
