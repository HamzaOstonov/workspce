package com.is.uzcard.model;

import java.util.Date;

public class Card {

	private String branch;
	private String company_name;
	private String card_number;
	private int is_primary;
	private String card_type;
	private String def_atm_account;
	private String def_pos_account;
	private String embossed_ch_name;
	private String expiration_date;
	private String card_status;
	private String contract_id;
	private Date date_open;
	private Date date_close;
	private String hot_card_status;

	public Card() {

	}

	public Card(String branch, String company_name, String card_number, int is_primary, String card_type,
			String def_atm_account, String def_pos_account, String embossed_ch_name, String expiration_date,
			String card_status, String contract_id, Date date_open, Date date_close, String hot_card_status) {

		this.branch = branch;
		this.company_name = company_name;
		this.card_number = card_number;
		this.is_primary = is_primary;
		this.card_type = card_type;
		this.def_atm_account = def_atm_account;
		this.def_pos_account = def_pos_account;
		this.embossed_ch_name = embossed_ch_name;
		this.expiration_date = expiration_date;
		this.card_status = card_status;
		this.contract_id = contract_id;
		this.date_open = date_open;
		this.date_close = date_close;
		this.hot_card_status = hot_card_status;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public int getIs_primary() {
		return is_primary;
	}

	public void setIs_primary(int is_primary) {
		this.is_primary = is_primary;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getDef_atm_account() {
		return def_atm_account;
	}

	public void setDef_atm_account(String def_atm_account) {
		this.def_atm_account = def_atm_account;
	}

	public String getDef_pos_account() {
		return def_pos_account;
	}

	public void setDef_pos_account(String def_pos_account) {
		this.def_pos_account = def_pos_account;
	}

	public String getEmbossed_ch_name() {
		return embossed_ch_name;
	}

	public void setEmbossed_ch_name(String embossed_ch_name) {
		this.embossed_ch_name = embossed_ch_name;
	}

	public String getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}

	public String getCard_status() {
		return card_status;
	}

	public void setCard_status(String card_status) {
		this.card_status = card_status;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public Date getDate_open() {
		return date_open;
	}

	public void setDate_open(Date date_open) {
		this.date_open = date_open;
	}

	public Date getDate_close() {
		return date_close;
	}

	public void setDate_close(Date date_close) {
		this.date_close = date_close;
	}

	public String getHot_card_status() {
		return hot_card_status;
	}

	public void setHot_card_status(String hot_card_status) {
		this.hot_card_status = hot_card_status;
	}

}