/**
 * TransCost.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class TransCost  implements java.io.Serializable {
    private java.lang.Short p100T11;

    private java.lang.String p3T11;

    private java.lang.Double p4T11;

    private java.util.Calendar p99T11;

    public TransCost() {
    }

    public TransCost(
           java.lang.Short p100T11,
           java.lang.String p3T11,
           java.lang.Double p4T11,
           java.util.Calendar p99T11) {
           this.p100T11 = p100T11;
           this.p3T11 = p3T11;
           this.p4T11 = p4T11;
           this.p99T11 = p99T11;
    }


    /**
     * Gets the p100T11 value for this TransCost.
     * 
     * @return p100T11
     */
    public java.lang.Short getP100T11() {
        return p100T11;
    }


    /**
     * Sets the p100T11 value for this TransCost.
     * 
     * @param p100T11
     */
    public void setP100T11(java.lang.Short p100T11) {
        this.p100T11 = p100T11;
    }


    /**
     * Gets the p3T11 value for this TransCost.
     * 
     * @return p3T11
     */
    public java.lang.String getP3T11() {
        return p3T11;
    }


    /**
     * Sets the p3T11 value for this TransCost.
     * 
     * @param p3T11
     */
    public void setP3T11(java.lang.String p3T11) {
        this.p3T11 = p3T11;
    }


    /**
     * Gets the p4T11 value for this TransCost.
     * 
     * @return p4T11
     */
    public java.lang.Double getP4T11() {
        return p4T11;
    }


    /**
     * Sets the p4T11 value for this TransCost.
     * 
     * @param p4T11
     */
    public void setP4T11(java.lang.Double p4T11) {
        this.p4T11 = p4T11;
    }


    /**
     * Gets the p99T11 value for this TransCost.
     * 
     * @return p99T11
     */
    public java.util.Calendar getP99T11() {
        return p99T11;
    }


    /**
     * Sets the p99T11 value for this TransCost.
     * 
     * @param p99T11
     */
    public void setP99T11(java.util.Calendar p99T11) {
        this.p99T11 = p99T11;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransCost)) return false;
        TransCost other = (TransCost) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p100T11==null && other.getP100T11()==null) || 
             (this.p100T11!=null &&
              this.p100T11.equals(other.getP100T11()))) &&
            ((this.p3T11==null && other.getP3T11()==null) || 
             (this.p3T11!=null &&
              this.p3T11.equals(other.getP3T11()))) &&
            ((this.p4T11==null && other.getP4T11()==null) || 
             (this.p4T11!=null &&
              this.p4T11.equals(other.getP4T11()))) &&
            ((this.p99T11==null && other.getP99T11()==null) || 
             (this.p99T11!=null &&
              this.p99T11.equals(other.getP99T11())));
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
        if (getP100T11() != null) {
            _hashCode += getP100T11().hashCode();
        }
        if (getP3T11() != null) {
            _hashCode += getP3T11().hashCode();
        }
        if (getP4T11() != null) {
            _hashCode += getP4T11().hashCode();
        }
        if (getP99T11() != null) {
            _hashCode += getP99T11().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransCost.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "transCost"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p100T11");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p100T11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p3T11");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p4T11");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p4T11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p99T11");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p99T11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
