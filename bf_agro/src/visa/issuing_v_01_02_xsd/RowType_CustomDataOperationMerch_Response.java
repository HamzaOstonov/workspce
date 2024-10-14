/**
 * RowType_CustomDataOperationMerch_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visa.issuing_v_01_02_xsd;

public class RowType_CustomDataOperationMerch_Response  implements java.io.Serializable {
    private java.math.BigDecimal AFFECTED_ROWS;

    public RowType_CustomDataOperationMerch_Response() {
    }

    public RowType_CustomDataOperationMerch_Response(
           java.math.BigDecimal AFFECTED_ROWS) {
           this.AFFECTED_ROWS = AFFECTED_ROWS;
    }


    /**
     * Gets the AFFECTED_ROWS value for this RowType_CustomDataOperationMerch_Response.
     * 
     * @return AFFECTED_ROWS
     */
    public java.math.BigDecimal getAFFECTED_ROWS() {
        return AFFECTED_ROWS;
    }


    /**
     * Sets the AFFECTED_ROWS value for this RowType_CustomDataOperationMerch_Response.
     * 
     * @param AFFECTED_ROWS
     */
    public void setAFFECTED_ROWS(java.math.BigDecimal AFFECTED_ROWS) {
        this.AFFECTED_ROWS = AFFECTED_ROWS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CustomDataOperationMerch_Response)) return false;
        RowType_CustomDataOperationMerch_Response other = (RowType_CustomDataOperationMerch_Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.AFFECTED_ROWS==null && other.getAFFECTED_ROWS()==null) || 
             (this.AFFECTED_ROWS!=null &&
              this.AFFECTED_ROWS.equals(other.getAFFECTED_ROWS())));
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
        if (getAFFECTED_ROWS() != null) {
            _hashCode += getAFFECTED_ROWS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_CustomDataOperationMerch_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CustomDataOperationMerch_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AFFECTED_ROWS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AFFECTED_ROWS"));
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
