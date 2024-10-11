package com.is.tieto_globuz.tieto;

import java.util.Date;

public class PacketPayment {
    
    private Long id;
    private String branch;
    private String account;
    private String codeOrganization;
    private String paymentPurpose;
    private Long generalPay;
    private int state;
    private String name;
    private Date date;
    private String stateText;
    
    public PacketPayment() {
        super();
    }

    public PacketPayment(Long id, String branch, String account, String codeOrganization, String paymentPurpose,
            Long generalPay, int state, String name, Date date, String stateText) {
        this.id = id;
        this.branch = branch;
        this.account = account;
        this.codeOrganization = codeOrganization;
        this.paymentPurpose = paymentPurpose;
        this.generalPay = generalPay;
        this.state = state;
        this.name = name;
        this.date = date;
        this.stateText = stateText;
    }
    
    public PacketPayment(String branch, String account, String codeOrganization, String paymentPurpose,
            Long generalPay, int state, String name, Date date, String stateText) {
        this.branch = branch;
        this.account = account;
        this.codeOrganization = codeOrganization;
        this.paymentPurpose = paymentPurpose;
        this.generalPay = generalPay;
        this.state = state;
        this.name = name;
        this.date = date;
        this.stateText = stateText;
    }
    
    public Long getId() {
        return id;
    }
    public String getAccount() {
        return account;
    }
    public String getCodeOrganization() {
        return codeOrganization;
    }
    public String getPaymentPurpose() {
        return paymentPurpose;
    }
    public Long getGeneralPay() {
        return generalPay;
    }
    public String getName() {
        return name;
    }
    public Date getDate() {
        return date;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public void setCodeOrganization(String codeOrganization) {
        this.codeOrganization = codeOrganization;
    }
    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }
    public void setGeneralPay(Long generalPay) {
        this.generalPay = generalPay;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public String getStateText() {
        return stateText;
    }

}
