package com.is.qr_online.merchant;

public class MerchantFilter {

	private String payee_id;
	private String id;
	private String activity;
	private String name;
	private String country;
	private String city;
	private String postal_code;
	private String phone_number;
	private String email;

	public MerchantFilter(String activity, String name, String country, String city, String postal_code, String phone_number,
			String email) {
		super();
		this.activity = activity;
		this.name = name;
		this.country = country;
		this.city = city;
		this.postal_code = postal_code;
		this.phone_number = phone_number;
		this.email = email;
	}

	public MerchantFilter(String payee_id, String activity, String name, String country, String city, String postal_code,
			String phone_number, String email) {
		super();
		this.payee_id = payee_id;
		this.activity = activity;
		this.name = name;
		this.country = country;
		this.city = city;
		this.postal_code = postal_code;
		this.phone_number = phone_number;
		this.email = email;
	}

	public MerchantFilter() {

	}

	public String getPayee_id() {
		return payee_id;
	}

	public void setPayee_id(String payee_id) {
		this.payee_id = payee_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return "{" + "\"activity\":\"" + activity + "\", " + "\"name\":\"" + name + "\", " + "\"country\":\"" + country
				+ "\", " + "\"city\":\"" + city + "\", " + "\"postal_code\":\"" + postal_code + "\", "
				+ "\"phone_number\":\"" + phone_number + "\", " + "\"email\":\"" + email + "\" " + "}";
	}

}
