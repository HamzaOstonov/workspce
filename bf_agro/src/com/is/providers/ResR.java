package com.is.providers;
public class ResR {
	private long amount;
	private int quantity;

	public ResR() {
		super();
	}

	public ResR(long amount, int quantity) {
		super();
		this.amount = amount;
		this.quantity = quantity;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
