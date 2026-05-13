/**
 * RowType_LimitEvent_Limit.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_LimitEvent_Limit  implements java.io.Serializable {
    private java.lang.String LIMIT_TYPE;

    private java.math.BigDecimal LIMIT_VALUE;

    public RowType_LimitEvent_Limit() {
    }

    public RowType_LimitEvent_Limit(
           java.lang.String LIMIT_TYPE,
           java.math.BigDecimal LIMIT_VALUE) {
           this.LIMIT_TYPE = LIMIT_TYPE;
           this.LIMIT_VALUE = LIMIT_VALUE;
    }


    /**
     * Gets the LIMIT_TYPE value for this RowType_LimitEvent_Limit.
     * 
     * @return LIMIT_TYPE
     */
    public java.lang.String getLIMIT_TYPE() {
        return LIMIT_TYPE;
    }


    /**
     * Sets the LIMIT_TYPE value for this RowType_LimitEvent_Limit.
     * 
     * @param LIMIT_TYPE
     */
    public void setLIMIT_TYPE(java.lang.String LIMIT_TYPE) {
        this.LIMIT_TYPE = LIMIT_TYPE;
    }


    /**
     * Gets the LIMIT_VALUE value for this RowType_LimitEvent_Limit.
     * 
     * @return LIMIT_VALUE
     */
    public java.math.BigDecimal getLIMIT_VALUE() {
        return LIMIT_VALUE;
    }


    /**
     * Sets the LIMIT_VALUE value for this RowType_LimitEvent_Limit.
     * 
     * @param LIMIT_VALUE
     */
    public void setLIMIT_VALUE(java.math.BigDecimal LIMIT_VALUE) {
        this.LIMIT_VALUE = LIMIT_VALUE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_LimitEvent_Limit)) return false;
        RowType_LimitEvent_Limit other = (RowType_LimitEvent_Limit) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.LIMIT_TYPE==null && other.getLIMIT_TYPE()==null) || 
             (this.LIMIT_TYPE!=null &&
              this.LIMIT_TYPE.equals(other.getLIMIT_TYPE()))) &&
            ((this.LIMIT_VALUE==null && other.getLIMIT_VALUE()==null) || 
             (this.LIMIT_VALUE!=null &&
              this.LIMIT_VALUE.equals(other.getLIMIT_VALUE())));
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
        if (getLIMIT_TYPE() != null) {
            _hashCode += getLIMIT_TYPE().hashCode();
        }
        if (getLIMIT_VALUE() != null) {
            _hashCode += getLIMIT_VALUE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_LimitEvent_Limit.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LimitEvent_Limit"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LIMIT_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LIMIT_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LIMIT_VALUE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LIMIT_VALUE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
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
