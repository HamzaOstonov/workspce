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
	private int amount;
	private String currency;
	private Date time_stamp;
	private int state;
	private String district;

	public Payment() {
		super();
	}

	public Payment(long id, long tr_id, String branch, String terminal_id,
			int provider_id, int service_id, String p_name, String p_number,
			Date from_date, Date to_date, String from_value, String to_value,
			int amount, String currency, Date time_stamp, int state) {
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
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
	
	


}