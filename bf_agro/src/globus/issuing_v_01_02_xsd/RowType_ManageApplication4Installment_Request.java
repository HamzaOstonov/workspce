/**
 * RowType_ManageApplication4Installment_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_ManageApplication4Installment_Request  implements java.io.Serializable {
    private java.lang.String CARD_ACCT;

    private java.lang.String CARD_ACCT_CCY;

    private java.math.BigDecimal CARD_ACCT_AMOUNT;

    private java.lang.String ACTION;

    private java.math.BigDecimal INSTL_APPLICATION_ID;

    private java.lang.String CODE;

    private java.math.BigDecimal ROW_NUM;

    private java.lang.String STAN;

    private java.lang.String CARD;

    private java.util.Calendar TRAN_DATE_TIME;

    private java.lang.String MCC_CODE;

    private java.lang.String REF_NUMBER;

    private java.lang.String APR_CODE;

    private java.lang.String MERCHANT_ID;

    private java.lang.String PROC_CODE;

    private java.math.BigDecimal TRAN_AMT;

    private java.lang.String TRAN_CCY;

    private java.lang.String CHECK_SLIP;

    public RowType_ManageApplication4Installment_Request() {
    }

    public RowType_ManageApplication4Installment_Request(
           java.lang.String CARD_ACCT,
           java.lang.String CARD_ACCT_CCY,
           java.math.BigDecimal CARD_ACCT_AMOUNT,
           java.lang.String ACTION,
           java.math.BigDecimal INSTL_APPLICATION_ID,
           java.lang.String CODE,
           java.math.BigDecimal ROW_NUM,
           java.lang.String STAN,
           java.lang.String CARD,
           java.util.Calendar TRAN_DATE_TIME,
           java.lang.String MCC_CODE,
           java.lang.String REF_NUMBER,
           java.lang.String APR_CODE,
           java.lang.String MERCHANT_ID,
           java.lang.String PROC_CODE,
           java.math.BigDecimal TRAN_AMT,
           java.lang.String TRAN_CCY,
           java.lang.String CHECK_SLIP) {
           this.CARD_ACCT = CARD_ACCT;
           this.CARD_ACCT_CCY = CARD_ACCT_CCY;
           this.CARD_ACCT_AMOUNT = CARD_ACCT_AMOUNT;
           this.ACTION = ACTION;
           this.INSTL_APPLICATION_ID = INSTL_APPLICATION_ID;
           this.CODE = CODE;
           this.ROW_NUM = ROW_NUM;
           this.STAN = STAN;
           this.CARD = CARD;
           this.TRAN_DATE_TIME = TRAN_DATE_TIME;
           this.MCC_CODE = MCC_CODE;
           this.REF_NUMBER = REF_NUMBER;
           this.APR_CODE = APR_CODE;
           this.MERCHANT_ID = MERCHANT_ID;
           this.PROC_CODE = PROC_CODE;
           this.TRAN_AMT = TRAN_AMT;
           this.TRAN_CCY = TRAN_CCY;
           this.CHECK_SLIP = CHECK_SLIP;
    }


    /**
     * Gets the CARD_ACCT value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return CARD_ACCT
     */
    public java.lang.String getCARD_ACCT() {
        return CARD_ACCT;
    }


    /**
     * Sets the CARD_ACCT value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param CARD_ACCT
     */
    public void setCARD_ACCT(java.lang.String CARD_ACCT) {
        this.CARD_ACCT = CARD_ACCT;
    }


    /**
     * Gets the CARD_ACCT_CCY value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return CARD_ACCT_CCY
     */
    public java.lang.String getCARD_ACCT_CCY() {
        return CARD_ACCT_CCY;
    }


    /**
     * Sets the CARD_ACCT_CCY value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param CARD_ACCT_CCY
     */
    public void setCARD_ACCT_CCY(java.lang.String CARD_ACCT_CCY) {
        this.CARD_ACCT_CCY = CARD_ACCT_CCY;
    }


    /**
     * Gets the CARD_ACCT_AMOUNT value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return CARD_ACCT_AMOUNT
     */
    public java.math.BigDecimal getCARD_ACCT_AMOUNT() {
        return CARD_ACCT_AMOUNT;
    }


    /**
     * Sets the CARD_ACCT_AMOUNT value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param CARD_ACCT_AMOUNT
     */
    public void setCARD_ACCT_AMOUNT(java.math.BigDecimal CARD_ACCT_AMOUNT) {
        this.CARD_ACCT_AMOUNT = CARD_ACCT_AMOUNT;
    }


    /**
     * Gets the ACTION value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return ACTION
     */
    public java.lang.String getACTION() {
        return ACTION;
    }


    /**
     * Sets the ACTION value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param ACTION
     */
    public void setACTION(java.lang.String ACTION) {
        this.ACTION = ACTION;
    }


    /**
     * Gets the INSTL_APPLICATION_ID value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return INSTL_APPLICATION_ID
     */
    public java.math.BigDecimal getINSTL_APPLICATION_ID() {
        return INSTL_APPLICATION_ID;
    }


    /**
     * Sets the INSTL_APPLICATION_ID value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param INSTL_APPLICATION_ID
     */
    public void setINSTL_APPLICATION_ID(java.math.BigDecimal INSTL_APPLICATION_ID) {
        this.INSTL_APPLICATION_ID = INSTL_APPLICATION_ID;
    }


    /**
     * Gets the CODE value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return CODE
     */
    public java.lang.String getCODE() {
        return CODE;
    }


    /**
     * Sets the CODE value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param CODE
     */
    public void setCODE(java.lang.String CODE) {
        this.CODE = CODE;
    }


    /**
     * Gets the ROW_NUM value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return ROW_NUM
     */
    public java.math.BigDecimal getROW_NUM() {
        return ROW_NUM;
    }


    /**
     * Sets the ROW_NUM value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param ROW_NUM
     */
    public void setROW_NUM(java.math.BigDecimal ROW_NUM) {
        this.ROW_NUM = ROW_NUM;
    }


    /**
     * Gets the STAN value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return STAN
     */
    public java.lang.String getSTAN() {
        return STAN;
    }


    /**
     * Sets the STAN value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param STAN
     */
    public void setSTAN(java.lang.String STAN) {
        this.STAN = STAN;
    }


    /**
     * Gets the CARD value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the TRAN_DATE_TIME value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return TRAN_DATE_TIME
     */
    public java.util.Calendar getTRAN_DATE_TIME() {
        return TRAN_DATE_TIME;
    }


    /**
     * Sets the TRAN_DATE_TIME value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param TRAN_DATE_TIME
     */
    public void setTRAN_DATE_TIME(java.util.Calendar TRAN_DATE_TIME) {
        this.TRAN_DATE_TIME = TRAN_DATE_TIME;
    }


    /**
     * Gets the MCC_CODE value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return MCC_CODE
     */
    public java.lang.String getMCC_CODE() {
        return MCC_CODE;
    }


    /**
     * Sets the MCC_CODE value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param MCC_CODE
     */
    public void setMCC_CODE(java.lang.String MCC_CODE) {
        this.MCC_CODE = MCC_CODE;
    }


    /**
     * Gets the REF_NUMBER value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return REF_NUMBER
     */
    public java.lang.String getREF_NUMBER() {
        return REF_NUMBER;
    }


    /**
     * Sets the REF_NUMBER value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param REF_NUMBER
     */
    public void setREF_NUMBER(java.lang.String REF_NUMBER) {
        this.REF_NUMBER = REF_NUMBER;
    }


    /**
     * Gets the APR_CODE value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return APR_CODE
     */
    public java.lang.String getAPR_CODE() {
        return APR_CODE;
    }


    /**
     * Sets the APR_CODE value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param APR_CODE
     */
    public void setAPR_CODE(java.lang.String APR_CODE) {
        this.APR_CODE = APR_CODE;
    }


    /**
     * Gets the MERCHANT_ID value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return MERCHANT_ID
     */
    public java.lang.String getMERCHANT_ID() {
        return MERCHANT_ID;
    }


    /**
     * Sets the MERCHANT_ID value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param MERCHANT_ID
     */
    public void setMERCHANT_ID(java.lang.String MERCHANT_ID) {
        this.MERCHANT_ID = MERCHANT_ID;
    }


    /**
     * Gets the PROC_CODE value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return PROC_CODE
     */
    public java.lang.String getPROC_CODE() {
        return PROC_CODE;
    }


    /**
     * Sets the PROC_CODE value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param PROC_CODE
     */
    public void setPROC_CODE(java.lang.String PROC_CODE) {
        this.PROC_CODE = PROC_CODE;
    }


    /**
     * Gets the TRAN_AMT value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return TRAN_AMT
     */
    public java.math.BigDecimal getTRAN_AMT() {
        return TRAN_AMT;
    }


    /**
     * Sets the TRAN_AMT value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param TRAN_AMT
     */
    public void setTRAN_AMT(java.math.BigDecimal TRAN_AMT) {
        this.TRAN_AMT = TRAN_AMT;
    }


    /**
     * Gets the TRAN_CCY value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return TRAN_CCY
     */
    public java.lang.String getTRAN_CCY() {
        return TRAN_CCY;
    }


    /**
     * Sets the TRAN_CCY value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param TRAN_CCY
     */
    public void setTRAN_CCY(java.lang.String TRAN_CCY) {
        this.TRAN_CCY = TRAN_CCY;
    }


    /**
     * Gets the CHECK_SLIP value for this RowType_ManageApplication4Installment_Request.
     * 
     * @return CHECK_SLIP
     */
    public java.lang.String getCHECK_SLIP() {
        return CHECK_SLIP;
    }


    /**
     * Sets the CHECK_SLIP value for this RowType_ManageApplication4Installment_Request.
     * 
     * @param CHECK_SLIP
     */
    public void setCHECK_SLIP(java.lang.String CHECK_SLIP) {
        this.CHECK_SLIP = CHECK_SLIP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ManageApplication4Installment_Request)) return false;
        RowType_ManageApplication4Installment_Request other = (RowType_ManageApplication4Installment_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CARD_ACCT==null && other.getCARD_ACCT()==null) || 
             (this.CARD_ACCT!=null &&
              this.CARD_ACCT.equals(other.getCARD_ACCT()))) &&
            ((this.CARD_ACCT_CCY==null && other.getCARD_ACCT_CCY()==null) || 
             (this.CARD_ACCT_CCY!=null &&
              this.CARD_ACCT_CCY.equals(other.getCARD_ACCT_CCY()))) &&
            ((this.CARD_ACCT_AMOUNT==null && other.getCARD_ACCT_AMOUNT()==null) || 
             (this.CARD_ACCT_AMOUNT!=null &&
              this.CARD_ACCT_AMOUNT.equals(other.getCARD_ACCT_AMOUNT()))) &&
            ((this.ACTION==null && other.getACTION()==null) || 
             (this.ACTION!=null &&
              this.ACTION.equals(other.getACTION()))) &&
            ((this.INSTL_APPLICATION_ID==null && other.getINSTL_APPLICATION_ID()==null) || 
             (this.INSTL_APPLICATION_ID!=null &&
              this.INSTL_APPLICATION_ID.equals(other.getINSTL_APPLICATION_ID()))) &&
            ((this.CODE==null && other.getCODE()==null) || 
             (this.CODE!=null &&
              this.CODE.equals(other.getCODE()))) &&
            ((this.ROW_NUM==null && other.getROW_NUM()==null) || 
             (this.ROW_NUM!=null &&
              this.ROW_NUM.equals(other.getROW_NUM()))) &&
            ((this.STAN==null && other.getSTAN()==null) || 
             (this.STAN!=null &&
              this.STAN.equals(other.getSTAN()))) &&
            ((this.CARD==null && other.getCARD()==null) || 
             (this.CARD!=null &&
              this.CARD.equals(other.getCARD()))) &&
            ((this.TRAN_DATE_TIME==null && other.getTRAN_DATE_TIME()==null) || 
             (this.TRAN_DATE_TIME!=null &&
              this.TRAN_DATE_TIME.equals(other.getTRAN_DATE_TIME()))) &&
            ((this.MCC_CODE==null && other.getMCC_CODE()==null) || 
             (this.MCC_CODE!=null &&
              this.MCC_CODE.equals(other.getMCC_CODE()))) &&
            ((this.REF_NUMBER==null && other.getREF_NUMBER()==null) || 
             (this.REF_NUMBER!=null &&
              this.REF_NUMBER.equals(other.getREF_NUMBER()))) &&
            ((this.APR_CODE==null && other.getAPR_CODE()==null) || 
             (this.APR_CODE!=null &&
              this.APR_CODE.equals(other.getAPR_CODE()))) &&
            ((this.MERCHANT_ID==null && other.getMERCHANT_ID()==null) || 
             (this.MERCHANT_ID!=null &&
              this.MERCHANT_ID.equals(other.getMERCHANT_ID()))) &&
            ((this.PROC_CODE==null && other.getPROC_CODE()==null) || 
             (this.PROC_CODE!=null &&
              this.PROC_CODE.equals(other.getPROC_CODE()))) &&
            ((this.TRAN_AMT==null && other.getTRAN_AMT()==null) || 
             (this.TRAN_AMT!=null &&
              this.TRAN_AMT.equals(other.getTRAN_AMT()))) &&
            ((this.TRAN_CCY==null && other.getTRAN_CCY()==null) || 
             (this.TRAN_CCY!=null &&
              this.TRAN_CCY.equals(other.getTRAN_CCY()))) &&
            ((this.CHECK_SLIP==null && other.getCHECK_SLIP()==null) || 
             (this.CHECK_SLIP!=null &&
              this.CHECK_SLIP.equals(other.getCHECK_SLIP())));
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
        if (getCARD_ACCT() != null) {
            _hashCode += getCARD_ACCT().hashCode();
        }
        if (getCARD_ACCT_CCY() != null) {
            _hashCode += getCARD_ACCT_CCY().hashCode();
        }
        if (getCARD_ACCT_AMOUNT() != null) {
            _hashCode += getCARD_ACCT_AMOUNT().hashCode();
        }
        if (getACTION() != null) {
            _hashCode += getACTION().hashCode();
        }
        if (getINSTL_APPLICATION_ID() != null) {
            _hashCode += getINSTL_APPLICATION_ID().hashCode();
        }
        if (getCODE() != null) {
            _hashCode += getCODE().hashCode();
        }
        if (getROW_NUM() != null) {
            _hashCode += getROW_NUM().hashCode();
        }
        if (getSTAN() != null) {
            _hashCode += getSTAN().hashCode();
        }
        if (getCARD() != null) {
            _hashCode += getCARD().hashCode();
        }
        if (getTRAN_DATE_TIME() != null) {
            _hashCode += getTRAN_DATE_TIME().hashCode();
        }
        if (getMCC_CODE() != null) {
            _hashCode += getMCC_CODE().hashCode();
        }
        if (getREF_NUMBER() != null) {
            _hashCode += getREF_NUMBER().hashCode();
        }
        if (getAPR_CODE() != null) {
            _hashCode += getAPR_CODE().hashCode();
        }
        if (getMERCHANT_ID() != null) {
            _hashCode += getMERCHANT_ID().hashCode();
        }
        if (getPROC_CODE() != null) {
            _hashCode += getPROC_CODE().hashCode();
        }
        if (getTRAN_AMT() != null) {
            _hashCode += getTRAN_AMT().hashCode();
        }
        if (getTRAN_CCY() != null) {
            _hashCode += getTRAN_CCY().hashCode();
        }
        if (getCHECK_SLIP() != null) {
            _hashCode += getCHECK_SLIP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ManageApplication4Installment_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ManageApplication4Installment_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_ACCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_ACCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_ACCT_CCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_ACCT_CCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_ACCT_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_ACCT_AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACTION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACTION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTL_APPLICATION_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSTL_APPLICATION_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ROW_NUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ROW_NUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STAN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STAN"));
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
        elemField.setFieldName("TRAN_DATE_TIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRAN_DATE_TIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MCC_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MCC_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REF_NUMBER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REF_NUMBER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APR_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "APR_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MERCHANT_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MERCHANT_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PROC_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PROC_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRAN_AMT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRAN_AMT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRAN_CCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRAN_CCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CHECK_SLIP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CHECK_SLIP"));
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
