package com.is.tieto_visae.tieto;

import java.util.Date;

/**
 * @author Rush
 * CLIENT					string(8)	Клиентский номер держателя карты.
 * STATUS					string(2)	Статус клиента
 * CLIENT_B					string(19)	Номер клиента в банке.
 * F_NAMES					string(34)	Имя.
 * SURNAME					string(20)	Фамилия.
 * PERSON_CODE				string(20)	Код личности (код плательщика налогов).
 * B_DATE					dateTime	Дата рождения клиента.
 * R_STREET					string(95)	Адрес проживания – улица, дом, квартира.
 * R_CITY					string(20)	Адрес проживания – город.
 * R_CNTRY					string(3)	Адрес проживания – страна.
 * R_PCODE					string(7)	Адрес проживания – почтовый индекс.
 * R_E_MAILS				string(100)	Адрес электронной почты.
 * R_MOB_PHONE				string(15)	Номер мобильного телефона.
 * CARD						string(19)	Номер карточки.
 * BANK_C					string(2)	Код банка.
 * U_COD1					string(3)	Заполняемый пользователем код 1.
 * A3VTS_COUNTY				string(95)	Улица.
 * A3VTS_FLAG1				string(1)	Первый символ из Apartment number.
 * A3VTS_FLAG2				string(1)	Второй символ из Apartment number.
 * SUPPLEMENTARY_PAN		string(20)	Заполняемое пользователем поле 1.
 * SUPPLEMENTARY_CVV2		string(20)	Заполняемое пользователем поле 2.
 * SUPPLEMENTARY_EXPIRY		dateTime	Дата, когда клиент начал работу у работодателя.
 * NOTES					string(400)	Комментарий пользователя.
 * EMP_NAME					string(34)	Название работодателя клиента.

 */

public class ListCustomersItem {
	private String client;
	private String status;
	private String client_b;
	private String f_names;
	private String surname;
	private String person_code;
	private Date b_date;
	private String r_street;
	private String r_city;	
	private String r_cntry;
	private String r_pcode;
	private String r_e_mails;
	private String r_mob_phone;
	private String card;
	private String bank_c;
	private String u_cod1;
	private String a3vts_county;
	private String a3vts_flag1;
	private String a3vts_flag2;
	private String supplementary_pan;
	private String supplementary_cvv2;
	private Date supplementary_expiry;
	private String notes;
	private String emp_name;
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClient_b() {
		return client_b;
	}
	public void setClient_b(String client_b) {
		this.client_b = client_b;
	}
	public String getF_names() {
		return f_names;
	}
	public void setF_names(String f_names) {
		this.f_names = f_names;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPerson_code() {
		return person_code;
	}
	public void setPerson_code(String person_code) {
		this.person_code = person_code;
	}
	public Date getB_date() {
		return b_date;
	}
	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	public String getR_street() {
		return r_street;
	}
	public void setR_street(String r_street) {
		this.r_street = r_street;
	}
	public String getR_city() {
		return r_city;
	}
	public void setR_city(String r_city) {
		this.r_city = r_city;
	}
	public String getR_cntry() {
		return r_cntry;
	}
	public void setR_cntry(String r_cntry) {
		this.r_cntry = r_cntry;
	}
	public String getR_pcode() {
		return r_pcode;
	}
	public void setR_pcode(String r_pcode) {
		this.r_pcode = r_pcode;
	}
	public String getR_e_mails() {
		return r_e_mails;
	}
	public void setR_e_mails(String r_e_mails) {
		this.r_e_mails = r_e_mails;
	}
	public String getR_mob_phone() {
		return r_mob_phone;
	}
	public void setR_mob_phone(String r_mob_phone) {
		this.r_mob_phone = r_mob_phone;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getBank_c() {
		return bank_c;
	}
	public void setBank_c(String bank_c) {
		this.bank_c = bank_c;
	}
	public String getU_cod1() {
		return u_cod1;
	}
	public void setU_cod1(String u_cod1) {
		this.u_cod1 = u_cod1;
	}
	public String getA3vts_county() {
		return a3vts_county;
	}
	public void setA3vts_county(String a3vts_county) {
		this.a3vts_county = a3vts_county;
	}
	public String getA3vts_flag1() {
		return a3vts_flag1;
	}
	public void setA3vts_flag1(String a3vts_flag1) {
		this.a3vts_flag1 = a3vts_flag1;
	}
	public String getA3vts_flag2() {
		return a3vts_flag2;
	}
	public void setA3vts_flag2(String a3vts_flag2) {
		this.a3vts_flag2 = a3vts_flag2;
	}
	public String getSupplementary_pan() {
		return supplementary_pan;
	}
	public void setSupplementary_pan(String supplementary_pan) {
		this.supplementary_pan = supplementary_pan;
	}
	public String getSupplementary_cvv2() {
		return supplementary_cvv2;
	}
	public void setSupplementary_cvv2(String supplementary_cvv2) {
		this.supplementary_cvv2 = supplementary_cvv2;
	}
	public Date getSupplementary_expiry() {
		return supplementary_expiry;
	}
	public void setSupplementary_expiry(Date supplementary_expiry) {
		this.supplementary_expiry = supplementary_expiry;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public ListCustomersItem(String client, String status, String client_b, String f_names, String surname,
			String person_code, Date b_date, String r_street, String r_city, String r_cntry, String r_pcode,
			String r_e_mails, String r_mob_phone, String card, String bank_c, String u_cod1, String a3vts_county,
			String a3vts_flag1, String a3vts_flag2, String supplementary_pan, String supplementary_cvv2,
			Date supplementary_expiry, String notes, String emp_name) {
		super();
		this.client = client;
		this.status = status;
		this.client_b = client_b;
		this.f_names = f_names;
		this.surname = surname;
		this.person_code = person_code;
		this.b_date = b_date;
		this.r_street = r_street;
		this.r_city = r_city;
		this.r_cntry = r_cntry;
		this.r_pcode = r_pcode;
		this.r_e_mails = r_e_mails;
		this.r_mob_phone = r_mob_phone;
		this.card = card;
		this.bank_c = bank_c;
		this.u_cod1 = u_cod1;
		this.a3vts_county = a3vts_county;
		this.a3vts_flag1 = a3vts_flag1;
		this.a3vts_flag2 = a3vts_flag2;
		this.supplementary_pan = supplementary_pan;
		this.supplementary_cvv2 = supplementary_cvv2;
		this.supplementary_expiry = supplementary_expiry;
		this.notes = notes;
		this.emp_name = emp_name;
	}
	public ListCustomersItem() {
		super();
	}	
}
