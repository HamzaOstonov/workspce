// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.fileProcessing;

import java.io.Serializable;

public class EmpcFilesBTransactions implements Serializable
{
    static final long serialVersionUID = 103844514947365244L;
    private Long id;
    private String rec_num;
    private String empc_file_id;
    private String mtid;
    private String rec_centr;
    private String send_centr;
    private String iss_cmi;
    private String send_cmi;
    private String settl_cmi;
    private String acq_bank;
    private String acq_branch;
    private String member;
    private String clearing_group;
    private String merchant_accept;
    private String batch_nr;
    private String slip_nr;
    private String card;
    private String exp_date;
    private String e_date;
    private String e_time;
    private String tran_type;
    private String appr_code;
    private String appr_src;
    private String stan;
    private String ref_number;
    private String amount;
    private String cash_back;
    private String fee;
    private String currency;
    private String ccy_exp;
    private String sb_amount;
    private String sb_cshback;
    private String sb_fee;
    private String sbnk_ccy;
    private String sb_ccyexp;
    private String sb_cnvrate;
    private String sb_cnvdate;
    private String i_amount;
    private String i_cshback;
    private String i_fee;
    private String ibnk_ccy;
    private String i_ccyexp;
    private String i_cnvrate;
    private String i_cnvdate;
    private String abvr_name;
    private String city;
    private String country;
    private String point_code;
    private String mcc_code;
    private String terminal;
    private String batch_id;
    private String settl_nr;
    private String settl_date;
    private String acqref_nr;
    private String file_id;
    private String ms_number;
    private String file_date;
    private String source_algorithm;
    private String term_nr;
    private String ecmc_fee;
    private String tran_info;
    private String pr_amount;
    private String pr_cshback;
    private String pr_fee;
    private String prnk_ccy;
    private String pr_ccyexp;
    private String pr_cnvrate;
    private String pr_cnvdate;
    private String e_region;
    private String card_type;
    private String proc_class;
    private String card_seq_nr;
    private String msg_type;
    private String org_msg_type;
    private String proc_code;
    private String msg_category;
    private String merchant;
    private String moto_ind;
    private String susp_status;
    private String transact_row;
    private String authoriz_row;
    private String fld_043;
    private String fld_098;
    private String fld_102;
    private String fld_103;
    private String fld_104;
    private String fld_039;
    private String fld_sh6;
    private String batch_date;
    private String tr_fee;
    private String fld_040;
    private String fld_123_1;
    private String epi_42_48;
    private String fld_003;
    private String msc;
    private String account_nr;
    private String epi_42_48_full;
    private String other_code;
    private String fld_015;
    private String fld_095;
    private String audit_date;
    private String other_fee1;
    private String other_fee2;
    private String other_fee3;
    private String other_fee4;
    private String other_fee5;
    private String state_id;
    
    public EmpcFilesBTransactions() {
    }
    
    public EmpcFilesBTransactions(final Long id, final String rec_num, final String empc_file_id, final String mtid, final String rec_centr, final String send_centr, final String iss_cmi, final String send_cmi, final String settl_cmi, final String acq_bank, final String acq_branch, final String member, final String clearing_group, final String merchant_accept, final String batch_nr, final String slip_nr, final String card, final String exp_date, final String e_date, final String e_time, final String tran_type, final String appr_code, final String appr_src, final String stan, final String ref_number, final String amount, final String cash_back, final String fee, final String currency, final String ccy_exp, final String sb_amount, final String sb_cshback, final String sb_fee, final String sbnk_ccy, final String sb_ccyexp, final String sb_cnvrate, final String sb_cnvdate, final String i_amount, final String i_cshback, final String i_fee, final String ibnk_ccy, final String i_ccyexp, final String i_cnvrate, final String i_cnvdate, final String abvr_name, final String city, final String country, final String point_code, final String mcc_code, final String terminal, final String batch_id, final String settl_nr, final String settl_date, final String acqref_nr, final String file_id, final String ms_number, final String file_date, final String source_algorithm, final String term_nr, final String ecmc_fee, final String tran_info, final String pr_amount, final String pr_cshback, final String pr_fee, final String prnk_ccy, final String pr_ccyexp, final String pr_cnvrate, final String pr_cnvdate, final String e_region, final String card_type, final String proc_class, final String card_seq_nr, final String msg_type, final String org_msg_type, final String proc_code, final String msg_category, final String merchant, final String moto_ind, final String susp_status, final String transact_row, final String authoriz_row, final String fld_043, final String fld_098, final String fld_102, final String fld_103, final String fld_104, final String fld_039, final String fld_sh6, final String batch_date, final String tr_fee, final String fld_040, final String fld_123_1, final String epi_42_48, final String fld_003, final String msc, final String account_nr, final String epi_42_48_full, final String other_code, final String fld_015, final String fld_095, final String audit_date, final String other_fee1, final String other_fee2, final String other_fee3, final String other_fee4, final String other_fee5, final String state_id) {
        this.id = id;
        this.rec_num = rec_num;
        this.empc_file_id = empc_file_id;
        this.mtid = mtid;
        this.rec_centr = rec_centr;
        this.send_centr = send_centr;
        this.iss_cmi = iss_cmi;
        this.send_cmi = send_cmi;
        this.settl_cmi = settl_cmi;
        this.acq_bank = acq_bank;
        this.acq_branch = acq_branch;
        this.member = member;
        this.clearing_group = clearing_group;
        this.merchant_accept = merchant_accept;
        this.batch_nr = batch_nr;
        this.slip_nr = slip_nr;
        this.card = card;
        this.exp_date = exp_date;
        this.e_date = e_date;
        this.e_time = e_time;
        this.tran_type = tran_type;
        this.appr_code = appr_code;
        this.appr_src = appr_src;
        this.stan = stan;
        this.ref_number = ref_number;
        this.amount = amount;
        this.cash_back = cash_back;
        this.fee = fee;
        this.currency = currency;
        this.ccy_exp = ccy_exp;
        this.sb_amount = sb_amount;
        this.sb_cshback = sb_cshback;
        this.sb_fee = sb_fee;
        this.sbnk_ccy = sbnk_ccy;
        this.sb_ccyexp = sb_ccyexp;
        this.sb_cnvrate = sb_cnvrate;
        this.sb_cnvdate = sb_cnvdate;
        this.i_amount = i_amount;
        this.i_cshback = i_cshback;
        this.i_fee = i_fee;
        this.ibnk_ccy = ibnk_ccy;
        this.i_ccyexp = i_ccyexp;
        this.i_cnvrate = i_cnvrate;
        this.i_cnvdate = i_cnvdate;
        this.abvr_name = abvr_name;
        this.city = city;
        this.country = country;
        this.point_code = point_code;
        this.mcc_code = mcc_code;
        this.terminal = terminal;
        this.batch_id = batch_id;
        this.settl_nr = settl_nr;
        this.settl_date = settl_date;
        this.acqref_nr = acqref_nr;
        this.file_id = file_id;
        this.ms_number = ms_number;
        this.file_date = file_date;
        this.source_algorithm = source_algorithm;
        this.term_nr = term_nr;
        this.ecmc_fee = ecmc_fee;
        this.tran_info = tran_info;
        this.pr_amount = pr_amount;
        this.pr_cshback = pr_cshback;
        this.pr_fee = pr_fee;
        this.prnk_ccy = prnk_ccy;
        this.pr_ccyexp = pr_ccyexp;
        this.pr_cnvrate = pr_cnvrate;
        this.pr_cnvdate = pr_cnvdate;
        this.e_region = e_region;
        this.card_type = card_type;
        this.proc_class = proc_class;
        this.card_seq_nr = card_seq_nr;
        this.msg_type = msg_type;
        this.org_msg_type = org_msg_type;
        this.proc_code = proc_code;
        this.msg_category = msg_category;
        this.merchant = merchant;
        this.moto_ind = moto_ind;
        this.susp_status = susp_status;
        this.transact_row = transact_row;
        this.authoriz_row = authoriz_row;
        this.fld_043 = fld_043;
        this.fld_098 = fld_098;
        this.fld_102 = fld_102;
        this.fld_103 = fld_103;
        this.fld_104 = fld_104;
        this.fld_039 = fld_039;
        this.fld_sh6 = fld_sh6;
        this.batch_date = batch_date;
        this.tr_fee = tr_fee;
        this.fld_040 = fld_040;
        this.fld_123_1 = fld_123_1;
        this.epi_42_48 = epi_42_48;
        this.fld_003 = fld_003;
        this.msc = msc;
        this.account_nr = account_nr;
        this.epi_42_48_full = epi_42_48_full;
        this.other_code = other_code;
        this.fld_015 = fld_015;
        this.fld_095 = fld_095;
        this.audit_date = audit_date;
        this.other_fee1 = other_fee1;
        this.other_fee2 = other_fee2;
        this.other_fee3 = other_fee3;
        this.other_fee4 = other_fee4;
        this.other_fee5 = other_fee5;
        this.state_id = state_id;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public String getRec_num() {
        return this.rec_num;
    }
    
    public void setRec_num(final String rec_num) {
        this.rec_num = rec_num;
    }
    
    public String getEmpc_file_id() {
        return this.empc_file_id;
    }
    
    public void setEmpc_file_id(final String empc_file_id) {
        this.empc_file_id = empc_file_id;
    }
    
    public String getMtid() {
        return this.mtid;
    }
    
    public void setMtid(final String mtid) {
        this.mtid = mtid;
    }
    
    public String getRec_centr() {
        return this.rec_centr;
    }
    
    public void setRec_centr(final String rec_centr) {
        this.rec_centr = rec_centr;
    }
    
    public String getSend_centr() {
        return this.send_centr;
    }
    
    public void setSend_centr(final String send_centr) {
        this.send_centr = send_centr;
    }
    
    public String getIss_cmi() {
        return this.iss_cmi;
    }
    
    public void setIss_cmi(final String iss_cmi) {
        this.iss_cmi = iss_cmi;
    }
    
    public String getSend_cmi() {
        return this.send_cmi;
    }
    
    public void setSend_cmi(final String send_cmi) {
        this.send_cmi = send_cmi;
    }
    
    public String getSettl_cmi() {
        return this.settl_cmi;
    }
    
    public void setSettl_cmi(final String settl_cmi) {
        this.settl_cmi = settl_cmi;
    }
    
    public String getAcq_bank() {
        return this.acq_bank;
    }
    
    public void setAcq_bank(final String acq_bank) {
        this.acq_bank = acq_bank;
    }
    
    public String getAcq_branch() {
        return this.acq_branch;
    }
    
    public void setAcq_branch(final String acq_branch) {
        this.acq_branch = acq_branch;
    }
    
    public String getMember() {
        return this.member;
    }
    
    public void setMember(final String member) {
        this.member = member;
    }
    
    public String getClearing_group() {
        return this.clearing_group;
    }
    
    public void setClearing_group(final String clearing_group) {
        this.clearing_group = clearing_group;
    }
    
    public String getMerchant_accept() {
        return this.merchant_accept;
    }
    
    public void setMerchant_accept(final String merchant_accept) {
        this.merchant_accept = merchant_accept;
    }
    
    public String getBatch_nr() {
        return this.batch_nr;
    }
    
    public void setBatch_nr(final String batch_nr) {
        this.batch_nr = batch_nr;
    }
    
    public String getSlip_nr() {
        return this.slip_nr;
    }
    
    public void setSlip_nr(final String slip_nr) {
        this.slip_nr = slip_nr;
    }
    
    public String getCard() {
        return this.card;
    }
    
    public void setCard(final String card) {
        this.card = card;
    }
    
    public String getExp_date() {
        return this.exp_date;
    }
    
    public void setExp_date(final String exp_date) {
        this.exp_date = exp_date;
    }
    
    public String getE_date() {
        return this.e_date;
    }
    
    public void setE_date(final String e_date) {
        this.e_date = e_date;
    }
    
    public String getE_time() {
        return this.e_time;
    }
    
    public void setE_time(final String e_time) {
        this.e_time = e_time;
    }
    
    public String getTran_type() {
        return this.tran_type;
    }
    
    public void setTran_type(final String tran_type) {
        this.tran_type = tran_type;
    }
    
    public String getAppr_code() {
        return this.appr_code;
    }
    
    public void setAppr_code(final String appr_code) {
        this.appr_code = appr_code;
    }
    
    public String getAppr_src() {
        return this.appr_src;
    }
    
    public void setAppr_src(final String appr_src) {
        this.appr_src = appr_src;
    }
    
    public String getStan() {
        return this.stan;
    }
    
    public void setStan(final String stan) {
        this.stan = stan;
    }
    
    public String getRef_number() {
        return this.ref_number;
    }
    
    public void setRef_number(final String ref_number) {
        this.ref_number = ref_number;
    }
    
    public String getAmount() {
        return this.amount;
    }
    
    public void setAmount(final String amount) {
        this.amount = amount;
    }
    
    public String getCash_back() {
        return this.cash_back;
    }
    
    public void setCash_back(final String cash_back) {
        this.cash_back = cash_back;
    }
    
    public String getFee() {
        return this.fee;
    }
    
    public void setFee(final String fee) {
        this.fee = fee;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(final String currency) {
        this.currency = currency;
    }
    
    public String getCcy_exp() {
        return this.ccy_exp;
    }
    
    public void setCcy_exp(final String ccy_exp) {
        this.ccy_exp = ccy_exp;
    }
    
    public String getSb_amount() {
        return this.sb_amount;
    }
    
    public void setSb_amount(final String sb_amount) {
        this.sb_amount = sb_amount;
    }
    
    public String getSb_cshback() {
        return this.sb_cshback;
    }
    
    public void setSb_cshback(final String sb_cshback) {
        this.sb_cshback = sb_cshback;
    }
    
    public String getSb_fee() {
        return this.sb_fee;
    }
    
    public void setSb_fee(final String sb_fee) {
        this.sb_fee = sb_fee;
    }
    
    public String getSbnk_ccy() {
        return this.sbnk_ccy;
    }
    
    public void setSbnk_ccy(final String sbnk_ccy) {
        this.sbnk_ccy = sbnk_ccy;
    }
    
    public String getSb_ccyexp() {
        return this.sb_ccyexp;
    }
    
    public void setSb_ccyexp(final String sb_ccyexp) {
        this.sb_ccyexp = sb_ccyexp;
    }
    
    public String getSb_cnvrate() {
        return this.sb_cnvrate;
    }
    
    public void setSb_cnvrate(final String sb_cnvrate) {
        this.sb_cnvrate = sb_cnvrate;
    }
    
    public String getSb_cnvdate() {
        return this.sb_cnvdate;
    }
    
    public void setSb_cnvdate(final String sb_cnvdate) {
        this.sb_cnvdate = sb_cnvdate;
    }
    
    public String getI_amount() {
        return this.i_amount;
    }
    
    public void setI_amount(final String i_amount) {
        this.i_amount = i_amount;
    }
    
    public String getI_cshback() {
        return this.i_cshback;
    }
    
    public void setI_cshback(final String i_cshback) {
        this.i_cshback = i_cshback;
    }
    
    public String getI_fee() {
        return this.i_fee;
    }
    
    public void setI_fee(final String i_fee) {
        this.i_fee = i_fee;
    }
    
    public String getIbnk_ccy() {
        return this.ibnk_ccy;
    }
    
    public void setIbnk_ccy(final String ibnk_ccy) {
        this.ibnk_ccy = ibnk_ccy;
    }
    
    public String getI_ccyexp() {
        return this.i_ccyexp;
    }
    
    public void setI_ccyexp(final String i_ccyexp) {
        this.i_ccyexp = i_ccyexp;
    }
    
    public String getI_cnvrate() {
        return this.i_cnvrate;
    }
    
    public void setI_cnvrate(final String i_cnvrate) {
        this.i_cnvrate = i_cnvrate;
    }
    
    public String getI_cnvdate() {
        return this.i_cnvdate;
    }
    
    public void setI_cnvdate(final String i_cnvdate) {
        this.i_cnvdate = i_cnvdate;
    }
    
    public String getAbvr_name() {
        return this.abvr_name;
    }
    
    public void setAbvr_name(final String abvr_name) {
        this.abvr_name = abvr_name;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(final String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(final String country) {
        this.country = country;
    }
    
    public String getPoint_code() {
        return this.point_code;
    }
    
    public void setPoint_code(final String point_code) {
        this.point_code = point_code;
    }
    
    public String getMcc_code() {
        return this.mcc_code;
    }
    
    public void setMcc_code(final String mcc_code) {
        this.mcc_code = mcc_code;
    }
    
    public String getTerminal() {
        return this.terminal;
    }
    
    public void setTerminal(final String terminal) {
        this.terminal = terminal;
    }
    
    public String getBatch_id() {
        return this.batch_id;
    }
    
    public void setBatch_id(final String batch_id) {
        this.batch_id = batch_id;
    }
    
    public String getSettl_nr() {
        return this.settl_nr;
    }
    
    public void setSettl_nr(final String settl_nr) {
        this.settl_nr = settl_nr;
    }
    
    public String getSettl_date() {
        return this.settl_date;
    }
    
    public void setSettl_date(final String settl_date) {
        this.settl_date = settl_date;
    }
    
    public String getAcqref_nr() {
        return this.acqref_nr;
    }
    
    public void setAcqref_nr(final String acqref_nr) {
        this.acqref_nr = acqref_nr;
    }
    
    public String getFile_id() {
        return this.file_id;
    }
    
    public void setFile_id(final String file_id) {
        this.file_id = file_id;
    }
    
    public String getMs_number() {
        return this.ms_number;
    }
    
    public void setMs_number(final String ms_number) {
        this.ms_number = ms_number;
    }
    
    public String getFile_date() {
        return this.file_date;
    }
    
    public void setFile_date(final String file_date) {
        this.file_date = file_date;
    }
    
    public String getSource_algorithm() {
        return this.source_algorithm;
    }
    
    public void setSource_algorithm(final String source_algorithm) {
        this.source_algorithm = source_algorithm;
    }
    
    public String getTerm_nr() {
        return this.term_nr;
    }
    
    public void setTerm_nr(final String term_nr) {
        this.term_nr = term_nr;
    }
    
    public String getEcmc_fee() {
        return this.ecmc_fee;
    }
    
    public void setEcmc_fee(final String ecmc_fee) {
        this.ecmc_fee = ecmc_fee;
    }
    
    public String getTran_info() {
        return this.tran_info;
    }
    
    public void setTran_info(final String tran_info) {
        this.tran_info = tran_info;
    }
    
    public String getPr_amount() {
        return this.pr_amount;
    }
    
    public void setPr_amount(final String pr_amount) {
        this.pr_amount = pr_amount;
    }
    
    public String getPr_cshback() {
        return this.pr_cshback;
    }
    
    public void setPr_cshback(final String pr_cshback) {
        this.pr_cshback = pr_cshback;
    }
    
    public String getPr_fee() {
        return this.pr_fee;
    }
    
    public void setPr_fee(final String pr_fee) {
        this.pr_fee = pr_fee;
    }
    
    public String getPrnk_ccy() {
        return this.prnk_ccy;
    }
    
    public void setPrnk_ccy(final String prnk_ccy) {
        this.prnk_ccy = prnk_ccy;
    }
    
    public String getPr_ccyexp() {
        return this.pr_ccyexp;
    }
    
    public void setPr_ccyexp(final String pr_ccyexp) {
        this.pr_ccyexp = pr_ccyexp;
    }
    
    public String getPr_cnvrate() {
        return this.pr_cnvrate;
    }
    
    public void setPr_cnvrate(final String pr_cnvrate) {
        this.pr_cnvrate = pr_cnvrate;
    }
    
    public String getPr_cnvdate() {
        return this.pr_cnvdate;
    }
    
    public void setPr_cnvdate(final String pr_cnvdate) {
        this.pr_cnvdate = pr_cnvdate;
    }
    
    public String getE_region() {
        return this.e_region;
    }
    
    public void setE_region(final String e_region) {
        this.e_region = e_region;
    }
    
    public String getCard_type() {
        return this.card_type;
    }
    
    public void setCard_type(final String card_type) {
        this.card_type = card_type;
    }
    
    public String getProc_class() {
        return this.proc_class;
    }
    
    public void setProc_class(final String proc_class) {
        this.proc_class = proc_class;
    }
    
    public String getCard_seq_nr() {
        return this.card_seq_nr;
    }
    
    public void setCard_seq_nr(final String card_seq_nr) {
        this.card_seq_nr = card_seq_nr;
    }
    
    public String getMsg_type() {
        return this.msg_type;
    }
    
    public void setMsg_type(final String msg_type) {
        this.msg_type = msg_type;
    }
    
    public String getOrg_msg_type() {
        return this.org_msg_type;
    }
    
    public void setOrg_msg_type(final String org_msg_type) {
        this.org_msg_type = org_msg_type;
    }
    
    public String getProc_code() {
        return this.proc_code;
    }
    
    public void setProc_code(final String proc_code) {
        this.proc_code = proc_code;
    }
    
    public String getMsg_category() {
        return this.msg_category;
    }
    
    public void setMsg_category(final String msg_category) {
        this.msg_category = msg_category;
    }
    
    public String getMerchant() {
        return this.merchant;
    }
    
    public void setMerchant(final String merchant) {
        this.merchant = merchant;
    }
    
    public String getMoto_ind() {
        return this.moto_ind;
    }
    
    public void setMoto_ind(final String moto_ind) {
        this.moto_ind = moto_ind;
    }
    
    public String getSusp_status() {
        return this.susp_status;
    }
    
    public void setSusp_status(final String susp_status) {
        this.susp_status = susp_status;
    }
    
    public String getTransact_row() {
        return this.transact_row;
    }
    
    public void setTransact_row(final String transact_row) {
        this.transact_row = transact_row;
    }
    
    public String getAuthoriz_row() {
        return this.authoriz_row;
    }
    
    public void setAuthoriz_row(final String authoriz_row) {
        this.authoriz_row = authoriz_row;
    }
    
    public String getFld_043() {
        return this.fld_043;
    }
    
    public void setFld_043(final String fld_043) {
        this.fld_043 = fld_043;
    }
    
    public String getFld_098() {
        return this.fld_098;
    }
    
    public void setFld_098(final String fld_098) {
        this.fld_098 = fld_098;
    }
    
    public String getFld_102() {
        return this.fld_102;
    }
    
    public void setFld_102(final String fld_102) {
        this.fld_102 = fld_102;
    }
    
    public String getFld_103() {
        return this.fld_103;
    }
    
    public void setFld_103(final String fld_103) {
        this.fld_103 = fld_103;
    }
    
    public String getFld_104() {
        return this.fld_104;
    }
    
    public void setFld_104(final String fld_104) {
        this.fld_104 = fld_104;
    }
    
    public String getFld_039() {
        return this.fld_039;
    }
    
    public void setFld_039(final String fld_039) {
        this.fld_039 = fld_039;
    }
    
    public String getFld_sh6() {
        return this.fld_sh6;
    }
    
    public void setFld_sh6(final String fld_sh6) {
        this.fld_sh6 = fld_sh6;
    }
    
    public String getBatch_date() {
        return this.batch_date;
    }
    
    public void setBatch_date(final String batch_date) {
        this.batch_date = batch_date;
    }
    
    public String getTr_fee() {
        return this.tr_fee;
    }
    
    public void setTr_fee(final String tr_fee) {
        this.tr_fee = tr_fee;
    }
    
    public String getFld_040() {
        return this.fld_040;
    }
    
    public void setFld_040(final String fld_040) {
        this.fld_040 = fld_040;
    }
    
    public String getFld_123_1() {
        return this.fld_123_1;
    }
    
    public void setFld_123_1(final String fld_123_1) {
        this.fld_123_1 = fld_123_1;
    }
    
    public String getEpi_42_48() {
        return this.epi_42_48;
    }
    
    public void setEpi_42_48(final String epi_42_48) {
        this.epi_42_48 = epi_42_48;
    }
    
    public String getFld_003() {
        return this.fld_003;
    }
    
    public void setFld_003(final String fld_003) {
        this.fld_003 = fld_003;
    }
    
    public String getMsc() {
        return this.msc;
    }
    
    public void setMsc(final String msc) {
        this.msc = msc;
    }
    
    public String getAccount_nr() {
        return this.account_nr;
    }
    
    public void setAccount_nr(final String account_nr) {
        this.account_nr = account_nr;
    }
    
    public String getEpi_42_48_full() {
        return this.epi_42_48_full;
    }
    
    public void setEpi_42_48_full(final String epi_42_48_full) {
        this.epi_42_48_full = epi_42_48_full;
    }
    
    public String getOther_code() {
        return this.other_code;
    }
    
    public void setOther_code(final String other_code) {
        this.other_code = other_code;
    }
    
    public String getFld_015() {
        return this.fld_015;
    }
    
    public void setFld_015(final String fld_015) {
        this.fld_015 = fld_015;
    }
    
    public String getFld_095() {
        return this.fld_095;
    }
    
    public void setFld_095(final String fld_095) {
        this.fld_095 = fld_095;
    }
    
    public String getAudit_date() {
        return this.audit_date;
    }
    
    public void setAudit_date(final String audit_date) {
        this.audit_date = audit_date;
    }
    
    public String getOther_fee1() {
        return this.other_fee1;
    }
    
    public void setOther_fee1(final String other_fee1) {
        this.other_fee1 = other_fee1;
    }
    
    public String getOther_fee2() {
        return this.other_fee2;
    }
    
    public void setOther_fee2(final String other_fee2) {
        this.other_fee2 = other_fee2;
    }
    
    public String getOther_fee3() {
        return this.other_fee3;
    }
    
    public void setOther_fee3(final String other_fee3) {
        this.other_fee3 = other_fee3;
    }
    
    public String getOther_fee4() {
        return this.other_fee4;
    }
    
    public void setOther_fee4(final String other_fee4) {
        this.other_fee4 = other_fee4;
    }
    
    public String getOther_fee5() {
        return this.other_fee5;
    }
    
    public void setOther_fee5(final String other_fee5) {
        this.other_fee5 = other_fee5;
    }
    
    public String getState_id() {
        return this.state_id;
    }
    
    public void setState_id(final String state_id) {
        this.state_id = state_id;
    }
    
    public static long getSerialversionuid() {
        return 103844514947365244L;
    }
}
