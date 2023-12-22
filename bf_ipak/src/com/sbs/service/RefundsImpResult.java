/**
 * RefundsImpResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class RefundsImpResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.RefundImp[] refundsImp;

    public RefundsImpResult() {
    }

    public RefundsImpResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.RefundImp[] refundsImp) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.refundsImp = refundsImp;
    }


    /**
     * Gets the refundsImp value for this RefundsImpResult.
     * 
     * @return refundsImp
     */
    public com.sbs.service.RefundImp[] getRefundsImp() {
        return refundsImp;
    }


    /**
     * Sets the refundsImp value for this RefundsImpResult.
     * 
     * @param refundsImp
     */
    public void setRefundsImp(com.sbs.service.RefundImp[] refundsImp) {
        this.refundsImp = refundsImp;
    }

    public com.sbs.service.RefundImp getRefundsImp(int i) {
        return this.refundsImp[i];
    }

    public void setRefundsImp(int i, com.sbs.service.RefundImp _value) {
        this.refundsImp[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RefundsImpResult)) return false;
        RefundsImpResult other = (RefundsImpResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.refundsImp==null && other.getRefundsImp()==null) || 
             (this.refundsImp!=null &&
              java.util.Arrays.equals(this.refundsImp, other.getRefundsImp())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getRefundsImp() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRefundsImp());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRefundsImp(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RefundsImpResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "refundsImpResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refundsImp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "refundsImp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "refundImp"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return fileType metadata object
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
