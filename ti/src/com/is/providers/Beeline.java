package com.is.providers;

import java.net.HttpURLConnection;
import java.net.URL;

import com.is.providers.AddInfo;
import com.is.providers.CheckTrResp;
import com.is.providers.Credentials;
import com.is.providers.PayResp;
import com.is.providers.Payment;
import com.is.providers.Res;

public class Beeline extends BaseProvider{

	public Beeline()
	{
		super();
		//this.setEndPoint("http://128.10.10.117:30001/beeline.aspx");
	}
	
	@Override
	public PayResp check(Credentials cr, Payment pay) {
		StringBuffer sb = new StringBuffer();
		try {
		sb.append(this.getEndPoint());
		sb.append("?ACT=0");
		sb.append("&PAY_AMOUNT="+pay.getAmount()+"&CURRENCY_CODE=1");
		sb.append("&MSISDN="+pay.getP_number());
		sb.append("&PAY_ID="+pay.getId());
		sb.append("&USERNAME="+cr.getUn());
		sb.append("&PASSWORD="+cr.getPw());
		sb.append("&BRANCH="+pay.getBranch());
		sb.append("&SOURCE_TYPE=1");
		sb.append("&TRADE_POINT=1");
		sb.append("&RECEIPT_NUM="+pay.getTr_id());
		URL url = new URL(sb.toString());
			//URL url = new URL("http://128.10.10.117:30001/beeline.aspx?ACT=0 &PAY_AMOUNT=500&CURRENCY_CODE=1&MSISDN=901878539&PAY_ID=1&USERNAME=admin&PASSWORD=admin&BRANCH=1&SOURCE_TYPE=1&TRADE_POINT=1&RECEIPT_NUM=1");
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		String strres = getResp(connection.getInputStream(), "cp1251");
		System.out.println("resp : "+strres);
		System.out.println("status_code : "+getRespVal(strres, "status_code"));
		getRes().setCode(Integer.parseInt(getRespVal(strres, "status_code")));
        
		getPres().setPaym(pay);
		getPres().setRs(getRes());
		//getPres().setAddInfo(new)

		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return getPres();
	}

	@Override
	public PayResp pay(Credentials cr, Payment pay, AddInfo addInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckTrResp checkTr(Credentials cr, long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
