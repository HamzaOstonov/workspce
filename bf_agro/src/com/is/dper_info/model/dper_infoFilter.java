package com.is.dper_info.model;

import java.math.BigDecimal;
import java.util.Date;


public class dper_infoFilter{
	 private Date date_begin;
	 private Date date_end;
	 private String branch;
     private String mbranch;
     private Long id;
     private String veoper;
     private String kind;
     private String strs;
     private String strr;
     private String distr;
     private BigDecimal summa;
     private String currency;
     private Date v_date;
     private String client;
     private String client_name1;
     private String client_name2;
     private String client_name3;
     private Long rezident;
     private Long doc_id;
     private String doc_series;
     private String doc_number;
     private String doc_issue;
     private Date doc_date_issue;
     private String client_i;
     private Long state;
     private String post_address;
     private Date birthday;
     private BigDecimal profit;
     private String mtcn;
     private int u1f2;
     private BigDecimal centsumma;
     private String client_i2;
     private String client_i3;
     private String client_i4;
     private String client_i5;
     private String client_i6;
     private String client_i7;
     private String client_i8;
     private String client_i9;
     private String client_i10;
     private Date client_i11date;
     private String client_i12;
     private BigDecimal summa2;
     private BigDecimal summa3;
     private BigDecimal summa4;
     private BigDecimal summa5;
     private String client_i13code_str;
     private String region_offshor;
     private String client_grstr;

     private String eval;
     private String kurs_val;
     private String acc_dep;
     private BigDecimal out_tmp;
     private String name_acc;
    
     private String purpose_1;
     private String purpose_2;
     private String purpose_3;
     private String purpose_4;
     private String purpose_5;
     private String purpose_6;
    
    public dper_infoFilter() {

    }

	public dper_infoFilter(Date date_begin, Date date_end, String branch,
			String mbranch, Long id, String veoper, String kind, String strs,
			String strr, String distr, BigDecimal summa, String currency,
			Date v_date, String client, String client_name1,
			String client_name2, String client_name3, Long rezident,
			Long doc_id, String doc_series, String doc_number,
			String doc_issue, Date doc_date_issue, String client_i, Long state,
			String post_address, Date birthday, BigDecimal profit, String mtcn,
			int u1f2, BigDecimal centsumma, String client_i2, String client_i3,
			String client_i4, String client_i5, String client_i6,
			String client_i7, String client_i8, String client_i9,
			String client_i10, Date client_i11date, String client_i12,
			BigDecimal summa2, BigDecimal summa3, BigDecimal summa4,
			BigDecimal summa5, String client_i13code_str,
			String region_offshor, String client_grstr, String eval,
			String kurs_val, String acc_dep, BigDecimal out_tmp,
			String name_acc, String purpose_1, String purpose_2,
			String purpose_3, String purpose_4, String purpose_5,
			String purpose_6) {
		super();
		this.date_begin = date_begin;
		this.date_end = date_end;
		this.branch = branch;
		this.mbranch = mbranch;
		this.id = id;
		this.veoper = veoper;
		this.kind = kind;
		this.strs = strs;
		this.strr = strr;
		this.distr = distr;
		this.summa = summa;
		this.currency = currency;
		this.v_date = v_date;
		this.client = client;
		this.client_name1 = client_name1;
		this.client_name2 = client_name2;
		this.client_name3 = client_name3;
		this.rezident = rezident;
		this.doc_id = doc_id;
		this.doc_series = doc_series;
		this.doc_number = doc_number;
		this.doc_issue = doc_issue;
		this.doc_date_issue = doc_date_issue;
		this.client_i = client_i;
		this.state = state;
		this.post_address = post_address;
		this.birthday = birthday;
		this.profit = profit;
		this.mtcn = mtcn;
		this.u1f2 = u1f2;
		this.centsumma = centsumma;
		this.client_i2 = client_i2;
		this.client_i3 = client_i3;
		this.client_i4 = client_i4;
		this.client_i5 = client_i5;
		this.client_i6 = client_i6;
		this.client_i7 = client_i7;
		this.client_i8 = client_i8;
		this.client_i9 = client_i9;
		this.client_i10 = client_i10;
		this.client_i11date = client_i11date;
		this.client_i12 = client_i12;
		this.summa2 = summa2;
		this.summa3 = summa3;
		this.summa4 = summa4;
		this.summa5 = summa5;
		this.client_i13code_str = client_i13code_str;
		this.region_offshor = region_offshor;
		this.client_grstr = client_grstr;
		this.eval = eval;
		this.kurs_val = kurs_val;
		this.acc_dep = acc_dep;
		this.out_tmp = out_tmp;
		this.name_acc = name_acc;
		this.purpose_1 = purpose_1;
		this.purpose_2 = purpose_2;
		this.purpose_3 = purpose_3;
		this.purpose_4 = purpose_4;
		this.purpose_5 = purpose_5;
		this.purpose_6 = purpose_6;
	}

	public Date getDate_begin() {
		return date_begin;
	}

	public void setDate_begin(Date date_begin) {
		this.date_begin = date_begin;
	}

	public Date getDate_end() {
		return date_end;
	}

	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getMbranch() {
		return mbranch;
	}

	public void setMbranch(String mbranch) {
		this.mbranch = mbranch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVeoper() {
		return veoper;
	}

	public void setVeoper(String veoper) {
		this.veoper = veoper;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getStrs() {
		return strs;
	}

	public void setStrs(String strs) {
		this.strs = strs;
	}

	public String getStrr() {
		return strr;
	}

	public void setStrr(String strr) {
		this.strr = strr;
	}

	public String getDistr() {
		return distr;
	}

	public void setDistr(String distr) {
		this.distr = distr;
	}

	public BigDecimal getSumma() {
		return summa;
	}

	public void setSumma(BigDecimal summa) {
		this.summa = summa;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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

	public Long getRezident() {
		return rezident;
	}

	public void setRezident(Long rezident) {
		this.rezident = rezident;
	}

	public Long getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
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

	public String getDoc_issue() {
		return doc_issue;
	}

	public void setDoc_issue(String doc_issue) {
		this.doc_issue = doc_issue;
	}

	public Date getDoc_date_issue() {
		return doc_date_issue;
	}

	public void setDoc_date_issue(Date doc_date_issue) {
		this.doc_date_issue = doc_date_issue;
	}

	public String getClient_i() {
		return client_i;
	}

	public void setClient_i(String client_i) {
		this.client_i = client_i;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getPost_address() {
		return post_address;
	}

	public void setPost_address(String post_address) {
		this.post_address = post_address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getMtcn() {
		return mtcn;
	}

	public void setMtcn(String mtcn) {
		this.mtcn = mtcn;
	}

	public int getU1f2() {
		return u1f2;
	}

	public void setU1f2(int u1f2) {
		this.u1f2 = u1f2;
	}

	public BigDecimal getCentsumma() {
		return centsumma;
	}

	public void setCentsumma(BigDecimal centsumma) {
		this.centsumma = centsumma;
	}

	public String getClient_i2() {
		return client_i2;
	}

	public void setClient_i2(String client_i2) {
		this.client_i2 = client_i2;
	}

	public String getClient_i3() {
		return client_i3;
	}

	public void setClient_i3(String client_i3) {
		this.client_i3 = client_i3;
	}

	public String getClient_i4() {
		return client_i4;
	}

	public void setClient_i4(String client_i4) {
		this.client_i4 = client_i4;
	}

	public String getClient_i5() {
		return client_i5;
	}

	public void setClient_i5(String client_i5) {
		this.client_i5 = client_i5;
	}

	public String getClient_i6() {
		return client_i6;
	}

	public void setClient_i6(String client_i6) {
		this.client_i6 = client_i6;
	}

	public String getClient_i7() {
		return client_i7;
	}

	public void setClient_i7(String client_i7) {
		this.client_i7 = client_i7;
	}

	public String getClient_i8() {
		return client_i8;
	}

	public void setClient_i8(String client_i8) {
		this.client_i8 = client_i8;
	}

	public String getClient_i9() {
		return client_i9;
	}

	public void setClient_i9(String client_i9) {
		this.client_i9 = client_i9;
	}

	public String getClient_i10() {
		return client_i10;
	}

	public void setClient_i10(String client_i10) {
		this.client_i10 = client_i10;
	}

	public Date getClient_i11date() {
		return client_i11date;
	}

	public void setClient_i11date(Date client_i11date) {
		this.client_i11date = client_i11date;
	}

	public String getClient_i12() {
		return client_i12;
	}

	public void setClient_i12(String client_i12) {
		this.client_i12 = client_i12;
	}

	public BigDecimal getSumma2() {
		return summa2;
	}

	public void setSumma2(BigDecimal summa2) {
		this.summa2 = summa2;
	}

	public BigDecimal getSumma3() {
		return summa3;
	}

	public void setSumma3(BigDecimal summa3) {
		this.summa3 = summa3;
	}

	public BigDecimal getSumma4() {
		return summa4;
	}

	public void setSumma4(BigDecimal summa4) {
		this.summa4 = summa4;
	}

	public BigDecimal getSumma5() {
		return summa5;
	}

	public void setSumma5(BigDecimal summa5) {
		this.summa5 = summa5;
	}

	public String getClient_i13code_str() {
		return client_i13code_str;
	}

	public void setClient_i13code_str(String client_i13code_str) {
		this.client_i13code_str = client_i13code_str;
	}

	public String getRegion_offshor() {
		return region_offshor;
	}

	public void setRegion_offshor(String region_offshor) {
		this.region_offshor = region_offshor;
	}

	public String getClient_grstr() {
		return client_grstr;
	}

	public void setClient_grstr(String client_grstr) {
		this.client_grstr = client_grstr;
	}

	public String getEval() {
		return eval;
	}

	public void setEval(String eval) {
		this.eval = eval;
	}

	public String getKurs_val() {
		return kurs_val;
	}

	public void setKurs_val(String kurs_val) {
		this.kurs_val = kurs_val;
	}

	public String getAcc_dep() {
		return acc_dep;
	}

	public void setAcc_dep(String acc_dep) {
		this.acc_dep = acc_dep;
	}

	public BigDecimal getOut_tmp() {
		return out_tmp;
	}

	public void setOut_tmp(BigDecimal out_tmp) {
		this.out_tmp = out_tmp;
	}

	public String getName_acc() {
		return name_acc;
	}

	public void setName_acc(String name_acc) {
		this.name_acc = name_acc;
	}

	public String getPurpose_1() {
		return purpose_1;
	}

	public void setPurpose_1(String purpose_1) {
		this.purpose_1 = purpose_1;
	}

	public String getPurpose_2() {
		return purpose_2;
	}

	public void setPurpose_2(String purpose_2) {
		this.purpose_2 = purpose_2;
	}

	public String getPurpose_3() {
		return purpose_3;
	}

	public void setPurpose_3(String purpose_3) {
		this.purpose_3 = purpose_3;
	}

	public String getPurpose_4() {
		return purpose_4;
	}

	public void setPurpose_4(String purpose_4) {
		this.purpose_4 = purpose_4;
	}

	public String getPurpose_5() {
		return purpose_5;
	}

	public void setPurpose_5(String purpose_5) {
		this.purpose_5 = purpose_5;
	}

	public String getPurpose_6() {
		return purpose_6;
	}

	public void setPurpose_6(String purpose_6) {
		this.purpose_6 = purpose_6;
	}
    
    
   
}
