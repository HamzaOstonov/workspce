package com.is.tieto_visa.tieto;

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
	
}
