package com.is.customer_.local.model;

import com.google.common.collect.ComparisonChain;

import java.util.Date;

public class History implements Comparable<History>{
	private Date date_open;
	private Date date_close;
	private Date date_correct;
	private Date date_time;
	private int action_id;
	private int emp_id;

	public History() {
		super();
	}

	public History(Date date_open, Date date_close, Date date_correct,
			Date date_time, int action_id, int emp_id) {
		super();
		this.date_open = date_open;
		this.date_close = date_close;
		this.date_correct = date_correct;
		this.date_time = date_time;
		this.action_id = action_id;
		this.emp_id = emp_id;
	}

	public Date getDate_open() {
		return date_open;
	}

	public void setDate_open(Date date_open) {
		this.date_open = date_open;
	}

	public Date getDate_close() {
		return date_close;
	}

	public void setDate_close(Date date_close) {
		this.date_close = date_close;
	}

	public Date getDate_correct() {
		return date_correct;
	}

	public void setDate_correct(Date date_correct) {
		this.date_correct = date_correct;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	public int getAction_id() {
		return action_id;
	}

	public void setAction_id(int action_id) {
		this.action_id = action_id;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	@Override
	public String toString() {
		return "History [date_open=" + date_open + ", date_close=" + date_close
				+ ", date_correct=" + date_correct + ", date_time=" + date_time
				+ ", id=" + action_id + ", emp_id=" + emp_id
				+ ", getDate_open()=" + getDate_open() + ", getDate_close()="
				+ getDate_close() + ", getDate_correct()=" + getDate_correct()
				+ ", getDate_time()=" + getDate_time() + ", getId()="
				+ getAction_id() + ", getEmp_id()=" + getEmp_id()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

    @Override
    public int compareTo(History that) {
        return ComparisonChain.
                start().
                compare(this.date_time,that.date_time)
                    .result();
    }
}
