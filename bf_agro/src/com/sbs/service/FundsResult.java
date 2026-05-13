/**
 * FundsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class FundsResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Fund[] funds;

    public FundsResult() {
    }

    public FundsResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Fund[] funds) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.funds = funds;
    }


    /**
     * Gets the funds value for this FundsResult.
     * 
     * @return funds
     */
    public com.sbs.service.Fund[] getFunds() {
        return funds;
    }


    /**
     * Sets the funds value for this FundsResult.
     * 
     * @param funds
     */
    public void setFunds(com.sbs.service.Fund[] funds) {
        this.funds = funds;
    }

    public com.sbs.service.Fund getFunds(int i) {
        return this.funds[i];
    }

    public void setFunds(int i, com.sbs.service.Fund _value) {
        this.funds[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FundsResult)) return false;
        FundsResult other = (FundsResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.funds==null && other.getFunds()==null) || 
             (this.funds!=null &&
              java.util.Arrays.equals(this.funds, other.getFunds())));
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
        if (getFunds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFunds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFunds(), i);
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
        new org.apache.axis.description.TypeDesc(FundsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "fundsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "funds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "fund"));
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
