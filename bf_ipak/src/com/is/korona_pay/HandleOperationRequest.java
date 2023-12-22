package com.is.korona_pay;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HandleOperationRequest {
	
	@JsonProperty("AbsClientId")
	public String absClientId;
	@JsonProperty("Action")
	public String action;
	@JsonProperty("CommissionAmount")
	public int commissionAmount;
	@JsonProperty("CommissionCur")
	public String commissionCur;
	@JsonProperty("CommissionExp")
	public int commissionExp;
	@JsonProperty("FromCountryIso")
	public String fromCountryIso;
	@JsonProperty("Kbe")
	public int kbe;
	@JsonProperty("Operation")
	public int operation;
	@JsonProperty("OperationDate")
	public String operationDate;
	@JsonProperty("PayAmount")
	public int payAmount;
	@JsonProperty("PayCur")
	public String payCur;
	@JsonProperty("PayExp")
	public int payExp;
	@JsonProperty("ReceiverBirthCity")
	public String receiverBirthCity;
	@JsonProperty("ReceiverBirthDate")
	public String receiverBirthDate;
	@JsonProperty("ReceiverCitizenship")
	public String receiverCitizenship;
	@JsonProperty("ReceiverCommissionAmount")
	public int receiverCommissionAmount;
	@JsonProperty("ReceiverCommissionCur")
	public String receiverCommissionCur;
	@JsonProperty("ReceiverCommissionExp")
	public int receiverCommissionExp;
	@JsonProperty("ReceiverDocIssueDate")
	public String receiverDocIssueDate;
	@JsonProperty("ReceiverDocIssuer")
	public String receiverDocIssuer;
	@JsonProperty("ReceiverDocNumber")
	public String receiverDocNumber;
	@JsonProperty("ReceiverDocSeries")
	public String receiverDocSeries;
	@JsonProperty("ReceiverDocType")
	public String receiverDocType;
	@JsonProperty("ReceiverFullName")
	public String receiverFullName;
	@JsonProperty("ReceiverOrigFullName")
	public String receiverOrigFullName;
	@JsonProperty("ReceiverPhone")
	public String receiverPhone;
	
	@JsonProperty("ReceiverINN")
	public String receiverINN;
	
	@JsonProperty("ReceiverRegAddress")
	public String receiverRegAddress;
	@JsonProperty("ReceiverRegCity")
	public String receiverRegCity;
	@JsonProperty("ReceiverRegCountryIso")
	public String receiverRegCountryIso;
	@JsonProperty("SenderBirthCity")
	public String senderBirthCity;
	@JsonProperty("SenderBirthDate")
	public String senderBirthDate;
	@JsonProperty("SenderRegCountryIso")
	public String senderRegCountryIso;
	@JsonProperty("SenderCard")
    public String senderCard;	
	@JsonProperty("SenderCitizenship")
	public String senderCitizenship;
	@JsonProperty("SenderDocIssueDate")
	public String senderDocIssueDate;
	@JsonProperty("SenderDocIssuer")
	public String senderDocIssuer;
	@JsonProperty("SenderDocIssuerCode")
	public String senderDocIssuerCode;
	@JsonProperty("SenderDocNumber")
	public String senderDocNumber;
	@JsonProperty("SenderDocSeries")
	public String senderDocSeries;
	@JsonProperty("SenderDocType")
	public String senderDocType;
	@JsonProperty("SenderFullName")
	public String senderFullName;
	
	@JsonProperty("SenderINN")
	public String senderINN;
	
	@JsonProperty("SenderPhone")
	public String senderPhone;
	@JsonProperty("SenderRegAddress")
	public String senderRegAddress;
	@JsonProperty("SenderRegCity")
	public String senderRegCity;
	@JsonProperty("ToCityId")
	public String toCityId;
	@JsonProperty("ToCountryIso")
	public String toCountryIso;
	@JsonProperty("TransferType")
	public String transferType;
	@JsonProperty("UIN")
	public String uIN;
	@JsonProperty("SenderCommissionAmount")
	public int senderCommissionAmount;
	@JsonProperty("SenderCommissionExp")
	public int senderCommissionExp;
	@JsonProperty("SenderCommissionCur")
	public String senderCommissionCur;
	public int state;
	public String account;
	public String name;
	public String sOut;
	
	public HandleOperationRequest() {
	}

	public String getAbsClientId() {
		return absClientId;
	}

	public void setAbsClientId(String absClientId) {
		this.absClientId = absClientId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(int commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public String getCommissionCur() {
		return commissionCur;
	}

	public void setCommissionCur(String commissionCur) {
		this.commissionCur = commissionCur;
	}

	public int getCommissionExp() {
		return commissionExp;
	}

	public void setCommissionExp(int commissionExp) {
		this.commissionExp = commissionExp;
	}

	public String getFromCountryIso() {
		return fromCountryIso;
	}

	public void setFromCountryIso(String fromCountryIso) {
		this.fromCountryIso = fromCountryIso;
	}
	
	public int getKbe() {
		return kbe;
	}

	public void setKbe(int kbe) {
		this.kbe = kbe;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public String getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayCur() {
		return payCur;
	}

	public void setPayCur(String payCur) {
		this.payCur = payCur;
	}

	public int getPayExp() {
		return payExp;
	}

	public void setPayExp(int payExp) {
		this.payExp = payExp;
	}

	public String getReceiverBirthCity() {
		return receiverBirthCity;
	}

	public void setReceiverBirthCity(String receiverBirthCity) {
		this.receiverBirthCity = receiverBirthCity;
	}

	public String getReceiverBirthDate() {
		return receiverBirthDate;
	}

	public void setReceiverBirthDate(String receiverBirthDate) {
		this.receiverBirthDate = receiverBirthDate;
	}

	public String getReceiverCitizenship() {
		return receiverCitizenship;
	}

	public void setReceiverCitizenship(String receiverCitizenship) {
		this.receiverCitizenship = receiverCitizenship;
	}

	public int getReceiverCommissionAmount() {
		return receiverCommissionAmount;
	}

	public void setReceiverCommissionAmount(int receiverCommissionAmount) {
		this.receiverCommissionAmount = receiverCommissionAmount;
	}

	public String getReceiverCommissionCur() {
		return receiverCommissionCur;
	}

	public void setReceiverCommissionCur(String receiverCommissionCur) {
		this.receiverCommissionCur = receiverCommissionCur;
	}

	public int getReceiverCommissionExp() {
		return receiverCommissionExp;
	}

	public void setReceiverCommissionExp(int receiverCommissionExp) {
		this.receiverCommissionExp = receiverCommissionExp;
	}

	public String getReceiverDocIssueDate() {
		return receiverDocIssueDate;
	}

	public void setReceiverDocIssueDate(String receiverDocIssueDate) {
		this.receiverDocIssueDate = receiverDocIssueDate;
	}

	public String getReceiverDocIssuer() {
		return receiverDocIssuer;
	}

	public void setReceiverDocIssuer(String receiverDocIssuer) {
		this.receiverDocIssuer = receiverDocIssuer;
	}

	public String getReceiverDocNumber() {
		return receiverDocNumber;
	}

	public void setReceiverDocNumber(String receiverDocNumber) {
		this.receiverDocNumber = receiverDocNumber;
	}

	public String getReceiverDocSeries() {
		return receiverDocSeries;
	}

	public void setReceiverDocSeries(String receiverDocSeries) {
		this.receiverDocSeries = receiverDocSeries;
	}

	public String getReceiverDocType() {
		return receiverDocType;
	}

	public void setReceiverDocType(String receiverDocType) {
		this.receiverDocType = receiverDocType;
	}

	public String getReceiverFullName() {
		return receiverFullName;
	}

	public void setReceiverFullName(String receiverFullName) {
		this.receiverFullName = receiverFullName;
	}

	public String getReceiverOrigFullName() {
		return receiverOrigFullName;
	}

	public void setReceiverOrigFullName(String receiverOrigFullName) {
		this.receiverOrigFullName = receiverOrigFullName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverRegAddress() {
		return receiverRegAddress;
	}

	public void setReceiverRegAddress(String receiverRegAddress) {
		this.receiverRegAddress = receiverRegAddress;
	}

	public String getReceiverRegCity() {
		return receiverRegCity;
	}

	public void setReceiverRegCity(String receiverRegCity) {
		this.receiverRegCity = receiverRegCity;
	}

	public String getReceiverRegCountryIso() {
		return receiverRegCountryIso;
	}

	public void setReceiverRegCountryIso(String receiverRegCountryIso) {
		this.receiverRegCountryIso = receiverRegCountryIso;
	}

	public String getSenderBirthCity() {
		return senderBirthCity;
	}

	public void setSenderBirthCity(String senderBirthCity) {
		this.senderBirthCity = senderBirthCity;
	}

	public String getSenderBirthDate() {
		return senderBirthDate;
	}

	public void setSenderBirthDate(String senderBirthDate) {
		this.senderBirthDate = senderBirthDate;
	}

	public String getSenderRegCountryIso() {
		return senderRegCountryIso;
	}

	public void setSenderRegCountryIso(String senderRegCountryIso) {
		this.senderRegCountryIso = senderRegCountryIso;
	}

	
	public String getSenderCard() {
		return senderCard;
	}

	public void setSenderCard(String senderCard) {
		this.senderCard = senderCard;
	}

	public String getSenderCitizenship() {
		return senderCitizenship;
	}

	public void setSenderCitizenship(String senderCitizenship) {
		this.senderCitizenship = senderCitizenship;
	}

	public String getSenderDocIssueDate() {
		return senderDocIssueDate;
	}

	public void setSenderDocIssueDate(String senderDocIssueDate) {
		this.senderDocIssueDate = senderDocIssueDate;
	}

	public String getSenderDocIssuer() {
		return senderDocIssuer;
	}

	public void setSenderDocIssuer(String senderDocIssuer) {
		this.senderDocIssuer = senderDocIssuer;
	}

	public String getSenderDocIssuerCode() {
		return senderDocIssuerCode;
	}

	public void setSenderDocIssuerCode(String senderDocIssuerCode) {
		this.senderDocIssuerCode = senderDocIssuerCode;
	}

	public String getSenderDocNumber() {
		return senderDocNumber;
	}

	public void setSenderDocNumber(String senderDocNumber) {
		this.senderDocNumber = senderDocNumber;
	}

	public String getSenderDocSeries() {
		return senderDocSeries;
	}

	public void setSenderDocSeries(String senderDocSeries) {
		this.senderDocSeries = senderDocSeries;
	}

	public String getSenderDocType() {
		return senderDocType;
	}

	public void setSenderDocType(String senderDocType) {
		this.senderDocType = senderDocType;
	}

	public String getSenderFullName() {
		return senderFullName;
	}

	public void setSenderFullName(String senderFullName) {
		this.senderFullName = senderFullName;
	}

	

	public String getSenderINN() {
		return senderINN;
	}

	
	public void setSenderINN(String senderINN) {
		this.senderINN = senderINN;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	
	
	public String getReceiverINN() {
		return receiverINN;
	}

	public void setReceiverINN(String receiverINN) {
		this.receiverINN = receiverINN;
	}

	public String getSenderRegAddress() {
		return senderRegAddress;
	}

	public void setSenderRegAddress(String senderRegAddress) {
		this.senderRegAddress = senderRegAddress;
	}

	public String getSenderRegCity() {
		return senderRegCity;
	}

	public void setSenderRegCity(String senderRegCity) {
		this.senderRegCity = senderRegCity;
	}

	public String getToCityId() {
		return toCityId;
	}

	public void setToCityId(String toCityId) {
		this.toCityId = toCityId;
	}

	public String getToCountryIso() {
		return toCountryIso;
	}

	public void setToCountryIso(String toCountryIso) {
		this.toCountryIso = toCountryIso;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getuIN() {
		return uIN;
	}

	public void setuIN(String uIN) {
		this.uIN = uIN;
	}

	public int getSenderCommissionAmount() {
		return senderCommissionAmount;
	}

	public void setSenderCommissionAmount(int senderCommissionAmount) {
		this.senderCommissionAmount = senderCommissionAmount;
	}

	public int getSenderCommissionExp() {
		return senderCommissionExp;
	}

	public void setSenderCommissionExp(int senderCommissionExp) {
		this.senderCommissionExp = senderCommissionExp;
	}

	public String getSenderCommissionCur() {
		return senderCommissionCur;
	}

	public void setSenderCommissionCur(String senderCommissionCur) {
		this.senderCommissionCur = senderCommissionCur;
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

	public String getsOut() {
		return sOut;
	}

	public void setsOut(String sOut) {
		this.sOut = sOut;
	}
	
	

}
