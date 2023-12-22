package com.is.creditanket.table_models;

import java.util.Date;

public class Ld_char {
	
	private String branch;
    private Long id;
    private String eq_num;
    private Date eq_date;
    private String currency;
    private Double amount;
    private String p_num;
    private Date p_date;
    private String crc_num;
    private Date crc_date;
    private String t_author;
    private Date t_date;
    private Integer t_type;
    private String is_inv;
    private String currency_inv;
    private Double amount_inv;
    private String ld_num;
    private Date ld_date;
    private Integer cl_num;
    private String shifr_id;
    private String prod_name;
    private String sred_id;
    private Integer ld_period;
    private String target;
    private Double ld_amount;
    private Date date_begin;
    private Date date_end;
    private Long calc_id;
    private Date date_fee;
    private String arrears_reason;
    private Long term_type;
    private Long type_id;
    private String kred_id;
    private String klass_id;
    private Long status;
    private String al_num;
    private Integer is_tax;
    private Double tax_rate;
    private Long pk;
    private String klassp_id;
    private Integer ld_type;
    private Integer status_prc;
    private Integer acceptance;
    private Long niki_id;
    private String kred_id_cb;
    private String use_branch;
    private Integer ldays;
    private Integer cres;
    private Date date_close;
    private String kod_int;
    private String kod_fin;
    private Integer faza_abr;
    private Integer foreign_means;
	
    public Ld_char() {
		
	}

	public Ld_char(String branch, Long id, String eq_num, Date eq_date,
			String currency, Double amount, String p_num, Date p_date,
			String crc_num, Date crc_date, String t_author, Date t_date,
			Integer t_type, String is_inv, String currency_inv,
			Double amount_inv, String ld_num, Date ld_date, Integer cl_num,
			String shifr_id, String prod_name, String sred_id,
			Integer ld_period, String target, Double ld_amount,
			Date date_begin, Date date_end, Long calc_id, Date date_fee,
			String arrears_reason, Long term_type, Long type_id,
			String kred_id, String klass_id, Long status, String al_num,
			Integer is_tax, Double tax_rate, Long pk, String klassp_id,
			Integer ld_type, Integer status_prc, Integer acceptance,
			Long niki_id, String kred_id_cb, String use_branch, Integer ldays,
			Integer cres, Date date_close, String kod_int, String kod_fin,
			Integer faza_abr, Integer foreign_means) {
		super();
		this.branch = branch;
		this.id = id;
		this.eq_num = eq_num;
		this.eq_date = eq_date;
		this.currency = currency;
		this.amount = amount;
		this.p_num = p_num;
		this.p_date = p_date;
		this.crc_num = crc_num;
		this.crc_date = crc_date;
		this.t_author = t_author;
		this.t_date = t_date;
		this.t_type = t_type;
		this.is_inv = is_inv;
		this.currency_inv = currency_inv;
		this.amount_inv = amount_inv;
		this.ld_num = ld_num;
		this.ld_date = ld_date;
		this.cl_num = cl_num;
		this.shifr_id = shifr_id;
		this.prod_name = prod_name;
		this.sred_id = sred_id;
		this.ld_period = ld_period;
		this.target = target;
		this.ld_amount = ld_amount;
		this.date_begin = date_begin;
		this.date_end = date_end;
		this.calc_id = calc_id;
		this.date_fee = date_fee;
		this.arrears_reason = arrears_reason;
		this.term_type = term_type;
		this.type_id = type_id;
		this.kred_id = kred_id;
		this.klass_id = klass_id;
		this.status = status;
		this.al_num = al_num;
		this.is_tax = is_tax;
		this.tax_rate = tax_rate;
		this.pk = pk;
		this.klassp_id = klassp_id;
		this.ld_type = ld_type;
		this.status_prc = status_prc;
		this.acceptance = acceptance;
		this.niki_id = niki_id;
		this.kred_id_cb = kred_id_cb;
		this.use_branch = use_branch;
		this.ldays = ldays;
		this.cres = cres;
		this.date_close = date_close;
		this.kod_int = kod_int;
		this.kod_fin = kod_fin;
		this.faza_abr = faza_abr;
		this.foreign_means = foreign_means;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEq_num() {
		return eq_num;
	}

	public void setEq_num(String eq_num) {
		this.eq_num = eq_num;
	}

	public Date getEq_date() {
		return eq_date;
	}

	public void setEq_date(Date eq_date) {
		this.eq_date = eq_date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	public String getCrc_num() {
		return crc_num;
	}

	public void setCrc_num(String crc_num) {
		this.crc_num = crc_num;
	}

	public Date getCrc_date() {
		return crc_date;
	}

	public void setCrc_date(Date crc_date) {
		this.crc_date = crc_date;
	}

	public String getT_author() {
		return t_author;
	}

	public void setT_author(String t_author) {
		this.t_author = t_author;
	}

	public Date getT_date() {
		return t_date;
	}

	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}

	public Integer getT_type() {
		return t_type;
	}

	public void setT_type(Integer t_type) {
		this.t_type = t_type;
	}

	public String getIs_inv() {
		return is_inv;
	}

	public void setIs_inv(String is_inv) {
		this.is_inv = is_inv;
	}

	public String getCurrency_inv() {
		return currency_inv;
	}

	public void setCurrency_inv(String currency_inv) {
		this.currency_inv = currency_inv;
	}

	public Double getAmount_inv() {
		return amount_inv;
	}

	public void setAmount_inv(Double amount_inv) {
		this.amount_inv = amount_inv;
	}

	public String getLd_num() {
		return ld_num;
	}

	public void setLd_num(String ld_num) {
		this.ld_num = ld_num;
	}

	public Date getLd_date() {
		return ld_date;
	}

	public void setLd_date(Date ld_date) {
		this.ld_date = ld_date;
	}

	public Integer getCl_num() {
		return cl_num;
	}

	public void setCl_num(Integer cl_num) {
		this.cl_num = cl_num;
	}

	public String getShifr_id() {
		return shifr_id;
	}

	public void setShifr_id(String shifr_id) {
		this.shifr_id = shifr_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getSred_id() {
		return sred_id;
	}

	public void setSred_id(String sred_id) {
		this.sred_id = sred_id;
	}

	public Integer getLd_period() {
		return ld_period;
	}

	public void setLd_period(Integer ld_period) {
		this.ld_period = ld_period;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Double getLd_amount() {
		return ld_amount;
	}

	public void setLd_amount(Double ld_amount) {
		this.ld_amount = ld_amount;
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

	public Long getCalc_id() {
		return calc_id;
	}

	public void setCalc_id(Long calc_id) {
		this.calc_id = calc_id;
	}

	public Date getDate_fee() {
		return date_fee;
	}

	public void setDate_fee(Date date_fee) {
		this.date_fee = date_fee;
	}

	public String getArrears_reason() {
		return arrears_reason;
	}

	public void setArrears_reason(String arrears_reason) {
		this.arrears_reason = arrears_reason;
	}

	public Long getTerm_type() {
		return term_type;
	}

	public void setTerm_type(Long term_type) {
		this.term_type = term_type;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public String getKred_id() {
		return kred_id;
	}

	public void setKred_id(String kred_id) {
		this.kred_id = kred_id;
	}

	public String getKlass_id() {
		return klass_id;
	}

	public void setKlass_id(String klass_id) {
		this.klass_id = klass_id;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getAl_num() {
		return al_num;
	}

	public void setAl_num(String al_num) {
		this.al_num = al_num;
	}

	public Integer getIs_tax() {
		return is_tax;
	}

	public void setIs_tax(Integer is_tax) {
		this.is_tax = is_tax;
	}

	public Double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(Double tax_rate) {
		this.tax_rate = tax_rate;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getKlassp_id() {
		return klassp_id;
	}

	public void setKlassp_id(String klassp_id) {
		this.klassp_id = klassp_id;
	}

	public Integer getLd_type() {
		return ld_type;
	}

	public void setLd_type(Integer ld_type) {
		this.ld_type = ld_type;
	}

	public Integer getStatus_prc() {
		return status_prc;
	}

	public void setStatus_prc(Integer status_prc) {
		this.status_prc = status_prc;
	}

	public Integer getAcceptance() {
		return acceptance;
	}

	public void setAcceptance(Integer acceptance) {
		this.acceptance = acceptance;
	}

	public Long getNiki_id() {
		return niki_id;
	}

	public void setNiki_id(Long niki_id) {
		this.niki_id = niki_id;
	}

	public String getKred_id_cb() {
		return kred_id_cb;
	}

	public void setKred_id_cb(String kred_id_cb) {
		this.kred_id_cb = kred_id_cb;
	}

	public String getUse_branch() {
		return use_branch;
	}

	public void setUse_branch(String use_branch) {
		this.use_branch = use_branch;
	}

	public Integer getLdays() {
		return ldays;
	}

	public void setLdays(Integer ldays) {
		this.ldays = ldays;
	}

	public Integer getCres() {
		return cres;
	}

	public void setCres(Integer cres) {
		this.cres = cres;
	}

	public Date getDate_close() {
		return date_close;
	}

	public void setDate_close(Date date_close) {
		this.date_close = date_close;
	}

	public String getKod_int() {
		return kod_int;
	}

	public void setKod_int(String kod_int) {
		this.kod_int = kod_int;
	}

	public String getKod_fin() {
		return kod_fin;
	}

	public void setKod_fin(String kod_fin) {
		this.kod_fin = kod_fin;
	}

	public Integer getFaza_abr() {
		return faza_abr;
	}

	public void setFaza_abr(Integer faza_abr) {
		this.faza_abr = faza_abr;
	}

	public Integer getForeign_means() {
		return foreign_means;
	}

	public void setForeign_means(Integer foreign_means) {
		this.foreign_means = foreign_means;
	}
    
}
