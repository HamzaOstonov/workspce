package com.is.providers;
import java.util.Date;

public class Payment {
	private long id;
	private long tr_id;
	private long munis_id;
	private String branch;
	private String terminal_id;
	private int provider_id;
	private int service_id;
	private String p_name;
	private String p_number;
	private Date from_date;
	private Date to_date;
	private String from_value;
	private String to_value;
	private long amount;
	private String currency;
	private Date time_stamp;
	private int state;
	private String district;
	private int operation_id;
	private int user_id;
	private String address;
	private String subdivision;
	
	private String acc_name;
	private String free_branch;
	private String purpose_add;
	private String pay_details;
	private String budget_inn;
    private String budget_account;

	public Payment() {
		super();
	}

	public Payment(long id, long tr_id, String branch, String terminal_id,
			int provider_id, int service_id, String p_name, String p_number,
			Date from_date, Date to_date, String from_value, String to_value,
			long amount, String currency, Date time_stamp, int state, int user_id,
			int operation_id, String address, String subdivision) {
		super();
		this.id = id;
		this.tr_id = tr_id;
		this.branch = branch;
		this.terminal_id = terminal_id;
		this.provider_id = provider_id;
		this.service_id = service_id;
		this.p_name = p_name;
		this.p_number = p_number;
		this.from_date = from_date;
		this.to_date = to_date;
		this.from_value = from_value;
		this.to_value = to_value;
		this.amount = amount;
		this.currency = currency;
		this.time_stamp = time_stamp;
		this.state = state;
		this.user_id = user_id;
		this.operation_id = operation_id;
		this.address = address;
		this.subdivision = subdivision;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTr_id() {
		return tr_id;
	}

	public void setTr_id(long tr_id) {
		this.tr_id = tr_id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public int getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(int provider_id) {
		this.provider_id = provider_id;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Date time_stamp) {
		this.time_stamp = time_stamp;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getMunis_id() {
		return munis_id;
	}

	public void setMunis_id(long munis_id) {
		this.munis_id = munis_id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getOperation_id()
	{
		return operation_id;
	}

	public void setOperation_id(int operation_id)
	{
		this.operation_id = operation_id;
	}

	public int getUser_id()
	{
		return user_id;
	}

	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
	

	public String getSubdivision()
	{
		return subdivision;
	}

	public void setSubdivision(String subdivision)
	{
		this.subdivision = subdivision;
	}

	@Override
	public String toString()
	{
		return("id: " + id +
		" tr_id: " + tr_id +
		" munis_id: " + munis_id +
		" branch: " + branch +
		" terminal_id: " + terminal_id +
		" provider_id: " + provider_id +
		" service_id: " + service_id +
		" p_name: " + p_name +
		" p_number: " + p_number +
		" from_date: " + from_date +
		" to_date: " + to_date +
		" from_value: " + from_value +
		" to_value: " + to_value +
		" amount: " + amount +
		" currency: " + currency +
		" time_stamp: " + time_stamp +
		" state: " + state +
		" district: " + district +
		" operation_id: " + operation_id +
		" user_id: " + user_id +
		" address: " + address+
		" subdivision: " + subdivision);
	}

	public String getAcc_name()
	{
		return acc_name;
	}

	public void setAcc_name(String acc_name)
	{
		this.acc_name = acc_name;
	}

	public String getFree_branch()
	{
		return free_branch;
	}

	public void setFree_branch(String free_branch)
	{
		this.free_branch = free_branch;
	}

	public String getPurpose_add()
	{
		return purpose_add;
	}

	public void setPurpose_add(String purpose_add)
	{
		this.purpose_add = purpose_add;
	}

	public String getPay_details()
	{
		return pay_details;
	}

	public void setPay_details(String pay_details)
	{
		this.pay_details = pay_details;
	}

	public String getBudget_inn()
	{
		return budget_inn;
	}

	public void setBudget_inn(String budget_inn)
	{
		this.budget_inn = budget_inn;
	}

	public String getBudget_account()
	{
		return budget_account;
	}

	public void setBudget_account(String budget_account)
	{
		this.budget_account = budget_account;
	}
	
	


}