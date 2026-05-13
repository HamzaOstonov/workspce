package com.is.tietovisa.model;

public class RowType_RemoveCardFromStop_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String TEXT;

    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    private java.lang.String STOP_CAUSE;

    private java.lang.String ALL_COMBI;

    public RowType_RemoveCardFromStop_Request() {
    }

    public RowType_RemoveCardFromStop_Request(
           java.lang.String CARD,
           java.lang.String TEXT,
           java.lang.String BANK_C,
           java.lang.String GROUPC,
           java.lang.String STOP_CAUSE,
           java.lang.String ALL_COMBI) {
           this.CARD = CARD;
           this.TEXT = TEXT;
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
           this.STOP_CAUSE = STOP_CAUSE;
           this.ALL_COMBI = ALL_COMBI;
    }


    /**
     * Gets the CARD value for this RowType_RemoveCardFromStop_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_RemoveCardFromStop_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the TEXT value for this RowType_RemoveCardFromStop_Request.
     * 
     * @return TEXT
     */
    public java.lang.String getTEXT() {
        return TEXT;
    }


    /**
     * Sets the TEXT value for this RowType_RemoveCardFromStop_Request.
     * 
     * @param TEXT
     */
    public void setTEXT(java.lang.String TEXT) {
        this.TEXT = TEXT;
    }


    /**
     * Gets the BANK_C value for this RowType_RemoveCardFromStop_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_RemoveCardFromStop_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_RemoveCardFromStop_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_RemoveCardFromStop_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }


    /**
     * Gets the STOP_CAUSE value for this RowType_RemoveCardFromStop_Request.
     * 
     * @return STOP_CAUSE
     */
    public java.lang.String getSTOP_CAUSE() {
        return STOP_CAUSE;
    }


    /**
     * Sets the STOP_CAUSE value for this RowType_RemoveCardFromStop_Request.
     * 
     * @param STOP_CAUSE
     */
    public void setSTOP_CAUSE(java.lang.String STOP_CAUSE) {
        this.STOP_CAUSE = STOP_CAUSE;
    }


    /**
     * Gets the ALL_COMBI value for this RowType_RemoveCardFromStop_Request.
     * 
     * @return ALL_COMBI
     */
    public java.lang.String getALL_COMBI() {
        return ALL_COMBI;
    }


    /**
     * Sets the ALL_COMBI value for this RowType_RemoveCardFromStop_Request.
     * 
     * @param ALL_COMBI
     */
    public void setALL_COMBI(java.lang.String ALL_COMBI) {
        this.ALL_COMBI = ALL_COMBI;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_RemoveCardFromStop_Request)) return false;
        RowType_RemoveCardFromStop_Request other = (RowType_RemoveCardFromStop_Request) obj;
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
            ((this.TEXT==null && other.getTEXT()==null) || 
             (this.TEXT!=null &&
              this.TEXT.equals(other.getTEXT()))) &&
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C()))) &&
            ((this.GROUPC==null && other.getGROUPC()==null) || 
             (this.GROUPC!=null &&
              this.GROUPC.equals(other.getGROUPC()))) &&
            ((this.STOP_CAUSE==null && other.getSTOP_CAUSE()==null) || 
             (this.STOP_CAUSE!=null &&
              this.STOP_CAUSE.equals(other.getSTOP_CAUSE()))) &&
            ((this.ALL_COMBI==null && other.getALL_COMBI()==null) || 
             (this.ALL_COMBI!=null &&
              this.ALL_COMBI.equals(other.getALL_COMBI())));
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
        if (getTEXT() != null) {
            _hashCode += getTEXT().hashCode();
        }
        if (getBANK_C() != null) {
            _hashCode += getBANK_C().hashCode();
        }
        if (getGROUPC() != null) {
            _hashCode += getGROUPC().hashCode();
        }
        if (getSTOP_CAUSE() != null) {
            _hashCode += getSTOP_CAUSE().hashCode();
        }
        if (getALL_COMBI() != null) {
            _hashCode += getALL_COMBI().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }


}
