package com.is.tieto_capital.cardApproval;

import java.io.Serializable;
import java.sql.Date;

public class CardApproval implements Serializable {

    static final long serialVersionUID = 103844514947365244L;

    private Long id;
    private String card_type;
    private int approval_type_id;
    private String user_id;
    private String branch;
    private int state_id;
    private Date v_date;
    private String t_user_id;
    private String new_card_account;
    private String holder_name;
    
	public CardApproval() {
		super();
	}

	public CardApproval(Long id, String card_type, int approval_type_id, String user_id, String branch, int state_id,
			Date v_date, String t_user_id, String new_card_account, String holder_name) {
		super();
		this.id = id;
		this.card_type = card_type;
		this.approval_type_id = approval_type_id;
		this.user_id = user_id;
		this.branch = branch;
		this.state_id = state_id;
		this.v_date = v_date;
		this.t_user_id = t_user_id;
		this.new_card_account = new_card_account;
		this.holder_name = holder_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public int getApproval_type_id() {
		return approval_type_id;
	}

	public void setApproval_type_id(int approval_type_id) {
		this.approval_type_id = approval_type_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	public String getT_user_id() {
		return t_user_id;
	}

	public void setT_user_id(String t_user_id) {
		this.t_user_id = t_user_id;
	}

	public String getNew_card_account() {
		return new_card_account;
	}

	public void setNew_card_account(String new_card_account) {
		this.new_card_account = new_card_account;
	}

	public String getHolder_name() {
		return holder_name;
	}

	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}   
   
}