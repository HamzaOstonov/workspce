package com.is.tieto_agro.fileProcessing;

import java.util.Date;

/**
 * ID NUMBER ID //PAY_ID NUMBER ? BRANCH VARCHAR2(5) Филиал D_DATE DATE Дата
 * документа BANK_CL VARCHAR2(5) Банк отправителя ACC_CL VARCHAR2(50) Счет
 * отправителя NAME_CL VARCHAR2(512) Наименование отправителя BANK_CO
 * VARCHAR2(5) Банк получателя ACC_CO VARCHAR2(512) Счет получателя NAME_CO
 * VARCHAR2(512) Наименование получателя SUMMA NUMBER Сумма PURPOSE
 * VARCHAR2(1024) Код назначения платежа TYPE_DOC VARCHAR2(5) Тип документа PDC
 * VARCHAR2(1) Признак Д/К PARENT_GROUP_ID NUMBER Порожден модулем PARENT_ID
 * NUMBER Порожден № //CASH_CODE VARCHAR2(5) Кассовый символ //ID_TRANSH_PURP
 * NUMBER(10) ? //SCHEMA_NAME VARCHAR2(512) ? //ORD NUMBER ? //G_BRANCH
 * VARCHAR2(5) ? //G_DOCID NUMBER ? //PURP_CODE VARCHAR2(5) ? //PAY_TYPE NUMBER
 * ? //TRANS_TYPE NUMBER ? //ACC_DT_ID NUMBER ? //ACC_CT_ID NUMBER ?
 * //DEAL_GROUP_ID NUMBER ? DEAL_ID NUMBER Тип сделки //PARENT_DEAL_ID NUMBER(5)
 * ? //STATE_ID NUMBER(2) ?
 */
public class FileProcessing {
	Long id;
	Long pay_id;
	String branch;
	Date d_date;
	String bank_cl;
	String acc_cl;
	String name_cl;

	String bank_co;

	String acc_co;

	String name_co;

	Long summa;

	String purpose;

	String type_doc;

	String pdc;

	Long parent_group_id;

	Long parent_id;

	String cash_code;

	Long id_transh_purp;

	String schema_name;

	Long ord;

	String g_branch;

	Long g_docid;

	String purp_code;

	Long pay_type;

	Long trans_type;

	Long acc_dt_id;

	Long acc_ct_id;

	Long deal_group_id;

	Long deal_id;

	Long parent_deal_id;

	Long state_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPay_id() {
		return pay_id;
	}

	public void setPay_id(Long pay_id) {
		this.pay_id = pay_id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Date getD_date() {
		return d_date;
	}

	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}

	public String getBank_cl() {
		return bank_cl;
	}

	public void setBank_cl(String bank_cl) {
		this.bank_cl = bank_cl;
	}

	public String getAcc_cl() {
		return acc_cl;
	}

	public void setAcc_cl(String acc_cl) {
		this.acc_cl = acc_cl;
	}

	public String getName_cl() {
		return name_cl;
	}

	public void setName_cl(String name_cl) {
		this.name_cl = name_cl;
	}

	public String getBank_co() {
		return bank_co;
	}

	public void setBank_co(String bank_co) {
		this.bank_co = bank_co;
	}

	public String getAcc_co() {
		return acc_co;
	}

	public void setAcc_co(String acc_co) {
		this.acc_co = acc_co;
	}

	public String getName_co() {
		return name_co;
	}

	public void setName_co(String name_co) {
		this.name_co = name_co;
	}

	public Long getSumma() {
		return summa;
	}

	public void setSumma(Long summa) {
		this.summa = summa;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getType_doc() {
		return type_doc;
	}

	public void setType_doc(String type_doc) {
		this.type_doc = type_doc;
	}

	public String getPdc() {
		return pdc;
	}

	public void setPdc(String pdc) {
		this.pdc = pdc;
	}

	public Long getParent_group_id() {
		return parent_group_id;
	}

	public void setParent_group_id(Long parent_group_id) {
		this.parent_group_id = parent_group_id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getCash_code() {
		return cash_code;
	}

	public void setCash_code(String cash_code) {
		this.cash_code = cash_code;
	}

	public Long getId_transh_purp() {
		return id_transh_purp;
	}

	public void setId_transh_purp(Long id_transh_purp) {
		this.id_transh_purp = id_transh_purp;
	}

	public String getSchema_name() {
		return schema_name;
	}

	public void setSchema_name(String schema_name) {
		this.schema_name = schema_name;
	}

	public Long getOrd() {
		return ord;
	}

	public void setOrd(Long ord) {
		this.ord = ord;
	}

	public String getG_branch() {
		return g_branch;
	}

	public void setG_branch(String g_branch) {
		this.g_branch = g_branch;
	}

	public Long getG_docid() {
		return g_docid;
	}

	public void setG_docid(Long g_docid) {
		this.g_docid = g_docid;
	}

	public String getPurp_code() {
		return purp_code;
	}

	public void setPurp_code(String purp_code) {
		this.purp_code = purp_code;
	}

	public Long getPay_type() {
		return pay_type;
	}

	public void setPay_type(Long pay_type) {
		this.pay_type = pay_type;
	}

	public Long getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(Long trans_type) {
		this.trans_type = trans_type;
	}

	public Long getAcc_dt_id() {
		return acc_dt_id;
	}

	public void setAcc_dt_id(Long acc_dt_id) {
		this.acc_dt_id = acc_dt_id;
	}

	public Long getAcc_ct_id() {
		return acc_ct_id;
	}

	public void setAcc_ct_id(Long acc_ct_id) {
		this.acc_ct_id = acc_ct_id;
	}

	public Long getDeal_group_id() {
		return deal_group_id;
	}

	public void setDeal_group_id(Long deal_group_id) {
		this.deal_group_id = deal_group_id;
	}

	public Long getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(Long deal_id) {
		this.deal_id = deal_id;
	}

	public Long getParent_deal_id() {
		return parent_deal_id;
	}

	public void setParent_deal_id(Long parent_deal_id) {
		this.parent_deal_id = parent_deal_id;
	}

	public Long getState_id() {
		return state_id;
	}

	public void setState_id(Long state_id) {
		this.state_id = state_id;
	}

	public FileProcessing(Long id, Long pay_id, String branch, Date d_date,
			String bank_cl, String acc_cl, String name_cl, String bank_co,
			String acc_co, String name_co, Long summa, String purpose,
			String type_doc, String pdc, Long parent_group_id, Long parent_id,
			String cash_code, Long id_transh_purp, String schema_name,
			Long ord, String g_branch, Long g_docid, String purp_code,
			Long pay_type, Long trans_type, Long acc_dt_id, Long acc_ct_id,
			Long deal_group_id, Long deal_id, Long parent_deal_id, Long state_id) {
		super();
		this.id = id;
		this.pay_id = pay_id;
		this.branch = branch;
		this.d_date = d_date;
		this.bank_cl = bank_cl;
		this.acc_cl = acc_cl;
		this.name_cl = name_cl;
		this.bank_co = bank_co;
		this.acc_co = acc_co;
		this.name_co = name_co;
		this.summa = summa;
		this.purpose = purpose;
		this.type_doc = type_doc;
		this.pdc = pdc;
		this.parent_group_id = parent_group_id;
		this.parent_id = parent_id;
		this.cash_code = cash_code;
		this.id_transh_purp = id_transh_purp;
		this.schema_name = schema_name;
		this.ord = ord;
		this.g_branch = g_branch;
		this.g_docid = g_docid;
		this.purp_code = purp_code;
		this.pay_type = pay_type;
		this.trans_type = trans_type;
		this.acc_dt_id = acc_dt_id;
		this.acc_ct_id = acc_ct_id;
		this.deal_group_id = deal_group_id;
		this.deal_id = deal_id;
		this.parent_deal_id = parent_deal_id;
		this.state_id = state_id;
	}

	public FileProcessing() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
