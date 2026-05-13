package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rush
 * CARD_ACCT	string(34)		Номер счета карты *
 * TRANZ_ACCT	string(34)		Номер счета для маршрутизации к банковской системе *
 * STATUS		string(1)		Статус счета 0,3,4
 * ACC_PRTY		string(1)		Приоритет счета 
 * C_ACCNT_TYPE	string(2)		Код тип счета
 * CCY			string(3)		Валюта счета
 * COND_SET		string(3)		Набор финансовых условий, общих для группы счетов
 * CLIENT_B		string(19)		Номер клиента в банке *
 * CLIENT		string(8)		Клиентский номер держателя карты *
 * F_NAMES		string(34)		Имя *
 * SURNAME		string(20)		Фамилия *
 * B_BR_ID		decimal(7,0)	Внутренний номер филиала банка *
 * OFFICE_ID	decimal(7,0)	Внутренний номер офиса филиала банка *
 * MAIN_ROW		decimal(20,0)	Внутренний идентификатор для всех счетов, связанных с картой (договором), кроме счета loyalty *
 * ACCOUNT_NO	decimal(20,0)	Внутренний номер счета *

 */
@NoArgsConstructor
@AllArgsConstructor
public class ListAccountsFilter {
	@Getter
	@Setter
	private String card_acct;
	@Getter
	@Setter
	private String tranz_acct;
	@Getter
	@Setter
	private String status;
	@Getter
	@Setter
	private String acc_prty;
	@Getter
	@Setter
	private String c_accnt_type;
	@Getter
	@Setter
	private String ccy;
	@Getter
	@Setter
	private String cond_set;
	@Getter
	@Setter
	private String client_b;
	@Getter
	@Setter
	private String client;
	@Getter
	@Setter
	private String f_names;
	@Getter
	@Setter
	private String surname;
	@Getter
	@Setter
	private BigDecimal b_br_id;
	@Getter
	@Setter
	private BigDecimal office_id;
	@Getter
	@Setter
	private BigDecimal main_row;
	@Getter
	@Setter
	private BigDecimal account_no;

}
