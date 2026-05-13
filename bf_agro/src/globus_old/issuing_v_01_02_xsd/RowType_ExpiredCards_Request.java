/**
 * RowType_ExpiredCards_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_ExpiredCards_Request  implements java.io.Serializable {
    private java.math.BigDecimal MAX_ROW_COUNT;

    private java.math.BigDecimal FIRST_ROW_NUMBER;

    private java.lang.String CARD_TYPE;

    private java.lang.String PRODUCT;

    private java.util.Calendar EXPIRY_DATE;

    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    public RowType_ExpiredCards_Request() {
    }

    public RowType_ExpiredCards_Request(
           java.math.BigDecimal MAX_ROW_COUNT,
           java.math.BigDecimal FIRST_ROW_NUMBER,
           java.lang.String CARD_TYPE,
           java.lang.String PRODUCT,
           java.util.Calendar EXPIRY_DATE,
           java.lang.String BANK_C,
           java.lang.String GROUPC) {
           this.MAX_ROW_COUNT = MAX_ROW_COUNT;
           this.FIRST_ROW_NUMBER = FIRST_ROW_NUMBER;
           this.CARD_TYPE = CARD_TYPE;
           this.PRODUCT = PRODUCT;
           this.EXPIRY_DATE = EXPIRY_DATE;
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
    }


    /**
     * Gets the MAX_ROW_COUNT value for this RowType_ExpiredCards_Request.
     * 
     * @return MAX_ROW_COUNT
     */
    public java.math.BigDecimal getMAX_ROW_COUNT() {
        return MAX_ROW_COUNT;
    }


    /**
     * Sets the MAX_ROW_COUNT value for this RowType_ExpiredCards_Request.
     * 
     * @param MAX_ROW_COUNT
     */
    public void setMAX_ROW_COUNT(java.math.BigDecimal MAX_ROW_COUNT) {
        this.MAX_ROW_COUNT = MAX_ROW_COUNT;
    }


    /**
     * Gets the FIRST_ROW_NUMBER value for this RowType_ExpiredCards_Request.
     * 
     * @return FIRST_ROW_NUMBER
     */
    public java.math.BigDecimal getFIRST_ROW_NUMBER() {
        return FIRST_ROW_NUMBER;
    }


    /**
     * Sets the FIRST_ROW_NUMBER value for this RowType_ExpiredCards_Request.
     * 
     * @param FIRST_ROW_NUMBER
     */
    public void setFIRST_ROW_NUMBER(java.math.BigDecimal FIRST_ROW_NUMBER) {
        this.FIRST_ROW_NUMBER = FIRST_ROW_NUMBER;
    }


    /**
     * Gets the CARD_TYPE value for this RowType_ExpiredCards_Request.
     * 
     * @return CARD_TYPE
     */
    public java.lang.String getCARD_TYPE() {
        return CARD_TYPE;
    }


    /**
     * Sets the CARD_TYPE value for this RowType_ExpiredCards_Request.
     * 
     * @param CARD_TYPE
     */
    public void setCARD_TYPE(java.lang.String CARD_TYPE) {
        this.CARD_TYPE = CARD_TYPE;
    }


    /**
     * Gets the PRODUCT value for this RowType_ExpiredCards_Request.
     * 
     * @return PRODUCT
     */
    public java.lang.String getPRODUCT() {
        return PRODUCT;
    }


    /**
     * Sets the PRODUCT value for this RowType_ExpiredCards_Request.
     * 
     * @param PRODUCT
     */
    public void setPRODUCT(java.lang.String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }


    /**
     * Gets the EXPIRY_DATE value for this RowType_ExpiredCards_Request.
     * 
     * @return EXPIRY_DATE
     */
    public java.util.Calendar getEXPIRY_DATE() {
        return EXPIRY_DATE;
    }


    /**
     * Sets the EXPIRY_DATE value for this RowType_ExpiredCards_Request.
     * 
     * @param EXPIRY_DATE
     */
    public void setEXPIRY_DATE(java.util.Calendar EXPIRY_DATE) {
        this.EXPIRY_DATE = EXPIRY_DATE;
    }


    /**
     * Gets the BANK_C value for this RowType_ExpiredCards_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_ExpiredCards_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_ExpiredCards_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_ExpiredCards_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ExpiredCards_Request)) return false;
        RowType_ExpiredCards_Request other = (RowType_ExpiredCards_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MAX_ROW_COUNT==null && other.getMAX_ROW_COUNT()==null) || 
             (this.MAX_ROW_COUNT!=null &&
              this.MAX_ROW_COUNT.equals(other.getMAX_ROW_COUNT()))) &&
            ((this.FIRST_ROW_NUMBER==null && other.getFIRST_ROW_NUMBER()==null) || 
             (this.FIRST_ROW_NUMBER!=null &&
              this.FIRST_ROW_NUMBER.equals(other.getFIRST_ROW_NUMBER()))) &&
            ((this.CARD_TYPE==null && other.getCARD_TYPE()==null) || 
             (this.CARD_TYPE!=null &&
              this.CARD_TYPE.equals(other.getCARD_TYPE()))) &&
            ((this.PRODUCT==null && other.getPRODUCT()==null) || 
             (this.PRODUCT!=null &&
              this.PRODUCT.equals(other.getPRODUCT()))) &&
            ((this.EXPIRY_DATE==null && other.getEXPIRY_DATE()==null) || 
             (this.EXPIRY_DATE!=null &&
              this.EXPIRY_DATE.equals(other.getEXPIRY_DATE()))) &&
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C()))) &&
            ((this.GROUPC==null && other.getGROUPC()==null) || 
             (this.GROUPC!=null &&
              this.GROUPC.equals(other.getGROUPC())));
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
        if (getMAX_ROW_COUNT() != null) {
            _hashCode += getMAX_ROW_COUNT().hashCode();
        }
        if (getFIRST_ROW_NUMBER() != null) {
            _hashCode += getFIRST_ROW_NUMBER().hashCode();
        }
        if (getCARD_TYPE() != null) {
            _hashCode += getCARD_TYPE().hashCode();
        }
        if (getPRODUCT() != null) {
            _hashCode += getPRODUCT().hashCode();
        }
        if (getEXPIRY_DATE() != null) {
            _hashCode += getEXPIRY_DATE().hashCode();
        }
        if (getBANK_C() != null) {
            _hashCode += getBANK_C().hashCode();
        }
        if (getGROUPC() != null) {
            _hashCode += getGROUPC().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ExpiredCards_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ExpiredCards_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MAX_ROW_COUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MAX_ROW_COUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FIRST_ROW_NUMBER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FIRST_ROW_NUMBER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRODUCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PRODUCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXPIRY_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXPIRY_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
