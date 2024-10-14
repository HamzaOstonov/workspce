package com.is.trpay;

import java.util.Date;

public class TrPayFilter {
    private Long id;
    private String branch;
    private int operation_id;
    private int amount;
    private String card_acc;
    private String cur_acc;
    private Date date_created;
    private int parent_group_id;
    private int state;
    private int deal_id;
    private String account_no;
    private String cl_name;

    public TrPayFilter() {
    	super();
    }

	public TrPayFilter(Long id, String branch, int operation_id, int amount,
			String card_acc, String cur_acc, Date date_created,
			int parent_group_id, int state, String account_no, String cl_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.operation_id = operation_id;
		this.amount = amount;
		this.card_acc = card_acc;
		this.cur_acc = cur_acc;
		this.date_created = date_created;
		this.parent_group_id = parent_group_id;
		this.state = state;
		this.account_no = account_no;
		this.cl_name = cl_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getOperation_id() {
		return operation_id;
	}

	public void setOperation_id(int operation_id) {
		this.operation_id = operation_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCard_acc() {
		return card_acc;
	}

	public void setCard_acc(String card_acc) {
		this.card_acc = card_acc;
	}

	public String getCur_acc() {
		return cur_acc;
	}

	public void setCur_acc(String cur_acc) {
		this.cur_acc = cur_acc;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public int getParent_group_id() {
		return parent_group_id;
	}

	public void setParent_group_id(int parent_group_id) {
		this.parent_group_id = parent_group_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getCl_name() {
		return cl_name;
	}

	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}

	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}
	
	
}
