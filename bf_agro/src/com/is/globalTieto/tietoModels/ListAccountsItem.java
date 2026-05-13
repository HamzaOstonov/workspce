package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rush
 * CARD_ACCT	String(34)		Номер счета карты *
 * TRANZ_ACCT	String(34)		Номер счета для маршрутизации на банковскую систему
 * STATUS		String(1)		Статус счета 0,3,4 *
 * ACC_PRTY		String(1)		Приоритет счета
 * C_ACCNT_TYPE	String(2)		Тип счета
 * CCY			String(3)		Валюта счета (алфавитный код) *
 * COND_SET		String(3)		Условия счета
 * CLIENT_B		String(19)		Код клиента
 * CLIENT		String(8)		Клиентский номер держателя карты
 * F_NAMES		String(34)		Имя
 * SURNAME		String(20)		Фамилия
 * B_BR_ID		Decimal(7,0)	Идентификатор филиала
 * OFFICE_ID	Decimal(7,0)	Идентификатор офиса
 * MAIN_ROW		Decimal(20,0)	Внутренний идентификатор для всех счетов, связанных с картой (договором), кроме счета loyalty 
 * ACCOUNT_NO	Decimal(20,0)	Внутренний номер счета
 * BANK_C		String(2)		Код банка – владельца строки
 * GROUPC		String(2)		Код группы карт – локальный для каждого банка
 * CARD_ACCT_B	String(2)		Номер счета карты в банке

 */
@NoArgsConstructor
@AllArgsConstructor
public class ListAccountsItem {
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
	@Getter
	@Setter
	private String bank_c;
	@Getter
	@Setter
	private String groupc;

}
