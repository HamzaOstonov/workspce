/**
 * RowType_ExecTransaction_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_ExecTransaction_Response  implements java.io.Serializable {
    private java.math.BigDecimal INTERNAL_NO;

    public RowType_ExecTransaction_Response() {
    }

    public RowType_ExecTransaction_Response(
           java.math.BigDecimal INTERNAL_NO) {
           this.INTERNAL_NO = INTERNAL_NO;
    }


    /**
     * Gets the INTERNAL_NO value for this RowType_ExecTransaction_Response.
     * 
     * @return INTERNAL_NO
     */
    public java.math.BigDecimal getINTERNAL_NO() {
        return INTERNAL_NO;
    }


    /**
     * Sets the INTERNAL_NO value for this RowType_ExecTransaction_Response.
     * 
     * @param INTERNAL_NO
     */
    public void setINTERNAL_NO(java.math.BigDecimal INTERNAL_NO) {
        this.INTERNAL_NO = INTERNAL_NO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ExecTransaction_Response)) return false;
        RowType_ExecTransaction_Response other = (RowType_ExecTransaction_Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INTERNAL_NO==null && other.getINTERNAL_NO()==null) || 
             (this.INTERNAL_NO!=null &&
              this.INTERNAL_NO.equals(other.getINTERNAL_NO())));
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
        if (getINTERNAL_NO() != null) {
            _hashCode += getINTERNAL_NO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ExecTransaction_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ExecTransaction_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTERNAL_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INTERNAL_NO"));
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
