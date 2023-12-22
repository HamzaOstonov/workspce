package com.is.account;

import com.is.account.model.Account;
import com.is.utils.CheckNull;

public class CheckAccount {
	private Account account;
	private String alias;
	
	
	public CheckAccount(Account account, String alias) {
		super();
		this.account = account;
		this.alias = alias;
	}
	
	
	public void check(){
		
	}
	
	
	public String checkEmpty(){
		if(CheckNull.isEmpty(account.getClient())){
			return "¬ведите id клиента";
		}
		if(CheckNull.isEmpty(account.getAcc_bal())){
			return "¬ведите балансовый счет";
		}
		if(CheckNull.isEmpty(account.getCurrency())){
			return "¬ведите валюту";
		}
		if(CheckNull.isEmpty(account.getId_order())){
			return "¬ведите пор€дковый номер";
		}
		if(CheckNull.isEmpty(account.getName())){
			return "¬ведите наименование";
		}
		if(CheckNull.isEmpty(account.getAcc_group_id())){
			return "¬ведите группу";
		}
		
		return "";
	}

}
