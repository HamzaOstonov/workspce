/**
 * RowType_LoyaltyAccountRelinkRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visa.issuing_v_01_02_xsd;

public class RowType_LoyaltyAccountRelinkRequest  implements java.io.Serializable {
    private java.lang.String SOURCE_CARD;

    private java.lang.String LS_CODE;

    private java.lang.String DESTINATION_CARD;

    public RowType_LoyaltyAccountRelinkRequest() {
    }

    public RowType_LoyaltyAccountRelinkRequest(
           java.lang.String SOURCE_CARD,
           java.lang.String LS_CODE,
           java.lang.String DESTINATION_CARD) {
           this.SOURCE_CARD = SOURCE_CARD;
           this.LS_CODE = LS_CODE;
           this.DESTINATION_CARD = DESTINATION_CARD;
    }


    /**
     * Gets the SOURCE_CARD value for this RowType_LoyaltyAccountRelinkRequest.
     * 
     * @return SOURCE_CARD
     */
    public java.lang.String getSOURCE_CARD() {
        return SOURCE_CARD;
    }


    /**
     * Sets the SOURCE_CARD value for this RowType_LoyaltyAccountRelinkRequest.
     * 
     * @param SOURCE_CARD
     */
    public void setSOURCE_CARD(java.lang.String SOURCE_CARD) {
        this.SOURCE_CARD = SOURCE_CARD;
    }


    /**
     * Gets the LS_CODE value for this RowType_LoyaltyAccountRelinkRequest.
     * 
     * @return LS_CODE
     */
    public java.lang.String getLS_CODE() {
        return LS_CODE;
    }


    /**
     * Sets the LS_CODE value for this RowType_LoyaltyAccountRelinkRequest.
     * 
     * @param LS_CODE
     */
    public void setLS_CODE(java.lang.String LS_CODE) {
        this.LS_CODE = LS_CODE;
    }


    /**
     * Gets the DESTINATION_CARD value for this RowType_LoyaltyAccountRelinkRequest.
     * 
     * @return DESTINATION_CARD
     */
    public java.lang.String getDESTINATION_CARD() {
        return DESTINATION_CARD;
    }


    /**
     * Sets the DESTINATION_CARD value for this RowType_LoyaltyAccountRelinkRequest.
     * 
     * @param DESTINATION_CARD
     */
    public void setDESTINATION_CARD(java.lang.String DESTINATION_CARD) {
        this.DESTINATION_CARD = DESTINATION_CARD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_LoyaltyAccountRelinkRequest)) return false;
        RowType_LoyaltyAccountRelinkRequest other = (RowType_LoyaltyAccountRelinkRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SOURCE_CARD==null && other.getSOURCE_CARD()==null) || 
             (this.SOURCE_CARD!=null &&
              this.SOURCE_CARD.equals(other.getSOURCE_CARD()))) &&
            ((this.LS_CODE==null && other.getLS_CODE()==null) || 
             (this.LS_CODE!=null &&
              this.LS_CODE.equals(other.getLS_CODE()))) &&
            ((this.DESTINATION_CARD==null && other.getDESTINATION_CARD()==null) || 
             (this.DESTINATION_CARD!=null &&
              this.DESTINATION_CARD.equals(other.getDESTINATION_CARD())));
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
        if (getSOURCE_CARD() != null) {
            _hashCode += getSOURCE_CARD().hashCode();
        }
        if (getLS_CODE() != null) {
            _hashCode += getLS_CODE().hashCode();
        }
        if (getDESTINATION_CARD() != null) {
            _hashCode += getDESTINATION_CARD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_LoyaltyAccountRelinkRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LoyaltyAccountRelinkRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOURCE_CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SOURCE_CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LS_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LS_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESTINATION_CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DESTINATION_CARD"));
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
