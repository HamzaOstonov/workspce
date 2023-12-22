package com.is.tieto_globuz.fileProcessing;

import java.io.Serializable;
import java.util.Date;

public class EmpcExptRecord implements Serializable {

    static final long serialVersionUID = 103844514947365244L;

    private Long id;
    private Long empc_file_id;
    private Double state_id;
    private String record_type;
    private Double line_number;
    private String client;
    private String card_acct;
    private String accnt_ccy;
    private String card;
    private String slip_nr;
    private String ref_number;
    private Date tran_date_time;
    private Date rec_date;
    private Date post_date;
    private String deal_desc;
    private String tran_type;
    private String deb_cred;
    private String tran_ccy;
    private Long tran_amt;
    private Long accnt_amt;
    private String terminal;
    private String mcc_code;
    private String merchant;
    private String abvr_name;
    private String country;
    private String city;
    private Long proc_id;
    private Long internal_no;
    private String product;
    private String iss_mfo;
    private String term_id;
    private String tranz_acct;
    
	public EmpcExptRecord() {
		super();
	}

	public EmpcExptRecord(Long id, Long empc_file_id, Double state_id, String record_type, Double line_number,
			String client, String card_acct, String accnt_ccy, String card, String slip_nr, String ref_number,
			Date tran_date_time, Date rec_date, Date post_date, String deal_desc, String tran_type, String deb_cred,
			String tran_ccy, Long tran_amt, Long accnt_amt, String terminal, String mcc_code, String merchant,
			String abvr_name, String country, String city, Long proc_id, Long internal_no, String product,
			String iss_mfo, String term_id, String tranz_acct) {
		super();
		this.id = id;
		this.empc_file_id = empc_file_id;
		this.state_id = state_id;
		this.record_type = record_type;
		this.line_number = line_number;
		this.client = client;
		this.card_acct = card_acct;
		this.accnt_ccy = accnt_ccy;
		this.card = card;
		this.slip_nr = slip_nr;
		this.ref_number = ref_number;
		this.tran_date_time = tran_date_time;
		this.rec_date = rec_date;
		this.post_date = post_date;
		this.deal_desc = deal_desc;
		this.tran_type = tran_type;
		this.deb_cred = deb_cred;
		this.tran_ccy = tran_ccy;
		this.tran_amt = tran_amt;
		this.accnt_amt = accnt_amt;
		this.terminal = terminal;
		this.mcc_code = mcc_code;
		this.merchant = merchant;
		this.abvr_name = abvr_name;
		this.country = country;
		this.city = city;
		this.proc_id = proc_id;
		this.internal_no = internal_no;
		this.product = product;
		this.iss_mfo = iss_mfo;
		this.term_id = term_id;
		this.tranz_acct = tranz_acct;
	}
	
	public EmpcExptRecord(Long id, Double state_id, String slip_nr, Long tran_amt, String mcc_code, String merchant, String term_id) {
		super();
		this.id = id;
		this.state_id = state_id;
		this.slip_nr = slip_nr;
		this.tran_amt = tran_amt;
		this.mcc_code = mcc_code;
		this.merchant = merchant;
		this.term_id = term_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmpc_file_id() {
		return empc_file_id;
	}

	public void setEmpc_file_id(Long empc_file_id) {
		this.empc_file_id = empc_file_id;
	}

	public Double getState_id() {
		return state_id;
	}

	public void setState_id(Double state_id) {
		this.state_id = state_id;
	}

	public String getRecord_type() {
		return record_type;
	}

	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}

	public Double getLine_number() {
		return line_number;
	}

	public void setLine_number(Double line_number) {
		this.line_number = line_number;
	}

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

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
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

	public Date getTran_date_time() {
		return tran_date_time;
	}

	public void setTran_date_time(Date tran_date_time) {
		this.tran_date_time = tran_date_time;
	}

	public Date getRec_date() {
		return rec_date;
	}

	public void setRec_date(Date rec_date) {
		this.rec_date = rec_date;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	public String getDeal_desc() {
		return deal_desc;
	}

	public void setDeal_desc(String deal_desc) {
		this.deal_desc = deal_desc;
	}

	public String getTran_type() {
		return tran_type;
	}

	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
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

	public Long getTran_amt() {
		return tran_amt;
	}

	public void setTran_amt(Long tran_amt) {
		this.tran_amt = tran_amt;
	}

	public Long getAccnt_amt() {
		return accnt_amt;
	}

	public void setAccnt_amt(Long accnt_amt) {
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

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getAbvr_name() {
		return abvr_name;
	}

	public void setAbvr_name(String abvr_name) {
		this.abvr_name = abvr_name;
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

	public Long getProc_id() {
		return proc_id;
	}

	public void setProc_id(Long proc_id) {
		this.proc_id = proc_id;
	}

	public Long getInternal_no() {
		return internal_no;
	}

	public void setInternal_no(Long internal_no) {
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

	public String getTranz_acct() {
		return tranz_acct;
	}

	public void setTranz_acct(String tranz_acct) {
		this.tranz_acct = tranz_acct;
	}    
}
