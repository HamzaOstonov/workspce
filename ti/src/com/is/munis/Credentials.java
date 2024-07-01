/**
 * Credentials.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.munis;

public class Credentials  implements java.io.Serializable {
    private java.lang.String pw;

    private java.lang.String un;

    public Credentials() {
    }

    public Credentials(
           java.lang.String pw,
           java.lang.String un) {
           this.pw = pw;
           this.un = un;
    }


    /**
     * Gets the pw value for this Credentials.
     * 
     * @return pw
     */
    public java.lang.String getPw() {
        return pw;
    }


    /**
     * Sets the pw value for this Credentials.
     * 
     * @param pw
     */
    public void setPw(java.lang.String pw) {
        this.pw = pw;
    }


    /**
     * Gets the un value for this Credentials.
     * 
     * @return un
     */
    public java.lang.String getUn() {
        return un;
    }


    /**
     * Sets the un value for this Credentials.
     * 
     * @param un
     */
    public void setUn(java.lang.String un) {
        this.un = un;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Credentials)) return false;
        Credentials other = (Credentials) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pw==null && other.getPw()==null) || 
             (this.pw!=null &&
              this.pw.equals(other.getPw()))) &&
            ((this.un==null && other.getUn()==null) || 
             (this.un!=null &&
              this.un.equals(other.getUn())));
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
        if (getPw() != null) {
            _hashCode += getPw().hashCode();
        }
        if (getUn() != null) {
            _hashCode += getUn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Credentials.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://munis.is.com/", "credentials"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pw");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pw"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("un");
        elemField.setXmlName(new javax.xml.namespace.QName("", "un"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
