package com.is.uzcard.model;

import java.util.Date;

public class BTRT03 extends BTRT {
	private int state;
	private int dealID;
	private String state_name;
	public BTRT03() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BTRT03(Double app_id, String app_tag, String app_type,
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
			String account_type, String currency, String edtWorkId,
			String stEmpId) {
		super(app_id, app_tag, app_type, rec_number, contract_id, emp_id,
				customer_id, vip_flag, customer_desc, inn, okpo, person_id,
				first_name, second_name, surname, birth_date, p_proc_mode,
				company_name, security_id, sex, residence, p_id_type,
				p_id_number, p_id_series, p_id_authority, p_id_issue_date,
				address_id, address_type, a_proc_mode, address_line1,
				address_line2, region, country, primary_phone, mobile_phone,
				email, card_number, card_type, embossed_ch_name,
				def_atm_account, def_pos_account, is_primary, expiration_date,
				company_name_card, account_number, account_type, currency,
				edtWorkId, stEmpId);
		// TODO Auto-generated constructor stub
	}

	public BTRT03(int state, int dealID, String state_name, String customerId) {
		super();
		this.state = state;
		this.dealID = dealID;
		this.state_name = state_name;
		this.client = customerId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getDealID() {
		return dealID;
	}

	public void setDealID(int dealID) {
		this.dealID = dealID;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

}
