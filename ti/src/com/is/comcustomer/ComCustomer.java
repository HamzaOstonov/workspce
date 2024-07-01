package com.is.comcustomer;

import java.util.Date;

public class ComCustomer {
    private int id;
    private String branch;
    private String account;
    private String name;
    private String inn;
    private String region;
    private String distr;
    private String agreement_number;
    private Date agreement_date;
    private String purpose_template;
    private String budget_inn;
    private String budget_account;
    private int is_budget_org;
    private String purpose_code;
    private int category_id;
    private String provider_class;
    private String provider_url;

    public ComCustomer() {

    }

	public ComCustomer(int id, String branch, String account, String name,
			String inn, String region, String distr, String agreement_number,
			Date agreement_date, String purpose_template, String budget_inn,
			String budget_account, int is_budget_org, String purpose_code,
			int category_id, String provider_class, String provider_url) {
		super();
		this.id = id;
		this.branch = branch;
		this.account = account;
		this.name = name;
		this.inn = inn;
		this.region = region;
		this.distr = distr;
		this.agreement_number = agreement_number;
		this.agreement_date = agreement_date;
		this.purpose_template = purpose_template;
		this.budget_inn = budget_inn;
		this.budget_account = budget_account;
		this.is_budget_org = is_budget_org;
		this.purpose_code = purpose_code;
		this.category_id = category_id;
		this.provider_class = provider_class;
		this.provider_url = provider_url;
	}
	
	public ComCustomer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDistr() {
		return distr;
	}

	public void setDistr(String distr) {
		this.distr = distr;
	}

	public String getAgreement_number() {
		return agreement_number;
	}

	public void setAgreement_number(String agreement_number) {
		this.agreement_number = agreement_number;
	}

	public Date getAgreement_date() {
		return agreement_date;
	}

	public void setAgreement_date(Date agreement_date) {
		this.agreement_date = agreement_date;
	}

	public String getPurpose_template() {
		return purpose_template;
	}

	public void setPurpose_template(String purpose_template) {
		this.purpose_template = purpose_template;
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

	public int getIs_budget_org() {
		return is_budget_org;
	}

	public void setIs_budget_org(int is_budget_org) {
		this.is_budget_org = is_budget_org;
	}

	public String getPurpose_code() {
		return purpose_code;
	}

	public void setPurpose_code(String purpose_code) {
		this.purpose_code = purpose_code;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getProvider_class() {
		return provider_class;
	}

	public void setProvider_class(String provider_class) {
		this.provider_class = provider_class;
	}

	public String getProvider_url() {
		return provider_url;
	}

	public void setProvider_url(String provider_url) {
		this.provider_url = provider_url;
	}

	
}
