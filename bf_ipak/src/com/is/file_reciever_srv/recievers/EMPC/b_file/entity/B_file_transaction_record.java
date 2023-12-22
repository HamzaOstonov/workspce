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
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getEMPC_file_id()
	{
		return EMPC_file_id;
	}
	public void setEMPC_file_id(Long eMPC_file_id)
	{
		EMPC_file_id = eMPC_file_id;
	}
	public void setMs_number(String ms_number)
	{
		Ms_number = ms_number;
	}
	public String getMs_number()
	{
		return Ms_number;
	}
	public String getMtid()
	{
		return Mtid;
	}
	public void setMtid(String mtid)
	{
		Mtid = mtid;
	}
	public String getRec_centr()
	{
		return Rec_centr;
	}
	public void setRec_centr(String rec_centr)
	{
		Rec_centr = rec_centr;
	}
	public String getSend_centr()
	{
		return Send_centr;
	}
	public void setSend_centr(String send_centr)
	{
		Send_centr = send_centr;
	}
	public String getISS_CMI()
	{
		return ISS_CMI;
	}
	public void setISS_CMI(String iSS_CMI)
	{
		ISS_CMI = iSS_CMI;
	}
	public String getSend_CMI()
	{
		return Send_CMI;
	}
	public void setSend_CMI(String send_CMI)
	{
		Send_CMI = send_CMI;
	}
	public Long getRec_num()
	{
		return rec_num;
	}
	public void setRec_num(Long rec_num)
	{
		this.rec_num = rec_num;
	}
	public String getSettl_CMI()
	{
		return Settl_CMI;
	}
	public void setSettl_CMI(String settl_CMI)
	{
		Settl_CMI = settl_CMI;
	}
	public String getAcq_bank()
	{
		return Acq_bank;
	}
	public void setAcq_bank(String acq_bank)
	{
		Acq_bank = acq_bank;
	}
	public String getAcq_branch()
	{
		return Acq_branch;
	}
	public void setAcq_branch(String acq_branch)
	{
		Acq_branch = acq_branch;
	}
	public String getMember()
	{
		return Member;
	}
	public void setMember(String member)
	{
		Member = member;
	}
	public String getClearing_group()
	{
		return Clearing_group;
	}
	public void setClearing_group(String clearing_group)
	{
		Clearing_group = clearing_group;
	}
	public String getMerchant_accept()
	{
		return Merchant_accept;
	}
	public void setMerchant_accept(String merchant_accept)
	{
		Merchant_accept = merchant_accept;
	}
	public String getBatch_nr()
	{
		return Batch_nr;
	}
	public void setBatch_nr(String batch_nr)
	{
		Batch_nr = batch_nr;
	}
	public String getSlip_nr()
	{
		return Slip_nr;
	}
	public void setSlip_nr(String slip_nr)
	{
		Slip_nr = slip_nr;
	}
	public String getCard()
	{
		return Card;
	}
	public void setCard(String card)
	{
		Card = card;
	}
	public String getExp_date()
	{
		return Exp_date;
	}
	public void setExp_date(String exp_date)
	{
		Exp_date = exp_date;
	}
	public String getDate()
	{
		return Date;
	}
	public void setDate(String date)
	{
		Date = date;
	}
	public String getTime()
	{
		return Time;
	}
	public void setTime(String time)
	{
		Time = time;
	}
	public String getTran_type()
	{
		return Tran_type;
	}
	public void setTran_type(String tran_type)
	{
		Tran_type = tran_type;
	}
	public String getAppr_code()
	{
		return Appr_code;
	}
	public void setAppr_code(String appr_code)
	{
		Appr_code = appr_code;
	}
	public String getAppr_src()
	{
		return Appr_src;
	}
	public void setAppr_src(String appr_src)
	{
		Appr_src = appr_src;
	}
	public String getStan()
	{
		return Stan;
	}
	public void setStan(String stan)
	{
		Stan = stan;
	}
	public String getRef_number()
	{
		return Ref_number;
	}
	public void setRef_number(String ref_number)
	{
		Ref_number = ref_number;
	}
	public String getAmount()
	{
		return Amount;
	}
	public void setAmount(String amount)
	{
		Amount = amount;
	}
	public String getCash_back()
	{
		return Cash_back;
	}
	public void setCash_back(String cash_back)
	{
		Cash_back = cash_back;
	}
	public String getFee()
	{
		return Fee;
	}
	public void setFee(String fee)
	{
		Fee = fee;
	}
	public String getCurrency()
	{
		return Currency;
	}
	public void setCurrency(String currency)
	{
		Currency = currency;
	}
	public String getCcy_exp()
	{
		return Ccy_exp;
	}
	public void setCcy_exp(String ccy_exp)
	{
		Ccy_exp = ccy_exp;
	}
	public String getSb_amount()
	{
		return Sb_amount;
	}
	public void setSb_amount(String sb_amount)
	{
		Sb_amount = sb_amount;
	}
	public String getSb_cshback()
	{
		return Sb_cshback;
	}
	public void setSb_cshback(String sb_cshback)
	{
		Sb_cshback = sb_cshback;
	}
	public String getSb_fee()
	{
		return Sb_fee;
	}
	public void setSb_fee(String sb_fee)
	{
		Sb_fee = sb_fee;
	}
	public String getSbnk_ccy()
	{
		return Sbnk_ccy;
	}
	public void setSbnk_ccy(String sbnk_ccy)
	{
		Sbnk_ccy = sbnk_ccy;
	}
	public String getSb_ccyexp()
	{
		return Sb_ccyexp;
	}
	public void setSb_ccyexp(String sb_ccyexp)
	{
		Sb_ccyexp = sb_ccyexp;
	}
	public String getSb_cnvrate()
	{
		return Sb_cnvrate;
	}
	public void setSb_cnvrate(String sb_cnvrate)
	{
		Sb_cnvrate = sb_cnvrate;
	}
	public String getSb_cnvdate()
	{
		return Sb_cnvdate;
	}
	public void setSb_cnvdate(String sb_cnvdate)
	{
		Sb_cnvdate = sb_cnvdate;
	}
	public String getI_amount()
	{
		return I_amount;
	}
	public void setI_amount(String i_amount)
	{
		I_amount = i_amount;
	}
	public String getI_cshback()
	{
		return I_cshback;
	}
	public void setI_cshback(String i_cshback)
	{
		I_cshback = i_cshback;
	}
	public String getI_fee()
	{
		return I_fee;
	}
	public void setI_fee(String i_fee)
	{
		I_fee = i_fee;
	}
	public String getIbnk_ccy()
	{
		return Ibnk_ccy;
	}
	public void setIbnk_ccy(String ibnk_ccy)
	{
		Ibnk_ccy = ibnk_ccy;
	}
	public String getI_ccyexp()
	{
		return I_ccyexp;
	}
	public void setI_ccyexp(String i_ccyexp)
	{
		I_ccyexp = i_ccyexp;
	}
	public String getI_cnvrate()
	{
		return I_cnvrate;
	}
	public void setI_cnvrate(String i_cnvrate)
	{
		I_cnvrate = i_cnvrate;
	}
	public String getI_cnvdate()
	{
		return I_cnvdate;
	}
	public void setI_cnvdate(String i_cnvdate)
	{
		I_cnvdate = i_cnvdate;
	}
	public String getAbvr_name()
	{
		return Abvr_name;
	}
	public void setAbvr_name(String abvr_name)
	{
		Abvr_name = abvr_name;
	}
	public String getCity()
	{
		return City;
	}
	public void setCity(String city)
	{
		City = city;
	}
	public String getCountry()
	{
		return Country;
	}
	public void setCountry(String country)
	{
		Country = country;
	}
	public String getPoint_code()
	{
		return Point_code;
	}
	public void setPoint_code(String point_code)
	{
		Point_code = point_code;
	}
	public String getMCC_code()
	{
		return MCC_code;
	}
	public void setMCC_code(String mCC_code)
	{
		MCC_code = mCC_code;
	}
	public String getTerminal()
	{
		return Terminal;
	}
	public void setTerminal(String terminal)
	{
		Terminal = terminal;
	}
	public String getBatch_id()
	{
		return Batch_id;
	}
	public void setBatch_id(String batch_id)
	{
		Batch_id = batch_id;
	}
	public String getSettl_nr()
	{
		return Settl_nr;
	}
	public void setSettl_nr(String settl_nr)
	{
		Settl_nr = settl_nr;
	}
	public String getSettl_date()
	{
		return Settl_date;
	}
	public void setSettl_date(String settl_date)
	{
		Settl_date = settl_date;
	}
	public String getAcqref_nr()
	{
		return Acqref_nr;
	}
	public void setAcqref_nr(String acqref_nr)
	{
		Acqref_nr = acqref_nr;
	}
	public String getFile_id()
	{
		return File_id;
	}
	public void setFile_id(String file_id)
	{
		File_id = file_id;
	}
	public String getFile_date()
	{
		return File_date;
	}
	public void setFile_date(String file_date)
	{
		File_date = file_date;
	}
	public String getSource_algorithm()
	{
		return Source_algorithm;
	}
	public void setSource_algorithm(String source_algorithm)
	{
		Source_algorithm = source_algorithm;
	}
	public String getTerm_nr()
	{
		return Term_nr;
	}
	public void setTerm_nr(String term_nr)
	{
		Term_nr = term_nr;
	}
	public String getECMC_Fee()
	{
		return ECMC_Fee;
	}
	public void setECMC_Fee(String eCMC_Fee)
	{
		ECMC_Fee = eCMC_Fee;
	}
	public String getTran_info()
	{
		return Tran_info;
	}
	public void setTran_info(String tran_info)
	{
		Tran_info = tran_info;
	}
	public String getPr_amount()
	{
		return Pr_amount;
	}
	public void setPr_amount(String pr_amount)
	{
		Pr_amount = pr_amount;
	}
	public String getPr_cshback()
	{
		return Pr_cshback;
	}
	public void setPr_cshback(String pr_cshback)
	{
		Pr_cshback = pr_cshback;
	}
	public String getPr_fee()
	{
		return Pr_fee;
	}
	public void setPr_fee(String pr_fee)
	{
		Pr_fee = pr_fee;
	}
	public String getPrnk_ccy()
	{
		return Prnk_ccy;
	}
	public void setPrnk_ccy(String prnk_ccy)
	{
		Prnk_ccy = prnk_ccy;
	}
	public String getPr_ccyexp()
	{
		return Pr_ccyexp;
	}
	public void setPr_ccyexp(String pr_ccyexp)
	{
		Pr_ccyexp = pr_ccyexp;
	}
	public String getPr_cnvrate()
	{
		return Pr_cnvrate;
	}
	public void setPr_cnvrate(String pr_cnvrate)
	{
		Pr_cnvrate = pr_cnvrate;
	}
	public String getPr_cnvdate()
	{
		return Pr_cnvdate;
	}
	public void setPr_cnvdate(String pr_cnvdate)
	{
		Pr_cnvdate = pr_cnvdate;
	}
	public String getRegion()
	{
		return Region;
	}
	public void setRegion(String region)
	{
		Region = region;
	}
	public String getCard_Type()
	{
		return Card_Type;
	}
	public void setCard_Type(String card_Type)
	{
		Card_Type = card_Type;
	}
	public String getProc_Class()
	{
		return Proc_Class;
	}
	public void setProc_Class(String proc_Class)
	{
		Proc_Class = proc_Class;
	}
	public String getCARD_SEQ_NR()
	{
		return CARD_SEQ_NR;
	}
	public void setCARD_SEQ_NR(String cARD_SEQ_NR)
	{
		CARD_SEQ_NR = cARD_SEQ_NR;
	}
	public String getMsg_type()
	{
		return Msg_type;
	}
	public void setMsg_type(String msg_type)
	{
		Msg_type = msg_type;
	}
	public String getOrg_msg_type()
	{
		return Org_msg_type;
	}
	public void setOrg_msg_type(String org_msg_type)
	{
		Org_msg_type = org_msg_type;
	}
	public String getProc_code()
	{
		return Proc_code;
	}
	public void setProc_code(String proc_code)
	{
		Proc_code = proc_code;
	}
	public String getMsg_category()
	{
		return Msg_category;
	}
	public void setMsg_category(String msg_category)
	{
		Msg_category = msg_category;
	}
	public String getMerchant()
	{
		return Merchant;
	}
	public void setMerchant(String merchant)
	{
		Merchant = merchant;
	}
	public String getMOTO_IND()
	{
		return MOTO_IND;
	}
	public void setMOTO_IND(String mOTO_IND)
	{
		MOTO_IND = mOTO_IND;
	}
	public String getSusp_status()
	{
		return Susp_status;
	}
	public void setSusp_status(String susp_status)
	{
		Susp_status = susp_status;
	}
	public String getTransact_row()
	{
		return Transact_row;
	}
	public void setTransact_row(String transact_row)
	{
		Transact_row = transact_row;
	}
	public String getAuthoriz_row()
	{
		return Authoriz_row;
	}
	public void setAuthoriz_row(String authoriz_row)
	{
		Authoriz_row = authoriz_row;
	}
	public String getFLD_043()
	{
		return FLD_043;
	}
	public void setFLD_043(String fLD_043)
	{
		FLD_043 = fLD_043;
	}
	public String getFLD_098()
	{
		return FLD_098;
	}
	public void setFLD_098(String fLD_098)
	{
		FLD_098 = fLD_098;
	}
	public String getFLD_102()
	{
		return FLD_102;
	}
	public void setFLD_102(String fLD_102)
	{
		FLD_102 = fLD_102;
	}
	public String getFLD_103()
	{
		return FLD_103;
	}
	public void setFLD_103(String fLD_103)
	{
		FLD_103 = fLD_103;
	}
	public String getFLD_104()
	{
		return FLD_104;
	}
	public void setFLD_104(String fLD_104)
	{
		FLD_104 = fLD_104;
	}
	public String getFLD_039()
	{
		return FLD_039;
	}
	public void setFLD_039(String fLD_039)
	{
		FLD_039 = fLD_039;
	}
	public String getFLD_SH6()
	{
		return FLD_SH6;
	}
	public void setFLD_SH6(String fLD_SH6)
	{
		FLD_SH6 = fLD_SH6;
	}
	public String getBatch_date()
	{
		return Batch_date;
	}
	public void setBatch_date(String batch_date)
	{
		Batch_date = batch_date;
	}
	public String getTr_fee()
	{
		return Tr_fee;
	}
	public void setTr_fee(String tr_fee)
	{
		Tr_fee = tr_fee;
	}
	public String getFLD_040()
	{
		return FLD_040;
	}
	public void setFLD_040(String fLD_040)
	{
		FLD_040 = fLD_040;
	}
	public String getFLD_123_1()
	{
		return FLD_123_1;
	}
	public void setFLD_123_1(String fLD_123_1)
	{
		FLD_123_1 = fLD_123_1;
	}
	public String getEPI_42_48()
	{
		return EPI_42_48;
	}
	public void setEPI_42_48(String ePI_42_48)
	{
		EPI_42_48 = ePI_42_48;
	}
	public String getFLD_003()
	{
		return FLD_003;
	}
	public void setFLD_003(String fLD_003)
	{
		FLD_003 = fLD_003;
	}
	public String getMSC()
	{
		return MSC;
	}
	public void setMSC(String mSC)
	{
		MSC = mSC;
	}
	public String getAccount_nr()
	{
		return Account_nr;
	}
	public void setAccount_nr(String account_nr)
	{
		Account_nr = account_nr;
	}
	public String getEPI_42_48_FULL()
	{
		return EPI_42_48_FULL;
	}
	public void setEPI_42_48_FULL(String ePI_42_48_FULL)
	{
		EPI_42_48_FULL = ePI_42_48_FULL;
	}
	public String getOther_Code()
	{
		return Other_Code;
	}
	public void setOther_Code(String other_Code)
	{
		Other_Code = other_Code;
	}
	public String getFLD_015()
	{
		return FLD_015;
	}
	public void setFLD_015(String fLD_015)
	{
		FLD_015 = fLD_015;
	}
	public String getFLD_095()
	{
		return FLD_095;
	}
	public void setFLD_095(String fLD_095)
	{
		FLD_095 = fLD_095;
	}
	public String getAudit_date()
	{
		return Audit_date;
	}
	public void setAudit_date(String audit_date)
	{
		Audit_date = audit_date;
	}
	public String getOther_fee1()
	{
		return Other_fee1;
	}
	public void setOther_fee1(String other_fee1)
	{
		Other_fee1 = other_fee1;
	}
	public String getOther_fee2()
	{
		return Other_fee2;
	}
	public void setOther_fee2(String other_fee2)
	{
		Other_fee2 = other_fee2;
	}
	public String getOther_fee3()
	{
		return Other_fee3;
	}
	public void setOther_fee3(String other_fee3)
	{
		Other_fee3 = other_fee3;
	}
	public String getOther_fee4()
	{
		return Other_fee4;
	}
	public void setOther_fee4(String other_fee4)
	{
		Other_fee4 = other_fee4;
	}
	public String getOther_fee5()
	{
		return Other_fee5;
	}
	public void setOther_fee5(String other_fee5)
	{
		Other_fee5 = other_fee5;
	}
	
	public B_file_transaction_record(Long eMPC_file_id, 
			Long rec_num, Long id, String mtid,
			String rec_centr, String send_centr, String iSS_CMI,
			String send_CMI, String settl_CMI, String acq_bank,
			String acq_branch, String member, String clearing_group,
			String merchant_accept, String batch_nr, String slip_nr,
			String card, String exp_date, String date, String time,
			String tran_type, String appr_code, String appr_src, String stan,
			String ref_number, String amount, String cash_back, String fee,
			String currency, String ccy_exp, String sb_amount,
			String sb_cshback, String sb_fee, String sbnk_ccy,
			String sb_ccyexp, String sb_cnvrate, String sb_cnvdate,
			String i_amount, String i_cshback, String i_fee, String ibnk_ccy,
			String i_ccyexp, String i_cnvrate, String i_cnvdate,
			String abvr_name, String city, String country, String point_code,
			String mCC_code, String terminal, String batch_id, String settl_nr,
			String settl_date, String acqref_nr, String file_id, String ms_number,
			String file_date, String source_algorithm, String term_nr,
			String eCMC_Fee, String tran_info, String pr_amount,
			String pr_cshback, String pr_fee, String prnk_ccy,
			String pr_ccyexp, String pr_cnvrate, String pr_cnvdate,
			String region, String card_Type, String proc_Class,
			String cARD_SEQ_NR, String msg_type, String org_msg_type,
			String proc_code, String msg_category, String merchant,
			String mOTO_IND, String susp_status, String transact_row,
			String authoriz_row, String fLD_043, String fLD_098,
			String fLD_102, String fLD_103, String fLD_104, String fLD_039,
			String fLD_SH6, String batch_date, String tr_fee, String fLD_040,
			String fLD_123_1, String ePI_42_48, String fLD_003, String mSC,
			String account_nr, String ePI_42_48_FULL, String other_Code,
			String fLD_015, String fLD_095, String audit_date,
			String other_fee1, String other_fee2, String other_fee3,
			String other_fee4, String other_fee5)
	{
		EMPC_file_id = eMPC_file_id;
		this.rec_num = rec_num;
		this.id = id;
		Mtid = mtid;
		Rec_centr = rec_centr;
		Send_centr = send_centr;
		ISS_CMI = iSS_CMI;
		Send_CMI = send_CMI;
		Settl_CMI = settl_CMI;
		Acq_bank = acq_bank;
		Acq_branch = acq_branch;
		Member = member;
		Clearing_group = clearing_group;
		Merchant_accept = merchant_accept;
		Batch_nr = batch_nr;
		Slip_nr = slip_nr;
		Card = card;
		Exp_date = exp_date;
		Date = date;
		Time = time;
		Tran_type = tran_type;
		Appr_code = appr_code;
		Appr_src = appr_src;
		Stan = stan;
		Ref_number = ref_number;
		Amount = amount;
		Cash_back = cash_back;
		Fee = fee;
		Currency = currency;
		Ccy_exp = ccy_exp;
		Sb_amount = sb_amount;
		Sb_cshback = sb_cshback;
		Sb_fee = sb_fee;
		Sbnk_ccy = sbnk_ccy;
		Sb_ccyexp = sb_ccyexp;
		Sb_cnvrate = sb_cnvrate;
		Sb_cnvdate = sb_cnvdate;
		I_amount = i_amount;
		I_cshback = i_cshback;
		I_fee = i_fee;
		Ibnk_ccy = ibnk_ccy;
		I_ccyexp = i_ccyexp;
		I_cnvrate = i_cnvrate;
		I_cnvdate = i_cnvdate;
		Abvr_name = abvr_name;
		City = city;
		Country = country;
		Point_code = point_code;
		MCC_code = mCC_code;
		Terminal = terminal;
		Batch_id = batch_id;
		Settl_nr = settl_nr;
		Settl_date = settl_date;
		Acqref_nr = acqref_nr;
		File_id = file_id;
		Ms_number = ms_number;
		File_date = file_date;
		Source_algorithm = source_algorithm;
		Term_nr = term_nr;
		ECMC_Fee = eCMC_Fee;
		Tran_info = tran_info;
		Pr_amount = pr_amount;
		Pr_cshback = pr_cshback;
		Pr_fee = pr_fee;
		Prnk_ccy = prnk_ccy;
		Pr_ccyexp = pr_ccyexp;
		Pr_cnvrate = pr_cnvrate;
		Pr_cnvdate = pr_cnvdate;
		Region = region;
		Card_Type = card_Type;
		Proc_Class = proc_Class;
		CARD_SEQ_NR = cARD_SEQ_NR;
		Msg_type = msg_type;
		Org_msg_type = org_msg_type;
		Proc_code = proc_code;
		Msg_category = msg_category;
		Merchant = merchant;
		MOTO_IND = mOTO_IND;
		Susp_status = susp_status;
		Transact_row = transact_row;
		Authoriz_row = authoriz_row;
		FLD_043 = fLD_043;
		FLD_098 = fLD_098;
		FLD_102 = fLD_102;
		FLD_103 = fLD_103;
		FLD_104 = fLD_104;
		FLD_039 = fLD_039;
		FLD_SH6 = fLD_SH6;
		Batch_date = batch_date;
		Tr_fee = tr_fee;
		FLD_040 = fLD_040;
		FLD_123_1 = fLD_123_1;
		EPI_42_48 = ePI_42_48;
		FLD_003 = fLD_003;
		MSC = mSC;
		Account_nr = account_nr;
		EPI_42_48_FULL = ePI_42_48_FULL;
		Other_Code = other_Code;
		FLD_015 = fLD_015;
		FLD_095 = fLD_095;
		Audit_date = audit_date;
		Other_fee1 = other_fee1;
		Other_fee2 = other_fee2;
		Other_fee3 = other_fee3;
		Other_fee4 = other_fee4;
		Other_fee5 = other_fee5;
	}
	
	public B_file_transaction_record()
	{
		super();
	}
	public B_file_transaction_record(long eMPC_file_id)
	{
		EMPC_file_id = eMPC_file_id;
	}
}
