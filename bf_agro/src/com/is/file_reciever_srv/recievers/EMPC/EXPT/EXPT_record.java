// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.EXPT;

import java.util.Date;

public class EXPT_record
{
    private Long id;
    private Long empc_file_id;
    private Integer state_id;
    private String record_type;
    private Long line_number;
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
    private Long product;
    private String iss_mfo;
    private String term_id;
    private String tranz_acct;
    private String tran_type2;
    private String tran_amt2;
    
    public String getTran_type2() {
        return this.tran_type2;
    }
    
    public void setTran_type2(final String tran_type2) {
        this.tran_type2 = tran_type2;
    }
    
    public String getTran_amt2() {
        return this.tran_amt2;
    }
    
    public void setTran_amt2(final String tran_amt2) {
        this.tran_amt2 = tran_amt2;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Long getEmpc_file_id() {
        return this.empc_file_id;
    }
    
    public void setEmpc_file_id(final Long empc_file_id) {
        this.empc_file_id = empc_file_id;
    }
    
    public Integer getState_id() {
        return this.state_id;
    }
    
    public void setState_id(final Integer state_id) {
        this.state_id = state_id;
    }
    
    public String getRecord_type() {
        return this.record_type;
    }
    
    public void setRecord_type(final String record_type) {
        this.record_type = record_type;
    }
    
    public Long getLine_number() {
        return this.line_number;
    }
    
    public void setLine_number(final Long line_number) {
        this.line_number = line_number;
    }
    
    public String getClient() {
        return this.client;
    }
    
    public void setClient(final String client) {
        this.client = client;
    }
    
    public String getCard_acct() {
        return this.card_acct;
    }
    
    public void setCard_acct(final String card_acct) {
        this.card_acct = card_acct;
    }
    
    public String getAccnt_ccy() {
        return this.accnt_ccy;
    }
    
    public void setAccnt_ccy(final String accnt_ccy) {
        this.accnt_ccy = accnt_ccy;
    }
    
    public String getCard() {
        return this.card;
    }
    
    public void setCard(final String card) {
        this.card = card;
    }
    
    public String getSlip_nr() {
        return this.slip_nr;
    }
    
    public void setSlip_nr(final String slip_nr) {
        this.slip_nr = slip_nr;
    }
    
    public String getRef_number() {
        return this.ref_number;
    }
    
    public void setRef_number(final String ref_number) {
        this.ref_number = ref_number;
    }
    
    public Date getTran_date_time() {
        return this.tran_date_time;
    }
    
    public void setTran_date_time(final Date tran_date_time) {
        this.tran_date_time = tran_date_time;
    }
    
    public Date getRec_date() {
        return this.rec_date;
    }
    
    public void setRec_date(final Date rec_date) {
        this.rec_date = rec_date;
    }
    
    public Date getPost_date() {
        return this.post_date;
    }
    
    public void setPost_date(final Date post_date) {
        this.post_date = post_date;
    }
    
    public String getDeal_desc() {
        return this.deal_desc;
    }
    
    public void setDeal_desc(final String deal_desc) {
        this.deal_desc = deal_desc;
    }
    
    public String getTran_type() {
        return this.tran_type;
    }
    
    public void setTran_type(final String tran_type) {
        this.tran_type = tran_type;
    }
    
    public String getDeb_cred() {
        return this.deb_cred;
    }
    
    public void setDeb_cred(final String deb_cred) {
        this.deb_cred = deb_cred;
    }
    
    public String getTran_ccy() {
        return this.tran_ccy;
    }
    
    public void setTran_ccy(final String tran_ccy) {
        this.tran_ccy = tran_ccy;
    }
    
    public Long getTran_amt() {
        return this.tran_amt;
    }
    
    public void setTran_amt(final Long tran_amt) {
        this.tran_amt = tran_amt;
    }
    
    public Long getAccnt_amt() {
        return this.accnt_amt;
    }
    
    public void setAccnt_amt(final Long accnt_amt) {
        this.accnt_amt = accnt_amt;
    }
    
    public String getTerminal() {
        return this.terminal;
    }
    
    public void setTerminal(final String terminal) {
        this.terminal = terminal;
    }
    
    public String getMcc_code() {
        return this.mcc_code;
    }
    
    public void setMcc_code(final String mcc_code) {
        this.mcc_code = mcc_code;
    }
    
    public String getMerchant() {
        return this.merchant;
    }
    
    public void setMerchant(final String merchant) {
        this.merchant = merchant;
    }
    
    public String getAbvr_name() {
        return this.abvr_name;
    }
    
    public void setAbvr_name(final String abvr_name) {
        this.abvr_name = abvr_name;
    }
    
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(final String country) {
        this.country = country;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(final String city) {
        this.city = city;
    }
    
    public Long getProc_id() {
        return this.proc_id;
    }
    
    public void setProc_id(final Long proc_id) {
        this.proc_id = proc_id;
    }
    
    public Long getInternal_no() {
        return this.internal_no;
    }
    
    public void setInternal_no(final Long internal_no) {
        this.internal_no = internal_no;
    }
    
    public Long getProduct() {
        return this.product;
    }
    
    public void setProduct(final Long product) {
        this.product = product;
    }
    
    public String getIss_mfo() {
        return this.iss_mfo;
    }
    
    public void setIss_mfo(final String iss_mfo) {
        this.iss_mfo = iss_mfo;
    }
    
    public String getTerm_id() {
        return this.term_id;
    }
    
    public void setTerm_id(final String term_id) {
        this.term_id = term_id;
    }
    
    public String getTranz_acct() {
        return this.tranz_acct;
    }
    
    public void setTranz_acct(final String tranz_acct) {
        this.tranz_acct = tranz_acct;
    }
    
    public EXPT_record(final Long id, final Long empc_file_id, final Integer state_id, final String record_type, final Long line_number, final String client, final String card_acct, final String accnt_ccy, final String card, final String slip_nr, final String ref_number, final Date tran_date_time, final Date rec_date, final Date post_date, final String deal_desc, final String tran_type, final String deb_cred, final String tran_ccy, final Long tran_amt, final Long accnt_amt, final String terminal, final String mcc_code, final String merchant, final String abvr_name, final String country, final String city, final Long proc_id, final Long internal_no, final Long product, final String iss_mfo, final String term_id, final String tranz_acct, final String tran_type2, final String tran_amt2) {
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
        this.tran_type2 = tran_type2;
        this.tran_amt2 = tran_amt2;
    }
    
    public EXPT_record() {
    }
    
    @Override
    public String toString() {
        return "EXPT_record [" + ((this.id != null) ? ("id=" + this.id + ",/n ") : "") + ((this.empc_file_id != null) ? ("empc_file_id=" + this.empc_file_id + ",/n ") : "") + ((this.state_id != null) ? ("state_id=" + this.state_id + ",/n ") : "") + ((this.record_type != null) ? ("record_type=" + this.record_type + ",/n ") : "") + ((this.line_number != null) ? ("line_number=" + this.line_number + ",/n ") : "") + ((this.client != null) ? ("client=" + this.client + ",/n ") : "") + ((this.card_acct != null) ? ("card_acct=" + this.card_acct + ",/n ") : "") + ((this.accnt_ccy != null) ? ("accnt_ccy=" + this.accnt_ccy + ",/n ") : "") + ((this.card != null) ? ("card=" + this.card + ",/n ") : "") + ((this.slip_nr != null) ? ("slip_nr=" + this.slip_nr + ",/n ") : "") + ((this.ref_number != null) ? ("ref_number=" + this.ref_number + ",/n ") : "") + ((this.tran_date_time != null) ? ("tran_date_time=" + this.tran_date_time + ",/n ") : "") + ((this.rec_date != null) ? ("rec_date=" + this.rec_date + ",/n ") : "") + ((this.post_date != null) ? ("post_date=" + this.post_date + ",/n ") : "") + ((this.deal_desc != null) ? ("deal_desc=" + this.deal_desc + ",/n ") : "") + ((this.tran_type != null) ? ("tran_type=" + this.tran_type + ",/n ") : "") + ((this.deb_cred != null) ? ("deb_cred=" + this.deb_cred + ",/n ") : "") + ((this.tran_ccy != null) ? ("tran_ccy=" + this.tran_ccy + ",/n ") : "") + ((this.tran_amt != null) ? ("tran_amt=" + this.tran_amt + ",/n ") : "") + ((this.accnt_amt != null) ? ("accnt_amt=" + this.accnt_amt + ",/n ") : "") + ((this.terminal != null) ? ("terminal=" + this.terminal + ",/n ") : "") + ((this.mcc_code != null) ? ("mcc_code=" + this.mcc_code + ",/n ") : "") + ((this.merchant != null) ? ("merchant=" + this.merchant + ",/n ") : "") + ((this.abvr_name != null) ? ("abvr_name=" + this.abvr_name + ",/n ") : "") + ((this.country != null) ? ("country=" + this.country + ",/n ") : "") + ((this.city != null) ? ("city=" + this.city + ",/n ") : "") + ((this.proc_id != null) ? ("proc_id=" + this.proc_id + ",/n ") : "") + ((this.internal_no != null) ? ("internal_no=" + this.internal_no + ",/n ") : "") + ((this.product != null) ? ("product=" + this.product + ",/n ") : "") + ((this.iss_mfo != null) ? ("iss_mfo=" + this.iss_mfo + ",/n ") : "") + ((this.term_id != null) ? ("term_id=" + this.term_id + ",/n ") : "") + ((this.tranz_acct != null) ? ("tranz_acct=" + this.tranz_acct) : "") + "]";
    }
}
