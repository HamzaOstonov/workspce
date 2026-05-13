package com.is.providers;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;



public abstract class BaseProvider {
	private String endPoint;
	private PayResp pres= new PayResp();
    private Res res = new Res();
    private ResR resr = new ResR();
    private CheckTrResp ctr = new CheckTrResp();
    private ListTrResp ltr = new ListTrResp();
    private CheckPerResp cpr = new CheckPerResp();
	
	public BaseProvider() {
		super();
	}	
	
	public BaseProvider(String endPoint) {
		super();
		this.setEndPoint(endPoint);
	}
	public abstract PayResp check(Credentials cr, Payment pay);
	public abstract PayResp pay(Credentials cr, Payment pay, HashMap<String, String> addInfo);
	public abstract PayResp checkTr(Credentials cr,long id);
	public abstract ListTrResp listTr(Credentials cr,Date startDate, Date endDate);
    public abstract CheckPerResp checkPer(Credentials cr,Date startDate, Date endDate);
	
//	public abstract int Check_payments(Date d_since, Date d_until, int trans_count, double value);

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}
	

	public PayResp getPres() {
		return pres;
	}

	public void setPres(PayResp pres) {
		this.pres = pres;
	}

	public Res getRes() {
		return res;
	}

	public void setRes(Res res) {
		this.res = res;
	}

	public CheckTrResp getCtr() {
		return ctr;
	}

	public void setCtr(CheckTrResp ctr) {
		this.ctr = ctr;
	}

	public String getResp(InputStream stream,String cp){
		StringWriter writer = new StringWriter();
		try {
		IOUtils.copy(stream, writer, cp);
		} catch (Exception e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		return writer.toString();
	}
	public String getRespVal(String sour,String key){
		//int strt=sour.indexOf(key)+key.length()+1;
		int strt=sour.indexOf(key+">")+key.length()+1;
		int end=(sour.indexOf(key, strt)==-1)?strt:sour.indexOf(key, strt)-2;
		return sour.substring(strt,end);
	}

	public ListTrResp getLtr() {
		return ltr;
	}

	public void setLtr(ListTrResp ltr) {
		this.ltr = ltr;
	}

	public CheckPerResp getCpr() {
		return cpr;
	}

	public void setCpr(CheckPerResp cpr) {
		this.cpr = cpr;
	}

	public ResR getResr() {
		return resr;
	}

	public void setResr(ResR resr) {
		this.resr = resr;
	}
	
	

}
