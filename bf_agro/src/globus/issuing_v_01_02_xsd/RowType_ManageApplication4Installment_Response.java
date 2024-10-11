/**
 * RowType_ManageApplication4Installment_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_ManageApplication4Installment_Response  implements java.io.Serializable {
    private java.math.BigDecimal INSTL_APPLICATION_ID;

    private java.math.BigDecimal SLIP_INTERNAL_NO;

    public RowType_ManageApplication4Installment_Response() {
    }

    public RowType_ManageApplication4Installment_Response(
           java.math.BigDecimal INSTL_APPLICATION_ID,
           java.math.BigDecimal SLIP_INTERNAL_NO) {
           this.INSTL_APPLICATION_ID = INSTL_APPLICATION_ID;
           this.SLIP_INTERNAL_NO = SLIP_INTERNAL_NO;
    }


    /**
     * Gets the INSTL_APPLICATION_ID value for this RowType_ManageApplication4Installment_Response.
     * 
     * @return INSTL_APPLICATION_ID
     */
    public java.math.BigDecimal getINSTL_APPLICATION_ID() {
        return INSTL_APPLICATION_ID;
    }


    /**
     * Sets the INSTL_APPLICATION_ID value for this RowType_ManageApplication4Installment_Response.
     * 
     * @param INSTL_APPLICATION_ID
     */
    public void setINSTL_APPLICATION_ID(java.math.BigDecimal INSTL_APPLICATION_ID) {
        this.INSTL_APPLICATION_ID = INSTL_APPLICATION_ID;
    }


    /**
     * Gets the SLIP_INTERNAL_NO value for this RowType_ManageApplication4Installment_Response.
     * 
     * @return SLIP_INTERNAL_NO
     */
    public java.math.BigDecimal getSLIP_INTERNAL_NO() {
        return SLIP_INTERNAL_NO;
    }


    /**
     * Sets the SLIP_INTERNAL_NO value for this RowType_ManageApplication4Installment_Response.
     * 
     * @param SLIP_INTERNAL_NO
     */
    public void setSLIP_INTERNAL_NO(java.math.BigDecimal SLIP_INTERNAL_NO) {
        this.SLIP_INTERNAL_NO = SLIP_INTERNAL_NO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ManageApplication4Installment_Response)) return false;
        RowType_ManageApplication4Installment_Response other = (RowType_ManageApplication4Installment_Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INSTL_APPLICATION_ID==null && other.getINSTL_APPLICATION_ID()==null) || 
             (this.INSTL_APPLICATION_ID!=null &&
              this.INSTL_APPLICATION_ID.equals(other.getINSTL_APPLICATION_ID()))) &&
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
        if (getINSTL_APPLICATION_ID() != null) {
            _hashCode += getINSTL_APPLICATION_ID().hashCode();
        }
        if (getSLIP_INTERNAL_NO() != null) {
            _hashCode += getSLIP_INTERNAL_NO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ManageApplication4Installment_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ManageApplication4Installment_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTL_APPLICATION_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSTL_APPLICATION_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
