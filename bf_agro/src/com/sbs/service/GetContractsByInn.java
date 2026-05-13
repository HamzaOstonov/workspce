/**
 * GetContractsByInn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class GetContractsByInn  implements java.io.Serializable {
    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String bankInn;

    private java.lang.String clientInn;

    public GetContractsByInn() {
    }

    public GetContractsByInn(
           java.lang.String username,
           java.lang.String password,
           java.lang.String bankInn,
           java.lang.String clientInn) {
           this.username = username;
           this.password = password;
           this.bankInn = bankInn;
           this.clientInn = clientInn;
    }


    /**
     * Gets the username value for this GetContractsByInn.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this GetContractsByInn.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this GetContractsByInn.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this GetContractsByInn.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the bankInn value for this GetContractsByInn.
     * 
     * @return bankInn
     */
    public java.lang.String getBankInn() {
        return bankInn;
    }


    /**
     * Sets the bankInn value for this GetContractsByInn.
     * 
     * @param bankInn
     */
    public void setBankInn(java.lang.String bankInn) {
        this.bankInn = bankInn;
    }


    /**
     * Gets the clientInn value for this GetContractsByInn.
     * 
     * @return clientInn
     */
    public java.lang.String getClientInn() {
        return clientInn;
    }


    /**
     * Sets the clientInn value for this GetContractsByInn.
     * 
     * @param clientInn
     */
    public void setClientInn(java.lang.String clientInn) {
        this.clientInn = clientInn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetContractsByInn)) return false;
        GetContractsByInn other = (GetContractsByInn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.bankInn==null && other.getBankInn()==null) || 
             (this.bankInn!=null &&
              this.bankInn.equals(other.getBankInn()))) &&
            ((this.clientInn==null && other.getClientInn()==null) || 
             (this.clientInn!=null &&
              this.clientInn.equals(other.getClientInn())));
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
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getBankInn() != null) {
            _hashCode += getBankInn().hashCode();
        }
        if (getClientInn() != null) {
            _hashCode += getClientInn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetContractsByInn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "getContractsByInn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankInn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bankInn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientInn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientInn"));
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
