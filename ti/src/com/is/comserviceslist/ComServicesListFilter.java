package com.is.comserviceslist;

public class ComServicesListFilter {
    private int id;
    private int customerj_id;
    private int state;
    private String name;
    private int p_name_mask;
    private int p_number_mask;
    private int from_date_mask;
    private int to_date_mask;
    private int from_value_mask;
    private int to_value_mask;
    private int difference_mask;
    private int penalty_amount_mask;
    private int operation_id;
    private int deal_id;
    private int parent_id;
    private int parent_group_id;
    private int client_address_mask;
    private int pay_cat_id;

    public ComServicesListFilter() {

    }

	public ComServicesListFilter(int id, int customerj_id, int state, String name,
			int p_name_mask, int p_number_mask, int from_date_mask,
			int to_date_mask, int from_value_mask, int to_value_mask,
			int difference_mask, int penalty_amount_mask, int operation_id,
			int deal_id, int parent_id, int parent_group_id,
			int client_address_mask, int pay_cat_id) {
		super();
		this.id = id;
		this.customerj_id = customerj_id;
		this.state = state;
		this.name = name;
		this.p_name_mask = p_name_mask;
		this.p_number_mask = p_number_mask;
		this.from_date_mask = from_date_mask;
		this.to_date_mask = to_date_mask;
		this.from_value_mask = from_value_mask;
		this.to_value_mask = to_value_mask;
		this.difference_mask = difference_mask;
		this.penalty_amount_mask = penalty_amount_mask;
		this.operation_id = operation_id;
		this.deal_id = deal_id;
		this.parent_id = parent_id;
		this.parent_group_id = parent_group_id;
		this.client_address_mask = client_address_mask;
		this.pay_cat_id = pay_cat_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerj_id() {
		return customerj_id;
	}

	public void setCustomerj_id(int customerj_id) {
		this.customerj_id = customerj_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getP_name_mask() {
		return p_name_mask;
	}

	public void setP_name_mask(int p_name_mask) {
		this.p_name_mask = p_name_mask;
	}

	public int getP_number_mask() {
		return p_number_mask;
	}

	public void setP_number_mask(int p_number_mask) {
		this.p_number_mask = p_number_mask;
	}

	public int getFrom_date_mask() {
		return from_date_mask;
	}

	public void setFrom_date_mask(int from_date_mask) {
		this.from_date_mask = from_date_mask;
	}

	public int getTo_date_mask() {
		return to_date_mask;
	}

	public void setTo_date_mask(int to_date_mask) {
		this.to_date_mask = to_date_mask;
	}

	public int getFrom_value_mask() {
		return from_value_mask;
	}

	public void setFrom_value_mask(int from_value_mask) {
		this.from_value_mask = from_value_mask;
	}

	public int getTo_value_mask() {
		return to_value_mask;
	}

	public void setTo_value_mask(int to_value_mask) {
		this.to_value_mask = to_value_mask;
	}

	public int getDifference_mask() {
		return difference_mask;
	}

	public void setDifference_mask(int difference_mask) {
		this.difference_mask = difference_mask;
	}

	public int getPenalty_amount_mask() {
		return penalty_amount_mask;
	}

	public void setPenalty_amount_mask(int penalty_amount_mask) {
		this.penalty_amount_mask = penalty_amount_mask;
	}

	public int getOperation_id() {
		return operation_id;
	}

	public void setOperation_id(int operation_id) {
		this.operation_id = operation_id;
	}

	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
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

	public int getClient_address_mask() {
		return client_address_mask;
	}

	public void setClient_address_mask(int client_address_mask) {
		this.client_address_mask = client_address_mask;
	}

	public int getPay_cat_id() {
		return pay_cat_id;
	}

	public void setPay_cat_id(int pay_cat_id) {
		this.pay_cat_id = pay_cat_id;
	}
}
