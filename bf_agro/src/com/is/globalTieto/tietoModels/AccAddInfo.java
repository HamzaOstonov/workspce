package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * CARD_NUMB Decimal(6,0) Количество присвоенных карт
 * IC_DATE dateTime Дата последнего биллинга
 * LOST_CARD Decimal(6,0) Количество потерянных (неактивных) карт
 * POST_DATE dateTime Дата последней проводки
 * BEGIN_BAL Decimal(12,0) Начальный баланс счета
 * DEBIT Decimal(12,0) Дебетный оборот
 * CREDIT Decimal(12,0) Кредитный оборот
 * END_BAL Decimal(12,0) Текущий баланс счета
 * AVAIL_AMT Decimal(12,0) Доступный баланс счета
 * DEBT Decimal(12,0) Сумма депозита для возобновления минимального баланса
 * DEBT1 Decimal(12,0) Общая задолженность за предыдущий месяц
 * PREV_DATE dateTime Дата предыдущей проводки
 * MBEGIN_BAL Decimal(12,0) Баланс на начало месяца
 * MDEBIT Decimal(12,0) Дебетный оборот за месяц
 * MCREDIT Decimal(12,0) Кредитный оборот за месяц
 * MEND_BAL Decimal(12,0) Баланс счета в конце месяца
 * MPREV_BAL Decimal(12,0) Баланс на начало предыдущего месяца
 * BRUTTO Decimal(12,0) Общая сумма транзакций за месяц
 * USED_AMOUNT Decimal(12,0) Сумма, блокированная в авторизациях
 * DBEGIN_BAL decimal(12,0) Начальный баланс дня
 * DDEBIT decimal(12,0) Дебетный оборот за день
 * DCREDIT decimal(12,0) Кредитный оборот за день
 * DEND_BAL decimal(12,0) Конечный баланс дня
 * DEP_CAP_DATE dateTime [Депозитный счет] Дата последней капитализации процентов
 * DEP_INT_CUR Decimal(21,9) [Депозитный счет] Текущая процентная ставка
 * DEP_INT_LAST decimal(21,9) [Депозитный счет] Предыдущая процентная ставка
 * DEP_INT_TOT decimal(21,9) [Депозитный счет] Сумма процентов за период – не подлежавшая капитализации 
 * DEP_INT_GTOT decimal(21,9) [Депозитный счет] Общие проценты с момента открытия счета
 * DEP_CUR_TRNSF decimal(21,9) [Депозитный счет] Последняя капитализированная сумма процентов
 * DEP_INT_TRNSF decimal(21,9) Общая капитализированная сумма процентов
 * DEP_LPER_DATE dateTime Дата последнего расчета процентов
 * REVERS_SUM Decimal(12,0) Сумма реверсала с последней даты биллинга
 * END_BAL1 decimal(12,0) Частичный конечный баланс
 * PAYM_AMOUNT decimal(12,0) Сумма последнего платежа
 * PAYM_DATE dateTime Дата последнего платежа
 * PAYM_INTERNAL_NO decimal(12,0) Внутренний номер последнего платежа в таблице izd_slip
 * TA_OTB decimal(12,0) OTB, основанное на балансе технического счета 
 * MPREV_DEBIT decimal(12,0) Дебетный оборот предыдущего биллингового цикла
 * MPREV_CREDIT decimal(12,0) Кредитный оборот предыдущего биллингового цикла
 * PROC_ID decimal(14,0) Идентификатор процесса последнего пакета
 * CRD_CHANGE_DATE dateTime Дата последних изменений кредитного лимита
 */
@NoArgsConstructor
@AllArgsConstructor
public class AccAddInfo {

	@Getter
	@Setter
	private BigDecimal CARD_NUMB;
	@Getter
	@Setter
	private Date IC_DATE;
	@Getter
	@Setter
	private BigDecimal LOST_CARD;
	@Getter
	@Setter
	private Date POST_DATE;
	@Getter
	@Setter
	private BigDecimal BEGIN_BAL;
	@Getter
	@Setter
	private BigDecimal DEBIT;
	@Getter
	@Setter
	private BigDecimal CREDIT;
	@Getter
	@Setter
	private BigDecimal END_BAL;
	@Getter
	@Setter
	private BigDecimal AVAIL_AMT;
	@Getter
	@Setter
	private BigDecimal DEBT;
	@Getter
	@Setter
	private BigDecimal DEBT1;
	@Getter
	@Setter
	private Date PREV_DATE;
	@Getter
	@Setter
	private BigDecimal MBEGIN_BAL;
	@Getter
	@Setter
	private BigDecimal MDEBIT;
	@Getter
	@Setter
	private BigDecimal MCREDIT;
	@Getter
	@Setter
	private BigDecimal MEND_BAL;
	@Getter
	@Setter
	private BigDecimal MPREV_BAL;
	@Getter
	@Setter
	private BigDecimal BRUTTO;
	@Getter
	@Setter
	private BigDecimal USED_AMOUNT;
	@Getter
	@Setter
	private BigDecimal DBEGIN_BAL;
	@Getter
	@Setter
	private BigDecimal DDEBIT;
	@Getter
	@Setter
	private BigDecimal DCREDIT;
	@Getter
	@Setter
	private BigDecimal DEND_BAL;
	@Getter
	@Setter
	private Date DEP_CAP_DATE;
	@Getter
	@Setter
	private BigDecimal DEP_INT_CUR;
	@Getter
	@Setter
	private BigDecimal DEP_INT_LAST;
	@Getter
	@Setter
	private BigDecimal DEP_INT_TOT;
	@Getter
	@Setter
	private BigDecimal DEP_INT_GTOT;
	@Getter
	@Setter
	private BigDecimal DEP_CUR_TRNSF;
	@Getter
	@Setter
	private BigDecimal DEP_INT_TRNSF;
	@Getter
	@Setter
	private Date DEP_LPER_DATE;
	@Getter
	@Setter
	private BigDecimal REVERS_SUM;
	@Getter
	@Setter
	private BigDecimal END_BAL1;
	@Getter
	@Setter
	private BigDecimal PAYM_AMOUNT;
	@Getter
	@Setter
	private Date PAYM_DATE;
	@Getter
	@Setter
	private BigDecimal PAYM_INTERNAL_NO;
	@Getter
	@Setter
	private BigDecimal TA_OTB;
	@Getter
	@Setter
	private BigDecimal MPREV_DEBIT;
	@Getter
	@Setter
	private BigDecimal MPREV_CREDIT;
	@Getter
	@Setter
	private BigDecimal PROC_ID;
	@Getter
	@Setter
	private Date CRD_CHANGE_DATE;
	
}
