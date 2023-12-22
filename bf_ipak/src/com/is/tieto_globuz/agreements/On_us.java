package com.is.tieto_globuz.agreements;

import java.util.Date;

public class On_us {
	private Long id;
	private String card_type;
	private String merchant;
	private String iss_cmi;
	private String imprint_fee;
	private String imprint_min;
	private String imprint_max;
	private String pos_fee;
	private String pos_min;
	private String pos_max;
	private String cashback_fee;
	private String cashback_min;
	private String cashback_max;
	private String atm_fee;
	private String atm_min;
	private String atm_max;
	private String tr_ccy;
	private String bin;
	private String algorithm;
	private String action;
	private String filename;
	private Date file_date;
	private String err_desc;
	private Date trdt;
	private Long id_agreement;
	
	public On_us() {
		super();
	}

	public Long getId_agreement() {
		return id_agreement;
	}

	public void setId_agreement(Long id_agreement) {
		this.id_agreement = id_agreement;
	}

	public On_us(Long id, String card_type, String merchant, String iss_cmi, String imprint_fee, String imprint_min,
			String imprint_max, String pos_fee, String pos_min, String pos_max, String cashback_fee,
			String cashback_min, String cashback_max, String atm_fee, String atm_min, String atm_max, String tr_ccy,
			String bin, String algorithm, String action, Long id_agreement) {
		super();
		this.id = id;
		this.card_type = card_type;
		this.merchant = merchant;
		this.iss_cmi = iss_cmi;
		this.imprint_fee = imprint_fee;
		this.imprint_min = imprint_min;
		this.imprint_max = imprint_max;
		this.pos_fee = pos_fee;
		this.pos_min = pos_min;
		this.pos_max = pos_max;
		this.cashback_fee = cashback_fee;
		this.cashback_min = cashback_min;
		this.cashback_max = cashback_max;
		this.atm_fee = atm_fee;
		this.atm_min = atm_min;
		this.atm_max = atm_max;
		this.tr_ccy = tr_ccy;
		this.bin = bin;
		this.algorithm = algorithm;
		this.action = action;
		this.id_agreement = id_agreement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getIss_cmi() {
		return iss_cmi;
	}

	public void setIss_cmi(String iss_cmi) {
		this.iss_cmi = iss_cmi;
	}

	public String getImprint_fee() {
		return imprint_fee;
	}

	public void setImprint_fee(String imprint_fee) {
		this.imprint_fee = imprint_fee;
	}

	public String getImprint_min() {
		return imprint_min;
	}

	public void setImprint_min(String imprint_min) {
		this.imprint_min = imprint_min;
	}

	public String getImprint_max() {
		return imprint_max;
	}

	public void setImprint_max(String imprint_max) {
		this.imprint_max = imprint_max;
	}

	public String getPos_fee() {
		return pos_fee;
	}

	public void setPos_fee(String pos_fee) {
		this.pos_fee = pos_fee;
	}

	public String getPos_min() {
		return pos_min;
	}

	public void setPos_min(String pos_min) {
		this.pos_min = pos_min;
	}

	public String getPos_max() {
		return pos_max;
	}

	public void setPos_max(String pos_max) {
		this.pos_max = pos_max;
	}

	public String getCashback_fee() {
		return cashback_fee;
	}

	public void setCashback_fee(String cashback_fee) {
		this.cashback_fee = cashback_fee;
	}

	public String getCashback_min() {
		return cashback_min;
	}

	public void setCashback_min(String cashback_min) {
		this.cashback_min = cashback_min;
	}

	public String getCashback_max() {
		return cashback_max;
	}

	public void setCashback_max(String cashback_max) {
		this.cashback_max = cashback_max;
	}

	public String getAtm_fee() {
		return atm_fee;
	}

	public void setAtm_fee(String atm_fee) {
		this.atm_fee = atm_fee;
	}

	public String getAtm_min() {
		return atm_min;
	}

	public void setAtm_min(String atm_min) {
		this.atm_min = atm_min;
	}

	public String getAtm_max() {
		return atm_max;
	}

	public void setAtm_max(String atm_max) {
		this.atm_max = atm_max;
	}

	public String getTr_ccy() {
		return tr_ccy;
	}

	public void setTr_ccy(String tr_ccy) {
		this.tr_ccy = tr_ccy;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getFile_date() {
		return file_date;
	}

	public void setFile_date(Date file_date) {
		this.file_date = file_date;
	}

	public String getErr_desc() {
		return err_desc;
	}

	public void setErr_desc(String err_desc) {
		this.err_desc = err_desc;
	}

	public Date getTrdt() {
		return trdt;
	}

	public void setTrdt(Date trdt) {
		this.trdt = trdt;
	}
	
}
