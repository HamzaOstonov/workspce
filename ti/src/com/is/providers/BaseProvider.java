package com.is.providers;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Date;

import org.apache.commons.io.IOUtils;



public abstract class BaseProvider {
	private String endPoint;
	private PayResp pres= new PayResp();
    private Res res = new Res();
    private CheckTrResp ctr = new CheckTrResp();
	
	public BaseProvider() {
		super();
	}	
	
	public BaseProvider(String endPoint) {
		super();
		this.setEndPoint(endPoint);
	}
	public abstract PayResp check(Credentials cr, Payment pay);
	public abstract PayResp pay(Credentials cr, Payment pay, AddInfo addInfo);
	public abstract CheckTrResp checkTr(Credentials cr,long id);
	
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
			e.printStackTrace();
		}
		return writer.toString();
	}
	public String getRespVal(String sour,String key){
		//int strt=sour.indexOf(key)+key.length()+1;
		int strt=sour.indexOf(key+">")+key.length()+1;
		int end=(sour.indexOf(key, strt)==-1)?strt:sour.indexOf(key, strt)-2;
		return sour.substring(strt,end);
	}
		
	

}
