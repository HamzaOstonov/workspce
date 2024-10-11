package com.is.tieto_visa.fileProcessing;

import java.io.Serializable;
import java.util.Date;

public class EmpcExptRecord implements Serializable{

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
    private String tr_code;
    private String mcc;
    private Long amount;
    
	private String settl_cmi;
	private Date settl_date;
	private String send_cmi;
	private String tran_name_uk;
	private String counterparty;
	private String country_code;
	private Long amount_acc;
	private String tr_fee_code;
	private Long tr_fee_amt;
	private Long i_amount;
	private Long sb_amount;
	private String bank_code;
	private String pointcode;
    private String branch;

    public EmpcExptRecord() {
    }

    public EmpcExptRecord(Long id, Long empc_file_id, Double state_id, String record_type, Double line_number, String client, String card_acct, String accnt_ccy, String card, String slip_nr, String ref_number, Date tran_date_time, Date rec_date, Date post_date, String deal_desc, String tran_type, String deb_cred, String tran_ccy, Long tran_amt, Long accnt_amt, String terminal, String mcc_code, String merchant, String abvr_name, String country, String city, Long proc_id, Long internal_no, String product, String iss_mfo, String term_id, String tranz_acct) {
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
        this.id = id;
        this.state_id = state_id;
        this.slip_nr = slip_nr;
        this.tran_amt = tran_amt;
        this.mcc_code = mcc_code;
        this.merchant = merchant;
        this.term_id = term_id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpc_file_id() {
        return this.empc_file_id;
    }

    public void setEmpc_file_id(Long empc_file_id) {
        this.empc_file_id = empc_file_id;
    }

    public Double getState_id() {
        return this.state_id;
    }

    public void setState_id(Double state_id) {
        this.state_id = state_id;
    }

    public String getRecord_type() {
        return this.record_type;
    }

    public void setRecord_type(String record_type) {
        this.record_type = record_type;
    }

    public Double getLine_number() {
        return this.line_number;
    }

    public void setLine_number(Double line_number) {
        this.line_number = line_number;
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCard_acct() {
        return this.card_acct;
    }

    public void setCard_acct(String card_acct) {
        this.card_acct = card_acct;
    }

    public String getAccnt_ccy() {
        return this.accnt_ccy;
    }

    public void setAccnt_ccy(String accnt_ccy) {
        this.accnt_ccy = accnt_ccy;
    }

    public String getCard() {
        return this.card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getSlip_nr() {
        return this.slip_nr;
    }

    public void setSlip_nr(String slip_nr) {
        this.slip_nr = slip_nr;
    }

    public String getRef_number() {
        return this.ref_number;
    }

    public void setRef_number(String ref_number) {
        this.ref_number = ref_number;
    }

    public Date getTran_date_time() {
        return this.tran_date_time;
    }

    public void setTran_date_time(Date tran_date_time) {
        this.tran_date_time = tran_date_time;
    }

    public Date getRec_date() {
        return this.rec_date;
    }

    public void setRec_date(Date rec_date) {
        this.rec_date = rec_date;
    }

    public Date getPost_date() {
        return this.post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public String getDeal_desc() {
        return this.deal_desc;
    }

    public void setDeal_desc(String deal_desc) {
        this.deal_desc = deal_desc;
    }

    public String getTran_type() {
        return this.tran_type;
    }

    public void setTran_type(String tran_type) {
        this.tran_type = tran_type;
    }

    public String getDeb_cred() {
        return this.deb_cred;
    }

    public void setDeb_cred(String deb_cred) {
        this.deb_cred = deb_cred;
    }

    public String getTran_ccy() {
        return this.tran_ccy;
    }

    public void setTran_ccy(String tran_ccy) {
        this.tran_ccy = tran_ccy;
    }

    public Long getTran_amt() {
        return this.tran_amt;
    }

    public void setTran_amt(Long tran_amt) {
        this.tran_amt = tran_amt;
    }

    public Long getAccnt_amt() {
        return this.accnt_amt;
    }

    public void setAccnt_amt(Long accnt_amt) {
        this.accnt_amt = accnt_amt;
    }

    public String getTerminal() {
        return this.terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getMcc_code() {
        return this.mcc_code;
    }

    public void setMcc_code(String mcc_code) {
        this.mcc_code = mcc_code;
    }

    public String getMerchant() {
        return this.merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getAbvr_name() {
        return this.abvr_name;
    }

    public void setAbvr_name(String abvr_name) {
        this.abvr_name = abvr_name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getProc_id() {
        return this.proc_id;
    }

    public void setProc_id(Long proc_id) {
        this.proc_id = proc_id;
    }

    public Long getInternal_no() {
        return this.internal_no;
    }

    public void setInternal_no(Long internal_no) {
        this.internal_no = internal_no;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getIss_mfo() {
        return this.iss_mfo;
    }

    public void setIss_mfo(String iss_mfo) {
        this.iss_mfo = iss_mfo;
    }

    public String getTerm_id() {
        return this.term_id;
    }

    public void setTerm_id(String term_id) {
        this.term_id = term_id;
    }

    public String getTranz_acct() {
        return this.tranz_acct;
    }

    public void setTranz_acct(String tranz_acct) {
        this.tranz_acct = tranz_acct;
    }

	@Override
	public String toString() {
		return "EmpcExptRecord ["
				+ (id != null ? "id=" + id + ",/n " : "")
				+ (empc_file_id != null ? "empc_file_id=" + empc_file_id
						+ ",/n " : "")
				+ (state_id != null ? "state_id=" + state_id + ",/n " : "")
				+ (record_type != null ? "record_type=" + record_type + ",/n "
						: "")
				+ (line_number != null ? "line_number=" + line_number + ",/n "
						: "")
				+ (client != null ? "client=" + client + ",/n " : "")
				+ (card_acct != null ? "card_acct=" + card_acct + ",/n " : "")
				+ (accnt_ccy != null ? "accnt_ccy=" + accnt_ccy + ",/n " : "")
				+ (card != null ? "card=" + card + ",/n " : "")
				+ (slip_nr != null ? "slip_nr=" + slip_nr + ",/n " : "")
				+ (ref_number != null ? "ref_number=" + ref_number + ",/n "
						: "")
				+ (tran_date_time != null ? "tran_date_time=" + tran_date_time
						+ ",/n " : "")
				+ (rec_date != null ? "rec_date=" + rec_date + ",/n " : "")
				+ (post_date != null ? "post_date=" + post_date + ",/n " : "")
				+ (deal_desc != null ? "deal_desc=" + deal_desc + ",/n " : "")
				+ (tran_type != null ? "tran_type=" + tran_type + ",/n " : "")
				+ (deb_cred != null ? "deb_cred=" + deb_cred + ",/n " : "")
				+ (tran_ccy != null ? "tran_ccy=" + tran_ccy + ",/n " : "")
				+ (tran_amt != null ? "tran_amt=" + tran_amt + ",/n " : "")
				+ (accnt_amt != null ? "accnt_amt=" + accnt_amt + ",/n " : "")
				+ (terminal != null ? "terminal=" + terminal + ",/n " : "")
				+ (mcc_code != null ? "mcc_code=" + mcc_code + ",/n " : "")
				+ (merchant != null ? "merchant=" + merchant + ",/n " : "")
				+ (abvr_name != null ? "abvr_name=" + abvr_name + ",/n " : "")
				+ (country != null ? "country=" + country + ",/n " : "")
				+ (city != null ? "city=" + city + ",/n " : "")
				+ (proc_id != null ? "proc_id=" + proc_id + ",/n " : "")
				+ (internal_no != null ? "internal_no=" + internal_no + ",/n "
						: "")
				+ (product != null ? "product=" + product + ",/n " : "")
				+ (iss_mfo != null ? "iss_mfo=" + iss_mfo + ",/n " : "")
				+ (term_id != null ? "term_id=" + term_id + ",/n " : "")
				+ (tranz_acct != null ? "tranz_acct=" + tranz_acct : "") + "]";
	}

	public void setTr_code(String tr_code) {
		this.tr_code = tr_code;
	}

	public String getTr_code() {
		return tr_code;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMcc() {
		return mcc;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getAmount() {
		return amount;
	}

	public String getSettl_cmi() {
		return settl_cmi;
	}

	public void setSettl_cmi(String settl_cmi) {
		this.settl_cmi = settl_cmi;
	}

	public Date getSettl_date() {
		return settl_date;
	}

	public void setSettl_date(Date settl_date) {
		this.settl_date = settl_date;
	}

	public String getSend_cmi() {
		return send_cmi;
	}

	public void setSend_cmi(String send_cmi) {
		this.send_cmi = send_cmi;
	}

	public String getTran_name_uk() {
		return tran_name_uk;
	}

	public void setTran_name_uk(String tran_name_uk) {
		this.tran_name_uk = tran_name_uk;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public Long getAmount_acc() {
		return amount_acc;
	}

	public void setAmount_acc(Long amount_acc) {
		this.amount_acc = amount_acc;
	}

	public String getTr_fee_code() {
		return tr_fee_code;
	}

	public void setTr_fee_code(String tr_fee_code) {
		this.tr_fee_code = tr_fee_code;
	}

	public Long getTr_fee_amt() {
		return tr_fee_amt;
	}

	public void setTr_fee_amt(Long tr_fee_amt) {
		this.tr_fee_amt = tr_fee_amt;
	}

	public Long getI_amount() {
		return i_amount;
	}

	public void setI_amount(Long i_amount) {
		this.i_amount = i_amount;
	}

	public Long getSb_amount() {
		return sb_amount;
	}

	public void setSb_amount(Long sb_amount) {
		this.sb_amount = sb_amount;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getPointcode() {
		return pointcode;
	}

	public void setPointcode(String pointcode) {
		this.pointcode = pointcode;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBranch() {
		return branch;
	}
	
	
	
}
