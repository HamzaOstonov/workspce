/**
 * RowType_DormantAccountByCard_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agroBank.issuing_v_01_02_xsd;

public class RowType_DormantAccountByCard_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String DORMANT_MODE;

    private java.lang.String TEXT;

    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    private java.math.BigDecimal INSTL_INTER;

    public RowType_DormantAccountByCard_Request() {
    }

    public RowType_DormantAccountByCard_Request(
           java.lang.String CARD,
           java.lang.String DORMANT_MODE,
           java.lang.String TEXT,
           java.lang.String BANK_C,
           java.lang.String GROUPC,
           java.math.BigDecimal INSTL_INTER) {
           this.CARD = CARD;
           this.DORMANT_MODE = DORMANT_MODE;
           this.TEXT = TEXT;
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
           this.INSTL_INTER = INSTL_INTER;
    }


    /**
     * Gets the CARD value for this RowType_DormantAccountByCard_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_DormantAccountByCard_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the DORMANT_MODE value for this RowType_DormantAccountByCard_Request.
     * 
     * @return DORMANT_MODE
     */
    public java.lang.String getDORMANT_MODE() {
        return DORMANT_MODE;
    }


    /**
     * Sets the DORMANT_MODE value for this RowType_DormantAccountByCard_Request.
     * 
     * @param DORMANT_MODE
     */
    public void setDORMANT_MODE(java.lang.String DORMANT_MODE) {
        this.DORMANT_MODE = DORMANT_MODE;
    }


    /**
     * Gets the TEXT value for this RowType_DormantAccountByCard_Request.
     * 
     * @return TEXT
     */
    public java.lang.String getTEXT() {
        return TEXT;
    }


    /**
     * Sets the TEXT value for this RowType_DormantAccountByCard_Request.
     * 
     * @param TEXT
     */
    public void setTEXT(java.lang.String TEXT) {
        this.TEXT = TEXT;
    }


    /**
     * Gets the BANK_C value for this RowType_DormantAccountByCard_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_DormantAccountByCard_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_DormantAccountByCard_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_DormantAccountByCard_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }


    /**
     * Gets the INSTL_INTER value for this RowType_DormantAccountByCard_Request.
     * 
     * @return INSTL_INTER
     */
    public java.math.BigDecimal getINSTL_INTER() {
        return INSTL_INTER;
    }


    /**
     * Sets the INSTL_INTER value for this RowType_DormantAccountByCard_Request.
     * 
     * @param INSTL_INTER
     */
    public void setINSTL_INTER(java.math.BigDecimal INSTL_INTER) {
        this.INSTL_INTER = INSTL_INTER;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_DormantAccountByCard_Request)) return false;
        RowType_DormantAccountByCard_Request other = (RowType_DormantAccountByCard_Request) obj;
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
            ((this.DORMANT_MODE==null && other.getDORMANT_MODE()==null) || 
             (this.DORMANT_MODE!=null &&
              this.DORMANT_MODE.equals(other.getDORMANT_MODE()))) &&
            ((this.TEXT==null && other.getTEXT()==null) || 
             (this.TEXT!=null &&
              this.TEXT.equals(other.getTEXT()))) &&
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C()))) &&
            ((this.GROUPC==null && other.getGROUPC()==null) || 
             (this.GROUPC!=null &&
              this.GROUPC.equals(other.getGROUPC()))) &&
            ((this.INSTL_INTER==null && other.getINSTL_INTER()==null) || 
             (this.INSTL_INTER!=null &&
              this.INSTL_INTER.equals(other.getINSTL_INTER())));
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
        if (getDORMANT_MODE() != null) {
            _hashCode += getDORMANT_MODE().hashCode();
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
        if (getINSTL_INTER() != null) {
            _hashCode += getINSTL_INTER().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_DormantAccountByCard_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DormantAccountByCard_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DORMANT_MODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DORMANT_MODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("INSTL_INTER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSTL_INTER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
