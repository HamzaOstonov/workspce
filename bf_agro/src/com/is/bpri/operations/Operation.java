package com.is.bpri.operations;

import java.math.BigDecimal;
import java.util.Date;

public class Operation {

	private long id;
	private String branch;
	private long form_id;
	private Date v_date;
	private Date d_date;
	private String doc_num;
	private BigDecimal realsumma;
	private double rate;
	private String bank;
	private String account;
	private String name;
	private String bank_inter;
	private String purpose;
	private int exp_id;
	private int s_deal_id;
	private int state;
	private String currency;
	private BigDecimal summa;
	private int prev_id;
	private int op_sign;
	private int num;
	private String exp_name;
	private String cash_acc;
	private String id_client;
	private String name_cl;
	private Date d_date1;
	private String doc_type_m;
	private int manual_op;
	private String code_plat;
	private String id_transh_purp;
	private String sp36;
	private String account_kazn;
	private String code1;
	private String code2;
	private String code_cb;
	private String state_name;
	
	public Operation() {
	
	}

	public Operation(long id, String branch, long form_id, Date v_date,
			Date d_date, String doc_num, BigDecimal realsumma, double rate,
			String bank, String account, String name, String bank_inter,
			String purpose, int exp_id, int s_deal_id, int state,
			String currency, BigDecimal summa, int prev_id, int op_sign,
			int num, String exp_name, String cash_acc, String id_client,
			String name_cl, Date d_date1, String doc_type_m, int manual_op,
			String code_plat, String id_transh_purp, String sp36,
			String account_kazn, String code1, String code2, String code_cb,String state_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.form_id = form_id;
		this.v_date = v_date;
		this.d_date = d_date;
		this.doc_num = doc_num;
		this.realsumma = realsumma;
		this.rate = rate;
		this.bank = bank;
		this.account = account;
		this.name = name;
		this.bank_inter = bank_inter;
		this.purpose = purpose;
		this.exp_id = exp_id;
		this.s_deal_id = s_deal_id;
		this.state = state;
		this.currency = currency;
		this.summa = summa;
		this.prev_id = prev_id;
		this.op_sign = op_sign;
		this.num = num;
		this.exp_name = exp_name;
		this.cash_acc = cash_acc;
		this.id_client = id_client;
		this.name_cl = name_cl;
		this.d_date1 = d_date1;
		this.doc_type_m = doc_type_m;
		this.manual_op = manual_op;
		this.code_plat = code_plat;
		this.id_transh_purp = id_transh_purp;
		this.sp36 = sp36;
		this.account_kazn = account_kazn;
		this.code1 = code1;
		this.code2 = code2;
		this.code_cb = code_cb;
		this.state_name = state_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public long getForm_id() {
		return form_id;
	}

	public void setForm_id(long form_id) {
		this.form_id = form_id;
	}

	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	public Date getD_date() {
		return d_date;
	}

	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}

	public String getDoc_num() {
		return doc_num;
	}

	public void setDoc_num(String doc_num) {
		this.doc_num = doc_num;
	}

	public BigDecimal getRealsumma() {
		return realsumma;
	}

	public void setRealsumma(BigDecimal realsumma) {
		this.realsumma = realsumma;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBank_inter() {
		return bank_inter;
	}

	public void setBank_inter(String bank_inter) {
		this.bank_inter = bank_inter;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public int getExp_id() {
		return exp_id;
	}

	public void setExp_id(int exp_id) {
		this.exp_id = exp_id;
	}

	public int getS_deal_id() {
		return s_deal_id;
	}

	public void setS_deal_id(int s_deal_id) {
		this.s_deal_id = s_deal_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getSumma() {
		return summa;
	}

	public void setSumma(BigDecimal summa) {
		this.summa = summa;
	}

	public int getPrev_id() {
		return prev_id;
	}

	public void setPrev_id(int prev_id) {
		this.prev_id = prev_id;
	}

	public int getOp_sign() {
		return op_sign;
	}

	public void setOp_sign(int op_sign) {
		this.op_sign = op_sign;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getExp_name() {
		return exp_name;
	}

	public void setExp_name(String exp_name) {
		this.exp_name = exp_name;
	}

	public String getCash_acc() {
		return cash_acc;
	}

	public void setCash_acc(String cash_acc) {
		this.cash_acc = cash_acc;
	}

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	public String getName_cl() {
		return name_cl;
	}

	public void setName_cl(String name_cl) {
		this.name_cl = name_cl;
	}

	public Date getD_date1() {
		return d_date1;
	}

	public void setD_date1(Date d_date1) {
		this.d_date1 = d_date1;
	}

	public String getDoc_type_m() {
		return doc_type_m;
	}

	public void setDoc_type_m(String doc_type_m) {
		this.doc_type_m = doc_type_m;
	}

	public int getManual_op() {
		return manual_op;
	}

	public void setManual_op(int manual_op) {
		this.manual_op = manual_op;
	}

	public String getCode_plat() {
		return code_plat;
	}

	public void setCode_plat(String code_plat) {
		this.code_plat = code_plat;
	}

	public String getId_transh_purp() {
		return id_transh_purp;
	}

	public void setId_transh_purp(String id_transh_purp) {
		this.id_transh_purp = id_transh_purp;
	}

	public String getSp36() {
		return sp36;
	}

	public void setSp36(String sp36) {
		this.sp36 = sp36;
	}

	public String getAccount_kazn() {
		return account_kazn;
	}

	public void setAccount_kazn(String account_kazn) {
		this.account_kazn = account_kazn;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getCode_cb() {
		return code_cb;
	}

	public void setCode_cb(String code_cb) {
		this.code_cb = code_cb;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	
//	
//	private long form_id;
//	private String client_id;
//	private int s_deal_id;
//	private String oper_name;
//	private BigDecimal summa;
//	private String doc_num;
//	private Date v_date;
//	private int state;
//	private String state_name;
//	private String branch;
//	private String doc_type_m;
//	private Date d_date;
//	private Long num;
//	private BigDecimal rate;
//	private String account;
//	private String acc_name;
//	private int manual_op;
//	private String acc_co;
//	private String acc_name_co;
//	private String branch_co;
//	private String name_co;
//	private String id_transh_purp;
//	private String account_kazn;
//	private String purpose;
//	private String code_plat;
//	
//	public Operation() {
//
//	}
//
//	public Operation(long id, long form_id, String client_id, int s_deal_id,
//			String oper_name,
//			BigDecimal summa, String doc_num, Date v_date,
//			int state, String state_name,String branch,String doc_type_m,Date d_date,Long num,BigDecimal rate,
//			String account,String acc_name,int manual_op,String acc_co,String acc_name_co,String branch_co,String name_co,
//			String id_transh_purp,String account_kazn,String purpose,String code_plat) {
//		super();
//		this.id = id;
//		this.form_id = form_id;
//		this.client_id = client_id;
//		this.s_deal_id = s_deal_id;
//		this.oper_name = oper_name;
//		this.summa = summa;
//		this.doc_num = doc_num;
//		this.v_date = v_date;
//		this.state = state;
//		this.state_name = state_name;
//		this.branch = branch;
//		this.doc_type_m = doc_type_m;
//		this.d_date = d_date;
//		this.num = num;
//		this.rate = rate;
//		this.account = account;
//		this.acc_name = acc_name;
//		this.manual_op = manual_op;
//		this.acc_co = acc_co;
//		this.acc_name_co = acc_name_co;
//		this.branch_co = branch_co;
//		this.name_co = name_co;
//		this.id_transh_purp = id_transh_purp;
//		this.account_kazn = account_kazn;
//		this.purpose = purpose;
//		this.code_plat = code_plat;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public long getForm_id() {
//		return form_id;
//	}
//
//	public void setForm_id(long form_id) {
//		this.form_id = form_id;
//	}
//
//	public String getClient_id() {
//		return client_id;
//	}
//
//	public void setClient_id(String client_id) {
//		this.client_id = client_id;
//	}
//
//	public int getS_deal_id() {
//		return s_deal_id;
//	}
//
//	public void setS_deal_id(int s_deal_id) {
//		this.s_deal_id = s_deal_id;
//	}
//
//	public String getOper_name() {
//		return oper_name;
//	}
//
//	public void setOper_name(String oper_name) {
//		this.oper_name = oper_name;
//	}
//
//	public BigDecimal getSumma() {
//		return summa;
//	}
//
//	public void setSumma(BigDecimal summa) {
//		this.summa = summa;
//	}
//
//	public String getDoc_num() {
//		return doc_num;
//	}
//
//	public void setDoc_num(String doc_num) {
//		this.doc_num = doc_num;
//	}
//
//	public Date getV_date() {
//		return v_date;
//	}
//
//	public void setV_date(Date v_date) {
//		this.v_date = v_date;
//	}
//
//	public int getState() {
//		return state;
//	}
//
//	public void setState(int state) {
//		this.state = state;
//	}
//
//	public String getState_name() {
//		return state_name;
//	}
//
//	public void setState_name(String state_name) {
//		this.state_name = state_name;
//	}
//
//	public String getBranch() {
//		return branch;
//	}
//
//	public void setBranch(String branch) {
//		this.branch = branch;
//	}
//
//	public String getDoc_type_m() {
//		return doc_type_m;
//	}
//
//	public void setDoc_type_m(String doc_type_m) {
//		this.doc_type_m = doc_type_m;
//	}
//
//	public Date getD_date() {
//		return d_date;
//	}
//
//	public void setD_date(Date d_date) {
//		this.d_date = d_date;
//	}
//
//	public Long getNum() {
//		return num;
//	}
//
//	public void setNum(Long num) {
//		this.num = num;
//	}
//
//	public BigDecimal getRate() {
//		return rate;
//	}
//
//	public void setRate(BigDecimal rate) {
//		this.rate = rate;
//	}
//
//	public String getAccount() {
//		return account;
//	}
//
//	public void setAccount(String account) {
//		this.account = account;
//	}
//
//	public String getAcc_name() {
//		return acc_name;
//	}
//
//	public void setAcc_name(String acc_name) {
//		this.acc_name = acc_name;
//	}
//
//	public int getManual_op() {
//		return manual_op;
//	}
//
//	public void setManual_op(int manual_op) {
//		this.manual_op = manual_op;
//	}
//
//	public String getAcc_co() {
//		return acc_co;
//	}
//
//	public void setAcc_co(String acc_co) {
//		this.acc_co = acc_co;
//	}
//
//	public String getBranch_co() {
//		return branch_co;
//	}
//
//	public void setBranch_co(String branch_co) {
//		this.branch_co = branch_co;
//	}
//
//	public String getName_co() {
//		return name_co;
//	}
//
//	public void setName_co(String name_co) {
//		this.name_co = name_co;
//	}
//
//	public String getId_transh_purp() {
//		return id_transh_purp;
//	}
//
//	public void setId_transh_purp(String id_transh_purp) {
//		this.id_transh_purp = id_transh_purp;
//	}
//
//	public String getAccount_kazn() {
//		return account_kazn;
//	}
//
//	public void setAccount_kazn(String account_kazn) {
//		this.account_kazn = account_kazn;
//	}
//
//	public String getPurpose() {
//		return purpose;
//	}
//
//	public void setPurpose(String purpose) {
//		this.purpose = purpose;
//	}
//
//	public String getCode_plat() {
//		return code_plat;
//	}
//
//	public void setCode_plat(String code_plat) {
//		this.code_plat = code_plat;
//	}
//
//	public String getAcc_name_co() {
//		return acc_name_co;
//	}
//
//	public void setAcc_name_co(String acc_name_co) {
//		this.acc_name_co = acc_name_co;
//	}
	
}
