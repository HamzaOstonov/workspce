/**
 * PaymentRefSumChangeResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class PaymentRefSumChangeResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.PaymentRefSumChange[] paymentRefSumChanges;

    public PaymentRefSumChangeResult() {
    }

    public PaymentRefSumChangeResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.PaymentRefSumChange[] paymentRefSumChanges) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.paymentRefSumChanges = paymentRefSumChanges;
    }


    /**
     * Gets the paymentRefSumChanges value for this PaymentRefSumChangeResult.
     * 
     * @return paymentRefSumChanges
     */
    public com.sbs.service.PaymentRefSumChange[] getPaymentRefSumChanges() {
        return paymentRefSumChanges;
    }


    /**
     * Sets the paymentRefSumChanges value for this PaymentRefSumChangeResult.
     * 
     * @param paymentRefSumChanges
     */
    public void setPaymentRefSumChanges(com.sbs.service.PaymentRefSumChange[] paymentRefSumChanges) {
        this.paymentRefSumChanges = paymentRefSumChanges;
    }

    public com.sbs.service.PaymentRefSumChange getPaymentRefSumChanges(int i) {
        return this.paymentRefSumChanges[i];
    }

    public void setPaymentRefSumChanges(int i, com.sbs.service.PaymentRefSumChange _value) {
        this.paymentRefSumChanges[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaymentRefSumChangeResult)) return false;
        PaymentRefSumChangeResult other = (PaymentRefSumChangeResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.paymentRefSumChanges==null && other.getPaymentRefSumChanges()==null) || 
             (this.paymentRefSumChanges!=null &&
              java.util.Arrays.equals(this.paymentRefSumChanges, other.getPaymentRefSumChanges())));
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
        if (getPaymentRefSumChanges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPaymentRefSumChanges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPaymentRefSumChanges(), i);
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
        new org.apache.axis.description.TypeDesc(PaymentRefSumChangeResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "paymentRefSumChangeResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentRefSumChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentRefSumChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "paymentRefSumChange"));
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
