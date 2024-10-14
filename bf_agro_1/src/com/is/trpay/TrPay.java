package com.is.trpay;

import java.math.BigDecimal;
import java.util.Date;

public class TrPay {
    private Long id;
    private String branch;
    private int operation_id;
    private BigDecimal amount;
    private String card_acc;
    private String cur_acc;
    private Date date_created;
    private int parent_group_id;
    private int state;
    private String account_no;
    private String cl_name;
    private int emp_id;
    private String pan;
    private int deal_id;
    private String doc_num;
    private long eqv_amount; 
    private String operation;

    public TrPay() {
    	super();
    }

	public TrPay(Long id, String branch, int operation_id, BigDecimal amount,
			String card_acc, String cur_acc, Date date_created,
			int parent_group_id, int state, String account_no, String cl_name, int deal_id) {
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
		this.deal_id = deal_id;
	}
	
	
	/*public TrPay(Long id, String branch, int operation_id, int amount,
			String card_acc, String cur_acc, Date date_created,
			int parent_group_id, int state, String account_no, String cl_name, int deal_id, String operation) {
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
		this.deal_id = deal_id;
		this.operation = operation;
	}*/
	
	



	public TrPay(Long id, String branch, int operation_id, BigDecimal amount,
			String card_acc, String cur_acc, Date date_created,
			int parent_group_id, int state, String account_no, String cl_name,
			int emp_id, String pan, int deal_id) {
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
		this.emp_id = emp_id;
		this.pan = pan;
		this.deal_id = deal_id;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}

	public String getDoc_num() {
		return doc_num;
	}

	public void setDoc_num(String doc_num) {
		this.doc_num = doc_num;
	}

	public long getEqv_amount() {
		return eqv_amount;
	}

	public void setEqv_amount(long eqv_amount) {
		this.eqv_amount = eqv_amount;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
	
    
    
}
