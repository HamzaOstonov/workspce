/**
 * EndOperation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class EndOperation  implements java.io.Serializable {
    private java.lang.Short p100T12;

    private java.util.Calendar p1T12;

    private java.lang.String p2T12;

    private java.util.Calendar p3T12;

    public EndOperation() {
    }

    public EndOperation(
           java.lang.Short p100T12,
           java.util.Calendar p1T12,
           java.lang.String p2T12,
           java.util.Calendar p3T12) {
           this.p100T12 = p100T12;
           this.p1T12 = p1T12;
           this.p2T12 = p2T12;
           this.p3T12 = p3T12;
    }


    /**
     * Gets the p100T12 value for this EndOperation.
     * 
     * @return p100T12
     */
    public java.lang.Short getP100T12() {
        return p100T12;
    }


    /**
     * Sets the p100T12 value for this EndOperation.
     * 
     * @param p100T12
     */
    public void setP100T12(java.lang.Short p100T12) {
        this.p100T12 = p100T12;
    }


    /**
     * Gets the p1T12 value for this EndOperation.
     * 
     * @return p1T12
     */
    public java.util.Calendar getP1T12() {
        return p1T12;
    }


    /**
     * Sets the p1T12 value for this EndOperation.
     * 
     * @param p1T12
     */
    public void setP1T12(java.util.Calendar p1T12) {
        this.p1T12 = p1T12;
    }


    /**
     * Gets the p2T12 value for this EndOperation.
     * 
     * @return p2T12
     */
    public java.lang.String getP2T12() {
        return p2T12;
    }


    /**
     * Sets the p2T12 value for this EndOperation.
     * 
     * @param p2T12
     */
    public void setP2T12(java.lang.String p2T12) {
        this.p2T12 = p2T12;
    }


    /**
     * Gets the p3T12 value for this EndOperation.
     * 
     * @return p3T12
     */
    public java.util.Calendar getP3T12() {
        return p3T12;
    }


    /**
     * Sets the p3T12 value for this EndOperation.
     * 
     * @param p3T12
     */
    public void setP3T12(java.util.Calendar p3T12) {
        this.p3T12 = p3T12;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EndOperation)) return false;
        EndOperation other = (EndOperation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p100T12==null && other.getP100T12()==null) || 
             (this.p100T12!=null &&
              this.p100T12.equals(other.getP100T12()))) &&
            ((this.p1T12==null && other.getP1T12()==null) || 
             (this.p1T12!=null &&
              this.p1T12.equals(other.getP1T12()))) &&
            ((this.p2T12==null && other.getP2T12()==null) || 
             (this.p2T12!=null &&
              this.p2T12.equals(other.getP2T12()))) &&
            ((this.p3T12==null && other.getP3T12()==null) || 
             (this.p3T12!=null &&
              this.p3T12.equals(other.getP3T12())));
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
        if (getP100T12() != null) {
            _hashCode += getP100T12().hashCode();
        }
        if (getP1T12() != null) {
            _hashCode += getP1T12().hashCode();
        }
        if (getP2T12() != null) {
            _hashCode += getP2T12().hashCode();
        }
        if (getP3T12() != null) {
            _hashCode += getP3T12().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EndOperation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "endOperation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p100T12");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p100T12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p1T12");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p1T12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T12");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p3T12");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
