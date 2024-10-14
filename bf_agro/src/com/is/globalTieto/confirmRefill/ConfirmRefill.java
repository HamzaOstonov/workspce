package com.is.globalTieto.confirmRefill;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* ID				number 			Id
* CARD_ACCT 		varchar2(20)	Номер счета карты
* CARD				varchar2(19)	Номер карты (PAN)
* TRANSACTION_AMNT	number			Сумма транзакции
* BRANCH			varchar2(64)	Филиал банка
* CLIENT_TIETO		varchar2(128)	Клиентский номер держателя карты
* CLIENT_BANK		varchar2(128)	Номер клиента в банке
* STATE				number(2)		Состояние
* PAYMENT_MODE		varchar2(1)		Следующие значения PAYMENT_MODE позволены: 0,1,2,3
* BANK_C			varchar2(2)		Код банка – владельца строки
* GROUPC			varchar2(2)		Код группы карт – локальный для каждого банка
* CARD_ACC_CCY		varchar2(3)		Валюта счета (алфавитный код)
* TRAN_TYPE			varchar2(3)		Тип транзакции
* TRAN_CCY			varchar2(3)		Код валюты транзакции
* CLIENT_NAME		varchar2(34)	Имя клиента
* CLIENT_SURNAME	varchar2(20)	Фамилия клиента
 */

@NoArgsConstructor
@AllArgsConstructor
public class ConfirmRefill {
	@Getter
	@Setter
	Long id;
	@Getter
	@Setter
	String card_acct;
	@Getter
	@Setter
	String card;
	@Getter
	@Setter
	BigDecimal transaction_amnt;
	@Getter
	@Setter
	String branch;
	@Getter
	@Setter
	String client_tieto;
	@Getter
	@Setter
	String client_bank;
	@Getter
	@Setter
	BigDecimal state;
	@Getter
	@Setter
	String payment_mode;
	@Getter
	@Setter
	String bank_c;
	@Getter
	@Setter
	String groupc;
	@Getter
	@Setter
	String card_acc_ccy;
	@Getter
	@Setter
	String tran_type;
	@Getter
	@Setter
	String tran_ccy;
	@Getter
	@Setter
	String client_name;
	@Getter
	@Setter
	String client_surname;
	@Getter
    @Setter
    Long is_cash_payment;
}
