package com.is.qr_online.transaction;

public class TransactionFilter {

	private String merchant_id;
	private String id;

	private String currency;
	private String amount;
	private String category;
	private String product_code;
	private String product_name;
	private String fee_type;
	private String fee_amount;
	private String fee_percent;

	private String result_code;
	private String result_message;
	private String format;

	private int code_type;

	public TransactionFilter(String currency, String amount, String category, String product_code, String product_name,
			String fee_type, String fee_amount, String fee_percent) {
		super();
		this.currency = currency;
		this.amount = amount;
		this.category = category;
		this.product_code = product_code;
		this.product_name = product_name;
		this.fee_type = fee_type;
		this.fee_amount = fee_amount;
		this.fee_percent = fee_percent;
	}

	public TransactionFilter(String merchant_id, String id, String currency, String amount, String category,
			String product_code, String product_name, String fee_type, String fee_amount, String fee_percent) {
		super();
		this.id = id;
		this.merchant_id = merchant_id;
		this.currency = currency;
		this.amount = amount;
		this.category = category;
		this.product_code = product_code;
		this.product_name = product_name;
		this.fee_type = fee_type;
		this.fee_amount = fee_amount;
		this.fee_percent = fee_percent;
	}

	public TransactionFilter(String merchant_id, String id, String currency, String amount, String category,
			String product_code, String product_name, String fee_type, String fee_amount, String fee_percent,
			String result_code, String result_message, String format) {
		super();
		this.merchant_id = merchant_id;
		this.id = id;
		this.currency = currency;
		this.amount = amount;
		this.category = category;
		this.product_code = product_code;
		this.product_name = product_name;
		this.fee_type = fee_type;
		this.fee_amount = fee_amount;
		this.fee_percent = fee_percent;
		this.format = format;
		this.result_code = result_code;
		this.result_message = result_message;
	}

	public TransactionFilter() {
		super();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getResult_message() {
		return result_message;
	}

	public void setResult_message(String result_message) {
		this.result_message = result_message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getFee_amount() {
		return fee_amount;
	}

	public void setFee_amount(String fee_amount) {
		this.fee_amount = fee_amount;
	}

	public String getFee_percent() {
		return fee_percent;
	}

	public void setFee_percent(String fee_percent) {
		this.fee_percent = fee_percent;
	}

	public int getCode_type() {
		return code_type;
	}

	public void setCode_type(int code_type) {
		this.code_type = code_type;
	}

	public String toString() {
		return "{" + "\"currency\":\"" + currency + "\", " + "\"amount\":\"" + amount + "\", " + "\"category\":\""
				+ category + "\", " + "\"product_code\":\"" + product_code + "\", " + "\"product_name\":\""
				+ product_name + "\", " + "\"fee_type\":\"" + fee_type + "\", " + "\"fee_amount\":\"" + fee_amount
				+ "\", " + "\"fee_percent\":\"" + fee_percent + "\" " + "}";
	}

}
