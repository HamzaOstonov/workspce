/**
 * RowType_CreateInstallmentAgreement_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_CreateInstallmentAgreement_Request  implements java.io.Serializable {
    private java.math.BigDecimal SLIP_INTERNAL_NO;

    private java.lang.String CODE;

    private java.math.BigDecimal ROW_NUM;

    public RowType_CreateInstallmentAgreement_Request() {
    }

    public RowType_CreateInstallmentAgreement_Request(
           java.math.BigDecimal SLIP_INTERNAL_NO,
           java.lang.String CODE,
           java.math.BigDecimal ROW_NUM) {
           this.SLIP_INTERNAL_NO = SLIP_INTERNAL_NO;
           this.CODE = CODE;
           this.ROW_NUM = ROW_NUM;
    }


    /**
     * Gets the SLIP_INTERNAL_NO value for this RowType_CreateInstallmentAgreement_Request.
     * 
     * @return SLIP_INTERNAL_NO
     */
    public java.math.BigDecimal getSLIP_INTERNAL_NO() {
        return SLIP_INTERNAL_NO;
    }


    /**
     * Sets the SLIP_INTERNAL_NO value for this RowType_CreateInstallmentAgreement_Request.
     * 
     * @param SLIP_INTERNAL_NO
     */
    public void setSLIP_INTERNAL_NO(java.math.BigDecimal SLIP_INTERNAL_NO) {
        this.SLIP_INTERNAL_NO = SLIP_INTERNAL_NO;
    }


    /**
     * Gets the CODE value for this RowType_CreateInstallmentAgreement_Request.
     * 
     * @return CODE
     */
    public java.lang.String getCODE() {
        return CODE;
    }


    /**
     * Sets the CODE value for this RowType_CreateInstallmentAgreement_Request.
     * 
     * @param CODE
     */
    public void setCODE(java.lang.String CODE) {
        this.CODE = CODE;
    }


    /**
     * Gets the ROW_NUM value for this RowType_CreateInstallmentAgreement_Request.
     * 
     * @return ROW_NUM
     */
    public java.math.BigDecimal getROW_NUM() {
        return ROW_NUM;
    }


    /**
     * Sets the ROW_NUM value for this RowType_CreateInstallmentAgreement_Request.
     * 
     * @param ROW_NUM
     */
    public void setROW_NUM(java.math.BigDecimal ROW_NUM) {
        this.ROW_NUM = ROW_NUM;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CreateInstallmentAgreement_Request)) return false;
        RowType_CreateInstallmentAgreement_Request other = (RowType_CreateInstallmentAgreement_Request) obj;
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
              this.SLIP_INTERNAL_NO.equals(other.getSLIP_INTERNAL_NO()))) &&
            ((this.CODE==null && other.getCODE()==null) || 
             (this.CODE!=null &&
              this.CODE.equals(other.getCODE()))) &&
            ((this.ROW_NUM==null && other.getROW_NUM()==null) || 
             (this.ROW_NUM!=null &&
              this.ROW_NUM.equals(other.getROW_NUM())));
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
        if (getCODE() != null) {
            _hashCode += getCODE().hashCode();
        }
        if (getROW_NUM() != null) {
            _hashCode += getROW_NUM().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_CreateInstallmentAgreement_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CreateInstallmentAgreement_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SLIP_INTERNAL_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SLIP_INTERNAL_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ROW_NUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ROW_NUM"));
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
