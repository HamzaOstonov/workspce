package com.is.globalTieto.tietoModels;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor
@AllArgsConstructor
public class ListCustomersItem {
	@Getter
	@Setter
	private String client;
	@Getter
	@Setter
	private String status;
	@Getter
	@Setter
	private String client_b;
	@Getter
	@Setter
	private String f_names;
	@Getter
	@Setter
	private String surname;
	@Getter
	@Setter
	private String person_code;
	@Getter
	@Setter
	private Date b_date;
	@Getter
	@Setter
	private String r_street;
	@Getter
	@Setter
	private String r_city;
	@Getter
	@Setter
	private String r_cntry;
	@Getter
	@Setter
	private String r_pcode;
	@Getter
	@Setter
	private String r_e_mails;
	@Getter
	@Setter
	private String r_mob_phone;
	@Getter
	@Setter
	private String card;
	@Getter
	@Setter
	private String bank_c;
	@Getter
	@Setter
	private String u_cod1;
	@Getter
	@Setter
	private String a3vts_county;
	@Getter
	@Setter
	private String a3vts_flag1;
	@Getter
	@Setter
	private String a3vts_flag2;
	@Getter
	@Setter
	private String supplementary_pan;
	@Getter
	@Setter
	private String supplementary_cvv2;
	@Getter
	@Setter
	private Date supplementary_expiry;
	@Getter
	@Setter
	private String notes;
	@Getter
	@Setter
	private String emp_name;	
}
