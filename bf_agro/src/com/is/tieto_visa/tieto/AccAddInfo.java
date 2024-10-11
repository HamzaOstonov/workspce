package com.is.tieto_visa.tieto;

import java.math.BigDecimal;
import java.util.Date;


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

public class AccAddInfo {

	
	
	private BigDecimal CARD_NUMB;
	
	
	private Date IC_DATE;
	
	
	private BigDecimal LOST_CARD;
	
	
	private Date POST_DATE;
	
	
	private BigDecimal BEGIN_BAL;
	
	
	private BigDecimal DEBIT;
	
	
	private BigDecimal CREDIT;
	
	
	private BigDecimal END_BAL;
	
	
	private BigDecimal AVAIL_AMT;
	
	
	private BigDecimal DEBT;
	
	
	private BigDecimal DEBT1;
	
	
	private Date PREV_DATE;
	
	
	private BigDecimal MBEGIN_BAL;
	
	
	private BigDecimal MDEBIT;
	
	
	private BigDecimal MCREDIT;
	
	
	private BigDecimal MEND_BAL;
	
	
	private BigDecimal MPREV_BAL;
	
	
	private BigDecimal BRUTTO;
	
	
	private BigDecimal USED_AMOUNT;
	
	
	private BigDecimal DBEGIN_BAL;
	
	
	private BigDecimal DDEBIT;
	
	
	private BigDecimal DCREDIT;
	
	
	private BigDecimal DEND_BAL;
	
	
	private Date DEP_CAP_DATE;
	
	
	private BigDecimal DEP_INT_CUR;
	
	
	private BigDecimal DEP_INT_LAST;
	
	
	private BigDecimal DEP_INT_TOT;
	
	
	private BigDecimal DEP_INT_GTOT;
	
	
	private BigDecimal DEP_CUR_TRNSF;
	
	
	private BigDecimal DEP_INT_TRNSF;
	
	
	private Date DEP_LPER_DATE;
	
	
	private BigDecimal REVERS_SUM;
	
	
	private BigDecimal END_BAL1;
	
	
	private BigDecimal PAYM_AMOUNT;
	
	
	private Date PAYM_DATE;
	
	
	private BigDecimal PAYM_INTERNAL_NO;
	
	
	private BigDecimal TA_OTB;
	
	
	private BigDecimal MPREV_DEBIT;
	
	
	private BigDecimal MPREV_CREDIT;
	
	
	private BigDecimal PROC_ID;
	
	
	private Date CRD_CHANGE_DATE;


	public BigDecimal getCARD_NUMB() {
		return CARD_NUMB;
	}


	public void setCARD_NUMB(BigDecimal cARD_NUMB) {
		CARD_NUMB = cARD_NUMB;
	}


	public Date getIC_DATE() {
		return IC_DATE;
	}


	public void setIC_DATE(Date iC_DATE) {
		IC_DATE = iC_DATE;
	}


	public BigDecimal getLOST_CARD() {
		return LOST_CARD;
	}


	public void setLOST_CARD(BigDecimal lOST_CARD) {
		LOST_CARD = lOST_CARD;
	}


	public Date getPOST_DATE() {
		return POST_DATE;
	}


	public void setPOST_DATE(Date pOST_DATE) {
		POST_DATE = pOST_DATE;
	}


	public BigDecimal getBEGIN_BAL() {
		return BEGIN_BAL;
	}


	public void setBEGIN_BAL(BigDecimal bEGIN_BAL) {
		BEGIN_BAL = bEGIN_BAL;
	}


	public BigDecimal getDEBIT() {
		return DEBIT;
	}


	public void setDEBIT(BigDecimal dEBIT) {
		DEBIT = dEBIT;
	}


	public BigDecimal getCREDIT() {
		return CREDIT;
	}


	public void setCREDIT(BigDecimal cREDIT) {
		CREDIT = cREDIT;
	}


	public BigDecimal getEND_BAL() {
		return END_BAL;
	}


	public void setEND_BAL(BigDecimal eND_BAL) {
		END_BAL = eND_BAL;
	}


	public BigDecimal getAVAIL_AMT() {
		return AVAIL_AMT;
	}


	public void setAVAIL_AMT(BigDecimal aVAIL_AMT) {
		AVAIL_AMT = aVAIL_AMT;
	}


	public BigDecimal getDEBT() {
		return DEBT;
	}


	public void setDEBT(BigDecimal dEBT) {
		DEBT = dEBT;
	}


	public BigDecimal getDEBT1() {
		return DEBT1;
	}


	public void setDEBT1(BigDecimal dEBT1) {
		DEBT1 = dEBT1;
	}


	public Date getPREV_DATE() {
		return PREV_DATE;
	}


	public void setPREV_DATE(Date pREV_DATE) {
		PREV_DATE = pREV_DATE;
	}


	public BigDecimal getMBEGIN_BAL() {
		return MBEGIN_BAL;
	}


	public void setMBEGIN_BAL(BigDecimal mBEGIN_BAL) {
		MBEGIN_BAL = mBEGIN_BAL;
	}


	public BigDecimal getMDEBIT() {
		return MDEBIT;
	}


	public void setMDEBIT(BigDecimal mDEBIT) {
		MDEBIT = mDEBIT;
	}


	public BigDecimal getMCREDIT() {
		return MCREDIT;
	}


	public void setMCREDIT(BigDecimal mCREDIT) {
		MCREDIT = mCREDIT;
	}


	public BigDecimal getMEND_BAL() {
		return MEND_BAL;
	}


	public void setMEND_BAL(BigDecimal mEND_BAL) {
		MEND_BAL = mEND_BAL;
	}


	public BigDecimal getMPREV_BAL() {
		return MPREV_BAL;
	}


	public void setMPREV_BAL(BigDecimal mPREV_BAL) {
		MPREV_BAL = mPREV_BAL;
	}


	public BigDecimal getBRUTTO() {
		return BRUTTO;
	}


	public void setBRUTTO(BigDecimal bRUTTO) {
		BRUTTO = bRUTTO;
	}


	public BigDecimal getUSED_AMOUNT() {
		return USED_AMOUNT;
	}


	public void setUSED_AMOUNT(BigDecimal uSED_AMOUNT) {
		USED_AMOUNT = uSED_AMOUNT;
	}


	public BigDecimal getDBEGIN_BAL() {
		return DBEGIN_BAL;
	}


	public void setDBEGIN_BAL(BigDecimal dBEGIN_BAL) {
		DBEGIN_BAL = dBEGIN_BAL;
	}


	public BigDecimal getDDEBIT() {
		return DDEBIT;
	}


	public void setDDEBIT(BigDecimal dDEBIT) {
		DDEBIT = dDEBIT;
	}


	public BigDecimal getDCREDIT() {
		return DCREDIT;
	}


	public void setDCREDIT(BigDecimal dCREDIT) {
		DCREDIT = dCREDIT;
	}


	public BigDecimal getDEND_BAL() {
		return DEND_BAL;
	}


	public void setDEND_BAL(BigDecimal dEND_BAL) {
		DEND_BAL = dEND_BAL;
	}


	public Date getDEP_CAP_DATE() {
		return DEP_CAP_DATE;
	}


	public void setDEP_CAP_DATE(Date dEP_CAP_DATE) {
		DEP_CAP_DATE = dEP_CAP_DATE;
	}


	public BigDecimal getDEP_INT_CUR() {
		return DEP_INT_CUR;
	}


	public void setDEP_INT_CUR(BigDecimal dEP_INT_CUR) {
		DEP_INT_CUR = dEP_INT_CUR;
	}


	public BigDecimal getDEP_INT_LAST() {
		return DEP_INT_LAST;
	}


	public void setDEP_INT_LAST(BigDecimal dEP_INT_LAST) {
		DEP_INT_LAST = dEP_INT_LAST;
	}


	public BigDecimal getDEP_INT_TOT() {
		return DEP_INT_TOT;
	}


	public void setDEP_INT_TOT(BigDecimal dEP_INT_TOT) {
		DEP_INT_TOT = dEP_INT_TOT;
	}


	public BigDecimal getDEP_INT_GTOT() {
		return DEP_INT_GTOT;
	}


	public void setDEP_INT_GTOT(BigDecimal dEP_INT_GTOT) {
		DEP_INT_GTOT = dEP_INT_GTOT;
	}


	public BigDecimal getDEP_CUR_TRNSF() {
		return DEP_CUR_TRNSF;
	}


	public void setDEP_CUR_TRNSF(BigDecimal dEP_CUR_TRNSF) {
		DEP_CUR_TRNSF = dEP_CUR_TRNSF;
	}


	public BigDecimal getDEP_INT_TRNSF() {
		return DEP_INT_TRNSF;
	}


	public void setDEP_INT_TRNSF(BigDecimal dEP_INT_TRNSF) {
		DEP_INT_TRNSF = dEP_INT_TRNSF;
	}


	public Date getDEP_LPER_DATE() {
		return DEP_LPER_DATE;
	}


	public void setDEP_LPER_DATE(Date dEP_LPER_DATE) {
		DEP_LPER_DATE = dEP_LPER_DATE;
	}


	public BigDecimal getREVERS_SUM() {
		return REVERS_SUM;
	}


	public void setREVERS_SUM(BigDecimal rEVERS_SUM) {
		REVERS_SUM = rEVERS_SUM;
	}


	public BigDecimal getEND_BAL1() {
		return END_BAL1;
	}


	public void setEND_BAL1(BigDecimal eND_BAL1) {
		END_BAL1 = eND_BAL1;
	}


	public BigDecimal getPAYM_AMOUNT() {
		return PAYM_AMOUNT;
	}


	public void setPAYM_AMOUNT(BigDecimal pAYM_AMOUNT) {
		PAYM_AMOUNT = pAYM_AMOUNT;
	}


	public Date getPAYM_DATE() {
		return PAYM_DATE;
	}


	public void setPAYM_DATE(Date pAYM_DATE) {
		PAYM_DATE = pAYM_DATE;
	}


	public BigDecimal getPAYM_INTERNAL_NO() {
		return PAYM_INTERNAL_NO;
	}


	public void setPAYM_INTERNAL_NO(BigDecimal pAYM_INTERNAL_NO) {
		PAYM_INTERNAL_NO = pAYM_INTERNAL_NO;
	}


	public BigDecimal getTA_OTB() {
		return TA_OTB;
	}


	public void setTA_OTB(BigDecimal tA_OTB) {
		TA_OTB = tA_OTB;
	}


	public BigDecimal getMPREV_DEBIT() {
		return MPREV_DEBIT;
	}


	public void setMPREV_DEBIT(BigDecimal mPREV_DEBIT) {
		MPREV_DEBIT = mPREV_DEBIT;
	}


	public BigDecimal getMPREV_CREDIT() {
		return MPREV_CREDIT;
	}


	public void setMPREV_CREDIT(BigDecimal mPREV_CREDIT) {
		MPREV_CREDIT = mPREV_CREDIT;
	}


	public BigDecimal getPROC_ID() {
		return PROC_ID;
	}


	public void setPROC_ID(BigDecimal pROC_ID) {
		PROC_ID = pROC_ID;
	}


	public Date getCRD_CHANGE_DATE() {
		return CRD_CHANGE_DATE;
	}


	public void setCRD_CHANGE_DATE(Date cRD_CHANGE_DATE) {
		CRD_CHANGE_DATE = cRD_CHANGE_DATE;
	}


	public AccAddInfo() {
		super();
	}


	public AccAddInfo(BigDecimal cARD_NUMB, Date iC_DATE, BigDecimal lOST_CARD,
			Date pOST_DATE, BigDecimal bEGIN_BAL, BigDecimal dEBIT,
			BigDecimal cREDIT, BigDecimal eND_BAL, BigDecimal aVAIL_AMT,
			BigDecimal dEBT, BigDecimal dEBT1, Date pREV_DATE,
			BigDecimal mBEGIN_BAL, BigDecimal mDEBIT, BigDecimal mCREDIT,
			BigDecimal mEND_BAL, BigDecimal mPREV_BAL, BigDecimal bRUTTO,
			BigDecimal uSED_AMOUNT, BigDecimal dBEGIN_BAL, BigDecimal dDEBIT,
			BigDecimal dCREDIT, BigDecimal dEND_BAL, Date dEP_CAP_DATE,
			BigDecimal dEP_INT_CUR, BigDecimal dEP_INT_LAST,
			BigDecimal dEP_INT_TOT, BigDecimal dEP_INT_GTOT,
			BigDecimal dEP_CUR_TRNSF, BigDecimal dEP_INT_TRNSF,
			Date dEP_LPER_DATE, BigDecimal rEVERS_SUM, BigDecimal eND_BAL1,
			BigDecimal pAYM_AMOUNT, Date pAYM_DATE,
			BigDecimal pAYM_INTERNAL_NO, BigDecimal tA_OTB,
			BigDecimal mPREV_DEBIT, BigDecimal mPREV_CREDIT,
			BigDecimal pROC_ID, Date cRD_CHANGE_DATE) {
		super();
		CARD_NUMB = cARD_NUMB;
		IC_DATE = iC_DATE;
		LOST_CARD = lOST_CARD;
		POST_DATE = pOST_DATE;
		BEGIN_BAL = bEGIN_BAL;
		DEBIT = dEBIT;
		CREDIT = cREDIT;
		END_BAL = eND_BAL;
		AVAIL_AMT = aVAIL_AMT;
		DEBT = dEBT;
		DEBT1 = dEBT1;
		PREV_DATE = pREV_DATE;
		MBEGIN_BAL = mBEGIN_BAL;
		MDEBIT = mDEBIT;
		MCREDIT = mCREDIT;
		MEND_BAL = mEND_BAL;
		MPREV_BAL = mPREV_BAL;
		BRUTTO = bRUTTO;
		USED_AMOUNT = uSED_AMOUNT;
		DBEGIN_BAL = dBEGIN_BAL;
		DDEBIT = dDEBIT;
		DCREDIT = dCREDIT;
		DEND_BAL = dEND_BAL;
		DEP_CAP_DATE = dEP_CAP_DATE;
		DEP_INT_CUR = dEP_INT_CUR;
		DEP_INT_LAST = dEP_INT_LAST;
		DEP_INT_TOT = dEP_INT_TOT;
		DEP_INT_GTOT = dEP_INT_GTOT;
		DEP_CUR_TRNSF = dEP_CUR_TRNSF;
		DEP_INT_TRNSF = dEP_INT_TRNSF;
		DEP_LPER_DATE = dEP_LPER_DATE;
		REVERS_SUM = rEVERS_SUM;
		END_BAL1 = eND_BAL1;
		PAYM_AMOUNT = pAYM_AMOUNT;
		PAYM_DATE = pAYM_DATE;
		PAYM_INTERNAL_NO = pAYM_INTERNAL_NO;
		TA_OTB = tA_OTB;
		MPREV_DEBIT = mPREV_DEBIT;
		MPREV_CREDIT = mPREV_CREDIT;
		PROC_ID = pROC_ID;
		CRD_CHANGE_DATE = cRD_CHANGE_DATE;
	}
	
	
}
