/**
 * PaymentsRefResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class PaymentsRefResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.PaymentRef[] paymentsRef;

    public PaymentsRefResult() {
    }

    public PaymentsRefResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.PaymentRef[] paymentsRef) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.paymentsRef = paymentsRef;
    }


    /**
     * Gets the paymentsRef value for this PaymentsRefResult.
     * 
     * @return paymentsRef
     */
    public com.sbs.service.PaymentRef[] getPaymentsRef() {
        return paymentsRef;
    }


    /**
     * Sets the paymentsRef value for this PaymentsRefResult.
     * 
     * @param paymentsRef
     */
    public void setPaymentsRef(com.sbs.service.PaymentRef[] paymentsRef) {
        this.paymentsRef = paymentsRef;
    }

    public com.sbs.service.PaymentRef getPaymentsRef(int i) {
        return this.paymentsRef[i];
    }

    public void setPaymentsRef(int i, com.sbs.service.PaymentRef _value) {
        this.paymentsRef[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaymentsRefResult)) return false;
        PaymentsRefResult other = (PaymentsRefResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.paymentsRef==null && other.getPaymentsRef()==null) || 
             (this.paymentsRef!=null &&
              java.util.Arrays.equals(this.paymentsRef, other.getPaymentsRef())));
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
        if (getPaymentsRef() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPaymentsRef());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPaymentsRef(), i);
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
        new org.apache.axis.description.TypeDesc(PaymentsRefResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "paymentsRefResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentsRef");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentsRef"));
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
