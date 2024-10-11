package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rush
 * PAYMENT_MODE		string(1)		Следующие значения PAYMENT_MODE позволены: 0,1,2,3 *
 * ACCOUNT_NO		decimal(20,0)	Внутренний номер счета.
 * CARD_ACCT		string(20)		Номер счета карты.
 * CARD_ACCT_CCY	string(3)		Валюта счета (алфавитный код).
 * CARD				string(19) 		Номер карты (PAN)
 * EXECUTE_ON		dateTime		Дата выполнения транзакции
 * TRAN_TYPE		string(3)		Тип транзакции *
 * TRAN_CCY			string(3)		Код валюты транзакции *
 * TRAN_AMNT		decimal(12,0)	Сумма транзакции. *
 * BRANCH			string(5)	 	Код филиала
 * BATCH_NR			string(7)	 	Номер пакета
 * SLIP_NR			string(8)	 	Номер квитанции 
 * DEAL_DESC		string(140)		Описание транзакции
 * COUNTERPARTY		string(200)		Второй (противоположный) участник сделки
 * INTERNAL_NO		decimal(12,0)	Внутренний номер строки.
 * BANK_C			string(2)		Код банка – владельца строки *
 * GROUPC			string(2)		Код группы карт – локальный для каждого банка *
 * TRAN_DATE_TIME	dateTime		Дата и время транзакции
 * EXECUTION_TYPE	decimal(2,0)	Тип выполнения транзакции
 * BOOKING_MSG		string(4000)	Подробная информация о проводке
 * TR_CODE			string(3)		Код комиссии за транзакцию
 * TR_FEE			decimal(12,0)	Комиссия за транзакцию
 * TR_CODE2			string(3)		Код комиссии за транзакцию – 2
 * TR_FEE2			decimal(12,0)	Комиссия за транзакцию – 2
 * TR_CODE3			string(3)		Код комиссии за транзакцию – 3
 * TR_FEE3			decimal(12,0)	Комиссия за транзакцию 3
 * TR_CODE4			string(3)		Код комиссии за транзакцию – 4
 * TR_FEE4			decimal(12,0)	Комиссия за транзакцию 4
 * TR_CODE5			string(3)		Код комиссии за транзакцию – 5
 * TR_FEE5			decimal(12,0)	Комиссия за транзакцию – 5
 * TR_CODE6			string(3)		Код комиссии за транзакцию – 6
 * TR_FEE6			decimal(12,0)	Комиссия за транзакцию – 6
 * TR_CODE7			string(3)		Код комиссии за транзакцию – 7
 * TR_FEE7			decimal(12,0)	Комиссия за транзакцию – 7
 * TR_CODE8			string(3)		Код комиссии за транзакцию – 8
 * TR_FEE8			decimal(12,0)	Комиссия за транзакцию – 8
 * TR_CODE9			string(3)		Код комиссии за транзакцию – 9
 * TR_FEE9			decimal(12,0)	Комиссия за транзакцию – 9
 * TR_CODE10		string(3)		Код комиссии за транзакцию – 10
 * TR_FEE10			decimal(12,0)	Комиссия за транзакцию – 10
 * CHECK_DUPL		decimal(1)		Проверка дублирующих транзакций
 * INSTL_AGR_NO		decimal(2,0)	Номер договора инстолментов (Installment).
 * ACCNT_TYPE		decimal(1)		Тег используется для установки типа счета – карточный или банковский счет
 */
@NoArgsConstructor
@AllArgsConstructor
public class ExecTransactionRequest {
	@Getter
	@Setter
	private String payment_mode;
	@Getter
	@Setter
	private BigDecimal account_no;
	@Getter
	@Setter
	private String card_acct;
	@Getter
	@Setter
	private String card_acct_ccy;
	@Getter
	@Setter
	private String card;
	@Getter
	@Setter
	private Date execute_on;
	@Getter
	@Setter
	private String tran_type;
	@Getter
	@Setter
	private String tran_ccy;
	@Getter
	@Setter
	private BigDecimal tran_amnt;
	@Getter
	@Setter
	private String branch;
	@Getter
	@Setter
	private String batch_nr;
	@Getter
	@Setter
	private String slip_nr;
	@Getter
	@Setter
	private String deal_desc;
	@Getter
	@Setter
	private String counterparty;
	@Getter
	@Setter
	private BigDecimal internal_no;
	@Getter
	@Setter
	private String bank_c;
	@Getter
	@Setter
	private String groupc;
	@Getter
	@Setter
	private Date tran_date_time;
	@Getter
	@Setter
	private BigDecimal execution_type;
	@Getter
	@Setter
	private String booking_msg;
	@Getter
	@Setter
	private String tr_code;
	@Getter
	@Setter
	private BigDecimal tr_fee;
	@Getter
	@Setter
	private String tr_code2;
	@Getter
	@Setter
	private BigDecimal tr_fee2;
	@Getter
	@Setter
	private String tr_code3;
	@Getter
	@Setter
	private BigDecimal tr_fee3;
	@Getter
	@Setter
	private String tr_code4;
	@Getter
	@Setter
	private BigDecimal tr_fee4;
	@Getter
	@Setter
	private String tr_code5;
	@Getter
	@Setter
	private BigDecimal tr_fee5;
	@Getter
	@Setter
	private String tr_code6;
	@Getter
	@Setter
	private BigDecimal tr_fee6;
	@Getter
	@Setter
	private String tr_code7;
	@Getter
	@Setter
	private BigDecimal tr_fee7;
	@Getter
	@Setter
	private String tr_code8;
	@Getter
	@Setter
	private BigDecimal tr_fee8;
	@Getter
	@Setter
	private String tr_code9;
	@Getter
	@Setter
	private BigDecimal tr_fee9;
	@Getter
	@Setter
	private String tr_code10;
	@Getter
	@Setter
	private BigDecimal tr_fee10;
	@Getter
	@Setter
	private BigDecimal check_dupl;
	@Getter
	@Setter
	private BigDecimal instl_agr_no;
	@Getter
	@Setter
	private BigDecimal accnt_type;
}
