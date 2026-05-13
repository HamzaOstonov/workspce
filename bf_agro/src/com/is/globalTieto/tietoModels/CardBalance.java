package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* CARD			String(19)		Номер карты
* EXPIRY1		dateTime		Дата завершения срока действия карты
* CARD_STATUS	String(1)		Статус карты
* ACCOUNT_NO	decimal(20,0)	Внутренний номер счета
* CARD_ACCT		String(34)		Номер счета карты
* ACC_STATUS	String(1)		Статус счета
* CCY			String(3)		Валюта счета (алфавитный счет)
* END_BAL		decimal(12,0)	Конечный баланс счета
* LOCKED_AMOUNT	decimal(22,0)	Блокированная сумма 
* AVAIL_AMOUNT	decimal(12,0)	Доступная сумма
* C_ACCNT_TYPE	String(2)		Тип счета (Пояснение элемента дано в разделе: 4.3. ACCOUNTS)
* STOP_CAUSE	String(1)		Причина остановки карты (пояснение элемента см. В разделе: 4.4. CARDS)
* MAIN_ROW		decimal(20,0)	Ссылка на счет с наивысшим приоритетом
* BANK_C		String(2)		Код банка – владельца строки
* GROUPC		String(2)		Код группы карт – локальный для каждого банка
* CCY_EXP		string(1)		Экспонента валюты – количество десятичных цифр в сумме валюты счета
* CRD			decimal(12,0)	Кредитный лимит счета.
* CRD_EXPIRY	dateTime		Дата завершения срока действия кредита. 
 */

@NoArgsConstructor
@AllArgsConstructor
public class CardBalance {
	@Getter
	@Setter
	private  String card;
	@Getter
	@Setter
	private  Date expiry1;
	@Getter
	@Setter
	private  String card_status;
	@Getter
	@Setter
	private  BigDecimal account_no;
	@Getter
	@Setter
	private  String card_acct;
	@Getter
	@Setter
	private  String acc_status;
	@Getter
	@Setter
	private  String ccy;
	@Getter
	@Setter
	private  BigDecimal end_bal;
	@Getter
	@Setter
	private  BigDecimal locked_amount;
	@Getter
	@Setter
	private  BigDecimal avail_amount;
	@Getter
	@Setter
	private  String c_accnt_type;
	@Getter
	@Setter
	private  String stop_cause;
	@Getter
	@Setter
	private  BigDecimal main_row;
	@Getter
	@Setter
	private  String bank_c;
	@Getter
	@Setter
	private  String groupc;
	@Getter
	@Setter
	private  String ccy_exp;
	@Getter
	@Setter
	private  BigDecimal crd;
	@Getter
	@Setter
	private  Date crd_expiry;

}
