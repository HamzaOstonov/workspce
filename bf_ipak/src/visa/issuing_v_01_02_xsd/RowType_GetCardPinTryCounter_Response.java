/**
 * RowType_GetCardPinTryCounter_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visa.issuing_v_01_02_xsd;

public class RowType_GetCardPinTryCounter_Response  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.math.BigInteger PIN_TRY_COUNT;

    private java.util.Calendar UPDATE_DATE;

    public RowType_GetCardPinTryCounter_Response() {
    }

    public RowType_GetCardPinTryCounter_Response(
           java.lang.String CARD,
           java.math.BigInteger PIN_TRY_COUNT,
           java.util.Calendar UPDATE_DATE) {
           this.CARD = CARD;
           this.PIN_TRY_COUNT = PIN_TRY_COUNT;
           this.UPDATE_DATE = UPDATE_DATE;
    }


    /**
     * Gets the CARD value for this RowType_GetCardPinTryCounter_Response.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_GetCardPinTryCounter_Response.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the PIN_TRY_COUNT value for this RowType_GetCardPinTryCounter_Response.
     * 
     * @return PIN_TRY_COUNT
     */
    public java.math.BigInteger getPIN_TRY_COUNT() {
        return PIN_TRY_COUNT;
    }


    /**
     * Sets the PIN_TRY_COUNT value for this RowType_GetCardPinTryCounter_Response.
     * 
     * @param PIN_TRY_COUNT
     */
    public void setPIN_TRY_COUNT(java.math.BigInteger PIN_TRY_COUNT) {
        this.PIN_TRY_COUNT = PIN_TRY_COUNT;
    }


    /**
     * Gets the UPDATE_DATE value for this RowType_GetCardPinTryCounter_Response.
     * 
     * @return UPDATE_DATE
     */
    public java.util.Calendar getUPDATE_DATE() {
        return UPDATE_DATE;
    }


    /**
     * Sets the UPDATE_DATE value for this RowType_GetCardPinTryCounter_Response.
     * 
     * @param UPDATE_DATE
     */
    public void setUPDATE_DATE(java.util.Calendar UPDATE_DATE) {
        this.UPDATE_DATE = UPDATE_DATE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_GetCardPinTryCounter_Response)) return false;
        RowType_GetCardPinTryCounter_Response other = (RowType_GetCardPinTryCounter_Response) obj;
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
            ((this.PIN_TRY_COUNT==null && other.getPIN_TRY_COUNT()==null) || 
             (this.PIN_TRY_COUNT!=null &&
              this.PIN_TRY_COUNT.equals(other.getPIN_TRY_COUNT()))) &&
            ((this.UPDATE_DATE==null && other.getUPDATE_DATE()==null) || 
             (this.UPDATE_DATE!=null &&
              this.UPDATE_DATE.equals(other.getUPDATE_DATE())));
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
        if (getPIN_TRY_COUNT() != null) {
            _hashCode += getPIN_TRY_COUNT().hashCode();
        }
        if (getUPDATE_DATE() != null) {
            _hashCode += getUPDATE_DATE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_GetCardPinTryCounter_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetCardPinTryCounter_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PIN_TRY_COUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PIN_TRY_COUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UPDATE_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UPDATE_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
