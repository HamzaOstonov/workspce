package com.is.hr;

import java.util.Date;

public class pay_calc_LOGFilter {
	private int id;
    private String branch;
    private int emp_id;
    private Date curdate;
    private Date period;
    private String user_id;
    private String user_name;
    private String txt;
    private String err;
    private Date sysd;

    public pay_calc_LOGFilter() {

    }

    public pay_calc_LOGFilter( int id, String branch, int emp_id, Date curdate, Date period, String user_id, String user_name, String txt, String err, Date sysd) {

                this.id = id;
                this.branch = branch;
                this.emp_id = emp_id;
                this.curdate = curdate;
                this.period = period;
                this.user_id = user_id;
                this.user_name = user_name;
                this.txt = txt;
                this.err = err;
                this.sysd = sysd;
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

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public Date getCurdate() {
		return curdate;
	}

	public void setCurdate(Date curdate) {
		this.curdate = curdate;
	}

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public Date getSysd() {
		return sysd;
	}

	public void setSysd(Date sysd) {
		this.sysd = sysd;
	}

    
}


