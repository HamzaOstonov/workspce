package com.is.humoOperation;

public class HumoOperation {
	
	private String id;
    private String term_type;
    private String oper;
    private String card_branch;
    private String term_branch;
    private String tr_type_expt;
    private String tr_type_b;
    private String mcc;
    private String komis;
    private String terminal;
    private String acc_term;
    private String accnt_ccy;
    private String tran_ccy;
    private String deb_cred;
    private String country;
    private String rid;
    private String in_file;
    private String msc;
    private String tr_type2_expt;
    private String operation_id;
    private String descripption;
    private String dt;
    private String kt;
    
    
    public HumoOperation() {

    }

	public HumoOperation(String id, String term_type, String oper,
			String card_branch, String term_branch, String tr_type_expt,
			String tr_type_b, String mcc, String komis, String terminal,
			String acc_term, String accnt_ccy, String tran_ccy,
			String deb_cred, String country, String in_file, String tr_type2_expt, String msc, String rowid) {
		super();
		this.id = id;
		this.term_type = term_type;
		this.oper = oper;
		this.card_branch = card_branch;
		this.term_branch = term_branch;
		this.tr_type_expt = tr_type_expt;
		this.tr_type_b = tr_type_b;
		this.mcc = mcc;
		this.komis = komis;
		this.terminal = terminal;
		this.acc_term = acc_term;
		this.accnt_ccy = accnt_ccy;
		this.tran_ccy = tran_ccy;
		this.deb_cred = deb_cred;
		this.country = country;
		this.in_file = in_file;
		this.tr_type2_expt = tr_type2_expt;
		this.msc = msc;
		this.rid = rowid;
	}
	
	

	public HumoOperation(String id, String term_type, String oper,
			String card_branch, String term_branch, String tr_type_expt,
			String tr_type_b, String mcc, String komis, String terminal,
			String acc_term, String accnt_ccy, String tran_ccy,
			String deb_cred, String country, String rid) {
		super();
		this.id = id;
		this.term_type = term_type;
		this.oper = oper;
		this.card_branch = card_branch;
		this.term_branch = term_branch;
		this.tr_type_expt = tr_type_expt;
		this.tr_type_b = tr_type_b;
		this.mcc = mcc;
		this.komis = komis;
		this.terminal = terminal;
		this.acc_term = acc_term;
		this.accnt_ccy = accnt_ccy;
		this.tran_ccy = tran_ccy;
		this.deb_cred = deb_cred;
		this.country = country;
		this.rid = rid;
	}
	
	

	public String getTr_type2_expt() {
		return tr_type2_expt;
	}

	public String getOperation_id() {
		return operation_id;
	}

	public String getDescripption() {
		return descripption;
	}

	public String getDt() {
		return dt;
	}

	public String getKt() {
		return kt;
	}

	public void setTr_type2_expt(String tr_type2_expt) {
		this.tr_type2_expt = tr_type2_expt;
	}

	public void setOperation_id(String operation_id) {
		this.operation_id = operation_id;
	}

	public void setDescripption(String descripption) {
		this.descripption = descripption;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public void setKt(String kt) {
		this.kt = kt;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTerm_type() {
		return term_type;
	}

	public void setTerm_type(String term_type) {
		this.term_type = term_type;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getCard_branch() {
		return card_branch;
	}

	public void setCard_branch(String card_branch) {
		this.card_branch = card_branch;
	}

	public String getTerm_branch() {
		return term_branch;
	}

	public void setTerm_branch(String term_branch) {
		this.term_branch = term_branch;
	}

	public String getTr_type_expt() {
		return tr_type_expt;
	}

	public void setTr_type_expt(String tr_type_expt) {
		this.tr_type_expt = tr_type_expt;
	}

	public String getTr_type_b() {
		return tr_type_b;
	}

	public void setTr_type_b(String tr_type_b) {
		this.tr_type_b = tr_type_b;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getKomis() {
		return komis;
	}

	public void setKomis(String komis) {
		this.komis = komis;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getAcc_term() {
		return acc_term;
	}

	public void setAcc_term(String acc_term) {
		this.acc_term = acc_term;
	}

	public String getAccnt_ccy() {
		return accnt_ccy;
	}

	public void setAccnt_ccy(String accnt_ccy) {
		this.accnt_ccy = accnt_ccy;
	}

	public String getTran_ccy() {
		return tran_ccy;
	}

	public void setTran_ccy(String tran_ccy) {
		this.tran_ccy = tran_ccy;
	}

	public String getDeb_cred() {
		return deb_cred;
	}

	public void setDeb_cred(String deb_cred) {
		this.deb_cred = deb_cred;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setIn_file(String in_file) {
		this.in_file = in_file;
	}

	public String getIn_file() {
		return in_file;
	}

	public void setMsc(String msc) {
		this.msc = msc;
	}

	public String getMsc() {
		return msc;
	}
    
    
    

}
