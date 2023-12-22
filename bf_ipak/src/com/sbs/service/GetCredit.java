/**
 * GetCredit.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class GetCredit  implements java.io.Serializable {
    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String p1T1;

    private java.lang.Integer p13T25;

    private java.lang.Short active;

    public GetCredit() {
    }

    public GetCredit(
           java.lang.String username,
           java.lang.String password,
           java.lang.String p1T1,
           java.lang.Integer p13T25,
           java.lang.Short active) {
           this.username = username;
           this.password = password;
           this.p1T1 = p1T1;
           this.p13T25 = p13T25;
           this.active = active;
    }


    /**
     * Gets the username value for this GetCredit.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this GetCredit.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this GetCredit.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this GetCredit.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the p1T1 value for this GetCredit.
     * 
     * @return p1T1
     */
    public java.lang.String getP1T1() {
        return p1T1;
    }


    /**
     * Sets the p1T1 value for this GetCredit.
     * 
     * @param p1T1
     */
    public void setP1T1(java.lang.String p1T1) {
        this.p1T1 = p1T1;
    }


    /**
     * Gets the p13T25 value for this GetCredit.
     * 
     * @return p13T25
     */
    public java.lang.Integer getP13T25() {
        return p13T25;
    }


    /**
     * Sets the p13T25 value for this GetCredit.
     * 
     * @param p13T25
     */
    public void setP13T25(java.lang.Integer p13T25) {
        this.p13T25 = p13T25;
    }


    /**
     * Gets the active value for this GetCredit.
     * 
     * @return active
     */
    public java.lang.Short getActive() {
        return active;
    }


    /**
     * Sets the active value for this GetCredit.
     * 
     * @param active
     */
    public void setActive(java.lang.Short active) {
        this.active = active;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetCredit)) return false;
        GetCredit other = (GetCredit) obj;
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
            ((this.p1T1==null && other.getP1T1()==null) || 
             (this.p1T1!=null &&
              this.p1T1.equals(other.getP1T1()))) &&
            ((this.p13T25==null && other.getP13T25()==null) || 
             (this.p13T25!=null &&
              this.p13T25.equals(other.getP13T25()))) &&
            ((this.active==null && other.getActive()==null) || 
             (this.active!=null &&
              this.active.equals(other.getActive())));
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
        if (getP1T1() != null) {
            _hashCode += getP1T1().hashCode();
        }
        if (getP13T25() != null) {
            _hashCode += getP13T25().hashCode();
        }
        if (getActive() != null) {
            _hashCode += getActive().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetCredit.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "getCredit"));
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
        elemField.setFieldName("p1T1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p1T1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p13T25");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p13T25"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("active");
        elemField.setXmlName(new javax.xml.namespace.QName("", "active"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
