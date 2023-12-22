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
			return "������� id �������";
		}
		if(CheckNull.isEmpty(account.getAcc_bal())){
			return "������� ���������� ����";
		}
		if(CheckNull.isEmpty(account.getCurrency())){
			return "������� ������";
		}
		if(CheckNull.isEmpty(account.getId_order())){
			return "������� ���������� �����";
		}
		if(CheckNull.isEmpty(account.getName())){
			return "������� ������������";
		}
		if(CheckNull.isEmpty(account.getAcc_group_id())){
			return "������� ������";
		}
		
		return "";
	}

}
