/**
 * RowType_CloseInstallmentAgreement_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_CloseInstallmentAgreement_Request  implements java.io.Serializable {
    private java.lang.String INSTL_AGR_NO;

    private java.math.BigDecimal INSTL_INTER;

    public RowType_CloseInstallmentAgreement_Request() {
    }

    public RowType_CloseInstallmentAgreement_Request(
           java.lang.String INSTL_AGR_NO,
           java.math.BigDecimal INSTL_INTER) {
           this.INSTL_AGR_NO = INSTL_AGR_NO;
           this.INSTL_INTER = INSTL_INTER;
    }


    /**
     * Gets the INSTL_AGR_NO value for this RowType_CloseInstallmentAgreement_Request.
     * 
     * @return INSTL_AGR_NO
     */
    public java.lang.String getINSTL_AGR_NO() {
        return INSTL_AGR_NO;
    }


    /**
     * Sets the INSTL_AGR_NO value for this RowType_CloseInstallmentAgreement_Request.
     * 
     * @param INSTL_AGR_NO
     */
    public void setINSTL_AGR_NO(java.lang.String INSTL_AGR_NO) {
        this.INSTL_AGR_NO = INSTL_AGR_NO;
    }


    /**
     * Gets the INSTL_INTER value for this RowType_CloseInstallmentAgreement_Request.
     * 
     * @return INSTL_INTER
     */
    public java.math.BigDecimal getINSTL_INTER() {
        return INSTL_INTER;
    }


    /**
     * Sets the INSTL_INTER value for this RowType_CloseInstallmentAgreement_Request.
     * 
     * @param INSTL_INTER
     */
    public void setINSTL_INTER(java.math.BigDecimal INSTL_INTER) {
        this.INSTL_INTER = INSTL_INTER;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CloseInstallmentAgreement_Request)) return false;
        RowType_CloseInstallmentAgreement_Request other = (RowType_CloseInstallmentAgreement_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INSTL_AGR_NO==null && other.getINSTL_AGR_NO()==null) || 
             (this.INSTL_AGR_NO!=null &&
              this.INSTL_AGR_NO.equals(other.getINSTL_AGR_NO()))) &&
            ((this.INSTL_INTER==null && other.getINSTL_INTER()==null) || 
             (this.INSTL_INTER!=null &&
              this.INSTL_INTER.equals(other.getINSTL_INTER())));
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
        if (getINSTL_AGR_NO() != null) {
            _hashCode += getINSTL_AGR_NO().hashCode();
        }
        if (getINSTL_INTER() != null) {
            _hashCode += getINSTL_INTER().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_CloseInstallmentAgreement_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CloseInstallmentAgreement_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTL_AGR_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSTL_AGR_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTL_INTER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSTL_INTER"));
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
