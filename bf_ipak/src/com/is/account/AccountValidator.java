package com.is.account;

import com.is.account.model.Account;
import com.is.utils.CheckNull;

public class AccountValidator {
	private Account account;
	
	private AccountValidator(Account account){
		this.account = account;
	}
	
	public static AccountValidator getInstance(Account account) {
		return new AccountValidator(account);
	}
	
	public void validate() {
		
	}
	
	private String checkEmpty() {
		if(CheckNull.isEmpty(account.getAcc_bal())) {
			
		}
		
		return null;
	}
}
