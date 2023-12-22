/**
 * RefundExpResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class RefundExpResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.RefundExp[] refundsExp;

    public RefundExpResult() {
    }

    public RefundExpResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.RefundExp[] refundsExp) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.refundsExp = refundsExp;
    }


    /**
     * Gets the refundsExp value for this RefundExpResult.
     * 
     * @return refundsExp
     */
    public com.sbs.service.RefundExp[] getRefundsExp() {
        return refundsExp;
    }


    /**
     * Sets the refundsExp value for this RefundExpResult.
     * 
     * @param refundsExp
     */
    public void setRefundsExp(com.sbs.service.RefundExp[] refundsExp) {
        this.refundsExp = refundsExp;
    }

    public com.sbs.service.RefundExp getRefundsExp(int i) {
        return this.refundsExp[i];
    }

    public void setRefundsExp(int i, com.sbs.service.RefundExp _value) {
        this.refundsExp[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RefundExpResult)) return false;
        RefundExpResult other = (RefundExpResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.refundsExp==null && other.getRefundsExp()==null) || 
             (this.refundsExp!=null &&
              java.util.Arrays.equals(this.refundsExp, other.getRefundsExp())));
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
        if (getRefundsExp() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRefundsExp());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRefundsExp(), i);
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
        new org.apache.axis.description.TypeDesc(RefundExpResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "refundExpResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refundsExp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "refundsExp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "refundExp"));
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
