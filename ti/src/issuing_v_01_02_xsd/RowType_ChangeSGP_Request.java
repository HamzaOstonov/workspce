/**
 * RowType_ChangeSGP_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_ChangeSGP_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String CVV2;

    private java.lang.String CURRENT_PASSWORD;

    private java.lang.String NEW_PASSWORD;

    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    public RowType_ChangeSGP_Request() {
    }

    public RowType_ChangeSGP_Request(
           java.lang.String CARD,
           java.lang.String CVV2,
           java.lang.String CURRENT_PASSWORD,
           java.lang.String NEW_PASSWORD,
           java.lang.String BANK_C,
           java.lang.String GROUPC) {
           this.CARD = CARD;
           this.CVV2 = CVV2;
           this.CURRENT_PASSWORD = CURRENT_PASSWORD;
           this.NEW_PASSWORD = NEW_PASSWORD;
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
    }


    /**
     * Gets the CARD value for this RowType_ChangeSGP_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_ChangeSGP_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the CVV2 value for this RowType_ChangeSGP_Request.
     * 
     * @return CVV2
     */
    public java.lang.String getCVV2() {
        return CVV2;
    }


    /**
     * Sets the CVV2 value for this RowType_ChangeSGP_Request.
     * 
     * @param CVV2
     */
    public void setCVV2(java.lang.String CVV2) {
        this.CVV2 = CVV2;
    }


    /**
     * Gets the CURRENT_PASSWORD value for this RowType_ChangeSGP_Request.
     * 
     * @return CURRENT_PASSWORD
     */
    public java.lang.String getCURRENT_PASSWORD() {
        return CURRENT_PASSWORD;
    }


    /**
     * Sets the CURRENT_PASSWORD value for this RowType_ChangeSGP_Request.
     * 
     * @param CURRENT_PASSWORD
     */
    public void setCURRENT_PASSWORD(java.lang.String CURRENT_PASSWORD) {
        this.CURRENT_PASSWORD = CURRENT_PASSWORD;
    }


    /**
     * Gets the NEW_PASSWORD value for this RowType_ChangeSGP_Request.
     * 
     * @return NEW_PASSWORD
     */
    public java.lang.String getNEW_PASSWORD() {
        return NEW_PASSWORD;
    }


    /**
     * Sets the NEW_PASSWORD value for this RowType_ChangeSGP_Request.
     * 
     * @param NEW_PASSWORD
     */
    public void setNEW_PASSWORD(java.lang.String NEW_PASSWORD) {
        this.NEW_PASSWORD = NEW_PASSWORD;
    }


    /**
     * Gets the BANK_C value for this RowType_ChangeSGP_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_ChangeSGP_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_ChangeSGP_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_ChangeSGP_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ChangeSGP_Request)) return false;
        RowType_ChangeSGP_Request other = (RowType_ChangeSGP_Request) obj;
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
            ((this.CVV2==null && other.getCVV2()==null) || 
             (this.CVV2!=null &&
              this.CVV2.equals(other.getCVV2()))) &&
            ((this.CURRENT_PASSWORD==null && other.getCURRENT_PASSWORD()==null) || 
             (this.CURRENT_PASSWORD!=null &&
              this.CURRENT_PASSWORD.equals(other.getCURRENT_PASSWORD()))) &&
            ((this.NEW_PASSWORD==null && other.getNEW_PASSWORD()==null) || 
             (this.NEW_PASSWORD!=null &&
              this.NEW_PASSWORD.equals(other.getNEW_PASSWORD()))) &&
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
        if (getCARD() != null) {
            _hashCode += getCARD().hashCode();
        }
        if (getCVV2() != null) {
            _hashCode += getCVV2().hashCode();
        }
        if (getCURRENT_PASSWORD() != null) {
            _hashCode += getCURRENT_PASSWORD().hashCode();
        }
        if (getNEW_PASSWORD() != null) {
            _hashCode += getNEW_PASSWORD().hashCode();
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
        new org.apache.axis.description.TypeDesc(RowType_ChangeSGP_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ChangeSGP_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CVV2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CVV2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CURRENT_PASSWORD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CURRENT_PASSWORD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NEW_PASSWORD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NEW_PASSWORD"));
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
