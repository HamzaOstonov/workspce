/**
 * RowType_EditAccount_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agroBank.issuing_v_01_02_xsd;

public class RowType_EditAccount_Request  implements java.io.Serializable {
    private java.math.BigDecimal ACCOUNT_NO;

    private java.lang.String ACC_PRTY;

    private java.lang.String c_ACCNT_TYPE;

    private java.lang.String CARD_ACCT;

    private java.lang.String CCY;

    private java.lang.String TRANZ_ACCT;

    private java.lang.String CYCLE;

    private java.math.BigDecimal MIN_BAL;

    private java.lang.String COND_SET;

    private java.lang.String STATUS;

    private java.lang.String STAT_CHANGE;

    private java.lang.String STA_COMENT;

    private java.math.BigDecimal AUTH_BONUS;

    private java.util.Calendar AB_EXPIRITY;

    private java.math.BigDecimal CRD;

    private java.util.Calendar CRD_EXPIRY;

    private java.math.BigDecimal ATM_LIMIT;

    private java.math.BigDecimal NON_REDUCE_BAL;

    private java.lang.String ADJUST_FLAG;

    private java.lang.String PAY_CODE;

    private java.lang.String PAY_FREQ;

    private java.lang.String CALCUL_MODE;

    private java.math.BigDecimal PAY_AMNT;

    private java.math.BigDecimal PAY_INTR;

    private java.math.BigDecimal LIM_AMNT;

    private java.math.BigDecimal LIM_INTR;

    private java.util.Calendar CREATED_DATE;

    private java.util.Calendar STOP_DATE;

    private java.lang.String MESSAGE;

    private java.lang.String u_COD7;

    private java.lang.String u_COD8;

    private java.lang.String UFIELD_5;

    private java.lang.String u_FIELD6;

    private java.math.BigDecimal DEPOSIT;

    private java.lang.String DEPOSIT_COMENT;

    private java.lang.String DEPOSIT_ACCOUNT;

    private java.math.BigDecimal AGR_AMOUNT;

    private java.util.Calendar DEP_EXP_DATE;

    private java.lang.String DEP_OPEN_F;

    private java.lang.String DEP_FRONT_F;

    private java.math.BigDecimal DEP_OPER_ACCT;

    private java.math.BigDecimal DEP_OPER_ACCTB;

    private java.lang.String DEP_OPER_BACCT;

    private java.math.BigDecimal IN_FILE_NUM;

    private java.math.BigDecimal OPEN_INSTL;

    private java.math.BigDecimal INSTL_LINE;

    private java.lang.String INSTL_CONDSET;

    public RowType_EditAccount_Request() {
    }

    public RowType_EditAccount_Request(
           java.math.BigDecimal ACCOUNT_NO,
           java.lang.String ACC_PRTY,
           java.lang.String c_ACCNT_TYPE,
           java.lang.String CARD_ACCT,
           java.lang.String CCY,
           java.lang.String TRANZ_ACCT,
           java.lang.String CYCLE,
           java.math.BigDecimal MIN_BAL,
           java.lang.String COND_SET,
           java.lang.String STATUS,
           java.lang.String STAT_CHANGE,
           java.lang.String STA_COMENT,
           java.math.BigDecimal AUTH_BONUS,
           java.util.Calendar AB_EXPIRITY,
           java.math.BigDecimal CRD,
           java.util.Calendar CRD_EXPIRY,
           java.math.BigDecimal ATM_LIMIT,
           java.math.BigDecimal NON_REDUCE_BAL,
           java.lang.String ADJUST_FLAG,
           java.lang.String PAY_CODE,
           java.lang.String PAY_FREQ,
           java.lang.String CALCUL_MODE,
           java.math.BigDecimal PAY_AMNT,
           java.math.BigDecimal PAY_INTR,
           java.math.BigDecimal LIM_AMNT,
           java.math.BigDecimal LIM_INTR,
           java.util.Calendar CREATED_DATE,
           java.util.Calendar STOP_DATE,
           java.lang.String MESSAGE,
           java.lang.String u_COD7,
           java.lang.String u_COD8,
           java.lang.String UFIELD_5,
           java.lang.String u_FIELD6,
           java.math.BigDecimal DEPOSIT,
           java.lang.String DEPOSIT_COMENT,
           java.lang.String DEPOSIT_ACCOUNT,
           java.math.BigDecimal AGR_AMOUNT,
           java.util.Calendar DEP_EXP_DATE,
           java.lang.String DEP_OPEN_F,
           java.lang.String DEP_FRONT_F,
           java.math.BigDecimal DEP_OPER_ACCT,
           java.math.BigDecimal DEP_OPER_ACCTB,
           java.lang.String DEP_OPER_BACCT,
           java.math.BigDecimal IN_FILE_NUM,
           java.math.BigDecimal OPEN_INSTL,
           java.math.BigDecimal INSTL_LINE,
           java.lang.String INSTL_CONDSET) {
           this.ACCOUNT_NO = ACCOUNT_NO;
           this.ACC_PRTY = ACC_PRTY;
           this.c_ACCNT_TYPE = c_ACCNT_TYPE;
           this.CARD_ACCT = CARD_ACCT;
           this.CCY = CCY;
           this.TRANZ_ACCT = TRANZ_ACCT;
           this.CYCLE = CYCLE;
           this.MIN_BAL = MIN_BAL;
           this.COND_SET = COND_SET;
           this.STATUS = STATUS;
           this.STAT_CHANGE = STAT_CHANGE;
           this.STA_COMENT = STA_COMENT;
           this.AUTH_BONUS = AUTH_BONUS;
           this.AB_EXPIRITY = AB_EXPIRITY;
           this.CRD = CRD;
           this.CRD_EXPIRY = CRD_EXPIRY;
           this.ATM_LIMIT = ATM_LIMIT;
           this.NON_REDUCE_BAL = NON_REDUCE_BAL;
           this.ADJUST_FLAG = ADJUST_FLAG;
           this.PAY_CODE = PAY_CODE;
           this.PAY_FREQ = PAY_FREQ;
           this.CALCUL_MODE = CALCUL_MODE;
           this.PAY_AMNT = PAY_AMNT;
           this.PAY_INTR = PAY_INTR;
           this.LIM_AMNT = LIM_AMNT;
           this.LIM_INTR = LIM_INTR;
           this.CREATED_DATE = CREATED_DATE;
           this.STOP_DATE = STOP_DATE;
           this.MESSAGE = MESSAGE;
           this.u_COD7 = u_COD7;
           this.u_COD8 = u_COD8;
           this.UFIELD_5 = UFIELD_5;
           this.u_FIELD6 = u_FIELD6;
           this.DEPOSIT = DEPOSIT;
           this.DEPOSIT_COMENT = DEPOSIT_COMENT;
           this.DEPOSIT_ACCOUNT = DEPOSIT_ACCOUNT;
           this.AGR_AMOUNT = AGR_AMOUNT;
           this.DEP_EXP_DATE = DEP_EXP_DATE;
           this.DEP_OPEN_F = DEP_OPEN_F;
           this.DEP_FRONT_F = DEP_FRONT_F;
           this.DEP_OPER_ACCT = DEP_OPER_ACCT;
           this.DEP_OPER_ACCTB = DEP_OPER_ACCTB;
           this.DEP_OPER_BACCT = DEP_OPER_BACCT;
           this.IN_FILE_NUM = IN_FILE_NUM;
           this.OPEN_INSTL = OPEN_INSTL;
           this.INSTL_LINE = INSTL_LINE;
           this.INSTL_CONDSET = INSTL_CONDSET;
    }


    /**
     * Gets the ACCOUNT_NO value for this RowType_EditAccount_Request.
     * 
     * @return ACCOUNT_NO
     */
    public java.math.BigDecimal getACCOUNT_NO() {
        return ACCOUNT_NO;
    }


    /**
     * Sets the ACCOUNT_NO value for this RowType_EditAccount_Request.
     * 
     * @param ACCOUNT_NO
     */
    public void setACCOUNT_NO(java.math.BigDecimal ACCOUNT_NO) {
        this.ACCOUNT_NO = ACCOUNT_NO;
    }


    /**
     * Gets the ACC_PRTY value for this RowType_EditAccount_Request.
     * 
     * @return ACC_PRTY
     */
    public java.lang.String getACC_PRTY() {
        return ACC_PRTY;
    }


    /**
     * Sets the ACC_PRTY value for this RowType_EditAccount_Request.
     * 
     * @param ACC_PRTY
     */
    public void setACC_PRTY(java.lang.String ACC_PRTY) {
        this.ACC_PRTY = ACC_PRTY;
    }


    /**
     * Gets the c_ACCNT_TYPE value for this RowType_EditAccount_Request.
     * 
     * @return c_ACCNT_TYPE
     */
    public java.lang.String getC_ACCNT_TYPE() {
        return c_ACCNT_TYPE;
    }


    /**
     * Sets the c_ACCNT_TYPE value for this RowType_EditAccount_Request.
     * 
     * @param c_ACCNT_TYPE
     */
    public void setC_ACCNT_TYPE(java.lang.String c_ACCNT_TYPE) {
        this.c_ACCNT_TYPE = c_ACCNT_TYPE;
    }


    /**
     * Gets the CARD_ACCT value for this RowType_EditAccount_Request.
     * 
     * @return CARD_ACCT
     */
    public java.lang.String getCARD_ACCT() {
        return CARD_ACCT;
    }


    /**
     * Sets the CARD_ACCT value for this RowType_EditAccount_Request.
     * 
     * @param CARD_ACCT
     */
    public void setCARD_ACCT(java.lang.String CARD_ACCT) {
        this.CARD_ACCT = CARD_ACCT;
    }


    /**
     * Gets the CCY value for this RowType_EditAccount_Request.
     * 
     * @return CCY
     */
    public java.lang.String getCCY() {
        return CCY;
    }


    /**
     * Sets the CCY value for this RowType_EditAccount_Request.
     * 
     * @param CCY
     */
    public void setCCY(java.lang.String CCY) {
        this.CCY = CCY;
    }


    /**
     * Gets the TRANZ_ACCT value for this RowType_EditAccount_Request.
     * 
     * @return TRANZ_ACCT
     */
    public java.lang.String getTRANZ_ACCT() {
        return TRANZ_ACCT;
    }


    /**
     * Sets the TRANZ_ACCT value for this RowType_EditAccount_Request.
     * 
     * @param TRANZ_ACCT
     */
    public void setTRANZ_ACCT(java.lang.String TRANZ_ACCT) {
        this.TRANZ_ACCT = TRANZ_ACCT;
    }


    /**
     * Gets the CYCLE value for this RowType_EditAccount_Request.
     * 
     * @return CYCLE
     */
    public java.lang.String getCYCLE() {
        return CYCLE;
    }


    /**
     * Sets the CYCLE value for this RowType_EditAccount_Request.
     * 
     * @param CYCLE
     */
    public void setCYCLE(java.lang.String CYCLE) {
        this.CYCLE = CYCLE;
    }


    /**
     * Gets the MIN_BAL value for this RowType_EditAccount_Request.
     * 
     * @return MIN_BAL
     */
    public java.math.BigDecimal getMIN_BAL() {
        return MIN_BAL;
    }


    /**
     * Sets the MIN_BAL value for this RowType_EditAccount_Request.
     * 
     * @param MIN_BAL
     */
    public void setMIN_BAL(java.math.BigDecimal MIN_BAL) {
        this.MIN_BAL = MIN_BAL;
    }


    /**
     * Gets the COND_SET value for this RowType_EditAccount_Request.
     * 
     * @return COND_SET
     */
    public java.lang.String getCOND_SET() {
        return COND_SET;
    }


    /**
     * Sets the COND_SET value for this RowType_EditAccount_Request.
     * 
     * @param COND_SET
     */
    public void setCOND_SET(java.lang.String COND_SET) {
        this.COND_SET = COND_SET;
    }


    /**
     * Gets the STATUS value for this RowType_EditAccount_Request.
     * 
     * @return STATUS
     */
    public java.lang.String getSTATUS() {
        return STATUS;
    }


    /**
     * Sets the STATUS value for this RowType_EditAccount_Request.
     * 
     * @param STATUS
     */
    public void setSTATUS(java.lang.String STATUS) {
        this.STATUS = STATUS;
    }


    /**
     * Gets the STAT_CHANGE value for this RowType_EditAccount_Request.
     * 
     * @return STAT_CHANGE
     */
    public java.lang.String getSTAT_CHANGE() {
        return STAT_CHANGE;
    }


    /**
     * Sets the STAT_CHANGE value for this RowType_EditAccount_Request.
     * 
     * @param STAT_CHANGE
     */
    public void setSTAT_CHANGE(java.lang.String STAT_CHANGE) {
        this.STAT_CHANGE = STAT_CHANGE;
    }


    /**
     * Gets the STA_COMENT value for this RowType_EditAccount_Request.
     * 
     * @return STA_COMENT
     */
    public java.lang.String getSTA_COMENT() {
        return STA_COMENT;
    }


    /**
     * Sets the STA_COMENT value for this RowType_EditAccount_Request.
     * 
     * @param STA_COMENT
     */
    public void setSTA_COMENT(java.lang.String STA_COMENT) {
        this.STA_COMENT = STA_COMENT;
    }


    /**
     * Gets the AUTH_BONUS value for this RowType_EditAccount_Request.
     * 
     * @return AUTH_BONUS
     */
    public java.math.BigDecimal getAUTH_BONUS() {
        return AUTH_BONUS;
    }


    /**
     * Sets the AUTH_BONUS value for this RowType_EditAccount_Request.
     * 
     * @param AUTH_BONUS
     */
    public void setAUTH_BONUS(java.math.BigDecimal AUTH_BONUS) {
        this.AUTH_BONUS = AUTH_BONUS;
    }


    /**
     * Gets the AB_EXPIRITY value for this RowType_EditAccount_Request.
     * 
     * @return AB_EXPIRITY
     */
    public java.util.Calendar getAB_EXPIRITY() {
        return AB_EXPIRITY;
    }


    /**
     * Sets the AB_EXPIRITY value for this RowType_EditAccount_Request.
     * 
     * @param AB_EXPIRITY
     */
    public void setAB_EXPIRITY(java.util.Calendar AB_EXPIRITY) {
        this.AB_EXPIRITY = AB_EXPIRITY;
    }


    /**
     * Gets the CRD value for this RowType_EditAccount_Request.
     * 
     * @return CRD
     */
    public java.math.BigDecimal getCRD() {
        return CRD;
    }


    /**
     * Sets the CRD value for this RowType_EditAccount_Request.
     * 
     * @param CRD
     */
    public void setCRD(java.math.BigDecimal CRD) {
        this.CRD = CRD;
    }


    /**
     * Gets the CRD_EXPIRY value for this RowType_EditAccount_Request.
     * 
     * @return CRD_EXPIRY
     */
    public java.util.Calendar getCRD_EXPIRY() {
        return CRD_EXPIRY;
    }


    /**
     * Sets the CRD_EXPIRY value for this RowType_EditAccount_Request.
     * 
     * @param CRD_EXPIRY
     */
    public void setCRD_EXPIRY(java.util.Calendar CRD_EXPIRY) {
        this.CRD_EXPIRY = CRD_EXPIRY;
    }


    /**
     * Gets the ATM_LIMIT value for this RowType_EditAccount_Request.
     * 
     * @return ATM_LIMIT
     */
    public java.math.BigDecimal getATM_LIMIT() {
        return ATM_LIMIT;
    }


    /**
     * Sets the ATM_LIMIT value for this RowType_EditAccount_Request.
     * 
     * @param ATM_LIMIT
     */
    public void setATM_LIMIT(java.math.BigDecimal ATM_LIMIT) {
        this.ATM_LIMIT = ATM_LIMIT;
    }


    /**
     * Gets the NON_REDUCE_BAL value for this RowType_EditAccount_Request.
     * 
     * @return NON_REDUCE_BAL
     */
    public java.math.BigDecimal getNON_REDUCE_BAL() {
        return NON_REDUCE_BAL;
    }


    /**
     * Sets the NON_REDUCE_BAL value for this RowType_EditAccount_Request.
     * 
     * @param NON_REDUCE_BAL
     */
    public void setNON_REDUCE_BAL(java.math.BigDecimal NON_REDUCE_BAL) {
        this.NON_REDUCE_BAL = NON_REDUCE_BAL;
    }


    /**
     * Gets the ADJUST_FLAG value for this RowType_EditAccount_Request.
     * 
     * @return ADJUST_FLAG
     */
    public java.lang.String getADJUST_FLAG() {
        return ADJUST_FLAG;
    }


    /**
     * Sets the ADJUST_FLAG value for this RowType_EditAccount_Request.
     * 
     * @param ADJUST_FLAG
     */
    public void setADJUST_FLAG(java.lang.String ADJUST_FLAG) {
        this.ADJUST_FLAG = ADJUST_FLAG;
    }


    /**
     * Gets the PAY_CODE value for this RowType_EditAccount_Request.
     * 
     * @return PAY_CODE
     */
    public java.lang.String getPAY_CODE() {
        return PAY_CODE;
    }


    /**
     * Sets the PAY_CODE value for this RowType_EditAccount_Request.
     * 
     * @param PAY_CODE
     */
    public void setPAY_CODE(java.lang.String PAY_CODE) {
        this.PAY_CODE = PAY_CODE;
    }


    /**
     * Gets the PAY_FREQ value for this RowType_EditAccount_Request.
     * 
     * @return PAY_FREQ
     */
    public java.lang.String getPAY_FREQ() {
        return PAY_FREQ;
    }


    /**
     * Sets the PAY_FREQ value for this RowType_EditAccount_Request.
     * 
     * @param PAY_FREQ
     */
    public void setPAY_FREQ(java.lang.String PAY_FREQ) {
        this.PAY_FREQ = PAY_FREQ;
    }


    /**
     * Gets the CALCUL_MODE value for this RowType_EditAccount_Request.
     * 
     * @return CALCUL_MODE
     */
    public java.lang.String getCALCUL_MODE() {
        return CALCUL_MODE;
    }


    /**
     * Sets the CALCUL_MODE value for this RowType_EditAccount_Request.
     * 
     * @param CALCUL_MODE
     */
    public void setCALCUL_MODE(java.lang.String CALCUL_MODE) {
        this.CALCUL_MODE = CALCUL_MODE;
    }


    /**
     * Gets the PAY_AMNT value for this RowType_EditAccount_Request.
     * 
     * @return PAY_AMNT
     */
    public java.math.BigDecimal getPAY_AMNT() {
        return PAY_AMNT;
    }


    /**
     * Sets the PAY_AMNT value for this RowType_EditAccount_Request.
     * 
     * @param PAY_AMNT
     */
    public void setPAY_AMNT(java.math.BigDecimal PAY_AMNT) {
        this.PAY_AMNT = PAY_AMNT;
    }


    /**
     * Gets the PAY_INTR value for this RowType_EditAccount_Request.
     * 
     * @return PAY_INTR
     */
    public java.math.BigDecimal getPAY_INTR() {
        return PAY_INTR;
    }


    /**
     * Sets the PAY_INTR value for this RowType_EditAccount_Request.
     * 
     * @param PAY_INTR
     */
    public void setPAY_INTR(java.math.BigDecimal PAY_INTR) {
        this.PAY_INTR = PAY_INTR;
    }


    /**
     * Gets the LIM_AMNT value for this RowType_EditAccount_Request.
     * 
     * @return LIM_AMNT
     */
    public java.math.BigDecimal getLIM_AMNT() {
        return LIM_AMNT;
    }


    /**
     * Sets the LIM_AMNT value for this RowType_EditAccount_Request.
     * 
     * @param LIM_AMNT
     */
    public void setLIM_AMNT(java.math.BigDecimal LIM_AMNT) {
        this.LIM_AMNT = LIM_AMNT;
    }


    /**
     * Gets the LIM_INTR value for this RowType_EditAccount_Request.
     * 
     * @return LIM_INTR
     */
    public java.math.BigDecimal getLIM_INTR() {
        return LIM_INTR;
    }


    /**
     * Sets the LIM_INTR value for this RowType_EditAccount_Request.
     * 
     * @param LIM_INTR
     */
    public void setLIM_INTR(java.math.BigDecimal LIM_INTR) {
        this.LIM_INTR = LIM_INTR;
    }


    /**
     * Gets the CREATED_DATE value for this RowType_EditAccount_Request.
     * 
     * @return CREATED_DATE
     */
    public java.util.Calendar getCREATED_DATE() {
        return CREATED_DATE;
    }


    /**
     * Sets the CREATED_DATE value for this RowType_EditAccount_Request.
     * 
     * @param CREATED_DATE
     */
    public void setCREATED_DATE(java.util.Calendar CREATED_DATE) {
        this.CREATED_DATE = CREATED_DATE;
    }


    /**
     * Gets the STOP_DATE value for this RowType_EditAccount_Request.
     * 
     * @return STOP_DATE
     */
    public java.util.Calendar getSTOP_DATE() {
        return STOP_DATE;
    }


    /**
     * Sets the STOP_DATE value for this RowType_EditAccount_Request.
     * 
     * @param STOP_DATE
     */
    public void setSTOP_DATE(java.util.Calendar STOP_DATE) {
        this.STOP_DATE = STOP_DATE;
    }


    /**
     * Gets the MESSAGE value for this RowType_EditAccount_Request.
     * 
     * @return MESSAGE
     */
    public java.lang.String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this RowType_EditAccount_Request.
     * 
     * @param MESSAGE
     */
    public void setMESSAGE(java.lang.String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the u_COD7 value for this RowType_EditAccount_Request.
     * 
     * @return u_COD7
     */
    public java.lang.String getU_COD7() {
        return u_COD7;
    }


    /**
     * Sets the u_COD7 value for this RowType_EditAccount_Request.
     * 
     * @param u_COD7
     */
    public void setU_COD7(java.lang.String u_COD7) {
        this.u_COD7 = u_COD7;
    }


    /**
     * Gets the u_COD8 value for this RowType_EditAccount_Request.
     * 
     * @return u_COD8
     */
    public java.lang.String getU_COD8() {
        return u_COD8;
    }


    /**
     * Sets the u_COD8 value for this RowType_EditAccount_Request.
     * 
     * @param u_COD8
     */
    public void setU_COD8(java.lang.String u_COD8) {
        this.u_COD8 = u_COD8;
    }


    /**
     * Gets the UFIELD_5 value for this RowType_EditAccount_Request.
     * 
     * @return UFIELD_5
     */
    public java.lang.String getUFIELD_5() {
        return UFIELD_5;
    }


    /**
     * Sets the UFIELD_5 value for this RowType_EditAccount_Request.
     * 
     * @param UFIELD_5
     */
    public void setUFIELD_5(java.lang.String UFIELD_5) {
        this.UFIELD_5 = UFIELD_5;
    }


    /**
     * Gets the u_FIELD6 value for this RowType_EditAccount_Request.
     * 
     * @return u_FIELD6
     */
    public java.lang.String getU_FIELD6() {
        return u_FIELD6;
    }


    /**
     * Sets the u_FIELD6 value for this RowType_EditAccount_Request.
     * 
     * @param u_FIELD6
     */
    public void setU_FIELD6(java.lang.String u_FIELD6) {
        this.u_FIELD6 = u_FIELD6;
    }


    /**
     * Gets the DEPOSIT value for this RowType_EditAccount_Request.
     * 
     * @return DEPOSIT
     */
    public java.math.BigDecimal getDEPOSIT() {
        return DEPOSIT;
    }


    /**
     * Sets the DEPOSIT value for this RowType_EditAccount_Request.
     * 
     * @param DEPOSIT
     */
    public void setDEPOSIT(java.math.BigDecimal DEPOSIT) {
        this.DEPOSIT = DEPOSIT;
    }


    /**
     * Gets the DEPOSIT_COMENT value for this RowType_EditAccount_Request.
     * 
     * @return DEPOSIT_COMENT
     */
    public java.lang.String getDEPOSIT_COMENT() {
        return DEPOSIT_COMENT;
    }


    /**
     * Sets the DEPOSIT_COMENT value for this RowType_EditAccount_Request.
     * 
     * @param DEPOSIT_COMENT
     */
    public void setDEPOSIT_COMENT(java.lang.String DEPOSIT_COMENT) {
        this.DEPOSIT_COMENT = DEPOSIT_COMENT;
    }


    /**
     * Gets the DEPOSIT_ACCOUNT value for this RowType_EditAccount_Request.
     * 
     * @return DEPOSIT_ACCOUNT
     */
    public java.lang.String getDEPOSIT_ACCOUNT() {
        return DEPOSIT_ACCOUNT;
    }


    /**
     * Sets the DEPOSIT_ACCOUNT value for this RowType_EditAccount_Request.
     * 
     * @param DEPOSIT_ACCOUNT
     */
    public void setDEPOSIT_ACCOUNT(java.lang.String DEPOSIT_ACCOUNT) {
        this.DEPOSIT_ACCOUNT = DEPOSIT_ACCOUNT;
    }


    /**
     * Gets the AGR_AMOUNT value for this RowType_EditAccount_Request.
     * 
     * @return AGR_AMOUNT
     */
    public java.math.BigDecimal getAGR_AMOUNT() {
        return AGR_AMOUNT;
    }


    /**
     * Sets the AGR_AMOUNT value for this RowType_EditAccount_Request.
     * 
     * @param AGR_AMOUNT
     */
    public void setAGR_AMOUNT(java.math.BigDecimal AGR_AMOUNT) {
        this.AGR_AMOUNT = AGR_AMOUNT;
    }


    /**
     * Gets the DEP_EXP_DATE value for this RowType_EditAccount_Request.
     * 
     * @return DEP_EXP_DATE
     */
    public java.util.Calendar getDEP_EXP_DATE() {
        return DEP_EXP_DATE;
    }


    /**
     * Sets the DEP_EXP_DATE value for this RowType_EditAccount_Request.
     * 
     * @param DEP_EXP_DATE
     */
    public void setDEP_EXP_DATE(java.util.Calendar DEP_EXP_DATE) {
        this.DEP_EXP_DATE = DEP_EXP_DATE;
    }


    /**
     * Gets the DEP_OPEN_F value for this RowType_EditAccount_Request.
     * 
     * @return DEP_OPEN_F
     */
    public java.lang.String getDEP_OPEN_F() {
        return DEP_OPEN_F;
    }


    /**
     * Sets the DEP_OPEN_F value for this RowType_EditAccount_Request.
     * 
     * @param DEP_OPEN_F
     */
    public void setDEP_OPEN_F(java.lang.String DEP_OPEN_F) {
        this.DEP_OPEN_F = DEP_OPEN_F;
    }


    /**
     * Gets the DEP_FRONT_F value for this RowType_EditAccount_Request.
     * 
     * @return DEP_FRONT_F
     */
    public java.lang.String getDEP_FRONT_F() {
        return DEP_FRONT_F;
    }


    /**
     * Sets the DEP_FRONT_F value for this RowType_EditAccount_Request.
     * 
     * @param DEP_FRONT_F
     */
    public void setDEP_FRONT_F(java.lang.String DEP_FRONT_F) {
        this.DEP_FRONT_F = DEP_FRONT_F;
    }


    /**
     * Gets the DEP_OPER_ACCT value for this RowType_EditAccount_Request.
     * 
     * @return DEP_OPER_ACCT
     */
    public java.math.BigDecimal getDEP_OPER_ACCT() {
        return DEP_OPER_ACCT;
    }


    /**
     * Sets the DEP_OPER_ACCT value for this RowType_EditAccount_Request.
     * 
     * @param DEP_OPER_ACCT
     */
    public void setDEP_OPER_ACCT(java.math.BigDecimal DEP_OPER_ACCT) {
        this.DEP_OPER_ACCT = DEP_OPER_ACCT;
    }


    /**
     * Gets the DEP_OPER_ACCTB value for this RowType_EditAccount_Request.
     * 
     * @return DEP_OPER_ACCTB
     */
    public java.math.BigDecimal getDEP_OPER_ACCTB() {
        return DEP_OPER_ACCTB;
    }


    /**
     * Sets the DEP_OPER_ACCTB value for this RowType_EditAccount_Request.
     * 
     * @param DEP_OPER_ACCTB
     */
    public void setDEP_OPER_ACCTB(java.math.BigDecimal DEP_OPER_ACCTB) {
        this.DEP_OPER_ACCTB = DEP_OPER_ACCTB;
    }


    /**
     * Gets the DEP_OPER_BACCT value for this RowType_EditAccount_Request.
     * 
     * @return DEP_OPER_BACCT
     */
    public java.lang.String getDEP_OPER_BACCT() {
        return DEP_OPER_BACCT;
    }


    /**
     * Sets the DEP_OPER_BACCT value for this RowType_EditAccount_Request.
     * 
     * @param DEP_OPER_BACCT
     */
    public void setDEP_OPER_BACCT(java.lang.String DEP_OPER_BACCT) {
        this.DEP_OPER_BACCT = DEP_OPER_BACCT;
    }


    /**
     * Gets the IN_FILE_NUM value for this RowType_EditAccount_Request.
     * 
     * @return IN_FILE_NUM
     */
    public java.math.BigDecimal getIN_FILE_NUM() {
        return IN_FILE_NUM;
    }


    /**
     * Sets the IN_FILE_NUM value for this RowType_EditAccount_Request.
     * 
     * @param IN_FILE_NUM
     */
    public void setIN_FILE_NUM(java.math.BigDecimal IN_FILE_NUM) {
        this.IN_FILE_NUM = IN_FILE_NUM;
    }


    /**
     * Gets the OPEN_INSTL value for this RowType_EditAccount_Request.
     * 
     * @return OPEN_INSTL
     */
    public java.math.BigDecimal getOPEN_INSTL() {
        return OPEN_INSTL;
    }


    /**
     * Sets the OPEN_INSTL value for this RowType_EditAccount_Request.
     * 
     * @param OPEN_INSTL
     */
    public void setOPEN_INSTL(java.math.BigDecimal OPEN_INSTL) {
        this.OPEN_INSTL = OPEN_INSTL;
    }


    /**
     * Gets the INSTL_LINE value for this RowType_EditAccount_Request.
     * 
     * @return INSTL_LINE
     */
    public java.math.BigDecimal getINSTL_LINE() {
        return INSTL_LINE;
    }


    /**
     * Sets the INSTL_LINE value for this RowType_EditAccount_Request.
     * 
     * @param INSTL_LINE
     */
    public void setINSTL_LINE(java.math.BigDecimal INSTL_LINE) {
        this.INSTL_LINE = INSTL_LINE;
    }


    /**
     * Gets the INSTL_CONDSET value for this RowType_EditAccount_Request.
     * 
     * @return INSTL_CONDSET
     */
    public java.lang.String getINSTL_CONDSET() {
        return INSTL_CONDSET;
    }


    /**
     * Sets the INSTL_CONDSET value for this RowType_EditAccount_Request.
     * 
     * @param INSTL_CONDSET
     */
    public void setINSTL_CONDSET(java.lang.String INSTL_CONDSET) {
        this.INSTL_CONDSET = INSTL_CONDSET;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_EditAccount_Request)) return false;
        RowType_EditAccount_Request other = (RowType_EditAccount_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ACCOUNT_NO==null && other.getACCOUNT_NO()==null) || 
             (this.ACCOUNT_NO!=null &&
              this.ACCOUNT_NO.equals(other.getACCOUNT_NO()))) &&
            ((this.ACC_PRTY==null && other.getACC_PRTY()==null) || 
             (this.ACC_PRTY!=null &&
              this.ACC_PRTY.equals(other.getACC_PRTY()))) &&
            ((this.c_ACCNT_TYPE==null && other.getC_ACCNT_TYPE()==null) || 
             (this.c_ACCNT_TYPE!=null &&
              this.c_ACCNT_TYPE.equals(other.getC_ACCNT_TYPE()))) &&
            ((this.CARD_ACCT==null && other.getCARD_ACCT()==null) || 
             (this.CARD_ACCT!=null &&
              this.CARD_ACCT.equals(other.getCARD_ACCT()))) &&
            ((this.CCY==null && other.getCCY()==null) || 
             (this.CCY!=null &&
              this.CCY.equals(other.getCCY()))) &&
            ((this.TRANZ_ACCT==null && other.getTRANZ_ACCT()==null) || 
             (this.TRANZ_ACCT!=null &&
              this.TRANZ_ACCT.equals(other.getTRANZ_ACCT()))) &&
            ((this.CYCLE==null && other.getCYCLE()==null) || 
             (this.CYCLE!=null &&
              this.CYCLE.equals(other.getCYCLE()))) &&
            ((this.MIN_BAL==null && other.getMIN_BAL()==null) || 
             (this.MIN_BAL!=null &&
              this.MIN_BAL.equals(other.getMIN_BAL()))) &&
            ((this.COND_SET==null && other.getCOND_SET()==null) || 
             (this.COND_SET!=null &&
              this.COND_SET.equals(other.getCOND_SET()))) &&
            ((this.STATUS==null && other.getSTATUS()==null) || 
             (this.STATUS!=null &&
              this.STATUS.equals(other.getSTATUS()))) &&
            ((this.STAT_CHANGE==null && other.getSTAT_CHANGE()==null) || 
             (this.STAT_CHANGE!=null &&
              this.STAT_CHANGE.equals(other.getSTAT_CHANGE()))) &&
            ((this.STA_COMENT==null && other.getSTA_COMENT()==null) || 
             (this.STA_COMENT!=null &&
              this.STA_COMENT.equals(other.getSTA_COMENT()))) &&
            ((this.AUTH_BONUS==null && other.getAUTH_BONUS()==null) || 
             (this.AUTH_BONUS!=null &&
              this.AUTH_BONUS.equals(other.getAUTH_BONUS()))) &&
            ((this.AB_EXPIRITY==null && other.getAB_EXPIRITY()==null) || 
             (this.AB_EXPIRITY!=null &&
              this.AB_EXPIRITY.equals(other.getAB_EXPIRITY()))) &&
            ((this.CRD==null && other.getCRD()==null) || 
             (this.CRD!=null &&
              this.CRD.equals(other.getCRD()))) &&
            ((this.CRD_EXPIRY==null && other.getCRD_EXPIRY()==null) || 
             (this.CRD_EXPIRY!=null &&
              this.CRD_EXPIRY.equals(other.getCRD_EXPIRY()))) &&
            ((this.ATM_LIMIT==null && other.getATM_LIMIT()==null) || 
             (this.ATM_LIMIT!=null &&
              this.ATM_LIMIT.equals(other.getATM_LIMIT()))) &&
            ((this.NON_REDUCE_BAL==null && other.getNON_REDUCE_BAL()==null) || 
             (this.NON_REDUCE_BAL!=null &&
              this.NON_REDUCE_BAL.equals(other.getNON_REDUCE_BAL()))) &&
            ((this.ADJUST_FLAG==null && other.getADJUST_FLAG()==null) || 
             (this.ADJUST_FLAG!=null &&
              this.ADJUST_FLAG.equals(other.getADJUST_FLAG()))) &&
            ((this.PAY_CODE==null && other.getPAY_CODE()==null) || 
             (this.PAY_CODE!=null &&
              this.PAY_CODE.equals(other.getPAY_CODE()))) &&
            ((this.PAY_FREQ==null && other.getPAY_FREQ()==null) || 
             (this.PAY_FREQ!=null &&
              this.PAY_FREQ.equals(other.getPAY_FREQ()))) &&
            ((this.CALCUL_MODE==null && other.getCALCUL_MODE()==null) || 
             (this.CALCUL_MODE!=null &&
              this.CALCUL_MODE.equals(other.getCALCUL_MODE()))) &&
            ((this.PAY_AMNT==null && other.getPAY_AMNT()==null) || 
             (this.PAY_AMNT!=null &&
              this.PAY_AMNT.equals(other.getPAY_AMNT()))) &&
            ((this.PAY_INTR==null && other.getPAY_INTR()==null) || 
             (this.PAY_INTR!=null &&
              this.PAY_INTR.equals(other.getPAY_INTR()))) &&
            ((this.LIM_AMNT==null && other.getLIM_AMNT()==null) || 
             (this.LIM_AMNT!=null &&
              this.LIM_AMNT.equals(other.getLIM_AMNT()))) &&
            ((this.LIM_INTR==null && other.getLIM_INTR()==null) || 
             (this.LIM_INTR!=null &&
              this.LIM_INTR.equals(other.getLIM_INTR()))) &&
            ((this.CREATED_DATE==null && other.getCREATED_DATE()==null) || 
             (this.CREATED_DATE!=null &&
              this.CREATED_DATE.equals(other.getCREATED_DATE()))) &&
            ((this.STOP_DATE==null && other.getSTOP_DATE()==null) || 
             (this.STOP_DATE!=null &&
              this.STOP_DATE.equals(other.getSTOP_DATE()))) &&
            ((this.MESSAGE==null && other.getMESSAGE()==null) || 
             (this.MESSAGE!=null &&
              this.MESSAGE.equals(other.getMESSAGE()))) &&
            ((this.u_COD7==null && other.getU_COD7()==null) || 
             (this.u_COD7!=null &&
              this.u_COD7.equals(other.getU_COD7()))) &&
            ((this.u_COD8==null && other.getU_COD8()==null) || 
             (this.u_COD8!=null &&
              this.u_COD8.equals(other.getU_COD8()))) &&
            ((this.UFIELD_5==null && other.getUFIELD_5()==null) || 
             (this.UFIELD_5!=null &&
              this.UFIELD_5.equals(other.getUFIELD_5()))) &&
            ((this.u_FIELD6==null && other.getU_FIELD6()==null) || 
             (this.u_FIELD6!=null &&
              this.u_FIELD6.equals(other.getU_FIELD6()))) &&
            ((this.DEPOSIT==null && other.getDEPOSIT()==null) || 
             (this.DEPOSIT!=null &&
              this.DEPOSIT.equals(other.getDEPOSIT()))) &&
            ((this.DEPOSIT_COMENT==null && other.getDEPOSIT_COMENT()==null) || 
             (this.DEPOSIT_COMENT!=null &&
              this.DEPOSIT_COMENT.equals(other.getDEPOSIT_COMENT()))) &&
            ((this.DEPOSIT_ACCOUNT==null && other.getDEPOSIT_ACCOUNT()==null) || 
             (this.DEPOSIT_ACCOUNT!=null &&
              this.DEPOSIT_ACCOUNT.equals(other.getDEPOSIT_ACCOUNT()))) &&
            ((this.AGR_AMOUNT==null && other.getAGR_AMOUNT()==null) || 
             (this.AGR_AMOUNT!=null &&
              this.AGR_AMOUNT.equals(other.getAGR_AMOUNT()))) &&
            ((this.DEP_EXP_DATE==null && other.getDEP_EXP_DATE()==null) || 
             (this.DEP_EXP_DATE!=null &&
              this.DEP_EXP_DATE.equals(other.getDEP_EXP_DATE()))) &&
            ((this.DEP_OPEN_F==null && other.getDEP_OPEN_F()==null) || 
             (this.DEP_OPEN_F!=null &&
              this.DEP_OPEN_F.equals(other.getDEP_OPEN_F()))) &&
            ((this.DEP_FRONT_F==null && other.getDEP_FRONT_F()==null) || 
             (this.DEP_FRONT_F!=null &&
              this.DEP_FRONT_F.equals(other.getDEP_FRONT_F()))) &&
            ((this.DEP_OPER_ACCT==null && other.getDEP_OPER_ACCT()==null) || 
             (this.DEP_OPER_ACCT!=null &&
              this.DEP_OPER_ACCT.equals(other.getDEP_OPER_ACCT()))) &&
            ((this.DEP_OPER_ACCTB==null && other.getDEP_OPER_ACCTB()==null) || 
             (this.DEP_OPER_ACCTB!=null &&
              this.DEP_OPER_ACCTB.equals(other.getDEP_OPER_ACCTB()))) &&
            ((this.DEP_OPER_BACCT==null && other.getDEP_OPER_BACCT()==null) || 
             (this.DEP_OPER_BACCT!=null &&
              this.DEP_OPER_BACCT.equals(other.getDEP_OPER_BACCT()))) &&
            ((this.IN_FILE_NUM==null && other.getIN_FILE_NUM()==null) || 
             (this.IN_FILE_NUM!=null &&
              this.IN_FILE_NUM.equals(other.getIN_FILE_NUM()))) &&
            ((this.OPEN_INSTL==null && other.getOPEN_INSTL()==null) || 
             (this.OPEN_INSTL!=null &&
              this.OPEN_INSTL.equals(other.getOPEN_INSTL()))) &&
            ((this.INSTL_LINE==null && other.getINSTL_LINE()==null) || 
             (this.INSTL_LINE!=null &&
              this.INSTL_LINE.equals(other.getINSTL_LINE()))) &&
            ((this.INSTL_CONDSET==null && other.getINSTL_CONDSET()==null) || 
             (this.INSTL_CONDSET!=null &&
              this.INSTL_CONDSET.equals(other.getINSTL_CONDSET())));
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
        if (getACCOUNT_NO() != null) {
            _hashCode += getACCOUNT_NO().hashCode();
        }
        if (getACC_PRTY() != null) {
            _hashCode += getACC_PRTY().hashCode();
        }
        if (getC_ACCNT_TYPE() != null) {
            _hashCode += getC_ACCNT_TYPE().hashCode();
        }
        if (getCARD_ACCT() != null) {
            _hashCode += getCARD_ACCT().hashCode();
        }
        if (getCCY() != null) {
            _hashCode += getCCY().hashCode();
        }
        if (getTRANZ_ACCT() != null) {
            _hashCode += getTRANZ_ACCT().hashCode();
        }
        if (getCYCLE() != null) {
            _hashCode += getCYCLE().hashCode();
        }
        if (getMIN_BAL() != null) {
            _hashCode += getMIN_BAL().hashCode();
        }
        if (getCOND_SET() != null) {
            _hashCode += getCOND_SET().hashCode();
        }
        if (getSTATUS() != null) {
            _hashCode += getSTATUS().hashCode();
        }
        if (getSTAT_CHANGE() != null) {
            _hashCode += getSTAT_CHANGE().hashCode();
        }
        if (getSTA_COMENT() != null) {
            _hashCode += getSTA_COMENT().hashCode();
        }
        if (getAUTH_BONUS() != null) {
            _hashCode += getAUTH_BONUS().hashCode();
        }
        if (getAB_EXPIRITY() != null) {
            _hashCode += getAB_EXPIRITY().hashCode();
        }
        if (getCRD() != null) {
            _hashCode += getCRD().hashCode();
        }
        if (getCRD_EXPIRY() != null) {
            _hashCode += getCRD_EXPIRY().hashCode();
        }
        if (getATM_LIMIT() != null) {
            _hashCode += getATM_LIMIT().hashCode();
        }
        if (getNON_REDUCE_BAL() != null) {
            _hashCode += getNON_REDUCE_BAL().hashCode();
        }
        if (getADJUST_FLAG() != null) {
            _hashCode += getADJUST_FLAG().hashCode();
        }
        if (getPAY_CODE() != null) {
            _hashCode += getPAY_CODE().hashCode();
        }
        if (getPAY_FREQ() != null) {
            _hashCode += getPAY_FREQ().hashCode();
        }
        if (getCALCUL_MODE() != null) {
            _hashCode += getCALCUL_MODE().hashCode();
        }
        if (getPAY_AMNT() != null) {
            _hashCode += getPAY_AMNT().hashCode();
        }
        if (getPAY_INTR() != null) {
            _hashCode += getPAY_INTR().hashCode();
        }
        if (getLIM_AMNT() != null) {
            _hashCode += getLIM_AMNT().hashCode();
        }
        if (getLIM_INTR() != null) {
            _hashCode += getLIM_INTR().hashCode();
        }
        if (getCREATED_DATE() != null) {
            _hashCode += getCREATED_DATE().hashCode();
        }
        if (getSTOP_DATE() != null) {
            _hashCode += getSTOP_DATE().hashCode();
        }
        if (getMESSAGE() != null) {
            _hashCode += getMESSAGE().hashCode();
        }
        if (getU_COD7() != null) {
            _hashCode += getU_COD7().hashCode();
        }
        if (getU_COD8() != null) {
            _hashCode += getU_COD8().hashCode();
        }
        if (getUFIELD_5() != null) {
            _hashCode += getUFIELD_5().hashCode();
        }
        if (getU_FIELD6() != null) {
            _hashCode += getU_FIELD6().hashCode();
        }
        if (getDEPOSIT() != null) {
            _hashCode += getDEPOSIT().hashCode();
        }
        if (getDEPOSIT_COMENT() != null) {
            _hashCode += getDEPOSIT_COMENT().hashCode();
        }
        if (getDEPOSIT_ACCOUNT() != null) {
            _hashCode += getDEPOSIT_ACCOUNT().hashCode();
        }
        if (getAGR_AMOUNT() != null) {
            _hashCode += getAGR_AMOUNT().hashCode();
        }
        if (getDEP_EXP_DATE() != null) {
            _hashCode += getDEP_EXP_DATE().hashCode();
        }
        if (getDEP_OPEN_F() != null) {
            _hashCode += getDEP_OPEN_F().hashCode();
        }
        if (getDEP_FRONT_F() != null) {
            _hashCode += getDEP_FRONT_F().hashCode();
        }
        if (getDEP_OPER_ACCT() != null) {
            _hashCode += getDEP_OPER_ACCT().hashCode();
        }
        if (getDEP_OPER_ACCTB() != null) {
            _hashCode += getDEP_OPER_ACCTB().hashCode();
        }
        if (getDEP_OPER_BACCT() != null) {
            _hashCode += getDEP_OPER_BACCT().hashCode();
        }
        if (getIN_FILE_NUM() != null) {
            _hashCode += getIN_FILE_NUM().hashCode();
        }
        if (getOPEN_INSTL() != null) {
            _hashCode += getOPEN_INSTL().hashCode();
        }
        if (getINSTL_LINE() != null) {
            _hashCode += getINSTL_LINE().hashCode();
        }
        if (getINSTL_CONDSET() != null) {
            _hashCode += getINSTL_CONDSET().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_EditAccount_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EditAccount_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACCOUNT_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACCOUNT_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACC_PRTY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACC_PRTY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_ACCNT_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "C_ACCNT_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_ACCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_ACCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRANZ_ACCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRANZ_ACCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CYCLE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CYCLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MIN_BAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MIN_BAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("STATUS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STATUS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STAT_CHANGE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STAT_CHANGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STA_COMENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STA_COMENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTH_BONUS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AUTH_BONUS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AB_EXPIRITY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AB_EXPIRITY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CRD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CRD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CRD_EXPIRY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CRD_EXPIRY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATM_LIMIT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ATM_LIMIT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NON_REDUCE_BAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NON_REDUCE_BAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ADJUST_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ADJUST_FLAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAY_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAY_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAY_FREQ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAY_FREQ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CALCUL_MODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CALCUL_MODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAY_AMNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAY_AMNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAY_INTR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAY_INTR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LIM_AMNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LIM_AMNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LIM_INTR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LIM_INTR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CREATED_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CREATED_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STOP_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STOP_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MESSAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_COD7");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_COD7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_COD8");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_COD8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UFIELD_5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UFIELD_5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_FIELD6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "U_FIELD6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEPOSIT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEPOSIT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEPOSIT_COMENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEPOSIT_COMENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEPOSIT_ACCOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEPOSIT_ACCOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGR_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AGR_AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_EXP_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_EXP_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_OPEN_F");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_OPEN_F"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_FRONT_F");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_FRONT_F"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_OPER_ACCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_OPER_ACCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_OPER_ACCTB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_OPER_ACCTB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_OPER_BACCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_OPER_BACCT"));
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
        elemField.setFieldName("OPEN_INSTL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPEN_INSTL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTL_LINE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSTL_LINE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTL_CONDSET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSTL_CONDSET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
