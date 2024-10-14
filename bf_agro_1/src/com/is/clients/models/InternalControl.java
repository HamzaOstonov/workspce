package com.is.clients.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class InternalControl {
	@Getter @Setter private String branch;
    @Getter @Setter private long id;
    @Getter @Setter private String client_id;
    @Getter @Setter private String reg_organization_inform;
    @Getter @Setter private String phone;
    @Getter @Setter private int emp_account_open;
    @Getter @Setter private int emp_account_confirm;
    @Getter @Setter private int risk_degree;
    @Getter @Setter private String risk_degree_detail;
    @Getter @Setter private int is_landlord;
    @Getter @Setter private String landlord;
    @Getter @Setter private String landlord_conract;
    @Getter @Setter private Date date_open;
    @Getter @Setter private Date date_change;
    @Getter @Setter private Date date_last_save;
    @Getter @Setter private int state;
    @Getter @Setter private String account_open;
    @Getter @Setter private String capital_inform;
    @Getter @Setter private String som_opers;
    @Getter @Setter private String pod_opers;
    @Getter @Setter private String add_data;
    @Getter @Setter private String svidet;
    @Getter @Setter private String soft;
    @Getter @Setter private Date date_soft_beg;
    @Getter @Setter private Date date_soft_end;
    @Getter @Setter private String capital_inform_reg;
    @Getter @Setter private Date risk_date;
    @Getter @Setter private String capital_currency;
    @Getter @Setter private int is_wrong_address;
    @Getter @Setter private String nal_letter_num;
    @Getter @Setter private Date nal_letter_date;
    @Getter @Setter private int number_worker;
    @Getter @Setter private String soft_m;
    @Getter @Setter private Date date_soft_m_beg;
    @Getter @Setter private Date date_soft_m_end;
    
    public InternalControl() {
		// TODO Auto-generated constructor stub
	}
    
    public InternalControl(long id) {
    	this.id = id;
	}
}
