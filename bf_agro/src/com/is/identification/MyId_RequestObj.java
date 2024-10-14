package com.is.identification;

public class MyId_RequestObj {
	
	private String type;
	private String value;
	private String birth_date;
	private String photo;
	
	public MyId_RequestObj() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyId_RequestObj(String type, String value, String birth_date, String photo) {
		super();
		this.type = type;
		this.value = value;
		this.birth_date = birth_date;
		this.photo = photo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "{"
        + "\"type\": \"" + type + "\" , "
        + "\"value\": \"" + value + "\" , "
        + "\"birth_date\": \"" + birth_date + "\" , "
        + "\"photo\":  \"" + photo + "\" "
        + "}";
		
			
		
	}	
}
