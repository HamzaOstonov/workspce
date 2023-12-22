package com.is.tieto_visa.fileProcessing;

public class HumoFileRecords {
Long id;
String state;
String merchant;
String card;
String termId;
String mcc;
String tran_type;
String errorText;
String summa;
String tran_date_time;
String abvr_name;
String tranz_acct;
String tran_amt2;

String client        ;
String card_acct     ;
String accnt_ccy     ;
String slip_nr       ;
String ref_number    ;
String rec_date      ;
String post_date     ;
String deal_desc     ;
String deb_cred      ;
String tran_ccy      ;
String tran_amt      ;
String accnt_amt     ;
String terminal      ;
String mcc_code      ;
String country       ;
String city          ;
String proc_id       ;
String internal_no   ;
String product       ;
String iss_mfo       ;
String term_id       ;
String tran_type2    ;
String tr_code       ;
String tr_fee        ;
String fee           ;
String surcharge     ;
String card_cond_set ;
String amount        ;

private Double line_number;

public String getClient() {
	return client;
}
public void setClient(String client) {
	this.client = client;
}
public String getCard_acct() {
	return card_acct;
}
public void setCard_acct(String card_acct) {
	this.card_acct = card_acct;
}
public String getAccnt_ccy() {
	return accnt_ccy;
}
public void setAccnt_ccy(String accnt_ccy) {
	this.accnt_ccy = accnt_ccy;
}
public String getSlip_nr() {
	return slip_nr;
}
public void setSlip_nr(String slip_nr) {
	this.slip_nr = slip_nr;
}
public String getRef_number() {
	return ref_number;
}
public void setRef_number(String ref_number) {
	this.ref_number = ref_number;
}
public String getRec_date() {
	return rec_date;
}
public void setRec_date(String rec_date) {
	this.rec_date = rec_date;
}
public String getPost_date() {
	return post_date;
}
public void setPost_date(String post_date) {
	this.post_date = post_date;
}
public String getDeal_desc() {
	return deal_desc;
}
public void setDeal_desc(String deal_desc) {
	this.deal_desc = deal_desc;
}
public String getDeb_cred() {
	return deb_cred;
}
public void setDeb_cred(String deb_cred) {
	this.deb_cred = deb_cred;
}
public String getTran_ccy() {
	return tran_ccy;
}
public void setTran_ccy(String tran_ccy) {
	this.tran_ccy = tran_ccy;
}
public String getTran_amt() {
	return tran_amt;
}
public void setTran_amt(String tran_amt) {
	this.tran_amt = tran_amt;
}
public String getAccnt_amt() {
	return accnt_amt;
}
public void setAccnt_amt(String accnt_amt) {
	this.accnt_amt = accnt_amt;
}
public String getTerminal() {
	return terminal;
}
public void setTerminal(String terminal) {
	this.terminal = terminal;
}
public String getMcc_code() {
	return mcc_code;
}
public void setMcc_code(String mcc_code) {
	this.mcc_code = mcc_code;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getProc_id() {
	return proc_id;
}
public void setProc_id(String proc_id) {
	this.proc_id = proc_id;
}
public String getInternal_no() {
	return internal_no;
}
public void setInternal_no(String internal_no) {
	this.internal_no = internal_no;
}
public String getProduct() {
	return product;
}
public void setProduct(String product) {
	this.product = product;
}
public String getIss_mfo() {
	return iss_mfo;
}
public void setIss_mfo(String iss_mfo) {
	this.iss_mfo = iss_mfo;
}
public String getTerm_id() {
	return term_id;
}
public void setTerm_id(String term_id) {
	this.term_id = term_id;
}
public String getTran_type2() {
	return tran_type2;
}
public void setTran_type2(String tran_type2) {
	this.tran_type2 = tran_type2;
}
public String getTr_code() {
	return tr_code;
}
public void setTr_code(String tr_code) {
	this.tr_code = tr_code;
}
public String getTr_fee() {
	return tr_fee;
}
public void setTr_fee(String tr_fee) {
	this.tr_fee = tr_fee;
}
public String getFee() {
	return fee;
}
public void setFee(String fee) {
	this.fee = fee;
}
public String getSurcharge() {
	return surcharge;
}
public void setSurcharge(String surcharge) {
	this.surcharge = surcharge;
}
public String getCard_cond_set() {
	return card_cond_set;
}
public void setCard_cond_set(String card_cond_set) {
	this.card_cond_set = card_cond_set;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}

public String getTran_amt2() {
	return tran_amt2;
}
public void setTran_amt2(String tran_amt2) {
	this.tran_amt2 = tran_amt2;
}
public String getTranz_acct() {
	return tranz_acct;
}
public void setTranz_acct(String tranz_acct) {
	this.tranz_acct = tranz_acct;
}
public String getAbvr_name() {
	return abvr_name;
}
public void setAbvr_name(String abvr_name) {
	this.abvr_name = abvr_name;
}
public String getTran_date_time() {
	return tran_date_time;
}
public void setTran_date_time(String tran_date_time) {
	this.tran_date_time = tran_date_time;
}
public String getCard() {
	return card;
}
public void setCard(String card) {
	this.card = card;
}
public String getSumma() {
	return summa;
}
public void setSumma(String summa) {
	this.summa = summa;
}
public String getTran_type() {
	return tran_type;
}
public void setTran_type(String tran_type) {
	this.tran_type = tran_type;
}
public String getErrorText() {
	return errorText;
}
public void setErrorText(String errorText) {
	this.errorText = errorText;
}
public HumoFileRecords(Long id, String state, String merchant,String abvr_name, String card,String tranz_acct, String termId,
		String mcc,String tran_type,String summa,String tran_date_time,String errorText,String tran_amt2, String tran_amt) {
	super();
	this.id = id;
	this.state = state;
	this.merchant = merchant;
	this.termId = termId;
	this.mcc = mcc;
	this.tran_type = tran_type;
	this.summa = summa;
	this.errorText=errorText;
	this.card=card;
	this.tran_date_time=tran_date_time;
	this.abvr_name=abvr_name;
	this.tranz_acct=tranz_acct;
	this.tran_amt2=tran_amt2;
	this.tran_amt=tran_amt;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getMerchant() {
	return merchant;
}
public void setMerchant(String merchant) {
	this.merchant = merchant;
}
public String getTermId() {
	return termId;
}
public void setTermId(String termId) {
	this.termId = termId;
}
public String getMcc() {
	return mcc;
}
public void setMcc(String mcc) {
	this.mcc = mcc;
}
public HumoFileRecords() {
	super();
}
public void setLine_number(Double line_number) {
	this.line_number = line_number;
}
public Double getLine_number() {
	return line_number;
}


}
