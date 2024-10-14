/**
 * ExchangePaymentResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class ExchangePaymentResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.ExchangePayment[] exchangePayments;

    public ExchangePaymentResult() {
    }

    public ExchangePaymentResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.ExchangePayment[] exchangePayments) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.exchangePayments = exchangePayments;
    }


    /**
     * Gets the exchangePayments value for this ExchangePaymentResult.
     * 
     * @return exchangePayments
     */
    public com.sbs.service.ExchangePayment[] getExchangePayments() {
        return exchangePayments;
    }


    /**
     * Sets the exchangePayments value for this ExchangePaymentResult.
     * 
     * @param exchangePayments
     */
    public void setExchangePayments(com.sbs.service.ExchangePayment[] exchangePayments) {
        this.exchangePayments = exchangePayments;
    }

    public com.sbs.service.ExchangePayment getExchangePayments(int i) {
        return this.exchangePayments[i];
    }

    public void setExchangePayments(int i, com.sbs.service.ExchangePayment _value) {
        this.exchangePayments[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExchangePaymentResult)) return false;
        ExchangePaymentResult other = (ExchangePaymentResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.exchangePayments==null && other.getExchangePayments()==null) || 
             (this.exchangePayments!=null &&
              java.util.Arrays.equals(this.exchangePayments, other.getExchangePayments())));
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
        if (getExchangePayments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExchangePayments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExchangePayments(), i);
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
        new org.apache.axis.description.TypeDesc(ExchangePaymentResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "exchangePaymentResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exchangePayments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exchangePayments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "exchangePayment"));
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
