/**
 * SaleReason.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class SaleReason  implements java.io.Serializable {
    private java.lang.Short p1T69;

    private java.lang.Double p2T69;

    public SaleReason() {
    }

    public SaleReason(
           java.lang.Short p1T69,
           java.lang.Double p2T69) {
           this.p1T69 = p1T69;
           this.p2T69 = p2T69;
    }


    /**
     * Gets the p1T69 value for this SaleReason.
     * 
     * @return p1T69
     */
    public java.lang.Short getP1T69() {
        return p1T69;
    }


    /**
     * Sets the p1T69 value for this SaleReason.
     * 
     * @param p1T69
     */
    public void setP1T69(java.lang.Short p1T69) {
        this.p1T69 = p1T69;
    }


    /**
     * Gets the p2T69 value for this SaleReason.
     * 
     * @return p2T69
     */
    public java.lang.Double getP2T69() {
        return p2T69;
    }


    /**
     * Sets the p2T69 value for this SaleReason.
     * 
     * @param p2T69
     */
    public void setP2T69(java.lang.Double p2T69) {
        this.p2T69 = p2T69;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SaleReason)) return false;
        SaleReason other = (SaleReason) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p1T69==null && other.getP1T69()==null) || 
             (this.p1T69!=null &&
              this.p1T69.equals(other.getP1T69()))) &&
            ((this.p2T69==null && other.getP2T69()==null) || 
             (this.p2T69!=null &&
              this.p2T69.equals(other.getP2T69())));
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
        if (getP1T69() != null) {
            _hashCode += getP1T69().hashCode();
        }
        if (getP2T69() != null) {
            _hashCode += getP2T69().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SaleReason.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "saleReason"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p1T69");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p1T69"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T69");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T69"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
