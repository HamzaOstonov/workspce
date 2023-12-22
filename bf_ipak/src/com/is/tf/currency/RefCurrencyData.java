package com.is.tf.currency;

public class RefCurrencyData {
    private String kod;
    private String currency;
    private Double course;
	
    public RefCurrencyData(String kod, String currency, Double course) {
		super();
		this.kod = kod;
		this.currency = currency;
		this.course = course;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getCourse() {
		return course;
	}

	public void setCourse(Double course) {
		this.course = course;
	}

}