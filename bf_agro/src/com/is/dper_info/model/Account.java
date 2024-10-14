package com.is.dper_info.model;

public class Account {
	private String acc_dep;
	private String name;
	private long s_out_tmp;
	public String getAcc_dep() {
		return acc_dep;
	}
	public void setAcc_dep(String acc_dep) {
		this.acc_dep = acc_dep;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getS_out_tmp() {
		return s_out_tmp;
	}
	public void setS_out_tmp(long s_out_tmp) {
		this.s_out_tmp = s_out_tmp;
	}
}
