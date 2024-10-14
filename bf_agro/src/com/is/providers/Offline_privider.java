package com.is.providers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Offline_privider extends BaseProvider
{
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	@Override
	public PayResp check(Credentials cr, Payment pay)
	{
		PayResp res = new PayResp();
		
		res.setRs(new Res(0,""));
		return res;
	}

	@Override
	public PayResp pay(Credentials cr, Payment pay,
			HashMap<String, String> addInfo)
	{
		PayResp res = new PayResp();
		
		res.setRs(new Res(0,""));
		
		HashMap <String, String> addinfo = new HashMap <String, String>();
		
		if (pay.getP_name()!=null && (!pay.getP_name().equals(""))) addinfo.put("p_name", pay.getP_name());
		if (pay.getP_number()!=null && (!pay.getP_number().equals(""))) addinfo.put("p_number", pay.getP_number());
		if (pay.getFrom_date()!=null) addinfo.put("from_date", df.format(pay.getFrom_date()));
		if (pay.getTo_date()!=null) addinfo.put("to_date", df.format(pay.getTo_date()));
		if (pay.getFrom_value()!=null && (!pay.getFrom_value().equals(""))) addinfo.put("from_value", pay.getFrom_value());
		if (pay.getTo_value()!=null && (!pay.getTo_value().equals(""))) addinfo.put("to_value", pay.getTo_value());
		
		if ((pay.getAcc_name()!=null) && (!pay.getAcc_name().equals("")))
			addinfo.put("acc_name", pay.getAcc_name());
		if ((pay.getFree_branch()!=null) && (!pay.getFree_branch().equals("")))
			addinfo.put("free_branch", pay.getFree_branch());
		if ((pay.getPurpose_add()!=null) && (!pay.getPurpose_add().equals("")))
			addinfo.put("purpose_add", pay.getPurpose_add());
		if ((pay.getPay_details()!=null) && (!pay.getPay_details().equals("")))
			addinfo.put("pay_details", pay.getPay_details());
		if ((pay.getBudget_account()!=null) && (!pay.getBudget_account().equals("")))
			addinfo.put("budget_account", pay.getBudget_account());
		if ((pay.getBudget_inn()!=null) && (!pay.getBudget_inn().equals("")))
			addinfo.put("budget_inn", pay.getBudget_inn());
		
		//addinfo.put("difference", pay.get);
		//addinfo.put("penalty_amount", pay.ge);
		if (pay.getAddress()!=null && (!pay.getAddress().equals(""))) addinfo.put("client_address", pay.getAddress());
		
		res.setAddInfo(addinfo);
		
		return res;
	}

	@Override
	public PayResp checkTr(Credentials cr, long id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListTrResp listTr(Credentials cr, Date startDate, Date endDate)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckPerResp checkPer(Credentials cr, Date startDate, Date endDate)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
