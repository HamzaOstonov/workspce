/**
 * DelegateResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class DelegateResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Delegate[] delegates;

    public DelegateResult() {
    }

    public DelegateResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Delegate[] delegates) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.delegates = delegates;
    }


    /**
     * Gets the delegates value for this DelegateResult.
     * 
     * @return delegates
     */
    public com.sbs.service.Delegate[] getDelegates() {
        return delegates;
    }


    /**
     * Sets the delegates value for this DelegateResult.
     * 
     * @param delegates
     */
    public void setDelegates(com.sbs.service.Delegate[] delegates) {
        this.delegates = delegates;
    }

    public com.sbs.service.Delegate getDelegates(int i) {
        return this.delegates[i];
    }

    public void setDelegates(int i, com.sbs.service.Delegate _value) {
        this.delegates[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DelegateResult)) return false;
        DelegateResult other = (DelegateResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.delegates==null && other.getDelegates()==null) || 
             (this.delegates!=null &&
              java.util.Arrays.equals(this.delegates, other.getDelegates())));
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
        if (getDelegates() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDelegates());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDelegates(), i);
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
        new org.apache.axis.description.TypeDesc(DelegateResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "delegateResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delegates");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delegates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "delegate"));
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
