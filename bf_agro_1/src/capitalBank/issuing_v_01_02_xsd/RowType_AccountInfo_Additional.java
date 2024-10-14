/**
 * RowType_AccountInfo_Additional.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package capitalBank.issuing_v_01_02_xsd;

public class RowType_AccountInfo_Additional  implements java.io.Serializable {
    private java.math.BigDecimal CARD_NUMB;

    private java.util.Calendar IC_DATE;

    private java.math.BigDecimal LOST_CARD;

    private java.util.Calendar POST_DATE;

    private java.math.BigDecimal BEGIN_BAL;

    private java.math.BigDecimal DEBIT;

    private java.math.BigDecimal CREDIT;

    private java.math.BigDecimal END_BAL;

    private java.math.BigDecimal AVAIL_AMT;

    private java.math.BigDecimal DEBT;

    private java.math.BigDecimal DEBT1;

    private java.util.Calendar PREV_DATE;

    private java.math.BigDecimal MBEGIN_BAL;

    private java.math.BigDecimal MDEBIT;

    private java.math.BigDecimal MCREDIT;

    private java.math.BigDecimal MEND_BAL;

    private java.math.BigDecimal MPREV_BAL;

    private java.math.BigDecimal BRUTTO;

    private java.math.BigDecimal USED_AMOUNT;

    private java.math.BigDecimal DBEGIN_BAL;

    private java.math.BigDecimal DDEBIT;

    private java.math.BigDecimal DCREDIT;

    private java.math.BigDecimal DEND_BAL;

    private java.util.Calendar DEP_CAP_DATE;

    private java.math.BigDecimal DEP_INT_CUR;

    private java.math.BigDecimal DEP_INT_LAST;

    private java.math.BigDecimal DEP_INT_TOT;

    private java.math.BigDecimal DEP_INT_GTOT;

    private java.math.BigDecimal DEP_CUR_TRNSF;

    private java.math.BigDecimal DEP_INT_TRNSF;

    private java.util.Calendar DEP_LPER_DATE;

    private java.math.BigDecimal REVERS_SUM;

    private java.math.BigDecimal END_BAL1;

    private java.math.BigDecimal PAYM_AMOUNT;

    private java.util.Calendar PAYM_DATE;

    private java.math.BigDecimal PAYM_INTERNAL_NO;

    private java.math.BigDecimal TA_OTB;

    private java.math.BigDecimal MPREV_DEBIT;

    private java.math.BigDecimal MPREV_CREDIT;

    private java.math.BigDecimal PROC_ID;

    private java.util.Calendar CRD_CHANGE_DATE;

    public RowType_AccountInfo_Additional() {
    }

    public RowType_AccountInfo_Additional(
           java.math.BigDecimal CARD_NUMB,
           java.util.Calendar IC_DATE,
           java.math.BigDecimal LOST_CARD,
           java.util.Calendar POST_DATE,
           java.math.BigDecimal BEGIN_BAL,
           java.math.BigDecimal DEBIT,
           java.math.BigDecimal CREDIT,
           java.math.BigDecimal END_BAL,
           java.math.BigDecimal AVAIL_AMT,
           java.math.BigDecimal DEBT,
           java.math.BigDecimal DEBT1,
           java.util.Calendar PREV_DATE,
           java.math.BigDecimal MBEGIN_BAL,
           java.math.BigDecimal MDEBIT,
           java.math.BigDecimal MCREDIT,
           java.math.BigDecimal MEND_BAL,
           java.math.BigDecimal MPREV_BAL,
           java.math.BigDecimal BRUTTO,
           java.math.BigDecimal USED_AMOUNT,
           java.math.BigDecimal DBEGIN_BAL,
           java.math.BigDecimal DDEBIT,
           java.math.BigDecimal DCREDIT,
           java.math.BigDecimal DEND_BAL,
           java.util.Calendar DEP_CAP_DATE,
           java.math.BigDecimal DEP_INT_CUR,
           java.math.BigDecimal DEP_INT_LAST,
           java.math.BigDecimal DEP_INT_TOT,
           java.math.BigDecimal DEP_INT_GTOT,
           java.math.BigDecimal DEP_CUR_TRNSF,
           java.math.BigDecimal DEP_INT_TRNSF,
           java.util.Calendar DEP_LPER_DATE,
           java.math.BigDecimal REVERS_SUM,
           java.math.BigDecimal END_BAL1,
           java.math.BigDecimal PAYM_AMOUNT,
           java.util.Calendar PAYM_DATE,
           java.math.BigDecimal PAYM_INTERNAL_NO,
           java.math.BigDecimal TA_OTB,
           java.math.BigDecimal MPREV_DEBIT,
           java.math.BigDecimal MPREV_CREDIT,
           java.math.BigDecimal PROC_ID,
           java.util.Calendar CRD_CHANGE_DATE) {
           this.CARD_NUMB = CARD_NUMB;
           this.IC_DATE = IC_DATE;
           this.LOST_CARD = LOST_CARD;
           this.POST_DATE = POST_DATE;
           this.BEGIN_BAL = BEGIN_BAL;
           this.DEBIT = DEBIT;
           this.CREDIT = CREDIT;
           this.END_BAL = END_BAL;
           this.AVAIL_AMT = AVAIL_AMT;
           this.DEBT = DEBT;
           this.DEBT1 = DEBT1;
           this.PREV_DATE = PREV_DATE;
           this.MBEGIN_BAL = MBEGIN_BAL;
           this.MDEBIT = MDEBIT;
           this.MCREDIT = MCREDIT;
           this.MEND_BAL = MEND_BAL;
           this.MPREV_BAL = MPREV_BAL;
           this.BRUTTO = BRUTTO;
           this.USED_AMOUNT = USED_AMOUNT;
           this.DBEGIN_BAL = DBEGIN_BAL;
           this.DDEBIT = DDEBIT;
           this.DCREDIT = DCREDIT;
           this.DEND_BAL = DEND_BAL;
           this.DEP_CAP_DATE = DEP_CAP_DATE;
           this.DEP_INT_CUR = DEP_INT_CUR;
           this.DEP_INT_LAST = DEP_INT_LAST;
           this.DEP_INT_TOT = DEP_INT_TOT;
           this.DEP_INT_GTOT = DEP_INT_GTOT;
           this.DEP_CUR_TRNSF = DEP_CUR_TRNSF;
           this.DEP_INT_TRNSF = DEP_INT_TRNSF;
           this.DEP_LPER_DATE = DEP_LPER_DATE;
           this.REVERS_SUM = REVERS_SUM;
           this.END_BAL1 = END_BAL1;
           this.PAYM_AMOUNT = PAYM_AMOUNT;
           this.PAYM_DATE = PAYM_DATE;
           this.PAYM_INTERNAL_NO = PAYM_INTERNAL_NO;
           this.TA_OTB = TA_OTB;
           this.MPREV_DEBIT = MPREV_DEBIT;
           this.MPREV_CREDIT = MPREV_CREDIT;
           this.PROC_ID = PROC_ID;
           this.CRD_CHANGE_DATE = CRD_CHANGE_DATE;
    }


    /**
     * Gets the CARD_NUMB value for this RowType_AccountInfo_Additional.
     * 
     * @return CARD_NUMB
     */
    public java.math.BigDecimal getCARD_NUMB() {
        return CARD_NUMB;
    }


    /**
     * Sets the CARD_NUMB value for this RowType_AccountInfo_Additional.
     * 
     * @param CARD_NUMB
     */
    public void setCARD_NUMB(java.math.BigDecimal CARD_NUMB) {
        this.CARD_NUMB = CARD_NUMB;
    }


    /**
     * Gets the IC_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @return IC_DATE
     */
    public java.util.Calendar getIC_DATE() {
        return IC_DATE;
    }


    /**
     * Sets the IC_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @param IC_DATE
     */
    public void setIC_DATE(java.util.Calendar IC_DATE) {
        this.IC_DATE = IC_DATE;
    }


    /**
     * Gets the LOST_CARD value for this RowType_AccountInfo_Additional.
     * 
     * @return LOST_CARD
     */
    public java.math.BigDecimal getLOST_CARD() {
        return LOST_CARD;
    }


    /**
     * Sets the LOST_CARD value for this RowType_AccountInfo_Additional.
     * 
     * @param LOST_CARD
     */
    public void setLOST_CARD(java.math.BigDecimal LOST_CARD) {
        this.LOST_CARD = LOST_CARD;
    }


    /**
     * Gets the POST_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @return POST_DATE
     */
    public java.util.Calendar getPOST_DATE() {
        return POST_DATE;
    }


    /**
     * Sets the POST_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @param POST_DATE
     */
    public void setPOST_DATE(java.util.Calendar POST_DATE) {
        this.POST_DATE = POST_DATE;
    }


    /**
     * Gets the BEGIN_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @return BEGIN_BAL
     */
    public java.math.BigDecimal getBEGIN_BAL() {
        return BEGIN_BAL;
    }


    /**
     * Sets the BEGIN_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @param BEGIN_BAL
     */
    public void setBEGIN_BAL(java.math.BigDecimal BEGIN_BAL) {
        this.BEGIN_BAL = BEGIN_BAL;
    }


    /**
     * Gets the DEBIT value for this RowType_AccountInfo_Additional.
     * 
     * @return DEBIT
     */
    public java.math.BigDecimal getDEBIT() {
        return DEBIT;
    }


    /**
     * Sets the DEBIT value for this RowType_AccountInfo_Additional.
     * 
     * @param DEBIT
     */
    public void setDEBIT(java.math.BigDecimal DEBIT) {
        this.DEBIT = DEBIT;
    }


    /**
     * Gets the CREDIT value for this RowType_AccountInfo_Additional.
     * 
     * @return CREDIT
     */
    public java.math.BigDecimal getCREDIT() {
        return CREDIT;
    }


    /**
     * Sets the CREDIT value for this RowType_AccountInfo_Additional.
     * 
     * @param CREDIT
     */
    public void setCREDIT(java.math.BigDecimal CREDIT) {
        this.CREDIT = CREDIT;
    }


    /**
     * Gets the END_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @return END_BAL
     */
    public java.math.BigDecimal getEND_BAL() {
        return END_BAL;
    }


    /**
     * Sets the END_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @param END_BAL
     */
    public void setEND_BAL(java.math.BigDecimal END_BAL) {
        this.END_BAL = END_BAL;
    }


    /**
     * Gets the AVAIL_AMT value for this RowType_AccountInfo_Additional.
     * 
     * @return AVAIL_AMT
     */
    public java.math.BigDecimal getAVAIL_AMT() {
        return AVAIL_AMT;
    }


    /**
     * Sets the AVAIL_AMT value for this RowType_AccountInfo_Additional.
     * 
     * @param AVAIL_AMT
     */
    public void setAVAIL_AMT(java.math.BigDecimal AVAIL_AMT) {
        this.AVAIL_AMT = AVAIL_AMT;
    }


    /**
     * Gets the DEBT value for this RowType_AccountInfo_Additional.
     * 
     * @return DEBT
     */
    public java.math.BigDecimal getDEBT() {
        return DEBT;
    }


    /**
     * Sets the DEBT value for this RowType_AccountInfo_Additional.
     * 
     * @param DEBT
     */
    public void setDEBT(java.math.BigDecimal DEBT) {
        this.DEBT = DEBT;
    }


    /**
     * Gets the DEBT1 value for this RowType_AccountInfo_Additional.
     * 
     * @return DEBT1
     */
    public java.math.BigDecimal getDEBT1() {
        return DEBT1;
    }


    /**
     * Sets the DEBT1 value for this RowType_AccountInfo_Additional.
     * 
     * @param DEBT1
     */
    public void setDEBT1(java.math.BigDecimal DEBT1) {
        this.DEBT1 = DEBT1;
    }


    /**
     * Gets the PREV_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @return PREV_DATE
     */
    public java.util.Calendar getPREV_DATE() {
        return PREV_DATE;
    }


    /**
     * Sets the PREV_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @param PREV_DATE
     */
    public void setPREV_DATE(java.util.Calendar PREV_DATE) {
        this.PREV_DATE = PREV_DATE;
    }


    /**
     * Gets the MBEGIN_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @return MBEGIN_BAL
     */
    public java.math.BigDecimal getMBEGIN_BAL() {
        return MBEGIN_BAL;
    }


    /**
     * Sets the MBEGIN_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @param MBEGIN_BAL
     */
    public void setMBEGIN_BAL(java.math.BigDecimal MBEGIN_BAL) {
        this.MBEGIN_BAL = MBEGIN_BAL;
    }


    /**
     * Gets the MDEBIT value for this RowType_AccountInfo_Additional.
     * 
     * @return MDEBIT
     */
    public java.math.BigDecimal getMDEBIT() {
        return MDEBIT;
    }


    /**
     * Sets the MDEBIT value for this RowType_AccountInfo_Additional.
     * 
     * @param MDEBIT
     */
    public void setMDEBIT(java.math.BigDecimal MDEBIT) {
        this.MDEBIT = MDEBIT;
    }


    /**
     * Gets the MCREDIT value for this RowType_AccountInfo_Additional.
     * 
     * @return MCREDIT
     */
    public java.math.BigDecimal getMCREDIT() {
        return MCREDIT;
    }


    /**
     * Sets the MCREDIT value for this RowType_AccountInfo_Additional.
     * 
     * @param MCREDIT
     */
    public void setMCREDIT(java.math.BigDecimal MCREDIT) {
        this.MCREDIT = MCREDIT;
    }


    /**
     * Gets the MEND_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @return MEND_BAL
     */
    public java.math.BigDecimal getMEND_BAL() {
        return MEND_BAL;
    }


    /**
     * Sets the MEND_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @param MEND_BAL
     */
    public void setMEND_BAL(java.math.BigDecimal MEND_BAL) {
        this.MEND_BAL = MEND_BAL;
    }


    /**
     * Gets the MPREV_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @return MPREV_BAL
     */
    public java.math.BigDecimal getMPREV_BAL() {
        return MPREV_BAL;
    }


    /**
     * Sets the MPREV_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @param MPREV_BAL
     */
    public void setMPREV_BAL(java.math.BigDecimal MPREV_BAL) {
        this.MPREV_BAL = MPREV_BAL;
    }


    /**
     * Gets the BRUTTO value for this RowType_AccountInfo_Additional.
     * 
     * @return BRUTTO
     */
    public java.math.BigDecimal getBRUTTO() {
        return BRUTTO;
    }


    /**
     * Sets the BRUTTO value for this RowType_AccountInfo_Additional.
     * 
     * @param BRUTTO
     */
    public void setBRUTTO(java.math.BigDecimal BRUTTO) {
        this.BRUTTO = BRUTTO;
    }


    /**
     * Gets the USED_AMOUNT value for this RowType_AccountInfo_Additional.
     * 
     * @return USED_AMOUNT
     */
    public java.math.BigDecimal getUSED_AMOUNT() {
        return USED_AMOUNT;
    }


    /**
     * Sets the USED_AMOUNT value for this RowType_AccountInfo_Additional.
     * 
     * @param USED_AMOUNT
     */
    public void setUSED_AMOUNT(java.math.BigDecimal USED_AMOUNT) {
        this.USED_AMOUNT = USED_AMOUNT;
    }


    /**
     * Gets the DBEGIN_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @return DBEGIN_BAL
     */
    public java.math.BigDecimal getDBEGIN_BAL() {
        return DBEGIN_BAL;
    }


    /**
     * Sets the DBEGIN_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @param DBEGIN_BAL
     */
    public void setDBEGIN_BAL(java.math.BigDecimal DBEGIN_BAL) {
        this.DBEGIN_BAL = DBEGIN_BAL;
    }


    /**
     * Gets the DDEBIT value for this RowType_AccountInfo_Additional.
     * 
     * @return DDEBIT
     */
    public java.math.BigDecimal getDDEBIT() {
        return DDEBIT;
    }


    /**
     * Sets the DDEBIT value for this RowType_AccountInfo_Additional.
     * 
     * @param DDEBIT
     */
    public void setDDEBIT(java.math.BigDecimal DDEBIT) {
        this.DDEBIT = DDEBIT;
    }


    /**
     * Gets the DCREDIT value for this RowType_AccountInfo_Additional.
     * 
     * @return DCREDIT
     */
    public java.math.BigDecimal getDCREDIT() {
        return DCREDIT;
    }


    /**
     * Sets the DCREDIT value for this RowType_AccountInfo_Additional.
     * 
     * @param DCREDIT
     */
    public void setDCREDIT(java.math.BigDecimal DCREDIT) {
        this.DCREDIT = DCREDIT;
    }


    /**
     * Gets the DEND_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @return DEND_BAL
     */
    public java.math.BigDecimal getDEND_BAL() {
        return DEND_BAL;
    }


    /**
     * Sets the DEND_BAL value for this RowType_AccountInfo_Additional.
     * 
     * @param DEND_BAL
     */
    public void setDEND_BAL(java.math.BigDecimal DEND_BAL) {
        this.DEND_BAL = DEND_BAL;
    }


    /**
     * Gets the DEP_CAP_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @return DEP_CAP_DATE
     */
    public java.util.Calendar getDEP_CAP_DATE() {
        return DEP_CAP_DATE;
    }


    /**
     * Sets the DEP_CAP_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @param DEP_CAP_DATE
     */
    public void setDEP_CAP_DATE(java.util.Calendar DEP_CAP_DATE) {
        this.DEP_CAP_DATE = DEP_CAP_DATE;
    }


    /**
     * Gets the DEP_INT_CUR value for this RowType_AccountInfo_Additional.
     * 
     * @return DEP_INT_CUR
     */
    public java.math.BigDecimal getDEP_INT_CUR() {
        return DEP_INT_CUR;
    }


    /**
     * Sets the DEP_INT_CUR value for this RowType_AccountInfo_Additional.
     * 
     * @param DEP_INT_CUR
     */
    public void setDEP_INT_CUR(java.math.BigDecimal DEP_INT_CUR) {
        this.DEP_INT_CUR = DEP_INT_CUR;
    }


    /**
     * Gets the DEP_INT_LAST value for this RowType_AccountInfo_Additional.
     * 
     * @return DEP_INT_LAST
     */
    public java.math.BigDecimal getDEP_INT_LAST() {
        return DEP_INT_LAST;
    }


    /**
     * Sets the DEP_INT_LAST value for this RowType_AccountInfo_Additional.
     * 
     * @param DEP_INT_LAST
     */
    public void setDEP_INT_LAST(java.math.BigDecimal DEP_INT_LAST) {
        this.DEP_INT_LAST = DEP_INT_LAST;
    }


    /**
     * Gets the DEP_INT_TOT value for this RowType_AccountInfo_Additional.
     * 
     * @return DEP_INT_TOT
     */
    public java.math.BigDecimal getDEP_INT_TOT() {
        return DEP_INT_TOT;
    }


    /**
     * Sets the DEP_INT_TOT value for this RowType_AccountInfo_Additional.
     * 
     * @param DEP_INT_TOT
     */
    public void setDEP_INT_TOT(java.math.BigDecimal DEP_INT_TOT) {
        this.DEP_INT_TOT = DEP_INT_TOT;
    }


    /**
     * Gets the DEP_INT_GTOT value for this RowType_AccountInfo_Additional.
     * 
     * @return DEP_INT_GTOT
     */
    public java.math.BigDecimal getDEP_INT_GTOT() {
        return DEP_INT_GTOT;
    }


    /**
     * Sets the DEP_INT_GTOT value for this RowType_AccountInfo_Additional.
     * 
     * @param DEP_INT_GTOT
     */
    public void setDEP_INT_GTOT(java.math.BigDecimal DEP_INT_GTOT) {
        this.DEP_INT_GTOT = DEP_INT_GTOT;
    }


    /**
     * Gets the DEP_CUR_TRNSF value for this RowType_AccountInfo_Additional.
     * 
     * @return DEP_CUR_TRNSF
     */
    public java.math.BigDecimal getDEP_CUR_TRNSF() {
        return DEP_CUR_TRNSF;
    }


    /**
     * Sets the DEP_CUR_TRNSF value for this RowType_AccountInfo_Additional.
     * 
     * @param DEP_CUR_TRNSF
     */
    public void setDEP_CUR_TRNSF(java.math.BigDecimal DEP_CUR_TRNSF) {
        this.DEP_CUR_TRNSF = DEP_CUR_TRNSF;
    }


    /**
     * Gets the DEP_INT_TRNSF value for this RowType_AccountInfo_Additional.
     * 
     * @return DEP_INT_TRNSF
     */
    public java.math.BigDecimal getDEP_INT_TRNSF() {
        return DEP_INT_TRNSF;
    }


    /**
     * Sets the DEP_INT_TRNSF value for this RowType_AccountInfo_Additional.
     * 
     * @param DEP_INT_TRNSF
     */
    public void setDEP_INT_TRNSF(java.math.BigDecimal DEP_INT_TRNSF) {
        this.DEP_INT_TRNSF = DEP_INT_TRNSF;
    }


    /**
     * Gets the DEP_LPER_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @return DEP_LPER_DATE
     */
    public java.util.Calendar getDEP_LPER_DATE() {
        return DEP_LPER_DATE;
    }


    /**
     * Sets the DEP_LPER_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @param DEP_LPER_DATE
     */
    public void setDEP_LPER_DATE(java.util.Calendar DEP_LPER_DATE) {
        this.DEP_LPER_DATE = DEP_LPER_DATE;
    }


    /**
     * Gets the REVERS_SUM value for this RowType_AccountInfo_Additional.
     * 
     * @return REVERS_SUM
     */
    public java.math.BigDecimal getREVERS_SUM() {
        return REVERS_SUM;
    }


    /**
     * Sets the REVERS_SUM value for this RowType_AccountInfo_Additional.
     * 
     * @param REVERS_SUM
     */
    public void setREVERS_SUM(java.math.BigDecimal REVERS_SUM) {
        this.REVERS_SUM = REVERS_SUM;
    }


    /**
     * Gets the END_BAL1 value for this RowType_AccountInfo_Additional.
     * 
     * @return END_BAL1
     */
    public java.math.BigDecimal getEND_BAL1() {
        return END_BAL1;
    }


    /**
     * Sets the END_BAL1 value for this RowType_AccountInfo_Additional.
     * 
     * @param END_BAL1
     */
    public void setEND_BAL1(java.math.BigDecimal END_BAL1) {
        this.END_BAL1 = END_BAL1;
    }


    /**
     * Gets the PAYM_AMOUNT value for this RowType_AccountInfo_Additional.
     * 
     * @return PAYM_AMOUNT
     */
    public java.math.BigDecimal getPAYM_AMOUNT() {
        return PAYM_AMOUNT;
    }


    /**
     * Sets the PAYM_AMOUNT value for this RowType_AccountInfo_Additional.
     * 
     * @param PAYM_AMOUNT
     */
    public void setPAYM_AMOUNT(java.math.BigDecimal PAYM_AMOUNT) {
        this.PAYM_AMOUNT = PAYM_AMOUNT;
    }


    /**
     * Gets the PAYM_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @return PAYM_DATE
     */
    public java.util.Calendar getPAYM_DATE() {
        return PAYM_DATE;
    }


    /**
     * Sets the PAYM_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @param PAYM_DATE
     */
    public void setPAYM_DATE(java.util.Calendar PAYM_DATE) {
        this.PAYM_DATE = PAYM_DATE;
    }


    /**
     * Gets the PAYM_INTERNAL_NO value for this RowType_AccountInfo_Additional.
     * 
     * @return PAYM_INTERNAL_NO
     */
    public java.math.BigDecimal getPAYM_INTERNAL_NO() {
        return PAYM_INTERNAL_NO;
    }


    /**
     * Sets the PAYM_INTERNAL_NO value for this RowType_AccountInfo_Additional.
     * 
     * @param PAYM_INTERNAL_NO
     */
    public void setPAYM_INTERNAL_NO(java.math.BigDecimal PAYM_INTERNAL_NO) {
        this.PAYM_INTERNAL_NO = PAYM_INTERNAL_NO;
    }


    /**
     * Gets the TA_OTB value for this RowType_AccountInfo_Additional.
     * 
     * @return TA_OTB
     */
    public java.math.BigDecimal getTA_OTB() {
        return TA_OTB;
    }


    /**
     * Sets the TA_OTB value for this RowType_AccountInfo_Additional.
     * 
     * @param TA_OTB
     */
    public void setTA_OTB(java.math.BigDecimal TA_OTB) {
        this.TA_OTB = TA_OTB;
    }


    /**
     * Gets the MPREV_DEBIT value for this RowType_AccountInfo_Additional.
     * 
     * @return MPREV_DEBIT
     */
    public java.math.BigDecimal getMPREV_DEBIT() {
        return MPREV_DEBIT;
    }


    /**
     * Sets the MPREV_DEBIT value for this RowType_AccountInfo_Additional.
     * 
     * @param MPREV_DEBIT
     */
    public void setMPREV_DEBIT(java.math.BigDecimal MPREV_DEBIT) {
        this.MPREV_DEBIT = MPREV_DEBIT;
    }


    /**
     * Gets the MPREV_CREDIT value for this RowType_AccountInfo_Additional.
     * 
     * @return MPREV_CREDIT
     */
    public java.math.BigDecimal getMPREV_CREDIT() {
        return MPREV_CREDIT;
    }


    /**
     * Sets the MPREV_CREDIT value for this RowType_AccountInfo_Additional.
     * 
     * @param MPREV_CREDIT
     */
    public void setMPREV_CREDIT(java.math.BigDecimal MPREV_CREDIT) {
        this.MPREV_CREDIT = MPREV_CREDIT;
    }


    /**
     * Gets the PROC_ID value for this RowType_AccountInfo_Additional.
     * 
     * @return PROC_ID
     */
    public java.math.BigDecimal getPROC_ID() {
        return PROC_ID;
    }


    /**
     * Sets the PROC_ID value for this RowType_AccountInfo_Additional.
     * 
     * @param PROC_ID
     */
    public void setPROC_ID(java.math.BigDecimal PROC_ID) {
        this.PROC_ID = PROC_ID;
    }


    /**
     * Gets the CRD_CHANGE_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @return CRD_CHANGE_DATE
     */
    public java.util.Calendar getCRD_CHANGE_DATE() {
        return CRD_CHANGE_DATE;
    }


    /**
     * Sets the CRD_CHANGE_DATE value for this RowType_AccountInfo_Additional.
     * 
     * @param CRD_CHANGE_DATE
     */
    public void setCRD_CHANGE_DATE(java.util.Calendar CRD_CHANGE_DATE) {
        this.CRD_CHANGE_DATE = CRD_CHANGE_DATE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_AccountInfo_Additional)) return false;
        RowType_AccountInfo_Additional other = (RowType_AccountInfo_Additional) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CARD_NUMB==null && other.getCARD_NUMB()==null) || 
             (this.CARD_NUMB!=null &&
              this.CARD_NUMB.equals(other.getCARD_NUMB()))) &&
            ((this.IC_DATE==null && other.getIC_DATE()==null) || 
             (this.IC_DATE!=null &&
              this.IC_DATE.equals(other.getIC_DATE()))) &&
            ((this.LOST_CARD==null && other.getLOST_CARD()==null) || 
             (this.LOST_CARD!=null &&
              this.LOST_CARD.equals(other.getLOST_CARD()))) &&
            ((this.POST_DATE==null && other.getPOST_DATE()==null) || 
             (this.POST_DATE!=null &&
              this.POST_DATE.equals(other.getPOST_DATE()))) &&
            ((this.BEGIN_BAL==null && other.getBEGIN_BAL()==null) || 
             (this.BEGIN_BAL!=null &&
              this.BEGIN_BAL.equals(other.getBEGIN_BAL()))) &&
            ((this.DEBIT==null && other.getDEBIT()==null) || 
             (this.DEBIT!=null &&
              this.DEBIT.equals(other.getDEBIT()))) &&
            ((this.CREDIT==null && other.getCREDIT()==null) || 
             (this.CREDIT!=null &&
              this.CREDIT.equals(other.getCREDIT()))) &&
            ((this.END_BAL==null && other.getEND_BAL()==null) || 
             (this.END_BAL!=null &&
              this.END_BAL.equals(other.getEND_BAL()))) &&
            ((this.AVAIL_AMT==null && other.getAVAIL_AMT()==null) || 
             (this.AVAIL_AMT!=null &&
              this.AVAIL_AMT.equals(other.getAVAIL_AMT()))) &&
            ((this.DEBT==null && other.getDEBT()==null) || 
             (this.DEBT!=null &&
              this.DEBT.equals(other.getDEBT()))) &&
            ((this.DEBT1==null && other.getDEBT1()==null) || 
             (this.DEBT1!=null &&
              this.DEBT1.equals(other.getDEBT1()))) &&
            ((this.PREV_DATE==null && other.getPREV_DATE()==null) || 
             (this.PREV_DATE!=null &&
              this.PREV_DATE.equals(other.getPREV_DATE()))) &&
            ((this.MBEGIN_BAL==null && other.getMBEGIN_BAL()==null) || 
             (this.MBEGIN_BAL!=null &&
              this.MBEGIN_BAL.equals(other.getMBEGIN_BAL()))) &&
            ((this.MDEBIT==null && other.getMDEBIT()==null) || 
             (this.MDEBIT!=null &&
              this.MDEBIT.equals(other.getMDEBIT()))) &&
            ((this.MCREDIT==null && other.getMCREDIT()==null) || 
             (this.MCREDIT!=null &&
              this.MCREDIT.equals(other.getMCREDIT()))) &&
            ((this.MEND_BAL==null && other.getMEND_BAL()==null) || 
             (this.MEND_BAL!=null &&
              this.MEND_BAL.equals(other.getMEND_BAL()))) &&
            ((this.MPREV_BAL==null && other.getMPREV_BAL()==null) || 
             (this.MPREV_BAL!=null &&
              this.MPREV_BAL.equals(other.getMPREV_BAL()))) &&
            ((this.BRUTTO==null && other.getBRUTTO()==null) || 
             (this.BRUTTO!=null &&
              this.BRUTTO.equals(other.getBRUTTO()))) &&
            ((this.USED_AMOUNT==null && other.getUSED_AMOUNT()==null) || 
             (this.USED_AMOUNT!=null &&
              this.USED_AMOUNT.equals(other.getUSED_AMOUNT()))) &&
            ((this.DBEGIN_BAL==null && other.getDBEGIN_BAL()==null) || 
             (this.DBEGIN_BAL!=null &&
              this.DBEGIN_BAL.equals(other.getDBEGIN_BAL()))) &&
            ((this.DDEBIT==null && other.getDDEBIT()==null) || 
             (this.DDEBIT!=null &&
              this.DDEBIT.equals(other.getDDEBIT()))) &&
            ((this.DCREDIT==null && other.getDCREDIT()==null) || 
             (this.DCREDIT!=null &&
              this.DCREDIT.equals(other.getDCREDIT()))) &&
            ((this.DEND_BAL==null && other.getDEND_BAL()==null) || 
             (this.DEND_BAL!=null &&
              this.DEND_BAL.equals(other.getDEND_BAL()))) &&
            ((this.DEP_CAP_DATE==null && other.getDEP_CAP_DATE()==null) || 
             (this.DEP_CAP_DATE!=null &&
              this.DEP_CAP_DATE.equals(other.getDEP_CAP_DATE()))) &&
            ((this.DEP_INT_CUR==null && other.getDEP_INT_CUR()==null) || 
             (this.DEP_INT_CUR!=null &&
              this.DEP_INT_CUR.equals(other.getDEP_INT_CUR()))) &&
            ((this.DEP_INT_LAST==null && other.getDEP_INT_LAST()==null) || 
             (this.DEP_INT_LAST!=null &&
              this.DEP_INT_LAST.equals(other.getDEP_INT_LAST()))) &&
            ((this.DEP_INT_TOT==null && other.getDEP_INT_TOT()==null) || 
             (this.DEP_INT_TOT!=null &&
              this.DEP_INT_TOT.equals(other.getDEP_INT_TOT()))) &&
            ((this.DEP_INT_GTOT==null && other.getDEP_INT_GTOT()==null) || 
             (this.DEP_INT_GTOT!=null &&
              this.DEP_INT_GTOT.equals(other.getDEP_INT_GTOT()))) &&
            ((this.DEP_CUR_TRNSF==null && other.getDEP_CUR_TRNSF()==null) || 
             (this.DEP_CUR_TRNSF!=null &&
              this.DEP_CUR_TRNSF.equals(other.getDEP_CUR_TRNSF()))) &&
            ((this.DEP_INT_TRNSF==null && other.getDEP_INT_TRNSF()==null) || 
             (this.DEP_INT_TRNSF!=null &&
              this.DEP_INT_TRNSF.equals(other.getDEP_INT_TRNSF()))) &&
            ((this.DEP_LPER_DATE==null && other.getDEP_LPER_DATE()==null) || 
             (this.DEP_LPER_DATE!=null &&
              this.DEP_LPER_DATE.equals(other.getDEP_LPER_DATE()))) &&
            ((this.REVERS_SUM==null && other.getREVERS_SUM()==null) || 
             (this.REVERS_SUM!=null &&
              this.REVERS_SUM.equals(other.getREVERS_SUM()))) &&
            ((this.END_BAL1==null && other.getEND_BAL1()==null) || 
             (this.END_BAL1!=null &&
              this.END_BAL1.equals(other.getEND_BAL1()))) &&
            ((this.PAYM_AMOUNT==null && other.getPAYM_AMOUNT()==null) || 
             (this.PAYM_AMOUNT!=null &&
              this.PAYM_AMOUNT.equals(other.getPAYM_AMOUNT()))) &&
            ((this.PAYM_DATE==null && other.getPAYM_DATE()==null) || 
             (this.PAYM_DATE!=null &&
              this.PAYM_DATE.equals(other.getPAYM_DATE()))) &&
            ((this.PAYM_INTERNAL_NO==null && other.getPAYM_INTERNAL_NO()==null) || 
             (this.PAYM_INTERNAL_NO!=null &&
              this.PAYM_INTERNAL_NO.equals(other.getPAYM_INTERNAL_NO()))) &&
            ((this.TA_OTB==null && other.getTA_OTB()==null) || 
             (this.TA_OTB!=null &&
              this.TA_OTB.equals(other.getTA_OTB()))) &&
            ((this.MPREV_DEBIT==null && other.getMPREV_DEBIT()==null) || 
             (this.MPREV_DEBIT!=null &&
              this.MPREV_DEBIT.equals(other.getMPREV_DEBIT()))) &&
            ((this.MPREV_CREDIT==null && other.getMPREV_CREDIT()==null) || 
             (this.MPREV_CREDIT!=null &&
              this.MPREV_CREDIT.equals(other.getMPREV_CREDIT()))) &&
            ((this.PROC_ID==null && other.getPROC_ID()==null) || 
             (this.PROC_ID!=null &&
              this.PROC_ID.equals(other.getPROC_ID()))) &&
            ((this.CRD_CHANGE_DATE==null && other.getCRD_CHANGE_DATE()==null) || 
             (this.CRD_CHANGE_DATE!=null &&
              this.CRD_CHANGE_DATE.equals(other.getCRD_CHANGE_DATE())));
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
        if (getCARD_NUMB() != null) {
            _hashCode += getCARD_NUMB().hashCode();
        }
        if (getIC_DATE() != null) {
            _hashCode += getIC_DATE().hashCode();
        }
        if (getLOST_CARD() != null) {
            _hashCode += getLOST_CARD().hashCode();
        }
        if (getPOST_DATE() != null) {
            _hashCode += getPOST_DATE().hashCode();
        }
        if (getBEGIN_BAL() != null) {
            _hashCode += getBEGIN_BAL().hashCode();
        }
        if (getDEBIT() != null) {
            _hashCode += getDEBIT().hashCode();
        }
        if (getCREDIT() != null) {
            _hashCode += getCREDIT().hashCode();
        }
        if (getEND_BAL() != null) {
            _hashCode += getEND_BAL().hashCode();
        }
        if (getAVAIL_AMT() != null) {
            _hashCode += getAVAIL_AMT().hashCode();
        }
        if (getDEBT() != null) {
            _hashCode += getDEBT().hashCode();
        }
        if (getDEBT1() != null) {
            _hashCode += getDEBT1().hashCode();
        }
        if (getPREV_DATE() != null) {
            _hashCode += getPREV_DATE().hashCode();
        }
        if (getMBEGIN_BAL() != null) {
            _hashCode += getMBEGIN_BAL().hashCode();
        }
        if (getMDEBIT() != null) {
            _hashCode += getMDEBIT().hashCode();
        }
        if (getMCREDIT() != null) {
            _hashCode += getMCREDIT().hashCode();
        }
        if (getMEND_BAL() != null) {
            _hashCode += getMEND_BAL().hashCode();
        }
        if (getMPREV_BAL() != null) {
            _hashCode += getMPREV_BAL().hashCode();
        }
        if (getBRUTTO() != null) {
            _hashCode += getBRUTTO().hashCode();
        }
        if (getUSED_AMOUNT() != null) {
            _hashCode += getUSED_AMOUNT().hashCode();
        }
        if (getDBEGIN_BAL() != null) {
            _hashCode += getDBEGIN_BAL().hashCode();
        }
        if (getDDEBIT() != null) {
            _hashCode += getDDEBIT().hashCode();
        }
        if (getDCREDIT() != null) {
            _hashCode += getDCREDIT().hashCode();
        }
        if (getDEND_BAL() != null) {
            _hashCode += getDEND_BAL().hashCode();
        }
        if (getDEP_CAP_DATE() != null) {
            _hashCode += getDEP_CAP_DATE().hashCode();
        }
        if (getDEP_INT_CUR() != null) {
            _hashCode += getDEP_INT_CUR().hashCode();
        }
        if (getDEP_INT_LAST() != null) {
            _hashCode += getDEP_INT_LAST().hashCode();
        }
        if (getDEP_INT_TOT() != null) {
            _hashCode += getDEP_INT_TOT().hashCode();
        }
        if (getDEP_INT_GTOT() != null) {
            _hashCode += getDEP_INT_GTOT().hashCode();
        }
        if (getDEP_CUR_TRNSF() != null) {
            _hashCode += getDEP_CUR_TRNSF().hashCode();
        }
        if (getDEP_INT_TRNSF() != null) {
            _hashCode += getDEP_INT_TRNSF().hashCode();
        }
        if (getDEP_LPER_DATE() != null) {
            _hashCode += getDEP_LPER_DATE().hashCode();
        }
        if (getREVERS_SUM() != null) {
            _hashCode += getREVERS_SUM().hashCode();
        }
        if (getEND_BAL1() != null) {
            _hashCode += getEND_BAL1().hashCode();
        }
        if (getPAYM_AMOUNT() != null) {
            _hashCode += getPAYM_AMOUNT().hashCode();
        }
        if (getPAYM_DATE() != null) {
            _hashCode += getPAYM_DATE().hashCode();
        }
        if (getPAYM_INTERNAL_NO() != null) {
            _hashCode += getPAYM_INTERNAL_NO().hashCode();
        }
        if (getTA_OTB() != null) {
            _hashCode += getTA_OTB().hashCode();
        }
        if (getMPREV_DEBIT() != null) {
            _hashCode += getMPREV_DEBIT().hashCode();
        }
        if (getMPREV_CREDIT() != null) {
            _hashCode += getMPREV_CREDIT().hashCode();
        }
        if (getPROC_ID() != null) {
            _hashCode += getPROC_ID().hashCode();
        }
        if (getCRD_CHANGE_DATE() != null) {
            _hashCode += getCRD_CHANGE_DATE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_AccountInfo_Additional.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AccountInfo_Additional"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_NUMB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_NUMB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IC_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IC_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOST_CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOST_CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POST_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "POST_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BEGIN_BAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BEGIN_BAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEBIT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEBIT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CREDIT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CREDIT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("END_BAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "END_BAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AVAIL_AMT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AVAIL_AMT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEBT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEBT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEBT1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEBT1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PREV_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PREV_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MBEGIN_BAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MBEGIN_BAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MDEBIT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MDEBIT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MCREDIT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MCREDIT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MEND_BAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MEND_BAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MPREV_BAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MPREV_BAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BRUTTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BRUTTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USED_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "USED_AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DBEGIN_BAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DBEGIN_BAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DDEBIT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DDEBIT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCREDIT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DCREDIT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEND_BAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEND_BAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_CAP_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_CAP_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_INT_CUR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_INT_CUR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_INT_LAST");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_INT_LAST"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_INT_TOT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_INT_TOT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_INT_GTOT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_INT_GTOT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_CUR_TRNSF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_CUR_TRNSF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_INT_TRNSF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_INT_TRNSF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEP_LPER_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEP_LPER_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REVERS_SUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REVERS_SUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("END_BAL1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "END_BAL1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAYM_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAYM_AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAYM_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAYM_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAYM_INTERNAL_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAYM_INTERNAL_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TA_OTB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TA_OTB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MPREV_DEBIT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MPREV_DEBIT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MPREV_CREDIT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MPREV_CREDIT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PROC_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PROC_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CRD_CHANGE_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CRD_CHANGE_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
