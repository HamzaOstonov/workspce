package com.is.uzcard.model;

import java.util.Date;

public class BTRTFilter {
	private Double app_id;
	private String app_tag;
	private String app_type;
	private Double rec_number;
	private String contract_id;
	private String emp_id;
	private String customer_id;
	private String vip_flag;
	private String customer_desc;
	private String inn;
	private String okpo;
	private String person_id;
	private String first_name;
	private String second_name;
	private String surname;
	private Date birth_date;
	private int p_proc_mode;
	private String company_name;
	private String security_id;
	private String sex;
	private String residence;
	private String p_id_type;
	private String p_id_number;
	private String p_id_series;
	private String p_id_authority;
	private Date p_id_issue_date;
	private String address_id;
	private String address_type;
	private int a_proc_mode;
	private String address_line1;
	private String address_line2;
	private String region;
	private String country;
	private String primary_phone;
	private String mobile_phone;
	private String email;
	private String card_number;
	private String card_type;
	private String embossed_ch_name;
	private String def_atm_account;
	private String def_pos_account;
	private int is_primary;
	private Date expiration_date;
	private String company_name_card;
	private String account_number;
	private String account_type;
	private String currency;
	private String edtWorkId;

	public BTRTFilter(Double app_id, String app_tag, String app_type,
			Double rec_number, String contract_id, String emp_id,
			String customer_id, String vip_flag, String customer_desc,
			String inn, String okpo, String person_id, String first_name,
			String second_name, String surname, Date birth_date,
			int p_proc_mode, String company_name, String security_id,
			String sex, String residence, String p_id_type, String p_id_number,
			String p_id_series, String p_id_authority, Date p_id_issue_date,
			String address_id, String address_type, int a_proc_mode,
			String address_line1, String address_line2, String region,
			String country, String primary_phone, String mobile_phone,
			String email, String card_number, String card_type,
			String embossed_ch_name, String def_atm_account,
			String def_pos_account, int is_primary, Date expiration_date,
			String company_name_card, String account_number,
			String account_type, String currency, String edtWorkId) {
		super();
		this.app_id = app_id;
		this.app_tag = app_tag;
		this.app_type = app_type;
		this.rec_number = rec_number;
		this.contract_id = contract_id;
		this.emp_id = emp_id;
		this.customer_id = customer_id;
		this.vip_flag = vip_flag;
		this.customer_desc = customer_desc;
		this.inn = inn;
		this.okpo = okpo;
		this.person_id = person_id;
		this.first_name = first_name;
		this.second_name = second_name;
		this.surname = surname;
		this.birth_date = birth_date;
		this.p_proc_mode = p_proc_mode;
		this.company_name = company_name;
		this.security_id = security_id;
		this.sex = sex;
		this.residence = residence;
		this.p_id_type = p_id_type;
		this.p_id_number = p_id_number;
		this.p_id_series = p_id_series;
		this.p_id_authority = p_id_authority;
		this.p_id_issue_date = p_id_issue_date;
		this.address_id = address_id;
		this.address_type = address_type;
		this.a_proc_mode = a_proc_mode;
		this.address_line1 = address_line1;
		this.address_line2 = address_line2;
		this.region = region;
		this.country = country;
		this.primary_phone = primary_phone;
		this.mobile_phone = mobile_phone;
		this.email = email;
		this.card_number = card_number;
		this.card_type = card_type;
		this.embossed_ch_name = embossed_ch_name;
		this.def_atm_account = def_atm_account;
		this.def_pos_account = def_pos_account;
		this.is_primary = is_primary;
		this.expiration_date = expiration_date;
		this.company_name_card = company_name_card;
		this.account_number = account_number;
		this.account_type = account_type;
		this.currency = currency;
		this.edtWorkId = edtWorkId;
	}

	public BTRTFilter() {

	}

	public BTRTFilter(Double app_id, String app_tag, String app_type,
			Double rec_number, String contract_id, String emp_id,
			String customer_id, String vip_flag, String customer_desc,
			String inn, String okpo, String person_id, String first_name,
			String second_name, String surname, Date birth_date,
			int p_proc_mode, String company_name, String security_id,
			String sex, String residence, String p_id_type, String p_id_number,
			String p_id_series, String p_id_authority, Date p_id_issue_date,
			String address_id, String address_type, int a_proc_mode,
			String address_line1, String address_line2, String region,
			String country, String primary_phone, String mobile_phone,
			String email, String card_number, String card_type,
			String embossed_ch_name, String def_atm_account,
			String def_pos_account, int is_primary, Date expiration_date,
			String company_name_card, String account_number,
			String account_type, String currency) {

		this.app_id = app_id;
		this.app_tag = app_tag;
		this.app_type = app_type;
		this.rec_number = rec_number;
		this.contract_id = contract_id;
		this.emp_id = emp_id;
		this.customer_id = customer_id;
		this.vip_flag = vip_flag;
		this.customer_desc = customer_desc;
		this.inn = inn;
		this.okpo = okpo;
		this.person_id = person_id;
		this.first_name = first_name;
		this.second_name = second_name;
		this.surname = surname;
		this.birth_date = birth_date;
		this.p_proc_mode = p_proc_mode;
		this.company_name = company_name;
		this.security_id = security_id;
		this.sex = sex;
		this.residence = residence;
		this.p_id_type = p_id_type;
		this.p_id_number = p_id_number;
		this.p_id_series = p_id_series;
		this.p_id_authority = p_id_authority;
		this.p_id_issue_date = p_id_issue_date;
		this.address_id = address_id;
		this.address_type = address_type;
		this.a_proc_mode = a_proc_mode;
		this.address_line1 = address_line1;
		this.address_line2 = address_line2;
		this.region = region;
		this.country = country;
		this.primary_phone = primary_phone;
		this.mobile_phone = mobile_phone;
		this.email = email;
		this.card_number = card_number;
		this.card_type = card_type;
		this.embossed_ch_name = embossed_ch_name;
		this.def_atm_account = def_atm_account;
		this.def_pos_account = def_pos_account;
		this.is_primary = is_primary;
		this.expiration_date = expiration_date;
		this.company_name_card = company_name_card;
		this.account_number = account_number;
		this.account_type = account_type;
		this.currency = currency;
	}

	public int getA_proc_mode() {
		return a_proc_mode;
	}

	public String getAccount_number() {
		return account_number;
	}

	public String getAccount_type() {
		return account_type;
	}

	public String getAddress_id() {
		return address_id;
	}

	public String getAddress_line1() {
		return address_line1;
	}

	public String getAddress_line2() {
		return address_line2;
	}

	public String getAddress_type() {
		return address_type;
	}

	public Double getApp_id() {
		return app_id;
	}

	public String getApp_tag() {
		return app_tag;
	}

	public String getApp_type() {
		return app_type;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public String getCard_number() {
		return card_number;
	}

	public String getCard_type() {
		return card_type;
	}

	public String getCompany_name() {
		return company_name;
	}

	public String getCompany_name_card() {
		return company_name_card;
	}

	public String getContract_id() {
		return contract_id;
	}

	public String getCountry() {
		return country;
	}

	public String getCurrency() {
		return currency;
	}

	public String getCustomer_desc() {
		return customer_desc;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public String getDef_atm_account() {
		return def_atm_account;
	}

	public String getDef_pos_account() {
		return def_pos_account;
	}

	public String getEmail() {
		return email;
	}

	public String getEmbossed_ch_name() {
		return embossed_ch_name;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public Date getExpiration_date() {
		return expiration_date;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getInn() {
		return inn;
	}

	public int getIs_primary() {
		return is_primary;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public String getOkpo() {
		return okpo;
	}

	public String getP_id_authority() {
		return p_id_authority;
	}

	public Date getP_id_issue_date() {
		return p_id_issue_date;
	}

	public String getP_id_number() {
		return p_id_number;
	}

	public String getP_id_series() {
		return p_id_series;
	}

	public String getP_id_type() {
		return p_id_type;
	}

	public int getP_proc_mode() {
		return p_proc_mode;
	}

	public String getPerson_id() {
		return person_id;
	}

	public String getPrimary_phone() {
		return primary_phone;
	}

	public Double getRec_number() {
		return rec_number;
	}

	public String getRegion() {
		return region;
	}

	public String getResidence() {
		return residence;
	}

	public String getSecond_name() {
		return second_name;
	}

	public String getSecurity_id() {
		return security_id;
	}

	public String getSex() {
		return sex;
	}

	public String getSurname() {
		return surname;
	}

	public String getVip_flag() {
		return vip_flag;
	}

	public void setA_proc_mode(int a_proc_mode) {
		this.a_proc_mode = a_proc_mode;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}

	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}

	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}

	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}

	public void setApp_id(Double app_id) {
		this.app_id = app_id;
	}

	public void setApp_tag(String app_tag) {
		this.app_tag = app_tag;
	}

	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public void setCompany_name_card(String company_name_card) {
		this.company_name_card = company_name_card;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setCustomer_desc(String customer_desc) {
		this.customer_desc = customer_desc;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public void setDef_atm_account(String def_atm_account) {
		this.def_atm_account = def_atm_account;
	}

	public void setDef_pos_account(String def_pos_account) {
		this.def_pos_account = def_pos_account;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmbossed_ch_name(String embossed_ch_name) {
		this.embossed_ch_name = embossed_ch_name;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public void setIs_primary(int is_primary) {
		this.is_primary = is_primary;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}

	public void setP_id_authority(String p_id_authority) {
		this.p_id_authority = p_id_authority;
	}

	public void setP_id_issue_date(Date p_id_issue_date) {
		this.p_id_issue_date = p_id_issue_date;
	}

	public void setP_id_number(String p_id_number) {
		this.p_id_number = p_id_number;
	}

	public void setP_id_series(String p_id_series) {
		this.p_id_series = p_id_series;
	}

	public void setP_id_type(String p_id_type) {
		this.p_id_type = p_id_type;
	}

	public void setP_proc_mode(int p_proc_mode) {
		this.p_proc_mode = p_proc_mode;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public void setPrimary_phone(String primary_phone) {
		this.primary_phone = primary_phone;
	}

	public void setRec_number(Double rec_number) {
		this.rec_number = rec_number;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public void setSecurity_id(String security_id) {
		this.security_id = security_id;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setVip_flag(String vip_flag) {
		this.vip_flag = vip_flag;
	}

	public void setEdtWorkId(String edtWorkId) {
		this.edtWorkId = edtWorkId;
	}

	public String getEdtWorkId() {
		return edtWorkId;
	}
}
