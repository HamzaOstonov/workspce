package com.is.qr_online.transaction;

public class Localized_info {
	private String local_lang;
	private String local_name;
	private String local_city;

	public Localized_info(String local_lang, String local_name, String local_city) {
		super();
		this.local_lang = local_lang;
		this.local_name = local_name;
		this.local_city = local_city;
	}

	public String getLocal_lang() {
		return local_lang;
	}

	public void setLocal_lang(String local_lang) {
		this.local_lang = local_lang;
	}

	public String getLocal_name() {
		return local_name;
	}

	public void setLocal_name(String local_name) {
		this.local_name = local_name;
	}

	public String getLocal_city() {
		return local_city;
	}

	public void setLocal_city(String local_city) {
		this.local_city = local_city;
	}
	
	public String toString() {
		return "{"  + "\"local_lang\":\"" + local_lang + "\", " 
					+ "\"local_name\":\"" + local_name + "\", "
				    + "\"local_city\":\"" + local_city + "\" " + "}";
	}

}
