/**
 * RowType_TransferLoyaltyPoints_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_TransferLoyaltyPoints_Response  implements java.io.Serializable {
    private java.math.BigDecimal TRANSFERED_AMOUNT;

    public RowType_TransferLoyaltyPoints_Response() {
    }

    public RowType_TransferLoyaltyPoints_Response(
           java.math.BigDecimal TRANSFERED_AMOUNT) {
           this.TRANSFERED_AMOUNT = TRANSFERED_AMOUNT;
    }


    /**
     * Gets the TRANSFERED_AMOUNT value for this RowType_TransferLoyaltyPoints_Response.
     * 
     * @return TRANSFERED_AMOUNT
     */
    public java.math.BigDecimal getTRANSFERED_AMOUNT() {
        return TRANSFERED_AMOUNT;
    }


    /**
     * Sets the TRANSFERED_AMOUNT value for this RowType_TransferLoyaltyPoints_Response.
     * 
     * @param TRANSFERED_AMOUNT
     */
    public void setTRANSFERED_AMOUNT(java.math.BigDecimal TRANSFERED_AMOUNT) {
        this.TRANSFERED_AMOUNT = TRANSFERED_AMOUNT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_TransferLoyaltyPoints_Response)) return false;
        RowType_TransferLoyaltyPoints_Response other = (RowType_TransferLoyaltyPoints_Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TRANSFERED_AMOUNT==null && other.getTRANSFERED_AMOUNT()==null) || 
             (this.TRANSFERED_AMOUNT!=null &&
              this.TRANSFERED_AMOUNT.equals(other.getTRANSFERED_AMOUNT())));
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
        if (getTRANSFERED_AMOUNT() != null) {
            _hashCode += getTRANSFERED_AMOUNT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_TransferLoyaltyPoints_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransferLoyaltyPoints_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRANSFERED_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRANSFERED_AMOUNT"));
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
