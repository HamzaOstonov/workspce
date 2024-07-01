/**
 * PayResp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.munis;

public class PayResp  implements java.io.Serializable {
    private com.is.munis.Payment paym;

    private com.is.munis.Res rs;

    public PayResp() {
    }

    public PayResp(
           com.is.munis.Payment paym,
           com.is.munis.Res rs) {
           this.paym = paym;
           this.rs = rs;
    }


    /**
     * Gets the paym value for this PayResp.
     * 
     * @return paym
     */
    public com.is.munis.Payment getPaym() {
        return paym;
    }


    /**
     * Sets the paym value for this PayResp.
     * 
     * @param paym
     */
    public void setPaym(com.is.munis.Payment paym) {
        this.paym = paym;
    }


    /**
     * Gets the rs value for this PayResp.
     * 
     * @return rs
     */
    public com.is.munis.Res getRs() {
        return rs;
    }


    /**
     * Sets the rs value for this PayResp.
     * 
     * @param rs
     */
    public void setRs(com.is.munis.Res rs) {
        this.rs = rs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PayResp)) return false;
        PayResp other = (PayResp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paym==null && other.getPaym()==null) || 
             (this.paym!=null &&
              this.paym.equals(other.getPaym()))) &&
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
        if (getPaym() != null) {
            _hashCode += getPaym().hashCode();
        }
        if (getRs() != null) {
            _hashCode += getRs().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PayResp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://munis.is.com/", "payResp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paym");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paym"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://munis.is.com/", "payment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
