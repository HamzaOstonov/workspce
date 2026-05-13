package com.is.tieto_globuz.tieto;

import java.util.Date;

public class AccrualEmployee
{
	private long id;
	private String branch;
	private String customerId;
	private String employeeId;
	private String employeeName;
	private String employeeAccount;
	private String amount;
	private String operDate;
	private int state;
	private Long generalId;
	private Long generalPay;
	private String fileName;
	private String report;
	private String accountNo;
	private String card;
	private String realCard;
	private String client;
	private String sessionId;
	private String stateText;
	
    public AccrualEmployee() {
        super();
    }
    
    public AccrualEmployee(long id, String branch, String customerId, String employeeId,
            String employeeName, String employeeAccount, String amount, String operDate, int state,
            Long generalId, Long generalPay, String fileName, String report, String accountNo,
            String card, String realCard, String client, String sessionId, String stateText) {
        super();
        this.id = id;
        this.branch = branch;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAccount = employeeAccount;
        this.amount = amount;
        this.operDate = operDate;
        this.state = state;
        this.generalId = generalId;
        this.generalPay = generalPay;
        this.fileName = fileName;
        this.report = report;
        this.accountNo = accountNo;
        this.card = card;
        this.realCard = realCard;
        this.client = client;
        this.sessionId = sessionId;
        this.stateText = stateText;
    }
    public long getId() {
        return id;
    }
    public String getBranch() {
        return branch;
    }
    public String getCustomerId() {
        return customerId;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public String getEmployeeAccount() {
        return employeeAccount;
    }
    public String getAmount() {
        return amount;
    }
    public String getOperDate() {
        return operDate;
    }
    public int getState() {
        return state;
    }
    public Long getGeneralId() {
        return generalId;
    }
    public Long getGeneralPay() {
        return generalPay;
    }
    public String getFileName() {
        return fileName;
    }
    public String getReport() {
        return report;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public String getCard() {
        return card;
    }
    public String getRealCard() {
        return realCard;
    }
    public String getClient() {
        return client;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public void setEmployeeAccount(String employeeAccount) {
        this.employeeAccount = employeeAccount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public void setOperDate(String operDate) {
        this.operDate = operDate;
    }
    public void setState(int state) {
        this.state = state;
    }
    public void setGeneralId(Long generalId) {
        this.generalId = generalId;
    }
    public void setGeneralPay(Long generalPay) {
        this.generalPay = generalPay;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setReport(String report) {
        this.report = report;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public void setCard(String card) {
        this.card = card;
    }
    public void setRealCard(String realCard) {
        this.realCard = realCard;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public String getStateText() {
        return stateText;
    }
	
	
	
}
	