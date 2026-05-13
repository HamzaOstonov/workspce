package com.is.tieto_globuz.tieto;

import java.util.HashMap;

import com.is.utils.Res;

public class PayResp {
	private Res rs;
	private AccrualEmployee paym;
	private HashMap< String,String> addInfo;

	public PayResp() {
		super();
	}




	public PayResp(Res rs, AccrualEmployee paym, HashMap<String, String> addInfo) {
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

	public AccrualEmployee getPaym() {
		return paym;
	}

	public void setPaym(AccrualEmployee paym) {
		this.paym = paym;
	}




	public HashMap<String, String> getAddInfo() {
		return addInfo;
	}




	public void setAddInfo(HashMap<String, String> addInfo) {
		this.addInfo = addInfo;
	}


	

}
