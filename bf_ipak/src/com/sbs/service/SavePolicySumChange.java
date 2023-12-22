/**
 * SavePolicySumChange.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class SavePolicySumChange  implements java.io.Serializable {
    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String p1T1;

    private java.lang.String p3T32;

    private com.sbs.service.PolicySumChange policySumChange;

    public SavePolicySumChange() {
    }

    public SavePolicySumChange(
           java.lang.String username,
           java.lang.String password,
           java.lang.String p1T1,
           java.lang.String p3T32,
           com.sbs.service.PolicySumChange policySumChange) {
           this.username = username;
           this.password = password;
           this.p1T1 = p1T1;
           this.p3T32 = p3T32;
           this.policySumChange = policySumChange;
    }


    /**
     * Gets the username value for this SavePolicySumChange.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this SavePolicySumChange.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this SavePolicySumChange.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this SavePolicySumChange.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the p1T1 value for this SavePolicySumChange.
     * 
     * @return p1T1
     */
    public java.lang.String getP1T1() {
        return p1T1;
    }


    /**
     * Sets the p1T1 value for this SavePolicySumChange.
     * 
     * @param p1T1
     */
    public void setP1T1(java.lang.String p1T1) {
        this.p1T1 = p1T1;
    }


    /**
     * Gets the p3T32 value for this SavePolicySumChange.
     * 
     * @return p3T32
     */
    public java.lang.String getP3T32() {
        return p3T32;
    }


    /**
     * Sets the p3T32 value for this SavePolicySumChange.
     * 
     * @param p3T32
     */
    public void setP3T32(java.lang.String p3T32) {
        this.p3T32 = p3T32;
    }


    /**
     * Gets the policySumChange value for this SavePolicySumChange.
     * 
     * @return policySumChange
     */
    public com.sbs.service.PolicySumChange getPolicySumChange() {
        return policySumChange;
    }


    /**
     * Sets the policySumChange value for this SavePolicySumChange.
     * 
     * @param policySumChange
     */
    public void setPolicySumChange(com.sbs.service.PolicySumChange policySumChange) {
        this.policySumChange = policySumChange;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SavePolicySumChange)) return false;
        SavePolicySumChange other = (SavePolicySumChange) obj;
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
            ((this.p3T32==null && other.getP3T32()==null) || 
             (this.p3T32!=null &&
              this.p3T32.equals(other.getP3T32()))) &&
            ((this.policySumChange==null && other.getPolicySumChange()==null) || 
             (this.policySumChange!=null &&
              this.policySumChange.equals(other.getPolicySumChange())));
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
        if (getP3T32() != null) {
            _hashCode += getP3T32().hashCode();
        }
        if (getPolicySumChange() != null) {
            _hashCode += getPolicySumChange().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SavePolicySumChange.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "savePolicySumChange"));
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
        elemField.setFieldName("p3T32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p3T32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policySumChange");
        elemField.setXmlName(new javax.xml.namespace.QName("", "policySumChange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "policySumChange"));
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
