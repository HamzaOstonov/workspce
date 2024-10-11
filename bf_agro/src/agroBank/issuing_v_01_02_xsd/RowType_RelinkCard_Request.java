/**
 * RowType_RelinkCard_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agroBank.issuing_v_01_02_xsd;

public class RowType_RelinkCard_Request  implements java.io.Serializable {
    private java.lang.String CARD_FOR_LINK;

    private java.lang.String TARGET_CARD;

    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    private java.math.BigDecimal DEST_ACCNT_TYPE;

    private java.lang.String DEST_ACCNT_NUMBER;

    private java.lang.String RELINK_MODE;

    private java.lang.String COND_SET;

    private java.lang.String FEE_CALC_MODE;

    private java.lang.String PRODUCT;

    private java.lang.String LINK_TYPE;

    private java.lang.String MOMENT;

    private java.lang.String LOCKS;

    private java.lang.String CONTRACT;

    public RowType_RelinkCard_Request() {
    }

    public RowType_RelinkCard_Request(
           java.lang.String CARD_FOR_LINK,
           java.lang.String TARGET_CARD,
           java.lang.String BANK_C,
           java.lang.String GROUPC,
           java.math.BigDecimal DEST_ACCNT_TYPE,
           java.lang.String DEST_ACCNT_NUMBER,
           java.lang.String RELINK_MODE,
           java.lang.String COND_SET,
           java.lang.String FEE_CALC_MODE,
           java.lang.String PRODUCT,
           java.lang.String LINK_TYPE,
           java.lang.String MOMENT,
           java.lang.String LOCKS,
           java.lang.String CONTRACT) {
           this.CARD_FOR_LINK = CARD_FOR_LINK;
           this.TARGET_CARD = TARGET_CARD;
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
           this.DEST_ACCNT_TYPE = DEST_ACCNT_TYPE;
           this.DEST_ACCNT_NUMBER = DEST_ACCNT_NUMBER;
           this.RELINK_MODE = RELINK_MODE;
           this.COND_SET = COND_SET;
           this.FEE_CALC_MODE = FEE_CALC_MODE;
           this.PRODUCT = PRODUCT;
           this.LINK_TYPE = LINK_TYPE;
           this.MOMENT = MOMENT;
           this.LOCKS = LOCKS;
           this.CONTRACT = CONTRACT;
    }


    /**
     * Gets the CARD_FOR_LINK value for this RowType_RelinkCard_Request.
     * 
     * @return CARD_FOR_LINK
     */
    public java.lang.String getCARD_FOR_LINK() {
        return CARD_FOR_LINK;
    }


    /**
     * Sets the CARD_FOR_LINK value for this RowType_RelinkCard_Request.
     * 
     * @param CARD_FOR_LINK
     */
    public void setCARD_FOR_LINK(java.lang.String CARD_FOR_LINK) {
        this.CARD_FOR_LINK = CARD_FOR_LINK;
    }


    /**
     * Gets the TARGET_CARD value for this RowType_RelinkCard_Request.
     * 
     * @return TARGET_CARD
     */
    public java.lang.String getTARGET_CARD() {
        return TARGET_CARD;
    }


    /**
     * Sets the TARGET_CARD value for this RowType_RelinkCard_Request.
     * 
     * @param TARGET_CARD
     */
    public void setTARGET_CARD(java.lang.String TARGET_CARD) {
        this.TARGET_CARD = TARGET_CARD;
    }


    /**
     * Gets the BANK_C value for this RowType_RelinkCard_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_RelinkCard_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_RelinkCard_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_RelinkCard_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }


    /**
     * Gets the DEST_ACCNT_TYPE value for this RowType_RelinkCard_Request.
     * 
     * @return DEST_ACCNT_TYPE
     */
    public java.math.BigDecimal getDEST_ACCNT_TYPE() {
        return DEST_ACCNT_TYPE;
    }


    /**
     * Sets the DEST_ACCNT_TYPE value for this RowType_RelinkCard_Request.
     * 
     * @param DEST_ACCNT_TYPE
     */
    public void setDEST_ACCNT_TYPE(java.math.BigDecimal DEST_ACCNT_TYPE) {
        this.DEST_ACCNT_TYPE = DEST_ACCNT_TYPE;
    }


    /**
     * Gets the DEST_ACCNT_NUMBER value for this RowType_RelinkCard_Request.
     * 
     * @return DEST_ACCNT_NUMBER
     */
    public java.lang.String getDEST_ACCNT_NUMBER() {
        return DEST_ACCNT_NUMBER;
    }


    /**
     * Sets the DEST_ACCNT_NUMBER value for this RowType_RelinkCard_Request.
     * 
     * @param DEST_ACCNT_NUMBER
     */
    public void setDEST_ACCNT_NUMBER(java.lang.String DEST_ACCNT_NUMBER) {
        this.DEST_ACCNT_NUMBER = DEST_ACCNT_NUMBER;
    }


    /**
     * Gets the RELINK_MODE value for this RowType_RelinkCard_Request.
     * 
     * @return RELINK_MODE
     */
    public java.lang.String getRELINK_MODE() {
        return RELINK_MODE;
    }


    /**
     * Sets the RELINK_MODE value for this RowType_RelinkCard_Request.
     * 
     * @param RELINK_MODE
     */
    public void setRELINK_MODE(java.lang.String RELINK_MODE) {
        this.RELINK_MODE = RELINK_MODE;
    }


    /**
     * Gets the COND_SET value for this RowType_RelinkCard_Request.
     * 
     * @return COND_SET
     */
    public java.lang.String getCOND_SET() {
        return COND_SET;
    }


    /**
     * Sets the COND_SET value for this RowType_RelinkCard_Request.
     * 
     * @param COND_SET
     */
    public void setCOND_SET(java.lang.String COND_SET) {
        this.COND_SET = COND_SET;
    }


    /**
     * Gets the FEE_CALC_MODE value for this RowType_RelinkCard_Request.
     * 
     * @return FEE_CALC_MODE
     */
    public java.lang.String getFEE_CALC_MODE() {
        return FEE_CALC_MODE;
    }


    /**
     * Sets the FEE_CALC_MODE value for this RowType_RelinkCard_Request.
     * 
     * @param FEE_CALC_MODE
     */
    public void setFEE_CALC_MODE(java.lang.String FEE_CALC_MODE) {
        this.FEE_CALC_MODE = FEE_CALC_MODE;
    }


    /**
     * Gets the PRODUCT value for this RowType_RelinkCard_Request.
     * 
     * @return PRODUCT
     */
    public java.lang.String getPRODUCT() {
        return PRODUCT;
    }


    /**
     * Sets the PRODUCT value for this RowType_RelinkCard_Request.
     * 
     * @param PRODUCT
     */
    public void setPRODUCT(java.lang.String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }


    /**
     * Gets the LINK_TYPE value for this RowType_RelinkCard_Request.
     * 
     * @return LINK_TYPE
     */
    public java.lang.String getLINK_TYPE() {
        return LINK_TYPE;
    }


    /**
     * Sets the LINK_TYPE value for this RowType_RelinkCard_Request.
     * 
     * @param LINK_TYPE
     */
    public void setLINK_TYPE(java.lang.String LINK_TYPE) {
        this.LINK_TYPE = LINK_TYPE;
    }


    /**
     * Gets the MOMENT value for this RowType_RelinkCard_Request.
     * 
     * @return MOMENT
     */
    public java.lang.String getMOMENT() {
        return MOMENT;
    }


    /**
     * Sets the MOMENT value for this RowType_RelinkCard_Request.
     * 
     * @param MOMENT
     */
    public void setMOMENT(java.lang.String MOMENT) {
        this.MOMENT = MOMENT;
    }


    /**
     * Gets the LOCKS value for this RowType_RelinkCard_Request.
     * 
     * @return LOCKS
     */
    public java.lang.String getLOCKS() {
        return LOCKS;
    }


    /**
     * Sets the LOCKS value for this RowType_RelinkCard_Request.
     * 
     * @param LOCKS
     */
    public void setLOCKS(java.lang.String LOCKS) {
        this.LOCKS = LOCKS;
    }


    /**
     * Gets the CONTRACT value for this RowType_RelinkCard_Request.
     * 
     * @return CONTRACT
     */
    public java.lang.String getCONTRACT() {
        return CONTRACT;
    }


    /**
     * Sets the CONTRACT value for this RowType_RelinkCard_Request.
     * 
     * @param CONTRACT
     */
    public void setCONTRACT(java.lang.String CONTRACT) {
        this.CONTRACT = CONTRACT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_RelinkCard_Request)) return false;
        RowType_RelinkCard_Request other = (RowType_RelinkCard_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CARD_FOR_LINK==null && other.getCARD_FOR_LINK()==null) || 
             (this.CARD_FOR_LINK!=null &&
              this.CARD_FOR_LINK.equals(other.getCARD_FOR_LINK()))) &&
            ((this.TARGET_CARD==null && other.getTARGET_CARD()==null) || 
             (this.TARGET_CARD!=null &&
              this.TARGET_CARD.equals(other.getTARGET_CARD()))) &&
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C()))) &&
            ((this.GROUPC==null && other.getGROUPC()==null) || 
             (this.GROUPC!=null &&
              this.GROUPC.equals(other.getGROUPC()))) &&
            ((this.DEST_ACCNT_TYPE==null && other.getDEST_ACCNT_TYPE()==null) || 
             (this.DEST_ACCNT_TYPE!=null &&
              this.DEST_ACCNT_TYPE.equals(other.getDEST_ACCNT_TYPE()))) &&
            ((this.DEST_ACCNT_NUMBER==null && other.getDEST_ACCNT_NUMBER()==null) || 
             (this.DEST_ACCNT_NUMBER!=null &&
              this.DEST_ACCNT_NUMBER.equals(other.getDEST_ACCNT_NUMBER()))) &&
            ((this.RELINK_MODE==null && other.getRELINK_MODE()==null) || 
             (this.RELINK_MODE!=null &&
              this.RELINK_MODE.equals(other.getRELINK_MODE()))) &&
            ((this.COND_SET==null && other.getCOND_SET()==null) || 
             (this.COND_SET!=null &&
              this.COND_SET.equals(other.getCOND_SET()))) &&
            ((this.FEE_CALC_MODE==null && other.getFEE_CALC_MODE()==null) || 
             (this.FEE_CALC_MODE!=null &&
              this.FEE_CALC_MODE.equals(other.getFEE_CALC_MODE()))) &&
            ((this.PRODUCT==null && other.getPRODUCT()==null) || 
             (this.PRODUCT!=null &&
              this.PRODUCT.equals(other.getPRODUCT()))) &&
            ((this.LINK_TYPE==null && other.getLINK_TYPE()==null) || 
             (this.LINK_TYPE!=null &&
              this.LINK_TYPE.equals(other.getLINK_TYPE()))) &&
            ((this.MOMENT==null && other.getMOMENT()==null) || 
             (this.MOMENT!=null &&
              this.MOMENT.equals(other.getMOMENT()))) &&
            ((this.LOCKS==null && other.getLOCKS()==null) || 
             (this.LOCKS!=null &&
              this.LOCKS.equals(other.getLOCKS()))) &&
            ((this.CONTRACT==null && other.getCONTRACT()==null) || 
             (this.CONTRACT!=null &&
              this.CONTRACT.equals(other.getCONTRACT())));
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
        if (getCARD_FOR_LINK() != null) {
            _hashCode += getCARD_FOR_LINK().hashCode();
        }
        if (getTARGET_CARD() != null) {
            _hashCode += getTARGET_CARD().hashCode();
        }
        if (getBANK_C() != null) {
            _hashCode += getBANK_C().hashCode();
        }
        if (getGROUPC() != null) {
            _hashCode += getGROUPC().hashCode();
        }
        if (getDEST_ACCNT_TYPE() != null) {
            _hashCode += getDEST_ACCNT_TYPE().hashCode();
        }
        if (getDEST_ACCNT_NUMBER() != null) {
            _hashCode += getDEST_ACCNT_NUMBER().hashCode();
        }
        if (getRELINK_MODE() != null) {
            _hashCode += getRELINK_MODE().hashCode();
        }
        if (getCOND_SET() != null) {
            _hashCode += getCOND_SET().hashCode();
        }
        if (getFEE_CALC_MODE() != null) {
            _hashCode += getFEE_CALC_MODE().hashCode();
        }
        if (getPRODUCT() != null) {
            _hashCode += getPRODUCT().hashCode();
        }
        if (getLINK_TYPE() != null) {
            _hashCode += getLINK_TYPE().hashCode();
        }
        if (getMOMENT() != null) {
            _hashCode += getMOMENT().hashCode();
        }
        if (getLOCKS() != null) {
            _hashCode += getLOCKS().hashCode();
        }
        if (getCONTRACT() != null) {
            _hashCode += getCONTRACT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_RelinkCard_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RelinkCard_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_FOR_LINK");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_FOR_LINK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TARGET_CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TARGET_CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("DEST_ACCNT_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEST_ACCNT_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEST_ACCNT_NUMBER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEST_ACCNT_NUMBER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RELINK_MODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RELINK_MODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COND_SET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COND_SET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FEE_CALC_MODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FEE_CALC_MODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRODUCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PRODUCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LINK_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LINK_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOMENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MOMENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOCKS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOCKS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTRACT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTRACT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
