package com.is.tieto_visa.tieto;

import java.math.BigDecimal;
import java.util.Date;


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

public class ExecTransactionRequest {
	private String payment_mode;
	private BigDecimal account_no;
	private String card_acct;
	private String card_acct_ccy;
	private String card;
	private Date execute_on;
	private String tran_type;
	private String tran_ccy;
	private BigDecimal tran_amnt;
	private String branch;
	private String batch_nr;
	private String slip_nr;
	private String deal_desc;
	private String counterparty;
	private BigDecimal internal_no;
	private String bank_c;
	private String groupc;
	private Date tran_date_time;
	private BigDecimal execution_type;
	private String booking_msg;
	private String tr_code;
	private BigDecimal tr_fee;
	private String tr_code2;
	private BigDecimal tr_fee2;
	private String tr_code3;
	private BigDecimal tr_fee3;
	private String tr_code4;
	private BigDecimal tr_fee4;
	private String tr_code5;
	private BigDecimal tr_fee5;
	private String tr_code6;
	private BigDecimal tr_fee6;
	private String tr_code7;
	private BigDecimal tr_fee7;
	private String tr_code8;
	private BigDecimal tr_fee8;
	private String tr_code9;
	private BigDecimal tr_fee9;
	private String tr_code10;
	private BigDecimal tr_fee10;
	private BigDecimal check_dupl;
	private BigDecimal instl_agr_no;
	private BigDecimal accnt_type;
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public BigDecimal getAccount_no() {
		return account_no;
	}
	public void setAccount_no(BigDecimal account_no) {
		this.account_no = account_no;
	}
	public String getCard_acct() {
		return card_acct;
	}
	public void setCard_acct(String card_acct) {
		this.card_acct = card_acct;
	}
	public String getCard_acct_ccy() {
		return card_acct_ccy;
	}
	public void setCard_acct_ccy(String card_acct_ccy) {
		this.card_acct_ccy = card_acct_ccy;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public Date getExecute_on() {
		return execute_on;
	}
	public void setExecute_on(Date execute_on) {
		this.execute_on = execute_on;
	}
	public String getTran_type() {
		return tran_type;
	}
	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}
	public String getTran_ccy() {
		return tran_ccy;
	}
	public void setTran_ccy(String tran_ccy) {
		this.tran_ccy = tran_ccy;
	}
	public BigDecimal getTran_amnt() {
		return tran_amnt;
	}
	public void setTran_amnt(BigDecimal tran_amnt) {
		this.tran_amnt = tran_amnt;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getBatch_nr() {
		return batch_nr;
	}
	public void setBatch_nr(String batch_nr) {
		this.batch_nr = batch_nr;
	}
	public String getSlip_nr() {
		return slip_nr;
	}
	public void setSlip_nr(String slip_nr) {
		this.slip_nr = slip_nr;
	}
	public String getDeal_desc() {
		return deal_desc;
	}
	public void setDeal_desc(String deal_desc) {
		this.deal_desc = deal_desc;
	}
	public String getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}
	public BigDecimal getInternal_no() {
		return internal_no;
	}
	public void setInternal_no(BigDecimal internal_no) {
		this.internal_no = internal_no;
	}
	public String getBank_c() {
		return bank_c;
	}
	public void setBank_c(String bank_c) {
		this.bank_c = bank_c;
	}
	public String getGroupc() {
		return groupc;
	}
	public void setGroupc(String groupc) {
		this.groupc = groupc;
	}
	public Date getTran_date_time() {
		return tran_date_time;
	}
	public void setTran_date_time(Date tran_date_time) {
		this.tran_date_time = tran_date_time;
	}
	public BigDecimal getExecution_type() {
		return execution_type;
	}
	public void setExecution_type(BigDecimal execution_type) {
		this.execution_type = execution_type;
	}
	public String getBooking_msg() {
		return booking_msg;
	}
	public void setBooking_msg(String booking_msg) {
		this.booking_msg = booking_msg;
	}
	public String getTr_code() {
		return tr_code;
	}
	public void setTr_code(String tr_code) {
		this.tr_code = tr_code;
	}
	public BigDecimal getTr_fee() {
		return tr_fee;
	}
	public void setTr_fee(BigDecimal tr_fee) {
		this.tr_fee = tr_fee;
	}
	public String getTr_code2() {
		return tr_code2;
	}
	public void setTr_code2(String tr_code2) {
		this.tr_code2 = tr_code2;
	}
	public BigDecimal getTr_fee2() {
		return tr_fee2;
	}
	public void setTr_fee2(BigDecimal tr_fee2) {
		this.tr_fee2 = tr_fee2;
	}
	public String getTr_code3() {
		return tr_code3;
	}
	public void setTr_code3(String tr_code3) {
		this.tr_code3 = tr_code3;
	}
	public BigDecimal getTr_fee3() {
		return tr_fee3;
	}
	public void setTr_fee3(BigDecimal tr_fee3) {
		this.tr_fee3 = tr_fee3;
	}
	public String getTr_code4() {
		return tr_code4;
	}
	public void setTr_code4(String tr_code4) {
		this.tr_code4 = tr_code4;
	}
	public BigDecimal getTr_fee4() {
		return tr_fee4;
	}
	public void setTr_fee4(BigDecimal tr_fee4) {
		this.tr_fee4 = tr_fee4;
	}
	public String getTr_code5() {
		return tr_code5;
	}
	public void setTr_code5(String tr_code5) {
		this.tr_code5 = tr_code5;
	}
	public BigDecimal getTr_fee5() {
		return tr_fee5;
	}
	public void setTr_fee5(BigDecimal tr_fee5) {
		this.tr_fee5 = tr_fee5;
	}
	public String getTr_code6() {
		return tr_code6;
	}
	public void setTr_code6(String tr_code6) {
		this.tr_code6 = tr_code6;
	}
	public BigDecimal getTr_fee6() {
		return tr_fee6;
	}
	public void setTr_fee6(BigDecimal tr_fee6) {
		this.tr_fee6 = tr_fee6;
	}
	public String getTr_code7() {
		return tr_code7;
	}
	public void setTr_code7(String tr_code7) {
		this.tr_code7 = tr_code7;
	}
	public BigDecimal getTr_fee7() {
		return tr_fee7;
	}
	public void setTr_fee7(BigDecimal tr_fee7) {
		this.tr_fee7 = tr_fee7;
	}
	public String getTr_code8() {
		return tr_code8;
	}
	public void setTr_code8(String tr_code8) {
		this.tr_code8 = tr_code8;
	}
	public BigDecimal getTr_fee8() {
		return tr_fee8;
	}
	public void setTr_fee8(BigDecimal tr_fee8) {
		this.tr_fee8 = tr_fee8;
	}
	public String getTr_code9() {
		return tr_code9;
	}
	public void setTr_code9(String tr_code9) {
		this.tr_code9 = tr_code9;
	}
	public BigDecimal getTr_fee9() {
		return tr_fee9;
	}
	public void setTr_fee9(BigDecimal tr_fee9) {
		this.tr_fee9 = tr_fee9;
	}
	public String getTr_code10() {
		return tr_code10;
	}
	public void setTr_code10(String tr_code10) {
		this.tr_code10 = tr_code10;
	}
	public BigDecimal getTr_fee10() {
		return tr_fee10;
	}
	public void setTr_fee10(BigDecimal tr_fee10) {
		this.tr_fee10 = tr_fee10;
	}
	public BigDecimal getCheck_dupl() {
		return check_dupl;
	}
	public void setCheck_dupl(BigDecimal check_dupl) {
		this.check_dupl = check_dupl;
	}
	public BigDecimal getInstl_agr_no() {
		return instl_agr_no;
	}
	public void setInstl_agr_no(BigDecimal instl_agr_no) {
		this.instl_agr_no = instl_agr_no;
	}
	public BigDecimal getAccnt_type() {
		return accnt_type;
	}
	public void setAccnt_type(BigDecimal accnt_type) {
		this.accnt_type = accnt_type;
	}
	public ExecTransactionRequest(String payment_mode, BigDecimal account_no,
			String card_acct, String card_acct_ccy, String card,
			Date execute_on, String tran_type, String tran_ccy,
			BigDecimal tran_amnt, String branch, String batch_nr,
			String slip_nr, String deal_desc, String counterparty,
			BigDecimal internal_no, String bank_c, String groupc,
			Date tran_date_time, BigDecimal execution_type, String booking_msg,
			String tr_code, BigDecimal tr_fee, String tr_code2,
			BigDecimal tr_fee2, String tr_code3, BigDecimal tr_fee3,
			String tr_code4, BigDecimal tr_fee4, String tr_code5,
			BigDecimal tr_fee5, String tr_code6, BigDecimal tr_fee6,
			String tr_code7, BigDecimal tr_fee7, String tr_code8,
			BigDecimal tr_fee8, String tr_code9, BigDecimal tr_fee9,
			String tr_code10, BigDecimal tr_fee10, BigDecimal check_dupl,
			BigDecimal instl_agr_no, BigDecimal accnt_type) {
		super();
		this.payment_mode = payment_mode;
		this.account_no = account_no;
		this.card_acct = card_acct;
		this.card_acct_ccy = card_acct_ccy;
		this.card = card;
		this.execute_on = execute_on;
		this.tran_type = tran_type;
		this.tran_ccy = tran_ccy;
		this.tran_amnt = tran_amnt;
		this.branch = branch;
		this.batch_nr = batch_nr;
		this.slip_nr = slip_nr;
		this.deal_desc = deal_desc;
		this.counterparty = counterparty;
		this.internal_no = internal_no;
		this.bank_c = bank_c;
		this.groupc = groupc;
		this.tran_date_time = tran_date_time;
		this.execution_type = execution_type;
		this.booking_msg = booking_msg;
		this.tr_code = tr_code;
		this.tr_fee = tr_fee;
		this.tr_code2 = tr_code2;
		this.tr_fee2 = tr_fee2;
		this.tr_code3 = tr_code3;
		this.tr_fee3 = tr_fee3;
		this.tr_code4 = tr_code4;
		this.tr_fee4 = tr_fee4;
		this.tr_code5 = tr_code5;
		this.tr_fee5 = tr_fee5;
		this.tr_code6 = tr_code6;
		this.tr_fee6 = tr_fee6;
		this.tr_code7 = tr_code7;
		this.tr_fee7 = tr_fee7;
		this.tr_code8 = tr_code8;
		this.tr_fee8 = tr_fee8;
		this.tr_code9 = tr_code9;
		this.tr_fee9 = tr_fee9;
		this.tr_code10 = tr_code10;
		this.tr_fee10 = tr_fee10;
		this.check_dupl = check_dupl;
		this.instl_agr_no = instl_agr_no;
		this.accnt_type = accnt_type;
	}
	public ExecTransactionRequest() {
		super();
	}
	
	
}
