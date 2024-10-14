package com.is.uzcard.model;


public class AccountInfo {
	private String accountId;
	private String accountName;

	/**
	 * @param accountId
	 *            Номер счёта
	 * @param accountName
	 *            Имя плательщика
	 */
	public AccountInfo(String accountId, String accountName) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
	}

	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
