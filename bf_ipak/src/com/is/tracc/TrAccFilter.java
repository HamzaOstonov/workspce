package com.is.tracc;

public class TrAccFilter {
    private int id;
    private String branch;
    private int acc_template_id;
    private String acc_mfo;
    private String account;
    private String acc_name;

    public TrAccFilter() {
    	super();
    }

	public TrAccFilter(int id, String branch, int acc_template_id, String acc_mfo,
			String account, String acc_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.acc_template_id = acc_template_id;
		this.acc_mfo = acc_mfo;
		this.account = account;
		this.acc_name = acc_name;
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

	public int getAcc_template_id() {
		return acc_template_id;
	}

	public void setAcc_template_id(int acc_template_id) {
		this.acc_template_id = acc_template_id;
	}

	public String getAcc_mfo() {
		return acc_mfo;
	}

	public void setAcc_mfo(String acc_mfo) {
		this.acc_mfo = acc_mfo;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAcc_name() {
		return acc_name;
	}

	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
}
