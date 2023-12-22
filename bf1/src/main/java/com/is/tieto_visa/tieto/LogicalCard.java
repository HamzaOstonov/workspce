package com.is.tieto_visa.tieto;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author D
 * CARD string(19) Номер карты
 * CLIENT string(8) Клиентский номер держателя карты
 * CL_ROLE string(1) Роль клиента.
 * CARD_TYPE string(2) Тип карты – дебетная, кредитная и пр.
 * BASE_SUPP string(1) Базовая или дополнительная карта
 * COND_SET string(3) Набор условий карты
 * RISK_LEVEL string(5) Уровень риска
 * CARD_SERVICES_SET string(16) Код набора сервисов карты
 * REC_DATE datetime Дата регистрации карты
 * M_NAME string(20) Девичья фамилия держателя карты
 * RELATION string(25) Отношение между владельцами дополнительной и базовой карты
 * ID_CARD string(16) Номер документа идентификации личности
 * B_DATE datetime Дата рождения
 * CALL_ID string(10) Поле ссылки интерфейса
 * F_NAMES string(34) Имя
 * SURNAME string(20) Фамилия
 * F_NAME1 string(20) Имя
 * MIDLE_NAME string(20) Отчество
 * SERIAL_NO string(20) Серийный номер документа идентификации личности
 * DOC_SINCE datetime Дата издания документа
 * CMPG_NAME string(24) Название корпоративной карты или Branch для частного клиента 
 * CRD_HOLD_MSG string(99) Сообщение карта держателю
 * INSURANC_TYPE string(2) Тип страховки
 * INSURANC_DATE datetime Дата последнего страховочного платежа
 * U_COD9 string(3) Заполняемый пользователем код 9
 * U_COD10 string(6) Заполняемый пользователем код 10
 * U_FIELD7 string(20) Заполняемое пользователем поле 7
 * U_FIELD8 string(20) Заполняемое пользователем поле 8
 * IN_FILE_NUM Decimal(7,0) Идентификатор задания пакета (Batch task ID)
 * OUT_FILE_NUM Decimal(20,0) Эмбоссирование номера исходящего файла
 * USRID string(6) Идентификационный номер пользователя
 * CTIME datetime Дата и время (по умолчанию sysdate) последнего ввода или модификации 
 * EFFECTIVE_DATE1 dateTime Специальное поле для Amex – дата, когда карта становится действительна;
 * COND_SET_2 string(3) Уникальное значение (идентификация) нового набора условий (Condition set). 3 символа.
 * COND_CHANGE_DATE dateTime дата смены набора условий карточек.
 * CHANGE_BACK_DATE dateTime дата возврата предыдущего набора условий карточек.
 * BRANCH String(5) Код филиала.
 * U_FIELD11 string(20) Заполняемое пользователем поле 11
 * U_FIELD12 string(20) Заполняемое пользователем поле 12
 * U_FIELD13 string(20) Заполняемое пользователем поле 13
 * U_FIELD14 string(20) Заполняемое пользователем поле 14
 * NO_NAME String(1)
 * RANGE_ID Decimal(5,0) Идентификатор диапазона карточных номеров.
 */

public class LogicalCard {

	
	
	private String CARD;
	
	
	private String CLIENT;
	
	
	private String CL_ROLE;
	
	
	private String CARD_TYPE;
	
	
	private String BASE_SUPP;
	
	
	private String COND_SET;
	
	
	private String RISK_LEVEL;
	
	
	private String CARD_SERVICES_SET;
	
	
	private Date REC_DATE;
	
	
	private String M_NAME;
	
	
	private String RELATION;
	
	
	private String ID_CARD;
	
	
	private Date B_DATE;
	
	
	private String CALL_ID;
	
	
	private String F_NAMES;
	
	
	private String SURNAME;
	
	
	private String F_NAME1;
	
	
	private String MIDLE_NAME;
	
	
	private String SERIAL_NO;
	
	
	private Date DOC_SINCE;
	
	
	private String CMPG_NAME;
	
	
	private String CRD_HOLD_MSG;
	
	
	private String INSURANC_TYPE;
	
	
	private Date INSURANC_DATE;
	
	
	private String U_COD9;
	
	
	private String U_COD10;
	
	
	private String U_FIELD7;
	
	
	private String U_FIELD8;
	
	
	private BigDecimal IN_FILE_NUM;
	
	
	private BigDecimal OUT_FILE_NUM;
	
	
	private String USRID;
	
	
	private Date CTIME;
	
	
	private Date EFFECTIVE_DATE1;
	
	
	private String COND_SET_2;
	
	
	private Date COND_CHANGE_DATE;
	
	
	private Date CHANGE_BACK_DATE;
	
	
	private String BRANCH;
	
	
	private String U_FIELD11;
	
	
	private String U_FIELD12;
	
	
	private String U_FIELD13;
	
	
	private String U_FIELD14;
	
	
	private String NO_NAME;
	
	
	private BigDecimal RANGE_ID;


	public String getCARD() {
		return CARD;
	}


	public void setCARD(String cARD) {
		CARD = cARD;
	}


	public String getCLIENT() {
		return CLIENT;
	}


	public void setCLIENT(String cLIENT) {
		CLIENT = cLIENT;
	}


	public String getCL_ROLE() {
		return CL_ROLE;
	}


	public void setCL_ROLE(String cL_ROLE) {
		CL_ROLE = cL_ROLE;
	}


	public String getCARD_TYPE() {
		return CARD_TYPE;
	}


	public void setCARD_TYPE(String cARD_TYPE) {
		CARD_TYPE = cARD_TYPE;
	}


	public String getBASE_SUPP() {
		return BASE_SUPP;
	}


	public void setBASE_SUPP(String bASE_SUPP) {
		BASE_SUPP = bASE_SUPP;
	}


	public String getCOND_SET() {
		return COND_SET;
	}


	public void setCOND_SET(String cOND_SET) {
		COND_SET = cOND_SET;
	}


	public String getRISK_LEVEL() {
		return RISK_LEVEL;
	}


	public void setRISK_LEVEL(String rISK_LEVEL) {
		RISK_LEVEL = rISK_LEVEL;
	}


	public String getCARD_SERVICES_SET() {
		return CARD_SERVICES_SET;
	}


	public void setCARD_SERVICES_SET(String cARD_SERVICES_SET) {
		CARD_SERVICES_SET = cARD_SERVICES_SET;
	}


	public Date getREC_DATE() {
		return REC_DATE;
	}


	public void setREC_DATE(Date rEC_DATE) {
		REC_DATE = rEC_DATE;
	}


	public String getM_NAME() {
		return M_NAME;
	}


	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}


	public String getRELATION() {
		return RELATION;
	}


	public void setRELATION(String rELATION) {
		RELATION = rELATION;
	}


	public String getID_CARD() {
		return ID_CARD;
	}


	public void setID_CARD(String iD_CARD) {
		ID_CARD = iD_CARD;
	}


	public Date getB_DATE() {
		return B_DATE;
	}


	public void setB_DATE(Date b_DATE) {
		B_DATE = b_DATE;
	}


	public String getCALL_ID() {
		return CALL_ID;
	}


	public void setCALL_ID(String cALL_ID) {
		CALL_ID = cALL_ID;
	}


	public String getF_NAMES() {
		return F_NAMES;
	}


	public void setF_NAMES(String f_NAMES) {
		F_NAMES = f_NAMES;
	}


	public String getSURNAME() {
		return SURNAME;
	}


	public void setSURNAME(String sURNAME) {
		SURNAME = sURNAME;
	}


	public String getF_NAME1() {
		return F_NAME1;
	}


	public void setF_NAME1(String f_NAME1) {
		F_NAME1 = f_NAME1;
	}


	public String getMIDLE_NAME() {
		return MIDLE_NAME;
	}


	public void setMIDLE_NAME(String mIDLE_NAME) {
		MIDLE_NAME = mIDLE_NAME;
	}


	public String getSERIAL_NO() {
		return SERIAL_NO;
	}


	public void setSERIAL_NO(String sERIAL_NO) {
		SERIAL_NO = sERIAL_NO;
	}


	public Date getDOC_SINCE() {
		return DOC_SINCE;
	}


	public void setDOC_SINCE(Date dOC_SINCE) {
		DOC_SINCE = dOC_SINCE;
	}


	public String getCMPG_NAME() {
		return CMPG_NAME;
	}


	public void setCMPG_NAME(String cMPG_NAME) {
		CMPG_NAME = cMPG_NAME;
	}


	public String getCRD_HOLD_MSG() {
		return CRD_HOLD_MSG;
	}


	public void setCRD_HOLD_MSG(String cRD_HOLD_MSG) {
		CRD_HOLD_MSG = cRD_HOLD_MSG;
	}


	public String getINSURANC_TYPE() {
		return INSURANC_TYPE;
	}


	public void setINSURANC_TYPE(String iNSURANC_TYPE) {
		INSURANC_TYPE = iNSURANC_TYPE;
	}


	public Date getINSURANC_DATE() {
		return INSURANC_DATE;
	}


	public void setINSURANC_DATE(Date iNSURANC_DATE) {
		INSURANC_DATE = iNSURANC_DATE;
	}


	public String getU_COD9() {
		return U_COD9;
	}


	public void setU_COD9(String u_COD9) {
		U_COD9 = u_COD9;
	}


	public String getU_COD10() {
		return U_COD10;
	}


	public void setU_COD10(String u_COD10) {
		U_COD10 = u_COD10;
	}


	public String getU_FIELD7() {
		return U_FIELD7;
	}


	public void setU_FIELD7(String u_FIELD7) {
		U_FIELD7 = u_FIELD7;
	}


	public String getU_FIELD8() {
		return U_FIELD8;
	}


	public void setU_FIELD8(String u_FIELD8) {
		U_FIELD8 = u_FIELD8;
	}


	public BigDecimal getIN_FILE_NUM() {
		return IN_FILE_NUM;
	}


	public void setIN_FILE_NUM(BigDecimal iN_FILE_NUM) {
		IN_FILE_NUM = iN_FILE_NUM;
	}


	public BigDecimal getOUT_FILE_NUM() {
		return OUT_FILE_NUM;
	}


	public void setOUT_FILE_NUM(BigDecimal oUT_FILE_NUM) {
		OUT_FILE_NUM = oUT_FILE_NUM;
	}


	public String getUSRID() {
		return USRID;
	}


	public void setUSRID(String uSRID) {
		USRID = uSRID;
	}


	public Date getCTIME() {
		return CTIME;
	}


	public void setCTIME(Date cTIME) {
		CTIME = cTIME;
	}


	public Date getEFFECTIVE_DATE1() {
		return EFFECTIVE_DATE1;
	}


	public void setEFFECTIVE_DATE1(Date eFFECTIVE_DATE1) {
		EFFECTIVE_DATE1 = eFFECTIVE_DATE1;
	}


	public String getCOND_SET_2() {
		return COND_SET_2;
	}


	public void setCOND_SET_2(String cOND_SET_2) {
		COND_SET_2 = cOND_SET_2;
	}


	public Date getCOND_CHANGE_DATE() {
		return COND_CHANGE_DATE;
	}


	public void setCOND_CHANGE_DATE(Date cOND_CHANGE_DATE) {
		COND_CHANGE_DATE = cOND_CHANGE_DATE;
	}


	public Date getCHANGE_BACK_DATE() {
		return CHANGE_BACK_DATE;
	}


	public void setCHANGE_BACK_DATE(Date cHANGE_BACK_DATE) {
		CHANGE_BACK_DATE = cHANGE_BACK_DATE;
	}


	public String getBRANCH() {
		return BRANCH;
	}


	public void setBRANCH(String bRANCH) {
		BRANCH = bRANCH;
	}


	public String getU_FIELD11() {
		return U_FIELD11;
	}


	public void setU_FIELD11(String u_FIELD11) {
		U_FIELD11 = u_FIELD11;
	}


	public String getU_FIELD12() {
		return U_FIELD12;
	}


	public void setU_FIELD12(String u_FIELD12) {
		U_FIELD12 = u_FIELD12;
	}


	public String getU_FIELD13() {
		return U_FIELD13;
	}


	public void setU_FIELD13(String u_FIELD13) {
		U_FIELD13 = u_FIELD13;
	}


	public String getU_FIELD14() {
		return U_FIELD14;
	}


	public void setU_FIELD14(String u_FIELD14) {
		U_FIELD14 = u_FIELD14;
	}


	public String getNO_NAME() {
		return NO_NAME;
	}


	public void setNO_NAME(String nO_NAME) {
		NO_NAME = nO_NAME;
	}


	public BigDecimal getRANGE_ID() {
		return RANGE_ID;
	}


	public void setRANGE_ID(BigDecimal rANGE_ID) {
		RANGE_ID = rANGE_ID;
	}


	public LogicalCard(String cARD, String cLIENT, String cL_ROLE,
			String cARD_TYPE, String bASE_SUPP, String cOND_SET,
			String rISK_LEVEL, String cARD_SERVICES_SET, Date rEC_DATE,
			String m_NAME, String rELATION, String iD_CARD, Date b_DATE,
			String cALL_ID, String f_NAMES, String sURNAME, String f_NAME1,
			String mIDLE_NAME, String sERIAL_NO, Date dOC_SINCE,
			String cMPG_NAME, String cRD_HOLD_MSG, String iNSURANC_TYPE,
			Date iNSURANC_DATE, String u_COD9, String u_COD10, String u_FIELD7,
			String u_FIELD8, BigDecimal iN_FILE_NUM, BigDecimal oUT_FILE_NUM,
			String uSRID, Date cTIME, Date eFFECTIVE_DATE1, String cOND_SET_2,
			Date cOND_CHANGE_DATE, Date cHANGE_BACK_DATE, String bRANCH,
			String u_FIELD11, String u_FIELD12, String u_FIELD13,
			String u_FIELD14, String nO_NAME, BigDecimal rANGE_ID) {
		super();
		CARD = cARD;
		CLIENT = cLIENT;
		CL_ROLE = cL_ROLE;
		CARD_TYPE = cARD_TYPE;
		BASE_SUPP = bASE_SUPP;
		COND_SET = cOND_SET;
		RISK_LEVEL = rISK_LEVEL;
		CARD_SERVICES_SET = cARD_SERVICES_SET;
		REC_DATE = rEC_DATE;
		M_NAME = m_NAME;
		RELATION = rELATION;
		ID_CARD = iD_CARD;
		B_DATE = b_DATE;
		CALL_ID = cALL_ID;
		F_NAMES = f_NAMES;
		SURNAME = sURNAME;
		F_NAME1 = f_NAME1;
		MIDLE_NAME = mIDLE_NAME;
		SERIAL_NO = sERIAL_NO;
		DOC_SINCE = dOC_SINCE;
		CMPG_NAME = cMPG_NAME;
		CRD_HOLD_MSG = cRD_HOLD_MSG;
		INSURANC_TYPE = iNSURANC_TYPE;
		INSURANC_DATE = iNSURANC_DATE;
		U_COD9 = u_COD9;
		U_COD10 = u_COD10;
		U_FIELD7 = u_FIELD7;
		U_FIELD8 = u_FIELD8;
		IN_FILE_NUM = iN_FILE_NUM;
		OUT_FILE_NUM = oUT_FILE_NUM;
		USRID = uSRID;
		CTIME = cTIME;
		EFFECTIVE_DATE1 = eFFECTIVE_DATE1;
		COND_SET_2 = cOND_SET_2;
		COND_CHANGE_DATE = cOND_CHANGE_DATE;
		CHANGE_BACK_DATE = cHANGE_BACK_DATE;
		BRANCH = bRANCH;
		U_FIELD11 = u_FIELD11;
		U_FIELD12 = u_FIELD12;
		U_FIELD13 = u_FIELD13;
		U_FIELD14 = u_FIELD14;
		NO_NAME = nO_NAME;
		RANGE_ID = rANGE_ID;
	}


	public LogicalCard() {
		super();
	}
	
	
}
