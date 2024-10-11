package com.is.humo;

import java.io.Serializable;
import java.util.Date;

public class HumoCardsFilter implements Serializable {

    static final long serialVersionUID = 103844514947365244L;


    private String client;
    private String client_b;
    private String branch;
    private String card;
    private String status1;
    private String status2;
    private Date expiry1;
    private Date expirity2;
    private String renew;
    private Date renew_date;
    private String card_name;
    private String mc_name;
    private String m_name;
    private String stop_cause;
    private String renewed_card;
    private int design_id;
    private String instant;
    private String tranz_acct;
    

    public HumoCardsFilter() {

    }

    public HumoCardsFilter( String client, String client_b, String branch, String card, String status1, String status2, Date expiry1, Date expirity2, String renew, Date renew_date, String card_name, String mc_name, String m_name, String stop_cause, String renewed_card, int design_id, String instant,String tranz_acct) {

                this.client = client;
                this.client_b = client_b;
                this.branch = branch;
                this.card = card;
                this.status1 = status1;
                this.status2 = status2;
                this.expiry1 = expiry1;
                this.expirity2 = expirity2;
                this.renew = renew;
                this.renew_date = renew_date;
                this.card_name = card_name;
                this.mc_name = mc_name;
                this.m_name = m_name;
                this.stop_cause = stop_cause;
                this.renewed_card = renewed_card;
                this.design_id = design_id;
                this.instant = instant;
                this.tranz_acct = tranz_acct;
        }

	public String getTranz_acct() {
		return tranz_acct;
	}

	public void setTranz_acct(String tranz_acct) {
		this.tranz_acct = tranz_acct;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClient_b() {
		return client_b;
	}

	public void setClient_b(String client_b) {
		this.client_b = client_b;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public Date getExpiry1() {
		return expiry1;
	}

	public void setExpiry1(Date expiry1) {
		this.expiry1 = expiry1;
	}

	public Date getExpirity2() {
		return expirity2;
	}

	public void setExpirity2(Date expirity2) {
		this.expirity2 = expirity2;
	}

	public String getRenew() {
		return renew;
	}

	public void setRenew(String renew) {
		this.renew = renew;
	}

	public Date getRenew_date() {
		return renew_date;
	}

	public void setRenew_date(Date renew_date) {
		this.renew_date = renew_date;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getMc_name() {
		return mc_name;
	}

	public void setMc_name(String mc_name) {
		this.mc_name = mc_name;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getStop_cause() {
		return stop_cause;
	}

	public void setStop_cause(String stop_cause) {
		this.stop_cause = stop_cause;
	}

	public String getRenewed_card() {
		return renewed_card;
	}

	public void setRenewed_card(String renewed_card) {
		this.renewed_card = renewed_card;
	}

	public int getDesign_id() {
		return design_id;
	}

	public void setDesign_id(int design_id) {
		this.design_id = design_id;
	}

	public String getInstant() {
		return instant;
	}

	public void setInstant(String instant) {
		this.instant = instant;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

