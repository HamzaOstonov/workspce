package com.is.tieto_visa.tieto;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author D
 * APARTMENT string(5) Номер квартиры
 * BUILDING string(15) Номер здания
 * CALL_ID string(10) Номер ссылки интерфейса
 * CCY_FOR_INCOM string(3) Валюта для годового дохода и имущества
 * CLIENT string(8) Клиентский номер держателя карты. 
 * CLIENT_B string(19) Номер клиента в банке
 * CLN_CAT string(3) Категория клиента
 * CL_TYPE string(1) Тип клиента: ‘1’ – Private; ‘2’ – Corporate. *
 * CMPG_NAME string(24) Название корпоративной карты 
 * CMP_NAME string(34) Название компании
 * CNT_E_MAILS string(100) Адрес электронной почты контактного лица
 * CNT_FAX string(15) Номер факса контактного лица
 * CNT_MOB_PHONE string(15) Мобильный телефон контактного лица 
 * CNT_PHONE string(15) Контактный телефон
 * CNT_TITLE string(2) Код звания (обращения) контактного лица
 * COMENT string(400) Комментарий пользователя
 * CONTACT string(25) Имя, фамилия контактного лица
 * CO_CODE string(5) Код корпоративного клиента
 * CO_POSITON string(26) Расположение корпоративного клиента
 * CR_CITY string(20) Адрес компании – город
 * CR_CNTRY string(3) Адрес компании – код страны
 * CR_E_MAILS string(100) Адрес электронной почты компании
 * CR_PCODE string(7) Адрес компании – почтовый индекс
 * CR_STREET string(60) Адрес компании – улица, дом, квартира
 * CTIME dateTime Дата и время (по умолчанию sysdate) внесения или обновления
 * C_SINCE string(4) Клиент банка с ... (дата)
 * DOC_SINCE dateTime Идентификационный документ личности с 
 * DOC_TYPE string(3) Код идентификационного документа
 * EMP_ADR string(120) Адрес работодателя клиента
 * EMP_CODE string(5) Код работодателя клиента 
 * EMP_DATE dateTime Дата работодателя
 * EMP_E_MAILS string(100) Адрес электронной почты работодателя клиента
 * EMP_FAX string(15) Номер факса работодателя клиента
 * EMP_NAME string(34) Имя работодателя клиента 
 * F_NAMES string(34) Имя
 * HOME_NUMBER string(15) Домашний номер
 * ID_CARD string(16) Номер идентификационного документа личности
 * IMM_PROP_VALUE Decimal(12,0) Стоимость недвижимости
 * IN_FILE_NUM Decimal(22,0) Идентификатор задания пакета (Batch task ID)
 * ISSUED_BY string(120) Издатель идентификационного документа личности
 * MARITAL_STATUS string(1) Семейный статус частного лица
 * MIDLE_NAME string(20) Второе имя
 * MNG_E_MAILS string(100) Адрес электронной почты менеджера 
 * MNG_FAX string(15) Номер факса менеджера
 * MNG_MOB_PHONE string(15) Номер мобильного телефона менеджера
 * MNG_NAME string(25) Имя и фамилия менеджера
 * MNG_PHONE string(15) Телефон менеджера
 * MNG_POSIT string(25) Должность менеджера
 * MNG_TITLE string(2) Код звания (обращения) менеджера
 * M_NAME string(20) Девичья фамилия держателя карты
 * PERSON_CODE string(20) Код личности (код плательщика налогов)
 * POSITION string(26) Должность клиента
 * REC_DATE dateTime Дата первой записи договора
 * REGION string(2) Код региона
 * REG_NR string(25) Регистрационный номер компании
 * RESIDENT string(1) Резидент или нерезидент: ‘1’ – резидент; ‘2’ – нерезидент. *
 * RESIDENT_SINCE dateTime Резидент с
 * R_CITY string(20) Адрес проживания – город
 * R_CNTRY string(3) Адрес проживания – код
 * R_E_MAILS string(100) Адрес электронной почты
 * R_FAX string(15) Адрес проживания – номер факса
 * R_MOB_PHONE string(15) Номер мобильного телефона клиента
 * R_PCODE string(7) Адрес проживания – почтовый индекс
 * R_PHONE string(15) Номер домашнего телефона клиента
 * R_STREET string(95) Адрес проживания – улица, дом, квартира
 * SEARCH_NAME string(55) Имя для поиска в верхнем регистре без кавычек 
 * SERIAL_NO string(15) Серийный номер документа идентификации личности
 * SEX string(1) Пол клиента (1 – мужской, 2 – женский)
 * STATUS string(2) Статус клиента <'3929' активен, иначе закрыт *
 * STREET1 string(95) Улица
 * SURNAME string(20) Фамилия
 * TITLE string(2) Код звания (обращения)
 * USRID string(6) Идентификационный номер пользователя
 * U_COD1 string(3) Заполненный пользователем code_1
 * U_COD2 string(6) Заполненный пользователем code_2
 * U_COD3 string(6) Заполненный пользователем code_3
 * U_FIELD1 string(20) Заполненный пользователем field 1
 * U_FIELD2 string(20) Заполненный пользователем field_2
 * WORK_PHONE string(15) Номер рабочего телефона клиента
 * YEAR_INC Decimal(12,0) Годовой доход
 * AMEX_MEMBER_SINCE dateTime Может иметь пустое значение (null). Дата, когда клиент присоединился к AmEx .
 * DCI_MEMBER_SINCE dateTime Дата, когда клиент присоединился к Diners Club .
 * REWARD_NO Varchar2(10) Номер счёта награды (Reward GlobuzAccount Number).
 * NATIONALITY string(3) Национальность (код).
 */

public class TietoCustomer {
	
	private String APARTMENT;
	
	
	private String BUILDING;
	
	
	private Date B_DATE;
	
	
	private String CALL_ID;
	
	
	private String CCY_FOR_INCOM;
	
	
	private String CLIENT;
	
	
	private String CLIENT_B;
	
	
	private String CLN_CAT;
	
	
	private String CL_TYPE;
	
	
	private String CMPG_NAME;
	
	
	private String CMP_NAME;
	
	
	private String CNT_E_MAILS;
	
	
	private String CNT_FAX;
	
	
	private String CNT_MOB_PHONE;
	
	
	private String CNT_PHONE;
	
	
	private String CNT_TITLE;
	
	
	private String COMENT;
	
	
	private String CONTACT;
	
	
	private String CO_CODE;
	
	
	private String CO_POSITON;
	
	
	private String CR_CITY;
	
	
	private String CR_CNTRY;
	
	
	private String CR_E_MAILS;
	
	
	private String CR_PCODE;
	
	
	private String CR_STREET;
	
	
	private Date CTIME;
	
	
	private String C_SINCE;
	
	
	private Date DOC_SINCE;
	
	
	private String DOC_TYPE;
	
	
	private String EMP_ADR;
	
	
	private String EMP_CODE;
	
	
	private Date EMP_DATE;
	
	
	private String EMP_E_MAILS;
	
	
	private String EMP_FAX;
	
	
	private String EMP_NAME;
	
	
	private String F_NAMES;
	
	
	private String HOME_NUMBER;
	
	
	private String ID_CARD;
	
	
	private BigDecimal IMM_PROP_VALUE;
	
	
	private BigDecimal IN_FILE_NUM;
	
	
	private String ISSUED_BY;
	
	
	private String MARITAL_STATUS;
	
	
	private String MIDLE_NAME;
	
	
	private String MNG_E_MAILS;
	
	
	private String MNG_FAX;
	
	
	private String MNG_MOB_PHONE;
	
	
	private String MNG_NAME;
	
	
	private String MNG_PHONE;
	
	
	private String MNG_POSIT;
	
	
	private String MNG_TITLE;
	
	
	private String M_NAME;
	
	
	private String PERSON_CODE;
	
	
	private String POSITION;
	
	
	private Date REC_DATE;
	
	
	private String REGION;
	
	
	private String REG_NR;
	
	
	private String RESIDENT;
	
	
	private Date RESIDENT_SINCE;
	
	
	private String R_CITY;
	
	
	private String R_CNTRY;
	
	
	private String R_E_MAILS;
	
	
	private String R_FAX;
	
	
	private String R_MOB_PHONE;
	
	
	private String R_PCODE;
	
	
	private String R_PHONE;
	
	
	private String R_STREET;
	
	
	private String SEARCH_NAME;
	
	
	private String SERIAL_NO;
	
	
	private String SEX;
	
	
	private String STATUS;
	
	
	private String STREET1;
	
	
	private String SURNAME;
	
	
	private String TITLE;
	
	
	private String USRID;
	
	
	private String U_COD1;
	
	
	private String U_COD2;
	
	
	private String U_COD3;
	
	
	private String U_FIELD1;
	
	
	private String U_FIELD2;
	
	
	private String WORK_PHONE;
	
	
	private BigDecimal YEAR_INC;
	
	
	private Date AMEX_MEMBER_SINCE;
	
	
	private Date DCI_MEMBER_SINCE;
	
	
	private String REWARD_NO;
	
	
	private String NATIONALITY;
	
	private List<Accnt> accounts = new ArrayList<Accnt>();

	public String getAPARTMENT() {
		return APARTMENT;
	}

	public void setAPARTMENT(String aPARTMENT) {
		APARTMENT = aPARTMENT;
	}

	public String getBUILDING() {
		return BUILDING;
	}

	public void setBUILDING(String bUILDING) {
		BUILDING = bUILDING;
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

	public String getCCY_FOR_INCOM() {
		return CCY_FOR_INCOM;
	}

	public void setCCY_FOR_INCOM(String cCY_FOR_INCOM) {
		CCY_FOR_INCOM = cCY_FOR_INCOM;
	}

	public String getCLIENT() {
		return CLIENT;
	}

	public void setCLIENT(String cLIENT) {
		CLIENT = cLIENT;
	}

	public String getCLIENT_B() {
		return CLIENT_B;
	}

	public void setCLIENT_B(String cLIENT_B) {
		CLIENT_B = cLIENT_B;
	}

	public String getCLN_CAT() {
		return CLN_CAT;
	}

	public void setCLN_CAT(String cLN_CAT) {
		CLN_CAT = cLN_CAT;
	}

	public String getCL_TYPE() {
		return CL_TYPE;
	}

	public void setCL_TYPE(String cL_TYPE) {
		CL_TYPE = cL_TYPE;
	}

	public String getCMPG_NAME() {
		return CMPG_NAME;
	}

	public void setCMPG_NAME(String cMPG_NAME) {
		CMPG_NAME = cMPG_NAME;
	}

	public String getCMP_NAME() {
		return CMP_NAME;
	}

	public void setCMP_NAME(String cMP_NAME) {
		CMP_NAME = cMP_NAME;
	}

	public String getCNT_E_MAILS() {
		return CNT_E_MAILS;
	}

	public void setCNT_E_MAILS(String cNT_E_MAILS) {
		CNT_E_MAILS = cNT_E_MAILS;
	}

	public String getCNT_FAX() {
		return CNT_FAX;
	}

	public void setCNT_FAX(String cNT_FAX) {
		CNT_FAX = cNT_FAX;
	}

	public String getCNT_MOB_PHONE() {
		return CNT_MOB_PHONE;
	}

	public void setCNT_MOB_PHONE(String cNT_MOB_PHONE) {
		CNT_MOB_PHONE = cNT_MOB_PHONE;
	}

	public String getCNT_PHONE() {
		return CNT_PHONE;
	}

	public void setCNT_PHONE(String cNT_PHONE) {
		CNT_PHONE = cNT_PHONE;
	}

	public String getCNT_TITLE() {
		return CNT_TITLE;
	}

	public void setCNT_TITLE(String cNT_TITLE) {
		CNT_TITLE = cNT_TITLE;
	}

	public String getCOMENT() {
		return COMENT;
	}

	public void setCOMENT(String cOMENT) {
		COMENT = cOMENT;
	}

	public String getCONTACT() {
		return CONTACT;
	}

	public void setCONTACT(String cONTACT) {
		CONTACT = cONTACT;
	}

	public String getCO_CODE() {
		return CO_CODE;
	}

	public void setCO_CODE(String cO_CODE) {
		CO_CODE = cO_CODE;
	}

	public String getCO_POSITON() {
		return CO_POSITON;
	}

	public void setCO_POSITON(String cO_POSITON) {
		CO_POSITON = cO_POSITON;
	}

	public String getCR_CITY() {
		return CR_CITY;
	}

	public void setCR_CITY(String cR_CITY) {
		CR_CITY = cR_CITY;
	}

	public String getCR_CNTRY() {
		return CR_CNTRY;
	}

	public void setCR_CNTRY(String cR_CNTRY) {
		CR_CNTRY = cR_CNTRY;
	}

	public String getCR_E_MAILS() {
		return CR_E_MAILS;
	}

	public void setCR_E_MAILS(String cR_E_MAILS) {
		CR_E_MAILS = cR_E_MAILS;
	}

	public String getCR_PCODE() {
		return CR_PCODE;
	}

	public void setCR_PCODE(String cR_PCODE) {
		CR_PCODE = cR_PCODE;
	}

	public String getCR_STREET() {
		return CR_STREET;
	}

	public void setCR_STREET(String cR_STREET) {
		CR_STREET = cR_STREET;
	}

	public Date getCTIME() {
		return CTIME;
	}

	public void setCTIME(Date cTIME) {
		CTIME = cTIME;
	}

	public String getC_SINCE() {
		return C_SINCE;
	}

	public void setC_SINCE(String c_SINCE) {
		C_SINCE = c_SINCE;
	}

	public Date getDOC_SINCE() {
		return DOC_SINCE;
	}

	public void setDOC_SINCE(Date dOC_SINCE) {
		DOC_SINCE = dOC_SINCE;
	}

	public String getDOC_TYPE() {
		return DOC_TYPE;
	}

	public void setDOC_TYPE(String dOC_TYPE) {
		DOC_TYPE = dOC_TYPE;
	}

	public String getEMP_ADR() {
		return EMP_ADR;
	}

	public void setEMP_ADR(String eMP_ADR) {
		EMP_ADR = eMP_ADR;
	}

	public String getEMP_CODE() {
		return EMP_CODE;
	}

	public void setEMP_CODE(String eMP_CODE) {
		EMP_CODE = eMP_CODE;
	}

	public Date getEMP_DATE() {
		return EMP_DATE;
	}

	public void setEMP_DATE(Date eMP_DATE) {
		EMP_DATE = eMP_DATE;
	}

	public String getEMP_E_MAILS() {
		return EMP_E_MAILS;
	}

	public void setEMP_E_MAILS(String eMP_E_MAILS) {
		EMP_E_MAILS = eMP_E_MAILS;
	}

	public String getEMP_FAX() {
		return EMP_FAX;
	}

	public void setEMP_FAX(String eMP_FAX) {
		EMP_FAX = eMP_FAX;
	}

	public String getEMP_NAME() {
		return EMP_NAME;
	}

	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}

	public String getF_NAMES() {
		return F_NAMES;
	}

	public void setF_NAMES(String f_NAMES) {
		F_NAMES = f_NAMES;
	}

	public String getHOME_NUMBER() {
		return HOME_NUMBER;
	}

	public void setHOME_NUMBER(String hOME_NUMBER) {
		HOME_NUMBER = hOME_NUMBER;
	}

	public String getID_CARD() {
		return ID_CARD;
	}

	public void setID_CARD(String iD_CARD) {
		ID_CARD = iD_CARD;
	}

	public BigDecimal getIMM_PROP_VALUE() {
		return IMM_PROP_VALUE;
	}

	public void setIMM_PROP_VALUE(BigDecimal iMM_PROP_VALUE) {
		IMM_PROP_VALUE = iMM_PROP_VALUE;
	}

	public BigDecimal getIN_FILE_NUM() {
		return IN_FILE_NUM;
	}

	public void setIN_FILE_NUM(BigDecimal iN_FILE_NUM) {
		IN_FILE_NUM = iN_FILE_NUM;
	}

	public String getISSUED_BY() {
		return ISSUED_BY;
	}

	public void setISSUED_BY(String iSSUED_BY) {
		ISSUED_BY = iSSUED_BY;
	}

	public String getMARITAL_STATUS() {
		return MARITAL_STATUS;
	}

	public void setMARITAL_STATUS(String mARITAL_STATUS) {
		MARITAL_STATUS = mARITAL_STATUS;
	}

	public String getMIDLE_NAME() {
		return MIDLE_NAME;
	}

	public void setMIDLE_NAME(String mIDLE_NAME) {
		MIDLE_NAME = mIDLE_NAME;
	}

	public String getMNG_E_MAILS() {
		return MNG_E_MAILS;
	}

	public void setMNG_E_MAILS(String mNG_E_MAILS) {
		MNG_E_MAILS = mNG_E_MAILS;
	}

	public String getMNG_FAX() {
		return MNG_FAX;
	}

	public void setMNG_FAX(String mNG_FAX) {
		MNG_FAX = mNG_FAX;
	}

	public String getMNG_MOB_PHONE() {
		return MNG_MOB_PHONE;
	}

	public void setMNG_MOB_PHONE(String mNG_MOB_PHONE) {
		MNG_MOB_PHONE = mNG_MOB_PHONE;
	}

	public String getMNG_NAME() {
		return MNG_NAME;
	}

	public void setMNG_NAME(String mNG_NAME) {
		MNG_NAME = mNG_NAME;
	}

	public String getMNG_PHONE() {
		return MNG_PHONE;
	}

	public void setMNG_PHONE(String mNG_PHONE) {
		MNG_PHONE = mNG_PHONE;
	}

	public String getMNG_POSIT() {
		return MNG_POSIT;
	}

	public void setMNG_POSIT(String mNG_POSIT) {
		MNG_POSIT = mNG_POSIT;
	}

	public String getMNG_TITLE() {
		return MNG_TITLE;
	}

	public void setMNG_TITLE(String mNG_TITLE) {
		MNG_TITLE = mNG_TITLE;
	}

	public String getM_NAME() {
		return M_NAME;
	}

	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}

	public String getPERSON_CODE() {
		return PERSON_CODE;
	}

	public void setPERSON_CODE(String pERSON_CODE) {
		PERSON_CODE = pERSON_CODE;
	}

	public String getPOSITION() {
		return POSITION;
	}

	public void setPOSITION(String pOSITION) {
		POSITION = pOSITION;
	}

	public Date getREC_DATE() {
		return REC_DATE;
	}

	public void setREC_DATE(Date rEC_DATE) {
		REC_DATE = rEC_DATE;
	}

	public String getREGION() {
		return REGION;
	}

	public void setREGION(String rEGION) {
		REGION = rEGION;
	}

	public String getREG_NR() {
		return REG_NR;
	}

	public void setREG_NR(String rEG_NR) {
		REG_NR = rEG_NR;
	}

	public String getRESIDENT() {
		return RESIDENT;
	}

	public void setRESIDENT(String rESIDENT) {
		RESIDENT = rESIDENT;
	}

	public Date getRESIDENT_SINCE() {
		return RESIDENT_SINCE;
	}

	public void setRESIDENT_SINCE(Date rESIDENT_SINCE) {
		RESIDENT_SINCE = rESIDENT_SINCE;
	}

	public String getR_CITY() {
		return R_CITY;
	}

	public void setR_CITY(String r_CITY) {
		R_CITY = r_CITY;
	}

	public String getR_CNTRY() {
		return R_CNTRY;
	}

	public void setR_CNTRY(String r_CNTRY) {
		R_CNTRY = r_CNTRY;
	}

	public String getR_E_MAILS() {
		return R_E_MAILS;
	}

	public void setR_E_MAILS(String r_E_MAILS) {
		R_E_MAILS = r_E_MAILS;
	}

	public String getR_FAX() {
		return R_FAX;
	}

	public void setR_FAX(String r_FAX) {
		R_FAX = r_FAX;
	}

	public String getR_MOB_PHONE() {
		return R_MOB_PHONE;
	}

	public void setR_MOB_PHONE(String r_MOB_PHONE) {
		R_MOB_PHONE = r_MOB_PHONE;
	}

	public String getR_PCODE() {
		return R_PCODE;
	}

	public void setR_PCODE(String r_PCODE) {
		R_PCODE = r_PCODE;
	}

	public String getR_PHONE() {
		return R_PHONE;
	}

	public void setR_PHONE(String r_PHONE) {
		R_PHONE = r_PHONE;
	}

	public String getR_STREET() {
		return R_STREET;
	}

	public void setR_STREET(String r_STREET) {
		R_STREET = r_STREET;
	}

	public String getSEARCH_NAME() {
		return SEARCH_NAME;
	}

	public void setSEARCH_NAME(String sEARCH_NAME) {
		SEARCH_NAME = sEARCH_NAME;
	}

	public String getSERIAL_NO() {
		return SERIAL_NO;
	}

	public void setSERIAL_NO(String sERIAL_NO) {
		SERIAL_NO = sERIAL_NO;
	}

	public String getSEX() {
		return SEX;
	}

	public void setSEX(String sEX) {
		SEX = sEX;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getSTREET1() {
		return STREET1;
	}

	public void setSTREET1(String sTREET1) {
		STREET1 = sTREET1;
	}

	public String getSURNAME() {
		return SURNAME;
	}

	public void setSURNAME(String sURNAME) {
		SURNAME = sURNAME;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getUSRID() {
		return USRID;
	}

	public void setUSRID(String uSRID) {
		USRID = uSRID;
	}

	public String getU_COD1() {
		return U_COD1;
	}

	public void setU_COD1(String u_COD1) {
		U_COD1 = u_COD1;
	}

	public String getU_COD2() {
		return U_COD2;
	}

	public void setU_COD2(String u_COD2) {
		U_COD2 = u_COD2;
	}

	public String getU_COD3() {
		return U_COD3;
	}

	public void setU_COD3(String u_COD3) {
		U_COD3 = u_COD3;
	}

	public String getU_FIELD1() {
		return U_FIELD1;
	}

	public void setU_FIELD1(String u_FIELD1) {
		U_FIELD1 = u_FIELD1;
	}

	public String getU_FIELD2() {
		return U_FIELD2;
	}

	public void setU_FIELD2(String u_FIELD2) {
		U_FIELD2 = u_FIELD2;
	}

	public String getWORK_PHONE() {
		return WORK_PHONE;
	}

	public void setWORK_PHONE(String wORK_PHONE) {
		WORK_PHONE = wORK_PHONE;
	}

	public BigDecimal getYEAR_INC() {
		return YEAR_INC;
	}

	public void setYEAR_INC(BigDecimal yEAR_INC) {
		YEAR_INC = yEAR_INC;
	}

	public Date getAMEX_MEMBER_SINCE() {
		return AMEX_MEMBER_SINCE;
	}

	public void setAMEX_MEMBER_SINCE(Date aMEX_MEMBER_SINCE) {
		AMEX_MEMBER_SINCE = aMEX_MEMBER_SINCE;
	}

	public Date getDCI_MEMBER_SINCE() {
		return DCI_MEMBER_SINCE;
	}

	public void setDCI_MEMBER_SINCE(Date dCI_MEMBER_SINCE) {
		DCI_MEMBER_SINCE = dCI_MEMBER_SINCE;
	}

	public String getREWARD_NO() {
		return REWARD_NO;
	}

	public void setREWARD_NO(String rEWARD_NO) {
		REWARD_NO = rEWARD_NO;
	}

	public String getNATIONALITY() {
		return NATIONALITY;
	}

	public void setNATIONALITY(String nATIONALITY) {
		NATIONALITY = nATIONALITY;
	}

	public List<Accnt> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Accnt> accounts) {
		this.accounts = accounts;
	}

	public TietoCustomer(String aPARTMENT, String bUILDING, Date b_DATE,
			String cALL_ID, String cCY_FOR_INCOM, String cLIENT,
			String cLIENT_B, String cLN_CAT, String cL_TYPE, String cMPG_NAME,
			String cMP_NAME, String cNT_E_MAILS, String cNT_FAX,
			String cNT_MOB_PHONE, String cNT_PHONE, String cNT_TITLE,
			String cOMENT, String cONTACT, String cO_CODE, String cO_POSITON,
			String cR_CITY, String cR_CNTRY, String cR_E_MAILS,
			String cR_PCODE, String cR_STREET, Date cTIME, String c_SINCE,
			Date dOC_SINCE, String dOC_TYPE, String eMP_ADR, String eMP_CODE,
			Date eMP_DATE, String eMP_E_MAILS, String eMP_FAX, String eMP_NAME,
			String f_NAMES, String hOME_NUMBER, String iD_CARD,
			BigDecimal iMM_PROP_VALUE, BigDecimal iN_FILE_NUM,
			String iSSUED_BY, String mARITAL_STATUS, String mIDLE_NAME,
			String mNG_E_MAILS, String mNG_FAX, String mNG_MOB_PHONE,
			String mNG_NAME, String mNG_PHONE, String mNG_POSIT,
			String mNG_TITLE, String m_NAME, String pERSON_CODE,
			String pOSITION, Date rEC_DATE, String rEGION, String rEG_NR,
			String rESIDENT, Date rESIDENT_SINCE, String r_CITY,
			String r_CNTRY, String r_E_MAILS, String r_FAX, String r_MOB_PHONE,
			String r_PCODE, String r_PHONE, String r_STREET,
			String sEARCH_NAME, String sERIAL_NO, String sEX, String sTATUS,
			String sTREET1, String sURNAME, String tITLE, String uSRID,
			String u_COD1, String u_COD2, String u_COD3, String u_FIELD1,
			String u_FIELD2, String wORK_PHONE, BigDecimal yEAR_INC,
			Date aMEX_MEMBER_SINCE, Date dCI_MEMBER_SINCE, String rEWARD_NO,
			String nATIONALITY, List<Accnt> accounts) {
		super();
		APARTMENT = aPARTMENT;
		BUILDING = bUILDING;
		B_DATE = b_DATE;
		CALL_ID = cALL_ID;
		CCY_FOR_INCOM = cCY_FOR_INCOM;
		CLIENT = cLIENT;
		CLIENT_B = cLIENT_B;
		CLN_CAT = cLN_CAT;
		CL_TYPE = cL_TYPE;
		CMPG_NAME = cMPG_NAME;
		CMP_NAME = cMP_NAME;
		CNT_E_MAILS = cNT_E_MAILS;
		CNT_FAX = cNT_FAX;
		CNT_MOB_PHONE = cNT_MOB_PHONE;
		CNT_PHONE = cNT_PHONE;
		CNT_TITLE = cNT_TITLE;
		COMENT = cOMENT;
		CONTACT = cONTACT;
		CO_CODE = cO_CODE;
		CO_POSITON = cO_POSITON;
		CR_CITY = cR_CITY;
		CR_CNTRY = cR_CNTRY;
		CR_E_MAILS = cR_E_MAILS;
		CR_PCODE = cR_PCODE;
		CR_STREET = cR_STREET;
		CTIME = cTIME;
		C_SINCE = c_SINCE;
		DOC_SINCE = dOC_SINCE;
		DOC_TYPE = dOC_TYPE;
		EMP_ADR = eMP_ADR;
		EMP_CODE = eMP_CODE;
		EMP_DATE = eMP_DATE;
		EMP_E_MAILS = eMP_E_MAILS;
		EMP_FAX = eMP_FAX;
		EMP_NAME = eMP_NAME;
		F_NAMES = f_NAMES;
		HOME_NUMBER = hOME_NUMBER;
		ID_CARD = iD_CARD;
		IMM_PROP_VALUE = iMM_PROP_VALUE;
		IN_FILE_NUM = iN_FILE_NUM;
		ISSUED_BY = iSSUED_BY;
		MARITAL_STATUS = mARITAL_STATUS;
		MIDLE_NAME = mIDLE_NAME;
		MNG_E_MAILS = mNG_E_MAILS;
		MNG_FAX = mNG_FAX;
		MNG_MOB_PHONE = mNG_MOB_PHONE;
		MNG_NAME = mNG_NAME;
		MNG_PHONE = mNG_PHONE;
		MNG_POSIT = mNG_POSIT;
		MNG_TITLE = mNG_TITLE;
		M_NAME = m_NAME;
		PERSON_CODE = pERSON_CODE;
		POSITION = pOSITION;
		REC_DATE = rEC_DATE;
		REGION = rEGION;
		REG_NR = rEG_NR;
		RESIDENT = rESIDENT;
		RESIDENT_SINCE = rESIDENT_SINCE;
		R_CITY = r_CITY;
		R_CNTRY = r_CNTRY;
		R_E_MAILS = r_E_MAILS;
		R_FAX = r_FAX;
		R_MOB_PHONE = r_MOB_PHONE;
		R_PCODE = r_PCODE;
		R_PHONE = r_PHONE;
		R_STREET = r_STREET;
		SEARCH_NAME = sEARCH_NAME;
		SERIAL_NO = sERIAL_NO;
		SEX = sEX;
		STATUS = sTATUS;
		STREET1 = sTREET1;
		SURNAME = sURNAME;
		TITLE = tITLE;
		USRID = uSRID;
		U_COD1 = u_COD1;
		U_COD2 = u_COD2;
		U_COD3 = u_COD3;
		U_FIELD1 = u_FIELD1;
		U_FIELD2 = u_FIELD2;
		WORK_PHONE = wORK_PHONE;
		YEAR_INC = yEAR_INC;
		AMEX_MEMBER_SINCE = aMEX_MEMBER_SINCE;
		DCI_MEMBER_SINCE = dCI_MEMBER_SINCE;
		REWARD_NO = rEWARD_NO;
		NATIONALITY = nATIONALITY;
		this.accounts = accounts;
	}

	public TietoCustomer() {
		super();
	}
	
	
	
}
