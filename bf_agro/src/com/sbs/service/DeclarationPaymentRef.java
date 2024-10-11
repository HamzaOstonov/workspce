/**
 * DeclarationPaymentRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class DeclarationPaymentRef  implements java.io.Serializable {
    private java.lang.String p1T70;

    private java.lang.Double p2T70;

    public DeclarationPaymentRef() {
    }

    public DeclarationPaymentRef(
           java.lang.String p1T70,
           java.lang.Double p2T70) {
           this.p1T70 = p1T70;
           this.p2T70 = p2T70;
    }


    /**
     * Gets the p1T70 value for this DeclarationPaymentRef.
     * 
     * @return p1T70
     */
    public java.lang.String getP1T70() {
        return p1T70;
    }


    /**
     * Sets the p1T70 value for this DeclarationPaymentRef.
     * 
     * @param p1T70
     */
    public void setP1T70(java.lang.String p1T70) {
        this.p1T70 = p1T70;
    }


    /**
     * Gets the p2T70 value for this DeclarationPaymentRef.
     * 
     * @return p2T70
     */
    public java.lang.Double getP2T70() {
        return p2T70;
    }


    /**
     * Sets the p2T70 value for this DeclarationPaymentRef.
     * 
     * @param p2T70
     */
    public void setP2T70(java.lang.Double p2T70) {
        this.p2T70 = p2T70;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeclarationPaymentRef)) return false;
        DeclarationPaymentRef other = (DeclarationPaymentRef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p1T70==null && other.getP1T70()==null) || 
             (this.p1T70!=null &&
              this.p1T70.equals(other.getP1T70()))) &&
            ((this.p2T70==null && other.getP2T70()==null) || 
             (this.p2T70!=null &&
              this.p2T70.equals(other.getP2T70())));
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
        if (getP1T70() != null) {
            _hashCode += getP1T70().hashCode();
        }
        if (getP2T70() != null) {
            _hashCode += getP2T70().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeclarationPaymentRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "declarationPaymentRef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p1T70");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p1T70"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p2T70");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T70"));
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
