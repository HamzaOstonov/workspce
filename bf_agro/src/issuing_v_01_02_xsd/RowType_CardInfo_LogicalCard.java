/**
 * RowType_CardInfo_LogicalCard.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_CardInfo_LogicalCard  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String CLIENT;

    private java.lang.String CL_ROLE;

    private java.lang.String CARD_TYPE;

    private java.lang.String BASE_SUPP;

    private java.lang.String COND_SET;

    private java.lang.String RISK_LEVEL;

    private java.lang.String CARD_SERVICES_SET;

    private java.util.Calendar REC_DATE;

    private java.lang.String m_NAME;

    private java.lang.String RELATION;

    private java.lang.String ID_CARD;

    private java.util.Calendar b_DATE;

    private java.lang.String CALL_ID;

    private java.lang.String f_NAMES;

    private java.lang.String SURNAME;

    private java.lang.String f_NAME1;

    private java.lang.String MIDLE_NAME;

    private java.lang.String SERIAL_NO;

    private java.util.Calendar DOC_SINCE;

    private java.lang.String CMPG_NAME;

    private java.lang.String INSURANC_TYPE;

    private java.util.Calendar INSURANC_DATE;

    private java.lang.String CRD_HOLD_MSG;

    private java.lang.String u_COD9;

    private java.lang.String u_COD10;

    private java.lang.String u_FIELD7;

    private java.lang.String u_FIELD8;

    private java.math.BigDecimal IN_FILE_NUM;

    private java.math.BigDecimal OUT_FILE_NUM;

    private java.lang.String USRID;

    private java.util.Calendar CTIME;

    private java.util.Calendar EFFECTIVE_DATE1;

    private java.lang.String COND_SET_2;

    private java.util.Calendar COND_CHANGE_DATE;

    private java.util.Calendar CHANGE_BACK_DATE;

    private java.lang.String BRANCH;

    private java.lang.String u_FIELD11;

    private java.lang.String u_FIELD12;

    private java.lang.String u_FIELD13;

    private java.lang.String u_FIELD14;

    private java.lang.String NO_NAME;

    private java.math.BigDecimal RANGE_ID;

    public RowType_CardInfo_LogicalCard() {
    }

    public RowType_CardInfo_LogicalCard(
           java.lang.String CARD,
           java.lang.String CLIENT,
           java.lang.String CL_ROLE,
           java.lang.String CARD_TYPE,
           java.lang.String BASE_SUPP,
           java.lang.String COND_SET,
           java.lang.String RISK_LEVEL,
           java.lang.String CARD_SERVICES_SET,
           java.util.Calendar REC_DATE,
           java.lang.String m_NAME,
           java.lang.String RELATION,
           java.lang.String ID_CARD,
           java.util.Calendar b_DATE,
           java.lang.String CALL_ID,
           java.lang.String f_NAMES,
           java.lang.String SURNAME,
           java.lang.String f_NAME1,
           java.lang.String MIDLE_NAME,
           java.lang.String SERIAL_NO,
           java.util.Calendar DOC_SINCE,
           java.lang.String CMPG_NAME,
           java.lang.String INSURANC_TYPE,
           java.util.Calendar INSURANC_DATE,
           java.lang.String CRD_HOLD_MSG,
           java.lang.String u_COD9,
           java.lang.String u_COD10,
           java.lang.String u_FIELD7,
           java.lang.String u_FIELD8,
           java.math.BigDecimal IN_FILE_NUM,
           java.math.BigDecimal OUT_FILE_NUM,
           java.lang.String USRID,
           java.util.Calendar CTIME,
           java.util.Calendar EFFECTIVE_DATE1,
           java.lang.String COND_SET_2,
           java.util.Calendar COND_CHANGE_DATE,
           java.util.Calendar CHANGE_BACK_DATE,
           java.lang.String BRANCH,
           java.lang.String u_FIELD11,
           java.lang.String u_FIELD12,
           java.lang.String u_FIELD13,
           java.lang.String u_FIELD14,
           java.lang.String NO_NAME,
           java.math.BigDecimal RANGE_ID) {
           this.CARD = CARD;
           this.CLIENT = CLIENT;
           this.CL_ROLE = CL_ROLE;
           this.CARD_TYPE = CARD_TYPE;
           this.BASE_SUPP = BASE_SUPP;
           this.COND_SET = COND_SET;
           this.RISK_LEVEL = RISK_LEVEL;
           this.CARD_SERVICES_SET = CARD_SERVICES_SET;
           this.REC_DATE = REC_DATE;
           this.m_NAME = m_NAME;
           this.RELATION = RELATION;
           this.ID_CARD = ID_CARD;
           this.b_DATE = b_DATE;
           this.CALL_ID = CALL_ID;
           this.f_NAMES = f_NAMES;
           this.SURNAME = SURNAME;
           this.f_NAME1 = f_NAME1;
           this.MIDLE_NAME = MIDLE_NAME;
           this.SERIAL_NO = SERIAL_NO;
           this.DOC_SINCE = DOC_SINCE;
           this.CMPG_NAME = CMPG_NAME;
           this.INSURANC_TYPE = INSURANC_TYPE;
           this.INSURANC_DATE = INSURANC_DATE;
           this.CRD_HOLD_MSG = CRD_HOLD_MSG;
           this.u_COD9 = u_COD9;
           this.u_COD10 = u_COD10;
           this.u_FIELD7 = u_FIELD7;
           this.u_FIELD8 = u_FIELD8;
           this.IN_FILE_NUM = IN_FILE_NUM;
           this.OUT_FILE_NUM = OUT_FILE_NUM;
           this.USRID = USRID;
           this.CTIME = CTIME;
           this.EFFECTIVE_DATE1 = EFFECTIVE_DATE1;
           this.COND_SET_2 = COND_SET_2;
           this.COND_CHANGE_DATE = COND_CHANGE_DATE;
           this.CHANGE_BACK_DATE = CHANGE_BACK_DATE;
           this.BRANCH = BRANCH;
           this.u_FIELD11 = u_FIELD11;
           this.u_FIELD12 = u_FIELD12;
           this.u_FIELD13 = u_FIELD13;
           this.u_FIELD14 = u_FIELD14;
           this.NO_NAME = NO_NAME;
           this.RANGE_ID = RANGE_ID;
    }


    /**
     * Gets the CARD value for this RowType_CardInfo_LogicalCard.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_CardInfo_LogicalCard.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the CLIENT value for this RowType_CardInfo_LogicalCard.
     * 
     * @return CLIENT
     */
    public java.lang.String getCLIENT() {
        return CLIENT;
    }


    /**
     * Sets the CLIENT value for this RowType_CardInfo_LogicalCard.
     * 
     * @param CLIENT
     */
    public void setCLIENT(java.lang.String CLIENT) {
        this.CLIENT = CLIENT;
    }


    /**
     * Gets the CL_ROLE value for this RowType_CardInfo_LogicalCard.
     * 
     * @return CL_ROLE
     */
    public java.lang.String getCL_ROLE() {
        return CL_ROLE;
    }


    /**
     * Sets the CL_ROLE value for this RowType_CardInfo_LogicalCard.
     * 
     * @param CL_ROLE
     */
    public void setCL_ROLE(java.lang.String CL_ROLE) {
        this.CL_ROLE = CL_ROLE;
    }


    /**
     * Gets the CARD_TYPE value for this RowType_CardInfo_LogicalCard.
     * 
     * @return CARD_TYPE
     */
    public java.lang.String getCARD_TYPE() {
        return CARD_TYPE;
    }


    /**
     * Sets the CARD_TYPE value for this RowType_CardInfo_LogicalCard.
     * 
     * @param CARD_TYPE
     */
    public void setCARD_TYPE(java.lang.String CARD_TYPE) {
        this.CARD_TYPE = CARD_TYPE;
    }


    /**
     * Gets the BASE_SUPP value for this RowType_CardInfo_LogicalCard.
     * 
     * @return BASE_SUPP
     */
    public java.lang.String getBASE_SUPP() {
        return BASE_SUPP;
    }


    /**
     * Sets the BASE_SUPP value for this RowType_CardInfo_LogicalCard.
     * 
     * @param BASE_SUPP
     */
    public void setBASE_SUPP(java.lang.String BASE_SUPP) {
        this.BASE_SUPP = BASE_SUPP;
    }


    /**
     * Gets the COND_SET value for this RowType_CardInfo_LogicalCard.
     * 
     * @return COND_SET
     */
    public java.lang.String getCOND_SET() {
        return COND_SET;
    }


    /**
     * Sets the COND_SET value for this RowType_CardInfo_LogicalCard.
     * 
     * @param COND_SET
     */
    public void setCOND_SET(java.lang.String COND_SET) {
        this.COND_SET = COND_SET;
    }


    /**
     * Gets the RISK_LEVEL value for this RowType_CardInfo_LogicalCard.
     * 
     * @return RISK_LEVEL
     */
    public java.lang.String getRISK_LEVEL() {
        return RISK_LEVEL;
    }


    /**
     * Sets the RISK_LEVEL value for this RowType_CardInfo_LogicalCard.
     * 
     * @param RISK_LEVEL
     */
    public void setRISK_LEVEL(java.lang.String RISK_LEVEL) {
        this.RISK_LEVEL = RISK_LEVEL;
    }


    /**
     * Gets the CARD_SERVICES_SET value for this RowType_CardInfo_LogicalCard.
     * 
     * @return CARD_SERVICES_SET
     */
    public java.lang.String getCARD_SERVICES_SET() {
        return CARD_SERVICES_SET;
    }


    /**
     * Sets the CARD_SERVICES_SET value for this RowType_CardInfo_LogicalCard.
     * 
     * @param CARD_SERVICES_SET
     */
    public void setCARD_SERVICES_SET(java.lang.String CARD_SERVICES_SET) {
        this.CARD_SERVICES_SET = CARD_SERVICES_SET;
    }


    /**
     * Gets the REC_DATE value for this RowType_CardInfo_LogicalCard.
     * 
     * @return REC_DATE
     */
    public java.util.Calendar getREC_DATE() {
        return REC_DATE;
    }


    /**
     * Sets the REC_DATE value for this RowType_CardInfo_LogicalCard.
     * 
     * @param REC_DATE
     */
    public void setREC_DATE(java.util.Calendar REC_DATE) {
        this.REC_DATE = REC_DATE;
    }


    /**
     * Gets the m_NAME value for this RowType_CardInfo_LogicalCard.
     * 
     * @return m_NAME
     */
    public java.lang.String getM_NAME() {
        return m_NAME;
    }


    /**
     * Sets the m_NAME value for this RowType_CardInfo_LogicalCard.
     * 
     * @param m_NAME
     */
    public void setM_NAME(java.lang.String m_NAME) {
        this.m_NAME = m_NAME;
    }


    /**
     * Gets the RELATION value for this RowType_CardInfo_LogicalCard.
     * 
     * @return RELATION
     */
    public java.lang.String getRELATION() {
        return RELATION;
    }


    /**
     * Sets the RELATION value for this RowType_CardInfo_LogicalCard.
     * 
     * @param RELATION
     */
    public void setRELATION(java.lang.String RELATION) {
        this.RELATION = RELATION;
    }


    /**
     * Gets the ID_CARD value for this RowType_CardInfo_LogicalCard.
     * 
     * @return ID_CARD
     */
    public java.lang.String getID_CARD() {
        return ID_CARD;
    }


    /**
     * Sets the ID_CARD value for this RowType_CardInfo_LogicalCard.
     * 
     * @param ID_CARD
     */
    public void setID_CARD(java.lang.String ID_CARD) {
        this.ID_CARD = ID_CARD;
    }


    /**
     * Gets the b_DATE value for this RowType_CardInfo_LogicalCard.
     * 
     * @return b_DATE
     */
    public java.util.Calendar getB_DATE() {
        return b_DATE;
    }


    /**
     * Sets the b_DATE value for this RowType_CardInfo_LogicalCard.
     * 
     * @param b_DATE
     */
    public void setB_DATE(java.util.Calendar b_DATE) {
        this.b_DATE = b_DATE;
    }


    /**
     * Gets the CALL_ID value for this RowType_CardInfo_LogicalCard.
     * 
     * @return CALL_ID
     */
    public java.lang.String getCALL_ID() {
        return CALL_ID;
    }


    /**
     * Sets the CALL_ID value for this RowType_CardInfo_LogicalCard.
     * 
     * @param CALL_ID
     */
    public void setCALL_ID(java.lang.String CALL_ID) {
        this.CALL_ID = CALL_ID;
    }


    /**
     * Gets the f_NAMES value for this RowType_CardInfo_LogicalCard.
     * 
     * @return f_NAMES
     */
    public java.lang.String getF_NAMES() {
        return f_NAMES;
    }


    /**
     * Sets the f_NAMES value for this RowType_CardInfo_LogicalCard.
     * 
     * @param f_NAMES
     */
    public void setF_NAMES(java.lang.String f_NAMES) {
        this.f_NAMES = f_NAMES;
    }


    /**
     * Gets the SURNAME value for this RowType_CardInfo_LogicalCard.
     * 
     * @return SURNAME
     */
    public java.lang.String getSURNAME() {
        return SURNAME;
    }


    /**
     * Sets the SURNAME value for this RowType_CardInfo_LogicalCard.
     * 
     * @param SURNAME
     */
    public void setSURNAME(java.lang.String SURNAME) {
        this.SURNAME = SURNAME;
    }


    /**
     * Gets the f_NAME1 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return f_NAME1
     */
    public java.lang.String getF_NAME1() {
        return f_NAME1;
    }


    /**
     * Sets the f_NAME1 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param f_NAME1
     */
    public void setF_NAME1(java.lang.String f_NAME1) {
        this.f_NAME1 = f_NAME1;
    }


    /**
     * Gets the MIDLE_NAME value for this RowType_CardInfo_LogicalCard.
     * 
     * @return MIDLE_NAME
     */
    public java.lang.String getMIDLE_NAME() {
        return MIDLE_NAME;
    }


    /**
     * Sets the MIDLE_NAME value for this RowType_CardInfo_LogicalCard.
     * 
     * @param MIDLE_NAME
     */
    public void setMIDLE_NAME(java.lang.String MIDLE_NAME) {
        this.MIDLE_NAME = MIDLE_NAME;
    }


    /**
     * Gets the SERIAL_NO value for this RowType_CardInfo_LogicalCard.
     * 
     * @return SERIAL_NO
     */
    public java.lang.String getSERIAL_NO() {
        return SERIAL_NO;
    }


    /**
     * Sets the SERIAL_NO value for this RowType_CardInfo_LogicalCard.
     * 
     * @param SERIAL_NO
     */
    public void setSERIAL_NO(java.lang.String SERIAL_NO) {
        this.SERIAL_NO = SERIAL_NO;
    }


    /**
     * Gets the DOC_SINCE value for this RowType_CardInfo_LogicalCard.
     * 
     * @return DOC_SINCE
     */
    public java.util.Calendar getDOC_SINCE() {
        return DOC_SINCE;
    }


    /**
     * Sets the DOC_SINCE value for this RowType_CardInfo_LogicalCard.
     * 
     * @param DOC_SINCE
     */
    public void setDOC_SINCE(java.util.Calendar DOC_SINCE) {
        this.DOC_SINCE = DOC_SINCE;
    }


    /**
     * Gets the CMPG_NAME value for this RowType_CardInfo_LogicalCard.
     * 
     * @return CMPG_NAME
     */
    public java.lang.String getCMPG_NAME() {
        return CMPG_NAME;
    }


    /**
     * Sets the CMPG_NAME value for this RowType_CardInfo_LogicalCard.
     * 
     * @param CMPG_NAME
     */
    public void setCMPG_NAME(java.lang.String CMPG_NAME) {
        this.CMPG_NAME = CMPG_NAME;
    }


    /**
     * Gets the INSURANC_TYPE value for this RowType_CardInfo_LogicalCard.
     * 
     * @return INSURANC_TYPE
     */
    public java.lang.String getINSURANC_TYPE() {
        return INSURANC_TYPE;
    }


    /**
     * Sets the INSURANC_TYPE value for this RowType_CardInfo_LogicalCard.
     * 
     * @param INSURANC_TYPE
     */
    public void setINSURANC_TYPE(java.lang.String INSURANC_TYPE) {
        this.INSURANC_TYPE = INSURANC_TYPE;
    }


    /**
     * Gets the INSURANC_DATE value for this RowType_CardInfo_LogicalCard.
     * 
     * @return INSURANC_DATE
     */
    public java.util.Calendar getINSURANC_DATE() {
        return INSURANC_DATE;
    }


    /**
     * Sets the INSURANC_DATE value for this RowType_CardInfo_LogicalCard.
     * 
     * @param INSURANC_DATE
     */
    public void setINSURANC_DATE(java.util.Calendar INSURANC_DATE) {
        this.INSURANC_DATE = INSURANC_DATE;
    }


    /**
     * Gets the CRD_HOLD_MSG value for this RowType_CardInfo_LogicalCard.
     * 
     * @return CRD_HOLD_MSG
     */
    public java.lang.String getCRD_HOLD_MSG() {
        return CRD_HOLD_MSG;
    }


    /**
     * Sets the CRD_HOLD_MSG value for this RowType_CardInfo_LogicalCard.
     * 
     * @param CRD_HOLD_MSG
     */
    public void setCRD_HOLD_MSG(java.lang.String CRD_HOLD_MSG) {
        this.CRD_HOLD_MSG = CRD_HOLD_MSG;
    }


    /**
     * Gets the u_COD9 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return u_COD9
     */
    public java.lang.String getU_COD9() {
        return u_COD9;
    }


    /**
     * Sets the u_COD9 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param u_COD9
     */
    public void setU_COD9(java.lang.String u_COD9) {
        this.u_COD9 = u_COD9;
    }


    /**
     * Gets the u_COD10 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return u_COD10
     */
    public java.lang.String getU_COD10() {
        return u_COD10;
    }


    /**
     * Sets the u_COD10 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param u_COD10
     */
    public void setU_COD10(java.lang.String u_COD10) {
        this.u_COD10 = u_COD10;
    }


    /**
     * Gets the u_FIELD7 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return u_FIELD7
     */
    public java.lang.String getU_FIELD7() {
        return u_FIELD7;
    }


    /**
     * Sets the u_FIELD7 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param u_FIELD7
     */
    public void setU_FIELD7(java.lang.String u_FIELD7) {
        this.u_FIELD7 = u_FIELD7;
    }


    /**
     * Gets the u_FIELD8 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return u_FIELD8
     */
    public java.lang.String getU_FIELD8() {
        return u_FIELD8;
    }


    /**
     * Sets the u_FIELD8 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param u_FIELD8
     */
    public void setU_FIELD8(java.lang.String u_FIELD8) {
        this.u_FIELD8 = u_FIELD8;
    }


    /**
     * Gets the IN_FILE_NUM value for this RowType_CardInfo_LogicalCard.
     * 
     * @return IN_FILE_NUM
     */
    public java.math.BigDecimal getIN_FILE_NUM() {
        return IN_FILE_NUM;
    }


    /**
     * Sets the IN_FILE_NUM value for this RowType_CardInfo_LogicalCard.
     * 
     * @param IN_FILE_NUM
     */
    public void setIN_FILE_NUM(java.math.BigDecimal IN_FILE_NUM) {
        this.IN_FILE_NUM = IN_FILE_NUM;
    }


    /**
     * Gets the OUT_FILE_NUM value for this RowType_CardInfo_LogicalCard.
     * 
     * @return OUT_FILE_NUM
     */
    public java.math.BigDecimal getOUT_FILE_NUM() {
        return OUT_FILE_NUM;
    }


    /**
     * Sets the OUT_FILE_NUM value for this RowType_CardInfo_LogicalCard.
     * 
     * @param OUT_FILE_NUM
     */
    public void setOUT_FILE_NUM(java.math.BigDecimal OUT_FILE_NUM) {
        this.OUT_FILE_NUM = OUT_FILE_NUM;
    }


    /**
     * Gets the USRID value for this RowType_CardInfo_LogicalCard.
     * 
     * @return USRID
     */
    public java.lang.String getUSRID() {
        return USRID;
    }


    /**
     * Sets the USRID value for this RowType_CardInfo_LogicalCard.
     * 
     * @param USRID
     */
    public void setUSRID(java.lang.String USRID) {
        this.USRID = USRID;
    }


    /**
     * Gets the CTIME value for this RowType_CardInfo_LogicalCard.
     * 
     * @return CTIME
     */
    public java.util.Calendar getCTIME() {
        return CTIME;
    }


    /**
     * Sets the CTIME value for this RowType_CardInfo_LogicalCard.
     * 
     * @param CTIME
     */
    public void setCTIME(java.util.Calendar CTIME) {
        this.CTIME = CTIME;
    }


    /**
     * Gets the EFFECTIVE_DATE1 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return EFFECTIVE_DATE1
     */
    public java.util.Calendar getEFFECTIVE_DATE1() {
        return EFFECTIVE_DATE1;
    }


    /**
     * Sets the EFFECTIVE_DATE1 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param EFFECTIVE_DATE1
     */
    public void setEFFECTIVE_DATE1(java.util.Calendar EFFECTIVE_DATE1) {
        this.EFFECTIVE_DATE1 = EFFECTIVE_DATE1;
    }


    /**
     * Gets the COND_SET_2 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return COND_SET_2
     */
    public java.lang.String getCOND_SET_2() {
        return COND_SET_2;
    }


    /**
     * Sets the COND_SET_2 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param COND_SET_2
     */
    public void setCOND_SET_2(java.lang.String COND_SET_2) {
        this.COND_SET_2 = COND_SET_2;
    }


    /**
     * Gets the COND_CHANGE_DATE value for this RowType_CardInfo_LogicalCard.
     * 
     * @return COND_CHANGE_DATE
     */
    public java.util.Calendar getCOND_CHANGE_DATE() {
        return COND_CHANGE_DATE;
    }


    /**
     * Sets the COND_CHANGE_DATE value for this RowType_CardInfo_LogicalCard.
     * 
     * @param COND_CHANGE_DATE
     */
    public void setCOND_CHANGE_DATE(java.util.Calendar COND_CHANGE_DATE) {
        this.COND_CHANGE_DATE = COND_CHANGE_DATE;
    }


    /**
     * Gets the CHANGE_BACK_DATE value for this RowType_CardInfo_LogicalCard.
     * 
     * @return CHANGE_BACK_DATE
     */
    public java.util.Calendar getCHANGE_BACK_DATE() {
        return CHANGE_BACK_DATE;
    }


    /**
     * Sets the CHANGE_BACK_DATE value for this RowType_CardInfo_LogicalCard.
     * 
     * @param CHANGE_BACK_DATE
     */
    public void setCHANGE_BACK_DATE(java.util.Calendar CHANGE_BACK_DATE) {
        this.CHANGE_BACK_DATE = CHANGE_BACK_DATE;
    }


    /**
     * Gets the BRANCH value for this RowType_CardInfo_LogicalCard.
     * 
     * @return BRANCH
     */
    public java.lang.String getBRANCH() {
        return BRANCH;
    }


    /**
     * Sets the BRANCH value for this RowType_CardInfo_LogicalCard.
     * 
     * @param BRANCH
     */
    public void setBRANCH(java.lang.String BRANCH) {
        this.BRANCH = BRANCH;
    }


    /**
     * Gets the u_FIELD11 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return u_FIELD11
     */
    public java.lang.String getU_FIELD11() {
        return u_FIELD11;
    }


    /**
     * Sets the u_FIELD11 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param u_FIELD11
     */
    public void setU_FIELD11(java.lang.String u_FIELD11) {
        this.u_FIELD11 = u_FIELD11;
    }


    /**
     * Gets the u_FIELD12 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return u_FIELD12
     */
    public java.lang.String getU_FIELD12() {
        return u_FIELD12;
    }


    /**
     * Sets the u_FIELD12 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param u_FIELD12
     */
    public void setU_FIELD12(java.lang.String u_FIELD12) {
        this.u_FIELD12 = u_FIELD12;
    }


    /**
     * Gets the u_FIELD13 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return u_FIELD13
     */
    public java.lang.String getU_FIELD13() {
        return u_FIELD13;
    }


    /**
     * Sets the u_FIELD13 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param u_FIELD13
     */
    public void setU_FIELD13(java.lang.String u_FIELD13) {
        this.u_FIELD13 = u_FIELD13;
    }


    /**
     * Gets the u_FIELD14 value for this RowType_CardInfo_LogicalCard.
     * 
     * @return u_FIELD14
     */
    public java.lang.String getU_FIELD14() {
        return u_FIELD14;
    }


    /**
     * Sets the u_FIELD14 value for this RowType_CardInfo_LogicalCard.
     * 
     * @param u_FIELD14
     */
    public void setU_FIELD14(java.lang.String u_FIELD14) {
        this.u_FIELD14 = u_FIELD14;
    }


    /**
     * Gets the NO_NAME value for this RowType_CardInfo_LogicalCard.
     * 
     * @return NO_NAME
     */
    public java.lang.String getNO_NAME() {
        return NO_NAME;
    }


    /**
     * Sets the NO_NAME value for this RowType_CardInfo_LogicalCard.
     * 
     * @param NO_NAME
     */
    public void setNO_NAME(java.lang.String NO_NAME) {
        this.NO_NAME = NO_NAME;
    }


    /**
     * Gets the RANGE_ID value for this RowType_CardInfo_LogicalCard.
     * 
     * @return RANGE_ID
     */
    public java.math.BigDecimal getRANGE_ID() {
        return RANGE_ID;
    }


    /**
     * Sets the RANGE_ID value for this RowType_CardInfo_LogicalCard.
     * 
     * @param RANGE_ID
     */
    public void setRANGE_ID(java.math.BigDecimal RANGE_ID) {
        this.RANGE_ID = RANGE_ID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CardInfo_LogicalCard)) return false;
        RowType_CardInfo_LogicalCard other = (RowType_CardInfo_LogicalCard) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CARD==null && other.getCARD()==null) || 
             (this.CARD!=null &&
              this.CARD.equals(other.getCARD()))) &&
            ((this.CLIENT==null && other.getCLIENT()==null) || 
             (this.CLIENT!=null &&
              this.CLIENT.equals(other.getCLIENT()))) &&
            ((this.CL_ROLE==null && other.getCL_ROLE()==null) || 
             (this.CL_ROLE!=null &&
              this.CL_ROLE.equals(other.getCL_ROLE()))) &&
            ((this.CARD_TYPE==null && other.getCARD_TYPE()==null) || 
             (this.CARD_TYPE!=null &&
              this.CARD_TYPE.equals(other.getCARD_TYPE()))) &&
            ((this.BASE_SUPP==null && other.getBASE_SUPP()==null) || 
             (this.BASE_SUPP!=null &&
              this.BASE_SUPP.equals(other.getBASE_SUPP()))) &&
            ((this.COND_SET==null && other.getCOND_SET()==null) || 
             (this.COND_SET!=null &&
              this.COND_SET.equals(other.getCOND_SET()))) &&
            ((this.RISK_LEVEL==null && other.getRISK_LEVEL()==null) || 
             (this.RISK_LEVEL!=null &&
              this.RISK_LEVEL.equals(other.getRISK_LEVEL()))) &&
            ((this.CARD_SERVICES_SET==null && other.getCARD_SERVICES_SET()==null) || 
             (this.CARD_SERVICES_SET!=null &&
              this.CARD_SERVICES_SET.equals(other.getCARD_SERVICES_SET()))) &&
            ((this.REC_DATE==null && other.getREC_DATE()==null) || 
             (this.REC_DATE!=null &&
              this.REC_DATE.equals(other.getREC_DATE()))) &&
            ((this.m_NAME==null && other.getM_NAME()==null) || 
             (this.m_NAME!=null &&
              this.m_NAME.equals(other.getM_NAME()))) &&
            ((this.RELATION==null && other.getRELATION()==null) || 
             (this.RELATION!=null &&
              this.RELATION.equals(other.getRELATION()))) &&
            ((this.ID_CARD==null && other.getID_CARD()==null) || 
             (this.ID_CARD!=null &&
              this.ID_CARD.equals(other.getID_CARD()))) &&
            ((this.b_DATE==null && other.getB_DATE()==null) || 
             (this.b_DATE!=null &&
              this.b_DATE.equals(other.getB_DATE()))) &&
            ((this.CALL_ID==null && other.getCALL_ID()==null) || 
             (this.CALL_ID!=null &&
              this.CALL_ID.equals(other.getCALL_ID()))) &&
            ((this.f_NAMES==null && other.getF_NAMES()==null) || 
             (this.f_NAMES!=null &&
              this.f_NAMES.equals(other.getF_NAMES()))) &&
            ((this.SURNAME==null && other.getSURNAME()==null) || 
             (this.SURNAME!=null &&
              this.SURNAME.equals(other.getSURNAME()))) &&
            ((this.f_NAME1==null && other.getF_NAME1()==null) || 
             (this.f_NAME1!=null &&
              this.f_NAME1.equals(other.getF_NAME1()))) &&
            ((this.MIDLE_NAME==null && other.getMIDLE_NAME()==null) || 
             (this.MIDLE_NAME!=null &&
              this.MIDLE_NAME.equals(other.getMIDLE_NAME()))) &&
            ((this.SERIAL_NO==null && other.getSERIAL_NO()==null) || 
             (this.SERIAL_NO!=null &&
              this.SERIAL_NO.equals(other.getSERIAL_NO()))) &&
            ((this.DOC_SINCE==null && other.getDOC_SINCE()==null) || 
             (this.DOC_SINCE!=null &&
              this.DOC_SINCE.equals(other.getDOC_SINCE()))) &&
            ((this.CMPG_NAME==null && other.getCMPG_NAME()==null) || 
             (this.CMPG_NAME!=null &&
              this.CMPG_NAME.equals(other.getCMPG_NAME()))) &&
            ((this.INSURANC_TYPE==null && other.getINSURANC_TYPE()==null) || 
             (this.INSURANC_TYPE!=null &&
              this.INSURANC_TYPE.equals(other.getINSURANC_TYPE()))) &&
            ((this.INSURANC_DATE==null && other.getINSURANC_DATE()==null) || 
             (this.INSURANC_DATE!=null &&
              this.INSURANC_DATE.equals(other.getINSURANC_DATE()))) &&
            ((this.CRD_HOLD_MSG==null && other.getCRD_HOLD_MSG()==null) || 
             (this.CRD_HOLD_MSG!=null &&
              this.CRD_HOLD_MSG.equals(other.getCRD_HOLD_MSG()))) &&
            ((this.u_COD9==null && other.getU_COD9()==null) || 
             (this.u_COD9!=null &&
              this.u_COD9.equals(other.getU_COD9()))) &&
            ((this.u_COD10==null && other.getU_COD10()==null) || 
             (this.u_COD10!=null &&
              this.u_COD10.equals(other.getU_COD10()))) &&
            ((this.u_FIELD7==null && other.getU_FIELD7()==null) || 
             (this.u_FIELD7!=null &&
              this.u_FIELD7.equals(other.getU_FIELD7()))) &&
            ((this.u_FIELD8==null && other.getU_FIELD8()==null) || 
             (this.u_FIELD8!=null &&
              this.u_FIELD8.equals(other.getU_FIELD8()))) &&
            ((this.IN_FILE_NUM==null && other.getIN_FILE_NUM()==null) || 
             (this.IN_FILE_NUM!=null &&
              this.IN_FILE_NUM.equals(other.getIN_FILE_NUM()))) &&
            ((this.OUT_FILE_NUM==null && other.getOUT_FILE_NUM()==null) || 
             (this.OUT_FILE_NUM!=null &&
              this.OUT_FILE_NUM.equals(other.getOUT_FILE_NUM()))) &&
            ((this.USRID==null && other.getUSRID()==null) || 
             (this.USRID!=null &&
              this.USRID.equals(other.getUSRID()))) &&
            ((this.CTIME==null && other.getCTIME()==null) || 
             (this.CTIME!=null &&
              this.CTIME.equals(other.getCTIME()))) &&
            ((this.EFFECTIVE_DATE1==null && other.getEFFECTIVE_DATE1()==null) || 
             (this.EFFECTIVE_DATE1!=null &&
              this.EFFECTIVE_DATE1.equals(other.getEFFECTIVE_DATE1()))) &&
            ((this.COND_SET_2==null && other.getCOND_SET_2()==null) || 
             (this.COND_SET_2!=null &&
              this.COND_SET_2.equals(other.getCOND_SET_2()))) &&
            ((this.COND_CHANGE_DATE==null && other.getCOND_CHANGE_DATE()==null) || 
             (this.COND_CHANGE_DATE!=null &&
              this.COND_CHANGE_DATE.equals(other.getCOND_CHANGE_DATE()))) &&
            ((this.CHANGE_BACK_DATE==null && other.getCHANGE_BACK_DATE()==null) || 
             (this.CHANGE_BACK_DATE!=null &&
              this.CHANGE_BACK_DATE.equals(other.getCHANGE_BACK_DATE()))) &&
            ((this.BRANCH==null && other.getBRANCH()==null) || 
             (this.BRANCH!=null &&
              this.BRANCH.equals(other.getBRANCH()))) &&
            ((this.u_FIELD11==null && other.getU_FIELD11()==null) || 
             (this.u_FIELD11!=null &&
              this.u_FIELD11.equals(other.getU_FIELD11()))) &&
            ((this.u_FIELD12==null && other.getU_FIELD12()==null) || 
             (this.u_FIELD12!=null &&
              this.u_FIELD12.equals(other.getU_FIELD12()))) &&
            ((this.u_FIELD13==null && other.getU_FIELD13()==null) || 
             (this.u_FIELD13!=null &&
              this.u_FIELD13.equals(other.getU_FIELD13()))) &&
            ((this.u_FIELD14==null && other.getU_FIELD14()==null) || 
             (this.u_FIELD14!=null &&
              this.u_FIELD14.equals(other.getU_FIELD14()))) &&
            ((this.NO_NAME==null && other.getNO_NAME()==null) || 
             (this.NO_NAME!=null &&
              this.NO_NAME.equals(other.getNO_NAME()))) &&
            ((this.RANGE_ID==null && other.getRANGE_ID()==null) || 
             (this.RANGE_ID!=null &&
              this.RANGE_ID.equals(other.getRANGE_ID())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCARD() != null) {
            _hashCode += getCARD().hashCode();
        }
        if (getCLIENT() != null) {
            _hashCode += getCLIENT().hashCode();
        }
        if (getCL_ROLE() != null) {
            _hashCode += getCL_ROLE().hashCode();
        }
        if (getCARD_TYPE() != null) {
            _hashCode += getCARD_TYPE().hashCode();
        }
        if (getBASE_SUPP() != null) {
            _hashCode += getBASE_SUPP().hashCode();
        }
        if (getCOND_SET() != null) {
            _hashCode += getCOND_SET().hashCode();
        }
        if (getRISK_LEVEL() != null) {
            _hashCode += getRISK_LEVEL().hashCode();
        }
        if (getCARD_SERVICES_SET() != null) {
            _hashCode += getCARD_SERVICES_SET().hashCode();
        }
        if (getREC_DATE() != null) {
            _hashCode += getREC_DATE().hashCode();
        }
        if (getM_NAME() != null) {
            _hashCode += getM_NAME().hashCode();
        }
        if (getRELATION() != null) {
            _hashCode += getRELATION().hashCode();
        }
        if (getID_CARD() != null) {
            _hashCode += getID_CARD().hashCode();
        }
        if (getB_DATE() != null) {
            _hashCode += getB_DATE().hashCode();
        }
        if (getCALL_ID() != null) {
            _hashCode += getCALL_ID().hashCode();
        }
        if (getF_NAMES() != null) {
            _hashCode += getF_NAMES().hashCode();
        }
        if (getSURNAME() != null) {
            _hashCode += getSURNAME().hashCode();
        }
        if (getF_NAME1() != null) {
            _hashCode += getF_NAME1().hashCode();
        }
        if (getMIDLE_NAME() != null) {
            _hashCode += getMIDLE_NAME().hashCode();
        }
        if (getSERIAL_NO() != null) {
            _hashCode += getSERIAL_NO().hashCode();
        }
        if (getDOC_SINCE() != null) {
            _hashCode += getDOC_SINCE().hashCode();
        }
        if (getCMPG_NAME() != null) {
            _hashCode += getCMPG_NAME().hashCode();
        }
        if (getINSURANC_TYPE() != null) {
            _hashCode += getINSURANC_TYPE().hashCode();
        }
        if (getINSURANC_DATE() != null) {
            _hashCode += getINSURANC_DATE().hashCode();
        }
        if (getCRD_HOLD_MSG() != null) {
            _hashCode += getCRD_HOLD_MSG().hashCode();
        }
        if (getU_COD9() != null) {
            _hashCode += getU_COD9().hashCode();
        }
        if (getU_COD10() != null) {
            _hashCode += getU_COD10().hashCode();
        }
        if (getU_FIELD7() != null) {
            _hashCode += getU_FIELD7().hashCode();
        }
        if (getU_FIELD8() != null) {
            _hashCode += getU_FIELD8().hashCode();
        }
        if (getIN_FILE_NUM() != null) {
            _hashCode += getIN_FILE_NUM().hashCode();
        }
        if (getOUT_FILE_NUM() != null) {
            _hashCode += getOUT_FILE_NUM().hashCode();
        }
        if (getUSRID() != null) {
            _hashCode += getUSRID().hashCode();
        }
        if (getCTIME() != null) {
            _hashCode += getCTIME().hashCode();
        }
        if (getEFFECTIVE_DATE1() != null) {
            _hashCode += getEFFECTIVE_DATE1().hashCode();
        }
        if (getCOND_SET_2() != null) {
            _hashCode += getCOND_SET_2().hashCode();
        }
        if (getCOND_CHANGE_DATE() != null) {
            _hashCode += getCOND_CHANGE_DATE().hashCode();
        }
        if (getCHANGE_BACK_DATE() != null) {
            _hashCode += getCHANGE_BACK_DATE().hashCode();
        }
        if (getBRANCH() != null) {
            _hashCode += getBRANCH().hashCode();
        }
        if (getU_FIELD11() != null) {
            _hashCode += getU_FIELD11().hashCode();
        }
        if (getU_FIELD12() != null) {
            _hashCode += getU_FIELD12().hashCode();
        }
        if (getU_FIELD13() != null) {
            _hashCode += getU_FIELD13().hashCode();
        }
        if (getU_FIELD14() != null) {
            _hashCode += getU_FIELD14().hashCode();
        }
        if (getNO_NAME() != null) {
            _hashCode += getNO_NAME().hashCode();
        }
        if (getRANGE_ID() != null) {
            _hashCode += getRANGE_ID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_CardInfo_LogicalCard.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo_LogicalCard"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLIENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLIENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CL_ROLE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CL_ROLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BASE_SUPP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BASE_SUPP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COND_SET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COND_SET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RISK_LEVEL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RISK_LEVEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_SERVICES_SET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_SERVICES_SET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REC_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REC_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("m_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "M_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RELATION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RELATION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID_CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ID_CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "B_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CALL_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CALL_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("f_NAMES");
        elemField.setXmlName(new javax.xml.namespace.QName("", "F_NAMES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SURNAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SURNAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("f_NAME1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "F_NAME1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MIDLE_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MIDLE_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERIAL_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SERIAL_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOC_SINCE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOC_SINCE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CMPG_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CMPG_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSURANC_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSURANC_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSURANC_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSURANC_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CRD_HOLD_MSG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CRD_HOLD_MSG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_COD9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_COD9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_COD10");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_COD10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_FIELD7");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_FIELD7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_FIELD8");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_FIELD8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IN_FILE_NUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IN_FILE_NUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OUT_FILE_NUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OUT_FILE_NUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USRID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "USRID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CTIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CTIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EFFECTIVE_DATE1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EFFECTIVE_DATE1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COND_SET_2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COND_SET_2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COND_CHANGE_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COND_CHANGE_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CHANGE_BACK_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CHANGE_BACK_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BRANCH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BRANCH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_FIELD11");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_FIELD11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_FIELD12");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_FIELD12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_FIELD13");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_FIELD13"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_FIELD14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_FIELD14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NO_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NO_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RANGE_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RANGE_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
