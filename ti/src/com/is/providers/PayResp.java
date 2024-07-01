package com.is.providers;

import java.util.HashMap;

public class PayResp {
	private Res rs;
	private Payment paym;
	private HashMap< String,String> addInfo;

	public PayResp() {
		super();
	}




	public PayResp(Res rs, Payment paym, HashMap<String, String> addInfo) {
		super();
		this.rs = rs;
		this.paym = paym;
		this.addInfo = addInfo;
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




	public HashMap<String, String> getAddInfo() {
		return addInfo;
	}




	public void setAddInfo(HashMap<String, String> addInfo) {
		this.addInfo = addInfo;
	}


	

}
