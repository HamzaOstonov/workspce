package com.is.userti;

import java.util.Date;

public class UserFilter {
    private int id;
    private String branch;
    private String user_name;
    private String full_name;
    private String title;
    private int not_chg_pas;
    private int locked;
    private Date date_open;
    private Date pwd_expired;

    public UserFilter() {
    	super();
    }

	public UserFilter(int id, String branch, String user_name, String full_name,
			String title, int not_chg_pas, int locked, Date date_open,
			Date pwd_expired) {
		super();
		this.id = id;
		this.branch = branch;
		this.user_name = user_name;
		this.full_name = full_name;
		this.title = title;
		this.not_chg_pas = not_chg_pas;
		this.locked = locked;
		this.date_open = date_open;
		this.pwd_expired = pwd_expired;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNot_chg_pas() {
		return not_chg_pas;
	}

	public void setNot_chg_pas(int not_chg_pas) {
		this.not_chg_pas = not_chg_pas;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public Date getDate_open() {
		return date_open;
	}

	public void setDate_open(Date date_open) {
		this.date_open = date_open;
	}

	public Date getPwd_expired() {
		return pwd_expired;
	}

	public void setPwd_expired(Date pwd_expired) {
		this.pwd_expired = pwd_expired;
	}

}
