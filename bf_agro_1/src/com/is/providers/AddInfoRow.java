package com.is.providers;
public class AddInfoRow {
	
	private String key;
	private String value;
	private int confirm;

	public AddInfoRow() {
		super();
	}

	public AddInfoRow(String key, String value, int confirm) {
		super();
		this.key = key;
		this.value = value;
		this.confirm = confirm;
	}

	public AddInfoRow(String key, String value)
	{
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getConfirm()
	{
		return confirm;
	}

	public void setConfirm(int confirm)
	{
		this.confirm = confirm;
	}



}
