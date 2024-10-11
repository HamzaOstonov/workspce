// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.b_file.entity;

public class B_file_transaction_record
{
    private Long id;
    private Long rec_num;
    private Long EMPC_file_id;
    private String Mtid;
    private String Rec_centr;
    private String Send_centr;
    private String ISS_CMI;
    private String Send_CMI;
    private String Settl_CMI;
    private String Acq_bank;
    private String Acq_branch;
    private String Member;
    private String Clearing_group;
    private String Merchant_accept;
    private String Batch_nr;
    private String Slip_nr;
    private String Card;
    private String Exp_date;
    private String Date;
    private String Time;
    private String Tran_type;
    private String Appr_code;
    private String Appr_src;
    private String Stan;
    private String Ref_number;
    private String Amount;
    private String Cash_back;
    private String Fee;
    private String Currency;
    private String Ccy_exp;
    private String Sb_amount;
    private String Sb_cshback;
    private String Sb_fee;
    private String Sbnk_ccy;
    private String Sb_ccyexp;
    private String Sb_cnvrate;
    private String Sb_cnvdate;
    private String I_amount;
    private String I_cshback;
    private String I_fee;
    private String Ibnk_ccy;
    private String I_ccyexp;
    private String I_cnvrate;
    private String I_cnvdate;
    private String Abvr_name;
    private String City;
    private String Country;
    private String Point_code;
    private String MCC_code;
    private String Terminal;
    private String Batch_id;
    private String Settl_nr;
    private String Settl_date;
    private String Acqref_nr;
    private String File_id;
    private String Ms_number;
    private String File_date;
    private String Source_algorithm;
    private String Term_nr;
    private String ECMC_Fee;
    private String Tran_info;
    private String Pr_amount;
    private String Pr_cshback;
    private String Pr_fee;
    private String Prnk_ccy;
    private String Pr_ccyexp;
    private String Pr_cnvrate;
    private String Pr_cnvdate;
    private String Region;
    private String Card_Type;
    private String Proc_Class;
    private String CARD_SEQ_NR;
    private String Msg_type;
    private String Org_msg_type;
    private String Proc_code;
    private String Msg_category;
    private String Merchant;
    private String MOTO_IND;
    private String Susp_status;
    private String Transact_row;
    private String Authoriz_row;
    private String FLD_043;
    private String FLD_098;
    private String FLD_102;
    private String FLD_103;
    private String FLD_104;
    private String FLD_039;
    private String FLD_SH6;
    private String Batch_date;
    private String Tr_fee;
    private String FLD_040;
    private String FLD_123_1;
    private String EPI_42_48;
    private String FLD_003;
    private String MSC;
    private String Account_nr;
    private String EPI_42_48_FULL;
    private String Other_Code;
    private String FLD_015;
    private String FLD_095;
    private String Audit_date;
    private String Other_fee1;
    private String Other_fee2;
    private String Other_fee3;
    private String Other_fee4;
    private String Other_fee5;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Long getEMPC_file_id() {
        return this.EMPC_file_id;
    }
    
    public void setEMPC_file_id(final Long eMPC_file_id) {
        this.EMPC_file_id = eMPC_file_id;
    }
    
    public void setMs_number(final String ms_number) {
        this.Ms_number = ms_number;
    }
    
    public String getMs_number() {
        return this.Ms_number;
    }
    
    public String getMtid() {
        return this.Mtid;
    }
    
    public void setMtid(final String mtid) {
        this.Mtid = mtid;
    }
    
    public String getRec_centr() {
        return this.Rec_centr;
    }
    
    public void setRec_centr(final String rec_centr) {
        this.Rec_centr = rec_centr;
    }
    
    public String getSend_centr() {
        return this.Send_centr;
    }
    
    public void setSend_centr(final String send_centr) {
        this.Send_centr = send_centr;
    }
    
    public String getISS_CMI() {
        return this.ISS_CMI;
    }
    
    public void setISS_CMI(final String iSS_CMI) {
        this.ISS_CMI = iSS_CMI;
    }
    
    public String getSend_CMI() {
        return this.Send_CMI;
    }
    
    public void setSend_CMI(final String send_CMI) {
        this.Send_CMI = send_CMI;
    }
    
    public Long getRec_num() {
        return this.rec_num;
    }
    
    public void setRec_num(final Long rec_num) {
        this.rec_num = rec_num;
    }
    
    public String getSettl_CMI() {
        return this.Settl_CMI;
    }
    
    public void setSettl_CMI(final String settl_CMI) {
        this.Settl_CMI = settl_CMI;
    }
    
    public String getAcq_bank() {
        return this.Acq_bank;
    }
    
    public void setAcq_bank(final String acq_bank) {
        this.Acq_bank = acq_bank;
    }
    
    public String getAcq_branch() {
        return this.Acq_branch;
    }
    
    public void setAcq_branch(final String acq_branch) {
        this.Acq_branch = acq_branch;
    }
    
    public String getMember() {
        return this.Member;
    }
    
    public void setMember(final String member) {
        this.Member = member;
    }
    
    public String getClearing_group() {
        return this.Clearing_group;
    }
    
    public void setClearing_group(final String clearing_group) {
        this.Clearing_group = clearing_group;
    }
    
    public String getMerchant_accept() {
        return this.Merchant_accept;
    }
    
    public void setMerchant_accept(final String merchant_accept) {
        this.Merchant_accept = merchant_accept;
    }
    
    public String getBatch_nr() {
        return this.Batch_nr;
    }
    
    public void setBatch_nr(final String batch_nr) {
        this.Batch_nr = batch_nr;
    }
    
    public String getSlip_nr() {
        return this.Slip_nr;
    }
    
    public void setSlip_nr(final String slip_nr) {
        this.Slip_nr = slip_nr;
    }
    
    public String getCard() {
        return this.Card;
    }
    
    public void setCard(final String card) {
        this.Card = card;
    }
    
    public String getExp_date() {
        return this.Exp_date;
    }
    
    public void setExp_date(final String exp_date) {
        this.Exp_date = exp_date;
    }
    
    public String getDate() {
        return this.Date;
    }
    
    public void setDate(final String date) {
        this.Date = date;
    }
    
    public String getTime() {
        return this.Time;
    }
    
    public void setTime(final String time) {
        this.Time = time;
    }
    
    public String getTran_type() {
        return this.Tran_type;
    }
    
    public void setTran_type(final String tran_type) {
        this.Tran_type = tran_type;
    }
    
    public String getAppr_code() {
        return this.Appr_code;
    }
    
    public void setAppr_code(final String appr_code) {
        this.Appr_code = appr_code;
    }
    
    public String getAppr_src() {
        return this.Appr_src;
    }
    
    public void setAppr_src(final String appr_src) {
        this.Appr_src = appr_src;
    }
    
    public String getStan() {
        return this.Stan;
    }
    
    public void setStan(final String stan) {
        this.Stan = stan;
    }
    
    public String getRef_number() {
        return this.Ref_number;
    }
    
    public void setRef_number(final String ref_number) {
        this.Ref_number = ref_number;
    }
    
    public String getAmount() {
        return this.Amount;
    }
    
    public void setAmount(final String amount) {
        this.Amount = amount;
    }
    
    public String getCash_back() {
        return this.Cash_back;
    }
    
    public void setCash_back(final String cash_back) {
        this.Cash_back = cash_back;
    }
    
    public String getFee() {
        return this.Fee;
    }
    
    public void setFee(final String fee) {
        this.Fee = fee;
    }
    
    public String getCurrency() {
        return this.Currency;
    }
    
    public void setCurrency(final String currency) {
        this.Currency = currency;
    }
    
    public String getCcy_exp() {
        return this.Ccy_exp;
    }
    
    public void setCcy_exp(final String ccy_exp) {
        this.Ccy_exp = ccy_exp;
    }
    
    public String getSb_amount() {
        return this.Sb_amount;
    }
    
    public void setSb_amount(final String sb_amount) {
        this.Sb_amount = sb_amount;
    }
    
    public String getSb_cshback() {
        return this.Sb_cshback;
    }
    
    public void setSb_cshback(final String sb_cshback) {
        this.Sb_cshback = sb_cshback;
    }
    
    public String getSb_fee() {
        return this.Sb_fee;
    }
    
    public void setSb_fee(final String sb_fee) {
        this.Sb_fee = sb_fee;
    }
    
    public String getSbnk_ccy() {
        return this.Sbnk_ccy;
    }
    
    public void setSbnk_ccy(final String sbnk_ccy) {
        this.Sbnk_ccy = sbnk_ccy;
    }
    
    public String getSb_ccyexp() {
        return this.Sb_ccyexp;
    }
    
    public void setSb_ccyexp(final String sb_ccyexp) {
        this.Sb_ccyexp = sb_ccyexp;
    }
    
    public String getSb_cnvrate() {
        return this.Sb_cnvrate;
    }
    
    public void setSb_cnvrate(final String sb_cnvrate) {
        this.Sb_cnvrate = sb_cnvrate;
    }
    
    public String getSb_cnvdate() {
        return this.Sb_cnvdate;
    }
    
    public void setSb_cnvdate(final String sb_cnvdate) {
        this.Sb_cnvdate = sb_cnvdate;
    }
    
    public String getI_amount() {
        return this.I_amount;
    }
    
    public void setI_amount(final String i_amount) {
        this.I_amount = i_amount;
    }
    
    public String getI_cshback() {
        return this.I_cshback;
    }
    
    public void setI_cshback(final String i_cshback) {
        this.I_cshback = i_cshback;
    }
    
    public String getI_fee() {
        return this.I_fee;
    }
    
    public void setI_fee(final String i_fee) {
        this.I_fee = i_fee;
    }
    
    public String getIbnk_ccy() {
        return this.Ibnk_ccy;
    }
    
    public void setIbnk_ccy(final String ibnk_ccy) {
        this.Ibnk_ccy = ibnk_ccy;
    }
    
    public String getI_ccyexp() {
        return this.I_ccyexp;
    }
    
    public void setI_ccyexp(final String i_ccyexp) {
        this.I_ccyexp = i_ccyexp;
    }
    
    public String getI_cnvrate() {
        return this.I_cnvrate;
    }
    
    public void setI_cnvrate(final String i_cnvrate) {
        this.I_cnvrate = i_cnvrate;
    }
    
    public String getI_cnvdate() {
        return this.I_cnvdate;
    }
    
    public void setI_cnvdate(final String i_cnvdate) {
        this.I_cnvdate = i_cnvdate;
    }
    
    public String getAbvr_name() {
        return this.Abvr_name;
    }
    
    public void setAbvr_name(final String abvr_name) {
        this.Abvr_name = abvr_name;
    }
    
    public String getCity() {
        return this.City;
    }
    
    public void setCity(final String city) {
        this.City = city;
    }
    
    public String getCountry() {
        return this.Country;
    }
    
    public void setCountry(final String country) {
        this.Country = country;
    }
    
    public String getPoint_code() {
        return this.Point_code;
    }
    
    public void setPoint_code(final String point_code) {
        this.Point_code = point_code;
    }
    
    public String getMCC_code() {
        return this.MCC_code;
    }
    
    public void setMCC_code(final String mCC_code) {
        this.MCC_code = mCC_code;
    }
    
    public String getTerminal() {
        return this.Terminal;
    }
    
    public void setTerminal(final String terminal) {
        this.Terminal = terminal;
    }
    
    public String getBatch_id() {
        return this.Batch_id;
    }
    
    public void setBatch_id(final String batch_id) {
        this.Batch_id = batch_id;
    }
    
    public String getSettl_nr() {
        return this.Settl_nr;
    }
    
    public void setSettl_nr(final String settl_nr) {
        this.Settl_nr = settl_nr;
    }
    
    public String getSettl_date() {
        return this.Settl_date;
    }
    
    public void setSettl_date(final String settl_date) {
        this.Settl_date = settl_date;
    }
    
    public String getAcqref_nr() {
        return this.Acqref_nr;
    }
    
    public void setAcqref_nr(final String acqref_nr) {
        this.Acqref_nr = acqref_nr;
    }
    
    public String getFile_id() {
        return this.File_id;
    }
    
    public void setFile_id(final String file_id) {
        this.File_id = file_id;
    }
    
    public String getFile_date() {
        return this.File_date;
    }
    
    public void setFile_date(final String file_date) {
        this.File_date = file_date;
    }
    
    public String getSource_algorithm() {
        return this.Source_algorithm;
    }
    
    public void setSource_algorithm(final String source_algorithm) {
        this.Source_algorithm = source_algorithm;
    }
    
    public String getTerm_nr() {
        return this.Term_nr;
    }
    
    public void setTerm_nr(final String term_nr) {
        this.Term_nr = term_nr;
    }
    
    public String getECMC_Fee() {
        return this.ECMC_Fee;
    }
    
    public void setECMC_Fee(final String eCMC_Fee) {
        this.ECMC_Fee = eCMC_Fee;
    }
    
    public String getTran_info() {
        return this.Tran_info;
    }
    
    public void setTran_info(final String tran_info) {
        this.Tran_info = tran_info;
    }
    
    public String getPr_amount() {
        return this.Pr_amount;
    }
    
    public void setPr_amount(final String pr_amount) {
        this.Pr_amount = pr_amount;
    }
    
    public String getPr_cshback() {
        return this.Pr_cshback;
    }
    
    public void setPr_cshback(final String pr_cshback) {
        this.Pr_cshback = pr_cshback;
    }
    
    public String getPr_fee() {
        return this.Pr_fee;
    }
    
    public void setPr_fee(final String pr_fee) {
        this.Pr_fee = pr_fee;
    }
    
    public String getPrnk_ccy() {
        return this.Prnk_ccy;
    }
    
    public void setPrnk_ccy(final String prnk_ccy) {
        this.Prnk_ccy = prnk_ccy;
    }
    
    public String getPr_ccyexp() {
        return this.Pr_ccyexp;
    }
    
    public void setPr_ccyexp(final String pr_ccyexp) {
        this.Pr_ccyexp = pr_ccyexp;
    }
    
    public String getPr_cnvrate() {
        return this.Pr_cnvrate;
    }
    
    public void setPr_cnvrate(final String pr_cnvrate) {
        this.Pr_cnvrate = pr_cnvrate;
    }
    
    public String getPr_cnvdate() {
        return this.Pr_cnvdate;
    }
    
    public void setPr_cnvdate(final String pr_cnvdate) {
        this.Pr_cnvdate = pr_cnvdate;
    }
    
    public String getRegion() {
        return this.Region;
    }
    
    public void setRegion(final String region) {
        this.Region = region;
    }
    
    public String getCard_Type() {
        return this.Card_Type;
    }
    
    public void setCard_Type(final String card_Type) {
        this.Card_Type = card_Type;
    }
    
    public String getProc_Class() {
        return this.Proc_Class;
    }
    
    public void setProc_Class(final String proc_Class) {
        this.Proc_Class = proc_Class;
    }
    
    public String getCARD_SEQ_NR() {
        return this.CARD_SEQ_NR;
    }
    
    public void setCARD_SEQ_NR(final String cARD_SEQ_NR) {
        this.CARD_SEQ_NR = cARD_SEQ_NR;
    }
    
    public String getMsg_type() {
        return this.Msg_type;
    }
    
    public void setMsg_type(final String msg_type) {
        this.Msg_type = msg_type;
    }
    
    public String getOrg_msg_type() {
        return this.Org_msg_type;
    }
    
    public void setOrg_msg_type(final String org_msg_type) {
        this.Org_msg_type = org_msg_type;
    }
    
    public String getProc_code() {
        return this.Proc_code;
    }
    
    public void setProc_code(final String proc_code) {
        this.Proc_code = proc_code;
    }
    
    public String getMsg_category() {
        return this.Msg_category;
    }
    
    public void setMsg_category(final String msg_category) {
        this.Msg_category = msg_category;
    }
    
    public String getMerchant() {
        return this.Merchant;
    }
    
    public void setMerchant(final String merchant) {
        this.Merchant = merchant;
    }
    
    public String getMOTO_IND() {
        return this.MOTO_IND;
    }
    
    public void setMOTO_IND(final String mOTO_IND) {
        this.MOTO_IND = mOTO_IND;
    }
    
    public String getSusp_status() {
        return this.Susp_status;
    }
    
    public void setSusp_status(final String susp_status) {
        this.Susp_status = susp_status;
    }
    
    public String getTransact_row() {
        return this.Transact_row;
    }
    
    public void setTransact_row(final String transact_row) {
        this.Transact_row = transact_row;
    }
    
    public String getAuthoriz_row() {
        return this.Authoriz_row;
    }
    
    public void setAuthoriz_row(final String authoriz_row) {
        this.Authoriz_row = authoriz_row;
    }
    
    public String getFLD_043() {
        return this.FLD_043;
    }
    
    public void setFLD_043(final String fLD_043) {
        this.FLD_043 = fLD_043;
    }
    
    public String getFLD_098() {
        return this.FLD_098;
    }
    
    public void setFLD_098(final String fLD_098) {
        this.FLD_098 = fLD_098;
    }
    
    public String getFLD_102() {
        return this.FLD_102;
    }
    
    public void setFLD_102(final String fLD_102) {
        this.FLD_102 = fLD_102;
    }
    
    public String getFLD_103() {
        return this.FLD_103;
    }
    
    public void setFLD_103(final String fLD_103) {
        this.FLD_103 = fLD_103;
    }
    
    public String getFLD_104() {
        return this.FLD_104;
    }
    
    public void setFLD_104(final String fLD_104) {
        this.FLD_104 = fLD_104;
    }
    
    public String getFLD_039() {
        return this.FLD_039;
    }
    
    public void setFLD_039(final String fLD_039) {
        this.FLD_039 = fLD_039;
    }
    
    public String getFLD_SH6() {
        return this.FLD_SH6;
    }
    
    public void setFLD_SH6(final String fLD_SH6) {
        this.FLD_SH6 = fLD_SH6;
    }
    
    public String getBatch_date() {
        return this.Batch_date;
    }
    
    public void setBatch_date(final String batch_date) {
        this.Batch_date = batch_date;
    }
    
    public String getTr_fee() {
        return this.Tr_fee;
    }
    
    public void setTr_fee(final String tr_fee) {
        this.Tr_fee = tr_fee;
    }
    
    public String getFLD_040() {
        return this.FLD_040;
    }
    
    public void setFLD_040(final String fLD_040) {
        this.FLD_040 = fLD_040;
    }
    
    public String getFLD_123_1() {
        return this.FLD_123_1;
    }
    
    public void setFLD_123_1(final String fLD_123_1) {
        this.FLD_123_1 = fLD_123_1;
    }
    
    public String getEPI_42_48() {
        return this.EPI_42_48;
    }
    
    public void setEPI_42_48(final String ePI_42_48) {
        this.EPI_42_48 = ePI_42_48;
    }
    
    public String getFLD_003() {
        return this.FLD_003;
    }
    
    public void setFLD_003(final String fLD_003) {
        this.FLD_003 = fLD_003;
    }
    
    public String getMSC() {
        return this.MSC;
    }
    
    public void setMSC(final String mSC) {
        this.MSC = mSC;
    }
    
    public String getAccount_nr() {
        return this.Account_nr;
    }
    
    public void setAccount_nr(final String account_nr) {
        this.Account_nr = account_nr;
    }
    
    public String getEPI_42_48_FULL() {
        return this.EPI_42_48_FULL;
    }
    
    public void setEPI_42_48_FULL(final String ePI_42_48_FULL) {
        this.EPI_42_48_FULL = ePI_42_48_FULL;
    }
    
    public String getOther_Code() {
        return this.Other_Code;
    }
    
    public void setOther_Code(final String other_Code) {
        this.Other_Code = other_Code;
    }
    
    public String getFLD_015() {
        return this.FLD_015;
    }
    
    public void setFLD_015(final String fLD_015) {
        this.FLD_015 = fLD_015;
    }
    
    public String getFLD_095() {
        return this.FLD_095;
    }
    
    public void setFLD_095(final String fLD_095) {
        this.FLD_095 = fLD_095;
    }
    
    public String getAudit_date() {
        return this.Audit_date;
    }
    
    public void setAudit_date(final String audit_date) {
        this.Audit_date = audit_date;
    }
    
    public String getOther_fee1() {
        return this.Other_fee1;
    }
    
    public void setOther_fee1(final String other_fee1) {
        this.Other_fee1 = other_fee1;
    }
    
    public String getOther_fee2() {
        return this.Other_fee2;
    }
    
    public void setOther_fee2(final String other_fee2) {
        this.Other_fee2 = other_fee2;
    }
    
    public String getOther_fee3() {
        return this.Other_fee3;
    }
    
    public void setOther_fee3(final String other_fee3) {
        this.Other_fee3 = other_fee3;
    }
    
    public String getOther_fee4() {
        return this.Other_fee4;
    }
    
    public void setOther_fee4(final String other_fee4) {
        this.Other_fee4 = other_fee4;
    }
    
    public String getOther_fee5() {
        return this.Other_fee5;
    }
    
    public void setOther_fee5(final String other_fee5) {
        this.Other_fee5 = other_fee5;
    }
    
    public B_file_transaction_record(final Long id, final Long rec_num, final Long eMPC_file_id, final String mtid, final String rec_centr, final String send_centr, final String iSS_CMI, final String send_CMI, final String settl_CMI, final String acq_bank, final String acq_branch, final String member, final String clearing_group, final String merchant_accept, final String batch_nr, final String slip_nr, final String card, final String exp_date, final String date, final String time, final String tran_type, final String appr_code, final String appr_src, final String stan, final String ref_number, final String amount, final String cash_back, final String fee, final String currency, final String ccy_exp, final String sb_amount, final String sb_cshback, final String sb_fee, final String sbnk_ccy, final String sb_ccyexp, final String sb_cnvrate, final String sb_cnvdate, final String i_amount, final String i_cshback, final String i_fee, final String ibnk_ccy, final String i_ccyexp, final String i_cnvrate, final String i_cnvdate, final String abvr_name, final String city, final String country, final String point_code, final String mCC_code, final String terminal, final String batch_id, final String settl_nr, final String settl_date, final String acqref_nr, final String file_id, final String ms_number, final String file_date, final String source_algorithm, final String term_nr, final String eCMC_Fee, final String tran_info, final String pr_amount, final String pr_cshback, final String pr_fee, final String prnk_ccy, final String pr_ccyexp, final String pr_cnvrate, final String pr_cnvdate, final String region, final String card_Type, final String proc_Class, final String cARD_SEQ_NR, final String msg_type, final String org_msg_type, final String proc_code, final String msg_category, final String merchant, final String mOTO_IND, final String susp_status, final String transact_row, final String authoriz_row, final String fLD_043, final String fLD_098, final String fLD_102, final String fLD_103, final String fLD_104, final String fLD_039, final String fLD_SH6, final String batch_date, final String tr_fee, final String fLD_040, final String fLD_123_1, final String ePI_42_48, final String fLD_003, final String mSC, final String account_nr, final String ePI_42_48_FULL, final String other_Code, final String fLD_015, final String fLD_095, final String audit_date, final String other_fee1, final String other_fee2, final String other_fee3, final String other_fee4, final String other_fee5) {
        this.EMPC_file_id = eMPC_file_id;
        this.rec_num = rec_num;
        this.id = id;
        this.Mtid = mtid;
        this.Rec_centr = rec_centr;
        this.Send_centr = send_centr;
        this.ISS_CMI = iSS_CMI;
        this.Send_CMI = send_CMI;
        this.Settl_CMI = settl_CMI;
        this.Acq_bank = acq_bank;
        this.Acq_branch = acq_branch;
        this.Member = member;
        this.Clearing_group = clearing_group;
        this.Merchant_accept = merchant_accept;
        this.Batch_nr = batch_nr;
        this.Slip_nr = slip_nr;
        this.Card = card;
        this.Exp_date = exp_date;
        this.Date = date;
        this.Time = time;
        this.Tran_type = tran_type;
        this.Appr_code = appr_code;
        this.Appr_src = appr_src;
        this.Stan = stan;
        this.Ref_number = ref_number;
        this.Amount = amount;
        this.Cash_back = cash_back;
        this.Fee = fee;
        this.Currency = currency;
        this.Ccy_exp = ccy_exp;
        this.Sb_amount = sb_amount;
        this.Sb_cshback = sb_cshback;
        this.Sb_fee = sb_fee;
        this.Sbnk_ccy = sbnk_ccy;
        this.Sb_ccyexp = sb_ccyexp;
        this.Sb_cnvrate = sb_cnvrate;
        this.Sb_cnvdate = sb_cnvdate;
        this.I_amount = i_amount;
        this.I_cshback = i_cshback;
        this.I_fee = i_fee;
        this.Ibnk_ccy = ibnk_ccy;
        this.I_ccyexp = i_ccyexp;
        this.I_cnvrate = i_cnvrate;
        this.I_cnvdate = i_cnvdate;
        this.Abvr_name = abvr_name;
        this.City = city;
        this.Country = country;
        this.Point_code = point_code;
        this.MCC_code = mCC_code;
        this.Terminal = terminal;
        this.Batch_id = batch_id;
        this.Settl_nr = settl_nr;
        this.Settl_date = settl_date;
        this.Acqref_nr = acqref_nr;
        this.File_id = file_id;
        this.Ms_number = ms_number;
        this.File_date = file_date;
        this.Source_algorithm = source_algorithm;
        this.Term_nr = term_nr;
        this.ECMC_Fee = eCMC_Fee;
        this.Tran_info = tran_info;
        this.Pr_amount = pr_amount;
        this.Pr_cshback = pr_cshback;
        this.Pr_fee = pr_fee;
        this.Prnk_ccy = prnk_ccy;
        this.Pr_ccyexp = pr_ccyexp;
        this.Pr_cnvrate = pr_cnvrate;
        this.Pr_cnvdate = pr_cnvdate;
        this.Region = region;
        this.Card_Type = card_Type;
        this.Proc_Class = proc_Class;
        this.CARD_SEQ_NR = cARD_SEQ_NR;
        this.Msg_type = msg_type;
        this.Org_msg_type = org_msg_type;
        this.Proc_code = proc_code;
        this.Msg_category = msg_category;
        this.Merchant = merchant;
        this.MOTO_IND = mOTO_IND;
        this.Susp_status = susp_status;
        this.Transact_row = transact_row;
        this.Authoriz_row = authoriz_row;
        this.FLD_043 = fLD_043;
        this.FLD_098 = fLD_098;
        this.FLD_102 = fLD_102;
        this.FLD_103 = fLD_103;
        this.FLD_104 = fLD_104;
        this.FLD_039 = fLD_039;
        this.FLD_SH6 = fLD_SH6;
        this.Batch_date = batch_date;
        this.Tr_fee = tr_fee;
        this.FLD_040 = fLD_040;
        this.FLD_123_1 = fLD_123_1;
        this.EPI_42_48 = ePI_42_48;
        this.FLD_003 = fLD_003;
        this.MSC = mSC;
        this.Account_nr = account_nr;
        this.EPI_42_48_FULL = ePI_42_48_FULL;
        this.Other_Code = other_Code;
        this.FLD_015 = fLD_015;
        this.FLD_095 = fLD_095;
        this.Audit_date = audit_date;
        this.Other_fee1 = other_fee1;
        this.Other_fee2 = other_fee2;
        this.Other_fee3 = other_fee3;
        this.Other_fee4 = other_fee4;
        this.Other_fee5 = other_fee5;
    }
    
    public B_file_transaction_record() {
    }
    
    public B_file_transaction_record(final long eMPC_file_id) {
        this.EMPC_file_id = eMPC_file_id;
    }
}
