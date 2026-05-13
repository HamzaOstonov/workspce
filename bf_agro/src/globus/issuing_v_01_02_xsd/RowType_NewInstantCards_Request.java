/**
 * RowType_NewInstantCards_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_NewInstantCards_Request  implements java.io.Serializable {
    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    private java.lang.String g_PROFILE_CODE;

    private java.lang.String p_PROFILE_CODE;

    private java.math.BigDecimal COUNT;

    private java.math.BigDecimal AGR_COUNT;

    private java.lang.String PRODUCT_CODE;

    private java.lang.String BRANCH;

    private java.lang.String OFFICE;

    private java.lang.String BIN;

    private java.lang.String CCY;

    private java.lang.String a_COND_SET;

    private java.lang.String c_COND_SET;

    private java.lang.String CL_CATEGORY;

    private java.lang.String CARD_FNAME;

    private java.lang.String STREET;

    private java.lang.String CITY;

    private java.lang.String OBJ_TREE_TYPE;

    private java.lang.String CLIENT_NUM_ALGO;

    private java.lang.String ACCOUNT_NUM_ALGO;

    private java.lang.String CARD_NUM_ALGO;

    private java.math.BigDecimal BIN_RANGE;

    private java.lang.String WITH_MONEY_FLAG;

    private java.math.BigDecimal AMOUNT;

    private java.lang.String TR_TYPE;

    private java.math.BigDecimal EXECUTION_TYPE;

    private java.lang.String LOCKED_FLAG;

    public RowType_NewInstantCards_Request() {
    }

    public RowType_NewInstantCards_Request(
           java.lang.String BANK_C,
           java.lang.String GROUPC,
           java.lang.String g_PROFILE_CODE,
           java.lang.String p_PROFILE_CODE,
           java.math.BigDecimal COUNT,
           java.math.BigDecimal AGR_COUNT,
           java.lang.String PRODUCT_CODE,
           java.lang.String BRANCH,
           java.lang.String OFFICE,
           java.lang.String BIN,
           java.lang.String CCY,
           java.lang.String a_COND_SET,
           java.lang.String c_COND_SET,
           java.lang.String CL_CATEGORY,
           java.lang.String CARD_FNAME,
           java.lang.String STREET,
           java.lang.String CITY,
           java.lang.String OBJ_TREE_TYPE,
           java.lang.String CLIENT_NUM_ALGO,
           java.lang.String ACCOUNT_NUM_ALGO,
           java.lang.String CARD_NUM_ALGO,
           java.math.BigDecimal BIN_RANGE,
           java.lang.String WITH_MONEY_FLAG,
           java.math.BigDecimal AMOUNT,
           java.lang.String TR_TYPE,
           java.math.BigDecimal EXECUTION_TYPE,
           java.lang.String LOCKED_FLAG) {
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
           this.g_PROFILE_CODE = g_PROFILE_CODE;
           this.p_PROFILE_CODE = p_PROFILE_CODE;
           this.COUNT = COUNT;
           this.AGR_COUNT = AGR_COUNT;
           this.PRODUCT_CODE = PRODUCT_CODE;
           this.BRANCH = BRANCH;
           this.OFFICE = OFFICE;
           this.BIN = BIN;
           this.CCY = CCY;
           this.a_COND_SET = a_COND_SET;
           this.c_COND_SET = c_COND_SET;
           this.CL_CATEGORY = CL_CATEGORY;
           this.CARD_FNAME = CARD_FNAME;
           this.STREET = STREET;
           this.CITY = CITY;
           this.OBJ_TREE_TYPE = OBJ_TREE_TYPE;
           this.CLIENT_NUM_ALGO = CLIENT_NUM_ALGO;
           this.ACCOUNT_NUM_ALGO = ACCOUNT_NUM_ALGO;
           this.CARD_NUM_ALGO = CARD_NUM_ALGO;
           this.BIN_RANGE = BIN_RANGE;
           this.WITH_MONEY_FLAG = WITH_MONEY_FLAG;
           this.AMOUNT = AMOUNT;
           this.TR_TYPE = TR_TYPE;
           this.EXECUTION_TYPE = EXECUTION_TYPE;
           this.LOCKED_FLAG = LOCKED_FLAG;
    }


    /**
     * Gets the BANK_C value for this RowType_NewInstantCards_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_NewInstantCards_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_NewInstantCards_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_NewInstantCards_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }


    /**
     * Gets the g_PROFILE_CODE value for this RowType_NewInstantCards_Request.
     * 
     * @return g_PROFILE_CODE
     */
    public java.lang.String getG_PROFILE_CODE() {
        return g_PROFILE_CODE;
    }


    /**
     * Sets the g_PROFILE_CODE value for this RowType_NewInstantCards_Request.
     * 
     * @param g_PROFILE_CODE
     */
    public void setG_PROFILE_CODE(java.lang.String g_PROFILE_CODE) {
        this.g_PROFILE_CODE = g_PROFILE_CODE;
    }


    /**
     * Gets the p_PROFILE_CODE value for this RowType_NewInstantCards_Request.
     * 
     * @return p_PROFILE_CODE
     */
    public java.lang.String getP_PROFILE_CODE() {
        return p_PROFILE_CODE;
    }


    /**
     * Sets the p_PROFILE_CODE value for this RowType_NewInstantCards_Request.
     * 
     * @param p_PROFILE_CODE
     */
    public void setP_PROFILE_CODE(java.lang.String p_PROFILE_CODE) {
        this.p_PROFILE_CODE = p_PROFILE_CODE;
    }


    /**
     * Gets the COUNT value for this RowType_NewInstantCards_Request.
     * 
     * @return COUNT
     */
    public java.math.BigDecimal getCOUNT() {
        return COUNT;
    }


    /**
     * Sets the COUNT value for this RowType_NewInstantCards_Request.
     * 
     * @param COUNT
     */
    public void setCOUNT(java.math.BigDecimal COUNT) {
        this.COUNT = COUNT;
    }


    /**
     * Gets the AGR_COUNT value for this RowType_NewInstantCards_Request.
     * 
     * @return AGR_COUNT
     */
    public java.math.BigDecimal getAGR_COUNT() {
        return AGR_COUNT;
    }


    /**
     * Sets the AGR_COUNT value for this RowType_NewInstantCards_Request.
     * 
     * @param AGR_COUNT
     */
    public void setAGR_COUNT(java.math.BigDecimal AGR_COUNT) {
        this.AGR_COUNT = AGR_COUNT;
    }


    /**
     * Gets the PRODUCT_CODE value for this RowType_NewInstantCards_Request.
     * 
     * @return PRODUCT_CODE
     */
    public java.lang.String getPRODUCT_CODE() {
        return PRODUCT_CODE;
    }


    /**
     * Sets the PRODUCT_CODE value for this RowType_NewInstantCards_Request.
     * 
     * @param PRODUCT_CODE
     */
    public void setPRODUCT_CODE(java.lang.String PRODUCT_CODE) {
        this.PRODUCT_CODE = PRODUCT_CODE;
    }


    /**
     * Gets the BRANCH value for this RowType_NewInstantCards_Request.
     * 
     * @return BRANCH
     */
    public java.lang.String getBRANCH() {
        return BRANCH;
    }


    /**
     * Sets the BRANCH value for this RowType_NewInstantCards_Request.
     * 
     * @param BRANCH
     */
    public void setBRANCH(java.lang.String BRANCH) {
        this.BRANCH = BRANCH;
    }


    /**
     * Gets the OFFICE value for this RowType_NewInstantCards_Request.
     * 
     * @return OFFICE
     */
    public java.lang.String getOFFICE() {
        return OFFICE;
    }


    /**
     * Sets the OFFICE value for this RowType_NewInstantCards_Request.
     * 
     * @param OFFICE
     */
    public void setOFFICE(java.lang.String OFFICE) {
        this.OFFICE = OFFICE;
    }


    /**
     * Gets the BIN value for this RowType_NewInstantCards_Request.
     * 
     * @return BIN
     */
    public java.lang.String getBIN() {
        return BIN;
    }


    /**
     * Sets the BIN value for this RowType_NewInstantCards_Request.
     * 
     * @param BIN
     */
    public void setBIN(java.lang.String BIN) {
        this.BIN = BIN;
    }


    /**
     * Gets the CCY value for this RowType_NewInstantCards_Request.
     * 
     * @return CCY
     */
    public java.lang.String getCCY() {
        return CCY;
    }


    /**
     * Sets the CCY value for this RowType_NewInstantCards_Request.
     * 
     * @param CCY
     */
    public void setCCY(java.lang.String CCY) {
        this.CCY = CCY;
    }


    /**
     * Gets the a_COND_SET value for this RowType_NewInstantCards_Request.
     * 
     * @return a_COND_SET
     */
    public java.lang.String getA_COND_SET() {
        return a_COND_SET;
    }


    /**
     * Sets the a_COND_SET value for this RowType_NewInstantCards_Request.
     * 
     * @param a_COND_SET
     */
    public void setA_COND_SET(java.lang.String a_COND_SET) {
        this.a_COND_SET = a_COND_SET;
    }


    /**
     * Gets the c_COND_SET value for this RowType_NewInstantCards_Request.
     * 
     * @return c_COND_SET
     */
    public java.lang.String getC_COND_SET() {
        return c_COND_SET;
    }


    /**
     * Sets the c_COND_SET value for this RowType_NewInstantCards_Request.
     * 
     * @param c_COND_SET
     */
    public void setC_COND_SET(java.lang.String c_COND_SET) {
        this.c_COND_SET = c_COND_SET;
    }


    /**
     * Gets the CL_CATEGORY value for this RowType_NewInstantCards_Request.
     * 
     * @return CL_CATEGORY
     */
    public java.lang.String getCL_CATEGORY() {
        return CL_CATEGORY;
    }


    /**
     * Sets the CL_CATEGORY value for this RowType_NewInstantCards_Request.
     * 
     * @param CL_CATEGORY
     */
    public void setCL_CATEGORY(java.lang.String CL_CATEGORY) {
        this.CL_CATEGORY = CL_CATEGORY;
    }


    /**
     * Gets the CARD_FNAME value for this RowType_NewInstantCards_Request.
     * 
     * @return CARD_FNAME
     */
    public java.lang.String getCARD_FNAME() {
        return CARD_FNAME;
    }


    /**
     * Sets the CARD_FNAME value for this RowType_NewInstantCards_Request.
     * 
     * @param CARD_FNAME
     */
    public void setCARD_FNAME(java.lang.String CARD_FNAME) {
        this.CARD_FNAME = CARD_FNAME;
    }


    /**
     * Gets the STREET value for this RowType_NewInstantCards_Request.
     * 
     * @return STREET
     */
    public java.lang.String getSTREET() {
        return STREET;
    }


    /**
     * Sets the STREET value for this RowType_NewInstantCards_Request.
     * 
     * @param STREET
     */
    public void setSTREET(java.lang.String STREET) {
        this.STREET = STREET;
    }


    /**
     * Gets the CITY value for this RowType_NewInstantCards_Request.
     * 
     * @return CITY
     */
    public java.lang.String getCITY() {
        return CITY;
    }


    /**
     * Sets the CITY value for this RowType_NewInstantCards_Request.
     * 
     * @param CITY
     */
    public void setCITY(java.lang.String CITY) {
        this.CITY = CITY;
    }


    /**
     * Gets the OBJ_TREE_TYPE value for this RowType_NewInstantCards_Request.
     * 
     * @return OBJ_TREE_TYPE
     */
    public java.lang.String getOBJ_TREE_TYPE() {
        return OBJ_TREE_TYPE;
    }


    /**
     * Sets the OBJ_TREE_TYPE value for this RowType_NewInstantCards_Request.
     * 
     * @param OBJ_TREE_TYPE
     */
    public void setOBJ_TREE_TYPE(java.lang.String OBJ_TREE_TYPE) {
        this.OBJ_TREE_TYPE = OBJ_TREE_TYPE;
    }


    /**
     * Gets the CLIENT_NUM_ALGO value for this RowType_NewInstantCards_Request.
     * 
     * @return CLIENT_NUM_ALGO
     */
    public java.lang.String getCLIENT_NUM_ALGO() {
        return CLIENT_NUM_ALGO;
    }


    /**
     * Sets the CLIENT_NUM_ALGO value for this RowType_NewInstantCards_Request.
     * 
     * @param CLIENT_NUM_ALGO
     */
    public void setCLIENT_NUM_ALGO(java.lang.String CLIENT_NUM_ALGO) {
        this.CLIENT_NUM_ALGO = CLIENT_NUM_ALGO;
    }


    /**
     * Gets the ACCOUNT_NUM_ALGO value for this RowType_NewInstantCards_Request.
     * 
     * @return ACCOUNT_NUM_ALGO
     */
    public java.lang.String getACCOUNT_NUM_ALGO() {
        return ACCOUNT_NUM_ALGO;
    }


    /**
     * Sets the ACCOUNT_NUM_ALGO value for this RowType_NewInstantCards_Request.
     * 
     * @param ACCOUNT_NUM_ALGO
     */
    public void setACCOUNT_NUM_ALGO(java.lang.String ACCOUNT_NUM_ALGO) {
        this.ACCOUNT_NUM_ALGO = ACCOUNT_NUM_ALGO;
    }


    /**
     * Gets the CARD_NUM_ALGO value for this RowType_NewInstantCards_Request.
     * 
     * @return CARD_NUM_ALGO
     */
    public java.lang.String getCARD_NUM_ALGO() {
        return CARD_NUM_ALGO;
    }


    /**
     * Sets the CARD_NUM_ALGO value for this RowType_NewInstantCards_Request.
     * 
     * @param CARD_NUM_ALGO
     */
    public void setCARD_NUM_ALGO(java.lang.String CARD_NUM_ALGO) {
        this.CARD_NUM_ALGO = CARD_NUM_ALGO;
    }


    /**
     * Gets the BIN_RANGE value for this RowType_NewInstantCards_Request.
     * 
     * @return BIN_RANGE
     */
    public java.math.BigDecimal getBIN_RANGE() {
        return BIN_RANGE;
    }


    /**
     * Sets the BIN_RANGE value for this RowType_NewInstantCards_Request.
     * 
     * @param BIN_RANGE
     */
    public void setBIN_RANGE(java.math.BigDecimal BIN_RANGE) {
        this.BIN_RANGE = BIN_RANGE;
    }


    /**
     * Gets the WITH_MONEY_FLAG value for this RowType_NewInstantCards_Request.
     * 
     * @return WITH_MONEY_FLAG
     */
    public java.lang.String getWITH_MONEY_FLAG() {
        return WITH_MONEY_FLAG;
    }


    /**
     * Sets the WITH_MONEY_FLAG value for this RowType_NewInstantCards_Request.
     * 
     * @param WITH_MONEY_FLAG
     */
    public void setWITH_MONEY_FLAG(java.lang.String WITH_MONEY_FLAG) {
        this.WITH_MONEY_FLAG = WITH_MONEY_FLAG;
    }


    /**
     * Gets the AMOUNT value for this RowType_NewInstantCards_Request.
     * 
     * @return AMOUNT
     */
    public java.math.BigDecimal getAMOUNT() {
        return AMOUNT;
    }


    /**
     * Sets the AMOUNT value for this RowType_NewInstantCards_Request.
     * 
     * @param AMOUNT
     */
    public void setAMOUNT(java.math.BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }


    /**
     * Gets the TR_TYPE value for this RowType_NewInstantCards_Request.
     * 
     * @return TR_TYPE
     */
    public java.lang.String getTR_TYPE() {
        return TR_TYPE;
    }


    /**
     * Sets the TR_TYPE value for this RowType_NewInstantCards_Request.
     * 
     * @param TR_TYPE
     */
    public void setTR_TYPE(java.lang.String TR_TYPE) {
        this.TR_TYPE = TR_TYPE;
    }


    /**
     * Gets the EXECUTION_TYPE value for this RowType_NewInstantCards_Request.
     * 
     * @return EXECUTION_TYPE
     */
    public java.math.BigDecimal getEXECUTION_TYPE() {
        return EXECUTION_TYPE;
    }


    /**
     * Sets the EXECUTION_TYPE value for this RowType_NewInstantCards_Request.
     * 
     * @param EXECUTION_TYPE
     */
    public void setEXECUTION_TYPE(java.math.BigDecimal EXECUTION_TYPE) {
        this.EXECUTION_TYPE = EXECUTION_TYPE;
    }


    /**
     * Gets the LOCKED_FLAG value for this RowType_NewInstantCards_Request.
     * 
     * @return LOCKED_FLAG
     */
    public java.lang.String getLOCKED_FLAG() {
        return LOCKED_FLAG;
    }


    /**
     * Sets the LOCKED_FLAG value for this RowType_NewInstantCards_Request.
     * 
     * @param LOCKED_FLAG
     */
    public void setLOCKED_FLAG(java.lang.String LOCKED_FLAG) {
        this.LOCKED_FLAG = LOCKED_FLAG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_NewInstantCards_Request)) return false;
        RowType_NewInstantCards_Request other = (RowType_NewInstantCards_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C()))) &&
            ((this.GROUPC==null && other.getGROUPC()==null) || 
             (this.GROUPC!=null &&
              this.GROUPC.equals(other.getGROUPC()))) &&
            ((this.g_PROFILE_CODE==null && other.getG_PROFILE_CODE()==null) || 
             (this.g_PROFILE_CODE!=null &&
              this.g_PROFILE_CODE.equals(other.getG_PROFILE_CODE()))) &&
            ((this.p_PROFILE_CODE==null && other.getP_PROFILE_CODE()==null) || 
             (this.p_PROFILE_CODE!=null &&
              this.p_PROFILE_CODE.equals(other.getP_PROFILE_CODE()))) &&
            ((this.COUNT==null && other.getCOUNT()==null) || 
             (this.COUNT!=null &&
              this.COUNT.equals(other.getCOUNT()))) &&
            ((this.AGR_COUNT==null && other.getAGR_COUNT()==null) || 
             (this.AGR_COUNT!=null &&
              this.AGR_COUNT.equals(other.getAGR_COUNT()))) &&
            ((this.PRODUCT_CODE==null && other.getPRODUCT_CODE()==null) || 
             (this.PRODUCT_CODE!=null &&
              this.PRODUCT_CODE.equals(other.getPRODUCT_CODE()))) &&
            ((this.BRANCH==null && other.getBRANCH()==null) || 
             (this.BRANCH!=null &&
              this.BRANCH.equals(other.getBRANCH()))) &&
            ((this.OFFICE==null && other.getOFFICE()==null) || 
             (this.OFFICE!=null &&
              this.OFFICE.equals(other.getOFFICE()))) &&
            ((this.BIN==null && other.getBIN()==null) || 
             (this.BIN!=null &&
              this.BIN.equals(other.getBIN()))) &&
            ((this.CCY==null && other.getCCY()==null) || 
             (this.CCY!=null &&
              this.CCY.equals(other.getCCY()))) &&
            ((this.a_COND_SET==null && other.getA_COND_SET()==null) || 
             (this.a_COND_SET!=null &&
              this.a_COND_SET.equals(other.getA_COND_SET()))) &&
            ((this.c_COND_SET==null && other.getC_COND_SET()==null) || 
             (this.c_COND_SET!=null &&
              this.c_COND_SET.equals(other.getC_COND_SET()))) &&
            ((this.CL_CATEGORY==null && other.getCL_CATEGORY()==null) || 
             (this.CL_CATEGORY!=null &&
              this.CL_CATEGORY.equals(other.getCL_CATEGORY()))) &&
            ((this.CARD_FNAME==null && other.getCARD_FNAME()==null) || 
             (this.CARD_FNAME!=null &&
              this.CARD_FNAME.equals(other.getCARD_FNAME()))) &&
            ((this.STREET==null && other.getSTREET()==null) || 
             (this.STREET!=null &&
              this.STREET.equals(other.getSTREET()))) &&
            ((this.CITY==null && other.getCITY()==null) || 
             (this.CITY!=null &&
              this.CITY.equals(other.getCITY()))) &&
            ((this.OBJ_TREE_TYPE==null && other.getOBJ_TREE_TYPE()==null) || 
             (this.OBJ_TREE_TYPE!=null &&
              this.OBJ_TREE_TYPE.equals(other.getOBJ_TREE_TYPE()))) &&
            ((this.CLIENT_NUM_ALGO==null && other.getCLIENT_NUM_ALGO()==null) || 
             (this.CLIENT_NUM_ALGO!=null &&
              this.CLIENT_NUM_ALGO.equals(other.getCLIENT_NUM_ALGO()))) &&
            ((this.ACCOUNT_NUM_ALGO==null && other.getACCOUNT_NUM_ALGO()==null) || 
             (this.ACCOUNT_NUM_ALGO!=null &&
              this.ACCOUNT_NUM_ALGO.equals(other.getACCOUNT_NUM_ALGO()))) &&
            ((this.CARD_NUM_ALGO==null && other.getCARD_NUM_ALGO()==null) || 
             (this.CARD_NUM_ALGO!=null &&
              this.CARD_NUM_ALGO.equals(other.getCARD_NUM_ALGO()))) &&
            ((this.BIN_RANGE==null && other.getBIN_RANGE()==null) || 
             (this.BIN_RANGE!=null &&
              this.BIN_RANGE.equals(other.getBIN_RANGE()))) &&
            ((this.WITH_MONEY_FLAG==null && other.getWITH_MONEY_FLAG()==null) || 
             (this.WITH_MONEY_FLAG!=null &&
              this.WITH_MONEY_FLAG.equals(other.getWITH_MONEY_FLAG()))) &&
            ((this.AMOUNT==null && other.getAMOUNT()==null) || 
             (this.AMOUNT!=null &&
              this.AMOUNT.equals(other.getAMOUNT()))) &&
            ((this.TR_TYPE==null && other.getTR_TYPE()==null) || 
             (this.TR_TYPE!=null &&
              this.TR_TYPE.equals(other.getTR_TYPE()))) &&
            ((this.EXECUTION_TYPE==null && other.getEXECUTION_TYPE()==null) || 
             (this.EXECUTION_TYPE!=null &&
              this.EXECUTION_TYPE.equals(other.getEXECUTION_TYPE()))) &&
            ((this.LOCKED_FLAG==null && other.getLOCKED_FLAG()==null) || 
             (this.LOCKED_FLAG!=null &&
              this.LOCKED_FLAG.equals(other.getLOCKED_FLAG())));
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
        if (getBANK_C() != null) {
            _hashCode += getBANK_C().hashCode();
        }
        if (getGROUPC() != null) {
            _hashCode += getGROUPC().hashCode();
        }
        if (getG_PROFILE_CODE() != null) {
            _hashCode += getG_PROFILE_CODE().hashCode();
        }
        if (getP_PROFILE_CODE() != null) {
            _hashCode += getP_PROFILE_CODE().hashCode();
        }
        if (getCOUNT() != null) {
            _hashCode += getCOUNT().hashCode();
        }
        if (getAGR_COUNT() != null) {
            _hashCode += getAGR_COUNT().hashCode();
        }
        if (getPRODUCT_CODE() != null) {
            _hashCode += getPRODUCT_CODE().hashCode();
        }
        if (getBRANCH() != null) {
            _hashCode += getBRANCH().hashCode();
        }
        if (getOFFICE() != null) {
            _hashCode += getOFFICE().hashCode();
        }
        if (getBIN() != null) {
            _hashCode += getBIN().hashCode();
        }
        if (getCCY() != null) {
            _hashCode += getCCY().hashCode();
        }
        if (getA_COND_SET() != null) {
            _hashCode += getA_COND_SET().hashCode();
        }
        if (getC_COND_SET() != null) {
            _hashCode += getC_COND_SET().hashCode();
        }
        if (getCL_CATEGORY() != null) {
            _hashCode += getCL_CATEGORY().hashCode();
        }
        if (getCARD_FNAME() != null) {
            _hashCode += getCARD_FNAME().hashCode();
        }
        if (getSTREET() != null) {
            _hashCode += getSTREET().hashCode();
        }
        if (getCITY() != null) {
            _hashCode += getCITY().hashCode();
        }
        if (getOBJ_TREE_TYPE() != null) {
            _hashCode += getOBJ_TREE_TYPE().hashCode();
        }
        if (getCLIENT_NUM_ALGO() != null) {
            _hashCode += getCLIENT_NUM_ALGO().hashCode();
        }
        if (getACCOUNT_NUM_ALGO() != null) {
            _hashCode += getACCOUNT_NUM_ALGO().hashCode();
        }
        if (getCARD_NUM_ALGO() != null) {
            _hashCode += getCARD_NUM_ALGO().hashCode();
        }
        if (getBIN_RANGE() != null) {
            _hashCode += getBIN_RANGE().hashCode();
        }
        if (getWITH_MONEY_FLAG() != null) {
            _hashCode += getWITH_MONEY_FLAG().hashCode();
        }
        if (getAMOUNT() != null) {
            _hashCode += getAMOUNT().hashCode();
        }
        if (getTR_TYPE() != null) {
            _hashCode += getTR_TYPE().hashCode();
        }
        if (getEXECUTION_TYPE() != null) {
            _hashCode += getEXECUTION_TYPE().hashCode();
        }
        if (getLOCKED_FLAG() != null) {
            _hashCode += getLOCKED_FLAG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_NewInstantCards_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_NewInstantCards_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("g_PROFILE_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "G_PROFILE_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_PROFILE_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "P_PROFILE_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGR_COUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AGR_COUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRODUCT_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PRODUCT_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("OFFICE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OFFICE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BIN"));
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
        elemField.setFieldName("a_COND_SET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "A_COND_SET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c_COND_SET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "C_COND_SET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CL_CATEGORY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CL_CATEGORY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_FNAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_FNAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STREET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STREET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CITY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CITY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBJ_TREE_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OBJ_TREE_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLIENT_NUM_ALGO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLIENT_NUM_ALGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACCOUNT_NUM_ALGO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACCOUNT_NUM_ALGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_NUM_ALGO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_NUM_ALGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BIN_RANGE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BIN_RANGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WITH_MONEY_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WITH_MONEY_FLAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TR_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TR_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("LOCKED_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOCKED_FLAG"));
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
