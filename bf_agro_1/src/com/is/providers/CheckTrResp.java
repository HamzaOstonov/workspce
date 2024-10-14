package com.is.providers;

public class CheckTrResp {
	private Res rs;
	private Payment paym;

	public CheckTrResp() {
		super();
	}

	public CheckTrResp(Res rs, Payment paym) {
		super();
		this.rs = rs;
		this.paym = paym;
	}

	public Res getRs() {
		return rs;
	}

	public void setRs(Res rs) {
		this.rs = rs;
	}

	public Payment getPaym() {
		return paym;
	}

	public void setPaym(Payment paym) {
		this.paym = paym;
	}
}
