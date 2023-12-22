/**
 * SaveAccreditivSumChange.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class SaveAccreditivSumChange  implements java.io.Serializable {
    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String p1T1;

    private java.lang.String p2T21;

    private com.sbs.service.AccreditivSumChange accreditivSumChange;

    public SaveAccreditivSumChange() {
    }

    public SaveAccreditivSumChange(
           java.lang.String username,
           java.lang.String password,
           java.lang.String p1T1,
           java.lang.String p2T21,
           com.sbs.service.AccreditivSumChange accreditivSumChange) {
           this.username = username;
           this.password = password;
           this.p1T1 = p1T1;
           this.p2T21 = p2T21;
           this.accreditivSumChange = accreditivSumChange;
    }


    /**
     * Gets the username value for this SaveAccreditivSumChange.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this SaveAccreditivSumChange.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this SaveAccreditivSumChange.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this SaveAccreditivSumChange.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the p1T1 value for this SaveAccreditivSumChange.
     * 
     * @return p1T1
     */
    public java.lang.String getP1T1() {
        return p1T1;
    }


    /**
     * Sets the p1T1 value for this SaveAccreditivSumChange.
     * 
     * @param p1T1
     */
    public void setP1T1(java.lang.String p1T1) {
        this.p1T1 = p1T1;
    }


    /**
     * Gets the p2T21 value for this SaveAccreditivSumChange.
     * 
     * @return p2T21
     */
    public java.lang.String getP2T21() {
        return p2T21;
    }


    /**
     * Sets the p2T21 value for this SaveAccreditivSumChange.
     * 
     * @param p2T21
     */
    public void setP2T21(java.lang.String p2T21) {
        this.p2T21 = p2T21;
    }


    /**
     * Gets the accreditivSumChange value for this SaveAccreditivSumChange.
     * 
     * @return accreditivSumChange
     */
    public com.sbs.service.AccreditivSumChange getAccreditivSumChange() {
        return accreditivSumChange;
    }


    /**
     * Sets the accreditivSumChange value for this SaveAccreditivSumChange.
     * 
     * @param accreditivSumChange
     */
    public void setAccreditivSumChange(com.sbs.service.AccreditivSumChange accreditivSumChange) {
        this.accreditivSumChange = accreditivSumChange;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SaveAccreditivSumChange)) return false;
        SaveAccreditivSumChange other = (SaveAccreditivSumChange) obj;
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
            ((this.p2T21==null && other.getP2T21()==null) || 
             (this.p2T21!=null &&
              this.p2T21.equals(other.getP2T21()))) &&
            ((this.accreditivSumChange==null && other.getAccreditivSumChange()==null) || 
             (this.accreditivSumChange!=null &&
              this.accreditivSumChange.equals(other.getAccreditivSumChange())));
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
        if (getP2T21() != null) {
            _hashCode += getP2T21().hashCode();
        }
        if (getAccreditivSumChange() != null) {
            _hashCode += getAccreditivSumChange().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SaveAccreditivSumChange.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "saveAccreditivSumChange"));
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
        elemField.setFieldName("p2T21");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p2T21"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accreditivSumChange");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accreditivSumChange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "accreditivSumChange"));
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
