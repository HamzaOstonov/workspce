package com.is.bpri.models;

import java.util.Date;

public class Client {
	
	private String name;
	private String last_name;
	private Date birthday;
	private Integer sex;
	private String post_adress;
	private String city;
	private String country;
	private String phone;
	private String type_document;
	private String serial_doc;
	private String num_doc;
	private Date date_doc;
	private String temp;
	
	public Client() {
	
	}
	
	public Client(String name, String last_name, Date birthday, Integer sex,
			String post_adress, String city, String country, String phone,
			String type_document, String serial_doc, String num_doc,
			Date date_doc, String temp) {
		super();
		this.name = name;
		this.last_name = last_name;
		this.birthday = birthday;
		this.sex = sex;
		this.post_adress = post_adress;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.type_document = type_document;
		this.serial_doc = serial_doc;
		this.num_doc = num_doc;
		this.date_doc = date_doc;
		this.temp = temp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPost_adress() {
		return post_adress;
	}

	public void setPost_adress(String post_adress) {
		this.post_adress = post_adress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType_document() {
		return type_document;
	}

	public void setType_document(String type_document) {
		this.type_document = type_document;
	}

	public String getSerial_doc() {
		return serial_doc;
	}

	public void setSerial_doc(String serial_doc) {
		this.serial_doc = serial_doc;
	}

	public String getNum_doc() {
		return num_doc;
	}

	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
	}

	public Date getDate_doc() {
		return date_doc;
	}

	public void setDate_doc(Date date_doc) {
		this.date_doc = date_doc;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}
	
}
