package com.is.tieto_capital.accApproval;

import java.io.Serializable;
import java.sql.Date;

public class AccApproval implements Serializable {

    static final long serialVersionUID = 103844514947365244L;

    private Long id;
    private String account;
    private String branch;
    private Long state_id;
    private String fullname;
    private Date birthday;
    private String passport_serial;
    private String passport_number;
    private String bank_id;
    private String tieto_id;
    private String deal_group_id;
    private String deal_id;
    private String action_id;
    private Long approval_type_id;
    
	public AccApproval() {
		super();
	}

	public AccApproval(Long id, String account, String branch, Long state_id, String fullname, Date birthday,
			String passport_serial, String passport_number, String bank_id, String tieto_id, String deal_group_id,
			String deal_id, String action_id, Long approval_type_id) {
		super();
		this.id = id;
		this.account = account;
		this.branch = branch;
		this.state_id = state_id;
		this.fullname = fullname;
		this.birthday = birthday;
		this.passport_serial = passport_serial;
		this.passport_number = passport_number;
		this.bank_id = bank_id;
		this.tieto_id = tieto_id;
		this.deal_group_id = deal_group_id;
		this.deal_id = deal_id;
		this.action_id = action_id;
		this.approval_type_id = approval_type_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getState_id() {
		return state_id;
	}

	public void setState_id(Long state_id) {
		this.state_id = state_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPassport_serial() {
		return passport_serial;
	}

	public void setPassport_serial(String passport_serial) {
		this.passport_serial = passport_serial;
	}

	public String getPassport_number() {
		return passport_number;
	}

	public void setPassport_number(String passport_number) {
		this.passport_number = passport_number;
	}

	public String getBank_id() {
		return bank_id;
	}

	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}

	public String getTieto_id() {
		return tieto_id;
	}

	public void setTieto_id(String tieto_id) {
		this.tieto_id = tieto_id;
	}

	public String getDeal_group_id() {
		return deal_group_id;
	}

	public void setDeal_group_id(String deal_group_id) {
		this.deal_group_id = deal_group_id;
	}

	public String getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}

	public String getAction_id() {
		return action_id;
	}

	public void setAction_id(String action_id) {
		this.action_id = action_id;
	}

	public Long getApproval_type_id() {
		return approval_type_id;
	}

	public void setApproval_type_id(Long approval_type_id) {
		this.approval_type_id = approval_type_id;
	}
    
    

}

