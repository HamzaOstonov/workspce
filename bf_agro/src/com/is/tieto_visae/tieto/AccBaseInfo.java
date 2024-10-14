package com.is.tieto_visae.tieto;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author D
 * ACCOUNT_NO decimal(20,0) Внутренний номер счета (ORASEQ)
 * ACC_PRTY string(1) Приоритет счета (по умолчанию 1)
 * C_ACCNT_TYPE string(2) Код типа счета
 * CARD_ACCT string(34) Номер счета карты.
 * CCY string(3) Валюта счета
 * TRANZ_ACCT string(34) Номер счета для маршрутизации на банковскую систему.
 * CYCLE string(10) Биллинговый цикл
 * MIN_BAL decimal(10,0) Наименьший положительный баланс, установленный банком для восполнения счета карты в завершении каждого биллингового цикла.
 * COND_SET string(3) Набор финансовых условий, общий для группы счетов (по умолчанию 0)
 * STATUS string(1) Статус счета 0,3,4, Для ‘generic’ продуктов счет необходимо открыть как closed
 * STAT_CHANGE string(1) Признак изменения статуса счета – ручной или автоматический.
 * STA_COMENT string(40) Комментарий к изменению статуса счета
 * AUTH_BONUS Decimal(12,0) Дополнительная сумма авторизации
 * AB_EXPIRITY dateTime Дата завершения действия дополнительной суммы авторизации (по умолчанию sysdate)
 * CRD Decimal(12,0) Кредитный лимит счета карты.
 * CRD_EXPIRY dateTime Дата завершения действия кредита 
 * ATM_LIMIT Decimal(12,0) Лимитная сумма для банкомата в биллинговом цикле (по умолчанию 0)
 * NON_REDUCE_BAL Decimal(12,0) Неуменьшаемый баланс (по умолчанию 0).
 * ADJUST_FLAG string(1) Признак корректирующей транзакции (0 – отключено,1 – разрешено с одного счета) (по умолчанию 0)
 * PAY_CODE string(1) Код платежа: 1 – автоматический со счета, 2 – ручной (по умолчанию 0)
 * PAY_FREQ string(1) Частота выполнения платежей (по умолчанию 0)
 * CALCUL_MODE string(2) Режим расчета выплачиваемой суммы (по умолчанию 0)
 * PAY_AMNT Decimal(12,0) Уплачиваемая сумма (по умолчанию 0).
 * PAY_INTR Decimal(6,2) % от кредитного лимита. Этот % используется для расчета уплачиваемой суммы (по умолчанию 0).
 * LIM_AMNT Decimal(12,0) Минимальная сумма, инициирующая необходимость платежа (по умолчанию 0)
 * LIM_INTR Decimal(6,2) Минимальный % от кредитной суммы, инициирующий необходимость платежа
 * CREATED_DATE dateTime Дата создания счета (по умолчанию sysdate)
 * STOP_DATE dateTime Дата остановки счета 
 * MESSAGE string(120) Сообщение карта держателя данного счета
 * U_COD7 string(3) Заполняемый пользователем код 7
 * U_COD8 string(6) Заполняемый пользователем код 8
 * UFIELD_5 string(20) Заполняемое пользователем поле 5
 * U_FIELD6 string(25) Заполняемое пользователем поле 6
 * DEPOSIT Decimal(12,0) Сумма депозита клиента
 * DEPOSIT_COMENT string(30) Комментарий к сумме депозита
 * DEPOSIT_ACCOUNT string(34) Номер счета депозита
 * AGR_AMOUNT Decimal(12,0) Поле, имеющее отношение к депозитной функциональности. 
 * DEP_EXP_DATE dateTime Поле, имеющее отношение к депозитной функциональности. 
 * DEP_OPEN_F string(1) Поле, имеющее отношение к депозитной функциональности. 
 * DEP_FRONT_F string(1) Поле, имеющее отношение к депозитной функциональности. 
 * DEP_OPER_ACCT Decimal(20,0) Поле, имеющее отношение к депозитной функциональности. 
 * DEP_OPER_ACCTB Decimal(1,0) Поле, имеющее отношение к депозитной функциональности. 
 * DEP_OPER_BACCT string(21) Поле, имеющее отношение к депозитной функциональности. 
 * IN_FILE_NUM Decimal(7,0) Идентификатор задания пакета (Batch task ID)
 * OPEN_INSTL Decimal(5,0) Максимальное позволенное число открытых уплат в рассрочку (Installments)
 * INSTL_LINE Decimal(7,2) Доступный процент от CRD для уплаты в рассрочку (Installments).
 * INSTL_CONDSET string(3) Условия уплаты в рассрочку (Installment).
 */

public class AccBaseInfo {

	
	
	private BigDecimal ACCOUNT_NO;
	
	
	private String ACC_PRTY;
	
	
	private String C_ACCNT_TYPE;
	
	
	private String CARD_ACCT;
	
	
	private String CCY;
	
	
	private String TRANZ_ACCT;
	
	
	private String CYCLE;
	
	
	private BigDecimal MIN_BAL;
	
	
	private String COND_SET;
	
	
	private String STATUS;
	
	
	private String STAT_CHANGE;
	
	
	private String STA_COMENT;
	
	
	private BigDecimal AUTH_BONUS;
	
	
	private Date AB_EXPIRITY;
	
	
	private BigDecimal CRD;
	
	
	private Date CRD_EXPIRY;
	
	
	private BigDecimal ATM_LIMIT;
	
	
	private BigDecimal NON_REDUCE_BAL;
	
	
	private String ADJUST_FLAG;
	
	
	private String PAY_CODE;
	
	
	private String PAY_FREQ;
	
	
	private String CALCUL_MODE;
	
	
	private BigDecimal PAY_AMNT;
	
	
	private BigDecimal PAY_INTR;
	
	
	private BigDecimal LIM_AMNT;
	
	
	private BigDecimal LIM_INTR;
	
	
	private Date CREATED_DATE;
	
	
	private Date STOP_DATE;
	
	
	private String MESSAGE;
	
	
	private String U_COD7;
	
	
	private String U_COD8;
	
	
	private String UFIELD_5;
	
	
	private String U_FIELD6;
	
	
	private BigDecimal DEPOSIT;
	
	
	private String DEPOSIT_COMENT;
	
	
	private String DEPOSIT_ACCOUNT;
	
	
	private BigDecimal AGR_AMOUNT;
	
	
	private Date DEP_EXP_DATE;
	
	
	private String DEP_OPEN_F;
	
	
	private String DEP_FRONT_F;
	
	
	private BigDecimal DEP_OPER_ACCT;
	
	
	private BigDecimal DEP_OPER_ACCTB;
	
	
	private String DEP_OPER_BACCT;
	
	
	private BigDecimal IN_FILE_NUM;
	
	
	private BigDecimal OPEN_INSTL;
	
	
	private BigDecimal INSTL_LINE;
	
	
	private String INSTL_CONDSET;


	public AccBaseInfo(BigDecimal aCCOUNT_NO, String aCC_PRTY,
			String c_ACCNT_TYPE, String cARD_ACCT, String cCY,
			String tRANZ_ACCT, String cYCLE, BigDecimal mIN_BAL,
			String cOND_SET, String sTATUS, String sTAT_CHANGE,
			String sTA_COMENT, BigDecimal aUTH_BONUS, Date aB_EXPIRITY,
			BigDecimal cRD, Date cRD_EXPIRY, BigDecimal aTM_LIMIT,
			BigDecimal nON_REDUCE_BAL, String aDJUST_FLAG, String pAY_CODE,
			String pAY_FREQ, String cALCUL_MODE, BigDecimal pAY_AMNT,
			BigDecimal pAY_INTR, BigDecimal lIM_AMNT, BigDecimal lIM_INTR,
			Date cREATED_DATE, Date sTOP_DATE, String mESSAGE, String u_COD7,
			String u_COD8, String uFIELD_5, String u_FIELD6,
			BigDecimal dEPOSIT, String dEPOSIT_COMENT, String dEPOSIT_ACCOUNT,
			BigDecimal aGR_AMOUNT, Date dEP_EXP_DATE, String dEP_OPEN_F,
			String dEP_FRONT_F, BigDecimal dEP_OPER_ACCT,
			BigDecimal dEP_OPER_ACCTB, String dEP_OPER_BACCT,
			BigDecimal iN_FILE_NUM, BigDecimal oPEN_INSTL,
			BigDecimal iNSTL_LINE, String iNSTL_CONDSET) {
		super();
		ACCOUNT_NO = aCCOUNT_NO;
		ACC_PRTY = aCC_PRTY;
		C_ACCNT_TYPE = c_ACCNT_TYPE;
		CARD_ACCT = cARD_ACCT;
		CCY = cCY;
		TRANZ_ACCT = tRANZ_ACCT;
		CYCLE = cYCLE;
		MIN_BAL = mIN_BAL;
		COND_SET = cOND_SET;
		STATUS = sTATUS;
		STAT_CHANGE = sTAT_CHANGE;
		STA_COMENT = sTA_COMENT;
		AUTH_BONUS = aUTH_BONUS;
		AB_EXPIRITY = aB_EXPIRITY;
		CRD = cRD;
		CRD_EXPIRY = cRD_EXPIRY;
		ATM_LIMIT = aTM_LIMIT;
		NON_REDUCE_BAL = nON_REDUCE_BAL;
		ADJUST_FLAG = aDJUST_FLAG;
		PAY_CODE = pAY_CODE;
		PAY_FREQ = pAY_FREQ;
		CALCUL_MODE = cALCUL_MODE;
		PAY_AMNT = pAY_AMNT;
		PAY_INTR = pAY_INTR;
		LIM_AMNT = lIM_AMNT;
		LIM_INTR = lIM_INTR;
		CREATED_DATE = cREATED_DATE;
		STOP_DATE = sTOP_DATE;
		MESSAGE = mESSAGE;
		U_COD7 = u_COD7;
		U_COD8 = u_COD8;
		UFIELD_5 = uFIELD_5;
		U_FIELD6 = u_FIELD6;
		DEPOSIT = dEPOSIT;
		DEPOSIT_COMENT = dEPOSIT_COMENT;
		DEPOSIT_ACCOUNT = dEPOSIT_ACCOUNT;
		AGR_AMOUNT = aGR_AMOUNT;
		DEP_EXP_DATE = dEP_EXP_DATE;
		DEP_OPEN_F = dEP_OPEN_F;
		DEP_FRONT_F = dEP_FRONT_F;
		DEP_OPER_ACCT = dEP_OPER_ACCT;
		DEP_OPER_ACCTB = dEP_OPER_ACCTB;
		DEP_OPER_BACCT = dEP_OPER_BACCT;
		IN_FILE_NUM = iN_FILE_NUM;
		OPEN_INSTL = oPEN_INSTL;
		INSTL_LINE = iNSTL_LINE;
		INSTL_CONDSET = iNSTL_CONDSET;
	}


	public AccBaseInfo() {
		super();
	}


	public BigDecimal getACCOUNT_NO() {
		return ACCOUNT_NO;
	}


	public void setACCOUNT_NO(BigDecimal aCCOUNT_NO) {
		ACCOUNT_NO = aCCOUNT_NO;
	}


	public String getACC_PRTY() {
		return ACC_PRTY;
	}


	public void setACC_PRTY(String aCC_PRTY) {
		ACC_PRTY = aCC_PRTY;
	}


	public String getC_ACCNT_TYPE() {
		return C_ACCNT_TYPE;
	}


	public void setC_ACCNT_TYPE(String c_ACCNT_TYPE) {
		C_ACCNT_TYPE = c_ACCNT_TYPE;
	}


	public String getCARD_ACCT() {
		return CARD_ACCT;
	}


	public void setCARD_ACCT(String cARD_ACCT) {
		CARD_ACCT = cARD_ACCT;
	}


	public String getCCY() {
		return CCY;
	}


	public void setCCY(String cCY) {
		CCY = cCY;
	}


	public String getTRANZ_ACCT() {
		return TRANZ_ACCT;
	}


	public void setTRANZ_ACCT(String tRANZ_ACCT) {
		TRANZ_ACCT = tRANZ_ACCT;
	}


	public String getCYCLE() {
		return CYCLE;
	}


	public void setCYCLE(String cYCLE) {
		CYCLE = cYCLE;
	}


	public BigDecimal getMIN_BAL() {
		return MIN_BAL;
	}


	public void setMIN_BAL(BigDecimal mIN_BAL) {
		MIN_BAL = mIN_BAL;
	}


	public String getCOND_SET() {
		return COND_SET;
	}


	public void setCOND_SET(String cOND_SET) {
		COND_SET = cOND_SET;
	}


	public String getSTATUS() {
		return STATUS;
	}


	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}


	public String getSTAT_CHANGE() {
		return STAT_CHANGE;
	}


	public void setSTAT_CHANGE(String sTAT_CHANGE) {
		STAT_CHANGE = sTAT_CHANGE;
	}


	public String getSTA_COMENT() {
		return STA_COMENT;
	}


	public void setSTA_COMENT(String sTA_COMENT) {
		STA_COMENT = sTA_COMENT;
	}


	public BigDecimal getAUTH_BONUS() {
		return AUTH_BONUS;
	}


	public void setAUTH_BONUS(BigDecimal aUTH_BONUS) {
		AUTH_BONUS = aUTH_BONUS;
	}


	public Date getAB_EXPIRITY() {
		return AB_EXPIRITY;
	}


	public void setAB_EXPIRITY(Date aB_EXPIRITY) {
		AB_EXPIRITY = aB_EXPIRITY;
	}


	public BigDecimal getCRD() {
		return CRD;
	}


	public void setCRD(BigDecimal cRD) {
		CRD = cRD;
	}


	public Date getCRD_EXPIRY() {
		return CRD_EXPIRY;
	}


	public void setCRD_EXPIRY(Date cRD_EXPIRY) {
		CRD_EXPIRY = cRD_EXPIRY;
	}


	public BigDecimal getATM_LIMIT() {
		return ATM_LIMIT;
	}


	public void setATM_LIMIT(BigDecimal aTM_LIMIT) {
		ATM_LIMIT = aTM_LIMIT;
	}


	public BigDecimal getNON_REDUCE_BAL() {
		return NON_REDUCE_BAL;
	}


	public void setNON_REDUCE_BAL(BigDecimal nON_REDUCE_BAL) {
		NON_REDUCE_BAL = nON_REDUCE_BAL;
	}


	public String getADJUST_FLAG() {
		return ADJUST_FLAG;
	}


	public void setADJUST_FLAG(String aDJUST_FLAG) {
		ADJUST_FLAG = aDJUST_FLAG;
	}


	public String getPAY_CODE() {
		return PAY_CODE;
	}


	public void setPAY_CODE(String pAY_CODE) {
		PAY_CODE = pAY_CODE;
	}


	public String getPAY_FREQ() {
		return PAY_FREQ;
	}


	public void setPAY_FREQ(String pAY_FREQ) {
		PAY_FREQ = pAY_FREQ;
	}


	public String getCALCUL_MODE() {
		return CALCUL_MODE;
	}


	public void setCALCUL_MODE(String cALCUL_MODE) {
		CALCUL_MODE = cALCUL_MODE;
	}


	public BigDecimal getPAY_AMNT() {
		return PAY_AMNT;
	}


	public void setPAY_AMNT(BigDecimal pAY_AMNT) {
		PAY_AMNT = pAY_AMNT;
	}


	public BigDecimal getPAY_INTR() {
		return PAY_INTR;
	}


	public void setPAY_INTR(BigDecimal pAY_INTR) {
		PAY_INTR = pAY_INTR;
	}


	public BigDecimal getLIM_AMNT() {
		return LIM_AMNT;
	}


	public void setLIM_AMNT(BigDecimal lIM_AMNT) {
		LIM_AMNT = lIM_AMNT;
	}


	public BigDecimal getLIM_INTR() {
		return LIM_INTR;
	}


	public void setLIM_INTR(BigDecimal lIM_INTR) {
		LIM_INTR = lIM_INTR;
	}


	public Date getCREATED_DATE() {
		return CREATED_DATE;
	}


	public void setCREATED_DATE(Date cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}


	public Date getSTOP_DATE() {
		return STOP_DATE;
	}


	public void setSTOP_DATE(Date sTOP_DATE) {
		STOP_DATE = sTOP_DATE;
	}


	public String getMESSAGE() {
		return MESSAGE;
	}


	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}


	public String getU_COD7() {
		return U_COD7;
	}


	public void setU_COD7(String u_COD7) {
		U_COD7 = u_COD7;
	}


	public String getU_COD8() {
		return U_COD8;
	}


	public void setU_COD8(String u_COD8) {
		U_COD8 = u_COD8;
	}


	public String getUFIELD_5() {
		return UFIELD_5;
	}


	public void setUFIELD_5(String uFIELD_5) {
		UFIELD_5 = uFIELD_5;
	}


	public String getU_FIELD6() {
		return U_FIELD6;
	}


	public void setU_FIELD6(String u_FIELD6) {
		U_FIELD6 = u_FIELD6;
	}


	public BigDecimal getDEPOSIT() {
		return DEPOSIT;
	}


	public void setDEPOSIT(BigDecimal dEPOSIT) {
		DEPOSIT = dEPOSIT;
	}


	public String getDEPOSIT_COMENT() {
		return DEPOSIT_COMENT;
	}


	public void setDEPOSIT_COMENT(String dEPOSIT_COMENT) {
		DEPOSIT_COMENT = dEPOSIT_COMENT;
	}


	public String getDEPOSIT_ACCOUNT() {
		return DEPOSIT_ACCOUNT;
	}


	public void setDEPOSIT_ACCOUNT(String dEPOSIT_ACCOUNT) {
		DEPOSIT_ACCOUNT = dEPOSIT_ACCOUNT;
	}


	public BigDecimal getAGR_AMOUNT() {
		return AGR_AMOUNT;
	}


	public void setAGR_AMOUNT(BigDecimal aGR_AMOUNT) {
		AGR_AMOUNT = aGR_AMOUNT;
	}


	public Date getDEP_EXP_DATE() {
		return DEP_EXP_DATE;
	}


	public void setDEP_EXP_DATE(Date dEP_EXP_DATE) {
		DEP_EXP_DATE = dEP_EXP_DATE;
	}


	public String getDEP_OPEN_F() {
		return DEP_OPEN_F;
	}


	public void setDEP_OPEN_F(String dEP_OPEN_F) {
		DEP_OPEN_F = dEP_OPEN_F;
	}


	public String getDEP_FRONT_F() {
		return DEP_FRONT_F;
	}


	public void setDEP_FRONT_F(String dEP_FRONT_F) {
		DEP_FRONT_F = dEP_FRONT_F;
	}


	public BigDecimal getDEP_OPER_ACCT() {
		return DEP_OPER_ACCT;
	}


	public void setDEP_OPER_ACCT(BigDecimal dEP_OPER_ACCT) {
		DEP_OPER_ACCT = dEP_OPER_ACCT;
	}


	public BigDecimal getDEP_OPER_ACCTB() {
		return DEP_OPER_ACCTB;
	}


	public void setDEP_OPER_ACCTB(BigDecimal dEP_OPER_ACCTB) {
		DEP_OPER_ACCTB = dEP_OPER_ACCTB;
	}


	public String getDEP_OPER_BACCT() {
		return DEP_OPER_BACCT;
	}


	public void setDEP_OPER_BACCT(String dEP_OPER_BACCT) {
		DEP_OPER_BACCT = dEP_OPER_BACCT;
	}


	public BigDecimal getIN_FILE_NUM() {
		return IN_FILE_NUM;
	}


	public void setIN_FILE_NUM(BigDecimal iN_FILE_NUM) {
		IN_FILE_NUM = iN_FILE_NUM;
	}


	public BigDecimal getOPEN_INSTL() {
		return OPEN_INSTL;
	}


	public void setOPEN_INSTL(BigDecimal oPEN_INSTL) {
		OPEN_INSTL = oPEN_INSTL;
	}


	public BigDecimal getINSTL_LINE() {
		return INSTL_LINE;
	}


	public void setINSTL_LINE(BigDecimal iNSTL_LINE) {
		INSTL_LINE = iNSTL_LINE;
	}


	public String getINSTL_CONDSET() {
		return INSTL_CONDSET;
	}


	public void setINSTL_CONDSET(String iNSTL_CONDSET) {
		INSTL_CONDSET = iNSTL_CONDSET;
	}
	
	
	
}
