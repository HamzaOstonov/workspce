/**
 * RowType_Hint_Confirmation_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_Hint_Confirmation_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String HINT_ANSWER;

    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    public RowType_Hint_Confirmation_Request() {
    }

    public RowType_Hint_Confirmation_Request(
           java.lang.String CARD,
           java.lang.String HINT_ANSWER,
           java.lang.String BANK_C,
           java.lang.String GROUPC) {
           this.CARD = CARD;
           this.HINT_ANSWER = HINT_ANSWER;
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
    }


    /**
     * Gets the CARD value for this RowType_Hint_Confirmation_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_Hint_Confirmation_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the HINT_ANSWER value for this RowType_Hint_Confirmation_Request.
     * 
     * @return HINT_ANSWER
     */
    public java.lang.String getHINT_ANSWER() {
        return HINT_ANSWER;
    }


    /**
     * Sets the HINT_ANSWER value for this RowType_Hint_Confirmation_Request.
     * 
     * @param HINT_ANSWER
     */
    public void setHINT_ANSWER(java.lang.String HINT_ANSWER) {
        this.HINT_ANSWER = HINT_ANSWER;
    }


    /**
     * Gets the BANK_C value for this RowType_Hint_Confirmation_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_Hint_Confirmation_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_Hint_Confirmation_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_Hint_Confirmation_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_Hint_Confirmation_Request)) return false;
        RowType_Hint_Confirmation_Request other = (RowType_Hint_Confirmation_Request) obj;
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
            ((this.HINT_ANSWER==null && other.getHINT_ANSWER()==null) || 
             (this.HINT_ANSWER!=null &&
              this.HINT_ANSWER.equals(other.getHINT_ANSWER()))) &&
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
        if (getHINT_ANSWER() != null) {
            _hashCode += getHINT_ANSWER().hashCode();
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
        new org.apache.axis.description.TypeDesc(RowType_Hint_Confirmation_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Hint_Confirmation_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HINT_ANSWER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HINT_ANSWER"));
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
