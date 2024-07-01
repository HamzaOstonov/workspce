package com.is.compaymentscheck;

import java.util.Date;

public class ComPaymentsCheckFilter {
    private Long id;
    private int provider_id;
    private Date from_time;
    private Date to_time;
    private Date exec_time;
    private int trans_count;
    private Long amount;
    private int state;

    public ComPaymentsCheckFilter() {
    	super();
    }

	public ComPaymentsCheckFilter(Long id, int provider_id, Date from_time,
			Date to_time, Date exec_time, int trans_count, Long amount,
			int state) {
		super();
		this.id = id;
		this.provider_id = provider_id;
		this.from_time = from_time;
		this.to_time = to_time;
		this.exec_time = exec_time;
		this.trans_count = trans_count;
		this.amount = amount;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(int provider_id) {
		this.provider_id = provider_id;
	}

	public Date getFrom_time() {
		return from_time;
	}

	public void setFrom_time(Date from_time) {
		this.from_time = from_time;
	}

	public Date getTo_time() {
		return to_time;
	}

	public void setTo_time(Date to_time) {
		this.to_time = to_time;
	}

	public Date getExec_time() {
		return exec_time;
	}

	public void setExec_time(Date exec_time) {
		this.exec_time = exec_time;
	}

	public int getTrans_count() {
		return trans_count;
	}

	public void setTrans_count(int trans_count) {
		this.trans_count = trans_count;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
