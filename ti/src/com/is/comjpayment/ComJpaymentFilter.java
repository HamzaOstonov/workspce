package com.is.comjpayment;

import java.util.Date;

public class ComJpaymentFilter {
    private int id;
    private int services_list_id;
    private String p_name;
    private String p_number;
    private Date from_date;
    private Date to_date;
    private String from_value;
    private String to_value;
    private String difference;
    private int penalty_amount;
    private int amount;
    private int full_amount;
    private String currency;
    private int provider_amount;
    private int fee_amount;
    private int customer_id;
    private int deal_id;
    private int state;
    private int customerj_id;
    private int document_id;
    private int transaction_id;
    private int branch_id;
    private int subbranch_id;
    private Date date_complete;
    private int operation_id;
    private int parent_id;
    private int parent_group_id;
    private int payment_type_id;
    private int prt_id;
    private String client_address;
    private int provider_discount_amount;
    private String budget_inn;
    private String budget_account;

    public ComJpaymentFilter() {
    	super();
    }

	public ComJpaymentFilter(int id, int services_list_id, String p_name,
			String p_number, Date from_date, Date to_date, String from_value,
			String to_value, String difference, int penalty_amount, int amount,
			int full_amount, String currency, int provider_amount,
			int fee_amount, int customer_id, int deal_id, int state,
			int customerj_id, int document_id, int transaction_id,
			int branch_id, int subbranch_id, Date date_complete,
			int operation_id, int parent_id, int parent_group_id,
			int payment_type_id, int prt_id, String client_address,
			int provider_discount_amount, String budget_inn,
			String budget_account) {
		super();
		this.id = id;
		this.services_list_id = services_list_id;
		this.p_name = p_name;
		this.p_number = p_number;
		this.from_date = from_date;
		this.to_date = to_date;
		this.from_value = from_value;
		this.to_value = to_value;
		this.difference = difference;
		this.penalty_amount = penalty_amount;
		this.amount = amount;
		this.full_amount = full_amount;
		this.currency = currency;
		this.provider_amount = provider_amount;
		this.fee_amount = fee_amount;
		this.customer_id = customer_id;
		this.deal_id = deal_id;
		this.state = state;
		this.customerj_id = customerj_id;
		this.document_id = document_id;
		this.transaction_id = transaction_id;
		this.branch_id = branch_id;
		this.subbranch_id = subbranch_id;
		this.date_complete = date_complete;
		this.operation_id = operation_id;
		this.parent_id = parent_id;
		this.parent_group_id = parent_group_id;
		this.payment_type_id = payment_type_id;
		this.prt_id = prt_id;
		this.client_address = client_address;
		this.provider_discount_amount = provider_discount_amount;
		this.budget_inn = budget_inn;
		this.budget_account = budget_account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getServices_list_id() {
		return services_list_id;
	}

	public void setServices_list_id(int services_list_id) {
		this.services_list_id = services_list_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_number() {
		return p_number;
	}

	public void setP_number(String p_number) {
		this.p_number = p_number;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	public String getFrom_value() {
		return from_value;
	}

	public void setFrom_value(String from_value) {
		this.from_value = from_value;
	}

	public String getTo_value() {
		return to_value;
	}

	public void setTo_value(String to_value) {
		this.to_value = to_value;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	public int getPenalty_amount() {
		return penalty_amount;
	}

	public void setPenalty_amount(int penalty_amount) {
		this.penalty_amount = penalty_amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getFull_amount() {
		return full_amount;
	}

	public void setFull_amount(int full_amount) {
		this.full_amount = full_amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getProvider_amount() {
		return provider_amount;
	}

	public void setProvider_amount(int provider_amount) {
		this.provider_amount = provider_amount;
	}

	public int getFee_amount() {
		return fee_amount;
	}

	public void setFee_amount(int fee_amount) {
		this.fee_amount = fee_amount;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCustomerj_id() {
		return customerj_id;
	}

	public void setCustomerj_id(int customerj_id) {
		this.customerj_id = customerj_id;
	}

	public int getDocument_id() {
		return document_id;
	}

	public void setDocument_id(int document_id) {
		this.document_id = document_id;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getSubbranch_id() {
		return subbranch_id;
	}

	public void setSubbranch_id(int subbranch_id) {
		this.subbranch_id = subbranch_id;
	}

	public Date getDate_complete() {
		return date_complete;
	}

	public void setDate_complete(Date date_complete) {
		this.date_complete = date_complete;
	}

	public int getOperation_id() {
		return operation_id;
	}

	public void setOperation_id(int operation_id) {
		this.operation_id = operation_id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public int getParent_group_id() {
		return parent_group_id;
	}

	public void setParent_group_id(int parent_group_id) {
		this.parent_group_id = parent_group_id;
	}

	public int getPayment_type_id() {
		return payment_type_id;
	}

	public void setPayment_type_id(int payment_type_id) {
		this.payment_type_id = payment_type_id;
	}

	public int getPrt_id() {
		return prt_id;
	}

	public void setPrt_id(int prt_id) {
		this.prt_id = prt_id;
	}

	public String getClient_address() {
		return client_address;
	}

	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}

	public int getProvider_discount_amount() {
		return provider_discount_amount;
	}

	public void setProvider_discount_amount(int provider_discount_amount) {
		this.provider_discount_amount = provider_discount_amount;
	}

	public String getBudget_inn() {
		return budget_inn;
	}

	public void setBudget_inn(String budget_inn) {
		this.budget_inn = budget_inn;
	}

	public String getBudget_account() {
		return budget_account;
	}

	public void setBudget_account(String budget_account) {
		this.budget_account = budget_account;
	}
}
