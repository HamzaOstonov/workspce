/**
 * RowType_ExecTransaction_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_ExecTransaction_Request  implements java.io.Serializable {
    private java.lang.String PAYMENT_MODE;

    private java.math.BigDecimal ACCOUNT_NO;

    private java.lang.String CARD_ACCT;

    private java.lang.String CARD_ACCT_CCY;

    private java.lang.String CARD;

    private java.util.Calendar EXECUTE_ON;

    private java.lang.String TRAN_TYPE;

    private java.lang.String TRAN_CCY;

    private java.math.BigDecimal TRAN_AMNT;

    private java.lang.String BRANCH;

    private java.lang.String BATCH_NR;

    private java.lang.String SLIP_NR;

    private java.lang.String DEAL_DESC;

    private java.lang.String COUNTERPARTY;

    private java.math.BigDecimal INTERNAL_NO;

    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    private java.util.Calendar TRAN_DATE_TIME;

    private java.math.BigDecimal EXECUTION_TYPE;

    private java.lang.String BOOKING_MSG;

    private java.lang.String TR_CODE;

    private java.math.BigDecimal TR_FEE;

    private java.lang.String TR_CODE2;

    private java.math.BigDecimal TR_FEE2;

    private java.lang.String TR_CODE3;

    private java.math.BigDecimal TR_FEE3;

    private java.lang.String TR_CODE4;

    private java.math.BigDecimal TR_FEE4;

    private java.lang.String TR_CODE5;

    private java.math.BigDecimal TR_FEE5;

    private java.lang.String TR_CODE6;

    private java.math.BigDecimal TR_FEE6;

    private java.lang.String TR_CODE7;

    private java.math.BigDecimal TR_FEE7;

    private java.lang.String TR_CODE8;

    private java.math.BigDecimal TR_FEE8;

    private java.lang.String TR_CODE9;

    private java.math.BigDecimal TR_FEE9;

    private java.lang.String TR_CODE10;

    private java.math.BigDecimal TR_FEE10;

    private java.math.BigInteger CHECK_DUPL;

    private java.math.BigDecimal INSTL_AGR_NO;

    private java.math.BigInteger ACCNT_TYPE;

    public RowType_ExecTransaction_Request() {
    }

    public RowType_ExecTransaction_Request(
           java.lang.String PAYMENT_MODE,
           java.math.BigDecimal ACCOUNT_NO,
           java.lang.String CARD_ACCT,
           java.lang.String CARD_ACCT_CCY,
           java.lang.String CARD,
           java.util.Calendar EXECUTE_ON,
           java.lang.String TRAN_TYPE,
           java.lang.String TRAN_CCY,
           java.math.BigDecimal TRAN_AMNT,
           java.lang.String BRANCH,
           java.lang.String BATCH_NR,
           java.lang.String SLIP_NR,
           java.lang.String DEAL_DESC,
           java.lang.String COUNTERPARTY,
           java.math.BigDecimal INTERNAL_NO,
           java.lang.String BANK_C,
           java.lang.String GROUPC,
           java.util.Calendar TRAN_DATE_TIME,
           java.math.BigDecimal EXECUTION_TYPE,
           java.lang.String BOOKING_MSG,
           java.lang.String TR_CODE,
           java.math.BigDecimal TR_FEE,
           java.lang.String TR_CODE2,
           java.math.BigDecimal TR_FEE2,
           java.lang.String TR_CODE3,
           java.math.BigDecimal TR_FEE3,
           java.lang.String TR_CODE4,
           java.math.BigDecimal TR_FEE4,
           java.lang.String TR_CODE5,
           java.math.BigDecimal TR_FEE5,
           java.lang.String TR_CODE6,
           java.math.BigDecimal TR_FEE6,
           java.lang.String TR_CODE7,
           java.math.BigDecimal TR_FEE7,
           java.lang.String TR_CODE8,
           java.math.BigDecimal TR_FEE8,
           java.lang.String TR_CODE9,
           java.math.BigDecimal TR_FEE9,
           java.lang.String TR_CODE10,
           java.math.BigDecimal TR_FEE10,
           java.math.BigInteger CHECK_DUPL,
           java.math.BigDecimal INSTL_AGR_NO,
           java.math.BigInteger ACCNT_TYPE) {
           this.PAYMENT_MODE = PAYMENT_MODE;
           this.ACCOUNT_NO = ACCOUNT_NO;
           this.CARD_ACCT = CARD_ACCT;
           this.CARD_ACCT_CCY = CARD_ACCT_CCY;
           this.CARD = CARD;
           this.EXECUTE_ON = EXECUTE_ON;
           this.TRAN_TYPE = TRAN_TYPE;
           this.TRAN_CCY = TRAN_CCY;
           this.TRAN_AMNT = TRAN_AMNT;
           this.BRANCH = BRANCH;
           this.BATCH_NR = BATCH_NR;
           this.SLIP_NR = SLIP_NR;
           this.DEAL_DESC = DEAL_DESC;
           this.COUNTERPARTY = COUNTERPARTY;
           this.INTERNAL_NO = INTERNAL_NO;
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
           this.TRAN_DATE_TIME = TRAN_DATE_TIME;
           this.EXECUTION_TYPE = EXECUTION_TYPE;
           this.BOOKING_MSG = BOOKING_MSG;
           this.TR_CODE = TR_CODE;
           this.TR_FEE = TR_FEE;
           this.TR_CODE2 = TR_CODE2;
           this.TR_FEE2 = TR_FEE2;
           this.TR_CODE3 = TR_CODE3;
           this.TR_FEE3 = TR_FEE3;
           this.TR_CODE4 = TR_CODE4;
           this.TR_FEE4 = TR_FEE4;
           this.TR_CODE5 = TR_CODE5;
           this.TR_FEE5 = TR_FEE5;
           this.TR_CODE6 = TR_CODE6;
           this.TR_FEE6 = TR_FEE6;
           this.TR_CODE7 = TR_CODE7;
           this.TR_FEE7 = TR_FEE7;
           this.TR_CODE8 = TR_CODE8;
           this.TR_FEE8 = TR_FEE8;
           this.TR_CODE9 = TR_CODE9;
           this.TR_FEE9 = TR_FEE9;
           this.TR_CODE10 = TR_CODE10;
           this.TR_FEE10 = TR_FEE10;
           this.CHECK_DUPL = CHECK_DUPL;
           this.INSTL_AGR_NO = INSTL_AGR_NO;
           this.ACCNT_TYPE = ACCNT_TYPE;
    }


    /**
     * Gets the PAYMENT_MODE value for this RowType_ExecTransaction_Request.
     * 
     * @return PAYMENT_MODE
     */
    public java.lang.String getPAYMENT_MODE() {
        return PAYMENT_MODE;
    }


    /**
     * Sets the PAYMENT_MODE value for this RowType_ExecTransaction_Request.
     * 
     * @param PAYMENT_MODE
     */
    public void setPAYMENT_MODE(java.lang.String PAYMENT_MODE) {
        this.PAYMENT_MODE = PAYMENT_MODE;
    }


    /**
     * Gets the ACCOUNT_NO value for this RowType_ExecTransaction_Request.
     * 
     * @return ACCOUNT_NO
     */
    public java.math.BigDecimal getACCOUNT_NO() {
        return ACCOUNT_NO;
    }


    /**
     * Sets the ACCOUNT_NO value for this RowType_ExecTransaction_Request.
     * 
     * @param ACCOUNT_NO
     */
    public void setACCOUNT_NO(java.math.BigDecimal ACCOUNT_NO) {
        this.ACCOUNT_NO = ACCOUNT_NO;
    }


    /**
     * Gets the CARD_ACCT value for this RowType_ExecTransaction_Request.
     * 
     * @return CARD_ACCT
     */
    public java.lang.String getCARD_ACCT() {
        return CARD_ACCT;
    }


    /**
     * Sets the CARD_ACCT value for this RowType_ExecTransaction_Request.
     * 
     * @param CARD_ACCT
     */
    public void setCARD_ACCT(java.lang.String CARD_ACCT) {
        this.CARD_ACCT = CARD_ACCT;
    }


    /**
     * Gets the CARD_ACCT_CCY value for this RowType_ExecTransaction_Request.
     * 
     * @return CARD_ACCT_CCY
     */
    public java.lang.String getCARD_ACCT_CCY() {
        return CARD_ACCT_CCY;
    }


    /**
     * Sets the CARD_ACCT_CCY value for this RowType_ExecTransaction_Request.
     * 
     * @param CARD_ACCT_CCY
     */
    public void setCARD_ACCT_CCY(java.lang.String CARD_ACCT_CCY) {
        this.CARD_ACCT_CCY = CARD_ACCT_CCY;
    }


    /**
     * Gets the CARD value for this RowType_ExecTransaction_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_ExecTransaction_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the EXECUTE_ON value for this RowType_ExecTransaction_Request.
     * 
     * @return EXECUTE_ON
     */
    public java.util.Calendar getEXECUTE_ON() {
        return EXECUTE_ON;
    }


    /**
     * Sets the EXECUTE_ON value for this RowType_ExecTransaction_Request.
     * 
     * @param EXECUTE_ON
     */
    public void setEXECUTE_ON(java.util.Calendar EXECUTE_ON) {
        this.EXECUTE_ON = EXECUTE_ON;
    }


    /**
     * Gets the TRAN_TYPE value for this RowType_ExecTransaction_Request.
     * 
     * @return TRAN_TYPE
     */
    public java.lang.String getTRAN_TYPE() {
        return TRAN_TYPE;
    }


    /**
     * Sets the TRAN_TYPE value for this RowType_ExecTransaction_Request.
     * 
     * @param TRAN_TYPE
     */
    public void setTRAN_TYPE(java.lang.String TRAN_TYPE) {
        this.TRAN_TYPE = TRAN_TYPE;
    }


    /**
     * Gets the TRAN_CCY value for this RowType_ExecTransaction_Request.
     * 
     * @return TRAN_CCY
     */
    public java.lang.String getTRAN_CCY() {
        return TRAN_CCY;
    }


    /**
     * Sets the TRAN_CCY value for this RowType_ExecTransaction_Request.
     * 
     * @param TRAN_CCY
     */
    public void setTRAN_CCY(java.lang.String TRAN_CCY) {
        this.TRAN_CCY = TRAN_CCY;
    }


    /**
     * Gets the TRAN_AMNT value for this RowType_ExecTransaction_Request.
     * 
     * @return TRAN_AMNT
     */
    public java.math.BigDecimal getTRAN_AMNT() {
        return TRAN_AMNT;
    }


    /**
     * Sets the TRAN_AMNT value for this RowType_ExecTransaction_Request.
     * 
     * @param TRAN_AMNT
     */
    public void setTRAN_AMNT(java.math.BigDecimal TRAN_AMNT) {
        this.TRAN_AMNT = TRAN_AMNT;
    }


    /**
     * Gets the BRANCH value for this RowType_ExecTransaction_Request.
     * 
     * @return BRANCH
     */
    public java.lang.String getBRANCH() {
        return BRANCH;
    }


    /**
     * Sets the BRANCH value for this RowType_ExecTransaction_Request.
     * 
     * @param BRANCH
     */
    public void setBRANCH(java.lang.String BRANCH) {
        this.BRANCH = BRANCH;
    }


    /**
     * Gets the BATCH_NR value for this RowType_ExecTransaction_Request.
     * 
     * @return BATCH_NR
     */
    public java.lang.String getBATCH_NR() {
        return BATCH_NR;
    }


    /**
     * Sets the BATCH_NR value for this RowType_ExecTransaction_Request.
     * 
     * @param BATCH_NR
     */
    public void setBATCH_NR(java.lang.String BATCH_NR) {
        this.BATCH_NR = BATCH_NR;
    }


    /**
     * Gets the SLIP_NR value for this RowType_ExecTransaction_Request.
     * 
     * @return SLIP_NR
     */
    public java.lang.String getSLIP_NR() {
        return SLIP_NR;
    }


    /**
     * Sets the SLIP_NR value for this RowType_ExecTransaction_Request.
     * 
     * @param SLIP_NR
     */
    public void setSLIP_NR(java.lang.String SLIP_NR) {
        this.SLIP_NR = SLIP_NR;
    }


    /**
     * Gets the DEAL_DESC value for this RowType_ExecTransaction_Request.
     * 
     * @return DEAL_DESC
     */
    public java.lang.String getDEAL_DESC() {
        return DEAL_DESC;
    }


    /**
     * Sets the DEAL_DESC value for this RowType_ExecTransaction_Request.
     * 
     * @param DEAL_DESC
     */
    public void setDEAL_DESC(java.lang.String DEAL_DESC) {
        this.DEAL_DESC = DEAL_DESC;
    }


    /**
     * Gets the COUNTERPARTY value for this RowType_ExecTransaction_Request.
     * 
     * @return COUNTERPARTY
     */
    public java.lang.String getCOUNTERPARTY() {
        return COUNTERPARTY;
    }


    /**
     * Sets the COUNTERPARTY value for this RowType_ExecTransaction_Request.
     * 
     * @param COUNTERPARTY
     */
    public void setCOUNTERPARTY(java.lang.String COUNTERPARTY) {
        this.COUNTERPARTY = COUNTERPARTY;
    }


    /**
     * Gets the INTERNAL_NO value for this RowType_ExecTransaction_Request.
     * 
     * @return INTERNAL_NO
     */
    public java.math.BigDecimal getINTERNAL_NO() {
        return INTERNAL_NO;
    }


    /**
     * Sets the INTERNAL_NO value for this RowType_ExecTransaction_Request.
     * 
     * @param INTERNAL_NO
     */
    public void setINTERNAL_NO(java.math.BigDecimal INTERNAL_NO) {
        this.INTERNAL_NO = INTERNAL_NO;
    }


    /**
     * Gets the BANK_C value for this RowType_ExecTransaction_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_ExecTransaction_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_ExecTransaction_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_ExecTransaction_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }


    /**
     * Gets the TRAN_DATE_TIME value for this RowType_ExecTransaction_Request.
     * 
     * @return TRAN_DATE_TIME
     */
    public java.util.Calendar getTRAN_DATE_TIME() {
        return TRAN_DATE_TIME;
    }


    /**
     * Sets the TRAN_DATE_TIME value for this RowType_ExecTransaction_Request.
     * 
     * @param TRAN_DATE_TIME
     */
    public void setTRAN_DATE_TIME(java.util.Calendar TRAN_DATE_TIME) {
        this.TRAN_DATE_TIME = TRAN_DATE_TIME;
    }


    /**
     * Gets the EXECUTION_TYPE value for this RowType_ExecTransaction_Request.
     * 
     * @return EXECUTION_TYPE
     */
    public java.math.BigDecimal getEXECUTION_TYPE() {
        return EXECUTION_TYPE;
    }


    /**
     * Sets the EXECUTION_TYPE value for this RowType_ExecTransaction_Request.
     * 
     * @param EXECUTION_TYPE
     */
    public void setEXECUTION_TYPE(java.math.BigDecimal EXECUTION_TYPE) {
        this.EXECUTION_TYPE = EXECUTION_TYPE;
    }


    /**
     * Gets the BOOKING_MSG value for this RowType_ExecTransaction_Request.
     * 
     * @return BOOKING_MSG
     */
    public java.lang.String getBOOKING_MSG() {
        return BOOKING_MSG;
    }


    /**
     * Sets the BOOKING_MSG value for this RowType_ExecTransaction_Request.
     * 
     * @param BOOKING_MSG
     */
    public void setBOOKING_MSG(java.lang.String BOOKING_MSG) {
        this.BOOKING_MSG = BOOKING_MSG;
    }


    /**
     * Gets the TR_CODE value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_CODE
     */
    public java.lang.String getTR_CODE() {
        return TR_CODE;
    }


    /**
     * Sets the TR_CODE value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_CODE
     */
    public void setTR_CODE(java.lang.String TR_CODE) {
        this.TR_CODE = TR_CODE;
    }


    /**
     * Gets the TR_FEE value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_FEE
     */
    public java.math.BigDecimal getTR_FEE() {
        return TR_FEE;
    }


    /**
     * Sets the TR_FEE value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_FEE
     */
    public void setTR_FEE(java.math.BigDecimal TR_FEE) {
        this.TR_FEE = TR_FEE;
    }


    /**
     * Gets the TR_CODE2 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_CODE2
     */
    public java.lang.String getTR_CODE2() {
        return TR_CODE2;
    }


    /**
     * Sets the TR_CODE2 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_CODE2
     */
    public void setTR_CODE2(java.lang.String TR_CODE2) {
        this.TR_CODE2 = TR_CODE2;
    }


    /**
     * Gets the TR_FEE2 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_FEE2
     */
    public java.math.BigDecimal getTR_FEE2() {
        return TR_FEE2;
    }


    /**
     * Sets the TR_FEE2 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_FEE2
     */
    public void setTR_FEE2(java.math.BigDecimal TR_FEE2) {
        this.TR_FEE2 = TR_FEE2;
    }


    /**
     * Gets the TR_CODE3 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_CODE3
     */
    public java.lang.String getTR_CODE3() {
        return TR_CODE3;
    }


    /**
     * Sets the TR_CODE3 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_CODE3
     */
    public void setTR_CODE3(java.lang.String TR_CODE3) {
        this.TR_CODE3 = TR_CODE3;
    }


    /**
     * Gets the TR_FEE3 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_FEE3
     */
    public java.math.BigDecimal getTR_FEE3() {
        return TR_FEE3;
    }


    /**
     * Sets the TR_FEE3 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_FEE3
     */
    public void setTR_FEE3(java.math.BigDecimal TR_FEE3) {
        this.TR_FEE3 = TR_FEE3;
    }


    /**
     * Gets the TR_CODE4 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_CODE4
     */
    public java.lang.String getTR_CODE4() {
        return TR_CODE4;
    }


    /**
     * Sets the TR_CODE4 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_CODE4
     */
    public void setTR_CODE4(java.lang.String TR_CODE4) {
        this.TR_CODE4 = TR_CODE4;
    }


    /**
     * Gets the TR_FEE4 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_FEE4
     */
    public java.math.BigDecimal getTR_FEE4() {
        return TR_FEE4;
    }


    /**
     * Sets the TR_FEE4 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_FEE4
     */
    public void setTR_FEE4(java.math.BigDecimal TR_FEE4) {
        this.TR_FEE4 = TR_FEE4;
    }


    /**
     * Gets the TR_CODE5 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_CODE5
     */
    public java.lang.String getTR_CODE5() {
        return TR_CODE5;
    }


    /**
     * Sets the TR_CODE5 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_CODE5
     */
    public void setTR_CODE5(java.lang.String TR_CODE5) {
        this.TR_CODE5 = TR_CODE5;
    }


    /**
     * Gets the TR_FEE5 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_FEE5
     */
    public java.math.BigDecimal getTR_FEE5() {
        return TR_FEE5;
    }


    /**
     * Sets the TR_FEE5 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_FEE5
     */
    public void setTR_FEE5(java.math.BigDecimal TR_FEE5) {
        this.TR_FEE5 = TR_FEE5;
    }


    /**
     * Gets the TR_CODE6 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_CODE6
     */
    public java.lang.String getTR_CODE6() {
        return TR_CODE6;
    }


    /**
     * Sets the TR_CODE6 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_CODE6
     */
    public void setTR_CODE6(java.lang.String TR_CODE6) {
        this.TR_CODE6 = TR_CODE6;
    }


    /**
     * Gets the TR_FEE6 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_FEE6
     */
    public java.math.BigDecimal getTR_FEE6() {
        return TR_FEE6;
    }


    /**
     * Sets the TR_FEE6 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_FEE6
     */
    public void setTR_FEE6(java.math.BigDecimal TR_FEE6) {
        this.TR_FEE6 = TR_FEE6;
    }


    /**
     * Gets the TR_CODE7 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_CODE7
     */
    public java.lang.String getTR_CODE7() {
        return TR_CODE7;
    }


    /**
     * Sets the TR_CODE7 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_CODE7
     */
    public void setTR_CODE7(java.lang.String TR_CODE7) {
        this.TR_CODE7 = TR_CODE7;
    }


    /**
     * Gets the TR_FEE7 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_FEE7
     */
    public java.math.BigDecimal getTR_FEE7() {
        return TR_FEE7;
    }


    /**
     * Sets the TR_FEE7 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_FEE7
     */
    public void setTR_FEE7(java.math.BigDecimal TR_FEE7) {
        this.TR_FEE7 = TR_FEE7;
    }


    /**
     * Gets the TR_CODE8 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_CODE8
     */
    public java.lang.String getTR_CODE8() {
        return TR_CODE8;
    }


    /**
     * Sets the TR_CODE8 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_CODE8
     */
    public void setTR_CODE8(java.lang.String TR_CODE8) {
        this.TR_CODE8 = TR_CODE8;
    }


    /**
     * Gets the TR_FEE8 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_FEE8
     */
    public java.math.BigDecimal getTR_FEE8() {
        return TR_FEE8;
    }


    /**
     * Sets the TR_FEE8 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_FEE8
     */
    public void setTR_FEE8(java.math.BigDecimal TR_FEE8) {
        this.TR_FEE8 = TR_FEE8;
    }


    /**
     * Gets the TR_CODE9 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_CODE9
     */
    public java.lang.String getTR_CODE9() {
        return TR_CODE9;
    }


    /**
     * Sets the TR_CODE9 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_CODE9
     */
    public void setTR_CODE9(java.lang.String TR_CODE9) {
        this.TR_CODE9 = TR_CODE9;
    }


    /**
     * Gets the TR_FEE9 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_FEE9
     */
    public java.math.BigDecimal getTR_FEE9() {
        return TR_FEE9;
    }


    /**
     * Sets the TR_FEE9 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_FEE9
     */
    public void setTR_FEE9(java.math.BigDecimal TR_FEE9) {
        this.TR_FEE9 = TR_FEE9;
    }


    /**
     * Gets the TR_CODE10 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_CODE10
     */
    public java.lang.String getTR_CODE10() {
        return TR_CODE10;
    }


    /**
     * Sets the TR_CODE10 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_CODE10
     */
    public void setTR_CODE10(java.lang.String TR_CODE10) {
        this.TR_CODE10 = TR_CODE10;
    }


    /**
     * Gets the TR_FEE10 value for this RowType_ExecTransaction_Request.
     * 
     * @return TR_FEE10
     */
    public java.math.BigDecimal getTR_FEE10() {
        return TR_FEE10;
    }


    /**
     * Sets the TR_FEE10 value for this RowType_ExecTransaction_Request.
     * 
     * @param TR_FEE10
     */
    public void setTR_FEE10(java.math.BigDecimal TR_FEE10) {
        this.TR_FEE10 = TR_FEE10;
    }


    /**
     * Gets the CHECK_DUPL value for this RowType_ExecTransaction_Request.
     * 
     * @return CHECK_DUPL
     */
    public java.math.BigInteger getCHECK_DUPL() {
        return CHECK_DUPL;
    }


    /**
     * Sets the CHECK_DUPL value for this RowType_ExecTransaction_Request.
     * 
     * @param CHECK_DUPL
     */
    public void setCHECK_DUPL(java.math.BigInteger CHECK_DUPL) {
        this.CHECK_DUPL = CHECK_DUPL;
    }


    /**
     * Gets the INSTL_AGR_NO value for this RowType_ExecTransaction_Request.
     * 
     * @return INSTL_AGR_NO
     */
    public java.math.BigDecimal getINSTL_AGR_NO() {
        return INSTL_AGR_NO;
    }


    /**
     * Sets the INSTL_AGR_NO value for this RowType_ExecTransaction_Request.
     * 
     * @param INSTL_AGR_NO
     */
    public void setINSTL_AGR_NO(java.math.BigDecimal INSTL_AGR_NO) {
        this.INSTL_AGR_NO = INSTL_AGR_NO;
    }


    /**
     * Gets the ACCNT_TYPE value for this RowType_ExecTransaction_Request.
     * 
     * @return ACCNT_TYPE
     */
    public java.math.BigInteger getACCNT_TYPE() {
        return ACCNT_TYPE;
    }


    /**
     * Sets the ACCNT_TYPE value for this RowType_ExecTransaction_Request.
     * 
     * @param ACCNT_TYPE
     */
    public void setACCNT_TYPE(java.math.BigInteger ACCNT_TYPE) {
        this.ACCNT_TYPE = ACCNT_TYPE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ExecTransaction_Request)) return false;
        RowType_ExecTransaction_Request other = (RowType_ExecTransaction_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PAYMENT_MODE==null && other.getPAYMENT_MODE()==null) || 
             (this.PAYMENT_MODE!=null &&
              this.PAYMENT_MODE.equals(other.getPAYMENT_MODE()))) &&
            ((this.ACCOUNT_NO==null && other.getACCOUNT_NO()==null) || 
             (this.ACCOUNT_NO!=null &&
              this.ACCOUNT_NO.equals(other.getACCOUNT_NO()))) &&
            ((this.CARD_ACCT==null && other.getCARD_ACCT()==null) || 
             (this.CARD_ACCT!=null &&
              this.CARD_ACCT.equals(other.getCARD_ACCT()))) &&
            ((this.CARD_ACCT_CCY==null && other.getCARD_ACCT_CCY()==null) || 
             (this.CARD_ACCT_CCY!=null &&
              this.CARD_ACCT_CCY.equals(other.getCARD_ACCT_CCY()))) &&
            ((this.CARD==null && other.getCARD()==null) || 
             (this.CARD!=null &&
              this.CARD.equals(other.getCARD()))) &&
            ((this.EXECUTE_ON==null && other.getEXECUTE_ON()==null) || 
             (this.EXECUTE_ON!=null &&
              this.EXECUTE_ON.equals(other.getEXECUTE_ON()))) &&
            ((this.TRAN_TYPE==null && other.getTRAN_TYPE()==null) || 
             (this.TRAN_TYPE!=null &&
              this.TRAN_TYPE.equals(other.getTRAN_TYPE()))) &&
            ((this.TRAN_CCY==null && other.getTRAN_CCY()==null) || 
             (this.TRAN_CCY!=null &&
              this.TRAN_CCY.equals(other.getTRAN_CCY()))) &&
            ((this.TRAN_AMNT==null && other.getTRAN_AMNT()==null) || 
             (this.TRAN_AMNT!=null &&
              this.TRAN_AMNT.equals(other.getTRAN_AMNT()))) &&
            ((this.BRANCH==null && other.getBRANCH()==null) || 
             (this.BRANCH!=null &&
              this.BRANCH.equals(other.getBRANCH()))) &&
            ((this.BATCH_NR==null && other.getBATCH_NR()==null) || 
             (this.BATCH_NR!=null &&
              this.BATCH_NR.equals(other.getBATCH_NR()))) &&
            ((this.SLIP_NR==null && other.getSLIP_NR()==null) || 
             (this.SLIP_NR!=null &&
              this.SLIP_NR.equals(other.getSLIP_NR()))) &&
            ((this.DEAL_DESC==null && other.getDEAL_DESC()==null) || 
             (this.DEAL_DESC!=null &&
              this.DEAL_DESC.equals(other.getDEAL_DESC()))) &&
            ((this.COUNTERPARTY==null && other.getCOUNTERPARTY()==null) || 
             (this.COUNTERPARTY!=null &&
              this.COUNTERPARTY.equals(other.getCOUNTERPARTY()))) &&
            ((this.INTERNAL_NO==null && other.getINTERNAL_NO()==null) || 
             (this.INTERNAL_NO!=null &&
              this.INTERNAL_NO.equals(other.getINTERNAL_NO()))) &&
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C()))) &&
            ((this.GROUPC==null && other.getGROUPC()==null) || 
             (this.GROUPC!=null &&
              this.GROUPC.equals(other.getGROUPC()))) &&
            ((this.TRAN_DATE_TIME==null && other.getTRAN_DATE_TIME()==null) || 
             (this.TRAN_DATE_TIME!=null &&
              this.TRAN_DATE_TIME.equals(other.getTRAN_DATE_TIME()))) &&
            ((this.EXECUTION_TYPE==null && other.getEXECUTION_TYPE()==null) || 
             (this.EXECUTION_TYPE!=null &&
              this.EXECUTION_TYPE.equals(other.getEXECUTION_TYPE()))) &&
            ((this.BOOKING_MSG==null && other.getBOOKING_MSG()==null) || 
             (this.BOOKING_MSG!=null &&
              this.BOOKING_MSG.equals(other.getBOOKING_MSG()))) &&
            ((this.TR_CODE==null && other.getTR_CODE()==null) || 
             (this.TR_CODE!=null &&
              this.TR_CODE.equals(other.getTR_CODE()))) &&
            ((this.TR_FEE==null && other.getTR_FEE()==null) || 
             (this.TR_FEE!=null &&
              this.TR_FEE.equals(other.getTR_FEE()))) &&
            ((this.TR_CODE2==null && other.getTR_CODE2()==null) || 
             (this.TR_CODE2!=null &&
              this.TR_CODE2.equals(other.getTR_CODE2()))) &&
            ((this.TR_FEE2==null && other.getTR_FEE2()==null) || 
             (this.TR_FEE2!=null &&
              this.TR_FEE2.equals(other.getTR_FEE2()))) &&
            ((this.TR_CODE3==null && other.getTR_CODE3()==null) || 
             (this.TR_CODE3!=null &&
              this.TR_CODE3.equals(other.getTR_CODE3()))) &&
            ((this.TR_FEE3==null && other.getTR_FEE3()==null) || 
             (this.TR_FEE3!=null &&
              this.TR_FEE3.equals(other.getTR_FEE3()))) &&
            ((this.TR_CODE4==null && other.getTR_CODE4()==null) || 
             (this.TR_CODE4!=null &&
              this.TR_CODE4.equals(other.getTR_CODE4()))) &&
            ((this.TR_FEE4==null && other.getTR_FEE4()==null) || 
             (this.TR_FEE4!=null &&
              this.TR_FEE4.equals(other.getTR_FEE4()))) &&
            ((this.TR_CODE5==null && other.getTR_CODE5()==null) || 
             (this.TR_CODE5!=null &&
              this.TR_CODE5.equals(other.getTR_CODE5()))) &&
            ((this.TR_FEE5==null && other.getTR_FEE5()==null) || 
             (this.TR_FEE5!=null &&
              this.TR_FEE5.equals(other.getTR_FEE5()))) &&
            ((this.TR_CODE6==null && other.getTR_CODE6()==null) || 
             (this.TR_CODE6!=null &&
              this.TR_CODE6.equals(other.getTR_CODE6()))) &&
            ((this.TR_FEE6==null && other.getTR_FEE6()==null) || 
             (this.TR_FEE6!=null &&
              this.TR_FEE6.equals(other.getTR_FEE6()))) &&
            ((this.TR_CODE7==null && other.getTR_CODE7()==null) || 
             (this.TR_CODE7!=null &&
              this.TR_CODE7.equals(other.getTR_CODE7()))) &&
            ((this.TR_FEE7==null && other.getTR_FEE7()==null) || 
             (this.TR_FEE7!=null &&
              this.TR_FEE7.equals(other.getTR_FEE7()))) &&
            ((this.TR_CODE8==null && other.getTR_CODE8()==null) || 
             (this.TR_CODE8!=null &&
              this.TR_CODE8.equals(other.getTR_CODE8()))) &&
            ((this.TR_FEE8==null && other.getTR_FEE8()==null) || 
             (this.TR_FEE8!=null &&
              this.TR_FEE8.equals(other.getTR_FEE8()))) &&
            ((this.TR_CODE9==null && other.getTR_CODE9()==null) || 
             (this.TR_CODE9!=null &&
              this.TR_CODE9.equals(other.getTR_CODE9()))) &&
            ((this.TR_FEE9==null && other.getTR_FEE9()==null) || 
             (this.TR_FEE9!=null &&
              this.TR_FEE9.equals(other.getTR_FEE9()))) &&
            ((this.TR_CODE10==null && other.getTR_CODE10()==null) || 
             (this.TR_CODE10!=null &&
              this.TR_CODE10.equals(other.getTR_CODE10()))) &&
            ((this.TR_FEE10==null && other.getTR_FEE10()==null) || 
             (this.TR_FEE10!=null &&
              this.TR_FEE10.equals(other.getTR_FEE10()))) &&
            ((this.CHECK_DUPL==null && other.getCHECK_DUPL()==null) || 
             (this.CHECK_DUPL!=null &&
              this.CHECK_DUPL.equals(other.getCHECK_DUPL()))) &&
            ((this.INSTL_AGR_NO==null && other.getINSTL_AGR_NO()==null) || 
             (this.INSTL_AGR_NO!=null &&
              this.INSTL_AGR_NO.equals(other.getINSTL_AGR_NO()))) &&
            ((this.ACCNT_TYPE==null && other.getACCNT_TYPE()==null) || 
             (this.ACCNT_TYPE!=null &&
              this.ACCNT_TYPE.equals(other.getACCNT_TYPE())));
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
        if (getPAYMENT_MODE() != null) {
            _hashCode += getPAYMENT_MODE().hashCode();
        }
        if (getACCOUNT_NO() != null) {
            _hashCode += getACCOUNT_NO().hashCode();
        }
        if (getCARD_ACCT() != null) {
            _hashCode += getCARD_ACCT().hashCode();
        }
        if (getCARD_ACCT_CCY() != null) {
            _hashCode += getCARD_ACCT_CCY().hashCode();
        }
        if (getCARD() != null) {
            _hashCode += getCARD().hashCode();
        }
        if (getEXECUTE_ON() != null) {
            _hashCode += getEXECUTE_ON().hashCode();
        }
        if (getTRAN_TYPE() != null) {
            _hashCode += getTRAN_TYPE().hashCode();
        }
        if (getTRAN_CCY() != null) {
            _hashCode += getTRAN_CCY().hashCode();
        }
        if (getTRAN_AMNT() != null) {
            _hashCode += getTRAN_AMNT().hashCode();
        }
        if (getBRANCH() != null) {
            _hashCode += getBRANCH().hashCode();
        }
        if (getBATCH_NR() != null) {
            _hashCode += getBATCH_NR().hashCode();
        }
        if (getSLIP_NR() != null) {
            _hashCode += getSLIP_NR().hashCode();
        }
        if (getDEAL_DESC() != null) {
            _hashCode += getDEAL_DESC().hashCode();
        }
        if (getCOUNTERPARTY() != null) {
            _hashCode += getCOUNTERPARTY().hashCode();
        }
        if (getINTERNAL_NO() != null) {
            _hashCode += getINTERNAL_NO().hashCode();
        }
        if (getBANK_C() != null) {
            _hashCode += getBANK_C().hashCode();
        }
        if (getGROUPC() != null) {
            _hashCode += getGROUPC().hashCode();
        }
        if (getTRAN_DATE_TIME() != null) {
            _hashCode += getTRAN_DATE_TIME().hashCode();
        }
        if (getEXECUTION_TYPE() != null) {
            _hashCode += getEXECUTION_TYPE().hashCode();
        }
        if (getBOOKING_MSG() != null) {
            _hashCode += getBOOKING_MSG().hashCode();
        }
        if (getTR_CODE() != null) {
            _hashCode += getTR_CODE().hashCode();
        }
        if (getTR_FEE() != null) {
            _hashCode += getTR_FEE().hashCode();
        }
        if (getTR_CODE2() != null) {
            _hashCode += getTR_CODE2().hashCode();
        }
        if (getTR_FEE2() != null) {
            _hashCode += getTR_FEE2().hashCode();
        }
        if (getTR_CODE3() != null) {
            _hashCode += getTR_CODE3().hashCode();
        }
        if (getTR_FEE3() != null) {
            _hashCode += getTR_FEE3().hashCode();
        }
        if (getTR_CODE4() != null) {
            _hashCode += getTR_CODE4().hashCode();
        }
        if (getTR_FEE4() != null) {
            _hashCode += getTR_FEE4().hashCode();
        }
        if (getTR_CODE5() != null) {
            _hashCode += getTR_CODE5().hashCode();
        }
        if (getTR_FEE5() != null) {
            _hashCode += getTR_FEE5().hashCode();
        }
        if (getTR_CODE6() != null) {
            _hashCode += getTR_CODE6().hashCode();
        }
        if (getTR_FEE6() != null) {
            _hashCode += getTR_FEE6().hashCode();
        }
        if (getTR_CODE7() != null) {
            _hashCode += getTR_CODE7().hashCode();
        }
        if (getTR_FEE7() != null) {
            _hashCode += getTR_FEE7().hashCode();
        }
        if (getTR_CODE8() != null) {
            _hashCode += getTR_CODE8().hashCode();
        }
        if (getTR_FEE8() != null) {
            _hashCode += getTR_FEE8().hashCode();
        }
        if (getTR_CODE9() != null) {
            _hashCode += getTR_CODE9().hashCode();
        }
        if (getTR_FEE9() != null) {
            _hashCode += getTR_FEE9().hashCode();
        }
        if (getTR_CODE10() != null) {
            _hashCode += getTR_CODE10().hashCode();
        }
        if (getTR_FEE10() != null) {
            _hashCode += getTR_FEE10().hashCode();
        }
        if (getCHECK_DUPL() != null) {
            _hashCode += getCHECK_DUPL().hashCode();
        }
        if (getINSTL_AGR_NO() != null) {
            _hashCode += getINSTL_AGR_NO().hashCode();
        }
        if (getACCNT_TYPE() != null) {
            _hashCode += getACCNT_TYPE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ExecTransaction_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ExecTransaction_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAYMENT_MODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAYMENT_MODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACCOUNT_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACCOUNT_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("CARD_ACCT_CCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_ACCT_CCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXECUTE_ON");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXECUTE_ON"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRAN_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRAN_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRAN_CCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRAN_CCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRAN_AMNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRAN_AMNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("BATCH_NR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BATCH_NR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SLIP_NR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SLIP_NR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEAL_DESC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEAL_DESC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COUNTERPARTY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COUNTERPARTY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTERNAL_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INTERNAL_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BANK_C");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BANK_C"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GROUPC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GROUPC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRAN_DATE_TIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRAN_DATE_TIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXECUTION_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXECUTION_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BOOKING_MSG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BOOKING_MSG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_FEE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_FEE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_CODE2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_CODE2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_FEE2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_FEE2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_CODE3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_CODE3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_FEE3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_FEE3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_CODE4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_CODE4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_FEE4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_FEE4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_CODE5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_CODE5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_FEE5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_FEE5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_CODE6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_CODE6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_FEE6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_FEE6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_CODE7");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_CODE7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_FEE7");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_FEE7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_CODE8");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_CODE8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_FEE8");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_FEE8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_CODE9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_CODE9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_FEE9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_FEE9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_CODE10");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_CODE10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_FEE10");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_FEE10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CHECK_DUPL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CHECK_DUPL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTL_AGR_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSTL_AGR_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACCNT_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACCNT_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
