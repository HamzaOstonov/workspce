package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.globalTieto.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rush
 * ID				decimal			Id
 * AGRE_NOM			decimal(22,0)	Внутренний номер договора
 * BINCOD			string(9)		BIN код
 * BANK_CODE		string(2)		Код банка – для договора
 * BRANCH			string(5)		Код филиала
 * B_BR_ID			decimal(22,0)	Идентификатор филиала – внутренний номер
 * OFFICE			string(8)		Название офиса
 * OFFICE_ID		decimal(7,0)	Код офиса
 * CITY				string(20)		Почтовый адрес – город
 * CLIENT			string(8)		Номер клиента в банка *
 * COMENT			string(200)		Комментарий пользователя
 * CONTRACT			string(15)		Номер договора.
 * COUNTRY			string(3)		Почтовый адрес – страна
 * CTIME			dateTime		Дата и время (по умолчанию sysdate) ввода или обновления 
 * DISTRIB_MODE		string(2)		Client Report Distribution Mode
 * ENROLLED			dateTime		Дата начала участия в договоре *
 * E_MAILS			string(100)		Адрес электронной почты для отправления отчета клиенту
 * IN_FILE_NUM		decimal(7,0)	Идентификатор задания пакета (Batch task ID)
 * ISURANCE_TYPE	string(2)		Тип страховки
 * POST_IND			string(7)		Почтовый адрес – почтовый индекс
 * PRODUCT			string(10)		Код продукта *
 * REP_LANG			string(1)		Язык отчета
 * RISK_LEVEL		string(5)		Уровень риска
 * STATUS			string(2)		Статус договора
 * STREET			string(60)		Почтовый адрес – улица, дом, квартира
 * USRID			string(6)		Идентификационный номер пользователя
 * U_COD4			string(10)		Заполняемый пользователем код 4
 * U_CODE5			string(6)		Заполняемый пользователем код 5
 * U_CODE6			string(6)		Заполняемый пользователем код 6
 * U_FIELD3			string(20)		Заполняемое пользователем поле 3
 * U_FIELD4			string(20)		Заполняемое пользователем поле 4
 * COMBI_ID			Decimal(10,0)	Поле используется только для определенного решения
 * COMBI_TYPE		String(20)		Поле используется только для определенного решения
 */
@NoArgsConstructor
@AllArgsConstructor
public class Agreement {
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private BigDecimal agre_nom;
	@Getter
	@Setter
	private String bincod;
	@Getter
	@Setter
	private String bank_code;
	@Getter
	@Setter
	private String branch;
	@Getter
	@Setter
	private BigDecimal b_br_id;
	@Getter
	@Setter
	private String office;
	@Getter
	@Setter
	private BigDecimal office_id;
	@Getter
	@Setter
	private String city;
	@Getter
	@Setter
	private String client;
	@Getter
	@Setter
	private String coment;
	@Getter
	@Setter
	private String contract;
	@Getter
	@Setter
	private String country;
	@Getter
	@Setter
	private Date ctime;
	@Getter
	@Setter
	private String distrib_mode;
	@Getter
	@Setter
	private Date enrolled;
	@Getter
	@Setter
	private String e_mails;
	@Getter
	@Setter
	private BigDecimal in_file_num;
	@Getter
	@Setter
	private String isurance_type;
	@Getter
	@Setter
	private String post_ind;
	@Getter
	@Setter
	private String product;
	@Getter
	@Setter
	private String rep_lang;
	@Getter
	@Setter
	private String risk_level;
	@Getter
	@Setter
	private String status;
	@Getter
	@Setter
	private String street;
	@Getter
	@Setter
	private String usrid;
	@Getter
	@Setter
	private String u_cod4;
	@Getter
	@Setter
	private String u_code5;
	@Getter
	@Setter
	private String u_code6;
	@Getter
	@Setter
	private String u_field3;
	@Getter
	@Setter
	private String u_field4;
	@Getter
	@Setter
	private BigDecimal combi_id;
	@Getter
	@Setter
	private String combi_type;
	@Getter
	@Setter
	private List<Account> accounts = new ArrayList<Account>();
}
