/**
 * RowType_GetInstallmentConditionsForTransaction_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_GetInstallmentConditionsForTransaction_Request  implements java.io.Serializable {
    private java.math.BigDecimal SLIP_INTERNAL_NO;

    public RowType_GetInstallmentConditionsForTransaction_Request() {
    }

    public RowType_GetInstallmentConditionsForTransaction_Request(
           java.math.BigDecimal SLIP_INTERNAL_NO) {
           this.SLIP_INTERNAL_NO = SLIP_INTERNAL_NO;
    }


    /**
     * Gets the SLIP_INTERNAL_NO value for this RowType_GetInstallmentConditionsForTransaction_Request.
     * 
     * @return SLIP_INTERNAL_NO
     */
    public java.math.BigDecimal getSLIP_INTERNAL_NO() {
        return SLIP_INTERNAL_NO;
    }


    /**
     * Sets the SLIP_INTERNAL_NO value for this RowType_GetInstallmentConditionsForTransaction_Request.
     * 
     * @param SLIP_INTERNAL_NO
     */
    public void setSLIP_INTERNAL_NO(java.math.BigDecimal SLIP_INTERNAL_NO) {
        this.SLIP_INTERNAL_NO = SLIP_INTERNAL_NO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_GetInstallmentConditionsForTransaction_Request)) return false;
        RowType_GetInstallmentConditionsForTransaction_Request other = (RowType_GetInstallmentConditionsForTransaction_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SLIP_INTERNAL_NO==null && other.getSLIP_INTERNAL_NO()==null) || 
             (this.SLIP_INTERNAL_NO!=null &&
              this.SLIP_INTERNAL_NO.equals(other.getSLIP_INTERNAL_NO())));
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
        if (getSLIP_INTERNAL_NO() != null) {
            _hashCode += getSLIP_INTERNAL_NO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_GetInstallmentConditionsForTransaction_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetInstallmentConditionsForTransaction_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SLIP_INTERNAL_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SLIP_INTERNAL_NO"));
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
