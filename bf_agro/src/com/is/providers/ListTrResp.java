package com.is.providers;
import java.util.List;

public class ListTrResp {
	private Res rs;
	private Payment [] lpay;

	public ListTrResp() {
		super();
	}

	public ListTrResp(Res rs, Payment[] lpay) {
		super();
		this.rs = rs;
		this.lpay = lpay;
	}

	public Res getRs() {
		return rs;
	}

	public void setRs(Res rs) {
		this.rs = rs;
	}

	public Payment[] getLpay() {
		return lpay;
	}

	public void setLpay(Payment[] lpay) {
		this.lpay = lpay;
	}



}
