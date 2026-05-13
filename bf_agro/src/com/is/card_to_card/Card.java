package com.is.card_to_card;

import java.sql.Date;

public class Card {
	private String pinfl;
	private String id;
	private String branch;
	private String card_number;
	private String account;
	private String name;
	private String expiry;
	private String status;
	private String contract;
	private String client_code;
	private String currency;
//	private Date time;
//	private Date bank_time;
	private String time;
	private String bank_time;
	private String amount;
	private String purpose;
	private String fromcardtype, fromcardnumber, fromcardbranch, fromcardacc, fromcard_client_id, fromcard_client_name;
	private String tocardtype, tocardnumber, tocardbranch, tocardacc, tocard_client_id, tocard_client_name;
	private String state;
	
	
	public Card() {
		
	}
	
	public Card(String id, String branch, String card_number, String account, String name, String pinfl) {
		this.id = id;
		this.branch = branch;
		this.card_number = card_number;
		this.account = account;
		this.name = name;
		this.pinfl = pinfl;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setBank_time(String bank_time) {
		this.bank_time = bank_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPinfl() {
		return pinfl;
	}

	public void setPinfl(String pinfl) {
		this.pinfl = pinfl;
	}

	public String getFromcardtype() {
		return fromcardtype;
	}
	public void setFromcardtype(String fromcardtype) {
		this.fromcardtype = fromcardtype;
	}
	public String getFromcardnumber() {
		return fromcardnumber;
	}
	public void setFromcardnumber(String fromcardnumber) {
		this.fromcardnumber = fromcardnumber;
	}
	public String getFromcardbranch() {
		return fromcardbranch;
	}
	public void setFromcardbranch(String fromcardbranch) {
		this.fromcardbranch = fromcardbranch;
	}
	public String getFromcardacc() {
		return fromcardacc;
	}
	public void setFromcardacc(String fromcardacc) {
		this.fromcardacc = fromcardacc;
	}
	public String getFromcard_client_id() {
		return fromcard_client_id;
	}
	public void setFromcard_client_id(String fromcard_client_id) {
		this.fromcard_client_id = fromcard_client_id;
	}
	public String getFromcard_client_name() {
		return fromcard_client_name;
	}
	public void setFromcard_client_name(String fromcard_client_name) {
		this.fromcard_client_name = fromcard_client_name;
	}
	public String getTocardtype() {
		return tocardtype;
	}
	public void setTocardtype(String tocardtype) {
		this.tocardtype = tocardtype;
	}
	public String getTocardnumber() {
		return tocardnumber;
	}
	public void setTocardnumber(String tocardnumber) {
		this.tocardnumber = tocardnumber;
	}
	public String getTocardbranch() {
		return tocardbranch;
	}
	public void setTocardbranch(String tocardbranch) {
		this.tocardbranch = tocardbranch;
	}
	public String getTocardacc() {
		return tocardacc;
	}
	public void setTocardacc(String tocardacc) {
		this.tocardacc = tocardacc;
	}
	public String getTocard_client_id() {
		return tocard_client_id;
	}
	public void setTocard_client_id(String tocard_client_id) {
		this.tocard_client_id = tocard_client_id;
	}
	public String getTocard_client_name() {
		return tocard_client_name;
	}
	public void setTocard_client_name(String tocard_client_name) {
		this.tocard_client_name = tocard_client_name;
	}

//	public Date getBank_time() {
//		return bank_time;
//	}
//	public void setBank_time(Date bank_time) {
//		this.bank_time = bank_time;
//	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getClient_code() {
		return client_code;
	}
	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
//	public Date getTime() {
//		return time;
//	}
//	public void setTime(Date time) {
//		this.time = time;
//	}
	public String getTime() {
		return time;
	}
	public String getBank_time() {
		return bank_time;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	
	
}
