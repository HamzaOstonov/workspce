/**
 * PaymentRefResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class PaymentRefResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.PaymentRef[] paymentRefs;

    public PaymentRefResult() {
    }

    public PaymentRefResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.PaymentRef[] paymentRefs) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.paymentRefs = paymentRefs;
    }


    /**
     * Gets the paymentRefs value for this PaymentRefResult.
     * 
     * @return paymentRefs
     */
    public com.sbs.service.PaymentRef[] getPaymentRefs() {
        return paymentRefs;
    }


    /**
     * Sets the paymentRefs value for this PaymentRefResult.
     * 
     * @param paymentRefs
     */
    public void setPaymentRefs(com.sbs.service.PaymentRef[] paymentRefs) {
        this.paymentRefs = paymentRefs;
    }

    public com.sbs.service.PaymentRef getPaymentRefs(int i) {
        return this.paymentRefs[i];
    }

    public void setPaymentRefs(int i, com.sbs.service.PaymentRef _value) {
        this.paymentRefs[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaymentRefResult)) return false;
        PaymentRefResult other = (PaymentRefResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.paymentRefs==null && other.getPaymentRefs()==null) || 
             (this.paymentRefs!=null &&
              java.util.Arrays.equals(this.paymentRefs, other.getPaymentRefs())));
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
        if (getPaymentRefs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPaymentRefs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPaymentRefs(), i);
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
        new org.apache.axis.description.TypeDesc(PaymentRefResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "paymentRefResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentRefs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentRefs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "paymentRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
