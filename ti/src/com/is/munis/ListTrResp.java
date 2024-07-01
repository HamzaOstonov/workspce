/**
 * ListTrResp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.munis;

public class ListTrResp  implements java.io.Serializable {
    private com.is.munis.Payment[] lpay;

    private com.is.munis.Res rs;

    public ListTrResp() {
    }

    public ListTrResp(
           com.is.munis.Payment[] lpay,
           com.is.munis.Res rs) {
           this.lpay = lpay;
           this.rs = rs;
    }


    /**
     * Gets the lpay value for this ListTrResp.
     * 
     * @return lpay
     */
    public com.is.munis.Payment[] getLpay() {
        return lpay;
    }


    /**
     * Sets the lpay value for this ListTrResp.
     * 
     * @param lpay
     */
    public void setLpay(com.is.munis.Payment[] lpay) {
        this.lpay = lpay;
    }

    public com.is.munis.Payment getLpay(int i) {
        return this.lpay[i];
    }

    public void setLpay(int i, com.is.munis.Payment _value) {
        this.lpay[i] = _value;
    }


    /**
     * Gets the rs value for this ListTrResp.
     * 
     * @return rs
     */
    public com.is.munis.Res getRs() {
        return rs;
    }


    /**
     * Sets the rs value for this ListTrResp.
     * 
     * @param rs
     */
    public void setRs(com.is.munis.Res rs) {
        this.rs = rs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListTrResp)) return false;
        ListTrResp other = (ListTrResp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lpay==null && other.getLpay()==null) || 
             (this.lpay!=null &&
              java.util.Arrays.equals(this.lpay, other.getLpay()))) &&
            ((this.rs==null && other.getRs()==null) || 
             (this.rs!=null &&
              this.rs.equals(other.getRs())));
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
        if (getLpay() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLpay());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLpay(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRs() != null) {
            _hashCode += getRs().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListTrResp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://munis.is.com/", "listTrResp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lpay");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lpay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://munis.is.com/", "payment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://munis.is.com/", "res"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
