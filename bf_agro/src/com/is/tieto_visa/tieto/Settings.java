package com.is.tieto_visa.tieto;


public class Settings {
    private String PaymentMode;
    private String AccCcy;
    private String TranzCcy;
	private String TranzType;
    
    
	public Settings() {
		super();
	}

	public Settings(String paymentMode, String accCcy, String tranzCcy,
			String tranzType) {
		super();
		PaymentMode = paymentMode;
		AccCcy = accCcy;
		TranzCcy = tranzCcy;
		TranzType = tranzType;
	}

	public String getPaymentMode() {
		return PaymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		PaymentMode = paymentMode;
	}
	public String getAccCcy() {
		return AccCcy;
	}
	public void setAccCcy(String accCcy) {
		AccCcy = accCcy;
	}
	public String getTranzCcy() {
		return TranzCcy;
	}
	public void setTranzCcy(String tranzCcy) {
		TranzCcy = tranzCcy;
	}
	public String getTranzType() {
		return TranzType;
	}
	public void setTranzType(String tranzType) {
		TranzType = tranzType;
	}
}
